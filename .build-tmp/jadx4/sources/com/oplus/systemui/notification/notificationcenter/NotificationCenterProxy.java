package com.oplus.systemui.notification.notificationcenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.HandlerExecutor;
import android.os.UserHandle;
import android.util.Log;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$callbackEvents$1$1$;
import com.oplus.dispatch.OCDHandler;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.notification.notificationcenter.antivoyeur.AonConfigManger;
import com.oplus.systemui.notification.notificationcenter.antivoyeur.AonConfigMangerImpl;
import com.oplus.systemui.notification.notificationcenter.ops.PackageStateObserver;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.receiver.RomUpdateReceiver;
import com.oplus.systemui.statusbar.notification.helper.NotificationCenterConnect;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.receiver.IReceiverListener;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationCenterProxy implements UserTracker.Callback {
    private static final String ACTION_MULTI_APP_ADDED = "oplus.intent.action.MULTI_APP_PACKAGE_ADDED";
    private static final String ACTION_MULTI_APP_REMOVED = "oplus.intent.action.MULTI_APP_PACKAGE_REMOVED";
    private static final String ACTION_PASSWORD_UNSPECIFIED = "oplus.intent.action.settings.PASSWORD_QUALITY_UNSPECIFIED";
    private static final String ACTION_ROM_UPDATE_CONFIG_SUCCESS = "oplus.intent.action.ROM_UPDATE_CONFIG_SUCCESS";
    private static final String COMPONENT_SAFE_PERMISSION = "oplus.permission.OPLUS_COMPONENT_SAFE";
    public static final Companion Companion = new Companion();
    private static final List<String> PROXY_BROADCAST_LIST;
    private static final List<String> PROXY_BROADCAST_LIST_FOR_ALL_USER;
    private static final String SAU_UPDATE_ACTION = "com.oplusos.sau.DATARES_UPDATE";
    private static final String TAG = "NotificationCenterProxy";
    private AonConfigManger aonConfigManger;
    private final NotificationCenterConnect centerConnect;
    private final List<Function0> clearActions = new ArrayList();
    private PackageStateObserver packageStateObserver;
    private ProxyActionListener proxyActionListener;
    private final Context systemContext;
    private Context userContext;
    private final UserTracker userTracker;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface ProxyActionListener extends NotificationServiceCaller {
        void notifyBroadcast(String str, Intent intent);

        void onUserChanged(int i, Context context);

        @Override // com.oplus.systemui.notification.notificationcenter.NotificationServiceCaller
        /* synthetic */ Object runAndWaitResult(Object obj, Function1 function1);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ProxyBroadcastReceiver extends BroadcastReceiver {
        public ProxyBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Uri data;
            String schemeSpecificPart;
            AonConfigManger aonConfigManger;
            AonConfigManger aonConfigManger2;
            String action = intent.getAction();
            if (action == null) {
                action = "";
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                KeyguardSettingProvider$.ExternalSyntheticOutline0.m("onReceive: ", action, "NotificationCenterProxy-->", "SystemUi--Notification");
            }
            ProxyActionListener proxyActionListener = NotificationCenterProxy.this.proxyActionListener;
            if (proxyActionListener != null) {
                proxyActionListener.notifyBroadcast(action, intent);
            }
            if ((action.equals("android.intent.action.PACKAGE_REMOVED") || action.equals(NotificationCenterProxy.ACTION_MULTI_APP_REMOVED)) && (data = intent.getData()) != null && (schemeSpecificPart = data.getSchemeSpecificPart()) != null && (aonConfigManger = NotificationCenterProxy.this.aonConfigManger) != null) {
                aonConfigManger.notifyPkgRemoved(schemeSpecificPart, action.equals(NotificationCenterProxy.ACTION_MULTI_APP_REMOVED));
            }
            if (!action.equals(NotificationCenterProxy.ACTION_PASSWORD_UNSPECIFIED) || (aonConfigManger2 = NotificationCenterProxy.this.aonConfigManger) == null) {
                return;
            }
            aonConfigManger2.setAllowAntiVoyeurEncryptedState(false);
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion()) {
            arrayList.add(SAU_UPDATE_ACTION);
        }
        PROXY_BROADCAST_LIST_FOR_ALL_USER = arrayList;
        List<String> listMutableListOf = CollectionsKt__CollectionsKt.mutableListOf(new String[]{"android.intent.action.LOCALE_CHANGED", "android.intent.action.BOOT_COMPLETED"});
        NotificationFeatureOption.INSTANCE.getClass();
        if (NotificationFeatureOption.isHardwareAonEnable()) {
            listMutableListOf.add(ACTION_PASSWORD_UNSPECIFIED);
        }
        PROXY_BROADCAST_LIST = listMutableListOf;
    }

    public NotificationCenterProxy(Context context, Context context2, UserTracker userTracker, NotificationCenterConnect notificationCenterConnect) {
        this.systemContext = context;
        this.userContext = context2;
        this.userTracker = userTracker;
        this.centerConnect = notificationCenterConnect;
    }

    public /* bridge */ /* synthetic */ void onBeforeUserSwitching(int i, Runnable runnable) {
        super.onBeforeUserSwitching(i, runnable);
    }

    public /* bridge */ /* synthetic */ void onProfilesChanged(List list) {
        super.onProfilesChanged(list);
    }

    public void onUserChanged(int i, Context context) {
        ProxyActionListener proxyActionListener = this.proxyActionListener;
        if (proxyActionListener != null) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "onUserChanged: " + context.getUser(), null, 8);
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(this.userContext.getUserId(), this.systemContext.getUserId(), "stopRegister: ", OplusPreventModeData.SEPARATOR), null, 8);
            AonConfigManger aonConfigManger = this.aonConfigManger;
            if (aonConfigManger != null) {
                ((AonConfigMangerImpl) aonConfigManger).stop();
            }
            PackageStateObserver packageStateObserver = this.packageStateObserver;
            if (packageStateObserver != null) {
                packageStateObserver.stopRegister();
            }
            Iterator<T> it = this.clearActions.iterator();
            while (it.hasNext()) {
                ((Function0) it.next()).invoke();
            }
            this.clearActions.clear();
            this.userTracker.removeCallback(this);
            this.userContext = context;
            startRegisterInner(proxyActionListener);
            proxyActionListener.onUserChanged(i, context);
        }
    }

    public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context) {
        super.onUserChanging(i, context);
    }

    public final void startRegister(final ProxyActionListener proxyActionListener) {
        SystemUIThreadUtil.runOnWorkThread(new Runnable() { // from class: com.oplus.systemui.notification.notificationcenter.NotificationCenterProxy.startRegister.1
            @Override // java.lang.Runnable
            public final void run() {
                NotificationCenterProxy notificationCenterProxy = NotificationCenterProxy.this;
                ProxyActionListener proxyActionListener2 = proxyActionListener;
                Companion companion = NotificationCenterProxy.Companion;
                notificationCenterProxy.startRegisterInner(proxyActionListener2);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v6, types: [com.oplus.systemui.notification.notificationcenter.NotificationCenterProxy$registerRusBroadcastForAllUser$receiver$1, com.oplusos.systemui.common.receiver.IReceiverListener] */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final void startRegisterInner(ProxyActionListener proxyActionListener) {
        Object notificationCenterProxy$$ExternalSyntheticLambda3;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "startRegister: system =" + this.systemContext.getUser() + " user=" + this.userContext.getUser(), null, 8);
        this.proxyActionListener = proxyActionListener;
        AonConfigManger aonConfigMangerCreate = AonConfigManger.Companion.create(this.userContext);
        this.aonConfigManger = aonConfigMangerCreate;
        if (aonConfigMangerCreate != null) {
            ((AonConfigMangerImpl) aonConfigMangerCreate).start();
        }
        PackageStateObserver packageStateObserver = new PackageStateObserver(this.userContext, this.centerConnect);
        this.packageStateObserver = packageStateObserver;
        packageStateObserver.startRegister();
        List<Function0> list = this.clearActions;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationCenterProxy-->registerRusBroadcastForAllUser: register for all users");
        }
        ?? r2 = new IReceiverListener() { // from class: com.oplus.systemui.notification.notificationcenter.NotificationCenterProxy$registerRusBroadcastForAllUser$receiver$1
            @Override // com.oplusos.systemui.common.receiver.IReceiverListener
            public final void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action == null) {
                    action = "";
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    KeyguardSettingProvider$.ExternalSyntheticOutline0.m("onReceive: ", action, "NotificationCenterProxy-->", "SystemUi--Notification");
                }
                NotificationCenterProxy.ProxyActionListener proxyActionListener2 = this.this$0.proxyActionListener;
                if (proxyActionListener2 != null) {
                    proxyActionListener2.notifyBroadcast(action, intent);
                }
            }
        };
        RomUpdateReceiver companion = RomUpdateReceiver.Companion.getInstance();
        if (companion != 0) {
            companion.addListener(r2);
        }
        NotificationCenterProxy$$ExternalSyntheticLambda5 notificationCenterProxy$$ExternalSyntheticLambda5 = new NotificationCenterProxy$$ExternalSyntheticLambda5();
        notificationCenterProxy$$ExternalSyntheticLambda5.f$1 = r2;
        VarHandle.storeStoreFence();
        list.add(notificationCenterProxy$$ExternalSyntheticLambda5);
        List<Function0> list2 = this.clearActions;
        Context context = this.systemContext;
        List<String> list3 = PROXY_BROADCAST_LIST_FOR_ALL_USER;
        if (list3.isEmpty()) {
            notificationCenterProxy$$ExternalSyntheticLambda3 = new NotificationCenterProxy$$ExternalSyntheticLambda3();
        } else {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(context.getUserId(), "registerNormalForAllUser(", "): ", "NotificationCenterProxy-->", "SystemUi--Notification");
            }
            IntentFilter intentFilter = new IntentFilter();
            Iterator<T> it = list3.iterator();
            while (it.hasNext()) {
                intentFilter.addAction((String) it.next());
            }
            ProxyBroadcastReceiver proxyBroadcastReceiver = new ProxyBroadcastReceiver();
            context.createContextAsUser(new UserHandle(-1), 0).registerReceiverForAllUsers(proxyBroadcastReceiver, intentFilter, "oplus.permission.OPLUS_COMPONENT_SAFE", null, 2);
            NotificationCenterProxy$$ExternalSyntheticLambda0 notificationCenterProxy$$ExternalSyntheticLambda0 = new NotificationCenterProxy$$ExternalSyntheticLambda0(3);
            notificationCenterProxy$$ExternalSyntheticLambda0.f$1 = context;
            notificationCenterProxy$$ExternalSyntheticLambda0.f$2 = proxyBroadcastReceiver;
            VarHandle.storeStoreFence();
            notificationCenterProxy$$ExternalSyntheticLambda3 = notificationCenterProxy$$ExternalSyntheticLambda0;
        }
        list2.add((Function0) notificationCenterProxy$$ExternalSyntheticLambda3);
        List<Function0> list4 = this.clearActions;
        Context context2 = this.userContext;
        List<String> list5 = PROXY_BROADCAST_LIST;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(context2.getUserId(), "registerNormal(", "): ", "NotificationCenterProxy-->", "SystemUi--Notification");
        }
        IntentFilter intentFilter2 = new IntentFilter();
        Iterator<T> it2 = list5.iterator();
        while (it2.hasNext()) {
            intentFilter2.addAction((String) it2.next());
        }
        ProxyBroadcastReceiver proxyBroadcastReceiver2 = new ProxyBroadcastReceiver();
        context2.registerReceiver(proxyBroadcastReceiver2, intentFilter2, null, null, 2);
        NotificationCenterProxy$$ExternalSyntheticLambda0 notificationCenterProxy$$ExternalSyntheticLambda1 = new NotificationCenterProxy$$ExternalSyntheticLambda0(2);
        notificationCenterProxy$$ExternalSyntheticLambda1.f$1 = context2;
        notificationCenterProxy$$ExternalSyntheticLambda1.f$2 = proxyBroadcastReceiver2;
        VarHandle.storeStoreFence();
        list4.add(notificationCenterProxy$$ExternalSyntheticLambda1);
        List<Function0> list6 = this.clearActions;
        Context context3 = this.userContext;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(context3.getUserId(), "registerMediaProviderReceiver(", "): ", "NotificationCenterProxy-->", "SystemUi--Notification");
        }
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction("android.intent.action.MEDIA_SCANNER_STARTED");
        intentFilter3.addDataScheme("file");
        ProxyBroadcastReceiver proxyBroadcastReceiver3 = new ProxyBroadcastReceiver();
        context3.registerReceiver(proxyBroadcastReceiver3, intentFilter3, 2);
        try {
            Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.constructor-impl(ResultKt.createFailure(th));
        }
        NotificationCenterProxy$$ExternalSyntheticLambda0 notificationCenterProxy$$ExternalSyntheticLambda2 = new NotificationCenterProxy$$ExternalSyntheticLambda0(0);
        notificationCenterProxy$$ExternalSyntheticLambda2.f$1 = context3;
        notificationCenterProxy$$ExternalSyntheticLambda2.f$2 = proxyBroadcastReceiver3;
        VarHandle.storeStoreFence();
        list6.add(notificationCenterProxy$$ExternalSyntheticLambda2);
        List<Function0> list7 = this.clearActions;
        Context context4 = this.systemContext;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(context4.getUserId(), "registerPkgBroadcastReceiver(", "): ", "NotificationCenterProxy-->", "SystemUi--Notification");
        }
        IntentFilter intentFilter4 = new IntentFilter();
        intentFilter4.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter4.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter4.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter4.addAction("android.intent.action.PACKAGE_DATA_CLEARED");
        intentFilter4.addAction(ACTION_MULTI_APP_ADDED);
        intentFilter4.addAction(ACTION_MULTI_APP_REMOVED);
        intentFilter4.addDataScheme("package");
        ProxyBroadcastReceiver proxyBroadcastReceiver4 = new ProxyBroadcastReceiver();
        Context contextCreateContextAsUser = context4.createContextAsUser(new UserHandle(-1), 0);
        contextCreateContextAsUser.registerReceiverForAllUsers(proxyBroadcastReceiver4, intentFilter4, null, null, 2);
        NotificationCenterProxy$$ExternalSyntheticLambda0 notificationCenterProxy$$ExternalSyntheticLambda4 = new NotificationCenterProxy$$ExternalSyntheticLambda0(1);
        notificationCenterProxy$$ExternalSyntheticLambda4.f$1 = contextCreateContextAsUser;
        notificationCenterProxy$$ExternalSyntheticLambda4.f$2 = proxyBroadcastReceiver4;
        VarHandle.storeStoreFence();
        list7.add(notificationCenterProxy$$ExternalSyntheticLambda4);
        this.userTracker.addCallback(this, new HandlerExecutor(new OCDHandler(SystemUIThreadUtil.getWorkLooper())));
    }

    public /* bridge */ /* synthetic */ void onUserChanging(int i, Context context, Runnable runnable) {
        super.onUserChanging(i, context, runnable);
    }
}

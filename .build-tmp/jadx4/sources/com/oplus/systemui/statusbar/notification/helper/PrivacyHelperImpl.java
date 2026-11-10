package com.oplus.systemui.statusbar.notification.helper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.util.ArraySet;
import android.util.Log;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.OplusNotificationMediaManagerExt;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.helper.NotificationCommHelper;
import com.android.systemui.statusbar.notification.helper.PrivacyHelper;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.SensitiveNotificationProtectionController;
import com.android.systemui.statusbar.policy.SensitiveNotificationProtectionControllerLogger;
import com.oplus.app.IOplusAccessControlObserver;
import com.oplus.app.OPlusAccessControlManager;
import com.oplus.app.OplusAccessControlInfo;
import com.oplus.systemui.notification.aon.AonAuthManager;
import com.oplus.systemui.notification.base.OplusPanelViewInteractor;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguard;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguardImpl;
import com.oplus.systemui.statusbar.observer.GlobalBannerSwitchObserver;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PrivacyHelperImpl extends PrivacyHelper {
    public static final Companion Companion = new Companion();
    private static final String NOTIFICATION_APP_LOCKER_SWITCH_KEY = "app_locker_switch";
    private static final String TAG = "PrivacyHelper";
    private final Lazy aonAuthManager;
    private final Set<Runnable> appEncryptListeners;
    public boolean appHiddenEnabled;
    private final Set<Runnable> appHideListeners;
    private Boolean appLockBlockNotificationType;
    public boolean appLockEnabledInner;
    private final PrivacyHelperImpl$bgHandler$1 bgHandler;
    private final Context context;
    private final ConcurrentHashMap<String, Integer> encryptedApps;
    private final ConcurrentHashMap<String, Integer> encryptedCloneApps;
    private final Lazy headsUpManager;
    private final ConcurrentHashMap<String, Integer> hiddenApps;
    private final ConcurrentHashMap<String, Integer> hiddenCloneApps;
    private final Lazy keyguardStateController;
    private final Lazy logBuffer;
    private final Lazy mainHandler;
    private final KeyguardUpdateMonitorCallback monitorCallback;
    private final Lazy notifPipeline;
    private final Lazy notificationDependOnKeyguard;
    private final Lazy notificationKeyguardHelper;
    private final Lazy notificationMediaManagerExt;
    private final Lazy notificationStackScrollLayoutController;
    private final Lazy oplusPanelViewInteractor;
    private final Lazy sensitiveNotificationProtectionController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AppLockBlockNotificationObserver extends AbstractObserver {
        public AppLockBlockNotificationObserver() {
        }

        @Override // com.oplusos.systemui.common.observer.AbstractObserver
        public String getKey() {
            return PrivacyHelperImpl.NOTIFICATION_APP_LOCKER_SWITCH_KEY;
        }

        @Override // com.oplusos.systemui.common.observer.AbstractObserver
        public Uri getUri(Context context) {
            return Settings.System.getUriFor(PrivacyHelperImpl.NOTIFICATION_APP_LOCKER_SWITCH_KEY);
        }

        @Override // com.oplusos.systemui.common.observer.AbstractObserver
        public final void onChange(boolean z) {
            super.onChange(z);
            MultiUserContentHelper.Companion companion = MultiUserContentHelper.Companion;
            PrivacyHelperImpl privacyHelperImpl = PrivacyHelperImpl.this;
            privacyHelperImpl.setAppLockBlockNotificationType(Boolean.valueOf(companion.getSystemIntValue(privacyHelperImpl.context, PrivacyHelperImpl.NOTIFICATION_APP_LOCKER_SWITCH_KEY, 1) == 1));
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [com.oplus.systemui.statusbar.notification.helper.PrivacyHelperImpl$bgHandler$1] */
    public PrivacyHelperImpl(Context context, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8, Lazy lazy9, Lazy lazy10, Lazy lazy11, Lazy lazy12) {
        this.context = context;
        this.mainHandler = lazy;
        this.notificationKeyguardHelper = lazy2;
        this.notificationMediaManagerExt = lazy3;
        this.notifPipeline = lazy4;
        this.aonAuthManager = lazy5;
        this.keyguardStateController = lazy6;
        this.notificationDependOnKeyguard = lazy7;
        this.sensitiveNotificationProtectionController = lazy8;
        this.logBuffer = lazy9;
        this.oplusPanelViewInteractor = lazy10;
        this.headsUpManager = lazy11;
        this.notificationStackScrollLayoutController = lazy12;
        OPlusAccessControlManager oPlusAccessControlManager = OPlusAccessControlManager.getInstance();
        int i = OPlusAccessControlManager.USER_CURRENT;
        this.appLockEnabledInner = oPlusAccessControlManager.getAccessControlEnabled("type_encrypt", i);
        this.encryptedApps = new ConcurrentHashMap<>();
        this.encryptedCloneApps = new ConcurrentHashMap<>();
        this.appHiddenEnabled = OPlusAccessControlManager.getInstance().getAccessControlEnabled("type_hide", i);
        this.hiddenApps = new ConcurrentHashMap<>();
        this.hiddenCloneApps = new ConcurrentHashMap<>();
        this.appHideListeners = new ArraySet();
        this.appEncryptListeners = new ArraySet();
        PrivacyHelperImpl$monitorCallback$1 privacyHelperImpl$monitorCallback$1 = new PrivacyHelperImpl$monitorCallback$1();
        privacyHelperImpl$monitorCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.monitorCallback = privacyHelperImpl$monitorCallback$1;
        final Looper looper = ((AonAuthManager) lazy5.get()).getAonBgHandler().getLooper();
        this.bgHandler = new Handler(looper) { // from class: com.oplus.systemui.statusbar.notification.helper.PrivacyHelperImpl$bgHandler$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                int i2 = message.what;
                Object[] objArr = 0;
                PrivacyHelperImpl privacyHelperImpl = this.this$0;
                char c = 1;
                if (i2 == 1) {
                    boolean zAccess$updateEncryptPkgs = PrivacyHelperImpl.access$updateEncryptPkgs(privacyHelperImpl);
                    boolean z = message.arg1 == 1;
                    Handler handler = (Handler) privacyHelperImpl.mainHandler.get();
                    handleMessage.1 r3 = new handleMessage.1(0);
                    r3.this$0 = privacyHelperImpl;
                    r3.$enabled = z;
                    r3.$listChanged = zAccess$updateEncryptPkgs;
                    VarHandle.storeStoreFence();
                    handler.post(r3);
                    return;
                }
                if (i2 == 2) {
                    if (PrivacyHelperImpl.access$updateEncryptPkgs(privacyHelperImpl)) {
                        Handler handler2 = (Handler) privacyHelperImpl.mainHandler.get();
                        PrivacyHelperImpl$bgHandler$1$handleMessage$2 privacyHelperImpl$bgHandler$1$handleMessage$2 = new PrivacyHelperImpl$bgHandler$1$handleMessage$2(objArr == true ? 1 : 0);
                        privacyHelperImpl$bgHandler$1$handleMessage$2.this$0 = privacyHelperImpl;
                        VarHandle.storeStoreFence();
                        handler2.post(privacyHelperImpl$bgHandler$1$handleMessage$2);
                        return;
                    }
                    return;
                }
                if (i2 != 3) {
                    if (i2 == 4 && PrivacyHelperImpl.access$updateHidePkgs(privacyHelperImpl)) {
                        Handler handler3 = (Handler) privacyHelperImpl.mainHandler.get();
                        PrivacyHelperImpl$bgHandler$1$handleMessage$2 privacyHelperImpl$bgHandler$1$handleMessage$3 = new PrivacyHelperImpl$bgHandler$1$handleMessage$2(c == true ? 1 : 0);
                        privacyHelperImpl$bgHandler$1$handleMessage$3.this$0 = privacyHelperImpl;
                        VarHandle.storeStoreFence();
                        handler3.post(privacyHelperImpl$bgHandler$1$handleMessage$3);
                        return;
                    }
                    return;
                }
                boolean zAccess$updateHidePkgs = PrivacyHelperImpl.access$updateHidePkgs(privacyHelperImpl);
                boolean z2 = message.arg1 == 1;
                Handler handler4 = (Handler) privacyHelperImpl.mainHandler.get();
                handleMessage.1 r4 = new handleMessage.1(1);
                r4.this$0 = privacyHelperImpl;
                r4.$enabled = z2;
                r4.$listChanged = zAccess$updateHidePkgs;
                VarHandle.storeStoreFence();
                handler4.post(r4);
            }
        };
        IOplusAccessControlObserver.Stub stub = new IOplusAccessControlObserver.Stub() { // from class: com.oplus.systemui.statusbar.notification.helper.PrivacyHelperImpl$accessObserver$1
            /* JADX WARN: Multi-variable type inference failed */
            public IBinder asBinder() {
                return this;
            }

            public void onEncryptEnableChange(boolean z) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    OplusLockIconView$.ExternalSyntheticOutline0.m("[onEncryptEnableChange] ", "PrivacyHelper-->", "SystemUi--Common", z);
                }
                removeMessages(1);
                removeMessages(2);
                sendMessage(obtainMessage(1, z ? 1 : 0, 0));
            }

            public void onEncryptStateChange(OplusAccessControlInfo oplusAccessControlInfo) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    Log.d("SystemUi--Common", "PrivacyHelper-->".concat("[onEncryptStateChange] " + oplusAccessControlInfo));
                }
                removeMessages(2);
                sendMessageDelayed(obtainMessage(2), 500L);
            }

            public void onHideEnableChange(boolean z) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    OplusLockIconView$.ExternalSyntheticOutline0.m("[onHideEnableChange] ", "PrivacyHelper-->", "SystemUi--Common", z);
                }
                removeMessages(3);
                removeMessages(4);
                sendMessage(obtainMessage(3, z ? 1 : 0, 0));
            }

            public void onHideStateChange(OplusAccessControlInfo oplusAccessControlInfo) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    Log.d("SystemUi--Common", "PrivacyHelper-->".concat("[onHideStateChange]" + oplusAccessControlInfo));
                }
                removeMessages(4);
                sendMessageDelayed(obtainMessage(4), 500L);
            }
        };
        OPlusAccessControlManager.getInstance().registerAccessControlObserver("type_encrypt", stub);
        OPlusAccessControlManager.getInstance().registerAccessControlObserver("type_hide", stub);
        ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).registerCallback(privacyHelperImpl$monitorCallback$1);
    }

    public static final boolean access$updateEncryptPkgs(PrivacyHelperImpl privacyHelperImpl) {
        LinkedHashMap linkedHashMap;
        Map accessControlAppsInfo = OPlusAccessControlManager.getInstance().getAccessControlAppsInfo("type_encrypt", OPlusAccessControlManager.USER_CURRENT);
        LinkedHashMap linkedHashMap2 = null;
        if (accessControlAppsInfo != null) {
            linkedHashMap = new LinkedHashMap();
            for (Map.Entry entry : accessControlAppsInfo.entrySet()) {
                if (((String) entry.getKey()) != null) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            }
        } else {
            linkedHashMap = null;
        }
        boolean zAreEqual = Intrinsics.areEqual(linkedHashMap, privacyHelperImpl.encryptedApps);
        if (!zAreEqual) {
            privacyHelperImpl.encryptedApps.clear();
            if (linkedHashMap != null) {
                privacyHelperImpl.encryptedApps.putAll(linkedHashMap);
            }
        }
        Map accessControlAppsInfo2 = OPlusAccessControlManager.getInstance().getAccessControlAppsInfo("type_encrypt", 999);
        if (accessControlAppsInfo2 != null) {
            linkedHashMap2 = new LinkedHashMap();
            for (Map.Entry entry2 : accessControlAppsInfo2.entrySet()) {
                if (((String) entry2.getKey()) != null) {
                    linkedHashMap2.put(entry2.getKey(), entry2.getValue());
                }
            }
        }
        boolean zAreEqual2 = Intrinsics.areEqual(linkedHashMap2, privacyHelperImpl.encryptedCloneApps);
        if (!zAreEqual2) {
            privacyHelperImpl.encryptedCloneApps.clear();
            if (linkedHashMap2 != null) {
                privacyHelperImpl.encryptedCloneApps.putAll(linkedHashMap2);
            }
        }
        return (zAreEqual && zAreEqual2) ? false : true;
    }

    public static final boolean access$updateHidePkgs(PrivacyHelperImpl privacyHelperImpl) {
        LinkedHashMap linkedHashMap;
        Map accessControlAppsInfo = OPlusAccessControlManager.getInstance().getAccessControlAppsInfo("type_hide", OPlusAccessControlManager.USER_CURRENT);
        LinkedHashMap linkedHashMap2 = null;
        if (accessControlAppsInfo != null) {
            linkedHashMap = new LinkedHashMap();
            for (Map.Entry entry : accessControlAppsInfo.entrySet()) {
                if (((String) entry.getKey()) != null) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            }
        } else {
            linkedHashMap = null;
        }
        boolean zAreEqual = Intrinsics.areEqual(linkedHashMap, privacyHelperImpl.hiddenApps);
        if (!zAreEqual) {
            privacyHelperImpl.hiddenApps.clear();
            if (linkedHashMap != null) {
                privacyHelperImpl.hiddenApps.putAll(linkedHashMap);
            }
        }
        Map accessControlAppsInfo2 = OPlusAccessControlManager.getInstance().getAccessControlAppsInfo("type_hide", 999);
        if (accessControlAppsInfo2 != null) {
            linkedHashMap2 = new LinkedHashMap();
            for (Map.Entry entry2 : accessControlAppsInfo2.entrySet()) {
                if (((String) entry2.getKey()) != null) {
                    linkedHashMap2.put(entry2.getKey(), entry2.getValue());
                }
            }
        }
        boolean zAreEqual2 = Intrinsics.areEqual(linkedHashMap2, privacyHelperImpl.hiddenCloneApps);
        if (!zAreEqual2) {
            privacyHelperImpl.hiddenCloneApps.clear();
            if (linkedHashMap2 != null) {
                privacyHelperImpl.hiddenCloneApps.putAll(linkedHashMap2);
            }
        }
        return (zAreEqual && zAreEqual2) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getAppLockEnabled() {
        return this.appLockEnabledInner && Intrinsics.areEqual(this.appLockBlockNotificationType, Boolean.TRUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAppHiddenEnabled(boolean z) {
        if (this.appHiddenEnabled != z) {
            this.appHiddenEnabled = z;
            notifyAppHideListener();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAppLockBlockNotificationType(Boolean bool) {
        if (bool == null || Intrinsics.areEqual(this.appLockBlockNotificationType, bool)) {
            return;
        }
        if (this.appLockBlockNotificationType == null) {
            this.appLockBlockNotificationType = bool;
            return;
        }
        boolean appLockEnabled = getAppLockEnabled();
        this.appLockBlockNotificationType = bool;
        if (appLockEnabled != getAppLockEnabled()) {
            notifyAppEncryptListener();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAppLockEnabledInner(boolean z) {
        if (this.appLockEnabledInner != z) {
            boolean appLockEnabled = getAppLockEnabled();
            this.appLockEnabledInner = z;
            if (appLockEnabled != getAppLockEnabled()) {
                notifyAppEncryptListener();
            }
        }
    }

    public void addAppHideListener(Runnable runnable) {
        this.appHideListeners.add(runnable);
    }

    public void addEncryptedChangedListener(Runnable runnable) {
        this.appEncryptListeners.add(runnable);
    }

    public boolean isAppHidden(String str, int i) {
        if (!this.appHiddenEnabled) {
            return false;
        }
        int userId = UserHandle.getUserId(i);
        if (userId == OPlusAccessControlManager.USER_CURRENT) {
            Integer num = this.hiddenApps.get(str);
            return ((num != null ? num.intValue() : 0) & 4) != 0;
        }
        if (userId == 999) {
            Integer num2 = this.hiddenCloneApps.get(str);
            if (((num2 != null ? num2.intValue() : 0) & 4) != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isAppLocked(String str, int i) {
        if (!getAppLockEnabled()) {
            return false;
        }
        int userId = UserHandle.getUserId(i);
        if (userId == OPlusAccessControlManager.USER_CURRENT) {
            return this.encryptedApps.containsKey(str);
        }
        if (userId == 999) {
            return this.encryptedCloneApps.containsKey(str);
        }
        return false;
    }

    public boolean needEncrypted(String str, int i, String str2, NotificationEntry notificationEntry, boolean z) {
        NotificationEntry entry;
        StatusBarNotification sbn;
        Notification notification;
        Integer numValueOf = null;
        if (notificationEntry == null) {
            entry = str2 != null ? ((NotifPipeline) this.notifPipeline.get()).getEntry(str2) : null;
        } else {
            entry = notificationEntry;
        }
        String key = entry != null ? entry.getKey() : null;
        Companion.getClass();
        if (notificationEntry != null && (sbn = notificationEntry.getSbn()) != null && (notification = sbn.getNotification()) != null && "source_from_wechat".equals(notification.extras.getString("notification_source"))) {
            numValueOf = Integer.valueOf(notification.extras.getInt("envelope_related_uid"));
        }
        if (numValueOf != null) {
            i = numValueOf.intValue();
        }
        if (((OplusNotificationMediaManagerExt) this.notificationMediaManagerExt.get()).isMediaNotification(entry)) {
            return false;
        }
        if (isAppLocked(str, i)) {
            ((SensitiveNotificationProtectionControllerLogger) this.logBuffer.get()).logPrivacyReason(str, "isInAppEncryptList");
            return true;
        }
        if (entry == null || key == null) {
            return false;
        }
        if (NotificationCommHelper.Companion.getInstance().isKeyguardShowing && !((KeyguardStateController) this.keyguardStateController.get()).mKeyguardGoingAway && ((NotificationDependOnKeyguardImpl) ((NotificationDependOnKeyguard) this.notificationDependOnKeyguard.get())).hasSecurityKeyguard() && shouldHideNotiContentWhenLockScreen(entry, key)) {
            ((SensitiveNotificationProtectionControllerLogger) this.logBuffer.get()).logPrivacyReason(str, "keyguard hide notification");
            return true;
        }
        if (((SensitiveNotificationProtectionController) this.sensitiveNotificationProtectionController.get()).shouldProtectNotification(entry)) {
            ((SensitiveNotificationProtectionControllerLogger) this.logBuffer.get()).logPrivacyReason(str, " is sensitive notification");
            return true;
        }
        if (!((AonAuthManager) this.aonAuthManager.get()).isAonEnable() || Intrinsics.areEqual(((AonAuthManager) this.aonAuthManager.get()).getAonAuthResult(), Boolean.TRUE)) {
            return false;
        }
        return (!((Boolean) ((OplusPanelViewInteractor) this.oplusPanelViewInteractor.get()).isVisibility().getValue()).booleanValue() && ((HeadsUpManager) this.headsUpManager.get()).isHeadsUpEntry(entry.getKey())) || z;
    }

    public boolean needsRedactionOverride(NotificationEntry notificationEntry) {
        if (isAppLocked(notificationEntry.getSbn().getPackageName(), notificationEntry.getSbn().getUid()) || ((SensitiveNotificationProtectionController) this.sensitiveNotificationProtectionController.get()).shouldProtectNotification(notificationEntry) || shouldHideNotiContentWhenLockScreen(notificationEntry, notificationEntry.getKey())) {
            return true;
        }
        return ((AonAuthManager) this.aonAuthManager.get()).isAonEnable() && GlobalBannerSwitchObserver.INSTANCE.getGlobalBannerSwitchEnabled();
    }

    public final void notifyAppEncryptListener() {
        Iterator<T> it = this.appEncryptListeners.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
    }

    public final void notifyAppHideListener() {
        Iterator<T> it = this.appHideListeners.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
    }

    public void removeAppHideListener(Runnable runnable) {
        this.appHideListeners.remove(runnable);
    }

    public void removeEncryptedChangedListener(Runnable runnable) {
        this.appEncryptListeners.remove(runnable);
    }

    public boolean shouldHideNotiContentWhenLockScreen(NotificationEntry notificationEntry, String str) {
        NotificationChannel channel;
        if (((NotificationKeyguardHelper) this.notificationKeyguardHelper.get()).mLockScreenNotificationState == 2) {
            return true;
        }
        if (notificationEntry == null) {
            notificationEntry = str != null ? ((NotifPipeline) this.notifPipeline.get()).getEntry(str) : null;
        }
        if (notificationEntry != null) {
            return notificationEntry.getRanking().getLockscreenVisibilityOverride() == 0 || ((channel = notificationEntry.getChannel()) != null && channel.getLockscreenVisibility() == 0);
        }
        return false;
    }

    public String toString() {
        boolean appLockEnabled = getAppLockEnabled();
        boolean z = this.appHiddenEnabled;
        ConcurrentHashMap<String, Integer> concurrentHashMap = this.encryptedApps;
        ConcurrentHashMap<String, Integer> concurrentHashMap2 = this.encryptedCloneApps;
        ConcurrentHashMap<String, Integer> concurrentHashMap3 = this.hiddenApps;
        ConcurrentHashMap<String, Integer> concurrentHashMap4 = this.hiddenCloneApps;
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("PrivacyConfig: {appLockEnabled = ", ", AppHiddenIsOn = ", ", mEncryptedApps = ", appLockEnabled, z);
        sbM.append(concurrentHashMap);
        sbM.append(", mEncryptedCloneApps = ");
        sbM.append(concurrentHashMap2);
        sbM.append(", mHiddenApps = ");
        sbM.append(concurrentHashMap3);
        sbM.append(", mHiddenCloneApps = ");
        sbM.append(concurrentHashMap4);
        sbM.append("}");
        return sbM.toString();
    }
}

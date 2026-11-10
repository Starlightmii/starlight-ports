package com.oplus.systemui.statusbar.controller;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.preference.PreferenceManager;
import android.support.v4.content.FileProvider$;
import android.telecom.TelecomManager;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.compose.ui.spatial.RectList$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.metadata.UtilsKt$;
import com.android.systemui.Dumpable;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.authentication.shared.model.AuthenticationResultModel$;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.broadcast.ActionReceiver$;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.inputmethod.ui.viewmodel.ImeSwitcherMenuViewModel$MenuItem$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.oplus.systemui.statusbar.receiver.SystemPromptReceiver;
import com.oplus.systemui.statusbar.seeding.OplusSeedlingCardController;
import com.oplus.systemui.statusbar.util.OplusStatusBarUtils;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.feature.StatusBarFeatureOption;
import com.oplusos.systemui.common.observer.DevelopmentSettingsObserver;
import com.oplusos.systemui.common.settingsvalue.CommonSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.Util;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SystemPromptController implements Dumpable {
    private static final String CALL_STATE = "call_state";
    public static final Companion Companion = new Companion();
    private static final String INCALL_SCREEN_FORGROUND = "incall_screen_forground";
    private static final String INCALL_SCREEN_NAME_TAG = "incall_screen_name_tag";
    private static final String KEY_ACTION_NAME = "action_name";
    private static final String KEY_IN_ASSISTANT_MODE = "in_assistant_mode";
    private static final String KEY_TARGET_PACKAGE = "target_package";
    private static final String TAG = "SystemPromptController";
    private String assistantCallActivityActionName;
    private String assistantCallActivityPackageName;
    private final TelephonyCallback.CallStateListener callStateListener;
    private final Context context;
    public int currentShowMode;
    public int currentUserId;
    private final DumpManager dumpManager;
    public boolean inCallScreen;
    private String inCallScreenClassName;
    public boolean inCallScreenFromBroadcast;
    private final Runnable incallStateChangedRunning;
    public boolean isAssistantCall;
    private final Handler mainHandler;
    public int mode;
    private final NotificationManager notificationManager;
    private final OplusSeedlingCardController oplusSeedlingCardController;
    private final SystemPromptReceiver systemPromptReceiver;
    private final Runnable updateIconRunnable;
    private final CopyOnWriteArrayList<WeakReference<IconVisibleChange>> callbacks = new CopyOnWriteArrayList<>();
    public boolean phoneStateIdle = true;
    private Handler handler = new Handler(Looper.getMainLooper());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface IconVisibleChange {
        default Rect getIconRect() {
            return null;
        }

        void updateIcon(int i, boolean z, int i2, String str);
    }

    public SystemPromptController(final Context context, Handler handler, NotificationManager notificationManager, OplusSeedlingCardController oplusSeedlingCardController, DumpManager dumpManager) {
        this.context = context;
        this.mainHandler = handler;
        this.notificationManager = notificationManager;
        this.oplusSeedlingCardController = oplusSeedlingCardController;
        this.dumpManager = dumpManager;
        SystemPromptReceiver systemPromptReceiver = new SystemPromptReceiver(context) { // from class: com.oplus.systemui.statusbar.controller.SystemPromptController$systemPromptReceiver$1
            @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
            public final void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("receive action: ", action, "SystemPromptController-->", "SystemUi--Statusbar");
                }
                boolean zAreEqual = Intrinsics.areEqual(action, context2.getString(2131953579));
                SystemPromptController systemPromptController = this.this$0;
                if (!zAreEqual) {
                    if (Intrinsics.areEqual(action, "android.intent.action.BOOT_COMPLETED")) {
                        SystemPromptController.access$handleBootCompleted(systemPromptController);
                        return;
                    } else {
                        if (Intrinsics.areEqual(action, "android.intent.action.USER_SWITCHED")) {
                            systemPromptController.currentUserId = intent.getIntExtra("android.intent.extra.user_handle", 0);
                            systemPromptController.mode = 0;
                            systemPromptController.delayUpdatePromptIcon();
                            return;
                        }
                        return;
                    }
                }
                systemPromptController.inCallScreenFromBroadcast = intent.getBooleanExtra("incall_screen_forground", true);
                systemPromptController.inCallScreenClassName = intent.getStringExtra("incall_screen_name_tag");
                int intExtra = intent.getIntExtra("call_state", -1);
                systemPromptController.isAssistantCall = intent.getBooleanExtra("in_assistant_mode", false);
                systemPromptController.assistantCallActivityPackageName = intent.getStringExtra("target_package");
                systemPromptController.assistantCallActivityActionName = intent.getStringExtra("action_name");
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    boolean z = systemPromptController.inCallScreenFromBroadcast;
                    String str = systemPromptController.inCallScreenClassName;
                    boolean z2 = systemPromptController.isAssistantCall;
                    String str2 = systemPromptController.assistantCallActivityPackageName;
                    String str3 = systemPromptController.assistantCallActivityActionName;
                    StringBuilder sbM = AuthenticationResultModel$.ExternalSyntheticOutline0.m("receive ACTION_INCALLSCREEN_STATE_CHANGED mInCallScreenFromBroadcast= ", " mInCallScreenClassName = ", str, z, " callState = ");
                    ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(intExtra, " mIsAssistanCall = ", " mAssistantCallActivityPackageName = ", sbM, z2);
                    sbM.append(str2);
                    sbM.append(" mAssistantCallActivityActionName = ");
                    sbM.append(str3);
                    Log.d("SystemUi--Statusbar", "SystemPromptController-->".concat(sbM.toString()));
                }
                systemPromptController.handler.removeCallbacks(systemPromptController.incallStateChangedRunning);
                systemPromptController.handler.postDelayed(systemPromptController.incallStateChangedRunning, 200L);
            }
        };
        this.systemPromptReceiver = systemPromptReceiver;
        SystemPromptController$updateIconRunnable$1 systemPromptController$updateIconRunnable$1 = new SystemPromptController$updateIconRunnable$1(1);
        systemPromptController$updateIconRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.incallStateChangedRunning = systemPromptController$updateIconRunnable$1;
        SystemPromptController$updateIconRunnable$1 systemPromptController$updateIconRunnable$2 = new SystemPromptController$updateIconRunnable$1(0);
        systemPromptController$updateIconRunnable$2.this$0 = this;
        VarHandle.storeStoreFence();
        this.updateIconRunnable = systemPromptController$updateIconRunnable$2;
        SystemPromptController$callStateListener$1 systemPromptController$callStateListener$1 = new SystemPromptController$callStateListener$1();
        systemPromptController$callStateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.callStateListener = systemPromptController$callStateListener$1;
        this.currentUserId = KeyguardUpdateMonitor.getCurrentUser();
        FlavorTwoFeatureOption.INSTANCE.getClass();
        if (!FlavorTwoFeatureOption.isFlavorTwoDeviceExp()) {
            StatusBarFeatureOption.INSTANCE.getClass();
            if (!StatusBarFeatureOption.isHighlightNoDeveloper) {
                DevelopmentSettingsObserver companion = DevelopmentSettingsObserver.Companion.getInstance();
                SystemPromptController$initDeveloperMode$1 systemPromptController$initDeveloperMode$1 = new SystemPromptController$initDeveloperMode$1();
                systemPromptController$initDeveloperMode$1.this$0 = this;
                VarHandle.storeStoreFence();
                companion.addListener(systemPromptController$initDeveloperMode$1);
                updateDeveloperMode();
            }
        }
        systemPromptReceiver.register(context, UserHandle.ALL, (Handler) null);
        dumpManager.registerDumpable(this);
    }

    public static final void access$changeInCallState(SystemPromptController systemPromptController) {
        if (systemPromptController.inCallScreenClassName == null) {
            systemPromptController.inCallScreenClassName = systemPromptController.context.getString(2131953578);
        }
        boolean zIsActivityAtTop = Util.isActivityAtTop(systemPromptController.inCallScreenClassName);
        systemPromptController.inCallScreen = systemPromptController.inCallScreenFromBroadcast || zIsActivityAtTop;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(", mPhoneStateIdle=", "SystemPromptController-->", TextFieldImplKt$.ExternalSyntheticOutline0.m("changeInCallState() mInCallScreenFromBroadcast = ", ", inCallScreenFromTopStack = ", ", mInCallScreen = ", systemPromptController.inCallScreenFromBroadcast, zIsActivityAtTop), systemPromptController.inCallScreen, systemPromptController.phoneStateIdle));
        }
        if (systemPromptController.inCallScreen) {
            systemPromptController.setInCallMode(false);
        } else {
            if (systemPromptController.phoneStateIdle) {
                return;
            }
            systemPromptController.setInCallMode(true);
        }
    }

    public static final void access$handleBootCompleted(SystemPromptController systemPromptController) {
        Intent intent;
        boolean z = PreferenceManager.getDefaultSharedPreferences(systemPromptController.context).getBoolean("close_developer_reboot", false);
        boolean z2 = CommonSettingsValueProxy.Companion.getDevelopmentSettingsState(systemPromptController.context) != 0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("boot completed!!! ", "  ", "SystemPromptController-->", z2, z));
        }
        if (z2 && z) {
            Intent intent2 = new Intent("oplus.intent.action.settings.DISABLE_DEVELOPMENT");
            Context context = systemPromptController.context;
            Uri uri = Util.ROM_UPDATE_CONTENT_URI;
            List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent2, 0);
            if (listQueryIntentServices == null || listQueryIntentServices.size() != 1) {
                Log.w("Util", "resolveInfos null or empty");
                intent = null;
            } else {
                ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
                ComponentName componentName = new ComponentName(serviceInfo.packageName, serviceInfo.name);
                intent = new Intent(intent2);
                intent.setComponent(componentName);
            }
            if (intent != null) {
                systemPromptController.context.startService(intent);
            }
        }
    }

    public static final void access$updatePhoneStateIdle(SystemPromptController systemPromptController, String str) {
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, FileProvider$.ExternalSyntheticOutline0.m("updatePhoneStateIdle state:", str), null, 8);
        if (!Intrinsics.areEqual(TelephonyManager.EXTRA_STATE_IDLE, str)) {
            systemPromptController.phoneStateIdle = false;
            return;
        }
        systemPromptController.handler.removeCallbacks(systemPromptController.incallStateChangedRunning);
        systemPromptController.setInCallMode(false);
        systemPromptController.inCallScreen = true;
        systemPromptController.phoneStateIdle = true;
        systemPromptController.isAssistantCall = false;
        systemPromptController.assistantCallActivityActionName = null;
        systemPromptController.assistantCallActivityPackageName = null;
    }

    private static /* synthetic */ void getCallStateListener$annotations() {
    }

    private final void setInCallMode(boolean z) {
        int i = this.mode;
        if (z) {
            this.mode = i | 1;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(Integer.toHexString(this.mode), "SystemPromptController-->", "SystemUi--Statusbar", ExifInterface$.ExternalSyntheticOutline1.m("addPromptMode() oldMode=", Integer.toHexString(i), ", mode=", Integer.toHexString(1), ", sMode="));
            }
            if (i == this.mode) {
                return;
            }
            delayUpdatePromptIcon();
            return;
        }
        if ((i & 1) != 0) {
            this.mode = ~((~i) | 1);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(Integer.toHexString(this.mode), "SystemPromptController-->", "SystemUi--Statusbar", ExifInterface$.ExternalSyntheticOutline1.m("removePromptMode() oldMode:", Integer.toHexString(i), ", mode:", Integer.toHexString(1), ", sMode:"));
        }
        if (i == this.mode) {
            return;
        }
        delayUpdatePromptIcon();
    }

    public final void addCallback(IconVisibleChange iconVisibleChange) {
        this.callbacks.add(new WeakReference<>(iconVisibleChange));
    }

    public final void delayUpdatePromptIcon() {
        boolean zShouldShowSystemPromptView = this.oplusSeedlingCardController.shouldShowSystemPromptView();
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("shouldShowSystemPromptView ", ", shouldShow: ", zShouldShowSystemPromptView, isInCallMode()), null, 8);
        if (!zShouldShowSystemPromptView || !isInCallMode()) {
            updatePromptIcon();
        } else {
            this.mainHandler.removeCallbacks(this.updateIconRunnable);
            this.mainHandler.postDelayed(this.updateIconRunnable, 500L);
        }
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("SystemPromptController state:");
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("  mode: ", this.mode, printWriter);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("  currentShowMode: ", this.currentShowMode, printWriter);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "  inCallScreen: ", this.inCallScreen);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "  phoneStateIdle: ", this.phoneStateIdle);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "  isAssistantCall: ", this.isAssistantCall);
        ActionReceiver$.ExternalSyntheticOutline1.m("  accaName: ", this.assistantCallActivityActionName, printWriter);
        ActionReceiver$.ExternalSyntheticOutline1.m("  accpName: ", this.assistantCallActivityPackageName, printWriter);
        ActionReceiver$.ExternalSyntheticOutline1.m("  inCallScreenClassName: ", this.inCallScreenClassName, printWriter);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "  inCallScreenFromBroadcast: ", this.inCallScreenFromBroadcast);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("  currentUserId: ", this.currentUserId, printWriter);
    }

    public final void handleClick() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(this.mode, "handleClick: mode= ", "SystemPromptController-->", "SystemUi--Statusbar");
        }
        if (!isInCallMode() || this.inCallScreen) {
            return;
        }
        if (this.isAssistantCall && this.assistantCallActivityActionName != null && this.assistantCallActivityPackageName != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "SystemPromptController-->returnToInCallScreen assistant call");
            }
            Intent intent = new Intent();
            intent.setFlags(268435456);
            intent.setAction(this.assistantCallActivityActionName);
            intent.setPackage(this.assistantCallActivityPackageName);
            this.context.startActivity(intent);
            return;
        }
        try {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "SystemPromptController-->returnToInCallScreen");
            }
            ((TelecomManager) this.context.getSystemService("telecom")).showInCallScreen(false);
        } catch (SecurityException e) {
            LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG, "showInCallScreen failed e=" + e);
        }
    }

    public final boolean isInCallMode() {
        return (this.mode & 1) != 0;
    }

    public final void removeCallback(IconVisibleChange iconVisibleChange) {
        WeakReference weakReference = new WeakReference(iconVisibleChange);
        CopyOnWriteArrayList<WeakReference<IconVisibleChange>> copyOnWriteArrayList = this.callbacks;
        removeCallback.1 r2 = new removeCallback.1();
        r2.$callback = weakReference;
        VarHandle.storeStoreFence();
        copyOnWriteArrayList.removeIf(r2);
    }

    public final void updateDeveloperMode() {
        if (StatusBarFeatureOption.INSTANCE.getSendDeveloperModeNotification()) {
            if (!DevelopmentSettingsObserver.Companion.getInstance().isDevelopmentSettingsEnabled) {
                this.notificationManager.cancel(10002);
                return;
            }
            PendingIntent service = PendingIntent.getService(this.context, 0, UtilsKt$.ExternalSyntheticOutline0.m("oplus.intent.action.settings.DISABLE_DEVELOPMENT", "com.android.settings"), 33554432);
            String string = this.context.getString(2131953038);
            String string2 = this.context.getString(2131953037);
            service.getClass();
            Notification.Builder ongoing = new Notification.Builder(this.context, "INS").setSmallIcon(2131236741).setContentTitle(string).setShowWhen(true).setVisibility(1).setCategory("sys").setContentIntent(service).setOngoing(false);
            if (!TextUtils.isEmpty(string2)) {
                ongoing.setContentText(string2);
            }
            this.notificationManager.notify(null, 10002, ongoing.build());
        }
    }

    public final void updatePromptIcon() {
        String string;
        boolean z;
        IconVisibleChange iconVisibleChange;
        int i = 0;
        this.currentShowMode = 0;
        if (isInCallMode()) {
            this.currentShowMode = 1;
            z = true;
            i = 2131232788;
            string = this.context.getResources().getString(2131956680);
        } else {
            string = "";
            z = false;
        }
        String resourceName = OplusStatusBarUtils.getResourceName(i, this.context);
        int i2 = this.mode;
        int i3 = this.currentShowMode;
        int size = this.callbacks.size();
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("updatePromptIcon: icon = ", resourceName, " ,visible = ", z, " ,sMode = ");
        RectList$.ExternalSyntheticOutline0.m(i2, i3, ", currentShowMode: ", ", callbacks.size = ", sbM);
        sbM.append(size);
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, sbM.toString(), null, 8);
        Iterator<T> it = this.callbacks.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            if (weakReference != null && (iconVisibleChange = (IconVisibleChange) weakReference.get()) != null) {
                iconVisibleChange.updateIcon(i, z, this.currentShowMode, string);
            }
        }
    }
}

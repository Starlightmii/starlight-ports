package com.oplus.systemui.separate.guide;

import android.app.KeyguardManager;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.qs.GuideEvent;
import com.android.systemui.plugins.qs.GuideEventInfo;
import com.android.systemui.plugins.qs.QSGuide;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.app.OplusAppSwitchConfig;
import com.oplus.app.OplusAppSwitchManager;
import com.oplus.systemui.separate.QSSpecialModeUpdateMonitorImpl;
import com.oplus.systemui.separate.QsCenterModeSelectDialogController;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.common.utils.AppInfoUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsGuideEventController extends QSGuide {
    private static final String LAUNCHER_PACKAGE_NAME = "com.android.launcher";
    private static final String TAG = "OplusQsGuideEventController";
    private static final String WSN_DISPLAY_COMPLETE = "com.oplus.upgradeguide.wsn_display_complete";
    private final MutableStateFlow _shouldHideStatusBar;
    private final Lazy appSwitchObserver$delegate;
    private final Context context;
    private final DumpManager dumpManager;
    private final OplusQsGuideEventExternalTransfer guideEventTransfer;
    private final Lazy guideExportListener$delegate;
    private final Lazy headsUpChangedListener$delegate;
    private final HeadsUpManager headsUpManager;
    public boolean isStartListening;
    private final KeyguardStateController keyguardStateController;
    private final KeyguardUpdateMonitor keyguardUpdateMonitor;
    private final Lazy keyguardUpdateMonitorCallback$delegate;
    private final LogBuffer logBuffer;
    private final QsCenterModeSelectDialogController modeSelectGuideDialog;
    public int orientation;
    private final Lazy provisionCompletedObserver$delegate;
    private final StateFlow shouldHideStatusBar;
    private final QSSpecialModeUpdateMonitorImpl specialModeUpdateMonitor;
    private final Lazy specialModeUpdateMonitorListener$delegate;
    private final UserTracker userTracker;
    public static final Companion Companion = new Companion();
    private static final List<String> PACKAGE_CONFIG_LIST = CollectionsKt__CollectionsJVMKt.listOf("com.android.launcher");
    private static final String LAUNCHER_ACTIVITY_NAME = "com.android.launcher.Launcher";
    private static final List<String> ACTIVITY_CONFIG_LIST = CollectionsKt__CollectionsJVMKt.listOf(LAUNCHER_ACTIVITY_NAME);
    private final List<QSGuide.QsGuideCallback> guideCallbacks = new ArrayList();
    public boolean isLaunchAppAtTop = true;
    public float qsFraction = -1.0f;
    public float notificationFraction = -1.0f;

    /* JADX INFO: renamed from: com.oplus.systemui.separate.guide.OplusQsGuideEventController$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Dumpable {
        public /* synthetic */ OplusQsGuideEventController this$0;

        public final void dump(PrintWriter printWriter, String[] strArr) {
            this.this$0.logBuffer.dump(printWriter, 0);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQsGuideEventController(Context context, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardStateController keyguardStateController, HeadsUpManager headsUpManager, QSSpecialModeUpdateMonitorImpl qSSpecialModeUpdateMonitorImpl, OplusQsGuideEventExternalTransfer oplusQsGuideEventExternalTransfer, QsCenterModeSelectDialogController qsCenterModeSelectDialogController, UserTracker userTracker, LogBuffer logBuffer, DumpManager dumpManager) {
        this.context = context;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.keyguardStateController = keyguardStateController;
        this.headsUpManager = headsUpManager;
        this.specialModeUpdateMonitor = qSSpecialModeUpdateMonitorImpl;
        this.guideEventTransfer = oplusQsGuideEventExternalTransfer;
        this.modeSelectGuideDialog = qsCenterModeSelectDialogController;
        this.userTracker = userTracker;
        this.logBuffer = logBuffer;
        this.dumpManager = dumpManager;
        this.orientation = context.getResources().getConfiguration().orientation;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this._shouldHideStatusBar = MutableStateFlow;
        this.shouldHideStatusBar = FlowKt.asStateFlow(MutableStateFlow);
        OplusQsGuideEventController$$ExternalSyntheticLambda0 oplusQsGuideEventController$$ExternalSyntheticLambda0 = new OplusQsGuideEventController$$ExternalSyntheticLambda0(0);
        oplusQsGuideEventController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.appSwitchObserver$delegate = LazyKt__LazyJVMKt.lazy(oplusQsGuideEventController$$ExternalSyntheticLambda0);
        OplusQsGuideEventController$$ExternalSyntheticLambda0 oplusQsGuideEventController$$ExternalSyntheticLambda1 = new OplusQsGuideEventController$$ExternalSyntheticLambda0(1);
        oplusQsGuideEventController$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.specialModeUpdateMonitorListener$delegate = LazyKt__LazyJVMKt.lazy(oplusQsGuideEventController$$ExternalSyntheticLambda1);
        OplusQsGuideEventController$$ExternalSyntheticLambda0 oplusQsGuideEventController$$ExternalSyntheticLambda2 = new OplusQsGuideEventController$$ExternalSyntheticLambda0(2);
        oplusQsGuideEventController$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.headsUpChangedListener$delegate = LazyKt__LazyJVMKt.lazy(oplusQsGuideEventController$$ExternalSyntheticLambda2);
        OplusQsGuideEventController$$ExternalSyntheticLambda0 oplusQsGuideEventController$$ExternalSyntheticLambda3 = new OplusQsGuideEventController$$ExternalSyntheticLambda0(3);
        oplusQsGuideEventController$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.keyguardUpdateMonitorCallback$delegate = LazyKt__LazyJVMKt.lazy(oplusQsGuideEventController$$ExternalSyntheticLambda3);
        OplusQsGuideEventController$$ExternalSyntheticLambda0 oplusQsGuideEventController$$ExternalSyntheticLambda4 = new OplusQsGuideEventController$$ExternalSyntheticLambda0(4);
        oplusQsGuideEventController$$ExternalSyntheticLambda4.f$0 = this;
        VarHandle.storeStoreFence();
        this.provisionCompletedObserver$delegate = LazyKt__LazyJVMKt.lazy(oplusQsGuideEventController$$ExternalSyntheticLambda4);
        OplusQsGuideEventController$$ExternalSyntheticLambda0 oplusQsGuideEventController$$ExternalSyntheticLambda5 = new OplusQsGuideEventController$$ExternalSyntheticLambda0(5);
        oplusQsGuideEventController$$ExternalSyntheticLambda5.f$0 = this;
        VarHandle.storeStoreFence();
        this.guideExportListener$delegate = LazyKt__LazyJVMKt.lazy(oplusQsGuideEventController$$ExternalSyntheticLambda5);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, " init", null, 8);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        dumpManager.registerCriticalDumpable(TAG, anonymousClass1);
    }

    private final OplusQsGuideEventController$appSwitchObserver$2$1 getAppSwitchObserver() {
        return (OplusQsGuideEventController$appSwitchObserver$2$1) this.appSwitchObserver$delegate.getValue();
    }

    private final OplusQsGuideEventExternalTransfer.GuideExportListener getGuideExportListener() {
        return (OplusQsGuideEventExternalTransfer.GuideExportListener) this.guideExportListener$delegate.getValue();
    }

    private final OplusQsGuideEventController$headsUpChangedListener$2$1 getHeadsUpChangedListener() {
        return (OplusQsGuideEventController$headsUpChangedListener$2$1) this.headsUpChangedListener$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OplusQsGuideEventController$keyguardUpdateMonitorCallback$2$1 getKeyguardUpdateMonitorCallback() {
        return (OplusQsGuideEventController$keyguardUpdateMonitorCallback$2$1) this.keyguardUpdateMonitorCallback$delegate.getValue();
    }

    private final OplusQsGuideEventController$provisionCompletedObserver$2$1 getProvisionCompletedObserver() {
        return (OplusQsGuideEventController$provisionCompletedObserver$2$1) this.provisionCompletedObserver$delegate.getValue();
    }

    private final OplusQsGuideEventController$specialModeUpdateMonitorListener$2$1 getSpecialModeUpdateMonitorListener() {
        return (OplusQsGuideEventController$specialModeUpdateMonitorListener$2$1) this.specialModeUpdateMonitorListener$delegate.getValue();
    }

    public void addCallBack(QSGuide.QsGuideCallback qsGuideCallback) {
        this.guideCallbacks.add(qsGuideCallback);
        int i = 1;
        if (!this.guideCallbacks.isEmpty()) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "startListener, ".concat(this.isStartListening ? "already isStartListening return" : ""), null, 8);
            if (!this.isStartListening) {
                this.isStartListening = true;
                this.guideEventTransfer.addListener(getGuideExportListener());
                OplusAppSwitchManager oplusAppSwitchManager = OplusAppSwitchManager.getInstance();
                Context context = this.context;
                OplusQsGuideEventController$appSwitchObserver$2$1 appSwitchObserver = getAppSwitchObserver();
                OplusAppSwitchConfig oplusAppSwitchConfig = new OplusAppSwitchConfig();
                oplusAppSwitchConfig.addAppConfig(2, PACKAGE_CONFIG_LIST);
                oplusAppSwitchConfig.addAppConfig(1, ACTIVITY_CONFIG_LIST);
                oplusAppSwitchManager.registerAppSwitchObserver(context, appSwitchObserver, oplusAppSwitchConfig);
                this.specialModeUpdateMonitor.addListener(getSpecialModeUpdateMonitorListener());
                OplusQsGuideEventController$stopListener$1 oplusQsGuideEventController$stopListener$1 = new OplusQsGuideEventController$stopListener$1(i);
                oplusQsGuideEventController$stopListener$1.this$0 = this;
                VarHandle.storeStoreFence();
                SystemUIThreadUtil.runOnUiThread(oplusQsGuideEventController$stopListener$1);
                this.headsUpManager.addListener(getHeadsUpChangedListener());
                FeatureOption.INSTANCE.getClass();
                boolean zIsExpRegion = FeatureOption.isExpRegion();
                Context context2 = this.context;
                if (zIsExpRegion) {
                    context2.getContentResolver().registerContentObserver(Settings.Global.getUriFor("device_provisioned"), false, getProvisionCompletedObserver());
                } else {
                    context2.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(WSN_DISPLAY_COMPLETE), false, getProvisionCompletedObserver());
                }
                resetInnerState();
                notifyGuideEvent(GuideEvent.START_LISTENING);
            }
        }
        logBuffer("addCallBack: " + qsGuideCallback + " , size = " + this.guideCallbacks.size(), true);
    }

    public final boolean isHeadsUpNotificationShow() {
        return this.headsUpManager.hasPinnedHeadsUp();
    }

    public final boolean isKeyguardShowing() {
        KeyguardManager keyguardManager = (KeyguardManager) this.context.getSystemService(KeyguardManager.class);
        return keyguardManager != null ? keyguardManager.isKeyguardLocked() : this.keyguardStateController.mShowing;
    }

    public final boolean isLaunchAppAtTop() {
        return this.isLaunchAppAtTop;
    }

    public final boolean isMainUser() {
        return this.userTracker.getUserInfo().isMain();
    }

    public final boolean isNotificationVisible() {
        return this.notificationFraction == 1.0f;
    }

    public final boolean isProvisionComplete() {
        FeatureOption.INSTANCE.getClass();
        boolean zIsExpRegion = FeatureOption.isExpRegion();
        Context context = this.context;
        if (zIsExpRegion) {
            return Settings.Global.getInt(context.getContentResolver(), "device_provisioned", -1) != 0;
        }
        return Settings.Secure.getInt(context.getContentResolver(), WSN_DISPLAY_COMPLETE, -1) != 0;
    }

    public final boolean isQsVisible() {
        return this.qsFraction == 1.0f;
    }

    public final boolean isSelectDialogNeedShow() {
        return !this.modeSelectGuideDialog.getDialogDisplayedBySettings();
    }

    public final void logBuffer(String str, boolean z) {
        this.logBuffer.log(TAG, LogLevel.DEBUG, str, (Throwable) null);
        if (z && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsGuideEventController-->".concat(str));
        }
    }

    public final void notifyGuideEvent(GuideEvent guideEvent) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("notifyGuideEvent: ", guideEvent.name(), "OplusQsGuideEventController-->", "SystemUi--QuickSettings");
        }
        for (QSGuide.QsGuideCallback qsGuideCallback : this.guideCallbacks) {
            logBuffer("notifyGuideEvent: " + guideEvent.name() + ", callback: [ " + qsGuideCallback + " ]", false);
            qsGuideCallback.handleGuideEvent(guideEvent, GuideEventInfo.NONE);
        }
    }

    public void removeCallback(QSGuide.QsGuideCallback qsGuideCallback) {
        this.guideCallbacks.remove(qsGuideCallback);
        if (this.guideCallbacks.isEmpty()) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "stopListener, ".concat(!this.isStartListening ? "not isStartListening return" : ""), null, 8);
            if (this.isStartListening) {
                this.isStartListening = false;
                this.guideEventTransfer.removeListener(getGuideExportListener());
                OplusAppSwitchManager.getInstance().unregisterAppSwitchObserver(this.context, getAppSwitchObserver());
                this.specialModeUpdateMonitor.removeListener(getSpecialModeUpdateMonitorListener());
                OplusQsGuideEventController$stopListener$1 oplusQsGuideEventController$stopListener$1 = new OplusQsGuideEventController$stopListener$1(0);
                oplusQsGuideEventController$stopListener$1.this$0 = this;
                VarHandle.storeStoreFence();
                SystemUIThreadUtil.runOnUiThread(oplusQsGuideEventController$stopListener$1);
                this.headsUpManager.removeListener(getHeadsUpChangedListener());
                this.context.getContentResolver().unregisterContentObserver(getProvisionCompletedObserver());
                resetInnerState();
                notifyGuideEvent(GuideEvent.STOP_LISTENING);
            }
        }
        logBuffer("removeCallBack: " + qsGuideCallback + " , size = " + this.guideCallbacks.size(), true);
    }

    public final void resetInnerState() {
        String topActivityComponentName = AppInfoUtil.getTopActivityComponentName();
        this.isLaunchAppAtTop = Intrinsics.areEqual(topActivityComponentName, LAUNCHER_ACTIVITY_NAME);
        this.orientation = this.context.getResources().getConfiguration().orientation;
        this.qsFraction = this.guideEventTransfer.getQsFraction();
        float notificationFraction = this.guideEventTransfer.getNotificationFraction();
        this.notificationFraction = notificationFraction;
        int i = this.orientation;
        float f = this.qsFraction;
        StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i, " resetInnerState: ", topActivityComponentName, ", ", ", ");
        sbM.append(f);
        sbM.append(", ");
        sbM.append(notificationFraction);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
    }

    public final StateFlow shouldHideStatusBar() {
        logBuffer("shouldHideStatusBar:" + this.shouldHideStatusBar.getValue(), true);
        return this.shouldHideStatusBar;
    }

    public final void updateStatusBarVisible(boolean z, int i, boolean z2) {
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("updateStatusBarVisible:", i, ", ", ", ", z);
        sbM.append(z2);
        logBuffer(sbM.toString(), true);
        this._shouldHideStatusBar.setValue(Boolean.valueOf(!z));
    }
}

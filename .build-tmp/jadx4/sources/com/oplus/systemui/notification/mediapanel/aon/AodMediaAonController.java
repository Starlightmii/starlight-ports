package com.oplus.systemui.notification.mediapanel.aon;

import android.app.WindowConfiguration;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.provider.Settings;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.DependencyEx;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.aon.AonFaceGazeController;
import com.oplus.systemui.aon.AonSmartAodHEController;
import com.oplus.systemui.aon.IAonController;
import com.oplus.systemui.notification.mediapanel.util.AodMediaStatisticUtil;
import com.oplus.systemui.notification.mediapanel.util.AonStateUtils;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplusos.systemui.aod.mediapanel.AodMediaController;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AodMediaAonController implements ConfigurationController.ConfigurationListener, BatteryController.BatteryStateChangeCallback, IObserverListener, IAodMediaAonController {
    public static final Companion Companion = new Companion();
    private static final String KEY_AON_ENABLE_STATE = "Setting_AodSceneMusicUseAoN";
    private static final String KEY_AON_HE_EXPANDED_TIMEOUT_DURATION = "aon_HE_expanded_timeout_duration";
    private static final String TAG = "AodMediaAonController";
    private AodMediaController aodMediaController;
    public boolean aonEnable;
    private final aonEnableStateObserver.1 aonEnableStateObserver;
    private final IAonController.EventCallback aonFaceGazeCallback;
    private final AonFaceGazeController aonFaceGazeController;
    private final aonHEExpandedTimeoutDurationObserver.1 aonHEExpandedTimeoutDurationObserver;
    private final IAonController.EventCallback aonSmartAodHECallback;
    private final AonSmartAodHEController aonSmartAodHEController;
    private final Runnable capsuleProgressCancel;
    private final Runnable capsuleProgressStart;
    private final ConfigurationController configurationController;
    private final Context context;
    public int curRotation;
    private final Runnable gotoExpandedAction;
    private final Runnable lightsOnTimeoutAction;
    private final Handler mainHandler;
    private final Runnable openAodLightsOnAction;
    private final Runnable rebootAonSmartHEAction;
    private final Runnable requestApplyModeOrStateAction;
    private final Runnable smartHEExpandedTimeoutAction;
    private final AtomicInteger oldSmartAodHEEvent = new AtomicInteger(0);
    private final AtomicInteger oldFaceGazeEvent = new AtomicInteger(0);
    private final AtomicBoolean curIsLowBatteryLevel = new AtomicBoolean(false);
    private final AtomicBoolean curIsPowerSave = new AtomicBoolean(false);
    private final AtomicBoolean curIsSuperPowerSave = new AtomicBoolean(false);
    private final AtomicBoolean curIsAodMediaInShow = new AtomicBoolean(true);
    private final AtomicInteger curModeAndState = new AtomicInteger(0);
    private final AtomicLong faceGazeStopTime = new AtomicLong(0);
    private final AtomicLong smartAodHEStartTime = new AtomicLong(0);
    private final AtomicLong faceGazeStartTime = new AtomicLong(0);
    private final AtomicLong smartAodHETotalDuration = new AtomicLong(0);
    private final AtomicLong faceGazeTotalDuration = new AtomicLong(0);
    public long aonHEExpandedTimeoutDuration = 5000;

    public AodMediaAonController(Context context, Handler handler, AonSmartAodHEController aonSmartAodHEController, ConfigurationController configurationController, AonFaceGazeController aonFaceGazeController) {
        this.context = context;
        this.mainHandler = handler;
        this.aonSmartAodHEController = aonSmartAodHEController;
        this.configurationController = configurationController;
        this.aonFaceGazeController = aonFaceGazeController;
        this.curRotation = context.getResources().getConfiguration().windowConfiguration.getRotation();
        aonHEExpandedTimeoutDurationObserver.1 r7 = new aonHEExpandedTimeoutDurationObserver.1(this, handler);
        this.aonHEExpandedTimeoutDurationObserver = r7;
        aonEnableStateObserver.1 r2 = new aonEnableStateObserver.1(this, handler);
        this.aonEnableStateObserver = r2;
        gotoExpandedAction.1 r6 = new gotoExpandedAction.1(7);
        r6.this$0 = this;
        VarHandle.storeStoreFence();
        this.smartHEExpandedTimeoutAction = r6;
        gotoExpandedAction.1 r8 = new gotoExpandedAction.1(5);
        r8.this$0 = this;
        VarHandle.storeStoreFence();
        this.rebootAonSmartHEAction = r8;
        gotoExpandedAction.1 r9 = new gotoExpandedAction.1(3);
        r9.this$0 = this;
        VarHandle.storeStoreFence();
        this.lightsOnTimeoutAction = r9;
        gotoExpandedAction.1 r10 = new gotoExpandedAction.1(0);
        r10.this$0 = this;
        VarHandle.storeStoreFence();
        this.gotoExpandedAction = r10;
        gotoExpandedAction.1 r11 = new gotoExpandedAction.1(2);
        r11.this$0 = this;
        VarHandle.storeStoreFence();
        this.capsuleProgressStart = r11;
        gotoExpandedAction.1 r12 = new gotoExpandedAction.1(1);
        r12.this$0 = this;
        VarHandle.storeStoreFence();
        this.capsuleProgressCancel = r12;
        gotoExpandedAction.1 r13 = new gotoExpandedAction.1(4);
        r13.this$0 = this;
        VarHandle.storeStoreFence();
        this.openAodLightsOnAction = r13;
        gotoExpandedAction.1 r14 = new gotoExpandedAction.1(6);
        r14.this$0 = this;
        VarHandle.storeStoreFence();
        this.requestApplyModeOrStateAction = r14;
        aonFaceGazeCallback.1 r15 = new aonFaceGazeCallback.1(1);
        r15.this$0 = this;
        VarHandle.storeStoreFence();
        this.aonSmartAodHECallback = r15;
        aonFaceGazeCallback.1 r16 = new aonFaceGazeCallback.1(0);
        r16.this$0 = this;
        VarHandle.storeStoreFence();
        this.aonFaceGazeCallback = r16;
        NotificationFeatureOption.INSTANCE.getClass();
        if (NotificationFeatureOption.isSoftwareAonAodEnable()) {
            aonFaceGazeController.setPhoneOrientation(this.curRotation);
            configurationController.addCallback(this);
            BatteryControllerImpl batteryControllerImpl = (BatteryController) DependencyEx.sDependency.getDependency(BatteryController.class);
            if (batteryControllerImpl != null) {
                batteryControllerImpl.addCallback(this);
            }
            SuperPowerSaveSettingsObserver.Companion.getInstance().addListener(this);
            initHEExpandedTimeoutDuration();
            context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(KEY_AON_HE_EXPANDED_TIMEOUT_DURATION), false, r7, 0);
            initAonEnableState();
            context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(KEY_AON_ENABLE_STATE), false, r2, 0);
        }
    }

    public final void applyModeOrState(int i, boolean z) {
        AodMediaController aodMediaController;
        NotificationFeatureOption.INSTANCE.getClass();
        if (NotificationFeatureOption.isSoftwareAonAodEnable()) {
            if (LogUtil.getNormalDebug() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                String modeString = AonStateUtils.getModeString(i);
                String stateString = AonStateUtils.getStateString(i);
                String modeString2 = AonStateUtils.getModeString(this.curModeAndState.get());
                String stateString2 = AonStateUtils.getStateString(this.curModeAndState.get());
                boolean z2 = this.aonEnable;
                boolean zIsSoftwareAonAodEnable = NotificationFeatureOption.isSoftwareAonAodEnable();
                boolean z3 = this.curIsLowBatteryLevel.get();
                boolean z4 = this.curIsPowerSave.get();
                boolean z5 = this.curIsSuperPowerSave.get();
                StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("applyModeOrState newMode: ", modeString, " newState: ", stateString, " oldMode: ");
                ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, modeString2, " oldState: ", stateString2, " aonEnable: ");
                KeyguardLogger$.ExternalSyntheticOutline0.m(" isSoftwareAonAodEnable: ", " curIsLowBatteryLevel: ", sbM, z2, zIsSoftwareAonAodEnable);
                KeyguardLogger$.ExternalSyntheticOutline0.m(" curIsPowerSave: ", " curIsSuperPowerSave: ", sbM, z3, z4);
                SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("AodMediaAonController-->", "SystemUi--Notification", sbM, z5);
            }
            if (this.mainHandler.hasCallbacks(this.requestApplyModeOrStateAction)) {
                this.mainHandler.removeCallbacks(this.requestApplyModeOrStateAction);
            }
            if (this.mainHandler.hasCallbacks(this.smartHEExpandedTimeoutAction)) {
                this.mainHandler.removeCallbacks(this.smartHEExpandedTimeoutAction);
            }
            if (i <= 0 || (aodMediaController = this.aodMediaController) == null || !aodMediaController.hasMediaPlayer()) {
                if (this.curModeAndState.get() != 0) {
                    this.curModeAndState.set(0);
                    stopAon("AodMediaPanel_close", true);
                    return;
                }
                return;
            }
            int i2 = this.curModeAndState.get();
            int i3 = i & 12;
            if (i3 != 0) {
                i2 = (i2 & 3) | i3;
            }
            int i4 = i & 3;
            if (i4 != 0) {
                i2 = (i2 & 12) | i4;
            }
            if (i2 != this.curModeAndState.get() || z) {
                this.curModeAndState.set(i2);
                boolean zIsAonEnable = isAonEnable();
                stopAon("AodMediaPanel_refresh", !zIsAonEnable || (i2 & 12) == 0 || (i2 & 3) == 0);
                if (zIsAonEnable) {
                    AodMediaStatisticUtil.writeAodMediaAonInitTime(this.context);
                    int i5 = i2 & 12;
                    if (i5 != 8) {
                        if (i5 != 12 || (i2 & 3) == 0) {
                            return;
                        }
                        this.aonSmartAodHEController.startAon(this.aonSmartAodHECallback, 100L, "AodMediaPanel_regular");
                        return;
                    }
                    int i6 = i2 & 3;
                    if (i6 == 3) {
                        this.mainHandler.postDelayed(this.smartHEExpandedTimeoutAction, this.aonHEExpandedTimeoutDuration);
                        long jAbs = Math.abs(System.currentTimeMillis() - this.faceGazeStopTime.get());
                        this.aonSmartAodHEController.startAon(this.aonSmartAodHECallback, jAbs < 1000 ? 1000 - jAbs : 0L, "AodMediaPanel_lightsOnAndExpanded");
                    } else if (i6 == 1) {
                        this.aonFaceGazeController.startAon(this.aonFaceGazeCallback, 100L, "AodMediaPanel_lightsOnAndCollapse");
                    }
                }
            }
        }
    }

    @Override // com.oplus.systemui.notification.mediapanel.aon.IAodMediaAonController
    public void bindAodMediaController(AodMediaController aodMediaController) {
        this.aodMediaController = aodMediaController;
    }

    public final boolean initAonEnableState() {
        boolean z = this.aonEnable;
        boolean z2 = Settings.Secure.getIntForUser(this.context.getContentResolver(), KEY_AON_ENABLE_STATE, 0, 0) == 1;
        this.aonEnable = z2;
        return z2 != z;
    }

    public final boolean initHEExpandedTimeoutDuration() {
        long j = this.aonHEExpandedTimeoutDuration;
        long longForUser = Settings.Secure.getLongForUser(this.context.getContentResolver(), KEY_AON_HE_EXPANDED_TIMEOUT_DURATION, 5000L, 0);
        this.aonHEExpandedTimeoutDuration = longForUser;
        if (longForUser < 1500) {
            longForUser = 1500;
        }
        this.aonHEExpandedTimeoutDuration = longForUser;
        return longForUser != j;
    }

    public final boolean isAonEnable() {
        if (!this.aonEnable) {
            return false;
        }
        NotificationFeatureOption.INSTANCE.getClass();
        return (!NotificationFeatureOption.isSoftwareAonAodEnable() || this.curIsLowBatteryLevel.get() || this.curIsPowerSave.get() || this.curIsSuperPowerSave.get() || !this.curIsAodMediaInShow.get()) ? false : true;
    }

    @Override // com.oplus.systemui.notification.mediapanel.aon.IAodMediaAonController
    public final void onAodMediaVisibilityChanged(boolean z) {
        if (z != this.curIsAodMediaInShow.get()) {
            this.curIsAodMediaInShow.set(z);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("AodMedia isShow:", "AodMediaAonController-->", "SystemUi--Notification", z);
            }
            if (isAonEnable()) {
                applyModeOrState(this.curModeAndState.get(), true);
            } else {
                stopAon("AodMediaPanel_aodMediaVisibilityChanged", true);
            }
        }
    }

    public final void onBatteryLevelChanged(int i, boolean z, boolean z2) {
        boolean z3 = i <= 10;
        if (z3 != this.curIsLowBatteryLevel.get()) {
            this.curIsLowBatteryLevel.set(z3);
            if (isAonEnable()) {
                applyModeOrState(this.curModeAndState.get(), true);
            } else {
                stopAon("AodMediaPanel_batteryLow", true);
            }
        }
    }

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        boolean z2 = SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState;
        if (z2 != this.curIsSuperPowerSave.get()) {
            this.curIsSuperPowerSave.set(z2);
            if (isAonEnable()) {
                applyModeOrState(this.curModeAndState.get(), true);
            } else {
                stopAon("AodMediaPanel_superPowerSave", true);
            }
        }
    }

    public void onConfigChanged(Configuration configuration) {
        WindowConfiguration windowConfiguration;
        int rotation;
        if (configuration == null || (windowConfiguration = configuration.windowConfiguration) == null || (rotation = windowConfiguration.getRotation()) == this.curRotation) {
            return;
        }
        this.curRotation = rotation;
        if (isAonEnable()) {
            this.aonFaceGazeController.setPhoneOrientation(this.curRotation);
        }
    }

    public final void onPowerSaveChanged(boolean z) {
        if (z != this.curIsPowerSave.get()) {
            this.curIsPowerSave.set(z);
            if (isAonEnable()) {
                applyModeOrState(this.curModeAndState.get(), true);
            } else {
                stopAon("AodMediaPanel_powerSave", true);
            }
        }
    }

    @Override // com.oplus.systemui.notification.mediapanel.aon.IAodMediaAonController
    public final void resetFaceGazeHEIfNeed() {
        if (isAonEnable() && (this.curModeAndState.get() & 12) == 8 && (this.curModeAndState.get() & 3) == 3 && this.mainHandler.hasCallbacks(this.smartHEExpandedTimeoutAction)) {
            this.mainHandler.removeCallbacks(this.smartHEExpandedTimeoutAction);
            this.mainHandler.postDelayed(this.smartHEExpandedTimeoutAction, this.aonHEExpandedTimeoutDuration);
        }
    }

    public final void stopAon(String str, boolean z) {
        this.aonSmartAodHEController.stopAon(this.aonSmartAodHECallback, str);
        this.aonFaceGazeController.stopAon(this.aonFaceGazeCallback, str);
        if (z) {
            if (this.smartAodHETotalDuration.get() > 0 || this.faceGazeTotalDuration.get() > 0) {
                AodMediaStatisticUtil.writeAodMediaAonTotalTime(this.context, this.smartAodHETotalDuration.get(), this.faceGazeTotalDuration.get());
                this.smartAodHETotalDuration.set(0L);
                this.faceGazeTotalDuration.set(0L);
            }
        }
    }

    @Override // com.oplus.systemui.notification.mediapanel.aon.IAodMediaAonController
    public final void applyModeOrState(int i) {
        applyModeOrState(i, false);
    }
}

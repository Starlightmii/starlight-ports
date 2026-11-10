package com.oplus.systemui.statusbar.phone;

import android.content.Context;
import android.hardware.biometrics.BiometricSourceType;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import androidx.collection.FloatList$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardViewController;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.inputmethod.ui.viewmodel.ImeSwitcherMenuViewModel$MenuItem$;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.shared.model.BiometricUnlockSource;
import com.android.systemui.statusbar.phone.BiometricUnlockController;
import com.android.systemui.statusbar.phone.OplusBiometricUnlockControllerEx;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.keyguard.OplusKeyguardVibratorHelper;
import com.oplus.keyguard.OplusPowerBlockController;
import com.oplus.keyguard.security.verify.OplusKeyguardVerifyManagerExImpl;
import com.oplus.systemui.aod.common.DreamHelper;
import com.oplus.systemui.aod.common.IDreamPolicy;
import com.oplus.systemui.aod.display.AODDisplayUtil;
import com.oplus.systemui.biometrics.OplusBiometricAuthController;
import com.oplus.systemui.biometrics.finger.FingerSwitchHelper;
import com.oplus.systemui.biometrics.finger.FingerprintStateHelper;
import com.oplus.systemui.common.flavor.OplusFlavorManager;
import com.oplus.systemui.scene.OplusSceneModeObserver;
import com.oplus.systemui.subdisplay.FakeSubDisplayUiController;
import com.oplusos.keyguard.utils.KeyguardThreadUtil;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusBiometricUnlockControllerExImpl extends OplusBiometricUnlockControllerEx {
    public static final Companion Companion = new Companion();
    public static final int FINGERPRINT_FAILS_TO_SHOW_BOUNCER_ATTEMPTS;
    public static final String TAG = "OplusBiometricUnlockController";
    private final Context context;
    private final Lazy flavorManagerLazy;
    private final KeyguardStateController keyguardStateController;
    private final KeyguardUpdateMonitor keyguardUpdateMonitor;
    private final Lazy keyguardVerifyManagerLazy;
    private final Lazy keyguardViewControllerLazy;
    public int lastFpFailAttempts;
    public boolean mWakeupBouncerImmersive;
    private final Lazy oplusBiometricAuthControllerLazy;
    private final Lazy oplusKeyguardUpdateMonitorLazy;
    private final Lazy oplusKeyguardVibratorHelperLazy;
    private final Lazy oplusPowerBlockControllerLazy;
    private final Lazy oplusSubDisplayUiControllerLazy;
    private final PowerManager powerManager;
    private final Lazy primaryBouncerInteractorLazy;
    private final ScreenLifecycle screenLifecycle;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final int getFINGERPRINT_FAILS_TO_SHOW_BOUNCER_ATTEMPTS() {
            return OplusBiometricUnlockControllerExImpl.FINGERPRINT_FAILS_TO_SHOW_BOUNCER_ATTEMPTS;
        }
    }

    static {
        FlavorTwoFeatureOption.INSTANCE.getClass();
        FINGERPRINT_FAILS_TO_SHOW_BOUNCER_ATTEMPTS = FlavorTwoFeatureOption.isFlavorTwoDeviceExp() ? 3 : 4;
    }

    public OplusBiometricUnlockControllerExImpl(Context context, ScreenLifecycle screenLifecycle, PowerManager powerManager, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardStateController keyguardStateController, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8, Lazy lazy9) {
        this.context = context;
        this.screenLifecycle = screenLifecycle;
        this.powerManager = powerManager;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.keyguardStateController = keyguardStateController;
        this.oplusBiometricAuthControllerLazy = lazy;
        this.oplusSubDisplayUiControllerLazy = lazy2;
        this.keyguardVerifyManagerLazy = lazy3;
        this.oplusKeyguardVibratorHelperLazy = lazy4;
        this.oplusKeyguardUpdateMonitorLazy = lazy5;
        this.keyguardViewControllerLazy = lazy6;
        this.flavorManagerLazy = lazy7;
        this.primaryBouncerInteractorLazy = lazy8;
        this.oplusPowerBlockControllerLazy = lazy9;
    }

    public final void armFpTouchShowDelayIfNeeded() {
        if (this.powerManager.isInteractive()) {
            KgdLogUtil.i(TAG, "armFpTouchShowDelayIfNeeded skip, screen is interactive");
        } else {
            KgdLogUtil.i(TAG, "armFpTouchShowDelayIfNeeded armed");
            ((PrimaryBouncerInteractor) this.primaryBouncerInteractorLazy.get()).armAutomaticShowDelay(1000L, "FpTouch", "BiometricUnlockController#MODE_SHOW_BOUNCER");
        }
    }

    public boolean getWakeupBouncerImmersive() {
        return this.mWakeupBouncerImmersive;
    }

    public boolean handleOnBiometricAuthFailed(BiometricUnlockController biometricUnlockController) {
        showBouncerByFingerprint(false, biometricUnlockController);
        return true;
    }

    public boolean handleOnBiometricError(BiometricSourceType biometricSourceType, BiometricUnlockController biometricUnlockController, int i) {
        if (biometricSourceType == BiometricSourceType.FINGERPRINT && i == 2000) {
            showBouncerByFingerprint(true, biometricUnlockController);
        } else if (biometricSourceType == BiometricSourceType.FACE) {
            OplusBiometricAuthController oplusBiometricAuthController = (OplusBiometricAuthController) this.oplusBiometricAuthControllerLazy.get();
            int faceFailedAttempts = oplusBiometricAuthController.getFaceFailedAttempts();
            boolean z = !(oplusBiometricAuthController.getFaceLockoutAttemptDeadline() == 0 && this.keyguardUpdateMonitor.isFaceClass3()) && faceFailedAttempts % 5 == 0;
            boolean zPrimaryBouncerIsOrWillBeShowing = ((KeyguardViewController) this.keyguardViewControllerLazy.get()).primaryBouncerIsOrWillBeShowing();
            long faceLockoutAttemptDeadline = oplusBiometricAuthController.getFaceLockoutAttemptDeadline();
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("showBouncerByFace currentFaceFailAttempts =", faceFailedAttempts, ", isFaceLockoutTimed =", ", primaryBouncerIsOrWillBeShowing =", z);
            sbM.append(zPrimaryBouncerIsOrWillBeShowing);
            sbM.append(", faceLockoutAttemptDeadline = ");
            sbM.append(faceLockoutAttemptDeadline);
            KgdLogUtil.i(TAG, sbM.toString());
            if (z && !zPrimaryBouncerIsOrWillBeShowing) {
                biometricUnlockController.startWakeAndUnlock(3, BiometricUnlockSource.FACE_SENSOR);
            }
        }
        return true;
    }

    public final void needWakeUpForUnlock(int i) {
        KeyguardFeatureOption.INSTANCE.getClass();
        if (KeyguardFeatureOption.isSupportFaceNoPowerBlock()) {
            if (i == 0) {
                return;
            }
        } else if (i == 0 || i == 1 || i == 5 || i == 6) {
            return;
        }
        if (KeyguardFeatureOption.isSupportUdfps() && this.keyguardUpdateMonitor.mIsDreaming) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "OplusBiometricUnlockController-->wake up from dreaming");
            }
            this.powerManager.wakeUp(SystemClock.uptimeMillis(), 4, "android.policy:BIOMETRIC");
            return;
        }
        if (KeyguardFeatureOption.isSupportUdfps()) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "OplusBiometricUnlockController-->wake up from screen is not on");
        }
        this.powerManager.wakeUp(SystemClock.uptimeMillis(), 4, "android.policy:BIOMETRIC");
    }

    public void onBiometricAuthenticated(int i, BiometricSourceType biometricSourceType) {
        ((OplusKeyguardVerifyManagerExImpl) this.keyguardVerifyManagerLazy.get()).setKeyguardDoneType(1, i);
        KeyguardThreadUtil.Companion companion = KeyguardThreadUtil.Companion;
        onBiometricAuthenticated.1 r0 = new onBiometricAuthenticated.1();
        r0.this$0 = this;
        r0.$biometricSourceType = biometricSourceType;
        VarHandle.storeStoreFence();
        companion.runOnWorkThread(r0);
    }

    public void onSideFpTouch(boolean z, BiometricUnlockController biometricUnlockController) {
        if (z) {
            if (this.keyguardUpdateMonitor.isKeyguardVisible$1() || OplusSceneModeObserver.Companion.getInstance(this.context).isInBrickMode || this.keyguardUpdateMonitor.getIsSecureLockDeviceEnabled()) {
                boolean z2 = this.keyguardStateController.mCanDismissLockScreen;
                ScreenLifecycle screenLifecycle = this.screenLifecycle;
                if (z2) {
                    if (screenLifecycle.getScreenState() == 2 || FingerSwitchHelper.Companion.getInstance(this.context).mSideFpUnlockMode != 0) {
                        return;
                    }
                    this.powerManager.wakeUp(SystemClock.uptimeMillis(), 4, "android.policy:FpTouch");
                    return;
                }
                if (screenLifecycle.getScreenState() == 2) {
                    if (this.keyguardStateController.mPrimaryBouncerShowing) {
                        return;
                    }
                    biometricUnlockController.startWakeAndUnlock(3, BiometricUnlockSource.FINGERPRINT_SENSOR);
                    ((FakeSubDisplayUiController) this.oplusSubDisplayUiControllerLazy.get()).requestKeyguardOnHomeCard();
                    return;
                }
                if (FingerSwitchHelper.Companion.getInstance(this.context).mSideFpUnlockMode == 0) {
                    armFpTouchShowDelayIfNeeded();
                    biometricUnlockController.startWakeAndUnlock(3, BiometricUnlockSource.FINGERPRINT_SENSOR);
                    ((FakeSubDisplayUiController) this.oplusSubDisplayUiControllerLazy.get()).requestKeyguardOnHomeCard();
                }
            }
        }
    }

    public final void resetMode() {
        ((OplusFlavorManager) this.flavorManagerLazy.get()).getPalmUnlockController().resetMode();
    }

    public void setWakeupBouncerImmersive(boolean z) {
        this.mWakeupBouncerImmersive = z;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x004e  */
    public final void showBouncerByFingerprint(boolean z, BiometricUnlockController biometricUnlockController) {
        int i;
        if (this.keyguardUpdateMonitor.getIsSecureLockDeviceEnabled()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "OplusBiometricUnlockController-->SecureLockDeviceEnabled, no need to show bouncer by finger");
                return;
            }
            return;
        }
        OplusBiometricAuthController oplusBiometricAuthController = (OplusBiometricAuthController) this.oplusBiometricAuthControllerLazy.get();
        int fpFailedAttempts = oplusBiometricAuthController.getFpFailedAttempts();
        boolean z2 = ((OplusPowerBlockController) this.oplusPowerBlockControllerLazy.get()).mFpAuthInScreenOff;
        if (this.keyguardUpdateMonitor.mIsDreaming) {
            KeyguardFeatureOption.INSTANCE.getClass();
            if (KeyguardFeatureOption.isSupportSidefps()) {
                i = FINGERPRINT_FAILS_TO_SHOW_BOUNCER_ATTEMPTS;
            } else {
                i = 3;
            }
        } else {
            i = FINGERPRINT_FAILS_TO_SHOW_BOUNCER_ATTEMPTS;
        }
        int i2 = fpFailedAttempts % 5;
        boolean z3 = false;
        boolean z4 = i2 >= i;
        boolean z5 = (oplusBiometricAuthController.getFpLockoutAttemptDeadline() == 0 || fpFailedAttempts == this.lastFpFailAttempts) ? false : true;
        boolean z6 = fpFailedAttempts > 0 && i2 == 0;
        if (!z && z2 && (z4 || z5 || z6)) {
            z3 = true;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            int screenState = this.screenLifecycle.getScreenState();
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("showBouncerByFingerprint currentFpFailAttempts =", fpFailedAttempts, i, ", countToShowBouncer=", ", isFpLockoutTimed =");
            KeyguardLogger$.ExternalSyntheticOutline0.m(", forceShowBouncer =", ", screenState=", sbM, z5, z);
            ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(screenState, ", isFingerprintAuthInScreenOff=", ", isTimedLockoutBoundary=", sbM, z2);
            Log.d("SystemUi--Keyguard", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(", shouldDelayFingerprintBouncer=", "OplusBiometricUnlockController-->", sbM, z6, z3));
        }
        if (z3) {
            ((PrimaryBouncerInteractor) this.primaryBouncerInteractorLazy.get()).armAutomaticShowDelay(1000L, "FingerprintFailure", "BiometricUnlockController#MODE_SHOW_BOUNCER");
        }
        if (z4) {
            IDreamPolicy iDreamPolicy = DreamHelper.getInstance().mDreamPolicy;
            if (iDreamPolicy != null) {
                iDreamPolicy.hideAodClock();
            }
            AODDisplayUtil.getInstance(this.context).hideAodBlackView("BouncerAlreadyShown");
        }
        if (z4 || z5 || z) {
            setWakeupBouncerImmersive(true);
            if (this.keyguardUpdateMonitor.getUserHasTrust(KeyguardUpdateMonitor.getCurrentUser())) {
                biometricUnlockController.startWakeAndUnlock(4, BiometricUnlockSource.FINGERPRINT_SENSOR);
            } else {
                biometricUnlockController.startWakeAndUnlock(3, BiometricUnlockSource.FINGERPRINT_SENSOR);
            }
            if (!oplusBiometricAuthController.isShowFpWhenBouncer()) {
                KeyguardFeatureOption.INSTANCE.getClass();
                if (KeyguardFeatureOption.isSupportUdfps()) {
                    oplusBiometricAuthController.cancelBiometric("wakeUpByFingerprint and show bouncer", 2);
                }
            }
        } else {
            KeyguardFeatureOption.INSTANCE.getClass();
            if (KeyguardFeatureOption.isFingerprintFaceCombineUnlock()) {
                FlavorOneFeatureOption.INSTANCE.getClass();
                if (FlavorOneFeatureOption.isFlavorOneDevice() && !oplusBiometricAuthController.getScreenOffFaceUnlock() && !z4) {
                    if (oplusBiometricAuthController.isScreenOn()) {
                        return;
                    }
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                        Log.d("SystemUi--Keyguard", "OplusBiometricUnlockController-->start face unlock when screen off");
                    }
                    oplusBiometricAuthController.startScreenOffFaceListener();
                }
            }
        }
        this.lastFpFailAttempts = fpFailedAttempts;
    }

    public final void vibrateError(boolean z, boolean z2) {
        if (z && ((OplusFlavorManager) this.flavorManagerLazy.get()).getPalmUnlockController().shouldFilterOutAuthVibrate()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "OplusBiometricUnlockController-->vibrateError() called with: shouldFilterOutAuthVibrate ...");
                return;
            }
            return;
        }
        if (!z2) {
            if (this.keyguardStateController.mShowing) {
                ((OplusKeyguardVibratorHelper) this.oplusKeyguardVibratorHelperLazy.get()).vibrateLongWhenVerifyFail();
                return;
            }
            return;
        }
        FingerprintStateHelper companion = FingerprintStateHelper.Companion.getInstance(this.context);
        KeyguardFeatureOption.INSTANCE.getClass();
        if (((KeyguardFeatureOption.isSupportUdfps() || !(companion.isLockLater || companion.isPendingLock)) && !this.keyguardStateController.mShowing) || this.keyguardUpdateMonitor.getIsFaceAuthenticated() || this.keyguardUpdateMonitor.isFingerprintAuthenticated()) {
            return;
        }
        ((OplusKeyguardVibratorHelper) this.oplusKeyguardVibratorHelperLazy.get()).vibrateLongWhenVerifyFail();
    }

    public final void vibrateShort() {
        ((OplusKeyguardVibratorHelper) this.oplusKeyguardVibratorHelperLazy.get()).vibrateShortWhenVerify();
    }

    public final void vibrateSuccess() {
        ((OplusKeyguardVibratorHelper) this.oplusKeyguardVibratorHelperLazy.get()).vibrateShortWhenVerify();
    }
}

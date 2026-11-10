package com.oplus.systemui.statusbar.phone;

import android.content.Context;
import android.net.Uri;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.Trace;
import android.util.Log;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.systemui.DependencyEx;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.lifecycle.UnlockViewLifecycleOwner$;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.OlockStateUtil$;
import com.android.systemui.statusbar.notification.helper.StudyCenterHelper;
import com.android.systemui.statusbar.phone.KeyguardNearbyUnlockController;
import com.android.systemui.statusbar.phone.OplusBiometricUnlockControllerEx;
import com.android.systemui.statusbar.policy.SecurityControllerImpl;
import com.oplus.base.ocrscanner.oseesdk.utils.ApplicationUtil$;
import com.oplus.keyguard.security.OplusKeyguardSecurityModel;
import com.oplus.keyguard.security.verify.OplusKeyguardVerifyManagerExImpl;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.multiuser.OplusMultiUserManager;
import com.oplus.systemui.biometrics.finger.FingerSwitchHelper;
import com.oplus.systemui.keyguard.OplusKeyguardBouncerExImpl;
import com.oplus.systemui.keyguard.OplusKeyguardStudySpace;
import com.oplus.systemui.keyguard.familymode.OplusFamilyModeHelper;
import com.oplus.systemui.keyguard.lockdead.LockDeadUtil;
import com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl;
import com.oplus.systemui.scene.OplusSceneModeObserver;
import com.oplusos.keyguard.utils.KeyguardThreadUtil;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.common.observer.ChildrenModeObserver;
import com.oplusos.systemui.common.observer.FocusModeSettingsObserver;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.common.utils.Util;
import dagger.Lazy;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Method;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardNearbyUnlockControllerImpl extends KeyguardNearbyUnlockController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "KeyguardNearbyUnlockController";
    private static final String WAKE_UP_DETAILS_UNLOCK_DREAMING = "com.android.systemui:UNLOCK_DREAMING";
    private final Lazy biometricUnlockControllerEx;
    private final Context context;
    private final Lazy familyModeHelper;
    private final Lazy keyguardUpdateMonitor;
    private final Lazy keyguardVerifyManager;
    private final Lazy keyguardViewMediator;
    private final Lazy lockPatternUtils;
    private final Lazy notificationShadeWindowController;
    private final kotlin.Lazy powerManager$delegate;
    private final Lazy securityController;

    /* JADX INFO: renamed from: $r8$lambda$-bm1wz4ZAqoowwRl37Qrk8NEqsM, reason: not valid java name */
    public static PowerManager m464$r8$lambda$bm1wz4ZAqoowwRl37Qrk8NEqsM(KeyguardNearbyUnlockControllerImpl keyguardNearbyUnlockControllerImpl) {
        Object systemService = keyguardNearbyUnlockControllerImpl.context.getSystemService("power");
        if (systemService instanceof PowerManager) {
            return (PowerManager) systemService;
        }
        return null;
    }

    public KeyguardNearbyUnlockControllerImpl(Context context, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8) {
        this.context = context;
        this.keyguardViewMediator = lazy;
        this.keyguardUpdateMonitor = lazy2;
        this.lockPatternUtils = lazy3;
        this.familyModeHelper = lazy4;
        this.securityController = lazy5;
        this.biometricUnlockControllerEx = lazy6;
        this.notificationShadeWindowController = lazy7;
        this.keyguardVerifyManager = lazy8;
        KeyguardNearbyUnlockControllerImpl$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new KeyguardNearbyUnlockControllerImpl$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.powerManager$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda0);
    }

    public static final void access$checkCondition(KeyguardNearbyUnlockControllerImpl keyguardNearbyUnlockControllerImpl, KeyguardNearbyUnlockControllerImpl$performNearbyUnlockKeyguard$1$.ExternalSyntheticLambda0 externalSyntheticLambda0) {
        FeatureOption.INSTANCE.getClass();
        boolean z = false;
        if (FeatureOption.isTablet()) {
            KeyguardFeatureOption.INSTANCE.getClass();
            if (!KeyguardFeatureOption.isTabletSupportNFC) {
                KgdLogUtil.i(TAG, "checkCondition: Tablet is not support NFC");
            } else if (!((KeyguardUpdateMonitor) keyguardNearbyUnlockControllerImpl.keyguardUpdateMonitor.get()).isKeyguardVisible$1()) {
                KgdLogUtil.i(TAG, "checkCondition: Keyguard is not visible");
            } else if (((KeyguardUpdateMonitor) keyguardNearbyUnlockControllerImpl.keyguardUpdateMonitor.get()).mKeyguardGoingAway) {
                KgdLogUtil.i(TAG, "checkCondition: Keyguard is going away");
            } else if (((KeyguardUpdateMonitor) keyguardNearbyUnlockControllerImpl.keyguardUpdateMonitor.get()).mGoingToSleep) {
                KgdLogUtil.i(TAG, "checkCondition: Is going to sleep");
            } else if (((KeyguardUpdateMonitor) keyguardNearbyUnlockControllerImpl.keyguardUpdateMonitor.get()).mIsSystemUser) {
                SuperPowerSaveRepositoryImpl.Companion.getClass();
                if (SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel()) {
                    KgdLogUtil.i(TAG, "checkCondition: Is super power save mode");
                } else if (OplusSceneModeObserver.Companion.getInstance(keyguardNearbyUnlockControllerImpl.context).isInBrickMode) {
                    KgdLogUtil.i(TAG, "checkCondition: Is in brick mode");
                } else if (FocusModeSettingsObserver.getsInstance().mIsFocusMode) {
                    KgdLogUtil.i(TAG, "checkCondition: Is focus mode");
                } else if (((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusCoverWindowManager().getState() == 1) {
                    KgdLogUtil.i(TAG, "checkCondition: Is device case close");
                } else if (OplusKeyguardStudySpace.Companion.getInstance().isClickStudySpace) {
                    KgdLogUtil.i(TAG, "checkCondition: Is click study space");
                } else {
                    Uri uri = Util.ROM_UPDATE_CONTENT_URI;
                    if (ChildrenModeObserver.getInstance().mIsChildrenMode) {
                        KgdLogUtil.i(TAG, "checkCondition: Is children mode");
                    } else if (StudyCenterHelper.Companion.getInstance().isStudyCenterMode) {
                        KgdLogUtil.i(TAG, "checkCondition: Is study center mode");
                    } else if (((KeyguardUpdateMonitor) keyguardNearbyUnlockControllerImpl.keyguardUpdateMonitor.get()).mDeviceProvisioned) {
                        OplusKeyguardBouncerExImpl.Companion.getClass();
                        if (KeyguardUpdateMonitor.getCurrentUser() == 888) {
                            KgdLogUtil.i(TAG, "checkCondition: Is repair mode");
                        } else if (((KeyguardUpdateMonitor) keyguardNearbyUnlockControllerImpl.keyguardUpdateMonitor.get()).mHandler.hasMessages(336)) {
                            KgdLogUtil.i(TAG, "checkCondition: Is keyguard family open");
                        } else {
                            OplusFamilyModeHelper oplusFamilyModeHelper = (OplusFamilyModeHelper) keyguardNearbyUnlockControllerImpl.familyModeHelper.get();
                            int multiSystemUserId = OplusMultiUserManager.getInstance().getMultiSystemUserId();
                            oplusFamilyModeHelper.getClass();
                            if (OplusFamilyModeHelper.isKeyguardFamilyOpen(multiSystemUserId)) {
                                KgdLogUtil.i(TAG, "checkCondition: Is keyguard family open");
                            } else {
                                LockDeadUtil.INSTANCE.getClass();
                                if (LockDeadUtil.isLockDeadState) {
                                    KgdLogUtil.i(TAG, "checkCondition: Is lock dead state");
                                } else {
                                    KeyguardSecurityModel.SecurityMode securityModeInSimCard = OplusKeyguardSecurityModel.INSTANCE.getSecurityModeInSimCard(keyguardNearbyUnlockControllerImpl.context);
                                    boolean z2 = securityModeInSimCard != KeyguardSecurityModel.SecurityMode.None;
                                    if (z2) {
                                        KgdLogUtil.i(TAG, "isSimSecure: simSecurityMode=" + securityModeInSimCard);
                                    }
                                    if (z2) {
                                        KgdLogUtil.i(TAG, "checkCondition: Is sim secure");
                                    } else if (KeyguardUtils.Companion.isSystemFbeMode(keyguardNearbyUnlockControllerImpl.context)) {
                                        KgdLogUtil.i(TAG, "checkCondition: Is system fbe mode");
                                    } else {
                                        SecurityControllerImpl securityControllerImpl = (SecurityControllerImpl) keyguardNearbyUnlockControllerImpl.securityController.get();
                                        if (securityControllerImpl.mDevicePolicyManager.isDeviceManaged() || securityControllerImpl.mDevicePolicyManager.isOrganizationOwnedDeviceWithManagedProfile()) {
                                            KgdLogUtil.i(TAG, "checkCondition: Is work profile");
                                        } else {
                                            z = true;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        KgdLogUtil.i(TAG, "checkCondition: Device is not provisioned");
                    }
                }
            } else {
                KgdLogUtil.i(TAG, "checkCondition: Not a primary user");
            }
        } else {
            KgdLogUtil.i(TAG, "checkCondition: Not a tablet");
        }
        externalSyntheticLambda0.invoke(Boolean.valueOf(z));
    }

    public static final void access$checkPswToken(KeyguardNearbyUnlockControllerImpl keyguardNearbyUnlockControllerImpl, long j, byte[] bArr, Function1 function1) {
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        int currentUser = KeyguardUpdateMonitor.getCurrentUser();
        if (((LockPatternUtils) keyguardNearbyUnlockControllerImpl.lockPatternUtils.get()).isSecure(currentUser)) {
            if (bArr == null || bArr.length == 0) {
                KgdLogUtil.i(TAG, "checkPswToken: Psw token is empty");
                booleanRef.element = false;
            } else if (!((LockPatternUtils) keyguardNearbyUnlockControllerImpl.lockPatternUtils.get()).getLockoutEndTime(currentUser).isZero()) {
                KgdLogUtil.i(TAG, "checkPswToken: Is lockout");
                booleanRef.element = false;
            } else if (((OplusKeyguardVerifyManagerExImpl) keyguardNearbyUnlockControllerImpl.keyguardVerifyManager.get()).needVerifyPwdWhenSysRebooted()) {
                KgdLogUtil.i(TAG, "checkPswToken: Sys rebooted");
                booleanRef.element = false;
            } else if (((OplusKeyguardVerifyManagerExImpl) keyguardNearbyUnlockControllerImpl.keyguardVerifyManager.get()).isStrongAuthTimeOut(currentUser)) {
                KgdLogUtil.i(TAG, "checkPswToken: Strong auth time out");
                booleanRef.element = false;
            } else if ((((KeyguardUpdateMonitor) keyguardNearbyUnlockControllerImpl.keyguardUpdateMonitor.get()).getStrongAuthTracker().getStrongAuthForUser(currentUser) & 2) != 0) {
                KgdLogUtil.i(TAG, "checkPswToken: Is dpm lock down");
                booleanRef.element = false;
            }
        }
        if (!booleanRef.element) {
            ((performNearbyUnlockKeyguard.1.1.1) function1).invoke(Boolean.FALSE);
            return;
        }
        KeyguardThreadUtil.Companion companion = KeyguardThreadUtil.Companion;
        checkPswToken.1 r2 = new checkPswToken.1();
        r2.this$0 = keyguardNearbyUnlockControllerImpl;
        r2.$tokenHandle = j;
        r2.$pswToken = bArr;
        r2.$allowUnlockKeyguard = booleanRef;
        r2.$onChecked = function1;
        VarHandle.storeStoreFence();
        companion.runOnWorkThread(r2);
    }

    public static final void access$performNearbyUnlockKeyguardInner(KeyguardNearbyUnlockControllerImpl keyguardNearbyUnlockControllerImpl, boolean z) {
        PowerManager powerManager;
        PowerManager powerManager2;
        boolean zIsKeyguardVisible$1 = ((KeyguardUpdateMonitor) keyguardNearbyUnlockControllerImpl.keyguardUpdateMonitor.get()).isKeyguardVisible$1();
        OlockStateUtil$.ExternalSyntheticOutline0.m("performNearbyUnlockKeyguardInner: isKeyguardVisible=", " ,allowUnlockKeyguard=", TAG, zIsKeyguardVisible$1, z);
        if (!zIsKeyguardVisible$1 || !z) {
            if (zIsKeyguardVisible$1) {
                ((OplusBiometricUnlockControllerEx) keyguardNearbyUnlockControllerImpl.biometricUnlockControllerEx.get()).vibrateError(false, false);
            }
            if ((((KeyguardUpdateMonitor) keyguardNearbyUnlockControllerImpl.keyguardUpdateMonitor.get()).mDeviceInteractive && keyguardNearbyUnlockControllerImpl.isPowerInteractive()) || (powerManager = keyguardNearbyUnlockControllerImpl.getPowerManager()) == null) {
                return;
            }
            PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(805306394, "KeyguardNearbyUnlockController:nearbyUnlockFail");
            wakeLockNewWakeLock.acquire(5000L);
            wakeLockNewWakeLock.release();
            return;
        }
        boolean zIsFingerprintUnlockEnterDesktop = FingerSwitchHelper.Companion.getInstance(keyguardNearbyUnlockControllerImpl.context).isFingerprintUnlockEnterDesktop();
        boolean z2 = ((KeyguardUpdateMonitor) keyguardNearbyUnlockControllerImpl.keyguardUpdateMonitor.get()).mIsDreaming;
        boolean z3 = ((KeyguardUpdateMonitor) keyguardNearbyUnlockControllerImpl.keyguardUpdateMonitor.get()).mDeviceInteractive;
        boolean z4 = zIsFingerprintUnlockEnterDesktop && z2 && z3 && keyguardNearbyUnlockControllerImpl.isPowerInteractive();
        UnlockViewLifecycleOwner$.ExternalSyntheticOutline0.m(" ,isDeviceInteractive=", TAG, TextFieldImplKt$.ExternalSyntheticOutline0.m("performNearbyUnlockKeyguardInner: wakingFromDream=", " ,isBypass=", " ,isDreaming=", z4, zIsFingerprintUnlockEnterDesktop), z2, z3);
        ((KeyguardViewMediator) keyguardNearbyUnlockControllerImpl.keyguardViewMediator.get()).userActivity();
        ((OplusBiometricUnlockControllerEx) keyguardNearbyUnlockControllerImpl.biometricUnlockControllerEx.get()).vibrateSuccess();
        if (!z4 && ((!z3 || z2) && (powerManager2 = keyguardNearbyUnlockControllerImpl.getPowerManager()) != null)) {
            powerManager2.wakeUp(SystemClock.uptimeMillis(), 2, WAKE_UP_DETAILS_UNLOCK_DREAMING);
        }
        ((NotificationShadeWindowController) keyguardNearbyUnlockControllerImpl.notificationShadeWindowController.get()).setNotificationShadeFocusable(false);
        Trace.traceBegin(8L, "KeyguardNearbyUnlockController#performNearbyUnlockKeyguardInner_onWakeAndUnlocking");
        ((KeyguardViewMediator) keyguardNearbyUnlockControllerImpl.keyguardViewMediator.get()).onWakeAndUnlocking(z4);
        Trace.traceEnd(8L);
    }

    public static final boolean access$unlockUserWithToken(KeyguardNearbyUnlockControllerImpl keyguardNearbyUnlockControllerImpl, long j, byte[] bArr, int i) {
        if (!((LockPatternUtils) keyguardNearbyUnlockControllerImpl.lockPatternUtils.get()).isSecure(i)) {
            return true;
        }
        if (bArr != null) {
            try {
                Class<?> cls = Class.forName("com.oplus.os.OplusLockPatternUtils");
                Method method = cls.getMethod("unlockUserWithToken", Long.TYPE, byte[].class, Integer.TYPE);
                if (method == null) {
                    KgdLogUtil.w(TAG, "unlockUserWithToken: Method(unlockUserWithToken) not found");
                    return false;
                }
                Object objInvoke = method.invoke(cls.newInstance(), Long.valueOf(j), bArr, Integer.valueOf(i));
                Boolean bool = objInvoke instanceof Boolean ? (Boolean) objInvoke : null;
                if (bool != null) {
                    return bool.booleanValue();
                }
                KgdLogUtil.w(TAG, "unlockUserWithToken: Token unlock result is null");
                return false;
            } catch (Throwable th) {
                Throwable thM = ApplicationUtil$.ExternalSyntheticOutline0.m(th);
                if (thM != null) {
                    KgdLogUtil.e(TAG, "unlockUserWithToken: exception", thM);
                }
            }
        }
        return false;
    }

    private final PowerManager getPowerManager() {
        return (PowerManager) this.powerManager$delegate.getValue();
    }

    public void dump(String[] strArr, PrintWriter printWriter, FileDescriptor fileDescriptor) {
    }

    public final boolean isPowerInteractive() {
        PowerManager powerManager = getPowerManager();
        Boolean boolValueOf = powerManager != null ? Boolean.valueOf(powerManager.isInteractive()) : null;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "KeyguardNearbyUnlockController-->".concat("isPowerInteractive: isInteractive=" + boolValueOf));
        }
        return Intrinsics.areEqual(boolValueOf, Boolean.TRUE);
    }

    public void performNearbyUnlockKeyguard(long j, byte[] bArr) {
        KgdLogUtil.i(TAG, "performNearbyUnlockKeyguard");
        performNearbyUnlockKeyguard.1 r0 = new performNearbyUnlockKeyguard.1();
        r0.this$0 = this;
        r0.$tokenHandle = j;
        r0.$pswToken = bArr;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(r0);
    }
}

package com.oplus.systemui.subdisplay;

import android.content.Context;
import com.android.keyguard.ViewMediatorCallback;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.oplus.systemui.subdisplay.security.OplusSubDisplayBouncerController;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import dagger.Lazy;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FakeSubDisplayUiController {
    public static final Companion Companion = new Companion();
    public static final String REQUEST_COMMAND_START_FACE_UNLOCK_FOR_SUB_DISPLAY = "startFaceUnlockForSubDisplaySysUi";
    public static final String REQUEST_COMMAND_STOP_FACE_UNLOCK_FOR_SUB_DISPLAY = "stopFaceUnlockForSubDisplaySysUi";
    public static final String REQUEST_COMMAND_UNLOCK_IN_SLIDE = "subUnlockInSlide";
    private final Lazy aodSubFullscreenControllerLazy;
    private final Context context;
    private final Lazy dialogContentUpdateToSecondaryHomeControllerLazy;
    private final Lazy dumpManagerLazy;
    private final Lazy keyguardStateControllerLazy;
    private final Lazy keyguardUpdateMonitorLazy;
    private final Lazy keyguardViewControllerLazy;
    private final Lazy oplusBiometricAuthControllerLazy;
    private final Lazy oplusKeyguardVerifyManagerLazy;
    private final Lazy oplusLockIconViewControllerLazy;
    private final ScreenLifecycle screenLifecycle;
    private final SecondaryKeyguardProviderClient secondaryProviderClient;
    private SubDisplayUiController subDisplayKeyguardController;
    private final WakefulnessLifecycle wakefulnessLifecycle;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public FakeSubDisplayUiController(Context context, Lazy lazy, Lazy lazy2, WakefulnessLifecycle wakefulnessLifecycle, ScreenLifecycle screenLifecycle, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8, SecondaryKeyguardProviderClient secondaryKeyguardProviderClient, Lazy lazy9) {
        this.context = context;
        this.keyguardUpdateMonitorLazy = lazy;
        this.keyguardStateControllerLazy = lazy2;
        this.wakefulnessLifecycle = wakefulnessLifecycle;
        this.screenLifecycle = screenLifecycle;
        this.oplusKeyguardVerifyManagerLazy = lazy3;
        this.dumpManagerLazy = lazy4;
        this.aodSubFullscreenControllerLazy = lazy5;
        this.oplusLockIconViewControllerLazy = lazy6;
        this.keyguardViewControllerLazy = lazy7;
        this.oplusBiometricAuthControllerLazy = lazy8;
        this.secondaryProviderClient = secondaryKeyguardProviderClient;
        this.dialogContentUpdateToSecondaryHomeControllerLazy = lazy9;
        if (OplusSubDisplayUtils.Companion.getInstance(context).getSupportSubDisplay()) {
            this.subDisplayKeyguardController = new SubDisplayUiController(context, lazy, lazy2, wakefulnessLifecycle, screenLifecycle, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, secondaryKeyguardProviderClient, lazy9);
        }
    }

    public final void collapsePanelsInSubDisplay() {
        SubDisplayUiController subDisplayUiController = this.subDisplayKeyguardController;
        if (subDisplayUiController != null) {
            SubDisplayUiController.showOrHideBouncer$default(subDisplayUiController, false);
            subDisplayUiController.sendMessage(6, null, false);
        }
    }

    public final int getState() {
        SubDisplayUiController subDisplayUiController = this.subDisplayKeyguardController;
        if (subDisplayUiController != null) {
            return subDisplayUiController.getState();
        }
        return 0;
    }

    public final boolean interruptPlayUnlockSound() {
        SubDisplayUiController subDisplayUiController = this.subDisplayKeyguardController;
        if (subDisplayUiController != null) {
            return subDisplayUiController.interruptPlayUnlockSound();
        }
        return false;
    }

    public final void keyguardStated() {
        SubDisplayUiController subDisplayUiController = this.subDisplayKeyguardController;
        if (subDisplayUiController != null) {
            subDisplayUiController.sendMessage(1, null, false);
            SubDisplayUiController.keyguardStated.1 r0 = new SubDisplayUiController.keyguardStated.1(0);
            r0.this$0 = subDisplayUiController;
            VarHandle.storeStoreFence();
            SystemUIThreadUtil.runOnUiThread(r0);
        }
    }

    public final void onUserSwitchComplete(int i) {
        SubDisplayUiController subDisplayUiController = this.subDisplayKeyguardController;
        if (subDisplayUiController != null) {
            subDisplayUiController.onUserSwitchComplete(i);
        }
    }

    public final void processLockWhenTrustEnable() {
        SubDisplayUiController subDisplayUiController = this.subDisplayKeyguardController;
        if (subDisplayUiController != null) {
            subDisplayUiController.processLockWhenTrustEnable();
        }
    }

    public final void requestKeyguardOnHomeCard() {
        SubDisplayUiController subDisplayUiController = this.subDisplayKeyguardController;
        if (subDisplayUiController == null || !DeviceStateController.getInstance().isFolding()) {
            return;
        }
        subDisplayUiController.sendMessage(12, null, false);
    }

    public final void setDismissReason(String str) {
        SubDisplayUiController subDisplayUiController = this.subDisplayKeyguardController;
        if (subDisplayUiController != null) {
            subDisplayUiController.setDismissReason(str);
        }
    }

    public final void setKeyguardViewMediator(KeyguardViewMediator keyguardViewMediator) {
        SubDisplayUiController subDisplayUiController = this.subDisplayKeyguardController;
        if (subDisplayUiController != null) {
            subDisplayUiController.setKeyguardViewMediator(keyguardViewMediator);
        }
    }

    public final void setSubDisplayBouncerChontroller(OplusSubDisplayBouncerController oplusSubDisplayBouncerController) {
        SubDisplayUiController subDisplayUiController = this.subDisplayKeyguardController;
        if (subDisplayUiController != null) {
            subDisplayUiController.setSubDisplayBouncerChontroller(oplusSubDisplayBouncerController);
        }
    }

    public final void setViewMediatorCallback(ViewMediatorCallback viewMediatorCallback) {
        SubDisplayUiController subDisplayUiController = this.subDisplayKeyguardController;
        if (subDisplayUiController != null) {
            subDisplayUiController.setViewMediatorCallback(viewMediatorCallback);
        }
    }

    public final void showOrHideBouncer(boolean z) {
        SubDisplayUiController subDisplayUiController = this.subDisplayKeyguardController;
        if (subDisplayUiController != null) {
            SubDisplayUiController.showOrHideBouncer$default(subDisplayUiController, z);
        }
    }

    public final void updateLockState(int i) {
        SubDisplayUiController subDisplayUiController = this.subDisplayKeyguardController;
        if (subDisplayUiController != null) {
            subDisplayUiController.updateLockState(i, 1, false);
        }
    }
}

package com.oplus.systemui.notification.lockscreen.capsule;

import com.oplus.systemui.common.manager.OplusKeyguardOrientationManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationCapsuleContainerControllerImpl$keyguardOrientationAnimationListener$1 implements OplusKeyguardOrientationManager.OrientationAnimationListener {
    public /* synthetic */ NotificationCapsuleContainerControllerImpl this$0;

    public final void onAppearAnimationCancel() {
        this.this$0.orientationAlpha.setValue(Float.valueOf(1.0f));
        NotificationCapsuleContainerControllerImpl.Companion.access$traceAlphaChange(NotificationCapsuleContainerControllerImpl.Companion, "orientAnimationAlpha:cancel", 1.0f);
    }

    public final void onAppearAnimationEnd() {
        this.this$0.orientationAlpha.setValue(Float.valueOf(1.0f));
        NotificationCapsuleContainerControllerImpl.Companion.access$traceAlphaChange(NotificationCapsuleContainerControllerImpl.Companion, "orientAnimationAlpha:end", 1.0f);
    }

    public final void onAppearAnimationProgress(float f) {
        this.this$0.orientationAlpha.setValue(Float.valueOf(f));
        NotificationCapsuleContainerControllerImpl.Companion.access$traceAlphaChange(NotificationCapsuleContainerControllerImpl.Companion, "orientAnimationAlpha:progress", f);
    }

    public final void onOrientationChangedStart(int i) {
        this.this$0.orientationAlpha.setValue(Float.valueOf(0.0f));
        NotificationCapsuleContainerControllerImpl.Companion.access$traceAlphaChange(NotificationCapsuleContainerControllerImpl.Companion, "orientAnimationAlpha:start:", 0.0f);
    }
}

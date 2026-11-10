package com.oplus.systemui.keyguard.watcher;

import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.keyguard.KeyguardUnlockAnimationController;
import com.android.systemui.statusbar.OlockStateUtil$;
import com.oplus.keyguard.OplusKeyguardUnlockAnimationControllerExImpl;
import com.oplus.keyguard.utils.KeyguardRapidUnlockUtils;
import com.oplus.systemui.aod.aodclock.constant.AodData;
import com.oplus.systemui.aod.display.AODDisplayUtil;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.keyguard.wallpaper.OplusLockScreenWallpaperController;
import com.oplusos.keyguard.utils.KgdLogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKgdImmersiveController$updateMonitorCallback$1 extends KeyguardUpdateMonitorCallback {
    public /* synthetic */ OplusKgdImmersiveController this$0;

    public final void onKeyguardDismissAnimationFinished() {
        OplusKgdImmersiveController oplusKgdImmersiveController = this.this$0;
        OplusLockIconView$.ExternalSyntheticOutline0.m("onKeyguardDismissAnimationFinished delayHideSurface:", "OplusKgdImmersiveController", oplusKgdImmersiveController.delayHideSurface);
        if (oplusKgdImmersiveController.delayHideSurface) {
            if (OplusKgdImmersiveController.access$shouldDelayHideSurface(oplusKgdImmersiveController)) {
                oplusKgdImmersiveController.setImmersiveWallpaperState(0);
                oplusKgdImmersiveController.immersiveAnimateTo(oplusKgdImmersiveController.surfaceAlpha, 0.0f, false);
            }
            oplusKgdImmersiveController.delayHideSurface = false;
        }
    }

    public final void onKeyguardGoingAway() {
        OplusKgdImmersiveController oplusKgdImmersiveController = this.this$0;
        boolean z = oplusKgdImmersiveController.pendingMusicScreenOffEnter;
        oplusKgdImmersiveController.pendingMusicScreenOffEnter = false;
        if (z) {
            oplusKgdImmersiveController.dismissMusicScreenshotOverlay();
        }
        if (oplusKgdImmersiveController.isImmersiveWallpaperState >= 3) {
            if (((Boolean) ((OplusLockScreenWallpaperController) oplusKgdImmersiveController.oplusLockScreenWallpaperController.get()).isLockScreenWallpaperEnableFlow().getValue()).booleanValue() || !OplusKeyguardUnlockAnimationControllerExImpl.Companion.isUnlockToLauncherByRemoteTarget(((KeyguardUnlockAnimationController) oplusKgdImmersiveController.keyguardUnlockAnimationController.get()).getSurfacesBehind())) {
                KgdLogUtil.i("OplusKgdImmersiveController", "onKeyguardGoingAway delayHideSurface");
                oplusKgdImmersiveController.delayHideSurface = true;
                return;
            }
            KgdLogUtil.i("OplusKgdImmersiveController", "onKeyguardGoingAway");
            boolean z2 = AodData.getInstance(oplusKgdImmersiveController.context).getAodType() == 2;
            boolean z3 = AODDisplayUtil.getInstance(oplusKgdImmersiveController.context).getDeviceDisplayState() == 1;
            if (!KeyguardRapidUnlockUtils.Companion.getInstance().hasResume || ((z2 && !z3) || oplusKgdImmersiveController.lastUiState == SmoothTransitionController.SmoothTransitionUiState.KEYGUARD)) {
                oplusKgdImmersiveController.setImmersiveWallpaperState(0);
                OplusKgdImmersiveController oplusKgdImmersiveController2 = this.this$0;
                oplusKgdImmersiveController2.immersiveAnimateTo(oplusKgdImmersiveController2.surfaceAlpha, 0.0f, false);
                oplusKgdImmersiveController.delayHideSurface = false;
                return;
            }
            KgdLogUtil.i("OplusKgdImmersiveController", "onKeyguardGoingAway rapid unlock, safeReleaseActiveImmersiveSurface directly");
            oplusKgdImmersiveController.getImmersiveAlphaAnimation().cancel();
            oplusKgdImmersiveController.setImmersiveWallpaperState(0);
            if (oplusKgdImmersiveController.immersiveRenderMode == OplusKgdImmersiveController.ImmersiveRenderMode.MUSIC_VIEW) {
                oplusKgdImmersiveController.getMusicArtHostContainer().setVisibility(8);
            }
            oplusKgdImmersiveController.safeReleaseActiveImmersiveSurface();
            oplusKgdImmersiveController.delayHideSurface = false;
        }
    }

    public final void onKeyguardVisibilityChanged(boolean z) {
        OplusKgdImmersiveController oplusKgdImmersiveController = this.this$0;
        OlockStateUtil$.ExternalSyntheticOutline0.m("onKeyguardVisibilityChanged visible=", " delayHideSurface:", "OplusKgdImmersiveController", z, oplusKgdImmersiveController.delayHideSurface);
        if (!z && oplusKgdImmersiveController.delayHideSurface) {
            if (OplusKgdImmersiveController.access$shouldDelayHideSurface(oplusKgdImmersiveController)) {
                oplusKgdImmersiveController.setImmersiveWallpaperState(0);
                oplusKgdImmersiveController.immersiveAnimateTo(oplusKgdImmersiveController.surfaceAlpha, 0.0f, false);
            }
            oplusKgdImmersiveController.delayHideSurface = false;
        }
        if (z) {
            oplusKgdImmersiveController.restoreImmersiveForKeyguardShow();
        }
    }
}

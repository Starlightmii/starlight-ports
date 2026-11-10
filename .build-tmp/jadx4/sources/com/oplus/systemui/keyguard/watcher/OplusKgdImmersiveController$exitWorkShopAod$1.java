package com.oplus.systemui.keyguard.watcher;

import com.oplus.systemui.keyguard.view.OplusKgdImmersiveMaskView;
import com.oplus.systemui.keyguard.wallpaper.OplusLockScreenWallpaperController;
import com.oplus.systemui.keyguard.wallpaper.OplusWallpaperBitmapUtil;
import com.oplusos.keyguard.utils.KgdLogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKgdImmersiveController$exitWorkShopAod$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusKgdImmersiveController this$0;

    public /* synthetic */ OplusKgdImmersiveController$exitWorkShopAod$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Integer num;
        switch (this.$r8$classId) {
            case 0:
                Integer num2 = this.this$0.currentImmersiveType;
                if ((num2 != null && num2.intValue() == -1) || ((num = this.this$0.currentImmersiveType) != null && num.intValue() == 1)) {
                    OplusKgdImmersiveController oplusKgdImmersiveController = this.this$0;
                    oplusKgdImmersiveController.cropBitmap = OplusWallpaperBitmapUtil.loadWallpaperBlurBitmap(oplusKgdImmersiveController.context);
                    if (this.this$0.cropBitmap != null) {
                        OplusKgdImmersiveController.sendEnterImmersiveLockScreenWallpaperMsg$default(this.this$0);
                    }
                    break;
                } else if (this.this$0.immersiveRenderMode == OplusKgdImmersiveController.ImmersiveRenderMode.MUSIC_VIEW && this.this$0.musicArtBackgroundView != null) {
                    OplusKgdImmersiveController.access$scheduleRestoreMusicImmersiveAfterWorkShopAod(this.this$0);
                    break;
                }
                break;
            case 1:
                this.this$0.restoreMusicAfterWorkShopRunnable = null;
                if (this.this$0.isImmersiveWallpaper) {
                    OplusKgdImmersiveController oplusKgdImmersiveController2 = this.this$0;
                    if (!oplusKgdImmersiveController2.isImmersiveWallpaperAod) {
                        if (oplusKgdImmersiveController2.immersiveRenderMode == OplusKgdImmersiveController.ImmersiveRenderMode.MUSIC_VIEW && this.this$0.musicArtBackgroundView != null) {
                            KgdLogUtil.i("OplusKgdImmersiveController", "restoreMusicImmersiveAfterWorkShopAod view=" + this.this$0.musicArtBackgroundView + ",state=" + this.this$0.isImmersiveWallpaperState);
                            this.this$0.enterMusicImmersiveLockScreenWallpaper(true);
                        } else {
                            KgdLogUtil.w("OplusKgdImmersiveController", "restoreMusicImmersiveAfterWorkShopAod skip, view or mode missing");
                        }
                        break;
                    }
                }
                break;
            case 2:
                boolean z = this.this$0.sharedPrefs.getBoolean("immersive_surface_showing", false);
                if (z && ((OplusLockScreenWallpaperController) this.this$0.oplusLockScreenWallpaperController.get()).isLiveWallpaper) {
                    KgdLogUtil.w("OplusKgdImmersiveController", "surface leaked, reset wallpaper component");
                    OplusKgdImmersiveController.access$resetWallpaperComponent(this.this$0);
                } else if (z) {
                    this.this$0.saveSurfaceStateToSp(false);
                }
                break;
            case 3:
                OplusKgdImmersiveController oplusKgdImmersiveController3 = this.this$0;
                OplusKgdImmersiveController$markDesktopWallpaperUnderlayIfNeeded$1$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusKgdImmersiveController$markDesktopWallpaperUnderlayIfNeeded$1$.ExternalSyntheticLambda0();
                externalSyntheticLambda0.f$0 = oplusKgdImmersiveController3;
                VarHandle.storeStoreFence();
                oplusKgdImmersiveController3.requestDesktopWallpaperBitmap(null, externalSyntheticLambda0);
                break;
            case 4:
                this.this$0.releaseActiveImmersiveSurface();
                break;
            default:
                ((OplusKgdImmersiveMaskView) this.this$0.oplusKgdImmersiveMaskView.get()).setBlackMaskRatioForWorkShopAod();
                break;
        }
    }
}

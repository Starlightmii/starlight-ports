package com.oplus.systemui.keyguard.watcher;

import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKgdImmersiveController$immersiveAlphaAnimation$2$1$2 implements COUIDynamicAnimation.OnAnimationEndListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusKgdImmersiveController this$0;

    public /* synthetic */ OplusKgdImmersiveController$immersiveAlphaAnimation$2$1$2(int i) {
        this.$r8$classId = i;
    }

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                OplusKgdImmersiveController oplusKgdImmersiveController = this.this$0;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                    MobileInputLogger$.ExternalSyntheticOutline0.m(oplusKgdImmersiveController.isImmersiveWallpaperState, "OplusKgdImmersiveController-->", "SystemUi--Keyguard", NotificationPanelViewController$.ExternalSyntheticOutline0.m("onAnimationEnd: isCanceled=", z, ",currValue=", f, ", isImmersiveWallpaperState="));
                }
                if (!z) {
                    if (f == 1.0f && this.this$0.isImmersiveWallpaperState == 3) {
                        OplusKgdImmersiveController oplusKgdImmersiveController2 = this.this$0;
                        oplusKgdImmersiveController2.isLockImmersive = false;
                        oplusKgdImmersiveController2.setImmersiveWallpaperState(4);
                        this.this$0.dismissMusicScreenshotOverlay();
                    } else if (f == 0.0f) {
                        if (this.this$0.isImmersiveWallpaperState < 2) {
                            OplusKgdImmersiveController oplusKgdImmersiveController3 = this.this$0;
                            if (!oplusKgdImmersiveController3.pendingMusicScreenOffEnter && !oplusKgdImmersiveController3.pendingMusicSurfaceAttach) {
                                oplusKgdImmersiveController3.isLockImmersive = false;
                                OplusKgdImmersiveController.ImmersiveRenderMode immersiveRenderMode = oplusKgdImmersiveController3.immersiveRenderMode;
                                OplusKgdImmersiveController.ImmersiveRenderMode immersiveRenderMode2 = OplusKgdImmersiveController.ImmersiveRenderMode.MUSIC_VIEW;
                                OplusKgdImmersiveController oplusKgdImmersiveController4 = this.this$0;
                                if (immersiveRenderMode != immersiveRenderMode2) {
                                    oplusKgdImmersiveController4.releaseMusicPipelineSilently();
                                    this.this$0.safeReleaseActiveImmersiveSurface();
                                } else if (!oplusKgdImmersiveController4.musicExitShouldClearState) {
                                    oplusKgdImmersiveController4.getMusicArtHostContainer().setVisibility(8);
                                    this.this$0.releaseMusicSurface();
                                    if (this.this$0.isImmersiveWallpaperState == 1) {
                                        this.this$0.setImmersiveWallpaperState(0);
                                    }
                                } else {
                                    oplusKgdImmersiveController4.isImmersiveWallpaper = false;
                                    this.this$0.setImmersiveWallpaperState(0);
                                    OplusKgdImmersiveController oplusKgdImmersiveController5 = this.this$0;
                                    oplusKgdImmersiveController5.musicExitShouldClearState = false;
                                    oplusKgdImmersiveController5.releaseMusicSurface();
                                    this.this$0.clearMusicImmersiveState();
                                    if (this.this$0.surfaceControlViewHost != null) {
                                        this.this$0.releaseMaskViewSurface();
                                        this.this$0.clearCropBitmap(true);
                                    }
                                }
                            }
                        }
                        OplusLockIconView$.ExternalSyntheticOutline0.m(this.this$0.isImmersiveWallpaperState, "ignore outdated fade-out end, state=", "OplusKgdImmersiveController");
                    }
                    break;
                }
                break;
            default:
                if (!z) {
                    OplusKgdImmersiveController oplusKgdImmersiveController6 = this.this$0;
                    if (oplusKgdImmersiveController6.isPipelineCrossfading && f >= 1.0f) {
                        OplusKgdImmersiveController.access$finishPipelineCrossfade(oplusKgdImmersiveController6);
                    }
                }
                break;
        }
    }
}

package com.oplus.systemui.keyguard.watcher;

import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.oplusos.keyguard.utils.KgdLogUtil;
import kotlin.LazyKt__LazyJVMKt$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKgdImmersiveController$wakeUpObserver$1 implements WakefulnessLifecycle.Observer {
    public /* synthetic */ OplusKgdImmersiveController this$0;

    public final void onFinishedGoingToSleep() {
        OplusKgdImmersiveController oplusKgdImmersiveController = this.this$0;
        if (oplusKgdImmersiveController.pendingMusicScreenOffEnter) {
            KgdLogUtil.i("OplusKgdImmersiveController", "onFinishedGoingToSleep try deferred music enter");
            oplusKgdImmersiveController.tryRunPendingMusicScreenOffEnter(false);
        }
    }

    /* JADX WARN: Code duplicated, block: B:19:0x003c  */
    public final void onFinishedWakingUp() {
        if (this.this$0.isImmersiveWallpaper && !((KeyguardUpdateMonitor) this.this$0.keyguardUpdateMonitor.get()).mKeyguardGoingAway && this.this$0.isImmersiveWallpaperState == 4) {
            int iOrdinal = this.this$0.immersiveRenderMode.ordinal();
            boolean z = true;
            if (iOrdinal != 0) {
                if (iOrdinal != 1) {
                    if (iOrdinal != 2) {
                        LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                        return;
                    } else if (this.this$0.musicSurfaceControlViewHost != null) {
                        z = false;
                    }
                } else if (this.this$0.surfaceControlViewHost != null) {
                    z = false;
                }
            }
            OplusKgdImmersiveController oplusKgdImmersiveController = this.this$0;
            if (oplusKgdImmersiveController.surfaceAlpha < 1.0f || z) {
                OplusKgdImmersiveController.ImmersiveRenderMode immersiveRenderMode = oplusKgdImmersiveController.immersiveRenderMode;
                OplusKgdImmersiveController.ImmersiveRenderMode immersiveRenderMode2 = OplusKgdImmersiveController.ImmersiveRenderMode.MUSIC_VIEW;
                if (immersiveRenderMode == immersiveRenderMode2 && this.this$0.musicArtBackgroundView == null) {
                    return;
                }
                KgdLogUtil.i("OplusKgdImmersiveController", "onFinishedWakingUp restore immersive, state=" + this.this$0.isImmersiveWallpaperState + ",renderMode=" + this.this$0.immersiveRenderMode);
                OplusKgdImmersiveController.ImmersiveRenderMode immersiveRenderMode3 = this.this$0.immersiveRenderMode;
                OplusKgdImmersiveController oplusKgdImmersiveController2 = this.this$0;
                if (immersiveRenderMode3 == immersiveRenderMode2) {
                    oplusKgdImmersiveController2.getMusicArtHostContainer().setVisibility(0);
                    OplusKgdImmersiveController.setMusicArtViewToSurface$default(this.this$0, false, 3);
                } else {
                    OplusKgdImmersiveController.setMaskViewToSurface$default(oplusKgdImmersiveController2, false, 3);
                }
                this.this$0.getImmersiveAlphaAnimation().cancel();
                OplusKgdImmersiveController oplusKgdImmersiveController3 = this.this$0;
                oplusKgdImmersiveController3.immersiveAnimateTo(oplusKgdImmersiveController3.surfaceAlpha, 1.0f, false);
            }
        }
    }

    public final void onStartedGoingToSleep() {
        OplusKgdImmersiveController oplusKgdImmersiveController = this.this$0;
        if (oplusKgdImmersiveController.delayHideSurface && oplusKgdImmersiveController.isImmersiveWallpaper) {
            KgdLogUtil.i("OplusKgdImmersiveController", "onStartedGoingToSleep clear delayHideSurface for immersive keep");
            this.this$0.delayHideSurface = false;
        }
    }
}

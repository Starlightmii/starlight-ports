package com.oplus.systemui.keyguard.watcher;

import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKgdImmersiveController$immersiveAlphaAnimation$2$1$1 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusKgdImmersiveController this$0;

    public /* synthetic */ OplusKgdImmersiveController$immersiveAlphaAnimation$2$1$1(int i) {
        this.$r8$classId = i;
    }

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                if (f < 0.0f) {
                    f = 0.0f;
                }
                this.this$0.setSurfaceAlpha(f <= 1.0f ? f : 1.0f);
                break;
            default:
                OplusKgdImmersiveController.access$applyPipelineCrossfadeProgress(this.this$0, RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f));
                break;
        }
    }
}

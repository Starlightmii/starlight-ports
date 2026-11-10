package com.oplus.systemui.util;

import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKgdSwipeUpTouchProgressTracker$progressSpring$1$1 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public /* synthetic */ OplusKgdSwipeUpTouchProgressTracker this$0;

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        OplusKgdSwipeUpTouchProgressTracker oplusKgdSwipeUpTouchProgressTracker = this.this$0;
        oplusKgdSwipeUpTouchProgressTracker.dispatchProgress(RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f), oplusKgdSwipeUpTouchProgressTracker.currentVel);
    }
}

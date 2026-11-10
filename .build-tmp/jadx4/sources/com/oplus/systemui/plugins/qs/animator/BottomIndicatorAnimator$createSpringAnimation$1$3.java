package com.oplus.systemui.plugins.qs.animator;

import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BottomIndicatorAnimator$createSpringAnimation$1$3 implements COUIDynamicAnimation.OnAnimationEndListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ BottomIndicatorAnimator this$0;

    public /* synthetic */ BottomIndicatorAnimator$createSpringAnimation$1$3(int i) {
        this.$r8$classId = i;
    }

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                BottomIndicatorAnimator.access$handleFadeInEnd(this.this$0, f);
                break;
            default:
                BottomIndicatorAnimator.access$handleFadeOutEnd(this.this$0, f);
                break;
        }
    }
}

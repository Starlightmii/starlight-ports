package com.oplus.systemui.navigationbar.gesture.sidegesture.animator;

import androidx.dynamicanimation.animation.DynamicAnimation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHandleFollowHandAnimator$2$3 implements DynamicAnimation.OnAnimationEndListener {
    public /* synthetic */ OplusHandleFollowHandAnimator this$0;

    public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
        OplusHandleFollowHandAnimator oplusHandleFollowHandAnimator = this.this$0;
        if (oplusHandleFollowHandAnimator.isTouchUp) {
            oplusHandleFollowHandAnimator.setCurrentState(OplusHandleFollowHandAnimator.State.STOPPED);
        }
    }
}

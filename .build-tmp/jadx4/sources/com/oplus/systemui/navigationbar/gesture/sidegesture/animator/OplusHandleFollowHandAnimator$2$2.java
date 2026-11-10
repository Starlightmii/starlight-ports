package com.oplus.systemui.navigationbar.gesture.sidegesture.animator;

import androidx.dynamicanimation.animation.DynamicAnimation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHandleFollowHandAnimator$2$2 implements DynamicAnimation.OnAnimationUpdateListener {
    public /* synthetic */ OplusHandleFollowHandAnimator this$0;

    public final void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f, float f2) {
        OplusHandleFollowHandAnimator oplusHandleFollowHandAnimator = this.this$0;
        float f3 = oplusHandleFollowHandAnimator.maxTransitionDistance;
        float f4 = 1.0f;
        if (oplusHandleFollowHandAnimator.getCurrentState() == OplusHandleFollowHandAnimator.State.FOLLOWXRUNNING) {
            oplusHandleFollowHandAnimator.oplusNavigationHandle.setTranslationX(f);
            float fAbs = ((Math.abs(f) / f3) * 0.12f) + 1.0f;
            oplusHandleFollowHandAnimator.oplusNavigationHandle.setScaleX(fAbs);
            oplusHandleFollowHandAnimator.oplusNavigationHandle.setScaleY(fAbs);
        }
        if (oplusHandleFollowHandAnimator.getCurrentState() == OplusHandleFollowHandAnimator.State.FOLLOWYRUNNING) {
            oplusHandleFollowHandAnimator.oplusNavigationHandle.setTranslationY(f);
            float f5 = -f3;
            if (f5 == 0.0f) {
                f4 = 1.12f;
            } else {
                float f6 = (((f - 0.0f) / (f5 - 0.0f)) * 0.12f) + 1.0f;
                if (f6 >= 1.0f) {
                    f4 = f6;
                }
            }
            oplusHandleFollowHandAnimator.oplusNavigationHandle.setScaleX(f4);
            oplusHandleFollowHandAnimator.oplusNavigationHandle.setScaleY(f4);
        }
    }
}

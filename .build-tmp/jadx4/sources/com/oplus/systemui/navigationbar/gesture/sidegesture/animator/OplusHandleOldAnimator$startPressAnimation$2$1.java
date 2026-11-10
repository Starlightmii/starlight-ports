package com.oplus.systemui.navigationbar.gesture.sidegesture.animator;

import android.animation.ValueAnimator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHandleOldAnimator$startPressAnimation$2$1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ ValueAnimator $this_apply;
    public /* synthetic */ OplusHandleOldAnimator this$0;

    public /* synthetic */ OplusHandleOldAnimator$startPressAnimation$2$1(int i) {
        this.$r8$classId = i;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.oplusNavigationHandle.invalidateHandle(((Float) this.$this_apply.getAnimatedValue()).floatValue());
                break;
            default:
                this.this$0.oplusNavigationHandle.invalidateHandle(((Float) this.$this_apply.getAnimatedValue()).floatValue());
                break;
        }
    }
}

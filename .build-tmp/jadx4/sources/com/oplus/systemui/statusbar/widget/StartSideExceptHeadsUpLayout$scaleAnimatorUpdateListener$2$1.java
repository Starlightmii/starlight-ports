package com.oplus.systemui.statusbar.widget;

import android.animation.ValueAnimator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StartSideExceptHeadsUpLayout$scaleAnimatorUpdateListener$2$1 implements ValueAnimator.AnimatorUpdateListener {
    public /* synthetic */ StartSideExceptHeadsUpLayout this$0;

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.this$0.onScaleChanged$1(this.this$0.getScaleX());
    }
}

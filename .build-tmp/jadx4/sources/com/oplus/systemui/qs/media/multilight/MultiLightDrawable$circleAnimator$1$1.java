package com.oplus.systemui.qs.media.multilight;

import android.animation.ValueAnimator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MultiLightDrawable$circleAnimator$1$1 implements ValueAnimator.AnimatorUpdateListener {
    public /* synthetic */ MultiLightDrawable this$0;

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.this$0.invalidateSelf();
    }
}

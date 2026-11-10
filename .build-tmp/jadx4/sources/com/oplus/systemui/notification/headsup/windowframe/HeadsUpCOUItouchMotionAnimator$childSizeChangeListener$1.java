package com.oplus.systemui.notification.headsup.windowframe;

import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HeadsUpCOUItouchMotionAnimator$childSizeChangeListener$1 implements View.OnLayoutChangeListener {
    public /* synthetic */ HeadsUpCOUItouchMotionAnimator this$0;

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = i4 - i2;
        int i10 = i8 - i6;
        if (i3 - i == i7 - i5 && i9 == i10) {
            return;
        }
        HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator = this.this$0;
        view.getClass();
        headsUpCOUItouchMotionAnimator.updateTouchMotionAnimatorSize(view);
        this.this$0.onTransformApplied.invoke();
    }
}

package com.oplus.systemui.statusbar.pipeline.battery.ui.binder;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BatteryViewBinder$startCoordinateTransition$coordinateTransitionAnimator$1$1 implements ValueAnimator.AnimatorUpdateListener {
    public /* synthetic */ View $changedView;
    public /* synthetic */ ViewGroup $container;

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        BatteryViewBinder batteryViewBinder = BatteryViewBinder.INSTANCE;
        ViewGroup viewGroup = this.$container;
        BatteryViewBinder.access$updateChildrenPosition(batteryViewBinder, viewGroup, this.$changedView, viewGroup.isLayoutRtl());
    }
}

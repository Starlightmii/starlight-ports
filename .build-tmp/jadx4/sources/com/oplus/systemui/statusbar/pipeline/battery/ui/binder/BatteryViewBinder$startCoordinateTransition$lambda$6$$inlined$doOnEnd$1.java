package com.oplus.systemui.statusbar.pipeline.battery.ui.binder;

import android.animation.Animator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BatteryViewBinder$startCoordinateTransition$lambda$6$$inlined$doOnEnd$1 implements Animator.AnimatorListener {
    public /* synthetic */ View $changedView$inlined;
    public /* synthetic */ ViewGroup $container$inlined;
    public /* synthetic */ String $moduleTag$inlined;

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        int iIndexOfChild = this.$container$inlined.indexOfChild(this.$changedView$inlined) - 1;
        View childAt = iIndexOfChild >= 0 ? this.$container$inlined.getChildAt(iIndexOfChild) : null;
        this.$changedView$inlined.setTag(2131365596, null);
        BatteryViewBinder batteryViewBinder = BatteryViewBinder.INSTANCE;
        ViewGroup viewGroup = this.$container$inlined;
        BatteryViewBinder.access$updateChildrenPosition(batteryViewBinder, viewGroup, this.$changedView$inlined, viewGroup.isLayoutRtl());
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            String str = this.$moduleTag$inlined;
            int width = this.$container$inlined.getWidth();
            int measuredWidth = this.$container$inlined.getMeasuredWidth();
            View view = this.$changedView$inlined;
            Log.d("SystemUi--Statusbar", "BatteryViewBinder-->".concat(str + " coordinateTransition end! container.widhth: " + width + ", measuredWidth:" + measuredWidth + "; " + view + " " + view.getLeft() + " " + this.$changedView$inlined.getRight() + " " + (childAt != null ? Integer.valueOf(childAt.getLeft()) : null) + " " + (childAt != null ? Integer.valueOf(childAt.getRight()) : null)));
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
    }
}

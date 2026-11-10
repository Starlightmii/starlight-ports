package com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.threestage;

import android.util.MathUtils;
import android.view.View;
import android.view.ViewGroup;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ThreeStageWidthSharedTransitionProperty extends ThreeStageShareTransitionProperty {
    private BaseDeformationAnimationController.OnAnimateListener onAnimateListener;

    public ThreeStageWidthSharedTransitionProperty(ViewGroup viewGroup, int i) {
        super(viewGroup, i, 0, 0);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.threestage.ThreeStageShareTransitionProperty
    public ThreeStageShareTransitionProperty createShareTransitionCopy(ViewGroup viewGroup) {
        return new ThreeStageWidthSharedTransitionProperty(viewGroup, getSharedElementId());
    }

    public final BaseDeformationAnimationController.OnAnimateListener getOnAnimateListener() {
        return this.onAnimateListener;
    }

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public Integer getValue(View view) {
        return Integer.valueOf(view.getWidth());
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.threestage.ThreeStageShareTransitionProperty
    public final void onAnimateUpdate(float f) {
        setValue(Integer.valueOf((int) MathUtils.lerp(((Number) getStartValue()).intValue(), ((Number) getEndValue()).intValue(), f)));
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.threestage.ThreeStageShareTransitionProperty, com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public final void onTransitionEnd(boolean z) {
        super.onTransitionEnd(false);
        this.onAnimateListener = null;
    }

    public final void setOnAnimateListener(BaseDeformationAnimationController.OnAnimateListener onAnimateListener) {
        this.onAnimateListener = onAnimateListener;
    }

    public void setValue(View view, int i) {
        BaseDeformationAnimationController.OnAnimateListener onAnimateListener;
        int iRoundToInt = MathKt.roundToInt((((View) view.getParent()).getWidth() - i) / 2.0f);
        int i2 = iRoundToInt + i;
        int width = view.getWidth();
        if (iRoundToInt != view.getLeft() || Math.abs(i - view.getWidth()) >= 2 || (i == ((Number) getEndValue()).intValue() && i == ((Number) getStartValue()).intValue())) {
            view.setLeftTopRightBottom(iRoundToInt, view.getTop(), i2, view.getBottom());
            if (width == i || (onAnimateListener = this.onAnimateListener) == null) {
                return;
            }
            onAnimateListener.onViewHostBoundChanged();
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.threestage.ThreeStageShareTransitionProperty
    public final void startTransition(Object obj, Object obj2) {
        ((Number) obj).intValue();
        ((Number) obj2).intValue();
        ThreeStageShareTransitionProperty.createSpringAnimation$default(this, "三段式开关形变Bound动画", 0.35f, 0.17f, 0.0f, 24).animateToFinalPosition(1.0f);
    }

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public final /* bridge */ /* synthetic */ void setValue(View view, Object obj) {
        setValue(view, ((Number) obj).intValue());
    }
}

package com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.threestage;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty;
import com.oplus.systemui.qs.base.shareanimation.ViewOverlayManagerKt;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ThreeStageBoundSharedTransitionProperty extends ThreeStageShareTransitionProperty {
    private final Rect curRect;
    public final boolean isNestedBoundShare;

    public ThreeStageBoundSharedTransitionProperty(ViewGroup viewGroup, int i, boolean z) {
        super(viewGroup, i, new Rect(), new Rect());
        this.isNestedBoundShare = z;
        this.curRect = new Rect();
    }

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public void captureValues(List<? extends ViewGroup> list) {
        super.captureValues(list);
        if (this.isNestedBoundShare) {
            return;
        }
        HashMap<WeakReference<ViewGroup>, Object> valuesOfDiffState = getValuesOfDiffState();
        captureValues.1 r0 = new captureValues.1();
        r0.this$0 = this;
        VarHandle.storeStoreFence();
        valuesOfDiffState.replaceAll(r0);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.threestage.ThreeStageShareTransitionProperty
    public ThreeStageShareTransitionProperty createShareTransitionCopy(ViewGroup viewGroup) {
        return new ThreeStageBoundSharedTransitionProperty(viewGroup, getSharedElementId(), this.isNestedBoundShare);
    }

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public Rect getValue(View view) {
        Rect rect = new Rect();
        rect.left = view.getLeft();
        rect.top = view.getTop();
        rect.right = view.getRight();
        rect.bottom = view.getBottom();
        return rect;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.threestage.ThreeStageShareTransitionProperty
    public final void onAnimateUpdate(float f) {
        Rect rect = this.curRect;
        rect.left = MathKt.roundToInt(((((Rect) getEndValue()).left - ((Rect) getStartValue()).left) * f) + ((Rect) getStartValue()).left);
        rect.top = MathKt.roundToInt(((((Rect) getEndValue()).top - ((Rect) getStartValue()).top) * f) + ((Rect) getStartValue()).top);
        rect.right = MathKt.roundToInt(((((Rect) getEndValue()).right - ((Rect) getStartValue()).right) * f) + ((Rect) getStartValue()).right);
        rect.bottom = MathKt.roundToInt(((((Rect) getEndValue()).bottom - ((Rect) getStartValue()).bottom) * f) + ((Rect) getStartValue()).bottom);
        ViewGroup targetViewHost = getTargetViewHost();
        View viewFindViewByIdUseCache = targetViewHost != null ? ShareTransitionProperty.Companion.findViewByIdUseCache(targetViewHost, getSharedElementId(), false) : null;
        if (!this.isNestedBoundShare && viewFindViewByIdUseCache != null) {
            ViewOverlayManagerKt.minusBoundsDiff(viewFindViewByIdUseCache, this.curRect);
        }
        setValue(this.curRect);
    }

    @Override // com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty
    public void setValue(View view, Rect rect) {
        view.setLeftTopRightBottom(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.threestage.ThreeStageShareTransitionProperty
    public void startTransition(Rect rect, Rect rect2) {
        ThreeStageShareTransitionProperty.createSpringAnimation$default(this, "开关形变位置大小动画", 0.4f, 0.0f, 0.0f, 28).animateToFinalPosition(1.0f);
    }
}

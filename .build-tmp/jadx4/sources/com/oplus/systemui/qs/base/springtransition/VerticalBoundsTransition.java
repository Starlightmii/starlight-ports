package com.oplus.systemui.qs.base.springtransition;

import android.graphics.Rect;
import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VerticalBoundsTransition extends RectTransitionProperty {
    public VerticalBoundsTransition(View view) {
        super(view, new Rect());
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public Rect getValue() {
        Rect tmpRect = getTmpRect();
        View view = (View) getTargetObject();
        tmpRect.left = view != null ? view.getLeft() : 0;
        View view2 = (View) getTargetObject();
        tmpRect.right = view2 != null ? view2.getRight() : 0;
        View view3 = (View) getTargetObject();
        tmpRect.top = view3 != null ? view3.getTop() : 0;
        View view4 = (View) getTargetObject();
        tmpRect.bottom = view4 != null ? view4.getBottom() : 0;
        return tmpRect;
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public final boolean isValid() {
        return super.isValid() && ((Rect) getStartValue()).left == ((Rect) getEndValue()).left && ((Rect) getStartValue()).right == ((Rect) getEndValue()).right;
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public void setValue(Rect rect) {
        View view;
        if (!rect.isValid() || (view = (View) getTargetObject()) == null) {
            return;
        }
        view.setLeftTopRightBottom(view.getLeft(), rect.top, view.getRight(), rect.bottom);
    }
}

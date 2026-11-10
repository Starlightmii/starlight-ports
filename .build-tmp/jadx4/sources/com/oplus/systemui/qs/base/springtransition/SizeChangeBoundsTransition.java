package com.oplus.systemui.qs.base.springtransition;

import android.graphics.Rect;
import android.view.View;
import com.oplus.systemui.qs.base.widget.recyclerview.ViewWithSizeChange;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SizeChangeBoundsTransition extends RectTransitionProperty {
    public SizeChangeBoundsTransition(ViewWithSizeChange viewWithSizeChange) {
        super(viewWithSizeChange, new Rect());
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public Rect getValue() {
        View targetView;
        Rect tmpRect = getTmpRect();
        ViewWithSizeChange viewWithSizeChange = (ViewWithSizeChange) getTargetObject();
        if (viewWithSizeChange == null || (targetView = viewWithSizeChange.getTargetView()) == null) {
            tmpRect.set(0, 0, 0, 0);
            return tmpRect;
        }
        tmpRect.left = targetView.getLeft();
        tmpRect.right = targetView.getRight();
        tmpRect.top = targetView.getTop();
        tmpRect.bottom = targetView.getBottom();
        return tmpRect;
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public void setValue(Rect rect) {
        View targetView;
        if (rect.isValid()) {
            ViewWithSizeChange viewWithSizeChange = (ViewWithSizeChange) getTargetObject();
            if (viewWithSizeChange == null || (targetView = viewWithSizeChange.getTargetView()) == null) {
                rect.set(0, 0, 0, 0);
                return;
            }
            int left = targetView.getLeft();
            int right = targetView.getRight();
            int top = targetView.getTop();
            int bottom = targetView.getBottom();
            ViewWithSizeChange viewWithSizeChange2 = (ViewWithSizeChange) getTargetObject();
            if (viewWithSizeChange2 != null) {
                viewWithSizeChange2.notifySizeChanged(right - left, bottom - top, rect.right - rect.left, rect.bottom - rect.top);
            }
            targetView.setLeftTopRightBottom(rect.left, rect.top, rect.right, rect.bottom);
        }
    }
}

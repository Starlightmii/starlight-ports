package com.oplus.systemui.qs.base.springtransition;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DrawableBoundsTransition extends RectTransitionProperty {
    public DrawableBoundsTransition(Drawable drawable) {
        super(drawable, new Rect());
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public Rect getValue() {
        Drawable drawable = (Drawable) getTargetObject();
        if (drawable != null) {
            getTmpRect().set(drawable.getBounds());
            Rect tmpRect = getTmpRect();
            if (tmpRect != null) {
                return tmpRect;
            }
        }
        return (Rect) getInvalidValue();
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public void setValue(Rect rect) {
        Drawable drawable;
        if (!rect.isValid() || (drawable = (Drawable) getTargetObject()) == null) {
            return;
        }
        drawable.setBounds(rect.left, rect.top, rect.right, rect.bottom);
    }
}

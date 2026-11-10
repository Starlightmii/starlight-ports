package com.oplus.systemui.qs.base.springtransition;

import android.graphics.Rect;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class RectTransitionProperty extends TransitionProperty {
    public static final Companion Companion = new Companion();
    private final Rect tmpRect;

    public RectTransitionProperty(Object obj, Rect rect) {
        super(obj, rect);
        this.tmpRect = new Rect();
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public final void captureEndValue() {
        super.captureEndValue();
        setEndValue(new Rect((Rect) getEndValue()));
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public final void captureStartValue() {
        super.captureStartValue();
        setStartValue(new Rect((Rect) getStartValue()));
    }

    public final Rect getTmpRect() {
        return this.tmpRect;
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public void setProgress(float f) {
        if (((Rect) getStartValue()).isValid() && ((Rect) getEndValue()).isValid()) {
            this.tmpRect.left = MathKt.roundToInt(((((Rect) getEndValue()).left - ((Rect) getStartValue()).left) * f) + ((Rect) getStartValue()).left);
            this.tmpRect.right = MathKt.roundToInt(((((Rect) getEndValue()).right - ((Rect) getStartValue()).right) * f) + ((Rect) getStartValue()).right);
            this.tmpRect.top = MathKt.roundToInt(((((Rect) getEndValue()).top - ((Rect) getStartValue()).top) * f) + ((Rect) getStartValue()).top);
            this.tmpRect.bottom = MathKt.roundToInt(((((Rect) getEndValue()).bottom - ((Rect) getStartValue()).bottom) * f) + ((Rect) getStartValue()).bottom);
        } else {
            this.tmpRect.setEmpty();
        }
        setValue(this.tmpRect);
    }
}

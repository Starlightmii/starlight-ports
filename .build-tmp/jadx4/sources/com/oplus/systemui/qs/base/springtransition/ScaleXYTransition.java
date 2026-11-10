package com.oplus.systemui.qs.base.springtransition;

import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScaleXYTransition extends FloatViewTransitionProperty {
    public ScaleXYTransition(View view) {
        super(view);
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public Float getValue() {
        View view = (View) getTargetObject();
        return Float.valueOf(view != null ? view.getScaleX() : Float.MIN_VALUE);
    }

    public void setValue(float f) {
        View view = (View) getTargetObject();
        if (view != null) {
            view.setScaleX(f);
        }
        View view2 = (View) getTargetObject();
        if (view2 != null) {
            view2.setScaleY(f);
        }
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public /* bridge */ /* synthetic */ void setValue(Object obj) {
        setValue(((Number) obj).floatValue());
    }
}

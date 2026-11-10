package com.oplus.systemui.qs.base.springtransition;

import android.view.View;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FadeTransition extends FloatViewTransitionProperty {
    public static final Companion Companion = new Companion();
    private static final String TAG = "FadeTransition";
    public int endVisibility;

    public FadeTransition(View view) {
        super(view);
    }

    private static /* synthetic */ void getEndVisibility$annotations() {
    }

    private static /* synthetic */ void getStartVisibility$annotations() {
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public final void captureEndValue() {
        super.captureEndValue();
        View view = (View) getTargetObject();
        int visibility = view != null ? view.getVisibility() : 0;
        this.endVisibility = visibility;
        setEndValue(Float.valueOf(visibility == 0 ? 1.0f : 0.0f));
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public final void captureStartValue() {
        super.captureStartValue();
        View view = (View) getTargetObject();
        setStartValue(Float.valueOf((view != null ? view.getVisibility() : 0) == 0 ? getValue().floatValue() : 0.0f));
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public Float getValue() {
        View view = (View) getTargetObject();
        return Float.valueOf(view != null ? view.getTransitionAlpha() : ((Number) getInvalidValue()).floatValue());
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public final void onTransitionEnd(boolean z) {
        View view;
        if (z || (view = (View) getTargetObject()) == null) {
            return;
        }
        view.setTransitionVisibility(this.endVisibility);
        view.setTransitionAlpha(1.0f);
        Object parent = view.getParent();
        View view2 = parent instanceof View ? (View) parent : null;
        if (view2 != null) {
            view2.invalidate();
        }
    }

    @Override // com.oplus.systemui.qs.base.springtransition.FloatTransitionProperty, com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public void setProgress(float f) {
        if (((Number) getStartValue()).floatValue() == ((Number) getEndValue()).floatValue() || ((Number) getStartValue()).floatValue() == Float.MIN_VALUE || ((Number) getEndValue()).floatValue() == Float.MIN_VALUE) {
            return;
        }
        setValue(((((Number) getEndValue()).floatValue() - ((Number) getStartValue()).floatValue()) * f) + ((Number) getStartValue()).floatValue());
    }

    public void setValue(float f) {
        View view = (View) getTargetObject();
        if (view == null) {
            LogUtil.w$default(8, LogUtil.TAG_COMMON, TAG, "[setValue] targetView is recycled by GC");
        } else {
            view.setTransitionVisibility(0);
            view.setTransitionAlpha(f);
        }
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public /* bridge */ /* synthetic */ void setValue(Object obj) {
        setValue(((Number) obj).floatValue());
    }
}

package com.oplus.systemui.qs.base.springtransition;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class FloatTransitionProperty extends TransitionProperty {
    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public void setProgress(float f) {
        if (((Number) getStartValue()).floatValue() != ((Number) getInvalidValue()).floatValue() && ((Number) getEndValue()).floatValue() != ((Number) getInvalidValue()).floatValue()) {
            f = ((((Number) getEndValue()).floatValue() - ((Number) getStartValue()).floatValue()) * f) + ((Number) getStartValue()).floatValue();
        }
        setValue(Float.valueOf(f));
    }
}

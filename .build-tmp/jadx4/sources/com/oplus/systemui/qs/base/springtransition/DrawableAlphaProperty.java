package com.oplus.systemui.qs.base.springtransition;

import android.graphics.drawable.Drawable;
import com.oplus.posteffect.drawable.BaseDrawable;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ReflectionUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DrawableAlphaProperty extends DrawableTransitionProperty {
    public static final Companion Companion = new Companion();
    private static final String TAG = "DrawableAlphaTransition";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final int getAlphaFromDrawable(Drawable drawable, int i) {
            if (!(drawable instanceof BaseDrawable)) {
                return drawable != null ? drawable.getAlpha() : i;
            }
            Object value = ReflectionUtils.getValue(BaseDrawable.class, drawable, "paintAlpha");
            Integer num = value instanceof Integer ? (Integer) value : null;
            return num != null ? num.intValue() : ((BaseDrawable) drawable).getAlpha();
        }
    }

    public DrawableAlphaProperty(Drawable drawable) {
        super(drawable, Integer.MIN_VALUE);
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public final void captureEndValue() {
        super.captureEndValue();
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "[captureEndValue] " + getTargetObject() + " endValue=" + getEndValue(), null, 8);
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public final void captureStartValue() {
        super.captureStartValue();
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "[captureStartValue] " + getTargetObject() + " startValue=" + getStartValue(), null, 8);
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public Integer getValue() {
        return Integer.valueOf(Companion.getAlphaFromDrawable((Drawable) getTargetObject(), ((Number) getInvalidValue()).intValue()));
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public void setProgress(float f) {
        if (((Number) getStartValue()).intValue() != ((Number) getInvalidValue()).intValue() && ((Number) getEndValue()).intValue() != ((Number) getInvalidValue()).intValue()) {
            f = ((((Number) getEndValue()).intValue() - ((Number) getStartValue()).intValue()) * f) + ((Number) getStartValue()).floatValue();
        }
        setValue((int) f);
    }

    public void setValue(int i) {
        Drawable drawable = (Drawable) getTargetObject();
        if (drawable != null) {
            drawable.setAlpha(i);
        }
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "[setValue] " + getTargetObject() + " " + i, null, 8);
    }

    @Override // com.oplus.systemui.qs.base.springtransition.TransitionProperty
    public /* bridge */ /* synthetic */ void setValue(Object obj) {
        setValue(((Number) obj).intValue());
    }
}

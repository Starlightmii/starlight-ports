package com.oplus.systemui.qs.base.springtransition;

import android.graphics.drawable.Drawable;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DrawableAlphaPropertyKt$DRAWABLE_ALPHA$1 extends FloatPropertyCompat {
    public final float getValue(Object obj) {
        return DrawableAlphaProperty.Companion.getAlphaFromDrawable((Drawable) obj, Integer.MIN_VALUE) / 255.0f;
    }

    public final void setValue(Object obj, float f) {
        Drawable drawable = (Drawable) obj;
        if (drawable != null) {
            drawable.setAlpha(RangesKt___RangesKt.coerceIn((int) (255.0f * f), 0, 255));
        }
    }
}

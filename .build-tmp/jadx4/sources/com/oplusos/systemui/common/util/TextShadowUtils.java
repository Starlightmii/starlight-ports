package com.oplusos.systemui.common.util;

import android.graphics.Color;
import android.graphics.Paint;
import android.widget.TextView;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TextShadowUtils {
    public static final TextShadowUtils INSTANCE = new TextShadowUtils();
    private static final String TAG = "TextShadowUtils";

    public static final void setInverseColorTextShadow(Paint paint, boolean z, float f) {
        if (z) {
            paint.setShadowLayer(0.0f, 0.0f, 0.0f, 1291845632);
            return;
        }
        paint.setShadowLayer(8.0f, 0.0f, 0.0f, Color.argb((int) (RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f) * Color.alpha(1291845632)), Color.red(1291845632), Color.green(1291845632), Color.blue(1291845632)));
    }

    public static final void setInverseColorTextShadow(TextView textView, boolean z, float f) {
        if (z) {
            textView.setShadowLayer(0.0f, 0.0f, 0.0f, 1291845632);
        } else {
            textView.setShadowLayer(f, 0.0f, 0.0f, 1291845632);
        }
    }

    public static final void setInverseColorTextShadow(Paint paint, boolean z) {
        if (z) {
            paint.setShadowLayer(0.0f, 0.0f, 0.0f, 1291845632);
        } else {
            paint.setShadowLayer(8.0f, 0.0f, 0.0f, 1291845632);
        }
    }

    public static final void setInverseColorTextShadow(TextView textView, boolean z) {
        if (z) {
            textView.setShadowLayer(0.0f, 0.0f, 0.0f, 1291845632);
        } else {
            textView.setShadowLayer(8.0f, 0.0f, 0.0f, 1291845632);
        }
    }
}

package z1;

import android.text.BoringLayout;
import android.text.Layout;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {
    public static final BoringLayout a(CharSequence charSequence, TextPaint textPaint, int i5, Layout.Alignment alignment, float f5, float f6, BoringLayout.Metrics metrics, boolean z2, TextUtils.TruncateAt truncateAt, int i6) {
        return new BoringLayout(charSequence, textPaint, i5, alignment, f5, f6, metrics, z2, truncateAt, i6);
    }

    public static final BoringLayout.Metrics b(CharSequence charSequence, TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic) {
        if (textDirectionHeuristic.isRtl(charSequence, 0, charSequence.length())) {
            return null;
        }
        return BoringLayout.isBoring(charSequence, textPaint, null);
    }
}

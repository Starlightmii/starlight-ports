package z1;

import android.text.BoringLayout;
import android.text.Layout;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {
    public static final BoringLayout a(CharSequence charSequence, TextPaint textPaint, int i5, Layout.Alignment alignment, float f5, float f6, BoringLayout.Metrics metrics, boolean z2, boolean z3, TextUtils.TruncateAt truncateAt, int i6) {
        return x.f.e(charSequence, textPaint, i5, alignment, f5, f6, metrics, z2, z3, truncateAt, i6);
    }

    public static final BoringLayout.Metrics b(CharSequence charSequence, TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic) {
        return BoringLayout.isBoring(charSequence, textPaint, textDirectionHeuristic, true, null);
    }

    public static final boolean c(BoringLayout boringLayout) {
        return boringLayout.isFallbackLineSpacingEnabled();
    }
}

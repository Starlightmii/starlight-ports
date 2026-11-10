package x;

import android.graphics.text.LineBreakConfig;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.inputmethod.EditorBoundsInfo;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class f {
    public static /* synthetic */ LineBreakConfig.Builder a() {
        return new LineBreakConfig.Builder();
    }

    public static /* synthetic */ BoringLayout e(CharSequence charSequence, TextPaint textPaint, int i5, Layout.Alignment alignment, float f5, float f6, BoringLayout.Metrics metrics, boolean z2, boolean z3, TextUtils.TruncateAt truncateAt, int i6) {
        return new BoringLayout(charSequence, textPaint, i5, alignment, f5, f6, metrics, z2, truncateAt, i6, z3);
    }

    public static /* synthetic */ EditorBoundsInfo.Builder h() {
        return new EditorBoundsInfo.Builder();
    }
}

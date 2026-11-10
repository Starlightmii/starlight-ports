package b2;

import android.graphics.Paint;
import android.text.style.LineHeightSpan;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g implements LineHeightSpan {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f580d;

    public g(float f5) {
        this.f580d = f5;
    }

    @Override // android.text.style.LineHeightSpan
    public final void chooseHeight(CharSequence charSequence, int i5, int i6, int i7, int i8, Paint.FontMetricsInt fontMetricsInt) {
        int i9 = fontMetricsInt.descent - fontMetricsInt.ascent;
        if (i9 <= 0) {
            return;
        }
        int iCeil = (int) Math.ceil(this.f580d);
        int iCeil2 = (int) Math.ceil(((double) fontMetricsInt.descent) * ((double) ((iCeil * 1.0f) / i9)));
        fontMetricsInt.descent = iCeil2;
        fontMetricsInt.ascent = iCeil2 - iCeil;
    }
}

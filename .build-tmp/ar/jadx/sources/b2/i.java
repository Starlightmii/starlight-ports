package b2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;
import b4.l;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i extends ReplacementSpan {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Paint.FontMetricsInt f592d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f593e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f594f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f595g;

    public final Paint.FontMetricsInt a() {
        Paint.FontMetricsInt fontMetricsInt = this.f592d;
        if (fontMetricsInt != null) {
            return fontMetricsInt;
        }
        l.j("fontMetrics");
        throw null;
    }

    public final int b() {
        if (this.f595g) {
            return this.f594f;
        }
        throw new IllegalStateException("PlaceholderSpan is not laid out yet.");
    }

    @Override // android.text.style.ReplacementSpan
    public final void draw(Canvas canvas, CharSequence charSequence, int i5, int i6, float f5, int i7, int i8, int i9, Paint paint) {
    }

    @Override // android.text.style.ReplacementSpan
    public final int getSize(Paint paint, CharSequence charSequence, int i5, int i6, Paint.FontMetricsInt fontMetricsInt) {
        this.f595g = true;
        paint.getTextSize();
        this.f592d = paint.getFontMetricsInt();
        if (a().descent <= a().ascent) {
            throw new IllegalArgumentException("Invalid fontMetrics: line height can not be negative.");
        }
        this.f593e = (int) Math.ceil(0.0f);
        this.f594f = (int) Math.ceil(0.0f);
        if (fontMetricsInt != null) {
            fontMetricsInt.ascent = a().ascent;
            fontMetricsInt.descent = a().descent;
            fontMetricsInt.leading = a().leading;
            if (fontMetricsInt.ascent > (-b())) {
                fontMetricsInt.ascent = -b();
            }
            fontMetricsInt.top = Math.min(a().top, fontMetricsInt.ascent);
            fontMetricsInt.bottom = Math.max(a().bottom, fontMetricsInt.descent);
        }
        if (this.f595g) {
            return this.f593e;
        }
        throw new IllegalStateException("PlaceholderSpan is not laid out yet.");
    }
}

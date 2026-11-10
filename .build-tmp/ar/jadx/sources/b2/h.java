package b2;

import android.graphics.Paint;
import android.text.style.LineHeightSpan;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h implements LineHeightSpan {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f581d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f582e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f583f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f584g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f585h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f586i = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f587j = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f588k = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f589l = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f590m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f591n;

    public h(float f5, int i5, boolean z2, boolean z3, float f6) {
        this.f581d = f5;
        this.f582e = i5;
        this.f583f = z2;
        this.f584g = z3;
        this.f585h = f6;
        if ((0.0f > f6 || f6 > 1.0f) && f6 != -1.0f) {
            throw new IllegalStateException("topRatio should be in [0..1] range or -1");
        }
    }

    @Override // android.text.style.LineHeightSpan
    public final void chooseHeight(CharSequence charSequence, int i5, int i6, int i7, int i8, Paint.FontMetricsInt fontMetricsInt) {
        int i9 = fontMetricsInt.descent;
        int i10 = fontMetricsInt.ascent;
        if (i9 - i10 <= 0) {
            return;
        }
        boolean z2 = i5 == 0;
        boolean z3 = i6 == this.f582e;
        boolean z4 = this.f584g;
        boolean z5 = this.f583f;
        if (z2 && z3 && z5 && z4) {
            return;
        }
        if (this.f586i == Integer.MIN_VALUE) {
            int i11 = i9 - i10;
            int iCeil = (int) Math.ceil(this.f581d);
            int i12 = iCeil - i11;
            float fAbs = this.f585h;
            if (fAbs == -1.0f) {
                fAbs = Math.abs(fontMetricsInt.ascent) / (fontMetricsInt.descent - fontMetricsInt.ascent);
            }
            int iCeil2 = (int) (i12 <= 0 ? Math.ceil(i12 * fAbs) : Math.ceil((1.0f - fAbs) * i12));
            int i13 = fontMetricsInt.descent;
            int i14 = iCeil2 + i13;
            this.f588k = i14;
            int i15 = i14 - iCeil;
            this.f587j = i15;
            if (z5) {
                i15 = fontMetricsInt.ascent;
            }
            this.f586i = i15;
            if (z4) {
                i14 = i13;
            }
            this.f589l = i14;
            this.f590m = fontMetricsInt.ascent - i15;
            this.f591n = i14 - i13;
        }
        fontMetricsInt.ascent = z2 ? this.f586i : this.f587j;
        fontMetricsInt.descent = z3 ? this.f589l : this.f588k;
    }
}

package z1;

import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Trace;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextPaint f7151a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f7152b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f7153c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a2.g f7154d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Layout f7155e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f7156f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f7157g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f7158h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f7159i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float f7160j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f7161k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Paint.FontMetricsInt f7162l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f7163m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final b2.h[] f7164n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Rect f7165o = new Rect();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public f3.a f7166p;

    /* JADX WARN: Code duplicated, block: B:100:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:104:0x0214  */
    /* JADX WARN: Code duplicated, block: B:106:0x021a  */
    /* JADX WARN: Code duplicated, block: B:108:0x0220  */
    /* JADX WARN: Code duplicated, block: B:111:0x022c  */
    /* JADX WARN: Code duplicated, block: B:116:0x023e  */
    /* JADX WARN: Code duplicated, block: B:134:0x0300  */
    /* JADX WARN: Code duplicated, block: B:136:0x0304  */
    /* JADX WARN: Code duplicated, block: B:144:0x0234 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:62:0x0139 A[PHI: r14
      0x0139: PHI (r14v10 int) = (r14v9 int), (r14v12 int) binds: [B:67:0x014b, B:60:0x0132] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:93:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:95:0x01e7  */
    /* JADX WARN: Code duplicated, block: B:96:0x01ea  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v15 */
    /* JADX WARN: Type inference failed for: r12v17 */
    /* JADX WARN: Type inference failed for: r12v18 */
    /* JADX WARN: Type inference failed for: r12v23 */
    /* JADX WARN: Type inference failed for: r12v24 */
    /* JADX WARN: Type inference failed for: r14v13 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r27v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v21 */
    /* JADX WARN: Type inference failed for: r4v35 */
    public y(CharSequence charSequence, float f5, TextPaint textPaint, int i5, TextUtils.TruncateAt truncateAt, int i6, boolean z2, int i7, int i8, int i9, int i10, int i11, int i12, n nVar) {
        Layout.Alignment alignment;
        int i13;
        int i14;
        TextDirectionHeuristic textDirectionHeuristic;
        p pVar;
        Layout layoutA;
        char c5;
        long j5;
        int i15;
        int i16;
        long j6;
        b2.h[] hVarArr;
        int i17;
        Layout layout;
        Paint.FontMetricsInt fontMetricsInt;
        int length;
        int i18;
        int iMax;
        int iMax2;
        int i19;
        Spanned spanned;
        int i20;
        ?? r12;
        boolean zA;
        int topPadding;
        boolean zC;
        BoringLayout boringLayoutA;
        this.f7151a = textPaint;
        this.f7152b = z2;
        int length2 = charSequence.length();
        TextDirectionHeuristic textDirectionHeuristicA = z.a(i6);
        Layout.Alignment alignment2 = w.f7148a;
        if (i5 == 0) {
            alignment = Layout.Alignment.ALIGN_NORMAL;
        } else if (i5 == 1) {
            alignment = Layout.Alignment.ALIGN_OPPOSITE;
        } else if (i5 == 2) {
            alignment = Layout.Alignment.ALIGN_CENTER;
        } else if (i5 != 3) {
            alignment = i5 != 4 ? Layout.Alignment.ALIGN_NORMAL : w.f7149b;
        } else {
            alignment = w.f7148a;
        }
        Layout.Alignment alignment3 = alignment;
        boolean z3 = (charSequence instanceof Spanned) && ((Spanned) charSequence).nextSpanTransition(-1, length2, b2.a.class) < length2;
        Trace.beginSection("TextLayout:initLayout");
        try {
            BoringLayout.Metrics metricsA = nVar.a();
            double d5 = f5;
            int iCeil = (int) Math.ceil(d5);
            boolean z4 = true;
            p pVar2 = t.f7132a;
            if (metricsA == null || nVar.b() > f5 || z3) {
                z4 = true;
                i13 = 0;
                this.f7161k = false;
                i14 = i7;
                textDirectionHeuristic = textDirectionHeuristicA;
                pVar = pVar2;
                layoutA = pVar.a(new v(charSequence, charSequence.length(), textPaint, iCeil, textDirectionHeuristic, alignment3, i14, truncateAt, (int) Math.ceil(d5), i12, z2, i8, i9, i10, i11));
            } else {
                this.f7161k = true;
                if (iCeil < 0) {
                    throw new IllegalArgumentException("negative width");
                }
                if (iCeil < 0) {
                    throw new IllegalArgumentException("negative ellipsized width");
                }
                if (Build.VERSION.SDK_INT >= 33) {
                    i13 = 0;
                    boringLayoutA = c.a(charSequence, textPaint, iCeil, alignment3, 1.0f, 0.0f, metricsA, z2, true, truncateAt, iCeil);
                } else {
                    i13 = 0;
                    boringLayoutA = d.a(charSequence, textPaint, iCeil, alignment3, 1.0f, 0.0f, metricsA, z2, truncateAt, iCeil);
                }
                i14 = i7;
                layoutA = boringLayoutA;
                textDirectionHeuristic = textDirectionHeuristicA;
                pVar = pVar2;
            }
            this.f7155e = layoutA;
            Trace.endSection();
            int iMin = Math.min(layoutA.getLineCount(), i14);
            this.f7156f = iMin;
            int i21 = iMin - 1;
            this.f7153c = (iMin >= i14 && (layoutA.getEllipsisCount(i21) > 0 || layoutA.getLineEnd(i21) != charSequence.length())) ? 1 : i13;
            long j7 = z.f7168b;
            if (!z2) {
                if (this.f7161k) {
                    BoringLayout boringLayout = (BoringLayout) layoutA;
                    i15 = 33;
                    if (Build.VERSION.SDK_INT >= 33) {
                        zC = c.c(boringLayout);
                    } else {
                        r12 = i13;
                    }
                } else {
                    i15 = 33;
                    StaticLayout staticLayout = (StaticLayout) layoutA;
                    int i22 = Build.VERSION.SDK_INT;
                    if (i22 >= 33) {
                        zA = s.a(staticLayout);
                    } else if (i22 >= 28) {
                        r12 = z4;
                    } else {
                        r12 = i13;
                    }
                }
                if (r12 != 0) {
                    r12 = zA;
                    r12 = zC;
                    c5 = ' ';
                    j5 = 4294967295L;
                } else {
                    r12 = zA;
                    TextPaint paint = layoutA.getPaint();
                    CharSequence text = layoutA.getText();
                    c5 = ' ';
                    Rect rectA = t.a(paint, text, layoutA.getLineStart(i13), layoutA.getLineEnd(i13));
                    int lineAscent = layoutA.getLineAscent(i13);
                    j5 = 4294967295L;
                    int i23 = rectA.top;
                    if (i23 < lineAscent) {
                        r12 = zC;
                        topPadding = lineAscent - i23;
                    } else {
                        r12 = zC;
                        topPadding = layoutA.getTopPadding();
                    }
                    rectA = iMin != 1 ? t.a(paint, text, layoutA.getLineStart(i21), layoutA.getLineEnd(i21)) : rectA;
                    int lineDescent = layoutA.getLineDescent(i21);
                    int i24 = rectA.bottom;
                    int bottomPadding = i24 > lineDescent ? i24 - lineDescent : layoutA.getBottomPadding();
                    if (topPadding == 0 && bottomPadding == 0) {
                        i16 = 1;
                        j6 = j7;
                    } else {
                        i16 = 1;
                        j6 = (((long) topPadding) << 32) | (((long) bottomPadding) & 4294967295L);
                    }
                }
                if (layoutA.getText() instanceof Spanned) {
                    CharSequence text2 = layoutA.getText();
                    b4.l.c("null cannot be cast to non-null type android.text.Spanned", text2);
                    spanned = (Spanned) text2;
                    if (spanned.nextSpanTransition(-1, spanned.length(), b2.h.class) != spanned.length()) {
                        i20 = i16;
                    } else {
                        i20 = i13;
                    }
                    if (i20 == 0 || layoutA.getText().length() <= 0) {
                        CharSequence text3 = layoutA.getText();
                        b4.l.c("null cannot be cast to non-null type android.text.Spanned", text3);
                        hVarArr = (b2.h[]) ((Spanned) text3).getSpans(i13, layoutA.getText().length(), b2.h.class);
                    } else {
                        hVarArr = null;
                    }
                } else {
                    hVarArr = null;
                }
                this.f7164n = hVarArr;
                if (hVarArr != null) {
                    length = hVarArr.length;
                    i18 = i13;
                    iMax = i18;
                    iMax2 = iMax;
                    while (i18 < length) {
                        b2.h hVar = hVarArr[i18];
                        int i25 = hVar.f590m;
                        iMax = i25 < 0 ? Math.max(iMax, Math.abs(i25)) : iMax;
                        i19 = hVar.f591n;
                        if (i19 < 0) {
                            iMax2 = Math.max(iMax, Math.abs(i19));
                        }
                        i18++;
                    }
                    if (iMax == 0 || iMax2 != 0) {
                        j7 = (((long) iMax2) & j5) | (((long) iMax) << c5);
                    } else {
                        j7 = z.f7168b;
                    }
                }
                this.f7157g = Math.max((int) (j6 >> c5), (int) (j7 >> c5));
                this.f7158h = Math.max((int) (j6 & j5), (int) (j7 & j5));
                TextPaint textPaint2 = this.f7151a;
                b2.h[] hVarArr2 = this.f7164n;
                i17 = this.f7156f - 1;
                layout = this.f7155e;
                if (layout.getLineStart(i17) == layout.getLineEnd(i17) || hVarArr2 == null || hVarArr2.length == 0) {
                    fontMetricsInt = null;
                } else {
                    SpannableString spannableString = new SpannableString("\u200b");
                    if (hVarArr2.length == 0) {
                        throw new NoSuchElementException("Array is empty.");
                    }
                    b2.h hVar2 = hVarArr2[i13];
                    spannableString.setSpan(new b2.h(hVar2.f581d, spannableString.length(), (i17 == 0 || !hVar2.f584g) ? hVar2.f584g : i13, hVar2.f584g, hVar2.f585h), i13, spannableString.length(), i15);
                    StaticLayout staticLayoutA = pVar.a(new v(spannableString, spannableString.length(), textPaint2, Integer.MAX_VALUE, textDirectionHeuristic, l.f7121a, Integer.MAX_VALUE, null, Integer.MAX_VALUE, 0, this.f7152b, 0, 0, 0, 0));
                    fontMetricsInt = new Paint.FontMetricsInt();
                    fontMetricsInt.ascent = staticLayoutA.getLineAscent(i13);
                    fontMetricsInt.descent = staticLayoutA.getLineDescent(i13);
                    fontMetricsInt.top = staticLayoutA.getLineTop(i13);
                    fontMetricsInt.bottom = staticLayoutA.getLineBottom(i13);
                }
                this.f7163m = fontMetricsInt != null ? fontMetricsInt.bottom - ((int) (e(i21) - g(i21))) : i13;
                this.f7162l = fontMetricsInt;
                Layout layout2 = this.f7155e;
                this.f7159i = a.a.v(layout2, i21, layout2.getPaint());
                Layout layout3 = this.f7155e;
                this.f7160j = a.a.w(layout3, i21, layout3.getPaint());
            }
            c5 = ' ';
            j5 = 4294967295L;
            i15 = 33;
            i16 = 1;
            j6 = j7;
            if (layoutA.getText() instanceof Spanned) {
                hVarArr = null;
            } else {
                CharSequence text4 = layoutA.getText();
                b4.l.c("null cannot be cast to non-null type android.text.Spanned", text4);
                spanned = (Spanned) text4;
                if (spanned.nextSpanTransition(-1, spanned.length(), b2.h.class) != spanned.length()) {
                    i20 = i16;
                } else {
                    i20 = i13;
                }
                if (i20 == 0) {
                }
                CharSequence text5 = layoutA.getText();
                b4.l.c("null cannot be cast to non-null type android.text.Spanned", text5);
                hVarArr = (b2.h[]) ((Spanned) text5).getSpans(i13, layoutA.getText().length(), b2.h.class);
            }
            this.f7164n = hVarArr;
            if (hVarArr != null) {
                length = hVarArr.length;
                i18 = i13;
                iMax = i18;
                iMax2 = iMax;
                while (i18 < length) {
                    b2.h hVar3 = hVarArr[i18];
                    int i26 = hVar3.f590m;
                    if (i26 < 0) {
                    }
                    i19 = hVar3.f591n;
                    if (i19 < 0) {
                        iMax2 = Math.max(iMax, Math.abs(i19));
                    }
                    i18++;
                }
                if (iMax == 0) {
                    j7 = (((long) iMax2) & j5) | (((long) iMax) << c5);
                } else {
                    j7 = (((long) iMax2) & j5) | (((long) iMax) << c5);
                }
            }
            this.f7157g = Math.max((int) (j6 >> c5), (int) (j7 >> c5));
            this.f7158h = Math.max((int) (j6 & j5), (int) (j7 & j5));
            TextPaint textPaint3 = this.f7151a;
            b2.h[] hVarArr3 = this.f7164n;
            i17 = this.f7156f - 1;
            layout = this.f7155e;
            if (layout.getLineStart(i17) == layout.getLineEnd(i17)) {
                fontMetricsInt = null;
            } else {
                fontMetricsInt = null;
            }
            this.f7163m = fontMetricsInt != null ? fontMetricsInt.bottom - ((int) (e(i21) - g(i21))) : i13;
            this.f7162l = fontMetricsInt;
            Layout layout4 = this.f7155e;
            this.f7159i = a.a.v(layout4, i21, layout4.getPaint());
            Layout layout5 = this.f7155e;
            this.f7160j = a.a.w(layout5, i21, layout5.getPaint());
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public final int a() {
        boolean z2 = this.f7153c;
        Layout layout = this.f7155e;
        return (z2 ? layout.getLineBottom(this.f7156f - 1) : layout.getHeight()) + this.f7157g + this.f7158h + this.f7163m;
    }

    public final float b(int i5) {
        if (i5 == this.f7156f - 1) {
            return this.f7159i + this.f7160j;
        }
        return 0.0f;
    }

    public final f3.a c() {
        f3.a aVar = this.f7166p;
        if (aVar != null) {
            return aVar;
        }
        f3.a aVar2 = new f3.a(this.f7155e);
        this.f7166p = aVar2;
        return aVar2;
    }

    public final float d(int i5) {
        Paint.FontMetricsInt fontMetricsInt;
        return this.f7157g + ((i5 != this.f7156f + (-1) || (fontMetricsInt = this.f7162l) == null) ? this.f7155e.getLineBaseline(i5) : g(i5) - fontMetricsInt.ascent);
    }

    public final float e(int i5) {
        Paint.FontMetricsInt fontMetricsInt;
        int i6 = this.f7156f;
        int i7 = i6 - 1;
        Layout layout = this.f7155e;
        if (i5 != i7 || (fontMetricsInt = this.f7162l) == null) {
            return this.f7157g + layout.getLineBottom(i5) + (i5 == i6 + (-1) ? this.f7158h : 0);
        }
        return layout.getLineBottom(i5 - 1) + fontMetricsInt.bottom;
    }

    public final int f(int i5) {
        Layout layout = this.f7155e;
        return layout.getEllipsisStart(i5) == 0 ? layout.getLineEnd(i5) : layout.getText().length();
    }

    public final float g(int i5) {
        return this.f7155e.getLineTop(i5) + (i5 == 0 ? 0 : this.f7157g);
    }

    public final float h(int i5, boolean z2) {
        return b(this.f7155e.getLineForOffset(i5)) + c().g(i5, true, z2);
    }

    public final float i(int i5, boolean z2) {
        return b(this.f7155e.getLineForOffset(i5)) + c().g(i5, false, z2);
    }

    public final a2.g j() {
        a2.g gVar = this.f7154d;
        if (gVar != null) {
            return gVar;
        }
        Layout layout = this.f7155e;
        a2.g gVar2 = new a2.g(layout.getText(), layout.getText().length(), this.f7151a.getTextLocale());
        this.f7154d = gVar2;
        return gVar2;
    }
}

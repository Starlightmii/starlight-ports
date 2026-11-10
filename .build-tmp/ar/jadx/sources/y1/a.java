package y1;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import c0.j1;
import java.util.ArrayList;
import y0.l0;
import y0.p0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g2.d f6703a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f6704b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f6705c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final z1.y f6706d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final CharSequence f6707e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f6708f;

    /* JADX WARN: Code duplicated, block: B:100:0x012b  */
    /* JADX WARN: Code duplicated, block: B:103:0x0158  */
    /* JADX WARN: Code duplicated, block: B:59:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:69:0x00cf  */
    public a(g2.d dVar, int i5, boolean z2, long j5) {
        CharSequence charSequence;
        int i6;
        int i7;
        TextUtils.TruncateAt truncateAt;
        i2.b[] bVarArr;
        Object obj;
        x0.d dVar2;
        float fH;
        int i8;
        Spannable spannableString;
        this.f6703a = dVar;
        this.f6704b = i5;
        this.f6705c = j5;
        if (k2.a.j(j5) != 0 || k2.a.k(j5) != 0) {
            throw new IllegalArgumentException("Setting Constraints.minWidth and Constraints.minHeight is not supported, these should be the default zero values instead.");
        }
        if (i5 < 1) {
            throw new IllegalArgumentException("maxLines should be greater than 0");
        }
        h0 h0Var = dVar.f2517b;
        CharSequence charSequence2 = dVar.f2523h;
        if (z2 && !k2.m.a(h0Var.f6780a.f6716h, d4.a.w(0)) && !k2.m.a(h0Var.f6780a.f6716h, k2.m.f3421c) && (i8 = h0Var.f6781b.f6809a) != Integer.MIN_VALUE && i8 != 5 && i8 != 4 && charSequence2.length() != 0) {
            if (charSequence2 instanceof Spannable) {
                charSequence = charSequence2;
                spannableString = (Spannable) charSequence2;
            } else {
                charSequence = charSequence2;
                spannableString = new SpannableString(charSequence2);
            }
            spannableString.setSpan(new b2.c(), spannableString.length() - 1, spannableString.length() - 1, 33);
            charSequence = spannableString;
        }
        charSequence = charSequence2;
        charSequence = charSequence2;
        charSequence = charSequence2;
        charSequence = charSequence2;
        charSequence = charSequence2;
        charSequence = charSequence2;
        charSequence = charSequence2;
        charSequence = charSequence2;
        charSequence = charSequence2;
        charSequence = charSequence2;
        charSequence = charSequence2;
        charSequence = charSequence2;
        charSequence = charSequence2;
        this.f6707e = charSequence;
        r rVar = h0Var.f6781b;
        a0 a0Var = h0Var.f6780a;
        int i9 = rVar.f6809a;
        int i10 = 3;
        int i11 = i9 == 1 ? 3 : i9 == 2 ? 4 : i9 == 3 ? 2 : (i9 != 5 && i9 == 6) ? 1 : 0;
        int i12 = i9 == 4 ? 1 : 0;
        int i13 = rVar.f6816h == 2 ? Build.VERSION.SDK_INT <= 32 ? 2 : 4 : 0;
        int i14 = rVar.f6815g;
        int i15 = i14 & 255;
        if (i15 == 1) {
            i6 = 0;
        } else if (i15 == 2) {
            i6 = 1;
        } else if (i15 == 3) {
            i6 = 2;
        } else {
            i6 = 0;
        }
        int i16 = (i14 >> 8) & 255;
        if (i16 == 1) {
            i10 = 0;
        } else if (i16 == 2) {
            i10 = 1;
        } else if (i16 == 3) {
            i10 = 2;
        } else if (i16 != 4) {
            i10 = 0;
        }
        int i17 = (i14 >> 16) & 255;
        int i18 = (i17 != 1 && i17 == 2) ? 1 : 0;
        if (z2) {
            int i19 = i11;
            truncateAt = TextUtils.TruncateAt.END;
            i7 = i19;
        } else {
            i7 = i11;
            truncateAt = null;
        }
        int i20 = i10;
        int i21 = i6;
        z1.y yVarA = a(i7, i12, truncateAt, i5, i13, i21, i20, i18);
        int i22 = yVarA.f7156f;
        if (z2) {
            int i23 = i7;
            if (yVarA.a() <= k2.a.h(j5) || i5 <= 1) {
                this.f6706d = yVarA;
            } else {
                int iH = k2.a.h(j5);
                for (int i24 = 0; i24 < i22; i24++) {
                    if (yVarA.e(i24) > iH) {
                        i22 = i24;
                        break;
                    }
                }
                if (i22 >= 0 && i22 != this.f6704b) {
                    yVarA = a(i23, i12, truncateAt, i22 < 1 ? 1 : i22, i13, i21, i20, i18);
                }
                this.f6706d = yVarA;
            }
        } else {
            this.f6706d = yVarA;
        }
        this.f6703a.f2522g.c(a0Var.f6709a.c(), p3.j.f(d(), b()), a0Var.f6709a.a());
        Layout layout = this.f6706d.f7155e;
        if (layout.getText() instanceof Spanned) {
            CharSequence text = layout.getText();
            b4.l.c("null cannot be cast to non-null type android.text.Spanned", text);
            Spanned spanned = (Spanned) text;
            if (spanned.nextSpanTransition(-1, spanned.length(), i2.b.class) != spanned.length()) {
                CharSequence text2 = layout.getText();
                b4.l.c("null cannot be cast to non-null type android.text.Spanned", text2);
                bVarArr = (i2.b[]) ((Spanned) text2).getSpans(0, layout.getText().length(), i2.b.class);
            } else {
                bVarArr = null;
            }
        } else {
            bVarArr = null;
        }
        if (bVarArr != null) {
            b4.b bVarH = b4.l.h(bVarArr);
            while (bVarH.hasNext()) {
                ((i2.b) bVarH.next()).f2669f.setValue(new x0.f(p3.j.f(d(), b())));
            }
        }
        CharSequence charSequence3 = this.f6707e;
        if (charSequence3 instanceof Spanned) {
            Spanned spanned2 = (Spanned) charSequence3;
            Object[] spans = spanned2.getSpans(0, charSequence3.length(), b2.i.class);
            ArrayList arrayList = new ArrayList(spans.length);
            for (Object obj2 : spans) {
                b2.i iVar = (b2.i) obj2;
                int spanStart = spanned2.getSpanStart(iVar);
                int spanEnd = spanned2.getSpanEnd(iVar);
                int lineForOffset = this.f6706d.f7155e.getLineForOffset(spanStart);
                boolean z3 = lineForOffset >= this.f6704b;
                boolean z4 = this.f6706d.f7155e.getEllipsisCount(lineForOffset) > 0 && spanEnd > this.f6706d.f7155e.getEllipsisStart(lineForOffset);
                boolean z5 = spanEnd > this.f6706d.f(lineForOffset);
                if (z4 || z5 || z3) {
                    dVar2 = null;
                } else {
                    int iOrdinal = (this.f6706d.f7155e.isRtlCharAt(spanStart) ? j2.h.f3319e : j2.h.f3318d).ordinal();
                    if (iOrdinal == 0) {
                        fH = this.f6706d.h(spanStart, false);
                    } else {
                        if (iOrdinal != 1) {
                            throw new i4.p();
                        }
                        float fH2 = this.f6706d.h(spanStart, false);
                        if (!iVar.f595g) {
                            throw new IllegalStateException("PlaceholderSpan is not laid out yet.");
                        }
                        fH = fH2 - iVar.f593e;
                    }
                    if (!iVar.f595g) {
                        throw new IllegalStateException("PlaceholderSpan is not laid out yet.");
                    }
                    float f5 = iVar.f593e + fH;
                    float fD = this.f6706d.d(lineForOffset) - iVar.b();
                    dVar2 = new x0.d(fH, fD, f5, iVar.b() + fD);
                }
                arrayList.add(dVar2);
            }
            obj = arrayList;
        } else {
            obj = q3.s.f5477d;
        }
        this.f6708f = obj;
    }

    public final z1.y a(int i5, int i6, TextUtils.TruncateAt truncateAt, int i7, int i8, int i9, int i10, int i11) {
        t tVar;
        float fD = d();
        g2.d dVar = this.f6703a;
        g2.f fVar = dVar.f2522g;
        int i12 = dVar.f2527l;
        z1.n nVar = dVar.f2524i;
        h0 h0Var = dVar.f2517b;
        g2.a aVar = g2.b.f2514a;
        v vVar = h0Var.f6782c;
        return new z1.y(this.f6707e, fD, fVar, i5, truncateAt, i12, (vVar == null || (tVar = vVar.f6824b) == null) ? false : tVar.f6821a, i7, i9, i10, i11, i8, i6, nVar);
    }

    public final float b() {
        return this.f6706d.a();
    }

    /* JADX WARN: Code duplicated, block: B:42:0x00ab  */
    public final long c(x0.d dVar, int i5, e2.f0 f0Var) {
        a2.f dVar2;
        int i6;
        int[] iArrA;
        RectF rectFV = l0.v(dVar);
        int i7 = (i5 != 0 && i5 == 1) ? 1 : 0;
        j1 j1Var = new j1(13, f0Var);
        z1.y yVar = this.f6706d;
        Layout layout = yVar.f7155e;
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 34) {
            iArrA = z1.b.f7112a.a(yVar, rectFV, i7, j1Var);
        } else {
            f3.a aVarC = yVar.c();
            if (i7 == 1) {
                dVar2 = new a2.h(layout.getText(), 0, yVar.j());
            } else {
                CharSequence text = layout.getText();
                dVar2 = i8 >= 29 ? new a2.d(text, yVar.f7151a) : new a2.e(text);
            }
            a2.f fVar = dVar2;
            int lineForVertical = layout.getLineForVertical((int) rectFV.top);
            if (rectFV.top <= yVar.e(lineForVertical) || (lineForVertical = lineForVertical + 1) < yVar.f7156f) {
                int i9 = lineForVertical;
                int lineForVertical2 = layout.getLineForVertical((int) rectFV.bottom);
                if (lineForVertical2 != 0 || rectFV.bottom >= yVar.g(0)) {
                    int iD = z1.t.d(yVar, layout, aVarC, i9, rectFV, fVar, j1Var, true);
                    while (true) {
                        i6 = i9;
                        if (iD != -1 || i6 >= lineForVertical2) {
                            break;
                        }
                        i9 = i6 + 1;
                        iD = z1.t.d(yVar, layout, aVarC, i9, rectFV, fVar, j1Var, true);
                    }
                    if (iD == -1) {
                        iArrA = null;
                    } else {
                        int i10 = lineForVertical2;
                        int iD2 = z1.t.d(yVar, layout, aVarC, i10, rectFV, fVar, j1Var, false);
                        while (iD2 == -1 && i6 < i10) {
                            i10--;
                            iD2 = z1.t.d(yVar, layout, aVarC, i10, rectFV, fVar, j1Var, false);
                        }
                        if (iD2 == -1) {
                            iArrA = null;
                        } else {
                            iArrA = new int[]{fVar.e(iD + 1), fVar.h(iD2 - 1)};
                        }
                    }
                } else {
                    iArrA = null;
                }
            } else {
                iArrA = null;
            }
        }
        return iArrA == null ? g0.f6768b : p3.j.g(iArrA[0], iArrA[1]);
    }

    public final float d() {
        return k2.a.i(this.f6705c);
    }

    public final void e(y0.r rVar) {
        Canvas canvasA = y0.e.a(rVar);
        z1.y yVar = this.f6706d;
        if (yVar.f7153c) {
            canvasA.save();
            canvasA.clipRect(0.0f, 0.0f, d(), b());
        }
        int i5 = yVar.f7157g;
        if (canvasA.getClipBounds(yVar.f7165o)) {
            if (i5 != 0) {
                canvasA.translate(0.0f, i5);
            }
            z1.x xVar = z1.z.f7167a;
            xVar.f7150a = canvasA;
            yVar.f7155e.draw(xVar);
            if (i5 != 0) {
                canvasA.translate(0.0f, (-1) * i5);
            }
        }
        if (yVar.f7153c) {
            canvasA.restore();
        }
    }

    public final void f(y0.r rVar, long j5, p0 p0Var, j2.j jVar, a1.g gVar) {
        g2.f fVar = this.f6703a.f2522g;
        int i5 = fVar.f2533c;
        fVar.d(j5);
        fVar.f(p0Var);
        fVar.g(jVar);
        fVar.e(gVar);
        fVar.b(3);
        e(rVar);
        fVar.b(i5);
    }

    public final void g(y0.r rVar, y0.p pVar, float f5, p0 p0Var, j2.j jVar, a1.g gVar) {
        g2.f fVar = this.f6703a.f2522g;
        int i5 = fVar.f2533c;
        fVar.c(pVar, p3.j.f(d(), b()), f5);
        fVar.f(p0Var);
        fVar.g(jVar);
        fVar.e(gVar);
        fVar.b(3);
        e(rVar);
        fVar.b(i5);
    }
}

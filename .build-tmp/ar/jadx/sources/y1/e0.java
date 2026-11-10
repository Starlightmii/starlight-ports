package y1;

import android.graphics.RectF;
import android.text.Layout;
import c0.b1;
import java.text.BreakIterator;
import java.util.ArrayList;
import y0.l0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d0 f6753a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m f6754b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f6755c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f6756d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f6757e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f6758f;

    public e0(d0 d0Var, m mVar, long j5) {
        this.f6753a = d0Var;
        this.f6754b = mVar;
        this.f6755c = j5;
        ArrayList arrayList = mVar.f6796h;
        float fD = 0.0f;
        this.f6756d = arrayList.isEmpty() ? 0.0f : ((o) arrayList.get(0)).f6799a.f6706d.d(0);
        if (!arrayList.isEmpty()) {
            o oVar = (o) q3.l.b0(arrayList);
            z1.y yVar = oVar.f6799a.f6706d;
            fD = yVar.d(yVar.f7156f - 1) + oVar.f6804f;
        }
        this.f6757e = fD;
        this.f6758f = mVar.f6795g;
    }

    public final j2.h a(int i5) {
        m mVar = this.f6754b;
        ArrayList arrayList = mVar.f6796h;
        mVar.i(i5);
        o oVar = (o) arrayList.get(i5 == ((f) mVar.f6789a.f2449a).f6759d.length() ? p3.j.z(arrayList) : p3.p.l(i5, arrayList));
        return oVar.f6799a.f6706d.f7155e.isRtlCharAt(oVar.b(i5)) ? j2.h.f3319e : j2.h.f3318d;
    }

    public final x0.d b(int i5) {
        float fI;
        float fI2;
        float fH;
        float fH2;
        m mVar = this.f6754b;
        mVar.h(i5);
        ArrayList arrayList = mVar.f6796h;
        o oVar = (o) arrayList.get(p3.p.l(i5, arrayList));
        a aVar = oVar.f6799a;
        int iB = oVar.b(i5);
        CharSequence charSequence = aVar.f6707e;
        if (iB < 0 || iB >= charSequence.length()) {
            StringBuilder sbG = b.b.g("offset(", iB, ") is out of bounds [0,");
            sbG.append(charSequence.length());
            sbG.append(')');
            throw new IllegalArgumentException(sbG.toString().toString());
        }
        z1.y yVar = aVar.f6706d;
        Layout layout = yVar.f7155e;
        int lineForOffset = layout.getLineForOffset(iB);
        float fG = yVar.g(lineForOffset);
        float fE = yVar.e(lineForOffset);
        boolean z2 = layout.getParagraphDirection(lineForOffset) == 1;
        boolean zIsRtlCharAt = layout.isRtlCharAt(iB);
        if (!z2 || zIsRtlCharAt) {
            if (z2 && zIsRtlCharAt) {
                fH = yVar.i(iB, false);
                fH2 = yVar.i(iB + 1, true);
            } else if (zIsRtlCharAt) {
                fH = yVar.h(iB, false);
                fH2 = yVar.h(iB + 1, true);
            } else {
                fI = yVar.i(iB, false);
                fI2 = yVar.i(iB + 1, true);
            }
            float f5 = fH;
            fI = fH2;
            fI2 = f5;
        } else {
            fI = yVar.h(iB, false);
            fI2 = yVar.h(iB + 1, true);
        }
        RectF rectF = new RectF(fI, fG, fI2, fE);
        float f6 = rectF.left;
        float f7 = rectF.top;
        float f8 = rectF.right;
        float f9 = rectF.bottom;
        long jB = p3.j.b(0.0f, oVar.f6804f);
        return new x0.d(x0.c.d(jB) + f6, x0.c.e(jB) + f7, x0.c.d(jB) + f8, x0.c.e(jB) + f9);
    }

    public final x0.d c(int i5) {
        m mVar = this.f6754b;
        ArrayList arrayList = mVar.f6796h;
        mVar.i(i5);
        o oVar = (o) arrayList.get(i5 == ((f) mVar.f6789a.f2449a).f6759d.length() ? p3.j.z(arrayList) : p3.p.l(i5, arrayList));
        a aVar = oVar.f6799a;
        int iB = oVar.b(i5);
        CharSequence charSequence = aVar.f6707e;
        z1.y yVar = aVar.f6706d;
        if (iB < 0 || iB > charSequence.length()) {
            StringBuilder sbG = b.b.g("offset(", iB, ") is out of bounds [0,");
            sbG.append(charSequence.length());
            sbG.append(']');
            throw new IllegalArgumentException(sbG.toString().toString());
        }
        float fH = yVar.h(iB, false);
        int lineForOffset = yVar.f7155e.getLineForOffset(iB);
        float fG = yVar.g(lineForOffset);
        float fE = yVar.e(lineForOffset);
        long jB = p3.j.b(0.0f, oVar.f6804f);
        return new x0.d(x0.c.d(jB) + fH, x0.c.e(jB) + fG, x0.c.d(jB) + fH, x0.c.e(jB) + fE);
    }

    public final int d(int i5, boolean z2) {
        int iF;
        m mVar = this.f6754b;
        mVar.j(i5);
        ArrayList arrayList = mVar.f6796h;
        o oVar = (o) arrayList.get(p3.p.m(i5, arrayList));
        a aVar = oVar.f6799a;
        int i6 = i5 - oVar.f6802d;
        z1.y yVar = aVar.f6706d;
        if (z2) {
            Layout layout = yVar.f7155e;
            if (layout.getEllipsisStart(i6) == 0) {
                f3.a aVarC = yVar.c();
                Layout layout2 = (Layout) aVarC.f2449a;
                iF = aVarC.j(layout2.getLineEnd(i6), layout2.getLineStart(i6));
            } else {
                iF = layout.getEllipsisStart(i6) + layout.getLineStart(i6);
            }
        } else {
            iF = yVar.f(i6);
        }
        return iF + oVar.f6800b;
    }

    public final int e(int i5) {
        int iL;
        m mVar = this.f6754b;
        ArrayList arrayList = mVar.f6796h;
        if (i5 >= ((f) mVar.f6789a.f2449a).f6759d.length()) {
            iL = p3.j.z(arrayList);
        } else {
            iL = i5 < 0 ? 0 : p3.p.l(i5, arrayList);
        }
        o oVar = (o) arrayList.get(iL);
        return oVar.f6799a.f6706d.f7155e.getLineForOffset(oVar.b(i5)) + oVar.f6802d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e0)) {
            return false;
        }
        e0 e0Var = (e0) obj;
        return b4.l.a(this.f6753a, e0Var.f6753a) && this.f6754b.equals(e0Var.f6754b) && k2.j.a(this.f6755c, e0Var.f6755c) && this.f6756d == e0Var.f6756d && this.f6757e == e0Var.f6757e && b4.l.a(this.f6758f, e0Var.f6758f);
    }

    public final float f(int i5) {
        m mVar = this.f6754b;
        mVar.j(i5);
        ArrayList arrayList = mVar.f6796h;
        o oVar = (o) arrayList.get(p3.p.m(i5, arrayList));
        a aVar = oVar.f6799a;
        int i6 = i5 - oVar.f6802d;
        z1.y yVar = aVar.f6706d;
        return yVar.f7155e.getLineLeft(i6) + (i6 == yVar.f7156f + (-1) ? yVar.f7159i : 0.0f);
    }

    public final float g(int i5) {
        m mVar = this.f6754b;
        mVar.j(i5);
        ArrayList arrayList = mVar.f6796h;
        o oVar = (o) arrayList.get(p3.p.m(i5, arrayList));
        a aVar = oVar.f6799a;
        int i6 = i5 - oVar.f6802d;
        z1.y yVar = aVar.f6706d;
        return yVar.f7155e.getLineRight(i6) + (i6 == yVar.f7156f + (-1) ? yVar.f7160j : 0.0f);
    }

    public final int h(int i5) {
        m mVar = this.f6754b;
        mVar.j(i5);
        ArrayList arrayList = mVar.f6796h;
        o oVar = (o) arrayList.get(p3.p.m(i5, arrayList));
        a aVar = oVar.f6799a;
        return aVar.f6706d.f7155e.getLineStart(i5 - oVar.f6802d) + oVar.f6800b;
    }

    public final int hashCode() {
        return this.f6758f.hashCode() + b.b.a(this.f6757e, b.b.a(this.f6756d, b.b.b((this.f6754b.hashCode() + (this.f6753a.hashCode() * 31)) * 31, 31, this.f6755c), 31), 31);
    }

    public final j2.h i(int i5) {
        m mVar = this.f6754b;
        ArrayList arrayList = mVar.f6796h;
        mVar.i(i5);
        o oVar = (o) arrayList.get(i5 == ((f) mVar.f6789a.f2449a).f6759d.length() ? p3.j.z(arrayList) : p3.p.l(i5, arrayList));
        a aVar = oVar.f6799a;
        int iB = oVar.b(i5);
        z1.y yVar = aVar.f6706d;
        return yVar.f7155e.getParagraphDirection(yVar.f7155e.getLineForOffset(iB)) == 1 ? j2.h.f3318d : j2.h.f3319e;
    }

    public final y0.j j(int i5, int i6) {
        m mVar = this.f6754b;
        f fVar = (f) mVar.f6789a.f2449a;
        if (i5 >= 0 && i5 <= i6 && i6 <= fVar.f6759d.length()) {
            if (i5 == i6) {
                return l0.h();
            }
            y0.j jVarH = l0.h();
            p3.p.o(mVar.f6796h, p3.j.g(i5, i6), new b1(jVarH, i5, i6, 3));
            return jVarH;
        }
        throw new IllegalArgumentException(("Start(" + i5 + ") or End(" + i6 + ") is out of range [0.." + fVar.f6759d.length() + "), or start > end!").toString());
    }

    public final long k(int i5) {
        int iPreceding;
        int iFollowing;
        int iFollowing2;
        m mVar = this.f6754b;
        ArrayList arrayList = mVar.f6796h;
        mVar.i(i5);
        o oVar = (o) arrayList.get(i5 == ((f) mVar.f6789a.f2449a).f6759d.length() ? p3.j.z(arrayList) : p3.p.l(i5, arrayList));
        a aVar = oVar.f6799a;
        int iB = oVar.b(i5);
        a2.g gVarJ = aVar.f6706d.j();
        BreakIterator breakIterator = (BreakIterator) gVarJ.f47e;
        gVarJ.a(iB);
        if (gVarJ.f(breakIterator.preceding(iB))) {
            gVarJ.a(iB);
            iPreceding = iB;
            while (iPreceding != -1 && (!gVarJ.f(iPreceding) || gVarJ.d(iPreceding))) {
                gVarJ.a(iPreceding);
                iPreceding = breakIterator.preceding(iPreceding);
            }
        } else {
            gVarJ.a(iB);
            if (gVarJ.e(iB)) {
                iPreceding = (!breakIterator.isBoundary(iB) || gVarJ.c(iB)) ? breakIterator.preceding(iB) : iB;
            } else {
                iPreceding = gVarJ.c(iB) ? breakIterator.preceding(iB) : -1;
            }
        }
        if (iPreceding == -1) {
            iPreceding = iB;
        }
        gVarJ.a(iB);
        if (gVarJ.d(breakIterator.following(iB))) {
            gVarJ.a(iB);
            iFollowing = iB;
            while (iFollowing != -1 && (gVarJ.f(iFollowing) || !gVarJ.d(iFollowing))) {
                gVarJ.a(iFollowing);
                iFollowing = breakIterator.following(iFollowing);
            }
        } else {
            gVarJ.a(iB);
            if (gVarJ.c(iB)) {
                if (!breakIterator.isBoundary(iB) || gVarJ.e(iB)) {
                    iFollowing2 = breakIterator.following(iB);
                    iFollowing = iFollowing2;
                } else {
                    iFollowing = iB;
                }
            } else if (gVarJ.e(iB)) {
                iFollowing2 = breakIterator.following(iB);
                iFollowing = iFollowing2;
            } else {
                iFollowing = -1;
            }
        }
        if (iFollowing != -1) {
            iB = iFollowing;
        }
        return oVar.a(p3.j.g(iPreceding, iB), false);
    }

    public final String toString() {
        return "TextLayoutResult(layoutInput=" + this.f6753a + ", multiParagraph=" + this.f6754b + ", size=" + ((Object) k2.j.b(this.f6755c)) + ", firstBaseline=" + this.f6756d + ", lastBaseline=" + this.f6757e + ", placeholderRects=" + this.f6758f + ')';
    }
}

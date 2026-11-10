package e2;

import android.graphics.Matrix;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.InputMethodManager;
import q1.m0;
import y0.l0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q1.u f2001a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a1.b f2002b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2004d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2005e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2006f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f2007g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f2008h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2009i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public x f2010j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public y1.e0 f2011k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public r f2012l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public x0.d f2014n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public x0.d f2015o;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f2003c = new Object();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public b4.m f2013m = d.f1993g;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final CursorAnchorInfo.Builder f2016p = new CursorAnchorInfo.Builder();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final float[] f2017q = y0.f0.a();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Matrix f2018r = new Matrix();

    public e(q1.u uVar, a1.b bVar) {
        this.f2001a = uVar;
        this.f2002b = bVar;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, p3.d] */
    /* JADX WARN: Type inference failed for: r3v3, types: [a4.c, b4.m] */
    public final void a() {
        a1.b bVar = this.f2002b;
        ?? r4 = bVar.f24e;
        InputMethodManager inputMethodManager = (InputMethodManager) r4.getValue();
        View view = (View) bVar.f23d;
        if (inputMethodManager.isActive(view)) {
            ?? r5 = this.f2013m;
            float[] fArr = this.f2017q;
            r5.c(new y0.f0(fArr));
            q1.u uVar = this.f2001a;
            uVar.z();
            y0.f0.g(fArr, uVar.O);
            float fD = x0.c.d(uVar.S);
            float fE = x0.c.e(uVar.S);
            float[] fArr2 = uVar.N;
            y0.f0.d(fArr2);
            y0.f0.h(fArr2, fD, fE);
            m0.u(fArr, fArr2);
            Matrix matrix = this.f2018r;
            l0.q(matrix, fArr);
            x xVar = this.f2010j;
            b4.l.b(xVar);
            long j5 = xVar.f2063b;
            r rVar = this.f2012l;
            b4.l.b(rVar);
            y1.e0 e0Var = this.f2011k;
            b4.l.b(e0Var);
            x0.d dVar = this.f2014n;
            b4.l.b(dVar);
            x0.d dVar2 = this.f2015o;
            b4.l.b(dVar2);
            boolean z2 = this.f2006f;
            boolean z3 = this.f2007g;
            boolean z4 = this.f2008h;
            boolean z5 = this.f2009i;
            CursorAnchorInfo.Builder builder = this.f2016p;
            builder.reset();
            builder.setMatrix(matrix);
            y1.g0 g0Var = xVar.f2064c;
            int iE = y1.g0.e(j5);
            builder.setSelectionRange(iE, y1.g0.d(j5));
            j2.h hVar = j2.h.f3319e;
            if (z2 && iE >= 0) {
                int iB = rVar.b(iE);
                x0.d dVarC = e0Var.c(iB);
                float fL = d4.a.l(dVarC.f6534a, 0.0f, (int) (e0Var.f6755c >> 32));
                boolean zP = d4.a.p(dVar, fL, dVarC.f6535b);
                boolean zP2 = d4.a.p(dVar, fL, dVarC.f6537d);
                boolean z6 = e0Var.a(iB) == hVar;
                int i5 = (zP || zP2) ? 1 : 0;
                if (!zP || !zP2) {
                    i5 |= 2;
                }
                if (z6) {
                    i5 |= 4;
                }
                float f5 = dVarC.f6535b;
                float f6 = dVarC.f6537d;
                builder.setInsertionMarkerLocation(fL, f5, f6, f6, i5);
            }
            if (z3) {
                int iE2 = g0Var != null ? y1.g0.e(g0Var.f6770a) : -1;
                int iD = g0Var != null ? y1.g0.d(g0Var.f6770a) : -1;
                if (iE2 >= 0 && iE2 < iD) {
                    builder.setComposingText(iE2, xVar.f2062a.f6759d.subSequence(iE2, iD));
                    int iB2 = rVar.b(iE2);
                    int iB3 = rVar.b(iD);
                    float[] fArr3 = new float[(iB3 - iB2) * 4];
                    e0Var.f6754b.a(p3.j.g(iB2, iB3), fArr3);
                    int i6 = iE2;
                    while (i6 < iD) {
                        int iB4 = rVar.b(i6);
                        int i7 = (iB4 - iB2) * 4;
                        float f7 = fArr3[i7];
                        int i8 = iD;
                        float f8 = fArr3[i7 + 1];
                        int i9 = iB2;
                        float f9 = fArr3[i7 + 2];
                        float f10 = fArr3[i7 + 3];
                        int i10 = i6;
                        int i11 = (dVar.f6536c <= f7 || f9 <= dVar.f6534a || dVar.f6537d <= f8 || f10 <= dVar.f6535b) ? 0 : 1;
                        if (!d4.a.p(dVar, f7, f8) || !d4.a.p(dVar, f9, f10)) {
                            i11 |= 2;
                        }
                        if (e0Var.a(iB4) == hVar) {
                            i11 |= 4;
                        }
                        builder.addCharacterBounds(i10, f7, f8, f9, f10, i11);
                        i6 = i10 + 1;
                        iD = i8;
                        iB2 = i9;
                    }
                }
            }
            int i12 = Build.VERSION.SDK_INT;
            if (i12 >= 33 && z4) {
                b.a(builder, dVar2);
            }
            if (i12 >= 34 && z5) {
                c.a(builder, e0Var, dVar);
            }
            ((InputMethodManager) r4.getValue()).updateCursorAnchorInfo(view, builder.build());
            this.f2005e = false;
        }
    }
}

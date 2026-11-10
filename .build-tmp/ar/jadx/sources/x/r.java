package x;

import android.graphics.Matrix;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.InputMethodManager;
import y0.f0;
import y0.l0;
import y1.e0;
import y1.g0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f6482a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final p f6483b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f6485d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6486e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f6487f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f6488g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f6489h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f6490i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public e2.x f6491j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public e0 f6492k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public e2.r f6493l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public x0.d f6494m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public x0.d f6495n;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f6484c = new Object();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final CursorAnchorInfo.Builder f6496o = new CursorAnchorInfo.Builder();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final float[] f6497p = f0.a();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Matrix f6498q = new Matrix();

    public r(b bVar, p pVar) {
        this.f6482a = bVar;
        this.f6483b = pVar;
    }

    public final void a() {
        p pVar = this.f6483b;
        InputMethodManager inputMethodManagerA = pVar.a();
        View view = pVar.f6477b;
        if (inputMethodManagerA.isActive(view)) {
            float[] fArr = this.f6497p;
            f0.d(fArr);
            n1.p pVar2 = (n1.p) this.f6482a.f6462k.f6481t.getValue();
            if (pVar2 != null) {
                if (!pVar2.N()) {
                    pVar2 = null;
                }
                if (pVar2 != null) {
                    pVar2.P(fArr);
                }
            }
            x0.d dVar = this.f6495n;
            b4.l.b(dVar);
            float f5 = -dVar.f6534a;
            x0.d dVar2 = this.f6495n;
            b4.l.b(dVar2);
            f0.h(fArr, f5, -dVar2.f6535b);
            Matrix matrix = this.f6498q;
            l0.q(matrix, fArr);
            e2.x xVar = this.f6491j;
            b4.l.b(xVar);
            long j5 = xVar.f2063b;
            e2.r rVar = this.f6493l;
            b4.l.b(rVar);
            e0 e0Var = this.f6492k;
            b4.l.b(e0Var);
            x0.d dVar3 = this.f6494m;
            b4.l.b(dVar3);
            x0.d dVar4 = this.f6495n;
            b4.l.b(dVar4);
            boolean z2 = this.f6487f;
            boolean z3 = this.f6488g;
            boolean z4 = this.f6489h;
            boolean z5 = this.f6490i;
            CursorAnchorInfo.Builder builder = this.f6496o;
            builder.reset();
            builder.setMatrix(matrix);
            g0 g0Var = xVar.f2064c;
            int iE = g0.e(j5);
            builder.setSelectionRange(iE, g0.d(j5));
            j2.h hVar = j2.h.f3319e;
            if (z2 && iE >= 0) {
                int iB = rVar.b(iE);
                x0.d dVarC = e0Var.c(iB);
                float fL = d4.a.l(dVarC.f6534a, 0.0f, (int) (e0Var.f6755c >> 32));
                boolean zM = q.f.m(dVar3, fL, dVarC.f6535b);
                boolean zM2 = q.f.m(dVar3, fL, dVarC.f6537d);
                boolean z6 = e0Var.a(iB) == hVar;
                int i5 = (zM || zM2) ? 1 : 0;
                if (!zM || !zM2) {
                    i5 |= 2;
                }
                if (z6) {
                    i5 |= 4;
                }
                float f6 = dVarC.f6535b;
                float f7 = dVarC.f6537d;
                builder.setInsertionMarkerLocation(fL, f6, f7, f7, i5);
            }
            if (z3) {
                int iE2 = g0Var != null ? g0.e(g0Var.f6770a) : -1;
                int iD = g0Var != null ? g0.d(g0Var.f6770a) : -1;
                if (iE2 >= 0 && iE2 < iD) {
                    builder.setComposingText(iE2, xVar.f2062a.f6759d.subSequence(iE2, iD));
                    int iB2 = rVar.b(iE2);
                    int iB3 = rVar.b(iD);
                    float[] fArr2 = new float[(iB3 - iB2) * 4];
                    e0Var.f6754b.a(p3.j.g(iB2, iB3), fArr2);
                    int i6 = iE2;
                    while (i6 < iD) {
                        int iB4 = rVar.b(i6);
                        int i7 = (iB4 - iB2) * 4;
                        float f8 = fArr2[i7];
                        int i8 = iD;
                        float f9 = fArr2[i7 + 1];
                        int i9 = iB2;
                        float f10 = fArr2[i7 + 2];
                        float f11 = fArr2[i7 + 3];
                        int i10 = i6;
                        int i11 = (dVar3.f6536c <= f8 || f10 <= dVar3.f6534a || dVar3.f6537d <= f9 || f11 <= dVar3.f6535b) ? 0 : 1;
                        if (!q.f.m(dVar3, f8, f9) || !q.f.m(dVar3, f10, f11)) {
                            i11 |= 2;
                        }
                        if (e0Var.a(iB4) == hVar) {
                            i11 |= 4;
                        }
                        builder.addCharacterBounds(i10, f8, f9, f10, f11, i11);
                        i6 = i10 + 1;
                        iD = i8;
                        iB2 = i9;
                    }
                }
            }
            int i12 = Build.VERSION.SDK_INT;
            if (i12 >= 33 && z4) {
                h.a(builder, dVar4);
            }
            if (i12 >= 34 && z5) {
                i.a(builder, e0Var, dVar3);
            }
            pVar.a().updateCursorAnchorInfo(view, builder.build());
            this.f6486e = false;
        }
    }
}

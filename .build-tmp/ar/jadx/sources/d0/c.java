package d0;

import c0.b0;
import c0.n4;
import f0.b2;
import f0.j0;
import f0.z1;
import y1.h0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final y1.v f1513a = new y1.v(null, new y1.t());

    public static final void a(long j5, h0 h0Var, a4.e eVar, f0.p pVar, int i5) {
        int i6;
        f0.v vVar = (f0.v) pVar;
        vVar.X(-716124955);
        if ((i5 & 6) == 0) {
            i6 = (vVar.e(j5) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.f(h0Var) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar.h(eVar) ? 256 : 128;
        }
        if ((i6 & 147) == 146 && vVar.z()) {
            vVar.Q();
        } else {
            j0 j0Var = n4.f1120a;
            f0.l.b(new z1[]{b0.f762a.a(new y0.u(j5)), j0Var.a(((h0) vVar.j(j0Var)).d(h0Var))}, eVar, vVar, ((i6 >> 3) & 112) | 8);
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new l(j5, h0Var, eVar, i5, 0);
        }
    }
}

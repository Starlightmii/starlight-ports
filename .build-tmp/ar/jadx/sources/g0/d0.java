package g0;

import f0.b2;
import f0.o2;
import f0.w2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d0 extends i0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final d0 f2473c = new d0(1, 0, 2);

    @Override // g0.i0
    public final void a(e2.l lVar, f0.c cVar, w2 w2Var, n0.j jVar, j0 j0Var) {
        int iC = lVar.c(0);
        int i5 = w2Var.f2411v;
        int iN = w2Var.N(w2Var.f2391b, w2Var.r(i5));
        int iG = w2Var.g(w2Var.f2391b, w2Var.r(i5 + 1));
        for (int iMax = Math.max(iN, iG - iC); iMax < iG; iMax++) {
            Object obj = w2Var.f2392c[w2Var.h(iMax)];
            if (obj instanceof o2) {
                jVar.d((o2) obj);
            } else if (obj instanceof b2) {
                ((b2) obj).d();
            }
        }
        if (iC <= 0) {
            f0.z.a("Check failed");
        }
        int i6 = w2Var.f2411v;
        int iN2 = w2Var.N(w2Var.f2391b, w2Var.r(i6));
        int iG2 = w2Var.g(w2Var.f2391b, w2Var.r(i6 + 1)) - iC;
        if (iG2 < iN2) {
            f0.z.a("Check failed");
        }
        w2Var.J(iG2, iC, i6);
        int i7 = w2Var.f2398i;
        if (i7 >= iN2) {
            w2Var.f2398i = i7 - iC;
        }
    }
}

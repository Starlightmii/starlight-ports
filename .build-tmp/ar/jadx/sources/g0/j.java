package g0;

import f0.w2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j extends i0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final j f2485c = new j(0, 2, 1);

    @Override // g0.i0
    public final void a(e2.l lVar, f0.c cVar, w2 w2Var, n0.j jVar, j0 j0Var) {
        int i5;
        n0.e eVar = (n0.e) lVar.d(0);
        int iC = w2Var.c((f0.a) lVar.d(1));
        if (w2Var.f2409t >= iC) {
            f0.z.a("Check failed");
        }
        h1.c.K(w2Var, cVar, iC);
        int i6 = w2Var.f2409t;
        int iE = w2Var.f2411v;
        while (iE >= 0 && !w2Var.y(iE)) {
            iE = w2Var.E(w2Var.f2391b, iE);
        }
        int iU = iE + 1;
        int iL = 0;
        while (iU < i6) {
            if (w2Var.v(i6, iU)) {
                if (w2Var.y(iU)) {
                    iL = 0;
                }
                iU++;
            } else {
                iL += w2Var.y(iU) ? 1 : w2Var.f2391b[(w2Var.r(iU) * 5) + 1] & 67108863;
                iU += w2Var.u(iU);
            }
        }
        while (true) {
            i5 = w2Var.f2409t;
            if (i5 >= iC) {
                break;
            }
            if (w2Var.v(iC, i5)) {
                int i7 = w2Var.f2409t;
                if (i7 < w2Var.f2410u && (w2Var.f2391b[(w2Var.r(i7) * 5) + 1] & 1073741824) != 0) {
                    cVar.e(w2Var.D(w2Var.f2409t));
                    iL = 0;
                }
                w2Var.P();
            } else {
                iL += w2Var.L();
            }
        }
        if (i5 != iC) {
            f0.z.a("Check failed");
        }
        eVar.f4062a = iL;
    }
}

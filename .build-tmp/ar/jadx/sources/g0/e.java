package g0;

import f0.o2;
import f0.w2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends i0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e f2474c = new e(0, 2, 1);

    @Override // g0.i0
    public final void a(e2.l lVar, f0.c cVar, w2 w2Var, n0.j jVar, j0 j0Var) {
        f0.a aVar = (f0.a) lVar.d(0);
        Object objD = lVar.d(1);
        if (objD instanceof o2) {
            o2 o2Var = (o2) objD;
            jVar.f4076e.b(o2Var);
            jVar.f4075d.a(o2Var);
        }
        if (w2Var.f2403n != 0) {
            f0.z.a("Can only append a slot if not current inserting");
        }
        int i5 = w2Var.f2398i;
        int i6 = w2Var.f2399j;
        int iC = w2Var.c(aVar);
        int iG = w2Var.g(w2Var.f2391b, w2Var.r(iC + 1));
        w2Var.f2398i = iG;
        w2Var.f2399j = iG;
        w2Var.x(1, iC);
        if (i5 >= iG) {
            i5++;
            i6++;
        }
        w2Var.f2392c[iG] = objD;
        w2Var.f2398i = i5;
        w2Var.f2399j = i6;
    }
}

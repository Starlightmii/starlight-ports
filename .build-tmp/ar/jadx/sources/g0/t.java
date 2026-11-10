package g0;

import f0.t2;
import f0.w2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t extends i0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final t f2505c = new t(0, 3, 1);

    @Override // g0.i0
    public final void a(e2.l lVar, f0.c cVar, w2 w2Var, n0.j jVar, j0 j0Var) {
        a2.h hVar;
        t2 t2Var = (t2) lVar.d(1);
        f0.a aVar = (f0.a) lVar.d(0);
        c cVar2 = (c) lVar.d(2);
        w2 w2VarD = t2Var.d();
        if (j0Var != null) {
            try {
                hVar = new a2.h(j0Var, 9, w2Var);
            } catch (Throwable th) {
                w2VarD.e(false);
                throw th;
            }
        } else {
            hVar = null;
        }
        if (!cVar2.f2470l.Q()) {
            f0.z.a("FixupList has pending fixup operations that were not realized. Were there mismatched insertNode() and endNodeInsert() calls?");
        }
        cVar2.f2469k.P(cVar, w2VarD, jVar, hVar);
        w2VarD.e(true);
        w2Var.d();
        aVar.getClass();
        w2Var.A(t2Var, t2Var.a(aVar));
        w2Var.k();
    }
}

package g0;

import f0.l1;
import f0.w2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f extends i0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final f f2476c = new f(0, 2, 1);

    @Override // g0.i0
    public final void a(e2.l lVar, f0.c cVar, w2 w2Var, n0.j jVar, j0 j0Var) {
        n0.e eVar = (n0.e) lVar.d(1);
        int i5 = eVar != null ? eVar.f4062a : 0;
        a aVar = (a) lVar.d(0);
        if (i5 > 0) {
            cVar = new l1(cVar, i5);
        }
        aVar.O(cVar, w2Var, jVar, j0Var != null ? new a2.h(j0Var, 9, w2Var) : null);
    }
}

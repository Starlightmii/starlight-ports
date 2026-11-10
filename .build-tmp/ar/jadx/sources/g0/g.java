package g0;

import f0.w2;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g extends i0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final g f2478c = new g(0, 2, 1);

    @Override // g0.i0
    public final void a(e2.l lVar, f0.c cVar, w2 w2Var, n0.j jVar, j0 j0Var) {
        int i5 = ((n0.e) lVar.d(0)).f4062a;
        List list = (List) lVar.d(1);
        int size = list.size();
        for (int i6 = 0; i6 < size; i6++) {
            Object obj = list.get(i6);
            int i7 = i5 + i6;
            cVar.c(i7, obj);
            cVar.h(i7, obj);
        }
    }
}

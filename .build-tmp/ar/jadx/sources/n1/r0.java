package n1;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r0 extends p1.b0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final r0 f4150b = new r0("Undefined intrinsics block and it is required");

    @Override // n1.f0
    public final g0 h(h0 h0Var, List list, long j5) {
        boolean zIsEmpty = list.isEmpty();
        q3.t tVar = q3.t.f5478d;
        if (zIsEmpty) {
            return h0Var.F(k2.a.k(j5), k2.a.j(j5), tVar, n0.f4139g);
        }
        if (list.size() == 1) {
            m0 m0VarC = ((e0) list.get(0)).c(j5);
            return h0Var.F(h1.c.q(j5, m0VarC.f4130d), h1.c.p(j5, m0VarC.f4131e), tVar, new p0(m0VarC, 0));
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i5 = 0; i5 < size; i5++) {
            arrayList.add(((e0) list.get(i5)).c(j5));
        }
        int size2 = arrayList.size();
        int iMax = 0;
        int iMax2 = 0;
        for (int i6 = 0; i6 < size2; i6++) {
            m0 m0Var = (m0) arrayList.get(i6);
            iMax = Math.max(m0Var.f4130d, iMax);
            iMax2 = Math.max(m0Var.f4131e, iMax2);
        }
        return h0Var.F(h1.c.q(j5, iMax), h1.c.p(j5, iMax2), tVar, new q0(0, arrayList));
    }
}

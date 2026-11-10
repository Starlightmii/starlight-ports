package r;

import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r implements n1.f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r0.h f5614a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f5615b;

    public r(r0.h hVar, boolean z2) {
        this.f5614a = hVar;
        this.f5615b = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return this.f5614a.equals(rVar.f5614a) && this.f5615b == rVar.f5615b;
    }

    @Override // n1.f0
    public final n1.g0 h(n1.h0 h0Var, List list, long j5) {
        int iK;
        int iJ;
        n1.m0 m0VarC;
        boolean zIsEmpty = list.isEmpty();
        q3.t tVar = q3.t.f5478d;
        if (zIsEmpty) {
            return h0Var.F(k2.a.k(j5), k2.a.j(j5), tVar, m.f5585g);
        }
        long jB = this.f5615b ? j5 : k2.a.b(j5, 0, 0, 0, 0, 10);
        if (list.size() == 1) {
            n1.e0 e0Var = (n1.e0) list.get(0);
            Object objH = e0Var.h();
            l lVar = objH instanceof l ? (l) objH : null;
            if (lVar != null ? lVar.f5583r : false) {
                iK = k2.a.k(j5);
                iJ = k2.a.j(j5);
                int iK2 = k2.a.k(j5);
                int iJ2 = k2.a.j(j5);
                if (!(iK2 >= 0 && iJ2 >= 0)) {
                    d4.a.J("width(" + iK2 + ") and height(" + iJ2 + ") must be >= 0");
                    throw null;
                }
                m0VarC = e0Var.c(h1.c.r(iK2, iK2, iJ2, iJ2));
            } else {
                m0VarC = e0Var.c(jB);
                iK = Math.max(k2.a.k(j5), m0VarC.f4130d);
                iJ = Math.max(k2.a.j(j5), m0VarC.f4131e);
            }
            int i5 = iJ;
            int i6 = iK;
            return h0Var.F(i6, i5, tVar, new p(m0VarC, e0Var, h0Var, i6, i5, this));
        }
        n1.m0[] m0VarArr = new n1.m0[list.size()];
        b4.u uVar = new b4.u();
        uVar.f695d = k2.a.k(j5);
        b4.u uVar2 = new b4.u();
        uVar2.f695d = k2.a.j(j5);
        int size = list.size();
        boolean z2 = false;
        for (int i7 = 0; i7 < size; i7++) {
            n1.e0 e0Var2 = (n1.e0) list.get(i7);
            Object objH2 = e0Var2.h();
            l lVar2 = objH2 instanceof l ? (l) objH2 : null;
            if (lVar2 != null ? lVar2.f5583r : false) {
                z2 = true;
            } else {
                n1.m0 m0VarC2 = e0Var2.c(jB);
                m0VarArr[i7] = m0VarC2;
                uVar.f695d = Math.max(uVar.f695d, m0VarC2.f4130d);
                uVar2.f695d = Math.max(uVar2.f695d, m0VarC2.f4131e);
            }
        }
        if (z2) {
            int i8 = uVar.f695d;
            int i9 = i8 != Integer.MAX_VALUE ? i8 : 0;
            int i10 = uVar2.f695d;
            long jB2 = h1.c.b(i9, i8, i10 != Integer.MAX_VALUE ? i10 : 0, i10);
            int size2 = list.size();
            for (int i11 = 0; i11 < size2; i11++) {
                n1.e0 e0Var3 = (n1.e0) list.get(i11);
                Object objH3 = e0Var3.h();
                l lVar3 = objH3 instanceof l ? (l) objH3 : null;
                if (lVar3 != null ? lVar3.f5583r : false) {
                    m0VarArr[i11] = e0Var3.c(jB2);
                }
            }
        }
        return h0Var.F(uVar.f695d, uVar2.f695d, tVar, new q(m0VarArr, list, h0Var, uVar, uVar2, this));
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f5615b) + (this.f5614a.hashCode() * 31);
    }

    public final String toString() {
        return "BoxMeasurePolicy(alignment=" + this.f5614a + ", propagateMinConstraints=" + this.f5615b + ')';
    }
}

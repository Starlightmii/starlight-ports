package r;

import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s0 implements n1.f0, o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f5621a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r0.g f5622b;

    public s0(e eVar, r0.g gVar) {
        this.f5621a = eVar;
        this.f5622b = gVar;
    }

    @Override // r.o0
    public final n1.g0 a(n1.m0[] m0VarArr, n1.h0 h0Var, int[] iArr, int i5, int i6) {
        return h0Var.F(i5, i6, q3.t.f5478d, new r0(m0VarArr, this, i6, iArr));
    }

    @Override // n1.f0
    public final int b(n1.m mVar, List list, int i5) {
        int iM = mVar.M(this.f5621a.a());
        if (list.isEmpty()) {
            return 0;
        }
        int size = list.size();
        int iMax = 0;
        int i6 = 0;
        float f5 = 0.0f;
        for (int i7 = 0; i7 < size; i7++) {
            n1.e0 e0Var = (n1.e0) list.get(i7);
            float fD = c.d(c.c(e0Var));
            int iA0 = e0Var.a0(i5);
            if (fD == 0.0f) {
                i6 += iA0;
            } else if (fD > 0.0f) {
                f5 += fD;
                iMax = Math.max(iMax, Math.round(iA0 / fD));
            }
        }
        return ((list.size() - 1) * iM) + Math.round(iMax * f5) + i6;
    }

    @Override // r.o0
    public final int c(n1.m0 m0Var) {
        return m0Var.f4131e;
    }

    @Override // n1.f0
    public final int d(n1.m mVar, List list, int i5) {
        int iM = mVar.M(this.f5621a.a());
        if (list.isEmpty()) {
            return 0;
        }
        int size = list.size();
        int iMax = 0;
        int i6 = 0;
        float f5 = 0.0f;
        for (int i7 = 0; i7 < size; i7++) {
            n1.e0 e0Var = (n1.e0) list.get(i7);
            float fD = c.d(c.c(e0Var));
            int iC0 = e0Var.c0(i5);
            if (fD == 0.0f) {
                i6 += iC0;
            } else if (fD > 0.0f) {
                f5 += fD;
                iMax = Math.max(iMax, Math.round(iC0 / fD));
            }
        }
        return ((list.size() - 1) * iM) + Math.round(iMax * f5) + i6;
    }

    @Override // r.o0
    public final void e(int i5, n1.h0 h0Var, int[] iArr, int[] iArr2) {
        this.f5621a.b(h0Var, i5, iArr, h0Var.getLayoutDirection(), iArr2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof s0)) {
            return false;
        }
        s0 s0Var = (s0) obj;
        return b4.l.a(this.f5621a, s0Var.f5621a) && b4.l.a(this.f5622b, s0Var.f5622b);
    }

    @Override // r.o0
    public final int f(n1.m0 m0Var) {
        return m0Var.f4130d;
    }

    @Override // r.o0
    public final long g(int i5, int i6, int i7, boolean z2) {
        if (!z2) {
            return h1.c.b(i5, i6, 0, i7);
        }
        int iMin = Math.min(i5, 262142);
        int iMin2 = i6 == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.min(i6, 262142);
        int iH = h1.c.h(iMin2 == Integer.MAX_VALUE ? iMin : iMin2);
        return h1.c.b(iMin, iMin2, Math.min(iH, 0), i7 != Integer.MAX_VALUE ? Math.min(iH, i7) : Integer.MAX_VALUE);
    }

    @Override // n1.f0
    public final n1.g0 h(n1.h0 h0Var, List list, long j5) {
        return c.e(this, k2.a.k(j5), k2.a.j(j5), k2.a.i(j5), k2.a.h(j5), h0Var.M(this.f5621a.a()), h0Var, list, new n1.m0[list.size()], list.size());
    }

    public final int hashCode() {
        return this.f5622b.hashCode() + (this.f5621a.hashCode() * 31);
    }

    @Override // n1.f0
    public final int i(n1.m mVar, List list, int i5) {
        int iM = mVar.M(this.f5621a.a());
        if (list.isEmpty()) {
            return 0;
        }
        int iMin = Math.min((list.size() - 1) * iM, i5);
        int size = list.size();
        int iMax = 0;
        float f5 = 0.0f;
        for (int i6 = 0; i6 < size; i6++) {
            n1.e0 e0Var = (n1.e0) list.get(i6);
            float fD = c.d(c.c(e0Var));
            if (fD == 0.0f) {
                int iMin2 = Math.min(e0Var.c0(Integer.MAX_VALUE), i5 == Integer.MAX_VALUE ? Integer.MAX_VALUE : i5 - iMin);
                iMin += iMin2;
                iMax = Math.max(iMax, e0Var.e0(iMin2));
            } else if (fD > 0.0f) {
                f5 += fD;
            }
        }
        int iRound = f5 == 0.0f ? 0 : i5 == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.round(Math.max(i5 - iMin, 0) / f5);
        int size2 = list.size();
        for (int i7 = 0; i7 < size2; i7++) {
            n1.e0 e0Var2 = (n1.e0) list.get(i7);
            float fD2 = c.d(c.c(e0Var2));
            if (fD2 > 0.0f) {
                iMax = Math.max(iMax, e0Var2.e0(iRound != Integer.MAX_VALUE ? Math.round(iRound * fD2) : Integer.MAX_VALUE));
            }
        }
        return iMax;
    }

    @Override // n1.f0
    public final int j(n1.m mVar, List list, int i5) {
        int iM = mVar.M(this.f5621a.a());
        if (list.isEmpty()) {
            return 0;
        }
        int iMin = Math.min((list.size() - 1) * iM, i5);
        int size = list.size();
        int iMax = 0;
        float f5 = 0.0f;
        for (int i6 = 0; i6 < size; i6++) {
            n1.e0 e0Var = (n1.e0) list.get(i6);
            float fD = c.d(c.c(e0Var));
            if (fD == 0.0f) {
                int iMin2 = Math.min(e0Var.c0(Integer.MAX_VALUE), i5 == Integer.MAX_VALUE ? Integer.MAX_VALUE : i5 - iMin);
                iMin += iMin2;
                iMax = Math.max(iMax, e0Var.d(iMin2));
            } else if (fD > 0.0f) {
                f5 += fD;
            }
        }
        int iRound = f5 == 0.0f ? 0 : i5 == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.round(Math.max(i5 - iMin, 0) / f5);
        int size2 = list.size();
        for (int i7 = 0; i7 < size2; i7++) {
            n1.e0 e0Var2 = (n1.e0) list.get(i7);
            float fD2 = c.d(c.c(e0Var2));
            if (fD2 > 0.0f) {
                iMax = Math.max(iMax, e0Var2.d(iRound != Integer.MAX_VALUE ? Math.round(iRound * fD2) : Integer.MAX_VALUE));
            }
        }
        return iMax;
    }

    public final String toString() {
        return "RowMeasurePolicy(horizontalArrangement=" + this.f5621a + ", verticalAlignment=" + this.f5622b + ')';
    }
}

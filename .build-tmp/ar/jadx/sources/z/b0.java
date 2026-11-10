package z;

import java.util.List;
import v.e1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y1.f f6899a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f6900b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final y1.e0 f6901c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final e2.r f6902d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final j0 f6903e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f6904f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final y1.f f6905g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final e2.x f6906h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final e1 f6907i;

    public b0(e2.x xVar, e2.r rVar, e1 e1Var, j0 j0Var) {
        y1.f fVar = xVar.f2062a;
        long j5 = xVar.f2063b;
        y1.e0 e0Var = e1Var != null ? e1Var.f5899a : null;
        this.f6899a = fVar;
        this.f6900b = j5;
        this.f6901c = e0Var;
        this.f6902d = rVar;
        this.f6903e = j0Var;
        this.f6904f = j5;
        this.f6905g = fVar;
        this.f6906h = xVar;
        this.f6907i = e1Var;
    }

    public final List a(a4.c cVar) {
        if (!y1.g0.b(this.f6904f)) {
            return p3.j.H(new e2.a("", 0), new e2.w(y1.g0.e(this.f6904f), y1.g0.e(this.f6904f)));
        }
        e2.i iVar = (e2.i) cVar.c(this);
        if (iVar != null) {
            return p3.j.G(iVar);
        }
        return null;
    }

    public final Integer b() {
        y1.e0 e0Var = this.f6901c;
        if (e0Var == null) {
            return null;
        }
        int iD = y1.g0.d(this.f6904f);
        e2.r rVar = this.f6902d;
        return Integer.valueOf(rVar.a(e0Var.d(e0Var.e(rVar.b(iD)), true)));
    }

    public final Integer c() {
        int length;
        y1.e0 e0Var = this.f6901c;
        if (e0Var == null) {
            return null;
        }
        int iP = p();
        while (true) {
            y1.f fVar = this.f6899a;
            if (iP < fVar.f6759d.length()) {
                int length2 = this.f6905g.f6759d.length() - 1;
                if (iP <= length2) {
                    length2 = iP;
                }
                long jK = e0Var.k(length2);
                int i5 = y1.g0.f6769c;
                int i6 = (int) (jK & 4294967295L);
                if (i6 > iP) {
                    length = this.f6902d.a(i6);
                    break;
                }
                iP++;
            } else {
                length = fVar.f6759d.length();
                break;
            }
        }
        return Integer.valueOf(length);
    }

    public final Integer d() {
        int iA;
        y1.e0 e0Var = this.f6901c;
        if (e0Var == null) {
            return null;
        }
        for (int iP = p(); iP > 0; iP--) {
            int length = this.f6905g.f6759d.length() - 1;
            if (iP <= length) {
                length = iP;
            }
            long jK = e0Var.k(length);
            int i5 = y1.g0.f6769c;
            int i6 = (int) (jK >> 32);
            if (i6 < iP) {
                iA = this.f6902d.a(i6);
                return Integer.valueOf(iA);
            }
        }
        iA = 0;
        return Integer.valueOf(iA);
    }

    public final boolean e() {
        y1.e0 e0Var = this.f6901c;
        return (e0Var != null ? e0Var.i(p()) : null) != j2.h.f3319e;
    }

    public final int f(y1.e0 e0Var, int i5) {
        int iP = p();
        j0 j0Var = this.f6903e;
        if (j0Var.f6971a == null) {
            j0Var.f6971a = Float.valueOf(e0Var.c(iP).f6534a);
        }
        int iE = e0Var.e(iP);
        y1.m mVar = e0Var.f6754b;
        int i6 = iE + i5;
        if (i6 < 0) {
            return 0;
        }
        if (i6 >= mVar.f6794f) {
            return this.f6905g.f6759d.length();
        }
        float fB = mVar.b(i6) - 1;
        Float f5 = j0Var.f6971a;
        b4.l.b(f5);
        float fFloatValue = f5.floatValue();
        if ((e() && fFloatValue >= e0Var.g(i6)) || (!e() && fFloatValue <= e0Var.f(i6))) {
            return e0Var.d(i6, true);
        }
        return this.f6902d.a(mVar.e(p3.j.b(f5.floatValue(), fB)));
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0013  */
    public final int g(e1 e1Var, int i5) {
        x0.d dVarK;
        n1.p pVar = e1Var.f5900b;
        y1.e0 e0Var = e1Var.f5899a;
        if (pVar == null) {
            dVarK = x0.d.f6533e;
        } else {
            n1.p pVar2 = e1Var.f5901c;
            dVarK = pVar2 != null ? pVar2.k(pVar, true) : null;
            if (dVarK == null) {
                dVarK = x0.d.f6533e;
            }
        }
        long j5 = this.f6906h.f2063b;
        int i6 = y1.g0.f6769c;
        int i7 = (int) (j5 & 4294967295L);
        e2.r rVar = this.f6902d;
        x0.d dVarC = e0Var.c(rVar.b(i7));
        return rVar.a(e0Var.f6754b.e(p3.j.b(dVarC.f6534a, (x0.f.b(p3.j.f(dVarK.c(), dVarK.b())) * i5) + dVarC.f6535b)));
    }

    public final void h() {
        j0 j0Var = this.f6903e;
        j0Var.f6971a = null;
        y1.f fVar = this.f6905g;
        if (fVar.f6759d.length() > 0) {
            if (e()) {
                j0Var.f6971a = null;
                if (fVar.f6759d.length() > 0) {
                    String str = fVar.f6759d;
                    long j5 = this.f6904f;
                    int i5 = y1.g0.f6769c;
                    int iP = v.l0.p((int) (j5 & 4294967295L), str);
                    if (iP != -1) {
                        o(iP, iP);
                        return;
                    }
                    return;
                }
                return;
            }
            j0Var.f6971a = null;
            if (fVar.f6759d.length() > 0) {
                String str2 = fVar.f6759d;
                long j6 = this.f6904f;
                int i6 = y1.g0.f6769c;
                int iM = v.l0.m((int) (j6 & 4294967295L), str2);
                if (iM != -1) {
                    o(iM, iM);
                }
            }
        }
    }

    public final void i() {
        this.f6903e.f6971a = null;
        y1.f fVar = this.f6905g;
        String str = fVar.f6759d;
        String str2 = fVar.f6759d;
        if (str.length() > 0) {
            int iN = v.l0.n(str2, y1.g0.d(this.f6904f));
            if (iN == y1.g0.d(this.f6904f) && iN != str2.length()) {
                iN = v.l0.n(str2, iN + 1);
            }
            o(iN, iN);
        }
    }

    public final void j() {
        this.f6903e.f6971a = null;
        y1.f fVar = this.f6905g;
        String str = fVar.f6759d;
        String str2 = fVar.f6759d;
        if (str.length() > 0) {
            int iO = v.l0.o(str2, y1.g0.e(this.f6904f));
            if (iO == y1.g0.e(this.f6904f) && iO != 0) {
                iO = v.l0.o(str2, iO - 1);
            }
            o(iO, iO);
        }
    }

    public final void k() {
        j0 j0Var = this.f6903e;
        j0Var.f6971a = null;
        y1.f fVar = this.f6905g;
        if (fVar.f6759d.length() > 0) {
            if (e()) {
                j0Var.f6971a = null;
                if (fVar.f6759d.length() > 0) {
                    String str = fVar.f6759d;
                    long j5 = this.f6904f;
                    int i5 = y1.g0.f6769c;
                    int iM = v.l0.m((int) (j5 & 4294967295L), str);
                    if (iM != -1) {
                        o(iM, iM);
                        return;
                    }
                    return;
                }
                return;
            }
            j0Var.f6971a = null;
            if (fVar.f6759d.length() > 0) {
                String str2 = fVar.f6759d;
                long j6 = this.f6904f;
                int i6 = y1.g0.f6769c;
                int iP = v.l0.p((int) (j6 & 4294967295L), str2);
                if (iP != -1) {
                    o(iP, iP);
                }
            }
        }
    }

    public final void l() {
        Integer numB;
        this.f6903e.f6971a = null;
        if (this.f6905g.f6759d.length() <= 0 || (numB = b()) == null) {
            return;
        }
        int iIntValue = numB.intValue();
        o(iIntValue, iIntValue);
    }

    public final void m() {
        Integer numValueOf = null;
        this.f6903e.f6971a = null;
        if (this.f6905g.f6759d.length() > 0) {
            y1.e0 e0Var = this.f6901c;
            if (e0Var != null) {
                int iE = y1.g0.e(this.f6904f);
                e2.r rVar = this.f6902d;
                numValueOf = Integer.valueOf(rVar.a(e0Var.h(e0Var.e(rVar.b(iE)))));
            }
            if (numValueOf != null) {
                int iIntValue = numValueOf.intValue();
                o(iIntValue, iIntValue);
            }
        }
    }

    public final void n() {
        if (this.f6905g.f6759d.length() > 0) {
            int i5 = y1.g0.f6769c;
            this.f6904f = p3.j.g((int) (this.f6900b >> 32), (int) (this.f6904f & 4294967295L));
        }
    }

    public final void o(int i5, int i6) {
        this.f6904f = p3.j.g(i5, i6);
    }

    public final int p() {
        long j5 = this.f6904f;
        int i5 = y1.g0.f6769c;
        return this.f6902d.b((int) (j5 & 4294967295L));
    }
}

package p1;

import c0.t2;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i0 extends n1.m0 implements n1.e0, a, r0 {
    public float B;
    public boolean C;
    public a4.c D;
    public float F;
    public final t2 G;
    public boolean H;
    public final /* synthetic */ j0 I;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4872i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f4875l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f4876m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f4878o;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public a4.c f4880q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f4881r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Object f4883t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f4884u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f4885v;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f4889z;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4873j = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f4874k = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f4877n = 3;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f4879p = 0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f4882s = true;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final e0 f4886w = new e0(this, 0);

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final h0.e f4887x = new h0.e(new i0[16]);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f4888y = true;
    public final b0.a A = new b0.a(17, this);
    public long E = 0;

    public i0(j0 j0Var) {
        this.I = j0Var;
        this.G = new t2(j0Var, 4, this);
    }

    @Override // p1.a
    public final void D() {
        h0.e eVarU;
        int i5;
        this.f4889z = true;
        e0 e0Var = this.f4886w;
        e0Var.h();
        j0 j0Var = this.I;
        d0 d0Var = j0Var.f4893a;
        if (j0Var.f4897e && (i5 = (eVarU = d0Var.u()).f2578f) > 0) {
            Object[] objArr = eVarU.f2576d;
            int i6 = 0;
            do {
                d0 d0Var2 = (d0) objArr[i6];
                j0 j0Var2 = d0Var2.f4802z;
                if (j0Var2.f4896d && j0Var2.f4910r.f4877n == 1 && d0.K(d0Var2)) {
                    d0.Q(d0Var, false, 7);
                }
                i6++;
            } while (i6 < i5);
        }
        if (j0Var.f4898f || (!this.f4878o && !x().f4924k && j0Var.f4897e)) {
            j0Var.f4897e = false;
            int i7 = j0Var.f4895c;
            j0Var.f4895c = 3;
            j0Var.e(false);
            g1 snapshotObserver = ((q1.u) f.u(d0Var)).getSnapshotObserver();
            snapshotObserver.a(d0Var, snapshotObserver.f4839e, this.A);
            j0Var.f4895c = i7;
            if (x().f4924k && j0Var.f4904l) {
                requestLayout();
            }
            j0Var.f4898f = false;
        }
        if (e0Var.f4819d) {
            e0Var.f4820e = true;
        }
        if (e0Var.f4817b && e0Var.e()) {
            e0Var.g();
        }
        this.f4889z = false;
    }

    @Override // p1.a
    public final boolean J() {
        return this.f4884u;
    }

    @Override // p1.r0
    public final void W(boolean z2) {
        j0 j0Var = this.I;
        boolean z3 = j0Var.a().f4922i;
        if (z2 != z3) {
            j0Var.a().f4922i = z3;
            this.H = true;
        }
    }

    @Override // n1.e0
    public final int a0(int i5) {
        s0();
        return this.I.a().a0(i5);
    }

    @Override // p1.a
    public final void b0() {
        d0.Q(this.I.f4893a, false, 7);
    }

    @Override // n1.e0
    public final n1.m0 c(long j5) {
        int i5;
        j0 j0Var = this.I;
        d0 d0Var = j0Var.f4893a;
        if (d0Var.H == 3) {
            d0Var.f();
        }
        if (f.o(d0Var)) {
            h0 h0Var = j0Var.f4911s;
            b4.l.b(h0Var);
            h0Var.f4851l = 3;
            h0Var.c(j5);
        }
        d0 d0VarR = d0Var.r();
        if (d0VarR != null) {
            j0 j0Var2 = d0VarR.f4802z;
            if (this.f4877n != 3 && !d0Var.f4800x) {
                d4.a.L("measure() may not be called multiple times on the same Measurable. If you want to get the content size of the Measurable before calculating the final constraints, please use methods like minIntrinsicWidth()/maxIntrinsicWidth() and minIntrinsicHeight()/maxIntrinsicHeight()");
                throw null;
            }
            int iC = m.h.c(j0Var2.f4895c);
            if (iC != 0) {
                i5 = 2;
                if (iC != 2) {
                    throw new IllegalStateException("Measurable could be only measured from the parent's measure or layout block. Parents state is ".concat(b.b.m(j0Var2.f4895c)));
                }
            } else {
                i5 = 1;
            }
            this.f4877n = i5;
        } else {
            this.f4877n = 3;
        }
        v0(j5);
        return this;
    }

    @Override // n1.e0
    public final int c0(int i5) {
        s0();
        return this.I.a().c0(i5);
    }

    @Override // n1.e0
    public final int d(int i5) {
        s0();
        return this.I.a().d(i5);
    }

    @Override // n1.e0
    public final int e0(int i5) {
        s0();
        return this.I.a().e0(i5);
    }

    @Override // n1.m0
    public final int f0(n1.l lVar) {
        j0 j0Var = this.I;
        d0 d0Var = j0Var.f4893a;
        d0 d0VarR = d0Var.r();
        int i5 = d0VarR != null ? d0VarR.f4802z.f4895c : 0;
        e0 e0Var = this.f4886w;
        if (i5 == 1) {
            e0Var.f4818c = true;
        } else {
            d0 d0VarR2 = d0Var.r();
            if ((d0VarR2 != null ? d0VarR2.f4802z.f4895c : 0) == 3) {
                e0Var.f4819d = true;
            }
        }
        this.f4878o = true;
        int iF0 = j0Var.a().f0(lVar);
        this.f4878o = false;
        return iF0;
    }

    @Override // n1.m0, n1.e0
    public final Object h() {
        return this.f4883t;
    }

    @Override // n1.m0
    public final void k0(long j5, float f5, a4.c cVar) {
        n1.l0 placementScope;
        j0 j0Var = this.I;
        d0 d0Var = j0Var.f4893a;
        this.f4885v = true;
        if (!k2.h.a(j5, this.f4879p) || this.H) {
            if (j0Var.f4905m || j0Var.f4904l || this.H) {
                j0Var.f4897e = true;
                this.H = false;
            }
            r0();
        }
        if (f.o(d0Var)) {
            y0 y0Var = j0Var.a().f5000q;
            if (y0Var == null || (placementScope = y0Var.f4925l) == null) {
                placementScope = ((q1.u) f.u(d0Var)).getPlacementScope();
            }
            h0 h0Var = j0Var.f4911s;
            b4.l.b(h0Var);
            d0 d0VarR = d0Var.r();
            if (d0VarR != null) {
                d0VarR.f4802z.f4902j = 0;
            }
            h0Var.f4850k = Integer.MAX_VALUE;
            n1.l0.d(placementScope, h0Var, (int) (j5 >> 32), (int) (4294967295L & j5));
        }
        h0 h0Var2 = j0Var.f4911s;
        if (h0Var2 == null || h0Var2.f4853n) {
            u0(j5, f5, cVar);
        } else {
            d4.a.L("Error: Placement happened before lookahead.");
            throw null;
        }
    }

    @Override // p1.a
    public final e0 m() {
        return this.f4886w;
    }

    public final List o0() {
        d0 d0Var = this.I.f4893a;
        d0Var.X();
        boolean z2 = this.f4888y;
        h0.e eVar = this.f4887x;
        if (!z2) {
            return eVar.f();
        }
        h0.e eVarU = d0Var.u();
        int i5 = eVarU.f2578f;
        if (i5 > 0) {
            Object[] objArr = eVarU.f2576d;
            int i6 = 0;
            do {
                d0 d0Var2 = (d0) objArr[i6];
                if (eVar.f2578f <= i6) {
                    eVar.b(d0Var2.f4802z.f4910r);
                } else {
                    i0 i0Var = d0Var2.f4802z.f4910r;
                    Object[] objArr2 = eVar.f2576d;
                    Object obj = objArr2[i6];
                    objArr2[i6] = i0Var;
                }
                i6++;
            } while (i6 < i5);
        }
        eVar.n(((h0.b) d0Var.m()).f2570d.f2578f, eVar.f2578f);
        this.f4888y = false;
        return eVar.f();
    }

    public final void p0() {
        boolean z2 = this.f4884u;
        this.f4884u = true;
        d0 d0Var = this.I.f4893a;
        if (!z2) {
            j0 j0Var = d0Var.f4802z;
            if (j0Var.f4896d) {
                d0.Q(d0Var, true, 6);
            } else if (j0Var.f4899g) {
                d0.O(d0Var, true, 6);
            }
        }
        t0 t0Var = d0Var.f4801y;
        y0 y0Var = t0Var.f4969b.f4999p;
        for (y0 y0Var2 = t0Var.f4970c; !b4.l.a(y0Var2, y0Var) && y0Var2 != null; y0Var2 = y0Var2.f4999p) {
            if (y0Var2.F) {
                y0Var2.R0();
            }
        }
        h0.e eVarU = d0Var.u();
        int i5 = eVarU.f2578f;
        if (i5 > 0) {
            Object[] objArr = eVarU.f2576d;
            int i6 = 0;
            do {
                d0 d0Var2 = (d0) objArr[i6];
                if (d0Var2.s() != Integer.MAX_VALUE) {
                    d0Var2.f4802z.f4910r.p0();
                    d0.R(d0Var2);
                }
                i6++;
            } while (i6 < i5);
        }
    }

    public final void q0() {
        d0 d0Var = this.I.f4893a;
        if (this.f4884u) {
            int i5 = 0;
            this.f4884u = false;
            t0 t0Var = d0Var.f4801y;
            y0 y0Var = t0Var.f4969b.f4999p;
            for (y0 y0Var2 = t0Var.f4970c; !b4.l.a(y0Var2, y0Var) && y0Var2 != null; y0Var2 = y0Var2.f4999p) {
                if (y0Var2.G != null) {
                    y0Var2.g1(null, false);
                    y0Var2.f4998o.P(false);
                }
            }
            h0.e eVarU = d0Var.u();
            int i6 = eVarU.f2578f;
            if (i6 > 0) {
                Object[] objArr = eVarU.f2576d;
                do {
                    ((d0) objArr[i5]).f4802z.f4910r.q0();
                    i5++;
                } while (i5 < i6);
            }
        }
    }

    @Override // p1.a
    public final void r(c0.g1 g1Var) {
        h0.e eVarU = this.I.f4893a.u();
        int i5 = eVarU.f2578f;
        if (i5 > 0) {
            Object[] objArr = eVarU.f2576d;
            int i6 = 0;
            do {
                g1Var.c(((d0) objArr[i6]).f4802z.f4910r);
                i6++;
            } while (i6 < i5);
        }
    }

    public final void r0() {
        h0.e eVarU;
        int i5;
        j0 j0Var = this.I;
        if (j0Var.f4906n <= 0 || (i5 = (eVarU = j0Var.f4893a.u()).f2578f) <= 0) {
            return;
        }
        Object[] objArr = eVarU.f2576d;
        int i6 = 0;
        do {
            d0 d0Var = (d0) objArr[i6];
            j0 j0Var2 = d0Var.f4802z;
            if ((j0Var2.f4904l || j0Var2.f4905m) && !j0Var2.f4897e) {
                d0Var.P(false);
            }
            j0Var2.f4910r.r0();
            i6++;
        } while (i6 < i5);
    }

    @Override // p1.a
    public final void requestLayout() {
        this.I.f4893a.P(false);
    }

    public final void s0() {
        int i5;
        d0 d0Var = this.I.f4893a;
        d0.Q(d0Var, false, 7);
        d0 d0VarR = d0Var.r();
        if (d0VarR == null || d0Var.H != 3) {
            return;
        }
        int iC = m.h.c(d0VarR.f4802z.f4895c);
        if (iC != 0) {
            i5 = 2;
            if (iC != 2) {
                i5 = d0VarR.H;
            }
        } else {
            i5 = 1;
        }
        d0Var.H = i5;
    }

    public final void t0() {
        this.C = true;
        d0 d0Var = this.I.f4893a;
        d0 d0VarR = d0Var.r();
        float f5 = x().A;
        t0 t0Var = d0Var.f4801y;
        y0 y0Var = t0Var.f4970c;
        t tVar = t0Var.f4969b;
        while (y0Var != tVar) {
            b4.l.c("null cannot be cast to non-null type androidx.compose.ui.node.LayoutModifierNodeCoordinator", y0Var);
            y yVar = (y) y0Var;
            f5 += yVar.A;
            y0Var = yVar.f4999p;
        }
        if (f5 != this.B) {
            this.B = f5;
            if (d0VarR != null) {
                d0VarR.I();
            }
            if (d0VarR != null) {
                d0VarR.y();
            }
        }
        if (!this.f4884u) {
            if (d0VarR != null) {
                d0VarR.y();
            }
            p0();
            if (this.f4872i && d0VarR != null) {
                d0VarR.P(false);
            }
        }
        if (d0VarR == null) {
            this.f4874k = 0;
        } else if (!this.f4872i) {
            j0 j0Var = d0VarR.f4802z;
            if (j0Var.f4895c == 3) {
                if (this.f4874k != Integer.MAX_VALUE) {
                    d4.a.L("Place was called on a node which was placed already");
                    throw null;
                }
                int i5 = j0Var.f4903k;
                this.f4874k = i5;
                j0Var.f4903k = i5 + 1;
            }
        }
        D();
    }

    public final void u0(long j5, float f5, a4.c cVar) {
        j0 j0Var = this.I;
        d0 d0Var = j0Var.f4893a;
        if (d0Var.G) {
            d4.a.K("place is called on a deactivated node");
            throw null;
        }
        j0Var.f4895c = 3;
        this.f4879p = j5;
        this.f4881r = f5;
        this.f4880q = cVar;
        this.f4876m = true;
        this.C = false;
        e1 e1VarU = f.u(d0Var);
        if (j0Var.f4897e || !this.f4884u) {
            this.f4886w.f4822g = false;
            j0Var.d(false);
            this.D = cVar;
            this.E = j5;
            this.F = f5;
            g1 snapshotObserver = ((q1.u) e1VarU).getSnapshotObserver();
            snapshotObserver.a(d0Var, snapshotObserver.f4840f, this.G);
        } else {
            y0 y0VarA = j0Var.a();
            y0VarA.Y0(k2.h.c(j5, y0VarA.f4134h), f5, cVar);
            t0();
        }
        j0Var.f4895c = 5;
    }

    public final boolean v0(long j5) {
        j0 j0Var = this.I;
        d0 d0Var = j0Var.f4893a;
        if (d0Var.G) {
            d4.a.K("measure is called on a deactivated node");
            throw null;
        }
        e1 e1VarU = f.u(d0Var);
        d0 d0VarR = d0Var.r();
        boolean z2 = true;
        d0Var.f4800x = d0Var.f4800x || (d0VarR != null && d0VarR.f4800x);
        if (!d0Var.f4802z.f4896d && k2.a.c(this.f4133g, j5)) {
            ((q1.u) e1VarU).J.d(d0Var, false);
            d0Var.S();
            return false;
        }
        this.f4886w.f4821f = false;
        h0.e eVarU = d0Var.u();
        int i5 = eVarU.f2578f;
        if (i5 > 0) {
            Object[] objArr = eVarU.f2576d;
            int i6 = 0;
            do {
                ((d0) objArr[i6]).f4802z.f4910r.f4886w.f4818c = false;
                i6++;
            } while (i6 < i5);
        }
        this.f4875l = true;
        long j6 = j0Var.a().f4132f;
        n0(j5);
        if (j0Var.f4895c != 5) {
            d4.a.L("layout state is not idle before measure starts");
            throw null;
        }
        j0Var.f4895c = 1;
        j0Var.f4896d = false;
        j0Var.f4912t = j5;
        g1 snapshotObserver = ((q1.u) f.u(d0Var)).getSnapshotObserver();
        snapshotObserver.a(d0Var, snapshotObserver.f4837c, j0Var.f4913u);
        if (j0Var.f4895c == 1) {
            j0Var.f4897e = true;
            j0Var.f4898f = true;
            j0Var.f4895c = 5;
        }
        if (k2.j.a(j0Var.a().f4132f, j6) && j0Var.a().f4130d == this.f4130d && j0Var.a().f4131e == this.f4131e) {
            z2 = false;
        }
        m0(a.a.f(j0Var.a().f4130d, j0Var.a().f4131e));
        return z2;
    }

    @Override // p1.a
    public final t x() {
        return this.I.f4893a.f4801y.f4969b;
    }

    @Override // p1.a
    public final a z() {
        j0 j0Var;
        d0 d0VarR = this.I.f4893a.r();
        if (d0VarR == null || (j0Var = d0VarR.f4802z) == null) {
            return null;
        }
        return j0Var.f4910r;
    }
}

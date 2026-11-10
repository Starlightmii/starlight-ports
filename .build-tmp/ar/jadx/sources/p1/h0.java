package p1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h0 extends n1.m0 implements n1.e0, a, r0 {
    public final /* synthetic */ j0 A;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4848i;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f4852m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f4853n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f4854o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public k2.a f4855p;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public a4.c f4857r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f4858s;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f4862w;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Object f4864y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f4865z;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4849j = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f4850k = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f4851l = 3;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f4856q = 0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final e0 f4859t = new e0(this, 1);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final h0.e f4860u = new h0.e(new h0[16]);

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f4861v = true;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f4863x = true;

    public h0(j0 j0Var) {
        this.A = j0Var;
        this.f4864y = j0Var.f4910r.f4883t;
    }

    @Override // p1.a
    public final void D() {
        h0.e eVarU;
        int i5;
        this.f4862w = true;
        e0 e0Var = this.f4859t;
        e0Var.h();
        j0 j0Var = this.A;
        boolean z2 = j0Var.f4900h;
        d0 d0Var = j0Var.f4893a;
        if (z2 && (i5 = (eVarU = d0Var.u()).f2578f) > 0) {
            Object[] objArr = eVarU.f2576d;
            int i6 = 0;
            do {
                d0 d0Var2 = (d0) objArr[i6];
                j0 j0Var2 = d0Var2.f4802z;
                if (j0Var2.f4899g && d0Var2.p() == 1) {
                    h0 h0Var = j0Var2.f4911s;
                    b4.l.b(h0Var);
                    h0 h0Var2 = j0Var2.f4911s;
                    k2.a aVar = h0Var2 != null ? h0Var2.f4855p : null;
                    b4.l.b(aVar);
                    if (h0Var.u0(aVar.f3400a)) {
                        d0.O(d0Var, false, 7);
                    }
                }
                i6++;
            } while (i6 < i5);
        }
        s sVar = x().N;
        b4.l.b(sVar);
        if (j0Var.f4901i || (!this.f4852m && !sVar.f4924k && j0Var.f4900h)) {
            j0Var.f4900h = false;
            int i7 = j0Var.f4895c;
            j0Var.f4895c = 4;
            e1 e1VarU = f.u(d0Var);
            j0Var.g(false);
            g1 snapshotObserver = ((q1.u) e1VarU).getSnapshotObserver();
            n.i iVar = new n.i(this, sVar, j0Var, 2);
            snapshotObserver.getClass();
            if (d0Var.f4782f != null) {
                snapshotObserver.a(d0Var, snapshotObserver.f4842h, iVar);
            } else {
                snapshotObserver.a(d0Var, snapshotObserver.f4839e, iVar);
            }
            j0Var.f4895c = i7;
            if (j0Var.f4907o && sVar.f4924k) {
                requestLayout();
            }
            j0Var.f4901i = false;
        }
        if (e0Var.f4819d) {
            e0Var.f4820e = true;
        }
        if (e0Var.f4817b && e0Var.e()) {
            e0Var.g();
        }
        this.f4862w = false;
    }

    @Override // p1.a
    public final boolean J() {
        return this.f4858s;
    }

    @Override // p1.r0
    public final void W(boolean z2) {
        n0 n0VarJ0;
        j0 j0Var = this.A;
        n0 n0VarJ1 = j0Var.a().J0();
        if (Boolean.valueOf(z2).equals(n0VarJ1 != null ? Boolean.valueOf(n0VarJ1.f4922i) : null) || (n0VarJ0 = j0Var.a().J0()) == null) {
            return;
        }
        n0VarJ0.f4922i = z2;
    }

    @Override // n1.e0
    public final int a0(int i5) {
        r0();
        n0 n0VarJ0 = this.A.a().J0();
        b4.l.b(n0VarJ0);
        return n0VarJ0.a0(i5);
    }

    @Override // p1.a
    public final void b0() {
        d0.O(this.A.f4893a, false, 7);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0025  */
    @Override // n1.e0
    public final n1.m0 c(long j5) {
        j0 j0Var = this.A;
        d0 d0Var = j0Var.f4893a;
        d0 d0Var2 = j0Var.f4893a;
        d0 d0VarR = d0Var.r();
        int i5 = 2;
        if ((d0VarR != null ? d0VarR.f4802z.f4895c : 0) == 2) {
            j0Var.f4894b = false;
        } else {
            d0 d0VarR2 = d0Var2.r();
            if ((d0VarR2 != null ? d0VarR2.f4802z.f4895c : 0) == 4) {
                j0Var.f4894b = false;
            }
        }
        d0 d0VarR3 = d0Var2.r();
        if (d0VarR3 != null) {
            j0 j0Var2 = d0VarR3.f4802z;
            if (this.f4851l != 3 && !d0Var2.f4800x) {
                d4.a.L("measure() may not be called multiple times on the same Measurable. If you want to get the content size of the Measurable before calculating the final constraints, please use methods like minIntrinsicWidth()/maxIntrinsicWidth() and minIntrinsicHeight()/maxIntrinsicHeight()");
                throw null;
            }
            int iC = m.h.c(j0Var2.f4895c);
            if (iC == 0 || iC == 1) {
                i5 = 1;
            } else if (iC != 2 && iC != 3) {
                throw new IllegalStateException("Measurable could be only measured from the parent's measure or layout block. Parents state is ".concat(b.b.m(j0Var2.f4895c)));
            }
            this.f4851l = i5;
        } else {
            this.f4851l = 3;
        }
        if (d0Var2.H == 3) {
            d0Var2.f();
        }
        u0(j5);
        return this;
    }

    @Override // n1.e0
    public final int c0(int i5) {
        r0();
        n0 n0VarJ0 = this.A.a().J0();
        b4.l.b(n0VarJ0);
        return n0VarJ0.c0(i5);
    }

    @Override // n1.e0
    public final int d(int i5) {
        r0();
        n0 n0VarJ0 = this.A.a().J0();
        b4.l.b(n0VarJ0);
        return n0VarJ0.d(i5);
    }

    @Override // n1.e0
    public final int e0(int i5) {
        r0();
        n0 n0VarJ0 = this.A.a().J0();
        b4.l.b(n0VarJ0);
        return n0VarJ0.e0(i5);
    }

    @Override // n1.m0
    public final int f0(n1.l lVar) {
        j0 j0Var = this.A;
        d0 d0VarR = j0Var.f4893a.r();
        int i5 = d0VarR != null ? d0VarR.f4802z.f4895c : 0;
        e0 e0Var = this.f4859t;
        if (i5 == 2) {
            e0Var.f4818c = true;
        } else {
            d0 d0VarR2 = j0Var.f4893a.r();
            if ((d0VarR2 != null ? d0VarR2.f4802z.f4895c : 0) == 4) {
                e0Var.f4819d = true;
            }
        }
        this.f4852m = true;
        n0 n0VarJ0 = j0Var.a().J0();
        b4.l.b(n0VarJ0);
        int iF0 = n0VarJ0.f0(lVar);
        this.f4852m = false;
        return iF0;
    }

    @Override // n1.m0, n1.e0
    public final Object h() {
        return this.f4864y;
    }

    @Override // n1.m0
    public final void k0(long j5, float f5, a4.c cVar) {
        t0(j5, cVar);
    }

    @Override // p1.a
    public final e0 m() {
        return this.f4859t;
    }

    public final void o0() {
        boolean z2 = this.f4858s;
        this.f4858s = true;
        j0 j0Var = this.A;
        if (!z2 && j0Var.f4899g) {
            d0.O(j0Var.f4893a, true, 6);
        }
        h0.e eVarU = j0Var.f4893a.u();
        int i5 = eVarU.f2578f;
        if (i5 > 0) {
            Object[] objArr = eVarU.f2576d;
            int i6 = 0;
            do {
                d0 d0Var = (d0) objArr[i6];
                if (d0Var.s() != Integer.MAX_VALUE) {
                    h0 h0Var = d0Var.f4802z.f4911s;
                    b4.l.b(h0Var);
                    h0Var.o0();
                    d0.R(d0Var);
                }
                i6++;
            } while (i6 < i5);
        }
    }

    public final void p0() {
        if (this.f4858s) {
            int i5 = 0;
            this.f4858s = false;
            h0.e eVarU = this.A.f4893a.u();
            int i6 = eVarU.f2578f;
            if (i6 > 0) {
                Object[] objArr = eVarU.f2576d;
                do {
                    h0 h0Var = ((d0) objArr[i5]).f4802z.f4911s;
                    b4.l.b(h0Var);
                    h0Var.p0();
                    i5++;
                } while (i5 < i6);
            }
        }
    }

    public final void q0() {
        h0.e eVarU;
        int i5;
        j0 j0Var = this.A;
        if (j0Var.f4909q <= 0 || (i5 = (eVarU = j0Var.f4893a.u()).f2578f) <= 0) {
            return;
        }
        Object[] objArr = eVarU.f2576d;
        int i6 = 0;
        do {
            d0 d0Var = (d0) objArr[i6];
            j0 j0Var2 = d0Var.f4802z;
            if ((j0Var2.f4907o || j0Var2.f4908p) && !j0Var2.f4900h) {
                d0Var.N(false);
            }
            h0 h0Var = j0Var2.f4911s;
            if (h0Var != null) {
                h0Var.q0();
            }
            i6++;
        } while (i6 < i5);
    }

    @Override // p1.a
    public final void r(c0.g1 g1Var) {
        h0.e eVarU = this.A.f4893a.u();
        int i5 = eVarU.f2578f;
        if (i5 > 0) {
            Object[] objArr = eVarU.f2576d;
            int i6 = 0;
            do {
                h0 h0Var = ((d0) objArr[i6]).f4802z.f4911s;
                b4.l.b(h0Var);
                g1Var.c(h0Var);
                i6++;
            } while (i6 < i5);
        }
    }

    public final void r0() {
        int i5;
        j0 j0Var = this.A;
        d0.O(j0Var.f4893a, false, 7);
        d0 d0Var = j0Var.f4893a;
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

    @Override // p1.a
    public final void requestLayout() {
        this.A.f4893a.N(false);
    }

    public final void s0() {
        j0 j0Var;
        int i5;
        this.f4865z = true;
        d0 d0VarR = this.A.f4893a.r();
        if (!this.f4858s) {
            o0();
            if (this.f4848i && d0VarR != null) {
                d0VarR.N(false);
            }
        }
        if (d0VarR == null) {
            this.f4850k = 0;
        } else if (!this.f4848i && ((i5 = (j0Var = d0VarR.f4802z).f4895c) == 3 || i5 == 4)) {
            if (this.f4850k != Integer.MAX_VALUE) {
                d4.a.L("Place was called on a node which was placed already");
                throw null;
            }
            int i6 = j0Var.f4902j;
            this.f4850k = i6;
            j0Var.f4902j = i6 + 1;
        }
        D();
    }

    public final void t0(long j5, a4.c cVar) {
        j0 j0Var = this.A;
        d0 d0Var = j0Var.f4893a;
        d0 d0Var2 = j0Var.f4893a;
        if (d0Var.G) {
            d4.a.K("place is called on a deactivated node");
            throw null;
        }
        j0Var.f4895c = 4;
        this.f4853n = true;
        this.f4865z = false;
        if (!k2.h.a(j5, this.f4856q)) {
            if (j0Var.f4908p || j0Var.f4907o) {
                j0Var.f4900h = true;
            }
            q0();
        }
        e1 e1VarU = f.u(d0Var2);
        if (j0Var.f4900h || !this.f4858s) {
            j0Var.f(false);
            this.f4859t.f4822g = false;
            g1 snapshotObserver = ((q1.u) e1VarU).getSnapshotObserver();
            g0 g0Var = new g0(j0Var, e1VarU, j5);
            snapshotObserver.getClass();
            if (d0Var2.f4782f != null) {
                snapshotObserver.a(d0Var2, snapshotObserver.f4841g, g0Var);
            } else {
                snapshotObserver.a(d0Var2, snapshotObserver.f4840f, g0Var);
            }
        } else {
            n0 n0VarJ0 = j0Var.a().J0();
            b4.l.b(n0VarJ0);
            n0VarJ0.B0(k2.h.c(j5, n0VarJ0.f4134h));
            s0();
        }
        this.f4856q = j5;
        this.f4857r = cVar;
        j0Var.f4895c = 5;
    }

    public final boolean u0(long j5) {
        j0 j0Var = this.A;
        d0 d0Var = j0Var.f4893a;
        d0 d0Var2 = j0Var.f4893a;
        if (d0Var.G) {
            d4.a.K("measure is called on a deactivated node");
            throw null;
        }
        d0 d0VarR = d0Var.r();
        d0Var2.f4800x = d0Var2.f4800x || (d0VarR != null && d0VarR.f4800x);
        if (!d0Var2.f4802z.f4899g) {
            k2.a aVar = this.f4855p;
            if (aVar == null ? false : k2.a.c(aVar.f3400a, j5)) {
                e1 e1Var = d0Var2.f4788l;
                if (e1Var != null) {
                    ((q1.u) e1Var).J.d(d0Var2, true);
                }
                d0Var2.S();
                return false;
            }
        }
        this.f4855p = new k2.a(j5);
        n0(j5);
        this.f4859t.f4821f = false;
        h0.e eVarU = d0Var2.u();
        int i5 = eVarU.f2578f;
        if (i5 > 0) {
            Object[] objArr = eVarU.f2576d;
            int i6 = 0;
            do {
                h0 h0Var = ((d0) objArr[i6]).f4802z.f4911s;
                b4.l.b(h0Var);
                h0Var.f4859t.f4818c = false;
                i6++;
            } while (i6 < i5);
        }
        long jF = this.f4854o ? this.f4132f : a.a.f(Integer.MIN_VALUE, Integer.MIN_VALUE);
        this.f4854o = true;
        n0 n0VarJ0 = j0Var.a().J0();
        if (!(n0VarJ0 != null)) {
            d4.a.L("Lookahead result from lookaheadRemeasure cannot be null");
            throw null;
        }
        j0Var.f4895c = 2;
        j0Var.f4899g = false;
        g1 snapshotObserver = ((q1.u) f.u(d0Var2)).getSnapshotObserver();
        g2.e eVar = new g2.e(1, j5, j0Var);
        snapshotObserver.getClass();
        if (d0Var2.f4782f != null) {
            snapshotObserver.a(d0Var2, snapshotObserver.f4836b, eVar);
        } else {
            snapshotObserver.a(d0Var2, snapshotObserver.f4837c, eVar);
        }
        j0Var.f4900h = true;
        j0Var.f4901i = true;
        if (f.o(d0Var2)) {
            j0Var.f4897e = true;
            j0Var.f4898f = true;
        } else {
            j0Var.f4896d = true;
        }
        j0Var.f4895c = 5;
        m0(a.a.f(n0VarJ0.f4130d, n0VarJ0.f4131e));
        return (((int) (jF >> 32)) == n0VarJ0.f4130d && ((int) (4294967295L & jF)) == n0VarJ0.f4131e) ? false : true;
    }

    @Override // p1.a
    public final t x() {
        return this.A.f4893a.f4801y.f4969b;
    }

    @Override // p1.a
    public final a z() {
        j0 j0Var;
        d0 d0VarR = this.A.f4893a.r();
        if (d0VarR == null || (j0Var = d0VarR.f4802z) == null) {
            return null;
        }
        return j0Var.f4911s;
    }
}

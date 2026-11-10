package p1;

import android.os.Trace;
import c0.t2;
import f0.s1;
import java.util.List;
import q1.o2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d0 implements f0.m, f1, j {
    public static final a0 J = new a0("Undefined intrinsics block and it is required");
    public static final z K = new z();
    public static final f0.w L = new f0.w(1);
    public n1.b0 A;
    public y0 B;
    public boolean C;
    public r0.p D;
    public r0.p E;
    public boolean F;
    public boolean G;
    public int H;
    public int I;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f4780d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4781e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public d0 f4782f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4783g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final a2.h f4784h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public h0.e f4785i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f4786j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public d0 f4787k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public e1 f4788l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4789m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f4790n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public w1.i f4791o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final h0.e f4792p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f4793q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public n1.f0 f4794r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public a2.h f4795s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public k2.b f4796t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public k2.k f4797u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public o2 f4798v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public f0.h0 f4799w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f4800x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final t0 f4801y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final j0 f4802z;

    public d0(int i5) {
        this(w1.k.f6311a.addAndGet(1), (i5 & 1) == 0);
    }

    public d0(int i5, boolean z2) {
        this.f4780d = z2;
        this.f4781e = i5;
        this.f4784h = new a2.h(new h0.e(new d0[16]), 20, new b0.a(16, this));
        this.f4792p = new h0.e(new d0[16]);
        this.f4793q = true;
        this.f4794r = J;
        this.f4796t = f.f4827b;
        this.f4797u = k2.k.f3416d;
        this.f4798v = K;
        f0.h0.f2191a.getClass();
        this.f4799w = f0.g0.f2176b;
        this.H = 3;
        this.I = 3;
        this.f4801y = new t0(this);
        this.f4802z = new j0(this);
        this.C = true;
        this.D = r0.m.f5680a;
    }

    public static boolean K(d0 d0Var) {
        i0 i0Var = d0Var.f4802z.f4910r;
        return d0Var.J(i0Var.f4875l ? new k2.a(i0Var.f4133g) : null);
    }

    public static void O(d0 d0Var, boolean z2, int i5) {
        d0 d0VarR;
        if ((i5 & 1) != 0) {
            z2 = false;
        }
        boolean z3 = (i5 & 4) != 0;
        if (d0Var.f4782f == null) {
            d4.a.L("Lookahead measure cannot be requested on a node that is not a part of theLookaheadScope");
            throw null;
        }
        e1 e1Var = d0Var.f4788l;
        if (e1Var == null || d0Var.f4790n || d0Var.f4780d) {
            return;
        }
        ((q1.u) e1Var).w(d0Var, true, z2);
        if (z3) {
            h0 h0Var = d0Var.f4802z.f4911s;
            b4.l.b(h0Var);
            j0 j0Var = h0Var.A;
            d0 d0VarR2 = j0Var.f4893a.r();
            int i6 = j0Var.f4893a.H;
            if (d0VarR2 == null || i6 == 3) {
                return;
            }
            while (d0VarR2.H == i6 && (d0VarR = d0VarR2.r()) != null) {
                d0VarR2 = d0VarR;
            }
            int iC = m.h.c(i6);
            if (iC == 0) {
                if (d0VarR2.f4782f != null) {
                    O(d0VarR2, z2, 6);
                    return;
                } else {
                    Q(d0VarR2, z2, 6);
                    return;
                }
            }
            if (iC != 1) {
                throw new IllegalStateException("Intrinsics isn't used by the parent");
            }
            if (d0VarR2.f4782f != null) {
                d0VarR2.N(z2);
            } else {
                d0VarR2.P(z2);
            }
        }
    }

    public static void Q(d0 d0Var, boolean z2, int i5) {
        e1 e1Var;
        d0 d0VarR;
        if ((i5 & 1) != 0) {
            z2 = false;
        }
        boolean z3 = (i5 & 4) != 0;
        if (d0Var.f4790n || d0Var.f4780d || (e1Var = d0Var.f4788l) == null) {
            return;
        }
        ((q1.u) e1Var).w(d0Var, false, z2);
        if (z3) {
            d0 d0Var2 = d0Var.f4802z.f4910r.I.f4893a;
            d0 d0VarR2 = d0Var2.r();
            int i6 = d0Var2.H;
            if (d0VarR2 == null || i6 == 3) {
                return;
            }
            while (d0VarR2.H == i6 && (d0VarR = d0VarR2.r()) != null) {
                d0VarR2 = d0VarR;
            }
            int iC = m.h.c(i6);
            if (iC == 0) {
                Q(d0VarR2, z2, 6);
            } else {
                if (iC != 1) {
                    throw new IllegalStateException("Intrinsics isn't used by the parent");
                }
                d0VarR2.P(z2);
            }
        }
    }

    public static void R(d0 d0Var) {
        j0 j0Var = d0Var.f4802z;
        if (c0.f4776a[m.h.c(j0Var.f4895c)] != 1) {
            throw new IllegalStateException("Unexpected state ".concat(b.b.m(j0Var.f4895c)));
        }
        if (j0Var.f4899g) {
            O(d0Var, true, 6);
            return;
        }
        if (j0Var.f4900h) {
            d0Var.N(true);
        }
        if (j0Var.f4896d) {
            Q(d0Var, true, 6);
        } else if (j0Var.f4897e) {
            d0Var.P(true);
        }
    }

    public final void A() {
        if (this.f4782f != null) {
            O(this, false, 7);
        } else {
            Q(this, false, 7);
        }
    }

    public final void B() {
        this.f4791o = null;
        ((q1.u) f.u(this)).y();
    }

    public final void C() {
        d0 d0Var;
        if (this.f4783g > 0) {
            this.f4786j = true;
        }
        if (!this.f4780d || (d0Var = this.f4787k) == null) {
            return;
        }
        d0Var.C();
    }

    public final boolean D() {
        return this.f4788l != null;
    }

    public final boolean E() {
        return this.f4802z.f4910r.f4884u;
    }

    public final Boolean F() {
        h0 h0Var = this.f4802z.f4911s;
        if (h0Var != null) {
            return Boolean.valueOf(h0Var.f4858s);
        }
        return null;
    }

    public final void G(int i5, int i6, int i7) {
        if (i5 == i6) {
            return;
        }
        for (int i8 = 0; i8 < i7; i8++) {
            int i9 = i5 > i6 ? i5 + i8 : i5;
            int i10 = i5 > i6 ? i6 + i8 : (i6 + i7) - 2;
            a2.h hVar = this.f4784h;
            h0.e eVar = (h0.e) hVar.f49e;
            b0.a aVar = (b0.a) hVar.f50f;
            Object objM = eVar.m(i9);
            aVar.a();
            ((h0.e) hVar.f49e).a(i10, (d0) objM);
            aVar.a();
        }
        I();
        C();
        A();
    }

    public final void H(d0 d0Var) {
        if (d0Var.f4802z.f4906n > 0) {
            j0 j0Var = this.f4802z;
            j0Var.b(j0Var.f4906n - 1);
        }
        if (this.f4788l != null) {
            d0Var.i();
        }
        d0Var.f4787k = null;
        d0Var.f4801y.f4970c.f5000q = null;
        if (d0Var.f4780d) {
            this.f4783g--;
            h0.e eVar = (h0.e) d0Var.f4784h.f49e;
            int i5 = eVar.f2578f;
            if (i5 > 0) {
                Object[] objArr = eVar.f2576d;
                int i6 = 0;
                do {
                    ((d0) objArr[i6]).f4801y.f4970c.f5000q = null;
                    i6++;
                } while (i6 < i5);
            }
        }
        C();
        I();
    }

    public final void I() {
        if (!this.f4780d) {
            this.f4793q = true;
            return;
        }
        d0 d0VarR = r();
        if (d0VarR != null) {
            d0VarR.I();
        }
    }

    public final boolean J(k2.a aVar) {
        if (aVar == null) {
            return false;
        }
        if (this.H == 3) {
            f();
        }
        return this.f4802z.f4910r.v0(aVar.f3400a);
    }

    public final void L() {
        a2.h hVar = this.f4784h;
        h0.e eVar = (h0.e) hVar.f49e;
        h0.e eVar2 = (h0.e) hVar.f49e;
        int i5 = eVar.f2578f;
        while (true) {
            i5--;
            if (-1 >= i5) {
                eVar2.g();
                ((b0.a) hVar.f50f).a();
                return;
            }
            H((d0) eVar2.f2576d[i5]);
        }
    }

    public final void M(int i5, int i6) {
        if (i6 < 0) {
            d4.a.K("count (" + i6 + ") must be greater than 0");
            throw null;
        }
        int i7 = (i6 + i5) - 1;
        if (i5 > i7) {
            return;
        }
        while (true) {
            a2.h hVar = this.f4784h;
            H((d0) ((h0.e) hVar.f49e).f2576d[i7]);
            Object objM = ((h0.e) hVar.f49e).m(i7);
            ((b0.a) hVar.f50f).a();
            if (i7 == i5) {
                return;
            } else {
                i7--;
            }
        }
    }

    public final void N(boolean z2) {
        e1 e1Var;
        if (this.f4780d || (e1Var = this.f4788l) == null) {
            return;
        }
        ((q1.u) e1Var).x(this, true, z2);
    }

    public final void P(boolean z2) {
        e1 e1Var;
        if (this.f4780d || (e1Var = this.f4788l) == null) {
            return;
        }
        ((q1.u) e1Var).x(this, false, z2);
    }

    public final void S() {
        h0.e eVarU = u();
        int i5 = eVarU.f2578f;
        if (i5 > 0) {
            Object[] objArr = eVarU.f2576d;
            int i6 = 0;
            do {
                d0 d0Var = (d0) objArr[i6];
                int i7 = d0Var.I;
                d0Var.H = i7;
                if (i7 != 3) {
                    d0Var.S();
                }
                i6++;
            } while (i6 < i5);
        }
    }

    public final void T(k2.b bVar) {
        if (b4.l.a(this.f4796t, bVar)) {
            return;
        }
        this.f4796t = bVar;
        A();
        d0 d0VarR = r();
        if (d0VarR != null) {
            d0VarR.y();
        }
        z();
        for (r0.o oVar = this.f4801y.f4972e; oVar != null; oVar = oVar.f5686i) {
            if ((oVar.f5683f & 16) != 0) {
                ((j1) oVar).h();
            } else if (oVar instanceof v0.b) {
                ((v0.b) oVar).y0();
            }
        }
    }

    public final void U(d0 d0Var) {
        if (b4.l.a(d0Var, this.f4782f)) {
            return;
        }
        this.f4782f = d0Var;
        if (d0Var != null) {
            j0 j0Var = this.f4802z;
            if (j0Var.f4911s == null) {
                j0Var.f4911s = new h0(j0Var);
            }
            t0 t0Var = this.f4801y;
            y0 y0Var = t0Var.f4969b.f4999p;
            for (y0 y0Var2 = t0Var.f4970c; !b4.l.a(y0Var2, y0Var) && y0Var2 != null; y0Var2 = y0Var2.f4999p) {
                y0Var2.G0();
            }
        }
        A();
    }

    public final void V(n1.f0 f0Var) {
        if (b4.l.a(this.f4794r, f0Var)) {
            return;
        }
        this.f4794r = f0Var;
        a2.h hVar = this.f4795s;
        if (hVar != null) {
            ((s1) hVar.f50f).setValue(f0Var);
        }
        A();
    }

    public final void W(r0.p pVar) {
        if (this.f4780d && this.D != r0.m.f5680a) {
            d4.a.K("Modifiers are not supported on virtual LayoutNodes");
            throw null;
        }
        if (this.G) {
            d4.a.K("modifier is updated when deactivated");
            throw null;
        }
        if (D()) {
            d(pVar);
        } else {
            this.E = pVar;
        }
    }

    public final void X() {
        if (this.f4783g <= 0 || !this.f4786j) {
            return;
        }
        int i5 = 0;
        this.f4786j = false;
        h0.e eVar = this.f4785i;
        if (eVar == null) {
            eVar = new h0.e(new d0[16]);
            this.f4785i = eVar;
        }
        eVar.g();
        h0.e eVar2 = (h0.e) this.f4784h.f49e;
        int i6 = eVar2.f2578f;
        if (i6 > 0) {
            Object[] objArr = eVar2.f2576d;
            do {
                d0 d0Var = (d0) objArr[i5];
                if (d0Var.f4780d) {
                    eVar.c(eVar.f2578f, d0Var.u());
                } else {
                    eVar.b(d0Var);
                }
                i5++;
            } while (i5 < i6);
        }
        j0 j0Var = this.f4802z;
        j0Var.f4910r.f4888y = true;
        h0 h0Var = j0Var.f4911s;
        if (h0Var != null) {
            h0Var.f4861v = true;
        }
    }

    @Override // f0.m
    public final void a() {
        if (!D()) {
            d4.a.K("onReuse is only expected on attached node");
            throw null;
        }
        n1.b0 b0Var = this.A;
        if (b0Var != null) {
            b0Var.f(false);
        }
        boolean z2 = this.G;
        t0 t0Var = this.f4801y;
        if (z2) {
            this.G = false;
            B();
        } else {
            r0.o oVar = t0Var.f4971d;
            for (r0.o oVar2 = oVar; oVar2 != null; oVar2 = oVar2.f5685h) {
                if (oVar2.f5693p) {
                    oVar2.t0();
                }
            }
            for (r0.o oVar3 = oVar; oVar3 != null; oVar3 = oVar3.f5685h) {
                if (oVar3.f5693p) {
                    oVar3.v0();
                }
            }
            while (oVar != null) {
                if (oVar.f5693p) {
                    oVar.q0();
                }
                oVar = oVar.f5685h;
            }
        }
        this.f4781e = w1.k.f6311a.addAndGet(1);
        for (r0.o oVar4 = t0Var.f4972e; oVar4 != null; oVar4 = oVar4.f5686i) {
            oVar4.p0();
        }
        t0Var.e();
        R(this);
    }

    @Override // f0.m
    public final void b() {
        n1.b0 b0Var = this.A;
        if (b0Var != null) {
            b0Var.b();
        }
        t0 t0Var = this.f4801y;
        y0 y0Var = t0Var.f4969b.f4999p;
        for (y0 y0Var2 = t0Var.f4970c; !b4.l.a(y0Var2, y0Var) && y0Var2 != null; y0Var2 = y0Var2.f4999p) {
            y0Var2.f5001r = true;
            y0Var2.E.a();
            if (y0Var2.G != null) {
                y0Var2.g1(null, false);
                y0Var2.f4998o.P(false);
            }
        }
    }

    @Override // f0.m
    public final void c() {
        n1.b0 b0Var = this.A;
        if (b0Var != null) {
            b0Var.f(true);
        }
        this.G = true;
        r0.o oVar = this.f4801y.f4971d;
        for (r0.o oVar2 = oVar; oVar2 != null; oVar2 = oVar2.f5685h) {
            if (oVar2.f5693p) {
                oVar2.t0();
            }
        }
        for (r0.o oVar3 = oVar; oVar3 != null; oVar3 = oVar3.f5685h) {
            if (oVar3.f5693p) {
                oVar3.v0();
            }
        }
        while (oVar != null) {
            if (oVar.f5693p) {
                oVar.q0();
            }
            oVar = oVar.f5685h;
        }
        if (D()) {
            B();
        }
    }

    public final void d(r0.p pVar) {
        t0 t0Var;
        h0.e eVar;
        boolean z2;
        this.D = pVar;
        t0 t0Var2 = this.f4801y;
        t tVar = t0Var2.f4969b;
        r0.o oVar = t0Var2.f4971d;
        d0 d0Var = t0Var2.f4968a;
        r0.o oVar2 = t0Var2.f4972e;
        r0.o oVarB = v0.f4980a;
        if (oVar2 == oVarB) {
            d4.a.L("padChain called on already padded chain");
            throw null;
        }
        oVar2.f5685h = oVarB;
        oVarB.f5686i = oVar2;
        h0.e eVar2 = t0Var2.f4973f;
        int i5 = eVar2 != null ? eVar2.f2578f : 0;
        h0.e eVar3 = t0Var2.f4974g;
        if (eVar3 == null) {
            eVar3 = new h0.e(new r0.n[16]);
        }
        int i6 = eVar3.f2578f;
        if (i6 < 16) {
            i6 = 16;
        }
        h0.e eVar4 = new h0.e(new r0.p[i6]);
        eVar4.b(pVar);
        c0.g1 g1Var = null;
        while (eVar4.k()) {
            r0.p pVar2 = (r0.p) eVar4.m(eVar4.f2578f - 1);
            if (pVar2 instanceof r0.j) {
                r0.j jVar = (r0.j) pVar2;
                eVar4.b(jVar.f5677b);
                eVar4.b(jVar.f5676a);
            } else if (pVar2 instanceof r0.n) {
                eVar3.b(pVar2);
            } else {
                if (g1Var == null) {
                    g1Var = new c0.g1(18, eVar3);
                }
                pVar2.b(g1Var);
                g1Var = g1Var;
            }
        }
        int i7 = eVar3.f2578f;
        if (i7 == i5) {
            r0.o oVar3 = oVarB.f5686i;
            int i8 = 0;
            while (oVar3 != null && i8 < i5) {
                if (eVar2 == null) {
                    d4.a.M("expected prior modifier list to be non-empty");
                    throw null;
                }
                r0.n nVar = (r0.n) eVar2.f2576d[i8];
                r0.n nVar2 = (r0.n) eVar3.f2576d[i8];
                char c5 = b4.l.a(nVar, nVar2) ? (char) 2 : nVar.getClass() == nVar2.getClass() ? (char) 1 : (char) 0;
                if (c5 == 0) {
                    oVar3 = oVar3.f5685h;
                    break;
                }
                if (c5 == 1) {
                    t0.h(nVar, nVar2, oVar3);
                }
                oVar3 = oVar3.f5686i;
                i8++;
            }
            if (i8 >= i5) {
                t0Var2 = t0Var2;
                t0Var = t0Var2;
                eVar = eVar3;
                z2 = false;
            } else {
                if (eVar2 == null) {
                    d4.a.M("expected prior modifier list to be non-empty");
                    throw null;
                }
                if (oVar3 == null) {
                    d4.a.M("structuralUpdate requires a non-null tail");
                    throw null;
                }
                boolean z3 = d0Var.E != null;
                r0.o oVar4 = oVar3;
                t0Var = t0Var2;
                eVar = eVar3;
                t0Var.f(i8, eVar2, eVar, oVar4, !z3);
                z2 = true;
            }
        } else {
            r0.p pVar3 = d0Var.E;
            if (pVar3 != null && i5 == 0) {
                for (int i9 = 0; i9 < eVar3.f2578f; i9++) {
                    oVarB = t0.b((r0.n) eVar3.f2576d[i9], oVarB);
                }
                int i10 = 0;
                for (r0.o oVar5 = oVar.f5685h; oVar5 != null && oVar5 != v0.f4980a; oVar5 = oVar5.f5685h) {
                    i10 |= oVar5.f5683f;
                    oVar5.f5684g = i10;
                }
                t0Var = t0Var2;
                eVar = eVar3;
            } else if (i7 != 0) {
                if (eVar2 == null) {
                    eVar2 = new h0.e(new r0.n[16]);
                }
                t0Var = t0Var2;
                eVar = eVar3;
                t0Var.f(0, eVar2, eVar, oVarB, !(pVar3 != null));
            } else {
                if (eVar2 == null) {
                    d4.a.M("expected prior modifier list to be non-empty");
                    throw null;
                }
                r0.o oVar6 = oVarB.f5686i;
                for (int i11 = 0; oVar6 != null && i11 < eVar2.f2578f; i11++) {
                    oVar6 = t0.c(oVar6).f5686i;
                }
                d0 d0VarR = d0Var.r();
                tVar.f5000q = d0VarR != null ? d0VarR.f4801y.f4969b : null;
                t0Var2.f4970c = tVar;
                t0Var = t0Var2;
                eVar = eVar3;
                z2 = false;
            }
            z2 = true;
        }
        t0Var.f4973f = eVar;
        if (eVar2 != null) {
            eVar2.g();
        } else {
            eVar2 = null;
        }
        t0Var.f4974g = eVar2;
        u0 u0Var = v0.f4980a;
        r0.o oVar7 = u0Var.f5686i;
        if (oVar7 != null) {
            oVar = oVar7;
        }
        oVar.f5685h = null;
        u0Var.f5686i = null;
        u0Var.f5684g = -1;
        u0Var.f5688k = null;
        if (oVar == u0Var) {
            d4.a.L("trimChain did not update the head");
            throw null;
        }
        t0Var.f4972e = oVar;
        if (z2) {
            t0Var.g();
        }
        this.f4802z.h();
        if (this.f4782f == null && t0Var.d(512)) {
            U(this);
        }
    }

    public final void e(e1 e1Var) {
        d0 d0Var;
        if (this.f4788l != null) {
            d4.a.L("Cannot attach " + this + " as it already is attached.  Tree: " + h(0));
            throw null;
        }
        d0 d0Var2 = this.f4787k;
        if (d0Var2 != null && !b4.l.a(d0Var2.f4788l, e1Var)) {
            StringBuilder sb = new StringBuilder("Attaching to a different owner(");
            sb.append(e1Var);
            sb.append(") than the parent's owner(");
            d0 d0VarR = r();
            sb.append(d0VarR != null ? d0VarR.f4788l : null);
            sb.append("). This tree: ");
            sb.append(h(0));
            sb.append(" Parent tree: ");
            d0 d0Var3 = this.f4787k;
            sb.append(d0Var3 != null ? d0Var3.h(0) : null);
            d4.a.L(sb.toString());
            throw null;
        }
        d0 d0VarR2 = r();
        j0 j0Var = this.f4802z;
        if (d0VarR2 == null) {
            j0Var.f4910r.f4884u = true;
            h0 h0Var = j0Var.f4911s;
            if (h0Var != null) {
                h0Var.f4858s = true;
            }
        }
        t0 t0Var = this.f4801y;
        t0Var.f4970c.f5000q = d0VarR2 != null ? d0VarR2.f4801y.f4969b : null;
        this.f4788l = e1Var;
        this.f4789m = (d0VarR2 != null ? d0VarR2.f4789m : -1) + 1;
        r0.p pVar = this.E;
        if (pVar != null) {
            d(pVar);
        }
        this.E = null;
        if (t0Var.d(8)) {
            B();
        }
        e1Var.getClass();
        d0 d0Var4 = this.f4787k;
        if (d0Var4 == null || (d0Var = d0Var4.f4782f) == null) {
            d0Var = this.f4782f;
        }
        U(d0Var);
        if (this.f4782f == null && t0Var.d(512)) {
            U(this);
        }
        if (!this.G) {
            for (r0.o oVar = t0Var.f4972e; oVar != null; oVar = oVar.f5686i) {
                oVar.p0();
            }
        }
        h0.e eVar = (h0.e) this.f4784h.f49e;
        int i5 = eVar.f2578f;
        if (i5 > 0) {
            Object[] objArr = eVar.f2576d;
            int i6 = 0;
            do {
                ((d0) objArr[i6]).e(e1Var);
                i6++;
            } while (i6 < i5);
        }
        if (!this.G) {
            t0Var.e();
        }
        A();
        if (d0VarR2 != null) {
            d0VarR2.A();
        }
        y0 y0Var = t0Var.f4969b.f4999p;
        for (y0 y0Var2 = t0Var.f4970c; !b4.l.a(y0Var2, y0Var) && y0Var2 != null; y0Var2 = y0Var2.f4999p) {
            y0Var2.g1(y0Var2.f5003t, true);
            d1 d1Var = y0Var2.G;
            if (d1Var != null) {
                d1Var.invalidate();
            }
        }
        j0Var.h();
        if (this.G) {
            return;
        }
        r0.o oVar2 = t0Var.f4972e;
        if ((oVar2.f5684g & 7168) != 0) {
            while (oVar2 != null) {
                int i7 = oVar2.f5683f;
                if (((i7 & 4096) != 0) | ((i7 & 1024) != 0) | ((i7 & 2048) != 0)) {
                    z0.a(oVar2);
                }
                oVar2 = oVar2.f5686i;
            }
        }
    }

    public final void f() {
        this.I = this.H;
        this.H = 3;
        h0.e eVarU = u();
        int i5 = eVarU.f2578f;
        if (i5 > 0) {
            Object[] objArr = eVarU.f2576d;
            int i6 = 0;
            do {
                d0 d0Var = (d0) objArr[i6];
                if (d0Var.H != 3) {
                    d0Var.f();
                }
                i6++;
            } while (i6 < i5);
        }
    }

    public final void g() {
        this.I = this.H;
        this.H = 3;
        h0.e eVarU = u();
        int i5 = eVarU.f2578f;
        if (i5 > 0) {
            Object[] objArr = eVarU.f2576d;
            int i6 = 0;
            do {
                d0 d0Var = (d0) objArr[i6];
                if (d0Var.H == 2) {
                    d0Var.g();
                }
                i6++;
            } while (i6 < i5);
        }
    }

    public final String h(int i5) {
        StringBuilder sb = new StringBuilder();
        for (int i6 = 0; i6 < i5; i6++) {
            sb.append("  ");
        }
        sb.append("|-");
        sb.append(toString());
        sb.append('\n');
        h0.e eVarU = u();
        int i7 = eVarU.f2578f;
        if (i7 > 0) {
            Object[] objArr = eVarU.f2576d;
            int i8 = 0;
            do {
                sb.append(((d0) objArr[i8]).h(i5 + 1));
                i8++;
            } while (i8 < i7);
        }
        String string = sb.toString();
        if (i5 != 0) {
            return string;
        }
        String strSubstring = string.substring(0, string.length() - 1);
        b4.l.d("this as java.lang.String…ing(startIndex, endIndex)", strSubstring);
        return strSubstring;
    }

    public final void i() {
        e0 e0Var;
        e1 e1Var = this.f4788l;
        if (e1Var == null) {
            StringBuilder sb = new StringBuilder("Cannot detach node that is already detached!  Tree: ");
            d0 d0VarR = r();
            sb.append(d0VarR != null ? d0VarR.h(0) : null);
            d4.a.M(sb.toString());
            throw null;
        }
        d0 d0VarR2 = r();
        j0 j0Var = this.f4802z;
        if (d0VarR2 != null) {
            d0VarR2.y();
            d0VarR2.A();
            j0Var.f4910r.f4877n = 3;
            h0 h0Var = j0Var.f4911s;
            if (h0Var != null) {
                h0Var.f4851l = 3;
            }
        }
        e0 e0Var2 = j0Var.f4910r.f4886w;
        e0Var2.f4817b = true;
        e0Var2.f4818c = false;
        e0Var2.f4820e = false;
        e0Var2.f4819d = false;
        e0Var2.f4821f = false;
        e0Var2.f4822g = false;
        e0Var2.f4823h = null;
        h0 h0Var2 = j0Var.f4911s;
        if (h0Var2 != null && (e0Var = h0Var2.f4859t) != null) {
            e0Var.f4817b = true;
            e0Var.f4818c = false;
            e0Var.f4820e = false;
            e0Var.f4819d = false;
            e0Var.f4821f = false;
            e0Var.f4822g = false;
            e0Var.f4823h = null;
        }
        t0 t0Var = this.f4801y;
        boolean zD = t0Var.d(8);
        r0.o oVar = t0Var.f4971d;
        if (zD) {
            B();
        }
        for (r0.o oVar2 = oVar; oVar2 != null; oVar2 = oVar2.f5685h) {
            if (oVar2.f5693p) {
                oVar2.v0();
            }
        }
        this.f4790n = true;
        h0.e eVar = (h0.e) this.f4784h.f49e;
        int i5 = eVar.f2578f;
        if (i5 > 0) {
            Object[] objArr = eVar.f2576d;
            int i6 = 0;
            do {
                ((d0) objArr[i6]).i();
                i6++;
            } while (i6 < i5);
        }
        this.f4790n = false;
        while (oVar != null) {
            if (oVar.f5693p) {
                oVar.q0();
            }
            oVar = oVar.f5685h;
        }
        q1.u uVar = (q1.u) e1Var;
        p0 p0Var = uVar.J;
        a2.h hVar = p0Var.f4946b;
        ((a2.h) hVar.f49e).v(this);
        ((a2.h) hVar.f50f).v(this);
        ((h0.e) p0Var.f4949e.f49e).l(this);
        uVar.B = true;
        this.f4788l = null;
        U(null);
        this.f4789m = 0;
        i0 i0Var = j0Var.f4910r;
        i0Var.f4874k = Integer.MAX_VALUE;
        i0Var.f4873j = Integer.MAX_VALUE;
        i0Var.f4884u = false;
        h0 h0Var3 = j0Var.f4911s;
        if (h0Var3 != null) {
            h0Var3.f4850k = Integer.MAX_VALUE;
            h0Var3.f4849j = Integer.MAX_VALUE;
            h0Var3.f4858s = false;
        }
    }

    public final void j(y0.r rVar, b1.b bVar) {
        this.f4801y.f4970c.D0(rVar, bVar);
    }

    public final List k() {
        h0 h0Var = this.f4802z.f4911s;
        b4.l.b(h0Var);
        h0.e eVar = h0Var.f4860u;
        j0 j0Var = h0Var.A;
        j0Var.f4893a.m();
        if (!h0Var.f4861v) {
            return eVar.f();
        }
        d0 d0Var = j0Var.f4893a;
        h0.e eVarU = d0Var.u();
        int i5 = eVarU.f2578f;
        if (i5 > 0) {
            Object[] objArr = eVarU.f2576d;
            int i6 = 0;
            do {
                d0 d0Var2 = (d0) objArr[i6];
                if (eVar.f2578f <= i6) {
                    h0 h0Var2 = d0Var2.f4802z.f4911s;
                    b4.l.b(h0Var2);
                    eVar.b(h0Var2);
                } else {
                    h0 h0Var3 = d0Var2.f4802z.f4911s;
                    b4.l.b(h0Var3);
                    Object[] objArr2 = eVar.f2576d;
                    Object obj = objArr2[i6];
                    objArr2[i6] = h0Var3;
                }
                i6++;
            } while (i6 < i5);
        }
        eVar.n(((h0.b) d0Var.m()).f2570d.f2578f, eVar.f2578f);
        h0Var.f4861v = false;
        return eVar.f();
    }

    public final List l() {
        return this.f4802z.f4910r.o0();
    }

    public final List m() {
        return u().f();
    }

    public final w1.i n() {
        Trace.beginSection("collapseSemantics");
        try {
            if (this.f4801y.d(8) && this.f4791o == null) {
                b4.w wVar = new b4.w();
                wVar.f697d = new w1.i();
                g1 snapshotObserver = ((q1.u) f.u(this)).getSnapshotObserver();
                snapshotObserver.a(this, snapshotObserver.f4838d, new t2(this, 3, wVar));
                Object obj = wVar.f697d;
                this.f4791o = (w1.i) obj;
                return (w1.i) obj;
            }
            return this.f4791o;
        } finally {
            Trace.endSection();
        }
    }

    public final List o() {
        return ((h0.e) this.f4784h.f49e).f();
    }

    public final int p() {
        int i5;
        h0 h0Var = this.f4802z.f4911s;
        if (h0Var == null || (i5 = h0Var.f4851l) == 0) {
            return 3;
        }
        return i5;
    }

    public final a2.h q() {
        a2.h hVar = this.f4795s;
        if (hVar != null) {
            return hVar;
        }
        a2.h hVar2 = new a2.h(this, this.f4794r);
        this.f4795s = hVar2;
        return hVar2;
    }

    public final d0 r() {
        d0 d0Var = this.f4787k;
        while (d0Var != null && d0Var.f4780d) {
            d0Var = d0Var.f4787k;
        }
        return d0Var;
    }

    public final int s() {
        return this.f4802z.f4910r.f4874k;
    }

    public final h0.e t() {
        boolean z2 = this.f4793q;
        h0.e eVar = this.f4792p;
        if (z2) {
            eVar.g();
            eVar.c(eVar.f2578f, u());
            eVar.p(L);
            this.f4793q = false;
        }
        return eVar;
    }

    public final String toString() {
        return q1.m0.w(this) + " children: " + ((h0.b) m()).f2570d.f2578f + " measurePolicy: " + this.f4794r;
    }

    public final h0.e u() {
        X();
        if (this.f4783g == 0) {
            return (h0.e) this.f4784h.f49e;
        }
        h0.e eVar = this.f4785i;
        b4.l.b(eVar);
        return eVar;
    }

    public final void v(long j5, r rVar, boolean z2, boolean z3) {
        t0 t0Var = this.f4801y;
        y0 y0Var = t0Var.f4970c;
        y0.n0 n0Var = y0.H;
        t0Var.f4970c.P0(y0.K, y0Var.I0(j5), rVar, z2, z3);
    }

    @Override // p1.f1
    public final boolean w() {
        return D();
    }

    public final void x(int i5, d0 d0Var) {
        if (d0Var.f4787k != null) {
            StringBuilder sb = new StringBuilder("Cannot insert ");
            sb.append(d0Var);
            sb.append(" because it already has a parent. This tree: ");
            sb.append(h(0));
            sb.append(" Other tree: ");
            d0 d0Var2 = d0Var.f4787k;
            sb.append(d0Var2 != null ? d0Var2.h(0) : null);
            d4.a.L(sb.toString());
            throw null;
        }
        if (d0Var.f4788l != null) {
            d4.a.L("Cannot insert " + d0Var + " because it already has an owner. This tree: " + h(0) + " Other tree: " + d0Var.h(0));
            throw null;
        }
        d0Var.f4787k = this;
        a2.h hVar = this.f4784h;
        ((h0.e) hVar.f49e).a(i5, d0Var);
        ((b0.a) hVar.f50f).a();
        I();
        if (d0Var.f4780d) {
            this.f4783g++;
        }
        C();
        e1 e1Var = this.f4788l;
        if (e1Var != null) {
            d0Var.e(e1Var);
        }
        if (d0Var.f4802z.f4906n > 0) {
            j0 j0Var = this.f4802z;
            j0Var.b(j0Var.f4906n + 1);
        }
    }

    public final void y() {
        if (this.C) {
            t0 t0Var = this.f4801y;
            y0 y0Var = t0Var.f4969b;
            y0 y0Var2 = t0Var.f4970c.f5000q;
            this.B = null;
            while (!b4.l.a(y0Var, y0Var2)) {
                if ((y0Var != null ? y0Var.G : null) != null) {
                    this.B = y0Var;
                    break;
                }
                y0Var = y0Var != null ? y0Var.f5000q : null;
            }
        }
        y0 y0Var3 = this.B;
        if (y0Var3 != null && y0Var3.G == null) {
            d4.a.M("layer was not set");
            throw null;
        }
        if (y0Var3 != null) {
            y0Var3.R0();
            return;
        }
        d0 d0VarR = r();
        if (d0VarR != null) {
            d0VarR.y();
        }
    }

    public final void z() {
        t0 t0Var = this.f4801y;
        y0 y0Var = t0Var.f4970c;
        t tVar = t0Var.f4969b;
        while (y0Var != tVar) {
            b4.l.c("null cannot be cast to non-null type androidx.compose.ui.node.LayoutModifierNodeCoordinator", y0Var);
            y yVar = (y) y0Var;
            d1 d1Var = yVar.G;
            if (d1Var != null) {
                d1Var.invalidate();
            }
            y0Var = yVar.f4999p;
        }
        d1 d1Var2 = t0Var.f4969b.G;
        if (d1Var2 != null) {
            d1Var2.invalidate();
        }
    }
}

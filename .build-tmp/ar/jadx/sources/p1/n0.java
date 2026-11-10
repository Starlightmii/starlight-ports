package p1;

import java.util.LinkedHashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class n0 extends m0 implements n1.e0 {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final y0 f4932o;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public LinkedHashMap f4934q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public n1.g0 f4936s;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f4933p = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final n1.d0 f4935r = new n1.d0(this);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final LinkedHashMap f4937t = new LinkedHashMap();

    public n0(y0 y0Var) {
        this.f4932o = y0Var;
    }

    public static final void z0(n0 n0Var, n1.g0 g0Var) {
        p3.n nVar;
        LinkedHashMap linkedHashMap;
        if (g0Var != null) {
            n0Var.m0(a.a.f(g0Var.l(), g0Var.e()));
            nVar = p3.n.f5026a;
        } else {
            nVar = null;
        }
        if (nVar == null) {
            n0Var.m0(0L);
        }
        if (!b4.l.a(n0Var.f4936s, g0Var) && g0Var != null && ((((linkedHashMap = n0Var.f4934q) != null && !linkedHashMap.isEmpty()) || !g0Var.m().isEmpty()) && !b4.l.a(g0Var.m(), n0Var.f4934q))) {
            h0 h0Var = n0Var.f4932o.f4998o.f4802z.f4911s;
            b4.l.b(h0Var);
            h0Var.f4859t.f();
            LinkedHashMap linkedHashMap2 = n0Var.f4934q;
            if (linkedHashMap2 == null) {
                linkedHashMap2 = new LinkedHashMap();
                n0Var.f4934q = linkedHashMap2;
            }
            linkedHashMap2.clear();
            linkedHashMap2.putAll(g0Var.m());
        }
        n0Var.f4936s = g0Var;
    }

    public void A0() {
        u0().n();
    }

    public final void B0(long j5) {
        if (!k2.h.a(this.f4933p, j5)) {
            this.f4933p = j5;
            y0 y0Var = this.f4932o;
            h0 h0Var = y0Var.f4998o.f4802z.f4911s;
            if (h0Var != null) {
                h0Var.q0();
            }
            m0.x0(y0Var);
        }
        if (this.f4924k) {
            return;
        }
        p0(new i1(u0(), this));
    }

    public final long C0(n0 n0Var, boolean z2) {
        long jC = 0;
        n0 n0VarJ0 = this;
        while (!n0VarJ0.equals(n0Var)) {
            if (!n0VarJ0.f4922i || !z2) {
                jC = k2.h.c(jC, n0VarJ0.f4933p);
            }
            y0 y0Var = n0VarJ0.f4932o.f5000q;
            b4.l.b(y0Var);
            n0VarJ0 = y0Var.J0();
            b4.l.b(n0VarJ0);
        }
        return jC;
    }

    @Override // k2.b
    public final float a() {
        return this.f4932o.a();
    }

    @Override // n1.m
    public final k2.k getLayoutDirection() {
        return this.f4932o.f4998o.f4797u;
    }

    @Override // n1.m0, n1.e0
    public final Object h() {
        return this.f4932o.h();
    }

    @Override // k2.b
    public final float j() {
        return this.f4932o.j();
    }

    @Override // n1.m0
    public final void k0(long j5, float f5, a4.c cVar) {
        B0(j5);
        if (this.f4923j) {
            return;
        }
        A0();
    }

    @Override // p1.m0, n1.m
    public final boolean o() {
        return true;
    }

    @Override // p1.m0
    public final m0 q0() {
        y0 y0Var = this.f4932o.f4999p;
        if (y0Var != null) {
            return y0Var.J0();
        }
        return null;
    }

    @Override // p1.m0
    public final n1.p r0() {
        return this.f4935r;
    }

    @Override // p1.m0
    public final boolean s0() {
        return this.f4936s != null;
    }

    @Override // p1.m0
    public final d0 t0() {
        return this.f4932o.f4998o;
    }

    @Override // p1.m0
    public final n1.g0 u0() {
        n1.g0 g0Var = this.f4936s;
        if (g0Var != null) {
            return g0Var;
        }
        throw new IllegalStateException("LookaheadDelegate has not been measured yet when measureResult is requested.");
    }

    @Override // p1.m0
    public final m0 v0() {
        y0 y0Var = this.f4932o.f5000q;
        if (y0Var != null) {
            return y0Var.J0();
        }
        return null;
    }

    @Override // p1.m0
    public final long w0() {
        return this.f4933p;
    }

    @Override // p1.m0
    public final void y0() {
        k0(this.f4933p, 0.0f, null);
    }
}

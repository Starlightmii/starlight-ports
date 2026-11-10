package p1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y extends y0 {
    public static final y0.h O;
    public w M;
    public x N;

    static {
        y0.h hVarG = y0.l0.g();
        int i5 = y0.u.f6695h;
        hVarG.e(y0.u.f6692e);
        hVarG.k(1.0f);
        hVarG.l(1);
        O = hVarG;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public y(d0 d0Var, w wVar) {
        super(d0Var);
        this.M = wVar;
        this.N = d0Var.f4782f != null ? new x(this) : null;
        if ((((r0.o) wVar).f5681d.f5683f & 512) != 0) {
            throw new ClassCastException();
        }
    }

    @Override // p1.y0
    public final void G0() {
        if (this.N == null) {
            this.N = new x(this);
        }
    }

    @Override // p1.y0
    public final n0 J0() {
        return this.N;
    }

    @Override // p1.y0
    public final r0.o L0() {
        return ((r0.o) this.M).f5681d;
    }

    @Override // p1.y0
    public final void X0(y0.r rVar, b1.b bVar) {
        y0 y0Var = this.f4999p;
        b4.l.b(y0Var);
        y0Var.D0(rVar, bVar);
        if (((q1.u) f.u(this.f4998o)).getShowLayoutBounds()) {
            E0(rVar, O);
        }
    }

    @Override // n1.e0
    public final int a0(int i5) {
        w wVar = this.M;
        y0 y0Var = this.f4999p;
        b4.l.b(y0Var);
        return wVar.m0(this, y0Var, i5);
    }

    @Override // n1.e0
    public final n1.m0 c(long j5) {
        n0(j5);
        w wVar = this.M;
        y0 y0Var = this.f4999p;
        b4.l.b(y0Var);
        a1(wVar.c(this, y0Var, j5));
        V0();
        return this;
    }

    @Override // n1.e0
    public final int c0(int i5) {
        w wVar = this.M;
        y0 y0Var = this.f4999p;
        b4.l.b(y0Var);
        return wVar.a0(this, y0Var, i5);
    }

    @Override // n1.e0
    public final int d(int i5) {
        w wVar = this.M;
        y0 y0Var = this.f4999p;
        b4.l.b(y0Var);
        return wVar.x(this, y0Var, i5);
    }

    @Override // n1.e0
    public final int e0(int i5) {
        w wVar = this.M;
        y0 y0Var = this.f4999p;
        b4.l.b(y0Var);
        return wVar.g0(this, y0Var, i5);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void i1(w wVar) {
        if (!wVar.equals(this.M) && (((r0.o) wVar).f5681d.f5683f & 512) != 0) {
            throw new ClassCastException();
        }
        this.M = wVar;
    }

    @Override // n1.m0
    public final void k0(long j5, float f5, a4.c cVar) {
        Y0(j5, f5, cVar);
        if (this.f4923j) {
            return;
        }
        W0();
        u0().n();
        b4.l.b(this.f4999p);
    }

    @Override // p1.m0
    public final int o0(n1.l lVar) {
        x xVar = this.N;
        if (xVar == null) {
            return f.c(this, lVar);
        }
        Integer num = (Integer) xVar.f4937t.get(lVar);
        if (num != null) {
            return num.intValue();
        }
        return Integer.MIN_VALUE;
    }
}

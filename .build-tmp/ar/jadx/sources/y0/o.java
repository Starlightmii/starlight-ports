package y0;

import c0.c2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o extends r0.o implements p1.w {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public a4.c f6665q;

    public o(a4.c cVar) {
        this.f6665q = cVar;
    }

    @Override // p1.w
    public final n1.g0 c(n1.h0 h0Var, n1.e0 e0Var, long j5) {
        n1.m0 m0VarC = e0Var.c(j5);
        return h0Var.F(m0VarC.f4130d, m0VarC.f4131e, q3.t.f5478d, new c2(m0VarC, 25, this));
    }

    @Override // r0.o
    public final boolean o0() {
        return false;
    }

    public final String toString() {
        return "BlockGraphicsLayerModifier(block=" + this.f6665q + ')';
    }
}

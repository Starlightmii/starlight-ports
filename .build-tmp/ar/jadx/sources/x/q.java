package x;

import f0.s1;
import p1.y0;
import v.o0;
import z.e0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q extends r0.o implements p1.k, p1.p, p1.l {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public c f6478q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public o0 f6479r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public e0 f6480s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final s1 f6481t = f0.l.s(null);

    public q(c cVar, o0 o0Var, e0 e0Var) {
        this.f6478q = cVar;
        this.f6479r = o0Var;
        this.f6480s = e0Var;
    }

    @Override // p1.p
    public final void D(y0 y0Var) {
        this.f6481t.setValue(y0Var);
    }

    @Override // r0.o
    public final void r0() {
        c cVar = this.f6478q;
        if (cVar.f6463a != null) {
            throw new IllegalStateException("Expected textInputModifierNode to be null");
        }
        cVar.f6463a = this;
    }

    @Override // r0.o
    public final void s0() {
        this.f6478q.k(this);
    }
}

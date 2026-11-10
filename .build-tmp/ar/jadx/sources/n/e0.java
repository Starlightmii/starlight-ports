package n;

import p1.o1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e0 extends r0.o implements o1, p1.p {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final u0 f3893s = new u0(5);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f3894q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public p1.y0 f3895r;

    @Override // p1.p
    public final void D(p1.y0 y0Var) {
        f0 f0VarY0;
        this.f3895r = y0Var;
        if (this.f3894q) {
            if (!y0Var.L0().f5693p) {
                f0 f0VarY1 = y0();
                if (f0VarY1 != null) {
                    f0VarY1.y0(null);
                    return;
                }
                return;
            }
            p1.y0 y0Var2 = this.f3895r;
            if (y0Var2 == null || !y0Var2.L0().f5693p || (f0VarY0 = y0()) == null) {
                return;
            }
            f0VarY0.y0(this.f3895r);
        }
    }

    @Override // p1.o1
    public final Object k() {
        return f3893s;
    }

    @Override // r0.o
    public final boolean o0() {
        return false;
    }

    public final f0 y0() {
        if (!this.f5693p) {
            return null;
        }
        o1 o1VarI = p1.f.i(this, f0.f3902r);
        if (o1VarI instanceof f0) {
            return (f0) o1VarI;
        }
        return null;
    }
}

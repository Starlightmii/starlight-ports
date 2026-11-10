package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o0 implements n2 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a4.c f2282d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public p0 f2283e;

    public o0(a4.c cVar) {
        this.f2282d = cVar;
    }

    @Override // f0.n2
    public final void d() {
        this.f2283e = (p0) this.f2282d.c(l.f2233e);
    }

    @Override // f0.n2
    public final void e() {
    }

    @Override // f0.n2
    public final void l() {
        p0 p0Var = this.f2283e;
        if (p0Var != null) {
            p0Var.dispose();
        }
        this.f2283e = null;
    }
}

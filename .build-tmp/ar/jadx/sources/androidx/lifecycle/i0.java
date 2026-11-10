package androidx.lifecycle;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i0 implements t, AutoCloseable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f257d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final h0 f258e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f259f;

    public i0(String str, h0 h0Var) {
        this.f257d = str;
        this.f258e = h0Var;
    }

    @Override // androidx.lifecycle.t
    public final void b(v vVar, o oVar) {
        if (oVar == o.ON_DESTROY) {
            this.f259f = false;
            vVar.getLifecycle().b(this);
        }
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
    }

    public final void e(q qVar, j3.e eVar) {
        b4.l.e("registry", eVar);
        b4.l.e("lifecycle", qVar);
        if (this.f259f) {
            throw new IllegalStateException("Already attached to lifecycleOwner");
        }
        this.f259f = true;
        qVar.a(this);
        eVar.b(this.f257d, (b.g) this.f258e.f256a.f2453e);
    }
}

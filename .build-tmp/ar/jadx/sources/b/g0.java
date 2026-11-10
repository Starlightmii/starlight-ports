package b;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g0 implements d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c.d f333d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ i0 f334e;

    public g0(i0 i0Var, c.d dVar) {
        b4.l.e("onBackPressedCallback", dVar);
        this.f334e = i0Var;
        this.f333d = dVar;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [a4.a, b4.j] */
    @Override // b.d
    public final void cancel() {
        i0 i0Var = this.f334e;
        q3.j jVar = i0Var.f341b;
        c.d dVar = this.f333d;
        jVar.remove(dVar);
        if (b4.l.a(i0Var.f342c, dVar)) {
            dVar.getClass();
            i0Var.f342c = null;
        }
        dVar.f707a.remove(this);
        ?? r1 = dVar.f708b;
        if (r1 != 0) {
            r1.a();
        }
        dVar.f708b = null;
    }
}

package b;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f0 implements androidx.lifecycle.t, d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final androidx.lifecycle.q f327d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c.d f328e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public g0 f329f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ i0 f330g;

    public f0(i0 i0Var, androidx.lifecycle.q qVar, c.d dVar) {
        b4.l.e("lifecycle", qVar);
        b4.l.e("onBackPressedCallback", dVar);
        this.f330g = i0Var;
        this.f327d = qVar;
        this.f328e = dVar;
        qVar.a(this);
    }

    @Override // androidx.lifecycle.t
    public final void b(androidx.lifecycle.v vVar, androidx.lifecycle.o oVar) {
        if (oVar != androidx.lifecycle.o.ON_START) {
            if (oVar != androidx.lifecycle.o.ON_STOP) {
                if (oVar == androidx.lifecycle.o.ON_DESTROY) {
                    cancel();
                    return;
                }
                return;
            } else {
                g0 g0Var = this.f329f;
                if (g0Var != null) {
                    g0Var.cancel();
                    return;
                }
                return;
            }
        }
        i0 i0Var = this.f330g;
        i0Var.getClass();
        c.d dVar = this.f328e;
        b4.l.e("onBackPressedCallback", dVar);
        i0Var.f341b.addLast(dVar);
        g0 g0Var2 = new g0(i0Var, dVar);
        dVar.f707a.add(g0Var2);
        i0Var.d();
        dVar.f708b = new h0(0, i0Var, i0.class, "updateEnabledCallbacks", "updateEnabledCallbacks()V", 0, 0, 1);
        this.f329f = g0Var2;
    }

    @Override // b.d
    public final void cancel() {
        this.f327d.b(this);
        this.f328e.f707a.remove(this);
        g0 g0Var = this.f329f;
        if (g0Var != null) {
            g0Var.cancel();
        }
        this.f329f = null;
    }
}

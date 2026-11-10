package w2;

import android.view.WindowInsets;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class f0 extends l0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final WindowInsets.Builder f6392e;

    public f0() {
        this.f6392e = r2.a.d();
    }

    public f0(z0 z0Var) {
        super(z0Var);
        WindowInsets windowInsetsA = z0Var.a();
        this.f6392e = windowInsetsA != null ? r2.a.e(windowInsetsA) : r2.a.d();
    }

    @Override // w2.l0
    public z0 b() {
        a();
        z0 z0VarB = z0.b(null, this.f6392e.build());
        r2.c[] cVarArr = this.f6405b;
        v0 v0Var = z0VarB.f6456a;
        v0Var.u(cVarArr);
        v0Var.t(null);
        v0Var.y(this.f6406c);
        v0Var.z(this.f6407d);
        return z0VarB;
    }

    @Override // w2.l0
    public void e(r2.c cVar) {
        this.f6392e.setMandatorySystemGestureInsets(cVar.d());
    }

    @Override // w2.l0
    public void f(r2.c cVar) {
        this.f6392e.setSystemGestureInsets(cVar.d());
    }

    @Override // w2.l0
    public void g(r2.c cVar) {
        this.f6392e.setSystemWindowInsets(cVar.d());
    }

    @Override // w2.l0
    public void h(r2.c cVar) {
        this.f6392e.setTappableElementInsets(cVar.d());
    }
}

package w2;

import android.view.WindowInsets;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class n0 extends m0 {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public r2.c f6425r;

    public n0(z0 z0Var, WindowInsets windowInsets) {
        super(z0Var, windowInsets);
        this.f6425r = null;
    }

    @Override // w2.v0
    public z0 b() {
        return z0.b(null, this.f6414c.consumeStableInsets());
    }

    @Override // w2.v0
    public z0 c() {
        return z0.b(null, this.f6414c.consumeSystemWindowInsets());
    }

    @Override // w2.v0
    public final r2.c k() {
        if (this.f6425r == null) {
            WindowInsets windowInsets = this.f6414c;
            this.f6425r = r2.c.b(windowInsets.getStableInsetLeft(), windowInsets.getStableInsetTop(), windowInsets.getStableInsetRight(), windowInsets.getStableInsetBottom());
        }
        return this.f6425r;
    }

    @Override // w2.v0
    public boolean q() {
        return this.f6414c.isConsumed();
    }

    @Override // w2.v0
    public void w(r2.c cVar) {
        this.f6425r = cVar;
    }
}

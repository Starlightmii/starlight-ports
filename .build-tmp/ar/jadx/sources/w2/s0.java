package w2;

import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class s0 extends r0 {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final z0 f6433w = z0.b(null, WindowInsets.CONSUMED);

    public s0(z0 z0Var, WindowInsets windowInsets) {
        super(z0Var, windowInsets);
    }

    @Override // w2.q0, w2.m0, w2.v0
    public r2.c h(int i5) {
        return r2.c.c(this.f6414c.getInsets(y0.a(i5)));
    }

    @Override // w2.q0, w2.m0, w2.v0
    public r2.c i(int i5) {
        return r2.c.c(this.f6414c.getInsetsIgnoringVisibility(y0.a(i5)));
    }

    @Override // w2.m0, w2.v0
    public void o(View view) {
    }

    @Override // w2.q0, w2.m0, w2.v0
    public boolean s(int i5) {
        return this.f6414c.isVisible(y0.a(i5));
    }
}

package w2;

import android.graphics.Rect;
import android.view.WindowInsets;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u0 extends s0 {
    public u0(z0 z0Var, WindowInsets windowInsets) {
        super(z0Var, windowInsets);
    }

    @Override // w2.m0, w2.v0
    public List<Rect> e(int i5) {
        return this.f6414c.getBoundingRects(y0.a(i5));
    }

    @Override // w2.m0, w2.v0
    public List<Rect> f(int i5) {
        return this.f6414c.getBoundingRectsIgnoringVisibility(y0.a(i5));
    }

    @Override // w2.m0, w2.v0
    public void p() {
    }
}

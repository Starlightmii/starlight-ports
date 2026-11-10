package w2;

import android.view.WindowInsets;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class p0 extends o0 {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public r2.c f6429s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public r2.c f6430t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public r2.c f6431u;

    public p0(z0 z0Var, WindowInsets windowInsets) {
        super(z0Var, windowInsets);
        this.f6429s = null;
        this.f6430t = null;
        this.f6431u = null;
    }

    @Override // w2.v0
    public r2.c j() {
        if (this.f6430t == null) {
            this.f6430t = r2.c.c(this.f6414c.getMandatorySystemGestureInsets());
        }
        return this.f6430t;
    }

    @Override // w2.v0
    public r2.c l() {
        if (this.f6429s == null) {
            this.f6429s = r2.c.c(this.f6414c.getSystemGestureInsets());
        }
        return this.f6429s;
    }

    @Override // w2.v0
    public r2.c n() {
        if (this.f6431u == null) {
            this.f6431u = r2.c.c(this.f6414c.getTappableElementInsets());
        }
        return this.f6431u;
    }

    @Override // w2.n0, w2.v0
    public void w(r2.c cVar) {
    }
}

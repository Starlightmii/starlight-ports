package r;

import p1.h1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g0 extends r0.o implements h1 {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f5563q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f5564r;

    @Override // p1.h1
    public final Object e0(Object obj) {
        p0 p0Var = obj instanceof p0 ? (p0) obj : null;
        if (p0Var == null) {
            p0Var = new p0();
            p0Var.f5605a = 0.0f;
            p0Var.f5606b = true;
        }
        p0Var.f5605a = this.f5563q;
        p0Var.f5606b = this.f5564r;
        return p0Var;
    }
}

package n;

import c0.q4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w extends r0.o implements p1.o {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final q.j f4036q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f4037r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f4038s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f4039t;

    public w(q.j jVar) {
        this.f4036q = jVar;
    }

    @Override // p1.o
    public final void d(p1.f0 f0Var) {
        f0Var.c();
        a1.c cVar = f0Var.f4828d;
        if (this.f4037r) {
            a1.f.B(f0Var, y0.u.b(0.3f, y0.u.f6689b), cVar.b(), 122);
        } else if (this.f4038s || this.f4039t) {
            a1.f.B(f0Var, y0.u.b(0.1f, y0.u.f6689b), cVar.b(), 122);
        }
    }

    @Override // r0.o
    public final void r0() {
        i4.y.n(n0(), null, new q4(this, null, 2), 3);
    }
}

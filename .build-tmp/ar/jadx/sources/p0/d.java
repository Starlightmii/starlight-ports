package p0;

import f0.k1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d extends f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a4.c f4684e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f f4685f;

    public d(long j5, k kVar, a4.c cVar, f fVar) {
        super(j5, kVar);
        this.f4684e = cVar;
        this.f4685f = fVar;
        fVar.k();
    }

    @Override // p0.f
    public final void c() {
        f fVar = this.f4685f;
        if (this.f4695c) {
            return;
        }
        if (this.f4694b != fVar.g()) {
            a();
        }
        fVar.l();
        this.f4695c = true;
        synchronized (m.f4719c) {
            o();
        }
    }

    @Override // p0.f
    public final a4.c e() {
        return this.f4684e;
    }

    @Override // p0.f
    public final boolean f() {
        return true;
    }

    @Override // p0.f
    public final a4.c i() {
        return null;
    }

    @Override // p0.f
    public final void k() {
        r.k();
        throw null;
    }

    @Override // p0.f
    public final void l() {
        r.k();
        throw null;
    }

    @Override // p0.f
    public final void m() {
    }

    @Override // p0.f
    public final void n(x xVar) {
        k1 k1Var = m.f4717a;
        throw new IllegalStateException("Cannot modify a state object in a read-only snapshot");
    }

    @Override // p0.f
    public final f u(a4.c cVar) {
        return new d(this.f4694b, this.f4693a, m.k(cVar, this.f4684e, true), this.f4685f);
    }
}

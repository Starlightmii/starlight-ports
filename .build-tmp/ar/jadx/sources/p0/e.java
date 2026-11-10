package p0;

import f0.k1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a4.c f4691e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4692f;

    public e(long j5, k kVar, a4.c cVar) {
        super(j5, kVar);
        this.f4691e = cVar;
        this.f4692f = 1;
    }

    @Override // p0.f
    public final void c() {
        if (this.f4695c) {
            return;
        }
        l();
        this.f4695c = true;
        synchronized (m.f4719c) {
            o();
        }
    }

    @Override // p0.f
    public final a4.c e() {
        return this.f4691e;
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
        this.f4692f++;
    }

    @Override // p0.f
    public final void l() {
        int i5 = this.f4692f - 1;
        this.f4692f = i5;
        if (i5 == 0) {
            a();
        }
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
        m.c(this);
        return new d(this.f4694b, this.f4693a, m.k(cVar, this.f4691e, true), this);
    }
}

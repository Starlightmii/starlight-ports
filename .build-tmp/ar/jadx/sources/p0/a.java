package p0;

import f0.b1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends b {
    @Override // p0.b
    public final b C(a4.c cVar, a4.c cVar2) {
        return (b) ((f) m.e(new io.github.andrea_lyz.assistrestore.ui.a(new b1(cVar, 1, cVar2), 4)));
    }

    @Override // p0.b, p0.f
    public final void c() {
        synchronized (m.f4719c) {
            o();
        }
    }

    @Override // p0.b, p0.f
    public final void k() {
        r.k();
        throw null;
    }

    @Override // p0.b, p0.f
    public final void l() {
        r.k();
        throw null;
    }

    @Override // p0.b, p0.f
    public final void m() {
        m.a();
    }

    @Override // p0.b, p0.f
    public final f u(a4.c cVar) {
        return (e) ((f) m.e(new io.github.andrea_lyz.assistrestore.ui.a(new f0.f(2, cVar), 4)));
    }

    @Override // p0.b
    public final r w() {
        throw new IllegalStateException("Cannot apply the global snapshot directly. Call Snapshot.advanceGlobalSnapshot");
    }
}

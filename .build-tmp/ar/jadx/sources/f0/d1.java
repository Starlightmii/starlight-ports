package f0;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d1 implements n2, i4.t {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final t3.h f2113d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a4.e f2114e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final n4.c f2115f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public i4.h1 f2116g;

    public d1(t3.h hVar, a4.e eVar) {
        this.f2113d = hVar;
        this.f2114e = eVar;
        this.f2115f = i4.y.a(hVar.k(this));
    }

    @Override // f0.n2
    public final void d() {
        i4.h1 h1Var = this.f2116g;
        if (h1Var != null) {
            CancellationException cancellationException = new CancellationException("Old job was still running!");
            cancellationException.initCause(null);
            h1Var.b(cancellationException);
        }
        this.f2116g = i4.y.n(this.f2115f, null, this.f2114e, 3);
    }

    @Override // f0.n2
    public final void e() {
        i4.h1 h1Var = this.f2116g;
        if (h1Var != null) {
            h1Var.y(new s0(1));
        }
        this.f2116g = null;
    }

    @Override // t3.h
    public final t3.h f(t3.g gVar) {
        return p3.p.y(this, gVar);
    }

    @Override // t3.f
    public final t3.g getKey() {
        return i4.s.f2773d;
    }

    @Override // t3.h
    public final t3.f j(t3.g gVar) {
        return p3.p.p(this, gVar);
    }

    @Override // t3.h
    public final t3.h k(t3.h hVar) {
        return p3.p.A(this, hVar);
    }

    @Override // f0.n2
    public final void l() {
        i4.h1 h1Var = this.f2116g;
        if (h1Var != null) {
            h1Var.y(new s0(1));
        }
        this.f2116g = null;
    }

    @Override // i4.t
    public final void s(Throwable th, t3.h hVar) throws Throwable {
        q0.d dVar = (q0.d) hVar.j(q0.d.f5089e);
        if (dVar != null) {
            p3.p.D(th, new f2(dVar, 1, this));
        }
        i4.t tVar = (i4.t) this.f2113d.j(i4.s.f2773d);
        if (tVar == null) {
            throw th;
        }
        tVar.s(th, hVar);
    }

    @Override // t3.h
    public final Object t(a4.e eVar, Object obj) {
        return eVar.invoke(obj, this);
    }
}

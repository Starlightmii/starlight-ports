package p0;

import f0.k1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d0 extends f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final f f4686e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f4687f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f4688g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a4.c f4689h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f4690i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d0(f fVar, a4.c cVar, boolean z2, boolean z3) {
        a4.c cVarE;
        super(0L, k.f4709h);
        k1 k1Var = m.f4717a;
        this.f4686e = fVar;
        this.f4687f = z2;
        this.f4688g = z3;
        this.f4689h = m.k(cVar, (fVar == null || (cVarE = fVar.e()) == null) ? m.f4726j.f4663e : cVarE, z2);
        this.f4690i = n0.i.b();
    }

    @Override // p0.f
    public final void c() {
        f fVar;
        this.f4695c = true;
        if (!this.f4688g || (fVar = this.f4686e) == null) {
            return;
        }
        fVar.c();
    }

    @Override // p0.f
    public final k d() {
        return v().d();
    }

    @Override // p0.f
    public final a4.c e() {
        return this.f4689h;
    }

    @Override // p0.f
    public final boolean f() {
        return v().f();
    }

    @Override // p0.f
    public final long g() {
        return v().g();
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
        v().m();
    }

    @Override // p0.f
    public final void n(x xVar) {
        v().n(xVar);
    }

    @Override // p0.f
    public final f u(a4.c cVar) {
        a4.c cVarK = m.k(cVar, this.f4689h, true);
        return !this.f4687f ? m.g(v().u(null), cVarK, true) : v().u(cVarK);
    }

    public final f v() {
        f fVar = this.f4686e;
        return fVar == null ? m.f4726j : fVar;
    }
}

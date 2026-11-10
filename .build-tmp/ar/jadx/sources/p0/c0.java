package p0;

import f0.k1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c0 extends b {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final b f4678o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f4679p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f4680q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public a4.c f4681r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public a4.c f4682s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final long f4683t;

    /* JADX WARN: Illegal instructions before constructor call */
    public c0(b bVar, a4.c cVar, a4.c cVar2, boolean z2, boolean z3) {
        a4.c cVarI;
        a4.c cVarE;
        k1 k1Var = m.f4717a;
        super(0L, k.f4709h, m.k(cVar, (bVar == null || (cVarE = bVar.e()) == null) ? m.f4726j.f4663e : cVarE, z2), m.l(cVar2, (bVar == null || (cVarI = bVar.i()) == null) ? m.f4726j.f4664f : cVarI));
        this.f4678o = bVar;
        this.f4679p = z2;
        this.f4680q = z3;
        this.f4681r = this.f4663e;
        this.f4682s = this.f4664f;
        this.f4683t = n0.i.b();
    }

    @Override // p0.b
    public final void B(j.a0 a0Var) {
        r.k();
        throw null;
    }

    @Override // p0.b
    public final b C(a4.c cVar, a4.c cVar2) {
        a4.c cVarK = m.k(cVar, this.f4681r, true);
        a4.c cVarL = m.l(cVar2, this.f4682s);
        return !this.f4679p ? new c0(D().C(null, cVarL), cVarK, cVarL, false, true) : D().C(cVarK, cVarL);
    }

    public final b D() {
        b bVar = this.f4678o;
        return bVar == null ? m.f4726j : bVar;
    }

    @Override // p0.b, p0.f
    public final void c() {
        b bVar;
        this.f4695c = true;
        if (!this.f4680q || (bVar = this.f4678o) == null) {
            return;
        }
        bVar.c();
    }

    @Override // p0.f
    public final k d() {
        return D().d();
    }

    @Override // p0.b, p0.f
    public final a4.c e() {
        return this.f4681r;
    }

    @Override // p0.b, p0.f
    public final boolean f() {
        return D().f();
    }

    @Override // p0.f
    public final long g() {
        return D().g();
    }

    @Override // p0.b, p0.f
    public final int h() {
        return D().h();
    }

    @Override // p0.b, p0.f
    public final a4.c i() {
        return this.f4682s;
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
        D().m();
    }

    @Override // p0.b, p0.f
    public final void n(x xVar) {
        D().n(xVar);
    }

    @Override // p0.f
    public final void r(k kVar) {
        r.k();
        throw null;
    }

    @Override // p0.f
    public final void s(long j5) {
        r.k();
        throw null;
    }

    @Override // p0.b, p0.f
    public final void t(int i5) {
        D().t(i5);
    }

    @Override // p0.b, p0.f
    public final f u(a4.c cVar) {
        a4.c cVarK = m.k(cVar, this.f4681r, true);
        return !this.f4679p ? m.g(D().u(null), cVarK, true) : D().u(cVarK);
    }

    @Override // p0.b
    public final r w() {
        return D().w();
    }

    @Override // p0.b
    public final j.a0 x() {
        return D().x();
    }

    @Override // p0.b
    /* JADX INFO: renamed from: y */
    public final a4.c e() {
        return this.f4681r;
    }
}

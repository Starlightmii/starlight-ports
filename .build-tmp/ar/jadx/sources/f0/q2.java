package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q2 implements i4.v, n2 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final j f2295g = new j();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final t3.h f2296d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final q2 f2297e = this;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile t3.h f2298f;

    public q2(t3.h hVar) {
        this.f2296d = hVar;
    }

    public final void a() {
        synchronized (this.f2297e) {
            try {
                t3.h hVar = this.f2298f;
                if (hVar == null) {
                    this.f2298f = f2295g;
                } else {
                    s0 s0Var = new s0(0);
                    i4.s0 s0Var2 = (i4.s0) hVar.j(i4.s.f2774e);
                    if (s0Var2 != null) {
                        s0Var2.b(s0Var);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // f0.n2
    public final void d() {
    }

    @Override // f0.n2
    public final void e() {
        a();
    }

    @Override // i4.v
    public final t3.h h() {
        t3.h hVarK;
        t3.h hVar = this.f2298f;
        if (hVar == null || hVar == f2295g) {
            q0.d dVar = (q0.d) this.f2296d.j(q0.d.f5089e);
            t3.h p2Var = dVar != null ? new p2(dVar, this) : t3.i.f5807d;
            synchronized (this.f2297e) {
                try {
                    t3.h hVar2 = this.f2298f;
                    if (hVar2 == null) {
                        t3.h hVar3 = this.f2296d;
                        hVarK = hVar3.k(new i4.u0((i4.s0) hVar3.j(i4.s.f2774e))).k(t3.i.f5807d).k(p2Var);
                    } else if (hVar2 == f2295g) {
                        t3.h hVar4 = this.f2296d;
                        i4.u0 u0Var = new i4.u0((i4.s0) hVar4.j(i4.s.f2774e));
                        u0Var.x(new s0(0));
                        hVarK = hVar4.k(u0Var).k(t3.i.f5807d).k(p2Var);
                    } else {
                        hVarK = hVar2;
                    }
                    this.f2298f = hVarK;
                } catch (Throwable th) {
                    throw th;
                }
            }
            hVar = hVarK;
        }
        b4.l.b(hVar);
        return hVar;
    }

    @Override // f0.n2
    public final void l() {
        a();
    }
}

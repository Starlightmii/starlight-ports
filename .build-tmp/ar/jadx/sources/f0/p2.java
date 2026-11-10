package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p2 extends t3.a implements i4.t {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ q0.d f2288e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ q2 f2289f;

    /* JADX WARN: Illegal instructions before constructor call */
    public p2(q0.d dVar, q2 q2Var) {
        i4.s sVar = i4.s.f2773d;
        this.f2288e = dVar;
        this.f2289f = q2Var;
        super(sVar);
    }

    @Override // i4.t
    public final void s(Throwable th, t3.h hVar) throws Throwable {
        q0.d dVar = this.f2288e;
        q2 q2Var = this.f2289f;
        p3.p.D(th, new f2(dVar, 1, q2Var));
        i4.t tVar = (i4.t) q2Var.f2296d.j(i4.s.f2773d);
        if (tVar == null) {
            throw th;
        }
        tVar.s(th, hVar);
    }
}

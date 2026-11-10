package v;

import c0.t2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ e2.e0 f6118e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ e2.x f6119f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ boolean f6120g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ e2.n f6121h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ o0 f6122i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ e2.r f6123j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ z.e0 f6124k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ w0.n f6125l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(e2.e0 e0Var, e2.x xVar, boolean z2, e2.n nVar, o0 o0Var, e2.r rVar, z.e0 e0Var2, w0.n nVar2) {
        super(1);
        this.f6118e = e0Var;
        this.f6119f = xVar;
        this.f6120g = z2;
        this.f6121h = nVar;
        this.f6122i = o0Var;
        this.f6123j = rVar;
        this.f6124k = e0Var2;
        this.f6125l = nVar2;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        w1.i iVar = (w1.i) obj;
        y1.f fVar = this.f6118e.f2019a;
        f4.d[] dVarArr = w1.s.f6363a;
        w1.t tVar = w1.q.f6358w;
        f4.d[] dVarArr2 = w1.s.f6363a;
        f4.d dVar = dVarArr2[16];
        tVar.a(iVar, fVar);
        e2.x xVar = this.f6119f;
        long j5 = xVar.f2063b;
        w1.t tVar2 = w1.q.f6359x;
        f4.d dVar2 = dVarArr2[17];
        tVar2.a(iVar, new y1.g0(j5));
        p3.n nVar = p3.n.f5026a;
        boolean z2 = this.f6120g;
        if (!z2) {
            iVar.c(w1.q.f6344i, nVar);
        }
        w1.t tVar3 = w1.q.D;
        f4.d dVar3 = dVarArr2[23];
        tVar3.a(iVar, Boolean.valueOf(z2));
        o0 o0Var = this.f6122i;
        w1.s.c(iVar, new q(o0Var, 2));
        if (z2) {
            iVar.c(w1.h.f6289h, new w1.a(null, new q(o0Var, iVar)));
            iVar.c(w1.h.f6293l, new w1.a(null, new u(z2, o0Var, iVar, xVar)));
        }
        e2.r rVar = this.f6123j;
        boolean z3 = this.f6120g;
        z.e0 e0Var = this.f6124k;
        iVar.c(w1.h.f6288g, new w1.a(null, new v(rVar, z3, xVar, e0Var, o0Var)));
        e2.n nVar2 = this.f6121h;
        int i5 = nVar2.f2043e;
        t2 t2Var = new t2(o0Var, 10, nVar2);
        iVar.c(w1.q.f6360y, new e2.m(i5));
        iVar.c(w1.h.f6294m, new w1.a(null, t2Var));
        iVar.c(w1.h.f6283b, new w1.a(null, new t2(o0Var, 11, this.f6125l)));
        iVar.c(w1.h.f6284c, new w1.a(null, new t(e0Var, 1)));
        if (!y1.g0.b(j5)) {
            iVar.c(w1.h.f6295n, new w1.a(null, new t(e0Var, 2)));
            if (z2) {
                iVar.c(w1.h.f6296o, new w1.a(null, new t(e0Var, 3)));
            }
        }
        if (z2) {
            iVar.c(w1.h.f6297p, new w1.a(null, new t(e0Var, 0)));
        }
        return nVar;
    }
}

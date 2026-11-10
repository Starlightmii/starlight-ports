package v;

import f0.w1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ z.e0 f5961e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ o0 f5962f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ boolean f5963g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ a4.c f5964h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ e2.x f5965i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ e2.r f5966j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ k2.b f5967k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f5968l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(z.e0 e0Var, o0 o0Var, boolean z2, a4.c cVar, e2.x xVar, e2.r rVar, k2.b bVar, int i5) {
        super(2);
        this.f5961e = e0Var;
        this.f5962f = o0Var;
        this.f5963g = z2;
        this.f5964h = cVar;
        this.f5965i = xVar;
        this.f5966j = rVar;
        this.f5967k = bVar;
        this.f5968l = i5;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x004c  */
    /* JADX WARN: Code duplicated, block: B:11:0x0050  */
    /* JADX WARN: Code duplicated, block: B:16:0x0071  */
    /* JADX WARN: Code duplicated, block: B:25:0x009e  */
    /* JADX WARN: Code duplicated, block: B:30:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:8:0x001c  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        o0 o0Var;
        int iP;
        f0.v vVar;
        p1.n nVar;
        p1.h hVar;
        f0 f0VarA;
        f0 f0Var;
        boolean z2;
        f0.p pVar = (f0.p) obj;
        if ((((Number) obj2).intValue() & 3) == 2) {
            f0.v vVar2 = (f0.v) pVar;
            if (vVar2.z()) {
                vVar2.Q();
            } else {
                k2.b bVar = this.f5967k;
                int i5 = this.f5968l;
                o0Var = this.f5962f;
                l lVar = new l(o0Var, this.f5964h, this.f5965i, this.f5966j, bVar, i5);
                iP = f0.l.p(pVar);
                vVar = (f0.v) pVar;
                w1 w1VarL = vVar.l();
                r0.p pVarC = r0.a.c(pVar, r0.m.f5680a);
                p1.j.f4892c.getClass();
                nVar = p1.i.f4867b;
                vVar.Y();
                if (vVar.R) {
                    vVar.k(nVar);
                } else {
                    vVar.i0();
                }
                f0.l.x(p1.i.f4870e, pVar, lVar);
                f0.l.x(p1.i.f4869d, pVar, w1VarL);
                hVar = p1.i.f4871f;
                if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iP))) {
                    b.b.i(iP, vVar, iP, hVar);
                }
                f0.l.x(p1.i.f4868c, pVar, pVarC);
                vVar.p(true);
                f0VarA = o0Var.a();
                f0Var = f0.f5917d;
                boolean z3 = this.f5963g;
                if (f0VarA == f0Var && o0Var.c() != null) {
                    n1.p pVarC2 = o0Var.c();
                    b4.l.b(pVarC2);
                    z2 = pVarC2.N() && z3;
                }
                z.e0 e0Var = this.f5961e;
                l0.f(e0Var, z2, pVar, 0);
                if (o0Var.a() == f0.f5919f || !z3) {
                    vVar.V(-1955394);
                    vVar.p(false);
                } else {
                    vVar.V(-2032274);
                    l0.e(e0Var, pVar, 0);
                    vVar.p(false);
                }
            }
        } else {
            k2.b bVar2 = this.f5967k;
            int i6 = this.f5968l;
            o0Var = this.f5962f;
            l lVar2 = new l(o0Var, this.f5964h, this.f5965i, this.f5966j, bVar2, i6);
            iP = f0.l.p(pVar);
            vVar = (f0.v) pVar;
            w1 w1VarL2 = vVar.l();
            r0.p pVarC3 = r0.a.c(pVar, r0.m.f5680a);
            p1.j.f4892c.getClass();
            nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(p1.i.f4870e, pVar, lVar2);
            f0.l.x(p1.i.f4869d, pVar, w1VarL2);
            hVar = p1.i.f4871f;
            if (vVar.R) {
                b.b.i(iP, vVar, iP, hVar);
            } else {
                b.b.i(iP, vVar, iP, hVar);
            }
            f0.l.x(p1.i.f4868c, pVar, pVarC3);
            vVar.p(true);
            f0VarA = o0Var.a();
            f0Var = f0.f5917d;
            boolean z4 = this.f5963g;
            if (f0VarA == f0Var) {
            }
            z.e0 e0Var2 = this.f5961e;
            l0.f(e0Var2, z2, pVar, 0);
            if (o0Var.a() == f0.f5919f) {
                vVar.V(-1955394);
                vVar.p(false);
            } else {
                vVar.V(-1955394);
                vVar.p(false);
            }
        }
        return p3.n.f5026a;
    }
}

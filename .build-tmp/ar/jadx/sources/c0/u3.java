package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u3 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ r0.p f1353e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f1354f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ n.i0 f1355g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ boolean f1356h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ a4.a f1357i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ n0.d f1358j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u3(r0.p pVar, boolean z2, n.i0 i0Var, boolean z3, a4.a aVar, n0.d dVar) {
        super(2);
        this.f1353e = pVar;
        this.f1354f = z2;
        this.f1355g = i0Var;
        this.f1356h = z3;
        this.f1357i = aVar;
        this.f1358j = dVar;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x005e  */
    /* JADX WARN: Code duplicated, block: B:11:0x0062  */
    /* JADX WARN: Code duplicated, block: B:16:0x0083  */
    /* JADX WARN: Code duplicated, block: B:8:0x001c  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        int iP;
        f0.v vVar;
        p1.n nVar;
        p1.h hVar;
        f0.p pVar = (f0.p) obj;
        if ((((Number) obj2).intValue() & 3) == 2) {
            f0.v vVar2 = (f0.v) pVar;
            if (vVar2.z()) {
                vVar2.Q();
            } else {
                r0.p pVarE = androidx.compose.foundation.selection.b.a(this.f1353e, this.f1354f, null, this.f1355g, this.f1356h, new w1.f(4), this.f1357i).e(androidx.compose.foundation.layout.c.f113a);
                r.v vVarA = r.t.a(r.j.f5573c, r0.b.f5666q, pVar, 54);
                iP = f0.l.p(pVar);
                vVar = (f0.v) pVar;
                f0.w1 w1VarL = vVar.l();
                r0.p pVarC = r0.a.c(pVar, pVarE);
                p1.j.f4892c.getClass();
                nVar = p1.i.f4867b;
                vVar.Y();
                if (vVar.R) {
                    vVar.k(nVar);
                } else {
                    vVar.i0();
                }
                f0.l.x(p1.i.f4870e, pVar, vVarA);
                f0.l.x(p1.i.f4869d, pVar, w1VarL);
                hVar = p1.i.f4871f;
                if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iP))) {
                    b.b.i(iP, vVar, iP, hVar);
                }
                f0.l.x(p1.i.f4868c, pVar, pVarC);
                this.f1358j.b(r.x.f5639a, pVar, 6);
                vVar.p(true);
            }
        } else {
            r0.p pVarE2 = androidx.compose.foundation.selection.b.a(this.f1353e, this.f1354f, null, this.f1355g, this.f1356h, new w1.f(4), this.f1357i).e(androidx.compose.foundation.layout.c.f113a);
            r.v vVarA2 = r.t.a(r.j.f5573c, r0.b.f5666q, pVar, 54);
            iP = f0.l.p(pVar);
            vVar = (f0.v) pVar;
            f0.w1 w1VarL2 = vVar.l();
            r0.p pVarC2 = r0.a.c(pVar, pVarE2);
            p1.j.f4892c.getClass();
            nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(p1.i.f4870e, pVar, vVarA2);
            f0.l.x(p1.i.f4869d, pVar, w1VarL2);
            hVar = p1.i.f4871f;
            if (vVar.R) {
                b.b.i(iP, vVar, iP, hVar);
            } else {
                b.b.i(iP, vVar, iP, hVar);
            }
            f0.l.x(p1.i.f4868c, pVar, pVarC2);
            this.f1358j.b(r.x.f5639a, pVar, 6);
            vVar.p(true);
        }
        return p3.n.f5026a;
    }
}

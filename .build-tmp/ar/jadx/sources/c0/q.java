package c0;

import androidx.compose.foundation.layout.LayoutWeightElement;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1181e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ n0.d f1182f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q(n0.d dVar, int i5) {
        super(2);
        this.f1181e = i5;
        this.f1182f = dVar;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0020  */
    /* JADX WARN: Code duplicated, block: B:19:0x0048  */
    /* JADX WARN: Code duplicated, block: B:21:0x0051  */
    /* JADX WARN: Code duplicated, block: B:23:0x0083  */
    /* JADX WARN: Code duplicated, block: B:24:0x0087  */
    /* JADX WARN: Code duplicated, block: B:29:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:33:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:41:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:43:0x010b  */
    /* JADX WARN: Code duplicated, block: B:44:0x010f  */
    /* JADX WARN: Code duplicated, block: B:49:0x0130  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        int iP;
        f0.v vVar;
        p1.n nVar;
        p1.h hVar;
        int iP2;
        f0.v vVar2;
        p1.n nVar2;
        p1.h hVar2;
        switch (this.f1181e) {
            case 0:
                f0.p pVar = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar3 = (f0.v) pVar;
                    if (vVar3.z()) {
                        vVar3.Q();
                    } else {
                        r.v vVarA = r.t.a(r.j.f5572b, r0.b.f5665p, pVar, 0);
                        iP = f0.l.p(pVar);
                        vVar = (f0.v) pVar;
                        f0.w1 w1VarL = vVar.l();
                        r0.p pVarC = r0.a.c(pVar, r0.m.f5680a);
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
                        this.f1182f.b(r.x.f5639a, pVar, 6);
                        vVar.p(true);
                    }
                } else {
                    r.v vVarA2 = r.t.a(r.j.f5572b, r0.b.f5665p, pVar, 0);
                    iP = f0.l.p(pVar);
                    vVar = (f0.v) pVar;
                    f0.w1 w1VarL2 = vVar.l();
                    r0.p pVarC2 = r0.a.c(pVar, r0.m.f5680a);
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
                    this.f1182f.b(r.x.f5639a, pVar, 6);
                    vVar.p(true);
                }
                return p3.n.f5026a;
            case 1:
                f0.p pVar2 = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar4 = (f0.v) pVar2;
                    if (vVar4.z()) {
                        vVar4.Q();
                    } else {
                        if (1.0f > 0.0d) {
                            throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                        }
                        r0.p pVarI = androidx.compose.foundation.layout.a.i(new LayoutWeightElement(1.0f), 0, 0.0f, 0, 10);
                        n1.f0 f0VarE = r.o.e(r0.b.f5653d, false);
                        iP2 = f0.l.p(pVar2);
                        vVar2 = (f0.v) pVar2;
                        f0.w1 w1VarL3 = vVar2.l();
                        r0.p pVarC3 = r0.a.c(pVar2, pVarI);
                        p1.j.f4892c.getClass();
                        nVar2 = p1.i.f4867b;
                        vVar2.Y();
                        if (vVar2.R) {
                            vVar2.k(nVar2);
                        } else {
                            vVar2.i0();
                        }
                        f0.l.x(p1.i.f4870e, pVar2, f0VarE);
                        f0.l.x(p1.i.f4869d, pVar2, w1VarL3);
                        hVar2 = p1.i.f4871f;
                        if (vVar2.R || !b4.l.a(vVar2.K(), Integer.valueOf(iP2))) {
                            b.b.i(iP2, vVar2, iP2, hVar2);
                        }
                        f0.l.x(p1.i.f4868c, pVar2, pVarC3);
                        this.f1182f.invoke(pVar2, 0);
                        vVar2.p(true);
                    }
                } else {
                    if (1.0f > 0.0d) {
                        throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                    }
                    r0.p pVarI2 = androidx.compose.foundation.layout.a.i(new LayoutWeightElement(1.0f), 0, 0.0f, 0, 10);
                    n1.f0 f0VarE2 = r.o.e(r0.b.f5653d, false);
                    iP2 = f0.l.p(pVar2);
                    vVar2 = (f0.v) pVar2;
                    f0.w1 w1VarL4 = vVar2.l();
                    r0.p pVarC4 = r0.a.c(pVar2, pVarI2);
                    p1.j.f4892c.getClass();
                    nVar2 = p1.i.f4867b;
                    vVar2.Y();
                    if (vVar2.R) {
                        vVar2.k(nVar2);
                    } else {
                        vVar2.i0();
                    }
                    f0.l.x(p1.i.f4870e, pVar2, f0VarE2);
                    f0.l.x(p1.i.f4869d, pVar2, w1VarL4);
                    hVar2 = p1.i.f4871f;
                    if (vVar2.R) {
                        b.b.i(iP2, vVar2, iP2, hVar2);
                    } else {
                        b.b.i(iP2, vVar2, iP2, hVar2);
                    }
                    f0.l.x(p1.i.f4868c, pVar2, pVarC4);
                    this.f1182f.invoke(pVar2, 0);
                    vVar2.p(true);
                }
                return p3.n.f5026a;
            default:
                f0.p pVar3 = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar5 = (f0.v) pVar3;
                    if (vVar5.z()) {
                        vVar5.Q();
                    } else {
                        this.f1182f.invoke(pVar3, 0);
                    }
                } else {
                    this.f1182f.invoke(pVar3, 0);
                }
                return p3.n.f5026a;
        }
    }
}

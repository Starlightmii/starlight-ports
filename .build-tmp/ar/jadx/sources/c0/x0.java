package c0;

import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x0 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1413e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f1414f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f1415g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f1416h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x0(c3 c3Var, r0.p pVar, a4.f fVar, int i5) {
        super(2);
        this.f1413e = 1;
        this.f1415g = c3Var;
        this.f1414f = pVar;
        this.f1416h = fVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ x0(Object obj, Object obj2, a4.e eVar, int i5, int i6) {
        super(2);
        this.f1413e = i6;
        this.f1414f = obj;
        this.f1415g = obj2;
        this.f1416h = eVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ x0(Object obj, Object obj2, Object obj3, int i5) {
        super(2);
        this.f1413e = i5;
        this.f1414f = obj;
        this.f1415g = obj2;
        this.f1416h = obj3;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0064  */
    /* JADX WARN: Code duplicated, block: B:24:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:26:0x010d  */
    /* JADX WARN: Code duplicated, block: B:27:0x0111  */
    /* JADX WARN: Code duplicated, block: B:32:0x0132  */
    /* JADX WARN: Code duplicated, block: B:43:0x017c  */
    /* JADX WARN: Code duplicated, block: B:45:0x01b6  */
    /* JADX WARN: Code duplicated, block: B:46:0x01ba  */
    /* JADX WARN: Code duplicated, block: B:51:0x01db  */
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
        int i5 = this.f1413e;
        int i6 = 0;
        p3.n nVar3 = p3.n.f5026a;
        Object obj3 = this.f1416h;
        Object obj4 = this.f1415g;
        Object obj5 = this.f1414f;
        switch (i5) {
            case 0:
                f0.p pVar = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) != 2) {
                    r0.p pVarS = h1.c.S(androidx.compose.foundation.layout.a.j(androidx.compose.foundation.layout.a.h((r0.p) obj5, 0.0f, a1.f727d, 1)), (n.h1) obj4);
                    n0.d dVar = (n0.d) obj3;
                    r.v vVarA = r.t.a(r.j.f5572b, r0.b.f5665p, pVar, 0);
                    iP = f0.l.p(pVar);
                    vVar = (f0.v) pVar;
                    f0.w1 w1VarL = vVar.l();
                    r0.p pVarC = r0.a.c(pVar, pVarS);
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
                    if (vVar.R) {
                        b.b.i(iP, vVar, iP, hVar);
                    } else {
                        b.b.i(iP, vVar, iP, hVar);
                    }
                    f0.l.x(p1.i.f4868c, pVar, pVarC);
                    dVar.b(r.x.f5639a, pVar, 6);
                    vVar.p(true);
                } else {
                    f0.v vVar3 = (f0.v) pVar;
                    if (!vVar3.z()) {
                        r0.p pVarS2 = h1.c.S(androidx.compose.foundation.layout.a.j(androidx.compose.foundation.layout.a.h((r0.p) obj5, 0.0f, a1.f727d, 1)), (n.h1) obj4);
                        n0.d dVar2 = (n0.d) obj3;
                        r.v vVarA2 = r.t.a(r.j.f5572b, r0.b.f5665p, pVar, 0);
                        iP = f0.l.p(pVar);
                        vVar = (f0.v) pVar;
                        f0.w1 w1VarL2 = vVar.l();
                        r0.p pVarC2 = r0.a.c(pVar, pVarS2);
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
                        if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iP))) {
                            b.b.i(iP, vVar, iP, hVar);
                        }
                        f0.l.x(p1.i.f4868c, pVar, pVarC2);
                        dVar2.b(r.x.f5639a, pVar, 6);
                        vVar.p(true);
                    } else {
                        vVar3.Q();
                    }
                }
                break;
            case 1:
                ((Number) obj2).intValue();
                h2.f((c3) obj4, (r0.p) obj5, (a4.f) obj3, (f0.p) obj, f0.l.z(1));
                break;
            case 2:
                f0.p pVar2 = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) != 2) {
                    r0.p pVarC3 = androidx.compose.ui.layout.a.c(r0.m.f5680a, "Container");
                    float f5 = d2.f798a;
                    r0.p pVarC4 = androidx.compose.ui.draw.a.c(pVarC3, new c2(new d0.n((f0.i1) obj5, f0.i1.class, "value", "getValue()Ljava/lang/Object;", 0, 0), i6, (r.l0) obj4));
                    n0.d dVar3 = (n0.d) obj3;
                    n1.f0 f0VarE = r.o.e(r0.b.f5653d, true);
                    iP2 = f0.l.p(pVar2);
                    vVar2 = (f0.v) pVar2;
                    f0.w1 w1VarL3 = vVar2.l();
                    r0.p pVarC5 = r0.a.c(pVar2, pVarC4);
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
                    if (vVar2.R) {
                        b.b.i(iP2, vVar2, iP2, hVar2);
                    } else {
                        b.b.i(iP2, vVar2, iP2, hVar2);
                    }
                    f0.l.x(p1.i.f4868c, pVar2, pVarC5);
                    dVar3.invoke(pVar2, 0);
                    vVar2.p(true);
                } else {
                    f0.v vVar4 = (f0.v) pVar2;
                    if (!vVar4.z()) {
                        r0.p pVarC6 = androidx.compose.ui.layout.a.c(r0.m.f5680a, "Container");
                        float f6 = d2.f798a;
                        r0.p pVarC7 = androidx.compose.ui.draw.a.c(pVarC6, new c2(new d0.n((f0.i1) obj5, f0.i1.class, "value", "getValue()Ljava/lang/Object;", 0, 0), i6, (r.l0) obj4));
                        n0.d dVar4 = (n0.d) obj3;
                        n1.f0 f0VarE2 = r.o.e(r0.b.f5653d, true);
                        iP2 = f0.l.p(pVar2);
                        vVar2 = (f0.v) pVar2;
                        f0.w1 w1VarL4 = vVar2.l();
                        r0.p pVarC8 = r0.a.c(pVar2, pVarC7);
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
                        if (vVar2.R || !b4.l.a(vVar2.K(), Integer.valueOf(iP2))) {
                            b.b.i(iP2, vVar2, iP2, hVar2);
                        }
                        f0.l.x(p1.i.f4868c, pVar2, pVarC8);
                        dVar4.invoke(pVar2, 0);
                        vVar2.p(true);
                    } else {
                        vVar4.Q();
                    }
                }
                break;
            case 3:
                float fFloatValue = ((Number) obj).floatValue();
                ((Number) obj2).floatValue();
                b4.t tVar = (b4.t) obj5;
                p.i1 i1Var = (p.i1) obj4;
                long jG = i1Var.g(i1Var.c(fFloatValue - tVar.f694d));
                p.i1 i1Var2 = ((p.g1) obj3).f4466a;
                tVar.f694d += i1Var.c(i1Var.f(p.i1.a(i1Var2, i1Var2.f4491h, jG, 1)));
                break;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                f0.p pVar3 = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) != 2) {
                    q1.j1.a((q1.u) obj5, (q1.x0) obj4, (a4.e) obj3, pVar3, 0);
                } else {
                    f0.v vVar5 = (f0.v) pVar3;
                    if (!vVar5.z()) {
                        q1.j1.a((q1.u) obj5, (q1.x0) obj4, (a4.e) obj3, pVar3, 0);
                    } else {
                        vVar5.Q();
                    }
                }
                break;
            case 5:
                ((Number) obj2).intValue();
                q1.j1.a((p1.e1) obj5, (q1.x0) obj4, (a4.e) obj3, (f0.p) obj, f0.l.z(1));
                break;
            default:
                ((Number) obj2).intValue();
                v.l0.d((r0.p) obj5, (z.e0) obj4, (n0.d) obj3, (f0.p) obj, f0.l.z(385));
                break;
        }
        return nVar3;
    }
}

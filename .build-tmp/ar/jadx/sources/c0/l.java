package c0;

import androidx.compose.foundation.layout.FillElement;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import io.github.libxposed.service.IXposedService;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1012e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f1013f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f1014g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l(int i5, int i6, Object obj, Object obj2) {
        super(2);
        this.f1012e = i6;
        this.f1013f = obj;
        this.f1014g = obj2;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(a4.f fVar, ArrayList arrayList) {
        super(2);
        this.f1012e = 4;
        this.f1014g = fVar;
        this.f1013f = arrayList;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l(Object obj, int i5, Object obj2) {
        super(2);
        this.f1012e = i5;
        this.f1013f = obj;
        this.f1014g = obj2;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(o.a aVar, c2 c2Var) {
        super(2);
        this.f1012e = 10;
        this.f1013f = aVar;
        this.f1014g = c2Var;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0295  */
    /* JADX WARN: Code duplicated, block: B:110:0x02d2  */
    /* JADX WARN: Code duplicated, block: B:112:0x031b  */
    /* JADX WARN: Code duplicated, block: B:113:0x031f  */
    /* JADX WARN: Code duplicated, block: B:118:0x0340  */
    /* JADX WARN: Code duplicated, block: B:127:0x036e  */
    /* JADX WARN: Code duplicated, block: B:135:0x0393  */
    /* JADX WARN: Code duplicated, block: B:137:0x03c8  */
    /* JADX WARN: Code duplicated, block: B:138:0x03cc  */
    /* JADX WARN: Code duplicated, block: B:143:0x03ed  */
    /* JADX WARN: Code duplicated, block: B:18:0x007c  */
    /* JADX WARN: Code duplicated, block: B:30:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:33:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:36:0x0104  */
    /* JADX WARN: Code duplicated, block: B:39:0x0134  */
    /* JADX WARN: Code duplicated, block: B:40:0x0138  */
    /* JADX WARN: Code duplicated, block: B:45:0x015b  */
    /* JADX WARN: Code duplicated, block: B:54:0x018a  */
    /* JADX WARN: Code duplicated, block: B:65:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:66:0x01db  */
    /* JADX WARN: Code duplicated, block: B:69:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:72:0x01e9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:73:0x01eb  */
    /* JADX WARN: Code duplicated, block: B:74:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:90:0x0258  */
    /* JADX WARN: Code duplicated, block: B:98:0x0281  */
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
        f0.v vVar3;
        boolean zF;
        Object objK;
        boolean zBooleanValue;
        a4.e eVar;
        f0.v vVar4;
        boolean zG;
        f0.v vVar5;
        boolean zH;
        Object objK2;
        int iHashCode;
        p1.n nVar3;
        p1.h hVar3;
        int i5 = this.f1012e;
        r.u0 u0Var = r.u0.f5629a;
        f0.h hVar4 = f0.o.f2281a;
        r0.m mVar = r0.m.f5680a;
        int i6 = 0;
        int i7 = 2;
        int i8 = 3;
        int i9 = 1;
        p3.n nVar4 = p3.n.f5026a;
        Object obj3 = this.f1014g;
        Object obj4 = this.f1013f;
        switch (i5) {
            case 0:
                f0.p pVar = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) != 2) {
                    r0.p pVarE = androidx.compose.foundation.layout.a.e(androidx.compose.foundation.layout.c.a(mVar, g.f866b, g.f867c), (r.l0) obj4);
                    a4.f fVar = (a4.f) obj3;
                    r.s0 s0VarA = r.q0.a(r.j.f5573c, r0.b.f5663n, pVar, 54);
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
                    f0.l.x(p1.i.f4870e, pVar, s0VarA);
                    f0.l.x(p1.i.f4869d, pVar, w1VarL);
                    hVar = p1.i.f4871f;
                    if (vVar.R) {
                        b.b.i(iP, vVar, iP, hVar);
                    } else {
                        b.b.i(iP, vVar, iP, hVar);
                    }
                    f0.l.x(p1.i.f4868c, pVar, pVarC);
                    fVar.b(u0Var, pVar, 6);
                    vVar.p(true);
                } else {
                    f0.v vVar6 = (f0.v) pVar;
                    if (!vVar6.z()) {
                        r0.p pVarE2 = androidx.compose.foundation.layout.a.e(androidx.compose.foundation.layout.c.a(mVar, g.f866b, g.f867c), (r.l0) obj4);
                        a4.f fVar2 = (a4.f) obj3;
                        r.s0 s0VarA2 = r.q0.a(r.j.f5573c, r0.b.f5663n, pVar, 54);
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
                        f0.l.x(p1.i.f4870e, pVar, s0VarA2);
                        f0.l.x(p1.i.f4869d, pVar, w1VarL2);
                        hVar = p1.i.f4871f;
                        if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iP))) {
                            b.b.i(iP, vVar, iP, hVar);
                        }
                        f0.l.x(p1.i.f4868c, pVar, pVarC2);
                        fVar2.b(u0Var, pVar, 6);
                        vVar.p(true);
                    } else {
                        vVar6.Q();
                    }
                }
                break;
            case 1:
                f0.p pVar2 = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) != 2) {
                    n4.a(((s4) obj4).f1289j, (a4.e) obj3, pVar2, 0);
                } else {
                    f0.v vVar7 = (f0.v) pVar2;
                    if (!vVar7.z()) {
                        n4.a(((s4) obj4).f1289j, (a4.e) obj3, pVar2, 0);
                    } else {
                        vVar7.Q();
                    }
                }
                break;
            case 2:
                f0.p pVar3 = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) != 2) {
                    FillElement fillElement = androidx.compose.foundation.layout.c.f113a;
                    o1.g gVar = r.c1.f5542a;
                    r0.p pVarA = w1.k.a(androidx.compose.foundation.layout.c.b(r0.a.a(fillElement, new s3(i8, (r.z0) obj4)), 0.0f, r1.f1219a, 1), t.a.f5776e);
                    r.b bVar = r.j.f5571a;
                    n0.d dVar = (n0.d) obj3;
                    r.s0 s0VarA3 = r.q0.a(new r.f(r1.f1220b), r0.b.f5663n, pVar3, 54);
                    iP2 = f0.l.p(pVar3);
                    vVar2 = (f0.v) pVar3;
                    f0.w1 w1VarL3 = vVar2.l();
                    r0.p pVarC3 = r0.a.c(pVar3, pVarA);
                    p1.j.f4892c.getClass();
                    nVar2 = p1.i.f4867b;
                    vVar2.Y();
                    if (vVar2.R) {
                        vVar2.k(nVar2);
                    } else {
                        vVar2.i0();
                    }
                    f0.l.x(p1.i.f4870e, pVar3, s0VarA3);
                    f0.l.x(p1.i.f4869d, pVar3, w1VarL3);
                    hVar2 = p1.i.f4871f;
                    if (vVar2.R) {
                        b.b.i(iP2, vVar2, iP2, hVar2);
                    } else {
                        b.b.i(iP2, vVar2, iP2, hVar2);
                    }
                    f0.l.x(p1.i.f4868c, pVar3, pVarC3);
                    dVar.b(u0Var, pVar3, 6);
                    vVar2.p(true);
                } else {
                    f0.v vVar8 = (f0.v) pVar3;
                    if (!vVar8.z()) {
                        FillElement fillElement2 = androidx.compose.foundation.layout.c.f113a;
                        o1.g gVar2 = r.c1.f5542a;
                        r0.p pVarA2 = w1.k.a(androidx.compose.foundation.layout.c.b(r0.a.a(fillElement2, new s3(i8, (r.z0) obj4)), 0.0f, r1.f1219a, 1), t.a.f5776e);
                        r.b bVar2 = r.j.f5571a;
                        n0.d dVar2 = (n0.d) obj3;
                        r.s0 s0VarA4 = r.q0.a(new r.f(r1.f1220b), r0.b.f5663n, pVar3, 54);
                        iP2 = f0.l.p(pVar3);
                        vVar2 = (f0.v) pVar3;
                        f0.w1 w1VarL4 = vVar2.l();
                        r0.p pVarC4 = r0.a.c(pVar3, pVarA2);
                        p1.j.f4892c.getClass();
                        nVar2 = p1.i.f4867b;
                        vVar2.Y();
                        if (vVar2.R) {
                            vVar2.k(nVar2);
                        } else {
                            vVar2.i0();
                        }
                        f0.l.x(p1.i.f4870e, pVar3, s0VarA4);
                        f0.l.x(p1.i.f4869d, pVar3, w1VarL4);
                        hVar2 = p1.i.f4871f;
                        if (vVar2.R || !b4.l.a(vVar2.K(), Integer.valueOf(iP2))) {
                            b.b.i(iP2, vVar2, iP2, hVar2);
                        }
                        f0.l.x(p1.i.f4868c, pVar3, pVarC4);
                        dVar2.b(u0Var, pVar3, 6);
                        vVar2.p(true);
                    } else {
                        vVar8.Q();
                    }
                }
                break;
            case 3:
                f0.p pVar4 = (f0.p) obj;
                f0.g3 g3Var = (f0.g3) obj4;
                if ((((Number) obj2).intValue() & 3) != 2) {
                    r0.p pVarC5 = androidx.compose.ui.layout.a.c(mVar, "indicator");
                    vVar3 = (f0.v) pVar4;
                    zF = vVar3.f(g3Var);
                    objK = vVar3.K();
                    if (zF) {
                        objK = new i1(g3Var, i6);
                        vVar3.f0(objK);
                    } else {
                        objK = new i1(g3Var, i6);
                        vVar3.f0(objK);
                    }
                    r0.p pVarA3 = androidx.compose.ui.graphics.a.a(pVarC5, (a4.c) objK);
                    long j5 = ((e1) obj3).f817c;
                    float f5 = e0.j.f1736a;
                    r.o.a(androidx.compose.foundation.a.b(pVarA3, j5, s2.a(vVar3, 5)), vVar3, 0);
                } else {
                    f0.v vVar9 = (f0.v) pVar4;
                    if (!vVar9.z()) {
                        r0.p pVarC6 = androidx.compose.ui.layout.a.c(mVar, "indicator");
                        vVar3 = (f0.v) pVar4;
                        zF = vVar3.f(g3Var);
                        objK = vVar3.K();
                        if (zF || objK == hVar4) {
                            objK = new i1(g3Var, i6);
                            vVar3.f0(objK);
                        }
                        r0.p pVarA4 = androidx.compose.ui.graphics.a.a(pVarC6, (a4.c) objK);
                        long j6 = ((e1) obj3).f817c;
                        float f6 = e0.j.f1736a;
                        r.o.a(androidx.compose.foundation.a.b(pVarA4, j6, s2.a(vVar3, 5)), vVar3, 0);
                    } else {
                        vVar9.Q();
                    }
                }
                break;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                f0.p pVar5 = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) != 2) {
                    ((a4.f) obj3).b((ArrayList) obj4, pVar5, 0);
                } else {
                    f0.v vVar10 = (f0.v) pVar5;
                    if (!vVar10.z()) {
                        ((a4.f) obj3).b((ArrayList) obj4, pVar5, 0);
                    } else {
                        vVar10.Q();
                    }
                }
                break;
            case 5:
                ((Number) obj2).intValue();
                a.a.a((r0.p) obj4, (a4.c) obj3, (f0.p) obj, f0.l.z(1));
                break;
            case 6:
                f0.p pVar6 = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) != 2) {
                    Boolean bool = (Boolean) ((n1.t) obj4).f4159f.getValue();
                    zBooleanValue = bool.booleanValue();
                    eVar = (a4.e) obj3;
                    vVar4 = (f0.v) pVar6;
                    if (!vVar4.R) {
                        vVar4.f2379z = vVar4.G.f2311g;
                        vVar4.f2378y = true;
                    }
                    vVar4.R(207, 0, null, bool);
                    zG = vVar4.g(zBooleanValue);
                    vVar4.V(-869707859);
                    if (zBooleanValue) {
                        eVar.invoke(pVar6, 0);
                    } else {
                        if (vVar4.f2365l != 0) {
                            f0.z.a("No nodes can be emitted before calling deactivateToEndGroup");
                        }
                        if (!vVar4.R) {
                            if (zG) {
                                f0.s2 s2Var = vVar4.G;
                                int i10 = s2Var.f2311g;
                                int i11 = s2Var.f2312h;
                                g0.b bVar3 = vVar4.M;
                                bVar3.getClass();
                                bVar3.d(false);
                                bVar3.f2457b.f2454k.S(g0.i.f2482c);
                                f0.l.h(vVar4.f2372s, i10, i11);
                                vVar4.G.t();
                            } else {
                                vVar4.P();
                            }
                        }
                    }
                    vVar4.p(false);
                    if (vVar4.f2378y) {
                        vVar4.f2379z = -1;
                        vVar4.f2378y = false;
                    }
                    vVar4.p(false);
                } else {
                    f0.v vVar11 = (f0.v) pVar6;
                    if (!vVar11.z()) {
                        Boolean bool2 = (Boolean) ((n1.t) obj4).f4159f.getValue();
                        zBooleanValue = bool2.booleanValue();
                        eVar = (a4.e) obj3;
                        vVar4 = (f0.v) pVar6;
                        if (!vVar4.R && vVar4.G.g() == 207 && !b4.l.a(vVar4.G.f(), bool2) && vVar4.f2379z < 0) {
                            vVar4.f2379z = vVar4.G.f2311g;
                            vVar4.f2378y = true;
                        }
                        vVar4.R(207, 0, null, bool2);
                        zG = vVar4.g(zBooleanValue);
                        vVar4.V(-869707859);
                        if (zBooleanValue) {
                            eVar.invoke(pVar6, 0);
                        } else {
                            if (vVar4.f2365l != 0) {
                                f0.z.a("No nodes can be emitted before calling deactivateToEndGroup");
                            }
                            if (!vVar4.R) {
                                if (zG) {
                                    vVar4.P();
                                } else {
                                    f0.s2 s2Var2 = vVar4.G;
                                    int i12 = s2Var2.f2311g;
                                    int i13 = s2Var2.f2312h;
                                    g0.b bVar4 = vVar4.M;
                                    bVar4.getClass();
                                    bVar4.d(false);
                                    bVar4.f2457b.f2454k.S(g0.i.f2482c);
                                    f0.l.h(vVar4.f2372s, i12, i13);
                                    vVar4.G.t();
                                }
                            }
                        }
                        vVar4.p(false);
                        if (vVar4.f2378y && vVar4.G.f2313i == vVar4.f2379z) {
                            vVar4.f2379z = -1;
                            vVar4.f2378y = false;
                        }
                        vVar4.p(false);
                    } else {
                        vVar11.Q();
                    }
                }
                break;
            case 7:
                f0.p pVar7 = (f0.p) obj;
                n2.p pVar8 = (n2.p) obj4;
                if ((((Number) obj2).intValue() & 3) != 2) {
                    r0.p pVarA5 = w1.k.a(mVar, n2.e.f4211h);
                    vVar5 = (f0.v) pVar7;
                    zH = vVar5.h(pVar8);
                    objK2 = vVar5.K();
                    if (zH) {
                        objK2 = new n2.f(pVar8, 1);
                        vVar5.f0(objK2);
                    } else {
                        objK2 = new n2.f(pVar8, 1);
                        vVar5.f0(objK2);
                    }
                    r0.p pVarG = p3.p.g(androidx.compose.ui.layout.a.e(pVarA5, (a4.c) objK2), pVar8.getCanCalculatePosition() ? 1.0f : 0.0f);
                    n0.d dVarC = n0.i.c(606497925, new c((f0.i1) obj3, i9), vVar5);
                    iHashCode = Long.hashCode(vVar5.S);
                    f0.w1 w1VarL5 = vVar5.l();
                    r0.p pVarC7 = r0.a.c(vVar5, pVarG);
                    p1.j.f4892c.getClass();
                    nVar3 = p1.i.f4867b;
                    vVar5.Y();
                    if (vVar5.R) {
                        vVar5.k(nVar3);
                    } else {
                        vVar5.i0();
                    }
                    f0.l.x(p1.i.f4870e, vVar5, n2.i.f4225a);
                    f0.l.x(p1.i.f4869d, vVar5, w1VarL5);
                    hVar3 = p1.i.f4871f;
                    if (vVar5.R) {
                        b.b.i(iHashCode, vVar5, iHashCode, hVar3);
                    } else {
                        b.b.i(iHashCode, vVar5, iHashCode, hVar3);
                    }
                    f0.l.x(p1.i.f4868c, vVar5, pVarC7);
                    dVarC.invoke(vVar5, 6);
                    vVar5.p(true);
                } else {
                    f0.v vVar12 = (f0.v) pVar7;
                    if (!vVar12.z()) {
                        r0.p pVarA6 = w1.k.a(mVar, n2.e.f4211h);
                        vVar5 = (f0.v) pVar7;
                        zH = vVar5.h(pVar8);
                        objK2 = vVar5.K();
                        if (zH || objK2 == hVar4) {
                            objK2 = new n2.f(pVar8, 1);
                            vVar5.f0(objK2);
                        }
                        r0.p pVarG2 = p3.p.g(androidx.compose.ui.layout.a.e(pVarA6, (a4.c) objK2), pVar8.getCanCalculatePosition() ? 1.0f : 0.0f);
                        n0.d dVarC2 = n0.i.c(606497925, new c((f0.i1) obj3, i9), vVar5);
                        iHashCode = Long.hashCode(vVar5.S);
                        f0.w1 w1VarL6 = vVar5.l();
                        r0.p pVarC8 = r0.a.c(vVar5, pVarG2);
                        p1.j.f4892c.getClass();
                        nVar3 = p1.i.f4867b;
                        vVar5.Y();
                        if (vVar5.R) {
                            vVar5.k(nVar3);
                        } else {
                            vVar5.i0();
                        }
                        f0.l.x(p1.i.f4870e, vVar5, n2.i.f4225a);
                        f0.l.x(p1.i.f4869d, vVar5, w1VarL6);
                        hVar3 = p1.i.f4871f;
                        if (vVar5.R || !b4.l.a(vVar5.K(), Integer.valueOf(iHashCode))) {
                            b.b.i(iHashCode, vVar5, iHashCode, hVar3);
                        }
                        f0.l.x(p1.i.f4868c, vVar5, pVarC8);
                        dVarC2.invoke(vVar5, 6);
                        vVar5.p(true);
                    } else {
                        vVar12.Q();
                    }
                }
                break;
            case 8:
                ((Number) obj2).intValue();
                ((o.e) obj4).a((o.a) obj3, (f0.p) obj, f0.l.z(1));
                break;
            case 9:
                ((Number) obj2).intValue();
                o.m.a((o.a) obj4, (n0.d) obj3, (f0.p) obj, f0.l.z(385));
                break;
            case 10:
                f0.p pVar9 = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) != 2) {
                    o.a aVar = (o.a) obj4;
                    o.m.a(aVar, n0.i.c(1156688164, new h2.b((c2) obj3, i7, aVar), pVar9), pVar9, 384);
                } else {
                    f0.v vVar13 = (f0.v) pVar9;
                    if (!vVar13.z()) {
                        o.a aVar2 = (o.a) obj4;
                        o.m.a(aVar2, n0.i.c(1156688164, new h2.b((c2) obj3, i7, aVar2), pVar9), pVar9, 384);
                    } else {
                        vVar13.Q();
                    }
                }
                break;
            case 11:
                long j7 = ((x0.c) obj2).f6532a;
                a.a.g((k1.c) obj4, (j1.u) obj);
                k4.c cVar = ((p.a1) obj3).f4411w;
                if (cVar != null) {
                    cVar.o(new p.p(j7));
                }
                break;
            case 12:
                ((Number) obj2).intValue();
                AndroidCompositionLocals_androidKt.a((q1.u) obj4, (a4.e) obj3, (f0.p) obj, f0.l.z(1));
                break;
            default:
                ((Number) obj2).intValue();
                p3.j.e((r0.p) obj4, (n0.d) obj3, (f0.p) obj, f0.l.z(49));
                break;
        }
        return nVar4;
    }
}

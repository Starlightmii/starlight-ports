package c0;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u2 extends b4.m implements a4.f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1348e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f1349f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f1350g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f1351h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f1352i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ u2(Object obj, Object obj2, Object obj3, Object obj4, int i5) {
        super(3);
        this.f1348e = i5;
        this.f1349f = obj;
        this.f1350g = obj2;
        this.f1351h = obj3;
        this.f1352i = obj4;
    }

    /* JADX WARN: Code duplicated, block: B:103:0x014d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:43:0x011b  */
    /* JADX WARN: Code duplicated, block: B:45:0x0127  */
    /* JADX WARN: Code duplicated, block: B:46:0x012b  */
    /* JADX WARN: Code duplicated, block: B:49:0x0130  */
    /* JADX WARN: Code duplicated, block: B:51:0x0144  */
    /* JADX WARN: Code duplicated, block: B:53:0x014a  */
    /* JADX WARN: Code duplicated, block: B:58:0x0157  */
    /* JADX WARN: Code duplicated, block: B:62:0x0174  */
    /* JADX WARN: Code duplicated, block: B:65:0x0187 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:66:0x0189  */
    /* JADX WARN: Code duplicated, block: B:67:0x018c  */
    /* JADX WARN: Code duplicated, block: B:72:0x01b8  */
    /* JADX WARN: Code duplicated, block: B:77:0x01e4 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:79:0x01e7  */
    /* JADX WARN: Code duplicated, block: B:84:0x020d  */
    /* JADX WARN: Code duplicated, block: B:88:0x025c  */
    /* JADX WARN: Code duplicated, block: B:91:0x028d  */
    /* JADX WARN: Code duplicated, block: B:92:0x0291  */
    /* JADX WARN: Code duplicated, block: B:97:0x02b2  */
    /* JADX WARN: Instruction removed from duplicated block: B:43:0x011b, please report this as an issue */
    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        boolean zA;
        int i5;
        int i6;
        m.z0 z0Var;
        f0.v vVar;
        boolean zF;
        Object objK;
        f0.h hVar;
        a4.a aVar;
        Object objK2;
        m.c cVar;
        Boolean boolValueOf;
        boolean zH;
        Object x2Var;
        Boolean bool;
        m.c cVar2;
        m.z0 z0Var2;
        Object objK3;
        m.c cVar3;
        boolean zH2;
        Object objK4;
        boolean zF2;
        Object objK5;
        int iHashCode;
        p1.n nVar;
        p1.h hVar2;
        float f5;
        ArrayList arrayList;
        ArrayList arrayList2;
        int size;
        int i7;
        Object obj4;
        Object objC;
        switch (this.f1348e) {
            case 0:
                a4.e eVar = (a4.e) obj;
                f0.p pVar = (f0.p) obj2;
                int iIntValue = ((Number) obj3).intValue();
                k0 k0Var = (k0) this.f1352i;
                z2 z2Var = (z2) this.f1349f;
                if ((iIntValue & 6) == 0) {
                    iIntValue |= ((f0.v) pVar).h(eVar) ? 4 : 2;
                }
                if ((iIntValue & 19) == 18) {
                    f0.v vVar2 = (f0.v) pVar;
                    if (vVar2.z()) {
                        vVar2.Q();
                    } else {
                        zA = b4.l.a(z2Var, (z2) this.f1350g);
                        if (zA) {
                            i5 = 150;
                        } else {
                            i5 = 75;
                        }
                        if (zA) {
                            arrayList = (ArrayList) this.f1351h;
                            arrayList2 = new ArrayList(arrayList.size());
                            size = arrayList.size();
                            for (i7 = 0; i7 < size; i7++) {
                                obj4 = arrayList.get(i7);
                                if (obj4 != null) {
                                    arrayList2.add(obj4);
                                }
                            }
                            i6 = arrayList2.size() == 1 ? 0 : 75;
                        }
                        z0Var = new m.z0(i5, i6, m.w.f3799c);
                        vVar = (f0.v) pVar;
                        zF = vVar.f(z2Var) | vVar.h(k0Var);
                        objK = vVar.K();
                        hVar = f0.o.f2281a;
                        if (zF || objK == hVar) {
                            objK = new t2(z2Var, 0, k0Var);
                            vVar.f0(objK);
                        }
                        aVar = (a4.a) objK;
                        objK2 = vVar.K();
                        if (objK2 == hVar) {
                            if (zA) {
                                f5 = 0.0f;
                            } else {
                                f5 = 1.0f;
                            }
                            objK2 = m.d.a(f5);
                            vVar.f0(objK2);
                        }
                        cVar = (m.c) objK2;
                        boolValueOf = Boolean.valueOf(zA);
                        zH = vVar.h(cVar) | vVar.g(zA) | vVar.h(z0Var) | vVar.f(aVar);
                        Object objK6 = vVar.K();
                        if (!zH || objK6 == hVar) {
                            bool = boolValueOf;
                            cVar2 = cVar;
                            x2Var = new x2(cVar2, zA, z0Var, aVar, null);
                            vVar.f0(x2Var);
                        } else {
                            bool = boolValueOf;
                            x2Var = objK6;
                            cVar2 = cVar;
                        }
                        f0.l.e((a4.e) x2Var, vVar, bool);
                        m.l lVar = cVar2.f3631c;
                        z0Var2 = new m.z0(i5, i6, m.w.f3797a);
                        objK3 = vVar.K();
                        if (objK3 == hVar) {
                            objK3 = m.d.a(zA ? 0.8f : 1.0f);
                            vVar.f0(objK3);
                        }
                        cVar3 = (m.c) objK3;
                        Boolean boolValueOf2 = Boolean.valueOf(zA);
                        zH2 = vVar.h(cVar3) | vVar.g(zA) | vVar.h(z0Var2);
                        objK4 = vVar.K();
                        if (zH2 || objK4 == hVar) {
                            objK4 = new y2(cVar3, zA, z0Var2, null);
                            vVar.f0(objK4);
                        }
                        f0.l.e((a4.e) objK4, vVar, boolValueOf2);
                        m.l lVar2 = cVar3.f3631c;
                        r0.p pVarB = androidx.compose.ui.graphics.a.b(r0.m.f5680a, ((Number) lVar2.f3696e.getValue()).floatValue(), ((Number) lVar2.f3696e.getValue()).floatValue(), ((Number) lVar.f3696e.getValue()).floatValue(), 0.0f, null, false, 131064);
                        zF2 = vVar.f(z2Var);
                        objK5 = vVar.K();
                        if (zF2 || objK5 == hVar) {
                            objK5 = new g1(1, z2Var);
                            vVar.f0(objK5);
                        }
                        r0.p pVarA = w1.k.a(pVarB, (a4.c) objK5);
                        n1.f0 f0VarE = r.o.e(r0.b.f5653d, false);
                        iHashCode = Long.hashCode(vVar.S);
                        f0.w1 w1VarL = vVar.l();
                        r0.p pVarC = r0.a.c(vVar, pVarA);
                        p1.j.f4892c.getClass();
                        nVar = p1.i.f4867b;
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(nVar);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(p1.i.f4870e, vVar, f0VarE);
                        f0.l.x(p1.i.f4869d, vVar, w1VarL);
                        hVar2 = p1.i.f4871f;
                        if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                            b.b.i(iHashCode, vVar, iHashCode, hVar2);
                        }
                        f0.l.x(p1.i.f4868c, vVar, pVarC);
                        eVar.invoke(vVar, Integer.valueOf(iIntValue & 14));
                        vVar.p(true);
                    }
                } else {
                    zA = b4.l.a(z2Var, (z2) this.f1350g);
                    if (zA) {
                        i5 = 150;
                    } else {
                        i5 = 75;
                    }
                    if (zA) {
                        arrayList = (ArrayList) this.f1351h;
                        arrayList2 = new ArrayList(arrayList.size());
                        size = arrayList.size();
                        while (i7 < size) {
                            obj4 = arrayList.get(i7);
                            if (obj4 != null) {
                                arrayList2.add(obj4);
                            }
                        }
                        if (arrayList2.size() == 1) {
                        }
                    }
                    z0Var = new m.z0(i5, i6, m.w.f3799c);
                    vVar = (f0.v) pVar;
                    zF = vVar.f(z2Var) | vVar.h(k0Var);
                    objK = vVar.K();
                    hVar = f0.o.f2281a;
                    if (zF) {
                        objK = new t2(z2Var, 0, k0Var);
                        vVar.f0(objK);
                    } else {
                        objK = new t2(z2Var, 0, k0Var);
                        vVar.f0(objK);
                    }
                    aVar = (a4.a) objK;
                    objK2 = vVar.K();
                    if (objK2 == hVar) {
                        if (zA) {
                            f5 = 1.0f;
                        } else {
                            f5 = 0.0f;
                        }
                        objK2 = m.d.a(f5);
                        vVar.f0(objK2);
                    }
                    cVar = (m.c) objK2;
                    boolValueOf = Boolean.valueOf(zA);
                    zH = vVar.h(cVar) | vVar.g(zA) | vVar.h(z0Var) | vVar.f(aVar);
                    Object objK7 = vVar.K();
                    if (zH) {
                        bool = boolValueOf;
                        cVar2 = cVar;
                        x2Var = new x2(cVar2, zA, z0Var, aVar, null);
                        vVar.f0(x2Var);
                    } else {
                        bool = boolValueOf;
                        cVar2 = cVar;
                        x2Var = new x2(cVar2, zA, z0Var, aVar, null);
                        vVar.f0(x2Var);
                    }
                    f0.l.e((a4.e) x2Var, vVar, bool);
                    m.l lVar3 = cVar2.f3631c;
                    z0Var2 = new m.z0(i5, i6, m.w.f3797a);
                    objK3 = vVar.K();
                    if (objK3 == hVar) {
                        objK3 = m.d.a(zA ? 0.8f : 1.0f);
                        vVar.f0(objK3);
                    }
                    cVar3 = (m.c) objK3;
                    Boolean boolValueOf3 = Boolean.valueOf(zA);
                    zH2 = vVar.h(cVar3) | vVar.g(zA) | vVar.h(z0Var2);
                    objK4 = vVar.K();
                    if (zH2) {
                        objK4 = new y2(cVar3, zA, z0Var2, null);
                        vVar.f0(objK4);
                    } else {
                        objK4 = new y2(cVar3, zA, z0Var2, null);
                        vVar.f0(objK4);
                    }
                    f0.l.e((a4.e) objK4, vVar, boolValueOf3);
                    m.l lVar4 = cVar3.f3631c;
                    r0.p pVarB2 = androidx.compose.ui.graphics.a.b(r0.m.f5680a, ((Number) lVar4.f3696e.getValue()).floatValue(), ((Number) lVar4.f3696e.getValue()).floatValue(), ((Number) lVar3.f3696e.getValue()).floatValue(), 0.0f, null, false, 131064);
                    zF2 = vVar.f(z2Var);
                    objK5 = vVar.K();
                    if (zF2) {
                        objK5 = new g1(1, z2Var);
                        vVar.f0(objK5);
                    } else {
                        objK5 = new g1(1, z2Var);
                        vVar.f0(objK5);
                    }
                    r0.p pVarA2 = w1.k.a(pVarB2, (a4.c) objK5);
                    n1.f0 f0VarE2 = r.o.e(r0.b.f5653d, false);
                    iHashCode = Long.hashCode(vVar.S);
                    f0.w1 w1VarL2 = vVar.l();
                    r0.p pVarC2 = r0.a.c(vVar, pVarA2);
                    p1.j.f4892c.getClass();
                    nVar = p1.i.f4867b;
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(nVar);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(p1.i.f4870e, vVar, f0VarE2);
                    f0.l.x(p1.i.f4869d, vVar, w1VarL2);
                    hVar2 = p1.i.f4871f;
                    if (vVar.R) {
                        b.b.i(iHashCode, vVar, iHashCode, hVar2);
                    } else {
                        b.b.i(iHashCode, vVar, iHashCode, hVar2);
                    }
                    f0.l.x(p1.i.f4868c, vVar, pVarC2);
                    eVar.invoke(vVar, Integer.valueOf(iIntValue & 14));
                    vVar.p(true);
                }
                return p3.n.f5026a;
            default:
                e2.r rVar = (e2.r) this.f1352i;
                r0.p pVar2 = (r0.p) obj;
                ((Number) obj3).intValue();
                v.o0 o0Var = (v.o0) this.f1350g;
                y0.s0 s0Var = (y0.s0) this.f1349f;
                e2.x xVar = (e2.x) this.f1351h;
                long j5 = xVar.f2063b;
                f0.v vVar3 = (f0.v) ((f0.p) obj2);
                vVar3.V(-84507373);
                Object objK8 = vVar3.K();
                f0.h hVar3 = f0.o.f2281a;
                if (objK8 == hVar3) {
                    objK8 = new x.j();
                    vVar3.f0(objK8);
                }
                x.j jVar = (x.j) objK8;
                boolean z2 = s0Var.f6684a != 16;
                if (((q1.v2) ((q1.u2) vVar3.j(q1.j1.f5249r))).a() && o0Var.b() && y1.g0.b(j5) && z2) {
                    vVar3.V(808320157);
                    y1.f fVar = xVar.f2062a;
                    y1.g0 g0Var = new y1.g0(j5);
                    boolean zH3 = vVar3.h(jVar);
                    Object objK9 = vVar3.K();
                    if (zH3 || objK9 == hVar3) {
                        objK9 = new q4(jVar, null, 5);
                        vVar3.f0(objK9);
                    }
                    f0.l.f(fVar, g0Var, (a4.e) objK9, vVar3);
                    boolean zH4 = vVar3.h(jVar) | vVar3.h(rVar) | vVar3.f(xVar) | vVar3.h(o0Var) | vVar3.f(s0Var);
                    Object objK10 = vVar3.K();
                    if (zH4 || objK10 == hVar3) {
                        n2.b bVar = new n2.b(jVar, rVar, xVar, o0Var, s0Var, 1);
                        vVar3.f0(bVar);
                        objK10 = bVar;
                    }
                    objC = androidx.compose.ui.draw.a.c(pVar2, (a4.c) objK10);
                    vVar3.p(false);
                } else {
                    vVar3.V(809534830);
                    vVar3.p(false);
                    objC = r0.m.f5680a;
                }
                vVar3.p(false);
                return objC;
        }
    }
}

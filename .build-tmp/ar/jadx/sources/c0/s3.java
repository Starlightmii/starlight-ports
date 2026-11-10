package c0;

import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s3 extends b4.m implements a4.f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1278e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f1279f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ s3(int i5, Object obj) {
        super(3);
        this.f1278e = i5;
        this.f1279f = obj;
    }

    /* JADX WARN: Code duplicated, block: B:81:0x029f  */
    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        int i5 = this.f1278e;
        int i6 = 4;
        int i7 = 1;
        f0.h hVar = f0.o.f2281a;
        Object obj4 = this.f1279f;
        switch (i5) {
            case 0:
                f0.p pVar = (f0.p) obj2;
                if ((((Number) obj3).intValue() & 17) == 16) {
                    f0.v vVar = (f0.v) pVar;
                    if (vVar.z()) {
                        vVar.Q();
                    } else {
                        a4.d((a4.e) obj4, pVar, 0);
                    }
                } else {
                    a4.d((a4.e) obj4, pVar, 0);
                }
                return p3.n.f5026a;
            case 1:
                ((Number) obj3).intValue();
                f0.v vVar2 = (f0.v) ((f0.p) obj2);
                vVar2.V(-1541271084);
                b4 b4Var = (b4) obj4;
                float f5 = b4Var.f775b;
                m.z0 z0Var = i4.f934a;
                f0.g3 g3VarA = m.f.a(f5, z0Var, vVar2, 0);
                f0.g3 g3VarA2 = m.f.a(b4Var.f774a, z0Var, vVar2, 0);
                r0.p pVarM = androidx.compose.foundation.layout.c.m(((r0.p) obj).e(androidx.compose.foundation.layout.c.f113a), r0.b.f5659j);
                boolean zF = vVar2.f(g3VarA2);
                Object objK = vVar2.K();
                if (zF || objK == hVar) {
                    objK = new i1(g3VarA2, i7);
                    vVar2.f0(objK);
                }
                r0.p pVarK = androidx.compose.foundation.layout.c.k(androidx.compose.foundation.layout.a.c(pVarM, (a4.c) objK), ((k2.e) g3VarA.getValue()).f3406d);
                vVar2.p(false);
                return pVarK;
            case 2:
                ((Number) obj3).intValue();
                f0.v vVar3 = (f0.v) ((f0.p) obj2);
                vVar3.V(-1608161351);
                a4.c cVar = (a4.c) obj4;
                boolean zF2 = vVar3.f(cVar);
                Object objK2 = vVar3.K();
                if (zF2 || objK2 == hVar) {
                    objK2 = new r.y(cVar);
                    vVar3.f0(objK2);
                }
                r.y yVar = (r.y) objK2;
                vVar3.p(false);
                return yVar;
            case 3:
                ((Number) obj3).intValue();
                f0.v vVar4 = (f0.v) ((f0.p) obj2);
                vVar4.V(-1415685722);
                r.z0 z0Var2 = (r.z0) obj4;
                boolean zF3 = vVar4.f(z0Var2);
                Object objK3 = vVar4.K();
                if (zF3 || objK3 == hVar) {
                    objK3 = new r.d0(z0Var2);
                    vVar4.f0(objK3);
                }
                r.d0 d0Var = (r.d0) objK3;
                vVar4.p(false);
                return d0Var;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                long j5 = ((k2.a) obj3).f3400a;
                long j6 = ((v.d1) obj4).f5882f;
                n1.m0 m0VarC = ((n1.e0) obj2).c(k2.a.b(j5, d4.a.m((int) (j6 >> 32), k2.a.k(j5), k2.a.i(j5)), 0, d4.a.m((int) (j6 & 4294967295L), k2.a.j(j5), k2.a.h(j5)), 0, 10));
                return ((n1.h0) obj).F(m0VarC.f4130d, m0VarC.f4131e, q3.t.f5478d, new n1.p0(m0VarC, 7));
            case 5:
                ((Number) obj3).intValue();
                f0.v vVar5 = (f0.v) ((f0.p) obj2);
                vVar5.V(1582736677);
                k2.b bVar = (k2.b) vVar5.j(q1.j1.f5237f);
                d2.d dVar = (d2.d) vVar5.j(q1.j1.f5240i);
                k2.k kVar = (k2.k) vVar5.j(q1.j1.f5243l);
                y1.h0 h0Var = (y1.h0) obj4;
                boolean zF4 = vVar5.f(h0Var) | vVar5.f(kVar);
                Object objK4 = vVar5.K();
                if (zF4 || objK4 == hVar) {
                    objK4 = p3.p.B(h0Var, kVar);
                    vVar5.f0(objK4);
                }
                y1.h0 h0Var2 = (y1.h0) objK4;
                boolean zF5 = vVar5.f(dVar) | vVar5.f(h0Var2);
                Object objK5 = vVar5.K();
                if (zF5 || objK5 == hVar) {
                    y1.a0 a0Var = h0Var2.f6780a;
                    d2.p pVar2 = a0Var.f6714f;
                    d2.k kVar2 = a0Var.f6711c;
                    if (kVar2 == null) {
                        kVar2 = d2.k.f1613f;
                    }
                    d2.i iVar = a0Var.f6712d;
                    int i8 = iVar != null ? iVar.f1610a : 0;
                    d2.j jVar = a0Var.f6713e;
                    objK5 = ((d2.e) dVar).b(pVar2, kVar2, i8, jVar != null ? jVar.f1611a : 1);
                    vVar5.f0(objK5);
                }
                f0.g3 g3Var = (f0.g3) objK5;
                Object objK6 = vVar5.K();
                Object obj5 = objK6;
                if (objK6 == hVar) {
                    Object value = g3Var.getValue();
                    v.d1 d1Var = new v.d1();
                    d1Var.f5877a = kVar;
                    d1Var.f5878b = bVar;
                    d1Var.f5879c = dVar;
                    d1Var.f5880d = h0Var;
                    d1Var.f5881e = value;
                    d1Var.f5882f = v.w0.a(h0Var, bVar, dVar, v.w0.f6126a, 1);
                    vVar5.f0(d1Var);
                    obj5 = d1Var;
                }
                v.d1 d1Var2 = (v.d1) obj5;
                Object value2 = g3Var.getValue();
                if (kVar != d1Var2.f5877a || !b4.l.a(bVar, d1Var2.f5878b) || !b4.l.a(dVar, d1Var2.f5879c) || !b4.l.a(h0Var2, d1Var2.f5880d) || !b4.l.a(value2, d1Var2.f5881e)) {
                    d1Var2.f5877a = kVar;
                    d1Var2.f5878b = bVar;
                    d1Var2.f5879c = dVar;
                    d1Var2.f5880d = h0Var2;
                    d1Var2.f5881e = value2;
                    d1Var2.f5882f = v.w0.a(h0Var2, bVar, dVar, v.w0.f6126a, 1);
                }
                boolean zH = vVar5.h(d1Var2);
                Object objK7 = vVar5.K();
                if (zH || objK7 == hVar) {
                    objK7 = new s3(i6, d1Var2);
                    vVar5.f0(objK7);
                }
                r0.p pVarB = androidx.compose.ui.layout.a.b((a4.f) objK7);
                vVar5.p(false);
                return pVarB;
            default:
                r0.p pVar3 = (r0.p) obj;
                ((Number) obj3).intValue();
                z.e0 e0Var = (z.e0) obj4;
                f0.v vVar6 = (f0.v) ((f0.p) obj2);
                vVar6.V(1980580247);
                k2.b bVar2 = (k2.b) vVar6.j(q1.j1.f5237f);
                Object objK8 = vVar6.K();
                if (objK8 == hVar) {
                    objK8 = f0.l.s(new k2.j(0L));
                    vVar6.f0(objK8);
                }
                f0.i1 i1Var = (f0.i1) objK8;
                boolean zH2 = vVar6.h(e0Var);
                Object objK9 = vVar6.K();
                if (zH2 || objK9 == hVar) {
                    objK9 = new t2(e0Var, 14, i1Var);
                    vVar6.f0(objK9);
                }
                a4.a aVar = (a4.a) objK9;
                boolean zF6 = vVar6.f(bVar2);
                Object objK10 = vVar6.K();
                if (zF6 || objK10 == hVar) {
                    objK10 = new z.i0(bVar2, i1Var, i7);
                    vVar6.f0(objK10);
                }
                m.n nVar = z.x.f7022a;
                r0.p pVarA = r0.a.a(pVar3, new h2.b(aVar, i6, (a4.c) objK10));
                vVar6.p(false);
                return pVarA;
        }
    }
}

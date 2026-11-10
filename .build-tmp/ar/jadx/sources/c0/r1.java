package c0;

import androidx.compose.foundation.layout.LayoutWeightElement;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class r1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f1219a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f1220b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final float f1221c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final float f1222d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final float f1223e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final float f1224f;

    static {
        float f5 = e0.j.f1736a;
        f1219a = e0.j.f1738c;
        f1220b = 8;
        f1221c = 4;
        float f6 = e0.j.f1737b;
        float f7 = e0.j.f1739d;
        float f8 = 2;
        f1222d = (f6 - f7) / f8;
        f1223e = (e0.j.f1736a - f7) / f8;
        f1224f = 12;
    }

    public static final void a(r0.p pVar, long j5, long j6, float f5, r.z0 z0Var, n0.d dVar, f0.p pVar2, int i5) {
        r.z0 h0Var;
        long j7;
        long j8;
        float f6;
        r0.p pVar3;
        f0.v vVar;
        r.z0 z0Var2;
        r0.p pVar4;
        long j9;
        long j10;
        float f7;
        f0.v vVar2 = (f0.v) pVar2;
        vVar2.X(1596802123);
        if (((i5 | 11414) & 74899) == 74898 && vVar2.z()) {
            vVar2.Q();
            pVar4 = pVar;
            j9 = j5;
            j10 = j6;
            f7 = f5;
            z0Var2 = z0Var;
            vVar = vVar2;
        } else {
            vVar2.S();
            if ((i5 & 1) == 0 || vVar2.x()) {
                float f8 = d1.f797a;
                float f9 = e0.j.f1736a;
                long jD = u.d(vVar2, 37);
                long jA = u.a((s) vVar2.j(u.f1329a), jD);
                float f10 = d1.f797a;
                WeakHashMap weakHashMap = r.a1.f5506u;
                h0Var = new r.h0(r.b.e(vVar2).f5513g, r.c.f5535g | 32);
                j7 = jD;
                j8 = jA;
                f6 = f10;
                pVar3 = r0.m.f5680a;
            } else {
                vVar2.Q();
                pVar3 = pVar;
                j7 = j5;
                j8 = j6;
                f6 = f5;
                h0Var = z0Var;
            }
            vVar2.q();
            vVar = vVar2;
            o3.a(pVar3, null, j7, j8, f6, 0.0f, n0.i.c(105663120, new l(h0Var, 2, dVar), vVar2), vVar, 12607494, 98);
            z0Var2 = h0Var;
            pVar4 = pVar3;
            j9 = j7;
            j10 = j8;
            f7 = f6;
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new f1(pVar4, j9, j10, f7, z0Var2, dVar, i5);
        }
    }

    public static final void b(r.t0 t0Var, boolean z2, a4.a aVar, n0.d dVar, r0.p pVar, boolean z3, a4.e eVar, boolean z4, e1 e1Var, f0.p pVar2, int i5) {
        int i6;
        int i7;
        r0.p pVar3;
        e1 e1Var2;
        boolean z5;
        boolean z6;
        e1 e1Var3;
        r0.p pVar4;
        f0.v vVar;
        boolean z7;
        boolean z8;
        f0.v vVar2 = (f0.v) pVar2;
        vVar2.X(-663510974);
        if ((i5 & 6) == 0) {
            i6 = (vVar2.f(t0Var) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar2.g(z2) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar2.h(aVar) ? 256 : 128;
        }
        if ((i5 & 3072) == 0) {
            i6 |= vVar2.h(dVar) ? 2048 : 1024;
        }
        int i8 = i6 | 221184;
        if ((1572864 & i5) == 0) {
            i8 |= vVar2.h(eVar) ? 1048576 : 524288;
        }
        int i9 = 12582912 | i8;
        if ((100663296 & i5) == 0) {
            i9 = 46137344 | i8;
        }
        int i10 = 805306368 | i9;
        if ((306783379 & i10) == 306783378 && vVar2.z()) {
            vVar2.Q();
            pVar4 = pVar;
            z7 = z3;
            e1Var3 = e1Var;
            vVar = vVar2;
            z8 = z4;
        } else {
            vVar2.S();
            if ((i5 & 1) == 0 || vVar2.x()) {
                s sVar = (s) vVar2.j(u.f1329a);
                e1 e1Var4 = sVar.O;
                if (e1Var4 == null) {
                    float f5 = e0.j.f1736a;
                    long jC = u.c(sVar, 15);
                    long jC2 = u.c(sVar, 18);
                    long jC3 = u.c(sVar, 32);
                    int i11 = e0.j.f1740e;
                    long jC4 = u.c(sVar, i11);
                    int i12 = e0.j.f1741f;
                    e1 e1Var5 = new e1(jC, jC2, jC3, jC4, u.c(sVar, i12), y0.u.b(0.38f, u.c(sVar, i11)), y0.u.b(0.38f, u.c(sVar, i12)));
                    sVar.O = e1Var5;
                    e1Var4 = e1Var5;
                }
                i7 = i10 & (-234881025);
                pVar3 = r0.m.f5680a;
                e1Var2 = e1Var4;
                z5 = true;
                z6 = true;
            } else {
                vVar2.Q();
                i7 = i10 & (-234881025);
                pVar3 = pVar;
                z5 = z3;
                z6 = z4;
                e1Var2 = e1Var;
            }
            int i13 = i7;
            vVar2.q();
            vVar2.V(-103235253);
            Object objK = vVar2.K();
            f0.h hVar = f0.o.f2281a;
            if (objK == hVar) {
                objK = new q.k();
                vVar2.f0(objK);
            }
            q.k kVar = (q.k) objK;
            vVar2.p(false);
            boolean z9 = z6;
            n0.d dVarC = n0.i.c(-1419576100, new l1(e1Var2, z2, z5, eVar, z6, dVar), vVar2);
            vVar2.V(-103209106);
            n0.d dVarC2 = eVar == null ? null : n0.i.c(1644987592, new m1(e1Var2, z2, z5, eVar), vVar2);
            vVar2.p(false);
            Object objK2 = vVar2.K();
            if (objK2 == hVar) {
                objK2 = new f0.p1(0);
                vVar2.f0(objK2);
            }
            f0.h1 h1Var = (f0.h1) objK2;
            boolean z10 = z5;
            r0.p pVar5 = pVar3;
            e1Var3 = e1Var2;
            pVar4 = pVar5;
            r0.p pVarB = androidx.compose.foundation.layout.c.b(androidx.compose.foundation.selection.b.a(pVar5, z2, kVar, null, z10, new w1.f(4), aVar), 0.0f, f1219a, 1);
            if (1.0f <= 0.0d) {
                throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
            }
            r0.p pVarE = pVarB.e(new LayoutWeightElement(1.0f));
            Object objK3 = vVar2.K();
            if (objK3 == hVar) {
                objK3 = new g1(0, h1Var);
                vVar2.f0(objK3);
            }
            r0.p pVarE2 = androidx.compose.ui.layout.a.e(pVarE, (a4.c) objK3);
            n1.f0 f0VarE = r.o.e(r0.b.f5657h, true);
            int iHashCode = Long.hashCode(vVar2.S);
            f0.w1 w1VarL = vVar2.l();
            r0.p pVarC = r0.a.c(vVar2, pVarE2);
            p1.j.f4892c.getClass();
            p1.n nVar = p1.i.f4867b;
            vVar2.Y();
            if (vVar2.R) {
                vVar2.k(nVar);
            } else {
                vVar2.i0();
            }
            f0.l.x(p1.i.f4870e, vVar2, f0VarE);
            f0.l.x(p1.i.f4869d, vVar2, w1VarL);
            p1.h hVar2 = p1.i.f4871f;
            if (vVar2.R || !b4.l.a(vVar2.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar2, iHashCode, hVar2);
            }
            f0.l.x(p1.i.f4868c, vVar2, pVarC);
            float f6 = z2 ? 1.0f : 0.0f;
            m.k kVarH = m.d.h(100, 6, null);
            int i14 = 3;
            if (kVarH == m.f.f3661a) {
                vVar2.V(1125598679);
                boolean zC = vVar2.c(0.01f);
                Object objK4 = vVar2.K();
                if (zC || objK4 == hVar) {
                    objK4 = m.d.g(3, Float.valueOf(0.01f));
                    vVar2.f0(objK4);
                }
                kVarH = (m.h0) objK4;
                vVar2.p(false);
            } else {
                vVar2.V(1125708605);
                vVar2.p(false);
            }
            f0.g3 g3VarB = m.f.b(Float.valueOf(f6), m.b1.f3620a, kVarH, Float.valueOf(0.01f), "FloatAnimation", vVar2, 0, 0);
            k2.b bVar = (k2.b) vVar2.j(q1.j1.f5237f);
            long jB = p3.j.b((((f0.p1) h1Var).g() - bVar.M(e0.j.f1737b)) / 2, bVar.v(f1224f));
            boolean zF = vVar2.f(kVar) | vVar2.e(jB);
            Object objK5 = vVar2.K();
            if (zF || objK5 == hVar) {
                objK5 = new d0.h(kVar, jB);
                vVar2.f0(objK5);
            }
            n0.d dVarC3 = n0.i.c(691730997, new j1(0, (d0.h) objK5), vVar2);
            n0.d dVarC4 = n0.i.c(-474426875, new l(g3VarB, i14, e1Var3), vVar2);
            boolean zF2 = vVar2.f(g3VarB);
            Object objK6 = vVar2.K();
            if (zF2 || objK6 == hVar) {
                objK6 = new h1(g3VarB, 0);
                vVar2.f0(objK6);
            }
            c(dVarC3, dVarC4, dVarC, dVarC2, z9, (a4.a) objK6, vVar2, ((i13 >> 9) & 57344) | 438);
            vVar = vVar2;
            vVar.p(true);
            z7 = z10;
            z8 = z9;
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new k1(t0Var, z2, aVar, dVar, pVar4, z7, eVar, z8, e1Var3, i5);
        }
    }

    public static final void c(n0.d dVar, n0.d dVar2, n0.d dVar3, a4.e eVar, boolean z2, a4.a aVar, f0.p pVar, int i5) {
        int i6;
        a4.e eVar2;
        boolean z3;
        a4.a aVar2;
        boolean z4;
        a4.e eVar3;
        a4.e eVar4 = eVar;
        f0.v vVar = (f0.v) pVar;
        vVar.X(-1427075886);
        if ((i5 & 6) == 0) {
            i6 = (vVar.h(dVar) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.h(dVar2) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar.h(dVar3) ? 256 : 128;
        }
        if ((i5 & 3072) == 0) {
            i6 |= vVar.h(eVar4) ? 2048 : 1024;
        }
        if ((i5 & 24576) == 0) {
            i6 |= vVar.g(z2) ? 16384 : 8192;
        }
        if ((196608 & i5) == 0) {
            i6 |= vVar.h(aVar) ? 131072 : 65536;
        }
        if ((74899 & i6) == 74898 && vVar.z()) {
            vVar.Q();
            aVar2 = aVar;
            z3 = z2;
            eVar3 = eVar4;
        } else {
            int i7 = 458752 & i6;
            int i8 = 57344 & i6;
            boolean z5 = (i7 == 131072) | ((i6 & 7168) == 2048) | (i8 == 16384);
            Object objK = vVar.K();
            f0.h hVar = f0.o.f2281a;
            if (z5 || objK == hVar) {
                objK = new o1(aVar, eVar4, z2);
                vVar.f0(objK);
            }
            n1.f0 f0Var = (n1.f0) objK;
            int iHashCode = Long.hashCode(vVar.S);
            f0.w1 w1VarL = vVar.l();
            r0.m mVar = r0.m.f5680a;
            r0.p pVarC = r0.a.c(vVar, mVar);
            p1.j.f4892c.getClass();
            int i9 = i6;
            p1.n nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            p1.h hVar2 = p1.i.f4870e;
            f0.l.x(hVar2, vVar, f0Var);
            p1.h hVar3 = p1.i.f4869d;
            f0.l.x(hVar3, vVar, w1VarL);
            p1.h hVar4 = p1.i.f4871f;
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar, iHashCode, hVar4);
            }
            p1.h hVar5 = p1.i.f4868c;
            f0.l.x(hVar5, vVar, pVarC);
            dVar.invoke(vVar, Integer.valueOf(i9 & 14));
            dVar2.invoke(vVar, Integer.valueOf((i9 >> 3) & 14));
            r0.p pVarC2 = androidx.compose.ui.layout.a.c(mVar, "icon");
            r0.h hVar6 = r0.b.f5653d;
            n1.f0 f0VarE = r.o.e(hVar6, false);
            int iHashCode2 = Long.hashCode(vVar.S);
            f0.w1 w1VarL2 = vVar.l();
            r0.p pVarC3 = r0.a.c(vVar, pVarC2);
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(hVar2, vVar, f0VarE);
            f0.l.x(hVar3, vVar, w1VarL2);
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode2))) {
                b.b.i(iHashCode2, vVar, iHashCode2, hVar4);
            }
            f0.l.x(hVar5, vVar, pVarC3);
            dVar3.invoke(vVar, Integer.valueOf((i9 >> 6) & 14));
            vVar.p(true);
            vVar.V(1087198243);
            if (eVar != null) {
                r0.p pVarC4 = androidx.compose.ui.layout.a.c(mVar, "label");
                boolean z6 = (i8 == 16384) | (i7 == 131072);
                Object objK2 = vVar.K();
                if (z6 || objK2 == hVar) {
                    z3 = z2;
                    aVar2 = aVar;
                    objK2 = new n1(aVar2, z3);
                    vVar.f0(objK2);
                } else {
                    z3 = z2;
                    aVar2 = aVar;
                }
                r0.p pVarH = androidx.compose.foundation.layout.a.h(androidx.compose.ui.graphics.a.a(pVarC4, (a4.c) objK2), f1220b / 2, 0.0f, 2);
                n1.f0 f0VarE2 = r.o.e(hVar6, false);
                int iHashCode3 = Long.hashCode(vVar.S);
                f0.w1 w1VarL3 = vVar.l();
                r0.p pVarC5 = r0.a.c(vVar, pVarH);
                vVar.Y();
                if (vVar.R) {
                    vVar.k(nVar);
                } else {
                    vVar.i0();
                }
                f0.l.x(hVar2, vVar, f0VarE2);
                f0.l.x(hVar3, vVar, w1VarL3);
                if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode3))) {
                    b.b.i(iHashCode3, vVar, iHashCode3, hVar4);
                }
                f0.l.x(hVar5, vVar, pVarC5);
                a4.e eVar5 = eVar;
                eVar5.invoke(vVar, Integer.valueOf((i9 >> 9) & 14));
                z4 = true;
                vVar.p(true);
                eVar2 = eVar5;
            } else {
                eVar2 = eVar;
                z3 = z2;
                aVar2 = aVar;
                z4 = true;
            }
            vVar.p(false);
            vVar.p(z4);
            eVar3 = eVar2;
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new z0(dVar, dVar2, dVar3, eVar3, z3, aVar2, i5);
        }
    }
}

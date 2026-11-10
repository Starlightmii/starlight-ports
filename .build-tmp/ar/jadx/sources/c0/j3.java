package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class j3 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final float f950d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final float f953g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f947a = 600;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f948b = 30;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final float f949c = 16;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final float f951e = 2;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final float f952f = 6;

    static {
        float f5 = 8;
        f950d = f5;
        f953g = f5;
    }

    public static final void a(r0.p pVar, a4.e eVar, a4.e eVar2, y0.q0 q0Var, long j5, long j6, long j7, long j8, n0.d dVar, f0.p pVar2, int i5) {
        int i6;
        a4.e eVar3;
        a4.e eVar4;
        y0.q0 q0Var2;
        long j9;
        long j10;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(-1235788955);
        if ((i5 & 6) == 0) {
            i6 = (vVar.f(pVar) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            eVar3 = eVar;
            i6 |= vVar.h(eVar3) ? 32 : 16;
        } else {
            eVar3 = eVar;
        }
        if ((i5 & 384) == 0) {
            eVar4 = eVar2;
            i6 |= vVar.h(eVar4) ? 256 : 128;
        } else {
            eVar4 = eVar2;
        }
        if ((i5 & 3072) == 0) {
            i6 |= vVar.g(false) ? 2048 : 1024;
        }
        if ((i5 & 24576) == 0) {
            q0Var2 = q0Var;
            i6 |= vVar.f(q0Var2) ? 16384 : 8192;
        } else {
            q0Var2 = q0Var;
        }
        if ((196608 & i5) == 0) {
            i6 |= vVar.e(j5) ? 131072 : 65536;
        }
        if ((1572864 & i5) == 0) {
            i6 |= vVar.e(j6) ? 1048576 : 524288;
        }
        if ((12582912 & i5) == 0) {
            j9 = j7;
            i6 |= vVar.e(j9) ? 8388608 : 4194304;
        } else {
            j9 = j7;
        }
        if ((100663296 & i5) == 0) {
            j10 = j8;
            i6 |= vVar.e(j10) ? 67108864 : 33554432;
        } else {
            j10 = j8;
        }
        if ((805306368 & i5) == 0) {
            i6 |= vVar.h(dVar) ? 536870912 : 268435456;
        }
        int i7 = i6;
        if ((i7 & 306783379) == 306783378 && vVar.z()) {
            vVar.Q();
        } else {
            vVar.S();
            if ((i5 & 1) != 0 && !vVar.x()) {
                vVar.Q();
            }
            vVar.q();
            int i8 = i7 >> 9;
            o3.a(pVar, q0Var2, j5, j6, 0.0f, e0.n.f1779a, n0.i.c(-1829663446, new g3(eVar3, dVar, eVar4, j9, j10), vVar), vVar, (i7 & 14) | 12779520 | (i8 & 112) | (i8 & 896) | (i8 & 7168), 80);
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new h3(pVar, eVar, eVar2, q0Var, j5, j6, j7, j8, dVar, i5);
        }
    }

    public static final void b(z2 z2Var, r0.p pVar, y0.q0 q0Var, long j5, long j6, long j7, long j8, long j9, f0.p pVar2, int i5) {
        int i6;
        long jD;
        r0.p pVar3;
        y0.q0 q0Var2;
        int i7;
        long j10;
        long j11;
        long j12;
        long j13;
        f0.v vVar;
        r0.p pVar4;
        long j14;
        y0.q0 q0Var3;
        long j15;
        long j16;
        long j17;
        long j18;
        f0.v vVar2 = (f0.v) pVar2;
        vVar2.X(274621471);
        if ((i5 & 6) == 0) {
            i6 = (vVar2.f(z2Var) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        int i8 = i6 | 432;
        if ((i5 & 3072) == 0) {
            i8 = i6 | 1456;
        }
        if ((i5 & 24576) == 0) {
            i8 |= 8192;
        }
        if ((196608 & i5) == 0) {
            i8 |= 65536;
        }
        if ((1572864 & i5) == 0) {
            i8 |= 524288;
        }
        if ((12582912 & i5) == 0) {
            i8 |= 4194304;
        }
        if ((100663296 & i5) == 0) {
            i8 |= 33554432;
        }
        if ((38347923 & i8) == 38347922 && vVar2.z()) {
            vVar2.Q();
            pVar4 = pVar;
            q0Var3 = q0Var;
            j15 = j5;
            j16 = j6;
            j14 = j7;
            j17 = j8;
            j18 = j9;
            vVar = vVar2;
        } else {
            vVar2.S();
            if ((i5 & 1) == 0 || vVar2.x()) {
                y0.q0 q0VarA = s2.a(vVar2, e0.n.f1780b);
                long jD2 = u.d(vVar2, 6);
                long jD3 = u.d(vVar2, e0.n.f1782d);
                jD = u.d(vVar2, 5);
                long jD4 = u.d(vVar2, 5);
                long jD5 = u.d(vVar2, e0.n.f1781c);
                pVar3 = r0.m.f5680a;
                q0Var2 = q0VarA;
                i7 = i8 & (-268434433);
                j10 = jD2;
                j11 = jD3;
                j12 = jD4;
                j13 = jD5;
            } else {
                vVar2.Q();
                i7 = i8 & (-268434433);
                pVar3 = pVar;
                q0Var2 = q0Var;
                j10 = j5;
                j11 = j6;
                jD = j7;
                j12 = j8;
                j13 = j9;
            }
            vVar2.q();
            z2Var.f1489a.getClass();
            vVar2.V(1561344786);
            vVar2.p(false);
            vVar2.V(1561358724);
            z2Var.f1489a.getClass();
            vVar2.p(false);
            vVar = vVar2;
            a(androidx.compose.foundation.layout.a.f(pVar3, 12), null, null, q0Var2, j10, j11, j12, j13, n0.i.c(-1266389126, new v2(z2Var, 1), vVar2), vVar, ((i7 << 3) & 7168) | 805306368);
            pVar4 = pVar3;
            j14 = jD;
            q0Var3 = q0Var2;
            j15 = j10;
            j16 = j11;
            j17 = j12;
            j18 = j13;
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new i3(z2Var, pVar4, q0Var3, j15, j16, j14, j17, j18, i5);
        }
    }

    public static final void c(n0.d dVar, a4.e eVar, a4.e eVar2, y1.h0 h0Var, long j5, long j6, f0.p pVar, int i5) {
        int i6;
        y1.h0 h0Var2;
        int i7;
        long j7;
        a4.e eVar3;
        long j8;
        boolean z2;
        f0.v vVar = (f0.v) pVar;
        vVar.X(-903235475);
        if ((i5 & 6) == 0) {
            i6 = (vVar.h(dVar) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.h(eVar) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar.h(eVar2) ? 256 : 128;
        }
        if ((i5 & 3072) == 0) {
            i6 |= vVar.f(h0Var) ? 2048 : 1024;
        }
        if ((i5 & 24576) == 0) {
            i6 |= vVar.e(j5) ? 16384 : 8192;
        }
        if ((196608 & i5) == 0) {
            i6 |= vVar.e(j6) ? 131072 : 65536;
        }
        if ((74899 & i6) == 74898 && vVar.z()) {
            vVar.Q();
            j7 = j5;
            j8 = j6;
            eVar3 = eVar2;
            h0Var2 = h0Var;
        } else {
            float f5 = eVar2 == null ? f950d : 0;
            r0.m mVar = r0.m.f5680a;
            r0.p pVarI = androidx.compose.foundation.layout.a.i(mVar, f949c, 0.0f, f5, 10);
            Object objK = vVar.K();
            if (objK == f0.o.f2281a) {
                objK = new e3();
                vVar.f0(objK);
            }
            n1.f0 f0Var = (n1.f0) objK;
            int iHashCode = Long.hashCode(vVar.S);
            f0.w1 w1VarL = vVar.l();
            r0.p pVarC = r0.a.c(vVar, pVarI);
            p1.j.f4892c.getClass();
            p1.n nVar = p1.i.f4867b;
            vVar.Y();
            int i8 = i6;
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            p1.h hVar = p1.i.f4870e;
            f0.l.x(hVar, vVar, f0Var);
            p1.h hVar2 = p1.i.f4869d;
            f0.l.x(hVar2, vVar, w1VarL);
            p1.h hVar3 = p1.i.f4871f;
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar, iHashCode, hVar3);
            }
            p1.h hVar4 = p1.i.f4868c;
            f0.l.x(hVar4, vVar, pVarC);
            r0.p pVarH = androidx.compose.foundation.layout.a.h(androidx.compose.ui.layout.a.c(mVar, "text"), 0.0f, f952f, 1);
            r0.h hVar5 = r0.b.f5653d;
            n1.f0 f0VarE = r.o.e(hVar5, false);
            int iHashCode2 = Long.hashCode(vVar.S);
            f0.w1 w1VarL2 = vVar.l();
            r0.p pVarC2 = r0.a.c(vVar, pVarH);
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(hVar, vVar, f0VarE);
            f0.l.x(hVar2, vVar, w1VarL2);
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode2))) {
                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
            }
            f0.l.x(hVar4, vVar, pVarC2);
            dVar.invoke(vVar, Integer.valueOf(i8 & 14));
            vVar.p(true);
            vVar.V(-904778058);
            if (eVar != null) {
                r0.p pVarC3 = androidx.compose.ui.layout.a.c(mVar, "action");
                n1.f0 f0VarE2 = r.o.e(hVar5, false);
                int iHashCode3 = Long.hashCode(vVar.S);
                f0.w1 w1VarL3 = vVar.l();
                r0.p pVarC4 = r0.a.c(vVar, pVarC3);
                vVar.Y();
                if (vVar.R) {
                    vVar.k(nVar);
                } else {
                    vVar.i0();
                }
                f0.l.x(hVar, vVar, f0VarE2);
                f0.l.x(hVar2, vVar, w1VarL3);
                if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode3))) {
                    b.b.i(iHashCode3, vVar, iHashCode3, hVar3);
                }
                f0.l.x(hVar4, vVar, pVarC4);
                i7 = 8;
                j7 = j5;
                h0Var2 = h0Var;
                f0.l.b(new f0.z1[]{b0.f762a.a(new y0.u(j7)), n4.f1120a.a(h0Var2)}, eVar, vVar, (i8 & 112) | 8);
                vVar.p(true);
            } else {
                h0Var2 = h0Var;
                i7 = 8;
                j7 = j5;
            }
            vVar.p(false);
            vVar.V(-904766579);
            if (eVar2 != null) {
                r0.p pVarC5 = androidx.compose.ui.layout.a.c(mVar, "dismissAction");
                n1.f0 f0VarE3 = r.o.e(hVar5, false);
                int i9 = i7;
                int iHashCode4 = Long.hashCode(vVar.S);
                f0.w1 w1VarL4 = vVar.l();
                r0.p pVarC6 = r0.a.c(vVar, pVarC5);
                vVar.Y();
                if (vVar.R) {
                    vVar.k(nVar);
                } else {
                    vVar.i0();
                }
                f0.l.x(hVar, vVar, f0VarE3);
                f0.l.x(hVar2, vVar, w1VarL4);
                if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode4))) {
                    b.b.i(iHashCode4, vVar, iHashCode4, hVar3);
                }
                f0.l.x(hVar4, vVar, pVarC6);
                j8 = j6;
                eVar3 = eVar2;
                f0.l.a(b0.f762a.a(new y0.u(j8)), eVar3, vVar, i9 | ((i8 >> 3) & 112));
                z2 = true;
                vVar.p(true);
            } else {
                eVar3 = eVar2;
                j8 = j6;
                z2 = true;
            }
            vVar.p(false);
            vVar.p(z2);
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new f4(dVar, eVar, eVar3, h0Var2, j7, j8, i5);
        }
    }
}

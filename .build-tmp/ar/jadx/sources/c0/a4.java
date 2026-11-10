package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f746a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f747b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final float f748c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final float f749d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f750e;

    static {
        float f5 = e0.l.f1769a;
        f746a = e0.l.f1771c;
        f747b = 16;
        f748c = 14;
        f749d = 6;
        f750e = d4.a.w(20);
    }

    public static final void a(boolean z2, a4.a aVar, r0.p pVar, boolean z3, long j5, long j6, n0.d dVar, f0.p pVar2, int i5) {
        int i6;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(-202735880);
        if ((i5 & 6) == 0) {
            i6 = (vVar.g(z2) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.h(aVar) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar.f(pVar) ? 256 : 128;
        }
        if ((i5 & 3072) == 0) {
            i6 |= vVar.g(z3) ? 2048 : 1024;
        }
        if ((i5 & 24576) == 0) {
            i6 |= vVar.e(j5) ? 16384 : 8192;
        }
        if ((196608 & i5) == 0) {
            i6 |= vVar.e(j6) ? 131072 : 65536;
        }
        if ((1572864 & i5) == 0) {
            i6 |= vVar.f(null) ? 1048576 : 524288;
        }
        if ((12582912 & i5) == 0) {
            i6 |= vVar.h(dVar) ? 8388608 : 4194304;
        }
        int i7 = i6;
        if ((i7 & 4793491) == 4793490 && vVar.z()) {
            vVar.Q();
        } else {
            vVar.S();
            if ((i5 & 1) != 0 && !vVar.x()) {
                vVar.Q();
            }
            vVar.q();
            int i8 = i7 >> 12;
            vVar = vVar;
            c(j5, j6, z2, n0.i.c(-551896140, new u3(pVar, z2, i2.a(true, 0.0f, j5, vVar, ((i7 >> 6) & 896) | 6, 2), z3, aVar, dVar), vVar), vVar, (i8 & 112) | (i8 & 14) | 3072 | ((i7 << 6) & 896));
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new v3(z2, aVar, pVar, z3, j5, j6, dVar, i5);
        }
    }

    public static final void b(boolean z2, a4.a aVar, r0.p pVar, boolean z3, a4.e eVar, long j5, long j6, f0.p pVar2, int i5) {
        int i6;
        boolean z4;
        r0.p pVar3;
        long j7;
        long j8;
        r0.p pVar4;
        boolean z5;
        long j9;
        long j10;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(-350627181);
        int i7 = i5 | (vVar.g(z2) ? 4 : 2) | (vVar.h(aVar) ? 32 : 16) | 105581952;
        if ((38347923 & i7) == 38347922 && vVar.z()) {
            vVar.Q();
            pVar4 = pVar;
            z5 = z3;
            j9 = j5;
            j10 = j6;
        } else {
            vVar.S();
            if ((i5 & 1) == 0 || vVar.x()) {
                long j11 = ((y0.u) vVar.j(b0.f762a)).f6696a;
                i6 = i7 & (-33030145);
                z4 = true;
                pVar3 = r0.m.f5680a;
                j7 = j11;
                j8 = j7;
            } else {
                vVar.Q();
                i6 = i7 & (-33030145);
                pVar3 = pVar;
                z4 = z3;
                j7 = j5;
                j8 = j6;
            }
            vVar.q();
            vVar.V(79583089);
            n0.d dVarC = eVar == null ? null : n0.i.c(708874428, new j1(1, eVar), vVar);
            vVar.p(false);
            a(z2, aVar, pVar3, z4, j7, j8, n0.i.c(1540996038, new s3(0, dVarC), vVar), vVar, (i6 & 112) | (i6 & 14) | 12582912 | 1576320);
            pVar4 = pVar3;
            z5 = z4;
            j9 = j7;
            j10 = j8;
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new t3(z2, aVar, pVar4, z5, eVar, j9, j10, i5);
        }
    }

    public static final void c(long j5, long j6, boolean z2, n0.d dVar, f0.p pVar, int i5) {
        int i6;
        long j7;
        f0.v vVar = (f0.v) pVar;
        vVar.X(735731848);
        if ((i5 & 6) == 0) {
            i6 = (vVar.e(j5) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            j7 = j6;
            i6 |= vVar.e(j7) ? 32 : 16;
        } else {
            j7 = j6;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar.g(z2) ? 256 : 128;
        }
        if ((i5 & 3072) == 0) {
            i6 |= vVar.h(dVar) ? 2048 : 1024;
        }
        if ((i6 & 1171) == 1170 && vVar.z()) {
            vVar.Q();
        } else {
            int i7 = 6;
            int i8 = i6 >> 6;
            m.s0 s0VarB = m.y0.b(Boolean.valueOf(z2), vVar, i8 & 14, 2);
            f0.s1 s1Var = s0VarB.f3752b;
            boolean zBooleanValue = ((Boolean) s1Var.getValue()).booleanValue();
            vVar.V(-1997025499);
            long j8 = zBooleanValue ? j5 : j7;
            vVar.p(false);
            z0.c cVarF = y0.u.f(j8);
            boolean zF = vVar.f(cVarF);
            Object objK = vVar.K();
            if (zF || objK == f0.o.f2281a) {
                g1 g1Var = new g1(i7, cVarF);
                m.a1 a1Var = m.b1.f3620a;
                m.a1 a1Var2 = new m.a1(l.c.f3485e, g1Var);
                vVar.f0(a1Var2);
                objK = a1Var2;
            }
            m.a1 a1Var3 = (m.a1) objK;
            boolean zBooleanValue2 = ((Boolean) s0VarB.c()).booleanValue();
            vVar.V(-1997025499);
            long j9 = zBooleanValue2 ? j5 : j7;
            vVar.p(false);
            y0.u uVar = new y0.u(j9);
            boolean zBooleanValue3 = ((Boolean) s1Var.getValue()).booleanValue();
            vVar.V(-1997025499);
            long j10 = zBooleanValue3 ? j5 : j7;
            vVar.p(false);
            y0.u uVar2 = new y0.u(j10);
            m.p0 p0VarE = s0VarB.e();
            vVar.V(-899623535);
            m.z0 z0Var = p0VarE.a(Boolean.FALSE, Boolean.TRUE) ? new m.z0(150, 100, m.w.f3799c) : m.d.h(100, 2, m.w.f3799c);
            vVar.p(false);
            f0.l.a(b0.f762a.a(new y0.u(((y0.u) m.y0.a(s0VarB, uVar, uVar2, z0Var, a1Var3, vVar, 0).f3743k.getValue()).f6696a)), dVar, vVar, (i8 & 112) | 8);
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new z3(j5, j7, z2, dVar, i5);
        }
    }

    public static final void d(a4.e eVar, f0.p pVar, int i5) {
        int i6;
        boolean z2;
        r0.h hVar = r0.b.f5653d;
        f0.v vVar = (f0.v) pVar;
        vVar.X(514131524);
        if ((i5 & 6) == 0) {
            i6 = (vVar.h(eVar) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.h(null) ? 32 : 16;
        }
        if ((i6 & 19) == 18 && vVar.z()) {
            vVar.Q();
        } else {
            int i7 = i6 & 14;
            boolean z3 = ((i6 & 112) == 32) | (i7 == 4);
            Object objK = vVar.K();
            if (z3 || objK == f0.o.f2281a) {
                objK = new x3(eVar);
                vVar.f0(objK);
            }
            n1.f0 f0Var = (n1.f0) objK;
            int iHashCode = Long.hashCode(vVar.S);
            f0.w1 w1VarL = vVar.l();
            r0.m mVar = r0.m.f5680a;
            r0.p pVarC = r0.a.c(vVar, mVar);
            p1.j.f4892c.getClass();
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
            vVar.V(871566271);
            if (eVar != null) {
                r0.p pVarH = androidx.compose.foundation.layout.a.h(androidx.compose.ui.layout.a.c(mVar, "text"), f747b, 0.0f, 2);
                n1.f0 f0VarE = r.o.e(hVar, false);
                int iHashCode2 = Long.hashCode(vVar.S);
                f0.w1 w1VarL2 = vVar.l();
                r0.p pVarC2 = r0.a.c(vVar, pVarH);
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
                f0.l.x(hVar5, vVar, pVarC2);
                eVar.invoke(vVar, Integer.valueOf(i7));
                z2 = true;
                vVar.p(true);
            } else {
                z2 = true;
            }
            vVar.p(false);
            vVar.V(871570579);
            vVar.p(false);
            vVar.p(z2);
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new y3(i5, eVar);
        }
    }
}

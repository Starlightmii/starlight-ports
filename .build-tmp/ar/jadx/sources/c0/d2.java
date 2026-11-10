package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class d2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f798a = 4;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f799b;

    static {
        long j5 = e0.p.f1814l;
        if (d4.a.A(j5)) {
            throw new IllegalArgumentException("Cannot perform operation for Unspecified type.");
        }
        f799b = d4.a.B(k2.m.c(j5) / 2, 1095216660480L & j5);
    }

    /* JADX WARN: Code duplicated, block: B:19:0x004c  */
    /* JADX WARN: Code duplicated, block: B:21:0x0052  */
    /* JADX WARN: Code duplicated, block: B:23:0x0058  */
    /* JADX WARN: Code duplicated, block: B:25:0x0060  */
    /* JADX WARN: Code duplicated, block: B:26:0x0063  */
    /* JADX WARN: Code duplicated, block: B:30:0x006f  */
    /* JADX WARN: Code duplicated, block: B:36:0x0092  */
    /* JADX WARN: Code duplicated, block: B:38:0x0099  */
    /* JADX WARN: Code duplicated, block: B:43:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:45:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:47:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:48:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:50:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:53:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:55:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:59:0x01fb  */
    /* JADX WARN: Code duplicated, block: B:63:0x021c  */
    /* JADX WARN: Code duplicated, block: B:65:0x022c  */
    /* JADX WARN: Code duplicated, block: B:67:0x0230 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:68:0x0232  */
    /* JADX WARN: Code duplicated, block: B:69:0x0235  */
    /* JADX WARN: Code duplicated, block: B:74:0x029d  */
    /* JADX WARN: Code duplicated, block: B:76:? A[RETURN, SYNTHETIC] */
    public static final void a(String str, a4.c cVar, r0.p pVar, boolean z2, y1.h0 h0Var, a4.e eVar, a4.e eVar2, a4.e eVar3, e2.f0 f0Var, v.n0 n0Var, v.m0 m0Var, boolean z3, int i5, int i6, y0.q0 q0Var, k4 k4Var, f0.p pVar2, int i7, int i8, int i9) {
        boolean z4;
        int i10;
        int i11;
        int i12;
        int i13;
        y1.h0 h0Var2;
        int i14;
        s sVar;
        k4 k4Var2;
        k4 k4Var3;
        y0.q0 q0Var2;
        e2.f0 f0Var2;
        v.n0 n0Var2;
        v.m0 m0Var2;
        int i15;
        k4 k4Var4;
        boolean z5;
        boolean z6;
        Object objK;
        q.k kVar;
        boolean z7;
        long jB;
        boolean zBooleanValue;
        long j5;
        v.n0 n0Var3;
        e2.f0 f0Var3;
        int i16;
        int i17;
        boolean z8;
        v.m0 m0Var3;
        y1.h0 h0Var3;
        k4 k4Var5;
        boolean z9;
        y0.q0 q0Var3;
        f0.b2 b2VarR;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(-1922450045);
        int i18 = i7 | (vVar.f(str) ? 4 : 2) | 817982464;
        int i19 = i8 | 1797174;
        int i20 = i9 & 131072;
        if (i20 == 0) {
            if ((i8 & 12582912) == 0) {
                z4 = z3;
                i19 |= vVar.g(z4) ? 8388608 : 4194304;
            }
            i10 = 33554432 | i19;
            i11 = i9 & 524288;
            if (i11 != 0) {
                if ((i8 & 805306368) == 0) {
                    i12 = i6;
                    if (vVar.d(i12)) {
                        i13 = 536870912;
                    } else {
                        i13 = 268435456;
                    }
                    i10 |= i13;
                }
                if ((i18 & 306783379) != 306783378 && (i10 & 306783379) == 306783378 && vVar.z()) {
                    vVar.Q();
                    h0Var3 = h0Var;
                    f0Var3 = f0Var;
                    n0Var3 = n0Var;
                    m0Var3 = m0Var;
                    i17 = i5;
                    q0Var3 = q0Var;
                    k4Var5 = k4Var;
                    i16 = i12;
                    z8 = z4;
                    z9 = z2;
                } else {
                    vVar.S();
                    if ((i7 & 1) != 0 || vVar.x()) {
                        h0Var2 = (y1.h0) vVar.j(n4.f1120a);
                        if (i20 != 0) {
                            z4 = false;
                        }
                        if (z4) {
                            i14 = 1;
                        } else {
                            i14 = Integer.MAX_VALUE;
                        }
                        if (i11 != 0) {
                            i12 = 1;
                        }
                        v1 v1Var = v1.f1366a;
                        y0.q0 q0VarA = s2.a(vVar, 3);
                        sVar = (s) vVar.j(u.f1329a);
                        k4Var2 = sVar.Q;
                        vVar.V(1540400102);
                        if (k4Var2 == null) {
                            long jC = u.c(sVar, 18);
                            long jC2 = u.c(sVar, 18);
                            long jB2 = y0.u.b(0.38f, u.c(sVar, 18));
                            long jC3 = u.c(sVar, 18);
                            long j6 = y0.u.f6693f;
                            k4Var3 = new k4(jC, jC2, jB2, jC3, j6, j6, j6, j6, u.c(sVar, 26), u.c(sVar, 2), (z.k0) vVar.j(z.l0.f6977a), u.c(sVar, 26), u.c(sVar, 24), y0.u.b(0.12f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 26), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19));
                            sVar.Q = k4Var3;
                        } else {
                            k4Var3 = k4Var2;
                        }
                        vVar.p(false);
                        q0Var2 = q0VarA;
                        f0Var2 = e2.g0.f2024a;
                        n0Var2 = v.n0.f5987a;
                        m0Var2 = v.m0.f5969a;
                        i15 = i14;
                        k4Var4 = k4Var3;
                        z5 = true;
                        z6 = z4;
                    } else {
                        vVar.Q();
                        z5 = z2;
                        h0Var2 = h0Var;
                        f0Var2 = f0Var;
                        n0Var2 = n0Var;
                        m0Var2 = m0Var;
                        i15 = i5;
                        q0Var2 = q0Var;
                        z6 = z4;
                        k4Var4 = k4Var;
                    }
                    int i21 = i12;
                    vVar.q();
                    vVar.V(30062948);
                    objK = vVar.K();
                    if (objK == f0.o.f2281a) {
                        objK = new q.k();
                        vVar.f0(objK);
                    }
                    kVar = (q.k) objK;
                    z7 = false;
                    vVar.p(false);
                    vVar.V(30069058);
                    jB = h0Var2.b();
                    if (jB == 16) {
                        zBooleanValue = ((Boolean) q.f.l(kVar, vVar, 0).getValue()).booleanValue();
                        if (!z5) {
                            j5 = k4Var4.f988c;
                        } else if (zBooleanValue) {
                            j5 = k4Var4.f986a;
                        } else {
                            j5 = k4Var4.f987b;
                        }
                        jB = j5;
                        z7 = false;
                    }
                    long j7 = jB;
                    vVar.p(z7);
                    f0.l.a(z.l0.f6977a.a(k4Var4.f996k), n0.i.c(-1886965181, new y1(pVar, eVar, (k2.b) vVar.j(q1.j1.f5237f), k4Var4, str, cVar, z5, h0Var2.d(new y1.h0(j7, 0L, null, 0L, 0, 0L, 16777214)), n0Var2, m0Var2, z6, i15, i21, f0Var2, kVar, eVar2, eVar3, q0Var2), vVar), vVar, 56);
                    v.m0 m0Var4 = m0Var2;
                    n0Var3 = n0Var2;
                    f0Var3 = f0Var2;
                    i16 = i21;
                    i17 = i15;
                    z8 = z6;
                    m0Var3 = m0Var4;
                    h0Var3 = h0Var2;
                    k4Var5 = k4Var4;
                    z9 = z5;
                    q0Var3 = q0Var2;
                }
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new z1(str, cVar, pVar, z9, h0Var3, eVar, eVar2, eVar3, f0Var3, n0Var3, m0Var3, z8, i17, i16, q0Var3, k4Var5, i7, i8, i9);
                }
            }
            i10 = 838860800 | i19;
            i12 = i6;
            if ((i18 & 306783379) != 306783378) {
                vVar.S();
                if ((i7 & 1) != 0) {
                    h0Var2 = (y1.h0) vVar.j(n4.f1120a);
                    if (i20 != 0) {
                        z4 = false;
                    }
                    if (z4) {
                        i14 = 1;
                    } else {
                        i14 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i12 = 1;
                    }
                    v1 v1Var2 = v1.f1366a;
                    y0.q0 q0VarA2 = s2.a(vVar, 3);
                    sVar = (s) vVar.j(u.f1329a);
                    k4Var2 = sVar.Q;
                    vVar.V(1540400102);
                    if (k4Var2 == null) {
                        long jC4 = u.c(sVar, 18);
                        long jC5 = u.c(sVar, 18);
                        long jB3 = y0.u.b(0.38f, u.c(sVar, 18));
                        long jC6 = u.c(sVar, 18);
                        long j8 = y0.u.f6693f;
                        k4Var3 = new k4(jC4, jC5, jB3, jC6, j8, j8, j8, j8, u.c(sVar, 26), u.c(sVar, 2), (z.k0) vVar.j(z.l0.f6977a), u.c(sVar, 26), u.c(sVar, 24), y0.u.b(0.12f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 26), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19));
                        sVar.Q = k4Var3;
                    } else {
                        k4Var3 = k4Var2;
                    }
                    vVar.p(false);
                    q0Var2 = q0VarA2;
                    f0Var2 = e2.g0.f2024a;
                    n0Var2 = v.n0.f5987a;
                    m0Var2 = v.m0.f5969a;
                    i15 = i14;
                    k4Var4 = k4Var3;
                    z5 = true;
                    z6 = z4;
                } else {
                    h0Var2 = (y1.h0) vVar.j(n4.f1120a);
                    if (i20 != 0) {
                        z4 = false;
                    }
                    if (z4) {
                        i14 = 1;
                    } else {
                        i14 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i12 = 1;
                    }
                    v1 v1Var3 = v1.f1366a;
                    y0.q0 q0VarA3 = s2.a(vVar, 3);
                    sVar = (s) vVar.j(u.f1329a);
                    k4Var2 = sVar.Q;
                    vVar.V(1540400102);
                    if (k4Var2 == null) {
                        long jC7 = u.c(sVar, 18);
                        long jC8 = u.c(sVar, 18);
                        long jB4 = y0.u.b(0.38f, u.c(sVar, 18));
                        long jC9 = u.c(sVar, 18);
                        long j9 = y0.u.f6693f;
                        k4Var3 = new k4(jC7, jC8, jB4, jC9, j9, j9, j9, j9, u.c(sVar, 26), u.c(sVar, 2), (z.k0) vVar.j(z.l0.f6977a), u.c(sVar, 26), u.c(sVar, 24), y0.u.b(0.12f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 26), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19));
                        sVar.Q = k4Var3;
                    } else {
                        k4Var3 = k4Var2;
                    }
                    vVar.p(false);
                    q0Var2 = q0VarA3;
                    f0Var2 = e2.g0.f2024a;
                    n0Var2 = v.n0.f5987a;
                    m0Var2 = v.m0.f5969a;
                    i15 = i14;
                    k4Var4 = k4Var3;
                    z5 = true;
                    z6 = z4;
                }
                int i22 = i12;
                vVar.q();
                vVar.V(30062948);
                objK = vVar.K();
                if (objK == f0.o.f2281a) {
                    objK = new q.k();
                    vVar.f0(objK);
                }
                kVar = (q.k) objK;
                z7 = false;
                vVar.p(false);
                vVar.V(30069058);
                jB = h0Var2.b();
                if (jB == 16) {
                    zBooleanValue = ((Boolean) q.f.l(kVar, vVar, 0).getValue()).booleanValue();
                    if (!z5) {
                        j5 = k4Var4.f988c;
                    } else if (zBooleanValue) {
                        j5 = k4Var4.f986a;
                    } else {
                        j5 = k4Var4.f987b;
                    }
                    jB = j5;
                    z7 = false;
                }
                long j10 = jB;
                vVar.p(z7);
                f0.l.a(z.l0.f6977a.a(k4Var4.f996k), n0.i.c(-1886965181, new y1(pVar, eVar, (k2.b) vVar.j(q1.j1.f5237f), k4Var4, str, cVar, z5, h0Var2.d(new y1.h0(j10, 0L, null, 0L, 0, 0L, 16777214)), n0Var2, m0Var2, z6, i15, i22, f0Var2, kVar, eVar2, eVar3, q0Var2), vVar), vVar, 56);
                v.m0 m0Var5 = m0Var2;
                n0Var3 = n0Var2;
                f0Var3 = f0Var2;
                i16 = i22;
                i17 = i15;
                z8 = z6;
                m0Var3 = m0Var5;
                h0Var3 = h0Var2;
                k4Var5 = k4Var4;
                z9 = z5;
                q0Var3 = q0Var2;
            } else {
                vVar.S();
                if ((i7 & 1) != 0) {
                    h0Var2 = (y1.h0) vVar.j(n4.f1120a);
                    if (i20 != 0) {
                        z4 = false;
                    }
                    if (z4) {
                        i14 = 1;
                    } else {
                        i14 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i12 = 1;
                    }
                    v1 v1Var4 = v1.f1366a;
                    y0.q0 q0VarA4 = s2.a(vVar, 3);
                    sVar = (s) vVar.j(u.f1329a);
                    k4Var2 = sVar.Q;
                    vVar.V(1540400102);
                    if (k4Var2 == null) {
                        long jC10 = u.c(sVar, 18);
                        long jC11 = u.c(sVar, 18);
                        long jB5 = y0.u.b(0.38f, u.c(sVar, 18));
                        long jC12 = u.c(sVar, 18);
                        long j11 = y0.u.f6693f;
                        k4Var3 = new k4(jC10, jC11, jB5, jC12, j11, j11, j11, j11, u.c(sVar, 26), u.c(sVar, 2), (z.k0) vVar.j(z.l0.f6977a), u.c(sVar, 26), u.c(sVar, 24), y0.u.b(0.12f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 26), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19));
                        sVar.Q = k4Var3;
                    } else {
                        k4Var3 = k4Var2;
                    }
                    vVar.p(false);
                    q0Var2 = q0VarA4;
                    f0Var2 = e2.g0.f2024a;
                    n0Var2 = v.n0.f5987a;
                    m0Var2 = v.m0.f5969a;
                    i15 = i14;
                    k4Var4 = k4Var3;
                    z5 = true;
                    z6 = z4;
                } else {
                    h0Var2 = (y1.h0) vVar.j(n4.f1120a);
                    if (i20 != 0) {
                        z4 = false;
                    }
                    if (z4) {
                        i14 = 1;
                    } else {
                        i14 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i12 = 1;
                    }
                    v1 v1Var5 = v1.f1366a;
                    y0.q0 q0VarA5 = s2.a(vVar, 3);
                    sVar = (s) vVar.j(u.f1329a);
                    k4Var2 = sVar.Q;
                    vVar.V(1540400102);
                    if (k4Var2 == null) {
                        long jC13 = u.c(sVar, 18);
                        long jC14 = u.c(sVar, 18);
                        long jB6 = y0.u.b(0.38f, u.c(sVar, 18));
                        long jC15 = u.c(sVar, 18);
                        long j12 = y0.u.f6693f;
                        k4Var3 = new k4(jC13, jC14, jB6, jC15, j12, j12, j12, j12, u.c(sVar, 26), u.c(sVar, 2), (z.k0) vVar.j(z.l0.f6977a), u.c(sVar, 26), u.c(sVar, 24), y0.u.b(0.12f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 26), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19));
                        sVar.Q = k4Var3;
                    } else {
                        k4Var3 = k4Var2;
                    }
                    vVar.p(false);
                    q0Var2 = q0VarA5;
                    f0Var2 = e2.g0.f2024a;
                    n0Var2 = v.n0.f5987a;
                    m0Var2 = v.m0.f5969a;
                    i15 = i14;
                    k4Var4 = k4Var3;
                    z5 = true;
                    z6 = z4;
                }
                int i23 = i12;
                vVar.q();
                vVar.V(30062948);
                objK = vVar.K();
                if (objK == f0.o.f2281a) {
                    objK = new q.k();
                    vVar.f0(objK);
                }
                kVar = (q.k) objK;
                z7 = false;
                vVar.p(false);
                vVar.V(30069058);
                jB = h0Var2.b();
                if (jB == 16) {
                    zBooleanValue = ((Boolean) q.f.l(kVar, vVar, 0).getValue()).booleanValue();
                    if (!z5) {
                        j5 = k4Var4.f988c;
                    } else if (zBooleanValue) {
                        j5 = k4Var4.f986a;
                    } else {
                        j5 = k4Var4.f987b;
                    }
                    jB = j5;
                    z7 = false;
                }
                long j13 = jB;
                vVar.p(z7);
                f0.l.a(z.l0.f6977a.a(k4Var4.f996k), n0.i.c(-1886965181, new y1(pVar, eVar, (k2.b) vVar.j(q1.j1.f5237f), k4Var4, str, cVar, z5, h0Var2.d(new y1.h0(j13, 0L, null, 0L, 0, 0L, 16777214)), n0Var2, m0Var2, z6, i15, i23, f0Var2, kVar, eVar2, eVar3, q0Var2), vVar), vVar, 56);
                v.m0 m0Var6 = m0Var2;
                n0Var3 = n0Var2;
                f0Var3 = f0Var2;
                i16 = i23;
                i17 = i15;
                z8 = z6;
                m0Var3 = m0Var6;
                h0Var3 = h0Var2;
                k4Var5 = k4Var4;
                z9 = z5;
                q0Var3 = q0Var2;
            }
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new z1(str, cVar, pVar, z9, h0Var3, eVar, eVar2, eVar3, f0Var3, n0Var3, m0Var3, z8, i17, i16, q0Var3, k4Var5, i7, i8, i9);
            }
        }
        i19 = i8 | 14380086;
        z4 = z3;
        i10 = 33554432 | i19;
        i11 = i9 & 524288;
        if (i11 != 0) {
            if ((i8 & 805306368) == 0) {
                i12 = i6;
                if (vVar.d(i12)) {
                    i13 = 536870912;
                } else {
                    i13 = 268435456;
                }
                i10 |= i13;
            }
            if ((i18 & 306783379) != 306783378) {
                vVar.S();
                if ((i7 & 1) != 0) {
                    h0Var2 = (y1.h0) vVar.j(n4.f1120a);
                    if (i20 != 0) {
                        z4 = false;
                    }
                    if (z4) {
                        i14 = 1;
                    } else {
                        i14 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i12 = 1;
                    }
                    v1 v1Var6 = v1.f1366a;
                    y0.q0 q0VarA6 = s2.a(vVar, 3);
                    sVar = (s) vVar.j(u.f1329a);
                    k4Var2 = sVar.Q;
                    vVar.V(1540400102);
                    if (k4Var2 == null) {
                        long jC16 = u.c(sVar, 18);
                        long jC17 = u.c(sVar, 18);
                        long jB7 = y0.u.b(0.38f, u.c(sVar, 18));
                        long jC18 = u.c(sVar, 18);
                        long j14 = y0.u.f6693f;
                        k4Var3 = new k4(jC16, jC17, jB7, jC18, j14, j14, j14, j14, u.c(sVar, 26), u.c(sVar, 2), (z.k0) vVar.j(z.l0.f6977a), u.c(sVar, 26), u.c(sVar, 24), y0.u.b(0.12f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 26), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19));
                        sVar.Q = k4Var3;
                    } else {
                        k4Var3 = k4Var2;
                    }
                    vVar.p(false);
                    q0Var2 = q0VarA6;
                    f0Var2 = e2.g0.f2024a;
                    n0Var2 = v.n0.f5987a;
                    m0Var2 = v.m0.f5969a;
                    i15 = i14;
                    k4Var4 = k4Var3;
                    z5 = true;
                    z6 = z4;
                } else {
                    h0Var2 = (y1.h0) vVar.j(n4.f1120a);
                    if (i20 != 0) {
                        z4 = false;
                    }
                    if (z4) {
                        i14 = 1;
                    } else {
                        i14 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i12 = 1;
                    }
                    v1 v1Var7 = v1.f1366a;
                    y0.q0 q0VarA7 = s2.a(vVar, 3);
                    sVar = (s) vVar.j(u.f1329a);
                    k4Var2 = sVar.Q;
                    vVar.V(1540400102);
                    if (k4Var2 == null) {
                        long jC19 = u.c(sVar, 18);
                        long jC110 = u.c(sVar, 18);
                        long jB8 = y0.u.b(0.38f, u.c(sVar, 18));
                        long jC111 = u.c(sVar, 18);
                        long j15 = y0.u.f6693f;
                        k4Var3 = new k4(jC19, jC110, jB8, jC111, j15, j15, j15, j15, u.c(sVar, 26), u.c(sVar, 2), (z.k0) vVar.j(z.l0.f6977a), u.c(sVar, 26), u.c(sVar, 24), y0.u.b(0.12f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 26), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19));
                        sVar.Q = k4Var3;
                    } else {
                        k4Var3 = k4Var2;
                    }
                    vVar.p(false);
                    q0Var2 = q0VarA7;
                    f0Var2 = e2.g0.f2024a;
                    n0Var2 = v.n0.f5987a;
                    m0Var2 = v.m0.f5969a;
                    i15 = i14;
                    k4Var4 = k4Var3;
                    z5 = true;
                    z6 = z4;
                }
                int i24 = i12;
                vVar.q();
                vVar.V(30062948);
                objK = vVar.K();
                if (objK == f0.o.f2281a) {
                    objK = new q.k();
                    vVar.f0(objK);
                }
                kVar = (q.k) objK;
                z7 = false;
                vVar.p(false);
                vVar.V(30069058);
                jB = h0Var2.b();
                if (jB == 16) {
                    zBooleanValue = ((Boolean) q.f.l(kVar, vVar, 0).getValue()).booleanValue();
                    if (!z5) {
                        j5 = k4Var4.f988c;
                    } else if (zBooleanValue) {
                        j5 = k4Var4.f986a;
                    } else {
                        j5 = k4Var4.f987b;
                    }
                    jB = j5;
                    z7 = false;
                }
                long j16 = jB;
                vVar.p(z7);
                f0.l.a(z.l0.f6977a.a(k4Var4.f996k), n0.i.c(-1886965181, new y1(pVar, eVar, (k2.b) vVar.j(q1.j1.f5237f), k4Var4, str, cVar, z5, h0Var2.d(new y1.h0(j16, 0L, null, 0L, 0, 0L, 16777214)), n0Var2, m0Var2, z6, i15, i24, f0Var2, kVar, eVar2, eVar3, q0Var2), vVar), vVar, 56);
                v.m0 m0Var7 = m0Var2;
                n0Var3 = n0Var2;
                f0Var3 = f0Var2;
                i16 = i24;
                i17 = i15;
                z8 = z6;
                m0Var3 = m0Var7;
                h0Var3 = h0Var2;
                k4Var5 = k4Var4;
                z9 = z5;
                q0Var3 = q0Var2;
            } else {
                vVar.S();
                if ((i7 & 1) != 0) {
                    h0Var2 = (y1.h0) vVar.j(n4.f1120a);
                    if (i20 != 0) {
                        z4 = false;
                    }
                    if (z4) {
                        i14 = 1;
                    } else {
                        i14 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i12 = 1;
                    }
                    v1 v1Var8 = v1.f1366a;
                    y0.q0 q0VarA8 = s2.a(vVar, 3);
                    sVar = (s) vVar.j(u.f1329a);
                    k4Var2 = sVar.Q;
                    vVar.V(1540400102);
                    if (k4Var2 == null) {
                        long jC112 = u.c(sVar, 18);
                        long jC113 = u.c(sVar, 18);
                        long jB9 = y0.u.b(0.38f, u.c(sVar, 18));
                        long jC114 = u.c(sVar, 18);
                        long j17 = y0.u.f6693f;
                        k4Var3 = new k4(jC112, jC113, jB9, jC114, j17, j17, j17, j17, u.c(sVar, 26), u.c(sVar, 2), (z.k0) vVar.j(z.l0.f6977a), u.c(sVar, 26), u.c(sVar, 24), y0.u.b(0.12f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 26), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19));
                        sVar.Q = k4Var3;
                    } else {
                        k4Var3 = k4Var2;
                    }
                    vVar.p(false);
                    q0Var2 = q0VarA8;
                    f0Var2 = e2.g0.f2024a;
                    n0Var2 = v.n0.f5987a;
                    m0Var2 = v.m0.f5969a;
                    i15 = i14;
                    k4Var4 = k4Var3;
                    z5 = true;
                    z6 = z4;
                } else {
                    h0Var2 = (y1.h0) vVar.j(n4.f1120a);
                    if (i20 != 0) {
                        z4 = false;
                    }
                    if (z4) {
                        i14 = 1;
                    } else {
                        i14 = Integer.MAX_VALUE;
                    }
                    if (i11 != 0) {
                        i12 = 1;
                    }
                    v1 v1Var9 = v1.f1366a;
                    y0.q0 q0VarA9 = s2.a(vVar, 3);
                    sVar = (s) vVar.j(u.f1329a);
                    k4Var2 = sVar.Q;
                    vVar.V(1540400102);
                    if (k4Var2 == null) {
                        long jC115 = u.c(sVar, 18);
                        long jC116 = u.c(sVar, 18);
                        long jB10 = y0.u.b(0.38f, u.c(sVar, 18));
                        long jC117 = u.c(sVar, 18);
                        long j18 = y0.u.f6693f;
                        k4Var3 = new k4(jC115, jC116, jB10, jC117, j18, j18, j18, j18, u.c(sVar, 26), u.c(sVar, 2), (z.k0) vVar.j(z.l0.f6977a), u.c(sVar, 26), u.c(sVar, 24), y0.u.b(0.12f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 26), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19));
                        sVar.Q = k4Var3;
                    } else {
                        k4Var3 = k4Var2;
                    }
                    vVar.p(false);
                    q0Var2 = q0VarA9;
                    f0Var2 = e2.g0.f2024a;
                    n0Var2 = v.n0.f5987a;
                    m0Var2 = v.m0.f5969a;
                    i15 = i14;
                    k4Var4 = k4Var3;
                    z5 = true;
                    z6 = z4;
                }
                int i25 = i12;
                vVar.q();
                vVar.V(30062948);
                objK = vVar.K();
                if (objK == f0.o.f2281a) {
                    objK = new q.k();
                    vVar.f0(objK);
                }
                kVar = (q.k) objK;
                z7 = false;
                vVar.p(false);
                vVar.V(30069058);
                jB = h0Var2.b();
                if (jB == 16) {
                    zBooleanValue = ((Boolean) q.f.l(kVar, vVar, 0).getValue()).booleanValue();
                    if (!z5) {
                        j5 = k4Var4.f988c;
                    } else if (zBooleanValue) {
                        j5 = k4Var4.f986a;
                    } else {
                        j5 = k4Var4.f987b;
                    }
                    jB = j5;
                    z7 = false;
                }
                long j19 = jB;
                vVar.p(z7);
                f0.l.a(z.l0.f6977a.a(k4Var4.f996k), n0.i.c(-1886965181, new y1(pVar, eVar, (k2.b) vVar.j(q1.j1.f5237f), k4Var4, str, cVar, z5, h0Var2.d(new y1.h0(j19, 0L, null, 0L, 0, 0L, 16777214)), n0Var2, m0Var2, z6, i15, i25, f0Var2, kVar, eVar2, eVar3, q0Var2), vVar), vVar, 56);
                v.m0 m0Var8 = m0Var2;
                n0Var3 = n0Var2;
                f0Var3 = f0Var2;
                i16 = i25;
                i17 = i15;
                z8 = z6;
                m0Var3 = m0Var8;
                h0Var3 = h0Var2;
                k4Var5 = k4Var4;
                z9 = z5;
                q0Var3 = q0Var2;
            }
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new z1(str, cVar, pVar, z9, h0Var3, eVar, eVar2, eVar3, f0Var3, n0Var3, m0Var3, z8, i17, i16, q0Var3, k4Var5, i7, i8, i9);
            }
        }
        i10 = 838860800 | i19;
        i12 = i6;
        if ((i18 & 306783379) != 306783378) {
            vVar.S();
            if ((i7 & 1) != 0) {
                h0Var2 = (y1.h0) vVar.j(n4.f1120a);
                if (i20 != 0) {
                    z4 = false;
                }
                if (z4) {
                    i14 = 1;
                } else {
                    i14 = Integer.MAX_VALUE;
                }
                if (i11 != 0) {
                    i12 = 1;
                }
                v1 v1Var10 = v1.f1366a;
                y0.q0 q0VarA10 = s2.a(vVar, 3);
                sVar = (s) vVar.j(u.f1329a);
                k4Var2 = sVar.Q;
                vVar.V(1540400102);
                if (k4Var2 == null) {
                    long jC118 = u.c(sVar, 18);
                    long jC119 = u.c(sVar, 18);
                    long jB11 = y0.u.b(0.38f, u.c(sVar, 18));
                    long jC1110 = u.c(sVar, 18);
                    long j110 = y0.u.f6693f;
                    k4Var3 = new k4(jC118, jC119, jB11, jC1110, j110, j110, j110, j110, u.c(sVar, 26), u.c(sVar, 2), (z.k0) vVar.j(z.l0.f6977a), u.c(sVar, 26), u.c(sVar, 24), y0.u.b(0.12f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 26), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19));
                    sVar.Q = k4Var3;
                } else {
                    k4Var3 = k4Var2;
                }
                vVar.p(false);
                q0Var2 = q0VarA10;
                f0Var2 = e2.g0.f2024a;
                n0Var2 = v.n0.f5987a;
                m0Var2 = v.m0.f5969a;
                i15 = i14;
                k4Var4 = k4Var3;
                z5 = true;
                z6 = z4;
            } else {
                h0Var2 = (y1.h0) vVar.j(n4.f1120a);
                if (i20 != 0) {
                    z4 = false;
                }
                if (z4) {
                    i14 = 1;
                } else {
                    i14 = Integer.MAX_VALUE;
                }
                if (i11 != 0) {
                    i12 = 1;
                }
                v1 v1Var11 = v1.f1366a;
                y0.q0 q0VarA11 = s2.a(vVar, 3);
                sVar = (s) vVar.j(u.f1329a);
                k4Var2 = sVar.Q;
                vVar.V(1540400102);
                if (k4Var2 == null) {
                    long jC1111 = u.c(sVar, 18);
                    long jC1112 = u.c(sVar, 18);
                    long jB12 = y0.u.b(0.38f, u.c(sVar, 18));
                    long jC1113 = u.c(sVar, 18);
                    long j111 = y0.u.f6693f;
                    k4Var3 = new k4(jC1111, jC1112, jB12, jC1113, j111, j111, j111, j111, u.c(sVar, 26), u.c(sVar, 2), (z.k0) vVar.j(z.l0.f6977a), u.c(sVar, 26), u.c(sVar, 24), y0.u.b(0.12f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 26), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19));
                    sVar.Q = k4Var3;
                } else {
                    k4Var3 = k4Var2;
                }
                vVar.p(false);
                q0Var2 = q0VarA11;
                f0Var2 = e2.g0.f2024a;
                n0Var2 = v.n0.f5987a;
                m0Var2 = v.m0.f5969a;
                i15 = i14;
                k4Var4 = k4Var3;
                z5 = true;
                z6 = z4;
            }
            int i26 = i12;
            vVar.q();
            vVar.V(30062948);
            objK = vVar.K();
            if (objK == f0.o.f2281a) {
                objK = new q.k();
                vVar.f0(objK);
            }
            kVar = (q.k) objK;
            z7 = false;
            vVar.p(false);
            vVar.V(30069058);
            jB = h0Var2.b();
            if (jB == 16) {
                zBooleanValue = ((Boolean) q.f.l(kVar, vVar, 0).getValue()).booleanValue();
                if (!z5) {
                    j5 = k4Var4.f988c;
                } else if (zBooleanValue) {
                    j5 = k4Var4.f986a;
                } else {
                    j5 = k4Var4.f987b;
                }
                jB = j5;
                z7 = false;
            }
            long j112 = jB;
            vVar.p(z7);
            f0.l.a(z.l0.f6977a.a(k4Var4.f996k), n0.i.c(-1886965181, new y1(pVar, eVar, (k2.b) vVar.j(q1.j1.f5237f), k4Var4, str, cVar, z5, h0Var2.d(new y1.h0(j112, 0L, null, 0L, 0, 0L, 16777214)), n0Var2, m0Var2, z6, i15, i26, f0Var2, kVar, eVar2, eVar3, q0Var2), vVar), vVar, 56);
            v.m0 m0Var9 = m0Var2;
            n0Var3 = n0Var2;
            f0Var3 = f0Var2;
            i16 = i26;
            i17 = i15;
            z8 = z6;
            m0Var3 = m0Var9;
            h0Var3 = h0Var2;
            k4Var5 = k4Var4;
            z9 = z5;
            q0Var3 = q0Var2;
        } else {
            vVar.S();
            if ((i7 & 1) != 0) {
                h0Var2 = (y1.h0) vVar.j(n4.f1120a);
                if (i20 != 0) {
                    z4 = false;
                }
                if (z4) {
                    i14 = 1;
                } else {
                    i14 = Integer.MAX_VALUE;
                }
                if (i11 != 0) {
                    i12 = 1;
                }
                v1 v1Var12 = v1.f1366a;
                y0.q0 q0VarA12 = s2.a(vVar, 3);
                sVar = (s) vVar.j(u.f1329a);
                k4Var2 = sVar.Q;
                vVar.V(1540400102);
                if (k4Var2 == null) {
                    long jC1114 = u.c(sVar, 18);
                    long jC1115 = u.c(sVar, 18);
                    long jB13 = y0.u.b(0.38f, u.c(sVar, 18));
                    long jC1116 = u.c(sVar, 18);
                    long j113 = y0.u.f6693f;
                    k4Var3 = new k4(jC1114, jC1115, jB13, jC1116, j113, j113, j113, j113, u.c(sVar, 26), u.c(sVar, 2), (z.k0) vVar.j(z.l0.f6977a), u.c(sVar, 26), u.c(sVar, 24), y0.u.b(0.12f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 26), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19));
                    sVar.Q = k4Var3;
                } else {
                    k4Var3 = k4Var2;
                }
                vVar.p(false);
                q0Var2 = q0VarA12;
                f0Var2 = e2.g0.f2024a;
                n0Var2 = v.n0.f5987a;
                m0Var2 = v.m0.f5969a;
                i15 = i14;
                k4Var4 = k4Var3;
                z5 = true;
                z6 = z4;
            } else {
                h0Var2 = (y1.h0) vVar.j(n4.f1120a);
                if (i20 != 0) {
                    z4 = false;
                }
                if (z4) {
                    i14 = 1;
                } else {
                    i14 = Integer.MAX_VALUE;
                }
                if (i11 != 0) {
                    i12 = 1;
                }
                v1 v1Var13 = v1.f1366a;
                y0.q0 q0VarA13 = s2.a(vVar, 3);
                sVar = (s) vVar.j(u.f1329a);
                k4Var2 = sVar.Q;
                vVar.V(1540400102);
                if (k4Var2 == null) {
                    long jC1117 = u.c(sVar, 18);
                    long jC1118 = u.c(sVar, 18);
                    long jB14 = y0.u.b(0.38f, u.c(sVar, 18));
                    long jC1119 = u.c(sVar, 18);
                    long j114 = y0.u.f6693f;
                    k4Var3 = new k4(jC1117, jC1118, jB14, jC1119, j114, j114, j114, j114, u.c(sVar, 26), u.c(sVar, 2), (z.k0) vVar.j(z.l0.f6977a), u.c(sVar, 26), u.c(sVar, 24), y0.u.b(0.12f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 26), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 18)), u.c(sVar, 2), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19), u.c(sVar, 19), u.c(sVar, 19), y0.u.b(0.38f, u.c(sVar, 19)), u.c(sVar, 19));
                    sVar.Q = k4Var3;
                } else {
                    k4Var3 = k4Var2;
                }
                vVar.p(false);
                q0Var2 = q0VarA13;
                f0Var2 = e2.g0.f2024a;
                n0Var2 = v.n0.f5987a;
                m0Var2 = v.m0.f5969a;
                i15 = i14;
                k4Var4 = k4Var3;
                z5 = true;
                z6 = z4;
            }
            int i27 = i12;
            vVar.q();
            vVar.V(30062948);
            objK = vVar.K();
            if (objK == f0.o.f2281a) {
                objK = new q.k();
                vVar.f0(objK);
            }
            kVar = (q.k) objK;
            z7 = false;
            vVar.p(false);
            vVar.V(30069058);
            jB = h0Var2.b();
            if (jB == 16) {
                zBooleanValue = ((Boolean) q.f.l(kVar, vVar, 0).getValue()).booleanValue();
                if (!z5) {
                    j5 = k4Var4.f988c;
                } else if (zBooleanValue) {
                    j5 = k4Var4.f986a;
                } else {
                    j5 = k4Var4.f987b;
                }
                jB = j5;
                z7 = false;
            }
            long j115 = jB;
            vVar.p(z7);
            f0.l.a(z.l0.f6977a.a(k4Var4.f996k), n0.i.c(-1886965181, new y1(pVar, eVar, (k2.b) vVar.j(q1.j1.f5237f), k4Var4, str, cVar, z5, h0Var2.d(new y1.h0(j115, 0L, null, 0L, 0, 0L, 16777214)), n0Var2, m0Var2, z6, i15, i27, f0Var2, kVar, eVar2, eVar3, q0Var2), vVar), vVar, 56);
            v.m0 m0Var10 = m0Var2;
            n0Var3 = n0Var2;
            f0Var3 = f0Var2;
            i16 = i27;
            i17 = i15;
            z8 = z6;
            m0Var3 = m0Var10;
            h0Var3 = h0Var2;
            k4Var5 = k4Var4;
            z9 = z5;
            q0Var3 = q0Var2;
        }
        b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new z1(str, cVar, pVar, z9, h0Var3, eVar, eVar2, eVar3, f0Var3, n0Var3, m0Var3, z8, i17, i16, q0Var3, k4Var5, i7, i8, i9);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [f0.p, f0.v, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v25 */
    /* JADX WARN: Type inference failed for: r12v26 */
    /* JADX WARN: Type inference failed for: r12v27, types: [a4.e] */
    /* JADX WARN: Type inference failed for: r12v28, types: [a4.e] */
    /* JADX WARN: Type inference failed for: r12v30 */
    /* JADX WARN: Type inference failed for: r12v52 */
    /* JADX WARN: Type inference failed for: r12v53 */
    /* JADX WARN: Type inference failed for: r1v10, types: [a4.e] */
    /* JADX WARN: Type inference failed for: r29v0, types: [a4.f, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r31v0, types: [a4.e, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r33v0, types: [a4.e, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r38v0, types: [java.lang.Object, n0.d] */
    /* JADX WARN: Type inference failed for: r3v61, types: [a4.e] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10, types: [a4.e] */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference failed for: r4v28 */
    /* JADX WARN: Type inference failed for: r4v29 */
    /* JADX WARN: Type inference failed for: r5v11, types: [a4.e] */
    /* JADX WARN: Type inference failed for: r5v45 */
    /* JADX WARN: Type inference failed for: r5v47 */
    /* JADX WARN: Type inference failed for: r5v48 */
    /* JADX WARN: Type inference failed for: r5v49 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v27, types: [a4.e] */
    /* JADX WARN: Type inference failed for: r6v37 */
    /* JADX WARN: Type inference failed for: r7v23, types: [a4.e] */
    /* JADX WARN: Type inference failed for: r7v37 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    public static final void b(a4.e eVar, a4.f fVar, a4.e eVar2, a4.e eVar3, a4.e eVar4, a4.e eVar5, a4.e eVar6, boolean z2, float f5, a4.c cVar, n0.d dVar, a4.e eVar7, r.l0 l0Var, f0.p pVar, int i5, int i6) {
        int i7;
        int i8;
        r.l0 l0Var2;
        r0.h hVar;
        ?? r6;
        int i9;
        r0.h hVar2;
        ?? r7;
        ?? r5;
        float f6;
        ?? r12;
        boolean z3;
        boolean z4;
        ?? r13;
        ?? r8;
        ?? r4;
        r0.h hVar3 = r0.b.f5657h;
        r0.h hVar4 = r0.b.f5653d;
        ?? r1 = (f0.v) pVar;
        r1.X(1408290209);
        int i10 = i5 & 6;
        r0.m mVar = r0.m.f5680a;
        if (i10 == 0) {
            i7 = i5 | (r1.f(mVar) ? 4 : 2);
        } else {
            i7 = i5;
        }
        if ((i5 & 48) == 0) {
            i7 |= r1.h(eVar) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i7 |= r1.h(fVar) ? 256 : 128;
        }
        if ((i5 & 3072) == 0) {
            i7 |= r1.h(eVar2) ? 2048 : 1024;
        }
        if ((i5 & 24576) == 0) {
            i7 |= r1.h(eVar3) ? 16384 : 8192;
        }
        if ((196608 & i5) == 0) {
            i7 |= r1.h(eVar4) ? 131072 : 65536;
        }
        if ((1572864 & i5) == 0) {
            i7 |= r1.h(eVar5) ? 1048576 : 524288;
        }
        if ((12582912 & i5) == 0) {
            i7 |= r1.h(eVar6) ? 8388608 : 4194304;
        }
        if ((100663296 & i5) == 0) {
            i7 |= r1.g(z2) ? 67108864 : 33554432;
        }
        if ((i5 & 805306368) == 0) {
            i7 |= r1.c(f5) ? 536870912 : 268435456;
        }
        if ((i6 & 6) == 0) {
            i8 = i6 | (r1.h(cVar) ? 4 : 2);
        } else {
            i8 = i6;
        }
        if ((i6 & 48) == 0) {
            i8 |= r1.h(dVar) ? 32 : 16;
        }
        if ((i6 & 384) == 0) {
            i8 |= r1.h(eVar7) ? 256 : 128;
        }
        if ((i6 & 3072) == 0) {
            l0Var2 = l0Var;
            i8 |= r1.f(l0Var2) ? 2048 : 1024;
        } else {
            l0Var2 = l0Var;
        }
        int i11 = i8;
        if ((i7 & 306783379) == 306783378 && (i11 & 1171) == 1170 && r1.z()) {
            r1.Q();
            r8 = eVar2;
            r13 = eVar7;
            r4 = eVar;
            f6 = f5;
        } else {
            boolean z5 = ((i11 & 14) == 4) | ((i7 & 234881024) == 67108864) | ((i7 & 1879048192) == 536870912) | ((i11 & 7168) == 2048);
            Object objK = r1.K();
            if (z5 || objK == f0.o.f2281a) {
                objK = new f2(cVar, z2, f5, l0Var2);
                r1.f0(objK);
            }
            f2 f2Var = (f2) objK;
            k2.k kVar = (k2.k) r1.j(q1.j1.f5243l);
            int iHashCode = Long.hashCode(r1.S);
            f0.w1 w1VarL = r1.l();
            r0.p pVarC = r0.a.c(r1, mVar);
            p1.j.f4892c.getClass();
            p1.n nVar = p1.i.f4867b;
            r1.Y();
            if (r1.R) {
                r1.k(nVar);
            } else {
                r1.i0();
            }
            p1.h hVar5 = p1.i.f4870e;
            f0.l.x(hVar5, r1, f2Var);
            p1.h hVar6 = p1.i.f4869d;
            f0.l.x(hVar6, r1, w1VarL);
            p1.h hVar7 = p1.i.f4871f;
            if (r1.R || !b4.l.a(r1.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, r1, iHashCode, hVar7);
            }
            p1.h hVar8 = p1.i.f4868c;
            f0.l.x(hVar8, r1, pVarC);
            dVar.invoke(r1, Integer.valueOf((i11 >> 3) & 14));
            r1.V(250370369);
            if (eVar3 != 0) {
                r0.p pVarE = androidx.compose.ui.layout.a.c(mVar, "Leading").e(d0.u.f1587i);
                hVar = hVar3;
                n1.f0 f0VarE = r.o.e(hVar, false);
                int iHashCode2 = Long.hashCode(r1.S);
                f0.w1 w1VarL2 = r1.l();
                r0.p pVarC2 = r0.a.c(r1, pVarE);
                r1.Y();
                if (r1.R) {
                    r1.k(nVar);
                } else {
                    r1.i0();
                }
                f0.l.x(hVar5, r1, f0VarE);
                f0.l.x(hVar6, r1, w1VarL2);
                if (r1.R || !b4.l.a(r1.K(), Integer.valueOf(iHashCode2))) {
                    b.b.i(iHashCode2, r1, iHashCode2, hVar7);
                }
                f0.l.x(hVar8, r1, pVarC2);
                eVar3.invoke(r1, Integer.valueOf((i7 >> 12) & 14));
                r1.p(true);
            } else {
                hVar = hVar3;
            }
            boolean z6 = false;
            r1.p(false);
            r1.V(250379492);
            if (eVar4 != null) {
                r0.p pVarE2 = androidx.compose.ui.layout.a.c(mVar, "Trailing").e(d0.u.f1587i);
                n1.f0 f0VarE2 = r.o.e(hVar, false);
                int iHashCode3 = Long.hashCode(r1.S);
                f0.w1 w1VarL3 = r1.l();
                r0.p pVarC3 = r0.a.c(r1, pVarE2);
                r1.Y();
                if (r1.R) {
                    r1.k(nVar);
                } else {
                    r1.i0();
                }
                f0.l.x(hVar5, r1, f0VarE2);
                f0.l.x(hVar6, r1, w1VarL3);
                if (r1.R || !b4.l.a(r1.K(), Integer.valueOf(iHashCode3))) {
                    b.b.i(iHashCode3, r1, iHashCode3, hVar7);
                }
                f0.l.x(hVar8, r1, pVarC3);
                ?? r9 = eVar4;
                r9.invoke(r1, Integer.valueOf((i7 >> 15) & 14));
                r1.p(true);
                z6 = false;
                r6 = r9;
            } else {
                r6 = eVar4;
            }
            r1.p(z6);
            k2.k kVar2 = k2.k.f3416d;
            float fC = kVar == kVar2 ? l0Var2.c(kVar) : l0Var2.d(kVar);
            float fD = kVar == kVar2 ? l0Var2.d(kVar) : l0Var2.c(kVar);
            if (eVar3 != 0) {
                fC -= d0.u.f1581c;
                i9 = 0;
                float f7 = 0;
                if (fC < f7) {
                    fC = f7;
                }
            } else {
                i9 = 0;
            }
            if (r6 != 0) {
                fD -= d0.u.f1581c;
                float f8 = i9;
                if (fD < f8) {
                    fD = f8;
                }
            }
            r1.V(250410106);
            if (eVar5 != 0) {
                r0.p pVarI = androidx.compose.foundation.layout.a.i(androidx.compose.foundation.layout.c.l(androidx.compose.foundation.layout.c.e(androidx.compose.ui.layout.a.c(mVar, "Prefix"), d0.u.f1584f, Float.NaN)), fC, 0.0f, d0.u.f1583e, 10);
                hVar2 = hVar4;
                n1.f0 f0VarE3 = r.o.e(hVar2, false);
                int iHashCode4 = Long.hashCode(r1.S);
                f0.w1 w1VarL4 = r1.l();
                r0.p pVarC4 = r0.a.c(r1, pVarI);
                r1.Y();
                if (r1.R) {
                    r1.k(nVar);
                } else {
                    r1.i0();
                }
                f0.l.x(hVar5, r1, f0VarE3);
                f0.l.x(hVar6, r1, w1VarL4);
                if (r1.R || !b4.l.a(r1.K(), Integer.valueOf(iHashCode4))) {
                    b.b.i(iHashCode4, r1, iHashCode4, hVar7);
                }
                f0.l.x(hVar8, r1, pVarC4);
                eVar5.invoke(r1, Integer.valueOf((i7 >> 18) & 14));
                r1.p(true);
            } else {
                hVar2 = hVar4;
            }
            ?? r10 = 0;
            r1.p(false);
            r1.V(250422072);
            if (eVar6 != null) {
                r0.p pVarI2 = androidx.compose.foundation.layout.a.i(androidx.compose.foundation.layout.c.l(androidx.compose.foundation.layout.c.e(androidx.compose.ui.layout.a.c(mVar, "Suffix"), d0.u.f1584f, Float.NaN)), d0.u.f1583e, 0.0f, fD, 10);
                n1.f0 f0VarE4 = r.o.e(hVar2, false);
                int iHashCode5 = Long.hashCode(r1.S);
                f0.w1 w1VarL5 = r1.l();
                r0.p pVarC5 = r0.a.c(r1, pVarI2);
                r1.Y();
                if (r1.R) {
                    r1.k(nVar);
                } else {
                    r1.i0();
                }
                f0.l.x(hVar5, r1, f0VarE4);
                f0.l.x(hVar6, r1, w1VarL5);
                if (r1.R || !b4.l.a(r1.K(), Integer.valueOf(iHashCode5))) {
                    b.b.i(iHashCode5, r1, iHashCode5, hVar7);
                }
                f0.l.x(hVar8, r1, pVarC5);
                ?? r11 = eVar6;
                r11.invoke(r1, Integer.valueOf((i7 >> 21) & 14));
                r1.p(true);
                r10 = 0;
                r7 = r11;
            } else {
                r7 = eVar6;
            }
            r1.p(r10);
            float f9 = d0.u.f1584f;
            r0.p pVarL = androidx.compose.foundation.layout.c.l(androidx.compose.foundation.layout.c.e(mVar, f9, Float.NaN));
            float f10 = eVar5 == 0 ? fC : (float) r10;
            if (r7 != 0) {
                fD = (float) r10;
            }
            r0.p pVarI3 = androidx.compose.foundation.layout.a.i(pVarL, f10, 0.0f, fD, 10);
            r1.V(250444361);
            if (fVar != 0) {
                fVar.b(androidx.compose.ui.layout.a.c(mVar, "Hint").e(pVarI3), r1, Integer.valueOf((i7 >> 3) & 112));
            }
            r1.p(false);
            r0.p pVarE3 = androidx.compose.ui.layout.a.c(mVar, "TextField").e(pVarI3);
            n1.f0 f0VarE5 = r.o.e(hVar2, true);
            int iHashCode6 = Long.hashCode(r1.S);
            f0.w1 w1VarL6 = r1.l();
            r0.p pVarC6 = r0.a.c(r1, pVarE3);
            r1.Y();
            if (r1.R) {
                r1.k(nVar);
            } else {
                r1.i0();
            }
            f0.l.x(hVar5, r1, f0VarE5);
            f0.l.x(hVar6, r1, w1VarL6);
            if (r1.R || !b4.l.a(r1.K(), Integer.valueOf(iHashCode6))) {
                b.b.i(iHashCode6, r1, iHashCode6, hVar7);
            }
            f0.l.x(hVar8, r1, pVarC6);
            ?? r14 = eVar;
            r14.invoke(r1, Integer.valueOf((i7 >> 3) & 14));
            r1.p(true);
            r1.V(250455481);
            if (eVar2 != null) {
                f6 = f5;
                r0.p pVarC7 = androidx.compose.ui.layout.a.c(androidx.compose.foundation.layout.c.l(androidx.compose.foundation.layout.c.e(mVar, a.a.D(f9, d0.u.f1585g, f6), Float.NaN)), "Label");
                n1.f0 f0VarE6 = r.o.e(hVar2, false);
                int iHashCode7 = Long.hashCode(r1.S);
                f0.w1 w1VarL7 = r1.l();
                r0.p pVarC8 = r0.a.c(r1, pVarC7);
                r1.Y();
                if (r1.R) {
                    r1.k(nVar);
                } else {
                    r1.i0();
                }
                f0.l.x(hVar5, r1, f0VarE6);
                f0.l.x(hVar6, r1, w1VarL7);
                if (r1.R || !b4.l.a(r1.K(), Integer.valueOf(iHashCode7))) {
                    b.b.i(iHashCode7, r1, iHashCode7, hVar7);
                }
                f0.l.x(hVar8, r1, pVarC8);
                ?? r15 = eVar2;
                r15.invoke(r1, Integer.valueOf((i7 >> 9) & 14));
                r1.p(true);
                r5 = r15;
            } else {
                r5 = eVar2;
                f6 = f5;
            }
            r1.p(false);
            r1.V(250473414);
            if (eVar7 != null) {
                r0.p pVarE4 = androidx.compose.foundation.layout.a.e(androidx.compose.foundation.layout.c.l(androidx.compose.foundation.layout.c.e(androidx.compose.ui.layout.a.c(mVar, "Supporting"), d0.u.f1586h, Float.NaN)), d4.b());
                n1.f0 f0VarE7 = r.o.e(hVar2, false);
                int iHashCode8 = Long.hashCode(r1.S);
                f0.w1 w1VarL8 = r1.l();
                r0.p pVarC9 = r0.a.c(r1, pVarE4);
                r1.Y();
                if (r1.R) {
                    r1.k(nVar);
                } else {
                    r1.i0();
                }
                f0.l.x(hVar5, r1, f0VarE7);
                f0.l.x(hVar6, r1, w1VarL8);
                if (r1.R || !b4.l.a(r1.K(), Integer.valueOf(iHashCode8))) {
                    b.b.i(iHashCode8, r1, iHashCode8, hVar7);
                }
                f0.l.x(hVar8, r1, pVarC9);
                ?? r16 = eVar7;
                r16.invoke(r1, Integer.valueOf((i11 >> 6) & 14));
                z3 = true;
                r1.p(true);
                z4 = false;
                r12 = r16;
            } else {
                r12 = eVar7;
                z3 = true;
                z4 = false;
            }
            r1.p(z4);
            r1.p(z3);
            r4 = r14;
            r8 = r5;
            r13 = r12;
        }
        f0.b2 b2VarR = r1.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new a2(r4, fVar, r8, eVar3, eVar4, eVar5, eVar6, z2, f6, cVar, dVar, r13, l0Var2, i5, i6);
        }
    }

    public static final int c(int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, float f5, long j5, float f6, r.l0 l0Var) {
        int[] iArr = {i11, i7, i8, a.a.E(f5, i10, 0)};
        for (int i13 = 0; i13 < 4; i13++) {
            i9 = Math.max(i9, iArr[i13]);
        }
        float fB = l0Var.b() * f6;
        return Math.max(k2.a.j(j5), Math.max(i5, Math.max(i6, d4.a.H(a.a.D(fB, Math.max(fB, i10 / 2.0f), f5) + i9 + (l0Var.a() * f6)))) + i12);
    }

    public static final int d(int i5, int i6, int i7, int i8, int i9, int i10, int i11, float f5, long j5, float f6, r.l0 l0Var) {
        int i12 = i7 + i8;
        int iMax = Math.max(i9 + i12, Math.max(i11 + i12, a.a.E(f5, i10, 0))) + i5 + i6;
        k2.k kVar = k2.k.f3416d;
        return Math.max(iMax, Math.max(d4.a.H((i10 + ((l0Var.d(kVar) + l0Var.c(kVar)) * f6)) * f5), k2.a.k(j5)));
    }

    public static final int e(boolean z2, int i5, int i6, n1.m0 m0Var, n1.m0 m0Var2) {
        if (z2) {
            i6 = Math.round((1 + 0.0f) * ((i5 - m0Var2.f4131e) / 2.0f));
        }
        float f5 = d0.u.f1580b;
        return Math.max(i6, (m0Var != null ? m0Var.f4131e : 0) / 2);
    }
}

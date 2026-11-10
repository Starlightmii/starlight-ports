package p1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d0 f4945a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4947c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f4948d;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public k2.a f4953i;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a2.h f4946b = new a2.h(18);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a2.h f4949e = new a2.h(21);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final h0.e f4950f = new h0.e(new d0[16]);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f4951g = 1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final h0.e f4952h = new h0.e(new o0[16]);

    public p0(d0 d0Var) {
        this.f4945a = d0Var;
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0018  */
    public static boolean b(d0 d0Var, k2.a aVar) {
        boolean zU0;
        d0 d0Var2 = d0Var.f4782f;
        j0 j0Var = d0Var.f4802z;
        if (d0Var2 == null) {
            return false;
        }
        if (aVar == null) {
            h0 h0Var = j0Var.f4911s;
            k2.a aVar2 = h0Var != null ? h0Var.f4855p : null;
            if (aVar2 == null || d0Var2 == null) {
                zU0 = false;
            } else {
                b4.l.b(h0Var);
                zU0 = h0Var.u0(aVar2.f3400a);
            }
        } else if (d0Var2 != null) {
            h0 h0Var2 = j0Var.f4911s;
            b4.l.b(h0Var2);
            zU0 = h0Var2.u0(aVar.f3400a);
        } else {
            zU0 = false;
        }
        d0 d0VarR = d0Var.r();
        if (zU0 && d0VarR != null) {
            if (d0VarR.f4782f == null) {
                d0.Q(d0VarR, false, 3);
                return zU0;
            }
            if (d0Var.p() == 1) {
                d0.O(d0VarR, false, 3);
                return zU0;
            }
            if (d0Var.p() == 2) {
                d0VarR.N(false);
            }
        }
        return zU0;
    }

    public static boolean c(d0 d0Var, k2.a aVar) {
        boolean zJ = aVar != null ? d0Var.J(aVar) : d0.K(d0Var);
        d0 d0VarR = d0Var.r();
        if (zJ && d0VarR != null) {
            int i5 = d0Var.f4802z.f4910r.f4877n;
            if (i5 == 1) {
                d0.Q(d0VarR, false, 3);
                return zJ;
            }
            if (i5 == 2) {
                d0VarR.P(false);
            }
        }
        return zJ;
    }

    public static boolean f(d0 d0Var) {
        i0 i0Var = d0Var.f4802z.f4910r;
        return i0Var.f4877n == 1 || i0Var.f4886w.e();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(boolean z2) {
        Object[] objArr;
        a2.h hVar = this.f4949e;
        if (z2) {
            h0.e eVar = (h0.e) hVar.f49e;
            eVar.g();
            d0 d0Var = this.f4945a;
            eVar.b(d0Var);
            d0Var.F = true;
        }
        h0.e eVar2 = (h0.e) hVar.f49e;
        eVar2.p(c1.f4777b);
        int i5 = eVar2.f2578f;
        d0[] d0VarArr = (d0[]) hVar.f50f;
        if (d0VarArr == null || d0VarArr.length < i5) {
            objArr = d0VarArr;
            objArr = new d0[Math.max(16, i5)];
        }
        objArr = d0VarArr;
        hVar.f50f = null;
        for (int i6 = 0; i6 < i5; i6++) {
            objArr[i6] = eVar2.f2576d[i6];
        }
        eVar2.g();
        for (int i7 = i5 - 1; -1 < i7; i7--) {
            d0 d0Var2 = objArr[i7];
            b4.l.b(d0Var2);
            if (d0Var2.F) {
                a2.h.r(d0Var2);
            }
        }
        hVar.f50f = objArr;
    }

    public final void d(d0 d0Var, boolean z2) {
        a2.h hVar = this.f4946b;
        if (((p1) ((a2.h) (z2 ? hVar.f49e : hVar.f50f)).f50f).isEmpty()) {
            return;
        }
        if (!this.f4947c) {
            d4.a.L("forceMeasureTheSubtree should be executed during the measureAndLayout pass");
            throw null;
        }
        j0 j0Var = d0Var.f4802z;
        if (z2 ? j0Var.f4899g : j0Var.f4896d) {
            d4.a.K("node not yet measured");
            throw null;
        }
        e(d0Var, z2);
    }

    public final void e(d0 d0Var, boolean z2) {
        h0 h0Var;
        e0 e0Var;
        h0.e eVarU = d0Var.u();
        int i5 = eVarU.f2578f;
        a2.h hVar = this.f4946b;
        boolean z3 = true;
        if (i5 > 0) {
            Object[] objArr = eVarU.f2576d;
            int i6 = 0;
            do {
                d0 d0Var2 = (d0) objArr[i6];
                if ((!z2 && f(d0Var2)) || (z2 && (d0Var2.p() == 1 || ((h0Var = d0Var2.f4802z.f4911s) != null && (e0Var = h0Var.f4859t) != null && e0Var.e())))) {
                    boolean zO = f.o(d0Var2);
                    j0 j0Var = d0Var2.f4802z;
                    if (zO && !z2) {
                        if (j0Var.f4899g && ((p1) ((a2.h) hVar.f49e).f50f).contains(d0Var2)) {
                            i(d0Var2, true, false);
                        } else {
                            d(d0Var2, true);
                        }
                    }
                    if (z2 ? j0Var.f4899g : j0Var.f4896d) {
                        boolean zContains = ((p1) ((a2.h) hVar.f49e).f50f).contains(d0Var2);
                        if (!z2) {
                            zContains = zContains || ((p1) ((a2.h) hVar.f50f).f50f).contains(d0Var2);
                        }
                        if (zContains) {
                            i(d0Var2, z2, false);
                        }
                    }
                    if (!(z2 ? j0Var.f4899g : j0Var.f4896d)) {
                        e(d0Var2, z2);
                    }
                }
                i6++;
            } while (i6 < i5);
        }
        j0 j0Var2 = d0Var.f4802z;
        if (z2 ? j0Var2.f4899g : j0Var2.f4896d) {
            boolean zContains2 = ((p1) ((a2.h) hVar.f49e).f50f).contains(d0Var);
            if (z2) {
                z3 = zContains2;
            } else if (!zContains2 && !((p1) ((a2.h) hVar.f50f).f50f).contains(d0Var)) {
                z3 = false;
            }
            if (z3) {
                i(d0Var, z2, false);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0115 A[EDGE_INSN: B:100:0x0115->B:83:0x0115 BREAK  A[LOOP:1: B:38:0x0084->B:82:0x0112], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:82:0x0112 A[LOOP:1: B:38:0x0084->B:82:0x0112, LOOP_END] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v10 */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v13, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r12v15 */
    /* JADX WARN: Type inference failed for: r12v16 */
    /* JADX WARN: Type inference failed for: r12v17 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r12v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r12v9 */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v11 */
    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v13 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r14v8, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r14v9 */
    /* JADX WARN: Type inference failed for: r15v4 */
    public final boolean g(q1.r rVar) {
        boolean z2;
        r0.o oVar;
        ?? E;
        d0 d0Var;
        a2.h hVar = this.f4946b;
        d0 d0Var2 = this.f4945a;
        if (!d0Var2.D()) {
            d4.a.K("performMeasureAndLayout called with unattached root");
            throw null;
        }
        if (!d0Var2.E()) {
            d4.a.K("performMeasureAndLayout called with unplaced root");
            throw null;
        }
        if (this.f4947c) {
            d4.a.K("performMeasureAndLayout called during measure layout");
            throw null;
        }
        int i5 = 0;
        if (this.f4953i != null) {
            this.f4947c = true;
            this.f4948d = true;
            try {
                if (hVar.u()) {
                    z2 = false;
                    while (true) {
                        boolean zU = hVar.u();
                        a2.h hVar2 = (a2.h) hVar.f49e;
                        if (!zU) {
                            break;
                        }
                        boolean zIsEmpty = ((p1) hVar2.f50f).isEmpty();
                        boolean z3 = !zIsEmpty;
                        if (zIsEmpty) {
                            a2.h hVar3 = (a2.h) hVar.f50f;
                            d0 d0Var3 = (d0) ((p1) hVar3.f50f).first();
                            hVar3.v(d0Var3);
                            d0Var = d0Var3;
                        } else {
                            d0Var = (d0) ((p1) hVar2.f50f).first();
                            hVar2.v(d0Var);
                        }
                        boolean zI = i(d0Var, z3, true);
                        if (d0Var == d0Var2 && zI) {
                            z2 = true;
                        }
                    }
                    if (rVar != null) {
                        rVar.a();
                    }
                } else {
                    z2 = false;
                }
                this.f4947c = false;
                this.f4948d = false;
            } catch (Throwable th) {
                this.f4947c = false;
                this.f4948d = false;
                throw th;
            }
        } else {
            z2 = false;
        }
        h0.e eVar = this.f4950f;
        int i6 = eVar.f2578f;
        if (i6 > 0) {
            Object[] objArr = eVar.f2576d;
            int i7 = 0;
            while (true) {
                t0 t0Var = ((d0) objArr[i7]).f4801y;
                t tVar = t0Var.f4969b;
                boolean zH = z0.h(128);
                if (zH) {
                    oVar = tVar.M;
                } else {
                    oVar = tVar.M.f5685h;
                    if (oVar == null) {
                        break;
                    }
                    i7++;
                    if (i7 >= i6) {
                        break;
                    }
                    i5 = 0;
                }
                y0.n0 n0Var = y0.H;
                r0.o oVarN0 = tVar.N0(zH);
                while (true) {
                    if (oVarN0 != null && (oVarN0.f5684g & 128) != 0) {
                        if ((oVarN0.f5683f & 128) != 0) {
                            ?? r12 = oVarN0;
                            ?? eVar2 = 0;
                            while (r12 != 0) {
                                if (r12 instanceof v) {
                                    ((v) r12).r(t0Var.f4969b);
                                } else {
                                    if ((r12.f5683f & 128) != 0 && (r12 instanceof m)) {
                                        r0.o oVar2 = ((m) r12).f4921r;
                                        int i8 = i5;
                                        while (oVar2 != null) {
                                            if ((oVar2.f5683f & 128) != 0) {
                                                i8++;
                                                if (i8 == 1) {
                                                    E = r12;
                                                    eVar2 = eVar2;
                                                    eVar2 = eVar2;
                                                    E = oVar2;
                                                } else {
                                                    if (eVar2 == 0) {
                                                        eVar2 = new h0.e(new r0.o[16]);
                                                    }
                                                    if (E != 0) {
                                                        eVar2.b(E);
                                                        E = 0;
                                                    }
                                                    eVar2.b(oVar2);
                                                }
                                            } else {
                                                E = r12;
                                                eVar2 = eVar2;
                                            }
                                            oVar2 = oVar2.f5686i;
                                            E = E;
                                            eVar2 = eVar2;
                                        }
                                        if (i8 == 1) {
                                            E = r12;
                                            eVar2 = eVar2;
                                        }
                                        i5 = 0;
                                        r12 = E;
                                        eVar2 = eVar2;
                                    }
                                    E = r12;
                                    eVar2 = eVar2;
                                    E = f.e(eVar2);
                                    i5 = 0;
                                    r12 = E;
                                    eVar2 = eVar2;
                                }
                                E = r12;
                                eVar2 = eVar2;
                                E = f.e(eVar2);
                                i5 = 0;
                                r12 = E;
                                eVar2 = eVar2;
                            }
                        }
                        if (oVarN0 == oVar) {
                            break;
                        }
                        oVarN0 = oVarN0.f5686i;
                        i5 = 0;
                    } else {
                        break;
                        break;
                    }
                }
                i7++;
                if (i7 >= i6) {
                    break;
                    break;
                }
                i5 = 0;
            }
        }
        eVar.g();
        return z2;
    }

    public final void h() {
        a2.h hVar = this.f4946b;
        if (hVar.u()) {
            d0 d0Var = this.f4945a;
            if (!d0Var.D()) {
                d4.a.K("performMeasureAndLayout called with unattached root");
                throw null;
            }
            if (!d0Var.E()) {
                d4.a.K("performMeasureAndLayout called with unplaced root");
                throw null;
            }
            if (this.f4947c) {
                d4.a.K("performMeasureAndLayout called during measure layout");
                throw null;
            }
            if (this.f4953i != null) {
                this.f4947c = true;
                this.f4948d = false;
                try {
                    if (!((p1) ((a2.h) hVar.f49e).f50f).isEmpty()) {
                        if (d0Var.f4782f != null) {
                            k(d0Var, true);
                        } else {
                            j(d0Var);
                        }
                    }
                    k(d0Var, false);
                } finally {
                    this.f4947c = false;
                    this.f4948d = false;
                }
            }
        }
    }

    public final boolean i(d0 d0Var, boolean z2, boolean z3) {
        k2.a aVar;
        boolean zC;
        d0 d0VarR;
        n1.l0 placementScope;
        t tVar;
        d0 d0VarR2;
        d0 d0VarR3;
        h0 h0Var;
        e0 e0Var;
        h0 h0Var2;
        e0 e0Var2;
        boolean z4 = d0Var.G;
        j0 j0Var = d0Var.f4802z;
        int i5 = 0;
        if (z4 || (!d0Var.E() && !j0Var.f4910r.f4885v && ((!j0Var.f4896d || !f(d0Var)) && !b4.l.a(d0Var.F(), Boolean.TRUE) && ((!j0Var.f4899g || (d0Var.p() != 1 && ((h0Var2 = j0Var.f4911s) == null || (e0Var2 = h0Var2.f4859t) == null || !e0Var2.e()))) && !j0Var.f4910r.f4886w.e() && ((h0Var = j0Var.f4911s) == null || (e0Var = h0Var.f4859t) == null || !e0Var.e()))))) {
            return false;
        }
        d0 d0Var2 = this.f4945a;
        if (d0Var == d0Var2) {
            aVar = this.f4953i;
            b4.l.b(aVar);
        } else {
            aVar = null;
        }
        if (z2) {
            zC = j0Var.f4899g ? b(d0Var, aVar) : false;
            if (z3 && ((zC || j0Var.f4900h) && b4.l.a(d0Var.F(), Boolean.TRUE))) {
                if (d0Var.H == 3) {
                    d0Var.g();
                }
                h0 h0Var3 = j0Var.f4911s;
                b4.l.b(h0Var3);
                try {
                    h0Var3.f4848i = true;
                    if (!h0Var3.f4853n) {
                        d4.a.L("replace() called on item that was not placed");
                        throw null;
                    }
                    h0Var3.f4865z = false;
                    boolean z5 = h0Var3.f4858s;
                    h0Var3.t0(h0Var3.f4856q, h0Var3.f4857r);
                    if (z5 && !h0Var3.f4865z && (d0VarR3 = h0Var3.A.f4893a.r()) != null) {
                        d0VarR3.N(false);
                    }
                    h0Var3.f4848i = false;
                } catch (Throwable th) {
                    h0Var3.f4848i = false;
                    throw th;
                }
            }
        } else {
            boolean z6 = j0Var.f4896d;
            i0 i0Var = j0Var.f4910r;
            zC = z6 ? c(d0Var, aVar) : false;
            if (z3 && j0Var.f4897e && (d0Var == d0Var2 || ((d0VarR2 = d0Var.r()) != null && d0VarR2.E() && j0Var.f4910r.f4885v))) {
                if (d0Var == d0Var2) {
                    if (d0Var.H == 3) {
                        d0Var.g();
                    }
                    d0 d0VarR4 = d0Var.r();
                    if (d0VarR4 == null || (tVar = d0VarR4.f4801y.f4969b) == null || (placementScope = tVar.f4925l) == null) {
                        placementScope = ((q1.u) f.u(d0Var)).getPlacementScope();
                    }
                    n1.l0.f(placementScope, i0Var, 0, 0);
                } else {
                    if (d0Var.H == 3) {
                        d0Var.g();
                    }
                    i0Var.getClass();
                    try {
                        i0Var.f4872i = true;
                        if (!i0Var.f4876m) {
                            d4.a.L("replace called on unplaced item");
                            throw null;
                        }
                        boolean z7 = i0Var.f4884u;
                        i0Var.u0(i0Var.f4879p, i0Var.f4881r, i0Var.f4880q);
                        if (z7 && !i0Var.C && (d0VarR = i0Var.I.f4893a.r()) != null) {
                            d0VarR.P(false);
                        }
                        i0Var.f4872i = false;
                    } catch (Throwable th2) {
                        i0Var.f4872i = false;
                        throw th2;
                    }
                }
                ((h0.e) this.f4949e.f49e).b(d0Var);
                d0Var.F = true;
            }
        }
        h0.e eVar = this.f4952h;
        if (eVar.k()) {
            int i6 = eVar.f2578f;
            if (i6 > 0) {
                Object[] objArr = eVar.f2576d;
                do {
                    o0 o0Var = (o0) objArr[i5];
                    d0 d0Var3 = o0Var.f4942a;
                    boolean z8 = o0Var.f4944c;
                    d0 d0Var4 = o0Var.f4942a;
                    if (d0Var3.D()) {
                        if (o0Var.f4943b) {
                            d0.O(d0Var4, z8, 2);
                        } else {
                            d0.Q(d0Var4, z8, 2);
                        }
                    }
                    i5++;
                } while (i5 < i6);
            }
            eVar.g();
        }
        return zC;
    }

    public final void j(d0 d0Var) {
        h0.e eVarU = d0Var.u();
        int i5 = eVarU.f2578f;
        if (i5 > 0) {
            Object[] objArr = eVarU.f2576d;
            int i6 = 0;
            do {
                d0 d0Var2 = (d0) objArr[i6];
                if (f(d0Var2)) {
                    if (f.o(d0Var2)) {
                        k(d0Var2, true);
                    } else {
                        j(d0Var2);
                    }
                }
                i6++;
            } while (i6 < i5);
        }
    }

    public final void k(d0 d0Var, boolean z2) {
        k2.a aVar;
        if (d0Var.G) {
            return;
        }
        if (d0Var == this.f4945a) {
            aVar = this.f4953i;
            b4.l.b(aVar);
        } else {
            aVar = null;
        }
        if (z2) {
            b(d0Var, aVar);
        } else {
            c(d0Var, aVar);
        }
    }

    public final boolean l(d0 d0Var, boolean z2) {
        j0 j0Var = d0Var.f4802z;
        j0 j0Var2 = d0Var.f4802z;
        int iC = m.h.c(j0Var.f4895c);
        if (iC != 0 && iC != 1) {
            if (iC == 2 || iC == 3) {
                this.f4952h.b(new o0(d0Var, false, z2));
            } else {
                if (iC != 4) {
                    throw new i4.p();
                }
                if (!j0Var2.f4896d || z2) {
                    j0Var2.f4896d = true;
                    if (d0Var.G || (!d0Var.E() && (!j0Var2.f4896d || !f(d0Var)))) {
                        return false;
                    }
                    d0 d0VarR = d0Var.r();
                    if (d0VarR == null || !d0VarR.f4802z.f4896d) {
                        this.f4946b.o(d0Var, false);
                    }
                    if (!this.f4948d) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final void m(long j5) {
        k2.a aVar = this.f4953i;
        if (aVar == null ? false : k2.a.c(aVar.f3400a, j5)) {
            return;
        }
        if (this.f4947c) {
            d4.a.K("updateRootConstraints called while measuring");
            throw null;
        }
        this.f4953i = new k2.a(j5);
        d0 d0Var = this.f4945a;
        d0 d0Var2 = d0Var.f4782f;
        j0 j0Var = d0Var.f4802z;
        if (d0Var2 != null) {
            j0Var.f4899g = true;
        }
        j0Var.f4896d = true;
        this.f4946b.o(d0Var, d0Var2 != null);
    }
}

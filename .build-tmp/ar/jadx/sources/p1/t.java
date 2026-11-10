package p1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t extends y0 {
    public static final y0.h O;
    public final m1 M;
    public s N;

    static {
        y0.h hVarG = y0.l0.g();
        int i5 = y0.u.f6695h;
        hVarG.e(y0.u.f6691d);
        hVarG.k(1.0f);
        hVarG.l(1);
        O = hVarG;
    }

    public t(d0 d0Var) {
        super(d0Var);
        m1 m1Var = new m1();
        m1Var.f5684g = 0;
        this.M = m1Var;
        m1Var.f5688k = this;
        this.N = d0Var.f4782f != null ? new s(this) : null;
    }

    @Override // p1.y0
    public final void G0() {
        if (this.N == null) {
            this.N = new s(this);
        }
    }

    @Override // p1.y0
    public final n0 J0() {
        return this.N;
    }

    @Override // p1.y0
    public final r0.o L0() {
        return this.M;
    }

    /* JADX WARN: Code duplicated, block: B:49:0x00b4 A[PHI: r9
      0x00b4: PHI (r9v3 p1.r) = (r9v2 p1.r), (r9v4 p1.r), (r9v4 p1.r), (r9v4 p1.r) binds: [B:37:0x006f, B:43:0x00a0, B:45:0x00a9, B:48:0x00af] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:52:0x00b9 A[LOOP:0: B:36:0x0067->B:52:0x00b9, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:56:0x00bc A[SYNTHETIC] */
    @Override // p1.y0
    public final void Q0(d dVar, long j5, r rVar, boolean z2, boolean z3) {
        boolean z4;
        boolean z5;
        d1 d1Var;
        long j6 = j5;
        r rVar2 = rVar;
        int i5 = dVar.f4779d;
        boolean z6 = false;
        d0 d0Var = this.f4998o;
        switch (i5) {
            case 1:
                z4 = true;
                break;
            default:
                w1.i iVarN = d0Var.n();
                z4 = !(iVarN != null && iVarN.f6310f);
                break;
        }
        if (z4) {
            if (p3.j.C(j6) && ((d1Var = this.G) == null || !this.f5002s || d1Var.k(j6))) {
                z5 = z3;
            } else {
                if (z2) {
                    float fC0 = C0(j6, K0());
                    if (!Float.isInfinite(fC0) && !Float.isNaN(fC0)) {
                        z5 = false;
                    }
                }
                z5 = z3;
            }
            z6 = true;
        } else {
            z5 = z3;
        }
        if (z6) {
            int i6 = rVar2.f4959f;
            h0.e eVarT = d0Var.t();
            int i7 = eVarT.f2578f;
            if (i7 > 0) {
                Object[] objArr = eVarT.f2576d;
                int i8 = i7 - 1;
                while (true) {
                    d0 d0Var2 = (d0) objArr[i8];
                    if (d0Var2.E()) {
                        switch (dVar.f4779d) {
                            case 1:
                                d0Var2.v(j6, rVar2, z2, z5);
                                break;
                            default:
                                t0 t0Var = d0Var2.f4801y;
                                t0Var.f4970c.P0(y0.L, t0Var.f4970c.I0(j6), rVar2, true, z5);
                                rVar2 = rVar;
                                break;
                        }
                        long jA = rVar2.a();
                        if (Float.intBitsToFloat((int) (jA >> 32)) >= 0.0f || ((int) (jA & 4294967295L)) == 0) {
                            i8--;
                            if (i8 >= 0) {
                                j6 = j5;
                            }
                        } else if (rVar2.f4961h) {
                            rVar2.f4959f = rVar2.f4960g - 1;
                            i8--;
                            if (i8 >= 0) {
                                j6 = j5;
                            }
                        }
                    } else {
                        i8--;
                        if (i8 >= 0) {
                            j6 = j5;
                        }
                    }
                }
            }
            rVar2.f4959f = i6;
        }
    }

    @Override // p1.y0
    public final void X0(y0.r rVar, b1.b bVar) {
        d0 d0Var = this.f4998o;
        e1 e1VarU = f.u(d0Var);
        h0.e eVarT = d0Var.t();
        int i5 = eVarT.f2578f;
        if (i5 > 0) {
            Object[] objArr = eVarT.f2576d;
            int i6 = 0;
            do {
                d0 d0Var2 = (d0) objArr[i6];
                if (d0Var2.E()) {
                    d0Var2.j(rVar, bVar);
                }
                i6++;
            } while (i6 < i5);
        }
        if (((q1.u) e1VarU).getShowLayoutBounds()) {
            E0(rVar, O);
        }
    }

    @Override // n1.e0
    public final int a0(int i5) {
        a2.h hVarQ = this.f4998o.q();
        n1.f0 f0VarS = hVarQ.s();
        d0 d0Var = (d0) hVarQ.f49e;
        return f0VarS.b(d0Var.f4801y.f4970c, d0Var.l(), i5);
    }

    @Override // n1.e0
    public final n1.m0 c(long j5) {
        n0(j5);
        d0 d0Var = this.f4998o;
        h0.e eVarU = d0Var.u();
        int i5 = eVarU.f2578f;
        if (i5 > 0) {
            Object[] objArr = eVarU.f2576d;
            int i6 = 0;
            do {
                ((d0) objArr[i6]).f4802z.f4910r.f4877n = 3;
                i6++;
            } while (i6 < i5);
        }
        a1(d0Var.f4794r.h(this, d0Var.l(), j5));
        V0();
        return this;
    }

    @Override // n1.e0
    public final int c0(int i5) {
        a2.h hVarQ = this.f4998o.q();
        n1.f0 f0VarS = hVarQ.s();
        d0 d0Var = (d0) hVarQ.f49e;
        return f0VarS.d(d0Var.f4801y.f4970c, d0Var.l(), i5);
    }

    @Override // n1.e0
    public final int d(int i5) {
        a2.h hVarQ = this.f4998o.q();
        n1.f0 f0VarS = hVarQ.s();
        d0 d0Var = (d0) hVarQ.f49e;
        return f0VarS.j(d0Var.f4801y.f4970c, d0Var.l(), i5);
    }

    @Override // n1.e0
    public final int e0(int i5) {
        a2.h hVarQ = this.f4998o.q();
        n1.f0 f0VarS = hVarQ.s();
        d0 d0Var = (d0) hVarQ.f49e;
        return f0VarS.i(d0Var.f4801y.f4970c, d0Var.l(), i5);
    }

    @Override // n1.m0
    public final void k0(long j5, float f5, a4.c cVar) {
        Y0(j5, f5, cVar);
        if (this.f4923j) {
            return;
        }
        W0();
        this.f4998o.f4802z.f4910r.t0();
    }

    @Override // p1.m0
    public final int o0(n1.l lVar) {
        s sVar = this.N;
        if (sVar != null) {
            return sVar.o0(lVar);
        }
        i0 i0Var = this.f4998o.f4802z.f4910r;
        e0 e0Var = i0Var.f4886w;
        if (!i0Var.f4878o) {
            j0 j0Var = i0Var.I;
            if (j0Var.f4895c == 1) {
                e0Var.f4821f = true;
                if (e0Var.f4817b) {
                    j0Var.f4897e = true;
                    j0Var.f4898f = true;
                }
            } else {
                e0Var.f4822g = true;
            }
        }
        i0Var.x().f4924k = true;
        i0Var.D();
        i0Var.x().f4924k = false;
        Integer num = (Integer) e0Var.f4824i.get(lVar);
        if (num != null) {
            return num.intValue();
        }
        return Integer.MIN_VALUE;
    }
}

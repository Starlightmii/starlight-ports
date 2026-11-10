package p1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f0 implements a1.f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a1.c f4828d = new a1.c();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public o f4829e;

    @Override // a1.f
    public final a1.b A() {
        return this.f4828d.f27e;
    }

    @Override // k2.b
    public final int E(long j5) {
        return this.f4828d.E(j5);
    }

    @Override // a1.f
    public final void G(y0.c0 c0Var, long j5, long j6, long j7, float f5, y0.m mVar, int i5) {
        this.f4828d.G(c0Var, j5, j6, j7, f5, mVar, i5);
    }

    @Override // k2.b
    public final float I(long j5) {
        return this.f4828d.I(j5);
    }

    @Override // a1.f
    public final void L(long j5, long j6, long j7, int i5) {
        this.f4828d.L(j5, j6, j7, i5);
    }

    @Override // k2.b
    public final int M(float f5) {
        return this.f4828d.M(f5);
    }

    @Override // a1.f
    public final long O() {
        return this.f4828d.O();
    }

    @Override // k2.b
    public final long R(long j5) {
        return this.f4828d.R(j5);
    }

    @Override // k2.b
    public final float U(long j5) {
        return this.f4828d.U(j5);
    }

    @Override // a1.f
    public final void V(y0.k0 k0Var, y0.p pVar, float f5, a1.g gVar, int i5) {
        this.f4828d.V(k0Var, pVar, f5, gVar, i5);
    }

    @Override // a1.f
    public final void X(long j5, long j6, long j7, float f5) {
        this.f4828d.X(j5, j6, j7, f5);
    }

    @Override // k2.b
    public final float a() {
        return this.f4828d.a();
    }

    @Override // a1.f
    public final long b() {
        return this.f4828d.b();
    }

    public final void c() {
        a1.c cVar = this.f4828d;
        y0.r rVarR = cVar.f27e.r();
        l lVar = this.f4829e;
        b4.l.b(lVar);
        r0.o oVar = (r0.o) lVar;
        r0.o oVarE = oVar.f5681d.f5686i;
        if (oVarE != null && (oVarE.f5684g & 4) != 0) {
            while (true) {
                if (oVarE != null) {
                    int i5 = oVarE.f5683f;
                    if ((i5 & 2) == 0) {
                        if ((i5 & 4) != 0) {
                            break;
                        } else {
                            oVarE = oVarE.f5686i;
                        }
                    }
                }
                oVarE = null;
                break;
            }
        } else {
            oVarE = null;
            break;
        }
        if (oVarE == null) {
            y0 y0VarQ = f.q(lVar, 4);
            if (y0VarQ.L0() == oVar.f5681d) {
                y0VarQ = y0VarQ.f4999p;
                b4.l.b(y0VarQ);
            }
            y0VarQ.X0(rVarR, (b1.b) cVar.f27e.f24e);
            return;
        }
        h0.e eVar = null;
        while (oVarE != null) {
            if (oVarE instanceof o) {
                o oVar2 = (o) oVarE;
                b1.b bVar = (b1.b) cVar.f27e.f24e;
                y0 y0VarQ2 = f.q(oVar2, 4);
                long jO = a.a.O(y0VarQ2.f4132f);
                d0 d0Var = y0VarQ2.f4998o;
                d0Var.getClass();
                ((q1.u) f.u(d0Var)).getSharedDrawScope().d(rVarR, jO, y0VarQ2, oVar2, bVar);
            } else if ((oVarE.f5683f & 4) != 0 && (oVarE instanceof m)) {
                int i6 = 0;
                for (r0.o oVar3 = ((m) oVarE).f4921r; oVar3 != null; oVar3 = oVar3.f5686i) {
                    if ((oVar3.f5683f & 4) != 0) {
                        i6++;
                        if (i6 == 1) {
                            oVarE = oVar3;
                        } else {
                            if (eVar == null) {
                                eVar = new h0.e(new r0.o[16]);
                            }
                            if (oVarE != null) {
                                eVar.b(oVarE);
                                oVarE = null;
                            }
                            eVar.b(oVar3);
                        }
                    }
                }
                if (i6 == 1) {
                }
            }
            oVarE = f.e(eVar);
        }
    }

    public final void d(y0.r rVar, long j5, y0 y0Var, o oVar, b1.b bVar) {
        o oVar2 = this.f4829e;
        this.f4829e = oVar;
        k2.k kVar = y0Var.f4998o.f4797u;
        a1.c cVar = this.f4828d;
        a1.b bVar2 = cVar.f27e;
        a1.a aVar = ((a1.c) bVar2.f25f).f26d;
        k2.b bVar3 = aVar.f18a;
        k2.k kVar2 = aVar.f19b;
        y0.r rVarR = bVar2.r();
        a1.b bVar4 = cVar.f27e;
        long jT = bVar4.t();
        b1.b bVar5 = (b1.b) bVar4.f24e;
        bVar4.B(y0Var);
        bVar4.C(kVar);
        bVar4.A(rVar);
        bVar4.D(j5);
        bVar4.f24e = bVar;
        rVar.p();
        try {
            oVar.d(this);
            rVar.m();
            bVar4.B(bVar3);
            bVar4.C(kVar2);
            bVar4.A(rVarR);
            bVar4.D(jT);
            bVar4.f24e = bVar5;
            this.f4829e = oVar2;
        } catch (Throwable th) {
            rVar.m();
            bVar4.B(bVar3);
            bVar4.C(kVar2);
            bVar4.A(rVarR);
            bVar4.D(jT);
            bVar4.f24e = bVar5;
            throw th;
        }
    }

    @Override // k2.b
    public final long d0(float f5) {
        return this.f4828d.d0(f5);
    }

    public final void e(y0.p pVar, long j5, long j6, float f5, a1.g gVar) {
        a1.c cVar = this.f4828d;
        cVar.f26d.f20c.f(x0.c.d(j5), x0.c.e(j5), x0.f.d(j6) + x0.c.d(j5), x0.f.b(j6) + x0.c.e(j5), cVar.d(pVar, gVar, f5, null, 3, 1));
    }

    public final void g(y0.p pVar, long j5, long j6, long j7, float f5, a1.g gVar) {
        a1.c cVar = this.f4828d;
        cVar.f26d.f20c.n(x0.c.d(j5), x0.c.e(j5), x0.f.d(j6) + x0.c.d(j5), x0.f.b(j6) + x0.c.e(j5), x0.a.b(j7), x0.a.c(j7), cVar.d(pVar, gVar, f5, null, 3, 1));
    }

    @Override // a1.f
    public final k2.k getLayoutDirection() {
        return this.f4828d.f26d.f19b;
    }

    @Override // k2.b
    public final float i0(int i5) {
        return this.f4828d.i0(i5);
    }

    @Override // k2.b
    public final float j() {
        return this.f4828d.j();
    }

    @Override // k2.b
    public final float j0(float f5) {
        return f5 / this.f4828d.a();
    }

    @Override // a1.f
    public final void l(float f5, long j5, long j6) {
        this.f4828d.l(f5, j5, j6);
    }

    @Override // a1.f
    public final void q(long j5, long j6, long j7, long j8) {
        this.f4828d.q(j5, j6, j7, j8);
    }

    @Override // k2.b
    public final long s(float f5) {
        return this.f4828d.s(f5);
    }

    @Override // a1.f
    public final void t(y0.k0 k0Var, long j5, a1.g gVar) {
        this.f4828d.t(k0Var, j5, gVar);
    }

    @Override // k2.b
    public final long u(long j5) {
        return this.f4828d.u(j5);
    }

    @Override // k2.b
    public final float v(float f5) {
        return this.f4828d.a() * f5;
    }
}

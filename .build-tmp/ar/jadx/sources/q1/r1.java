package q1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r1 implements p1.d1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b1.b f5336d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final y0.a0 f5337e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final u f5338f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public a4.e f5339g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a4.a f5340h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f5342j;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float[] f5344l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f5345m;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f5349q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public y0.j0 f5351s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public y0.j f5352t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public y0.h f5353u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f5354v;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f5341i = a.a.f(Integer.MAX_VALUE, Integer.MAX_VALUE);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final float[] f5343k = y0.f0.a();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public k2.b f5346n = new k2.c(1.0f, 1.0f);

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public k2.k f5347o = k2.k.f3416d;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final a1.c f5348p = new a1.c();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public long f5350r = y0.t0.f6686b;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final c0.g1 f5355w = new c0.g1(21, this);

    public r1(b1.b bVar, y0.a0 a0Var, u uVar, a4.e eVar, a4.a aVar) {
        this.f5336d = bVar;
        this.f5337e = a0Var;
        this.f5338f = uVar;
        this.f5339g = eVar;
        this.f5340h = aVar;
    }

    @Override // p1.d1
    public final long a(long j5, boolean z2) {
        if (!z2) {
            return y0.f0.b(j5, n());
        }
        float[] fArrM = m();
        if (fArrM != null) {
            return y0.f0.b(j5, fArrM);
        }
        return 9187343241974906880L;
    }

    @Override // p1.d1
    public final void b(y0.r rVar, b1.b bVar) {
        float f5;
        y0.r rVar2;
        boolean z2;
        float f6;
        Canvas canvasA = y0.e.a(rVar);
        if (!canvasA.isHardwareAccelerated()) {
            b1.b bVar2 = this.f5336d;
            long j5 = bVar2.f493r;
            float f7 = (int) (j5 >> 32);
            float f8 = (int) (j5 & 4294967295L);
            long j6 = this.f5341i;
            float f9 = ((int) (j6 >> 32)) + f7;
            float f10 = ((int) (j6 & 4294967295L)) + f8;
            if (bVar2.f476a.a() < 1.0f) {
                y0.h hVarG = this.f5353u;
                if (hVarG == null) {
                    hVarG = y0.l0.g();
                    this.f5353u = hVarG;
                }
                hVarG.c(this.f5336d.f476a.a());
                Paint paint = hVarG.f6629a;
                f5 = f7;
                canvasA.saveLayer(f5, f8, f9, f10, paint);
            } else {
                f5 = f7;
                rVar.p();
            }
            rVar.k(f5, f8);
            rVar.r(n());
            if (this.f5336d.f476a.p() && this.f5336d.f476a.p()) {
                y0.j0 j0VarC = this.f5336d.c();
                if (j0VarC instanceof y0.h0) {
                    y0.r.h(rVar, ((y0.h0) j0VarC).f6633a);
                } else if (j0VarC instanceof y0.i0) {
                    y0.j jVarH = this.f5352t;
                    if (jVarH == null) {
                        jVarH = y0.l0.h();
                        this.f5352t = jVarH;
                    }
                    jVarH.d();
                    y0.k0.b(jVarH, ((y0.i0) j0VarC).f6636a);
                    rVar.a(jVarH);
                } else if (j0VarC instanceof y0.g0) {
                    rVar.a(((y0.g0) j0VarC).f6628a);
                }
            }
            a4.e eVar = this.f5339g;
            if (eVar != null) {
                eVar.invoke(rVar, null);
            }
            rVar.m();
            return;
        }
        i();
        this.f5354v = this.f5336d.f476a.G() > 0.0f;
        a1.c cVar = this.f5348p;
        a1.b bVar3 = cVar.f27e;
        a1.b bVar4 = cVar.f27e;
        bVar3.A(rVar);
        bVar3.f24e = bVar;
        b1.b bVar5 = this.f5336d;
        y0.r rVarR = bVar4.r();
        b1.b bVar6 = (b1.b) bVar4.f24e;
        b1.d dVar = bVar5.f476a;
        if (bVar5.f492q) {
            return;
        }
        if (!dVar.n()) {
            try {
                bVar5.e();
            } catch (Throwable unused) {
            }
        }
        bVar5.a();
        boolean z3 = dVar.G() > 0.0f;
        if (z3) {
            rVarR.s();
        }
        Canvas canvasA2 = y0.e.a(rVarR);
        boolean zIsHardwareAccelerated = canvasA2.isHardwareAccelerated();
        if (!zIsHardwareAccelerated) {
            canvasA2.save();
            long j7 = bVar5.f493r;
            float f11 = (int) (j7 >> 32);
            float f12 = (int) (j7 & 4294967295L);
            long j8 = bVar5.f494s;
            float f13 = ((int) (j8 >> 32)) + f11;
            float f14 = f12 + ((int) (j8 & 4294967295L));
            float fA = dVar.a();
            int iK = dVar.K();
            if (fA < 1.0f || iK != 3 || dVar.y() == 1) {
                y0.h hVarG2 = bVar5.f489n;
                if (hVarG2 == null) {
                    hVarG2 = y0.l0.g();
                    bVar5.f489n = hVarG2;
                }
                hVarG2.c(fA);
                hVarG2.d(iK);
                hVarG2.f(null);
                f6 = f11;
                canvasA2.saveLayer(f6, f12, f13, f14, hVarG2.f6629a);
            } else {
                canvasA2.save();
                f6 = f11;
            }
            canvasA2.translate(f6, f12);
            canvasA2.concat(dVar.C());
        }
        boolean z4 = bVar5.f488m || (!zIsHardwareAccelerated && dVar.p());
        if (z4) {
            rVarR.p();
            y0.j0 j0VarC2 = bVar5.c();
            if (j0VarC2 instanceof y0.h0) {
                rVar2 = rVarR;
                y0.r.h(rVar2, ((y0.h0) j0VarC2).f6633a);
            } else {
                rVar2 = rVarR;
                if (j0VarC2 instanceof y0.i0) {
                    y0.j jVarH2 = bVar5.f487l;
                    if (jVarH2 != null) {
                        jVarH2.f6638a.rewind();
                    } else {
                        jVarH2 = y0.l0.h();
                        bVar5.f487l = jVarH2;
                    }
                    y0.k0.b(jVarH2, ((y0.i0) j0VarC2).f6636a);
                    rVar2.a(jVarH2);
                } else if (j0VarC2 instanceof y0.g0) {
                    rVar2.a(((y0.g0) j0VarC2).f6628a);
                }
            }
        } else {
            rVar2 = rVarR;
        }
        if (bVar6 != null) {
            b0.a0 a0Var = bVar6.f491p;
            if (!a0Var.f373a) {
                throw new IllegalArgumentException("Only add dependencies during a tracking");
            }
            j.a0 a0Var2 = (j.a0) a0Var.f376d;
            if (a0Var2 != null) {
                a0Var2.a(bVar5);
            } else if (((b1.b) a0Var.f374b) != null) {
                int i5 = j.g0.f3106a;
                j.a0 a0Var3 = new j.a0();
                b1.b bVar7 = (b1.b) a0Var.f374b;
                b4.l.b(bVar7);
                a0Var3.a(bVar7);
                a0Var3.a(bVar5);
                a0Var.f376d = a0Var3;
                a0Var.f374b = null;
            } else {
                a0Var.f374b = bVar5;
            }
            j.a0 a0Var4 = (j.a0) a0Var.f377e;
            if (a0Var4 != null) {
                z2 = !a0Var4.j(bVar5);
            } else if (((b1.b) a0Var.f375c) != bVar5) {
                z2 = true;
            } else {
                a0Var.f375c = null;
                z2 = false;
            }
            if (z2) {
                bVar5.f490o++;
            }
        }
        dVar.D(rVar2);
        if (z4) {
            rVar2.m();
        }
        if (z3) {
            rVar2.q();
        }
        if (zIsHardwareAccelerated) {
            return;
        }
        canvasA2.restore();
    }

    @Override // p1.d1
    public final void c(a4.e eVar, a4.a aVar) {
        y0.a0 a0Var = this.f5337e;
        if (a0Var == null) {
            throw new IllegalArgumentException("currently reuse is only supported when we manage the layer lifecycle");
        }
        if (!this.f5336d.f492q) {
            throw new IllegalArgumentException("layer should have been released before reuse");
        }
        this.f5336d = a0Var.b();
        this.f5342j = false;
        this.f5339g = eVar;
        this.f5340h = aVar;
        this.f5350r = y0.t0.f6686b;
        this.f5354v = false;
        this.f5341i = a.a.f(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.f5351s = null;
        this.f5349q = 0;
    }

    @Override // p1.d1
    public final void d(long j5) {
        if (k2.j.a(j5, this.f5341i)) {
            return;
        }
        this.f5341i = j5;
        if (this.f5345m || this.f5342j) {
            return;
        }
        u uVar = this.f5338f;
        uVar.invalidate();
        if (true != this.f5345m) {
            this.f5345m = true;
            uVar.s(this, true);
        }
    }

    @Override // p1.d1
    public final void e(float[] fArr) {
        y0.f0.g(fArr, n());
    }

    @Override // p1.d1
    public final void f(float[] fArr) {
        float[] fArrM = m();
        if (fArrM != null) {
            y0.f0.g(fArr, fArrM);
        }
    }

    @Override // p1.d1
    public final void g() {
        this.f5339g = null;
        this.f5340h = null;
        this.f5342j = true;
        boolean z2 = this.f5345m;
        u uVar = this.f5338f;
        if (z2) {
            this.f5345m = false;
            uVar.s(this, false);
        }
        y0.a0 a0Var = this.f5337e;
        if (a0Var != null) {
            a0Var.a(this.f5336d);
            uVar.A(this);
        }
    }

    @Override // p1.d1
    public final void h(long j5) {
        b1.b bVar = this.f5336d;
        if (!k2.h.a(bVar.f493r, j5)) {
            bVar.f493r = j5;
            bVar.f476a.E((int) (j5 >> 32), (int) (j5 & 4294967295L), bVar.f494s);
        }
        g3.f5214a.a(this.f5338f);
    }

    @Override // p1.d1
    public final void i() {
        if (this.f5345m) {
            if (this.f5350r != y0.t0.f6686b && !k2.j.a(this.f5336d.f494s, this.f5341i)) {
                b1.b bVar = this.f5336d;
                long jB = p3.j.b(y0.t0.a(this.f5350r) * ((int) (this.f5341i >> 32)), y0.t0.b(this.f5350r) * ((int) (this.f5341i & 4294967295L)));
                if (!x0.c.b(bVar.f495t, jB)) {
                    bVar.f495t = jB;
                    bVar.f476a.L(jB);
                }
            }
            b1.b bVar2 = this.f5336d;
            k2.b bVar3 = this.f5346n;
            k2.k kVar = this.f5347o;
            long j5 = this.f5341i;
            if (!k2.j.a(bVar2.f494s, j5)) {
                bVar2.f494s = j5;
                long j6 = bVar2.f493r;
                bVar2.f476a.E((int) (j6 >> 32), (int) (4294967295L & j6), j5);
                if (bVar2.f483h == 9205357640488583168L) {
                    bVar2.f481f = true;
                    bVar2.a();
                }
            }
            bVar2.f477b = bVar3;
            bVar2.f478c = kVar;
            bVar2.f479d = this.f5355w;
            bVar2.e();
            if (this.f5345m) {
                this.f5345m = false;
                this.f5338f.s(this, false);
            }
        }
    }

    @Override // p1.d1
    public final void invalidate() {
        if (this.f5345m || this.f5342j) {
            return;
        }
        u uVar = this.f5338f;
        uVar.invalidate();
        if (true != this.f5345m) {
            this.f5345m = true;
            uVar.s(this, true);
        }
    }

    @Override // p1.d1
    public final void j(x0.b bVar, boolean z2) {
        if (!z2) {
            y0.f0.c(n(), bVar);
            return;
        }
        float[] fArrM = m();
        if (fArrM != null) {
            y0.f0.c(fArrM, bVar);
            return;
        }
        bVar.f6528a = 0.0f;
        bVar.f6529b = 0.0f;
        bVar.f6530c = 0.0f;
        bVar.f6531d = 0.0f;
    }

    @Override // p1.d1
    public final boolean k(long j5) {
        float fD = x0.c.d(j5);
        float fE = x0.c.e(j5);
        if (this.f5336d.f476a.p()) {
            return m0.r(this.f5336d.c(), fD, fE);
        }
        return true;
    }

    @Override // p1.d1
    public final void l(y0.n0 n0Var) {
        a4.a aVar;
        a4.a aVar2;
        int i5 = n0Var.f6650d | this.f5349q;
        this.f5347o = n0Var.f6663q;
        this.f5346n = n0Var.f6662p;
        int i6 = i5 & 4096;
        if (i6 != 0) {
            this.f5350r = n0Var.f6658l;
        }
        if ((i5 & 1) != 0) {
            b1.b bVar = this.f5336d;
            float f5 = n0Var.f6651e;
            b1.d dVar = bVar.f476a;
            if (dVar.q() != f5) {
                dVar.g(f5);
            }
        }
        if ((i5 & 2) != 0) {
            b1.b bVar2 = this.f5336d;
            float f6 = n0Var.f6652f;
            b1.d dVar2 = bVar2.f476a;
            if (dVar2.I() != f6) {
                dVar2.k(f6);
            }
        }
        if ((i5 & 4) != 0) {
            b1.b bVar3 = this.f5336d;
            float f7 = n0Var.f6653g;
            b1.d dVar3 = bVar3.f476a;
            if (dVar3.a() != f7) {
                dVar3.c(f7);
            }
        }
        if ((i5 & 8) != 0) {
            b1.d dVar4 = this.f5336d.f476a;
            if (dVar4.w() != 0.0f) {
                dVar4.i();
            }
        }
        if ((i5 & 16) != 0) {
            b1.d dVar5 = this.f5336d.f476a;
            if (dVar5.s() != 0.0f) {
                dVar5.d();
            }
        }
        boolean z2 = false;
        if ((i5 & 32) != 0) {
            b1.b bVar4 = this.f5336d;
            float f8 = n0Var.f6654h;
            b1.d dVar6 = bVar4.f476a;
            if (dVar6.G() != f8) {
                dVar6.r(f8);
                dVar6.x(dVar6.p() || f8 > 0.0f);
                bVar4.f481f = true;
                bVar4.a();
            }
            if (n0Var.f6654h > 0.0f && !this.f5354v && (aVar2 = this.f5340h) != null) {
                aVar2.a();
            }
        }
        if ((i5 & 64) != 0) {
            b1.b bVar5 = this.f5336d;
            long j5 = n0Var.f6655i;
            b1.d dVar7 = bVar5.f476a;
            if (!y0.u.c(j5, dVar7.M())) {
                dVar7.u(j5);
            }
        }
        if ((i5 & 128) != 0) {
            b1.b bVar6 = this.f5336d;
            long j6 = n0Var.f6656j;
            b1.d dVar8 = bVar6.f476a;
            if (!y0.u.c(j6, dVar8.t())) {
                dVar8.B(j6);
            }
        }
        if ((i5 & 1024) != 0) {
            b1.d dVar9 = this.f5336d.f476a;
            if (dVar9.J() != 0.0f) {
                dVar9.j();
            }
        }
        if ((i5 & 256) != 0) {
            b1.d dVar10 = this.f5336d.f476a;
            if (dVar10.z() != 0.0f) {
                dVar10.b();
            }
        }
        if ((i5 & 512) != 0) {
            b1.d dVar11 = this.f5336d.f476a;
            if (dVar11.F() != 0.0f) {
                dVar11.f();
            }
        }
        if ((i5 & 2048) != 0) {
            b1.b bVar7 = this.f5336d;
            float f9 = n0Var.f6657k;
            b1.d dVar12 = bVar7.f476a;
            if (dVar12.v() != f9) {
                dVar12.m(f9);
            }
        }
        if (i6 != 0) {
            long j7 = this.f5350r;
            if (j7 == y0.t0.f6686b) {
                b1.b bVar8 = this.f5336d;
                if (!x0.c.b(bVar8.f495t, 9205357640488583168L)) {
                    bVar8.f495t = 9205357640488583168L;
                    bVar8.f476a.L(9205357640488583168L);
                }
            } else {
                b1.b bVar9 = this.f5336d;
                long jB = p3.j.b(y0.t0.a(j7) * ((int) (this.f5341i >> 32)), y0.t0.b(this.f5350r) * ((int) (this.f5341i & 4294967295L)));
                if (!x0.c.b(bVar9.f495t, jB)) {
                    bVar9.f495t = jB;
                    bVar9.f476a.L(jB);
                }
            }
        }
        if ((i5 & 16384) != 0) {
            b1.b bVar10 = this.f5336d;
            boolean z3 = n0Var.f6660n;
            b1.d dVar13 = bVar10.f476a;
            if (dVar13.p() != z3) {
                dVar13.x(z3);
                bVar10.f481f = true;
                bVar10.a();
            }
        }
        if ((131072 & i5) != 0) {
            b1.d dVar14 = this.f5336d.f476a;
        }
        if ((32768 & i5) != 0) {
            b1.d dVar15 = this.f5336d.f476a;
            if (dVar15.y() != 0) {
                dVar15.A(0);
            }
        }
        if (!b4.l.a(this.f5351s, n0Var.f6664r)) {
            y0.j0 j0Var = n0Var.f6664r;
            this.f5351s = j0Var;
            if (j0Var != null) {
                b1.b bVar11 = this.f5336d;
                if (j0Var instanceof y0.h0) {
                    x0.d dVar16 = ((y0.h0) j0Var).f6633a;
                    bVar11.f(0.0f, p3.j.b(dVar16.f6534a, dVar16.f6535b), p3.j.f(dVar16.c(), dVar16.b()));
                } else if (j0Var instanceof y0.g0) {
                    y0.k0 k0Var = ((y0.g0) j0Var).f6628a;
                    bVar11.f485j = null;
                    bVar11.f483h = 9205357640488583168L;
                    bVar11.f482g = 0L;
                    bVar11.f484i = 0.0f;
                    bVar11.f481f = true;
                    bVar11.f488m = false;
                    bVar11.f486k = k0Var;
                    bVar11.a();
                } else if (j0Var instanceof y0.i0) {
                    y0.i0 i0Var = (y0.i0) j0Var;
                    y0.j jVar = i0Var.f6637b;
                    if (jVar != null) {
                        bVar11.f485j = null;
                        bVar11.f483h = 9205357640488583168L;
                        bVar11.f482g = 0L;
                        bVar11.f484i = 0.0f;
                        bVar11.f481f = true;
                        bVar11.f488m = false;
                        bVar11.f486k = jVar;
                        bVar11.a();
                    } else {
                        x0.e eVar = i0Var.f6636a;
                        bVar11.f(x0.a.b(eVar.f6545h), p3.j.b(eVar.f6538a, eVar.f6539b), p3.j.f(eVar.b(), eVar.a()));
                    }
                }
                if ((j0Var instanceof y0.g0) && Build.VERSION.SDK_INT < 33 && (aVar = this.f5340h) != null) {
                    aVar.a();
                }
            }
            z2 = true;
        }
        this.f5349q = n0Var.f6650d;
        if (i5 != 0 || z2) {
            g3.f5214a.a(this.f5338f);
        }
    }

    public final float[] m() {
        float[] fArrN = n();
        float[] fArrA = this.f5344l;
        if (fArrA == null) {
            fArrA = y0.f0.a();
            this.f5344l = fArrA;
        }
        if (m0.o(fArrN, fArrA)) {
            return fArrA;
        }
        return null;
    }

    public final float[] n() {
        b1.b bVar = this.f5336d;
        long j5 = bVar.f495t;
        b1.d dVar = bVar.f476a;
        long jW = p3.j.F(j5) ? p3.j.w(a.a.O(this.f5341i)) : bVar.f495t;
        float[] fArr = this.f5343k;
        y0.f0.d(fArr);
        float[] fArrA = y0.f0.a();
        y0.f0.h(fArrA, -x0.c.d(jW), -x0.c.e(jW));
        y0.f0.g(fArr, fArrA);
        float[] fArrA2 = y0.f0.a();
        y0.f0.h(fArrA2, dVar.w(), dVar.s());
        double dZ = (((double) dVar.z()) * 3.141592653589793d) / 180.0d;
        float fCos = (float) Math.cos(dZ);
        float fSin = (float) Math.sin(dZ);
        float f5 = fArrA2[1];
        float f6 = fArrA2[2];
        float f7 = fArrA2[5];
        float f8 = fArrA2[6];
        float f9 = fArrA2[9];
        float f10 = fArrA2[10];
        float f11 = fArrA2[13];
        float f12 = fArrA2[14];
        fArrA2[1] = (f5 * fCos) - (f6 * fSin);
        fArrA2[2] = (f6 * fCos) + (f5 * fSin);
        fArrA2[5] = (f7 * fCos) - (f8 * fSin);
        fArrA2[6] = (f8 * fCos) + (f7 * fSin);
        fArrA2[9] = (f9 * fCos) - (f10 * fSin);
        fArrA2[10] = (f10 * fCos) + (f9 * fSin);
        fArrA2[13] = (f11 * fCos) - (f12 * fSin);
        fArrA2[14] = (f12 * fCos) + (f11 * fSin);
        double dF = (((double) dVar.F()) * 3.141592653589793d) / 180.0d;
        float fCos2 = (float) Math.cos(dF);
        float fSin2 = (float) Math.sin(dF);
        float f13 = fArrA2[0];
        float f14 = fArrA2[2];
        float f15 = fArrA2[4];
        float f16 = fArrA2[6];
        float f17 = fArrA2[8];
        float f18 = fArrA2[10];
        float f19 = fArrA2[12];
        float f20 = fArrA2[14];
        fArrA2[0] = (f14 * fSin2) + (f13 * fCos2);
        fArrA2[2] = (f14 * fCos2) + ((-f13) * fSin2);
        fArrA2[4] = (f16 * fSin2) + (f15 * fCos2);
        fArrA2[6] = (f16 * fCos2) + ((-f15) * fSin2);
        fArrA2[8] = (f18 * fSin2) + (f17 * fCos2);
        fArrA2[10] = (f18 * fCos2) + ((-f17) * fSin2);
        fArrA2[12] = (f20 * fSin2) + (f19 * fCos2);
        fArrA2[14] = (f20 * fCos2) + ((-f19) * fSin2);
        y0.f0.e(fArrA2, dVar.J());
        y0.f0.f(fArrA2, dVar.q(), dVar.I());
        y0.f0.g(fArr, fArrA2);
        float[] fArrA3 = y0.f0.a();
        y0.f0.h(fArrA3, x0.c.d(jW), x0.c.e(jW));
        y0.f0.g(fArr, fArrA3);
        return fArr;
    }
}

package q1;

import android.graphics.Canvas;
import android.os.Build;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e2 implements p1.d1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u f5162d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a4.e f5163e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a4.a f5164f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5165g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f5167i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f5168j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public y0.h f5169k;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final l1 f5173o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f5174p;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final x1 f5166h = new x1();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final k3.b f5170l = new k3.b(g1.f5210g);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final y0.s f5171m = new y0.s();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f5172n = y0.t0.f6686b;

    public e2(u uVar, a4.e eVar, a4.a aVar) {
        this.f5162d = uVar;
        this.f5163e = eVar;
        this.f5164f = aVar;
        l1 c2Var = Build.VERSION.SDK_INT >= 29 ? new c2() : new b2(uVar);
        c2Var.J();
        c2Var.y(false);
        this.f5173o = c2Var;
    }

    @Override // p1.d1
    public final long a(long j5, boolean z2) {
        l1 l1Var = this.f5173o;
        k3.b bVar = this.f5170l;
        if (!z2) {
            return y0.f0.b(j5, bVar.b(l1Var));
        }
        float[] fArrA = bVar.a(l1Var);
        if (fArrA != null) {
            return y0.f0.b(j5, fArrA);
        }
        return 9187343241974906880L;
    }

    @Override // p1.d1
    public final void b(y0.r rVar, b1.b bVar) {
        Canvas canvasA = y0.e.a(rVar);
        boolean zIsHardwareAccelerated = canvasA.isHardwareAccelerated();
        l1 l1Var = this.f5173o;
        if (zIsHardwareAccelerated) {
            i();
            boolean z2 = l1Var.L() > 0.0f;
            this.f5168j = z2;
            if (z2) {
                rVar.s();
            }
            l1Var.t(canvasA);
            if (this.f5168j) {
                rVar.q();
                return;
            }
            return;
        }
        float fV = l1Var.v();
        float fU = l1Var.u();
        float fE = l1Var.E();
        float fQ = l1Var.q();
        if (l1Var.a() < 1.0f) {
            y0.h hVarG = this.f5169k;
            if (hVarG == null) {
                hVarG = y0.l0.g();
                this.f5169k = hVarG;
            }
            hVarG.c(l1Var.a());
            canvasA.saveLayer(fV, fU, fE, fQ, hVarG.f6629a);
        } else {
            rVar.p();
        }
        rVar.k(fV, fU);
        rVar.r(this.f5170l.b(l1Var));
        if (l1Var.F() || l1Var.r()) {
            this.f5166h.a(rVar);
        }
        a4.e eVar = this.f5163e;
        if (eVar != null) {
            eVar.invoke(rVar, null);
        }
        rVar.m();
        m(false);
    }

    @Override // p1.d1
    public final void c(a4.e eVar, a4.a aVar) {
        m(false);
        this.f5167i = false;
        this.f5168j = false;
        int i5 = y0.t0.f6687c;
        this.f5172n = y0.t0.f6686b;
        this.f5163e = eVar;
        this.f5164f = aVar;
    }

    @Override // p1.d1
    public final void d(long j5) {
        int i5 = (int) (j5 >> 32);
        int i6 = (int) (j5 & 4294967295L);
        float fA = y0.t0.a(this.f5172n) * i5;
        l1 l1Var = this.f5173o;
        l1Var.x(fA);
        l1Var.C(y0.t0.b(this.f5172n) * i6);
        if (l1Var.z(l1Var.v(), l1Var.u(), l1Var.v() + i5, l1Var.u() + i6)) {
            l1Var.o(this.f5166h.b());
            if (!this.f5165g && !this.f5167i) {
                this.f5162d.invalidate();
                m(true);
            }
            this.f5170l.c();
        }
    }

    @Override // p1.d1
    public final void e(float[] fArr) {
        y0.f0.g(fArr, this.f5170l.b(this.f5173o));
    }

    @Override // p1.d1
    public final void f(float[] fArr) {
        float[] fArrA = this.f5170l.a(this.f5173o);
        if (fArrA != null) {
            y0.f0.g(fArr, fArrA);
        }
    }

    @Override // p1.d1
    public final void g() {
        l1 l1Var = this.f5173o;
        if (l1Var.n()) {
            l1Var.h();
        }
        this.f5163e = null;
        this.f5164f = null;
        this.f5167i = true;
        m(false);
        u uVar = this.f5162d;
        uVar.B = true;
        uVar.A(this);
    }

    @Override // p1.d1
    public final void h(long j5) {
        l1 l1Var = this.f5173o;
        int iV = l1Var.v();
        int iU = l1Var.u();
        int i5 = (int) (j5 >> 32);
        int i6 = (int) (j5 & 4294967295L);
        if (iV == i5 && iU == i6) {
            return;
        }
        if (iV != i5) {
            l1Var.p(i5 - iV);
        }
        if (iU != i6) {
            l1Var.G(i6 - iU);
        }
        g3.f5214a.a(this.f5162d);
        this.f5170l.c();
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0020  */
    @Override // p1.d1
    public final void i() {
        y0.k0 k0Var;
        boolean z2 = this.f5165g;
        l1 l1Var = this.f5173o;
        if (z2 || !l1Var.n()) {
            if (l1Var.F()) {
                x1 x1Var = this.f5166h;
                if (x1Var.f5451g) {
                    x1Var.d();
                    k0Var = x1Var.f5449e;
                } else {
                    k0Var = null;
                }
            } else {
                k0Var = null;
            }
            a4.e eVar = this.f5163e;
            if (eVar != null) {
                l1Var.w(this.f5171m, k0Var, new c0.g1(23, eVar));
            }
            m(false);
        }
    }

    @Override // p1.d1
    public final void invalidate() {
        if (this.f5165g || this.f5167i) {
            return;
        }
        this.f5162d.invalidate();
        m(true);
    }

    @Override // p1.d1
    public final void j(x0.b bVar, boolean z2) {
        l1 l1Var = this.f5173o;
        k3.b bVar2 = this.f5170l;
        if (!z2) {
            y0.f0.c(bVar2.b(l1Var), bVar);
            return;
        }
        float[] fArrA = bVar2.a(l1Var);
        if (fArrA != null) {
            y0.f0.c(fArrA, bVar);
            return;
        }
        bVar.f6528a = 0.0f;
        bVar.f6529b = 0.0f;
        bVar.f6530c = 0.0f;
        bVar.f6531d = 0.0f;
    }

    @Override // p1.d1
    public final boolean k(long j5) {
        y0.j0 j0Var;
        float fD = x0.c.d(j5);
        float fE = x0.c.e(j5);
        l1 l1Var = this.f5173o;
        if (l1Var.r()) {
            if (0.0f > fD || fD >= l1Var.l() || 0.0f > fE || fE >= l1Var.e()) {
                return false;
            }
        } else if (l1Var.F()) {
            x1 x1Var = this.f5166h;
            if (x1Var.f5457m && (j0Var = x1Var.f5447c) != null) {
                return m0.r(j0Var, x0.c.d(j5), x0.c.e(j5));
            }
            return true;
        }
        return true;
    }

    @Override // p1.d1
    public final void l(y0.n0 n0Var) {
        a4.a aVar;
        int i5 = n0Var.f6650d | this.f5174p;
        int i6 = i5 & 4096;
        if (i6 != 0) {
            this.f5172n = n0Var.f6658l;
        }
        l1 l1Var = this.f5173o;
        boolean zF = l1Var.F();
        x1 x1Var = this.f5166h;
        boolean z2 = false;
        boolean z3 = zF && x1Var.f5451g;
        if ((i5 & 1) != 0) {
            l1Var.g(n0Var.f6651e);
        }
        if ((i5 & 2) != 0) {
            l1Var.k(n0Var.f6652f);
        }
        if ((i5 & 4) != 0) {
            l1Var.c(n0Var.f6653g);
        }
        if ((i5 & 8) != 0) {
            l1Var.i();
        }
        if ((i5 & 16) != 0) {
            l1Var.d();
        }
        if ((i5 & 32) != 0) {
            l1Var.D(n0Var.f6654h);
        }
        if ((i5 & 64) != 0) {
            l1Var.B(y0.l0.w(n0Var.f6655i));
        }
        if ((i5 & 128) != 0) {
            l1Var.I(y0.l0.w(n0Var.f6656j));
        }
        if ((i5 & 1024) != 0) {
            l1Var.j();
        }
        if ((i5 & 256) != 0) {
            l1Var.b();
        }
        if ((i5 & 512) != 0) {
            l1Var.f();
        }
        if ((i5 & 2048) != 0) {
            l1Var.m(n0Var.f6657k);
        }
        if (i6 != 0) {
            l1Var.x(y0.t0.a(this.f5172n) * l1Var.l());
            l1Var.C(y0.t0.b(this.f5172n) * l1Var.e());
        }
        boolean z4 = n0Var.f6660n;
        r3.c cVar = y0.l0.f6642a;
        boolean z5 = z4 && n0Var.f6659m != cVar;
        if ((i5 & 24576) != 0) {
            l1Var.H(z5);
            l1Var.y(n0Var.f6660n && n0Var.f6659m == cVar);
        }
        if ((131072 & i5) != 0) {
            l1Var.s();
        }
        if ((32768 & i5) != 0) {
            l1Var.A();
        }
        boolean zC = this.f5166h.c(n0Var.f6664r, n0Var.f6653g, z5, n0Var.f6654h, n0Var.f6661o);
        if (x1Var.f5450f) {
            l1Var.o(x1Var.b());
        }
        if (z5 && x1Var.f5451g) {
            z2 = true;
        }
        u uVar = this.f5162d;
        if (z3 == z2 && (!z2 || !zC)) {
            g3.f5214a.a(uVar);
        } else if (!this.f5165g && !this.f5167i) {
            uVar.invalidate();
            m(true);
        }
        if (!this.f5168j && l1Var.L() > 0.0f && (aVar = this.f5164f) != null) {
            aVar.a();
        }
        if ((i5 & 7963) != 0) {
            this.f5170l.c();
        }
        this.f5174p = n0Var.f6650d;
    }

    public final void m(boolean z2) {
        if (z2 != this.f5165g) {
            this.f5165g = z2;
            this.f5162d.s(this, z2);
        }
    }
}

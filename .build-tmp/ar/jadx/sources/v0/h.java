package v0;

import n1.e0;
import n1.g0;
import n1.h0;
import n1.p0;
import n1.v0;
import p1.f0;
import p1.m0;
import p1.w;
import p3.j;
import q3.t;
import r0.o;
import y0.m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h extends o implements w, p1.o {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public d1.b f6171q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f6172r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public r0.c f6173s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public n1.h f6174t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f6175u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public m f6176v;

    public static boolean A0(long j5) {
        if (x0.f.a(j5, 9205357640488583168L)) {
            return false;
        }
        float fD = x0.f.d(j5);
        return (Float.isInfinite(fD) || Float.isNaN(fD)) ? false : true;
    }

    public static boolean z0(long j5) {
        if (x0.f.a(j5, 9205357640488583168L)) {
            return false;
        }
        float fB = x0.f.b(j5);
        return (Float.isInfinite(fB) || Float.isNaN(fB)) ? false : true;
    }

    public final long B0(long j5) {
        boolean z2 = false;
        boolean z3 = k2.a.e(j5) && k2.a.d(j5);
        if (k2.a.g(j5) && k2.a.f(j5)) {
            z2 = true;
        }
        if ((!y0() && z3) || z2) {
            return k2.a.b(j5, k2.a.i(j5), 0, k2.a.h(j5), 0, 10);
        }
        long jD = this.f6171q.d();
        long jF = j.f(h1.c.q(j5, A0(jD) ? Math.round(x0.f.d(jD)) : k2.a.k(j5)), h1.c.p(j5, z0(jD) ? Math.round(x0.f.b(jD)) : k2.a.j(j5)));
        if (y0()) {
            long jF2 = j.f(!A0(this.f6171q.d()) ? x0.f.d(jF) : x0.f.d(this.f6171q.d()), !z0(this.f6171q.d()) ? x0.f.b(jF) : x0.f.b(this.f6171q.d()));
            jF = (x0.f.d(jF) == 0.0f || x0.f.b(jF) == 0.0f) ? 0L : v0.h(jF2, this.f6174t.a(jF2, jF));
        }
        return k2.a.b(j5, h1.c.q(j5, Math.round(x0.f.d(jF))), 0, h1.c.p(j5, Math.round(x0.f.b(jF))), 0, 10);
    }

    @Override // p1.w
    public final int a0(m0 m0Var, e0 e0Var, int i5) {
        if (!y0()) {
            return e0Var.c0(i5);
        }
        long jB0 = B0(h1.c.c(0, i5, 7));
        return Math.max(k2.a.k(jB0), e0Var.c0(i5));
    }

    @Override // p1.w
    public final g0 c(h0 h0Var, e0 e0Var, long j5) {
        n1.m0 m0VarC = e0Var.c(B0(j5));
        return h0Var.F(m0VarC.f4130d, m0VarC.f4131e, t.f5478d, new p0(m0VarC, 8));
    }

    @Override // p1.o
    public final void d(f0 f0Var) {
        a1.c cVar = f0Var.f4828d;
        long jD = this.f6171q.d();
        long jF = j.f(A0(jD) ? x0.f.d(jD) : x0.f.d(cVar.b()), z0(jD) ? x0.f.b(jD) : x0.f.b(cVar.b()));
        long jH = (x0.f.d(cVar.b()) == 0.0f || x0.f.b(cVar.b()) == 0.0f) ? 0L : v0.h(jF, this.f6174t.a(jF, cVar.b()));
        long jA = this.f6173s.a(a.a.f(Math.round(x0.f.d(jH)), Math.round(x0.f.b(jH))), a.a.f(Math.round(x0.f.d(cVar.b())), Math.round(x0.f.b(cVar.b()))), f0Var.getLayoutDirection());
        float f5 = (int) (jA >> 32);
        float f6 = (int) (jA & 4294967295L);
        ((a1.d) cVar.f27e.f23d).x(f5, f6);
        try {
            this.f6171q.c(f0Var, jH, this.f6175u, this.f6176v);
            ((a1.d) cVar.f27e.f23d).x(-f5, -f6);
            f0Var.c();
        } catch (Throwable th) {
            ((a1.d) cVar.f27e.f23d).x(-f5, -f6);
            throw th;
        }
    }

    @Override // p1.w
    public final int g0(m0 m0Var, e0 e0Var, int i5) {
        if (!y0()) {
            return e0Var.e0(i5);
        }
        long jB0 = B0(h1.c.c(i5, 0, 13));
        return Math.max(k2.a.j(jB0), e0Var.e0(i5));
    }

    @Override // p1.w
    public final int m0(m0 m0Var, e0 e0Var, int i5) {
        if (!y0()) {
            return e0Var.a0(i5);
        }
        long jB0 = B0(h1.c.c(0, i5, 7));
        return Math.max(k2.a.k(jB0), e0Var.a0(i5));
    }

    @Override // r0.o
    public final boolean o0() {
        return false;
    }

    public final String toString() {
        return "PainterModifier(painter=" + this.f6171q + ", sizeToIntrinsics=" + this.f6172r + ", alignment=" + this.f6173s + ", alpha=" + this.f6175u + ", colorFilter=" + this.f6176v + ')';
    }

    @Override // p1.w
    public final int x(m0 m0Var, e0 e0Var, int i5) {
        if (!y0()) {
            return e0Var.d(i5);
        }
        long jB0 = B0(h1.c.c(i5, 0, 13));
        return Math.max(k2.a.j(jB0), e0Var.d(i5));
    }

    public final boolean y0() {
        return this.f6172r && this.f6171q.d() != 9205357640488583168L;
    }
}

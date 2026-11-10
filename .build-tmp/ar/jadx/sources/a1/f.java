package a1;

import k2.k;
import p1.f0;
import y0.c0;
import y0.k0;
import y0.m;
import y0.p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public interface f extends k2.b {
    static /* synthetic */ void B(f fVar, long j5, long j6, int i5) {
        if ((i5 & 4) != 0) {
            j6 = l0(fVar.b(), 0L);
        }
        fVar.L(j5, 0L, j6, (i5 & 64) != 0 ? 3 : 0);
    }

    static /* synthetic */ void C(f fVar, long j5, float f5, long j6, int i5) {
        if ((i5 & 4) != 0) {
            j6 = fVar.O();
        }
        fVar.l(f5, j5, j6);
    }

    static void Q(f fVar, c0 c0Var, long j5, long j6, float f5, m mVar, int i5) {
        fVar.G(c0Var, 0L, j5, (i5 & 16) != 0 ? j5 : j6, (i5 & 32) != 0 ? 1.0f : f5, mVar, 1);
    }

    static /* synthetic */ void f(f fVar, k0 k0Var, p pVar, float f5, j jVar, int i5) {
        if ((i5 & 4) != 0) {
            f5 = 1.0f;
        }
        float f6 = f5;
        g gVar = jVar;
        if ((i5 & 8) != 0) {
            gVar = i.f33a;
        }
        fVar.V(k0Var, pVar, f6, gVar, (i5 & 32) != 0 ? 3 : 0);
    }

    static long l0(long j5, long j6) {
        return p3.j.f(x0.f.d(j5) - x0.c.d(j6), x0.f.b(j5) - x0.c.e(j6));
    }

    static void n(f0 f0Var, p pVar, long j5, long j6, float f5, g gVar, int i5) {
        if ((i5 & 2) != 0) {
            j5 = 0;
        }
        long j7 = j5;
        if ((i5 & 4) != 0) {
            j6 = l0(f0Var.f4828d.b(), j7);
        }
        f0Var.e(pVar, j7, j6, (i5 & 8) != 0 ? 1.0f : f5, (i5 & 16) != 0 ? i.f33a : gVar);
    }

    static void p(f0 f0Var, p pVar, long j5, long j6, long j7, g gVar, int i5) {
        if ((i5 & 2) != 0) {
            j5 = 0;
        }
        long j8 = j5;
        f0Var.g(pVar, j8, (i5 & 4) != 0 ? l0(f0Var.f4828d.b(), j8) : j6, j7, 1.0f, (i5 & 32) != 0 ? i.f33a : gVar);
    }

    b A();

    void G(c0 c0Var, long j5, long j6, long j7, float f5, m mVar, int i5);

    void L(long j5, long j6, long j7, int i5);

    default long O() {
        return p3.j.w(A().t());
    }

    void V(k0 k0Var, p pVar, float f5, g gVar, int i5);

    void X(long j5, long j6, long j7, float f5);

    default long b() {
        return A().t();
    }

    k getLayoutDirection();

    void l(float f5, long j5, long j6);

    void q(long j5, long j6, long j7, long j8);

    void t(k0 k0Var, long j5, g gVar);
}

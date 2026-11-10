package v;

import f0.b2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f5874a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f5875b;

    static {
        float f5 = 25;
        f5874a = f5;
        f5875b = (f5 * 2.0f) / 2.4142137f;
    }

    public static final void a(z.l lVar, r0.p pVar, long j5, f0.p pVar2, int i5) {
        int i6;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(1776202187);
        int i7 = (vVar.f(lVar) ? 4 : 2) | i5 | (vVar.f(pVar) ? 32 : 16) | 128;
        if ((i7 & 147) == 146 && vVar.z()) {
            vVar.Q();
        } else {
            vVar.S();
            if ((i5 & 1) == 0 || vVar.x()) {
                i6 = i7 & (-897);
                j5 = 9205357640488583168L;
            } else {
                vVar.Q();
                i6 = i7 & (-897);
            }
            vVar.q();
            int i8 = i6 & 14;
            boolean z2 = i8 == 4;
            Object objK = vVar.K();
            if (z2 || objK == f0.o.f2281a) {
                objK = new c0.g1(24, lVar);
                vVar.f0(objK);
            }
            p3.j.a(lVar, r0.b.f5654e, n0.i.c(-1653527038, new d0.p(1, j5, w1.k.a(pVar, (a4.c) objK)), vVar), vVar, i8 | 432);
        }
        long j6 = j5;
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new c0.m(lVar, pVar, j6, i5);
        }
    }

    public static final void b(r0.p pVar, f0.p pVar2, int i5, int i6) {
        int i7;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(694251107);
        int i8 = i6 & 1;
        if (i8 != 0) {
            i7 = i5 | 6;
        } else if ((i5 & 6) == 0) {
            i7 = (vVar.f(pVar) ? 4 : 2) | i5;
        } else {
            i7 = i5;
        }
        if ((i7 & 3) == 2 && vVar.z()) {
            vVar.Q();
        } else {
            if (i8 != 0) {
                pVar = r0.m.f5680a;
            }
            r.c.a(vVar, r0.a.a(androidx.compose.foundation.layout.c.i(pVar, f5875b, f5874a), c.f5866e));
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new a(pVar, i5, i6);
        }
    }
}

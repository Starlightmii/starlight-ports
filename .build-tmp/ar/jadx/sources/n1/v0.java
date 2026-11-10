package n1;

import f0.b2;
import f0.w1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class v0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h f4177a = new h(2);

    public static final long a(float f5, float f6) {
        long jFloatToRawIntBits = (((long) Float.floatToRawIntBits(f6)) & 4294967295L) | (Float.floatToRawIntBits(f5) << 32);
        int i5 = s0.f4153b;
        return jFloatToRawIntBits;
    }

    public static final void b(y0 y0Var, r0.p pVar, a4.e eVar, f0.p pVar2, int i5) {
        int i6;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(-511989831);
        if ((i5 & 6) == 0) {
            i6 = (vVar.h(y0Var) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.f(pVar) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar.h(eVar) ? 256 : 128;
        }
        if ((i6 & 147) == 146 && vVar.z()) {
            vVar.Q();
        } else {
            int iHashCode = Long.hashCode(vVar.S);
            f0.t tVarU = f0.l.u(vVar);
            r0.p pVarC = r0.a.c(vVar, pVar);
            w1 w1VarL = vVar.l();
            p1.n nVar = p1.n.f4930g;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(y0Var.f4192b, vVar, y0Var);
            f0.l.x(y0Var.f4193c, vVar, tVarU);
            f0.l.x(y0Var.f4194d, vVar, eVar);
            p1.j.f4892c.getClass();
            f0.l.x(p1.i.f4869d, vVar, w1VarL);
            f0.l.x(p1.i.f4868c, vVar, pVarC);
            p1.h hVar = p1.i.f4871f;
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar, iHashCode, hVar);
            }
            vVar.p(true);
            if (vVar.z()) {
                vVar.V(-26502501);
                vVar.p(false);
            } else {
                vVar.V(-26580342);
                boolean zH = vVar.h(y0Var);
                Object objK = vVar.K();
                if (zH || objK == f0.o.f2281a) {
                    objK = new b0.a(13, y0Var);
                    vVar.f0(objK);
                }
                f0.l.g((a4.a) objK, vVar);
                vVar.p(false);
            }
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new u0(y0Var, pVar, eVar, i5, 0);
        }
    }

    public static final void c(r0.p pVar, a4.e eVar, f0.p pVar2, int i5, int i6) {
        int i7;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(-1298353104);
        int i8 = i6 & 1;
        if (i8 != 0) {
            i7 = i5 | 6;
        } else if ((i5 & 6) == 0) {
            i7 = (vVar.f(pVar) ? 4 : 2) | i5;
        } else {
            i7 = i5;
        }
        int i9 = i7 | (vVar.h(eVar) ? 32 : 16);
        if ((i9 & 19) == 18 && vVar.z()) {
            vVar.Q();
        } else {
            if (i8 != 0) {
                pVar = r0.m.f5680a;
            }
            Object objK = vVar.K();
            if (objK == f0.o.f2281a) {
                objK = new y0();
                vVar.f0(objK);
            }
            b((y0) objK, pVar, eVar, vVar, (i9 << 3) & 1008);
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new t0(pVar, eVar, i5, i6);
        }
    }

    public static final x0.d d(p pVar) {
        p pVarI = pVar.i();
        return pVarI != null ? pVarI.k(pVar, true) : new x0.d(0.0f, 0.0f, (int) (pVar.Y() >> 32), (int) (pVar.Y() & 4294967295L));
    }

    public static final x0.d e(p pVar) {
        p pVarF = f(pVar);
        float fY = (int) (pVarF.Y() >> 32);
        float fY2 = (int) (pVarF.Y() & 4294967295L);
        x0.d dVarK = f(pVar).k(pVar, true);
        float f5 = dVarK.f6534a;
        if (f5 < 0.0f) {
            f5 = 0.0f;
        }
        if (f5 > fY) {
            f5 = fY;
        }
        float f6 = dVarK.f6535b;
        if (f6 < 0.0f) {
            f6 = 0.0f;
        }
        if (f6 > fY2) {
            f6 = fY2;
        }
        float f7 = dVarK.f6536c;
        if (f7 < 0.0f) {
            f7 = 0.0f;
        }
        if (f7 <= fY) {
            fY = f7;
        }
        float f8 = dVarK.f6537d;
        float f9 = f8 >= 0.0f ? f8 : 0.0f;
        if (f9 <= fY2) {
            fY2 = f9;
        }
        if (f5 == fY || f6 == fY2) {
            return x0.d.f6533e;
        }
        long jG = pVarF.g(p3.j.b(f5, f6));
        long jG2 = pVarF.g(p3.j.b(fY, f6));
        long jG3 = pVarF.g(p3.j.b(fY, fY2));
        long jG4 = pVarF.g(p3.j.b(f5, fY2));
        float fD = x0.c.d(jG);
        float fD2 = x0.c.d(jG2);
        float fD3 = x0.c.d(jG4);
        float fD4 = x0.c.d(jG3);
        float fMin = Math.min(fD, Math.min(fD2, Math.min(fD3, fD4)));
        float fMax = Math.max(fD, Math.max(fD2, Math.max(fD3, fD4)));
        float fE = x0.c.e(jG);
        float fE2 = x0.c.e(jG2);
        float fE3 = x0.c.e(jG4);
        float fE4 = x0.c.e(jG3);
        return new x0.d(fMin, Math.min(fE, Math.min(fE2, Math.min(fE3, fE4))), fMax, Math.max(fE, Math.max(fE2, Math.max(fE3, fE4))));
    }

    public static final p f(p pVar) {
        p pVar2;
        p pVarI = pVar.i();
        while (true) {
            p pVar3 = pVarI;
            pVar2 = pVar;
            pVar = pVar3;
            if (pVar == null) {
                break;
            }
            pVarI = pVar.i();
        }
        p1.y0 y0Var = pVar2 instanceof p1.y0 ? (p1.y0) pVar2 : null;
        if (y0Var == null) {
            return pVar2;
        }
        p1.y0 y0Var2 = y0Var.f5000q;
        while (true) {
            p1.y0 y0Var3 = y0Var2;
            p1.y0 y0Var4 = y0Var;
            y0Var = y0Var3;
            if (y0Var == null) {
                return y0Var4;
            }
            y0Var2 = y0Var.f5000q;
        }
    }

    public static final p1.n0 g(p1.n0 n0Var) {
        p1.d0 d0Var = n0Var.f4932o.f4998o;
        while (true) {
            p1.d0 d0VarR = d0Var.r();
            p1.d0 d0Var2 = null;
            if ((d0VarR != null ? d0VarR.f4782f : null) == null) {
                p1.n0 n0VarJ0 = d0Var.f4801y.f4970c.J0();
                b4.l.b(n0VarJ0);
                return n0VarJ0;
            }
            p1.d0 d0VarR2 = d0Var.r();
            if (d0VarR2 != null) {
                d0Var2 = d0VarR2.f4782f;
            }
            b4.l.b(d0Var2);
            p1.d0 d0VarR3 = d0Var.r();
            b4.l.b(d0VarR3);
            d0Var = d0VarR3.f4782f;
            b4.l.b(d0Var);
        }
    }

    public static final long h(long j5, long j6) {
        float fD = x0.f.d(j5);
        long j7 = s0.f4152a;
        if (j6 == j7) {
            d4.a.L("ScaleFactor is unspecified");
            throw null;
        }
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j6 >> 32)) * fD;
        float fB = x0.f.b(j5);
        if (j6 != j7) {
            return p3.j.f(fIntBitsToFloat, Float.intBitsToFloat((int) (j6 & 4294967295L)) * fB);
        }
        d4.a.L("ScaleFactor is unspecified");
        throw null;
    }
}

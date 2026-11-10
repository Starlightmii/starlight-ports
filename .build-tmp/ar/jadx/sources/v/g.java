package v;

import c0.t2;
import f0.b2;
import f0.i1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class g {
    static {
        float f5 = 40;
        a.a.d(f5, f5);
    }

    public static final void a(String str, a4.c cVar, r0.p pVar, boolean z2, y1.h0 h0Var, n0 n0Var, m0 m0Var, boolean z3, int i5, int i6, e2.f0 f0Var, a4.c cVar2, q.k kVar, y0.s0 s0Var, n0.d dVar, f0.p pVar2, int i7) {
        a4.c cVar3;
        f0.v vVar;
        a4.c cVar4;
        f0.v vVar2 = (f0.v) pVar2;
        vVar2.X(945255183);
        int i8 = i7 | (vVar2.f(str) ? 4 : 2) | (vVar2.h(cVar) ? 32 : 16) | (vVar2.f(pVar) ? 256 : 128) | (vVar2.g(z2) ? 2048 : 1024) | (vVar2.g(false) ? 16384 : 8192) | (vVar2.f(h0Var) ? 131072 : 65536) | (vVar2.f(n0Var) ? 1048576 : 524288) | (vVar2.f(m0Var) ? 8388608 : 4194304) | (vVar2.g(z3) ? 67108864 : 33554432) | (vVar2.d(i5) ? 536870912 : 268435456);
        int i9 = 196608 | (vVar2.d(i6) ? 4 : 2) | (vVar2.f(f0Var) ? 32 : 16) | 384 | (vVar2.f(kVar) ? 2048 : 1024) | (vVar2.f(s0Var) ? 16384 : 8192);
        if ((306783379 & i8) == 306783378 && (i9 & 74899) == 74898 && vVar2.z()) {
            vVar2.Q();
            cVar4 = cVar2;
            vVar = vVar2;
        } else {
            vVar2.S();
            if ((i7 & 1) == 0 || vVar2.x()) {
                cVar3 = e.f5883f;
            } else {
                vVar2.Q();
                cVar3 = cVar2;
            }
            vVar2.q();
            Object objK = vVar2.K();
            f0.h hVar = f0.o.f2281a;
            if (objK == hVar) {
                objK = f0.l.s(new e2.x(str, 0L, 6));
                vVar2.f0(objK);
            }
            i1 i1Var = (i1) objK;
            e2.x xVar = (e2.x) i1Var.getValue();
            e2.x xVar2 = new e2.x(new y1.f(str, null, 6), xVar.f2063b, xVar.f2064c);
            boolean zF = vVar2.f(xVar2);
            Object objK2 = vVar2.K();
            if (zF || objK2 == hVar) {
                objK2 = new t2(xVar2, 9, i1Var);
                vVar2.f0(objK2);
            }
            f0.l.g((a4.a) objK2, vVar2);
            boolean z4 = (i8 & 14) == 4;
            Object objK3 = vVar2.K();
            if (z4 || objK3 == hVar) {
                objK3 = f0.l.s(str);
                vVar2.f0(objK3);
            }
            i1 i1Var2 = (i1) objK3;
            n0Var.getClass();
            e2.n nVar = new e2.n(z3, 0, true, 1, 1, f2.b.f2445f);
            boolean z5 = !z3;
            int i10 = z3 ? 1 : i6;
            int i11 = z3 ? 1 : i5;
            boolean zF2 = ((i8 & 112) == 32) | vVar2.f(i1Var2);
            Object objK4 = vVar2.K();
            if (zF2 || objK4 == hVar) {
                objK4 = new c.b(cVar, i1Var, i1Var2, 8);
                vVar2.f0(objK4);
            }
            int i12 = i9 << 9;
            vVar = vVar2;
            a4.c cVar5 = cVar3;
            l0.c(xVar2, (a4.c) objK4, pVar, h0Var, f0Var, cVar5, kVar, s0Var, z5, i11, i10, nVar, m0Var, z2, dVar, vVar, (i8 & 896) | ((i8 >> 6) & 7168) | (i12 & 57344) | 196608 | (3670016 & i12) | (i12 & 29360128), ((i8 >> 15) & 896) | (i8 & 7168) | (57344 & i8) | 196608);
            cVar4 = cVar5;
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new f(str, cVar, pVar, z2, h0Var, n0Var, m0Var, z3, i5, i6, f0Var, cVar4, kVar, s0Var, dVar, i7);
        }
    }
}

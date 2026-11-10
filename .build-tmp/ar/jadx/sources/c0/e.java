package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final n2.t f809a = new n2.t(14);

    public static final void a(boolean z2, a4.a aVar, r0.p pVar, long j5, n.h1 h1Var, n2.t tVar, y0.q0 q0Var, long j6, float f5, float f6, n0.d dVar, f0.p pVar2, int i5) {
        long jFloatToRawIntBits;
        n2.t tVar2;
        n.h1 h1Var2;
        y0.q0 q0Var2;
        long j7;
        float f7;
        float f8;
        r0.p pVar3;
        long j8;
        n2.t tVar3;
        r0.p pVar4;
        n.h1 h1Var3;
        y0.q0 q0Var3;
        long j9;
        float f9;
        float f10;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(1431928300);
        if (((i5 | (vVar.g(z2) ? 4 : 2) | 910896512) & 306783379) == 306783378 && vVar.z()) {
            vVar.Q();
            pVar4 = pVar;
            j8 = j5;
            h1Var3 = h1Var;
            tVar3 = tVar;
            q0Var3 = q0Var;
            j9 = j6;
            f9 = f5;
            f10 = f6;
        } else {
            vVar.S();
            int i6 = 0;
            if ((i5 & 1) == 0 || vVar.x()) {
                float f11 = 0;
                jFloatToRawIntBits = (((long) Float.floatToRawIntBits(f11)) << 32) | (((long) Float.floatToRawIntBits(f11)) & 4294967295L);
                n.h1 h1VarL = h1.c.L(vVar);
                float f12 = u0.f1331a;
                y0.q0 q0VarA = s2.a(vVar, e0.i.f1735b);
                long jD = u.d(vVar, 37);
                float f13 = u0.f1331a;
                float f14 = u0.f1332b;
                r0.m mVar = r0.m.f5680a;
                tVar2 = f809a;
                h1Var2 = h1VarL;
                q0Var2 = q0VarA;
                j7 = jD;
                f7 = f13;
                f8 = f14;
                pVar3 = mVar;
            } else {
                vVar.Q();
                pVar3 = pVar;
                jFloatToRawIntBits = j5;
                h1Var2 = h1Var;
                tVar2 = tVar;
                q0Var2 = q0Var;
                j7 = j6;
                f7 = f5;
                f8 = f6;
            }
            vVar.q();
            Object objK = vVar.K();
            f0.h hVar = f0.o.f2281a;
            if (objK == hVar) {
                objK = new m.c0(Boolean.FALSE);
                vVar.f0(objK);
            }
            m.c0 c0Var = (m.c0) objK;
            c0Var.f3641c.setValue(Boolean.valueOf(z2));
            if (((Boolean) c0Var.f3640b.getValue()).booleanValue() || ((Boolean) c0Var.f3641c.getValue()).booleanValue()) {
                Object objK2 = vVar.K();
                if (objK2 == hVar) {
                    objK2 = f0.l.s(new y0.t0(y0.t0.f6686b));
                    vVar.f0(objK2);
                }
                f0.i1 i1Var = (f0.i1) objK2;
                k2.b bVar = (k2.b) vVar.j(q1.j1.f5237f);
                boolean zF = vVar.f(bVar);
                Object objK3 = vVar.K();
                if (zF || objK3 == hVar) {
                    objK3 = new d0.d(jFloatToRawIntBits, bVar, new c(i1Var, i6));
                    vVar.f0(objK3);
                }
                n2.j.a((d0.d) objK3, aVar, tVar2, n0.i.c(2126968933, new a(pVar3, c0Var, i1Var, h1Var2, q0Var2, j7, f7, f8, dVar), vVar), vVar, 3504, 0);
            }
            j8 = jFloatToRawIntBits;
            tVar3 = tVar2;
            pVar4 = pVar3;
            h1Var3 = h1Var2;
            q0Var3 = q0Var2;
            j9 = j7;
            f9 = f7;
            f10 = f8;
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new b(z2, aVar, pVar4, j8, h1Var3, tVar3, q0Var3, j9, f9, f10, dVar, i5);
        }
    }

    public static final void b(n0.d dVar, a4.a aVar, r0.p pVar, boolean z2, v0 v0Var, r.l0 l0Var, f0.p pVar2, int i5) {
        v0 v0Var2;
        int i6;
        boolean z3;
        r0.p pVar3;
        r.l0 l0Var2;
        v0 v0Var3;
        r0.p pVar4;
        boolean z4;
        v0 v0Var4;
        r.l0 l0Var3;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(1826340448);
        int i7 = i5 | (vVar.h(aVar) ? 32 : 16) | 113995136;
        if ((38347923 & i7) == 38347922 && vVar.z()) {
            vVar.Q();
            pVar4 = pVar;
            z4 = z2;
            v0Var4 = v0Var;
            l0Var3 = l0Var;
        } else {
            vVar.S();
            if ((i5 & 1) == 0 || vVar.x()) {
                float f5 = u0.f1331a;
                s sVar = (s) vVar.j(u.f1329a);
                v0 v0Var5 = sVar.N;
                if (v0Var5 == null) {
                    v0Var2 = new v0(u.c(sVar, e0.h.f1731g), u.c(sVar, e0.h.f1732h), u.c(sVar, e0.h.f1733i), y0.u.b(e0.h.f1725a, u.c(sVar, e0.h.f1728d)), y0.u.b(e0.h.f1726b, u.c(sVar, e0.h.f1729e)), y0.u.b(e0.h.f1727c, u.c(sVar, e0.h.f1730f)));
                    sVar.N = v0Var2;
                } else {
                    v0Var2 = v0Var5;
                }
                i6 = i7 & (-3670017);
                r.m0 m0Var = u0.f1333c;
                z3 = true;
                pVar3 = r0.m.f5680a;
                l0Var2 = m0Var;
                v0Var3 = v0Var2;
            } else {
                vVar.Q();
                i6 = i7 & (-3670017);
                pVar3 = pVar;
                z3 = z2;
                v0Var3 = v0Var;
                l0Var2 = l0Var;
            }
            vVar.q();
            a1.b(dVar, aVar, pVar3, z3, v0Var3, l0Var2, vVar, i6 & 268435454);
            pVar4 = pVar3;
            z4 = z3;
            v0Var4 = v0Var3;
            l0Var3 = l0Var2;
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new d(dVar, aVar, pVar4, z4, v0Var4, l0Var3, i5);
        }
    }
}

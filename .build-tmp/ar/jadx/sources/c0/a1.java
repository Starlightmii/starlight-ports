package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f724a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f725b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final float f726c = 12;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final float f727d = 8;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final float f728e = 112;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final float f729f = 280;

    static {
        float f5 = 48;
        f724a = f5;
        f725b = f5;
    }

    public static final void a(r0.p pVar, m.c0 c0Var, f0.i1 i1Var, n.h1 h1Var, y0.q0 q0Var, long j5, float f5, float f6, n0.d dVar, f0.p pVar2, int i5) {
        f0.v vVar;
        f0.v vVar2 = (f0.v) pVar2;
        vVar2.X(-151448888);
        int i6 = i5 | (vVar2.f(pVar) ? 4 : 2) | (vVar2.f(c0Var) ? 32 : 16) | (vVar2.f(h1Var) ? 2048 : 1024) | (vVar2.f(q0Var) ? 16384 : 8192) | (vVar2.e(j5) ? 131072 : 65536) | (vVar2.c(f5) ? 1048576 : 524288) | (vVar2.c(f6) ? 8388608 : 4194304) | (vVar2.f(null) ? 67108864 : 33554432) | (vVar2.h(dVar) ? 536870912 : 268435456);
        if ((i6 & 306783379) == 306783378 && vVar2.z()) {
            vVar2.Q();
            vVar = vVar2;
        } else {
            int i7 = ((i6 >> 3) & 14) | 48;
            int i8 = m.y0.f3804a;
            boolean z2 = (((i7 & 14) ^ 6) > 4 && vVar2.f(c0Var)) || (i7 & 6) == 4;
            Object objK = vVar2.K();
            Object obj = f0.o.f2281a;
            if (z2 || objK == obj) {
                objK = new m.s0(c0Var);
                vVar2.f0(objK);
            }
            m.s0 s0Var = (m.s0) objK;
            vVar2.V(1030744251);
            s0Var.a(c0Var.f3641c.getValue(), vVar2, 0);
            f0.s1 s1Var = s0Var.f3752b;
            vVar2.p(false);
            boolean zF = vVar2.f(s0Var);
            Object objK2 = vVar2.K();
            if (zF || objK2 == obj) {
                objK2 = new m.x0(s0Var, 0);
                vVar2.f0(objK2);
            }
            f0.l.c(s0Var, (a4.c) objK2, vVar2);
            m.a1 a1Var = m.b1.f3620a;
            boolean zBooleanValue = ((Boolean) s0Var.c()).booleanValue();
            vVar2.V(2139028452);
            float f7 = zBooleanValue ? 1.0f : 0.8f;
            vVar2.p(false);
            Float fValueOf = Float.valueOf(f7);
            boolean zBooleanValue2 = ((Boolean) s1Var.getValue()).booleanValue();
            vVar2.V(2139028452);
            float f8 = zBooleanValue2 ? 1.0f : 0.8f;
            vVar2.p(false);
            Float fValueOf2 = Float.valueOf(f8);
            m.p0 p0VarE = s0Var.e();
            vVar2.V(1033023423);
            Object obj2 = Boolean.FALSE;
            Object obj3 = Boolean.TRUE;
            m.z0 z0VarH = p0VarE.a(obj2, obj3) ? m.d.h(120, 2, m.w.f3798b) : m.d.h(1, 4, null);
            vVar2.p(false);
            m.q0 q0VarA = m.y0.a(s0Var, fValueOf, fValueOf2, z0VarH, a1Var, vVar2, 0);
            boolean zBooleanValue3 = ((Boolean) s0Var.c()).booleanValue();
            vVar2.V(-249413128);
            float f9 = zBooleanValue3 ? 1.0f : 0.0f;
            vVar2.p(false);
            Float fValueOf3 = Float.valueOf(f9);
            boolean zBooleanValue4 = ((Boolean) s1Var.getValue()).booleanValue();
            vVar2.V(-249413128);
            float f10 = zBooleanValue4 ? 1.0f : 0.0f;
            vVar2.p(false);
            Float fValueOf4 = Float.valueOf(f10);
            m.p0 p0VarE2 = s0Var.e();
            vVar2.V(-1355418157);
            m.z0 z0VarH2 = p0VarE2.a(obj2, obj3) ? m.d.h(30, 6, null) : m.d.h(75, 6, null);
            vVar2.p(false);
            m.q0 q0VarA2 = m.y0.a(s0Var, fValueOf3, fValueOf4, z0VarH2, a1Var, vVar2, 0);
            boolean zBooleanValue5 = ((Boolean) vVar2.j(q1.u1.f5422a)).booleanValue();
            boolean zG = vVar2.g(zBooleanValue5) | vVar2.f(q0VarA) | ((i6 & 112) == 32) | vVar2.f(q0VarA2);
            Object objK3 = vVar2.K();
            if (zG || objK3 == obj) {
                Object w0Var = new w0(zBooleanValue5, c0Var, i1Var, q0VarA, q0VarA2);
                vVar2.f0(w0Var);
                objK3 = w0Var;
            }
            int i9 = i6 >> 9;
            int i10 = i6 >> 6;
            o3.a(androidx.compose.ui.graphics.a.a(r0.m.f5680a, (a4.c) objK3), q0Var, j5, 0L, f5, f6, n0.i.c(1573559053, new x0(pVar, h1Var, dVar, 0), vVar2), vVar2, (i9 & 896) | (i9 & 112) | 12582912 | (57344 & i10) | (458752 & i10) | (i10 & 3670016), 8);
            vVar = vVar2;
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new a(pVar, c0Var, i1Var, h1Var, q0Var, j5, f5, f6, dVar, i5);
        }
    }

    public static final void b(n0.d dVar, a4.a aVar, r0.p pVar, boolean z2, v0 v0Var, r.l0 l0Var, f0.p pVar2, int i5) {
        int i6;
        boolean z3 = z2;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(-1564716777);
        if ((i5 & 6) == 0) {
            i6 = (vVar.h(dVar) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.h(aVar) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar.f(pVar) ? 256 : 128;
        }
        if ((i5 & 3072) == 0) {
            i6 |= vVar.h(null) ? 2048 : 1024;
        }
        if ((i5 & 24576) == 0) {
            i6 |= vVar.h(null) ? 16384 : 8192;
        }
        if ((196608 & i5) == 0) {
            i6 |= vVar.g(z3) ? 131072 : 65536;
        }
        if ((1572864 & i5) == 0) {
            i6 |= vVar.f(v0Var) ? 1048576 : 524288;
        }
        if ((12582912 & i5) == 0) {
            i6 |= vVar.f(l0Var) ? 8388608 : 4194304;
        }
        if ((100663296 & i5) == 0) {
            i6 |= vVar.f(null) ? 67108864 : 33554432;
        }
        if ((i6 & 38347923) == 38347922 && vVar.z()) {
            vVar.Q();
        } else {
            r0.p pVarD = androidx.compose.foundation.a.d(pVar, null, i2.a(true, 0.0f, 0L, vVar, 6, 6), z3, null, aVar, 24);
            z3 = z3;
            r0.p pVarE = androidx.compose.foundation.layout.a.e(androidx.compose.foundation.layout.c.j(pVarD.e(androidx.compose.foundation.layout.c.f113a), f728e, f725b, f729f, Float.NaN), l0Var);
            r.s0 s0VarA = r.q0.a(r.j.f5571a, r0.b.f5663n, vVar, 48);
            int iHashCode = Long.hashCode(vVar.S);
            f0.w1 w1VarL = vVar.l();
            r0.p pVarC = r0.a.c(vVar, pVarE);
            p1.j.f4892c.getClass();
            p1.n nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(p1.i.f4870e, vVar, s0VarA);
            f0.l.x(p1.i.f4869d, vVar, w1VarL);
            p1.h hVar = p1.i.f4871f;
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar, iHashCode, hVar);
            }
            f0.l.x(p1.i.f4868c, vVar, pVarC);
            n4.a(((s4) vVar.j(t4.f1328a)).f1292m, n0.i.c(1065051884, new y0(v0Var, z3, dVar), vVar), vVar, 48);
            vVar.p(true);
        }
        f0.b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new z0(dVar, aVar, pVar, z3, v0Var, l0Var, i5);
        }
    }
}

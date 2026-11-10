package androidx.compose.material3;

import a4.c;
import androidx.compose.foundation.BorderModifierNodeElement;
import androidx.compose.foundation.d;
import androidx.compose.foundation.selection.b;
import c0.i2;
import c0.p3;
import c0.q3;
import c0.r0;
import c0.r3;
import c0.s;
import c0.s2;
import c0.u;
import e0.o;
import f0.b2;
import f0.h3;
import f0.l;
import f0.w1;
import m.f0;
import m.v;
import m.z0;
import p1.h;
import p1.i;
import p1.n;
import q.j;
import q.k;
import r0.m;
import r0.p;
import w1.f;
import y0.l0;
import y0.q0;
import y0.s0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f152a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f153b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final float f154c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final float f155d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final float f156e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final f0 f157f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final z0 f158g;

    static {
        float f5 = o.f1787b;
        f152a = f5;
        f153b = o.f1792g;
        f154c = o.f1791f;
        float f6 = o.f1789d;
        f155d = f6;
        f156e = (f6 - f5) / 2;
        f157f = new f0();
        f158g = new z0(100, (v) null, 6);
    }

    public static final void a(boolean z2, c cVar, p pVar, boolean z3, p3 p3Var, f0.p pVar2, int i5) {
        int i6;
        p3 p3Var2;
        int i7;
        p pVar3;
        p3 p3Var3;
        p pVar4;
        p3 p3Var4;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(1580463220);
        if ((i5 & 6) == 0) {
            i6 = (vVar.g(z2) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.h(cVar) ? 32 : 16;
        }
        int i8 = i6 | 3456;
        if ((i5 & 24576) == 0) {
            i8 |= vVar.g(z3) ? 16384 : 8192;
        }
        if ((196608 & i5) == 0) {
            i8 |= 65536;
        }
        int i9 = i8 | 1572864;
        if ((599187 & i9) == 599186 && vVar.z()) {
            vVar.Q();
            pVar4 = pVar;
            p3Var4 = p3Var;
        } else {
            vVar.S();
            int i10 = i5 & 1;
            p pVarB = m.f5680a;
            if (i10 == 0 || vVar.x()) {
                s sVar = (s) vVar.j(u.f1329a);
                p3 p3Var5 = sVar.P;
                long j5 = sVar.f1259p;
                if (p3Var5 == null) {
                    float f5 = o.f1786a;
                    long jC = u.c(sVar, 10);
                    long jC2 = u.c(sVar, 26);
                    long j6 = y0.u.f6693f;
                    p3Var2 = new p3(jC, jC2, j6, u.c(sVar, 11), u.c(sVar, 24), u.c(sVar, 39), u.c(sVar, 24), u.c(sVar, 39), l0.l(y0.u.b(1.0f, u.c(sVar, 35)), j5), l0.l(y0.u.b(0.12f, u.c(sVar, 18)), j5), j6, l0.l(y0.u.b(0.38f, u.c(sVar, 18)), j5), l0.l(y0.u.b(0.38f, u.c(sVar, 18)), j5), l0.l(y0.u.b(0.12f, u.c(sVar, 39)), j5), l0.l(y0.u.b(0.12f, u.c(sVar, 18)), j5), l0.l(y0.u.b(0.38f, u.c(sVar, 39)), j5));
                    sVar.P = p3Var2;
                } else {
                    p3Var2 = p3Var5;
                }
                i7 = i9 & (-458753);
                pVar3 = pVarB;
                p3Var3 = p3Var2;
            } else {
                vVar.Q();
                i7 = i9 & (-458753);
                pVar3 = pVar;
                p3Var3 = p3Var;
            }
            int i11 = i7;
            vVar.q();
            vVar.V(783532531);
            Object objK = vVar.K();
            if (objK == f0.o.f2281a) {
                objK = new k();
                vVar.f0(objK);
            }
            k kVar = (k) objK;
            vVar.p(false);
            if (cVar != null) {
                h3 h3Var = r0.f1218a;
                pVarB = b.b(MinimumInteractiveModifier.f149a, z2, kVar, z3, new f(2), cVar);
            }
            p pVarF = androidx.compose.foundation.layout.c.f(androidx.compose.foundation.layout.c.m(pVar3.e(pVarB), r0.b.f5657h), f154c, f155d);
            float f6 = o.f1786a;
            int i12 = i11 << 3;
            b(pVarF, z2, z3, p3Var3, kVar, s2.a(vVar, 5), vVar, (i12 & 112) | ((i11 >> 6) & 896) | (i12 & 57344));
            pVar4 = pVar3;
            p3Var4 = p3Var3;
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new q3(z2, cVar, pVar4, z3, p3Var4, i5);
        }
    }

    public static final void b(p pVar, boolean z2, boolean z3, p3 p3Var, j jVar, q0 q0Var, f0.p pVar2, int i5) {
        int i6;
        long j5;
        long j6;
        long j7;
        long j8;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(-1594099146);
        if ((i5 & 6) == 0) {
            i6 = (vVar.f(pVar) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.g(z2) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar.g(z3) ? 256 : 128;
        }
        if ((i5 & 3072) == 0) {
            i6 |= vVar.f(p3Var) ? 2048 : 1024;
        }
        if ((i5 & 24576) == 0) {
            i6 |= vVar.h(null) ? 16384 : 8192;
        }
        if ((196608 & i5) == 0) {
            i6 |= vVar.f(jVar) ? 131072 : 65536;
        }
        if ((1572864 & i5) == 0) {
            i6 |= vVar.f(q0Var) ? 1048576 : 524288;
        }
        if ((i6 & 599187) == 599186 && vVar.z()) {
            vVar.Q();
        } else {
            if (z3) {
                j5 = z2 ? p3Var.f1162b : p3Var.f1166f;
            } else {
                j5 = z2 ? p3Var.f1170j : p3Var.f1174n;
            }
            if (z3) {
                j6 = z2 ? p3Var.f1161a : p3Var.f1165e;
            } else {
                j6 = z2 ? p3Var.f1169i : p3Var.f1173m;
            }
            float f5 = o.f1786a;
            q0 q0VarA = s2.a(vVar, 5);
            float f6 = o.f1790e;
            if (z3) {
                j7 = j5;
                j8 = z2 ? p3Var.f1163c : p3Var.f1167g;
            } else {
                j7 = j5;
                j8 = z2 ? p3Var.f1171k : p3Var.f1175o;
            }
            p pVarB = androidx.compose.foundation.a.b(pVar.e(new BorderModifierNodeElement(f6, new s0(j8), q0VarA)), j7, q0VarA);
            n1.f0 f0VarE = r.o.e(r0.b.f5653d, false);
            int iHashCode = Long.hashCode(vVar.S);
            w1 w1VarL = vVar.l();
            p pVarC = r0.a.c(vVar, pVarB);
            p1.j.f4892c.getClass();
            n nVar = i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            h hVar = i.f4870e;
            l.x(hVar, vVar, f0VarE);
            h hVar2 = i.f4869d;
            l.x(hVar2, vVar, w1VarL);
            h hVar3 = i.f4871f;
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar, iHashCode, hVar3);
            }
            h hVar4 = i.f4868c;
            l.x(hVar4, vVar, pVarC);
            p pVarB2 = androidx.compose.foundation.a.b(d.a(androidx.compose.foundation.layout.b.f112a.a(r0.b.f5656g).e(new ThumbElement(jVar, z2)), jVar, i2.a(false, o.f1788c / 2, 0L, vVar, 54, 4)), j6, q0Var);
            n1.f0 f0VarE2 = r.o.e(r0.b.f5657h, false);
            int iHashCode2 = Long.hashCode(vVar.S);
            w1 w1VarL2 = vVar.l();
            p pVarC2 = r0.a.c(vVar, pVarB2);
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            l.x(hVar, vVar, f0VarE2);
            l.x(hVar2, vVar, w1VarL2);
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode2))) {
                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
            }
            l.x(hVar4, vVar, pVarC2);
            vVar.V(1163457794);
            vVar.p(false);
            vVar.p(true);
            vVar.p(true);
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new r3(pVar, z2, z3, p3Var, jVar, q0Var, i5);
        }
    }
}

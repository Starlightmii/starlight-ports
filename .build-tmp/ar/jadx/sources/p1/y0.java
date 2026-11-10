package p1;

import android.os.Build;
import android.view.View;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedHashMap;
import q1.e2;
import q1.q2;
import q1.r1;
import q1.r2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class y0 extends m0 implements n1.e0, n1.p, f1 {
    public static final y0.n0 H;
    public static final u I;
    public static final float[] J;
    public static final d K;
    public static final d L;
    public float A;
    public x0.b B;
    public u C;
    public boolean F;
    public d1 G;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final d0 f4998o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public y0 f4999p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public y0 f5000q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f5001r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f5002s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public a4.c f5003t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public k2.b f5004u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public k2.k f5005v;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public n1.g0 f5007x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public LinkedHashMap f5008y;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f5006w = 0.8f;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public long f5009z = 0;
    public final c0.j1 D = new c0.j1(4, this);
    public final b0.a E = new b0.a(19, this);

    static {
        y0.n0 n0Var = new y0.n0();
        n0Var.f6651e = 1.0f;
        n0Var.f6652f = 1.0f;
        n0Var.f6653g = 1.0f;
        long j5 = y0.b0.f6615a;
        n0Var.f6655i = j5;
        n0Var.f6656j = j5;
        n0Var.f6657k = 8.0f;
        n0Var.f6658l = y0.t0.f6686b;
        n0Var.f6659m = y0.l0.f6642a;
        n0Var.f6661o = 9205357640488583168L;
        n0Var.f6662p = new k2.c(1.0f, 1.0f);
        n0Var.f6663q = k2.k.f3416d;
        H = n0Var;
        I = new u();
        J = y0.f0.a();
        K = new d(1);
        L = new d(2);
    }

    public y0(d0 d0Var) {
        this.f4998o = d0Var;
        this.f5004u = d0Var.f4796t;
        this.f5005v = d0Var.f4797u;
    }

    public static y0 c1(n1.p pVar) {
        y0 y0Var;
        n1.d0 d0Var = pVar instanceof n1.d0 ? (n1.d0) pVar : null;
        if (d0Var != null && (y0Var = d0Var.f4113d.f4932o) != null) {
            return y0Var;
        }
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.node.NodeCoordinator", pVar);
        return (y0) pVar;
    }

    public final long A0(y0 y0Var, long j5) {
        if (y0Var == this) {
            return j5;
        }
        y0 y0Var2 = this.f5000q;
        return (y0Var2 == null || b4.l.a(y0Var, y0Var2)) ? I0(j5) : I0(y0Var2.A0(y0Var, j5));
    }

    public final long B0(long j5) {
        return p3.j.f(Math.max(0.0f, (x0.f.d(j5) - g0()) / 2.0f), Math.max(0.0f, (x0.f.b(j5) - ((int) (this.f4132f & 4294967295L))) / 2.0f));
    }

    public final float C0(long j5, long j6) {
        if (g0() >= x0.f.d(j6) && ((int) (this.f4132f & 4294967295L)) >= x0.f.b(j6)) {
            return Float.POSITIVE_INFINITY;
        }
        long jB0 = B0(j6);
        float fD = x0.f.d(jB0);
        float fB = x0.f.b(jB0);
        float fD2 = x0.c.d(j5);
        float fMax = Math.max(0.0f, fD2 < 0.0f ? -fD2 : fD2 - g0());
        float fE = x0.c.e(j5);
        long jB = p3.j.b(fMax, Math.max(0.0f, fE < 0.0f ? -fE : fE - ((int) (this.f4132f & 4294967295L))));
        if ((fD <= 0.0f && fB <= 0.0f) || x0.c.d(jB) > fD || x0.c.e(jB) > fB) {
            return Float.POSITIVE_INFINITY;
        }
        float fIntBitsToFloat = Float.intBitsToFloat((int) (jB >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (jB & 4294967295L));
        return (fIntBitsToFloat2 * fIntBitsToFloat2) + (fIntBitsToFloat * fIntBitsToFloat);
    }

    public final void D0(y0.r rVar, b1.b bVar) {
        d1 d1Var = this.G;
        if (d1Var != null) {
            d1Var.b(rVar, bVar);
            return;
        }
        long j5 = this.f5009z;
        float f5 = (int) (j5 >> 32);
        float f6 = (int) (j5 & 4294967295L);
        rVar.k(f5, f6);
        F0(rVar, bVar);
        rVar.k(-f5, -f6);
    }

    public final void E0(y0.r rVar, y0.h hVar) {
        long j5 = this.f4132f;
        rVar.getClass();
        rVar.f(0.5f, 0.5f, ((int) (j5 >> 32)) - 0.5f, ((int) (j5 & 4294967295L)) - 0.5f, hVar);
    }

    public final void F0(y0.r rVar, b1.b bVar) {
        y0.r rVar2;
        b1.b bVar2;
        r0.o oVarM0 = M0(4);
        if (oVarM0 == null) {
            X0(rVar, bVar);
            return;
        }
        d0 d0Var = this.f4998o;
        d0Var.getClass();
        f0 sharedDrawScope = ((q1.u) f.u(d0Var)).getSharedDrawScope();
        long jO = a.a.O(this.f4132f);
        sharedDrawScope.getClass();
        h0.e eVar = null;
        while (oVarM0 != null) {
            if (oVarM0 instanceof o) {
                rVar2 = rVar;
                bVar2 = bVar;
                sharedDrawScope.d(rVar2, jO, this, (o) oVarM0, bVar2);
            } else {
                rVar2 = rVar;
                bVar2 = bVar;
                if ((oVarM0.f5683f & 4) != 0 && (oVarM0 instanceof m)) {
                    int i5 = 0;
                    for (r0.o oVar = ((m) oVarM0).f4921r; oVar != null; oVar = oVar.f5686i) {
                        if ((oVar.f5683f & 4) != 0) {
                            i5++;
                            if (i5 == 1) {
                                oVarM0 = oVar;
                            } else {
                                if (eVar == null) {
                                    eVar = new h0.e(new r0.o[16]);
                                }
                                if (oVarM0 != null) {
                                    eVar.b(oVarM0);
                                    oVarM0 = null;
                                }
                                eVar.b(oVar);
                            }
                        }
                    }
                    if (i5 == 1) {
                    }
                }
                rVar = rVar2;
                bVar = bVar2;
            }
            oVarM0 = f.e(eVar);
            rVar = rVar2;
            bVar = bVar2;
        }
    }

    public abstract void G0();

    public final y0 H0(y0 y0Var) {
        d0 d0VarR = y0Var.f4998o;
        d0 d0Var = this.f4998o;
        if (d0VarR == d0Var) {
            r0.o oVarL0 = y0Var.L0();
            r0.o oVar = L0().f5681d;
            if (!oVar.f5693p) {
                d4.a.L("visitLocalAncestors called on an unattached node");
                throw null;
            }
            for (r0.o oVar2 = oVar.f5685h; oVar2 != null; oVar2 = oVar2.f5685h) {
                if ((oVar2.f5683f & 2) != 0 && oVar2 == oVarL0) {
                    return y0Var;
                }
            }
            return this;
        }
        while (d0VarR.f4789m > d0Var.f4789m) {
            d0VarR = d0VarR.r();
            b4.l.b(d0VarR);
        }
        d0 d0VarR2 = d0Var;
        while (d0VarR2.f4789m > d0VarR.f4789m) {
            d0VarR2 = d0VarR2.r();
            b4.l.b(d0VarR2);
        }
        while (d0VarR != d0VarR2) {
            d0VarR = d0VarR.r();
            d0VarR2 = d0VarR2.r();
            if (d0VarR == null || d0VarR2 == null) {
                throw new IllegalArgumentException("layouts are not part of the same hierarchy");
            }
        }
        if (d0VarR2 != d0Var) {
            if (d0VarR != y0Var.f4998o) {
                return d0VarR.f4801y.f4969b;
            }
            return y0Var;
        }
        return this;
    }

    public final long I0(long j5) {
        long j6 = this.f5009z;
        long jB = p3.j.b(x0.c.d(j5) - ((int) (j6 >> 32)), x0.c.e(j5) - ((int) (j6 & 4294967295L)));
        d1 d1Var = this.G;
        return d1Var != null ? d1Var.a(jB, true) : jB;
    }

    public abstract n0 J0();

    @Override // n1.p
    public final long K(long j5) {
        if (L0().f5693p) {
            return T0(n1.v0.f(this), ((q1.u) f.u(this.f4998o)).C(j5));
        }
        d4.a.L("LayoutCoordinate operations are only valid when isAttached is true");
        throw null;
    }

    public final long K0() {
        return this.f5004u.R(this.f4998o.f4798v.g());
    }

    public abstract r0.o L0();

    public final r0.o M0(int i5) {
        boolean zH = z0.h(i5);
        r0.o oVarL0 = L0();
        if (!zH && (oVarL0 = oVarL0.f5685h) == null) {
            return null;
        }
        for (r0.o oVarN0 = N0(zH); oVarN0 != null && (oVarN0.f5684g & i5) != 0; oVarN0 = oVarN0.f5686i) {
            if ((oVarN0.f5683f & i5) != 0) {
                return oVarN0;
            }
            if (oVarN0 == oVarL0) {
                return null;
            }
        }
        return null;
    }

    @Override // n1.p
    public final boolean N() {
        return L0().f5693p;
    }

    public final r0.o N0(boolean z2) {
        r0.o oVarL0;
        t0 t0Var = this.f4998o.f4801y;
        if (t0Var.f4970c == this) {
            return t0Var.f4972e;
        }
        if (!z2) {
            y0 y0Var = this.f5000q;
            if (y0Var != null) {
                return y0Var.L0();
            }
            return null;
        }
        y0 y0Var2 = this.f5000q;
        if (y0Var2 == null || (oVarL0 = y0Var2.L0()) == null) {
            return null;
        }
        return oVarL0.f5686i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v11, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r13v12, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v13 */
    /* JADX WARN: Type inference failed for: r13v14 */
    /* JADX WARN: Type inference failed for: r13v15 */
    /* JADX WARN: Type inference failed for: r13v16 */
    /* JADX WARN: Type inference failed for: r13v17 */
    /* JADX WARN: Type inference failed for: r13v18 */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v7, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v5 */
    public final void O0(r0.o oVar, d dVar, long j5, r rVar, boolean z2, boolean z3) {
        if (oVar == null) {
            Q0(dVar, j5, rVar, z2, z3);
            return;
        }
        rVar.b(oVar, -1.0f, z3, new w0(this, oVar, dVar, j5, rVar, z2, z3));
        y0 y0Var = oVar.f5688k;
        if (y0Var != null) {
            r0.o oVarN0 = y0Var.N0(z0.h(16));
            if (oVarN0 != null && oVarN0.f5693p) {
                r0.o oVar2 = oVarN0.f5681d;
                if (!oVar2.f5693p) {
                    d4.a.L("visitLocalDescendants called on an unattached node");
                    throw null;
                }
                if ((oVar2.f5684g & 16) != 0) {
                    while (oVar2 != null) {
                        if ((oVar2.f5683f & 16) != 0) {
                            ?? E = oVar2;
                            ?? eVar = 0;
                            while (E != 0) {
                                if (E instanceof j1) {
                                    if (((j1) E).P()) {
                                        return;
                                    }
                                } else if ((E.f5683f & 16) != 0 && (E instanceof m)) {
                                    r0.o oVar3 = ((m) E).f4921r;
                                    int i5 = 0;
                                    eVar = eVar;
                                    E = E;
                                    while (oVar3 != null) {
                                        if ((oVar3.f5683f & 16) != 0) {
                                            i5++;
                                            if (i5 == 1) {
                                                eVar = eVar;
                                                E = oVar3;
                                            } else {
                                                if (eVar == 0) {
                                                    eVar = new h0.e(new r0.o[16]);
                                                }
                                                if (E != 0) {
                                                    eVar.b(E);
                                                    E = 0;
                                                }
                                                eVar.b(oVar3);
                                            }
                                        }
                                        oVar3 = oVar3.f5686i;
                                        eVar = eVar;
                                        E = E;
                                    }
                                    if (i5 == 1) {
                                    }
                                }
                                E = f.e(eVar);
                            }
                        }
                        oVar2 = oVar2.f5686i;
                    }
                }
            }
            rVar.f4961h = false;
        }
    }

    @Override // n1.p
    public final void P(float[] fArr) {
        e1 e1VarU = f.u(this.f4998o);
        f1(c1(n1.v0.f(this)), fArr);
        q1.u uVar = (q1.u) e1VarU;
        uVar.z();
        y0.f0.g(fArr, uVar.O);
        float fD = x0.c.d(uVar.S);
        float fE = x0.c.e(uVar.S);
        float[] fArr2 = uVar.N;
        y0.f0.d(fArr2);
        y0.f0.h(fArr2, fD, fE);
        q1.m0.u(fArr, fArr2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x00e4, code lost:
    
        if (p1.f.g(r18.a(), p1.f.a(r9, r20)) > 0) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void P0(p1.d r15, long r16, p1.r r18, boolean r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 262
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p1.y0.P0(p1.d, long, p1.r, boolean, boolean):void");
    }

    public void Q0(d dVar, long j5, r rVar, boolean z2, boolean z3) {
        y0 y0Var = this.f4999p;
        if (y0Var != null) {
            y0Var.P0(dVar, y0Var.I0(j5), rVar, z2, z3);
        }
    }

    public final void R0() {
        d1 d1Var = this.G;
        if (d1Var != null) {
            d1Var.invalidate();
            return;
        }
        y0 y0Var = this.f5000q;
        if (y0Var != null) {
            y0Var.R0();
        }
    }

    public final boolean S0() {
        if (this.G != null && this.f5006w <= 0.0f) {
            return true;
        }
        y0 y0Var = this.f5000q;
        if (y0Var != null) {
            return y0Var.S0();
        }
        return false;
    }

    @Override // n1.p
    public final void T(n1.p pVar, float[] fArr) {
        y0 y0VarC1 = c1(pVar);
        y0VarC1.U0();
        y0 y0VarH0 = H0(y0VarC1);
        y0.f0.d(fArr);
        y0VarC1.f1(y0VarH0, fArr);
        e1(y0VarH0, fArr);
    }

    public final long T0(n1.p pVar, long j5) {
        if (pVar instanceof n1.d0) {
            n1.d0 d0Var = (n1.d0) pVar;
            d0Var.f4113d.f4932o.U0();
            return d0Var.b(this, j5 ^ (-9223372034707292160L)) ^ (-9223372034707292160L);
        }
        y0 y0VarC1 = c1(pVar);
        y0VarC1.U0();
        y0 y0VarH0 = H0(y0VarC1);
        while (y0VarC1 != y0VarH0) {
            j5 = y0VarC1.d1(j5);
            y0VarC1 = y0VarC1.f5000q;
            b4.l.b(y0VarC1);
        }
        return A0(y0VarH0, j5);
    }

    public final void U0() {
        j0 j0Var = this.f4998o.f4802z;
        int i5 = j0Var.f4893a.f4802z.f4895c;
        if (i5 == 3 || i5 == 4) {
            if (j0Var.f4910r.f4889z) {
                j0Var.e(true);
            } else {
                j0Var.d(true);
            }
        }
        if (i5 == 4) {
            h0 h0Var = j0Var.f4911s;
            if (h0Var == null || !h0Var.f4862w) {
                j0Var.f(true);
            } else {
                j0Var.g(true);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r7v7, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v2, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Type inference failed for: r9v5 */
    public final void V0() {
        r0.o oVarL0;
        boolean zH = z0.h(128);
        r0.o oVarN0 = N0(zH);
        if (oVarN0 == null || (oVarN0.f5681d.f5684g & 128) == 0) {
            return;
        }
        p0.f fVarD = p0.r.d();
        a4.c cVarE = fVarD != null ? fVarD.e() : null;
        p0.f fVarG = p0.r.g(fVarD);
        try {
            if (!zH) {
                oVarL0 = L0().f5685h;
                if (oVarL0 == null) {
                }
                p0.r.j(fVarD, fVarG, cVarE);
            }
            oVarL0 = L0();
            for (r0.o oVarN1 = N0(zH); oVarN1 != null && (oVarN1.f5684g & 128) != 0; oVarN1 = oVarN1.f5686i) {
                if ((oVarN1.f5683f & 128) != 0) {
                    ?? E = oVarN1;
                    ?? eVar = 0;
                    while (E != 0) {
                        if (E instanceof v) {
                            ((v) E).m(this.f4132f);
                        } else if ((E.f5683f & 128) != 0 && (E instanceof m)) {
                            r0.o oVar = ((m) E).f4921r;
                            int i5 = 0;
                            E = E;
                            eVar = eVar;
                            while (oVar != null) {
                                if ((oVar.f5683f & 128) != 0) {
                                    i5++;
                                    if (i5 == 1) {
                                        eVar = eVar;
                                        E = oVar;
                                    } else {
                                        if (eVar == 0) {
                                            eVar = new h0.e(new r0.o[16]);
                                        }
                                        if (E != 0) {
                                            eVar.b(E);
                                            E = 0;
                                        }
                                        eVar.b(oVar);
                                    }
                                }
                                oVar = oVar.f5686i;
                                E = E;
                                eVar = eVar;
                            }
                            if (i5 == 1) {
                            }
                        }
                        E = f.e(eVar);
                    }
                }
                if (oVarN1 == oVarL0) {
                    break;
                }
            }
            p0.r.j(fVarD, fVarG, cVarE);
        } catch (Throwable th) {
            p0.r.j(fVarD, fVarG, cVarE);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r6v4 */
    public final void W0() {
        boolean zH = z0.h(128);
        r0.o oVarL0 = L0();
        if (!zH && (oVarL0 = oVarL0.f5685h) == null) {
            return;
        }
        for (r0.o oVarN0 = N0(zH); oVarN0 != null && (oVarN0.f5684g & 128) != 0; oVarN0 = oVarN0.f5686i) {
            if ((oVarN0.f5683f & 128) != 0) {
                ?? E = oVarN0;
                ?? eVar = 0;
                while (E != 0) {
                    if (E instanceof v) {
                        ((v) E).r(this);
                    } else if ((E.f5683f & 128) != 0 && (E instanceof m)) {
                        r0.o oVar = ((m) E).f4921r;
                        int i5 = 0;
                        E = E;
                        eVar = eVar;
                        while (oVar != null) {
                            if ((oVar.f5683f & 128) != 0) {
                                i5++;
                                if (i5 == 1) {
                                    eVar = eVar;
                                    E = oVar;
                                } else {
                                    if (eVar == 0) {
                                        eVar = new h0.e(new r0.o[16]);
                                    }
                                    if (E != 0) {
                                        eVar.b(E);
                                        E = 0;
                                    }
                                    eVar.b(oVar);
                                }
                            }
                            oVar = oVar.f5686i;
                            E = E;
                            eVar = eVar;
                        }
                        if (i5 == 1) {
                        }
                    }
                    E = f.e(eVar);
                }
            }
            if (oVarN0 == oVarL0) {
                return;
            }
        }
    }

    public abstract void X0(y0.r rVar, b1.b bVar);

    @Override // n1.p
    public final long Y() {
        return this.f4132f;
    }

    public final void Y0(long j5, float f5, a4.c cVar) {
        g1(cVar, false);
        if (!k2.h.a(this.f5009z, j5)) {
            this.f5009z = j5;
            d0 d0Var = this.f4998o;
            d0Var.f4802z.f4910r.r0();
            d1 d1Var = this.G;
            if (d1Var != null) {
                d1Var.h(j5);
            } else {
                y0 y0Var = this.f5000q;
                if (y0Var != null) {
                    y0Var.R0();
                }
            }
            m0.x0(this);
            e1 e1Var = d0Var.f4788l;
            if (e1Var != null) {
                ((q1.u) e1Var).v(d0Var);
            }
        }
        this.A = f5;
        if (this.f4924k) {
            return;
        }
        p0(new i1(u0(), this));
    }

    @Override // n1.p
    public final long Z(long j5) {
        if (!L0().f5693p) {
            d4.a.L("LayoutCoordinate operations are only valid when isAttached is true");
            throw null;
        }
        U0();
        for (y0 y0Var = this; y0Var != null; y0Var = y0Var.f5000q) {
            j5 = y0Var.d1(j5);
        }
        return j5;
    }

    public final void Z0(x0.b bVar, boolean z2, boolean z3) {
        d1 d1Var = this.G;
        if (d1Var != null) {
            if (this.f5002s) {
                if (z3) {
                    long jK0 = K0();
                    float fD = x0.f.d(jK0) / 2.0f;
                    float fB = x0.f.b(jK0) / 2.0f;
                    long j5 = this.f4132f;
                    bVar.a(-fD, -fB, ((int) (j5 >> 32)) + fD, ((int) (j5 & 4294967295L)) + fB);
                } else if (z2) {
                    long j6 = this.f4132f;
                    bVar.a(0.0f, 0.0f, (int) (j6 >> 32), (int) (j6 & 4294967295L));
                }
                if (bVar.b()) {
                    return;
                }
            }
            d1Var.j(bVar, false);
        }
        long j7 = this.f5009z;
        float f5 = (int) (j7 >> 32);
        bVar.f6528a += f5;
        bVar.f6530c += f5;
        float f6 = (int) (j7 & 4294967295L);
        bVar.f6529b += f6;
        bVar.f6531d += f6;
    }

    @Override // k2.b
    public final float a() {
        return this.f4998o.f4796t.a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r8v4 */
    public final void a1(n1.g0 g0Var) {
        y0 y0Var;
        n1.g0 g0Var2 = this.f5007x;
        if (g0Var != g0Var2) {
            this.f5007x = g0Var;
            d0 d0Var = this.f4998o;
            if (g0Var2 == null || g0Var.l() != g0Var2.l() || g0Var.e() != g0Var2.e()) {
                int iL = g0Var.l();
                int iE = g0Var.e();
                d1 d1Var = this.G;
                if (d1Var != null) {
                    d1Var.d(a.a.f(iL, iE));
                } else if (d0Var.E() && (y0Var = this.f5000q) != null) {
                    y0Var.R0();
                }
                m0(a.a.f(iL, iE));
                if (this.f5003t != null) {
                    h1(false);
                }
                boolean zH = z0.h(4);
                r0.o oVarL0 = L0();
                if (zH || (oVarL0 = oVarL0.f5685h) != null) {
                    for (r0.o oVarN0 = N0(zH); oVarN0 != null && (oVarN0.f5684g & 4) != 0; oVarN0 = oVarN0.f5686i) {
                        if ((oVarN0.f5683f & 4) != 0) {
                            ?? E = oVarN0;
                            ?? eVar = 0;
                            while (E != 0) {
                                if (E instanceof o) {
                                    ((o) E).f0();
                                } else if ((E.f5683f & 4) != 0 && (E instanceof m)) {
                                    r0.o oVar = ((m) E).f4921r;
                                    int i5 = 0;
                                    E = E;
                                    eVar = eVar;
                                    while (oVar != null) {
                                        if ((oVar.f5683f & 4) != 0) {
                                            i5++;
                                            if (i5 == 1) {
                                                eVar = eVar;
                                                E = oVar;
                                            } else {
                                                if (eVar == 0) {
                                                    eVar = new h0.e(new r0.o[16]);
                                                }
                                                if (E != 0) {
                                                    eVar.b(E);
                                                    E = 0;
                                                }
                                                eVar.b(oVar);
                                            }
                                        }
                                        oVar = oVar.f5686i;
                                        E = E;
                                        eVar = eVar;
                                    }
                                    if (i5 == 1) {
                                    }
                                }
                                E = f.e(eVar);
                            }
                        }
                        if (oVarN0 == oVarL0) {
                            break;
                        }
                    }
                }
                e1 e1Var = d0Var.f4788l;
                if (e1Var != null) {
                    ((q1.u) e1Var).v(d0Var);
                }
            }
            LinkedHashMap linkedHashMap = this.f5008y;
            if (((linkedHashMap == null || linkedHashMap.isEmpty()) && g0Var.m().isEmpty()) || b4.l.a(g0Var.m(), this.f5008y)) {
                return;
            }
            d0Var.f4802z.f4910r.f4886w.f();
            LinkedHashMap linkedHashMap2 = this.f5008y;
            if (linkedHashMap2 == null) {
                linkedHashMap2 = new LinkedHashMap();
                this.f5008y = linkedHashMap2;
            }
            linkedHashMap2.clear();
            linkedHashMap2.putAll(g0Var.m());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r5v4 */
    public final void b1(r0.o oVar, d dVar, long j5, r rVar, boolean z2, boolean z3, float f5) {
        boolean z4;
        if (oVar == null) {
            Q0(dVar, j5, rVar, z2, z3);
            return;
        }
        switch (dVar.f4779d) {
            case 1:
                ?? E = oVar;
                ?? eVar = 0;
                while (true) {
                    int i5 = 0;
                    if (E == 0) {
                        z4 = false;
                    } else {
                        if (E instanceof j1) {
                            if (((j1) E).h0()) {
                                z4 = true;
                            }
                            break;
                        } else if ((E.f5683f & 16) != 0 && (E instanceof m)) {
                            r0.o oVar2 = ((m) E).f4921r;
                            while (oVar2 != null) {
                                if ((oVar2.f5683f & 16) != 0) {
                                    i5++;
                                    if (i5 == 1) {
                                        E = E;
                                        eVar = eVar;
                                        eVar = eVar;
                                        E = oVar2;
                                    } else {
                                        if (eVar == 0) {
                                            eVar = new h0.e(new r0.o[16]);
                                        }
                                        if (E != 0) {
                                            eVar.b(E);
                                            E = 0;
                                        }
                                        eVar.b(oVar2);
                                    }
                                } else {
                                    E = E;
                                    eVar = eVar;
                                }
                                oVar2 = oVar2.f5686i;
                                E = E;
                                eVar = eVar;
                            }
                            if (i5 == 1) {
                                E = E;
                                eVar = eVar;
                            } else {
                                E = E;
                                eVar = eVar;
                            }
                        }
                        E = f.e(eVar);
                    }
                    break;
                }
                break;
            default:
                z4 = false;
                break;
        }
        if (!z4) {
            b1(f.d(oVar, dVar.a()), dVar, j5, rVar, z2, z3, f5);
            return;
        }
        x0 x0Var = new x0(this, oVar, dVar, j5, rVar, z2, z3, f5, 1);
        if (rVar.f4959f == p3.j.z(rVar)) {
            rVar.b(oVar, f5, z3, x0Var);
            if (rVar.f4959f + 1 == p3.j.z(rVar)) {
                rVar.c();
                return;
            }
            return;
        }
        long jA = rVar.a();
        int i6 = rVar.f4959f;
        rVar.f4959f = p3.j.z(rVar);
        rVar.b(oVar, f5, z3, x0Var);
        if (rVar.f4959f + 1 < p3.j.z(rVar) && f.g(jA, rVar.a()) > 0) {
            int i7 = rVar.f4959f + 1;
            int i8 = i6 + 1;
            Object[] objArr = rVar.f4957d;
            q3.k.A(objArr, objArr, i8, i7, rVar.f4960g);
            long[] jArr = rVar.f4958e;
            q3.k.z(jArr, jArr, i8, i7, rVar.f4960g);
            rVar.f4959f = ((rVar.f4960g + i6) - rVar.f4959f) - 1;
        }
        rVar.c();
        rVar.f4959f = i6;
    }

    public final long d1(long j5) {
        d1 d1Var = this.G;
        if (d1Var != null) {
            j5 = d1Var.a(j5, false);
        }
        long j6 = this.f5009z;
        return p3.j.b(x0.c.d(j5) + ((int) (j6 >> 32)), x0.c.e(j5) + ((int) (j6 & 4294967295L)));
    }

    @Override // n1.p
    public final long e(long j5) {
        if (!L0().f5693p) {
            d4.a.L("LayoutCoordinate operations are only valid when isAttached is true");
            throw null;
        }
        n1.p pVarF = n1.v0.f(this);
        q1.u uVar = (q1.u) f.u(this.f4998o);
        uVar.z();
        return T0(pVarF, x0.c.g(y0.f0.b(j5, uVar.P), pVarF.Z(0L)));
    }

    public final void e1(y0 y0Var, float[] fArr) {
        if (b4.l.a(y0Var, this)) {
            return;
        }
        y0 y0Var2 = this.f5000q;
        b4.l.b(y0Var2);
        y0Var2.e1(y0Var, fArr);
        if (!k2.h.a(this.f5009z, 0L)) {
            float[] fArr2 = J;
            y0.f0.d(fArr2);
            long j5 = this.f5009z;
            y0.f0.h(fArr2, -((int) (j5 >> 32)), -((int) (j5 & 4294967295L)));
            y0.f0.g(fArr, fArr2);
        }
        d1 d1Var = this.G;
        if (d1Var != null) {
            d1Var.f(fArr);
        }
    }

    public final void f1(y0 y0Var, float[] fArr) {
        y0 y0Var2 = this;
        while (!y0Var2.equals(y0Var)) {
            d1 d1Var = y0Var2.G;
            if (d1Var != null) {
                d1Var.e(fArr);
            }
            long j5 = y0Var2.f5009z;
            if (!k2.h.a(j5, 0L)) {
                float[] fArr2 = J;
                y0.f0.d(fArr2);
                y0.f0.h(fArr2, (int) (j5 >> 32), (int) (j5 & 4294967295L));
                y0.f0.g(fArr, fArr2);
            }
            y0Var2 = y0Var2.f5000q;
            b4.l.b(y0Var2);
        }
    }

    @Override // n1.p
    public final long g(long j5) {
        long jZ = Z(j5);
        q1.u uVar = (q1.u) f.u(this.f4998o);
        uVar.z();
        return y0.f0.b(jZ, uVar.O);
    }

    /* JADX WARN: Code duplicated, block: B:46:0x00af  */
    /* JADX WARN: Code duplicated, block: B:48:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:51:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:52:0x00cd  */
    public final void g1(a4.c cVar, boolean z2) {
        e1 e1Var;
        Reference referencePoll;
        Object obj;
        q1.p1 r2Var;
        d0 d0Var = this.f4998o;
        boolean z3 = (!z2 && this.f5003t == cVar && b4.l.a(this.f5004u, d0Var.f4796t) && this.f5005v == d0Var.f4797u) ? false : true;
        this.f5004u = d0Var.f4796t;
        this.f5005v = d0Var.f4797u;
        boolean zD = d0Var.D();
        b0.a aVar = this.E;
        if (!zD || cVar == null) {
            this.f5003t = null;
            d1 d1Var = this.G;
            if (d1Var != null) {
                d1Var.g();
                d0Var.C = true;
                aVar.a();
                if (L0().f5693p && (e1Var = d0Var.f4788l) != null) {
                    ((q1.u) e1Var).v(d0Var);
                }
            }
            this.G = null;
            this.F = false;
            return;
        }
        this.f5003t = cVar;
        if (this.G != null) {
            if (z3) {
                h1(true);
                return;
            }
            return;
        }
        q1.u uVar = (q1.u) f.u(d0Var);
        a2.h hVar = uVar.f5404r0;
        h0.e eVar = (h0.e) hVar.f49e;
        do {
            referencePoll = ((ReferenceQueue) hVar.f50f).poll();
            if (referencePoll != null) {
                ((h0.e) hVar.f49e).l(referencePoll);
            }
        } while (referencePoll != null);
        do {
            if (!eVar.k()) {
                obj = null;
                break;
            }
            obj = ((Reference) eVar.m(eVar.f2578f - 1)).get();
        } while (obj == null);
        d1 q2Var = (d1) obj;
        c0.j1 j1Var = this.D;
        if (q2Var != null) {
            q2Var.c(j1Var, aVar);
        } else if (uVar.isHardwareAccelerated() && Build.VERSION.SDK_INT != 28) {
            q2Var = new r1(uVar.getGraphicsContext().b(), uVar.getGraphicsContext(), uVar, j1Var, aVar);
        } else if (uVar.isHardwareAccelerated() && uVar.T) {
            try {
                q2Var = new e2(uVar, j1Var, aVar);
            } catch (Throwable unused) {
                uVar.T = false;
                if (uVar.G == null) {
                    if (!q2.f5313v) {
                        q1.m0.y(new View(uVar.getContext()));
                    }
                    if (q2.f5314w) {
                        r2Var = new q1.p1(uVar.getContext());
                    } else {
                        r2Var = new r2(uVar.getContext());
                    }
                    uVar.G = r2Var;
                    uVar.addView(r2Var, -1);
                }
                q1.p1 p1Var = uVar.G;
                b4.l.b(p1Var);
                q2Var = new q2(uVar, p1Var, j1Var, aVar);
            }
        } else {
            if (uVar.G == null) {
                if (!q2.f5313v) {
                    q1.m0.y(new View(uVar.getContext()));
                }
                if (q2.f5314w) {
                    r2Var = new q1.p1(uVar.getContext());
                } else {
                    r2Var = new r2(uVar.getContext());
                }
                uVar.G = r2Var;
                uVar.addView(r2Var, -1);
            }
            q1.p1 p1Var2 = uVar.G;
            b4.l.b(p1Var2);
            q2Var = new q2(uVar, p1Var2, j1Var, aVar);
        }
        q2Var.d(this.f4132f);
        q2Var.h(this.f5009z);
        this.G = q2Var;
        h1(true);
        d0Var.C = true;
        aVar.a();
    }

    @Override // n1.m
    public final k2.k getLayoutDirection() {
        return this.f4998o.f4797u;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r6v4 */
    @Override // n1.m0, n1.e0
    public final Object h() {
        d0 d0Var = this.f4998o;
        if (!d0Var.f4801y.d(64)) {
            return null;
        }
        L0();
        Object objE0 = null;
        for (r0.o oVar = d0Var.f4801y.f4971d; oVar != null; oVar = oVar.f5685h) {
            if ((oVar.f5683f & 64) != 0) {
                ?? E = oVar;
                ?? eVar = 0;
                while (E != 0) {
                    if (E instanceof h1) {
                        objE0 = ((h1) E).e0(objE0);
                    } else if ((E.f5683f & 64) != 0 && (E instanceof m)) {
                        r0.o oVar2 = ((m) E).f4921r;
                        int i5 = 0;
                        E = E;
                        eVar = eVar;
                        while (oVar2 != null) {
                            if ((oVar2.f5683f & 64) != 0) {
                                i5++;
                                if (i5 == 1) {
                                    eVar = eVar;
                                    E = oVar2;
                                } else {
                                    if (eVar == 0) {
                                        eVar = new h0.e(new r0.o[16]);
                                    }
                                    if (E != 0) {
                                        eVar.b(E);
                                        E = 0;
                                    }
                                    eVar.b(oVar2);
                                }
                            }
                            oVar2 = oVar2.f5686i;
                            E = E;
                            eVar = eVar;
                        }
                        if (i5 == 1) {
                        }
                    }
                    E = f.e(eVar);
                }
            }
        }
        return objE0;
    }

    public final void h1(boolean z2) {
        e1 e1Var;
        d1 d1Var = this.G;
        if (d1Var == null) {
            if (this.f5003t == null) {
                return;
            }
            d4.a.L("null layer with a non-null layerBlock");
            throw null;
        }
        a4.c cVar = this.f5003t;
        if (cVar == null) {
            d4.a.M("updateLayerParameters requires a non-null layerBlock");
            throw null;
        }
        y0.n0 n0Var = H;
        n0Var.e(1.0f);
        n0Var.g(1.0f);
        n0Var.c(1.0f);
        n0Var.h(0.0f);
        long j5 = y0.b0.f6615a;
        n0Var.d(j5);
        n0Var.k(j5);
        if (n0Var.f6657k != 8.0f) {
            n0Var.f6650d |= 2048;
            n0Var.f6657k = 8.0f;
        }
        n0Var.m(y0.t0.f6686b);
        n0Var.i(y0.l0.f6642a);
        if (n0Var.f6660n) {
            n0Var.f6650d |= 16384;
            n0Var.f6660n = false;
        }
        n0Var.f6661o = 9205357640488583168L;
        n0Var.f6664r = null;
        n0Var.f6650d = 0;
        d0 d0Var = this.f4998o;
        n0Var.f6662p = d0Var.f4796t;
        n0Var.f6663q = d0Var.f4797u;
        n0Var.f6661o = a.a.O(this.f4132f);
        ((q1.u) f.u(d0Var)).getSnapshotObserver().a(this, e.f4806i, new b0.a(20, cVar));
        u uVar = this.C;
        if (uVar == null) {
            uVar = new u();
            this.C = uVar;
        }
        uVar.f4976a = n0Var.f6651e;
        uVar.f4977b = n0Var.f6652f;
        uVar.f4978c = n0Var.f6657k;
        uVar.f4979d = n0Var.f6658l;
        d1Var.l(n0Var);
        this.f5002s = n0Var.f6660n;
        this.f5006w = n0Var.f6653g;
        if (!z2 || (e1Var = d0Var.f4788l) == null) {
            return;
        }
        ((q1.u) e1Var).v(d0Var);
    }

    @Override // n1.p
    public final n1.p i() {
        if (L0().f5693p) {
            U0();
            return this.f4998o.f4801y.f4970c.f5000q;
        }
        d4.a.L("LayoutCoordinate operations are only valid when isAttached is true");
        throw null;
    }

    @Override // k2.b
    public final float j() {
        return this.f4998o.f4796t.j();
    }

    @Override // n1.p
    public final x0.d k(n1.p pVar, boolean z2) {
        if (!L0().f5693p) {
            d4.a.L("LayoutCoordinate operations are only valid when isAttached is true");
            throw null;
        }
        if (!pVar.N()) {
            d4.a.L("LayoutCoordinates " + pVar + " is not attached!");
            throw null;
        }
        y0 y0VarC1 = c1(pVar);
        y0VarC1.U0();
        y0 y0VarH0 = H0(y0VarC1);
        x0.b bVar = this.B;
        if (bVar == null) {
            bVar = new x0.b();
            bVar.f6528a = 0.0f;
            bVar.f6529b = 0.0f;
            bVar.f6530c = 0.0f;
            bVar.f6531d = 0.0f;
            this.B = bVar;
        }
        bVar.f6528a = 0.0f;
        bVar.f6529b = 0.0f;
        bVar.f6530c = (int) (pVar.Y() >> 32);
        bVar.f6531d = (int) (pVar.Y() & 4294967295L);
        while (y0VarC1 != y0VarH0) {
            y0VarC1.Z0(bVar, z2, false);
            if (bVar.b()) {
                return x0.d.f6533e;
            }
            y0VarC1 = y0VarC1.f5000q;
            b4.l.b(y0VarC1);
        }
        z0(y0VarH0, bVar, z2);
        return new x0.d(bVar.f6528a, bVar.f6529b, bVar.f6530c, bVar.f6531d);
    }

    @Override // p1.m0
    public final m0 q0() {
        return this.f4999p;
    }

    @Override // p1.m0
    public final n1.p r0() {
        return this;
    }

    @Override // p1.m0
    public final boolean s0() {
        return this.f5007x != null;
    }

    @Override // p1.m0
    public final d0 t0() {
        return this.f4998o;
    }

    @Override // p1.m0
    public final n1.g0 u0() {
        n1.g0 g0Var = this.f5007x;
        if (g0Var != null) {
            return g0Var;
        }
        throw new IllegalStateException("Asking for measurement result of unmeasured layout modifier");
    }

    @Override // p1.m0
    public final m0 v0() {
        return this.f5000q;
    }

    @Override // p1.f1
    public final boolean w() {
        return (this.G == null || this.f5001r || !this.f4998o.D()) ? false : true;
    }

    @Override // p1.m0
    public final long w0() {
        return this.f5009z;
    }

    @Override // n1.p
    public final long y(n1.p pVar, long j5) {
        return T0(pVar, j5);
    }

    @Override // p1.m0
    public final void y0() {
        k0(this.f5009z, this.A, this.f5003t);
    }

    public final void z0(y0 y0Var, x0.b bVar, boolean z2) {
        if (y0Var == this) {
            return;
        }
        y0 y0Var2 = this.f5000q;
        if (y0Var2 != null) {
            y0Var2.z0(y0Var, bVar, z2);
        }
        long j5 = this.f5009z;
        float f5 = (int) (j5 >> 32);
        bVar.f6528a -= f5;
        bVar.f6530c -= f5;
        float f6 = (int) (j5 & 4294967295L);
        bVar.f6529b -= f6;
        bVar.f6531d -= f6;
        d1 d1Var = this.G;
        if (d1Var != null) {
            d1Var.j(bVar, true);
            if (this.f5002s && z2) {
                long j6 = this.f4132f;
                bVar.a(0.0f, 0.0f, (int) (j6 >> 32), (int) (j6 & 4294967295L));
            }
        }
    }
}

package p1;

import android.view.View;
import f0.y1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f4826a = new d(0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final k2.c f4827b = new k2.c(1.0f, 1.0f);

    public static final long a(float f5, boolean z2) {
        return ((z2 ? 1L : 0L) & 4294967295L) | (((long) Float.floatToIntBits(f5)) << 32);
    }

    public static final void b(h0.e eVar, r0.o oVar) {
        h0.e eVarU = s(oVar).u();
        int i5 = eVarU.f2578f;
        if (i5 > 0) {
            int i6 = i5 - 1;
            Object[] objArr = eVarU.f2576d;
            do {
                eVar.b(((d0) objArr[i6]).f4801y.f4972e);
                i6--;
            } while (i6 >= 0);
        }
    }

    public static final int c(m0 m0Var, n1.l lVar) {
        m0 m0VarQ0 = m0Var.q0();
        if (m0VarQ0 == null) {
            d4.a.L("Child of " + m0Var + " cannot be null when calculating alignment line");
            throw null;
        }
        if (m0Var.u0().m().containsKey(lVar)) {
            Integer num = (Integer) m0Var.u0().m().get(lVar);
            if (num != null) {
                return num.intValue();
            }
        } else {
            int iF0 = m0VarQ0.f0(lVar);
            if (iF0 != Integer.MIN_VALUE) {
                m0VarQ0.f4923j = true;
                m0Var.f4924k = true;
                m0Var.y0();
                m0VarQ0.f4923j = false;
                m0Var.f4924k = false;
                return iF0 + ((int) (lVar instanceof n1.l ? m0VarQ0.w0() & 4294967295L : m0VarQ0.w0() >> 32));
            }
        }
        return Integer.MIN_VALUE;
    }

    public static final r0.o d(l lVar, int i5) {
        r0.o oVar = ((r0.o) lVar).f5681d.f5686i;
        if (oVar == null || (oVar.f5684g & i5) == 0) {
            return null;
        }
        while (oVar != null) {
            int i6 = oVar.f5683f;
            if ((i6 & 2) != 0) {
                return null;
            }
            if ((i6 & i5) != 0) {
                return oVar;
            }
            oVar = oVar.f5686i;
        }
        return null;
    }

    public static final r0.o e(h0.e eVar) {
        if (eVar == null || eVar.j()) {
            return null;
        }
        return (r0.o) eVar.m(eVar.f2578f - 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final w f(r0.o oVar) {
        if ((oVar.f5683f & 2) != 0) {
            if (oVar instanceof w) {
                return (w) oVar;
            }
            if (oVar instanceof m) {
                r0.o oVar2 = ((m) oVar).f4921r;
                while (oVar2 != 0) {
                    if (oVar2 instanceof w) {
                        return (w) oVar2;
                    }
                    oVar2 = (!(oVar2 instanceof m) || (oVar2.f5683f & 2) == 0) ? oVar2.f5686i : ((m) oVar2).f4921r;
                }
            }
        }
        return null;
    }

    public static final int g(long j5, long j6) {
        boolean z2 = ((int) (j5 & 4294967295L)) != 0;
        if (z2 != (((int) (4294967295L & j6)) != 0)) {
            return z2 ? -1 : 1;
        }
        return (int) Math.signum(Float.intBitsToFloat((int) (j5 >> 32)) - Float.intBitsToFloat((int) (j6 >> 32)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Object h(k kVar, y1 y1Var) {
        if (!((r0.o) kVar).f5681d.f5693p) {
            d4.a.L("Cannot read CompositionLocal because the Modifier node is not currently attached.");
            throw null;
        }
        n0.h hVar = (n0.h) s(kVar).f4799w;
        hVar.getClass();
        return f0.l.t(hVar, y1Var);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r5v6 */
    public static final o1 i(l lVar, Object obj) {
        t0 t0Var;
        r0.o oVar = ((r0.o) lVar).f5681d;
        if (!oVar.f5693p) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        r0.o oVar2 = oVar.f5685h;
        d0 d0VarS = s(lVar);
        while (d0VarS != null) {
            if ((d0VarS.f4801y.f4972e.f5684g & 262144) != 0) {
                while (oVar2 != null) {
                    if ((oVar2.f5683f & 262144) != 0) {
                        ?? E = oVar2;
                        ?? eVar = 0;
                        while (E != 0) {
                            if (E instanceof o1) {
                                o1 o1Var = (o1) E;
                                if (obj.equals(o1Var.k())) {
                                    return o1Var;
                                }
                            } else if ((E.f5683f & 262144) != 0 && (E instanceof m)) {
                                r0.o oVar3 = ((m) E).f4921r;
                                int i5 = 0;
                                E = E;
                                eVar = eVar;
                                while (oVar3 != null) {
                                    if ((oVar3.f5683f & 262144) != 0) {
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
                                    E = E;
                                    eVar = eVar;
                                }
                                if (i5 == 1) {
                                }
                            }
                            E = e(eVar);
                        }
                    }
                    oVar2 = oVar2.f5685h;
                }
            }
            d0VarS = d0VarS.r();
            oVar2 = (d0VarS == null || (t0Var = d0VarS.f4801y) == null) ? null : t0Var.f4971d;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.Object, p1.l, p1.o1] */
    /* JADX WARN: Type inference failed for: r3v10, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r3v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r3v9 */
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
    /* JADX WARN: Type inference failed for: r6v7 */
    public static final o1 j(o1 o1Var) {
        t0 t0Var;
        r0.o oVar = ((r0.o) o1Var).f5681d;
        if (!oVar.f5693p) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        r0.o oVar2 = oVar.f5685h;
        d0 d0VarS = s(o1Var);
        while (d0VarS != null) {
            if ((d0VarS.f4801y.f4972e.f5684g & 262144) != 0) {
                while (oVar2 != null) {
                    if ((oVar2.f5683f & 262144) != 0) {
                        ?? E = oVar2;
                        ?? eVar = 0;
                        while (E != 0) {
                            if (E instanceof o1) {
                                o1 o1Var2 = (o1) E;
                                if (b4.l.a(o1Var.k(), o1Var2.k()) && o1Var.getClass() == o1Var2.getClass()) {
                                    return o1Var2;
                                }
                            } else if ((E.f5683f & 262144) != 0 && (E instanceof m)) {
                                r0.o oVar3 = ((m) E).f4921r;
                                int i5 = 0;
                                E = E;
                                eVar = eVar;
                                while (oVar3 != null) {
                                    if ((oVar3.f5683f & 262144) != 0) {
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
                                    E = E;
                                    eVar = eVar;
                                }
                                if (i5 == 1) {
                                }
                            }
                            E = e(eVar);
                        }
                    }
                    oVar2 = oVar2.f5685h;
                }
            }
            d0VarS = d0VarS.r();
            oVar2 = (d0VarS == null || (t0Var = d0VarS.f4801y) == null) ? null : t0Var.f4971d;
        }
        return null;
    }

    public static final int k(int[] iArr) {
        return Math.min(iArr[2] - iArr[0], iArr[3] - iArr[1]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void l(o oVar) {
        if (((r0.o) oVar).f5681d.f5693p) {
            q(oVar, 1).R0();
        }
    }

    public static final void m(w wVar) {
        s(wVar).A();
    }

    public static final void n(l1 l1Var) {
        s(l1Var).B();
    }

    public static final boolean o(d0 d0Var) {
        if (d0Var.f4782f == null) {
            return false;
        }
        d0 d0VarR = d0Var.r();
        return (d0VarR != null ? d0VarR.f4782f : null) == null || d0Var.f4802z.f4894b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void p(r0.o oVar, a4.a aVar) {
        b1 b1Var = oVar.f5687j;
        if (b1Var == null) {
            b1Var = new b1((a1) oVar);
            oVar.f5687j = b1Var;
        }
        ((q1.u) t(oVar)).getSnapshotObserver().a(b1Var, e.f4807j, aVar);
    }

    public static final y0 q(l lVar, int i5) {
        y0 y0Var = ((r0.o) lVar).f5681d.f5688k;
        b4.l.b(y0Var);
        if (y0Var.L0() != lVar || !z0.h(i5)) {
            return y0Var;
        }
        y0 y0Var2 = y0Var.f4999p;
        b4.l.b(y0Var2);
        return y0Var2;
    }

    public static final y0 r(l lVar) {
        if (!((r0.o) lVar).f5681d.f5693p) {
            d4.a.L("Cannot get LayoutCoordinates, Modifier.Node is not attached.");
            throw null;
        }
        y0 y0VarQ = q(lVar, 2);
        if (y0VarQ.L0().f5693p) {
            return y0VarQ;
        }
        d4.a.L("LayoutCoordinates is not attached.");
        throw null;
    }

    public static final d0 s(l lVar) {
        y0 y0Var = ((r0.o) lVar).f5681d.f5688k;
        if (y0Var != null) {
            return y0Var.f4998o;
        }
        d4.a.M("Cannot obtain node coordinator. Is the Modifier.Node attached?");
        throw null;
    }

    public static final e1 t(l lVar) {
        e1 e1Var = s(lVar).f4788l;
        if (e1Var != null) {
            return e1Var;
        }
        d4.a.M("This node does not have an owner.");
        throw null;
    }

    public static final e1 u(d0 d0Var) {
        e1 e1Var = d0Var.f4788l;
        if (e1Var != null) {
            return e1Var;
        }
        d4.a.M("LayoutNode should be attached to an owner");
        throw null;
    }

    public static final View v(l lVar) {
        if (((r0.o) lVar).f5681d.f5693p) {
            return (View) u(s(lVar));
        }
        d4.a.L("Cannot get View because the Modifier node is not currently attached.");
        throw null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [a4.c] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r1v14, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r5v9 */
    public static final void w(j1.o oVar, a4.c cVar) {
        t0 t0Var;
        r0.o oVar2 = oVar.f5681d;
        if (!oVar2.f5693p) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        r0.o oVar3 = oVar2.f5685h;
        d0 d0VarS = s(oVar);
        while (d0VarS != null) {
            if ((d0VarS.f4801y.f4972e.f5684g & 262144) != 0) {
                while (oVar3 != null) {
                    if ((oVar3.f5683f & 262144) != 0) {
                        ?? E = oVar3;
                        ?? eVar = 0;
                        while (E != 0) {
                            boolean zBooleanValue = true;
                            if (E instanceof o1) {
                                o1 o1Var = (o1) E;
                                if ("androidx.compose.ui.input.pointer.PointerHoverIcon".equals(o1Var.k()) && j1.o.class == o1Var.getClass()) {
                                    zBooleanValue = ((Boolean) cVar.c(o1Var)).booleanValue();
                                }
                                if (!zBooleanValue) {
                                    return;
                                }
                            } else {
                                if (((E.f5683f & 262144) != 0) && (E instanceof m)) {
                                    r0.o oVar4 = ((m) E).f4921r;
                                    int i5 = 0;
                                    while (oVar4 != null) {
                                        if ((oVar4.f5683f & 262144) != 0) {
                                            E = E;
                                            eVar = eVar;
                                            i5++;
                                            if (i5 == 1) {
                                                E = E;
                                                eVar = eVar;
                                                eVar = eVar;
                                                E = oVar4;
                                            } else {
                                                if (eVar == 0) {
                                                    eVar = new h0.e(new r0.o[16]);
                                                }
                                                if (E != 0) {
                                                    eVar.b(E);
                                                    E = 0;
                                                }
                                                eVar.b(oVar4);
                                            }
                                        } else {
                                            E = E;
                                            eVar = eVar;
                                            E = E;
                                            eVar = eVar;
                                        }
                                        oVar4 = oVar4.f5686i;
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
                            }
                            E = e(eVar);
                        }
                    }
                    oVar3 = oVar3.f5685h;
                }
            }
            d0VarS = d0VarS.r();
            oVar3 = (d0VarS == null || (t0Var = d0VarS.f4801y) == null) ? null : t0Var.f4971d;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.Object, p1.o1] */
    /* JADX WARN: Type inference failed for: r13v0, types: [a4.c] */
    /* JADX WARN: Type inference failed for: r7v10, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r7v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Type inference failed for: r9v9 */
    public static final void x(o1 o1Var, a4.c cVar) {
        r0.o oVar = ((r0.o) o1Var).f5681d;
        if (!oVar.f5693p) {
            d4.a.L("visitSubtreeIf called on an unattached node");
            throw null;
        }
        h0.e eVar = new h0.e(new r0.o[16]);
        r0.o oVar2 = oVar.f5686i;
        if (oVar2 == null) {
            b(eVar, oVar);
        } else {
            eVar.b(oVar2);
        }
        while (eVar.k()) {
            r0.o oVar3 = (r0.o) eVar.m(eVar.f2578f - 1);
            if ((oVar3.f5684g & 262144) != 0) {
                r0.o oVar4 = oVar3;
                while (true) {
                    if (oVar4 != null) {
                        if ((oVar4.f5683f & 262144) != 0) {
                            ?? eVar2 = 0;
                            ?? E = oVar4;
                            while (E != 0) {
                                if (E instanceof o1) {
                                    o1 o1Var2 = (o1) E;
                                    n1 n1Var = (b4.l.a(o1Var.k(), o1Var2.k()) && o1Var.getClass() == o1Var2.getClass()) ? (n1) cVar.c(o1Var2) : n1.f4938d;
                                    if (n1Var != n1.f4940f) {
                                        if (n1Var == n1.f4939e) {
                                            break;
                                        }
                                    } else {
                                        return;
                                    }
                                } else if ((E.f5683f & 262144) != 0 && (E instanceof m)) {
                                    r0.o oVar5 = ((m) E).f4921r;
                                    int i5 = 0;
                                    while (oVar5 != null) {
                                        if ((oVar5.f5683f & 262144) != 0) {
                                            i5++;
                                            if (i5 == 1) {
                                                E = E;
                                                eVar2 = eVar2;
                                                eVar2 = eVar2;
                                                E = oVar5;
                                            } else {
                                                if (eVar2 == 0) {
                                                    eVar2 = new h0.e(new r0.o[16]);
                                                }
                                                if (E != 0) {
                                                    eVar2.b(E);
                                                    E = 0;
                                                }
                                                eVar2.b(oVar5);
                                            }
                                        } else {
                                            E = E;
                                            eVar2 = eVar2;
                                        }
                                        oVar5 = oVar5.f5686i;
                                        E = E;
                                        eVar2 = eVar2;
                                    }
                                    if (i5 == 1) {
                                        E = E;
                                        eVar2 = eVar2;
                                    } else {
                                        E = E;
                                        eVar2 = eVar2;
                                    }
                                }
                                E = e(eVar2);
                            }
                        }
                        oVar4 = oVar4.f5686i;
                    }
                }
            }
            b(eVar, oVar3);
        }
    }
}

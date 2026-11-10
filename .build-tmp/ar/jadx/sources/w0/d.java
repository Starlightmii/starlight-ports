package w0;

import android.graphics.Rect;
import android.view.FocusFinder;
import android.view.View;
import android.view.ViewGroup;
import java.util.Arrays;
import n1.v0;
import p1.d0;
import p1.t0;
import p1.y0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f6226a = new int[2];

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
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r5v5 */
    public static final void A(r rVar) {
        t0 t0Var;
        r0.o oVar = rVar.f5681d;
        if (!oVar.f5693p) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        d0 d0VarS = p1.f.s(rVar);
        r0.o oVar2 = oVar;
        while (d0VarS != null) {
            if ((d0VarS.f4801y.f4972e.f5684g & 5120) != 0) {
                while (oVar2 != null) {
                    int i5 = oVar2.f5683f;
                    if ((i5 & 5120) != 0) {
                        if (oVar2 != oVar && (i5 & 1024) != 0) {
                            return;
                        }
                        if ((i5 & 4096) != 0) {
                            ?? E = oVar2;
                            ?? eVar = 0;
                            while (E != 0) {
                                if (E instanceof c) {
                                    c cVar = (c) E;
                                    cVar.k0(o(cVar));
                                } else if ((E.f5683f & 4096) != 0 && (E instanceof p1.m)) {
                                    r0.o oVar3 = ((p1.m) E).f4921r;
                                    int i6 = 0;
                                    E = E;
                                    eVar = eVar;
                                    while (oVar3 != null) {
                                        if ((oVar3.f5683f & 4096) != 0) {
                                            i6++;
                                            if (i6 == 1) {
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
                                    if (i6 == 1) {
                                    }
                                }
                                E = p1.f.e(eVar);
                            }
                        }
                    }
                    oVar2 = oVar2.f5685h;
                }
            }
            d0VarS = d0VarS.r();
            oVar2 = (d0VarS == null || (t0Var = d0VarS.f4801y) == null) ? null : t0Var.f4971d;
        }
    }

    public static final Boolean B(r rVar) {
        Boolean boolValueOf;
        s sVarE = E(rVar);
        t tVar = new t(rVar, 1);
        try {
            if (sVarE.f6266b) {
                s.a(sVarE);
            }
            sVarE.f6266b = true;
            ((h0.e) sVarE.f6268d).b(tVar);
            int iC = m.h.c(w(rVar));
            if (iC == 0) {
                boolValueOf = Boolean.valueOf(x(rVar));
            } else if (iC == 1) {
                boolValueOf = null;
            } else if (iC == 2) {
                boolValueOf = Boolean.TRUE;
            } else {
                if (iC != 3) {
                    throw new i4.p();
                }
                boolValueOf = null;
            }
            s.b(sVarE);
            return boolValueOf;
        } catch (Throwable th) {
            s.b(sVarE);
            throw th;
        }
    }

    public static final boolean C(r rVar, r rVar2) {
        r0.o oVarE;
        r0.o oVarE2;
        t0 t0Var;
        t0 t0Var2;
        r0.o oVar = rVar2.f5681d;
        if (!oVar.f5693p) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        r0.o oVar2 = oVar.f5685h;
        d0 d0VarS = p1.f.s(rVar2);
        loop0: while (true) {
            if (d0VarS == null) {
                oVarE = null;
                break;
            }
            if ((d0VarS.f4801y.f4972e.f5684g & 1024) != 0) {
                while (oVar2 != null) {
                    if ((oVar2.f5683f & 1024) != 0) {
                        oVarE = oVar2;
                        h0.e eVar = null;
                        while (oVarE != null) {
                            if (oVarE instanceof r) {
                                break loop0;
                            }
                            if ((oVarE.f5683f & 1024) != 0 && (oVarE instanceof p1.m)) {
                                int i5 = 0;
                                for (r0.o oVar3 = ((p1.m) oVarE).f4921r; oVar3 != null; oVar3 = oVar3.f5686i) {
                                    if ((oVar3.f5683f & 1024) != 0) {
                                        i5++;
                                        if (i5 == 1) {
                                            oVarE = oVar3;
                                        } else {
                                            if (eVar == null) {
                                                eVar = new h0.e(new r0.o[16]);
                                            }
                                            if (oVarE != null) {
                                                eVar.b(oVarE);
                                                oVarE = null;
                                            }
                                            eVar.b(oVar3);
                                        }
                                    }
                                }
                                if (i5 == 1) {
                                }
                            }
                            oVarE = p1.f.e(eVar);
                        }
                    }
                    oVar2 = oVar2.f5685h;
                }
            }
            d0VarS = d0VarS.r();
            oVar2 = (d0VarS == null || (t0Var2 = d0VarS.f4801y) == null) ? null : t0Var2.f4971d;
        }
        if (!b4.l.a(oVarE, rVar)) {
            throw new IllegalStateException("Non child node cannot request focus.");
        }
        int iOrdinal = rVar.z0().ordinal();
        q qVar = q.f6259e;
        if (iOrdinal == 0) {
            p(rVar2);
            rVar.D0(qVar);
            return true;
        }
        if (iOrdinal == 1) {
            if (n(rVar) == null) {
                throw new IllegalArgumentException("ActiveParent with no focused child");
            }
            r rVarN = n(rVar);
            if (rVarN != null ? e(rVarN, false) : true) {
                p(rVar2);
                return true;
            }
        } else if (iOrdinal != 2) {
            if (iOrdinal != 3) {
                throw new i4.p();
            }
            r0.o oVar4 = rVar.f5681d;
            if (!oVar4.f5693p) {
                throw new IllegalStateException("visitAncestors called on an unattached node");
            }
            r0.o oVar5 = oVar4.f5685h;
            d0 d0VarS2 = p1.f.s(rVar);
            loop4: while (true) {
                if (d0VarS2 == null) {
                    oVarE2 = null;
                    break;
                }
                if ((d0VarS2.f4801y.f4972e.f5684g & 1024) != 0) {
                    while (oVar5 != null) {
                        if ((oVar5.f5683f & 1024) != 0) {
                            oVarE2 = oVar5;
                            h0.e eVar2 = null;
                            while (oVarE2 != null) {
                                if (oVarE2 instanceof r) {
                                    break loop4;
                                }
                                if ((oVarE2.f5683f & 1024) != 0 && (oVarE2 instanceof p1.m)) {
                                    int i6 = 0;
                                    for (r0.o oVar6 = ((p1.m) oVarE2).f4921r; oVar6 != null; oVar6 = oVar6.f5686i) {
                                        if ((oVar6.f5683f & 1024) != 0) {
                                            i6++;
                                            if (i6 == 1) {
                                                oVarE2 = oVar6;
                                            } else {
                                                if (eVar2 == null) {
                                                    eVar2 = new h0.e(new r0.o[16]);
                                                }
                                                if (oVarE2 != null) {
                                                    eVar2.b(oVarE2);
                                                    oVarE2 = null;
                                                }
                                                eVar2.b(oVar6);
                                            }
                                        }
                                    }
                                    if (i6 == 1) {
                                    }
                                }
                                oVarE2 = p1.f.e(eVar2);
                            }
                        }
                        oVar5 = oVar5.f5685h;
                    }
                }
                d0VarS2 = d0VarS2.r();
                oVar5 = (d0VarS2 == null || (t0Var = d0VarS2.f4801y) == null) ? null : t0Var.f4971d;
            }
            r rVar3 = (r) oVarE2;
            if (rVar3 == null && ((Boolean) ((androidx.compose.ui.focus.b) ((q1.u) p1.f.t(rVar)).getFocusOwner()).f174a.invoke(null, null)).booleanValue()) {
                p(rVar2);
                rVar.D0(qVar);
                return true;
            }
            if (rVar3 != null && C(rVar3, rVar)) {
                boolean zC = C(rVar, rVar2);
                if (rVar.z0() != qVar) {
                    throw new IllegalStateException("Deactivated node is focused");
                }
                if (zC) {
                    A(rVar3);
                }
                return zC;
            }
        }
        return false;
    }

    public static final boolean D(View view, Integer num, Rect rect) {
        if (!(view instanceof ViewGroup)) {
            return view.requestFocus(num.intValue(), rect);
        }
        ViewGroup viewGroup = (ViewGroup) view;
        if (viewGroup.isFocused()) {
            return true;
        }
        if ((!viewGroup.isFocusable() || view.hasFocus()) && !(view instanceof q1.u)) {
            if (rect != null) {
                View viewFindNextFocusFromRect = FocusFinder.getInstance().findNextFocusFromRect(viewGroup, rect, num.intValue());
                return viewFindNextFocusFromRect != null ? viewFindNextFocusFromRect.requestFocus(num.intValue(), rect) : view.requestFocus(num.intValue(), rect);
            }
            View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(viewGroup, view.hasFocus() ? view.findFocus() : null, num.intValue());
            return viewFindNextFocus != null ? viewFindNextFocus.requestFocus(num.intValue()) : view.requestFocus(num.intValue());
        }
        return view.requestFocus(num.intValue(), rect);
    }

    public static final s E(r rVar) {
        return ((androidx.compose.ui.focus.b) ((q1.u) p1.f.t(rVar)).getFocusOwner()).f181h;
    }

    public static final void F(r rVar) {
        r0.o oVar;
        t0 t0Var;
        r0.o oVar2 = rVar.f5681d;
        if (!oVar2.f5693p) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        r0.o oVar3 = oVar2.f5685h;
        d0 d0VarS = p1.f.s(rVar);
        loop0: while (true) {
            oVar = null;
            if (d0VarS == null) {
                break;
            }
            if ((d0VarS.f4801y.f4972e.f5684g & 1024) != 0) {
                while (oVar3 != null) {
                    if ((oVar3.f5683f & 1024) != 0) {
                        r0.o oVarE = oVar3;
                        h0.e eVar = null;
                        while (oVarE != null) {
                            if (oVarE instanceof r) {
                                oVar = oVarE;
                                break loop0;
                            }
                            if ((oVarE.f5683f & 1024) != 0 && (oVarE instanceof p1.m)) {
                                int i5 = 0;
                                for (r0.o oVar4 = ((p1.m) oVarE).f4921r; oVar4 != null; oVar4 = oVar4.f5686i) {
                                    if ((oVar4.f5683f & 1024) != 0) {
                                        i5++;
                                        if (i5 == 1) {
                                            oVarE = oVar4;
                                        } else {
                                            if (eVar == null) {
                                                eVar = new h0.e(new r0.o[16]);
                                            }
                                            if (oVarE != null) {
                                                eVar.b(oVarE);
                                                oVarE = null;
                                            }
                                            eVar.b(oVar4);
                                        }
                                    }
                                }
                                if (i5 == 1) {
                                }
                            }
                            oVarE = p1.f.e(eVar);
                        }
                    }
                    oVar3 = oVar3.f5685h;
                }
            }
            d0VarS = d0VarS.r();
            oVar3 = (d0VarS == null || (t0Var = d0VarS.f4801y) == null) ? null : t0Var.f4971d;
        }
        r rVar2 = (r) oVar;
        if (rVar2 == null) {
            if (rVar.e(n1.e.f4114a) != null) {
                throw new ClassCastException();
            }
            return;
        }
        o1.g gVar = n1.e.f4114a;
        if (rVar2.e(gVar) != null) {
            throw new ClassCastException();
        }
        if (rVar.e(gVar) != null) {
            throw new ClassCastException();
        }
    }

    public static final boolean G(int i5, c.b bVar, r rVar, x0.d dVar) {
        r rVarH;
        h0.e eVar = new h0.e(new r[16]);
        r0.o oVar = rVar.f5681d;
        if (!oVar.f5693p) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        h0.e eVar2 = new h0.e(new r0.o[16]);
        r0.o oVar2 = oVar.f5686i;
        if (oVar2 == null) {
            p1.f.b(eVar2, oVar);
        } else {
            eVar2.b(oVar2);
        }
        while (eVar2.k()) {
            r0.o oVarE = (r0.o) eVar2.m(eVar2.f2578f - 1);
            if ((oVarE.f5684g & 1024) == 0) {
                p1.f.b(eVar2, oVarE);
            } else {
                while (oVarE != null) {
                    if ((oVarE.f5683f & 1024) != 0) {
                        h0.e eVar3 = null;
                        while (oVarE != null) {
                            if (oVarE instanceof r) {
                                r rVar2 = (r) oVarE;
                                if (rVar2.f5693p) {
                                    eVar.b(rVar2);
                                }
                            } else if ((oVarE.f5683f & 1024) != 0 && (oVarE instanceof p1.m)) {
                                int i6 = 0;
                                for (r0.o oVar3 = ((p1.m) oVarE).f4921r; oVar3 != null; oVar3 = oVar3.f5686i) {
                                    if ((oVar3.f5683f & 1024) != 0) {
                                        i6++;
                                        if (i6 == 1) {
                                            oVarE = oVar3;
                                        } else {
                                            if (eVar3 == null) {
                                                eVar3 = new h0.e(new r0.o[16]);
                                            }
                                            if (oVarE != null) {
                                                eVar3.b(oVarE);
                                                oVarE = null;
                                            }
                                            eVar3.b(oVar3);
                                        }
                                    }
                                }
                                if (i6 == 1) {
                                }
                            }
                            oVarE = p1.f.e(eVar3);
                        }
                        break;
                    }
                    oVarE = oVarE.f5686i;
                }
            }
        }
        while (eVar.k() && (rVarH = h(eVar, dVar, i5)) != null) {
            if (rVarH.y0().f6242a) {
                return ((Boolean) bVar.c(rVarH)).booleanValue();
            }
            if (l(i5, bVar, rVarH, dVar)) {
                return true;
            }
            eVar.l(rVarH);
        }
        return false;
    }

    public static final Integer H(int i5) {
        if (i5 == 5) {
            return 33;
        }
        if (i5 == 6) {
            return 130;
        }
        if (i5 == 3) {
            return 17;
        }
        if (i5 == 4) {
            return 66;
        }
        if (i5 == 1) {
            return 2;
        }
        return i5 == 2 ? 1 : null;
    }

    public static final b I(int i5) {
        if (i5 == 1) {
            return new b(2);
        }
        if (i5 == 2) {
            return new b(1);
        }
        if (i5 == 17) {
            return new b(3);
        }
        if (i5 == 33) {
            return new b(5);
        }
        if (i5 == 66) {
            return new b(4);
        }
        if (i5 != 130) {
            return null;
        }
        return new b(6);
    }

    public static final Boolean J(int i5, c.b bVar, r rVar, x0.d dVar) {
        int iOrdinal = rVar.z0().ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                r rVarN = n(rVar);
                if (rVarN == null) {
                    throw new IllegalStateException("ActiveParent must have a focusedChild");
                }
                int iOrdinal2 = rVarN.z0().ordinal();
                if (iOrdinal2 != 0) {
                    if (iOrdinal2 == 1) {
                        Boolean boolJ = J(i5, bVar, rVarN, dVar);
                        if (!b4.l.a(boolJ, Boolean.FALSE)) {
                            return boolJ;
                        }
                        if (dVar == null) {
                            if (rVarN.z0() != q.f6259e) {
                                throw new IllegalStateException("Searching for active node in inactive hierarchy");
                            }
                            r rVarG = g(rVarN);
                            if (rVarG == null) {
                                throw new IllegalStateException("ActiveParent must have a focusedChild");
                            }
                            dVar = j(rVarG);
                        }
                        return Boolean.valueOf(l(i5, bVar, rVar, dVar));
                    }
                    if (iOrdinal2 != 2) {
                        if (iOrdinal2 != 3) {
                            throw new i4.p();
                        }
                        throw new IllegalStateException("ActiveParent must have a focusedChild");
                    }
                }
                if (dVar == null) {
                    dVar = j(rVarN);
                }
                return Boolean.valueOf(l(i5, bVar, rVar, dVar));
            }
            if (iOrdinal != 2) {
                if (iOrdinal != 3) {
                    throw new i4.p();
                }
                if (rVar.y0().f6242a) {
                    return (Boolean) bVar.c(rVar);
                }
                return dVar == null ? Boolean.valueOf(i(rVar, i5, bVar)) : Boolean.valueOf(G(i5, bVar, rVar, dVar));
            }
        }
        return Boolean.valueOf(i(rVar, i5, bVar));
    }

    /* JADX WARN: Code duplicated, block: B:38:0x007c A[RETURN] */
    public static final boolean a(r rVar, c.b bVar) {
        int iOrdinal = rVar.z0().ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                r rVarN = n(rVar);
                if (rVarN == null) {
                    throw new IllegalStateException("ActiveParent must have a focusedChild");
                }
                int iOrdinal2 = rVarN.z0().ordinal();
                if (iOrdinal2 != 0) {
                    if (iOrdinal2 == 1) {
                        if (a(rVarN, bVar) || m(rVar, rVarN, 2, bVar) || (rVarN.y0().f6242a && ((Boolean) bVar.c(rVarN)).booleanValue())) {
                            return true;
                        }
                        return false;
                    }
                    if (iOrdinal2 != 2) {
                        if (iOrdinal2 != 3) {
                            throw new i4.p();
                        }
                        throw new IllegalStateException("ActiveParent must have a focusedChild");
                    }
                }
                return m(rVar, rVarN, 2, bVar);
            }
            if (iOrdinal != 2) {
                if (iOrdinal != 3) {
                    throw new i4.p();
                }
                if (!y(rVar, bVar)) {
                    if (!(rVar.y0().f6242a ? ((Boolean) bVar.c(rVar)).booleanValue() : false)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return y(rVar, bVar);
    }

    public static final boolean b(x0.d dVar, x0.d dVar2, x0.d dVar3, int i5) {
        float f5;
        float f6;
        boolean zC = c(i5, dVar3, dVar);
        float f7 = dVar3.f6535b;
        float f8 = dVar3.f6537d;
        float f9 = dVar3.f6534a;
        float f10 = dVar3.f6536c;
        float f11 = dVar.f6537d;
        float f12 = dVar.f6535b;
        float f13 = dVar.f6536c;
        float f14 = dVar.f6534a;
        if (zC || !c(i5, dVar2, dVar)) {
            return false;
        }
        if (i5 == 3) {
            if (f14 < f10) {
                return true;
            }
        } else if (i5 == 4) {
            if (f13 > f9) {
                return true;
            }
        } else if (i5 == 5) {
            if (f12 < f8) {
                return true;
            }
        } else {
            if (i5 != 6) {
                throw new IllegalStateException("This function should only be used for 2-D focus search");
            }
            if (f11 > f7) {
                return true;
            }
        }
        if (i5 == 3 || i5 == 4) {
            return true;
        }
        if (i5 == 3) {
            f5 = f14 - dVar2.f6536c;
        } else if (i5 == 4) {
            f5 = dVar2.f6534a - f13;
        } else if (i5 == 5) {
            f5 = f12 - dVar2.f6537d;
        } else {
            if (i5 != 6) {
                throw new IllegalStateException("This function should only be used for 2-D focus search");
            }
            f5 = dVar2.f6535b - f11;
        }
        float fMax = Math.max(0.0f, f5);
        if (i5 == 3) {
            f6 = f14 - f9;
        } else if (i5 == 4) {
            f6 = f10 - f13;
        } else if (i5 == 5) {
            f6 = f12 - f7;
        } else {
            if (i5 != 6) {
                throw new IllegalStateException("This function should only be used for 2-D focus search");
            }
            f6 = f8 - f11;
        }
        return fMax < Math.max(1.0f, f6);
    }

    public static final boolean c(int i5, x0.d dVar, x0.d dVar2) {
        if (i5 == 3 || i5 == 4) {
            return dVar.f6537d > dVar2.f6535b && dVar.f6535b < dVar2.f6537d;
        }
        if (i5 == 5 || i5 == 6) {
            return dVar.f6536c > dVar2.f6534a && dVar.f6534a < dVar2.f6536c;
        }
        throw new IllegalStateException("This function should only be used for 2-D focus search");
    }

    public static final x0.d d(View view) {
        int[] iArr = f6226a;
        view.getLocationInWindow(iArr);
        float f5 = iArr[0];
        return new x0.d(f5, iArr[1], view.getWidth() + f5, iArr[1] + view.getHeight());
    }

    public static final boolean e(r rVar, boolean z2) {
        int iOrdinal = rVar.z0().ordinal();
        q qVar = q.f6260f;
        if (iOrdinal == 0) {
            rVar.D0(qVar);
            A(rVar);
            return true;
        }
        if (iOrdinal == 1) {
            r rVarN = n(rVar);
            if (!(rVarN != null ? e(rVarN, z2) : true)) {
                return false;
            }
            rVar.D0(qVar);
            A(rVar);
            return true;
        }
        if (iOrdinal != 2) {
            if (iOrdinal == 3) {
                return true;
            }
            throw new i4.p();
        }
        if (z2) {
            rVar.D0(qVar);
            A(rVar);
        }
        return z2;
    }

    public static final void f(r rVar, h0.e eVar) {
        r0.o oVar = rVar.f5681d;
        if (!oVar.f5693p) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        h0.e eVar2 = new h0.e(new r0.o[16]);
        r0.o oVar2 = oVar.f5686i;
        if (oVar2 == null) {
            p1.f.b(eVar2, oVar);
        } else {
            eVar2.b(oVar2);
        }
        while (eVar2.k()) {
            r0.o oVarE = (r0.o) eVar2.m(eVar2.f2578f - 1);
            if ((oVarE.f5684g & 1024) == 0) {
                p1.f.b(eVar2, oVarE);
            } else {
                while (oVarE != null) {
                    if ((oVarE.f5683f & 1024) != 0) {
                        h0.e eVar3 = null;
                        while (oVarE != null) {
                            if (oVarE instanceof r) {
                                r rVar2 = (r) oVarE;
                                if (rVar2.f5693p && !p1.f.s(rVar2).G) {
                                    if (rVar2.y0().f6242a) {
                                        eVar.b(rVar2);
                                    } else {
                                        f(rVar2, eVar);
                                    }
                                }
                            } else if ((oVarE.f5683f & 1024) != 0 && (oVarE instanceof p1.m)) {
                                int i5 = 0;
                                for (r0.o oVar3 = ((p1.m) oVarE).f4921r; oVar3 != null; oVar3 = oVar3.f5686i) {
                                    if ((oVar3.f5683f & 1024) != 0) {
                                        i5++;
                                        if (i5 == 1) {
                                            oVarE = oVar3;
                                        } else {
                                            if (eVar3 == null) {
                                                eVar3 = new h0.e(new r0.o[16]);
                                            }
                                            if (oVarE != null) {
                                                eVar3.b(oVarE);
                                                oVarE = null;
                                            }
                                            eVar3.b(oVar3);
                                        }
                                    }
                                }
                                if (i5 == 1) {
                                }
                            }
                            oVarE = p1.f.e(eVar3);
                        }
                        break;
                    }
                    oVarE = oVarE.f5686i;
                }
            }
        }
    }

    public static final r g(r rVar) {
        int iOrdinal = rVar.z0().ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                r0.o oVar = rVar.f5681d;
                if (!oVar.f5693p) {
                    throw new IllegalStateException("visitChildren called on an unattached node");
                }
                h0.e eVar = new h0.e(new r0.o[16]);
                r0.o oVar2 = oVar.f5686i;
                if (oVar2 == null) {
                    p1.f.b(eVar, oVar);
                } else {
                    eVar.b(oVar2);
                }
                while (eVar.k()) {
                    r0.o oVarE = (r0.o) eVar.m(eVar.f2578f - 1);
                    if ((oVarE.f5684g & 1024) == 0) {
                        p1.f.b(eVar, oVarE);
                    } else {
                        while (oVarE != null) {
                            if ((oVarE.f5683f & 1024) != 0) {
                                h0.e eVar2 = null;
                                while (oVarE != null) {
                                    if (oVarE instanceof r) {
                                        r rVarG = g((r) oVarE);
                                        if (rVarG != null) {
                                            return rVarG;
                                        }
                                    } else if ((oVarE.f5683f & 1024) != 0 && (oVarE instanceof p1.m)) {
                                        int i5 = 0;
                                        for (r0.o oVar3 = ((p1.m) oVarE).f4921r; oVar3 != null; oVar3 = oVar3.f5686i) {
                                            if ((oVar3.f5683f & 1024) != 0) {
                                                i5++;
                                                if (i5 == 1) {
                                                    oVarE = oVar3;
                                                } else {
                                                    if (eVar2 == null) {
                                                        eVar2 = new h0.e(new r0.o[16]);
                                                    }
                                                    if (oVarE != null) {
                                                        eVar2.b(oVarE);
                                                        oVarE = null;
                                                    }
                                                    eVar2.b(oVar3);
                                                }
                                            }
                                        }
                                        if (i5 == 1) {
                                        }
                                    }
                                    oVarE = p1.f.e(eVar2);
                                }
                                break;
                            }
                            oVarE = oVarE.f5686i;
                        }
                    }
                }
            } else if (iOrdinal != 2) {
                if (iOrdinal != 3) {
                    throw new i4.p();
                }
            }
            return null;
        }
        return rVar;
    }

    public static final r h(h0.e eVar, x0.d dVar, int i5) {
        x0.d dVarG;
        if (i5 == 3) {
            dVarG = dVar.g(dVar.c() + 1, 0.0f);
        } else if (i5 == 4) {
            dVarG = dVar.g(-(dVar.c() + 1), 0.0f);
        } else if (i5 == 5) {
            dVarG = dVar.g(0.0f, dVar.b() + 1);
        } else {
            if (i5 != 6) {
                throw new IllegalStateException("This function should only be used for 2-D focus search");
            }
            dVarG = dVar.g(0.0f, -(dVar.b() + 1));
        }
        int i6 = eVar.f2578f;
        r rVar = null;
        if (i6 > 0) {
            Object[] objArr = eVar.f2576d;
            int i7 = 0;
            do {
                r rVar2 = (r) objArr[i7];
                if (t(rVar2)) {
                    x0.d dVarJ = j(rVar2);
                    if (r(i5, dVarJ, dVar) && (!r(i5, dVarG, dVar) || b(dVar, dVarJ, dVarG, i5) || (!b(dVar, dVarG, dVarJ, i5) && s(i5, dVar, dVarJ) < s(i5, dVar, dVarG)))) {
                        rVar = rVar2;
                        dVarG = dVarJ;
                    }
                }
                i7++;
            } while (i7 < i6);
        }
        return rVar;
    }

    public static final boolean i(r rVar, int i5, a4.c cVar) {
        x0.d dVar;
        h0.e eVar = new h0.e(new r[16]);
        f(rVar, eVar);
        if (eVar.f2578f <= 1) {
            r rVar2 = (r) (eVar.j() ? null : eVar.f2576d[0]);
            if (rVar2 != null) {
                return ((Boolean) cVar.c(rVar2)).booleanValue();
            }
        } else {
            if (i5 == 7) {
                i5 = 4;
            }
            if (i5 == 4 || i5 == 6) {
                x0.d dVarJ = j(rVar);
                float f5 = dVarJ.f6534a;
                float f6 = dVarJ.f6535b;
                dVar = new x0.d(f5, f6, f5, f6);
            } else {
                if (i5 != 3 && i5 != 5) {
                    throw new IllegalStateException("This function should only be used for 2-D focus search");
                }
                x0.d dVarJ2 = j(rVar);
                float f7 = dVarJ2.f6536c;
                float f8 = dVarJ2.f6537d;
                dVar = new x0.d(f7, f8, f7, f8);
            }
            r rVarH = h(eVar, dVar, i5);
            if (rVarH != null) {
                return ((Boolean) cVar.c(rVarH)).booleanValue();
            }
        }
        return false;
    }

    public static final x0.d j(r rVar) {
        y0 y0Var = rVar.f5688k;
        return y0Var != null ? v0.f(y0Var).k(y0Var, false) : x0.d.f6533e;
    }

    public static final boolean k(r rVar, c.b bVar) {
        int iOrdinal = rVar.z0().ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                r rVarN = n(rVar);
                if (rVarN != null) {
                    return k(rVarN, bVar) || m(rVar, rVarN, 1, bVar);
                }
                throw new IllegalStateException("ActiveParent must have a focusedChild");
            }
            if (iOrdinal != 2) {
                if (iOrdinal == 3) {
                    return rVar.y0().f6242a ? ((Boolean) bVar.c(rVar)).booleanValue() : z(rVar, bVar);
                }
                throw new i4.p();
            }
        }
        return z(rVar, bVar);
    }

    public static final boolean l(int i5, c.b bVar, r rVar, x0.d dVar) {
        if (G(i5, bVar, rVar, dVar)) {
            return true;
        }
        F(rVar);
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:127:0x0192  */
    /* JADX WARN: Code duplicated, block: B:128:0x019d  */
    /* JADX WARN: Code duplicated, block: B:163:0x0140 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:171:0x017d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:86:0x011e  */
    /* JADX WARN: Code duplicated, block: B:88:0x0126  */
    /* JADX WARN: Code duplicated, block: B:90:0x0130 A[ADDED_TO_REGION, LOOP:6: B:90:0x0130->B:118:0x017d, LOOP_START, PHI: r13
      0x0130: PHI (r13v16 r0.o) = (r13v9 r0.o), (r13v17 r0.o) binds: [B:89:0x012e, B:118:0x017d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:91:0x0132  */
    /* JADX WARN: Code duplicated, block: B:93:0x0138  */
    /* JADX WARN: Code duplicated, block: B:95:0x013c  */
    /* JADX WARN: Code duplicated, block: B:98:0x0142  */
    public static final boolean m(r rVar, r rVar2, int i5, c.b bVar) {
        r0.o oVar;
        r0.o oVar2;
        r0.o oVar3;
        d0 d0VarS;
        t0 t0Var;
        r0.o oVarE;
        h0.e eVar;
        if (rVar.z0() != q.f6259e) {
            throw new IllegalStateException("This function should only be used within a parent that has focus.");
        }
        Object[] objArr = new r[16];
        r0.o oVar4 = rVar.f5681d;
        if (!oVar4.f5693p) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        h0.e eVar2 = new h0.e(new r0.o[16]);
        r0.o oVar5 = oVar4.f5686i;
        boolean zBooleanValue = false;
        if (oVar5 == null) {
            p1.f.b(eVar2, oVar4);
        } else {
            eVar2.b(oVar5);
        }
        int i6 = 0;
        while (true) {
            oVar = null;
            if (!eVar2.k()) {
                break;
            }
            r0.o oVarE2 = (r0.o) eVar2.m(eVar2.f2578f - 1);
            if ((oVarE2.f5684g & 1024) == 0) {
                p1.f.b(eVar2, oVarE2);
            } else {
                while (oVarE2 != null) {
                    if ((oVarE2.f5683f & 1024) != 0) {
                        h0.e eVar3 = null;
                        while (oVarE2 != null) {
                            if (oVarE2 instanceof r) {
                                r rVar3 = (r) oVarE2;
                                int i7 = i6 + 1;
                                if (objArr.length < i7) {
                                    int length = objArr.length;
                                    Object[] objArr2 = new Object[Math.max(i7, length * 2)];
                                    System.arraycopy(objArr, 0, objArr2, 0, length);
                                    objArr = objArr2;
                                }
                                objArr[i6] = rVar3;
                                i6 = i7;
                            } else if ((oVarE2.f5683f & 1024) != 0 && (oVarE2 instanceof p1.m)) {
                                int i8 = 0;
                                for (r0.o oVar6 = ((p1.m) oVarE2).f4921r; oVar6 != null; oVar6 = oVar6.f5686i) {
                                    if ((oVar6.f5683f & 1024) != 0) {
                                        i8++;
                                        if (i8 == 1) {
                                            oVarE2 = oVar6;
                                        } else {
                                            if (eVar3 == null) {
                                                eVar3 = new h0.e(new r0.o[16]);
                                            }
                                            if (oVarE2 != null) {
                                                eVar3.b(oVarE2);
                                                oVarE2 = null;
                                            }
                                            eVar3.b(oVar6);
                                        }
                                    }
                                }
                                if (i8 == 1) {
                                }
                            }
                            oVarE2 = p1.f.e(eVar3);
                        }
                        break;
                    }
                    oVarE2 = oVarE2.f5686i;
                }
            }
        }
        Arrays.sort(objArr, 0, i6, u.f6271a);
        if (i5 == 1) {
            int i9 = new e4.d(0, i6 - 1, 1).f2075e;
            if (i9 >= 0) {
                boolean z2 = false;
                int i10 = 0;
                while (true) {
                    if (z2) {
                        r rVar4 = (r) objArr[i10];
                        if (t(rVar4) && k(rVar4, bVar)) {
                            zBooleanValue = true;
                        }
                    }
                    if (b4.l.a(objArr[i10], rVar2)) {
                        z2 = true;
                    }
                    if (i10 != i9) {
                        i10++;
                    }
                }
            }
            if (i5 != 1 && rVar.y0().f6242a) {
                oVar2 = rVar.f5681d;
                if (oVar2.f5693p) {
                    throw new IllegalStateException("visitAncestors called on an unattached node");
                }
                oVar3 = oVar2.f5685h;
                d0VarS = p1.f.s(rVar);
                loop5: while (d0VarS != null) {
                    if ((d0VarS.f4801y.f4972e.f5684g & 1024) != 0) {
                        while (oVar3 != null) {
                            if ((oVar3.f5683f & 1024) != 0) {
                                oVarE = oVar3;
                                eVar = null;
                                while (oVarE != null) {
                                    if (oVarE instanceof r) {
                                        oVar = oVarE;
                                        break loop5;
                                    }
                                    if ((oVarE.f5683f & 1024) == 0 && (oVarE instanceof p1.m)) {
                                        int i11 = 0;
                                        for (r0.o oVar7 = ((p1.m) oVarE).f4921r; oVar7 != null; oVar7 = oVar7.f5686i) {
                                            if ((oVar7.f5683f & 1024) != 0) {
                                                i11++;
                                                if (i11 == 1) {
                                                    oVarE = oVar7;
                                                } else {
                                                    if (eVar == null) {
                                                        eVar = new h0.e(new r0.o[16]);
                                                    }
                                                    if (oVarE != null) {
                                                        eVar.b(oVarE);
                                                        oVarE = null;
                                                    }
                                                    eVar.b(oVar7);
                                                }
                                            }
                                        }
                                        if (i11 == 1) {
                                        }
                                    }
                                    oVarE = p1.f.e(eVar);
                                }
                            }
                            oVar3 = oVar3.f5685h;
                        }
                    }
                    d0VarS = d0VarS.r();
                    oVar3 = (d0VarS != null || (t0Var = d0VarS.f4801y) == null) ? null : t0Var.f4971d;
                }
                if (oVar != null) {
                    zBooleanValue = ((Boolean) bVar.c(rVar)).booleanValue();
                }
            }
        } else {
            if (i5 != 2) {
                throw new IllegalStateException("This function should only be used for 1-D focus search");
            }
            int i12 = new e4.d(0, i6 - 1, 1).f2075e;
            if (i12 >= 0) {
                boolean z3 = false;
                while (true) {
                    if (z3) {
                        r rVar5 = (r) objArr[i12];
                        if (t(rVar5) && a(rVar5, bVar)) {
                            zBooleanValue = true;
                        }
                    }
                    if (b4.l.a(objArr[i12], rVar2)) {
                        z3 = true;
                    }
                    if (i12 != 0) {
                        i12--;
                    }
                }
            }
            if (i5 != 1) {
                oVar2 = rVar.f5681d;
                if (oVar2.f5693p) {
                    throw new IllegalStateException("visitAncestors called on an unattached node");
                }
                oVar3 = oVar2.f5685h;
                d0VarS = p1.f.s(rVar);
                loop5: while (d0VarS != null) {
                    if ((d0VarS.f4801y.f4972e.f5684g & 1024) != 0) {
                        while (oVar3 != null) {
                            if ((oVar3.f5683f & 1024) != 0) {
                                oVarE = oVar3;
                                eVar = null;
                                while (oVarE != null) {
                                    if (oVarE instanceof r) {
                                        oVar = oVarE;
                                        break loop5;
                                    }
                                    if ((oVarE.f5683f & 1024) == 0) {
                                    }
                                    oVarE = p1.f.e(eVar);
                                }
                            }
                            oVar3 = oVar3.f5685h;
                        }
                    }
                    d0VarS = d0VarS.r();
                    if (d0VarS != null) {
                    }
                }
                if (oVar != null) {
                    zBooleanValue = ((Boolean) bVar.c(rVar)).booleanValue();
                }
            }
        }
        if (zBooleanValue) {
            return true;
        }
        F(rVar);
        return false;
    }

    public static final r n(r rVar) {
        int iOrdinal;
        r0.o oVar = rVar.f5681d;
        boolean z2 = oVar.f5693p;
        if (z2) {
            if (!z2) {
                throw new IllegalStateException("visitChildren called on an unattached node");
            }
            h0.e eVar = new h0.e(new r0.o[16]);
            r0.o oVar2 = oVar.f5686i;
            if (oVar2 == null) {
                p1.f.b(eVar, oVar);
            } else {
                eVar.b(oVar2);
            }
            while (eVar.k()) {
                r0.o oVarE = (r0.o) eVar.m(eVar.f2578f - 1);
                if ((oVarE.f5684g & 1024) == 0) {
                    p1.f.b(eVar, oVarE);
                } else {
                    while (oVarE != null) {
                        if ((oVarE.f5683f & 1024) != 0) {
                            h0.e eVar2 = null;
                            while (oVarE != null) {
                                if (oVarE instanceof r) {
                                    r rVar2 = (r) oVarE;
                                    if (rVar2.f5681d.f5693p && ((iOrdinal = rVar2.z0().ordinal()) == 0 || iOrdinal == 1 || iOrdinal == 2)) {
                                        return rVar2;
                                    }
                                } else if ((oVarE.f5683f & 1024) != 0 && (oVarE instanceof p1.m)) {
                                    int i5 = 0;
                                    for (r0.o oVar3 = ((p1.m) oVarE).f4921r; oVar3 != null; oVar3 = oVar3.f5686i) {
                                        if ((oVar3.f5683f & 1024) != 0) {
                                            i5++;
                                            if (i5 == 1) {
                                                oVarE = oVar3;
                                            } else {
                                                if (eVar2 == null) {
                                                    eVar2 = new h0.e(new r0.o[16]);
                                                }
                                                if (oVarE != null) {
                                                    eVar2.b(oVarE);
                                                    oVarE = null;
                                                }
                                                eVar2.b(oVar3);
                                            }
                                        }
                                    }
                                    if (i5 == 1) {
                                    }
                                }
                                oVarE = p1.f.e(eVar2);
                            }
                            break;
                        }
                        oVarE = oVarE.f5686i;
                    }
                }
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final q o(c cVar) {
        r0.o oVar = (r0.o) cVar;
        r0.o oVarE = oVar.f5681d;
        h0.e eVar = null;
        while (true) {
            int i5 = 0;
            if (oVarE == null) {
                r0.o oVar2 = oVar.f5681d;
                if (!oVar2.f5693p) {
                    throw new IllegalStateException("visitChildren called on an unattached node");
                }
                h0.e eVar2 = new h0.e(new r0.o[16]);
                r0.o oVar3 = oVar2.f5686i;
                if (oVar3 == null) {
                    p1.f.b(eVar2, oVar2);
                } else {
                    eVar2.b(oVar3);
                }
                while (eVar2.k()) {
                    r0.o oVarE2 = (r0.o) eVar2.m(eVar2.f2578f - 1);
                    if ((oVarE2.f5684g & 1024) == 0) {
                        p1.f.b(eVar2, oVarE2);
                    } else {
                        while (oVarE2 != null) {
                            if ((oVarE2.f5683f & 1024) != 0) {
                                h0.e eVar3 = null;
                                while (oVarE2 != null) {
                                    if (oVarE2 instanceof r) {
                                        q qVarZ0 = ((r) oVarE2).z0();
                                        int iOrdinal = qVarZ0.ordinal();
                                        if (iOrdinal == 0 || iOrdinal == 1 || iOrdinal == 2) {
                                            return qVarZ0;
                                        }
                                    } else if ((oVarE2.f5683f & 1024) != 0 && (oVarE2 instanceof p1.m)) {
                                        int i6 = 0;
                                        for (r0.o oVar4 = ((p1.m) oVarE2).f4921r; oVar4 != null; oVar4 = oVar4.f5686i) {
                                            if ((oVar4.f5683f & 1024) != 0) {
                                                i6++;
                                                if (i6 == 1) {
                                                    oVarE2 = oVar4;
                                                } else {
                                                    if (eVar3 == null) {
                                                        eVar3 = new h0.e(new r0.o[16]);
                                                    }
                                                    if (oVarE2 != null) {
                                                        eVar3.b(oVarE2);
                                                        oVarE2 = null;
                                                    }
                                                    eVar3.b(oVar4);
                                                }
                                            }
                                        }
                                        if (i6 == 1) {
                                        }
                                    }
                                    oVarE2 = p1.f.e(eVar3);
                                }
                                break;
                            }
                            oVarE2 = oVarE2.f5686i;
                        }
                    }
                }
                return q.f6260f;
            }
            if (oVarE instanceof r) {
                q qVarZ1 = ((r) oVarE).z0();
                int iOrdinal2 = qVarZ1.ordinal();
                if (iOrdinal2 == 0 || iOrdinal2 == 1 || iOrdinal2 == 2) {
                    return qVarZ1;
                }
            } else if ((oVarE.f5683f & 1024) != 0 && (oVarE instanceof p1.m)) {
                for (r0.o oVar5 = ((p1.m) oVarE).f4921r; oVar5 != null; oVar5 = oVar5.f5686i) {
                    if ((oVar5.f5683f & 1024) != 0) {
                        i5++;
                        if (i5 == 1) {
                            oVarE = oVar5;
                        } else {
                            if (eVar == null) {
                                eVar = new h0.e(new r0.o[16]);
                            }
                            if (oVarE != null) {
                                eVar.b(oVarE);
                                oVarE = null;
                            }
                            eVar.b(oVar5);
                        }
                    }
                }
                if (i5 == 1) {
                }
            }
            oVarE = p1.f.e(eVar);
        }
    }

    public static final void p(r rVar) {
        p1.f.p(rVar, new t(rVar, 0));
        int iOrdinal = rVar.z0().ordinal();
        if (iOrdinal == 1 || iOrdinal == 3) {
            rVar.D0(q.f6258d);
        }
    }

    public static final void q(r rVar) {
        e eVar = ((androidx.compose.ui.focus.b) ((q1.u) p1.f.t(rVar)).getFocusOwner()).f180g;
        eVar.b(eVar.f6229c, rVar);
    }

    public static final boolean r(int i5, x0.d dVar, x0.d dVar2) {
        float f5 = dVar.f6535b;
        float f6 = dVar.f6537d;
        float f7 = dVar.f6534a;
        float f8 = dVar.f6536c;
        if (i5 == 3) {
            float f9 = dVar2.f6536c;
            float f10 = dVar2.f6534a;
            return (f9 > f8 || f10 >= f8) && f10 > f7;
        }
        if (i5 == 4) {
            float f11 = dVar2.f6534a;
            float f12 = dVar2.f6536c;
            return (f11 < f7 || f12 <= f7) && f12 < f8;
        }
        if (i5 == 5) {
            float f13 = dVar2.f6537d;
            float f14 = dVar2.f6535b;
            return (f13 > f6 || f14 >= f6) && f14 > f5;
        }
        if (i5 != 6) {
            throw new IllegalStateException("This function should only be used for 2-D focus search");
        }
        float f15 = dVar2.f6535b;
        float f16 = dVar2.f6537d;
        return (f15 < f5 || f16 <= f5) && f16 < f6;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0036  */
    public static final long s(int i5, x0.d dVar, x0.d dVar2) {
        float f5;
        float f6;
        float f7;
        float fB;
        float fB2;
        float f8 = dVar2.f6535b;
        float f9 = dVar2.f6534a;
        if (i5 != 3) {
            if (i5 == 4) {
                f5 = f9 - dVar.f6536c;
            } else if (i5 == 5) {
                f6 = dVar.f6535b;
                f7 = dVar2.f6537d;
            } else {
                if (i5 != 6) {
                    throw new IllegalStateException("This function should only be used for 2-D focus search");
                }
                f5 = f8 - dVar.f6537d;
            }
            long jAbs = (long) Math.abs(Math.max(0.0f, f5));
            if (i5 != 3 || i5 == 4) {
                float f10 = 2;
                fB = (dVar.b() / f10) + dVar.f6535b;
                fB2 = (dVar2.b() / f10) + f8;
            } else {
                if (i5 != 5 && i5 != 6) {
                    throw new IllegalStateException("This function should only be used for 2-D focus search");
                }
                float f11 = 2;
                fB = (dVar.c() / f11) + dVar.f6534a;
                fB2 = (dVar2.c() / f11) + f9;
            }
            long jAbs2 = (long) Math.abs(fB - fB2);
            return (jAbs2 * jAbs2) + (((long) 13) * jAbs * jAbs);
        }
        f6 = dVar.f6534a;
        f7 = dVar2.f6536c;
        f5 = f6 - f7;
        long jAbs3 = (long) Math.abs(Math.max(0.0f, f5));
        if (i5 != 3) {
            float f12 = 2;
            fB = (dVar.b() / f12) + dVar.f6535b;
            fB2 = (dVar2.b() / f12) + f8;
        } else {
            if (i5 != 5) {
                throw new IllegalStateException("This function should only be used for 2-D focus search");
            }
            float f13 = 2;
            fB = (dVar.c() / f13) + dVar.f6534a;
            fB2 = (dVar2.c() / f13) + f9;
        }
        long jAbs4 = (long) Math.abs(fB - fB2);
        return (jAbs4 * jAbs4) + (((long) 13) * jAbs3 * jAbs3);
    }

    public static final boolean t(r rVar) {
        d0 d0Var;
        y0 y0Var;
        d0 d0Var2;
        y0 y0Var2 = rVar.f5688k;
        return (y0Var2 == null || (d0Var = y0Var2.f4998o) == null || !d0Var.E() || (y0Var = rVar.f5688k) == null || (d0Var2 = y0Var.f4998o) == null || !d0Var2.D()) ? false : true;
    }

    public static final int u(r rVar) {
        int iOrdinal = rVar.z0().ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                r rVarN = n(rVar);
                if (rVarN == null) {
                    throw new IllegalArgumentException("ActiveParent with no focused child");
                }
                int iU = u(rVarN);
                if (iU == 1) {
                    iU = 0;
                }
                if (iU != 0) {
                    return iU;
                }
                if (!rVar.f6262q) {
                    rVar.f6262q = true;
                    try {
                        rVar.y0().f6252k.getClass();
                        n nVar = n.f6254b;
                        return 1;
                    } finally {
                        rVar.f6262q = false;
                    }
                }
            } else {
                if (iOrdinal == 2) {
                    return 2;
                }
                if (iOrdinal != 3) {
                    throw new i4.p();
                }
            }
        }
        return 1;
    }

    public static final void v(r rVar) {
        if (rVar.f6263r) {
            return;
        }
        rVar.f6263r = true;
        try {
            rVar.y0().f6251j.getClass();
            n nVar = n.f6254b;
        } finally {
            rVar.f6263r = false;
        }
    }

    public static final int w(r rVar) {
        r0.o oVar;
        t0 t0Var;
        int iOrdinal = rVar.z0().ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                r rVarN = n(rVar);
                if (rVarN != null) {
                    return u(rVarN);
                }
                throw new IllegalArgumentException("ActiveParent with no focused child");
            }
            if (iOrdinal != 2) {
                if (iOrdinal != 3) {
                    throw new i4.p();
                }
                r0.o oVar2 = rVar.f5681d;
                if (!oVar2.f5693p) {
                    throw new IllegalStateException("visitAncestors called on an unattached node");
                }
                r0.o oVar3 = oVar2.f5685h;
                d0 d0VarS = p1.f.s(rVar);
                loop0: while (true) {
                    oVar = null;
                    if (d0VarS == null) {
                        break;
                    }
                    if ((d0VarS.f4801y.f4972e.f5684g & 1024) != 0) {
                        while (oVar3 != null) {
                            if ((oVar3.f5683f & 1024) != 0) {
                                r0.o oVarE = oVar3;
                                h0.e eVar = null;
                                while (oVarE != null) {
                                    if (oVarE instanceof r) {
                                        oVar = oVarE;
                                        break loop0;
                                    }
                                    if ((oVarE.f5683f & 1024) != 0 && (oVarE instanceof p1.m)) {
                                        int i5 = 0;
                                        for (r0.o oVar4 = ((p1.m) oVarE).f4921r; oVar4 != null; oVar4 = oVar4.f5686i) {
                                            if ((oVar4.f5683f & 1024) != 0) {
                                                i5++;
                                                if (i5 == 1) {
                                                    oVarE = oVar4;
                                                } else {
                                                    if (eVar == null) {
                                                        eVar = new h0.e(new r0.o[16]);
                                                    }
                                                    if (oVarE != null) {
                                                        eVar.b(oVarE);
                                                        oVarE = null;
                                                    }
                                                    eVar.b(oVar4);
                                                }
                                            }
                                        }
                                        if (i5 == 1) {
                                        }
                                    }
                                    oVarE = p1.f.e(eVar);
                                }
                            }
                            oVar3 = oVar3.f5685h;
                        }
                    }
                    d0VarS = d0VarS.r();
                    oVar3 = (d0VarS == null || (t0Var = d0VarS.f4801y) == null) ? null : t0Var.f4971d;
                }
                r rVar2 = (r) oVar;
                if (rVar2 != null) {
                    int iOrdinal2 = rVar2.z0().ordinal();
                    if (iOrdinal2 == 0) {
                        v(rVar2);
                        return 1;
                    }
                    if (iOrdinal2 == 1) {
                        return w(rVar2);
                    }
                    if (iOrdinal2 == 2) {
                        return 2;
                    }
                    if (iOrdinal2 != 3) {
                        throw new i4.p();
                    }
                    int iW = w(rVar2);
                    int i6 = iW != 1 ? iW : 0;
                    if (i6 != 0) {
                        return i6;
                    }
                    v(rVar2);
                    return 1;
                }
            }
        }
        return 1;
    }

    /* JADX WARN: Code duplicated, block: B:63:0x00c4  */
    public static final boolean x(r rVar) {
        r0.o oVarE;
        t0 t0Var;
        int iOrdinal = rVar.z0().ordinal();
        boolean zC = true;
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                r rVarN = n(rVar);
                if (rVarN != null ? e(rVarN, false) : true) {
                    p(rVar);
                } else {
                    zC = false;
                }
            } else if (iOrdinal != 2) {
                if (iOrdinal != 3) {
                    throw new i4.p();
                }
                r0.o oVar = rVar.f5681d;
                if (!oVar.f5693p) {
                    throw new IllegalStateException("visitAncestors called on an unattached node");
                }
                r0.o oVar2 = oVar.f5685h;
                d0 d0VarS = p1.f.s(rVar);
                loop0: while (true) {
                    if (d0VarS == null) {
                        oVarE = null;
                        break;
                    }
                    if ((d0VarS.f4801y.f4972e.f5684g & 1024) != 0) {
                        while (oVar2 != null) {
                            if ((oVar2.f5683f & 1024) != 0) {
                                oVarE = oVar2;
                                h0.e eVar = null;
                                while (oVarE != null) {
                                    if (oVarE instanceof r) {
                                        break loop0;
                                    }
                                    if ((oVarE.f5683f & 1024) != 0 && (oVarE instanceof p1.m)) {
                                        int i5 = 0;
                                        for (r0.o oVar3 = ((p1.m) oVarE).f4921r; oVar3 != null; oVar3 = oVar3.f5686i) {
                                            if ((oVar3.f5683f & 1024) != 0) {
                                                i5++;
                                                if (i5 == 1) {
                                                    oVarE = oVar3;
                                                } else {
                                                    if (eVar == null) {
                                                        eVar = new h0.e(new r0.o[16]);
                                                    }
                                                    if (oVarE != null) {
                                                        eVar.b(oVarE);
                                                        oVarE = null;
                                                    }
                                                    eVar.b(oVar3);
                                                }
                                            }
                                        }
                                        if (i5 == 1) {
                                        }
                                    }
                                    oVarE = p1.f.e(eVar);
                                }
                            }
                            oVar2 = oVar2.f5685h;
                        }
                    }
                    d0VarS = d0VarS.r();
                    oVar2 = (d0VarS == null || (t0Var = d0VarS.f4801y) == null) ? null : t0Var.f4971d;
                }
                r rVar2 = (r) oVarE;
                if (rVar2 != null) {
                    q qVarZ0 = rVar2.z0();
                    zC = C(rVar2, rVar);
                    if (zC && qVarZ0 != rVar2.z0()) {
                        A(rVar2);
                    }
                } else if (((Boolean) ((androidx.compose.ui.focus.b) ((q1.u) p1.f.t(rVar)).getFocusOwner()).f174a.invoke(null, null)).booleanValue()) {
                    p(rVar);
                } else {
                    zC = false;
                }
            }
        }
        if (zC) {
            A(rVar);
        }
        return zC;
    }

    public static final boolean y(r rVar, c.b bVar) {
        Object[] objArr = new r[16];
        r0.o oVar = rVar.f5681d;
        if (!oVar.f5693p) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        h0.e eVar = new h0.e(new r0.o[16]);
        r0.o oVar2 = oVar.f5686i;
        if (oVar2 == null) {
            p1.f.b(eVar, oVar);
        } else {
            eVar.b(oVar2);
        }
        int i5 = 0;
        while (eVar.k()) {
            r0.o oVarE = (r0.o) eVar.m(eVar.f2578f - 1);
            if ((oVarE.f5684g & 1024) == 0) {
                p1.f.b(eVar, oVarE);
            } else {
                while (oVarE != null) {
                    if ((oVarE.f5683f & 1024) != 0) {
                        h0.e eVar2 = null;
                        while (oVarE != null) {
                            if (oVarE instanceof r) {
                                r rVar2 = (r) oVarE;
                                int i6 = i5 + 1;
                                if (objArr.length < i6) {
                                    int length = objArr.length;
                                    Object[] objArr2 = new Object[Math.max(i6, length * 2)];
                                    System.arraycopy(objArr, 0, objArr2, 0, length);
                                    objArr = objArr2;
                                }
                                objArr[i5] = rVar2;
                                i5 = i6;
                            } else if ((oVarE.f5683f & 1024) != 0 && (oVarE instanceof p1.m)) {
                                int i7 = 0;
                                for (r0.o oVar3 = ((p1.m) oVarE).f4921r; oVar3 != null; oVar3 = oVar3.f5686i) {
                                    if ((oVar3.f5683f & 1024) != 0) {
                                        i7++;
                                        if (i7 == 1) {
                                            oVarE = oVar3;
                                        } else {
                                            if (eVar2 == null) {
                                                eVar2 = new h0.e(new r0.o[16]);
                                            }
                                            if (oVarE != null) {
                                                eVar2.b(oVarE);
                                                oVarE = null;
                                            }
                                            eVar2.b(oVar3);
                                        }
                                    }
                                }
                                if (i7 == 1) {
                                }
                            }
                            oVarE = p1.f.e(eVar2);
                        }
                        break;
                    }
                    oVarE = oVarE.f5686i;
                }
            }
        }
        Arrays.sort(objArr, 0, i5, u.f6271a);
        if (i5 > 0) {
            int i8 = i5 - 1;
            do {
                r rVar3 = (r) objArr[i8];
                if (t(rVar3) && a(rVar3, bVar)) {
                    return true;
                }
                i8--;
            } while (i8 >= 0);
        }
        return false;
    }

    public static final boolean z(r rVar, c.b bVar) {
        Object[] objArr = new r[16];
        r0.o oVar = rVar.f5681d;
        if (!oVar.f5693p) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        h0.e eVar = new h0.e(new r0.o[16]);
        r0.o oVar2 = oVar.f5686i;
        if (oVar2 == null) {
            p1.f.b(eVar, oVar);
        } else {
            eVar.b(oVar2);
        }
        int i5 = 0;
        while (eVar.k()) {
            r0.o oVarE = (r0.o) eVar.m(eVar.f2578f - 1);
            if ((oVarE.f5684g & 1024) == 0) {
                p1.f.b(eVar, oVarE);
            } else {
                while (oVarE != null) {
                    if ((oVarE.f5683f & 1024) != 0) {
                        h0.e eVar2 = null;
                        while (oVarE != null) {
                            if (oVarE instanceof r) {
                                r rVar2 = (r) oVarE;
                                int i6 = i5 + 1;
                                if (objArr.length < i6) {
                                    int length = objArr.length;
                                    Object[] objArr2 = new Object[Math.max(i6, length * 2)];
                                    System.arraycopy(objArr, 0, objArr2, 0, length);
                                    objArr = objArr2;
                                }
                                objArr[i5] = rVar2;
                                i5 = i6;
                            } else if ((oVarE.f5683f & 1024) != 0 && (oVarE instanceof p1.m)) {
                                int i7 = 0;
                                for (r0.o oVar3 = ((p1.m) oVarE).f4921r; oVar3 != null; oVar3 = oVar3.f5686i) {
                                    if ((oVar3.f5683f & 1024) != 0) {
                                        i7++;
                                        if (i7 == 1) {
                                            oVarE = oVar3;
                                        } else {
                                            if (eVar2 == null) {
                                                eVar2 = new h0.e(new r0.o[16]);
                                            }
                                            if (oVarE != null) {
                                                eVar2.b(oVarE);
                                                oVarE = null;
                                            }
                                            eVar2.b(oVar3);
                                        }
                                    }
                                }
                                if (i7 == 1) {
                                }
                            }
                            oVarE = p1.f.e(eVar2);
                        }
                        break;
                    }
                    oVarE = oVarE.f5686i;
                }
            }
        }
        Arrays.sort(objArr, 0, i5, u.f6271a);
        if (i5 > 0) {
            int i8 = 0;
            do {
                r rVar3 = (r) objArr[i8];
                if (t(rVar3) && k(rVar3, bVar)) {
                    return true;
                }
                i8++;
            } while (i8 < i5);
        }
        return false;
    }
}

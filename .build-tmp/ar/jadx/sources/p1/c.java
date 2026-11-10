package p1;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c extends r0.o implements w, o, l1, j1, o1.e, o1.f, h1, v, p, w0.c, w0.l, w0.o, f1, v0.a {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public r0.n f4773q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public o1.a f4774r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public HashSet f4775s;

    public final void A0() {
        if (this.f5693p) {
            this.f4775s.clear();
            ((q1.u) f.t(this)).getSnapshotObserver().a(this, e.f4803f, new b(this, 1));
        }
    }

    @Override // p1.p
    public final void D(y0 y0Var) {
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.layout.OnGloballyPositionedModifier", this.f4773q);
        throw new ClassCastException();
    }

    @Override // w0.l
    public final void K(w0.i iVar) {
        d4.a.L("applyFocusProperties called on wrong node");
        throw null;
    }

    @Override // p1.j1
    public final boolean P() {
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.input.pointer.PointerInputModifier", this.f4773q);
        throw new ClassCastException();
    }

    @Override // p1.j1
    public final void W() {
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.input.pointer.PointerInputModifier", this.f4773q);
        throw new ClassCastException();
    }

    @Override // p1.l1
    public final void Z(w1.i iVar) {
        r0.n nVar = this.f4773q;
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.semantics.SemanticsModifier", nVar);
        w1.i iVarG = ((w1.j) nVar).g();
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.semantics.SemanticsConfiguration", iVar);
        LinkedHashMap linkedHashMap = iVar.f6308d;
        if (iVarG.f6309e) {
            iVar.f6309e = true;
        }
        if (iVarG.f6310f) {
            iVar.f6310f = true;
        }
        for (Map.Entry entry : iVarG.f6308d.entrySet()) {
            w1.t tVar = (w1.t) entry.getKey();
            Object value = entry.getValue();
            if (!linkedHashMap.containsKey(tVar)) {
                linkedHashMap.put(tVar, value);
            } else if (value instanceof w1.a) {
                Object obj = linkedHashMap.get(tVar);
                b4.l.c("null cannot be cast to non-null type androidx.compose.ui.semantics.AccessibilityAction<*>", obj);
                w1.a aVar = (w1.a) obj;
                String str = aVar.f6272a;
                if (str == null) {
                    str = ((w1.a) value).f6272a;
                }
                p3.c cVar = aVar.f6273b;
                if (cVar == null) {
                    cVar = ((w1.a) value).f6273b;
                }
                linkedHashMap.put(tVar, new w1.a(str, cVar));
            }
        }
    }

    @Override // v0.a
    public final k2.b a() {
        return f.s(this).f4796t;
    }

    @Override // p1.w
    public final int a0(m0 m0Var, n1.e0 e0Var, int i5) {
        r0.n nVar = this.f4773q;
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.layout.LayoutModifier", nVar);
        return ((n1.r) nVar).c(new n1.o(m0Var, m0Var.getLayoutDirection()), new n1.j(e0Var, 2, 1, 1), h1.c.c(0, i5, 7)).l();
    }

    @Override // v0.a
    public final long b() {
        return a.a.O(f.q(this, 128).f4132f);
    }

    @Override // p1.w
    public final n1.g0 c(n1.h0 h0Var, n1.e0 e0Var, long j5) {
        r0.n nVar = this.f4773q;
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.layout.LayoutModifier", nVar);
        return ((n1.r) nVar).c(h0Var, e0Var, j5);
    }

    @Override // p1.o
    public final void d(f0 f0Var) {
        r0.n nVar = this.f4773q;
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.draw.DrawModifier", nVar);
        ((v0.e) nVar).d(f0Var);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v2, types: [a4.a, b4.m] */
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
    @Override // o1.e, o1.f
    public final Object e(o1.g gVar) {
        t0 t0Var;
        this.f4775s.add(gVar);
        r0.o oVar = this.f5681d;
        if (!oVar.f5693p) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        r0.o oVar2 = oVar.f5685h;
        d0 d0VarS = f.s(this);
        while (d0VarS != null) {
            if ((d0VarS.f4801y.f4972e.f5684g & 32) != 0) {
                while (oVar2 != null) {
                    if ((oVar2.f5683f & 32) != 0) {
                        ?? E = oVar2;
                        ?? eVar = 0;
                        while (E != 0) {
                            if (E instanceof o1.e) {
                                o1.e eVar2 = (o1.e) E;
                                if (eVar2.g().p(gVar)) {
                                    return eVar2.g().t(gVar);
                                }
                            } else if ((E.f5683f & 32) != 0 && (E instanceof m)) {
                                r0.o oVar3 = ((m) E).f4921r;
                                int i5 = 0;
                                E = E;
                                eVar = eVar;
                                while (oVar3 != null) {
                                    if ((oVar3.f5683f & 32) != 0) {
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
                            E = f.e(eVar);
                        }
                    }
                    oVar2 = oVar2.f5685h;
                }
            }
            d0VarS = d0VarS.r();
            oVar2 = (d0VarS == null || (t0Var = d0VarS.f4801y) == null) ? null : t0Var.f4971d;
        }
        return gVar.f4376a.a();
    }

    @Override // p1.h1
    public final Object e0(Object obj) {
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.layout.ParentDataModifier", this.f4773q);
        throw new ClassCastException();
    }

    @Override // p1.o
    public final void f0() {
        f.l(this);
    }

    @Override // o1.e
    public final a.a g() {
        o1.a aVar = this.f4774r;
        return aVar != null ? aVar : o1.b.f4369l;
    }

    @Override // p1.w
    public final int g0(m0 m0Var, n1.e0 e0Var, int i5) {
        r0.n nVar = this.f4773q;
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.layout.LayoutModifier", nVar);
        return ((n1.r) nVar).c(new n1.o(m0Var, m0Var.getLayoutDirection()), new n1.j(e0Var, 1, 2, 1), h1.c.c(i5, 0, 13)).e();
    }

    @Override // v0.a
    public final k2.k getLayoutDirection() {
        return f.s(this).f4797u;
    }

    @Override // p1.j1
    public final boolean h0() {
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.input.pointer.PointerInputModifier", this.f4773q);
        throw new ClassCastException();
    }

    @Override // w0.c
    public final void k0(w0.q qVar) {
        d4.a.L("onFocusEvent called on wrong node");
        throw null;
    }

    @Override // p1.v
    public final void m(long j5) {
    }

    @Override // p1.w
    public final int m0(m0 m0Var, n1.e0 e0Var, int i5) {
        r0.n nVar = this.f4773q;
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.layout.LayoutModifier", nVar);
        return ((n1.r) nVar).c(new n1.o(m0Var, m0Var.getLayoutDirection()), new n1.j(e0Var, 1, 1, 1), h1.c.c(0, i5, 7)).l();
    }

    @Override // p1.v
    public final void r(n1.p pVar) {
    }

    @Override // r0.o
    public final void r0() {
        y0(true);
    }

    @Override // r0.o
    public final void s0() {
        z0();
    }

    public final String toString() {
        return this.f4773q.toString();
    }

    @Override // p1.f1
    public final boolean w() {
        return this.f5693p;
    }

    @Override // p1.w
    public final int x(m0 m0Var, n1.e0 e0Var, int i5) {
        r0.n nVar = this.f4773q;
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.layout.LayoutModifier", nVar);
        return ((n1.r) nVar).c(new n1.o(m0Var, m0Var.getLayoutDirection()), new n1.j(e0Var, 2, 2, 1), h1.c.c(i5, 0, 13)).e();
    }

    @Override // p1.j1
    public final void y(j1.i iVar, j1.j jVar, long j5) {
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.input.pointer.PointerInputModifier", this.f4773q);
        throw new ClassCastException();
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0055  */
    /* JADX WARN: Code duplicated, block: B:20:0x006d  */
    public final void y0(boolean z2) {
        m1 m1Var;
        if (!this.f5693p) {
            d4.a.L("initializeModifier called on unattached node");
            throw null;
        }
        r0.n nVar = this.f4773q;
        if ((this.f5683f & 32) != 0) {
            if (nVar instanceof o1.c) {
                b bVar = new b(this, 0);
                h0.e eVar = ((q1.u) f.t(this)).f5406s0;
                if (!eVar.h(bVar)) {
                    eVar.b(bVar);
                }
            }
            if (nVar instanceof r.d0) {
                r.d0 d0Var = (r.d0) nVar;
                o1.a aVar = this.f4774r;
                if (aVar != null) {
                    o1.g gVar = r.c1.f5542a;
                    if (aVar.p(gVar)) {
                        aVar.f4368l = d0Var;
                        o1.d modifierLocalManager = ((q1.u) f.t(this)).getModifierLocalManager();
                        modifierLocalManager.f4371b.b(this);
                        modifierLocalManager.f4372c.b(gVar);
                        modifierLocalManager.a();
                    } else {
                        o1.a aVar2 = new o1.a();
                        aVar2.f4368l = d0Var;
                        this.f4774r = aVar2;
                        m1Var = f.s(this).f4801y.f4971d;
                        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.node.TailModifierNode", m1Var);
                        if (m1Var.f4928q) {
                            o1.d modifierLocalManager2 = ((q1.u) f.t(this)).getModifierLocalManager();
                            o1.g gVar2 = r.c1.f5542a;
                            modifierLocalManager2.f4371b.b(this);
                            modifierLocalManager2.f4372c.b(gVar2);
                            modifierLocalManager2.a();
                        }
                    }
                } else {
                    o1.a aVar3 = new o1.a();
                    aVar3.f4368l = d0Var;
                    this.f4774r = aVar3;
                    m1Var = f.s(this).f4801y.f4971d;
                    b4.l.c("null cannot be cast to non-null type androidx.compose.ui.node.TailModifierNode", m1Var);
                    if (m1Var.f4928q) {
                        o1.d modifierLocalManager3 = ((q1.u) f.t(this)).getModifierLocalManager();
                        o1.g gVar3 = r.c1.f5542a;
                        modifierLocalManager3.f4371b.b(this);
                        modifierLocalManager3.f4372c.b(gVar3);
                        modifierLocalManager3.a();
                    }
                }
            }
        }
        if ((this.f5683f & 4) != 0 && !z2) {
            f.q(this, 2).R0();
        }
        if ((this.f5683f & 2) != 0) {
            m1 m1Var2 = f.s(this).f4801y.f4971d;
            b4.l.c("null cannot be cast to non-null type androidx.compose.ui.node.TailModifierNode", m1Var2);
            if (m1Var2.f4928q) {
                y0 y0Var = this.f5688k;
                b4.l.b(y0Var);
                ((y) y0Var).i1(this);
                d1 d1Var = y0Var.G;
                if (d1Var != null) {
                    d1Var.invalidate();
                }
            }
            if (!z2) {
                f.q(this, 2).R0();
                f.s(this).A();
            }
        }
        if ((this.f5683f & 8) != 0) {
            ((q1.u) f.t(this)).y();
        }
    }

    public final void z0() {
        if (!this.f5693p) {
            d4.a.L("unInitializeModifier called on unattached node");
            throw null;
        }
        r0.n nVar = this.f4773q;
        if ((this.f5683f & 32) != 0) {
            if (nVar instanceof r.d0) {
                o1.d modifierLocalManager = ((q1.u) f.t(this)).getModifierLocalManager();
                o1.g gVar = r.c1.f5542a;
                modifierLocalManager.f4373d.b(f.s(this));
                modifierLocalManager.f4374e.b(gVar);
                modifierLocalManager.a();
            }
            if (nVar instanceof o1.c) {
                ((o1.c) nVar).f(f.f4826a);
            }
        }
        if ((this.f5683f & 8) != 0) {
            ((q1.u) f.t(this)).y();
        }
    }
}

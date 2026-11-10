package o1;

import p1.d0;
import p1.l;
import p1.m;
import p1.t0;
import r0.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public interface e extends f, l {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v3, types: [a4.a, b4.m] */
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
    /* JADX WARN: Type inference failed for: r9v0, types: [o1.e, p1.l] */
    @Override // o1.f
    default Object e(g gVar) {
        t0 t0Var;
        o oVar = ((o) this).f5681d;
        boolean z2 = oVar.f5693p;
        if (!z2) {
            d4.a.K("ModifierLocal accessed from an unattached node");
            throw null;
        }
        if (!z2) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        o oVar2 = oVar.f5685h;
        d0 d0VarS = p1.f.s(this);
        while (d0VarS != null) {
            if ((d0VarS.f4801y.f4972e.f5684g & 32) != 0) {
                while (oVar2 != null) {
                    if ((oVar2.f5683f & 32) != 0) {
                        ?? E = oVar2;
                        ?? eVar = 0;
                        while (E != 0) {
                            if (E instanceof e) {
                                e eVar2 = (e) E;
                                if (eVar2.g().p(gVar)) {
                                    return eVar2.g().t(gVar);
                                }
                            } else if ((E.f5683f & 32) != 0 && (E instanceof m)) {
                                o oVar3 = ((m) E).f4921r;
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
                                                eVar = new h0.e(new o[16]);
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
                            E = p1.f.e(eVar);
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

    default a.a g() {
        return b.f4369l;
    }
}

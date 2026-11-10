package w0;

import b4.w;
import c0.t2;
import j.z;
import p1.a1;
import p1.d0;
import p1.e1;
import p1.t0;
import p1.y0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r extends r0.o implements p1.k, a1, o1.e {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f6262q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f6263r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public q f6264s;

    public static final boolean A0(r rVar) {
        r0.o oVar = rVar.f5681d;
        if (!oVar.f5693p) {
            d4.a.L("visitSubtreeIf called on an unattached node");
            throw null;
        }
        h0.e eVar = new h0.e(new r0.o[16]);
        r0.o oVar2 = oVar.f5686i;
        if (oVar2 == null) {
            p1.f.b(eVar, oVar);
        } else {
            eVar.b(oVar2);
        }
        loop0: while (eVar.k()) {
            r0.o oVar3 = (r0.o) eVar.m(eVar.f2578f - 1);
            if ((oVar3.f5684g & 1024) != 0) {
                for (r0.o oVar4 = oVar3; oVar4 != null; oVar4 = oVar4.f5686i) {
                    if ((oVar4.f5683f & 1024) != 0) {
                        h0.e eVar2 = null;
                        r0.o oVarE = oVar4;
                        while (oVarE != null) {
                            if (oVarE instanceof r) {
                                r rVar2 = (r) oVarE;
                                if (rVar2.f6264s != null) {
                                    int iOrdinal = rVar2.z0().ordinal();
                                    if (iOrdinal != 0 && iOrdinal != 1 && iOrdinal != 2) {
                                        if (iOrdinal == 3) {
                                            break loop0;
                                        }
                                        throw new i4.p();
                                    }
                                    return true;
                                }
                            } else if ((oVarE.f5683f & 1024) != 0 && (oVarE instanceof p1.m)) {
                                int i5 = 0;
                                for (r0.o oVar5 = ((p1.m) oVarE).f4921r; oVar5 != null; oVar5 = oVar5.f5686i) {
                                    if ((oVar5.f5683f & 1024) != 0) {
                                        i5++;
                                        if (i5 == 1) {
                                            oVarE = oVar5;
                                        } else {
                                            if (eVar2 == null) {
                                                eVar2 = new h0.e(new r0.o[16]);
                                            }
                                            if (oVarE != null) {
                                                eVar2.b(oVarE);
                                                oVarE = null;
                                            }
                                            eVar2.b(oVar5);
                                        }
                                    }
                                }
                                if (i5 == 1) {
                                }
                            }
                            oVarE = p1.f.e(eVar2);
                        }
                    }
                }
            }
            p1.f.b(eVar, oVar3);
        }
        return false;
    }

    public static final boolean B0(r rVar) {
        t0 t0Var;
        r0.o oVar = rVar.f5681d;
        if (!oVar.f5693p) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        r0.o oVar2 = oVar.f5685h;
        d0 d0VarS = p1.f.s(rVar);
        loop0: while (d0VarS != null) {
            if ((d0VarS.f4801y.f4972e.f5684g & 1024) != 0) {
                while (oVar2 != null) {
                    if ((oVar2.f5683f & 1024) != 0) {
                        r0.o oVarE = oVar2;
                        h0.e eVar = null;
                        while (oVarE != null) {
                            if (oVarE instanceof r) {
                                r rVar2 = (r) oVarE;
                                if (rVar2.f6264s != null) {
                                    int iOrdinal = rVar2.z0().ordinal();
                                    if (iOrdinal == 0) {
                                        break loop0;
                                    }
                                    if (iOrdinal == 1) {
                                        return true;
                                    }
                                    if (iOrdinal != 2 && iOrdinal != 3) {
                                        throw new i4.p();
                                    }
                                    break loop0;
                                    break loop0;
                                }
                            } else if ((oVarE.f5683f & 1024) != 0 && (oVarE instanceof p1.m)) {
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
        return false;
    }

    public final void C0() {
        q qVar = this.f6264s;
        if (qVar == null) {
            if (qVar != null) {
                throw new IllegalStateException("Re-initializing focus target node.");
            }
            s sVarE = d.E(this);
            try {
                if (sVarE.f6266b) {
                    s.a(sVarE);
                }
                sVarE.f6266b = true;
                D0((B0(this) && A0(this)) ? q.f6259e : q.f6260f);
                s.b(sVarE);
            } catch (Throwable th) {
                s.b(sVarE);
                throw th;
            }
        }
        int iOrdinal = z0().ordinal();
        if (iOrdinal == 0 || iOrdinal == 2) {
            w wVar = new w();
            p1.f.p(this, new t2(wVar, 13, this));
            Object obj = wVar.f697d;
            if (obj == null) {
                b4.l.j("focusProperties");
                throw null;
            }
            if (((i) obj).a()) {
                return;
            }
            ((androidx.compose.ui.focus.b) ((q1.u) p1.f.t(this)).getFocusOwner()).a(8, true, true);
        }
    }

    public final void D0(q qVar) {
        ((z) d.E(this).f6267c).i(this, qVar);
    }

    @Override // p1.a1
    public final void J() {
        q qVarZ0 = z0();
        C0();
        if (qVarZ0 != z0()) {
            d.A(this);
        }
    }

    @Override // r0.o
    public final boolean o0() {
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x002e  */
    @Override // r0.o
    public final void s0() {
        int iOrdinal = z0().ordinal();
        if (iOrdinal == 0) {
            ((androidx.compose.ui.focus.b) ((q1.u) p1.f.t(this)).getFocusOwner()).a(8, true, false);
            d.q(this);
        } else if (iOrdinal == 1) {
            s sVarE = d.E(this);
            try {
                if (sVarE.f6266b) {
                    s.a(sVarE);
                }
                sVarE.f6266b = true;
                D0(q.f6260f);
                s.b(sVarE);
            } catch (Throwable th) {
                s.b(sVarE);
                throw th;
            }
        } else if (iOrdinal == 2) {
            ((androidx.compose.ui.focus.b) ((q1.u) p1.f.t(this)).getFocusOwner()).a(8, true, false);
            d.q(this);
        }
        this.f6264s = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v10, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r5v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7, types: [r0.o] */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7, types: [h0.e] */
    /* JADX WARN: Type inference failed for: r8v4 */
    public final j y0() {
        t0 t0Var;
        j jVar = new j();
        jVar.f6242a = true;
        n nVar = n.f6254b;
        jVar.f6243b = nVar;
        jVar.f6244c = nVar;
        jVar.f6245d = nVar;
        jVar.f6246e = nVar;
        jVar.f6247f = nVar;
        jVar.f6248g = nVar;
        jVar.f6249h = nVar;
        jVar.f6250i = nVar;
        jVar.f6251j = h.f6237g;
        jVar.f6252k = h.f6238h;
        r0.o oVar = this.f5681d;
        if (!oVar.f5693p) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        d0 d0VarS = p1.f.s(this);
        r0.o oVar2 = oVar;
        loop0: while (d0VarS != null) {
            if ((d0VarS.f4801y.f4972e.f5684g & 3072) != 0) {
                while (oVar2 != null) {
                    int i5 = oVar2.f5683f;
                    if ((i5 & 3072) != 0) {
                        if (oVar2 != oVar && (i5 & 1024) != 0) {
                            break loop0;
                        }
                        if ((i5 & 2048) != 0) {
                            ?? E = oVar2;
                            ?? eVar = 0;
                            while (E != 0) {
                                if (E instanceof l) {
                                    ((l) E).K(jVar);
                                } else if ((E.f5683f & 2048) != 0 && (E instanceof p1.m)) {
                                    r0.o oVar3 = ((p1.m) E).f4921r;
                                    int i6 = 0;
                                    while (oVar3 != null) {
                                        if ((oVar3.f5683f & 2048) != 0) {
                                            i6++;
                                            if (i6 == 1) {
                                                E = E;
                                                eVar = eVar;
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
                                        } else {
                                            E = E;
                                            eVar = eVar;
                                        }
                                        oVar3 = oVar3.f5686i;
                                        E = E;
                                        eVar = eVar;
                                    }
                                    if (i6 == 1) {
                                        E = E;
                                        eVar = eVar;
                                    } else {
                                        E = E;
                                        eVar = eVar;
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
        return jVar;
    }

    public final q z0() {
        q qVar;
        d0 d0Var;
        e1 e1Var;
        g focusOwner;
        y0 y0Var = this.f5681d.f5688k;
        s sVar = (y0Var == null || (d0Var = y0Var.f4998o) == null || (e1Var = d0Var.f4788l) == null || (focusOwner = ((q1.u) e1Var).getFocusOwner()) == null) ? null : ((androidx.compose.ui.focus.b) focusOwner).f181h;
        if (sVar != null && (qVar = (q) ((z) sVar.f6267c).e(this)) != null) {
            return qVar;
        }
        q qVar2 = this.f6264s;
        return qVar2 == null ? q.f6260f : qVar2;
    }
}

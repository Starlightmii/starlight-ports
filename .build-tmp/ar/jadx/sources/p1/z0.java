package p1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class z0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j.x f5010a;

    static {
        j.x xVar = j.d0.f3095a;
        f5010a = new j.x();
    }

    public static final void a(r0.o oVar) {
        if (oVar.f5693p) {
            b(oVar, -1, 1);
        } else {
            d4.a.L("autoInvalidateInsertedNode called on unattached node");
            throw null;
        }
    }

    public static final void b(r0.o oVar, int i5, int i6) {
        if (!(oVar instanceof m)) {
            c(oVar, i5 & oVar.f5683f, i6);
            return;
        }
        m mVar = (m) oVar;
        int i7 = mVar.f4920q;
        c(oVar, i7 & i5, i6);
        int i8 = (~i7) & i5;
        for (r0.o oVar2 = mVar.f4921r; oVar2 != null; oVar2 = oVar2.f5686i) {
            b(oVar2, i8, i6);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static final void c(r0.o oVar, int i5, int i6) {
        if (i6 != 0 || oVar.o0()) {
            if ((i5 & 2) != 0 && (oVar instanceof w)) {
                f.m((w) oVar);
                if (i6 == 2) {
                    y0 y0VarQ = f.q(oVar, 2);
                    y0VarQ.f5001r = true;
                    y0VarQ.E.a();
                    if (y0VarQ.G != null) {
                        y0VarQ.g1(null, false);
                        y0VarQ.f4998o.P(false);
                    }
                }
            }
            if ((i5 & 128) != 0 && (oVar instanceof v) && i6 != 2) {
                f.s(oVar).A();
            }
            if ((i5 & 256) != 0 && (oVar instanceof p) && i6 != 2) {
                d0 d0VarS = f.s(oVar);
                j0 j0Var = d0VarS.f4802z;
                if (!j0Var.f4897e && !j0Var.f4896d && !d0VarS.F) {
                    q1.u uVar = (q1.u) f.u(d0VarS);
                    ((h0.e) uVar.J.f4949e.f49e).b(d0VarS);
                    d0VarS.F = true;
                    uVar.B(null);
                }
            }
            if ((i5 & 4) != 0 && (oVar instanceof o)) {
                f.l((o) oVar);
            }
            if ((i5 & 8) != 0 && (oVar instanceof l1)) {
                f.n((l1) oVar);
            }
            if ((i5 & 64) != 0 && (oVar instanceof h1)) {
                j0 j0Var2 = f.s((h1) oVar).f4802z;
                j0Var2.f4910r.f4882s = true;
                h0 h0Var = j0Var2.f4911s;
                if (h0Var != null) {
                    h0Var.f4863x = true;
                }
            }
            if ((i5 & 1024) != 0 && (oVar instanceof w0.r) && i6 != 2) {
                w0.d.q((w0.r) oVar);
            }
            if ((i5 & 2048) != 0 && (oVar instanceof w0.l)) {
                w0.l lVar = (w0.l) oVar;
                g.f4831b = null;
                lVar.K(g.f4830a);
                if (g.f4831b != null) {
                    if (i6 == 2) {
                        r0.o oVar2 = ((r0.o) lVar).f5681d;
                        if (!oVar2.f5693p) {
                            throw new IllegalStateException("visitChildren called on an unattached node");
                        }
                        h0.e eVar = new h0.e(new r0.o[16]);
                        r0.o oVar3 = oVar2.f5686i;
                        if (oVar3 == null) {
                            f.b(eVar, oVar2);
                        } else {
                            eVar.b(oVar3);
                        }
                        while (eVar.k()) {
                            r0.o oVarE = (r0.o) eVar.m(eVar.f2578f - 1);
                            if ((oVarE.f5684g & 1024) == 0) {
                                f.b(eVar, oVarE);
                            } else {
                                while (oVarE != null) {
                                    if ((oVarE.f5683f & 1024) != 0) {
                                        h0.e eVar2 = null;
                                        while (oVarE != null) {
                                            if (oVarE instanceof w0.r) {
                                                w0.d.q((w0.r) oVarE);
                                            } else if ((oVarE.f5683f & 1024) != 0 && (oVarE instanceof m)) {
                                                int i7 = 0;
                                                for (r0.o oVar4 = ((m) oVarE).f4921r; oVar4 != null; oVar4 = oVar4.f5686i) {
                                                    if ((oVar4.f5683f & 1024) != 0) {
                                                        i7++;
                                                        if (i7 == 1) {
                                                            oVarE = oVar4;
                                                        } else {
                                                            if (eVar2 == null) {
                                                                eVar2 = new h0.e(new r0.o[16]);
                                                            }
                                                            if (oVarE != null) {
                                                                eVar2.b(oVarE);
                                                                oVarE = null;
                                                            }
                                                            eVar2.b(oVar4);
                                                        }
                                                    }
                                                }
                                                if (i7 == 1) {
                                                }
                                            }
                                            oVarE = f.e(eVar2);
                                        }
                                        break;
                                    }
                                    oVarE = oVarE.f5686i;
                                }
                            }
                        }
                    } else {
                        w0.e eVar3 = ((androidx.compose.ui.focus.b) ((q1.u) f.t(lVar)).getFocusOwner()).f180g;
                        eVar3.b(eVar3.f6231e, lVar);
                    }
                }
            }
            if ((i5 & 4096) == 0 || !(oVar instanceof w0.c)) {
                return;
            }
            w0.c cVar = (w0.c) oVar;
            w0.e eVar4 = ((androidx.compose.ui.focus.b) ((q1.u) f.t(cVar)).getFocusOwner()).f180g;
            eVar4.b(eVar4.f6230d, cVar);
        }
    }

    public static final void d(r0.o oVar) {
        if (oVar.f5693p) {
            b(oVar, -1, 0);
        } else {
            d4.a.L("autoInvalidateUpdatedNode called on unattached node");
            throw null;
        }
    }

    public static final int e(r0.n nVar) {
        int i5 = nVar instanceof n1.r ? 3 : 1;
        if (nVar instanceof v0.e) {
            i5 |= 4;
        }
        if (nVar instanceof w1.j) {
            i5 |= 8;
        }
        return ((nVar instanceof o1.c) || (nVar instanceof r.d0)) ? i5 | 32 : i5;
    }

    public static final int f(r0.o oVar) {
        int i5 = oVar.f5683f;
        if (i5 != 0) {
            return i5;
        }
        Class<?> cls = oVar.getClass();
        j.x xVar = f5010a;
        int iC = xVar.c(cls);
        if (iC >= 0) {
            return xVar.f3170c[iC];
        }
        int i6 = oVar instanceof w ? 3 : 1;
        if (oVar instanceof o) {
            i6 |= 4;
        }
        if (oVar instanceof l1) {
            i6 |= 8;
        }
        if (oVar instanceof j1) {
            i6 |= 16;
        }
        if (oVar instanceof o1.e) {
            i6 |= 32;
        }
        if (oVar instanceof h1) {
            i6 |= 64;
        }
        if (oVar instanceof v) {
            i6 |= 128;
        }
        if (oVar instanceof p) {
            i6 |= 256;
        }
        if (oVar instanceof w0.r) {
            i6 |= 1024;
        }
        if (oVar instanceof w0.l) {
            i6 |= 2048;
        }
        if (oVar instanceof w0.c) {
            i6 |= 4096;
        }
        if (oVar instanceof h1.d) {
            i6 |= 8192;
        }
        if (oVar instanceof l1.a) {
            i6 |= 16384;
        }
        if (oVar instanceof k) {
            i6 |= 32768;
        }
        if (oVar instanceof o1) {
            i6 |= 262144;
        }
        int iB = xVar.b(cls);
        if (iB < 0) {
            iB = ~iB;
        }
        xVar.f3169b[iB] = cls;
        xVar.f3170c[iB] = i6;
        return i6;
    }

    public static final int g(r0.o oVar) {
        if (!(oVar instanceof m)) {
            return f(oVar);
        }
        m mVar = (m) oVar;
        int iG = mVar.f4920q;
        for (r0.o oVar2 = mVar.f4921r; oVar2 != null; oVar2 = oVar2.f5686i) {
            iG |= g(oVar2);
        }
        return iG;
    }

    public static final boolean h(int i5) {
        return (i5 & 128) != 0;
    }
}

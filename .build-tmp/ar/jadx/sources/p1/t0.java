package p1;

import java.util.HashSet;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d0 f4968a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final t f4969b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public y0 f4970c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final m1 f4971d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public r0.o f4972e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public h0.e f4973f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public h0.e f4974g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public s0 f4975h;

    public t0(d0 d0Var) {
        this.f4968a = d0Var;
        t tVar = new t(d0Var);
        this.f4969b = tVar;
        this.f4970c = tVar;
        m1 m1Var = tVar.M;
        this.f4971d = m1Var;
        this.f4972e = m1Var;
    }

    public static final void a(t0 t0Var, r0.o oVar, y0 y0Var) {
        for (r0.o oVar2 = oVar.f5685h; oVar2 != null; oVar2 = oVar2.f5685h) {
            if (oVar2 == v0.f4980a) {
                d0 d0VarR = t0Var.f4968a.r();
                y0Var.f5000q = d0VarR != null ? d0VarR.f4801y.f4969b : null;
                t0Var.f4970c = y0Var;
                return;
            } else {
                if ((oVar2.f5683f & 2) != 0) {
                    return;
                }
                oVar2.x0(y0Var);
            }
        }
    }

    public static r0.o b(r0.n nVar, r0.o oVar) {
        r0.o oVarH;
        if (nVar instanceof q0) {
            oVarH = ((q0) nVar).h();
            oVarH.f5683f = z0.g(oVarH);
        } else {
            c cVar = new c();
            cVar.f5683f = z0.e(nVar);
            cVar.f4773q = nVar;
            cVar.f4775s = new HashSet();
            oVarH = cVar;
        }
        if (oVarH.f5693p) {
            d4.a.L("A ModifierNodeElement cannot return an already attached node from create() ");
            throw null;
        }
        oVarH.f5689l = true;
        r0.o oVar2 = oVar.f5686i;
        if (oVar2 != null) {
            oVar2.f5685h = oVarH;
            oVarH.f5686i = oVar2;
        }
        oVar.f5686i = oVarH;
        oVarH.f5685h = oVar;
        return oVarH;
    }

    public static r0.o c(r0.o oVar) {
        boolean z2 = oVar.f5693p;
        if (z2) {
            j.x xVar = z0.f5010a;
            if (!z2) {
                d4.a.L("autoInvalidateRemovedNode called on unattached node");
                throw null;
            }
            z0.b(oVar, -1, 2);
            oVar.v0();
            oVar.q0();
        }
        r0.o oVar2 = oVar.f5686i;
        r0.o oVar3 = oVar.f5685h;
        if (oVar2 != null) {
            oVar2.f5685h = oVar3;
            oVar.f5686i = null;
        }
        if (oVar3 != null) {
            oVar3.f5686i = oVar2;
            oVar.f5685h = null;
        }
        b4.l.b(oVar3);
        return oVar3;
    }

    public static void h(r0.n nVar, r0.n nVar2, r0.o oVar) {
        if ((nVar instanceof q0) && (nVar2 instanceof q0)) {
            u0 u0Var = v0.f4980a;
            b4.l.c("null cannot be cast to non-null type T of androidx.compose.ui.node.NodeChainKt.updateUnsafe", oVar);
            ((q0) nVar2).i(oVar);
            if (oVar.f5693p) {
                z0.d(oVar);
                return;
            } else {
                oVar.f5690m = true;
                return;
            }
        }
        if (!(oVar instanceof c)) {
            throw new IllegalStateException("Unknown Modifier.Node type");
        }
        c cVar = (c) oVar;
        if (cVar.f5693p) {
            cVar.z0();
        }
        cVar.f4773q = nVar2;
        cVar.f5683f = z0.e(nVar2);
        if (cVar.f5693p) {
            cVar.y0(false);
        }
        if (oVar.f5693p) {
            z0.d(oVar);
        } else {
            oVar.f5690m = true;
        }
    }

    public final boolean d(int i5) {
        return (i5 & this.f4972e.f5684g) != 0;
    }

    public final void e() {
        for (r0.o oVar = this.f4972e; oVar != null; oVar = oVar.f5686i) {
            oVar.u0();
            if (oVar.f5689l) {
                z0.a(oVar);
            }
            if (oVar.f5690m) {
                z0.d(oVar);
            }
            oVar.f5689l = false;
            oVar.f5690m = false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:158:0x0135 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:33:0x00ee A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:36:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:39:0x00fe A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:45:0x0111  */
    /* JADX WARN: Code duplicated, block: B:47:0x011b  */
    /* JADX WARN: Code duplicated, block: B:52:0x0133  */
    /* JADX WARN: Code duplicated, block: B:74:0x0183  */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:76:0x018b
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:272)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:237)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:80)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeEndlessLoop(LoopRegionMaker.java:590)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:82)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeEndlessLoop(LoopRegionMaker.java:590)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:82)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeEndlessLoop(LoopRegionMaker.java:590)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:82)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeEndlessLoop(LoopRegionMaker.java:590)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:82)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:49)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    public final void f(int r32, h0.e r33, h0.e r34, r0.o r35, boolean r36) {
        /*
            Method dump skipped, instruction units count: 836
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p1.t0.f(int, h0.e, h0.e, r0.o, boolean):void");
    }

    public final void g() {
        d0 d0Var;
        y yVar;
        d1 d1Var;
        r0.o oVar = this.f4971d.f5685h;
        y0 y0Var = this.f4969b;
        while (true) {
            d0Var = this.f4968a;
            if (oVar == null) {
                break;
            }
            w wVarF = f.f(oVar);
            if (wVarF != null) {
                y0 y0Var2 = oVar.f5688k;
                if (y0Var2 != null) {
                    yVar = (y) y0Var2;
                    w wVar = yVar.M;
                    yVar.i1(wVarF);
                    if (wVar != oVar && (d1Var = yVar.G) != null) {
                        d1Var.invalidate();
                    }
                } else {
                    yVar = new y(d0Var, wVarF);
                    oVar.x0(yVar);
                }
                y0Var.f5000q = yVar;
                yVar.f4999p = y0Var;
                y0Var = yVar;
            } else {
                oVar.x0(y0Var);
            }
            oVar = oVar.f5685h;
        }
        d0 d0VarR = d0Var.r();
        y0Var.f5000q = d0VarR != null ? d0VarR.f4801y.f4969b : null;
        this.f4970c = y0Var;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        r0.o oVar = this.f4972e;
        m1 m1Var = this.f4971d;
        if (oVar == m1Var) {
            sb.append("]");
        } else {
            while (oVar != null && oVar != m1Var) {
                sb.append(String.valueOf(oVar));
                if (oVar.f5686i == m1Var) {
                    sb.append("]");
                    break;
                }
                sb.append(",");
                oVar = oVar.f5686i;
            }
        }
        String string = sb.toString();
        b4.l.d("StringBuilder().apply(builderAction).toString()", string);
        return string;
    }
}

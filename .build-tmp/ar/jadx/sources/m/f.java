package m;

import c0.t2;
import f0.g3;
import f0.i1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h0 f3661a = d.g(7, null);

    static {
        Object obj = f1.f3662a;
        new k2.e(0.1f);
        p3.j.f(0.5f, 0.5f);
        p3.j.b(0.5f, 0.5f);
    }

    public static final g3 a(float f5, k kVar, f0.p pVar, int i5) {
        return b(new k2.e(f5), b1.f3622c, kVar, null, "DpAnimation", pVar, (i5 << 3) & 896, 8);
    }

    public static final g3 b(Object obj, a1 a1Var, k kVar, Float f5, String str, f0.p pVar, int i5, int i6) {
        if ((i6 & 8) != 0) {
            f5 = null;
        }
        f0.v vVar = (f0.v) pVar;
        Object objK = vVar.K();
        f0.h hVar = f0.o.f2281a;
        if (objK == hVar) {
            objK = f0.l.s(null);
            vVar.f0(objK);
        }
        i1 i1Var = (i1) objK;
        Object objK2 = vVar.K();
        if (objK2 == hVar) {
            objK2 = new c(obj, a1Var, f5);
            vVar.f0(objK2);
        }
        c cVar = (c) objK2;
        i1 i1VarV = f0.l.v(null, vVar);
        if (f5 != null && (kVar instanceof h0)) {
            h0 h0Var = (h0) kVar;
            if (!b4.l.a(h0Var.f3673c, f5)) {
                kVar = new h0(h0Var.f3671a, h0Var.f3672b, f5);
            }
        }
        i1 i1VarV2 = f0.l.v(kVar, vVar);
        Object objK3 = vVar.K();
        if (objK3 == hVar) {
            objK3 = k4.j.a(-1, 6, null);
            vVar.f0(objK3);
        }
        k4.g gVar = (k4.g) objK3;
        boolean zH = vVar.h(gVar) | vVar.h(obj);
        Object objK4 = vVar.K();
        if (zH || objK4 == hVar) {
            objK4 = new t2(gVar, 1, obj);
            vVar.f0(objK4);
        }
        f0.l.g((a4.a) objK4, vVar);
        boolean zH2 = vVar.h(gVar) | vVar.h(cVar) | vVar.f(i1VarV2) | vVar.f(i1VarV);
        Object objK5 = vVar.K();
        if (zH2 || objK5 == hVar) {
            e eVar = new e(gVar, cVar, i1VarV2, i1VarV, null);
            vVar.f0(eVar);
            objK5 = eVar;
        }
        f0.l.e((a4.e) objK5, vVar, gVar);
        g3 g3Var = (g3) i1Var.getValue();
        return g3Var == null ? cVar.f3631c : g3Var;
    }
}

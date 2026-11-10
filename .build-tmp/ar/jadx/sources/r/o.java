package r;

import c0.j1;
import f0.b2;
import f0.w1;
import java.util.HashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap f5596a = c(true);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final HashMap f5597b = c(false);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final n f5598c = n.f5592b;

    public static final void a(r0.p pVar, f0.p pVar2, int i5) {
        f0.v vVar = (f0.v) pVar2;
        vVar.X(-211209833);
        if ((((vVar.f(pVar) ? 4 : 2) | i5) & 3) == 2 && vVar.z()) {
            vVar.Q();
        } else {
            int iHashCode = Long.hashCode(vVar.S);
            r0.p pVarC = r0.a.c(vVar, pVar);
            w1 w1VarL = vVar.l();
            p1.j.f4892c.getClass();
            p1.n nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(p1.i.f4870e, vVar, f5598c);
            f0.l.x(p1.i.f4869d, vVar, w1VarL);
            f0.l.x(p1.i.f4868c, vVar, pVarC);
            p1.h hVar = p1.i.f4871f;
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar, iHashCode, hVar);
            }
            vVar.p(true);
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new j1(i5, 7, pVar);
        }
    }

    public static final void b(n1.l0 l0Var, n1.m0 m0Var, n1.e0 e0Var, k2.k kVar, int i5, int i6, r0.h hVar) {
        r0.h hVar2;
        Object objH = e0Var.h();
        l lVar = objH instanceof l ? (l) objH : null;
        n1.l0.e(l0Var, m0Var, ((lVar == null || (hVar2 = lVar.f5582q) == null) ? hVar : hVar2).a(a.a.f(m0Var.f4130d, m0Var.f4131e), a.a.f(i5, i6), kVar));
    }

    public static final HashMap c(boolean z2) {
        HashMap map = new HashMap(9);
        d(map, z2, r0.b.f5653d);
        d(map, z2, r0.b.f5654e);
        d(map, z2, r0.b.f5655f);
        d(map, z2, r0.b.f5656g);
        d(map, z2, r0.b.f5657h);
        d(map, z2, r0.b.f5658i);
        d(map, z2, r0.b.f5659j);
        d(map, z2, r0.b.f5660k);
        d(map, z2, r0.b.f5661l);
        return map;
    }

    public static final void d(HashMap map, boolean z2, r0.h hVar) {
        map.put(hVar, new r(hVar, z2));
    }

    public static final n1.f0 e(r0.h hVar, boolean z2) {
        n1.f0 f0Var = (n1.f0) (z2 ? f5596a : f5597b).get(hVar);
        return f0Var == null ? new r(hVar, z2) : f0Var;
    }
}

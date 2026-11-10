package l;

import c0.g1;
import f0.g3;
import f0.o;
import f0.p;
import f0.v;
import m.a1;
import m.b1;
import m.z0;
import y0.u;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class g {
    public static final g3 a(long j5, z0 z0Var, p pVar) {
        v vVar = (v) pVar;
        boolean zF = vVar.f(u.f(j5));
        Object objK = vVar.K();
        if (zF || objK == o.f2281a) {
            g1 g1Var = new g1(6, u.f(j5));
            a1 a1Var = b1.f3620a;
            a1 a1Var2 = new a1(c.f3485e, g1Var);
            vVar.f0(a1Var2);
            objK = a1Var2;
        }
        return m.f.b(new u(j5), (a1) objK, z0Var, null, "ColorAnimation", vVar, 384, 8);
    }
}

package m;

import c0.c2;
import f0.o1;
import f0.s1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class y0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f3804a = 0;

    static {
        h1.c.D(u0.f3793e);
    }

    public static final q0 a(s0 s0Var, Object obj, Object obj2, x xVar, a1 a1Var, f0.p pVar, int i5) {
        boolean zF = ((f0.v) pVar).f(s0Var);
        f0.v vVar = (f0.v) pVar;
        Object objK = vVar.K();
        f0.h hVar = f0.o.f2281a;
        if (zF || objK == hVar) {
            q qVar = (q) a1Var.f3614a.c(obj2);
            qVar.d();
            objK = new q0(s0Var, obj, qVar, a1Var);
            vVar.f0(objK);
        }
        q0 q0Var = (q0) objK;
        if (((Boolean) s0Var.f3759i.getValue()).booleanValue()) {
            q0Var.f3737e.setValue(obj2);
            q0Var.f3738f.setValue(xVar);
            if (!b4.l.a(q0Var.a().f3724d, obj) || !b4.l.a(q0Var.a().f3723c, obj2)) {
                q0Var.b(obj, false);
            }
        } else {
            boolean z2 = q0Var.f3742j;
            s1 s1Var = q0Var.f3740h;
            s1 s1Var2 = q0Var.f3737e;
            s1 s1Var3 = q0Var.f3743k;
            o1 o1Var = q0Var.f3741i;
            if ((!z2 || !obj2.equals(null)) && (!b4.l.a(s1Var2.getValue(), obj2) || o1Var.g() != -1.0f)) {
                s1Var2.setValue(obj2);
                q0Var.f3738f.setValue(xVar);
                q0Var.b(o1Var.g() == -3.0f ? obj2 : s1Var3.getValue(), !((Boolean) s1Var.getValue()).booleanValue());
                s1Var.setValue(Boolean.valueOf(o1Var.g() == -3.0f));
                if (o1Var.g() >= 0.0f) {
                    s1Var3.setValue(q0Var.a().b((long) (o1Var.g() * q0Var.a().c())));
                } else if (o1Var.g() == -3.0f) {
                    s1Var3.setValue(obj2);
                }
                q0Var.f3742j = false;
                o1Var.h(-1.0f);
            }
        }
        boolean zF2 = vVar.f(s0Var) | vVar.f(q0Var);
        Object objK2 = vVar.K();
        if (zF2 || objK2 == hVar) {
            objK2 = new c2(s0Var, 5, q0Var);
            vVar.f0(objK2);
        }
        f0.l.c(q0Var, (a4.c) objK2, vVar);
        return q0Var;
    }

    public static final s0 b(Object obj, f0.p pVar, int i5, int i6) {
        f0.v vVar = (f0.v) pVar;
        Object objK = vVar.K();
        f0.h hVar = f0.o.f2281a;
        if (objK == hVar) {
            objK = new s0(new c0(obj));
            vVar.f0(objK);
        }
        s0 s0Var = (s0) objK;
        s0Var.a(obj, vVar, (i5 & 14) | (i5 & 8) | 48);
        Object objK2 = vVar.K();
        if (objK2 == hVar) {
            objK2 = new x0(s0Var, 1);
            vVar.f0(objK2);
        }
        f0.l.c(s0Var, (a4.c) objK2, vVar);
        return s0Var;
    }
}

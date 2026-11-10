package n0;

import f0.b2;
import f0.e0;
import f0.o;
import f0.p;
import f0.v;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final StackTraceElement[] f4070a = new StackTraceElement[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final k f4071b = new k(0, new long[0], new Object[0]);

    public static final int a(int i5, int i6) {
        return i5 << (((i6 % 10) * 3) + 1);
    }

    public static final long b() {
        return Thread.currentThread().getId();
    }

    public static final d c(int i5, p3.c cVar, p pVar) {
        v vVar = (v) pVar;
        Object objK = vVar.K();
        if (objK == o.f2281a) {
            objK = new d(i5, true, cVar);
            vVar.f0(objK);
        }
        d dVar = (d) objK;
        if (!b4.l.a(dVar.f4059f, cVar)) {
            boolean z2 = dVar.f4059f == null;
            dVar.f4059f = cVar;
            if (!z2 && dVar.f4058e) {
                b2 b2Var = dVar.f4060g;
                if (b2Var != null) {
                    e0 e0Var = b2Var.f2095a;
                    if (e0Var != null) {
                        e0Var.r(b2Var, null);
                    }
                    dVar.f4060g = null;
                }
                ArrayList arrayList = dVar.f4061h;
                if (arrayList != null) {
                    int size = arrayList.size();
                    for (int i6 = 0; i6 < size; i6++) {
                        b2 b2Var2 = (b2) arrayList.get(i6);
                        e0 e0Var2 = b2Var2.f2095a;
                        if (e0Var2 != null) {
                            e0Var2.r(b2Var2, null);
                        }
                    }
                    arrayList.clear();
                }
            }
        }
        return dVar;
    }

    public static final boolean d(b2 b2Var, b2 b2Var2) {
        if (b2Var == null) {
            return true;
        }
        if (b2Var instanceof b2) {
            return !b2Var.b() || b2Var.equals(b2Var2) || b4.l.a(b2Var.f2097c, b2Var2.f2097c);
        }
        return false;
    }
}

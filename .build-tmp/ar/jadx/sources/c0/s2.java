package c0;

import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class s2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f0.h3 f1277a = new f0.h3(t.f1303n);

    public static final y0.q0 a(f0.p pVar, int i5) {
        r2 r2Var = (r2) ((f0.v) pVar).j(f1277a);
        switch (m.h.c(i5)) {
            case 0:
                return r2Var.f1229e;
            case 1:
                return b(r2Var.f1229e);
            case 2:
                return r2Var.f1225a;
            case 3:
                return b(r2Var.f1225a);
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                return u.e.f5816a;
            case 5:
                return r2Var.f1228d;
            case 6:
                float f5 = (float) 0.0d;
                return u.d.a(r2Var.f1228d, new u.b(f5), null, new u.b(f5), 6);
            case 7:
                return b(r2Var.f1228d);
            case 8:
                return r2Var.f1227c;
            case 9:
                return y0.l0.f6642a;
            case 10:
                return r2Var.f1226b;
            default:
                throw new i4.p();
        }
    }

    public static final u.d b(u.d dVar) {
        float f5 = (float) 0.0d;
        return u.d.a(dVar, null, new u.b(f5), new u.b(f5), 3);
    }
}

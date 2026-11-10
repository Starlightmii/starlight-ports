package c0;

import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class t4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f0.h3 f1328a = new f0.h3(t.f1306q);

    public static final y1.h0 a(f0.p pVar, int i5) {
        s4 s4Var = (s4) ((f0.v) pVar).j(f1328a);
        switch (m.h.c(i5)) {
            case 0:
                return s4Var.f1289j;
            case 1:
                return s4Var.f1290k;
            case 2:
                return s4Var.f1291l;
            case 3:
                return s4Var.f1280a;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                return s4Var.f1281b;
            case 5:
                return s4Var.f1282c;
            case 6:
                return s4Var.f1283d;
            case 7:
                return s4Var.f1284e;
            case 8:
                return s4Var.f1285f;
            case 9:
                return s4Var.f1292m;
            case 10:
                return s4Var.f1293n;
            case 11:
                return s4Var.f1294o;
            case 12:
                return s4Var.f1286g;
            case 13:
                return s4Var.f1287h;
            case 14:
                return s4Var.f1288i;
            default:
                throw new i4.p();
        }
    }
}

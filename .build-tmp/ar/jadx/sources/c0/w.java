package c0;

import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w extends b4.m implements a4.e {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final w f1381f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final w f1382g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final w f1383h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final w f1384i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final w f1385j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final w f1386k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final w f1387l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final w f1388m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final w f1389n;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1390e;

    static {
        int i5 = 2;
        f1381f = new w(i5, 0);
        f1382g = new w(i5, 1);
        f1383h = new w(i5, 2);
        f1384i = new w(i5, 3);
        f1385j = new w(i5, 4);
        f1386k = new w(i5, 5);
        f1387l = new w(i5, 6);
        f1388m = new w(i5, 7);
        f1389n = new w(i5, 8);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ w(int i5, int i6) {
        super(i5);
        this.f1390e = i6;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0065  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f1390e) {
            case 0:
                f0.p pVar = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar = (f0.v) pVar;
                    if (vVar.z()) {
                        vVar.Q();
                    }
                }
                return p3.n.f5026a;
            case 1:
                f0.p pVar2 = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar2 = (f0.v) pVar2;
                    if (vVar2.z()) {
                        vVar2.Q();
                    }
                }
                return p3.n.f5026a;
            case 2:
                f0.p pVar3 = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar3 = (f0.v) pVar3;
                    if (vVar3.z()) {
                        vVar3.Q();
                    }
                }
                return p3.n.f5026a;
            case 3:
                f0.p pVar4 = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar4 = (f0.v) pVar4;
                    if (vVar4.z()) {
                        vVar4.Q();
                    }
                }
                return p3.n.f5026a;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                f0.p pVar5 = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar5 = (f0.v) pVar5;
                    if (vVar5.z()) {
                        vVar5.Q();
                    } else {
                        h2.d(null, 0.0f, 0L, pVar5, 0, 7);
                    }
                } else {
                    h2.d(null, 0.0f, 0L, pVar5, 0, 7);
                }
                return p3.n.f5026a;
            case 5:
                return Integer.valueOf(((n1.e0) obj).d(((Number) obj2).intValue()));
            case 6:
                return Integer.valueOf(((n1.e0) obj).c0(((Number) obj2).intValue()));
            case 7:
                return Integer.valueOf(((n1.e0) obj).e0(((Number) obj2).intValue()));
            default:
                return Integer.valueOf(((n1.e0) obj).a0(((Number) obj2).intValue()));
        }
    }
}

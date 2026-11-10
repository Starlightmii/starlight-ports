package c0;

import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t extends b4.m implements a4.a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final t f1295f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final t f1296g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final t f1297h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final t f1298i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final t f1299j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final t f1300k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final t f1301l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final t f1302m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final t f1303n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final t f1304o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final t f1305p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final t f1306q;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1307e;

    static {
        int i5 = 0;
        f1295f = new t(i5, 0);
        f1296g = new t(i5, 1);
        f1297h = new t(i5, 2);
        f1298i = new t(i5, 3);
        f1299j = new t(i5, 4);
        f1300k = new t(i5, 5);
        f1301l = new t(i5, 6);
        f1302m = new t(i5, 7);
        f1303n = new t(i5, 8);
        f1304o = new t(i5, 9);
        f1305p = new t(i5, 10);
        f1306q = new t(i5, 11);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t(int i5, int i6) {
        super(i5);
        this.f1307e = i6;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f1307e) {
            case 0:
                return u.e();
            case 1:
                return Boolean.TRUE;
            case 2:
                return new y0.u(y0.u.f6689b);
            case 3:
                return Boolean.TRUE;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                return new k2.e(48);
            case 5:
                return Boolean.FALSE;
            case 6:
                return new g2();
            case 7:
                return Boolean.FALSE;
            case 8:
                return new r2();
            case 9:
                return new k2.e(0);
            case 10:
                return e0.s.f1858a;
            default:
                return new s4();
        }
    }
}

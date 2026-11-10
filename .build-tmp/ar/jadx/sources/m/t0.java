package m;

import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t0 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final t0 f3765f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final t0 f3766g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final t0 f3767h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final t0 f3768i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final t0 f3769j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final t0 f3770k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final t0 f3771l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final t0 f3772m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final t0 f3773n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final t0 f3774o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final t0 f3775p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final t0 f3776q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final t0 f3777r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final t0 f3778s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final t0 f3779t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final t0 f3780u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final t0 f3781v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final t0 f3782w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final t0 f3783x;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f3784e;

    static {
        int i5 = 1;
        f3765f = new t0(i5, 0);
        f3766g = new t0(i5, 1);
        f3767h = new t0(i5, 2);
        f3768i = new t0(i5, 3);
        f3769j = new t0(i5, 4);
        f3770k = new t0(i5, 5);
        f3771l = new t0(i5, 6);
        f3772m = new t0(i5, 7);
        f3773n = new t0(i5, 8);
        f3774o = new t0(i5, 9);
        f3775p = new t0(i5, 10);
        f3776q = new t0(i5, 11);
        f3777r = new t0(i5, 12);
        f3778s = new t0(i5, 13);
        f3779t = new t0(i5, 14);
        f3780u = new t0(i5, 15);
        f3781v = new t0(i5, 16);
        f3782w = new t0(i5, 17);
        f3783x = new t0(i5, 18);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t0(int i5, int i6) {
        super(i5);
        this.f3784e = i6;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f3784e) {
            case 0:
                ((a4.a) obj).a();
                return p3.n.f5026a;
            case 1:
                long j5 = ((k2.f) obj).f3407a;
                return new n(Float.intBitsToFloat((int) (j5 >> 32)), Float.intBitsToFloat((int) (j5 & 4294967295L)));
            case 2:
                n nVar = (n) obj;
                return new k2.f((((long) Float.floatToRawIntBits(nVar.f3711a)) << 32) | (((long) Float.floatToRawIntBits(nVar.f3712b)) & 4294967295L));
            case 3:
                return new m(((k2.e) obj).f3406d);
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                return new k2.e(((m) obj).f3703a);
            case 5:
                return new m(((Number) obj).floatValue());
            case 6:
                return Float.valueOf(((m) obj).f3703a);
            case 7:
                long j6 = ((k2.h) obj).f3409a;
                return new n((int) (j6 >> 32), (int) (j6 & 4294967295L));
            case 8:
                n nVar2 = (n) obj;
                return new k2.h(h1.c.d(Math.round(nVar2.f3711a), Math.round(nVar2.f3712b)));
            case 9:
                long j7 = ((k2.j) obj).f3415a;
                return new n((int) (j7 >> 32), (int) (j7 & 4294967295L));
            case 10:
                n nVar3 = (n) obj;
                int iRound = Math.round(nVar3.f3711a);
                if (iRound < 0) {
                    iRound = 0;
                }
                int iRound2 = Math.round(nVar3.f3712b);
                return new k2.j(a.a.f(iRound, iRound2 >= 0 ? iRound2 : 0));
            case 11:
                return new m(((Number) obj).intValue());
            case 12:
                return Integer.valueOf((int) ((m) obj).f3703a);
            case 13:
                long j8 = ((x0.c) obj).f6532a;
                return new n(x0.c.d(j8), x0.c.e(j8));
            case 14:
                n nVar4 = (n) obj;
                return new x0.c(p3.j.b(nVar4.f3711a, nVar4.f3712b));
            case 15:
                x0.d dVar = (x0.d) obj;
                return new p(dVar.f6534a, dVar.f6535b, dVar.f6536c, dVar.f6537d);
            case 16:
                p pVar = (p) obj;
                return new x0.d(pVar.f3730a, pVar.f3731b, pVar.f3732c, pVar.f3733d);
            case 17:
                long j9 = ((x0.f) obj).f6546a;
                return new n(x0.f.d(j9), x0.f.b(j9));
            default:
                n nVar5 = (n) obj;
                return new x0.f(p3.j.f(nVar5.f3711a, nVar5.f3712b));
        }
    }
}

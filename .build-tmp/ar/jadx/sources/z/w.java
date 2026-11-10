package z;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w extends b4.m implements a4.c {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final w f7018f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final w f7019g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final w f7020h;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f7021e;

    static {
        int i5 = 1;
        f7018f = new w(i5, 0);
        f7019g = new w(i5, 1);
        f7020h = new w(i5, 2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ w(int i5, int i6) {
        super(i5);
        this.f7021e = i6;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f7021e) {
            case 0:
                long j5 = ((x0.c) obj).f6532a;
                return p3.j.E(j5) ? new m.n(x0.c.d(j5), x0.c.e(j5)) : x.f7022a;
            case 1:
                m.n nVar = (m.n) obj;
                return new x0.c(p3.j.b(nVar.f3711a, nVar.f3712b));
            default:
                return p3.n.f5026a;
        }
    }
}

package n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l extends b4.m implements a4.c {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final l f3953f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final l f3954g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final l f3955h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final l f3956i;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f3957e;

    static {
        int i5 = 1;
        f3953f = new l(i5, 0);
        f3954g = new l(i5, 1);
        f3955h = new l(i5, 2);
        f3956i = new l(i5, 3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l(int i5, int i6) {
        super(i5);
        this.f3957e = i6;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f3957e) {
            case 0:
                ((p1.f0) obj).c();
                return p3.n.f5026a;
            case 1:
                return p3.n.f5026a;
            case 2:
                ((Number) obj).longValue();
                return p3.n.f5026a;
            default:
                return new h1(((Number) obj).intValue());
        }
    }
}

package n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k0 extends b4.m implements a4.a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final k0 f3950f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final k0 f3951g;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f3952e;

    static {
        int i5 = 0;
        f3950f = new k0(i5, 0);
        f3951g = new k0(i5, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k0(int i5, int i6) {
        super(i5);
        this.f3952e = i6;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f3952e) {
            case 0:
                return x.f4041a;
            case 1:
                return new v0();
            default:
                return new h1(0);
        }
    }
}

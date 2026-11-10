package w0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f extends b4.m implements a4.a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final f f6233f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final f f6234g;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6235e;

    static {
        int i5 = 0;
        f6233f = new f(i5, 0);
        f6234g = new f(i5, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f(int i5, int i6) {
        super(i5);
        this.f6235e = i6;
    }

    @Override // a4.a
    public final /* bridge */ /* synthetic */ Object a() {
        switch (this.f6235e) {
            case 0:
                return Boolean.FALSE;
            default:
                return p3.n.f5026a;
        }
    }
}

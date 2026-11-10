package z;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y extends b4.m implements a4.a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final y f7026f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final y f7027g;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f7028e;

    static {
        int i5 = 0;
        f7026f = new y(i5, 0);
        f7027g = new y(i5, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ y(int i5, int i6) {
        super(i5);
        this.f7028e = i6;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f7028e) {
            case 0:
                return null;
            default:
                return l0.f6978b;
        }
    }
}

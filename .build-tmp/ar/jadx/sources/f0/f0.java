package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class f0 implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2162d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f2163e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f2164f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f2165g;

    public /* synthetic */ f0(int i5, int i6, Object obj, Object obj2) {
        this.f2162d = i6;
        this.f2165g = obj;
        this.f2163e = obj2;
        this.f2164f = i5;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f2162d) {
            case 0:
                ((Integer) obj2).intValue();
                l.a((z1) this.f2165g, (a4.e) this.f2163e, (p) obj, l.z(this.f2164f | 1));
                break;
            case 1:
                ((Integer) obj2).intValue();
                l.b((z1[]) this.f2165g, (a4.e) this.f2163e, (p) obj, l.z(this.f2164f | 1));
                break;
            default:
                ((Integer) obj2).intValue();
                int iZ = l.z(this.f2164f) | 1;
                ((n0.d) this.f2165g).e(this.f2163e, (p) obj, iZ);
                break;
        }
        return p3.n.f5026a;
    }
}

package z;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o implements j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final o f6985b = new o(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final o f6986c = new o(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final e2.f0 f6987d = new e2.f0(6);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final e2.f0 f6988e = new e2.f0(7);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final e2.f0 f6989f = new e2.f0(8);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final e2.f0 f6990g = new e2.f0(9);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6991a;

    public /* synthetic */ o(int i5) {
        this.f6991a = i5;
    }

    @Override // z.j
    public long a(e2.l lVar, int i5) {
        switch (this.f6991a) {
            case 0:
                String str = ((y1.e0) lVar.f2036e).f6753a.f6743a.f6759d;
                return p3.j.g(v.l0.o(str, i5), v.l0.n(str, i5));
            default:
                return ((y1.e0) lVar.f2036e).k(i5);
        }
    }
}

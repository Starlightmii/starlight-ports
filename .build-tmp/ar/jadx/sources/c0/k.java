package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k extends b4.m implements a4.c {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final k f958f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final k f959g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final k f960h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final k f961i;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f962e;

    static {
        int i5 = 1;
        f958f = new k(i5, 0);
        f959g = new k(i5, 1);
        f960h = new k(i5, 2);
        f961i = new k(i5, 3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k(int i5, int i6) {
        super(i5);
        this.f962e = i6;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        int i5 = this.f962e;
        p3.n nVar = p3.n.f5026a;
        switch (i5) {
            case 0:
                w1.s.d((w1.i) obj, 0);
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                f4.d[] dVarArr = w1.s.f6363a;
                w1.t tVar = w1.q.f6347l;
                f4.d dVar = w1.s.f6363a[5];
                tVar.a((w1.i) obj, Boolean.TRUE);
                break;
        }
        return nVar;
    }
}

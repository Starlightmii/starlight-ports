package v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ z.e0 f6143e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f6144f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f6145g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(z.e0 e0Var, boolean z2, int i5) {
        super(2);
        this.f6143e = e0Var;
        this.f6144f = z2;
        this.f6145g = i5;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iZ = f0.l.z(this.f6145g | 1);
        l0.f(this.f6143e, this.f6144f, (f0.p) obj, iZ);
        return p3.n.f5026a;
    }
}

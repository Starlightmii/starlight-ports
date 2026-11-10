package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m0 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ a4.a f1041e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ r0.p f1042f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ boolean f1043g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ l0 f1044h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ a4.e f1045i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m0(a4.a aVar, r0.p pVar, boolean z2, l0 l0Var, a4.e eVar, int i5) {
        super(2);
        this.f1041e = aVar;
        this.f1042f = pVar;
        this.f1043g = z2;
        this.f1044h = l0Var;
        this.f1045i = eVar;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iZ = f0.l.z(196609);
        h2.e(this.f1041e, this.f1042f, this.f1043g, this.f1044h, this.f1045i, (f0.p) obj, iZ);
        return p3.n.f5026a;
    }
}

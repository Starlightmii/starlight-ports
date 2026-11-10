package v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ r0.p f5848e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f5849f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f5850g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(r0.p pVar, int i5, int i6) {
        super(2);
        this.f5848e = pVar;
        this.f5849f = i5;
        this.f5850g = i6;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iZ = f0.l.z(this.f5849f | 1);
        int i5 = this.f5850g;
        d.b(this.f5848e, (f0.p) obj, iZ, i5);
        return p3.n.f5026a;
    }
}

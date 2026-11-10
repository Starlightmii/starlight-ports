package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t3 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ boolean f1321e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a4.a f1322f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ r0.p f1323g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ boolean f1324h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ a4.e f1325i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ long f1326j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ long f1327k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t3(boolean z2, a4.a aVar, r0.p pVar, boolean z3, a4.e eVar, long j5, long j6, int i5) {
        super(2);
        this.f1321e = z2;
        this.f1322f = aVar;
        this.f1323g = pVar;
        this.f1324h = z3;
        this.f1325i = eVar;
        this.f1326j = j5;
        this.f1327k = j6;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iZ = f0.l.z(24577);
        a4.b(this.f1321e, this.f1322f, this.f1323g, this.f1324h, this.f1325i, this.f1326j, this.f1327k, (f0.p) obj, iZ);
        return p3.n.f5026a;
    }
}

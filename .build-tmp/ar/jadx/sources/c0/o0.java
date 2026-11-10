package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o0 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ d1.b f1125e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ String f1126f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ r0.p f1127g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ long f1128h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1129i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o0(d1.b bVar, String str, r0.p pVar, long j5, int i5) {
        super(2);
        this.f1125e = bVar;
        this.f1126f = str;
        this.f1127g = pVar;
        this.f1128h = j5;
        this.f1129i = i5;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        q0.a(this.f1125e, this.f1126f, this.f1127g, this.f1128h, (f0.p) obj, f0.l.z(this.f1129i | 1));
        return p3.n.f5026a;
    }
}

package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h0 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ r0.p f889e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ float f890f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ long f891g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f892h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f893i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h0(r0.p pVar, float f5, long j5, int i5, int i6) {
        super(2);
        this.f889e = pVar;
        this.f890f = f5;
        this.f891g = j5;
        this.f892h = i5;
        this.f893i = i6;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        h2.d(this.f889e, this.f890f, this.f891g, (f0.p) obj, f0.l.z(this.f892h | 1), this.f893i);
        return p3.n.f5026a;
    }
}

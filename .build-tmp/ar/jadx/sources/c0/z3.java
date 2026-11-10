package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z3 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ long f1491e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ long f1492f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ boolean f1493g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ n0.d f1494h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1495i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z3(long j5, long j6, boolean z2, n0.d dVar, int i5) {
        super(2);
        this.f1491e = j5;
        this.f1492f = j6;
        this.f1493g = z2;
        this.f1494h = dVar;
        this.f1495i = i5;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        a4.c(this.f1491e, this.f1492f, this.f1493g, this.f1494h, (f0.p) obj, f0.l.z(this.f1495i | 1));
        return p3.n.f5026a;
    }
}

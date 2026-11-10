package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h3 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ r0.p f897e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a4.e f898f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ a4.e f899g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ y0.q0 f900h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ long f901i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ long f902j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ long f903k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ long f904l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ n0.d f905m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f906n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h3(r0.p pVar, a4.e eVar, a4.e eVar2, y0.q0 q0Var, long j5, long j6, long j7, long j8, n0.d dVar, int i5) {
        super(2);
        this.f897e = pVar;
        this.f898f = eVar;
        this.f899g = eVar2;
        this.f900h = q0Var;
        this.f901i = j5;
        this.f902j = j6;
        this.f903k = j7;
        this.f904l = j8;
        this.f905m = dVar;
        this.f906n = i5;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iZ = f0.l.z(this.f906n | 1);
        j3.a(this.f897e, this.f898f, this.f899g, this.f900h, this.f901i, this.f902j, this.f903k, this.f904l, this.f905m, (f0.p) obj, iZ);
        return p3.n.f5026a;
    }
}

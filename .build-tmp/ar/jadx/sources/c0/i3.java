package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i3 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ z2 f925e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ r0.p f926f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ y0.q0 f927g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ long f928h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ long f929i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ long f930j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ long f931k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ long f932l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f933m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i3(z2 z2Var, r0.p pVar, y0.q0 q0Var, long j5, long j6, long j7, long j8, long j9, int i5) {
        super(2);
        this.f925e = z2Var;
        this.f926f = pVar;
        this.f927g = q0Var;
        this.f928h = j5;
        this.f929i = j6;
        this.f930j = j7;
        this.f931k = j8;
        this.f932l = j9;
        this.f933m = i5;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iZ = f0.l.z(this.f933m | 1);
        j3.b(this.f925e, this.f926f, this.f927g, this.f928h, this.f929i, this.f930j, this.f931k, this.f932l, (f0.p) obj, iZ);
        return p3.n.f5026a;
    }
}

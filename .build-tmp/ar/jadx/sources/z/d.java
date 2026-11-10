package z;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ l f6915e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f6916f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ j2.h f6917g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ boolean f6918h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ long f6919i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ r0.p f6920j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ int f6921k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(l lVar, boolean z2, j2.h hVar, boolean z3, long j5, r0.p pVar, int i5) {
        super(2);
        this.f6915e = lVar;
        this.f6916f = z2;
        this.f6917g = hVar;
        this.f6918h = z3;
        this.f6919i = j5;
        this.f6920j = pVar;
        this.f6921k = i5;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        p3.j.c(this.f6915e, this.f6916f, this.f6917g, this.f6918h, this.f6919i, this.f6920j, (f0.p) obj, f0.l.z(this.f6921k | 1));
        return p3.n.f5026a;
    }
}

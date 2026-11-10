package n1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t0 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ r0.p f4160e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a4.e f4161f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f4162g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f4163h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t0(r0.p pVar, a4.e eVar, int i5, int i6) {
        super(2);
        this.f4160e = pVar;
        this.f4161f = eVar;
        this.f4162g = i5;
        this.f4163h = i6;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iZ = f0.l.z(this.f4162g | 1);
        int i5 = this.f4163h;
        v0.c(this.f4160e, this.f4161f, (f0.p) obj, iZ, i5);
        return p3.n.f5026a;
    }
}

package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n0 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ e1.e f1095e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ String f1096f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ r0.p f1097g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ long f1098h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1099i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ int f1100j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n0(e1.e eVar, String str, r0.p pVar, long j5, int i5, int i6) {
        super(2);
        this.f1095e = eVar;
        this.f1096f = str;
        this.f1097g = pVar;
        this.f1098h = j5;
        this.f1099i = i5;
        this.f1100j = i6;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        q0.b(this.f1095e, this.f1096f, this.f1097g, this.f1098h, (f0.p) obj, f0.l.z(this.f1099i | 1), this.f1100j);
        return p3.n.f5026a;
    }
}

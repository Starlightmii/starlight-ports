package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c4 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ d4 f785e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ r0.p f786f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ float f787g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ long f788h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c4(d4 d4Var, r0.p pVar, float f5, long j5, int i5) {
        super(2);
        this.f785e = d4Var;
        this.f786f = pVar;
        this.f787g = f5;
        this.f788h = j5;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iZ = f0.l.z(3073);
        this.f785e.a(this.f786f, this.f787g, this.f788h, (f0.p) obj, iZ);
        return p3.n.f5026a;
    }
}

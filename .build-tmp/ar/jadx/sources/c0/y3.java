package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y3 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ a4.e f1460e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f1461f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y3(int i5, a4.e eVar) {
        super(2);
        this.f1460e = eVar;
        this.f1461f = i5;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iZ = f0.l.z(this.f1461f | 1);
        a4.d(this.f1460e, (f0.p) obj, iZ);
        return p3.n.f5026a;
    }
}

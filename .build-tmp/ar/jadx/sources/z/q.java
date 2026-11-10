package z;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ e2.l f6997e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f6998f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(e2.l lVar, int i5) {
        super(0);
        this.f6997e = lVar;
        this.f6998f = i5;
    }

    @Override // a4.a
    public final Object a() {
        return Integer.valueOf(((y1.e0) this.f6997e.f2036e).e(this.f6998f));
    }
}

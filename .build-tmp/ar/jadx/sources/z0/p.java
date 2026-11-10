package z0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f7085e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ q f7086f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ p(q qVar, int i5) {
        super(1);
        this.f7085e = i5;
        this.f7086f = qVar;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f7085e) {
            case 0:
                double dDoubleValue = ((Number) obj).doubleValue();
                q qVar = this.f7086f;
                return Double.valueOf(qVar.f7098n.b(d4.a.k(dDoubleValue, qVar.f7089e, qVar.f7090f)));
            default:
                double dDoubleValue2 = ((Number) obj).doubleValue();
                q qVar2 = this.f7086f;
                return Double.valueOf(d4.a.k(qVar2.f7095k.b(dDoubleValue2), qVar2.f7089e, qVar2.f7090f));
        }
    }
}

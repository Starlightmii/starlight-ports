package z0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class m implements i {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f7079d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ q f7080e;

    public /* synthetic */ m(q qVar, int i5) {
        this.f7079d = i5;
        this.f7080e = qVar;
    }

    @Override // z0.i
    public final double b(double d5) {
        switch (this.f7079d) {
            case 0:
                q qVar = this.f7080e;
                return d4.a.k(qVar.f7095k.b(d5), qVar.f7089e, qVar.f7090f);
            default:
                q qVar2 = this.f7080e;
                return qVar2.f7098n.b(d4.a.k(d5, qVar2.f7089e, qVar2.f7090f));
        }
    }
}

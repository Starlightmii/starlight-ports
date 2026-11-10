package w0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6269e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ r f6270f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t(r rVar, int i5) {
        super(0);
        this.f6269e = i5;
        this.f6270f = rVar;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f6269e) {
            case 0:
                this.f6270f.y0();
                break;
            default:
                r rVar = this.f6270f;
                if (rVar.f5681d.f5693p) {
                    d.A(rVar);
                }
                break;
        }
        return p3.n.f5026a;
    }
}

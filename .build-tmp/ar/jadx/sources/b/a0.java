package b;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a0 extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f309e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ i0 f310f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a0(i0 i0Var, int i5) {
        super(0);
        this.f309e = i5;
        this.f310f = i0Var;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f309e) {
            case 0:
                this.f310f.b();
                break;
            case 1:
                this.f310f.a();
                break;
            default:
                this.f310f.b();
                break;
        }
        return p3.n.f5026a;
    }
}

package n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g1 extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f3915e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ h1 f3916f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g1(h1 h1Var, int i5) {
        super(0);
        this.f3915e = i5;
        this.f3916f = h1Var;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f3915e) {
            case 0:
                return Boolean.valueOf(this.f3916f.f3920a.g() > 0);
            default:
                h1 h1Var = this.f3916f;
                return Boolean.valueOf(h1Var.f3920a.g() < h1Var.f3923d.g());
        }
    }
}

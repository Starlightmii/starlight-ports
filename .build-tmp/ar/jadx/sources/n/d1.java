package n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d1 extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f3886e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ e1 f3887f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d1(e1 e1Var, int i5) {
        super(0);
        this.f3886e = i5;
        this.f3887f = e1Var;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f3886e) {
            case 0:
                return Float.valueOf(this.f3887f.f3896q.f3920a.g());
            default:
                return Float.valueOf(this.f3887f.f3896q.f3923d.g());
        }
    }
}

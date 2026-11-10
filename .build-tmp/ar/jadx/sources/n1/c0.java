package n1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c0 extends l0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f4108b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f4109c;

    public /* synthetic */ c0(int i5, Object obj) {
        this.f4108b = i5;
        this.f4109c = obj;
    }

    @Override // n1.l0
    public final k2.k b() {
        switch (this.f4108b) {
            case 0:
                return ((p1.m0) this.f4109c).getLayoutDirection();
            default:
                return ((q1.u) this.f4109c).getLayoutDirection();
        }
    }

    @Override // n1.l0
    public final int c() {
        switch (this.f4108b) {
            case 0:
                return ((p1.m0) this.f4109c).g0();
            default:
                return ((q1.u) this.f4109c).getRoot().f4802z.f4910r.f4130d;
        }
    }
}

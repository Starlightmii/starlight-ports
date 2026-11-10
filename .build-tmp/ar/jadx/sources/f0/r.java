package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class r implements a4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2299d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ v f2300e;

    public /* synthetic */ r(v vVar, int i5) {
        this.f2299d = i5;
        this.f2300e = vVar;
    }

    public /* synthetic */ r(v vVar, g1 g1Var) {
        this.f2299d = 0;
        this.f2300e = vVar;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f2299d) {
            case 0:
                throw null;
            case 1:
                return this.f2300e.m();
            default:
                return this.f2300e.m();
        }
    }
}

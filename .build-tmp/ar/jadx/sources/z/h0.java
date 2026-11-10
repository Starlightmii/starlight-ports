package z;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h0 extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6963e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ o.j f6964f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ e0 f6965g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h0(o.j jVar, e0 e0Var, int i5) {
        super(0);
        this.f6963e = i5;
        this.f6964f = jVar;
        this.f6965g = e0Var;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f6963e) {
            case 0:
                this.f6965g.d();
                this.f6964f.f4340a.setValue(o.g.f4338a);
                break;
            case 1:
                this.f6965g.b(false);
                this.f6964f.f4340a.setValue(o.g.f4338a);
                break;
            case 2:
                this.f6965g.l();
                this.f6964f.f4340a.setValue(o.g.f4338a);
                break;
            default:
                this.f6965g.m();
                this.f6964f.f4340a.setValue(o.g.f4338a);
                break;
        }
        return p3.n.f5026a;
    }
}

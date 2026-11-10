package n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o0 extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f3977e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ p0 f3978f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o0(p0 p0Var, int i5) {
        super(0);
        this.f3977e = i5;
        this.f3978f = p0Var;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f3977e) {
            case 0:
                n1.p pVar = (n1.p) this.f3978f.f3990w.getValue();
                return new x0.c(pVar != null ? pVar.Z(0L) : 9205357640488583168L);
            case 1:
                return new x0.c(this.f3978f.f3992y);
            default:
                this.f3978f.A0();
                return p3.n.f5026a;
        }
    }
}

package v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ o0 f6127e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ w0.n f6128f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ e2.n f6129g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ x.c f6130h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(o0 o0Var, w0.n nVar, e2.n nVar2, x.c cVar) {
        super(0);
        this.f6127e = o0Var;
        this.f6128f = nVar;
        this.f6129g = nVar2;
        this.f6130h = cVar;
    }

    @Override // a4.a
    public final Object a() {
        l4.o oVarI;
        if (!this.f6127e.b()) {
            this.f6128f.a(w0.h.f6239i);
        }
        int i5 = this.f6129g.f2042d;
        if (i5 != 7 && i5 != 8 && (oVarI = this.f6130h.i()) != null) {
            ((l4.t) oVarI).q(p3.n.f5026a);
        }
        return Boolean.TRUE;
    }
}

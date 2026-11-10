package v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b0 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public /* synthetic */ Object f5859d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ j1.d0 f5860e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ u0 f5861f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ z.e0 f5862g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b0(j1.d0 d0Var, u0 u0Var, z.e0 e0Var, t3.c cVar) {
        super(2, cVar);
        this.f5860e = d0Var;
        this.f5861f = u0Var;
        this.f5862g = e0Var;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        b0 b0Var = new b0(this.f5860e, this.f5861f, this.f5862g, cVar);
        b0Var.f5859d = obj;
        return b0Var;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        b0 b0Var = (b0) create((i4.v) obj, (t3.c) obj2);
        p3.n nVar = p3.n.f5026a;
        b0Var.invokeSuspend(nVar);
        return nVar;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        p3.j.O(obj);
        i4.v vVar = (i4.v) this.f5859d;
        j1.d0 d0Var = this.f5860e;
        t3.c cVar = null;
        i4.y.n(vVar, null, new a0(d0Var, this.f5861f, cVar, 0), 1);
        i4.y.n(vVar, null, new b0.t(d0Var, this.f5862g, cVar, 17), 1);
        return p3.n.f5026a;
    }
}

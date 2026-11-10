package v;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p0 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public /* synthetic */ Object f6048d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ j1.d0 f6049e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ u0 f6050f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p0(j1.d0 d0Var, u0 u0Var, t3.c cVar) {
        super(2, cVar);
        this.f6049e = d0Var;
        this.f6050f = u0Var;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        p0 p0Var = new p0(this.f6049e, this.f6050f, cVar);
        p0Var.f6048d = obj;
        return p0Var;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        return ((p0) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        p3.j.O(obj);
        i4.v vVar = (i4.v) this.f6048d;
        j1.d0 d0Var = this.f6049e;
        u0 u0Var = this.f6050f;
        i4.y.n(vVar, null, new a0(d0Var, u0Var, null, 1), 1);
        return i4.y.n(vVar, null, new a0(d0Var, u0Var, null, 2), 1);
    }
}

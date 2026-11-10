package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4521d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ n f4522e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f4523f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ b0.d f4524g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(n nVar, int i5, b0.d dVar, t3.c cVar) {
        super(2, cVar);
        this.f4522e = nVar;
        this.f4523f = i5;
        this.f4524g = dVar;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        return new l(this.f4522e, this.f4523f, this.f4524g, cVar);
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        return ((l) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        int i5 = this.f4521d;
        if (i5 == 0) {
            p3.j.O(obj);
            n nVar = this.f4522e;
            n.t0 t0Var = nVar.f4540c;
            m mVar = nVar.f4539b;
            b0.d dVar = new b0.d(nVar, this.f4524g, (t3.c) null, 5);
            this.f4521d = 1;
            t0Var.getClass();
            Object objC = i4.y.c(new n.s0(this.f4523f, t0Var, dVar, mVar, null), this);
            u3.a aVar = u3.a.f5838d;
            if (objC == aVar) {
                return aVar;
            }
        } else {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            p3.j.O(obj);
        }
        return p3.n.f5026a;
    }
}

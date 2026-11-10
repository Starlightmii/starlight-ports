package n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s extends v3.i implements a4.f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4003d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public /* synthetic */ p.o0 f4004e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public /* synthetic */ long f4005f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ t f4006g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(t tVar, t3.c cVar) {
        super(3, cVar);
        this.f4006g = tVar;
    }

    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        long j5 = ((x0.c) obj2).f6532a;
        s sVar = new s(this.f4006g, (t3.c) obj3);
        sVar.f4004e = (p.o0) obj;
        sVar.f4005f = j5;
        return sVar.invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        Object objC;
        int i5 = this.f4003d;
        p3.n nVar = p3.n.f5026a;
        if (i5 != 0) {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            p3.j.O(obj);
            return nVar;
        }
        p3.j.O(obj);
        p.o0 o0Var = this.f4004e;
        long j5 = this.f4005f;
        t tVar = this.f4006g;
        if (tVar.f4021w) {
            this.f4003d = 1;
            q.k kVar = tVar.f4017s;
            u3.a aVar = u3.a.f5838d;
            if (kVar == null || (objC = i4.y.c(new b(o0Var, j5, kVar, tVar, null), this)) != aVar) {
                objC = nVar;
            }
            if (objC == aVar) {
                return aVar;
            }
        }
        return nVar;
    }
}

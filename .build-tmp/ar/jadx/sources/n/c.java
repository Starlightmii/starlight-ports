package n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3876d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3877e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ t f3878f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ q.m f3879g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c(t tVar, q.m mVar, t3.c cVar, int i5) {
        super(2, cVar);
        this.f3876d = i5;
        this.f3878f = tVar;
        this.f3879g = mVar;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f3876d) {
            case 0:
                return new c(this.f3878f, this.f3879g, cVar, 0);
            default:
                return new c(this.f3878f, this.f3879g, cVar, 1);
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        i4.v vVar = (i4.v) obj;
        t3.c cVar = (t3.c) obj2;
        switch (this.f3876d) {
            case 0:
                break;
        }
        return ((c) create(vVar, cVar)).invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        switch (this.f3876d) {
            case 0:
                int i5 = this.f3877e;
                if (i5 == 0) {
                    p3.j.O(obj);
                    q.k kVar = this.f3878f.f4017s;
                    if (kVar != null) {
                        this.f3877e = 1;
                        Object objB = kVar.b(this.f3879g, this);
                        u3.a aVar = u3.a.f5838d;
                        if (objB == aVar) {
                            return aVar;
                        }
                    }
                } else {
                    if (i5 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                return p3.n.f5026a;
            default:
                int i6 = this.f3877e;
                if (i6 == 0) {
                    p3.j.O(obj);
                    q.k kVar2 = this.f3878f.f4017s;
                    if (kVar2 != null) {
                        q.n nVar = new q.n(this.f3879g);
                        this.f3877e = 1;
                        Object objB2 = kVar2.b(nVar, this);
                        u3.a aVar2 = u3.a.f5838d;
                        if (objB2 == aVar2) {
                            return aVar2;
                        }
                    }
                } else {
                    if (i6 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                return p3.n.f5026a;
        }
    }
}

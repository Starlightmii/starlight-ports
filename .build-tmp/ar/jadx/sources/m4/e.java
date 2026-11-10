package m4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3819d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3820e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public /* synthetic */ Object f3821f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ f f3822g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(f fVar, t3.c cVar, int i5) {
        super(2, cVar);
        this.f3819d = i5;
        this.f3822g = fVar;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f3819d) {
            case 0:
                e eVar = new e(this.f3822g, cVar, 0);
                eVar.f3821f = obj;
                return eVar;
            default:
                e eVar2 = new e(this.f3822g, cVar, 1);
                eVar2.f3821f = obj;
                return eVar2;
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f3819d) {
            case 0:
                return ((e) create((k4.p) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            default:
                return ((e) create((l4.e) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
        }
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f3819d) {
            case 0:
                int i5 = this.f3820e;
                p3.n nVar = p3.n.f5026a;
                if (i5 != 0) {
                    if (i5 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                    return nVar;
                }
                p3.j.O(obj);
                k4.p pVar = (k4.p) this.f3821f;
                this.f3820e = 1;
                Object objD = this.f3822g.d(new s(pVar), this);
                u3.a aVar = u3.a.f5838d;
                if (objD != aVar) {
                    objD = nVar;
                }
                return objD == aVar ? aVar : nVar;
            default:
                int i6 = this.f3820e;
                if (i6 == 0) {
                    p3.j.O(obj);
                    l4.e eVar = (l4.e) this.f3821f;
                    this.f3820e = 1;
                    Object objD2 = this.f3822g.d(eVar, this);
                    u3.a aVar2 = u3.a.f5838d;
                    if (objD2 == aVar2) {
                        return aVar2;
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

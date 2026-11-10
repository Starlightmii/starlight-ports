package q1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d3 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f5154d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5155e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ f3 f5156f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d3(f3 f3Var, t3.c cVar, int i5) {
        super(2, cVar);
        this.f5154d = i5;
        this.f5156f = f3Var;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f5154d) {
            case 0:
                return new d3(this.f5156f, cVar, 0);
            default:
                return new d3(this.f5156f, cVar, 1);
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        i4.v vVar = (i4.v) obj;
        t3.c cVar = (t3.c) obj2;
        switch (this.f5154d) {
            case 0:
                break;
        }
        return ((d3) create(vVar, cVar)).invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        switch (this.f5154d) {
            case 0:
                int i5 = this.f5155e;
                p3.n nVar = p3.n.f5026a;
                if (i5 != 0) {
                    if (i5 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                    return nVar;
                }
                p3.j.O(obj);
                u uVar = this.f5156f.f5202d;
                this.f5155e = 1;
                Object objE = uVar.f5399p.e(this);
                u3.a aVar = u3.a.f5838d;
                if (objE != aVar) {
                    objE = nVar;
                }
                return objE == aVar ? aVar : nVar;
            default:
                int i6 = this.f5155e;
                p3.n nVar2 = p3.n.f5026a;
                if (i6 != 0) {
                    if (i6 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                    return nVar2;
                }
                p3.j.O(obj);
                u uVar2 = this.f5156f.f5202d;
                this.f5155e = 1;
                Object objE2 = uVar2.f5401q.e(this);
                u3.a aVar2 = u3.a.f5838d;
                if (objE2 != aVar2) {
                    objE2 = nVar2;
                }
                return objE2 == aVar2 ? aVar2 : nVar2;
        }
    }
}

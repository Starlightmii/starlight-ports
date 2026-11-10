package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o1 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f4560d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4561e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a4.f f4562f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ o0 f4563g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ j1.u f4564h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o1(a4.f fVar, o0 o0Var, j1.u uVar, t3.c cVar, int i5) {
        super(2, cVar);
        this.f4560d = i5;
        this.f4562f = fVar;
        this.f4563g = o0Var;
        this.f4564h = uVar;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f4560d) {
            case 0:
                return new o1(this.f4562f, this.f4563g, this.f4564h, cVar, 0);
            default:
                return new o1(this.f4562f, this.f4563g, this.f4564h, cVar, 1);
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        i4.v vVar = (i4.v) obj;
        t3.c cVar = (t3.c) obj2;
        switch (this.f4560d) {
            case 0:
                break;
        }
        return ((o1) create(vVar, cVar)).invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f4560d) {
            case 0:
                int i5 = this.f4561e;
                if (i5 == 0) {
                    p3.j.O(obj);
                    x0.c cVar = new x0.c(this.f4564h.f3276c);
                    this.f4561e = 1;
                    Object objB = this.f4562f.b(this.f4563g, cVar, this);
                    u3.a aVar = u3.a.f5838d;
                    if (objB == aVar) {
                        return aVar;
                    }
                } else {
                    if (i5 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                return p3.n.f5026a;
            default:
                int i6 = this.f4561e;
                if (i6 == 0) {
                    p3.j.O(obj);
                    x0.c cVar2 = new x0.c(this.f4564h.f3276c);
                    this.f4561e = 1;
                    Object objB2 = this.f4562f.b(this.f4563g, cVar2, this);
                    u3.a aVar2 = u3.a.f5838d;
                    if (objB2 == aVar2) {
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

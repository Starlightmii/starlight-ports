package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l1 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f4528d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4529e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ o0 f4530f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l1(o0 o0Var, t3.c cVar, int i5) {
        super(2, cVar);
        this.f4528d = i5;
        this.f4530f = o0Var;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f4528d) {
            case 0:
                return new l1(this.f4530f, cVar, 0);
            case 1:
                return new l1(this.f4530f, cVar, 1);
            default:
                return new l1(this.f4530f, cVar, 2);
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        i4.v vVar = (i4.v) obj;
        t3.c cVar = (t3.c) obj2;
        switch (this.f4528d) {
            case 0:
                break;
            case 1:
                break;
        }
        return ((l1) create(vVar, cVar)).invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f4528d) {
            case 0:
                int i5 = this.f4529e;
                if (i5 == 0) {
                    p3.j.O(obj);
                    this.f4529e = 1;
                    Object objC = this.f4530f.c(this);
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
            case 1:
                int i6 = this.f4529e;
                if (i6 == 0) {
                    p3.j.O(obj);
                    this.f4529e = 1;
                    Object objC2 = this.f4530f.c(this);
                    u3.a aVar2 = u3.a.f5838d;
                    if (objC2 == aVar2) {
                        return aVar2;
                    }
                } else {
                    if (i6 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                return p3.n.f5026a;
            default:
                int i7 = this.f4529e;
                if (i7 == 0) {
                    p3.j.O(obj);
                    this.f4529e = 1;
                    Object objC3 = this.f4530f.c(this);
                    u3.a aVar3 = u3.a.f5838d;
                    if (objC3 == aVar3) {
                        return aVar3;
                    }
                } else {
                    if (i7 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                return p3.n.f5026a;
        }
    }
}

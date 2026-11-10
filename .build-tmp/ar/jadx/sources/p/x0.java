package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x0 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f4643d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4644e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a1 f4645f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public /* synthetic */ long f4646g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ x0(a1 a1Var, long j5, t3.c cVar, int i5) {
        super(2, cVar);
        this.f4643d = i5;
        this.f4645f = a1Var;
        this.f4646g = j5;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x0(a1 a1Var, t3.c cVar) {
        super(2, cVar);
        this.f4643d = 3;
        this.f4645f = a1Var;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f4643d) {
            case 0:
                return new x0(this.f4645f, this.f4646g, cVar, 0);
            case 1:
                return new x0(this.f4645f, this.f4646g, cVar, 1);
            case 2:
                return new x0(this.f4645f, this.f4646g, cVar, 2);
            default:
                x0 x0Var = new x0(this.f4645f, cVar);
                x0Var.f4646g = ((x0.c) obj).f6532a;
                return x0Var;
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f4643d) {
            case 0:
                return ((x0) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 1:
                return ((x0) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            case 2:
                return ((x0) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
            default:
                long j5 = ((x0.c) obj).f6532a;
                x0 x0Var = new x0(this.f4645f, (t3.c) obj2);
                x0Var.f4646g = j5;
                return x0Var.invokeSuspend(p3.n.f5026a);
        }
    }

    /* JADX WARN: Code duplicated, block: B:55:0x00e5  */
    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        Object objInvokeSuspend;
        switch (this.f4643d) {
            case 0:
                int i5 = this.f4644e;
                p3.n nVar = p3.n.f5026a;
                if (i5 != 0) {
                    if (i5 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                    return nVar;
                }
                p3.j.O(obj);
                i1 i1Var = this.f4645f.E;
                long j5 = this.f4646g;
                this.f4644e = 1;
                long jA = i1Var.f4487d == l0.f4526e ? k2.o.a(j5, 0.0f, 0.0f, 1) : k2.o.a(j5, 0.0f, 0.0f, 2);
                h1 h1Var = new h1(i1Var, null);
                n.x0 x0Var = i1Var.f4485b;
                u3.a aVar = u3.a.f5838d;
                if (x0Var == null || !(i1Var.f4484a.c() || i1Var.f4484a.a())) {
                    h1 h1Var2 = new h1(i1Var, this);
                    h1Var2.f4472f = jA;
                    objInvokeSuspend = h1Var2.invokeSuspend(nVar);
                    if (objInvokeSuspend != aVar) {
                        objInvokeSuspend = nVar;
                    }
                } else {
                    objInvokeSuspend = x0Var.e(jA, h1Var, this);
                    if (objInvokeSuspend != aVar) {
                        objInvokeSuspend = nVar;
                    }
                }
                return objInvokeSuspend == aVar ? aVar : nVar;
            case 1:
                int i6 = this.f4644e;
                if (i6 == 0) {
                    p3.j.O(obj);
                    i1 i1Var2 = this.f4645f.E;
                    y0 y0Var = new y0(this.f4646g, null, 0);
                    this.f4644e = 1;
                    Object objE = i1Var2.e(2, y0Var, this);
                    u3.a aVar2 = u3.a.f5838d;
                    if (objE == aVar2) {
                        return aVar2;
                    }
                } else {
                    if (i6 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                return p3.n.f5026a;
            case 2:
                int i7 = this.f4644e;
                if (i7 == 0) {
                    p3.j.O(obj);
                    i1 i1Var3 = this.f4645f.E;
                    y0 y0Var2 = new y0(this.f4646g, null, 1);
                    this.f4644e = 1;
                    Object objE2 = i1Var3.e(2, y0Var2, this);
                    u3.a aVar3 = u3.a.f5838d;
                    if (objE2 == aVar3) {
                        return aVar3;
                    }
                } else {
                    if (i7 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                }
                return p3.n.f5026a;
            default:
                int i8 = this.f4644e;
                if (i8 != 0) {
                    if (i8 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    p3.j.O(obj);
                    return obj;
                }
                p3.j.O(obj);
                long j6 = this.f4646g;
                i1 i1Var4 = this.f4645f.E;
                this.f4644e = 1;
                Object objA = androidx.compose.foundation.gestures.a.a(i1Var4, j6, this);
                u3.a aVar4 = u3.a.f5838d;
                return objA == aVar4 ? aVar4 : objA;
        }
    }
}

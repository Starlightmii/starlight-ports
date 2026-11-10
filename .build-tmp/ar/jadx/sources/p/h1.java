package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h1 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f4470d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4471e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public /* synthetic */ long f4472f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ i1 f4473g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h1(i1 i1Var, t3.c cVar) {
        super(2, cVar);
        this.f4473g = i1Var;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        h1 h1Var = new h1(this.f4473g, cVar);
        h1Var.f4472f = ((k2.o) obj).f3424a;
        return h1Var;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        long j5 = ((k2.o) obj).f3424a;
        h1 h1Var = new h1(this.f4473g, (t3.c) obj2);
        h1Var.f4472f = j5;
        return h1Var.invokeSuspend(p3.n.f5026a);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x006f  */
    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        long j5;
        long j6;
        long j7;
        long j8;
        long j9;
        int i5 = this.f4471e;
        i1 i1Var = this.f4473g;
        u3.a aVar = u3.a.f5838d;
        if (i5 == 0) {
            p3.j.O(obj);
            j5 = this.f4472f;
            a1.b bVar = i1Var.f4489f;
            this.f4472f = j5;
            this.f4471e = 1;
            obj = bVar.o(j5, this);
            if (obj != aVar) {
            }
            return aVar;
        }
        if (i5 == 1) {
            j5 = this.f4472f;
            p3.j.O(obj);
        } else {
            if (i5 == 2) {
                j6 = this.f4470d;
                j5 = this.f4472f;
                p3.j.O(obj);
                j7 = ((k2.o) obj).f3424a;
                a1.b bVar2 = i1Var.f4489f;
                long jD = k2.o.d(j6, j7);
                this.f4472f = j5;
                this.f4470d = j7;
                this.f4471e = 3;
                obj = bVar2.n(jD, j7, this);
                if (obj != aVar) {
                    j8 = j5;
                    j9 = j7;
                }
                return aVar;
            }
            if (i5 != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            j9 = this.f4470d;
            j8 = this.f4472f;
            p3.j.O(obj);
        }
        return new k2.o(k2.o.d(j8, k2.o.d(j9, ((k2.o) obj).f3424a)));
        long jD2 = k2.o.d(j5, ((k2.o) obj).f3424a);
        this.f4472f = j5;
        this.f4470d = jD2;
        this.f4471e = 2;
        obj = i1Var.b(jD2, this);
        if (obj != aVar) {
            j6 = jD2;
            j7 = ((k2.o) obj).f3424a;
            a1.b bVar3 = i1Var.f4489f;
            long jD3 = k2.o.d(j6, j7);
            this.f4472f = j5;
            this.f4470d = j7;
            this.f4471e = 3;
            obj = bVar3.n(jD3, j7, this);
            if (obj != aVar) {
                j8 = j5;
                j9 = j7;
                return new k2.o(k2.o.d(j8, k2.o.d(j9, ((k2.o) obj).f3424a)));
            }
        }
        return aVar;
    }
}

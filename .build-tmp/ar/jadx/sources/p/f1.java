package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f1 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public i1 f4450d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b4.v f4451e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f4452f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4453g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public /* synthetic */ Object f4454h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ i1 f4455i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ b4.v f4456j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ long f4457k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f1(i1 i1Var, b4.v vVar, long j5, t3.c cVar) {
        super(2, cVar);
        this.f4455i = i1Var;
        this.f4456j = vVar;
        this.f4457k = j5;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        f1 f1Var = new f1(this.f4455i, this.f4456j, this.f4457k, cVar);
        f1Var.f4454h = obj;
        return f1Var;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        return ((f1) create((g1) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        i1 i1Var;
        b4.v vVar;
        long j5;
        i1 i1Var2;
        int i5 = this.f4453g;
        l0 l0Var = l0.f4526e;
        if (i5 == 0) {
            p3.j.O(obj);
            g1 g1Var = (g1) this.f4454h;
            i1Var = this.f4455i;
            e1 e1Var = new e1(i1Var, g1Var);
            a2.h hVar = i1Var.f4486c;
            b4.v vVar2 = this.f4456j;
            long j6 = vVar2.f696d;
            l0 l0Var2 = i1Var.f4487d;
            long j7 = this.f4457k;
            float fC = i1Var.c(l0Var2 == l0Var ? k2.o.b(j7) : k2.o.c(j7));
            this.f4454h = i1Var;
            this.f4450d = i1Var;
            this.f4451e = vVar2;
            this.f4452f = j6;
            this.f4453g = 1;
            hVar.getClass();
            obj = i4.y.t((r0) hVar.f50f, new k(fC, hVar, e1Var, null), this);
            u3.a aVar = u3.a.f5838d;
            if (obj == aVar) {
                return aVar;
            }
            vVar = vVar2;
            j5 = j6;
            i1Var2 = i1Var;
        } else {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            j5 = this.f4452f;
            vVar = this.f4451e;
            i1Var = this.f4450d;
            i1Var2 = (i1) this.f4454h;
            p3.j.O(obj);
        }
        float fC2 = i1Var2.c(((Number) obj).floatValue());
        vVar.f696d = i1Var.f4487d == l0Var ? k2.o.a(j5, fC2, 0.0f, 2) : k2.o.a(j5, 0.0f, fC2, 1);
        return p3.n.f5026a;
    }
}

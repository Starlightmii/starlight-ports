package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x2 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1427d = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1428e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f1429f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f1430g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f1431h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f1432i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x2(f0.i1 i1Var, boolean z2, q.k kVar, t3.c cVar) {
        super(2, cVar);
        this.f1431h = i1Var;
        this.f1429f = z2;
        this.f1432i = kVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x2(m.c cVar, boolean z2, m.z0 z0Var, a4.a aVar, t3.c cVar2) {
        super(2, cVar2);
        this.f1430g = cVar;
        this.f1429f = z2;
        this.f1431h = z0Var;
        this.f1432i = aVar;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f1427d) {
            case 0:
                return new x2((m.c) this.f1430g, this.f1429f, (m.z0) this.f1431h, (a4.a) this.f1432i, cVar);
            default:
                return new x2((f0.i1) this.f1431h, this.f1429f, (q.k) this.f1432i, cVar);
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        i4.v vVar = (i4.v) obj;
        t3.c cVar = (t3.c) obj2;
        switch (this.f1427d) {
            case 0:
                break;
        }
        return ((x2) create(vVar, cVar)).invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        switch (this.f1427d) {
            case 0:
                int i5 = this.f1428e;
                if (i5 == 0) {
                    p3.j.O(obj);
                    m.c cVar = (m.c) this.f1430g;
                    Float f5 = new Float(this.f1429f ? 1.0f : 0.0f);
                    m.z0 z0Var = (m.z0) this.f1431h;
                    this.f1428e = 1;
                    Object objB = m.c.b(cVar, f5, z0Var, this);
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
                ((a4.a) this.f1432i).a();
                return p3.n.f5026a;
            default:
                f0.i1 i1Var = (f0.i1) this.f1431h;
                int i6 = this.f1428e;
                if (i6 == 0) {
                    p3.j.O(obj);
                    q.m mVar = (q.m) i1Var.getValue();
                    if (mVar != null) {
                        q.k kVar = (q.k) this.f1432i;
                        q.i nVar = this.f1429f ? new q.n(mVar) : new q.l(mVar);
                        if (kVar != null) {
                            this.f1430g = i1Var;
                            this.f1428e = 1;
                            Object objB2 = kVar.b(nVar, this);
                            u3.a aVar2 = u3.a.f5838d;
                            if (objB2 == aVar2) {
                                return aVar2;
                            }
                        }
                    }
                    return p3.n.f5026a;
                }
                if (i6 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i1Var = (f0.i1) this.f1430g;
                p3.j.O(obj);
                i1Var.setValue(null);
                return p3.n.f5026a;
        }
    }
}

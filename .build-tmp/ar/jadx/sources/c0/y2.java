package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y2 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1456d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ m.c f1457e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f1458f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ m.z0 f1459g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y2(m.c cVar, boolean z2, m.z0 z0Var, t3.c cVar2) {
        super(2, cVar2);
        this.f1457e = cVar;
        this.f1458f = z2;
        this.f1459g = z0Var;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        return new y2(this.f1457e, this.f1458f, this.f1459g, cVar);
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        return ((y2) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        int i5 = this.f1456d;
        if (i5 == 0) {
            p3.j.O(obj);
            Float f5 = new Float(this.f1458f ? 1.0f : 0.8f);
            this.f1456d = 1;
            Object objB = m.c.b(this.f1457e, f5, this.f1459g, this);
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
    }
}

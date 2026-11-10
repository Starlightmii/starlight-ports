package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z0 extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4655d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ a1 f4656e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ float f4657f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ float f4658g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z0(a1 a1Var, float f5, float f6, t3.c cVar) {
        super(2, cVar);
        this.f4656e = a1Var;
        this.f4657f = f5;
        this.f4658g = f6;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        return new z0(this.f4656e, this.f4657f, this.f4658g, cVar);
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        return ((z0) create((i4.v) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        int i5 = this.f4655d;
        if (i5 == 0) {
            p3.j.O(obj);
            i1 i1Var = this.f4656e.E;
            long jB = p3.j.b(this.f4657f, this.f4658g);
            this.f4655d = 1;
            Object objA = androidx.compose.foundation.gestures.a.a(i1Var, jB, this);
            u3.a aVar = u3.a.f5838d;
            if (objA == aVar) {
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

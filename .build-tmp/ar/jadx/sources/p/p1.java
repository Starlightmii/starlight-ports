package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p1 extends v3.h implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4566e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public /* synthetic */ Object f4567f;

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        p1 p1Var = new p1(cVar);
        p1Var.f4567f = obj;
        return p1Var;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        return ((p1) create((j1.c0) obj, (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        int i5 = this.f4566e;
        if (i5 != 0) {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            p3.j.O(obj);
            return obj;
        }
        p3.j.O(obj);
        j1.c0 c0Var = (j1.c0) this.f4567f;
        this.f4566e = 1;
        Object objD = s1.d(c0Var, j1.j.f3261e, this);
        u3.a aVar = u3.a.f5838d;
        return objD == aVar ? aVar : objD;
    }
}

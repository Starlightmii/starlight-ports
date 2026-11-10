package l4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public /* synthetic */ int f3559d;

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        m mVar = new m(2, cVar);
        mVar.f3559d = ((Number) obj).intValue();
        return mVar;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        return ((m) create(Integer.valueOf(((Number) obj).intValue()), (t3.c) obj2)).invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        p3.j.O(obj);
        return Boolean.valueOf(this.f3559d > 0);
    }
}

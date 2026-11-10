package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends v3.i implements a4.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ c f3616d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f3617e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(c cVar, Object obj, t3.c cVar2) {
        super(1, cVar2);
        this.f3616d = cVar;
        this.f3617e = obj;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        b bVar = (b) create((t3.c) obj);
        p3.n nVar = p3.n.f5026a;
        bVar.invokeSuspend(nVar);
        return nVar;
    }

    @Override // v3.a
    public final t3.c create(t3.c cVar) {
        return new b(this.f3616d, this.f3617e, cVar);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        p3.j.O(obj);
        c cVar = this.f3616d;
        l lVar = cVar.f3631c;
        lVar.f3697f.d();
        lVar.f3698g = Long.MIN_VALUE;
        cVar.f3632d.setValue(Boolean.FALSE);
        Object objA = c.a(cVar, this.f3617e);
        lVar.f3696e.setValue(objA);
        cVar.f3633e.setValue(objA);
        return p3.n.f5026a;
    }
}

package n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d extends v3.i implements a4.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3884d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ t f3885e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d(t tVar, t3.c cVar, int i5) {
        super(2, cVar);
        this.f3884d = i5;
        this.f3885e = tVar;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        switch (this.f3884d) {
            case 0:
                return new d(this.f3885e, cVar, 0);
            default:
                return new d(this.f3885e, cVar, 1);
        }
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        i4.v vVar = (i4.v) obj;
        t3.c cVar = (t3.c) obj2;
        switch (this.f3884d) {
            case 0:
                d dVar = (d) create(vVar, cVar);
                p3.n nVar = p3.n.f5026a;
                dVar.invokeSuspend(nVar);
                return nVar;
            default:
                d dVar2 = (d) create(vVar, cVar);
                p3.n nVar2 = p3.n.f5026a;
                dVar2.invokeSuspend(nVar2);
                return nVar2;
        }
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f3884d) {
            case 0:
                p3.j.O(obj);
                t tVar = this.f3885e;
                if (tVar.D == null) {
                    q.g gVar = new q.g();
                    q.k kVar = tVar.f4017s;
                    if (kVar != null) {
                        i4.y.n(tVar.n0(), null, new b0.t(kVar, gVar, null, 5), 3);
                    }
                    tVar.D = gVar;
                }
                break;
            default:
                p3.j.O(obj);
                t tVar2 = this.f3885e;
                q.g gVar2 = tVar2.D;
                if (gVar2 != null) {
                    q.h hVar = new q.h(gVar2);
                    q.k kVar2 = tVar2.f4017s;
                    t3.c cVar = null;
                    if (kVar2 != null) {
                        i4.y.n(tVar2.n0(), null, new b0.t(kVar2, hVar, cVar, 6), 3);
                    }
                    tVar2.D = null;
                }
                break;
        }
        return p3.n.f5026a;
    }
}

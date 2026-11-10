package i4;

import f0.c2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g implements a4.f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2729d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f2730e;

    public /* synthetic */ g(int i5, Object obj) {
        this.f2729d = i5;
        this.f2730e = obj;
    }

    public /* synthetic */ g(q4.c cVar, q4.b bVar) {
        this.f2729d = 1;
        this.f2730e = cVar;
    }

    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        switch (this.f2729d) {
            case 0:
                ((c2) this.f2730e).c((Throwable) obj);
                break;
            case 1:
                q4.c cVar = (q4.c) this.f2730e;
                q4.c.f5483g.set(cVar, null);
                cVar.d(null);
                break;
            default:
                ((q4.g) this.f2730e).b();
                break;
        }
        return p3.n.f5026a;
    }
}

package i1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public /* synthetic */ Object f2646d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ a1.b f2647e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2648f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(a1.b bVar, v3.c cVar) {
        super(cVar);
        this.f2647e = bVar;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f2646d = obj;
        this.f2648f |= Integer.MIN_VALUE;
        return this.f2647e.n(0L, 0L, this);
    }
}

package t0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f5778d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public k4.b f5779e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public /* synthetic */ Object f5780f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ c f5781g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f5782h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(c cVar, v3.c cVar2) {
        super(cVar2);
        this.f5781g = cVar;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f5780f = obj;
        this.f5782h |= Integer.MIN_VALUE;
        return this.f5781g.e(this);
    }
}

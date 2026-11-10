package m4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public j f3832d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f3833e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public /* synthetic */ Object f3834f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ j f3835g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f3836h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(j jVar, t3.c cVar) {
        super(cVar);
        this.f3835g = jVar;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f3834f = obj;
        this.f3836h |= Integer.MIN_VALUE;
        return this.f3835g.d(null, this);
    }
}

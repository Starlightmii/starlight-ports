package n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g f3888d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f3889e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public /* synthetic */ Object f3890f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ g f3891g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f3892h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(g gVar, v3.c cVar) {
        super(cVar);
        this.f3891g = gVar;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f3890f = obj;
        this.f3892h |= Integer.MIN_VALUE;
        return this.f3891g.e(0L, null, this);
    }
}

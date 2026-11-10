package i1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public e f2652d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f2653e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f2654f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public /* synthetic */ Object f2655g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ e f2656h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2657i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(e eVar, v3.c cVar) {
        super(cVar);
        this.f2656h = eVar;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f2655g = obj;
        this.f2657i |= Integer.MIN_VALUE;
        return this.f2656h.z0(0L, 0L, this);
    }
}

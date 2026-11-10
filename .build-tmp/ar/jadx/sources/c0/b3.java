package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b3 extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c3 f768d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a3 f769e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public q4.a f770f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public /* synthetic */ Object f771g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ c3 f772h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f773i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b3(c3 c3Var, v3.c cVar) {
        super(cVar);
        this.f772h = c3Var;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f771g = obj;
        this.f773i |= Integer.MIN_VALUE;
        return this.f772h.a(null, this);
    }
}

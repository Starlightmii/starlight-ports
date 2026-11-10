package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u1 extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public v1 f4601d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public p3.c f4602e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a4.a f4603f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f4604g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public /* synthetic */ Object f4605h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ v1 f4606i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4607j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u1(v1 v1Var, v3.c cVar) {
        super(cVar);
        this.f4606i = v1Var;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f4605h = obj;
        this.f4607j |= Integer.MIN_VALUE;
        return this.f4606i.a(null, null, this);
    }
}

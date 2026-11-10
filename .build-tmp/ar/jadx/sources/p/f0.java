package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f0 extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a1 f4445d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public r f4446e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public /* synthetic */ Object f4447f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ a1 f4448g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4449h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f0(a1 a1Var, v3.c cVar) {
        super(cVar);
        this.f4448g = a1Var;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f4447f = obj;
        this.f4449h |= Integer.MIN_VALUE;
        return a1.D0(this.f4448g, null, this);
    }
}

package l4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public m4.p f3510d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public /* synthetic */ Object f3511e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a1.d f3512f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3513g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(a1.d dVar, t3.c cVar) {
        super(cVar);
        this.f3512f = dVar;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f3511e = obj;
        this.f3513g |= Integer.MIN_VALUE;
        return this.f3512f.g(null, this);
    }
}

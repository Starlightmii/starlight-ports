package v1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g f6197d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public /* synthetic */ Object f6198e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ g f6199f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f6200g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(g gVar, v3.c cVar) {
        super(cVar);
        this.f6199f = gVar;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f6198e = obj;
        this.f6200g |= Integer.MIN_VALUE;
        return this.f6199f.b(0.0f, this);
    }
}

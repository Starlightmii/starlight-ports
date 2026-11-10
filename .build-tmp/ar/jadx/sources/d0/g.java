package d0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public /* synthetic */ Object f1534d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1535e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ b0.h f1536f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(b0.h hVar, t3.c cVar) {
        super(cVar);
        this.f1536f = hVar;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f1534d = obj;
        this.f1535e |= Integer.MIN_VALUE;
        return this.f1536f.d(null, this);
    }
}

package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class v0 extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f4614d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public /* synthetic */ Object f4615e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ w0 f4616f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4617g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v0(w0 w0Var, v3.c cVar) {
        super(cVar);
        this.f4616f = w0Var;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f4615e = obj;
        this.f4617g |= Integer.MIN_VALUE;
        return this.f4616f.a(0L, 0L, this);
    }
}

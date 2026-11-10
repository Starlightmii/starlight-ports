package s;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public x0.d f5740d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object[] f5741e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5742f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f5743g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public /* synthetic */ Object f5744h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ c f5745i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f5746j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(c cVar, v3.c cVar2) {
        super(cVar2);
        this.f5745i = cVar;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f5744h = obj;
        this.f5746j |= Integer.MIN_VALUE;
        return this.f5745i.a(null, this);
    }
}

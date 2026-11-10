package i1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public /* synthetic */ Object f2649d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ a1.b f2650e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2651f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(a1.b bVar, v3.c cVar) {
        super(cVar);
        this.f2650e = bVar;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f2649d = obj;
        this.f2651f |= Integer.MIN_VALUE;
        return this.f2650e.o(0L, this);
    }
}

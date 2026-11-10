package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d1 extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b4.v f4429d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public /* synthetic */ Object f4430e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ i1 f4431f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4432g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d1(i1 i1Var, v3.c cVar) {
        super(cVar);
        this.f4431f = i1Var;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f4430e = obj;
        this.f4432g |= Integer.MIN_VALUE;
        return this.f4431f.b(0L, this);
    }
}

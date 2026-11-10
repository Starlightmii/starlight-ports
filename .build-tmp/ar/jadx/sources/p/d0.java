package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d0 extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a1 f4425d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public /* synthetic */ Object f4426e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a1 f4427f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4428g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d0(a1 a1Var, v3.c cVar) {
        super(cVar);
        this.f4427f = a1Var;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f4426e = obj;
        this.f4428g |= Integer.MIN_VALUE;
        return a1.B0(this.f4427f, this);
    }
}

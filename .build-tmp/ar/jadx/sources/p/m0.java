package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m0 extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public o0 f4532d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public /* synthetic */ Object f4533e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ o0 f4534f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4535g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m0(o0 o0Var, v3.c cVar) {
        super(cVar);
        this.f4534f = o0Var;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f4533e = obj;
        this.f4535g |= Integer.MIN_VALUE;
        return this.f4534f.c(this);
    }
}

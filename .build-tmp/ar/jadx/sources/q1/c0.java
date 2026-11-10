package q1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c0 extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public f0 f5139d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public j.s f5140e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public k4.b f5141f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public /* synthetic */ Object f5142g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ f0 f5143h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5144i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c0(f0 f0Var, v3.c cVar) {
        super(cVar);
        this.f5143h = f0Var;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f5142g = obj;
        this.f5144i |= Integer.MIN_VALUE;
        return this.f5143h.e(this);
    }
}

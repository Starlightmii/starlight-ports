package q1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public /* synthetic */ Object f5364d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ u f5365e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5366f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(u uVar, v3.c cVar) {
        super(cVar);
        this.f5365e = uVar;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f5364d = obj;
        this.f5366f |= Integer.MIN_VALUE;
        this.f5365e.F(null, this);
        return u3.a.f5838d;
    }
}

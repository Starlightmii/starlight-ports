package l4;

import i4.s0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public t f3571d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public e f3572e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public v f3573f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public s0 f3574g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public /* synthetic */ Object f3575h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ t f3576i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f3577j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(t tVar, t3.c cVar) {
        super(cVar);
        this.f3576i = tVar;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        this.f3575h = obj;
        this.f3577j |= Integer.MIN_VALUE;
        t.k(this.f3576i, null, this);
        return u3.a.f5838d;
    }
}

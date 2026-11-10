package j1;

import i4.h1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a0 extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public h1 f3213d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public /* synthetic */ Object f3214e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ c0 f3215f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3216g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a0(c0 c0Var, v3.a aVar) {
        super(aVar);
        this.f3215f = c0Var;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f3214e = obj;
        this.f3216g |= Integer.MIN_VALUE;
        return this.f3215f.h(0L, null, this);
    }
}

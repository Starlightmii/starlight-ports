package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d1 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ e1 f5548e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f5549f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f5550g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f5551h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ n1.h0 f5552i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d1(e1 e1Var, int i5, n1.m0 m0Var, int i6, n1.h0 h0Var) {
        super(1);
        this.f5548e = e1Var;
        this.f5549f = i5;
        this.f5550g = m0Var;
        this.f5551h = i6;
        this.f5552i = h0Var;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [a4.e, b4.m] */
    @Override // a4.c
    public final Object c(Object obj) {
        ?? r1 = this.f5548e.f5558r;
        n1.m0 m0Var = this.f5550g;
        n1.l0.e((n1.l0) obj, m0Var, ((k2.h) r1.invoke(new k2.j(a.a.f(this.f5549f - m0Var.f4130d, this.f5551h - m0Var.f4131e)), this.f5552i.getLayoutDirection())).f3409a);
        return p3.n.f5026a;
    }
}

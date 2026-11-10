package n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ y0.p f3959e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ long f3960f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ long f3961g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ a1.g f3962h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(y0.s0 s0Var, long j5, long j6, a1.g gVar) {
        super(1);
        this.f3959e = s0Var;
        this.f3960f = j5;
        this.f3961g = j6;
        this.f3962h = gVar;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        p1.f0 f0Var = (p1.f0) obj;
        f0Var.c();
        a1.f.n(f0Var, this.f3959e, this.f3960f, this.f3961g, 0.0f, this.f3962h, 104);
        return p3.n.f5026a;
    }
}

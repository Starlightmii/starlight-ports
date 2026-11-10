package z;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ l f6924e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f6925f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ boolean f6926g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(l lVar, boolean z2, boolean z3) {
        super(1);
        this.f6924e = lVar;
        this.f6925f = z2;
        this.f6926g = z3;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        w1.i iVar = (w1.i) obj;
        long jA = this.f6924e.a();
        iVar.c(v.f7017c, new u(this.f6925f ? v.e0.f5896e : v.e0.f5897f, jA, this.f6926g ? 1 : 3, p3.j.E(jA)));
        return p3.n.f5026a;
    }
}

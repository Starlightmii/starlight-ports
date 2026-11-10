package z;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ a4.a f6955e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f6956f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ y0.c0 f6957g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ y0.m f6958h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(a4.a aVar, boolean z2, y0.c0 c0Var, y0.m mVar) {
        super(1);
        this.f6955e = aVar;
        this.f6956f = z2;
        this.f6957g = c0Var;
        this.f6958h = mVar;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        p1.f0 f0Var = (p1.f0) obj;
        f0Var.c();
        a1.c cVar = f0Var.f4828d;
        if (((Boolean) this.f6955e.a()).booleanValue()) {
            boolean z2 = this.f6956f;
            y0.m mVar = this.f6958h;
            y0.c0 c0Var = this.f6957g;
            if (z2) {
                long jO = cVar.O();
                a1.b bVar = cVar.f27e;
                long jT = bVar.t();
                bVar.r().p();
                try {
                    ((a1.d) bVar.f23d).v(-1.0f, 1.0f, jO);
                    cVar.e(c0Var, mVar);
                } finally {
                    bVar.r().m();
                    bVar.D(jT);
                }
            } else {
                cVar.e(c0Var, mVar);
            }
        }
        return p3.n.f5026a;
    }
}

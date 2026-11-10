package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d0 extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f795e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ e0 f796f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d0(e0 e0Var, int i5) {
        super(0);
        this.f795e = i5;
        this.f796f = e0Var;
    }

    @Override // a4.a
    public final Object a() {
        int i5 = this.f795e;
        e0 e0Var = this.f796f;
        switch (i5) {
            case 0:
                return h2.f896a;
            default:
                if (((g2) p1.f.h(e0Var, i2.f922b)) == null) {
                    b0.u uVar = e0Var.f814w;
                    if (uVar != null) {
                        e0Var.z0(uVar);
                    }
                } else if (e0Var.f814w == null) {
                    c0 c0Var = new c0(e0Var);
                    d0 d0Var = new d0(e0Var, 0);
                    q.j jVar = e0Var.f810s;
                    boolean z2 = e0Var.f811t;
                    float f5 = e0Var.f812u;
                    m.z0 z0Var = b0.s.f448a;
                    b0.u eVar = b0.y.f467a ? new b0.e(jVar, z2, f5, c0Var, d0Var) : new b0.c(jVar, z2, f5, c0Var, d0Var);
                    e0Var.y0(eVar);
                    e0Var.f814w = eVar;
                }
                return p3.n.f5026a;
        }
    }
}

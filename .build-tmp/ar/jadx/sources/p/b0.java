package p;

import f0.h3;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b0 extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4415e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a1 f4416f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b0(a1 a1Var, int i5) {
        super(0);
        this.f4415e = i5;
        this.f4416f = a1Var;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x003c  */
    @Override // a4.a
    public final Object a() {
        boolean z2;
        switch (this.f4415e) {
            case 0:
                k4.c cVar = this.f4416f.f4411w;
                if (cVar != null) {
                    cVar.o(o.f4555a);
                }
                return p3.n.f5026a;
            case 1:
                i1 i1Var = this.f4416f.E;
                if (!i1Var.f4484a.b()) {
                    n.x0 x0Var = i1Var.f4485b;
                    z2 = x0Var != null ? x0Var.b() : false;
                }
                return Boolean.valueOf(!z2);
            default:
                h3 h3Var = q1.j1.f5237f;
                a1 a1Var = this.f4416f;
                a1Var.D.f49e = new a1.d(13, new a1.d((k2.b) p1.f.h(a1Var, h3Var)));
                return p3.n.f5026a;
        }
    }
}

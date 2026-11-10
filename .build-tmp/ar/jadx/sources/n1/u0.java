package n1;

import c0.c2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u0 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4166e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f4167f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f4168g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f4169h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ p3.c f4170i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ u0(Object obj, Object obj2, a4.e eVar, int i5, int i6) {
        super(2);
        this.f4166e = i6;
        this.f4168g = obj;
        this.f4169h = obj2;
        this.f4170i = eVar;
        this.f4167f = i5;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u0(n2.s sVar, a4.a aVar, c2 c2Var, int i5) {
        super(2);
        this.f4166e = 2;
        this.f4168g = sVar;
        this.f4169h = aVar;
        this.f4170i = c2Var;
        this.f4167f = i5;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u0(o.j jVar, a4.a aVar, c2 c2Var, int i5) {
        super(2);
        this.f4166e = 1;
        this.f4168g = jVar;
        this.f4169h = aVar;
        this.f4170i = c2Var;
        this.f4167f = i5;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f4166e) {
            case 0:
                ((Number) obj2).intValue();
                v0.b((y0) this.f4168g, (r0.p) this.f4169h, (a4.e) this.f4170i, (f0.p) obj, f0.l.z(this.f4167f | 1));
                break;
            case 1:
                ((Number) obj2).intValue();
                o.j jVar = (o.j) this.f4168g;
                a4.a aVar = (a4.a) this.f4169h;
                c2 c2Var = (c2) this.f4170i;
                a.a.b(jVar, aVar, r0.m.f5680a, c2Var, (f0.p) obj, f0.l.z(this.f4167f | 1));
                break;
            case 2:
                ((Number) obj2).intValue();
                o.m.c((n2.s) this.f4168g, (a4.a) this.f4169h, (c2) this.f4170i, (f0.p) obj, f0.l.z(this.f4167f | 1));
                break;
            default:
                ((Number) obj2).intValue();
                p3.j.a((z.l) this.f4168g, (r0.c) this.f4169h, (n0.d) this.f4170i, (f0.p) obj, f0.l.z(this.f4167f | 1));
                break;
        }
        return p3.n.f5026a;
    }
}

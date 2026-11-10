package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s0 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1270e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f1271f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f1272g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f1273h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f1274i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ p3.c f1275j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ s0(Object obj, Object obj2, Object obj3, p3.c cVar, int i5, int i6) {
        super(2);
        this.f1270e = i6;
        this.f1272g = obj;
        this.f1273h = obj2;
        this.f1274i = obj3;
        this.f1275j = cVar;
        this.f1271f = i5;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s0(n2.s sVar, a4.a aVar, o.a aVar2, c2 c2Var, int i5) {
        super(2);
        this.f1270e = 2;
        this.f1272g = sVar;
        this.f1273h = aVar;
        this.f1274i = aVar2;
        this.f1275j = c2Var;
        this.f1271f = i5;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f1270e) {
            case 0:
                ((Number) obj2).intValue();
                t0.a((s) this.f1272g, (r2) this.f1273h, (s4) this.f1274i, (a4.e) this.f1275j, (f0.p) obj, f0.l.z(this.f1271f | 1));
                break;
            case 1:
                ((Number) obj2).intValue();
                a.a.b((o.j) this.f1272g, (a4.a) this.f1273h, (r0.p) this.f1274i, (c2) this.f1275j, (f0.p) obj, f0.l.z(this.f1271f | 1));
                break;
            default:
                ((Number) obj2).intValue();
                o.m.d((n2.s) this.f1272g, (a4.a) this.f1273h, (o.a) this.f1274i, (c2) this.f1275j, (f0.p) obj, f0.l.z(this.f1271f | 1));
                break;
        }
        return p3.n.f5026a;
    }
}

package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z0 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1463e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ n0.d f1464f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ boolean f1465g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ a4.a f1466h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1467i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ Object f1468j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ Object f1469k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f1470l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z0(n0.d dVar, a4.a aVar, r0.p pVar, boolean z2, v0 v0Var, r.l0 l0Var, int i5) {
        super(2);
        this.f1464f = dVar;
        this.f1466h = aVar;
        this.f1468j = pVar;
        this.f1465g = z2;
        this.f1469k = v0Var;
        this.f1470l = l0Var;
        this.f1467i = i5;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z0(n0.d dVar, n0.d dVar2, n0.d dVar3, a4.e eVar, boolean z2, a4.a aVar, int i5) {
        super(2);
        this.f1464f = dVar;
        this.f1468j = dVar2;
        this.f1469k = dVar3;
        this.f1470l = eVar;
        this.f1465g = z2;
        this.f1466h = aVar;
        this.f1467i = i5;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z0(o.j jVar, a4.a aVar, c2 c2Var, r0.p pVar, boolean z2, n0.d dVar, int i5) {
        super(2);
        this.f1469k = jVar;
        this.f1466h = aVar;
        this.f1470l = c2Var;
        this.f1468j = pVar;
        this.f1465g = z2;
        this.f1464f = dVar;
        this.f1467i = i5;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f1463e) {
            case 0:
                ((Number) obj2).intValue();
                r0.p pVar = (r0.p) this.f1468j;
                v0 v0Var = (v0) this.f1469k;
                r.l0 l0Var = (r.l0) this.f1470l;
                a1.b(this.f1464f, this.f1466h, pVar, this.f1465g, v0Var, l0Var, (f0.p) obj, f0.l.z(this.f1467i | 1));
                break;
            case 1:
                ((Number) obj2).intValue();
                n0.d dVar = (n0.d) this.f1468j;
                n0.d dVar2 = (n0.d) this.f1469k;
                a4.e eVar = (a4.e) this.f1470l;
                r1.c(this.f1464f, dVar, dVar2, eVar, this.f1465g, this.f1466h, (f0.p) obj, f0.l.z(this.f1467i | 1));
                break;
            default:
                ((Number) obj2).intValue();
                o.j jVar = (o.j) this.f1469k;
                c2 c2Var = (c2) this.f1470l;
                r0.p pVar2 = (r0.p) this.f1468j;
                a.a.c(jVar, this.f1466h, c2Var, pVar2, this.f1465g, this.f1464f, (f0.p) obj, f0.l.z(this.f1467i | 1));
                break;
        }
        return p3.n.f5026a;
    }
}

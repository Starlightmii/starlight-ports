package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f713e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ r0.p f714f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ m.c0 f715g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ f0.i1 f716h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ n.h1 f717i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ y0.q0 f718j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ long f719k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ float f720l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ float f721m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ n0.d f722n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(r0.p pVar, m.c0 c0Var, f0.i1 i1Var, n.h1 h1Var, y0.q0 q0Var, long j5, float f5, float f6, n0.d dVar) {
        super(2);
        this.f714f = pVar;
        this.f715g = c0Var;
        this.f716h = i1Var;
        this.f717i = h1Var;
        this.f718j = q0Var;
        this.f719k = j5;
        this.f720l = f5;
        this.f721m = f6;
        this.f722n = dVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(r0.p pVar, m.c0 c0Var, f0.i1 i1Var, n.h1 h1Var, y0.q0 q0Var, long j5, float f5, float f6, n0.d dVar, int i5) {
        super(2);
        this.f714f = pVar;
        this.f715g = c0Var;
        this.f716h = i1Var;
        this.f717i = h1Var;
        this.f718j = q0Var;
        this.f719k = j5;
        this.f720l = f5;
        this.f721m = f6;
        this.f722n = dVar;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0047  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f713e) {
            case 0:
                f0.p pVar = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar = (f0.v) pVar;
                    if (vVar.z()) {
                        vVar.Q();
                    } else {
                        a1.a(this.f714f, this.f715g, this.f716h, this.f717i, this.f718j, this.f719k, this.f720l, this.f721m, this.f722n, pVar, 384);
                    }
                } else {
                    a1.a(this.f714f, this.f715g, this.f716h, this.f717i, this.f718j, this.f719k, this.f720l, this.f721m, this.f722n, pVar, 384);
                }
                break;
            default:
                ((Number) obj2).intValue();
                int iZ = f0.l.z(385);
                a1.a(this.f714f, this.f715g, this.f716h, this.f717i, this.f718j, this.f719k, this.f720l, this.f721m, this.f722n, (f0.p) obj, iZ);
                break;
        }
        return p3.n.f5026a;
    }
}

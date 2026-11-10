package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f4 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f857e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f858f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ long f859g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ long f860h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f861i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ a4.e f862j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ n0.d f863k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f864l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f4(int i5, r0.p pVar, long j5, long j6, a4.f fVar, a4.e eVar, n0.d dVar, int i6) {
        super(2);
        this.f864l = i5;
        this.f858f = pVar;
        this.f859g = j5;
        this.f860h = j6;
        this.f861i = fVar;
        this.f862j = eVar;
        this.f863k = dVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f4(n0.d dVar, a4.e eVar, a4.e eVar2, y1.h0 h0Var, long j5, long j6, int i5) {
        super(2);
        this.f863k = dVar;
        this.f862j = eVar;
        this.f858f = eVar2;
        this.f861i = h0Var;
        this.f859g = j5;
        this.f860h = j6;
        this.f864l = i5;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f4(r0.p pVar, long j5, long j6, a4.f fVar, a4.e eVar, n0.d dVar, int i5) {
        super(2);
        this.f858f = pVar;
        this.f859g = j5;
        this.f860h = j6;
        this.f861i = fVar;
        this.f862j = eVar;
        this.f863k = dVar;
        this.f864l = i5;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f857e) {
            case 0:
                ((Number) obj2).intValue();
                r0.p pVar = (r0.p) this.f858f;
                a4.f fVar = (a4.f) this.f861i;
                i4.a(this.f864l, pVar, this.f859g, this.f860h, fVar, this.f862j, this.f863k, (f0.p) obj, f0.l.z(1572865));
                break;
            case 1:
                ((Number) obj2).intValue();
                r0.p pVar2 = (r0.p) this.f858f;
                a4.f fVar2 = (a4.f) this.f861i;
                i4.b(pVar2, this.f859g, this.f860h, fVar2, this.f862j, this.f863k, (f0.p) obj, f0.l.z(this.f864l | 1));
                break;
            default:
                ((Number) obj2).intValue();
                a4.e eVar = (a4.e) this.f858f;
                y1.h0 h0Var = (y1.h0) this.f861i;
                j3.c(this.f863k, this.f862j, eVar, h0Var, this.f859g, this.f860h, (f0.p) obj, f0.l.z(this.f864l | 1));
                break;
        }
        return p3.n.f5026a;
    }
}

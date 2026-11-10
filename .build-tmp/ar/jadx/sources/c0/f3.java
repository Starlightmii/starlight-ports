package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f3 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ a4.e f851e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ n0.d f852f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ a4.e f853g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ y1.h0 f854h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ long f855i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ long f856j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f3(a4.e eVar, n0.d dVar, a4.e eVar2, y1.h0 h0Var, long j5, long j6) {
        super(2);
        this.f851e = eVar;
        this.f852f = dVar;
        this.f853g = eVar2;
        this.f854h = h0Var;
        this.f855i = j5;
        this.f856j = j6;
    }

    /* JADX WARN: Code duplicated, block: B:8:0x001b  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        f0.p pVar = (f0.p) obj;
        if ((((Number) obj2).intValue() & 3) == 2) {
            f0.v vVar = (f0.v) pVar;
            if (vVar.z()) {
                vVar.Q();
            } else {
                f0.v vVar2 = (f0.v) pVar;
                vVar2.V(-810701708);
                j3.c(this.f852f, this.f851e, this.f853g, this.f854h, this.f855i, this.f856j, vVar2, 0);
                vVar2.p(false);
            }
        } else {
            f0.v vVar3 = (f0.v) pVar;
            vVar3.V(-810701708);
            j3.c(this.f852f, this.f851e, this.f853g, this.f854h, this.f855i, this.f856j, vVar3, 0);
            vVar3.p(false);
        }
        return p3.n.f5026a;
    }
}

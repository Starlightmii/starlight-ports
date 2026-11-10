package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w1 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ boolean f1397e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ q.k f1398f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ k4 f1399g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ y0.q0 f1400h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w1(boolean z2, q.k kVar, k4 k4Var, y0.q0 q0Var) {
        super(2);
        this.f1397e = z2;
        this.f1398f = kVar;
        this.f1399g = k4Var;
        this.f1400h = q0Var;
    }

    /* JADX WARN: Code duplicated, block: B:8:0x001c  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        f0.p pVar = (f0.p) obj;
        if ((((Number) obj2).intValue() & 3) == 2) {
            f0.v vVar = (f0.v) pVar;
            if (vVar.z()) {
                vVar.Q();
            } else {
                v1.f1366a.a(this.f1397e, this.f1398f, null, this.f1399g, this.f1400h, 0.0f, 0.0f, pVar, 100663296, 200);
            }
        } else {
            v1.f1366a.a(this.f1397e, this.f1398f, null, this.f1399g, this.f1400h, 0.0f, 0.0f, pVar, 100663296, 200);
        }
        return p3.n.f5026a;
    }
}

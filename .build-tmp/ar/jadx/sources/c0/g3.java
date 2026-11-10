package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g3 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ a4.e f873e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ n0.d f874f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ a4.e f875g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ long f876h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ long f877i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g3(a4.e eVar, n0.d dVar, a4.e eVar2, long j5, long j6) {
        super(2);
        this.f873e = eVar;
        this.f874f = dVar;
        this.f875g = eVar2;
        this.f876h = j5;
        this.f877i = j6;
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
                y1.h0 h0VarA = t4.a(pVar, e0.n.f1783e);
                y1.h0 h0VarA2 = t4.a(pVar, 10);
                f0.l.a(n4.f1120a.a(h0VarA), n0.i.c(835891690, new f3(this.f873e, this.f874f, this.f875g, h0VarA2, this.f876h, this.f877i), pVar), pVar, 56);
            }
        } else {
            y1.h0 h0VarA3 = t4.a(pVar, e0.n.f1783e);
            y1.h0 h0VarA4 = t4.a(pVar, 10);
            f0.l.a(n4.f1120a.a(h0VarA3), n0.i.c(835891690, new f3(this.f873e, this.f874f, this.f875g, h0VarA4, this.f876h, this.f877i), pVar), pVar, 56);
        }
        return p3.n.f5026a;
    }
}

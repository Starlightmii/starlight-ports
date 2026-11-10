package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1037e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ long f1038f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f1039g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f1040h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m(long j5, Object obj, p3.c cVar, int i5) {
        super(2);
        this.f1037e = i5;
        this.f1038f = j5;
        this.f1039g = obj;
        this.f1040h = cVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(z.l lVar, r0.p pVar, long j5, int i5) {
        super(2);
        this.f1037e = 2;
        this.f1039g = lVar;
        this.f1040h = pVar;
        this.f1038f = j5;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0040  */
    /* JADX WARN: Code duplicated, block: B:21:0x006f  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f1037e) {
            case 0:
                f0.p pVar = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar = (f0.v) pVar;
                    if (vVar.z()) {
                        vVar.Q();
                    } else {
                        d0.c.a(this.f1038f, ((s4) ((f0.v) pVar).j(t4.f1328a)).f1292m, n0.i.c(1327513942, new l((r.l0) this.f1039g, 0, (a4.f) this.f1040h), pVar), pVar, 384);
                    }
                } else {
                    d0.c.a(this.f1038f, ((s4) ((f0.v) pVar).j(t4.f1328a)).f1292m, n0.i.c(1327513942, new l((r.l0) this.f1039g, 0, (a4.f) this.f1040h), pVar), pVar, 384);
                }
                break;
            case 1:
                f0.p pVar2 = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar2 = (f0.v) pVar2;
                    if (vVar2.z()) {
                        vVar2.Q();
                    } else {
                        d0.u.b(this.f1038f, (y1.h0) this.f1039g, (a4.e) this.f1040h, pVar2, 0);
                    }
                } else {
                    d0.u.b(this.f1038f, (y1.h0) this.f1039g, (a4.e) this.f1040h, pVar2, 0);
                }
                break;
            default:
                ((Number) obj2).intValue();
                z.l lVar = (z.l) this.f1039g;
                r0.p pVar3 = (r0.p) this.f1040h;
                v.d.a(lVar, pVar3, this.f1038f, (f0.p) obj, f0.l.z(1));
                break;
        }
        return p3.n.f5026a;
    }
}

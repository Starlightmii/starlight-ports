package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w2 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1401e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f1402f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f1403g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f1404h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ w2(int i5, int i6, Object obj, Object obj2) {
        super(2);
        this.f1401e = i6;
        this.f1403g = obj;
        this.f1404h = obj2;
        this.f1402f = i5;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w2(z2 z2Var, r0.p pVar, int i5) {
        super(2);
        this.f1401e = 0;
        n0.d dVar = z.f1462a;
        this.f1403g = z2Var;
        this.f1404h = pVar;
        this.f1402f = i5;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        int i5 = this.f1401e;
        p3.n nVar = p3.n.f5026a;
        int i6 = this.f1402f;
        Object obj3 = this.f1404h;
        Object obj4 = this.f1403g;
        f0.p pVar = (f0.p) obj;
        ((Number) obj2).intValue();
        switch (i5) {
            case 0:
                n0.d dVar = z.f1462a;
                h2.c((z2) obj4, (r0.p) obj3, pVar, f0.l.z(i6 | 1));
                break;
            case 1:
                n4.a((y1.h0) obj4, (a4.e) obj3, pVar, f0.l.z(i6 | 1));
                break;
            case 2:
                ((m.s0) obj4).a(obj3, pVar, f0.l.z(i6 | 1));
                break;
            case 3:
                d4.a.b((d1.b) obj4, (r0.p) obj3, pVar, f0.l.z(i6 | 1));
                break;
            default:
                v.l0.b((z.e0) obj4, (n0.d) obj3, pVar, f0.l.z(i6 | 1));
                break;
        }
        return nVar;
    }
}

package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m1 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ e1 f1046e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f1047f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ boolean f1048g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ a4.e f1049h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m1(e1 e1Var, boolean z2, boolean z3, a4.e eVar) {
        super(2);
        this.f1046e = e1Var;
        this.f1047f = z2;
        this.f1048g = z3;
        this.f1049h = eVar;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0028  */
    /* JADX WARN: Code duplicated, block: B:11:0x002b  */
    /* JADX WARN: Code duplicated, block: B:13:0x002f  */
    /* JADX WARN: Code duplicated, block: B:14:0x0032  */
    /* JADX WARN: Code duplicated, block: B:8:0x001c  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        boolean z2;
        e1 e1Var;
        long j5;
        f0.p pVar = (f0.p) obj;
        if ((((Number) obj2).intValue() & 3) == 2) {
            f0.v vVar = (f0.v) pVar;
            if (vVar.z()) {
                vVar.Q();
            } else {
                y1.h0 h0VarA = t4.a(pVar, e0.j.f1742g);
                z2 = this.f1048g;
                e1Var = this.f1046e;
                if (!z2) {
                    j5 = e1Var.f821g;
                } else if (this.f1047f) {
                    j5 = e1Var.f816b;
                } else {
                    j5 = e1Var.f819e;
                }
                d0.c.a(((y0.u) l.g.a(j5, m.d.h(100, 6, null), pVar).getValue()).f6696a, h0VarA, this.f1049h, pVar, 0);
            }
        } else {
            y1.h0 h0VarA2 = t4.a(pVar, e0.j.f1742g);
            z2 = this.f1048g;
            e1Var = this.f1046e;
            if (!z2) {
                j5 = e1Var.f821g;
            } else if (this.f1047f) {
                j5 = e1Var.f816b;
            } else {
                j5 = e1Var.f819e;
            }
            d0.c.a(((y0.u) l.g.a(j5, m.d.h(100, 6, null), pVar).getValue()).f6696a, h0VarA2, this.f1049h, pVar, 0);
        }
        return p3.n.f5026a;
    }
}

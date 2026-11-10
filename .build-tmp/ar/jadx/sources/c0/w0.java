package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w0 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1391e = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f1392f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f1393g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f1394h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f1395i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ Object f1396j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w0(v.o0 o0Var, w0.n nVar, boolean z2, z.e0 e0Var, e2.r rVar) {
        super(1);
        this.f1393g = o0Var;
        this.f1394h = nVar;
        this.f1392f = z2;
        this.f1395i = e0Var;
        this.f1396j = rVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w0(boolean z2, m.c0 c0Var, f0.i1 i1Var, m.q0 q0Var, m.q0 q0Var2) {
        super(1);
        this.f1392f = z2;
        this.f1393g = c0Var;
        this.f1394h = i1Var;
        this.f1395i = q0Var;
        this.f1396j = q0Var2;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        float fFloatValue;
        switch (this.f1391e) {
            case 0:
                y0.n0 n0Var = (y0.n0) obj;
                f0.g3 g3Var = (f0.g3) this.f1395i;
                f0.s1 s1Var = ((m.c0) this.f1393g).f3641c;
                float fFloatValue2 = 0.8f;
                float fFloatValue3 = 1.0f;
                boolean z2 = this.f1392f;
                if (z2) {
                    fFloatValue = ((Boolean) s1Var.getValue()).booleanValue() ? 1.0f : 0.8f;
                } else {
                    fFloatValue = ((Number) g3Var.getValue()).floatValue();
                }
                n0Var.e(fFloatValue);
                if (!z2) {
                    fFloatValue2 = ((Number) g3Var.getValue()).floatValue();
                } else if (((Boolean) s1Var.getValue()).booleanValue()) {
                    fFloatValue2 = 1.0f;
                }
                n0Var.g(fFloatValue2);
                if (!z2) {
                    fFloatValue3 = ((Number) ((f0.g3) this.f1396j).getValue()).floatValue();
                } else if (!((Boolean) s1Var.getValue()).booleanValue()) {
                    fFloatValue3 = 0.0f;
                }
                n0Var.c(fFloatValue3);
                n0Var.m(((y0.t0) ((f0.i1) this.f1394h).getValue()).f6688a);
                break;
            default:
                long j5 = ((x0.c) obj).f6532a;
                v.o0 o0Var = (v.o0) this.f1393g;
                w0.n nVar = (w0.n) this.f1394h;
                if (o0Var.b()) {
                    q1.k2 k2Var = o0Var.f6008c;
                    if (k2Var != null) {
                        ((q1.k1) k2Var).b();
                    }
                } else {
                    nVar.a(w0.h.f6239i);
                }
                if (o0Var.b() && this.f1392f) {
                    if (o0Var.a() != v.f0.f5918e) {
                        v.e1 e1VarD = o0Var.d();
                        if (e1VarD != null) {
                            e2.r rVar = (e2.r) this.f1396j;
                            a2.h hVar = o0Var.f6009d;
                            v.q qVar = o0Var.f6025t;
                            int iA = rVar.a(e1VarD.b(j5, true));
                            qVar.c(e2.x.a((e2.x) hVar.f49e, null, p3.j.g(iA, iA), 5));
                            if (o0Var.f6006a.f6098a.f6759d.length() > 0) {
                                o0Var.f6016k.setValue(v.f0.f5919f);
                            }
                        }
                    } else {
                        ((z.e0) this.f1395i).e(new x0.c(j5));
                    }
                }
                break;
        }
        return p3.n.f5026a;
    }
}

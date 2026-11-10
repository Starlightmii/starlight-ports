package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p4 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1177e = 2;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ float f1178f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f1179g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f1180h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p4(float f5, y0.c0 c0Var, y0.m mVar) {
        super(1);
        this.f1178f = f5;
        this.f1179g = c0Var;
        this.f1180h = mVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p4(n1.m0 m0Var, r4 r4Var, float f5) {
        super(1);
        this.f1179g = m0Var;
        this.f1180h = r4Var;
        this.f1178f = f5;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p4(p.v1 v1Var, float f5, a4.c cVar) {
        super(1);
        this.f1179g = v1Var;
        this.f1178f = f5;
        this.f1180h = cVar;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        long jRound;
        switch (this.f1177e) {
            case 0:
                n1.l0 l0Var = (n1.l0) obj;
                n1.m0 m0Var = (n1.m0) this.f1179g;
                m.c cVar = ((r4) this.f1180h).f1240t;
                n1.l0.f(l0Var, m0Var, (int) (cVar != null ? ((Number) cVar.c()).floatValue() : this.f1178f), 0);
                return p3.n.f5026a;
            case 1:
                long jLongValue = ((Number) obj).longValue();
                p.v1 v1Var = (p.v1) this.f1179g;
                if (v1Var.f4620b == Long.MIN_VALUE) {
                    v1Var.f4620b = jLongValue;
                }
                float f5 = v1Var.f4623e;
                m.m mVar = new m.m(f5);
                float f6 = this.f1178f;
                m.m mVar2 = p.v1.f4618f;
                if (f6 == 0.0f) {
                    jRound = v1Var.f4619a.b(new m.m(f5), mVar2, v1Var.f4621c);
                } else {
                    double d5 = (jLongValue - v1Var.f4620b) / f6;
                    if (Double.isNaN(d5)) {
                        throw new IllegalArgumentException("Cannot round NaN value.");
                    }
                    jRound = Math.round(d5);
                }
                long j5 = jRound;
                float f7 = ((m.m) v1Var.f4619a.f(j5, mVar, mVar2, v1Var.f4621c)).f3703a;
                v1Var.f4621c = (m.m) v1Var.f4619a.d(j5, mVar, mVar2, v1Var.f4621c);
                v1Var.f4620b = jLongValue;
                float f8 = v1Var.f4623e - f7;
                v1Var.f4623e = f7;
                ((a4.c) this.f1180h).c(Float.valueOf(f8));
                return p3.n.f5026a;
            default:
                p1.f0 f0Var = (p1.f0) obj;
                f0Var.c();
                float f9 = this.f1178f;
                y0.c0 c0Var = (y0.c0) this.f1179g;
                y0.m mVar3 = (y0.m) this.f1180h;
                a1.c cVar2 = f0Var.f4828d;
                a1.b bVar = cVar2.f27e;
                long jT = bVar.t();
                bVar.r().p();
                try {
                    a1.d dVar = (a1.d) bVar.f23d;
                    dVar.x(f9, 0.0f);
                    y0.r rVarR = ((a1.b) dVar.f31e).r();
                    rVarR.k(x0.c.d(0L), x0.c.e(0L));
                    rVarR.l();
                    rVarR.k(-x0.c.d(0L), -x0.c.e(0L));
                    cVar2.e(c0Var, mVar3);
                    return p3.n.f5026a;
                } finally {
                    bVar.r().m();
                    bVar.D(jT);
                }
        }
    }
}

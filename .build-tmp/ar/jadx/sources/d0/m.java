package d0;

import f0.i1;
import f0.q1;
import f0.s1;
import f0.z2;
import m.s0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1546e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ float f1547f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f1548g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(float f5, i1 i1Var) {
        super(1);
        this.f1547f = f5;
        this.f1548g = i1Var;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(s0 s0Var, float f5) {
        super(1);
        this.f1548g = s0Var;
        this.f1547f = f5;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f1546e) {
            case 0:
                long j5 = ((x0.f) obj).f6546a;
                float fD = x0.f.d(j5);
                float f5 = this.f1547f;
                float f6 = fD * f5;
                float fB = x0.f.b(j5) * f5;
                i1 i1Var = (i1) this.f1548g;
                if (x0.f.d(((x0.f) i1Var.getValue()).f6546a) != f6 || x0.f.b(((x0.f) i1Var.getValue()).f6546a) != fB) {
                    i1Var.setValue(new x0.f(p3.j.f(f6, fB)));
                }
                return p3.n.f5026a;
            default:
                long jLongValue = ((Number) obj).longValue();
                s0 s0Var = (s0) this.f1548g;
                s1 s1Var = s0Var.f3759i;
                q1 q1Var = s0Var.f3755e;
                if (!((Boolean) s1Var.getValue()).booleanValue()) {
                    if (((z2) p0.m.t(q1Var.f2294e, q1Var)).f2442c == Long.MIN_VALUE) {
                        q1Var.g(jLongValue);
                        s0Var.f3751a.f3639a.setValue(Boolean.TRUE);
                    }
                    long jRound = jLongValue - ((z2) p0.m.t(q1Var.f2294e, q1Var)).f2442c;
                    float f7 = this.f1547f;
                    if (f7 != 0.0f) {
                        double d5 = jRound / ((double) f7);
                        if (Double.isNaN(d5)) {
                            throw new IllegalArgumentException("Cannot round NaN value.");
                        }
                        jRound = Math.round(d5);
                    }
                    s0Var.f3754d.g(jRound);
                    s0Var.f(jRound, f7 == 0.0f);
                }
                return p3.n.f5026a;
        }
    }
}

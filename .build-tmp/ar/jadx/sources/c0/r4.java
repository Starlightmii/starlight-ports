package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r4 extends r0.o implements p1.w {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public q.j f1237q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1238r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1239s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public m.c f1240t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public m.c f1241u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public float f1242v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f1243w;

    @Override // p1.w
    public final n1.g0 c(n1.h0 h0Var, n1.e0 e0Var, long j5) {
        float f5;
        boolean z2 = (e0Var.d(k2.a.i(j5)) == 0 || e0Var.c0(k2.a.h(j5)) == 0) ? false : true;
        if (this.f1239s) {
            f5 = e0.o.f1786a;
        } else {
            f5 = (z2 || this.f1238r) ? androidx.compose.material3.a.f152a : androidx.compose.material3.a.f153b;
        }
        float fV = h0Var.v(f5);
        m.c cVar = this.f1241u;
        int iFloatValue = (int) (cVar != null ? ((Number) cVar.c()).floatValue() : fV);
        t3.c cVar2 = null;
        if (iFloatValue < 0 || iFloatValue < 0) {
            d4.a.J("width(" + iFloatValue + ") and height(" + iFloatValue + ") must be >= 0");
            throw null;
        }
        n1.m0 m0VarC = e0Var.c(h1.c.r(iFloatValue, iFloatValue, iFloatValue, iFloatValue));
        float fV2 = h0Var.v((androidx.compose.material3.a.f155d - h0Var.j0(fV)) / 2.0f);
        float fV3 = h0Var.v((androidx.compose.material3.a.f154c - androidx.compose.material3.a.f152a) - androidx.compose.material3.a.f156e);
        boolean z3 = this.f1239s;
        if (z3 && this.f1238r) {
            fV2 = fV3 - h0Var.v(e0.o.f1790e);
        } else if (z3 && !this.f1238r) {
            fV2 = h0Var.v(e0.o.f1790e);
        } else if (this.f1238r) {
            fV2 = fV3;
        }
        m.c cVar3 = this.f1241u;
        Float f6 = cVar3 != null ? (Float) cVar3.f3633e.getValue() : null;
        if (f6 == null || f6.floatValue() != fV) {
            i4.y.n(n0(), null, new o4(this, fV, cVar2, 0), 3);
        }
        m.c cVar4 = this.f1240t;
        Float f7 = cVar4 != null ? (Float) cVar4.f3633e.getValue() : null;
        if (f7 == null || f7.floatValue() != fV2) {
            i4.y.n(n0(), null, new o4(this, fV2, cVar2, 1), 3);
        }
        if (Float.isNaN(this.f1243w) && Float.isNaN(this.f1242v)) {
            this.f1243w = fV;
            this.f1242v = fV2;
        }
        return h0Var.F(iFloatValue, iFloatValue, q3.t.f5478d, new p4(m0VarC, this, fV2));
    }

    @Override // r0.o
    public final boolean o0() {
        return false;
    }

    @Override // r0.o
    public final void r0() {
        i4.y.n(n0(), null, new q4(this, null, 0), 3);
    }
}

package z;

import v.e1;
import v.o0;
import v.u0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d0 implements u0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ e0 f6922a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f6923b;

    public d0(e0 e0Var, boolean z2) {
        this.f6922a = e0Var;
        this.f6923b = z2;
    }

    @Override // v.u0
    public final void a() {
        e0 e0Var = this.f6922a;
        e0Var.f6941o.setValue(null);
        e0Var.f6942p.setValue(null);
        e0Var.p(true);
    }

    @Override // v.u0
    public final void b() {
        e0 e0Var = this.f6922a;
        e0Var.f6941o.setValue(null);
        e0Var.f6942p.setValue(null);
        e0Var.p(true);
    }

    @Override // v.u0
    public final void c(long j5) {
    }

    @Override // v.u0
    public final void d() {
        e1 e1VarD;
        boolean z2 = this.f6923b;
        v.e0 e0Var = z2 ? v.e0.f5896e : v.e0.f5897f;
        e0 e0Var2 = this.f6922a;
        e0Var2.f6941o.setValue(e0Var);
        long jI = e0Var2.i(z2);
        float f5 = v.f7015a;
        long jB = p3.j.b(x0.c.d(jI), x0.c.e(jI) - 1.0f);
        o0 o0Var = e0Var2.f6930d;
        if (o0Var == null || (e1VarD = o0Var.d()) == null) {
            return;
        }
        long jE = e1VarD.e(jB);
        e0Var2.f6938l = jE;
        e0Var2.f6942p.setValue(new x0.c(jE));
        e0Var2.f6940n = 0L;
        e0Var2.f6943q = -1;
        o0 o0Var2 = e0Var2.f6930d;
        if (o0Var2 != null) {
            o0Var2.f6022q.setValue(Boolean.TRUE);
        }
        e0Var2.p(false);
    }

    @Override // v.u0
    public final void e(long j5) {
        e0 e0Var = this.f6922a;
        long jH = x0.c.h(e0Var.f6940n, j5);
        e0Var.f6940n = jH;
        e0Var.f6942p.setValue(new x0.c(x0.c.h(e0Var.f6938l, jH)));
        e2.x xVarJ = e0Var.j();
        x0.c cVarG = e0Var.g();
        b4.l.b(cVarG);
        e0.a(e0Var, xVarJ, cVarG.f6532a, false, this.f6923b, o.f6990g, true);
        e0Var.p(false);
    }

    @Override // v.u0
    public final void onCancel() {
    }
}

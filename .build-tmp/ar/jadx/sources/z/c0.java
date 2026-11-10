package z;

import f0.s1;
import v.e1;
import v.o0;
import v.u0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c0 implements u0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6913a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ e0 f6914b;

    public /* synthetic */ c0(e0 e0Var, int i5) {
        this.f6913a = i5;
        this.f6914b = e0Var;
    }

    private final void f() {
    }

    private final void g() {
    }

    private final void h() {
    }

    private final void j() {
    }

    @Override // v.u0
    public final void a() {
        switch (this.f6913a) {
            case 0:
                e0 e0Var = this.f6914b;
                e0Var.f6941o.setValue(null);
                e0Var.f6942p.setValue(null);
                break;
            default:
                i();
                break;
        }
    }

    @Override // v.u0
    public final void b() {
        switch (this.f6913a) {
            case 0:
                e0 e0Var = this.f6914b;
                e0Var.f6941o.setValue(null);
                e0Var.f6942p.setValue(null);
                break;
        }
    }

    @Override // v.u0
    public final void c(long j5) {
        e1 e1VarD;
        long j6;
        e1 e1VarD2;
        e1 e1VarD3;
        switch (this.f6913a) {
            case 0:
                e0 e0Var = this.f6914b;
                long jI = e0Var.i(true);
                float f5 = v.f7015a;
                long jB = p3.j.b(x0.c.d(jI), x0.c.e(jI) - 1.0f);
                o0 o0Var = e0Var.f6930d;
                if (o0Var != null && (e1VarD = o0Var.d()) != null) {
                    long jE = e1VarD.e(jB);
                    e0Var.f6938l = jE;
                    e0Var.f6942p.setValue(new x0.c(jE));
                    e0Var.f6940n = 0L;
                    e0Var.f6941o.setValue(v.e0.f5895d);
                    e0Var.p(false);
                    break;
                }
                break;
            default:
                e0 e0Var2 = this.f6914b;
                s1 s1Var = e0Var2.f6941o;
                if (e0Var2.h() && ((v.e0) s1Var.getValue()) == null) {
                    s1Var.setValue(v.e0.f5897f);
                    e0Var2.f6943q = -1;
                    e0Var2.k();
                    o0 o0Var2 = e0Var2.f6930d;
                    if (o0Var2 == null || (e1VarD3 = o0Var2.d()) == null || !e1VarD3.c(j5)) {
                        j6 = j5;
                        o0 o0Var3 = e0Var2.f6930d;
                        if (o0Var3 != null && (e1VarD2 = o0Var3.d()) != null) {
                            int iA = e0Var2.f6928b.a(e1VarD2.b(j6, true));
                            e2.x xVarC = e0.c(e0Var2.j().f2062a, p3.j.g(iA, iA));
                            e0Var2.f(false);
                            f1.a aVar = e0Var2.f6934h;
                            if (aVar != null) {
                                ((f1.b) aVar).a();
                            }
                            e0Var2.f6929c.c(xVarC);
                        }
                    } else if (e0Var2.j().f2062a.f6759d.length() != 0) {
                        e0Var2.f(false);
                        j6 = j5;
                        e0Var2.f6939m = Integer.valueOf((int) (e0.a(e0Var2, e2.x.a(e0Var2.j(), null, y1.g0.f6768b, 5), j6, true, false, o.f6988e, true) >> 32));
                    }
                    e0Var2.n(v.f0.f5917d);
                    e0Var2.f6938l = j6;
                    e0Var2.f6942p.setValue(new x0.c(j6));
                    e0Var2.f6940n = 0L;
                    break;
                }
                break;
        }
    }

    @Override // v.u0
    public final void d() {
        int i5 = this.f6913a;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0099  */
    /* JADX WARN: Code duplicated, block: B:23:0x009d  */
    /* JADX WARN: Code duplicated, block: B:24:0x00a2  */
    @Override // v.u0
    public final void e(long j5) {
        e1 e1VarD;
        f1.a aVar;
        e1 e1VarD2;
        Integer num;
        int iB;
        switch (this.f6913a) {
            case 0:
                e0 e0Var = this.f6914b;
                e0Var.f6940n = x0.c.h(e0Var.f6940n, j5);
                o0 o0Var = e0Var.f6930d;
                if (o0Var != null && (e1VarD = o0Var.d()) != null) {
                    e0Var.f6942p.setValue(new x0.c(x0.c.h(e0Var.f6938l, e0Var.f6940n)));
                    e2.r rVar = e0Var.f6928b;
                    x0.c cVarG = e0Var.g();
                    b4.l.b(cVarG);
                    int iA = rVar.a(e1VarD.b(cVarG.f6532a, true));
                    long jG = p3.j.g(iA, iA);
                    if (!y1.g0.a(jG, e0Var.j().f2063b)) {
                        o0 o0Var2 = e0Var.f6930d;
                        if ((o0Var2 == null || ((Boolean) o0Var2.f6022q.getValue()).booleanValue()) && (aVar = e0Var.f6934h) != null) {
                            ((f1.b) aVar).a();
                        }
                        e0Var.f6929c.c(e0.c(e0Var.j().f2062a, jG));
                        break;
                    }
                }
                break;
            default:
                e2.f0 f0Var = o.f6988e;
                e0 e0Var2 = this.f6914b;
                if (e0Var2.h() && e0Var2.j().f2062a.f6759d.length() != 0) {
                    e0Var2.f6940n = x0.c.h(e0Var2.f6940n, j5);
                    o0 o0Var3 = e0Var2.f6930d;
                    if (o0Var3 != null && (e1VarD2 = o0Var3.d()) != null) {
                        e0Var2.f6942p.setValue(new x0.c(x0.c.h(e0Var2.f6938l, e0Var2.f6940n)));
                        if (e0Var2.f6939m == null) {
                            x0.c cVarG2 = e0Var2.g();
                            b4.l.b(cVarG2);
                            if (e1VarD2.c(cVarG2.f6532a)) {
                                num = e0Var2.f6939m;
                                if (num != null) {
                                    iB = num.intValue();
                                } else {
                                    iB = e1VarD2.b(e0Var2.f6938l, false);
                                }
                                x0.c cVarG3 = e0Var2.g();
                                b4.l.b(cVarG3);
                                int iB2 = e1VarD2.b(cVarG3.f6532a, false);
                                if (e0Var2.f6939m == null || iB != iB2) {
                                    e2.x xVarJ = e0Var2.j();
                                    x0.c cVarG4 = e0Var2.g();
                                    b4.l.b(cVarG4);
                                    e0.a(e0Var2, xVarJ, cVarG4.f6532a, false, false, f0Var, true);
                                }
                            } else {
                                int iA2 = e0Var2.f6928b.a(e1VarD2.b(e0Var2.f6938l, true));
                                e2.r rVar2 = e0Var2.f6928b;
                                x0.c cVarG5 = e0Var2.g();
                                b4.l.b(cVarG5);
                                if (iA2 == rVar2.a(e1VarD2.b(cVarG5.f6532a, true))) {
                                    f0Var = o.f6987d;
                                }
                                e2.x xVarJ2 = e0Var2.j();
                                x0.c cVarG6 = e0Var2.g();
                                b4.l.b(cVarG6);
                                e0.a(e0Var2, xVarJ2, cVarG6.f6532a, false, false, f0Var, true);
                            }
                        } else {
                            num = e0Var2.f6939m;
                            if (num != null) {
                                iB = num.intValue();
                            } else {
                                iB = e1VarD2.b(e0Var2.f6938l, false);
                            }
                            x0.c cVarG7 = e0Var2.g();
                            b4.l.b(cVarG7);
                            int iB3 = e1VarD2.b(cVarG7.f6532a, false);
                            if (e0Var2.f6939m == null) {
                            }
                            e2.x xVarJ3 = e0Var2.j();
                            x0.c cVarG8 = e0Var2.g();
                            b4.l.b(cVarG8);
                            e0.a(e0Var2, xVarJ3, cVarG8.f6532a, false, false, f0Var, true);
                        }
                        int i5 = y1.g0.f6769c;
                    }
                    e0Var2.p(false);
                    break;
                }
                break;
        }
    }

    public void i() {
        e0 e0Var = this.f6914b;
        e0Var.f6941o.setValue(null);
        e0Var.f6942p.setValue(null);
        e0Var.p(true);
        e0Var.f6939m = null;
        boolean zB = y1.g0.b(e0Var.j().f2063b);
        e0Var.n(zB ? v.f0.f5919f : v.f0.f5918e);
        o0 o0Var = e0Var.f6930d;
        if (o0Var != null) {
            o0Var.f6018m.setValue(Boolean.valueOf(!zB && p3.p.v(e0Var, true)));
        }
        o0 o0Var2 = e0Var.f6930d;
        if (o0Var2 != null) {
            o0Var2.f6019n.setValue(Boolean.valueOf(!zB && p3.p.v(e0Var, false)));
        }
        o0 o0Var3 = e0Var.f6930d;
        if (o0Var3 == null) {
            return;
        }
        o0Var3.f6020o.setValue(Boolean.valueOf(zB && p3.p.v(e0Var, true)));
    }

    @Override // v.u0
    public final void onCancel() {
        switch (this.f6913a) {
            case 0:
                break;
            default:
                i();
                break;
        }
    }
}

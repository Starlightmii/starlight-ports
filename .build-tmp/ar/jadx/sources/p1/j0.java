package p1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d0 f4893a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f4894b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f4896d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4897e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4898f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4899g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4900h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4901i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4902j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f4903k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f4904l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f4905m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f4906n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f4907o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f4908p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f4909q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public h0 f4911s;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4895c = 5;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final i0 f4910r = new i0(this);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f4912t = h1.c.c(0, 0, 15);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final b0.a f4913u = new b0.a(18, this);

    public j0(d0 d0Var) {
        this.f4893a = d0Var;
    }

    public final y0 a() {
        return this.f4893a.f4801y.f4970c;
    }

    public final void b(int i5) {
        int i6 = this.f4906n;
        this.f4906n = i5;
        if ((i6 == 0) != (i5 == 0)) {
            d0 d0VarR = this.f4893a.r();
            j0 j0Var = d0VarR != null ? d0VarR.f4802z : null;
            if (j0Var != null) {
                if (i5 == 0) {
                    j0Var.b(j0Var.f4906n - 1);
                } else {
                    j0Var.b(j0Var.f4906n + 1);
                }
            }
        }
    }

    public final void c(int i5) {
        int i6 = this.f4909q;
        this.f4909q = i5;
        if ((i6 == 0) != (i5 == 0)) {
            d0 d0VarR = this.f4893a.r();
            j0 j0Var = d0VarR != null ? d0VarR.f4802z : null;
            if (j0Var != null) {
                if (i5 == 0) {
                    j0Var.c(j0Var.f4909q - 1);
                } else {
                    j0Var.c(j0Var.f4909q + 1);
                }
            }
        }
    }

    public final void d(boolean z2) {
        if (this.f4905m != z2) {
            this.f4905m = z2;
            if (z2 && !this.f4904l) {
                b(this.f4906n + 1);
            } else {
                if (z2 || this.f4904l) {
                    return;
                }
                b(this.f4906n - 1);
            }
        }
    }

    public final void e(boolean z2) {
        if (this.f4904l != z2) {
            this.f4904l = z2;
            if (z2 && !this.f4905m) {
                b(this.f4906n + 1);
            } else {
                if (z2 || this.f4905m) {
                    return;
                }
                b(this.f4906n - 1);
            }
        }
    }

    public final void f(boolean z2) {
        if (this.f4908p != z2) {
            this.f4908p = z2;
            if (z2 && !this.f4907o) {
                c(this.f4909q + 1);
            } else {
                if (z2 || this.f4907o) {
                    return;
                }
                c(this.f4909q - 1);
            }
        }
    }

    public final void g(boolean z2) {
        if (this.f4907o != z2) {
            this.f4907o = z2;
            if (z2 && !this.f4908p) {
                c(this.f4909q + 1);
            } else {
                if (z2 || this.f4908p) {
                    return;
                }
                c(this.f4909q - 1);
            }
        }
    }

    public final void h() {
        i0 i0Var = this.f4910r;
        j0 j0Var = i0Var.I;
        Object obj = i0Var.f4883t;
        d0 d0Var = this.f4893a;
        if ((obj != null || j0Var.a().h() != null) && i0Var.f4882s) {
            i0Var.f4882s = false;
            i0Var.f4883t = j0Var.a().h();
            d0 d0VarR = d0Var.r();
            if (d0VarR != null) {
                d0.Q(d0VarR, false, 7);
            }
        }
        h0 h0Var = this.f4911s;
        if (h0Var != null) {
            j0 j0Var2 = h0Var.A;
            if (h0Var.f4864y == null) {
                n0 n0VarJ0 = j0Var2.a().J0();
                b4.l.b(n0VarJ0);
                if (n0VarJ0.f4932o.h() == null) {
                    return;
                }
            }
            if (h0Var.f4863x) {
                h0Var.f4863x = false;
                n0 n0VarJ1 = j0Var2.a().J0();
                b4.l.b(n0VarJ1);
                h0Var.f4864y = n0VarJ1.f4932o.h();
                if (f.o(d0Var)) {
                    d0 d0VarR2 = d0Var.r();
                    if (d0VarR2 != null) {
                        d0.Q(d0VarR2, false, 7);
                        return;
                    }
                    return;
                }
                d0 d0VarR3 = d0Var.r();
                if (d0VarR3 != null) {
                    d0.O(d0VarR3, false, 7);
                }
            }
        }
    }
}

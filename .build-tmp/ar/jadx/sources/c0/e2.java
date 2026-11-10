package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e2 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f822e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f823f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f824g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f825h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f826i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f827j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f828k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f829l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f830m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f831n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ n1.m0 f832o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ f2 f833p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ n1.h0 f834q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e2(int i5, int i6, n1.m0 m0Var, n1.m0 m0Var2, n1.m0 m0Var3, n1.m0 m0Var4, n1.m0 m0Var5, n1.m0 m0Var6, n1.m0 m0Var7, n1.m0 m0Var8, n1.m0 m0Var9, f2 f2Var, n1.h0 h0Var) {
        super(1);
        this.f822e = i5;
        this.f823f = i6;
        this.f824g = m0Var;
        this.f825h = m0Var2;
        this.f826i = m0Var3;
        this.f827j = m0Var4;
        this.f828k = m0Var5;
        this.f829l = m0Var6;
        this.f830m = m0Var7;
        this.f831n = m0Var8;
        this.f832o = m0Var9;
        this.f833p = f2Var;
        this.f834q = h0Var;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        int iRound;
        float f5;
        n1.l0 l0Var = (n1.l0) obj;
        f2 f2Var = this.f833p;
        float f6 = f2Var.f849c;
        boolean z2 = f2Var.f848b;
        n1.h0 h0Var = this.f834q;
        float fA = h0Var.a();
        k2.k layoutDirection = h0Var.getLayoutDirection();
        r.l0 l0Var2 = f2Var.f850d;
        float f7 = d2.f798a;
        n1.l0.e(l0Var, this.f831n, 0L);
        float f8 = d0.u.f1580b;
        n1.m0 m0Var = this.f832o;
        int i5 = this.f822e - (m0Var != null ? m0Var.f4131e : 0);
        int iH = d4.a.H(l0Var2.b() * fA);
        int iH2 = d4.a.H((layoutDirection == k2.k.f3416d ? l0Var2.c(layoutDirection) : l0Var2.d(layoutDirection)) * fA);
        float f9 = d0.u.f1581c * fA;
        n1.m0 m0Var2 = this.f824g;
        float f10 = 2.0f;
        if (m0Var2 != null) {
            n1.l0.f(l0Var, m0Var2, 0, Math.round((1 + 0.0f) * ((i5 - m0Var2.f4131e) / 2.0f)));
        }
        n1.m0 m0Var3 = this.f829l;
        if (m0Var3 != null) {
            if (z2) {
                iRound = Math.round((1 + 0.0f) * ((i5 - m0Var3.f4131e) / 2.0f));
            } else {
                iRound = iH;
            }
            int iE = a.a.E(f6, iRound, -(m0Var3.f4131e / 2));
            if (m0Var2 == null) {
                f5 = 0.0f;
            } else {
                f5 = (1 - f6) * (m0Var2.f4130d - f9);
            }
            n1.l0.f(l0Var, m0Var3, d4.a.H(f5) + iH2, iE);
        } else {
            f10 = 2.0f;
        }
        n1.m0 m0Var4 = this.f826i;
        if (m0Var4 != null) {
            n1.l0.f(l0Var, m0Var4, m0Var2 != null ? m0Var2.f4130d : 0, d2.e(z2, i5, iH, m0Var3, m0Var4));
        }
        int i6 = (m0Var2 != null ? m0Var2.f4130d : 0) + (m0Var4 != null ? m0Var4.f4130d : 0);
        n1.m0 m0Var5 = this.f828k;
        n1.l0.f(l0Var, m0Var5, i6, d2.e(z2, i5, iH, m0Var3, m0Var5));
        n1.m0 m0Var6 = this.f830m;
        if (m0Var6 != null) {
            n1.l0.f(l0Var, m0Var6, i6, d2.e(z2, i5, iH, m0Var3, m0Var6));
        }
        int i7 = this.f823f;
        n1.m0 m0Var7 = this.f825h;
        n1.m0 m0Var8 = this.f827j;
        if (m0Var8 != null) {
            n1.l0.f(l0Var, m0Var8, (i7 - (m0Var7 != null ? m0Var7.f4130d : 0)) - m0Var8.f4130d, d2.e(z2, i5, iH, m0Var3, m0Var8));
        }
        if (m0Var7 != null) {
            n1.l0.f(l0Var, m0Var7, i7 - m0Var7.f4130d, Math.round((1 + 0.0f) * ((i5 - m0Var7.f4131e) / f10)));
        }
        if (m0Var != null) {
            n1.l0.f(l0Var, m0Var, 0, i5);
        }
        return p3.n.f5026a;
    }
}

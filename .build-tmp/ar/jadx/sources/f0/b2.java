package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public e0 f2095a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2096b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a f2097c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a4.e f2098d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2099e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public j.x f2100f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public j.z f2101g;

    public b2(e0 e0Var) {
        this.f2095a = e0Var;
    }

    public static boolean a(n0 n0Var, j.z zVar) {
        b4.l.c("null cannot be cast to non-null type androidx.compose.runtime.DerivedState<kotlin.Any?>", n0Var);
        b3 b3Var = n0Var.f2278f;
        if (b3Var == null) {
            b3Var = h.f2189j;
        }
        return !b3Var.a(n0Var.h().f2271f, zVar.e(n0Var));
    }

    public final boolean b() {
        if (this.f2095a != null) {
            a aVar = this.f2097c;
            if (aVar != null ? aVar.a() : false) {
                return true;
            }
        }
        return false;
    }

    public final y0 c(Object obj) {
        y0 y0VarR;
        e0 e0Var = this.f2095a;
        return (e0Var == null || (y0VarR = e0Var.r(this, obj)) == null) ? y0.f2422d : y0VarR;
    }

    public final void d() {
        e0 e0Var = this.f2095a;
        if (e0Var != null) {
            e0Var.f2144r = true;
            e0Var.f2147u.j();
        }
        this.f2095a = null;
        this.f2100f = null;
        this.f2101g = null;
        this.f2098d = null;
    }

    public final void e(boolean z2) {
        int i5 = this.f2096b;
        this.f2096b = z2 ? i5 | 32 : i5 & (-33);
    }
}

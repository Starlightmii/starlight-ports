package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y1 f2436a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f2437b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b3 f2438c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f2439d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f2440e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2441f = true;

    public z1(y1 y1Var, Object obj, boolean z2, b3 b3Var, boolean z3) {
        this.f2436a = y1Var;
        this.f2437b = z2;
        this.f2438c = b3Var;
        this.f2439d = z3;
        this.f2440e = obj;
    }

    public final Object a() {
        if (this.f2437b) {
            return null;
        }
        Object obj = this.f2440e;
        if (obj != null) {
            return obj;
        }
        z.b("Unexpected form of a provided value");
        throw new i4.p();
    }
}

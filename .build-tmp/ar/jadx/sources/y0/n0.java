package y0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n0 implements k2.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f6650d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f6651e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f6652f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f6653g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f6654h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f6655i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f6656j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f6657k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f6658l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public q0 f6659m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f6660n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public long f6661o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public k2.b f6662p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public k2.k f6663q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public j0 f6664r;

    @Override // k2.b
    public final float a() {
        return this.f6662p.a();
    }

    public final void c(float f5) {
        if (this.f6653g == f5) {
            return;
        }
        this.f6650d |= 4;
        this.f6653g = f5;
    }

    public final void d(long j5) {
        if (u.c(this.f6655i, j5)) {
            return;
        }
        this.f6650d |= 64;
        this.f6655i = j5;
    }

    public final void e(float f5) {
        if (this.f6651e == f5) {
            return;
        }
        this.f6650d |= 1;
        this.f6651e = f5;
    }

    public final void g(float f5) {
        if (this.f6652f == f5) {
            return;
        }
        this.f6650d |= 2;
        this.f6652f = f5;
    }

    public final void h(float f5) {
        if (this.f6654h == f5) {
            return;
        }
        this.f6650d |= 32;
        this.f6654h = f5;
    }

    public final void i(q0 q0Var) {
        if (b4.l.a(this.f6659m, q0Var)) {
            return;
        }
        this.f6650d |= 8192;
        this.f6659m = q0Var;
    }

    @Override // k2.b
    public final float j() {
        return this.f6662p.j();
    }

    public final void k(long j5) {
        if (u.c(this.f6656j, j5)) {
            return;
        }
        this.f6650d |= 128;
        this.f6656j = j5;
    }

    public final void m(long j5) {
        long j6 = this.f6658l;
        int i5 = t0.f6687c;
        if (j6 == j5) {
            return;
        }
        this.f6650d |= 4096;
        this.f6658l = j5;
    }
}

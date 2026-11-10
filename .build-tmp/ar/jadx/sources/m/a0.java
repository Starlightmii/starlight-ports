package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a0 implements y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3609a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3610b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final v f3611c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f3612d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f3613e;

    public a0(int i5, int i6, v vVar) {
        this.f3609a = i5;
        this.f3610b = i6;
        this.f3611c = vVar;
        this.f3612d = ((long) i5) * 1000000;
        this.f3613e = ((long) i6) * 1000000;
    }

    @Override // m.y
    public final float b(long j5, float f5, float f6, float f7) {
        float fN = this.f3609a == 0 ? 1.0f : d4.a.n(j5 - this.f3613e, 0L, this.f3612d) / this.f3612d;
        if (fN < 0.0f) {
            fN = 0.0f;
        }
        float fA = this.f3611c.a(fN <= 1.0f ? fN : 1.0f);
        a1 a1Var = b1.f3620a;
        return (f6 * fA) + ((1 - fA) * f5);
    }

    @Override // m.y
    public final float c(long j5, float f5, float f6, float f7) {
        long jN = d4.a.n(j5 - this.f3613e, 0L, this.f3612d);
        if (jN < 0) {
            return 0.0f;
        }
        if (jN == 0) {
            return f7;
        }
        return (b(jN, f5, f6, f7) - b(jN - 1000000, f5, f6, f7)) * 1000.0f;
    }

    @Override // m.y
    public final long d(float f5, float f6, float f7) {
        return ((long) (this.f3610b + this.f3609a)) * 1000000;
    }
}

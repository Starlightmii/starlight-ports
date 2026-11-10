package l;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f3489a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f3490b;

    public e(float f5, k2.b bVar) {
        this.f3489a = f5;
        float fA = bVar.a();
        float f6 = f.f3491a;
        this.f3490b = fA * 386.0878f * 160.0f * 0.84f;
    }

    public final d a(float f5) {
        double dB = b(f5);
        double d5 = f.f3491a;
        double d6 = d5 - 1.0d;
        return new d(f5, (float) (Math.exp((d5 / d6) * dB) * ((double) (this.f3489a * this.f3490b))), (long) (Math.exp(dB / d6) * 1000.0d));
    }

    public final double b(float f5) {
        float[] fArr = b.f3484a;
        return Math.log(((double) (Math.abs(f5) * 0.35f)) / ((double) (this.f3489a * this.f3490b)));
    }
}

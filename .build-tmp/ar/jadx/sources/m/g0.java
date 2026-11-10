package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f3663a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public double f3664b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f3665c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public double f3666d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f3667e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public double f3668f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f3669g;

    public final long a(float f5, float f6, long j5) {
        double dCos;
        double dExp;
        if (!this.f3665c) {
            if (this.f3663a == Float.MAX_VALUE) {
                throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
            }
            float f7 = this.f3669g;
            double d5 = f7;
            double d6 = d5 * d5;
            if (f7 > 1.0f) {
                double d7 = this.f3664b;
                double d8 = d6 - ((double) 1);
                this.f3666d = (Math.sqrt(d8) * d7) + (((double) (-f7)) * d7);
                double d9 = -this.f3669g;
                double d10 = this.f3664b;
                this.f3667e = (d9 * d10) - (Math.sqrt(d8) * d10);
            } else if (f7 >= 0.0f && f7 < 1.0f) {
                this.f3668f = Math.sqrt(((double) 1) - d6) * this.f3664b;
            }
            this.f3665c = true;
        }
        float f8 = f5 - this.f3663a;
        double d11 = j5 / 1000.0d;
        float f9 = this.f3669g;
        if (f9 > 1.0f) {
            double d12 = f8;
            double d13 = this.f3667e;
            double d14 = ((d13 * d12) - ((double) f6)) / (d13 - this.f3666d);
            double d15 = d12 - d14;
            dExp = (Math.exp(this.f3666d * d11) * d14) + (Math.exp(d13 * d11) * d15);
            double d16 = this.f3667e;
            double dExp2 = Math.exp(d16 * d11) * d15 * d16;
            double d17 = this.f3666d;
            dCos = (Math.exp(d17 * d11) * d14 * d17) + dExp2;
        } else if (f9 == 1.0f) {
            double d18 = this.f3664b;
            double d19 = f8;
            double d20 = (d18 * d19) + ((double) f6);
            double d21 = (d20 * d11) + d19;
            dExp = Math.exp((-d18) * d11) * d21;
            double dExp3 = Math.exp((-this.f3664b) * d11) * d21;
            double d22 = -this.f3664b;
            dCos = (dExp3 * d22) + (Math.exp(d22 * d11) * d20);
        } else {
            double d23 = ((double) 1) / this.f3668f;
            double d24 = this.f3664b;
            double d25 = f8;
            double d26 = ((((double) f9) * d24 * d25) + ((double) f6)) * d23;
            double dExp4 = Math.exp(((double) (-f9)) * d24 * d11) * ((Math.sin(this.f3668f * d11) * d26) + (Math.cos(this.f3668f * d11) * d25));
            double d27 = this.f3664b;
            float f10 = this.f3669g;
            double d28 = (-d27) * dExp4 * ((double) f10);
            double dExp5 = Math.exp(((double) (-f10)) * d27 * d11);
            double d29 = this.f3668f;
            double dSin = Math.sin(d29 * d11) * (-d29) * d25;
            double d30 = this.f3668f;
            dCos = (((Math.cos(d30 * d11) * d26 * d30) + dSin) * dExp5) + d28;
            dExp = dExp4;
        }
        return (((long) Float.floatToRawIntBits((float) (dExp + ((double) this.f3663a)))) << 32) | (((long) Float.floatToRawIntBits((float) dCos)) & 4294967295L);
    }
}

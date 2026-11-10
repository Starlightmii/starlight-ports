package z0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final double f7102a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final double f7103b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final double f7104c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final double f7105d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final double f7106e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final double f7107f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final double f7108g;

    public /* synthetic */ r(double d5, double d6, double d7, double d8, double d9) {
        this(d5, d6, d7, d8, d9, 0.0d, 0.0d);
    }

    public r(double d5, double d6, double d7, double d8, double d9, double d10, double d11) {
        this.f7102a = d5;
        this.f7103b = d6;
        this.f7104c = d7;
        this.f7105d = d8;
        this.f7106e = d9;
        this.f7107f = d10;
        this.f7108g = d11;
        if (Double.isNaN(d6) || Double.isNaN(d7) || Double.isNaN(d8) || Double.isNaN(d9) || Double.isNaN(d10) || Double.isNaN(d11) || Double.isNaN(d5)) {
            throw new IllegalArgumentException("Parameters cannot be NaN");
        }
        if (d9 < 0.0d || d9 > 1.0d) {
            throw new IllegalArgumentException("Parameter d must be in the range [0..1], was " + d9);
        }
        if (d9 == 0.0d && (d6 == 0.0d || d5 == 0.0d)) {
            throw new IllegalArgumentException("Parameter a or g is zero, the transfer function is constant");
        }
        if (d9 >= 1.0d && d8 == 0.0d) {
            throw new IllegalArgumentException("Parameter c is zero, the transfer function is constant");
        }
        if ((d6 == 0.0d || d5 == 0.0d) && d8 == 0.0d) {
            throw new IllegalArgumentException("Parameter a or g is zero, and c is zero, the transfer function is constant");
        }
        if (d8 < 0.0d) {
            throw new IllegalArgumentException("The transfer function must be increasing");
        }
        if (d6 < 0.0d || d5 < 0.0d) {
            throw new IllegalArgumentException("The transfer function must be positive or increasing");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return Double.compare(this.f7102a, rVar.f7102a) == 0 && Double.compare(this.f7103b, rVar.f7103b) == 0 && Double.compare(this.f7104c, rVar.f7104c) == 0 && Double.compare(this.f7105d, rVar.f7105d) == 0 && Double.compare(this.f7106e, rVar.f7106e) == 0 && Double.compare(this.f7107f, rVar.f7107f) == 0 && Double.compare(this.f7108g, rVar.f7108g) == 0;
    }

    public final int hashCode() {
        return Double.hashCode(this.f7108g) + ((Double.hashCode(this.f7107f) + ((Double.hashCode(this.f7106e) + ((Double.hashCode(this.f7105d) + ((Double.hashCode(this.f7104c) + ((Double.hashCode(this.f7103b) + (Double.hashCode(this.f7102a) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "TransferParameters(gamma=" + this.f7102a + ", a=" + this.f7103b + ", b=" + this.f7104c + ", c=" + this.f7105d + ", d=" + this.f7106e + ", e=" + this.f7107f + ", f=" + this.f7108g + ')';
    }
}

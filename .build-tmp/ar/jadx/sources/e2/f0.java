package e2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class f0 implements f0.i, m.v, z0.i {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2021d;

    @Override // m.v
    public float a(float f5) {
        return f5;
    }

    @Override // z0.i
    public double b(double d5) {
        double d6;
        switch (this.f2021d) {
            case 10:
                double dPow = d5 < 0.0d ? -d5 : d5;
                if (dPow >= 0.0031308049535603718d) {
                    dPow = Math.pow(dPow, 0.4166666666666667d) - 0.05213270142180095d;
                    d6 = 0.9478672985781991d;
                } else {
                    d6 = 0.07739938080495357d;
                }
                return Math.copySign(dPow / d6, d5);
            case 11:
                double d7 = d5 < 0.0d ? -d5 : d5;
                return Math.copySign(d7 >= 0.04045d ? Math.pow((0.9478672985781991d * d7) + 0.05213270142180095d, 2.4d) : d7 * 0.07739938080495357d, d5);
            default:
                return d5;
        }
    }

    @Override // f0.i
    public void cancel() {
    }
}

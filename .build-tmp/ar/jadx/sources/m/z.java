package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z implements y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f3805a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g0 f3806b;

    public z(float f5, float f6, float f7) {
        this.f3805a = f7;
        g0 g0Var = new g0();
        g0Var.f3663a = 1.0f;
        double dSqrt = Math.sqrt(50.0d);
        g0Var.f3664b = dSqrt;
        g0Var.f3669g = 1.0f;
        if (f5 < 0.0f) {
            throw new IllegalArgumentException("Damping ratio must be non-negative");
        }
        g0Var.f3669g = f5;
        g0Var.f3665c = false;
        if (((float) (dSqrt * dSqrt)) <= 0.0f) {
            throw new IllegalArgumentException("Spring stiffness constant must be positive.");
        }
        g0Var.f3664b = Math.sqrt(f6);
        g0Var.f3665c = false;
        this.f3806b = g0Var;
    }

    @Override // m.y
    public final float b(long j5, float f5, float f6, float f7) {
        g0 g0Var = this.f3806b;
        g0Var.f3663a = f6;
        return Float.intBitsToFloat((int) (g0Var.a(f5, f7, j5 / 1000000) >> 32));
    }

    @Override // m.y
    public final float c(long j5, float f5, float f6, float f7) {
        g0 g0Var = this.f3806b;
        g0Var.f3663a = f6;
        return Float.intBitsToFloat((int) (g0Var.a(f5, f7, j5 / 1000000) & 4294967295L));
    }

    @Override // m.y
    public final long d(float f5, float f6, float f7) {
        double dLog;
        long j5;
        g0 g0Var = this.f3806b;
        double d5 = g0Var.f3664b;
        float f8 = (float) (d5 * d5);
        float f9 = g0Var.f3669g;
        float f10 = this.f3805a;
        float f11 = (f5 - f6) / f10;
        float f12 = f7 / f10;
        if (f9 == 0.0f) {
            j5 = 9223372036854L;
        } else {
            double d6 = f8;
            double d7 = f9;
            double d8 = f12;
            double d9 = f11;
            double d10 = 1.0f;
            double dSqrt = d7 * 2.0d * Math.sqrt(d6);
            double d11 = (dSqrt * dSqrt) - (d6 * 4.0d);
            double d12 = -dSqrt;
            s sVar = d11 < 0.0d ? new s(0.0d, Math.sqrt(Math.abs(d11))) : new s(Math.sqrt(d11), 0.0d);
            sVar.f3749a = (sVar.f3749a + d12) * 0.5d;
            sVar.f3750b *= 0.5d;
            s sVar2 = d11 < 0.0d ? new s(0.0d, Math.sqrt(Math.abs(d11))) : new s(Math.sqrt(d11), 0.0d);
            double d13 = -1;
            double d14 = sVar2.f3749a * d13;
            double d15 = sVar2.f3750b * d13;
            sVar2.f3749a = (d14 + d12) * 0.5d;
            sVar2.f3750b = d15 * 0.5d;
            if (d9 == 0.0d && d8 == 0.0d) {
                j5 = 0;
            } else {
                if (d9 < 0.0d) {
                    d8 = -d8;
                }
                double dAbs = Math.abs(d9);
                double dAbs2 = Double.MAX_VALUE;
                if (d7 > 1.0d) {
                    double d16 = sVar.f3749a;
                    double d17 = sVar2.f3749a;
                    double d18 = (d16 * dAbs) - d8;
                    double d19 = d16 - d17;
                    double d20 = d18 / d19;
                    double d21 = dAbs - d20;
                    dLog = Math.log(Math.abs(d10 / d21)) / d16;
                    double dLog2 = Math.log(Math.abs(d10 / d20)) / d17;
                    if (Double.isInfinite(dLog) || Double.isNaN(dLog)) {
                        dLog = dLog2;
                    } else if (!Double.isInfinite(dLog2) && !Double.isNaN(dLog2)) {
                        dLog = Math.max(dLog, dLog2);
                    }
                    double d22 = d21 * d16;
                    double dLog3 = Math.log(d22 / ((-d20) * d17)) / (d17 - d16);
                    if (Double.isNaN(dLog3) || dLog3 <= 0.0d) {
                        d10 = -d10;
                    } else {
                        if (dLog3 > 0.0d) {
                            if ((-((Math.exp(dLog3 * d17) * d20) + (Math.exp(d16 * dLog3) * d21))) < d10) {
                                if (d20 > 0.0d && d21 < 0.0d) {
                                    dLog = 0.0d;
                                }
                                d10 = -d10;
                            }
                        }
                        dLog = Math.log((-((d20 * d17) * d17)) / (d22 * d16)) / d19;
                    }
                    double d23 = d20 * d17;
                    if (Math.abs((Math.exp(d17 * dLog) * d23) + (Math.exp(d16 * dLog) * d22)) >= 1.0E-4d) {
                        int i5 = 0;
                        while (dAbs2 > 0.001d && i5 < 100) {
                            i5++;
                            double d24 = d16 * dLog;
                            double d25 = d17 * dLog;
                            double dExp = dLog - ((((Math.exp(d25) * d20) + (Math.exp(d24) * d21)) + d10) / ((Math.exp(d25) * d23) + (Math.exp(d24) * d22)));
                            dAbs2 = Math.abs(dLog - dExp);
                            dLog = dExp;
                        }
                    }
                } else if (d7 < 1.0d) {
                    double d26 = sVar.f3749a;
                    double d27 = (d8 - (d26 * dAbs)) / sVar.f3750b;
                    dLog = Math.log(d10 / Math.sqrt((d27 * d27) + (dAbs * dAbs))) / d26;
                } else {
                    double d28 = sVar.f3749a;
                    double d29 = d28 * dAbs;
                    double d30 = d8 - d29;
                    double dLog4 = Math.log(Math.abs(d10 / dAbs)) / d28;
                    double dLog5 = Math.log(Math.abs(d10 / d30));
                    double dLog6 = dLog5;
                    for (int i6 = 0; i6 < 6; i6++) {
                        dLog6 = dLog5 - Math.log(Math.abs(dLog6 / d28));
                    }
                    double d31 = dLog6 / d28;
                    if (Double.isInfinite(dLog4) || Double.isNaN(dLog4)) {
                        dLog4 = d31;
                    } else if (!Double.isInfinite(d31) && !Double.isNaN(d31)) {
                        dLog4 = Math.max(dLog4, d31);
                    }
                    double d32 = (-(d29 + d30)) / (d28 * d30);
                    double d33 = d28 * d32;
                    double dExp2 = (Math.exp(d33) * d30 * d32) + (Math.exp(d33) * dAbs);
                    if (Double.isNaN(d32) || d32 <= 0.0d) {
                        d10 = -d10;
                    } else if (d32 <= 0.0d || (-dExp2) >= d10) {
                        dLog4 = (-(2.0d / d28)) - (dAbs / d30);
                    } else {
                        d10 = -d10;
                        dLog4 = (d30 >= 0.0d || dAbs <= 0.0d) ? dLog4 : 0.0d;
                    }
                    int i7 = 0;
                    while (dAbs2 > 0.001d && i7 < 100) {
                        i7++;
                        double d34 = d28 * dLog4;
                        double dExp3 = dLog4 - (((Math.exp(d34) * ((d30 * dLog4) + dAbs)) + d10) / (Math.exp(d34) * (((((double) 1) + d34) * d30) + d29)));
                        dAbs2 = Math.abs(dLog4 - dExp3);
                        dLog4 = dExp3;
                    }
                    dLog = dLog4;
                }
                j5 = (long) (dLog * 1000.0d);
            }
        }
        return j5 * 1000000;
    }

    @Override // m.y
    public final float e(float f5, float f6, float f7) {
        return 0.0f;
    }
}

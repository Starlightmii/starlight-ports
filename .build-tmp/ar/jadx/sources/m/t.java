package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t implements v {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f3760d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f3761e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f3762f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f3763g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f3764h;

    public t(float f5, float f6, float f7) {
        int iA;
        this.f3760d = f5;
        this.f3761e = f6;
        this.f3762f = f7;
        if (Float.isNaN(f5) || Float.isNaN(f6) || Float.isNaN(f7) || Float.isNaN(1.0f)) {
            throw new IllegalArgumentException("Parameters to CubicBezierEasing cannot be NaN. Actual parameters are: " + f5 + ", " + f6 + ", " + f7 + ", 1.0.");
        }
        float[] fArr = new float[5];
        float f8 = (f6 - 0.0f) * 3.0f;
        float f9 = (1.0f - f6) * 3.0f;
        double d5 = f8;
        double d6 = f9;
        double d7 = 0.0f;
        double d8 = d6 * 2.0d;
        double d9 = (d5 - d8) + d7;
        if (d9 == 0.0d) {
            iA = d6 == d7 ? 0 : y0.l0.A((float) ((d8 - d7) / (d8 - (d7 * 2.0d))), fArr, 0);
        } else {
            double d10 = -Math.sqrt((d6 * d6) - (d7 * d5));
            double d11 = (-d5) + d6;
            int iA2 = y0.l0.A((float) ((-(d10 + d11)) / d9), fArr, 0);
            iA = y0.l0.A((float) ((d10 - d11) / d9), fArr, iA2) + iA2;
            if (iA > 1) {
                float f10 = fArr[0];
                float f11 = fArr[1];
                if (f10 > f11) {
                    fArr[0] = f11;
                    fArr[1] = f10;
                } else if (f10 == f11) {
                    iA--;
                }
            }
        }
        float f12 = (f9 - f8) * 2.0f;
        int iA3 = y0.l0.A((-f12) / (((0.0f - f9) * 2.0f) - f12), fArr, iA) + iA;
        float fMin = Math.min(0.0f, 1.0f);
        float fMax = Math.max(0.0f, 1.0f);
        for (int i5 = 0; i5 < iA3; i5++) {
            float f13 = fArr[i5];
            float f14 = (((((((((f6 - 1.0f) * 3.0f) + 1.0f) - 0.0f) * f13) + (((1.0f - (f6 * 2.0f)) + 0.0f) * 3.0f)) * f13) + f8) * f13) + 0.0f;
            fMin = Math.min(fMin, f14);
            fMax = Math.max(fMax, f14);
        }
        long jFloatToRawIntBits = (((long) Float.floatToRawIntBits(fMin)) << 32) | (((long) Float.floatToRawIntBits(fMax)) & 4294967295L);
        this.f3763g = Float.intBitsToFloat((int) (jFloatToRawIntBits >> 32));
        this.f3764h = Float.intBitsToFloat((int) (jFloatToRawIntBits & 4294967295L));
    }

    /* JADX WARN: Code duplicated, block: B:17:0x007c  */
    /* JADX WARN: Code duplicated, block: B:22:0x0088  */
    /* JADX WARN: Code duplicated, block: B:28:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:36:0x00bc A[PHI: r4
      0x00bc: PHI (r4v25 float) = (r4v15 float), (r4v20 float), (r4v33 float) binds: [B:67:0x014b, B:81:0x017a, B:35:0x00ba] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:60:0x013a  */
    /* JADX WARN: Code duplicated, block: B:74:0x0169  */
    /* JADX WARN: Code duplicated, block: B:99:0x01b8  */
    @Override // m.v
    public final float a(float f5) {
        float fS;
        float fCos;
        if (f5 <= 0.0f || f5 >= 1.0f) {
            return f5;
        }
        float f6 = 0.0f - f5;
        float f7 = this.f3760d;
        float f8 = f7 - f5;
        float f9 = this.f3762f;
        float f10 = f9 - f5;
        double d5 = f6;
        double d6 = ((d5 - (((double) f8) * 2.0d)) + ((double) f10)) * 3.0d;
        double d7 = ((double) (f8 - f6)) * 3.0d;
        double d8 = (((double) (f8 - f10)) * 3.0d) + ((double) (-f6)) + ((double) (1.0f - f5));
        if (Math.abs(d8 - 0.0d) >= 1.0E-7d) {
            double d9 = d6 / d8;
            double d10 = d7 / d8;
            double d11 = d5 / d8;
            double d12 = ((d10 * 3.0d) - (d9 * d9)) / 9.0d;
            double d13 = ((d11 * 27.0d) + ((((2.0d * d9) * d9) * d9) - ((9.0d * d9) * d10))) / 54.0d;
            double d14 = d12 * d12 * d12;
            double d15 = (d13 * d13) + d14;
            double d16 = d9 / 3.0d;
            if (d15 < 0.0d) {
                double dSqrt = Math.sqrt(-d14);
                double d17 = (-d13) / dSqrt;
                if (d17 < -1.0d) {
                    d17 = -1.0d;
                }
                if (d17 > 1.0d) {
                    d17 = 1.0d;
                }
                double dAcos = Math.acos(d17);
                double dS = a.a.s((float) dSqrt) * 2.0f;
                fCos = (float) ((Math.cos(dAcos / 3.0d) * dS) - d16);
                if (fCos < 0.0f) {
                    if (fCos >= -8.34465E-7f) {
                        fCos = 0.0f;
                    } else {
                        fCos = Float.NaN;
                    }
                } else if (fCos > 1.0f) {
                    if (fCos <= 1.0000008f) {
                        fCos = 1.0f;
                    } else {
                        fCos = Float.NaN;
                    }
                }
                if (Float.isNaN(fCos)) {
                    fCos = (float) ((Math.cos((6.283185307179586d + dAcos) / 3.0d) * dS) - d16);
                    if (fCos < 0.0f) {
                        if (fCos >= -8.34465E-7f) {
                            fCos = 0.0f;
                        } else {
                            fCos = Float.NaN;
                        }
                    } else if (fCos > 1.0f) {
                        if (fCos <= 1.0000008f) {
                            fCos = 1.0f;
                        } else {
                            fCos = Float.NaN;
                        }
                    }
                    if (Float.isNaN(fCos)) {
                        fS = (float) ((Math.cos((dAcos + 12.566370614359172d) / 3.0d) * dS) - d16);
                        if (fS < 0.0f) {
                            if (fS >= -8.34465E-7f) {
                                fS = 0.0f;
                            }
                        } else if (fS > 1.0f) {
                            if (fS <= 1.0000008f) {
                                fS = 1.0f;
                            }
                        }
                        fS = Float.NaN;
                    } else {
                        fS = fCos;
                    }
                } else {
                    fS = fCos;
                }
            } else if (d15 == 0.0d) {
                float f11 = -a.a.s((float) d13);
                float f12 = (float) d16;
                float f13 = (f11 * 2.0f) - f12;
                if (f13 < 0.0f) {
                    if (f13 >= -8.34465E-7f) {
                        f13 = 0.0f;
                    } else {
                        f13 = Float.NaN;
                    }
                } else if (f13 > 1.0f) {
                    if (f13 <= 1.0000008f) {
                        f13 = 1.0f;
                    } else {
                        f13 = Float.NaN;
                    }
                }
                if (Float.isNaN(f13)) {
                    fS = (-f11) - f12;
                    if (fS < 0.0f) {
                        if (fS >= -8.34465E-7f) {
                            fS = 0.0f;
                        }
                    } else if (fS > 1.0f) {
                        if (fS <= 1.0000008f) {
                            fS = 1.0f;
                        }
                    }
                    fS = Float.NaN;
                } else {
                    fS = f13;
                }
            } else {
                double dSqrt2 = Math.sqrt(d15);
                fS = (float) (((double) (a.a.s((float) ((-d13) + dSqrt2)) - a.a.s((float) (d13 + dSqrt2)))) - d16);
                if (fS < 0.0f) {
                    if (fS >= -8.34465E-7f) {
                        fS = 0.0f;
                    }
                } else if (fS > 1.0f) {
                    if (fS <= 1.0000008f) {
                        fS = 1.0f;
                    }
                }
                fS = Float.NaN;
            }
        } else if (Math.abs(d6 - 0.0d) >= 1.0E-7d) {
            double dSqrt3 = Math.sqrt((d7 * d7) - ((4.0d * d6) * d5));
            double d18 = d6 * 2.0d;
            fCos = (float) ((dSqrt3 - d7) / d18);
            if (fCos < 0.0f) {
                if (fCos >= -8.34465E-7f) {
                    fCos = 0.0f;
                } else {
                    fCos = Float.NaN;
                }
            } else if (fCos > 1.0f) {
                if (fCos <= 1.0000008f) {
                    fCos = 1.0f;
                } else {
                    fCos = Float.NaN;
                }
            }
            if (Float.isNaN(fCos)) {
                fS = (float) (((-d7) - dSqrt3) / d18);
                if (fS < 0.0f) {
                    if (fS >= -8.34465E-7f) {
                        fS = 0.0f;
                    }
                } else if (fS > 1.0f) {
                    if (fS <= 1.0000008f) {
                        fS = 1.0f;
                    }
                }
                fS = Float.NaN;
            } else {
                fS = fCos;
            }
        } else if (Math.abs(d7 - 0.0d) < 1.0E-7d) {
            fS = Float.NaN;
        } else {
            fS = (float) ((-d5) / d7);
            if (fS < 0.0f) {
                if (fS >= -8.34465E-7f) {
                    fS = 0.0f;
                }
            } else if (fS > 1.0f) {
                if (fS <= 1.0000008f) {
                    fS = 1.0f;
                }
            }
            fS = Float.NaN;
        }
        boolean zIsNaN = Float.isNaN(fS);
        float f14 = this.f3761e;
        if (!zIsNaN) {
            float f15 = ((((((f14 - 1.0f) + 0.33333334f) * fS) + (1.0f - (2.0f * f14))) * fS) + f14) * 3.0f * fS;
            float f16 = this.f3763g;
            if (f15 < f16) {
                f15 = f16;
            }
            float f17 = this.f3764h;
            return f15 > f17 ? f17 : f15;
        }
        throw new IllegalArgumentException("The cubic curve with parameters (" + f7 + ", " + f14 + ", " + f9 + ", 1.0) has no solution at " + f5);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof t)) {
            return false;
        }
        t tVar = (t) obj;
        return this.f3760d == tVar.f3760d && this.f3761e == tVar.f3761e && this.f3762f == tVar.f3762f;
    }

    public final int hashCode() {
        return Float.hashCode(1.0f) + b.b.a(this.f3762f, b.b.a(this.f3761e, Float.hashCode(this.f3760d) * 31, 31), 31);
    }

    public final String toString() {
        return "CubicBezierEasing(a=" + this.f3760d + ", b=" + this.f3761e + ", c=" + this.f3762f + ", d=1.0)";
    }
}

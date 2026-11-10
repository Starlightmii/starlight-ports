package y0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f6689b = l0.d(4278190080L);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final long f6690c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final long f6691d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f6692e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final long f6693f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final long f6694g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final /* synthetic */ int f6695h = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f6696a;

    static {
        l0.d(4282664004L);
        l0.d(4287137928L);
        l0.d(4291611852L);
        f6690c = l0.d(4294967295L);
        f6691d = l0.d(4294901760L);
        l0.d(4278255360L);
        f6692e = l0.d(4278190335L);
        l0.d(4294967040L);
        l0.d(4278255615L);
        l0.d(4294902015L);
        f6693f = l0.c(0);
        f6694g = l0.b(0.0f, 0.0f, 0.0f, 0.0f, z0.d.f7058s);
    }

    public /* synthetic */ u(long j5) {
        this.f6696a = j5;
    }

    public static final long a(long j5, z0.c cVar) {
        z0.g gVarE;
        z0.c cVarF = f(j5);
        int i5 = cVarF.f7039c;
        int i6 = cVar.f7039c;
        if ((i5 | i6) < 0) {
            gVarE = z0.j.e(cVarF, cVar);
        } else {
            j.r rVar = z0.h.f7068a;
            int i7 = i5 | (i6 << 6);
            Object objD = rVar.d(i7);
            if (objD == null) {
                objD = z0.j.e(cVarF, cVar);
                rVar.g(i7, objD);
            }
            gVarE = (z0.g) objD;
        }
        return gVarE.a(j5);
    }

    public static long b(float f5, long j5) {
        return l0.b(h(j5), g(j5), e(j5), f5, f(j5));
    }

    public static final boolean c(long j5, long j6) {
        return j5 == j6;
    }

    public static final float d(long j5) {
        float fE;
        float f5;
        if ((63 & j5) == 0) {
            fE = (float) p3.p.E((j5 >>> 56) & 255);
            f5 = 255.0f;
        } else {
            fE = (float) p3.p.E((j5 >>> 6) & 1023);
            f5 = 1023.0f;
        }
        return fE / f5;
    }

    public static final float e(long j5) {
        int i5;
        int i6;
        int i7;
        if ((63 & j5) == 0) {
            return ((float) p3.p.E((j5 >>> 32) & 255)) / 255.0f;
        }
        short s4 = (short) ((j5 >>> 16) & 65535);
        int i8 = Short.MIN_VALUE & s4;
        int i9 = ((65535 & s4) >>> 10) & 31;
        int i10 = s4 & 1023;
        if (i9 != 0) {
            int i11 = i10 << 13;
            if (i9 == 31) {
                i5 = 255;
                if (i11 != 0) {
                    i11 |= 4194304;
                }
            } else {
                i5 = i9 + 112;
            }
            int i12 = i5;
            i6 = i11;
            i7 = i12;
        } else {
            if (i10 != 0) {
                float fIntBitsToFloat = Float.intBitsToFloat(i10 + 1056964608) - z.f6702a;
                return i8 == 0 ? fIntBitsToFloat : -fIntBitsToFloat;
            }
            i7 = 0;
            i6 = 0;
        }
        return Float.intBitsToFloat((i7 << 23) | (i8 << 16) | i6);
    }

    public static final z0.c f(long j5) {
        float[] fArr = z0.d.f7040a;
        return z0.d.f7060u[(int) (j5 & 63)];
    }

    public static final float g(long j5) {
        int i5;
        int i6;
        int i7;
        if ((63 & j5) == 0) {
            return ((float) p3.p.E((j5 >>> 40) & 255)) / 255.0f;
        }
        short s4 = (short) ((j5 >>> 32) & 65535);
        int i8 = Short.MIN_VALUE & s4;
        int i9 = ((65535 & s4) >>> 10) & 31;
        int i10 = s4 & 1023;
        if (i9 != 0) {
            int i11 = i10 << 13;
            if (i9 == 31) {
                i5 = 255;
                if (i11 != 0) {
                    i11 |= 4194304;
                }
            } else {
                i5 = i9 + 112;
            }
            int i12 = i5;
            i6 = i11;
            i7 = i12;
        } else {
            if (i10 != 0) {
                float fIntBitsToFloat = Float.intBitsToFloat(i10 + 1056964608) - z.f6702a;
                return i8 == 0 ? fIntBitsToFloat : -fIntBitsToFloat;
            }
            i7 = 0;
            i6 = 0;
        }
        return Float.intBitsToFloat((i7 << 23) | (i8 << 16) | i6);
    }

    public static final float h(long j5) {
        int i5;
        int i6;
        int i7;
        if ((63 & j5) == 0) {
            return ((float) p3.p.E((j5 >>> 48) & 255)) / 255.0f;
        }
        short s4 = (short) ((j5 >>> 48) & 65535);
        int i8 = Short.MIN_VALUE & s4;
        int i9 = ((65535 & s4) >>> 10) & 31;
        int i10 = s4 & 1023;
        if (i9 != 0) {
            int i11 = i10 << 13;
            if (i9 == 31) {
                i5 = 255;
                if (i11 != 0) {
                    i11 |= 4194304;
                }
            } else {
                i5 = i9 + 112;
            }
            int i12 = i5;
            i6 = i11;
            i7 = i12;
        } else {
            if (i10 != 0) {
                float fIntBitsToFloat = Float.intBitsToFloat(i10 + 1056964608) - z.f6702a;
                return i8 == 0 ? fIntBitsToFloat : -fIntBitsToFloat;
            }
            i7 = 0;
            i6 = 0;
        }
        return Float.intBitsToFloat((i7 << 23) | (i8 << 16) | i6);
    }

    public static String i(long j5) {
        return "Color(" + h(j5) + ", " + g(j5) + ", " + e(j5) + ", " + d(j5) + ", " + f(j5).f7037a + ')';
    }

    public final boolean equals(Object obj) {
        if (obj instanceof u) {
            return this.f6696a == ((u) obj).f6696a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f6696a);
    }

    public final String toString() {
        return i(this.f6696a);
    }
}

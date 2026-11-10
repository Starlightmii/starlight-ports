package z0;

import y0.l0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k extends c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f7074d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k(int i5, int i6, long j5, String str) {
        super(str, j5, i5);
        this.f7074d = i6;
    }

    @Override // z0.c
    public final float a(int i5) {
        switch (this.f7074d) {
            case 0:
                return i5 == 0 ? 100.0f : 128.0f;
            default:
                return 2.0f;
        }
    }

    @Override // z0.c
    public final float b(int i5) {
        switch (this.f7074d) {
            case 0:
                return i5 == 0 ? 0.0f : -128.0f;
            default:
                return -2.0f;
        }
    }

    @Override // z0.c
    public final long d(float f5, float f6, float f7) {
        switch (this.f7074d) {
            case 0:
                if (f5 < 0.0f) {
                    f5 = 0.0f;
                }
                if (f5 > 100.0f) {
                    f5 = 100.0f;
                }
                if (f6 < -128.0f) {
                    f6 = -128.0f;
                }
                if (f6 > 128.0f) {
                    f6 = 128.0f;
                }
                float f8 = (f5 + 16.0f) / 116.0f;
                float f9 = (f6 * 0.002f) + f8;
                float f10 = f9 > 0.20689656f ? f9 * f9 * f9 : (f9 - 0.13793103f) * 0.12841855f;
                float f11 = f8 > 0.20689656f ? f8 * f8 * f8 : (f8 - 0.13793103f) * 0.12841855f;
                float[] fArr = j.f7073e;
                return (((long) Float.floatToRawIntBits(f11 * fArr[1])) & 4294967295L) | (((long) Float.floatToRawIntBits(f10 * fArr[0])) << 32);
            default:
                if (f5 < -2.0f) {
                    f5 = -2.0f;
                }
                if (f5 > 2.0f) {
                    f5 = 2.0f;
                }
                if (f6 < -2.0f) {
                    f6 = -2.0f;
                }
                return (((long) Float.floatToRawIntBits(f5)) << 32) | (((long) Float.floatToRawIntBits(f6 <= 2.0f ? f6 : 2.0f)) & 4294967295L);
        }
    }

    @Override // z0.c
    public final float e(float f5, float f6, float f7) {
        switch (this.f7074d) {
            case 0:
                if (f5 < 0.0f) {
                    f5 = 0.0f;
                }
                if (f5 > 100.0f) {
                    f5 = 100.0f;
                }
                if (f7 < -128.0f) {
                    f7 = -128.0f;
                }
                if (f7 > 128.0f) {
                    f7 = 128.0f;
                }
                float f8 = ((f5 + 16.0f) / 116.0f) - (f7 * 0.005f);
                return (f8 > 0.20689656f ? f8 * f8 * f8 : 0.12841855f * (f8 - 0.13793103f)) * j.f7073e[2];
            default:
                if (f7 < -2.0f) {
                    f7 = -2.0f;
                }
                if (f7 > 2.0f) {
                    return 2.0f;
                }
                return f7;
        }
    }

    @Override // z0.c
    public final long f(float f5, float f6, float f7, float f8, c cVar) {
        switch (this.f7074d) {
            case 0:
                float[] fArr = j.f7073e;
                float f9 = f5 / fArr[0];
                float f10 = f6 / fArr[1];
                float f11 = f7 / fArr[2];
                float fCbrt = f9 > 0.008856452f ? (float) Math.cbrt(f9) : (f9 * 7.787037f) + 0.13793103f;
                float fCbrt2 = f10 > 0.008856452f ? (float) Math.cbrt(f10) : (f10 * 7.787037f) + 0.13793103f;
                float f12 = (116.0f * fCbrt2) - 16.0f;
                float f13 = (fCbrt - fCbrt2) * 500.0f;
                float fCbrt3 = (fCbrt2 - (f11 > 0.008856452f ? (float) Math.cbrt(f11) : (f11 * 7.787037f) + 0.13793103f)) * 200.0f;
                if (f12 < 0.0f) {
                    f12 = 0.0f;
                }
                if (f12 > 100.0f) {
                    f12 = 100.0f;
                }
                if (f13 < -128.0f) {
                    f13 = -128.0f;
                }
                if (f13 > 128.0f) {
                    f13 = 128.0f;
                }
                if (fCbrt3 < -128.0f) {
                    fCbrt3 = -128.0f;
                }
                return l0.b(f12, f13, fCbrt3 <= 128.0f ? fCbrt3 : 128.0f, f8, cVar);
            default:
                if (f5 < -2.0f) {
                    f5 = -2.0f;
                }
                if (f5 > 2.0f) {
                    f5 = 2.0f;
                }
                if (f6 < -2.0f) {
                    f6 = -2.0f;
                }
                if (f6 > 2.0f) {
                    f6 = 2.0f;
                }
                if (f7 < -2.0f) {
                    f7 = -2.0f;
                }
                return l0.b(f5, f6, f7 <= 2.0f ? f7 : 2.0f, f8, cVar);
        }
    }
}

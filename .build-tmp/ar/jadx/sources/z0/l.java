package z0;

import y0.l0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l extends c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final float[] f7075d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final float[] f7076e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final float[] f7077f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final float[] f7078g;

    static {
        float[] fArrH = j.h(new float[]{0.818933f, 0.032984544f, 0.0482003f, 0.36186674f, 0.9293119f, 0.26436627f, -0.12885971f, 0.03614564f, 0.6338517f}, j.c(a.f7030b.f7031a, new float[]{0.964212f, 1.0f, 0.8251883f}, new float[]{0.95042855f, 1.0f, 1.0889004f}));
        f7075d = fArrH;
        float[] fArr = {0.21045426f, 1.9779985f, 0.025904037f, 0.7936178f, -2.4285922f, 0.78277177f, -0.004072047f, 0.4505937f, -0.80867577f};
        f7076e = fArr;
        f7077f = j.g(fArrH);
        f7078g = j.g(fArr);
    }

    @Override // z0.c
    public final float a(int i5) {
        return i5 == 0 ? 1.0f : 0.5f;
    }

    @Override // z0.c
    public final float b(int i5) {
        return i5 == 0 ? 0.0f : -0.5f;
    }

    @Override // z0.c
    public final long d(float f5, float f6, float f7) {
        if (f5 < 0.0f) {
            f5 = 0.0f;
        }
        if (f5 > 1.0f) {
            f5 = 1.0f;
        }
        if (f6 < -0.5f) {
            f6 = -0.5f;
        }
        if (f6 > 0.5f) {
            f6 = 0.5f;
        }
        if (f7 < -0.5f) {
            f7 = -0.5f;
        }
        float f8 = f7 <= 0.5f ? f7 : 0.5f;
        float[] fArr = f7078g;
        float f9 = (fArr[6] * f8) + (fArr[3] * f6) + (fArr[0] * f5);
        float f10 = (fArr[7] * f8) + (fArr[4] * f6) + (fArr[1] * f5);
        float f11 = (fArr[8] * f8) + (fArr[5] * f6) + (fArr[2] * f5);
        float f12 = f9 * f9 * f9;
        float f13 = f10 * f10 * f10;
        float f14 = f11 * f11 * f11;
        float[] fArr2 = f7077f;
        return (((long) Float.floatToRawIntBits((fArr2[7] * f14) + (fArr2[4] * f13) + (fArr2[1] * f12))) & 4294967295L) | (((long) Float.floatToRawIntBits((fArr2[6] * f14) + ((fArr2[3] * f13) + (fArr2[0] * f12)))) << 32);
    }

    @Override // z0.c
    public final float e(float f5, float f6, float f7) {
        if (f5 < 0.0f) {
            f5 = 0.0f;
        }
        if (f5 > 1.0f) {
            f5 = 1.0f;
        }
        if (f6 < -0.5f) {
            f6 = -0.5f;
        }
        if (f6 > 0.5f) {
            f6 = 0.5f;
        }
        if (f7 < -0.5f) {
            f7 = -0.5f;
        }
        float f8 = f7 <= 0.5f ? f7 : 0.5f;
        float[] fArr = f7078g;
        float f9 = (fArr[6] * f8) + (fArr[3] * f6) + (fArr[0] * f5);
        float f10 = (fArr[7] * f8) + (fArr[4] * f6) + (fArr[1] * f5);
        float f11 = (fArr[8] * f8) + (fArr[5] * f6) + (fArr[2] * f5);
        float f12 = f9 * f9 * f9;
        float f13 = f10 * f10 * f10;
        float f14 = f11 * f11 * f11;
        float[] fArr2 = f7077f;
        return (fArr2[8] * f14) + (fArr2[5] * f13) + (fArr2[2] * f12);
    }

    @Override // z0.c
    public final long f(float f5, float f6, float f7, float f8, c cVar) {
        float[] fArr = f7075d;
        float f9 = (fArr[6] * f7) + (fArr[3] * f6) + (fArr[0] * f5);
        float f10 = (fArr[7] * f7) + (fArr[4] * f6) + (fArr[1] * f5);
        float f11 = (fArr[8] * f7) + (fArr[5] * f6) + (fArr[2] * f5);
        float fS = a.a.s(f9);
        float fS2 = a.a.s(f10);
        float fS3 = a.a.s(f11);
        float[] fArr2 = f7076e;
        return l0.b((fArr2[6] * fS3) + (fArr2[3] * fS2) + (fArr2[0] * fS), (fArr2[7] * fS3) + (fArr2[4] * fS2) + (fArr2[1] * fS), (fArr2[8] * fS3) + (fArr2[5] * fS2) + (fArr2[2] * fS), f8, cVar);
    }
}

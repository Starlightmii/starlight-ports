package l;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float[] f3484a;

    static {
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12;
        float f13;
        float[] fArr = new float[101];
        f3484a = fArr;
        float[] fArr2 = new float[101];
        float f14 = 0.0f;
        int i5 = 0;
        float f15 = 0.0f;
        while (true) {
            float f16 = 1.0f;
            if (i5 >= 100) {
                fArr2[100] = 1.0f;
                fArr[100] = 1.0f;
                return;
            }
            float f17 = i5 / 100;
            float f18 = 1.0f;
            while (true) {
                f5 = ((f18 - f14) / 2.0f) + f14;
                f6 = f16 - f5;
                f7 = f5 * 3.0f * f6;
                f8 = f5 * f5 * f5;
                float f19 = (((f5 * 0.35000002f) + (f6 * 0.175f)) * f7) + f8;
                f9 = f16;
                if (Math.abs(f19 - f17) < 1.0E-5d) {
                    break;
                }
                if (f19 > f17) {
                    f18 = f5;
                } else {
                    f14 = f5;
                }
                f16 = f9;
            }
            float f20 = 0.5f;
            fArr[i5] = (((f6 * 0.5f) + f5) * f7) + f8;
            float f21 = f9;
            while (true) {
                f10 = ((f21 - f15) / 2.0f) + f15;
                f11 = f9 - f10;
                f12 = f10 * 3.0f * f11;
                f13 = f10 * f10 * f10;
                float f22 = (((f11 * f20) + f10) * f12) + f13;
                float f23 = f21;
                if (Math.abs(f22 - f17) >= 1.0E-5d) {
                    if (f22 > f17) {
                        f21 = f10;
                    } else {
                        f15 = f10;
                        f21 = f23;
                    }
                    f20 = 0.5f;
                }
            }
            fArr2[i5] = (((f10 * 0.35000002f) + (f11 * 0.175f)) * f12) + f13;
            i5++;
        }
    }

    public static a a(float f5) {
        float f6 = 0.0f;
        float f7 = 1.0f;
        float fL = d4.a.l(f5, 0.0f, 1.0f);
        float f8 = 100;
        int i5 = (int) (f8 * fL);
        if (i5 < 100) {
            float f9 = i5 / f8;
            int i6 = i5 + 1;
            float f10 = i6 / f8;
            float[] fArr = f3484a;
            float f11 = fArr[i5];
            float f12 = (fArr[i6] - f11) / (f10 - f9);
            float f13 = ((fL - f9) * f12) + f11;
            f6 = f12;
            f7 = f13;
        }
        return new a(f7, f6);
    }
}

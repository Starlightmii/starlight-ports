package k1;

import i4.p;
import m.h;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3391a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a[] f3392b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3393c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float[] f3394d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float[] f3395e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float[] f3396f;

    public b() {
        int i5;
        int iC = h.c(1);
        if (iC == 0) {
            i5 = 3;
        } else {
            if (iC != 1) {
                throw new p();
            }
            i5 = 2;
        }
        this.f3391a = i5;
        this.f3392b = new a[20];
        this.f3394d = new float[20];
        this.f3395e = new float[20];
        this.f3396f = new float[3];
    }

    public final void a(float f5, long j5) {
        int i5 = (this.f3393c + 1) % 20;
        this.f3393c = i5;
        a[] aVarArr = this.f3392b;
        a aVar = aVarArr[i5];
        if (aVar != null) {
            aVar.f3389a = j5;
            aVar.f3390b = f5;
        } else {
            a aVar2 = new a();
            aVar2.f3389a = j5;
            aVar2.f3390b = f5;
            aVarArr[i5] = aVar2;
        }
    }

    public final float b(float f5) {
        float[] fArr;
        float[] fArr2;
        float f6;
        float fSignum;
        float f7 = f5;
        float f8 = 0.0f;
        if (f7 <= 0.0f) {
            d4.a.L("maximumVelocity should be a positive value. You specified=" + f7);
            throw null;
        }
        int i5 = this.f3393c;
        a[] aVarArr = this.f3392b;
        a aVar = aVarArr[i5];
        if (aVar == null) {
            f6 = 0.0f;
        } else {
            int i6 = 0;
            a aVar2 = aVar;
            while (true) {
                a aVar3 = aVarArr[i5];
                fArr = this.f3394d;
                fArr2 = this.f3395e;
                if (aVar3 == null) {
                    f6 = f8;
                    break;
                }
                long j5 = aVar.f3389a;
                long j6 = aVar3.f3389a;
                float f9 = j5 - j6;
                f6 = f8;
                int i7 = i5;
                float fAbs = Math.abs(j6 - aVar2.f3389a);
                if (f9 > 100.0f || fAbs > 40.0f) {
                    break;
                }
                fArr[i6] = aVar3.f3390b;
                fArr2[i6] = -f9;
                i5 = (i7 == 0 ? 20 : i7) - 1;
                i6++;
                if (i6 >= 20) {
                    break;
                }
                aVar2 = aVar3;
                f8 = f6;
            }
            if (i6 >= this.f3391a) {
                int iC = h.c(1);
                if (iC == 0) {
                    try {
                        float[] fArr3 = this.f3396f;
                        a.a.G(fArr2, fArr, i6, fArr3);
                        fSignum = fArr3[1];
                    } catch (IllegalArgumentException unused) {
                        fSignum = f6;
                    }
                } else {
                    if (iC != 1) {
                        throw new p();
                    }
                    int i8 = i6 - 1;
                    float f10 = fArr2[i8];
                    int i9 = i8;
                    float fAbs2 = f6;
                    while (i9 > 0) {
                        int i10 = i9 - 1;
                        float f11 = fArr2[i10];
                        if (f10 != f11) {
                            float f12 = (fArr[i9] - fArr[i10]) / (f10 - f11);
                            fAbs2 += Math.abs(f12) * (f12 - (Math.signum(fAbs2) * ((float) Math.sqrt(Math.abs(fAbs2) * 2))));
                            if (i9 == i8) {
                                fAbs2 *= 0.5f;
                            }
                        }
                        i9--;
                        f10 = f11;
                    }
                    fSignum = Math.signum(fAbs2) * ((float) Math.sqrt(Math.abs(fAbs2) * 2));
                }
                f8 = fSignum * 1000;
            } else {
                f8 = f6;
            }
        }
        if (f8 == f6 || Float.isNaN(f8)) {
            return f6;
        }
        if (f8 <= f6) {
            f7 = -f7;
            if (f8 >= f7) {
                return f8;
            }
        } else if (f8 <= f7) {
            f7 = f8;
        }
        return f7;
    }
}

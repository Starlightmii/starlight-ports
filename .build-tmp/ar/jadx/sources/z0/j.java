package z0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final s f7069a = new s(0.31006f, 0.31616f);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final s f7070b = new s(0.34567f, 0.3585f);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final s f7071c = new s(0.32168f, 0.33767f);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final s f7072d = new s(0.31271f, 0.32902f);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final float[] f7073e = {0.964212f, 1.0f, 0.825188f};

    public static c a(c cVar) {
        if (b.a(cVar.f7038b, b.f7032a)) {
            q qVar = (q) cVar;
            s sVar = qVar.f7088d;
            s sVar2 = f7070b;
            if (!d(sVar, sVar2)) {
                return new q(qVar.f7037a, qVar.f7092h, sVar2, h(c(a.f7030b.f7031a, sVar.a(), sVar2.a()), qVar.f7093i), qVar.f7095k, qVar.f7098n, qVar.f7089e, qVar.f7090f, qVar.f7091g, -1);
            }
        }
        return cVar;
    }

    public static float b(float[] fArr) {
        float f5 = fArr[0];
        float f6 = fArr[1];
        float f7 = fArr[2];
        float f8 = fArr[3];
        float f9 = fArr[4];
        float f10 = fArr[5];
        float f11 = (((((f7 * f10) + ((f6 * f9) + (f5 * f8))) - (f8 * f9)) - (f6 * f7)) - (f5 * f10)) * 0.5f;
        return f11 < 0.0f ? -f11 : f11;
    }

    public static final float[] c(float[] fArr, float[] fArr2, float[] fArr3) {
        i(fArr, fArr2);
        i(fArr, fArr3);
        float[] fArr4 = {fArr3[0] / fArr2[0], fArr3[1] / fArr2[1], fArr3[2] / fArr2[2]};
        float[] fArrG = g(fArr);
        float f5 = fArr4[0];
        float f6 = fArr[0] * f5;
        float f7 = fArr4[1];
        float f8 = fArr[1] * f7;
        float f9 = fArr4[2];
        return h(fArrG, new float[]{f6, f8, fArr[2] * f9, fArr[3] * f5, fArr[4] * f7, fArr[5] * f9, f5 * fArr[6], f7 * fArr[7], f9 * fArr[8]});
    }

    public static final boolean d(s sVar, s sVar2) {
        if (sVar == sVar2) {
            return true;
        }
        return Math.abs(sVar.f7109a - sVar2.f7109a) < 0.001f && Math.abs(sVar.f7110b - sVar2.f7110b) < 0.001f;
    }

    public static final g e(c cVar, c cVar2) {
        if (cVar == cVar2) {
            return new e(cVar, cVar, 1);
        }
        long j5 = cVar.f7038b;
        long j6 = b.f7032a;
        return (b.a(j5, j6) && b.a(cVar2.f7038b, j6)) ? new f((q) cVar, (q) cVar2) : new g(cVar, cVar2, 0);
    }

    public static float f(float f5, float f6, float f7, float f8) {
        return (f5 * f8) - (f6 * f7);
    }

    public static final float[] g(float[] fArr) {
        float f5 = fArr[0];
        float f6 = fArr[3];
        float f7 = fArr[6];
        float f8 = fArr[1];
        float f9 = fArr[4];
        float f10 = fArr[7];
        float f11 = fArr[2];
        float f12 = fArr[5];
        float f13 = fArr[8];
        float f14 = (f9 * f13) - (f10 * f12);
        float f15 = (f10 * f11) - (f8 * f13);
        float f16 = (f8 * f12) - (f9 * f11);
        float f17 = (f7 * f16) + (f6 * f15) + (f5 * f14);
        float[] fArr2 = new float[fArr.length];
        fArr2[0] = f14 / f17;
        fArr2[1] = f15 / f17;
        fArr2[2] = f16 / f17;
        fArr2[3] = ((f7 * f12) - (f6 * f13)) / f17;
        fArr2[4] = ((f13 * f5) - (f7 * f11)) / f17;
        fArr2[5] = ((f11 * f6) - (f12 * f5)) / f17;
        fArr2[6] = ((f6 * f10) - (f7 * f9)) / f17;
        fArr2[7] = ((f7 * f8) - (f10 * f5)) / f17;
        fArr2[8] = ((f5 * f9) - (f6 * f8)) / f17;
        return fArr2;
    }

    public static final float[] h(float[] fArr, float[] fArr2) {
        float f5 = fArr[0];
        float f6 = fArr2[0];
        float f7 = fArr[3];
        float f8 = fArr2[1];
        float f9 = fArr[6];
        float f10 = fArr2[2];
        float f11 = (f9 * f10) + (f7 * f8) + (f5 * f6);
        float f12 = fArr[1];
        float f13 = fArr[4];
        float f14 = fArr[7];
        float f15 = (f14 * f10) + (f13 * f8) + (f12 * f6);
        float f16 = fArr[2];
        float f17 = fArr[5];
        float f18 = fArr[8];
        float f19 = (f10 * f18) + (f8 * f17) + (f6 * f16);
        float f20 = fArr2[3];
        float f21 = fArr2[4];
        float f22 = fArr2[5];
        float f23 = (f9 * f22) + (f7 * f21) + (f5 * f20);
        float f24 = (f14 * f22) + (f13 * f21) + (f12 * f20);
        float f25 = (f22 * f18) + (f21 * f17) + (f20 * f16);
        float f26 = fArr2[6];
        float f27 = fArr2[7];
        float f28 = (f7 * f27) + (f5 * f26);
        float f29 = fArr2[8];
        return new float[]{f11, f15, f19, f23, f24, f25, (f9 * f29) + f28, (f14 * f29) + (f13 * f27) + (f12 * f26), (f18 * f29) + (f17 * f27) + (f16 * f26)};
    }

    public static final void i(float[] fArr, float[] fArr2) {
        float f5 = fArr2[0];
        float f6 = fArr2[1];
        float f7 = fArr2[2];
        fArr2[0] = (fArr[6] * f7) + (fArr[3] * f6) + (fArr[0] * f5);
        fArr2[1] = (fArr[7] * f7) + (fArr[4] * f6) + (fArr[1] * f5);
        fArr2[2] = (fArr[8] * f7) + (fArr[5] * f6) + (fArr[2] * f5);
    }
}

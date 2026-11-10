package y0;

import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float[] f6626a;

    public static float[] a() {
        return new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    }

    public static final long b(long j5, float[] fArr) {
        float fD = x0.c.d(j5);
        float fE = x0.c.e(j5);
        float f5 = 1 / (((fArr[7] * fE) + (fArr[3] * fD)) + fArr[15]);
        if (Float.isInfinite(f5) || Float.isNaN(f5)) {
            f5 = 0.0f;
        }
        return p3.j.b(((fArr[4] * fE) + (fArr[0] * fD) + fArr[12]) * f5, ((fArr[5] * fE) + (fArr[1] * fD) + fArr[13]) * f5);
    }

    public static final void c(float[] fArr, x0.b bVar) {
        long jB = b(p3.j.b(bVar.f6528a, bVar.f6529b), fArr);
        long jB2 = b(p3.j.b(bVar.f6528a, bVar.f6531d), fArr);
        long jB3 = b(p3.j.b(bVar.f6530c, bVar.f6529b), fArr);
        long jB4 = b(p3.j.b(bVar.f6530c, bVar.f6531d), fArr);
        bVar.f6528a = Math.min(Math.min(x0.c.d(jB), x0.c.d(jB2)), Math.min(x0.c.d(jB3), x0.c.d(jB4)));
        bVar.f6529b = Math.min(Math.min(x0.c.e(jB), x0.c.e(jB2)), Math.min(x0.c.e(jB3), x0.c.e(jB4)));
        bVar.f6530c = Math.max(Math.max(x0.c.d(jB), x0.c.d(jB2)), Math.max(x0.c.d(jB3), x0.c.d(jB4)));
        bVar.f6531d = Math.max(Math.max(x0.c.e(jB), x0.c.e(jB2)), Math.max(x0.c.e(jB3), x0.c.e(jB4)));
    }

    public static final void d(float[] fArr) {
        int i5 = 0;
        while (i5 < 4) {
            int i6 = 0;
            while (i6 < 4) {
                fArr[(i6 * 4) + i5] = i5 == i6 ? 1.0f : 0.0f;
                i6++;
            }
            i5++;
        }
    }

    public static final void e(float[] fArr, float f5) {
        double d5 = (((double) f5) * 3.141592653589793d) / 180.0d;
        float fCos = (float) Math.cos(d5);
        float fSin = (float) Math.sin(d5);
        float f6 = fArr[0];
        float f7 = fArr[4];
        float f8 = (fSin * f7) + (fCos * f6);
        float f9 = -fSin;
        float f10 = (f7 * fCos) + (f6 * f9);
        float f11 = fArr[1];
        float f12 = fArr[5];
        float f13 = (fSin * f12) + (fCos * f11);
        float f14 = (f12 * fCos) + (f11 * f9);
        float f15 = fArr[2];
        float f16 = fArr[6];
        float f17 = (fSin * f16) + (fCos * f15);
        float f18 = (f16 * fCos) + (f15 * f9);
        float f19 = fArr[3];
        float f20 = fArr[7];
        float f21 = (fSin * f20) + (fCos * f19);
        fArr[0] = f8;
        fArr[1] = f13;
        fArr[2] = f17;
        fArr[3] = f21;
        fArr[4] = f10;
        fArr[5] = f14;
        fArr[6] = f18;
        fArr[7] = (fCos * f20) + (f9 * f19);
    }

    public static final void f(float[] fArr, float f5, float f6) {
        fArr[0] = fArr[0] * f5;
        fArr[1] = fArr[1] * f5;
        fArr[2] = fArr[2] * f5;
        fArr[3] = fArr[3] * f5;
        fArr[4] = fArr[4] * f6;
        fArr[5] = fArr[5] * f6;
        fArr[6] = fArr[6] * f6;
        fArr[7] = fArr[7] * f6;
        fArr[8] = fArr[8] * 1.0f;
        fArr[9] = fArr[9] * 1.0f;
        fArr[10] = fArr[10] * 1.0f;
        fArr[11] = fArr[11] * 1.0f;
    }

    public static final void g(float[] fArr, float[] fArr2) {
        float fJ = l0.j(fArr, 0, fArr2, 0);
        float fJ2 = l0.j(fArr, 0, fArr2, 1);
        float fJ3 = l0.j(fArr, 0, fArr2, 2);
        float fJ4 = l0.j(fArr, 0, fArr2, 3);
        float fJ5 = l0.j(fArr, 1, fArr2, 0);
        float fJ6 = l0.j(fArr, 1, fArr2, 1);
        float fJ7 = l0.j(fArr, 1, fArr2, 2);
        float fJ8 = l0.j(fArr, 1, fArr2, 3);
        float fJ9 = l0.j(fArr, 2, fArr2, 0);
        float fJ10 = l0.j(fArr, 2, fArr2, 1);
        float fJ11 = l0.j(fArr, 2, fArr2, 2);
        float fJ12 = l0.j(fArr, 2, fArr2, 3);
        float fJ13 = l0.j(fArr, 3, fArr2, 0);
        float fJ14 = l0.j(fArr, 3, fArr2, 1);
        float fJ15 = l0.j(fArr, 3, fArr2, 2);
        float fJ16 = l0.j(fArr, 3, fArr2, 3);
        fArr[0] = fJ;
        fArr[1] = fJ2;
        fArr[2] = fJ3;
        fArr[3] = fJ4;
        fArr[4] = fJ5;
        fArr[5] = fJ6;
        fArr[6] = fJ7;
        fArr[7] = fJ8;
        fArr[8] = fJ9;
        fArr[9] = fJ10;
        fArr[10] = fJ11;
        fArr[11] = fJ12;
        fArr[12] = fJ13;
        fArr[13] = fJ14;
        fArr[14] = fJ15;
        fArr[15] = fJ16;
    }

    public static final void h(float[] fArr, float f5, float f6) {
        float f7 = (fArr[8] * 0.0f) + (fArr[4] * f6) + (fArr[0] * f5) + fArr[12];
        float f8 = (fArr[9] * 0.0f) + (fArr[5] * f6) + (fArr[1] * f5) + fArr[13];
        float f9 = (fArr[10] * 0.0f) + (fArr[6] * f6) + (fArr[2] * f5) + fArr[14];
        float f10 = (fArr[11] * 0.0f) + (fArr[7] * f6) + (fArr[3] * f5) + fArr[15];
        fArr[12] = f7;
        fArr[13] = f8;
        fArr[14] = f9;
        fArr[15] = f10;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof f0) {
            return b4.l.a(this.f6626a, ((f0) obj).f6626a);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f6626a);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("\n            |");
        float[] fArr = this.f6626a;
        sb.append(fArr[0]);
        sb.append(' ');
        sb.append(fArr[1]);
        sb.append(' ');
        sb.append(fArr[2]);
        sb.append(' ');
        sb.append(fArr[3]);
        sb.append("|\n            |");
        sb.append(fArr[4]);
        sb.append(' ');
        sb.append(fArr[5]);
        sb.append(' ');
        sb.append(fArr[6]);
        sb.append(' ');
        sb.append(fArr[7]);
        sb.append("|\n            |");
        sb.append(fArr[8]);
        sb.append(' ');
        sb.append(fArr[9]);
        sb.append(' ');
        sb.append(fArr[10]);
        sb.append(' ');
        sb.append(fArr[11]);
        sb.append("|\n            |");
        sb.append(fArr[12]);
        sb.append(' ');
        sb.append(fArr[13]);
        sb.append(' ');
        sb.append(fArr[14]);
        sb.append(' ');
        sb.append(fArr[15]);
        sb.append("|\n        ");
        return h4.b.P(sb.toString());
    }
}

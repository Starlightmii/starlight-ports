package f0;

import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f2388a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2389b;

    public w0() {
        this.f2388a = new int[10];
    }

    public w0(int i5) {
        this.f2388a = new int[i5];
    }

    public int a(int i5) {
        int i6 = this.f2389b - 1;
        return i6 >= 0 ? this.f2388a[i6] : i5;
    }

    public int b() {
        int[] iArr = this.f2388a;
        int i5 = this.f2389b - 1;
        this.f2389b = i5;
        return iArr[i5];
    }

    public void c(int i5) {
        int[] iArrCopyOf = this.f2388a;
        if (this.f2389b >= iArrCopyOf.length) {
            iArrCopyOf = Arrays.copyOf(iArrCopyOf, iArrCopyOf.length * 2);
            b4.l.d("copyOf(...)", iArrCopyOf);
            this.f2388a = iArrCopyOf;
        }
        int i6 = this.f2389b;
        this.f2389b = i6 + 1;
        iArrCopyOf[i6] = i5;
    }

    public void d(int i5, int i6, int i7) {
        int i8 = this.f2389b;
        int i9 = i8 + 3;
        int[] iArr = this.f2388a;
        if (i9 >= iArr.length) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length * 2);
            b4.l.d("copyOf(this, newSize)", iArrCopyOf);
            this.f2388a = iArrCopyOf;
        }
        int[] iArr2 = this.f2388a;
        iArr2[i8] = i5 + i7;
        iArr2[i8 + 1] = i6 + i7;
        iArr2[i8 + 2] = i7;
        this.f2389b = i9;
    }

    public void e(int i5, int i6, int i7, int i8) {
        int i9 = this.f2389b;
        int i10 = i9 + 4;
        int[] iArr = this.f2388a;
        if (i10 >= iArr.length) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length * 2);
            b4.l.d("copyOf(this, newSize)", iArrCopyOf);
            this.f2388a = iArrCopyOf;
        }
        int[] iArr2 = this.f2388a;
        iArr2[i9] = i5;
        iArr2[i9 + 1] = i6;
        iArr2[i9 + 2] = i7;
        iArr2[i9 + 3] = i8;
        this.f2389b = i10;
    }

    public void f(int i5, int i6) {
        if (i5 < i6) {
            int i7 = i5 - 3;
            for (int i8 = i5; i8 < i6; i8 += 3) {
                int[] iArr = this.f2388a;
                int i9 = iArr[i8];
                int i10 = iArr[i6];
                if (i9 < i10 || (i9 == i10 && iArr[i8 + 1] <= iArr[i6 + 1])) {
                    i7 += 3;
                    g(i7, i8);
                }
            }
            g(i7 + 3, i6);
            f(i5, i7);
            f(i7 + 6, i6);
        }
    }

    public void g(int i5, int i6) {
        int[] iArr = this.f2388a;
        int i7 = iArr[i5];
        iArr[i5] = iArr[i6];
        iArr[i6] = i7;
        int i8 = i5 + 1;
        int i9 = i6 + 1;
        int i10 = iArr[i8];
        iArr[i8] = iArr[i9];
        iArr[i9] = i10;
        int i11 = i5 + 2;
        int i12 = i6 + 2;
        int i13 = iArr[i11];
        iArr[i11] = iArr[i12];
        iArr[i12] = i13;
    }
}

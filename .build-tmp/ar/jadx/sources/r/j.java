package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f5571a = new b(2);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final g f5572b = new g();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final d f5573c = new d(0);

    static {
        new d(3);
        new d(2);
        new d(1);
    }

    public static void a(int i5, int[] iArr, int[] iArr2, boolean z2) {
        int i6 = 0;
        int i7 = 0;
        for (int i8 : iArr) {
            i7 += i8;
        }
        float f5 = (i5 - i7) / 2;
        if (!z2) {
            int length = iArr.length;
            int i9 = 0;
            while (i6 < length) {
                int i10 = iArr[i6];
                iArr2[i9] = Math.round(f5);
                f5 += i10;
                i6++;
                i9++;
            }
            return;
        }
        int length2 = iArr.length;
        while (true) {
            length2--;
            if (-1 >= length2) {
                return;
            }
            int i11 = iArr[length2];
            iArr2[length2] = Math.round(f5);
            f5 += i11;
        }
    }

    public static void b(int[] iArr, int[] iArr2, boolean z2) {
        int i5 = 0;
        if (!z2) {
            int length = iArr.length;
            int i6 = 0;
            int i7 = 0;
            while (i5 < length) {
                int i8 = iArr[i5];
                iArr2[i6] = i7;
                i7 += i8;
                i5++;
                i6++;
            }
            return;
        }
        int length2 = iArr.length;
        while (true) {
            length2--;
            if (-1 >= length2) {
                return;
            }
            int i9 = iArr[length2];
            iArr2[length2] = i5;
            i5 += i9;
        }
    }

    public static void c(int i5, int[] iArr, int[] iArr2, boolean z2) {
        int i6 = 0;
        int i7 = 0;
        for (int i8 : iArr) {
            i7 += i8;
        }
        int i9 = i5 - i7;
        if (!z2) {
            int length = iArr.length;
            int i10 = 0;
            while (i6 < length) {
                int i11 = iArr[i6];
                iArr2[i10] = i9;
                i9 += i11;
                i6++;
                i10++;
            }
            return;
        }
        int length2 = iArr.length;
        while (true) {
            length2--;
            if (-1 >= length2) {
                return;
            }
            int i12 = iArr[length2];
            iArr2[length2] = i9;
            i9 += i12;
        }
    }

    public static void d(int i5, int[] iArr, int[] iArr2, boolean z2) {
        int i6 = 0;
        int i7 = 0;
        for (int i8 : iArr) {
            i7 += i8;
        }
        float length = iArr.length == 0 ? 0.0f : (i5 - i7) / iArr.length;
        float f5 = length / 2;
        if (!z2) {
            int length2 = iArr.length;
            int i9 = 0;
            while (i6 < length2) {
                int i10 = iArr[i6];
                iArr2[i9] = Math.round(f5);
                f5 += i10 + length;
                i6++;
                i9++;
            }
            return;
        }
        int length3 = iArr.length;
        while (true) {
            length3--;
            if (-1 >= length3) {
                return;
            }
            int i11 = iArr[length3];
            iArr2[length3] = Math.round(f5);
            f5 += i11 + length;
        }
    }

    public static void e(int i5, int[] iArr, int[] iArr2, boolean z2) {
        if (iArr.length == 0) {
            return;
        }
        int i6 = 0;
        int i7 = 0;
        for (int i8 : iArr) {
            i7 += i8;
        }
        float fMax = (i5 - i7) / Math.max(iArr.length - 1, 1);
        float f5 = (z2 && iArr.length == 1) ? fMax : 0.0f;
        if (z2) {
            for (int length = iArr.length - 1; -1 < length; length--) {
                int i9 = iArr[length];
                iArr2[length] = Math.round(f5);
                f5 += i9 + fMax;
            }
            return;
        }
        int length2 = iArr.length;
        int i10 = 0;
        while (i6 < length2) {
            int i11 = iArr[i6];
            iArr2[i10] = Math.round(f5);
            f5 += i11 + fMax;
            i6++;
            i10++;
        }
    }

    public static void f(int i5, int[] iArr, int[] iArr2, boolean z2) {
        int i6 = 0;
        int i7 = 0;
        for (int i8 : iArr) {
            i7 += i8;
        }
        float length = (i5 - i7) / (iArr.length + 1);
        if (z2) {
            float f5 = length;
            for (int length2 = iArr.length - 1; -1 < length2; length2--) {
                int i9 = iArr[length2];
                iArr2[length2] = Math.round(f5);
                f5 += i9 + length;
            }
            return;
        }
        int length3 = iArr.length;
        float f6 = length;
        int i10 = 0;
        while (i6 < length3) {
            int i11 = iArr[i6];
            iArr2[i10] = Math.round(f6);
            f6 += i11 + length;
            i6++;
            i10++;
        }
    }
}

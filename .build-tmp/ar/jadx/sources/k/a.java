package k;

import b4.l;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f3353a = new int[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long[] f3354b = new long[0];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object[] f3355c = new Object[0];

    public static final int a(int[] iArr, int i5, int i6) {
        l.e("array", iArr);
        int i7 = i5 - 1;
        int i8 = 0;
        while (i8 <= i7) {
            int i9 = (i8 + i7) >>> 1;
            int i10 = iArr[i9];
            if (i10 < i6) {
                i8 = i9 + 1;
            } else {
                if (i10 <= i6) {
                    return i9;
                }
                i7 = i9 - 1;
            }
        }
        return ~i8;
    }

    public static final int b(long[] jArr, int i5, long j5) {
        l.e("array", jArr);
        int i6 = i5 - 1;
        int i7 = 0;
        while (i7 <= i6) {
            int i8 = (i7 + i6) >>> 1;
            long j6 = jArr[i8];
            if (j6 < j5) {
                i7 = i8 + 1;
            } else {
                if (j6 <= j5) {
                    return i8;
                }
                i6 = i8 - 1;
            }
        }
        return ~i7;
    }

    public static final void c(String str) {
        l.e("message", str);
        throw new IllegalArgumentException(str);
    }

    public static final void d(String str) {
        l.e("message", str);
        throw new IndexOutOfBoundsException(str);
    }

    public static final void e(String str) {
        l.e("message", str);
        throw new NoSuchElementException(str);
    }
}

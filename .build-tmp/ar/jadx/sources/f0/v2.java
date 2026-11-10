package f0;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class v2 {
    public static final int a(int[] iArr, int i5) {
        return iArr[(i5 * 5) + 3];
    }

    public static final int b(ArrayList arrayList, int i5, int i6) {
        int iE = e(arrayList, i5, i6);
        return iE >= 0 ? iE : -(iE + 1);
    }

    public static final int c(int[] iArr, int i5) {
        int i6 = i5 * 5;
        return Integer.bitCount(iArr[i6 + 1] >> 28) + iArr[i6 + 4];
    }

    public static final void d(int[] iArr, int i5, int i6) {
        if (i6 >= 0) {
        }
        int i7 = (i5 * 5) + 1;
        iArr[i7] = i6 | (iArr[i7] & (-67108864));
    }

    public static final int e(ArrayList arrayList, int i5, int i6) {
        int size = arrayList.size() - 1;
        int i7 = 0;
        while (i7 <= size) {
            int i8 = (i7 + size) >>> 1;
            int i9 = ((a) arrayList.get(i8)).f2082a;
            if (i9 < 0) {
                i9 += i6;
            }
            int iF = b4.l.f(i9, i5);
            if (iF < 0) {
                i7 = i8 + 1;
            } else {
                if (iF <= 0) {
                    return i8;
                }
                size = i8 - 1;
            }
        }
        return -(i7 + 1);
    }

    public static final void f() {
        throw new ConcurrentModificationException();
    }
}

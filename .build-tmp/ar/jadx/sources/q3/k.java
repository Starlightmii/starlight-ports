package q3;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class k extends q.f {
    public static void A(Object[] objArr, Object[] objArr2, int i5, int i6, int i7) {
        b4.l.e("<this>", objArr);
        b4.l.e("destination", objArr2);
        System.arraycopy(objArr, i6, objArr2, i5, i7 - i6);
    }

    public static /* synthetic */ void B(int[] iArr, int[] iArr2, int i5, int i6, int i7) {
        if ((i7 & 2) != 0) {
            i5 = 0;
        }
        if ((i7 & 8) != 0) {
            i6 = iArr.length;
        }
        y(iArr, iArr2, i5, 0, i6);
    }

    public static /* synthetic */ void C(Object[] objArr, Object[] objArr2, int i5, int i6, int i7) {
        if ((i7 & 4) != 0) {
            i5 = 0;
        }
        A(objArr, objArr2, 0, i5, i6);
    }

    public static Object[] D(Object[] objArr, int i5, int i6) {
        b4.l.e("<this>", objArr);
        int length = objArr.length;
        if (i6 <= length) {
            Object[] objArrCopyOfRange = Arrays.copyOfRange(objArr, i5, i6);
            b4.l.d("copyOfRange(...)", objArrCopyOfRange);
            return objArrCopyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i6 + ") is greater than size (" + length + ").");
    }

    public static void E(Object[] objArr, int i5, int i6) {
        b4.l.e("<this>", objArr);
        Arrays.fill(objArr, i5, i6, (Object) null);
    }

    public static void F(long[] jArr) {
        int length = jArr.length;
        b4.l.e("<this>", jArr);
        Arrays.fill(jArr, 0, length, -9187201950435737472L);
    }

    public static int G(long[] jArr) {
        b4.l.e("<this>", jArr);
        return jArr.length - 1;
    }

    public static int H(Object[] objArr, Object obj) {
        int i5 = 0;
        if (obj == null) {
            int length = objArr.length;
            while (i5 < length) {
                if (objArr[i5] == null) {
                    return i5;
                }
                i5++;
            }
            return -1;
        }
        int length2 = objArr.length;
        while (i5 < length2) {
            if (obj.equals(objArr[i5])) {
                return i5;
            }
            i5++;
        }
        return -1;
    }

    public static void x(char[] cArr, char[] cArr2, int i5, int i6, int i7) {
        b4.l.e("<this>", cArr);
        b4.l.e("destination", cArr2);
        System.arraycopy(cArr, i6, cArr2, i5, i7 - i6);
    }

    public static void y(int[] iArr, int[] iArr2, int i5, int i6, int i7) {
        b4.l.e("<this>", iArr);
        b4.l.e("destination", iArr2);
        System.arraycopy(iArr, i6, iArr2, i5, i7 - i6);
    }

    public static void z(long[] jArr, long[] jArr2, int i5, int i6, int i7) {
        b4.l.e("<this>", jArr);
        b4.l.e("destination", jArr2);
        System.arraycopy(jArr, i6, jArr2, i5, i7 - i6);
    }
}

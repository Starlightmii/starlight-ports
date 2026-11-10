package k2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f3400a;

    public /* synthetic */ a(long j5) {
        this.f3400a = j5;
    }

    public static final long a(int i5, int i6, int i7, int i8) {
        if (i7 < 0 || i5 < 0) {
            d4.a.J("minHeight(" + i7 + ") and minWidth(" + i5 + ") must be >= 0");
            throw null;
        }
        if (i6 < i5) {
            d4.a.J("maxWidth(" + i6 + ") must be >= minWidth(" + i5 + ')');
            throw null;
        }
        if (i8 >= i7) {
            return h1.c.r(i5, i6, i7, i8);
        }
        d4.a.J("maxHeight(" + i8 + ") must be >= minHeight(" + i7 + ')');
        throw null;
    }

    public static /* synthetic */ long b(long j5, int i5, int i6, int i7, int i8, int i9) {
        if ((i9 & 1) != 0) {
            i5 = k(j5);
        }
        if ((i9 & 2) != 0) {
            i6 = i(j5);
        }
        if ((i9 & 4) != 0) {
            i7 = j(j5);
        }
        if ((i9 & 8) != 0) {
            i8 = h(j5);
        }
        return a(i5, i6, i7, i8);
    }

    public static final boolean c(long j5, long j6) {
        return j5 == j6;
    }

    public static final boolean d(long j5) {
        int i5 = (int) (3 & j5);
        int i6 = (((i5 & 2) >> 1) * 3) + ((i5 & 1) << 1);
        return (((int) (j5 >> (i6 + 46))) & ((1 << (18 - i6)) - 1)) != 0;
    }

    public static final boolean e(long j5) {
        int i5 = (int) (3 & j5);
        return (((int) (j5 >> 33)) & ((1 << (((((i5 & 2) >> 1) * 3) + ((i5 & 1) << 1)) + 13)) - 1)) != 0;
    }

    public static final boolean f(long j5) {
        int i5 = (int) (3 & j5);
        int i6 = (((i5 & 2) >> 1) * 3) + ((i5 & 1) << 1);
        int i7 = (1 << (18 - i6)) - 1;
        int i8 = ((int) (j5 >> (i6 + 15))) & i7;
        int i9 = ((int) (j5 >> (i6 + 46))) & i7;
        return i8 == (i9 == 0 ? Integer.MAX_VALUE : i9 - 1);
    }

    public static final boolean g(long j5) {
        int i5 = (int) (3 & j5);
        int i6 = (1 << (((((i5 & 2) >> 1) * 3) + ((i5 & 1) << 1)) + 13)) - 1;
        int i7 = ((int) (j5 >> 2)) & i6;
        int i8 = ((int) (j5 >> 33)) & i6;
        return i7 == (i8 == 0 ? Integer.MAX_VALUE : i8 - 1);
    }

    public static final int h(long j5) {
        int i5 = (int) (3 & j5);
        int i6 = (((i5 & 2) >> 1) * 3) + ((i5 & 1) << 1);
        int i7 = ((int) (j5 >> (i6 + 46))) & ((1 << (18 - i6)) - 1);
        if (i7 == 0) {
            return Integer.MAX_VALUE;
        }
        return i7 - 1;
    }

    public static final int i(long j5) {
        int i5 = (int) (3 & j5);
        int i6 = (int) (j5 >> 33);
        int i7 = i6 & ((1 << (((((i5 & 2) >> 1) * 3) + ((i5 & 1) << 1)) + 13)) - 1);
        if (i7 == 0) {
            return Integer.MAX_VALUE;
        }
        return i7 - 1;
    }

    public static final int j(long j5) {
        int i5 = (int) (3 & j5);
        int i6 = (((i5 & 2) >> 1) * 3) + ((i5 & 1) << 1);
        return ((int) (j5 >> (i6 + 15))) & ((1 << (18 - i6)) - 1);
    }

    public static final int k(long j5) {
        int i5 = (int) (3 & j5);
        return ((int) (j5 >> 2)) & ((1 << (((((i5 & 2) >> 1) * 3) + ((i5 & 1) << 1)) + 13)) - 1);
    }

    public static String l(long j5) {
        int i5 = i(j5);
        String strValueOf = i5 == Integer.MAX_VALUE ? "Infinity" : String.valueOf(i5);
        int iH = h(j5);
        return "Constraints(minWidth = " + k(j5) + ", maxWidth = " + strValueOf + ", minHeight = " + j(j5) + ", maxHeight = " + (iH != Integer.MAX_VALUE ? String.valueOf(iH) : "Infinity") + ')';
    }

    public final boolean equals(Object obj) {
        if (obj instanceof a) {
            return this.f3400a == ((a) obj).f3400a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f3400a);
    }

    public final String toString() {
        return l(this.f3400a);
    }
}

package x0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f6546a;

    public static final boolean a(long j5, long j6) {
        return j5 == j6;
    }

    public static final float b(long j5) {
        if (j5 != 9205357640488583168L) {
            return Float.intBitsToFloat((int) (j5 & 4294967295L));
        }
        throw new IllegalStateException("Size is unspecified");
    }

    public static final float c(long j5) {
        if (j5 != 9205357640488583168L) {
            return Math.min(Float.intBitsToFloat((int) ((j5 >> 32) & 2147483647L)), Float.intBitsToFloat((int) (j5 & 2147483647L)));
        }
        throw new IllegalStateException("Size is unspecified");
    }

    public static final float d(long j5) {
        if (j5 != 9205357640488583168L) {
            return Float.intBitsToFloat((int) (j5 >> 32));
        }
        throw new IllegalStateException("Size is unspecified");
    }

    public static final boolean e(long j5) {
        if (j5 == 9205357640488583168L) {
            throw new IllegalStateException("Size is unspecified");
        }
        long j6 = j5 & (~((((-9223372034707292160L) & j5) >>> 31) * ((long) (-1))));
        return ((j6 & 4294967295L) & (j6 >>> 32)) == 0;
    }

    public static String f(long j5) {
        if (j5 == 9205357640488583168L) {
            return "Size.Unspecified";
        }
        return "Size(" + q.f.v(d(j5)) + ", " + q.f.v(b(j5)) + ')';
    }

    public final boolean equals(Object obj) {
        if (obj instanceof f) {
            return this.f6546a == ((f) obj).f6546a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f6546a);
    }

    public final String toString() {
        return f(this.f6546a);
    }
}

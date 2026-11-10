package k2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f3424a;

    public static long a(long j5, float f5, float f6, int i5) {
        if ((i5 & 1) != 0) {
            f5 = Float.intBitsToFloat((int) (j5 >> 32));
        }
        if ((i5 & 2) != 0) {
            f6 = Float.intBitsToFloat((int) (j5 & 4294967295L));
        }
        return (((long) Float.floatToRawIntBits(f5)) << 32) | (((long) Float.floatToRawIntBits(f6)) & 4294967295L);
    }

    public static final float b(long j5) {
        return Float.intBitsToFloat((int) (j5 >> 32));
    }

    public static final float c(long j5) {
        return Float.intBitsToFloat((int) (j5 & 4294967295L));
    }

    public static final long d(long j5, long j6) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j5 >> 32)) - Float.intBitsToFloat((int) (j6 >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j5 & 4294967295L)) - Float.intBitsToFloat((int) (j6 & 4294967295L));
        return (((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & 4294967295L);
    }

    public static final long e(long j5, long j6) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j6 >> 32)) + Float.intBitsToFloat((int) (j5 >> 32));
        return (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (j6 & 4294967295L)) + Float.intBitsToFloat((int) (j5 & 4294967295L)))) & 4294967295L) | (Float.floatToRawIntBits(fIntBitsToFloat) << 32);
    }

    public static String f(long j5) {
        return "(" + b(j5) + ", " + c(j5) + ") px/sec";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof o) {
            return this.f3424a == ((o) obj).f3424a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f3424a);
    }

    public final String toString() {
        return f(this.f3424a);
    }
}

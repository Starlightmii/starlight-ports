package x0;

import p3.j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f6532a;

    public /* synthetic */ c(long j5) {
        this.f6532a = j5;
    }

    public static long a(long j5, float f5, int i5) {
        float fIntBitsToFloat = (i5 & 1) != 0 ? Float.intBitsToFloat((int) (j5 >> 32)) : 0.0f;
        if ((i5 & 2) != 0) {
            f5 = Float.intBitsToFloat((int) (j5 & 4294967295L));
        }
        return (((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(f5)) & 4294967295L);
    }

    public static final boolean b(long j5, long j6) {
        return j5 == j6;
    }

    public static final float c(long j5) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j5 >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j5 & 4294967295L));
        return (float) Math.sqrt((fIntBitsToFloat2 * fIntBitsToFloat2) + (fIntBitsToFloat * fIntBitsToFloat));
    }

    public static final float d(long j5) {
        return Float.intBitsToFloat((int) (j5 >> 32));
    }

    public static final float e(long j5) {
        return Float.intBitsToFloat((int) (j5 & 4294967295L));
    }

    public static final boolean f(long j5) {
        long j6 = j5 & 9223372034707292159L;
        return (((~j6) & (j6 - 9187343246269874177L)) & (-9223372034707292160L)) == -9223372034707292160L;
    }

    public static final long g(long j5, long j6) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j5 >> 32)) - Float.intBitsToFloat((int) (j6 >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j5 & 4294967295L)) - Float.intBitsToFloat((int) (j6 & 4294967295L));
        return (((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & 4294967295L);
    }

    public static final long h(long j5, long j6) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j6 >> 32)) + Float.intBitsToFloat((int) (j5 >> 32));
        return (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (j6 & 4294967295L)) + Float.intBitsToFloat((int) (j5 & 4294967295L)))) & 4294967295L) | (Float.floatToRawIntBits(fIntBitsToFloat) << 32);
    }

    public static final long i(float f5, long j5) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j5 >> 32)) * f5;
        return (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (j5 & 4294967295L)) * f5)) & 4294967295L) | (Float.floatToRawIntBits(fIntBitsToFloat) << 32);
    }

    public static String j(long j5) {
        if (!j.E(j5)) {
            return "Offset.Unspecified";
        }
        return "Offset(" + q.f.v(d(j5)) + ", " + q.f.v(e(j5)) + ')';
    }

    public final boolean equals(Object obj) {
        if (obj instanceof c) {
            return this.f6532a == ((c) obj).f6532a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f6532a);
    }

    public final String toString() {
        return j(this.f6532a);
    }
}

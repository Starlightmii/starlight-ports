package k2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final n[] f3420b = {new n(0), new n(4294967296L), new n(8589934592L)};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final long f3421c = d4.a.B(Float.NaN, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f3422a;

    public static final boolean a(long j5, long j6) {
        return j5 == j6;
    }

    public static final long b(long j5) {
        return f3420b[(int) ((j5 & 1095216660480L) >>> 32)].f3423a;
    }

    public static final float c(long j5) {
        return Float.intBitsToFloat((int) (j5 & 4294967295L));
    }

    public static String d(long j5) {
        long jB = b(j5);
        if (n.a(jB, 0L)) {
            return "Unspecified";
        }
        if (n.a(jB, 4294967296L)) {
            return c(j5) + ".sp";
        }
        if (!n.a(jB, 8589934592L)) {
            return "Invalid";
        }
        return c(j5) + ".em";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof m) {
            return this.f3422a == ((m) obj).f3422a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f3422a);
    }

    public final String toString() {
        return d(this.f3422a);
    }
}

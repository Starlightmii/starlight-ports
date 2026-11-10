package y0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f6686b = (((long) Float.floatToRawIntBits(0.5f)) << 32) | (((long) Float.floatToRawIntBits(0.5f)) & 4294967295L);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ int f6687c = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f6688a;

    public static final float a(long j5) {
        return Float.intBitsToFloat((int) (j5 >> 32));
    }

    public static final float b(long j5) {
        return Float.intBitsToFloat((int) (j5 & 4294967295L));
    }

    public static String c(long j5) {
        return "TransformOrigin(packedValue=" + j5 + ')';
    }

    public final boolean equals(Object obj) {
        if (obj instanceof t0) {
            return this.f6688a == ((t0) obj).f6688a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f6688a);
    }

    public final String toString() {
        return c(this.f6688a);
    }
}

package k2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f3409a;

    public static final boolean a(long j5, long j6) {
        return j5 == j6;
    }

    public static final long b(long j5, long j6) {
        return (((long) (((int) (j5 >> 32)) - ((int) (j6 >> 32)))) << 32) | (((long) (((int) (j5 & 4294967295L)) - ((int) (j6 & 4294967295L)))) & 4294967295L);
    }

    public static final long c(long j5, long j6) {
        return (((long) (((int) (j5 >> 32)) + ((int) (j6 >> 32)))) << 32) | (((long) (((int) (j5 & 4294967295L)) + ((int) (j6 & 4294967295L)))) & 4294967295L);
    }

    public static String d(long j5) {
        StringBuilder sb = new StringBuilder("(");
        sb.append((int) (j5 >> 32));
        sb.append(", ");
        return b.b.f(sb, (int) (j5 & 4294967295L), ')');
    }

    public final boolean equals(Object obj) {
        if (obj instanceof h) {
            return this.f3409a == ((h) obj).f3409a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f3409a);
    }

    public final String toString() {
        return d(this.f3409a);
    }
}

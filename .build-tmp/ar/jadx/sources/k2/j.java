package k2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f3415a;

    public static final boolean a(long j5, long j6) {
        return j5 == j6;
    }

    public static String b(long j5) {
        return ((int) (j5 >> 32)) + " x " + ((int) (j5 & 4294967295L));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof j) {
            return this.f3415a == ((j) obj).f3415a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f3415a);
    }

    public final String toString() {
        return b(this.f3415a);
    }
}

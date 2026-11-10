package k2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f3423a;

    public static final boolean a(long j5, long j6) {
        return j5 == j6;
    }

    public static String b(long j5) {
        if (a(j5, 0L)) {
            return "Unspecified";
        }
        if (a(j5, 4294967296L)) {
            return "Sp";
        }
        return a(j5, 8589934592L) ? "Em" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof n) {
            return this.f3423a == ((n) obj).f3423a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f3423a);
    }

    public final String toString() {
        return b(this.f3423a);
    }
}

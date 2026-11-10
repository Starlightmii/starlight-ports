package j1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f3273a;

    public static final boolean a(long j5, long j6) {
        return j5 == j6;
    }

    public static String b(long j5) {
        return "PointerId(value=" + j5 + ')';
    }

    public final boolean equals(Object obj) {
        if (obj instanceof t) {
            return this.f3273a == ((t) obj).f3273a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f3273a);
    }

    public final String toString() {
        return b(this.f3273a);
    }
}

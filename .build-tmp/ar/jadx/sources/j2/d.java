package j2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3309a;

    public static String a(int i5) {
        if (i5 == 1) {
            return "Hyphens.None";
        }
        if (i5 == 2) {
            return "Hyphens.Auto";
        }
        return i5 == Integer.MIN_VALUE ? "Hyphens.Unspecified" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof d) {
            return this.f3309a == ((d) obj).f3309a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f3309a);
    }

    public final String toString() {
        return a(this.f3309a);
    }
}

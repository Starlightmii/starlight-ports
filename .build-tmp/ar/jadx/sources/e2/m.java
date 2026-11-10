package e2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2037a;

    public static String a(int i5) {
        if (i5 == -1) {
            return "Unspecified";
        }
        if (i5 == 0) {
            return "None";
        }
        if (i5 == 1) {
            return "Default";
        }
        if (i5 == 2) {
            return "Go";
        }
        if (i5 == 3) {
            return "Search";
        }
        if (i5 == 4) {
            return "Send";
        }
        if (i5 == 5) {
            return "Previous";
        }
        if (i5 == 6) {
            return "Next";
        }
        return i5 == 7 ? "Done" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof m) {
            return this.f2037a == ((m) obj).f2037a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f2037a);
    }

    public final String toString() {
        return a(this.f2037a);
    }
}

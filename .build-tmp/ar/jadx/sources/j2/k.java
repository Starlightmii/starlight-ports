package j2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3326a;

    public static String a(int i5) {
        if (i5 == 1) {
            return "Ltr";
        }
        if (i5 == 2) {
            return "Rtl";
        }
        if (i5 == 3) {
            return "Content";
        }
        if (i5 == 4) {
            return "ContentOrLtr";
        }
        if (i5 == 5) {
            return "ContentOrRtl";
        }
        return i5 == Integer.MIN_VALUE ? "Unspecified" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof k) {
            return this.f3326a == ((k) obj).f3326a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f3326a);
    }

    public final String toString() {
        return a(this.f3326a);
    }
}

package j2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3321a;

    public static String a(int i5) {
        if (i5 == 1) {
            return "Left";
        }
        if (i5 == 2) {
            return "Right";
        }
        if (i5 == 3) {
            return "Center";
        }
        if (i5 == 4) {
            return "Justify";
        }
        if (i5 == 5) {
            return "Start";
        }
        if (i5 == 6) {
            return "End";
        }
        return i5 == Integer.MIN_VALUE ? "Unspecified" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof i) {
            return this.f3321a == ((i) obj).f3321a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f3321a);
    }

    public final String toString() {
        return a(this.f3321a);
    }
}

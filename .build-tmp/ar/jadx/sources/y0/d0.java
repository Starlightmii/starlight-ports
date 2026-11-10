package y0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f6620a;

    public final boolean equals(Object obj) {
        if (obj instanceof d0) {
            return this.f6620a == ((d0) obj).f6620a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f6620a);
    }

    public final String toString() {
        int i5 = this.f6620a;
        if (i5 == 0) {
            return "Argb8888";
        }
        if (i5 == 1) {
            return "Alpha8";
        }
        if (i5 == 2) {
            return "Rgb565";
        }
        if (i5 == 3) {
            return "F16";
        }
        return i5 == 4 ? "Gpu" : "Unknown";
    }
}

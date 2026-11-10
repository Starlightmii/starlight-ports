package d2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1611a;

    public final boolean equals(Object obj) {
        if (obj instanceof j) {
            return this.f1611a == ((j) obj).f1611a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f1611a);
    }

    public final String toString() {
        int i5 = this.f1611a;
        if (i5 == 0) {
            return "None";
        }
        if (i5 == 1) {
            return "All";
        }
        if (i5 == 2) {
            return "Weight";
        }
        return i5 == 3 ? "Style" : "Invalid";
    }
}

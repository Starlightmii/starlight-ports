package d2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1610a;

    public final boolean equals(Object obj) {
        if (obj instanceof i) {
            return this.f1610a == ((i) obj).f1610a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f1610a);
    }

    public final String toString() {
        int i5 = this.f1610a;
        if (i5 == 0) {
            return "Normal";
        }
        return i5 == 1 ? "Italic" : "Invalid";
    }
}

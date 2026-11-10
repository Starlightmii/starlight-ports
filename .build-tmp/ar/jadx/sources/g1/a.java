package g1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2512a;

    public final boolean equals(Object obj) {
        if (obj instanceof a) {
            return this.f2512a == ((a) obj).f2512a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f2512a);
    }

    public final String toString() {
        int i5 = this.f2512a;
        if (i5 == 1) {
            return "Touch";
        }
        return i5 == 2 ? "Keyboard" : "Error";
    }
}

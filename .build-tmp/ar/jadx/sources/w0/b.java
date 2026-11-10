package w0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f6225a;

    public static String a(int i5) {
        if (i5 == 1) {
            return "Next";
        }
        if (i5 == 2) {
            return "Previous";
        }
        if (i5 == 3) {
            return "Left";
        }
        if (i5 == 4) {
            return "Right";
        }
        if (i5 == 5) {
            return "Up";
        }
        if (i5 == 6) {
            return "Down";
        }
        if (i5 == 7) {
            return "Enter";
        }
        return i5 == 8 ? "Exit" : "Invalid FocusDirection";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof b) {
            return this.f6225a == ((b) obj).f6225a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f6225a);
    }

    public final String toString() {
        return a(this.f6225a);
    }
}

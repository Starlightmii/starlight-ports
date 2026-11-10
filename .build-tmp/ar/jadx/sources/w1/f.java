package w1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f6279a;

    public final boolean equals(Object obj) {
        if (obj instanceof f) {
            return this.f6279a == ((f) obj).f6279a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f6279a);
    }

    public final String toString() {
        int i5 = this.f6279a;
        if (i5 == 0) {
            return "Button";
        }
        if (i5 == 1) {
            return "Checkbox";
        }
        if (i5 == 2) {
            return "Switch";
        }
        if (i5 == 3) {
            return "RadioButton";
        }
        if (i5 == 4) {
            return "Tab";
        }
        if (i5 == 5) {
            return "Image";
        }
        return i5 == 6 ? "DropdownList" : "Unknown";
    }
}

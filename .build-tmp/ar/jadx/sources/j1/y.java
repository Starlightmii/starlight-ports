package j1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3302a;

    public final boolean equals(Object obj) {
        if (obj instanceof y) {
            return this.f3302a == ((y) obj).f3302a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f3302a);
    }

    public final String toString() {
        return "PointerKeyboardModifiers(packedValue=" + this.f3302a + ')';
    }
}

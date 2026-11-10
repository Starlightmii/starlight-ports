package j2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f3305a;

    public final boolean equals(Object obj) {
        if (obj instanceof a) {
            return Float.compare(this.f3305a, ((a) obj).f3305a) == 0;
        }
        return false;
    }

    public final int hashCode() {
        return Float.hashCode(this.f3305a);
    }

    public final String toString() {
        return "BaselineShift(multiplier=" + this.f3305a + ')';
    }
}

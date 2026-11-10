package k2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f3408a;

    public final boolean equals(Object obj) {
        if (obj instanceof g) {
            return this.f3408a == ((g) obj).f3408a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f3408a);
    }

    public final String toString() {
        long j5 = this.f3408a;
        if (j5 == 9205357640488583168L) {
            return "DpSize.Unspecified";
        }
        return ((Object) e.b(Float.intBitsToFloat((int) (j5 >> 32)))) + " x " + ((Object) e.b(Float.intBitsToFloat((int) (j5 & 4294967295L))));
    }
}

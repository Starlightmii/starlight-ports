package m0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3810a;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof a) && this.f3810a == ((a) obj).f3810a;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f3810a);
    }

    public final String toString() {
        return b.b.f(new StringBuilder("DeltaCounter(count="), this.f3810a, ')');
    }
}

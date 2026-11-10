package r0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f5669a;

    public d(float f5) {
        this.f5669a = f5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof d) && Float.compare(this.f5669a, ((d) obj).f5669a) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f5669a);
    }

    public final String toString() {
        return b.b.e(new StringBuilder("Horizontal(bias="), this.f5669a, ')');
    }
}

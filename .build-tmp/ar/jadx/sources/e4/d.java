package e4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d extends b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final d f2081g = new d(1, 0, 1);

    @Override // e4.b
    public final boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        if (isEmpty() && ((d) obj).isEmpty()) {
            return true;
        }
        d dVar = (d) obj;
        return this.f2074d == dVar.f2074d && this.f2075e == dVar.f2075e;
    }

    @Override // e4.b
    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (this.f2074d * 31) + this.f2075e;
    }

    @Override // e4.b
    public final boolean isEmpty() {
        return this.f2074d > this.f2075e;
    }

    @Override // e4.b
    public final String toString() {
        return this.f2074d + ".." + this.f2075e;
    }
}

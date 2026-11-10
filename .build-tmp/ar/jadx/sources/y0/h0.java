package y0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h0 extends j0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final x0.d f6633a;

    public h0(x0.d dVar) {
        this.f6633a = dVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof h0) {
            return b4.l.a(this.f6633a, ((h0) obj).f6633a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f6633a.hashCode();
    }
}

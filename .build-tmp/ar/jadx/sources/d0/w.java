package d0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r0.d f1590a;

    public w(r0.d dVar) {
        this.f1590a = dVar;
    }

    @Override // d0.i
    public final int a(k2.i iVar, long j5, int i5, k2.k kVar) {
        int i6 = (int) (j5 >> 32);
        if (i5 >= i6) {
            return Math.round((1 + (kVar != k2.k.f3416d ? 0.0f * (-1) : 0.0f)) * ((i6 - i5) / 2.0f));
        }
        return d4.a.m(Math.round((1 + this.f1590a.f5669a) * ((i6 - i5) / 2.0f)), 0, i6 - i5);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof w) && this.f1590a.equals(((w) obj).f1590a);
    }

    public final int hashCode() {
        return Integer.hashCode(0) + (Float.hashCode(this.f1590a.f5669a) * 31);
    }

    public final String toString() {
        return "Horizontal(alignment=" + this.f1590a + ", margin=0)";
    }
}

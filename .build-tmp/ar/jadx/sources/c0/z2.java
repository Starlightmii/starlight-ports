package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a3 f1489a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i4.h f1490b;

    public z2(a3 a3Var, i4.h hVar) {
        this.f1489a = a3Var;
        this.f1490b = hVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || z2.class != obj.getClass()) {
            return false;
        }
        z2 z2Var = (z2) obj;
        return b4.l.a(this.f1489a, z2Var.f1489a) && this.f1490b.equals(z2Var.f1490b);
    }

    public final int hashCode() {
        return this.f1490b.hashCode() + (this.f1489a.hashCode() * 31);
    }
}

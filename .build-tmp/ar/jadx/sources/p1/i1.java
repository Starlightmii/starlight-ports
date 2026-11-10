package p1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i1 implements f1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final n1.g0 f4890d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final m0 f4891e;

    public i1(n1.g0 g0Var, m0 m0Var) {
        this.f4890d = g0Var;
        this.f4891e = m0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i1)) {
            return false;
        }
        i1 i1Var = (i1) obj;
        return b4.l.a(this.f4890d, i1Var.f4890d) && b4.l.a(this.f4891e, i1Var.f4891e);
    }

    public final int hashCode() {
        return this.f4891e.hashCode() + (this.f4890d.hashCode() * 31);
    }

    public final String toString() {
        return "PlaceableResult(result=" + this.f4890d + ", placeable=" + this.f4891e + ')';
    }

    @Override // p1.f1
    public final boolean w() {
        return this.f4891e.r0().N();
    }
}

package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i0 implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f3676a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f3677b;

    public i0(x xVar, long j5) {
        this.f3676a = xVar;
        this.f3677b = j5;
    }

    @Override // m.k
    public final c1 a(a1 a1Var) {
        return new j0(this.f3676a.a(a1Var), this.f3677b);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof i0)) {
            return false;
        }
        i0 i0Var = (i0) obj;
        return i0Var.f3677b == this.f3677b && b4.l.a(i0Var.f3676a, this.f3676a);
    }

    public final int hashCode() {
        return Long.hashCode(this.f3677b) + (this.f3676a.hashCode() * 31);
    }
}

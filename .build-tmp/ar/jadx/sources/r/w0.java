package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w0 implements z0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z0 f5637a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final z0 f5638b;

    public w0(z0 z0Var, z0 z0Var2) {
        this.f5637a = z0Var;
        this.f5638b = z0Var2;
    }

    @Override // r.z0
    public final int a(k2.b bVar) {
        return Math.max(this.f5637a.a(bVar), this.f5638b.a(bVar));
    }

    @Override // r.z0
    public final int b(k2.b bVar) {
        return Math.max(this.f5637a.b(bVar), this.f5638b.b(bVar));
    }

    @Override // r.z0
    public final int c(k2.b bVar, k2.k kVar) {
        return Math.max(this.f5637a.c(bVar, kVar), this.f5638b.c(bVar, kVar));
    }

    @Override // r.z0
    public final int d(k2.b bVar, k2.k kVar) {
        return Math.max(this.f5637a.d(bVar, kVar), this.f5638b.d(bVar, kVar));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof w0)) {
            return false;
        }
        w0 w0Var = (w0) obj;
        return b4.l.a(w0Var.f5637a, this.f5637a) && b4.l.a(w0Var.f5638b, this.f5638b);
    }

    public final int hashCode() {
        return (this.f5638b.hashCode() * 31) + this.f5637a.hashCode();
    }

    public final String toString() {
        return "(" + this.f5637a + " ∪ " + this.f5638b + ')';
    }
}

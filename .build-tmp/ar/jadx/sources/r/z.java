package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z implements z0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z0 f5646a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final z0 f5647b;

    public z(z0 z0Var, z0 z0Var2) {
        this.f5646a = z0Var;
        this.f5647b = z0Var2;
    }

    @Override // r.z0
    public final int a(k2.b bVar) {
        int iA = this.f5646a.a(bVar) - this.f5647b.a(bVar);
        if (iA < 0) {
            return 0;
        }
        return iA;
    }

    @Override // r.z0
    public final int b(k2.b bVar) {
        int iB = this.f5646a.b(bVar) - this.f5647b.b(bVar);
        if (iB < 0) {
            return 0;
        }
        return iB;
    }

    @Override // r.z0
    public final int c(k2.b bVar, k2.k kVar) {
        int iC = this.f5646a.c(bVar, kVar) - this.f5647b.c(bVar, kVar);
        if (iC < 0) {
            return 0;
        }
        return iC;
    }

    @Override // r.z0
    public final int d(k2.b bVar, k2.k kVar) {
        int iD = this.f5646a.d(bVar, kVar) - this.f5647b.d(bVar, kVar);
        if (iD < 0) {
            return 0;
        }
        return iD;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof z)) {
            return false;
        }
        z zVar = (z) obj;
        return b4.l.a(zVar.f5646a, this.f5646a) && b4.l.a(zVar.f5647b, this.f5647b);
    }

    public final int hashCode() {
        return this.f5647b.hashCode() + (this.f5646a.hashCode() * 31);
    }

    public final String toString() {
        return "(" + this.f5646a + " - " + this.f5647b + ')';
    }
}

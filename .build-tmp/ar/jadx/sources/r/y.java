package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y implements o1.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a4.c f5642a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public z0 f5643b;

    public y(a4.c cVar) {
        this.f5642a = cVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof y) && ((y) obj).f5642a == this.f5642a;
    }

    @Override // o1.c
    public final void f(o1.f fVar) {
        z0 z0Var = (z0) fVar.e(c1.f5542a);
        if (b4.l.a(z0Var, this.f5643b)) {
            return;
        }
        this.f5643b = z0Var;
        this.f5642a.c(z0Var);
    }

    public final int hashCode() {
        return this.f5642a.hashCode();
    }
}

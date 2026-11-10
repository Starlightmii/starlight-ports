package y0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i0 extends j0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final x0.e f6636a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j f6637b;

    public i0(x0.e eVar) {
        j jVarH;
        this.f6636a = eVar;
        if (q.f.q(eVar)) {
            jVarH = null;
        } else {
            jVarH = l0.h();
            k0.b(jVarH, eVar);
        }
        this.f6637b = jVarH;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof i0) {
            return b4.l.a(this.f6636a, ((i0) obj).f6636a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f6636a.hashCode();
    }
}

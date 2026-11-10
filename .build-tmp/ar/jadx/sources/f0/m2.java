package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m2 extends l {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final l f2274f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f2275g;

    public m2(l lVar, int i5) {
        this.f2274f = lVar;
        this.f2275g = i5;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof m2)) {
            return false;
        }
        m2 m2Var = (m2) obj;
        return b4.l.a(m2Var.f2274f, this.f2274f) && m2Var.f2275g == this.f2275g;
    }

    public final int hashCode() {
        return this.f2274f.hashCode() + (this.f2275g * 31);
    }
}

package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d0 implements q0.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a0 f2112d;

    public d0(a0 a0Var) {
        this.f2112d = a0Var;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof d0) {
            return b4.l.a(this.f2112d, ((d0) obj).f2112d);
        }
        return false;
    }

    public final int hashCode() {
        return this.f2112d.hashCode() * 31;
    }
}

package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f3734a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f3735b;

    public p0(Object obj, Object obj2) {
        this.f3734a = obj;
        this.f3735b = obj2;
    }

    public final boolean a(Object obj, Object obj2) {
        return obj.equals(this.f3734a) && obj2.equals(this.f3735b);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof p0)) {
            return false;
        }
        p0 p0Var = (p0) obj;
        return b4.l.a(this.f3734a, p0Var.f3734a) && b4.l.a(this.f3735b, p0Var.f3735b);
    }

    public final int hashCode() {
        Object obj = this.f3734a;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * 31;
        Object obj2 = this.f3735b;
        return iHashCode + (obj2 != null ? obj2.hashCode() : 0);
    }
}

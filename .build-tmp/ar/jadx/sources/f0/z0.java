package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Integer f2434a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f2435b;

    public z0(Integer num, Object obj) {
        this.f2434a = num;
        this.f2435b = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof z0)) {
            return false;
        }
        z0 z0Var = (z0) obj;
        return this.f2434a.equals(z0Var.f2434a) && b4.l.a(this.f2435b, z0Var.f2435b);
    }

    public final int hashCode() {
        int iHashCode;
        int iHashCode2 = this.f2434a.hashCode() * 31;
        Object obj = this.f2435b;
        if (obj instanceof Enum) {
            iHashCode = ((Enum) obj).ordinal();
        } else {
            iHashCode = obj != null ? obj.hashCode() : 0;
        }
        return iHashCode + iHashCode2;
    }

    public final String toString() {
        return "JoinedKey(left=" + this.f2434a + ", right=" + this.f2435b + ')';
    }
}

package z;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f6975a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f6976b;

    public k0(long j5, long j6) {
        this.f6975a = j5;
        this.f6976b = j6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k0)) {
            return false;
        }
        k0 k0Var = (k0) obj;
        return y0.u.c(this.f6975a, k0Var.f6975a) && y0.u.c(this.f6976b, k0Var.f6976b);
    }

    public final int hashCode() {
        int i5 = y0.u.f6695h;
        return Long.hashCode(this.f6976b) + (Long.hashCode(this.f6975a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SelectionColors(selectionHandleColor=");
        b.b.j(this.f6975a, sb, ", selectionBackgroundColor=");
        sb.append((Object) y0.u.i(this.f6976b));
        sb.append(')');
        return sb.toString();
    }
}

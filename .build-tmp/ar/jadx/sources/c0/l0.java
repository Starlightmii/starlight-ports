package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f1015a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f1016b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f1017c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f1018d;

    public l0(long j5, long j6, long j7, long j8) {
        this.f1015a = j5;
        this.f1016b = j6;
        this.f1017c = j7;
        this.f1018d = j8;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof l0)) {
            return false;
        }
        l0 l0Var = (l0) obj;
        return y0.u.c(this.f1015a, l0Var.f1015a) && y0.u.c(this.f1016b, l0Var.f1016b) && y0.u.c(this.f1017c, l0Var.f1017c) && y0.u.c(this.f1018d, l0Var.f1018d);
    }

    public final int hashCode() {
        int i5 = y0.u.f6695h;
        return Long.hashCode(this.f1018d) + b.b.b(b.b.b(Long.hashCode(this.f1015a) * 31, 31, this.f1016b), 31, this.f1017c);
    }
}

package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class v0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f1360a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f1361b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f1362c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f1363d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f1364e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f1365f;

    public v0(long j5, long j6, long j7, long j8, long j9, long j10) {
        this.f1360a = j5;
        this.f1361b = j6;
        this.f1362c = j7;
        this.f1363d = j8;
        this.f1364e = j9;
        this.f1365f = j10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof v0)) {
            return false;
        }
        v0 v0Var = (v0) obj;
        return y0.u.c(this.f1360a, v0Var.f1360a) && y0.u.c(this.f1361b, v0Var.f1361b) && y0.u.c(this.f1362c, v0Var.f1362c) && y0.u.c(this.f1363d, v0Var.f1363d) && y0.u.c(this.f1364e, v0Var.f1364e) && y0.u.c(this.f1365f, v0Var.f1365f);
    }

    public final int hashCode() {
        int i5 = y0.u.f6695h;
        return Long.hashCode(this.f1365f) + b.b.b(b.b.b(b.b.b(b.b.b(Long.hashCode(this.f1360a) * 31, 31, this.f1361b), 31, this.f1362c), 31, this.f1363d), 31, this.f1364e);
    }
}

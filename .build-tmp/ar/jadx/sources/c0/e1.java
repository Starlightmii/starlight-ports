package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f815a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f816b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f817c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f818d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f819e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f820f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f821g;

    public e1(long j5, long j6, long j7, long j8, long j9, long j10, long j11) {
        this.f815a = j5;
        this.f816b = j6;
        this.f817c = j7;
        this.f818d = j8;
        this.f819e = j9;
        this.f820f = j10;
        this.f821g = j11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof e1)) {
            return false;
        }
        e1 e1Var = (e1) obj;
        return y0.u.c(this.f815a, e1Var.f815a) && y0.u.c(this.f818d, e1Var.f818d) && y0.u.c(this.f816b, e1Var.f816b) && y0.u.c(this.f819e, e1Var.f819e) && y0.u.c(this.f817c, e1Var.f817c) && y0.u.c(this.f820f, e1Var.f820f) && y0.u.c(this.f821g, e1Var.f821g);
    }

    public final int hashCode() {
        int i5 = y0.u.f6695h;
        return Long.hashCode(this.f821g) + b.b.b(b.b.b(b.b.b(b.b.b(b.b.b(Long.hashCode(this.f815a) * 31, 31, this.f818d), 31, this.f816b), 31, this.f819e), 31, this.f817c), 31, this.f820f);
    }
}

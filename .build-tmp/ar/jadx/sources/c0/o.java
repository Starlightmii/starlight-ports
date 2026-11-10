package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f1121a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f1122b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f1123c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f1124d;

    public o(long j5, long j6, long j7, long j8) {
        this.f1121a = j5;
        this.f1122b = j6;
        this.f1123c = j7;
        this.f1124d = j8;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof o)) {
            return false;
        }
        o oVar = (o) obj;
        return y0.u.c(this.f1121a, oVar.f1121a) && y0.u.c(this.f1122b, oVar.f1122b) && y0.u.c(this.f1123c, oVar.f1123c) && y0.u.c(this.f1124d, oVar.f1124d);
    }

    public final int hashCode() {
        int i5 = y0.u.f6695h;
        return Long.hashCode(this.f1124d) + b.b.b(b.b.b(Long.hashCode(this.f1121a) * 31, 31, this.f1122b), 31, this.f1123c);
    }
}

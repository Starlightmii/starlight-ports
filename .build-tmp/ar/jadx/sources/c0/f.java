package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f836a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f837b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f838c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f839d;

    public f(long j5, long j6, long j7, long j8) {
        this.f836a = j5;
        this.f837b = j6;
        this.f838c = j7;
        this.f839d = j8;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return y0.u.c(this.f836a, fVar.f836a) && y0.u.c(this.f837b, fVar.f837b) && y0.u.c(this.f838c, fVar.f838c) && y0.u.c(this.f839d, fVar.f839d);
    }

    public final int hashCode() {
        int i5 = y0.u.f6695h;
        return Long.hashCode(this.f839d) + b.b.b(b.b.b(Long.hashCode(this.f836a) * 31, 31, this.f837b), 31, this.f838c);
    }
}

package y0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final p0 f6668d = new p0();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f6669a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f6670b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f6671c;

    public /* synthetic */ p0() {
        this(0.0f, l0.d(4278190080L), 0L);
    }

    public p0(float f5, long j5, long j6) {
        this.f6669a = j5;
        this.f6670b = j6;
        this.f6671c = f5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p0)) {
            return false;
        }
        p0 p0Var = (p0) obj;
        return u.c(this.f6669a, p0Var.f6669a) && x0.c.b(this.f6670b, p0Var.f6670b) && this.f6671c == p0Var.f6671c;
    }

    public final int hashCode() {
        int i5 = u.f6695h;
        return Float.hashCode(this.f6671c) + b.b.b(Long.hashCode(this.f6669a) * 31, 31, this.f6670b);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Shadow(color=");
        b.b.j(this.f6669a, sb, ", offset=");
        sb.append((Object) x0.c.j(this.f6670b));
        sb.append(", blurRadius=");
        return b.b.e(sb, this.f6671c, ')');
    }
}

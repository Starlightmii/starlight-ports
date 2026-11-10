package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j2 implements n.n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f944a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f945b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f946c;

    public j2(boolean z2, float f5, long j5) {
        this.f944a = z2;
        this.f945b = f5;
        this.f946c = j5;
    }

    @Override // n.n0
    public final p1.l b(q.j jVar) {
        return new e0(jVar, this.f944a, this.f945b, new c0(this));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j2)) {
            return false;
        }
        j2 j2Var = (j2) obj;
        if (this.f944a == j2Var.f944a && k2.e.a(this.f945b, j2Var.f945b)) {
            return y0.u.c(this.f946c, j2Var.f946c);
        }
        return false;
    }

    public final int hashCode() {
        int iA = b.b.a(this.f945b, Boolean.hashCode(this.f944a) * 31, 961);
        int i5 = y0.u.f6695h;
        return Long.hashCode(this.f946c) + iA;
    }
}

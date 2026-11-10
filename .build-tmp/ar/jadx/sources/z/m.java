package z;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j2.h f6979a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f6980b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f6981c;

    public m(j2.h hVar, int i5, long j5) {
        this.f6979a = hVar;
        this.f6980b = i5;
        this.f6981c = j5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        return this.f6979a == mVar.f6979a && this.f6980b == mVar.f6980b && this.f6981c == mVar.f6981c;
    }

    public final int hashCode() {
        return Long.hashCode(this.f6981c) + m.h.b(this.f6980b, this.f6979a.hashCode() * 31, 31);
    }

    public final String toString() {
        return "AnchorInfo(direction=" + this.f6979a + ", offset=" + this.f6980b + ", selectableId=" + this.f6981c + ')';
    }
}

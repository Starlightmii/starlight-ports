package z1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7122a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f7123b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f7124c;

    public m(int i5, int i6, boolean z2) {
        this.f7122a = i5;
        this.f7123b = i6;
        this.f7124c = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        return this.f7122a == mVar.f7122a && this.f7123b == mVar.f7123b && this.f7124c == mVar.f7124c;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f7124c) + m.h.b(this.f7123b, Integer.hashCode(this.f7122a) * 31, 31);
    }

    public final String toString() {
        return "BidiRun(start=" + this.f7122a + ", end=" + this.f7123b + ", isRtl=" + this.f7124c + ')';
    }
}

package y1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g2.d f6806a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f6807b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f6808c;

    public p(g2.d dVar, int i5, int i6) {
        this.f6806a = dVar;
        this.f6807b = i5;
        this.f6808c = i6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return this.f6806a.equals(pVar.f6806a) && this.f6807b == pVar.f6807b && this.f6808c == pVar.f6808c;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f6808c) + m.h.b(this.f6807b, this.f6806a.hashCode() * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ParagraphIntrinsicInfo(intrinsics=");
        sb.append(this.f6806a);
        sb.append(", startIndex=");
        sb.append(this.f6807b);
        sb.append(", endIndex=");
        return b.b.f(sb, this.f6808c, ')');
    }
}

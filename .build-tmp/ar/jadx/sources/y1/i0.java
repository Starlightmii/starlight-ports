package y1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f6783a;

    public i0(String str) {
        this.f6783a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof i0) {
            return b4.l.a(this.f6783a, ((i0) obj).f6783a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f6783a.hashCode();
    }

    public final String toString() {
        return "UrlAnnotation(url=" + this.f6783a + ')';
    }
}

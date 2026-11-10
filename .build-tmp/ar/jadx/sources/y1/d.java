package y1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f6739a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f6740b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f6741c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f6742d;

    public d(int i5, int i6, Object obj) {
        this(obj, i5, i6, "");
    }

    public d(Object obj, int i5, int i6, String str) {
        this.f6739a = obj;
        this.f6740b = i5;
        this.f6741c = i6;
        this.f6742d = str;
        if (i5 > i6) {
            throw new IllegalArgumentException("Reversed range is not supported");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return b4.l.a(this.f6739a, dVar.f6739a) && this.f6740b == dVar.f6740b && this.f6741c == dVar.f6741c && b4.l.a(this.f6742d, dVar.f6742d);
    }

    public final int hashCode() {
        Object obj = this.f6739a;
        return this.f6742d.hashCode() + m.h.b(this.f6741c, m.h.b(this.f6740b, (obj == null ? 0 : obj.hashCode()) * 31, 31), 31);
    }

    public final String toString() {
        return "Range(item=" + this.f6739a + ", start=" + this.f6740b + ", end=" + this.f6741c + ", tag=" + this.f6742d + ')';
    }
}

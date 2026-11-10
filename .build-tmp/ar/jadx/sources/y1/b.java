package y1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f6725a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f6726b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f6727c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f6728d;

    public /* synthetic */ b(int i5, int i6, Object obj) {
        this(obj, i5, i6, "");
    }

    public b(Object obj, int i5, int i6, String str) {
        this.f6725a = obj;
        this.f6726b = i5;
        this.f6727c = i6;
        this.f6728d = str;
    }

    public final d a(int i5) {
        int i6 = this.f6727c;
        if (i6 != Integer.MIN_VALUE) {
            i5 = i6;
        }
        if (i5 == Integer.MIN_VALUE) {
            throw new IllegalStateException("Item.end should be set first");
        }
        return new d(this.f6725a, this.f6726b, i5, this.f6728d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return b4.l.a(this.f6725a, bVar.f6725a) && this.f6726b == bVar.f6726b && this.f6727c == bVar.f6727c && b4.l.a(this.f6728d, bVar.f6728d);
    }

    public final int hashCode() {
        Object obj = this.f6725a;
        return this.f6728d.hashCode() + m.h.b(this.f6727c, m.h.b(this.f6726b, (obj == null ? 0 : obj.hashCode()) * 31, 31), 31);
    }

    public final String toString() {
        return "MutableRange(item=" + this.f6725a + ", start=" + this.f6726b + ", end=" + this.f6727c + ", tag=" + this.f6728d + ')';
    }
}

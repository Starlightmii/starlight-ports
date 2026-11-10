package q0;

import b4.l;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f5087a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Integer f5088b;

    public b(int i5, q.f fVar, Integer num) {
        this.f5087a = i5;
        this.f5088b = num;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f5087a == bVar.f5087a && l.a(null, null) && l.a(this.f5088b, bVar.f5088b);
    }

    public final int hashCode() {
        int iHashCode = ((Integer.hashCode(this.f5087a) * 31) + 0) * 31;
        Integer num = this.f5088b;
        return iHashCode + (num != null ? num.hashCode() : 0);
    }

    public final String toString() {
        return "ComposeStackTraceFrame(groupKey=" + this.f5087a + ", sourceInfo=" + ((Object) null) + ", groupOffset=" + this.f5088b + ')';
    }
}

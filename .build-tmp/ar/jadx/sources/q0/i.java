package q0;

import b4.l;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f5094a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Integer f5095b;

    public i(int i5, Integer num) {
        this.f5094a = i5;
        this.f5095b = num;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return this.f5094a == iVar.f5094a && l.a(this.f5095b, iVar.f5095b);
    }

    public final int hashCode() {
        int iHashCode = Integer.hashCode(this.f5094a) * 31;
        Integer num = this.f5095b;
        return iHashCode + (num == null ? 0 : num.hashCode());
    }

    public final String toString() {
        return "ObjectLocation(group=" + this.f5094a + ", dataOffset=" + this.f5095b + ')';
    }
}

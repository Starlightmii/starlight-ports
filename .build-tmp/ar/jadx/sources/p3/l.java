package p3;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l implements Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f5022d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f5023e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f5024f;

    public l(Object obj, Object obj2, Object obj3) {
        this.f5022d = obj;
        this.f5023e = obj2;
        this.f5024f = obj3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        return b4.l.a(this.f5022d, lVar.f5022d) && b4.l.a(this.f5023e, lVar.f5023e) && b4.l.a(this.f5024f, lVar.f5024f);
    }

    public final int hashCode() {
        Object obj = this.f5022d;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.f5023e;
        int iHashCode2 = (iHashCode + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Object obj3 = this.f5024f;
        return iHashCode2 + (obj3 != null ? obj3.hashCode() : 0);
    }

    public final String toString() {
        return "(" + this.f5022d + ", " + this.f5023e + ", " + this.f5024f + ')';
    }
}

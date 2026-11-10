package p3;

import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f implements Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f5014d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f5015e;

    public f(Object obj, Object obj2) {
        this.f5014d = obj;
        this.f5015e = obj2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return b4.l.a(this.f5014d, fVar.f5014d) && b4.l.a(this.f5015e, fVar.f5015e);
    }

    public final int hashCode() {
        Object obj = this.f5014d;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.f5015e;
        return iHashCode + (obj2 != null ? obj2.hashCode() : 0);
    }

    public final String toString() {
        return "(" + this.f5014d + ", " + this.f5015e + ')';
    }
}

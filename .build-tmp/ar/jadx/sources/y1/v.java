package y1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u f6823a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final t f6824b;

    public v(u uVar, t tVar) {
        this.f6823a = uVar;
        this.f6824b = tVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof v)) {
            return false;
        }
        v vVar = (v) obj;
        return b4.l.a(this.f6824b, vVar.f6824b) && b4.l.a(this.f6823a, vVar.f6823a);
    }

    public final int hashCode() {
        u uVar = this.f6823a;
        int iHashCode = (uVar != null ? uVar.hashCode() : 0) * 31;
        t tVar = this.f6824b;
        return iHashCode + (tVar != null ? tVar.hashCode() : 0);
    }

    public final String toString() {
        return "PlatformTextStyle(spanStyle=" + this.f6823a + ", paragraphSyle=" + this.f6824b + ')';
    }
}

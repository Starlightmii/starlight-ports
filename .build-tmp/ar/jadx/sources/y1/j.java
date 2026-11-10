package y1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j extends l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f6784a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f0 f6785b;

    public j(String str, f0 f0Var) {
        this.f6784a = str;
        this.f6785b = f0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        return b4.l.a(this.f6784a, jVar.f6784a) && b4.l.a(this.f6785b, jVar.f6785b);
    }

    public final int hashCode() {
        int iHashCode = this.f6784a.hashCode() * 31;
        f0 f0Var = this.f6785b;
        return (iHashCode + (f0Var != null ? f0Var.hashCode() : 0)) * 31;
    }

    public final String toString() {
        return "LinkAnnotation.Clickable(tag=" + this.f6784a + ')';
    }
}

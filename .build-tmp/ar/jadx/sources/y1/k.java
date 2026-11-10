package y1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k extends l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f6787a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f0 f6788b;

    public k(String str, f0 f0Var) {
        this.f6787a = str;
        this.f6788b = f0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return b4.l.a(this.f6787a, kVar.f6787a) && b4.l.a(this.f6788b, kVar.f6788b);
    }

    public final int hashCode() {
        int iHashCode = this.f6787a.hashCode() * 31;
        f0 f0Var = this.f6788b;
        return (iHashCode + (f0Var != null ? f0Var.hashCode() : 0)) * 31;
    }

    public final String toString() {
        return "LinkAnnotation.Url(url=" + this.f6787a + ')';
    }
}

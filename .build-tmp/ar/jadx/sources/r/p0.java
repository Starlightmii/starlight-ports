package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f5605a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f5606b;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p0)) {
            return false;
        }
        p0 p0Var = (p0) obj;
        return Float.compare(this.f5605a, p0Var.f5605a) == 0 && this.f5606b == p0Var.f5606b && b4.l.a(null, null);
    }

    public final int hashCode() {
        return (b.b.c(Float.hashCode(this.f5605a) * 31, 31, this.f5606b) + 0) * 31;
    }

    public final String toString() {
        return "RowColumnParentData(weight=" + this.f5605a + ", fill=" + this.f5606b + ", crossAxisAlignment=" + ((Object) null) + ", flowLayoutData=null)";
    }
}

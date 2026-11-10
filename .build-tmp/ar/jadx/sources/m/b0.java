package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Float f3618a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final v f3619b;

    public b0(Float f5, v vVar) {
        this.f3618a = f5;
        this.f3619b = vVar;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b0)) {
            return false;
        }
        b0 b0Var = (b0) obj;
        return b0Var.f3618a.equals(this.f3618a) && b4.l.a(b0Var.f3619b, this.f3619b);
    }

    public final int hashCode() {
        return this.f3619b.hashCode() + h.b(0, this.f3618a.hashCode() * 31, 31);
    }
}

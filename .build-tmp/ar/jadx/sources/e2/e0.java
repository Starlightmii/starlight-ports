package e2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y1.f f2019a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r f2020b;

    public e0(y1.f fVar, r rVar) {
        this.f2019a = fVar;
        this.f2020b = rVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e0)) {
            return false;
        }
        e0 e0Var = (e0) obj;
        return b4.l.a(this.f2019a, e0Var.f2019a) && b4.l.a(this.f2020b, e0Var.f2020b);
    }

    public final int hashCode() {
        return this.f2020b.hashCode() + (this.f2019a.hashCode() * 31);
    }

    public final String toString() {
        return "TransformedText(text=" + ((Object) this.f2019a) + ", offsetMapping=" + this.f2020b + ')';
    }
}

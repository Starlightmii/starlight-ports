package n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f3994a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final y0.s0 f3995b;

    public q(float f5, y0.s0 s0Var) {
        this.f3994a = f5;
        this.f3995b = s0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof q)) {
            return false;
        }
        q qVar = (q) obj;
        return k2.e.a(this.f3994a, qVar.f3994a) && this.f3995b.equals(qVar.f3995b);
    }

    public final int hashCode() {
        return this.f3995b.hashCode() + (Float.hashCode(this.f3994a) * 31);
    }

    public final String toString() {
        return "BorderStroke(width=" + ((Object) k2.e.b(this.f3994a)) + ", brush=" + this.f3995b + ')';
    }
}

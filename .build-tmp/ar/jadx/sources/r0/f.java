package r0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f5671a;

    public f(float f5) {
        this.f5671a = f5;
    }

    public final int a(int i5, int i6, k2.k kVar) {
        float f5 = (i6 - i5) / 2.0f;
        k2.k kVar2 = k2.k.f3416d;
        float f6 = this.f5671a;
        if (kVar != kVar2) {
            f6 *= -1;
        }
        return Math.round((1 + f6) * f5);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof f) && Float.compare(this.f5671a, ((f) obj).f5671a) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f5671a);
    }

    public final String toString() {
        return b.b.e(new StringBuilder("Horizontal(bias="), this.f5671a, ')');
    }
}

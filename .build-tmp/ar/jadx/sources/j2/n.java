package j2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final n f3328c = new n(1.0f, 0.0f);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f3329a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f3330b;

    public n(float f5, float f6) {
        this.f3329a = f5;
        this.f3330b = f6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        return this.f3329a == nVar.f3329a && this.f3330b == nVar.f3330b;
    }

    public final int hashCode() {
        return Float.hashCode(this.f3330b) + (Float.hashCode(this.f3329a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TextGeometricTransform(scaleX=");
        sb.append(this.f3329a);
        sb.append(", skewX=");
        return b.b.e(sb, this.f3330b, ')');
    }
}

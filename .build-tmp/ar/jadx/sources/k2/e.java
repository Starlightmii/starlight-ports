package k2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e implements Comparable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f3406d;

    public static final boolean a(float f5, float f6) {
        return Float.compare(f5, f6) == 0;
    }

    public static String b(float f5) {
        if (Float.isNaN(f5)) {
            return "Dp.Unspecified";
        }
        return f5 + ".dp";
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Float.compare(this.f3406d, ((e) obj).f3406d);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof e) {
            return Float.compare(this.f3406d, ((e) obj).f3406d) == 0;
        }
        return false;
    }

    public final int hashCode() {
        return Float.hashCode(this.f3406d);
    }

    public final String toString() {
        return b(this.f3406d);
    }
}

package k2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l implements l2.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f3419a;

    public l(float f5) {
        this.f3419a = f5;
    }

    @Override // l2.a
    public final float a(float f5) {
        return f5 / this.f3419a;
    }

    @Override // l2.a
    public final float b(float f5) {
        return f5 * this.f3419a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof l) && Float.compare(this.f3419a, ((l) obj).f3419a) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f3419a);
    }

    public final String toString() {
        return b.b.e(new StringBuilder("LinearFontScaleConverter(fontScale="), this.f3419a, ')');
    }
}

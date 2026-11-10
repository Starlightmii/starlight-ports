package e1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p extends u {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f1944b;

    public p(float f5) {
        super(3);
        this.f1944b = f5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof p) && Float.compare(this.f1944b, ((p) obj).f1944b) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f1944b);
    }

    public final String toString() {
        return b.b.e(new StringBuilder("RelativeHorizontalTo(dx="), this.f1944b, ')');
    }
}

package e1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t extends u {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f1952b;

    public t(float f5) {
        super(3);
        this.f1952b = f5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof t) && Float.compare(this.f1952b, ((t) obj).f1952b) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f1952b);
    }

    public final String toString() {
        return b.b.e(new StringBuilder("VerticalTo(y="), this.f1952b, ')');
    }
}

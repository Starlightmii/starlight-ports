package r0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f5672a;

    public g(float f5) {
        this.f5672a = f5;
    }

    public final int a(int i5, int i6) {
        return Math.round((1 + this.f5672a) * ((i6 - i5) / 2.0f));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof g) && Float.compare(this.f5672a, ((g) obj).f5672a) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f5672a);
    }

    public final String toString() {
        return b.b.e(new StringBuilder("Vertical(bias="), this.f5672a, ')');
    }
}

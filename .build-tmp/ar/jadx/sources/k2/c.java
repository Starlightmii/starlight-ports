package k2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c implements b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f3401d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f3402e;

    public c(float f5, float f6) {
        this.f3401d = f5;
        this.f3402e = f6;
    }

    @Override // k2.b
    public final float a() {
        return this.f3401d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return Float.compare(this.f3401d, cVar.f3401d) == 0 && Float.compare(this.f3402e, cVar.f3402e) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f3402e) + (Float.hashCode(this.f3401d) * 31);
    }

    @Override // k2.b
    public final float j() {
        return this.f3402e;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DensityImpl(density=");
        sb.append(this.f3401d);
        sb.append(", fontScale=");
        return b.b.e(sb, this.f3402e, ')');
    }
}

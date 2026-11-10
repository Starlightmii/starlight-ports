package l;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f3482a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f3483b;

    public a(float f5, float f6) {
        this.f3482a = f5;
        this.f3483b = f6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return Float.compare(this.f3482a, aVar.f3482a) == 0 && Float.compare(this.f3483b, aVar.f3483b) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f3483b) + (Float.hashCode(this.f3482a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FlingResult(distanceCoefficient=");
        sb.append(this.f3482a);
        sb.append(", velocityCoefficient=");
        return b.b.e(sb, this.f3483b, ')');
    }
}

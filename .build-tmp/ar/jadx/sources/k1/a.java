package k1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f3389a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f3390b;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f3389a == aVar.f3389a && Float.compare(this.f3390b, aVar.f3390b) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f3390b) + (Long.hashCode(this.f3389a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DataPointAtTime(time=");
        sb.append(this.f3389a);
        sb.append(", dataPoint=");
        return b.b.e(sb, this.f3390b, ')');
    }
}

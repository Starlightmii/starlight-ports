package l;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f3486a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f3487b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f3488c;

    public d(float f5, float f6, long j5) {
        this.f3486a = f5;
        this.f3487b = f6;
        this.f3488c = j5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return Float.compare(this.f3486a, dVar.f3486a) == 0 && Float.compare(this.f3487b, dVar.f3487b) == 0 && this.f3488c == dVar.f3488c;
    }

    public final int hashCode() {
        return Long.hashCode(this.f3488c) + b.b.a(this.f3487b, Float.hashCode(this.f3486a) * 31, 31);
    }

    public final String toString() {
        return "FlingInfo(initialVelocity=" + this.f3486a + ", distance=" + this.f3487b + ", duration=" + this.f3488c + ')';
    }
}

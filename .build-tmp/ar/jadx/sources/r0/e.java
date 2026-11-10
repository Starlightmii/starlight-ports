package r0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f5670a;

    public e(float f5) {
        this.f5670a = f5;
    }

    @Override // r0.c
    public final long a(long j5, long j6, k2.k kVar) {
        long jF = a.a.f(((int) (j6 >> 32)) - ((int) (j5 >> 32)), ((int) (j6 & 4294967295L)) - ((int) (j5 & 4294967295L)));
        float f5 = 1;
        return h1.c.d(Math.round((this.f5670a + f5) * (((int) (jF >> 32)) / 2.0f)), Math.round((f5 - 1.0f) * (((int) (jF & 4294967295L)) / 2.0f)));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof e) && Float.compare(this.f5670a, ((e) obj).f5670a) == 0 && Float.compare(-1.0f, -1.0f) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(-1.0f) + (Float.hashCode(this.f5670a) * 31);
    }

    public final String toString() {
        return "BiasAbsoluteAlignment(horizontalBias=" + this.f5670a + ", verticalBias=-1.0)";
    }
}

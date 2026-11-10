package r0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f5673a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f5674b;

    public h(float f5, float f6) {
        this.f5673a = f5;
        this.f5674b = f6;
    }

    @Override // r0.c
    public final long a(long j5, long j6, k2.k kVar) {
        float f5 = (((int) (j6 >> 32)) - ((int) (j5 >> 32))) / 2.0f;
        float f6 = (((int) (j6 & 4294967295L)) - ((int) (j5 & 4294967295L))) / 2.0f;
        k2.k kVar2 = k2.k.f3416d;
        float f7 = this.f5673a;
        if (kVar != kVar2) {
            f7 *= -1;
        }
        float f8 = 1;
        return h1.c.d(Math.round((f7 + f8) * f5), Math.round((f8 + this.f5674b) * f6));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return Float.compare(this.f5673a, hVar.f5673a) == 0 && Float.compare(this.f5674b, hVar.f5674b) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f5674b) + (Float.hashCode(this.f5673a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BiasAlignment(horizontalBias=");
        sb.append(this.f5673a);
        sb.append(", verticalBias=");
        return b.b.e(sb, this.f5674b, ')');
    }
}

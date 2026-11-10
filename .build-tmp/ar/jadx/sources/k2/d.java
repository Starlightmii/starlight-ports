package k2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d implements b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f3403d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f3404e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final l2.a f3405f;

    public d(float f5, float f6, l2.a aVar) {
        this.f3403d = f5;
        this.f3404e = f6;
        this.f3405f = aVar;
    }

    @Override // k2.b
    public final float I(long j5) {
        if (n.a(m.b(j5), 4294967296L)) {
            return this.f3405f.b(m.c(j5));
        }
        throw new IllegalStateException("Only Sp can convert to Px");
    }

    @Override // k2.b
    public final float a() {
        return this.f3403d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return Float.compare(this.f3403d, dVar.f3403d) == 0 && Float.compare(this.f3404e, dVar.f3404e) == 0 && b4.l.a(this.f3405f, dVar.f3405f);
    }

    public final int hashCode() {
        return this.f3405f.hashCode() + b.b.a(this.f3404e, Float.hashCode(this.f3403d) * 31, 31);
    }

    @Override // k2.b
    public final float j() {
        return this.f3404e;
    }

    @Override // k2.b
    public final long s(float f5) {
        return d4.a.B(this.f3405f.a(f5), 4294967296L);
    }

    public final String toString() {
        return "DensityWithConverter(density=" + this.f3403d + ", fontScale=" + this.f3404e + ", converter=" + this.f3405f + ')';
    }
}

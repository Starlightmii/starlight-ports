package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f3703a;

    public m(float f5) {
        this.f3703a = f5;
    }

    @Override // m.q
    public final float a(int i5) {
        if (i5 == 0) {
            return this.f3703a;
        }
        return 0.0f;
    }

    @Override // m.q
    public final int b() {
        return 1;
    }

    @Override // m.q
    public final q c() {
        return new m(0.0f);
    }

    @Override // m.q
    public final void d() {
        this.f3703a = 0.0f;
    }

    @Override // m.q
    public final void e(float f5, int i5) {
        if (i5 == 0) {
            this.f3703a = f5;
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof m) && ((m) obj).f3703a == this.f3703a;
    }

    public final int hashCode() {
        return Float.hashCode(this.f3703a);
    }

    public final String toString() {
        return "AnimationVector1D: value = " + this.f3703a;
    }
}

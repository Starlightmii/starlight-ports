package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f3711a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f3712b;

    public n(float f5, float f6) {
        this.f3711a = f5;
        this.f3712b = f6;
    }

    @Override // m.q
    public final float a(int i5) {
        if (i5 == 0) {
            return this.f3711a;
        }
        if (i5 != 1) {
            return 0.0f;
        }
        return this.f3712b;
    }

    @Override // m.q
    public final int b() {
        return 2;
    }

    @Override // m.q
    public final q c() {
        return new n(0.0f, 0.0f);
    }

    @Override // m.q
    public final void d() {
        this.f3711a = 0.0f;
        this.f3712b = 0.0f;
    }

    @Override // m.q
    public final void e(float f5, int i5) {
        if (i5 == 0) {
            this.f3711a = f5;
        } else {
            if (i5 != 1) {
                return;
            }
            this.f3712b = f5;
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        return nVar.f3711a == this.f3711a && nVar.f3712b == this.f3712b;
    }

    public final int hashCode() {
        return Float.hashCode(this.f3712b) + (Float.hashCode(this.f3711a) * 31);
    }

    public final String toString() {
        return "AnimationVector2D: v1 = " + this.f3711a + ", v2 = " + this.f3712b;
    }
}

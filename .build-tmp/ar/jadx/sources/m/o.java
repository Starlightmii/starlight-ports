package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f3718a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f3719b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f3720c;

    public o(float f5, float f6, float f7) {
        this.f3718a = f5;
        this.f3719b = f6;
        this.f3720c = f7;
    }

    @Override // m.q
    public final float a(int i5) {
        if (i5 == 0) {
            return this.f3718a;
        }
        if (i5 == 1) {
            return this.f3719b;
        }
        if (i5 != 2) {
            return 0.0f;
        }
        return this.f3720c;
    }

    @Override // m.q
    public final int b() {
        return 3;
    }

    @Override // m.q
    public final q c() {
        return new o(0.0f, 0.0f, 0.0f);
    }

    @Override // m.q
    public final void d() {
        this.f3718a = 0.0f;
        this.f3719b = 0.0f;
        this.f3720c = 0.0f;
    }

    @Override // m.q
    public final void e(float f5, int i5) {
        if (i5 == 0) {
            this.f3718a = f5;
        } else if (i5 == 1) {
            this.f3719b = f5;
        } else {
            if (i5 != 2) {
                return;
            }
            this.f3720c = f5;
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof o)) {
            return false;
        }
        o oVar = (o) obj;
        return oVar.f3718a == this.f3718a && oVar.f3719b == this.f3719b && oVar.f3720c == this.f3720c;
    }

    public final int hashCode() {
        return Float.hashCode(this.f3720c) + b.b.a(this.f3719b, Float.hashCode(this.f3718a) * 31, 31);
    }

    public final String toString() {
        return "AnimationVector3D: v1 = " + this.f3718a + ", v2 = " + this.f3719b + ", v3 = " + this.f3720c;
    }
}

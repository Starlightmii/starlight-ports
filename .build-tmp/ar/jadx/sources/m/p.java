package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f3730a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f3731b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f3732c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f3733d;

    public p(float f5, float f6, float f7, float f8) {
        this.f3730a = f5;
        this.f3731b = f6;
        this.f3732c = f7;
        this.f3733d = f8;
    }

    @Override // m.q
    public final float a(int i5) {
        if (i5 == 0) {
            return this.f3730a;
        }
        if (i5 == 1) {
            return this.f3731b;
        }
        if (i5 == 2) {
            return this.f3732c;
        }
        if (i5 != 3) {
            return 0.0f;
        }
        return this.f3733d;
    }

    @Override // m.q
    public final int b() {
        return 4;
    }

    @Override // m.q
    public final q c() {
        return new p(0.0f, 0.0f, 0.0f, 0.0f);
    }

    @Override // m.q
    public final void d() {
        this.f3730a = 0.0f;
        this.f3731b = 0.0f;
        this.f3732c = 0.0f;
        this.f3733d = 0.0f;
    }

    @Override // m.q
    public final void e(float f5, int i5) {
        if (i5 == 0) {
            this.f3730a = f5;
            return;
        }
        if (i5 == 1) {
            this.f3731b = f5;
        } else if (i5 == 2) {
            this.f3732c = f5;
        } else {
            if (i5 != 3) {
                return;
            }
            this.f3733d = f5;
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return pVar.f3730a == this.f3730a && pVar.f3731b == this.f3731b && pVar.f3732c == this.f3732c && pVar.f3733d == this.f3733d;
    }

    public final int hashCode() {
        return Float.hashCode(this.f3733d) + b.b.a(this.f3732c, b.b.a(this.f3731b, Float.hashCode(this.f3730a) * 31, 31), 31);
    }

    public final String toString() {
        return "AnimationVector4D: v1 = " + this.f3730a + ", v2 = " + this.f3731b + ", v3 = " + this.f3732c + ", v4 = " + this.f3733d;
    }
}

package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m0 implements l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f5588a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f5589b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f5590c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f5591d;

    public m0(float f5, float f6, float f7, float f8) {
        this.f5588a = f5;
        this.f5589b = f6;
        this.f5590c = f7;
        this.f5591d = f8;
        if (f5 < 0.0f) {
            throw new IllegalArgumentException("Start padding must be non-negative");
        }
        if (f6 < 0.0f) {
            throw new IllegalArgumentException("Top padding must be non-negative");
        }
        if (f7 < 0.0f) {
            throw new IllegalArgumentException("End padding must be non-negative");
        }
        if (f8 < 0.0f) {
            throw new IllegalArgumentException("Bottom padding must be non-negative");
        }
    }

    @Override // r.l0
    public final float a() {
        return this.f5591d;
    }

    @Override // r.l0
    public final float b() {
        return this.f5589b;
    }

    @Override // r.l0
    public final float c(k2.k kVar) {
        return kVar == k2.k.f3416d ? this.f5588a : this.f5590c;
    }

    @Override // r.l0
    public final float d(k2.k kVar) {
        return kVar == k2.k.f3416d ? this.f5590c : this.f5588a;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof m0)) {
            return false;
        }
        m0 m0Var = (m0) obj;
        return k2.e.a(this.f5588a, m0Var.f5588a) && k2.e.a(this.f5589b, m0Var.f5589b) && k2.e.a(this.f5590c, m0Var.f5590c) && k2.e.a(this.f5591d, m0Var.f5591d);
    }

    public final int hashCode() {
        return Float.hashCode(this.f5591d) + b.b.a(this.f5590c, b.b.a(this.f5589b, Float.hashCode(this.f5588a) * 31, 31), 31);
    }

    public final String toString() {
        return "PaddingValues(start=" + ((Object) k2.e.b(this.f5588a)) + ", top=" + ((Object) k2.e.b(this.f5589b)) + ", end=" + ((Object) k2.e.b(this.f5590c)) + ", bottom=" + ((Object) k2.e.b(this.f5591d)) + ')';
    }
}

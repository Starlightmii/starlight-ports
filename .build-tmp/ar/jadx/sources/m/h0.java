package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h0 implements x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f3671a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f3672b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f3673c;

    public h0(float f5, float f6, Object obj) {
        this.f3671a = f5;
        this.f3672b = f6;
        this.f3673c = obj;
    }

    public /* synthetic */ h0(Object obj) {
        this(1.0f, 1500.0f, obj);
    }

    @Override // m.k
    public final c1 a(a1 a1Var) {
        Object obj = this.f3673c;
        return new a1.d(this.f3671a, this.f3672b, obj == null ? null : (q) a1Var.f3614a.c(obj));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof h0) {
            h0 h0Var = (h0) obj;
            if (h0Var.f3671a == this.f3671a && h0Var.f3672b == this.f3672b && b4.l.a(h0Var.f3673c, this.f3673c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        Object obj = this.f3673c;
        return Float.hashCode(this.f3672b) + b.b.a(this.f3671a, (obj != null ? obj.hashCode() : 0) * 31, 31);
    }
}

package y0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s0 extends p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f6684a;

    public s0(long j5) {
        this.f6684a = j5;
    }

    @Override // y0.p
    public final void a(float f5, long j5, h hVar) {
        hVar.c(1.0f);
        long jB = this.f6684a;
        if (f5 != 1.0f) {
            jB = u.b(u.d(jB) * f5, jB);
        }
        hVar.e(jB);
        if (hVar.f6631c != null) {
            hVar.h(null);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof s0) {
            return u.c(this.f6684a, ((s0) obj).f6684a);
        }
        return false;
    }

    public final int hashCode() {
        int i5 = u.f6695h;
        return Long.hashCode(this.f6684a);
    }

    public final String toString() {
        return "SolidColor(value=" + ((Object) u.i(this.f6684a)) + ')';
    }
}

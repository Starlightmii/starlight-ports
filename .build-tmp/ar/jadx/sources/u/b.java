package u;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f5810a;

    public b(float f5) {
        this.f5810a = f5;
    }

    @Override // u.a
    public final float a(long j5, k2.b bVar) {
        return bVar.v(this.f5810a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof b) && k2.e.a(this.f5810a, ((b) obj).f5810a);
    }

    public final int hashCode() {
        return Float.hashCode(this.f5810a);
    }

    public final String toString() {
        return "CornerSize(size = " + this.f5810a + ".dp)";
    }
}

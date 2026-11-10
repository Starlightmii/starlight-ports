package z0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7037a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f7038b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f7039c;

    public c(String str, long j5, int i5) {
        this.f7037a = str;
        this.f7038b = j5;
        this.f7039c = i5;
        if (str.length() == 0) {
            throw new IllegalArgumentException("The name of a color space cannot be null and must contain at least 1 character");
        }
        if (i5 < -1 || i5 > 63) {
            throw new IllegalArgumentException("The id must be between -1 and 63");
        }
    }

    public abstract float a(int i5);

    public abstract float b(int i5);

    public boolean c() {
        return false;
    }

    public abstract long d(float f5, float f6, float f7);

    public abstract float e(float f5, float f6, float f7);

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        if (this.f7039c == cVar.f7039c && b4.l.a(this.f7037a, cVar.f7037a)) {
            return b.a(this.f7038b, cVar.f7038b);
        }
        return false;
    }

    public abstract long f(float f5, float f6, float f7, float f8, c cVar);

    public int hashCode() {
        int iHashCode = this.f7037a.hashCode() * 31;
        int i5 = b.f7036e;
        return b.b.b(iHashCode, 31, this.f7038b) + this.f7039c;
    }

    public final String toString() {
        return this.f7037a + " (id=" + this.f7039c + ", model=" + ((Object) b.b(this.f7038b)) + ')';
    }
}

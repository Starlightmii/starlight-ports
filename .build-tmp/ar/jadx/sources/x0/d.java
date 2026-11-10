package x0;

import p3.j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final d f6533e = new d(0.0f, 0.0f, 0.0f, 0.0f);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f6534a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f6535b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f6536c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f6537d;

    public d(float f5, float f6, float f7, float f8) {
        this.f6534a = f5;
        this.f6535b = f6;
        this.f6536c = f7;
        this.f6537d = f8;
    }

    public final long a() {
        return j.b((c() / 2.0f) + this.f6534a, (b() / 2.0f) + this.f6535b);
    }

    public final float b() {
        return this.f6537d - this.f6535b;
    }

    public final float c() {
        return this.f6536c - this.f6534a;
    }

    public final d d(d dVar) {
        return new d(Math.max(this.f6534a, dVar.f6534a), Math.max(this.f6535b, dVar.f6535b), Math.min(this.f6536c, dVar.f6536c), Math.min(this.f6537d, dVar.f6537d));
    }

    public final boolean e() {
        return this.f6534a >= this.f6536c || this.f6535b >= this.f6537d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return Float.compare(this.f6534a, dVar.f6534a) == 0 && Float.compare(this.f6535b, dVar.f6535b) == 0 && Float.compare(this.f6536c, dVar.f6536c) == 0 && Float.compare(this.f6537d, dVar.f6537d) == 0;
    }

    public final boolean f(d dVar) {
        return this.f6536c > dVar.f6534a && dVar.f6536c > this.f6534a && this.f6537d > dVar.f6535b && dVar.f6537d > this.f6535b;
    }

    public final d g(float f5, float f6) {
        return new d(this.f6534a + f5, this.f6535b + f6, this.f6536c + f5, this.f6537d + f6);
    }

    public final d h(long j5) {
        return new d(c.d(j5) + this.f6534a, c.e(j5) + this.f6535b, c.d(j5) + this.f6536c, c.e(j5) + this.f6537d);
    }

    public final int hashCode() {
        return Float.hashCode(this.f6537d) + b.b.a(this.f6536c, b.b.a(this.f6535b, Float.hashCode(this.f6534a) * 31, 31), 31);
    }

    public final String toString() {
        return "Rect.fromLTRB(" + q.f.v(this.f6534a) + ", " + q.f.v(this.f6535b) + ", " + q.f.v(this.f6536c) + ", " + q.f.v(this.f6537d) + ')';
    }
}

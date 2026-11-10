package x0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f6528a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f6529b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f6530c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f6531d;

    public final void a(float f5, float f6, float f7, float f8) {
        this.f6528a = Math.max(f5, this.f6528a);
        this.f6529b = Math.max(f6, this.f6529b);
        this.f6530c = Math.min(f7, this.f6530c);
        this.f6531d = Math.min(f8, this.f6531d);
    }

    public final boolean b() {
        return this.f6528a >= this.f6530c || this.f6529b >= this.f6531d;
    }

    public final String toString() {
        return "MutableRect(" + q.f.v(this.f6528a) + ", " + q.f.v(this.f6529b) + ", " + q.f.v(this.f6530c) + ", " + q.f.v(this.f6531d) + ')';
    }
}

package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f935a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f936b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f937c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f938d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f939e;

    public j(float f5, float f6, float f7, float f8, float f9) {
        this.f935a = f5;
        this.f936b = f6;
        this.f937c = f7;
        this.f938d = f8;
        this.f939e = f9;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        return k2.e.a(this.f935a, jVar.f935a) && k2.e.a(this.f936b, jVar.f936b) && k2.e.a(this.f937c, jVar.f937c) && k2.e.a(this.f938d, jVar.f938d) && k2.e.a(this.f939e, jVar.f939e);
    }

    public final int hashCode() {
        return Float.hashCode(this.f939e) + b.b.a(this.f938d, b.b.a(this.f937c, b.b.a(this.f936b, Float.hashCode(this.f935a) * 31, 31), 31), 31);
    }
}

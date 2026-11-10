package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f1139a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f1140b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f1141c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f1142d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f1143e;

    public p(float f5, float f6, float f7, float f8, float f9, float f10) {
        this.f1139a = f5;
        this.f1140b = f6;
        this.f1141c = f7;
        this.f1142d = f8;
        this.f1143e = f10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return k2.e.a(this.f1139a, pVar.f1139a) && k2.e.a(this.f1140b, pVar.f1140b) && k2.e.a(this.f1141c, pVar.f1141c) && k2.e.a(this.f1142d, pVar.f1142d) && k2.e.a(this.f1143e, pVar.f1143e);
    }

    public final int hashCode() {
        return Float.hashCode(this.f1143e) + b.b.a(this.f1142d, b.b.a(this.f1141c, b.b.a(this.f1140b, Float.hashCode(this.f1139a) * 31, 31), 31), 31);
    }
}

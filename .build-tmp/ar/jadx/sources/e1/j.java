package e1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j extends u {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f1923b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f1924c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f1925d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f1926e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f1927f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f1928g;

    public j(float f5, float f6, float f7, float f8, float f9, float f10) {
        super(2);
        this.f1923b = f5;
        this.f1924c = f6;
        this.f1925d = f7;
        this.f1926e = f8;
        this.f1927f = f9;
        this.f1928g = f10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        return Float.compare(this.f1923b, jVar.f1923b) == 0 && Float.compare(this.f1924c, jVar.f1924c) == 0 && Float.compare(this.f1925d, jVar.f1925d) == 0 && Float.compare(this.f1926e, jVar.f1926e) == 0 && Float.compare(this.f1927f, jVar.f1927f) == 0 && Float.compare(this.f1928g, jVar.f1928g) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f1928g) + b.b.a(this.f1927f, b.b.a(this.f1926e, b.b.a(this.f1925d, b.b.a(this.f1924c, Float.hashCode(this.f1923b) * 31, 31), 31), 31), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CurveTo(x1=");
        sb.append(this.f1923b);
        sb.append(", y1=");
        sb.append(this.f1924c);
        sb.append(", x2=");
        sb.append(this.f1925d);
        sb.append(", y2=");
        sb.append(this.f1926e);
        sb.append(", x3=");
        sb.append(this.f1927f);
        sb.append(", y3=");
        return b.b.e(sb, this.f1928g, ')');
    }
}

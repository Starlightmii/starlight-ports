package e1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r extends u {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f1947b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f1948c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f1949d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f1950e;

    public r(float f5, float f6, float f7, float f8) {
        super(2);
        this.f1947b = f5;
        this.f1948c = f6;
        this.f1949d = f7;
        this.f1950e = f8;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return Float.compare(this.f1947b, rVar.f1947b) == 0 && Float.compare(this.f1948c, rVar.f1948c) == 0 && Float.compare(this.f1949d, rVar.f1949d) == 0 && Float.compare(this.f1950e, rVar.f1950e) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f1950e) + b.b.a(this.f1949d, b.b.a(this.f1948c, Float.hashCode(this.f1947b) * 31, 31), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("RelativeReflectiveCurveTo(dx1=");
        sb.append(this.f1947b);
        sb.append(", dy1=");
        sb.append(this.f1948c);
        sb.append(", dx2=");
        sb.append(this.f1949d);
        sb.append(", dy2=");
        return b.b.e(sb, this.f1950e, ')');
    }
}

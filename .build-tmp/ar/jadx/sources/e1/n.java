package e1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n extends u {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f1934b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f1935c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f1936d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f1937e;

    public n(float f5, float f6, float f7, float f8) {
        super(2);
        this.f1934b = f5;
        this.f1935c = f6;
        this.f1936d = f7;
        this.f1937e = f8;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        return Float.compare(this.f1934b, nVar.f1934b) == 0 && Float.compare(this.f1935c, nVar.f1935c) == 0 && Float.compare(this.f1936d, nVar.f1936d) == 0 && Float.compare(this.f1937e, nVar.f1937e) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f1937e) + b.b.a(this.f1936d, b.b.a(this.f1935c, Float.hashCode(this.f1934b) * 31, 31), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ReflectiveCurveTo(x1=");
        sb.append(this.f1934b);
        sb.append(", y1=");
        sb.append(this.f1935c);
        sb.append(", x2=");
        sb.append(this.f1936d);
        sb.append(", y2=");
        return b.b.e(sb, this.f1937e, ')');
    }
}

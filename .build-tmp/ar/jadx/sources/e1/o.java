package e1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o extends u {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f1938b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f1939c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f1940d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f1941e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f1942f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f1943g;

    public o(float f5, float f6, float f7, float f8, float f9, float f10) {
        super(2);
        this.f1938b = f5;
        this.f1939c = f6;
        this.f1940d = f7;
        this.f1941e = f8;
        this.f1942f = f9;
        this.f1943g = f10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof o)) {
            return false;
        }
        o oVar = (o) obj;
        return Float.compare(this.f1938b, oVar.f1938b) == 0 && Float.compare(this.f1939c, oVar.f1939c) == 0 && Float.compare(this.f1940d, oVar.f1940d) == 0 && Float.compare(this.f1941e, oVar.f1941e) == 0 && Float.compare(this.f1942f, oVar.f1942f) == 0 && Float.compare(this.f1943g, oVar.f1943g) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f1943g) + b.b.a(this.f1942f, b.b.a(this.f1941e, b.b.a(this.f1940d, b.b.a(this.f1939c, Float.hashCode(this.f1938b) * 31, 31), 31), 31), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("RelativeCurveTo(dx1=");
        sb.append(this.f1938b);
        sb.append(", dy1=");
        sb.append(this.f1939c);
        sb.append(", dx2=");
        sb.append(this.f1940d);
        sb.append(", dy2=");
        sb.append(this.f1941e);
        sb.append(", dx3=");
        sb.append(this.f1942f);
        sb.append(", dy3=");
        return b.b.e(sb, this.f1943g, ')');
    }
}

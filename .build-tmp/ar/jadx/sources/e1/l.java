package e1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l extends u {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f1930b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f1931c;

    public l(float f5, float f6) {
        super(3);
        this.f1930b = f5;
        this.f1931c = f6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        return Float.compare(this.f1930b, lVar.f1930b) == 0 && Float.compare(this.f1931c, lVar.f1931c) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f1931c) + (Float.hashCode(this.f1930b) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("LineTo(x=");
        sb.append(this.f1930b);
        sb.append(", y=");
        return b.b.e(sb, this.f1931c, ')');
    }
}

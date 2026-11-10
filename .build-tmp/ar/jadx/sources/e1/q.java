package e1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class q extends u {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f1945b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f1946c;

    public q(float f5, float f6) {
        super(3);
        this.f1945b = f5;
        this.f1946c = f6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof q)) {
            return false;
        }
        q qVar = (q) obj;
        return Float.compare(this.f1945b, qVar.f1945b) == 0 && Float.compare(this.f1946c, qVar.f1946c) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f1946c) + (Float.hashCode(this.f1945b) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("RelativeLineTo(dx=");
        sb.append(this.f1945b);
        sb.append(", dy=");
        return b.b.e(sb, this.f1946c, ')');
    }
}

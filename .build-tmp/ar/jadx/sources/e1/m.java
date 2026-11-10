package e1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m extends u {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f1932b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f1933c;

    public m(float f5, float f6) {
        super(3);
        this.f1932b = f5;
        this.f1933c = f6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        return Float.compare(this.f1932b, mVar.f1932b) == 0 && Float.compare(this.f1933c, mVar.f1933c) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f1933c) + (Float.hashCode(this.f1932b) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("MoveTo(x=");
        sb.append(this.f1932b);
        sb.append(", y=");
        return b.b.e(sb, this.f1933c, ')');
    }
}

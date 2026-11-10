package b0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f408a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f409b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f410c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f411d;

    public i(float f5, float f6, float f7, float f8) {
        this.f408a = f5;
        this.f409b = f6;
        this.f410c = f7;
        this.f411d = f8;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return this.f408a == iVar.f408a && this.f409b == iVar.f409b && this.f410c == iVar.f410c && this.f411d == iVar.f411d;
    }

    public final int hashCode() {
        return Float.hashCode(this.f411d) + b.b.a(this.f410c, b.b.a(this.f409b, Float.hashCode(this.f408a) * 31, 31), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("RippleAlpha(draggedAlpha=");
        sb.append(this.f408a);
        sb.append(", focusedAlpha=");
        sb.append(this.f409b);
        sb.append(", hoveredAlpha=");
        sb.append(this.f410c);
        sb.append(", pressedAlpha=");
        return b.b.e(sb, this.f411d, ')');
    }
}

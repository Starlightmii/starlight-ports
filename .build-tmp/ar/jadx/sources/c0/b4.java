package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f774a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f775b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f776c;

    public b4(float f5, float f6, float f7) {
        this.f774a = f5;
        this.f775b = f6;
        this.f776c = f7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b4)) {
            return false;
        }
        b4 b4Var = (b4) obj;
        return k2.e.a(this.f774a, b4Var.f774a) && k2.e.a(this.f775b, b4Var.f775b) && k2.e.a(this.f776c, b4Var.f776c);
    }

    public final int hashCode() {
        return Float.hashCode(this.f776c) + b.b.a(this.f775b, Float.hashCode(this.f774a) * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TabPosition(left=");
        float f5 = this.f774a;
        sb.append((Object) k2.e.b(f5));
        sb.append(", right=");
        float f6 = this.f775b;
        sb.append((Object) k2.e.b(f5 + f6));
        sb.append(", width=");
        sb.append((Object) k2.e.b(f6));
        sb.append(", contentWidth=");
        sb.append((Object) k2.e.b(this.f776c));
        sb.append(')');
        return sb.toString();
    }
}

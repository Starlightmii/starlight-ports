package j2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final g f3315c = new g(f.f3313b, 17);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f3316a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3317b;

    public g(float f5, int i5) {
        this.f3316a = f5;
        this.f3317b = i5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        float f5 = gVar.f3316a;
        float f6 = f.f3312a;
        return Float.compare(this.f3316a, f5) == 0 && this.f3317b == gVar.f3317b;
    }

    public final int hashCode() {
        float f5 = f.f3312a;
        return Integer.hashCode(this.f3317b) + (Float.hashCode(this.f3316a) * 31);
    }

    public final String toString() {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder("LineHeightStyle(alignment=");
        float f5 = this.f3316a;
        if (f5 == 0.0f) {
            float f6 = f.f3312a;
            str = "LineHeightStyle.Alignment.Top";
        } else if (f5 == f.f3312a) {
            str = "LineHeightStyle.Alignment.Center";
        } else if (f5 == f.f3313b) {
            str = "LineHeightStyle.Alignment.Proportional";
        } else if (f5 == f.f3314c) {
            str = "LineHeightStyle.Alignment.Bottom";
        } else {
            str = "LineHeightStyle.Alignment(topPercentage = " + f5 + ')';
        }
        sb.append((Object) str);
        sb.append(", trim=");
        int i5 = this.f3317b;
        if (i5 == 1) {
            str2 = "LineHeightStyle.Trim.FirstLineTop";
        } else if (i5 == 16) {
            str2 = "LineHeightStyle.Trim.LastLineBottom";
        } else if (i5 == 17) {
            str2 = "LineHeightStyle.Trim.Both";
        } else {
            str2 = i5 == 0 ? "LineHeightStyle.Trim.None" : "Invalid";
        }
        sb.append((Object) str2);
        sb.append(')');
        return sb.toString();
    }
}

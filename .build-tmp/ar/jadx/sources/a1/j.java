package a1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j extends g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f34a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f35b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f36c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f37d;

    public j(float f5, float f6, int i5, int i6) {
        f6 = (i6 & 2) != 0 ? 4.0f : f6;
        i5 = (i6 & 8) != 0 ? 0 : i5;
        this.f34a = f5;
        this.f35b = f6;
        this.f36c = 0;
        this.f37d = i5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        return this.f34a == jVar.f34a && this.f35b == jVar.f35b && this.f36c == jVar.f36c && this.f37d == jVar.f37d;
    }

    public final int hashCode() {
        return m.h.b(this.f37d, m.h.b(this.f36c, b.b.a(this.f35b, Float.hashCode(this.f34a) * 31, 31), 31), 31);
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("Stroke(width=");
        sb.append(this.f34a);
        sb.append(", miter=");
        sb.append(this.f35b);
        sb.append(", cap=");
        String str2 = "Unknown";
        int i5 = this.f36c;
        if (i5 == 0) {
            str = "Butt";
        } else if (i5 == 1) {
            str = "Round";
        } else {
            str = i5 == 2 ? "Square" : "Unknown";
        }
        sb.append((Object) str);
        sb.append(", join=");
        int i6 = this.f37d;
        if (i6 == 0) {
            str2 = "Miter";
        } else if (i6 == 1) {
            str2 = "Round";
        } else if (i6 == 2) {
            str2 = "Bevel";
        }
        sb.append((Object) str2);
        sb.append(", pathEffect=null)");
        return sb.toString();
    }
}

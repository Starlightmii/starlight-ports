package w2;

import android.graphics.Point;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f6401a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f6402b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Point f6403c;

    public l(int i5, int i6, Point point) {
        int i7 = point.x;
        int i8 = point.y;
        this.f6401a = i5;
        this.f6402b = i6;
        this.f6403c = new Point(i7, i8);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof l) {
            l lVar = (l) obj;
            if (this.f6401a == lVar.f6401a && this.f6402b == lVar.f6402b && this.f6403c.equals(lVar.f6403c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f6403c.hashCode() + (((this.f6401a * 31) + this.f6402b) * 31);
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("RoundedCornerCompat{position=");
        int i5 = this.f6401a;
        if (i5 == 0) {
            str = "TopLeft";
        } else if (i5 == 1) {
            str = "TopRight";
        } else if (i5 != 2) {
            str = i5 != 3 ? "Invalid" : "BottomLeft";
        } else {
            str = "BottomRight";
        }
        sb.append(str);
        sb.append(", radius=");
        sb.append(this.f6402b);
        sb.append(", center=");
        sb.append(this.f6403c);
        sb.append('}');
        return sb.toString();
    }
}

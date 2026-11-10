package y0;

import android.graphics.ColorFilter;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ColorFilter f6646a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f6647b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f6648c;

    public m(long j5, int i5, ColorFilter colorFilter) {
        this.f6646a = colorFilter;
        this.f6647b = j5;
        this.f6648c = i5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        return u.c(this.f6647b, mVar.f6647b) && this.f6648c == mVar.f6648c;
    }

    public final int hashCode() {
        int i5 = u.f6695h;
        return Integer.hashCode(this.f6648c) + (Long.hashCode(this.f6647b) * 31);
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("BlendModeColorFilter(color=");
        b.b.j(this.f6647b, sb, ", blendMode=");
        int i5 = this.f6648c;
        if (i5 == 0) {
            str = "Clear";
        } else if (i5 == 1) {
            str = "Src";
        } else if (i5 == 2) {
            str = "Dst";
        } else if (i5 == 3) {
            str = "SrcOver";
        } else if (i5 == 4) {
            str = "DstOver";
        } else if (i5 == 5) {
            str = "SrcIn";
        } else if (i5 == 6) {
            str = "DstIn";
        } else if (i5 == 7) {
            str = "SrcOut";
        } else if (i5 == 8) {
            str = "DstOut";
        } else if (i5 == 9) {
            str = "SrcAtop";
        } else if (i5 == 10) {
            str = "DstAtop";
        } else if (i5 == 11) {
            str = "Xor";
        } else if (i5 == 12) {
            str = "Plus";
        } else if (i5 == 13) {
            str = "Modulate";
        } else if (i5 == 14) {
            str = "Screen";
        } else if (i5 == 15) {
            str = "Overlay";
        } else if (i5 == 16) {
            str = "Darken";
        } else if (i5 == 17) {
            str = "Lighten";
        } else if (i5 == 18) {
            str = "ColorDodge";
        } else if (i5 == 19) {
            str = "ColorBurn";
        } else if (i5 == 20) {
            str = "HardLight";
        } else if (i5 == 21) {
            str = "Softlight";
        } else if (i5 == 22) {
            str = "Difference";
        } else if (i5 == 23) {
            str = "Exclusion";
        } else if (i5 == 24) {
            str = "Multiply";
        } else if (i5 == 25) {
            str = "Hue";
        } else if (i5 == 26) {
            str = "Saturation";
        } else if (i5 == 27) {
            str = "Color";
        } else {
            str = i5 == 28 ? "Luminosity" : "Unknown";
        }
        sb.append((Object) str);
        sb.append(')');
        return sb.toString();
    }
}

package j2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f3310b = 66305;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3311a;

    public static String a(int i5) {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder("LineBreak(strategy=");
        int i6 = i5 & 255;
        String str3 = "Invalid";
        if (i6 == 1) {
            str = "Strategy.Simple";
        } else if (i6 == 2) {
            str = "Strategy.HighQuality";
        } else if (i6 == 3) {
            str = "Strategy.Balanced";
        } else {
            str = i6 == 0 ? "Strategy.Unspecified" : "Invalid";
        }
        sb.append((Object) str);
        sb.append(", strictness=");
        int i7 = (i5 >> 8) & 255;
        if (i7 == 1) {
            str2 = "Strictness.None";
        } else if (i7 == 2) {
            str2 = "Strictness.Loose";
        } else if (i7 == 3) {
            str2 = "Strictness.Normal";
        } else if (i7 == 4) {
            str2 = "Strictness.Strict";
        } else {
            str2 = i7 == 0 ? "Strictness.Unspecified" : "Invalid";
        }
        sb.append((Object) str2);
        sb.append(", wordBreak=");
        int i8 = (i5 >> 16) & 255;
        if (i8 == 1) {
            str3 = "WordBreak.None";
        } else if (i8 == 2) {
            str3 = "WordBreak.Phrase";
        } else if (i8 == 0) {
            str3 = "WordBreak.Unspecified";
        }
        sb.append((Object) str3);
        sb.append(')');
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof e) {
            return this.f3311a == ((e) obj).f3311a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f3311a);
    }

    public final String toString() {
        return a(this.f3311a);
    }
}

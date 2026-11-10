package w2;

import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f6386a;

    static {
        new e(0, 0, "");
    }

    public e(int i5, int i6, String str) {
        this.f6386a = new d(i5, i6, str);
    }

    public static e a(int i5, int i6, boolean z2, int i7, int i8, int i9, int i10) {
        String string;
        if (z2) {
            int i11 = i5 / 2;
            int i12 = i6 / 2;
            string = "M0," + i12 + " A" + i11 + "," + i12 + " 0 1,1 " + i5 + "," + i12 + " A" + i11 + "," + i12 + " 0 1,1 0," + i12 + " Z";
        } else {
            StringBuilder sb = new StringBuilder("M ");
            int iMin = Math.min(i5 / 2, i6 / 2);
            int iMin2 = Math.min(iMin, i7);
            int iMin3 = Math.min(iMin, i8);
            int iMin4 = Math.min(iMin, i9);
            int iMin5 = Math.min(iMin, i10);
            sb.append(iMin2);
            sb.append(",0 L ");
            sb.append(i5 - iMin3);
            sb.append(",0");
            if (iMin3 > 0) {
                sb.append(" A ");
                sb.append(iMin3);
                sb.append(",");
                sb.append(iMin3);
                sb.append(" 0 0,1 ");
                sb.append(i5);
                sb.append(",");
                sb.append(iMin3);
            }
            sb.append(" L ");
            sb.append(i5);
            sb.append(",");
            sb.append(i6 - iMin4);
            if (iMin4 > 0) {
                sb.append(" A ");
                sb.append(iMin4);
                sb.append(",");
                sb.append(iMin4);
                sb.append(" 0 0,1 ");
                sb.append(i5 - iMin4);
                sb.append(",");
                sb.append(i6);
            }
            sb.append(" L ");
            sb.append(iMin5);
            sb.append(",");
            sb.append(i6);
            if (iMin5 > 0) {
                sb.append(" A ");
                sb.append(iMin5);
                sb.append(",");
                sb.append(iMin5);
                sb.append(" 0 0,1 0,");
                sb.append(i6 - iMin5);
            }
            if (iMin2 > 0) {
                sb.append(" L 0,");
                sb.append(iMin2);
                sb.append(" A ");
                sb.append(iMin2);
                sb.append(",");
                sb.append(iMin2);
                sb.append(" 0 0,1 ");
                sb.append(iMin2);
                sb.append(",0");
            }
            sb.append(" Z");
            string = sb.toString();
        }
        return new e(i5, i6, string);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof e) {
            return Objects.equals(this.f6386a, ((e) obj).f6386a);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.f6386a);
    }

    public final String toString() {
        return this.f6386a.toString();
    }
}

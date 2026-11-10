package q1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static e f5157c;

    @Override // q1.b
    public final int[] a(int i5) {
        int length = c().length();
        if (length <= 0 || i5 >= length) {
            return null;
        }
        if (i5 < 0) {
            i5 = 0;
        }
        while (i5 < length && c().charAt(i5) == '\n' && (c().charAt(i5) == '\n' || (i5 != 0 && c().charAt(i5 - 1) != '\n'))) {
            i5++;
        }
        if (i5 >= length) {
            return null;
        }
        int i6 = i5 + 1;
        while (i6 < length && !e(i6)) {
            i6++;
        }
        return b(i5, i6);
    }

    @Override // q1.b
    public final int[] d(int i5) {
        int length = c().length();
        if (length <= 0 || i5 <= 0) {
            return null;
        }
        if (i5 > length) {
            i5 = length;
        }
        while (i5 > 0 && c().charAt(i5 - 1) == '\n' && !e(i5)) {
            i5--;
        }
        if (i5 <= 0) {
            return null;
        }
        int i6 = i5 - 1;
        while (i6 > 0 && (c().charAt(i6) == '\n' || (i6 != 0 && c().charAt(i6 - 1) != '\n'))) {
            i6--;
        }
        return b(i6, i5);
    }

    public final boolean e(int i5) {
        if (i5 <= 0 || c().charAt(i5 - 1) == '\n') {
            return false;
        }
        return i5 == c().length() || c().charAt(i5) == '\n';
    }
}

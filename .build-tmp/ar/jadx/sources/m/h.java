package m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f3670a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48};

    public static /* synthetic */ int a(int i5, int i6) {
        if (i5 == 0 || i6 == 0) {
            throw null;
        }
        return i5 - i6;
    }

    public static int b(int i5, int i6, int i7) {
        return (Integer.hashCode(i5) + i6) * i7;
    }

    public static /* synthetic */ int c(int i5) {
        if (i5 != 0) {
            return i5 - 1;
        }
        throw null;
    }
}

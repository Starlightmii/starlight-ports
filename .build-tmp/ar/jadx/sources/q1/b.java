package q1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5116a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f5117b = new int[2];

    public abstract int[] a(int i5);

    public final int[] b(int i5, int i6) {
        if (i5 < 0 || i6 < 0 || i5 == i6) {
            return null;
        }
        int[] iArr = this.f5117b;
        iArr[0] = i5;
        iArr[1] = i6;
        return iArr;
    }

    public final String c() {
        String str = this.f5116a;
        if (str != null) {
            return str;
        }
        b4.l.j("text");
        throw null;
    }

    public abstract int[] d(int i5);
}

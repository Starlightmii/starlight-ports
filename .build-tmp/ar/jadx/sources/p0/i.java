package p0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4698a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long[] f4699b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int[] f4700c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f4701d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4702e;

    public final int a(long j5) {
        int i5 = this.f4698a + 1;
        long[] jArr = this.f4699b;
        int length = jArr.length;
        if (i5 > length) {
            int i6 = length * 2;
            long[] jArr2 = new long[i6];
            int[] iArr = new int[i6];
            q3.k.z(jArr, jArr2, 0, 0, jArr.length);
            q3.k.B(this.f4700c, iArr, 0, 0, 14);
            this.f4699b = jArr2;
            this.f4700c = iArr;
        }
        int i7 = this.f4698a;
        this.f4698a = i7 + 1;
        int length2 = this.f4701d.length;
        if (this.f4702e >= length2) {
            int i8 = length2 * 2;
            int[] iArr2 = new int[i8];
            int i9 = 0;
            while (i9 < i8) {
                int i10 = i9 + 1;
                iArr2[i9] = i10;
                i9 = i10;
            }
            q3.k.B(this.f4701d, iArr2, 0, 0, 14);
            this.f4701d = iArr2;
        }
        int i11 = this.f4702e;
        int[] iArr3 = this.f4701d;
        this.f4702e = iArr3[i11];
        long[] jArr3 = this.f4699b;
        jArr3[i7] = j5;
        this.f4700c[i7] = i11;
        iArr3[i11] = i7;
        while (i7 > 0) {
            int i12 = ((i7 + 1) >> 1) - 1;
            if (b4.l.g(jArr3[i12], j5) <= 0) {
                break;
            }
            b(i12, i7);
            i7 = i12;
        }
        return i11;
    }

    public final void b(int i5, int i6) {
        long[] jArr = this.f4699b;
        int[] iArr = this.f4700c;
        int[] iArr2 = this.f4701d;
        long j5 = jArr[i5];
        jArr[i5] = jArr[i6];
        jArr[i6] = j5;
        int i7 = iArr[i5];
        int i8 = iArr[i6];
        iArr[i5] = i8;
        iArr[i6] = i7;
        iArr2[i8] = i5;
        iArr2[i7] = i6;
    }
}

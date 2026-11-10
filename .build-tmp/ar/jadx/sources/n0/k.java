package n0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f4082a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long[] f4083b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object[] f4084c;

    public k(int i5, long[] jArr, Object[] objArr) {
        this.f4082a = i5;
        this.f4083b = jArr;
        this.f4084c = objArr;
    }

    public final int a(long j5) {
        int i5 = this.f4082a - 1;
        if (i5 != -1) {
            long[] jArr = this.f4083b;
            int i6 = 0;
            if (i5 != 0) {
                while (i6 <= i5) {
                    int i7 = (i6 + i5) >>> 1;
                    long j6 = jArr[i7] - j5;
                    if (j6 < 0) {
                        i6 = i7 + 1;
                    } else {
                        if (j6 <= 0) {
                            return i7;
                        }
                        i5 = i7 - 1;
                    }
                }
                return -(i6 + 1);
            }
            long j7 = jArr[0];
            if (j7 == j5) {
                return 0;
            }
            if (j7 > j5) {
                return -2;
            }
        }
        return -1;
    }

    public final k b(long j5, Object obj) {
        long[] jArr;
        int i5;
        Object[] objArr = this.f4084c;
        int i6 = 0;
        int i7 = 0;
        for (Object obj2 : objArr) {
            if (obj2 != null) {
                i7++;
            }
        }
        int i8 = i7 + 1;
        long[] jArr2 = new long[i8];
        Object[] objArr2 = new Object[i8];
        if (i8 > 1) {
            int i9 = 0;
            while (true) {
                jArr = this.f4083b;
                i5 = this.f4082a;
                if (i6 >= i8 || i9 >= i5) {
                    break;
                }
                long j6 = jArr[i9];
                Object obj3 = objArr[i9];
                if (j6 > j5) {
                    jArr2[i6] = j5;
                    objArr2[i6] = obj;
                    i6++;
                    break;
                }
                if (obj3 != null) {
                    jArr2[i6] = j6;
                    objArr2[i6] = obj3;
                    i6++;
                }
                i9++;
            }
            if (i9 == i5) {
                jArr2[i7] = j5;
                objArr2[i7] = obj;
            } else {
                while (i6 < i8) {
                    long j7 = jArr[i9];
                    Object obj4 = objArr[i9];
                    if (obj4 != null) {
                        jArr2[i6] = j7;
                        objArr2[i6] = obj4;
                        i6++;
                    }
                    i9++;
                }
            }
        } else {
            jArr2[0] = j5;
            objArr2[0] = obj;
        }
        return new k(i8, jArr2, objArr2);
    }
}

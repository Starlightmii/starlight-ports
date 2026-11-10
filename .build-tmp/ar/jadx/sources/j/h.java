package j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float[] f3107a;

    static {
        long[] jArr = f0.f3102a;
        int iD = f0.d(0);
        int iMax = iD > 0 ? Math.max(7, f0.c(iD)) : 0;
        if (iMax != 0) {
            jArr = new long[((iMax + 15) & (-8)) >> 3];
            q3.k.F(jArr);
        }
        int i5 = iMax >> 3;
        long j5 = 255 << ((iMax & 7) << 3);
        jArr[i5] = (jArr[i5] & (~j5)) | j5;
        float[] fArr = new float[iMax];
        f3107a = new float[0];
    }
}

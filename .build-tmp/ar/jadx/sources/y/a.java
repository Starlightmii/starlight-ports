package y;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f6569a = a(Float.NaN, Float.NaN);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f6570b = 0;

    public static long a(float f5, float f6) {
        return (((long) Float.floatToRawIntBits(f6)) & 4294967295L) | (Float.floatToRawIntBits(f5) << 32);
    }
}

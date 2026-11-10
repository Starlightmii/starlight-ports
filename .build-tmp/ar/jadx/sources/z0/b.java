package z0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f7032a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f7033b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final long f7034c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final long f7035d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ int f7036e = 0;

    static {
        long j5 = 3;
        long j6 = j5 << 32;
        f7032a = (((long) 0) & 4294967295L) | j6;
        f7033b = (((long) 1) & 4294967295L) | j6;
        f7034c = j6 | (((long) 2) & 4294967295L);
        f7035d = (j5 & 4294967295L) | (((long) 4) << 32);
    }

    public static final boolean a(long j5, long j6) {
        return j5 == j6;
    }

    public static String b(long j5) {
        if (a(j5, f7032a)) {
            return "Rgb";
        }
        if (a(j5, f7033b)) {
            return "Xyz";
        }
        if (a(j5, f7034c)) {
            return "Lab";
        }
        return a(j5, f7035d) ? "Cmyk" : "Unknown";
    }
}

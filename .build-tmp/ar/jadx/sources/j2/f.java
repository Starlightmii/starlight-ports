package j2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f3312a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f3313b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final float f3314c;

    static {
        a(0.0f);
        a(0.5f);
        f3312a = 0.5f;
        a(-1.0f);
        f3313b = -1.0f;
        a(1.0f);
        f3314c = 1.0f;
    }

    public static void a(float f5) {
        if ((0.0f > f5 || f5 > 1.0f) && f5 != -1.0f) {
            throw new IllegalStateException("topRatio should be in [0..1] range or -1");
        }
    }
}

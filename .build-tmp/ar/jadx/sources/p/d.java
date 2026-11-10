package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public interface d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c f4424a = c.f4417a;

    default float a(float f5, float f6, float f7) {
        f4424a.getClass();
        float f8 = f6 + f5;
        if ((f5 >= 0.0f && f8 <= f7) || (f5 < 0.0f && f8 > f7)) {
            return 0.0f;
        }
        float f9 = f8 - f7;
        return Math.abs(f5) < Math.abs(f9) ? f5 : f9;
    }

    default m.k b() {
        f4424a.getClass();
        return c.f4418b;
    }
}

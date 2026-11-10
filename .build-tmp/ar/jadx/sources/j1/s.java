package j1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f3272a = new a(1000);

    static {
        new a(1007);
        new a(1008);
        new a(1002);
    }

    public static final boolean a(u uVar) {
        return !uVar.f3281h && uVar.f3277d;
    }

    public static final boolean b(u uVar) {
        return (uVar.b() || !uVar.f3281h || uVar.f3277d) ? false : true;
    }

    public static final boolean c(u uVar) {
        return uVar.f3281h && !uVar.f3277d;
    }

    public static final boolean d(u uVar, long j5) {
        long j6 = uVar.f3276c;
        float fD = x0.c.d(j6);
        float fE = x0.c.e(j6);
        return fD < 0.0f || fD > ((float) ((int) (j5 >> 32))) || fE < 0.0f || fE > ((float) ((int) (j5 & 4294967295L)));
    }

    public static final boolean e(u uVar, long j5, long j6) {
        if (uVar.f3282i != 1) {
            return d(uVar, j5);
        }
        long j7 = uVar.f3276c;
        float fD = x0.c.d(j7);
        float fE = x0.c.e(j7);
        return fD < (-x0.f.d(j6)) || fD > x0.f.d(j6) + ((float) ((int) (j5 >> 32))) || fE < (-x0.f.b(j6)) || fE > x0.f.b(j6) + ((float) ((int) (j5 & 4294967295L)));
    }

    public static final long f(u uVar, boolean z2) {
        long jG = x0.c.g(uVar.f3276c, uVar.f3280g);
        if (z2 || !uVar.b()) {
            return jG;
        }
        return 0L;
    }
}

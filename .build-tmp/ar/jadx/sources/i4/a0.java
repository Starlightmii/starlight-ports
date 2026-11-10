package i4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b0 f2714a;

    static {
        String property;
        j4.d dVar;
        b0 b0Var;
        int i5 = n4.s.f4309a;
        try {
            property = System.getProperty("kotlinx.coroutines.main.delay");
        } catch (SecurityException unused) {
            property = null;
        }
        if (property != null ? Boolean.parseBoolean(property) : false) {
            p4.e eVar = e0.f2727a;
            dVar = n4.m.f4303a;
            j4.d dVar2 = dVar.f3351i;
            if (dVar == null) {
                b0Var = dVar;
                b0Var = z.f2800m;
            }
        } else {
            b0Var = z.f2800m;
        }
        b0Var = dVar;
        f2714a = b0Var;
    }
}

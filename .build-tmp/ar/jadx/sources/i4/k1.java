package i4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class k1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal f2748a = new ThreadLocal();

    public static l0 a() {
        ThreadLocal threadLocal = f2748a;
        l0 l0Var = (l0) threadLocal.get();
        if (l0Var != null) {
            return l0Var;
        }
        d dVar = new d(Thread.currentThread());
        threadLocal.set(dVar);
        return dVar;
    }
}

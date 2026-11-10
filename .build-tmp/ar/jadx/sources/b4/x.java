package b4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final y f698a;

    static {
        y yVar = null;
        try {
            yVar = (y) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (yVar == null) {
            yVar = new y();
        }
        f698a = yVar;
    }

    public static f a(Class cls) {
        f698a.getClass();
        return new f(cls);
    }
}

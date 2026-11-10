package x3;

import b4.l;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Method f6567a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Method f6568b;

    static {
        Method method;
        Method method2;
        Method[] methods = Throwable.class.getMethods();
        l.b(methods);
        int length = methods.length;
        int i5 = 0;
        while (true) {
            method = null;
            if (i5 >= length) {
                method2 = null;
                break;
            }
            method2 = methods[i5];
            if (l.a(method2.getName(), "addSuppressed")) {
                Class<?>[] parameterTypes = method2.getParameterTypes();
                l.d("getParameterTypes(...)", parameterTypes);
                if (l.a(parameterTypes.length == 1 ? parameterTypes[0] : null, Throwable.class)) {
                    break;
                }
            }
            i5++;
        }
        f6567a = method2;
        for (Method method3 : methods) {
            if (l.a(method3.getName(), "getSuppressed")) {
                method = method3;
                break;
            }
        }
        f6568b = method;
    }
}

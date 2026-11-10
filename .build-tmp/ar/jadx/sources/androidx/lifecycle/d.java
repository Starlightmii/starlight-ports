package androidx.lifecycle;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final d f236c = new d();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f237a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f238b = new HashMap();

    public static void b(HashMap map, c cVar, o oVar, Class cls) {
        o oVar2 = (o) map.get(cVar);
        if (oVar2 == null || oVar == oVar2) {
            if (oVar2 == null) {
                map.put(cVar, oVar);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + cVar.f235b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + oVar2 + ", new value " + oVar);
    }

    public final b a(Class cls, Method[] methodArr) {
        int i5;
        Class superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        HashMap map2 = this.f237a;
        if (superclass != null) {
            b bVarA = (b) map2.get(superclass);
            if (bVarA == null) {
                bVarA = a(superclass, null);
            }
            map.putAll(bVarA.f233b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            b bVarA2 = (b) map2.get(cls2);
            if (bVarA2 == null) {
                bVarA2 = a(cls2, null);
            }
            for (Map.Entry entry : bVarA2.f233b.entrySet()) {
                b(map, (c) entry.getKey(), (o) entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            try {
                methodArr = cls.getDeclaredMethods();
            } catch (NoClassDefFoundError e5) {
                throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e5);
            }
        }
        boolean z2 = false;
        for (Method method : methodArr) {
            z zVar = (z) method.getAnnotation(z.class);
            if (zVar != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i5 = 0;
                } else {
                    if (!v.class.isAssignableFrom(parameterTypes[0])) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i5 = 1;
                }
                o oVarValue = zVar.value();
                if (parameterTypes.length > 1) {
                    if (!o.class.isAssignableFrom(parameterTypes[1])) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (oVarValue != o.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i5 = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                b(map, new c(i5, method), oVarValue, cls);
                z2 = true;
            }
        }
        b bVar = new b(map);
        map2.put(cls, bVar);
        this.f238b.put(cls, Boolean.valueOf(z2));
        return bVar;
    }
}

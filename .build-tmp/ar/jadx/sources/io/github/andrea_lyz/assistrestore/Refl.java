package io.github.andrea_lyz.assistrestore;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class Refl {
    private Refl() {
    }

    public static Field field(Class<?> cls, String str) {
        while (cls != null && cls != Object.class) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                declaredField.setAccessible(true);
                return declaredField;
            } catch (Throwable unused) {
                cls = cls.getSuperclass();
            }
        }
        return null;
    }

    public static Class<?> load(ClassLoader classLoader, String str) {
        try {
            return Class.forName(str, false, classLoader);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Method method(Class<?> cls, String str, Class<?>... clsArr) {
        while (cls != null && cls != Object.class) {
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (Throwable unused) {
                cls = cls.getSuperclass();
            }
        }
        return null;
    }

    public static Boolean staticBooleanField(ClassLoader classLoader, String str, String str2) {
        Class<?> clsLoad = load(classLoader, str);
        if (clsLoad == null) {
            return null;
        }
        try {
            return Boolean.valueOf(clsLoad.getField(str2).getBoolean(null));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Method staticMethod(ClassLoader classLoader, String str, String str2, Class<?>... clsArr) {
        Class<?> clsLoad = load(classLoader, str);
        if (clsLoad == null) {
            return null;
        }
        try {
            return clsLoad.getMethod(str2, clsArr);
        } catch (Throwable unused) {
            return null;
        }
    }
}

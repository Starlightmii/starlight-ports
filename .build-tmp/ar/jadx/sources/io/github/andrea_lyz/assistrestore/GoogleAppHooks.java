package io.github.andrea_lyz.assistrestore;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class GoogleAppHooks {
    private static final String BRAND = "samsung";
    private static final String DEVICE = "e3s";
    private static final String MANUFACTURER = "samsung";
    private static final String MODEL = "SM-S928B";
    private static final String PRODUCT = "e3s";

    private GoogleAppHooks() {
    }

    public static void install(AssistRestoreModule assistRestoreModule, ClassLoader classLoader) {
        if (!AssistConfig.isEnabled(HookPrefs.get())) {
            assistRestoreModule.logInfo("google_app_identity_spoof_skipped reason=module_disabled");
            return;
        }
        if (!AssistConfig.spoofGoogleBuild(HookPrefs.get())) {
            assistRestoreModule.logInfo("google_app_identity_spoof_skipped reason=switch_off");
            return;
        }
        try {
            Class<?> cls = Class.forName("android.os.Build", true, classLoader);
            setStaticField(cls, "MANUFACTURER", "samsung");
            setStaticField(cls, "BRAND", "samsung");
            setStaticField(cls, "MODEL", MODEL);
            setStaticField(cls, "PRODUCT", "e3s");
            setStaticField(cls, "DEVICE", "e3s");
            assistRestoreModule.logInfo("google_app_identity_spoofed model=SM-S928B manufacturer=samsung");
        } catch (Throwable th) {
            assistRestoreModule.logError("google_app_identity_spoof_failed", th);
        }
    }

    private static Object internalUnsafe() {
        try {
            Field declaredField = Class.forName("sun.misc.Unsafe").getDeclaredField("theInternalUnsafe");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(null);
            if (obj != null) {
                return obj;
            }
        } catch (Throwable unused) {
        }
        return Class.forName("jdk.internal.misc.Unsafe").getMethod("getUnsafe", null).invoke(null, null);
    }

    private static void setStaticField(Class<?> cls, String str, String str2) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);
        try {
            declaredField.set(null, str2);
        } catch (Throwable unused) {
            writeWithUnsafe(declaredField, str2);
        }
    }

    private static void writeWithUnsafe(Field field, Object obj) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object objInternalUnsafe = internalUnsafe();
        Class<?> cls = objInternalUnsafe.getClass();
        cls.getMethod("putObjectVolatile", Object.class, Long.TYPE, Object.class).invoke(objInternalUnsafe, cls.getMethod("staticFieldBase", Field.class).invoke(objInternalUnsafe, field), Long.valueOf(((Number) cls.getMethod("staticFieldOffset", Field.class).invoke(objInternalUnsafe, field)).longValue()), obj);
    }
}

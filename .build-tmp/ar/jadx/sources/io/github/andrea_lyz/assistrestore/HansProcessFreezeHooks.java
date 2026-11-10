package io.github.andrea_lyz.assistrestore;

import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class HansProcessFreezeHooks {
    private static final String SCENE = "com.android.server.hans.scene.HansSceneManager";

    private HansProcessFreezeHooks() {
    }

    public static void install(AssistRestoreModule assistRestoreModule, ClassLoader classLoader) {
        try {
            Class<?> cls = Class.forName(SCENE, false, classLoader);
            Class<?> cls2 = Class.forName("com.android.server.hans.OplusHansPackage", false, classLoader);
            Class<?> cls3 = Class.forName("com.android.server.hans.OplusHansRestriction", false, classLoader);
            Object obj = Class.forName("com.android.server.hans.scene.HansSceneManager$Freezing", false, classLoader).getField("IMPORTANT").get(null);
            Method method = cls2.getMethod("getPkgName", null);
            Method method2 = cls2.getMethod("getUid", null);
            installMethod(assistRestoreModule, cls.getDeclaredMethod("freezeForSceneCombo", cls2, cls3), method, method2, obj);
            installMethod(assistRestoreModule, cls.getDeclaredMethod("freezeDirectlyForSceneCombo", cls2), method, method2, obj);
        } catch (Throwable th) {
            assistRestoreModule.logError("hook_failed target=com.android.server.hans.scene.HansSceneManager", th);
        }
    }

    private static void installMethod(AssistRestoreModule assistRestoreModule, Method method, Method method2, Method method3, Object obj) {
        method.setAccessible(true);
        assistRestoreModule.hook(method).setId("google_hans_scene_" + method.getName()).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new d(assistRestoreModule, method2, method, method3, obj));
        assistRestoreModule.logInfo("hook_installed target=com.android.server.hans.scene.HansSceneManager." + method.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$installMethod$0(Method method, AssistRestoreModule assistRestoreModule, Method method2, Method method3, Object obj, XposedInterface.Chain chain) {
        Object arg = chain.getArg(0);
        if (arg == null || !AssistConfig.isEnabled(HookPrefs.get()) || !"com.google.android.googlequicksearchbox".equals(method.invoke(arg, null))) {
            return chain.proceed();
        }
        assistRestoreModule.logInfo("google_hans_scene_exempt method=" + method2.getName() + " uid=" + method3.invoke(arg, null));
        return obj;
    }
}

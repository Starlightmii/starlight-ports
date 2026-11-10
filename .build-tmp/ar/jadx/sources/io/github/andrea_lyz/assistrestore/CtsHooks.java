package io.github.andrea_lyz.assistrestore;

import android.content.Context;
import android.os.Binder;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class CtsHooks {
    static final int CONTEXTUAL_SEARCH_ENTRYPOINT = 2;
    private static final String CTS_CONFIG = "android.app.contextualsearch.ContextualSearchConfig";
    private static final String CTS_SERVICE = "com.android.server.contextualsearch.ContextualSearchManagerService";
    static final String PKG_GOOGLE = "com.google.android.googlequicksearchbox";
    private static final String PKG_SYSTEMUI = "com.android.systemui";
    private static final String SYSTEM_SERVER = "com.android.server.SystemServer";

    private CtsHooks() {
    }

    private static int hookStartContextualSearch(AssistRestoreModule assistRestoreModule, Class<?> cls) {
        int i5 = 0;
        for (Method method : cls.getDeclaredMethods()) {
            if ("startContextualSearch".equals(method.getName())) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                int length = parameterTypes.length;
                Class<?> cls2 = Integer.TYPE;
                boolean z2 = length == 1 && parameterTypes[0] == cls2;
                int i6 = 2;
                boolean z3 = parameterTypes.length == 2 && parameterTypes[0] == cls2 && CTS_CONFIG.equals(parameterTypes[1].getName());
                if (z2 || z3) {
                    method.setAccessible(true);
                    try {
                        assistRestoreModule.hook(method).setId("cts_start_contextual_search").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new a(i6, assistRestoreModule));
                        i5++;
                    } catch (Throwable th) {
                        assistRestoreModule.logError("hook_failed target=" + cls.getName() + ".startContextualSearch", th);
                    }
                }
            }
        }
        return i5;
    }

    public static void install(AssistRestoreModule assistRestoreModule, ClassLoader classLoader) {
        installDeviceHasConfigString(assistRestoreModule, classLoader, resolvePackageNameResId(assistRestoreModule, classLoader));
        installContextualSearchPackageName(assistRestoreModule, classLoader);
        installContextualSearchAvailability(assistRestoreModule, classLoader);
        installPermissionBypass(assistRestoreModule, classLoader);
        installStartContextualSearch(assistRestoreModule, classLoader);
    }

    private static void installContextualSearchAvailability(AssistRestoreModule assistRestoreModule, ClassLoader classLoader) {
        try {
            Class<?> cls = Class.forName(CTS_SERVICE, true, classLoader);
            int i5 = 0;
            Method method = method(cls, "isContextualSearchAvailable", new Class[0]);
            if (method == null) {
                for (Class<?> cls2 : cls.getDeclaredClasses()) {
                    method = method(cls2, "isContextualSearchAvailable", new Class[0]);
                    if (method != null) {
                        break;
                    }
                }
            }
            if (method == null) {
                assistRestoreModule.logInfo("hook_skipped target=com.android.server.contextualsearch.ContextualSearchManagerService.isContextualSearchAvailable reason=not_found");
            } else {
                assistRestoreModule.hook(method).setId("cts_is_available").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new a(i5, assistRestoreModule));
                assistRestoreModule.logInfo("hook_installed target=com.android.server.contextualsearch.ContextualSearchManagerService.isContextualSearchAvailable");
            }
        } catch (Throwable th) {
            assistRestoreModule.logError("hook_failed target=com.android.server.contextualsearch.ContextualSearchManagerService.isContextualSearchAvailable", th);
        }
    }

    private static void installContextualSearchPackageName(AssistRestoreModule assistRestoreModule, ClassLoader classLoader) {
        try {
            Method method = method(Class.forName(CTS_SERVICE, true, classLoader), "getContextualSearchPackageName", new Class[0]);
            if (method == null) {
                assistRestoreModule.logError("hook_skipped target=com.android.server.contextualsearch.ContextualSearchManagerService.getContextualSearchPackageName reason=not_found");
            } else {
                assistRestoreModule.hook(method).setId("cts_package_name").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new b());
                assistRestoreModule.logInfo("hook_installed target=com.android.server.contextualsearch.ContextualSearchManagerService.getContextualSearchPackageName");
            }
        } catch (Throwable th) {
            assistRestoreModule.logError("hook_failed target=com.android.server.contextualsearch.ContextualSearchManagerService.getContextualSearchPackageName", th);
        }
    }

    private static void installDeviceHasConfigString(final AssistRestoreModule assistRestoreModule, ClassLoader classLoader, final int i5) {
        try {
            Method method = method(Class.forName(SYSTEM_SERVER, true, classLoader), "deviceHasConfigString", Context.class, Integer.TYPE);
            if (method == null) {
                assistRestoreModule.logError("hook_skipped target=com.android.server.SystemServer.deviceHasConfigString reason=not_found");
            } else {
                assistRestoreModule.hook(method).setId("cts_device_has_config_string").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new XposedInterface.Hooker() { // from class: io.github.andrea_lyz.assistrestore.c
                    public final Object intercept(XposedInterface.Chain chain) {
                        return CtsHooks.lambda$installDeviceHasConfigString$0(i5, assistRestoreModule, chain);
                    }
                });
                assistRestoreModule.logInfo("hook_installed target=com.android.server.SystemServer.deviceHasConfigString");
            }
        } catch (Throwable th) {
            assistRestoreModule.logError("hook_failed target=com.android.server.SystemServer.deviceHasConfigString", th);
        }
    }

    private static void installPermissionBypass(AssistRestoreModule assistRestoreModule, ClassLoader classLoader) {
        try {
            int i5 = 1;
            Method method = method(Class.forName(CTS_SERVICE, true, classLoader), "enforcePermission", String.class);
            if (method == null) {
                assistRestoreModule.logError("hook_skipped target=com.android.server.contextualsearch.ContextualSearchManagerService.enforcePermission reason=not_found");
            } else {
                assistRestoreModule.hook(method).setId("cts_enforce_permission").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new a(i5, assistRestoreModule));
                assistRestoreModule.logInfo("hook_installed target=com.android.server.contextualsearch.ContextualSearchManagerService.enforcePermission");
            }
        } catch (Throwable th) {
            assistRestoreModule.logError("hook_failed target=com.android.server.contextualsearch.ContextualSearchManagerService.enforcePermission", th);
        }
    }

    private static void installStartContextualSearch(AssistRestoreModule assistRestoreModule, ClassLoader classLoader) {
        try {
            Class<?> cls = Class.forName(CTS_SERVICE, true, classLoader);
            int iHookStartContextualSearch = hookStartContextualSearch(assistRestoreModule, cls);
            for (Class<?> cls2 : cls.getDeclaredClasses()) {
                iHookStartContextualSearch += hookStartContextualSearch(assistRestoreModule, cls2);
            }
            if (iHookStartContextualSearch == 0) {
                assistRestoreModule.logError("hook_skipped target=com.android.server.contextualsearch.ContextualSearchManagerService.startContextualSearch reason=not_found");
                return;
            }
            assistRestoreModule.logInfo("hook_installed target=com.android.server.contextualsearch.ContextualSearchManagerService.startContextualSearch count=" + iHookStartContextualSearch);
        } catch (Throwable th) {
            assistRestoreModule.logError("hook_failed target=com.android.server.contextualsearch.ContextualSearchManagerService.startContextualSearch", th);
        }
    }

    public static boolean isTrustedCaller(AssistRestoreModule assistRestoreModule) {
        int callingUid = Binder.getCallingUid();
        if (callingUid == 1000) {
            return true;
        }
        String[] strArrPackagesForUid = packagesForUid(callingUid);
        if (strArrPackagesForUid == null) {
            return false;
        }
        for (String str : strArrPackagesForUid) {
            if (PKG_SYSTEMUI.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$hookStartContextualSearch$4(AssistRestoreModule assistRestoreModule, XposedInterface.Chain chain) {
        if (!isTrustedCaller(assistRestoreModule)) {
            return chain.proceed();
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            assistRestoreModule.logInfo("cts_start_contextual_search entrypoint=" + chain.getArg(0));
            return chain.proceed();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$installContextualSearchAvailability$2(AssistRestoreModule assistRestoreModule, XposedInterface.Chain chain) {
        return isTrustedCaller(assistRestoreModule) ? Boolean.TRUE : chain.proceed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$installDeviceHasConfigString$0(int i5, AssistRestoreModule assistRestoreModule, XposedInterface.Chain chain) {
        int iIntValue = ((Integer) chain.getArg(1)).intValue();
        if (i5 == 0 || iIntValue != i5) {
            return chain.proceed();
        }
        assistRestoreModule.logInfo("cts_device_has_config_string forced=true");
        return Boolean.TRUE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$installPermissionBypass$3(AssistRestoreModule assistRestoreModule, XposedInterface.Chain chain) {
        if (isTrustedCaller(assistRestoreModule)) {
            return null;
        }
        return chain.proceed();
    }

    private static Method method(Class<?> cls, String str, Class<?>... clsArr) {
        Method method = Refl.method(cls, str, clsArr);
        if (method != null) {
            method.setAccessible(true);
        }
        return method;
    }

    private static String[] packagesForUid(int i5) {
        try {
            Object objInvoke = Class.forName("android.app.AppGlobals").getMethod("getPackageManager", null).invoke(null, null);
            if (objInvoke == null) {
                return null;
            }
            return (String[]) objInvoke.getClass().getMethod("getPackagesForUid", Integer.TYPE).invoke(objInvoke, Integer.valueOf(i5));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static int resolvePackageNameResId(AssistRestoreModule assistRestoreModule, ClassLoader classLoader) {
        try {
            int i5 = Class.forName("com.android.internal.R$string", true, classLoader).getField("config_defaultContextualSearchPackageName").getInt(null);
            assistRestoreModule.logInfo("cts_resource_ids packageNameId=" + i5);
            return i5;
        } catch (Throwable th) {
            assistRestoreModule.logError("cts_resource_ids_failed", th);
            return 0;
        }
    }
}

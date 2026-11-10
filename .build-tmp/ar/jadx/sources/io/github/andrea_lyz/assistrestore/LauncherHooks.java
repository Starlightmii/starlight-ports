package io.github.andrea_lyz.assistrestore;

import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class LauncherHooks {
    private static final long BLOCKING_STATES = 2059;
    private static final String LAUNCHER_GESTURE_CONTRACT = "ab.n";
    private static final String LAUNCHER_GESTURE_METHOD = "c";
    private static final long PAGE_LEVEL_STATES = 1152;
    private static final String QUICK_STEP_CONTRACT = "com.android.systemui.shared.system.QuickStepContract";
    private static final long STATE_ALLOW_GESTURE_IGNORING_BAR_VISIBILITY = 131072;
    private static final long STATE_BOUNCER_SHOWING = 8;
    private static final long STATE_NAV_BAR_HIDDEN = 2;
    private static final long STATE_NOTIFICATION_PANEL_EXPANDED = 4;
    private static final long STATE_OVERVIEW_DISABLED = 128;
    private static final long STATE_QUICK_SETTINGS_EXPANDED = 2048;
    private static final long STATE_SCREEN_PINNING = 1;
    private static final long STATE_SEARCH_DISABLED = 1024;
    private static final long STATE_STATUS_BAR_KEYGUARD_SHOWING = 64;
    private static volatile boolean loggedPageFlagsUnsupported;

    private LauncherHooks() {
    }

    public static void install(AssistRestoreModule assistRestoreModule, ClassLoader classLoader) {
        try {
            Method methodResolveGestureDisabled = resolveGestureDisabled(assistRestoreModule, classLoader);
            if (methodResolveGestureDisabled == null) {
                assistRestoreModule.logError("hook_skipped target=com.android.systemui.shared.system.QuickStepContract.isAssistantGestureDisabled reason=not_found");
                return;
            }
            assistRestoreModule.hook(methodResolveGestureDisabled).setId("assistant_gesture_disabled").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new a(3, assistRestoreModule));
            assistRestoreModule.logInfo("hook_installed target=" + methodResolveGestureDisabled.getDeclaringClass().getName() + "." + methodResolveGestureDisabled.getName());
        } catch (Throwable th) {
            assistRestoreModule.logError("hook_failed target=com.android.systemui.shared.system.QuickStepContract.isAssistantGestureDisabled", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$install$0(AssistRestoreModule assistRestoreModule, XposedInterface.Chain chain) {
        if (!AssistConfig.isEnabled(HookPrefs.get())) {
            return chain.proceed();
        }
        Object objProceed = chain.proceed();
        if (Boolean.TRUE.equals(objProceed)) {
            if (!AssistConfig.PAGE_FLAGS_SUPPORTED) {
                logPageFlagsUnsupported(assistRestoreModule);
                return objProceed;
            }
            if (AssistConfig.unblockPageFlags(HookPrefs.get())) {
                long jLongValue = ((Long) chain.getArg(0)).longValue();
                long j5 = (STATE_ALLOW_GESTURE_IGNORING_BAR_VISIBILITY & jLongValue) != 0 ? (-3) & jLongValue : jLongValue;
                if ((BLOCKING_STATES & j5) != 0 || ((4 & j5) != 0 && (STATE_STATUS_BAR_KEYGUARD_SHOWING & j5) == 0)) {
                    return objProceed;
                }
                long j6 = j5 & PAGE_LEVEL_STATES;
                if (j6 == 0) {
                    assistRestoreModule.logWarn("assist_gesture_keep_disabled flags=0x" + Long.toHexString(jLongValue));
                    return objProceed;
                }
                assistRestoreModule.logInfo("assist_gesture_unblocked pageFlags=0x" + Long.toHexString(j6) + " flags=0x" + Long.toHexString(jLongValue));
                return Boolean.FALSE;
            }
        }
        return objProceed;
    }

    private static void logPageFlagsUnsupported(AssistRestoreModule assistRestoreModule) {
        if (loggedPageFlagsUnsupported) {
            return;
        }
        loggedPageFlagsUnsupported = true;
        assistRestoreModule.logInfo("assist_gesture_unblock_skipped reason=unsupported_on_this_build");
    }

    private static Method resolveGestureDisabled(AssistRestoreModule assistRestoreModule, ClassLoader classLoader) {
        Class<?> clsLoad = Refl.load(classLoader, QUICK_STEP_CONTRACT);
        Class cls = Long.TYPE;
        if (clsLoad != null) {
            try {
                return clsLoad.getMethod("isAssistantGestureDisabled", cls);
            } catch (Throwable unused) {
            }
        }
        Class<?> clsLoad2 = Refl.load(classLoader, LAUNCHER_GESTURE_CONTRACT);
        if (clsLoad2 == null) {
            return null;
        }
        try {
            Method declaredMethod = clsLoad2.getDeclaredMethod(LAUNCHER_GESTURE_METHOD, cls);
            if (Modifier.isStatic(declaredMethod.getModifiers()) && declaredMethod.getReturnType() == Boolean.TYPE) {
                declaredMethod.setAccessible(true);
                return declaredMethod;
            }
            assistRestoreModule.logWarn("assist_gesture_contract_unexpected candidate=" + clsLoad2.getName() + "." + declaredMethod.getName());
        } catch (Throwable unused2) {
        }
        return null;
    }
}

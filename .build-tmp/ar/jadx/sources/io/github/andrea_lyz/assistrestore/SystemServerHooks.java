package io.github.andrea_lyz.assistrestore;

import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class SystemServerHooks {
    private static final int HAPTIC_EFFECT_LONG_PRESS = 0;
    private static final String HAPTIC_REASON = "Speech - Long Press";
    private static final int INVOCATION_TYPE_HOME_BUTTON_LONG_PRESS = 5;
    private static final int INVOCATION_TYPE_OTHER = 0;
    private static final int INVOCATION_TYPE_POWER_BUTTON_LONG_PRESS = 6;
    private static final int INVOCATION_TYPE_SHORTCUT = 7;
    private static final String PHONE_WINDOW_MANAGER_EXT = "com.android.server.policy.PhoneWindowManagerExtImpl";
    private static final int SOURCE_HOME_KEY_LONG_PRESS = 4;
    private static final int SOURCE_POWER_KEY_LONG_PRESS = 1024;
    private static final int SOURCE_SHORTCUT = 102;

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public static final class HapticCall {
        private final Method method;
        private final Object target;

        private HapticCall(Method method, Object obj) {
            this.method = method;
            this.target = obj;
        }

        public /* synthetic */ HapticCall(Method method, Object obj, int i5) {
            this(method, obj);
        }

        public void perform() throws IllegalAccessException, InvocationTargetException {
            this.method.invoke(this.target, 0, SystemServerHooks.HAPTIC_REASON);
        }
    }

    private SystemServerHooks() {
    }

    private static Method findPerformHapticFeedback(Class<?> cls) {
        try {
            Method method = cls.getMethod("performHapticFeedback", Integer.TYPE, String.class);
            method.setAccessible(true);
            return method;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void install(AssistRestoreModule assistRestoreModule, ClassLoader classLoader) {
        AssistRestoreModule assistRestoreModule2;
        try {
            Class<?> cls = Class.forName(PHONE_WINDOW_MANAGER_EXT, true, classLoader);
            Class cls2 = Integer.TYPE;
            Method declaredMethod = cls.getDeclaredMethod("startSpeech", cls2, cls2, Long.TYPE);
            declaredMethod.setAccessible(true);
            Field declaredField = cls.getDeclaredField("mBase");
            declaredField.setAccessible(true);
            Field declaredField2 = cls.getDeclaredField("mSpeechLongPressHandled");
            declaredField2.setAccessible(true);
            Method[] methodArr = new Method[1];
            Method[] methodArr2 = new Method[1];
            HapticCall[] hapticCallArr = new HapticCall[1];
            assistRestoreModule2 = assistRestoreModule;
            try {
                assistRestoreModule.hook(declaredMethod).setId("power_key_long_press").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new e(assistRestoreModule2, declaredField, methodArr, methodArr2, declaredField2, hapticCallArr));
                assistRestoreModule2.logInfo("hook_installed target=com.android.server.policy.PhoneWindowManagerExtImpl.startSpeech");
            } catch (Throwable th) {
                th = th;
                assistRestoreModule2.logError("hook_failed target=com.android.server.policy.PhoneWindowManagerExtImpl.startSpeech", th);
            }
        } catch (Throwable th2) {
            th = th2;
            assistRestoreModule2 = assistRestoreModule;
        }
    }

    private static int invocationTypeFor(int i5) {
        if (i5 == 4) {
            return INVOCATION_TYPE_HOME_BUTTON_LONG_PRESS;
        }
        if (i5 == 102) {
            return INVOCATION_TYPE_SHORTCUT;
        }
        if (i5 != SOURCE_POWER_KEY_LONG_PRESS) {
            return 0;
        }
        return INVOCATION_TYPE_POWER_BUTTON_LONG_PRESS;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$install$0(AssistRestoreModule assistRestoreModule, Field field, Method[] methodArr, Method[] methodArr2, Field field2, HapticCall[] hapticCallArr, XposedInterface.Chain chain) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (!AssistConfig.isEnabled(HookPrefs.get())) {
            assistRestoreModule.logInfo("power_key_skip reason=module_disabled");
            return chain.proceed();
        }
        if (AssistConfig.MODE_NONE.equals(AssistConfig.mode(HookPrefs.get(), AssistConfig.ENTRY_POWER))) {
            assistRestoreModule.logInfo("power_key_skip reason=disabled");
            return null;
        }
        Object thisObject = chain.getThisObject();
        Object obj = field.get(thisObject);
        if (obj == null) {
            assistRestoreModule.logWarn("power_key_long_press_fallback reason=no_phone_window_manager");
            return chain.proceed();
        }
        Integer num = (Integer) chain.getArg(0);
        int iIntValue = num.intValue();
        int iIntValue2 = ((Integer) chain.getArg(1)).intValue();
        Long l5 = (Long) chain.getArg(2);
        l5.longValue();
        if (methodArr[0] == null) {
            methodArr[0] = obj.getClass().getMethod("getWrapper", null);
        }
        Object objInvoke = methodArr[0].invoke(obj, null);
        if (objInvoke == null) {
            assistRestoreModule.logWarn("power_key_long_press_fallback reason=no_wrapper");
            return chain.proceed();
        }
        if (methodArr2[0] == null) {
            Class<?> cls = objInvoke.getClass();
            Class cls2 = Integer.TYPE;
            Method method = cls.getMethod("launchAssistAction", String.class, cls2, Long.TYPE, cls2, cls2);
            method.setAccessible(true);
            methodArr2[0] = method;
        }
        int iInvocationTypeFor = invocationTypeFor(iIntValue2);
        assistRestoreModule.logInfo("power_key_target mode=" + AssistConfig.mode(HookPrefs.get(), AssistConfig.ENTRY_POWER) + " package=" + AssistConfig.targetPackage(HookPrefs.get(), AssistConfig.ENTRY_POWER));
        field2.setBoolean(thisObject, true);
        assistRestoreModule.logInfo("power_key_long_press startSource=" + iIntValue2 + " deviceId=" + iIntValue + " invocationType=" + iInvocationTypeFor);
        if (hapticCallArr[0] == null) {
            hapticCallArr[0] = resolveHaptic(objInvoke, obj);
        }
        HapticCall hapticCall = hapticCallArr[0];
        if (hapticCall != null) {
            try {
                hapticCall.perform();
                assistRestoreModule.logInfo("power_key_haptic effect=0 reason=Speech - Long Press");
            } catch (Throwable th) {
                assistRestoreModule.logWarn("power_key_haptic_failed " + th);
            }
        }
        methodArr2[0].invoke(objInvoke, null, num, l5, Integer.valueOf(iInvocationTypeFor), 1);
        return null;
    }

    private static HapticCall resolveHaptic(Object obj, Object obj2) {
        Method methodFindPerformHapticFeedback = findPerformHapticFeedback(obj.getClass());
        int i5 = 0;
        if (methodFindPerformHapticFeedback != null) {
            return new HapticCall(methodFindPerformHapticFeedback, obj, i5);
        }
        Method methodFindPerformHapticFeedback2 = findPerformHapticFeedback(obj2.getClass());
        if (methodFindPerformHapticFeedback2 != null) {
            return new HapticCall(methodFindPerformHapticFeedback2, obj2, i5);
        }
        return null;
    }
}

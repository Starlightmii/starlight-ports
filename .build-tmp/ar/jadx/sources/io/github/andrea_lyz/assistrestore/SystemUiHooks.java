package io.github.andrea_lyz.assistrestore;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.Region;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
import b1.j;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
final class SystemUiHooks {
    private static final String ACTION_VOICE_INTERACTION_SERVICE = "android.service.voice.VoiceInteractionService";
    private static final String ASSIST_MANAGER = "com.android.systemui.assist.AssistManager";
    private static final String ASSIST_TOUCH_GESTURE_ENABLED = "assist_touch_gesture_enabled";
    private static final String ASSIST_UTILS = "com.android.internal.app.AssistUtils";
    private static final String CTS_CONFIG = "android.app.contextualsearch.ContextualSearchConfig";
    private static final String CTS_INTERFACE = "android.app.contextualsearch.IContextualSearchManager";
    private static final String CUSTOMIZE_FEATURE_OPTION = "com.oplusos.systemui.common.feature.CustomizeFeatureOption";
    private static final String DEPENDENCY = "com.android.systemui.Dependency";
    private static final String EXTRA_INVOCATION_TYPE = "invocation_type";
    private static final String FEATURE_OPTION = "com.oplusos.systemui.common.feature.FeatureOption";
    private static final long HANDLE_DISPATCH_DEBOUNCE_MS = 800;
    private static final float HIDDEN_BAR_WINDOW_ALPHA = 0.01f;
    private static final String INTERNAL_BOOL_RES = "com.android.internal.R$bool";
    private static final int INVOCATION_TYPE_HOME_BUTTON_LONG_PRESS = 5;
    private static final int INVOCATION_TYPE_POWER_BUTTON_LONG_PRESS = 6;
    private static final String KEY_HANDLE_WAKE_CUI = "oplus_gesture_handle_cui_enable";
    private static final String KEY_HANDLE_WAKE_OCR = "oplus_home_handle_wake_up_ocr_enable";
    private static final String NAVIGATION_BAR = "com.android.systemui.navigationbar.views.NavigationBar";
    private static final String NAV_BAR_UTILS = "com.oplus.systemui.navigationbar.utils.NavBarUtils";
    private static final String OCR_SCREEN_HANDLER = "com.oplus.systemui.navigationbar.ocrscreen.OplusOcrScreenServiceHandler";
    private static final String OPLUS_NAV_BAR_VIEW = "com.oplusos.systemui.navigationbar.OplusNavigationBarView";
    private static final String OP_UTILS = "com.oplusos.systemui.util.OpUtils";
    private static final String QUICK_STEP_CONTRACT = "com.android.systemui.shared.system.QuickStepContract";
    private static final String SETTING_ASSISTANT = "assistant";
    private static final String SETTING_VOICE_INTERACTION_SERVICE = "voice_interaction_service";
    private static final String SIDE_GESTURE_DETECTOR = "com.oplus.systemui.navigationbar.gesture.sidegesture.SideGestureDetector";
    private static final String SIDE_GESTURE_HANDLE = "com.oplus.systemui.navigationbar.gesture.sidegesture.OplusNavigationHandle";
    private static final String SPEED_CHASSIST = "com.oplus.systemui.navigationbar.gesture.otherbusiness.SpeedChassistMainBusiness";
    private static volatile Region appliedHandleRegion;
    private static volatile long lastHandleDispatchAtMs;
    private static volatile View lastHandleView;
    private static volatile String lastRegionSkipReason;
    private static volatile Field paramsForRotationField;
    private static volatile Method touchableRegionSetter;
    private static volatile Method viewRootImplGetter;
    private static volatile boolean windowAlphaForced;

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public static final class AssistPipeline {
        private final Field contextField;
        private final Method getAssistInfo;
        private final Method getVoiceInteractorComponentName;
        private final Method startAssistInternal;

        private AssistPipeline(Method method, Method method2, Method method3, Field field) {
            this.getAssistInfo = method;
            this.getVoiceInteractorComponentName = method2;
            this.startAssistInternal = method3;
            this.contextField = field;
        }

        public /* synthetic */ AssistPipeline(Method method, Method method2, Method method3, Field field, int i5) {
            this(method, method2, method3, field);
        }

        private Context contextOf(Object obj) {
            Field field = this.contextField;
            if (field == null) {
                return null;
            }
            try {
                Object obj2 = field.get(obj);
                if (obj2 instanceof Context) {
                    return (Context) obj2;
                }
            } catch (Throwable unused) {
            }
            return null;
        }

        private void invokeStartAssistInternal(AssistRestoreModule assistRestoreModule, Object obj, Bundle bundle, Object obj2, boolean z2) throws IllegalAccessException, InvocationTargetException {
            if (this.startAssistInternal.getParameterCount() != 4) {
                this.startAssistInternal.invoke(obj, bundle, obj2, Boolean.valueOf(z2));
                return;
            }
            Context contextContextOf = contextOf(obj);
            if (contextContextOf == null) {
                assistRestoreModule.logWarn("assist_dispatch_skipped reason=no_context");
            } else {
                this.startAssistInternal.invoke(obj, contextContextOf, bundle, obj2, Boolean.valueOf(z2));
            }
        }

        public Object dispatch(AssistRestoreModule assistRestoreModule, Object obj, Bundle bundle) throws IllegalAccessException, InvocationTargetException {
            Object objInvoke = this.getAssistInfo.invoke(obj, null);
            if (objInvoke == null) {
                assistRestoreModule.logWarn("assist_dispatch_skipped reason=no_assistant_configured");
                return null;
            }
            if (!(objInvoke instanceof ComponentName)) {
                assistRestoreModule.logWarn("assist_dispatch_skipped reason=unexpected_assistant_type type=".concat(objInvoke.getClass().getName()));
                return null;
            }
            ComponentName componentName = (ComponentName) objInvoke;
            Context contextContextOf = contextOf(obj);
            SystemUiHooks.ensureVoiceInteractionService(assistRestoreModule, contextContextOf);
            Object objInvoke2 = this.getVoiceInteractorComponentName.invoke(obj, null);
            boolean z2 = objInvoke.equals(objInvoke2) || SystemUiHooks.isDeclaredVoiceInteractionService(contextContextOf, componentName);
            assistRestoreModule.logInfo("assist_dispatch component=" + objInvoke + " isService=" + z2 + " voiceInteractor=" + objInvoke2 + " invocationType=" + bundle.getInt(SystemUiHooks.EXTRA_INVOCATION_TYPE, 0));
            invokeStartAssistInternal(assistRestoreModule, obj, bundle, componentName, z2);
            return componentName;
        }

        public Object dispatchTo(AssistRestoreModule assistRestoreModule, Object obj, Bundle bundle, ComponentName componentName, boolean z2) throws IllegalAccessException, InvocationTargetException {
            assistRestoreModule.logInfo("assist_dispatch component=" + componentName + " isService=" + z2 + " invocationType=" + bundle.getInt(SystemUiHooks.EXTRA_INVOCATION_TYPE, 0) + " pinned=true");
            invokeStartAssistInternal(assistRestoreModule, obj, bundle, componentName, z2);
            return componentName;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public static final class CtsPipeline {
        private final Method asInterface;
        private final Object defaultConfig;
        private final Method getService;
        private final Method startContextualSearch;

        private CtsPipeline(Method method, Method method2, Method method3, Object obj) {
            this.getService = method;
            this.asInterface = method2;
            this.startContextualSearch = method3;
            this.defaultConfig = obj;
        }

        public /* synthetic */ CtsPipeline(Method method, Method method2, Method method3, Object obj, int i5) {
            this(method, method2, method3, obj);
        }

        public boolean trigger() throws IllegalAccessException, InvocationTargetException {
            Object objInvoke;
            Object objInvoke2 = this.getService.invoke(null, "contextual_search");
            if (objInvoke2 == null || (objInvoke = this.asInterface.invoke(null, objInvoke2)) == null) {
                return false;
            }
            if (this.defaultConfig != null) {
                this.startContextualSearch.invoke(objInvoke, 2, this.defaultConfig);
                return true;
            }
            this.startContextualSearch.invoke(objInvoke, 2);
            return true;
        }
    }

    private SystemUiHooks() {
    }

    private static void applyHandleTouchRegion(AssistRestoreModule assistRestoreModule, View view) {
        String str;
        try {
            Region regionHandleRegionFor = handleRegionFor(assistRestoreModule, view);
            if (!keepHiddenWindowReachable(assistRestoreModule, view, regionHandleRegionFor != null)) {
                if (regionHandleRegionFor == null) {
                    if (appliedHandleRegion == null) {
                        return;
                    }
                } else if (regionHandleRegionFor.equals(appliedHandleRegion)) {
                    return;
                }
            }
            if (viewRootImplGetter == null) {
                viewRootImplGetter = View.class.getMethod("getViewRootImpl", null);
            }
            Object objInvoke = viewRootImplGetter.invoke(view, null);
            if (objInvoke == null) {
                return;
            }
            if (touchableRegionSetter == null) {
                touchableRegionSetter = objInvoke.getClass().getMethod("setTouchableRegion", Region.class);
            }
            touchableRegionSetter.invoke(objInvoke, regionHandleRegionFor != null ? regionHandleRegionFor : new Region());
            appliedHandleRegion = regionHandleRegionFor;
            if (regionHandleRegionFor == null) {
                str = "handle_touch_region cleared (bar area goes back to the page)";
            } else {
                str = "handle_touch_region applied=" + regionHandleRegionFor;
            }
            assistRestoreModule.logInfo(str);
        } catch (Throwable th) {
            assistRestoreModule.logWarn("handle_touch_region_failed " + th);
        }
    }

    private static int assistTouchGestureDefaultValue(Context context) {
        try {
            int i5 = Class.forName(INTERNAL_BOOL_RES).getField("config_assistTouchGestureEnabledDefault").getInt(null);
            if (i5 != 0) {
                return context.getResources().getBoolean(i5) ? 1 : 0;
            }
            return 1;
        } catch (Throwable unused) {
            return 1;
        }
    }

    private static int bottomGestureAreaHeight(View view) {
        try {
            int identifier = view.getResources().getIdentifier("bottom_gesture_area_height", "dimen", "com.android.systemui");
            if (identifier != 0) {
                return view.getResources().getDimensionPixelSize(identifier);
            }
            return 0;
        } catch (Throwable unused) {
            return 0;
        }
    }

    private static boolean dispatchGestureHandleLongPress(AssistRestoreModule assistRestoreModule, ClassLoader classLoader, AssistPipeline assistPipeline, Class<?> cls, CtsPipeline ctsPipeline, Context context) throws IllegalAccessException, InvocationTargetException {
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (jUptimeMillis - lastHandleDispatchAtMs < HANDLE_DISPATCH_DEBOUNCE_MS) {
            assistRestoreModule.logInfo("gesture_handle_long_press_skipped reason=debounce");
            return true;
        }
        if (isHandleOemMode()) {
            assistRestoreModule.logInfo("gesture_handle_long_press_skipped reason=oem_mode");
            return false;
        }
        if (AssistConfig.MODE_NONE.equals(AssistConfig.mode(HookPrefs.get(), AssistConfig.ENTRY_HANDLE))) {
            assistRestoreModule.logInfo("gesture_handle_long_press_skipped reason=disabled");
            return true;
        }
        if (context != null && isHandleWakeSwitchOff(assistRestoreModule, context)) {
            return true;
        }
        if (!AssistConfig.isEnabled(HookPrefs.get())) {
            assistRestoreModule.logInfo("gesture_handle_long_press_skipped reason=module_disabled");
            return false;
        }
        String strMode = AssistConfig.mode(HookPrefs.get(), AssistConfig.ENTRY_HANDLE);
        Object objLookupAssistManager = lookupAssistManager(assistRestoreModule, classLoader, cls);
        if (AssistConfig.MODE_CTS.equals(strMode)) {
            if (triggerCircleToSearch(assistRestoreModule, ctsPipeline)) {
                lastHandleDispatchAtMs = jUptimeMillis;
                return true;
            }
            assistRestoreModule.logWarn("circle_to_search_unavailable entry=handle");
        } else if (AssistConfig.MODE_APP.equals(strMode) || AssistConfig.MODE_CUSTOM.equals(strMode)) {
            if (startConfiguredTarget(assistRestoreModule, context, AssistConfig.ENTRY_HANDLE, assistPipeline, objLookupAssistManager)) {
                lastHandleDispatchAtMs = jUptimeMillis;
                return true;
            }
            assistRestoreModule.logWarn("gesture_handle_fallback reason=target_unavailable entry=handle");
        }
        if (!AssistConfig.MODE_DEFAULT.equals(strMode)) {
            assistRestoreModule.logInfo("gesture_handle_assistant mode=" + strMode);
        } else if (ctsPipeline != null && isGoogleAssistantConfigured(assistRestoreModule, classLoader, context)) {
            try {
                if (ctsPipeline.trigger()) {
                    assistRestoreModule.logInfo("circle_to_search_triggered entrypoint=2");
                    lastHandleDispatchAtMs = jUptimeMillis;
                    return true;
                }
                assistRestoreModule.logWarn("circle_to_search_unavailable reason=no_service");
            } catch (Throwable th) {
                assistRestoreModule.logWarn("circle_to_search_failed " + th);
            }
        }
        if (objLookupAssistManager == null) {
            assistRestoreModule.logWarn("gesture_handle_long_press_fallback reason=no_assist_manager");
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_INVOCATION_TYPE, INVOCATION_TYPE_HOME_BUTTON_LONG_PRESS);
        assistRestoreModule.logInfo("gesture_handle_long_press invocationType=5");
        assistPipeline.dispatch(assistRestoreModule, objLookupAssistManager, bundle);
        lastHandleDispatchAtMs = jUptimeMillis;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void ensureVoiceInteractionService(AssistRestoreModule assistRestoreModule, Context context) {
        ComponentName componentNameResolveVoiceInteractionComponent;
        boolean zPutString;
        if (context == null || !AssistConfig.isEnabled(HookPrefs.get())) {
            return;
        }
        try {
            ContentResolver contentResolver = context.getContentResolver();
            String string = Settings.Secure.getString(contentResolver, SETTING_VOICE_INTERACTION_SERVICE);
            if (string == null || string.isEmpty()) {
                String string2 = Settings.Secure.getString(contentResolver, SETTING_ASSISTANT);
                ComponentName componentNameUnflattenFromString = string2 == null ? null : ComponentName.unflattenFromString(string2);
                if (componentNameUnflattenFromString != null && (componentNameResolveVoiceInteractionComponent = resolveVoiceInteractionComponent(context, componentNameUnflattenFromString.getPackageName())) != null) {
                    String strFlattenToString = componentNameResolveVoiceInteractionComponent.flattenToString();
                    try {
                        zPutString = !Boolean.FALSE.equals(Settings.Secure.class.getMethod("putStringForUser", ContentResolver.class, String.class, String.class, Integer.TYPE).invoke(null, contentResolver, SETTING_VOICE_INTERACTION_SERVICE, strFlattenToString, -2));
                    } catch (Throwable unused) {
                        zPutString = Settings.Secure.putString(contentResolver, SETTING_VOICE_INTERACTION_SERVICE, strFlattenToString);
                    }
                    if (zPutString) {
                        assistRestoreModule.logInfo("voice_interaction_service_repaired component=" + strFlattenToString);
                    } else {
                        assistRestoreModule.logWarn("voice_interaction_service_repair_failed component=" + strFlattenToString);
                    }
                }
            }
        } catch (Throwable th) {
            assistRestoreModule.logWarn("voice_interaction_service_repair_failed " + th);
        }
    }

    private static String entryForInvocationType(Bundle bundle) {
        int i5 = bundle != null ? bundle.getInt(EXTRA_INVOCATION_TYPE, 0) : 0;
        if (i5 == INVOCATION_TYPE_POWER_BUTTON_LONG_PRESS) {
            return AssistConfig.ENTRY_POWER;
        }
        return i5 == INVOCATION_TYPE_HOME_BUTTON_LONG_PRESS ? AssistConfig.ENTRY_HANDLE : AssistConfig.ENTRY_CORNER;
    }

    private static Boolean evaluateAssistantAvailable(AssistRestoreModule assistRestoreModule, ClassLoader classLoader, Context context, int i5, int i6, Method method, Constructor<?> constructor, Method method2) {
        try {
            Boolean boolStaticBooleanField = Refl.staticBooleanField(classLoader, CUSTOMIZE_FEATURE_OPTION, "sIsSupportCircleToSearch");
            Boolean bool = Boolean.TRUE;
            if (bool.equals(boolStaticBooleanField)) {
                return Boolean.FALSE;
            }
            if (method != null && !bool.equals(method.invoke(null, Integer.valueOf(i5)))) {
                return Boolean.FALSE;
            }
            Object objInvoke = method2.invoke(constructor.newInstance(context), Integer.valueOf(i6));
            if (objInvoke == null) {
                assistRestoreModule.logInfo("assistant_availability available=false reason=no_assistant_configured");
                return Boolean.FALSE;
            }
            boolean z2 = getSecureSettingForUser(context, ASSIST_TOUCH_GESTURE_ENABLED, assistTouchGestureDefaultValue(context), i6) != 0;
            assistRestoreModule.logInfo("assistant_availability available=" + z2 + " component=" + objInvoke + " navBarMode=" + i5 + " userId=" + i6);
            return Boolean.valueOf(z2);
        } catch (Throwable th) {
            assistRestoreModule.logError("assistant_availability_failed", th);
            return null;
        }
    }

    private static Field findContextField(Class<?> cls) {
        for (Class<?> superclass = cls; superclass != null && superclass != Object.class; superclass = superclass.getSuperclass()) {
            try {
                Field declaredField = superclass.getDeclaredField("mContext");
                if (declaredField.getType() == Context.class) {
                    declaredField.setAccessible(true);
                    return declaredField;
                }
                continue;
            } catch (Throwable unused) {
            }
        }
        while (cls != null && cls != Object.class) {
            for (Field field : cls.getDeclaredFields()) {
                if (field.getType() == Context.class) {
                    field.setAccessible(true);
                    return field;
                }
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    private static View findHandleView(View view) {
        if (view == null) {
            return null;
        }
        if (view.getClass().getName().startsWith(SIDE_GESTURE_HANDLE)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i5 = 0; i5 < viewGroup.getChildCount(); i5++) {
            View viewFindHandleView = findHandleView(viewGroup.getChildAt(i5));
            if (viewFindHandleView != null) {
                return viewFindHandleView;
            }
        }
        return null;
    }

    private static int gestureBarWidth(View view) {
        return oplusDimension(view, "navigation_gesture_view_width");
    }

    private static int gestureBarWidthForCurrentRotation(View view) {
        try {
            int rotation = view.getDisplay() == null ? 0 : view.getDisplay().getRotation();
            int iOplusDimension = oplusDimension(view, (rotation == 1 || rotation == 3) ? "navigation_gesture_view_landscape_width" : "navigation_gesture_view_width");
            return iOplusDimension > 0 ? iOplusDimension : gestureBarWidth(view);
        } catch (Throwable unused) {
            return gestureBarWidth(view);
        }
    }

    private static int getSecureSettingForUser(Context context, String str, int i5, int i6) {
        ContentResolver contentResolver = context.getContentResolver();
        try {
            Class cls = Integer.TYPE;
            Object objInvoke = Settings.Secure.class.getMethod("getIntForUser", ContentResolver.class, String.class, cls, cls).invoke(null, contentResolver, str, Integer.valueOf(i5), Integer.valueOf(i6));
            if (objInvoke instanceof Integer) {
                return ((Integer) objInvoke).intValue();
            }
        } catch (Throwable unused) {
        }
        return Settings.Secure.getInt(contentResolver, str, i5);
    }

    private static boolean handleOwnedByModule() {
        if (!AssistConfig.isEnabled(HookPrefs.get())) {
            return false;
        }
        String strMode = AssistConfig.mode(HookPrefs.get(), AssistConfig.ENTRY_HANDLE);
        return (AssistConfig.MODE_NONE.equals(strMode) || AssistConfig.MODE_OEM.equals(strMode)) ? false : true;
    }

    private static Region handleRegionFor(AssistRestoreModule assistRestoreModule, View view) {
        int i5;
        int width;
        if (!handleOwnedByModule()) {
            return skipRegion(assistRestoreModule, "not_owned");
        }
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        if (rootWindowInsets != null && rootWindowInsets.isVisible(WindowInsets.Type.ime())) {
            return skipRegion(assistRestoreModule, "ime_visible");
        }
        View viewWindowParamOwner = windowParamOwner(view);
        if (viewWindowParamOwner == null) {
            return skipRegion(assistRestoreModule, "no_window_view");
        }
        int width2 = viewWindowParamOwner.getWidth();
        int height = viewWindowParamOwner.getHeight();
        if (width2 <= 0 || height <= 0) {
            return skipRegion(assistRestoreModule, "window_not_laid_out");
        }
        lastRegionSkipReason = null;
        int iBottomGestureAreaHeight = bottomGestureAreaHeight(view);
        if (iBottomGestureAreaHeight <= 0) {
            iBottomGestureAreaHeight = height;
        }
        if (view.getWidth() <= 0 || view.getHeight() <= 0) {
            int iGestureBarWidth = gestureBarWidth(view);
            if (iGestureBarWidth <= 0) {
                return skipRegion(assistRestoreModule, "no_bar_geometry");
            }
            i5 = (width2 - iGestureBarWidth) / 2;
            width = iGestureBarWidth + i5;
        } else {
            int[] iArr = new int[2];
            view.getLocationInWindow(iArr);
            i5 = iArr[0];
            width = view.getWidth() + i5;
        }
        return new Region(i5, Math.max(0, height - iBottomGestureAreaHeight), width, height);
    }

    public static void install(AssistRestoreModule assistRestoreModule, ClassLoader classLoader) {
        ensureVoiceInteractionService(assistRestoreModule, TargetIntents.appContext());
        AssistPipeline assistPipelineResolveAssistPipeline = resolveAssistPipeline(assistRestoreModule, classLoader);
        CtsPipeline ctsPipelineResolveCtsPipeline = resolveCtsPipeline(assistRestoreModule, classLoader);
        installAssistDispatch(assistRestoreModule, classLoader, assistPipelineResolveAssistPipeline, ctsPipelineResolveCtsPipeline);
        installAssistantAvailability(assistRestoreModule, classLoader);
        installGestureHandleLongPress(assistRestoreModule, classLoader, assistPipelineResolveAssistPipeline, ctsPipelineResolveCtsPipeline);
        installOcrScreenHandleLongPress(assistRestoreModule, classLoader, assistPipelineResolveAssistPipeline, ctsPipelineResolveCtsPipeline);
        installHandlePressAvailability(assistRestoreModule, classLoader);
        installHiddenGestureBarHandleTouch(assistRestoreModule, classLoader);
        installHandleTouchRegion(assistRestoreModule, classLoader);
    }

    private static void installAssistDispatch(final AssistRestoreModule assistRestoreModule, ClassLoader classLoader, final AssistPipeline assistPipeline, final CtsPipeline ctsPipeline) {
        if (assistPipeline == null) {
            assistRestoreModule.logError("hook_skipped target=com.android.systemui.assist.AssistManager.startAssist reason=assist_pipeline_unavailable");
            return;
        }
        try {
            Class<?> cls = Class.forName(ASSIST_MANAGER, true, classLoader);
            Method method = Refl.method(cls, "startAssist$1", Bundle.class);
            if (method != null) {
                method.setAccessible(true);
            } else {
                method = cls.getMethod("startAssist", Bundle.class);
            }
            Method method2 = method;
            final Field field = cls.getField("mAssistOverrideInvocationTypes");
            final Field field2 = cls.getField("mActivityManager");
            final Method methodStaticMethod = Refl.staticMethod(classLoader, FEATURE_OPTION, "isExpRegion", new Class[0]);
            final boolean zEquals = Boolean.TRUE.equals(Refl.staticBooleanField(classLoader, OP_UTILS, "sIsClosedSuperFirewall"));
            if (methodStaticMethod == null) {
                assistRestoreModule.logWarn("region_gate_unresolved target=com.oplusos.systemui.common.feature.FeatureOption.isExpRegion -> treating the region gate as closed");
            }
            assistRestoreModule.hook(method2).setId("assist_manager_start_assist").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new XposedInterface.Hooker() { // from class: io.github.andrea_lyz.assistrestore.f
                public final Object intercept(XposedInterface.Chain chain) {
                    return SystemUiHooks.lambda$installAssistDispatch$0(assistRestoreModule, methodStaticMethod, zEquals, field2, field, ctsPipeline, assistPipeline, chain);
                }
            });
            assistRestoreModule.logInfo("hook_installed target=com.android.systemui.assist.AssistManager." + method2.getName());
        } catch (Throwable th) {
            assistRestoreModule.logError("hook_failed target=com.android.systemui.assist.AssistManager.startAssist", th);
        }
    }

    private static void installAssistantAvailability(AssistRestoreModule assistRestoreModule, ClassLoader classLoader) {
        AssistRestoreModule assistRestoreModule2;
        try {
            Class<?> cls = Class.forName(NAV_BAR_UTILS, true, classLoader);
            Class cls2 = Integer.TYPE;
            Method method = cls.getMethod("isAssistantAvailable", Context.class, cls2, cls2);
            Method methodStaticMethod = Refl.staticMethod(classLoader, QUICK_STEP_CONTRACT, "isGesturalMode", cls2);
            Class<?> cls3 = Class.forName(ASSIST_UTILS, false, classLoader);
            Constructor<?> constructor = cls3.getConstructor(Context.class);
            Method method2 = cls3.getMethod("getAssistComponentForUser", cls2);
            assistRestoreModule2 = assistRestoreModule;
            try {
                assistRestoreModule.hook(method).setId("assistant_availability").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new d(assistRestoreModule2, classLoader, methodStaticMethod, constructor, method2));
                assistRestoreModule2.logInfo("hook_installed target=com.oplus.systemui.navigationbar.utils.NavBarUtils.isAssistantAvailable");
            } catch (Throwable th) {
                th = th;
                assistRestoreModule2.logError("hook_failed target=com.oplus.systemui.navigationbar.utils.NavBarUtils.isAssistantAvailable", th);
            }
        } catch (Throwable th2) {
            th = th2;
            assistRestoreModule2 = assistRestoreModule;
        }
    }

    private static void installGestureHandleLongPress(AssistRestoreModule assistRestoreModule, ClassLoader classLoader, AssistPipeline assistPipeline, CtsPipeline ctsPipeline) {
        AssistRestoreModule assistRestoreModule2;
        if (assistPipeline == null) {
            assistRestoreModule.logError("hook_skipped target=com.oplus.systemui.navigationbar.gesture.otherbusiness.SpeedChassistMainBusiness.onLongPressed reason=assist_pipeline_unavailable");
            return;
        }
        try {
            Class<?> cls = Class.forName(SPEED_CHASSIST, true, classLoader);
            Method method = cls.getMethod("onLongPressed", null);
            Class<?> cls2 = Class.forName(ASSIST_MANAGER, true, classLoader);
            Field declaredField = cls.getDeclaredField("mContext");
            declaredField.setAccessible(true);
            assistRestoreModule2 = assistRestoreModule;
            try {
                assistRestoreModule.hook(method).setId("gesture_handle_long_press").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new e(declaredField, assistRestoreModule2, classLoader, assistPipeline, cls2, ctsPipeline, 1));
                assistRestoreModule2.logInfo("hook_installed target=com.oplus.systemui.navigationbar.gesture.otherbusiness.SpeedChassistMainBusiness.onLongPressed");
            } catch (Throwable th) {
                th = th;
                assistRestoreModule2.logError("hook_failed target=com.oplus.systemui.navigationbar.gesture.otherbusiness.SpeedChassistMainBusiness.onLongPressed", th);
            }
        } catch (Throwable th2) {
            th = th2;
            assistRestoreModule2 = assistRestoreModule;
        }
    }

    private static void installHandlePressAvailability(AssistRestoreModule assistRestoreModule, ClassLoader classLoader) {
        try {
            Class<?> cls = Class.forName(SIDE_GESTURE_HANDLE, true, classLoader);
            XposedInterface.HookBuilder id = assistRestoreModule.hook(cls.getMethod("handleValidTouchEvent", MotionEvent.class)).setId("handle_press_range_bootstrap");
            XposedInterface.ExceptionMode exceptionMode = XposedInterface.ExceptionMode.PROTECTIVE;
            id.setExceptionMode(exceptionMode).intercept(new a(10, assistRestoreModule));
            assistRestoreModule.logInfo("hook_installed target=com.oplus.systemui.navigationbar.gesture.sidegesture.OplusNavigationHandle.handleValidTouchEvent");
            assistRestoreModule.hook(cls.getMethod("shouldHandlePress", null)).setId("handle_press_availability").setExceptionMode(exceptionMode).intercept(new a(11, assistRestoreModule));
            assistRestoreModule.logInfo("hook_installed target=com.oplus.systemui.navigationbar.gesture.sidegesture.OplusNavigationHandle.shouldHandlePress");
        } catch (Throwable th) {
            assistRestoreModule.logWarn("hook_skipped target=com.oplus.systemui.navigationbar.gesture.sidegesture.OplusNavigationHandle.shouldHandlePress " + th);
        }
    }

    private static void installHandleTouchRegion(AssistRestoreModule assistRestoreModule, ClassLoader classLoader) {
        try {
            Class<?> cls = Class.forName(SIDE_GESTURE_HANDLE, true, classLoader);
            Class cls2 = Boolean.TYPE;
            Class cls3 = Integer.TYPE;
            assistRestoreModule.hook(cls.getMethod("onLayout", cls2, cls3, cls3, cls3, cls3)).setId("handle_touch_region").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new a(4, assistRestoreModule));
            assistRestoreModule.logInfo("hook_installed target=com.oplus.systemui.navigationbar.gesture.sidegesture.OplusNavigationHandle.onLayout");
        } catch (Throwable th) {
            assistRestoreModule.logError("hook_failed target=com.oplus.systemui.navigationbar.gesture.sidegesture.OplusNavigationHandle.onLayout", th);
        }
        installHiddenBarWindowHooks(assistRestoreModule, classLoader);
    }

    private static void installHiddenBarWindowHooks(AssistRestoreModule assistRestoreModule, ClassLoader classLoader) {
        Class cls = Integer.TYPE;
        try {
            assistRestoreModule.hook(Class.forName(NAVIGATION_BAR, true, classLoader).getMethod("getBarLayoutParamsForRotation", cls, j.k())).setId("bar_window_alpha_created").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new a(8, assistRestoreModule));
            assistRestoreModule.logInfo("hook_installed target=com.android.systemui.navigationbar.views.NavigationBar.getBarLayoutParamsForRotation");
        } catch (Throwable th) {
            assistRestoreModule.logWarn("hook_skipped target=com.android.systemui.navigationbar.views.NavigationBar.getBarLayoutParamsForRotation " + th);
        }
        try {
            assistRestoreModule.hook(Class.forName(OPLUS_NAV_BAR_VIEW, true, classLoader).getMethod("updateWindowAlpha", cls)).setId("bar_window_alpha_toggled").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new a(9, assistRestoreModule));
            assistRestoreModule.logInfo("hook_installed target=com.oplusos.systemui.navigationbar.OplusNavigationBarView.updateWindowAlpha");
        } catch (Throwable th2) {
            assistRestoreModule.logWarn("hook_skipped target=com.oplusos.systemui.navigationbar.OplusNavigationBarView.updateWindowAlpha " + th2);
        }
    }

    private static void installHiddenGestureBarHandleTouch(AssistRestoreModule assistRestoreModule, ClassLoader classLoader) {
        try {
            assistRestoreModule.hook(Class.forName(NAV_BAR_UTILS, true, classLoader).getDeclaredMethod("isSideGestureBarHide", null)).setId("hidden_gesture_bar_handle_touch").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new a(INVOCATION_TYPE_HOME_BUTTON_LONG_PRESS, assistRestoreModule));
            assistRestoreModule.logInfo("hook_installed target=com.oplus.systemui.navigationbar.utils.NavBarUtils.isSideGestureBarHide");
        } catch (Throwable th) {
            assistRestoreModule.logError("hook_failed target=com.oplus.systemui.navigationbar.utils.NavBarUtils.isSideGestureBarHide", th);
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x007e A[Catch: all -> 0x0071, TRY_ENTER, TryCatch #0 {all -> 0x0071, blocks: (B:6:0x000a, B:7:0x006c, B:14:0x007e, B:15:0x009d), top: B:20:0x000a }] */
    /* JADX WARN: Code duplicated, block: B:15:0x009d A[Catch: all -> 0x0071, TRY_LEAVE, TryCatch #0 {all -> 0x0071, blocks: (B:6:0x000a, B:7:0x006c, B:14:0x007e, B:15:0x009d), top: B:20:0x000a }] */
    private static void installOcrScreenHandleLongPress(AssistRestoreModule assistRestoreModule, ClassLoader classLoader, AssistPipeline assistPipeline, CtsPipeline ctsPipeline) {
        Method method;
        if (assistPipeline == null) {
            assistRestoreModule.logError("hook_skipped target=com.oplus.systemui.navigationbar.ocrscreen.OplusOcrScreenServiceHandler.onLongPressed reason=assist_pipeline_unavailable");
            return;
        }
        try {
            Class<?> cls = Class.forName(OCR_SCREEN_HANDLER, true, classLoader);
            Class<?> cls2 = Class.forName(ASSIST_MANAGER, true, classLoader);
            Field declaredField = cls.getDeclaredField("context");
            declaredField.setAccessible(true);
            Method declaredMethod = null;
            XposedInterface.HookBuilder id = assistRestoreModule.hook(cls.getMethod("onPreLongPress", null)).setId("ocr_screen_handle_preload");
            XposedInterface.ExceptionMode exceptionMode = XposedInterface.ExceptionMode.PROTECTIVE;
            id.setExceptionMode(exceptionMode).intercept(new a(INVOCATION_TYPE_POWER_BUTTON_LONG_PRESS, assistRestoreModule));
            assistRestoreModule.logInfo("hook_installed target=com.oplus.systemui.navigationbar.ocrscreen.OplusOcrScreenServiceHandler.onPreLongPress");
            assistRestoreModule.hook(cls.getMethod("onLongPressed", null)).setId("ocr_screen_handle_long_press").setExceptionMode(exceptionMode).intercept(new e(declaredField, assistRestoreModule, classLoader, assistPipeline, cls2, ctsPipeline, 0));
            assistRestoreModule.logInfo("hook_installed target=com.oplus.systemui.navigationbar.ocrscreen.OplusOcrScreenServiceHandler.onLongPressed");
            try {
                try {
                    method = cls.getMethod("handleLongPressAction", null);
                } catch (Throwable unused) {
                    method = declaredMethod;
                }
            } catch (NoSuchMethodException unused2) {
                declaredMethod = cls.getDeclaredMethod("handleLongPressAction", null);
                declaredMethod.setAccessible(true);
                method = declaredMethod;
                if (method != null) {
                    assistRestoreModule.logWarn("hook_skipped target=com.oplus.systemui.navigationbar.ocrscreen.OplusOcrScreenServiceHandler.handleLongPressAction reason=method_missing");
                } else {
                    assistRestoreModule.hook(method).setId("ocr_screen_handle_action").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new a(7, assistRestoreModule));
                    assistRestoreModule.logInfo("hook_installed target=com.oplus.systemui.navigationbar.ocrscreen.OplusOcrScreenServiceHandler.handleLongPressAction");
                }
            }
            if (method != null) {
                assistRestoreModule.logWarn("hook_skipped target=com.oplus.systemui.navigationbar.ocrscreen.OplusOcrScreenServiceHandler.handleLongPressAction reason=method_missing");
            } else {
                assistRestoreModule.hook(method).setId("ocr_screen_handle_action").setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new a(7, assistRestoreModule));
                assistRestoreModule.logInfo("hook_installed target=com.oplus.systemui.navigationbar.ocrscreen.OplusOcrScreenServiceHandler.handleLongPressAction");
            }
        } catch (Throwable th) {
            assistRestoreModule.logError("hook_failed target=com.oplus.systemui.navigationbar.ocrscreen.OplusOcrScreenServiceHandler.onLongPressed", th);
        }
    }

    private static int invocationTypeForEntry(String str) {
        if (AssistConfig.ENTRY_POWER.equals(str)) {
            return INVOCATION_TYPE_POWER_BUTTON_LONG_PRESS;
        }
        if (AssistConfig.ENTRY_HANDLE.equals(str)) {
            return INVOCATION_TYPE_HOME_BUTTON_LONG_PRESS;
        }
        return 1;
    }

    private static boolean isCalledFromSideGestureDetector() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        for (int i5 = 1; i5 < stackTrace.length && i5 <= 8; i5++) {
            if (stackTrace[i5].getClassName().startsWith(SIDE_GESTURE_DETECTOR)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isDeclaredVoiceInteractionService(Context context, ComponentName componentName) {
        if (context != null && componentName != null) {
            try {
                List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(new Intent(ACTION_VOICE_INTERACTION_SERVICE).setPackage(componentName.getPackageName()), 0);
                if (listQueryIntentServices == null) {
                    return false;
                }
                Iterator<ResolveInfo> it = listQueryIntentServices.iterator();
                while (it.hasNext()) {
                    ServiceInfo serviceInfo = it.next().serviceInfo;
                    if (serviceInfo != null && componentName.getPackageName().equals(serviceInfo.packageName) && componentName.getClassName().equals(serviceInfo.name)) {
                        return true;
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    private static boolean isExpRegionEnabled(Method method) {
        if (method == null) {
            return false;
        }
        try {
            Object objInvoke = method.invoke(null, null);
            return (objInvoke instanceof Boolean) && ((Boolean) objInvoke).booleanValue();
        } catch (Throwable unused) {
        }
    }

    private static boolean isGoogleAssistantConfigured(AssistRestoreModule assistRestoreModule, ClassLoader classLoader, Context context) {
        try {
            Class<?> cls = Class.forName(ASSIST_UTILS, false, classLoader);
            Object objInvoke = cls.getMethod("getAssistComponentForUser", Integer.TYPE).invoke(cls.getConstructor(Context.class).newInstance(context), -2);
            if (objInvoke != null) {
                boolean zContains = objInvoke.toString().contains("com.google.android.googlequicksearchbox");
                assistRestoreModule.logInfo("gesture_handle_assistant component=" + objInvoke + " circleToSearch=" + zContains);
                return zContains;
            }
        } catch (Throwable th) {
            assistRestoreModule.logWarn("gesture_handle_assistant_lookup_failed " + th);
        }
        if (context != null) {
            try {
                String string = Settings.Secure.getString(context.getContentResolver(), SETTING_ASSISTANT);
                if (string != null) {
                    boolean zContains2 = string.contains("com.google.android.googlequicksearchbox");
                    assistRestoreModule.logInfo("gesture_handle_assistant setting=" + string + " circleToSearch=" + zContains2);
                    return zContains2;
                }
            } catch (Throwable th2) {
                assistRestoreModule.logWarn("gesture_handle_assistant_setting_failed " + th2);
            }
        }
        assistRestoreModule.logWarn("gesture_handle_assistant_unknown assuming google");
        return true;
    }

    private static boolean isHandleInGestureRange(Object obj) {
        Field field = obj == null ? null : Refl.field(obj.getClass(), "inGestureXRange");
        if (field == null) {
            return false;
        }
        try {
            return field.getBoolean(obj);
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean isHandleOemMode() {
        return AssistConfig.MODE_OEM.equals(AssistConfig.mode(HookPrefs.get(), AssistConfig.ENTRY_HANDLE));
    }

    private static boolean isHandleWakeSwitchOff(AssistRestoreModule assistRestoreModule, Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        int i5 = Settings.Secure.getInt(contentResolver, KEY_HANDLE_WAKE_OCR, -1);
        int i6 = Settings.Secure.getInt(contentResolver, KEY_HANDLE_WAKE_CUI, -1);
        boolean z2 = false;
        boolean z3 = i5 == 1 || i6 == 1;
        boolean z4 = i5 == 0 || i6 == 0;
        if (!z3 && z4) {
            z2 = true;
        }
        if (z2) {
            assistRestoreModule.logInfo("gesture_handle_long_press_skipped reason=nav_bar_switch_off ocr=" + i5 + " cui=" + i6);
        }
        return z2;
    }

    private static boolean isHandledByLauncherOverride(Object obj, Bundle bundle) {
        if ((obj instanceof int[]) && bundle != null && bundle.containsKey(EXTRA_INVOCATION_TYPE)) {
            int i5 = bundle.getInt(EXTRA_INVOCATION_TYPE, 0);
            for (int i6 : (int[]) obj) {
                if (i6 == i5) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isKeyguardShowing(Object obj) {
        Field field = obj == null ? null : Refl.field(obj.getClass(), "isKeyguardShowing");
        if (field == null) {
            return false;
        }
        try {
            return field.getBoolean(obj);
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean isLockTaskMode(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            Object objInvoke = obj.getClass().getMethod("getLockTaskModeState", null).invoke(obj, null);
            return (objInvoke instanceof Integer) && ((Integer) objInvoke).intValue() == 1;
        } catch (Throwable unused) {
        }
    }

    private static boolean keepHiddenWindowReachable(AssistRestoreModule assistRestoreModule, final View view, final boolean z2) {
        final AssistRestoreModule assistRestoreModule2;
        Object obj;
        try {
            final View viewWindowParamOwner = windowParamOwner(view);
            if (viewWindowParamOwner != null && (viewWindowParamOwner.getLayoutParams() instanceof WindowManager.LayoutParams)) {
                final WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) viewWindowParamOwner.getLayoutParams();
                if (z2) {
                    try {
                        if (layoutParams.alpha != 0.0f) {
                            windowAlphaForced = false;
                            return false;
                        }
                        layoutParams.alpha = HIDDEN_BAR_WINDOW_ALPHA;
                        windowAlphaForced = true;
                    } catch (Throwable th) {
                        obj = th;
                        assistRestoreModule2 = assistRestoreModule;
                        assistRestoreModule2.logWarn("handle_window_alpha_probe_failed " + obj);
                        return false;
                    }
                } else if (windowAlphaForced) {
                    layoutParams.alpha = 0.0f;
                    windowAlphaForced = false;
                }
                assistRestoreModule2 = assistRestoreModule;
                try {
                    view.post(new Runnable() { // from class: io.github.andrea_lyz.assistrestore.g
                        @Override // java.lang.Runnable
                        public final void run() {
                            SystemUiHooks.lambda$keepHiddenWindowReachable$13(view, assistRestoreModule2, viewWindowParamOwner, layoutParams, z2);
                        }
                    });
                    return true;
                } catch (Throwable th2) {
                    th = th2;
                    obj = th;
                    assistRestoreModule2.logWarn("handle_window_alpha_probe_failed " + obj);
                    return false;
                }
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            assistRestoreModule2 = assistRestoreModule;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:35:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:40:0x00c2  */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00bc, code lost:
    
        if (startConfiguredTarget(r4, io.github.andrea_lyz.assistrestore.TargetIntents.appContext(), r5, r10, r0) == false) goto L38;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object lambda$installAssistDispatch$0(io.github.andrea_lyz.assistrestore.AssistRestoreModule r4, java.lang.reflect.Method r5, boolean r6, java.lang.reflect.Field r7, java.lang.reflect.Field r8, io.github.andrea_lyz.assistrestore.SystemUiHooks.CtsPipeline r9, io.github.andrea_lyz.assistrestore.SystemUiHooks.AssistPipeline r10, io.github.libxposed.api.XposedInterface.Chain r11) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instruction units count: 209
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.andrea_lyz.assistrestore.SystemUiHooks.lambda$installAssistDispatch$0(io.github.andrea_lyz.assistrestore.AssistRestoreModule, java.lang.reflect.Method, boolean, java.lang.reflect.Field, java.lang.reflect.Field, io.github.andrea_lyz.assistrestore.SystemUiHooks$CtsPipeline, io.github.andrea_lyz.assistrestore.SystemUiHooks$AssistPipeline, io.github.libxposed.api.XposedInterface$Chain):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$installAssistantAvailability$1(AssistRestoreModule assistRestoreModule, ClassLoader classLoader, Method method, Constructor constructor, Method method2, XposedInterface.Chain chain) {
        if (!AssistConfig.isEnabled(HookPrefs.get())) {
            return chain.proceed();
        }
        if (AssistConfig.MODE_NONE.equals(AssistConfig.mode(HookPrefs.get(), AssistConfig.ENTRY_CORNER))) {
            assistRestoreModule.logInfo("assistant_availability available=false reason=disabled");
            return Boolean.FALSE;
        }
        Boolean boolEvaluateAssistantAvailable = evaluateAssistantAvailable(assistRestoreModule, classLoader, (Context) chain.getArg(0), ((Integer) chain.getArg(1)).intValue(), ((Integer) chain.getArg(2)).intValue(), method, constructor, method2);
        return boolEvaluateAssistantAvailable == null ? chain.proceed() : boolEvaluateAssistantAvailable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$installGestureHandleLongPress$2(Field field, AssistRestoreModule assistRestoreModule, ClassLoader classLoader, AssistPipeline assistPipeline, Class cls, CtsPipeline ctsPipeline, XposedInterface.Chain chain) {
        if (dispatchGestureHandleLongPress(assistRestoreModule, classLoader, assistPipeline, cls, ctsPipeline, (Context) field.get(chain.getThisObject()))) {
            return null;
        }
        return chain.proceed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$installHandlePressAvailability$6(AssistRestoreModule assistRestoreModule, XposedInterface.Chain chain) {
        Object thisObject = chain.getThisObject();
        MotionEvent motionEvent = (MotionEvent) chain.getArg(0);
        repairHiddenHandleGestureRange(assistRestoreModule, thisObject, motionEvent, true);
        Object objProceed = chain.proceed();
        repairHiddenHandleGestureRange(assistRestoreModule, thisObject, motionEvent, false);
        return objProceed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$installHandlePressAvailability$7(AssistRestoreModule assistRestoreModule, XposedInterface.Chain chain) {
        Object objProceed = chain.proceed();
        Boolean bool = Boolean.TRUE;
        if (!bool.equals(objProceed) && handleOwnedByModule() && isHandleInGestureRange(chain.getThisObject())) {
            Object thisObject = chain.getThisObject();
            if (!isKeyguardShowing(thisObject)) {
                Context context = thisObject instanceof View ? ((View) thisObject).getContext() : null;
                if (context == null || !isHandleWakeSwitchOff(assistRestoreModule, context)) {
                    assistRestoreModule.logInfo("handle_press_availability_repaired");
                    return bool;
                }
            }
        }
        return objProceed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$installHandleTouchRegion$9(AssistRestoreModule assistRestoreModule, XposedInterface.Chain chain) {
        Object objProceed = chain.proceed();
        View view = (View) chain.getThisObject();
        lastHandleView = view;
        applyHandleTouchRegion(assistRestoreModule, view);
        return objProceed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$installHiddenBarWindowHooks$10(AssistRestoreModule assistRestoreModule, XposedInterface.Chain chain) {
        Object objProceed = chain.proceed();
        patchCreatedWindowAlpha(assistRestoreModule, objProceed);
        return objProceed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$installHiddenBarWindowHooks$11(AssistRestoreModule assistRestoreModule, View view) {
        if (keepHiddenWindowReachable(assistRestoreModule, view, true)) {
            assistRestoreModule.logInfo("handle_window_alpha reasserted after toggle");
        }
        View viewFindHandleView = lastHandleView;
        if (viewFindHandleView == null) {
            viewFindHandleView = findHandleView(view);
            lastHandleView = viewFindHandleView;
        }
        if (viewFindHandleView != null) {
            applyHandleTouchRegion(assistRestoreModule, viewFindHandleView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$installHiddenBarWindowHooks$12(final AssistRestoreModule assistRestoreModule, XposedInterface.Chain chain) {
        final View view = (View) chain.getThisObject();
        Object objProceed = chain.proceed();
        view.post(new Runnable() { // from class: io.github.andrea_lyz.assistrestore.h
            @Override // java.lang.Runnable
            public final void run() {
                SystemUiHooks.lambda$installHiddenBarWindowHooks$11(assistRestoreModule, view);
            }
        });
        return objProceed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$installHiddenGestureBarHandleTouch$8(AssistRestoreModule assistRestoreModule, XposedInterface.Chain chain) {
        Object objProceed = chain.proceed();
        if (Boolean.TRUE.equals(objProceed) && AssistConfig.isEnabled(HookPrefs.get()) && AssistConfig.handleWhenBarHidden(HookPrefs.get())) {
            String strMode = AssistConfig.mode(HookPrefs.get(), AssistConfig.ENTRY_HANDLE);
            if (!AssistConfig.MODE_NONE.equals(strMode) && !AssistConfig.MODE_OEM.equals(strMode) && isCalledFromSideGestureDetector()) {
                assistRestoreModule.logInfo("hidden_gesture_bar_handle_unblocked mode=" + strMode);
                return Boolean.FALSE;
            }
        }
        return objProceed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$installOcrScreenHandleLongPress$3(AssistRestoreModule assistRestoreModule, XposedInterface.Chain chain) {
        if (!AssistConfig.isEnabled(HookPrefs.get()) || !AssistConfig.skipOcrPreload(HookPrefs.get()) || isHandleOemMode()) {
            return chain.proceed();
        }
        assistRestoreModule.logInfo("gesture_handle_ocr_preload_skipped");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$installOcrScreenHandleLongPress$4(Field field, AssistRestoreModule assistRestoreModule, ClassLoader classLoader, AssistPipeline assistPipeline, Class cls, CtsPipeline ctsPipeline, XposedInterface.Chain chain) throws IllegalAccessException, InvocationTargetException {
        Context context = (Context) field.get(chain.getThisObject());
        Object objProceed = chain.proceed();
        dispatchGestureHandleLongPress(assistRestoreModule, classLoader, assistPipeline, cls, ctsPipeline, context);
        return objProceed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$installOcrScreenHandleLongPress$5(AssistRestoreModule assistRestoreModule, XposedInterface.Chain chain) {
        if (isHandleOemMode()) {
            return chain.proceed();
        }
        assistRestoreModule.logInfo("gesture_handle_ocr_action_skipped");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$keepHiddenWindowReachable$13(View view, AssistRestoreModule assistRestoreModule, View view2, WindowManager.LayoutParams layoutParams, boolean z2) {
        try {
            WindowManager windowManager = (WindowManager) view.getContext().getSystemService("window");
            if (windowManager == null) {
                assistRestoreModule.logWarn("handle_window_alpha_skipped reason=no_window_manager");
                return;
            }
            windowManager.updateViewLayout(view2, layoutParams);
            StringBuilder sb = new StringBuilder("handle_window_alpha=");
            sb.append(layoutParams.alpha);
            sb.append(" reason=");
            sb.append(z2 ? "keep_input" : "restore");
            assistRestoreModule.logInfo(sb.toString());
        } catch (Throwable th) {
            assistRestoreModule.logWarn("handle_window_alpha_failed " + th);
        }
    }

    private static Object lookupAssistManager(AssistRestoreModule assistRestoreModule, ClassLoader classLoader, Class<?> cls) {
        try {
            Class<?> cls2 = Class.forName(DEPENDENCY, false, classLoader);
            Object obj = cls2.getField("sDependency").get(null);
            if (obj == null) {
                return null;
            }
            return cls2.getMethod("getDependencyInner", Object.class).invoke(obj, cls);
        } catch (Throwable th) {
            assistRestoreModule.logError("assist_manager_lookup_failed", th);
            return null;
        }
    }

    private static int oplusDimension(View view, String str) {
        try {
            int identifier = view.getResources().getIdentifier(str, "dimen", "oplus");
            if (identifier != 0) {
                return view.getResources().getDimensionPixelSize(identifier);
            }
        } catch (Throwable unused) {
        }
        return 0;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0038 A[Catch: all -> 0x0020, TryCatch #0 {all -> 0x0020, blocks: (B:8:0x0015, B:10:0x0019, B:15:0x002e, B:17:0x0038, B:19:0x003c, B:21:0x0042, B:14:0x0022), top: B:28:0x0015, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:19:0x003c A[Catch: all -> 0x0020, TryCatch #0 {all -> 0x0020, blocks: (B:8:0x0015, B:10:0x0019, B:15:0x002e, B:17:0x0038, B:19:0x003c, B:21:0x0042, B:14:0x0022), top: B:28:0x0015, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:21:0x0042 A[Catch: all -> 0x0020, TRY_LEAVE, TryCatch #0 {all -> 0x0020, blocks: (B:8:0x0015, B:10:0x0019, B:15:0x002e, B:17:0x0038, B:19:0x003c, B:21:0x0042, B:14:0x0022), top: B:28:0x0015, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:34:0x0049 A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:17:0x0038, please report this as an issue */
    private static void patchCreatedWindowAlpha(AssistRestoreModule assistRestoreModule, Object obj) {
        Object[] objArr;
        int i5;
        if (handleOwnedByModule() && (obj instanceof WindowManager.LayoutParams)) {
            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) obj;
            boolean zPatchHiddenAlpha = patchHiddenAlpha(layoutParams);
            try {
                if (paramsForRotationField == null) {
                    try {
                        paramsForRotationField = WindowManager.LayoutParams.class.getField("paramsForRotation");
                    } catch (NoSuchFieldException unused) {
                        paramsForRotationField = WindowManager.LayoutParams.class.getDeclaredField("paramsForRotation");
                        paramsForRotationField.setAccessible(true);
                    }
                    objArr = (Object[]) paramsForRotationField.get(layoutParams);
                    if (objArr != null) {
                        for (Object obj2 : objArr) {
                            if (obj2 instanceof WindowManager.LayoutParams) {
                                zPatchHiddenAlpha |= patchHiddenAlpha((WindowManager.LayoutParams) obj2);
                            }
                        }
                    }
                } else {
                    objArr = (Object[]) paramsForRotationField.get(layoutParams);
                    if (objArr != null) {
                        while (i5 < r0) {
                            if (obj2 instanceof WindowManager.LayoutParams) {
                                zPatchHiddenAlpha |= patchHiddenAlpha((WindowManager.LayoutParams) obj2);
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                assistRestoreModule.logWarn("bar_window_alpha_rotation_failed " + th);
            }
            if (zPatchHiddenAlpha) {
                assistRestoreModule.logInfo("handle_window_alpha=0.01 reason=created_hidden");
            }
        }
    }

    private static boolean patchHiddenAlpha(WindowManager.LayoutParams layoutParams) {
        if (layoutParams == null || layoutParams.alpha != 0.0f) {
            return false;
        }
        layoutParams.alpha = HIDDEN_BAR_WINDOW_ALPHA;
        return true;
    }

    private static void repairHiddenHandleGestureRange(AssistRestoreModule assistRestoreModule, Object obj, MotionEvent motionEvent, boolean z2) {
        int iGestureBarWidthForCurrentRotation;
        if ((obj instanceof View) && motionEvent != null && motionEvent.getActionMasked() == 0 && handleOwnedByModule()) {
            View view = (View) obj;
            if (view.getWidth() <= 0 && (iGestureBarWidthForCurrentRotation = gestureBarWidthForCurrentRotation(view)) > 0) {
                boolean z3 = false;
                int width = view.getRootView() == null ? 0 : view.getRootView().getWidth();
                if (width <= 0) {
                    width = view.getResources().getDisplayMetrics().widthPixels;
                }
                if (width <= 0) {
                    return;
                }
                int iMax = Math.max(0, (width - iGestureBarWidthForCurrentRotation) / 2);
                int i5 = iGestureBarWidthForCurrentRotation + iMax;
                if (motionEvent.getX() >= iMax && motionEvent.getX() <= i5) {
                    z3 = true;
                }
                Field field = Refl.field(view.getClass(), "inGestureXRange");
                if (field == null) {
                    return;
                }
                try {
                    field.setBoolean(view, z3);
                    if (z2 && z3) {
                        assistRestoreModule.logInfo("handle_press_range_bootstrapped left=" + iMax + " right=" + i5);
                    }
                } catch (Throwable th) {
                    assistRestoreModule.logWarn("handle_press_range_bootstrap_failed " + th);
                }
            }
        }
    }

    private static AssistPipeline resolveAssistPipeline(AssistRestoreModule assistRestoreModule, ClassLoader classLoader) {
        try {
            Class<?> cls = Class.forName(ASSIST_MANAGER, true, classLoader);
            Class cls2 = Boolean.TYPE;
            Method method = Refl.method(cls, "startAssistInternal", Context.class, Bundle.class, ComponentName.class, cls2);
            if (method == null) {
                method = cls.getMethod("startAssistInternal", Bundle.class, ComponentName.class, cls2);
            }
            return new AssistPipeline(cls.getMethod("getAssistInfo", null), cls.getMethod("getVoiceInteractorComponentName", null), method, findContextField(cls), 0);
        } catch (Throwable th) {
            assistRestoreModule.logError("assist_pipeline_resolve_failed", th);
            return null;
        }
    }

    private static CtsPipeline resolveCtsPipeline(AssistRestoreModule assistRestoreModule, ClassLoader classLoader) {
        Method method;
        Object obj;
        try {
            Method method2 = Class.forName("android.os.ServiceManager", false, classLoader).getMethod("getService", String.class);
            Class<?> cls = Class.forName(CTS_INTERFACE, false, classLoader);
            Method method3 = Class.forName("android.app.contextualsearch.IContextualSearchManager$Stub", false, classLoader).getMethod("asInterface", IBinder.class);
            Class<?> clsLoad = Refl.load(classLoader, CTS_CONFIG);
            Class cls2 = Integer.TYPE;
            if (clsLoad != null) {
                method = Refl.method(cls, "startContextualSearch", cls2, clsLoad);
                obj = method != null ? clsLoad.getField("DEFAULT_CONFIG").get(null) : null;
            } else {
                method = null;
                obj = null;
            }
            if (method == null) {
                method = cls.getMethod("startContextualSearch", cls2);
            }
            return new CtsPipeline(method2, method3, method, obj, 0);
        } catch (Throwable th) {
            assistRestoreModule.logError("circle_to_search_pipeline_failed", th);
            return null;
        }
    }

    private static ComponentName resolveVoiceInteractionComponent(Context context, String str) {
        ServiceInfo serviceInfo;
        try {
            List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(new Intent(ACTION_VOICE_INTERACTION_SERVICE).setPackage(str), 0);
            if (listQueryIntentServices == null || listQueryIntentServices.isEmpty() || (serviceInfo = listQueryIntentServices.get(0).serviceInfo) == null) {
                return null;
            }
            return new ComponentName(serviceInfo.packageName, serviceInfo.name);
        } catch (Throwable unused) {
        }
        return null;
    }

    private static Region skipRegion(AssistRestoreModule assistRestoreModule, String str) {
        if (str.equals(lastRegionSkipReason)) {
            return null;
        }
        lastRegionSkipReason = str;
        assistRestoreModule.logInfo("handle_touch_region_skipped reason=".concat(str));
        return null;
    }

    private static boolean startConfiguredTarget(AssistRestoreModule assistRestoreModule, Context context, String str, AssistPipeline assistPipeline, Object obj) {
        String strTargetPackage = AssistConfig.targetPackage(HookPrefs.get(), str);
        if (context == null || strTargetPackage.isEmpty()) {
            StringBuilder sb = new StringBuilder("target_start_skipped entry=");
            sb.append(str);
            sb.append(" reason=");
            sb.append(context == null ? "no_context" : "no_package");
            assistRestoreModule.logWarn(sb.toString());
            return false;
        }
        Intent intentBuild = TargetIntents.build(context, HookPrefs.get(), str);
        if (intentBuild == null) {
            assistRestoreModule.logWarn("target_start_skipped entry=" + str + " reason=no_intent package=" + strTargetPackage);
            return false;
        }
        try {
            context.startActivity(intentBuild);
            assistRestoreModule.logInfo("target_started entry=" + str + " method=" + AssistConfig.targetMethod(HookPrefs.get(), str) + " component=" + intentBuild.getComponent() + " action=" + intentBuild.getAction() + " package=" + strTargetPackage);
            return true;
        } catch (Throwable th) {
            assistRestoreModule.logWarn("target_start_failed entry=" + str + " " + th);
            return false;
        }
    }

    private static boolean triggerCircleToSearch(AssistRestoreModule assistRestoreModule, CtsPipeline ctsPipeline) {
        if (ctsPipeline == null) {
            return false;
        }
        try {
            if (ctsPipeline.trigger()) {
                assistRestoreModule.logInfo("circle_to_search_triggered entrypoint=2");
                return true;
            }
        } catch (Throwable th) {
            assistRestoreModule.logWarn("circle_to_search_failed " + th);
        }
        return false;
    }

    private static View windowParamOwner(View view) {
        while (view != null) {
            if (view.getLayoutParams() instanceof WindowManager.LayoutParams) {
                return view;
            }
            Object parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        return null;
    }
}

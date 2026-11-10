package com.oplus.transition;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.RemoteAnimationTarget;
import android.view.SurfaceControl;
import android.window.BackAnimationAdapter;
import android.window.StartingWindowInfo;
import android.window.TransitionInfo;
import android.window.WindowContainerTransaction;
import com.android.launcher3.icons.IconProvider$;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SharedReflectionHelper {
    private static final String CLASS_TRANSITION_EXTENDED = "android.window.OplusTransitionExtendedInfo";
    private static final String CLASS_WCT_EXTENDED = "android.window.OplusWCTExtendInfo";
    static final String EXT = "mExt";
    public static final String FIELD_FLEXIBLE_TASK_BUNDLE = "mFlexibleTaskBundle";
    private static final String GET_BACK_ANIM_CONTINUOUS = "getBackAnimContinuous";
    static final String GET_EXTENDED_INFO = "getExtendedInfo";
    public static final String GET_FLEXIBLE_TASK_BUNDLE = "getFlexibleTaskBundle";
    private static final String GET_RECENT_FINISH_INPUT_CONSUMER_ENABLED = "getRecentFinishInputConsumerEnabled";
    private static final String GET_RECENT_FINISH_SEQ = "getRecentFinishSeq";
    private static final String GET_REMOTE_BACK_ANIM_CONTINUOUS = "getRemoteBackAnimContinuous";
    private static final String GET_REMOTE_INTERRUPT = "getRemoteInterrupt";
    private static final String GET_SPLIT_PAIR_PREVIEW_BITMAP = "getSplitPairPreviewBitmap";
    private static final String GET_TMP_FINISH_T = "getTmpFinishT";
    private static final String GET_WCT_EXTENDED_INFO = "getWCTExtendInfo";
    private static final String IS_FORCE_USE_BACKGROUND_COLOR = "isForceUseBackgroundColor";
    private static final String IS_MERGED_WITH_PREDICTIVE = "isMergedWithPredictive";
    private static final String IS_RECENT_FINISH_TO_HOME = "isRecentFinishToHome";
    private static final String KEY_TRIGGER_PREBOOT_LIMIT_SIZE = "trigger_preBoot_limit_size";
    private static final String SET_BACK_ANIM_CLOSING_LEASH = "setBackAnimClosingLeash";
    private static final String SET_BACK_ANIM_CONTINUOUS = "setBackAnimContinuous";
    static final String SET_EXTENDED_INFO = "setExtendedInfo";
    private static final String SET_IS_LEFT = "setIsLeft";
    private static final String SET_IS_PANORAMA_REMOTE = "setIsPanoramaRemote";
    private static final String SET_IS_PREDICATIVE_BACK_ANIMATION = "setIsPredictiveBackAnimation";
    static final String SET_RECENTS_CONTROLLER = "setRecentsController";
    private static final String SET_RECENT_FINISH_INPUT_CONSUMER_ENABLED = "setRecentFinishInputConsumerEnabled";
    private static final String SET_RECENT_FINISH_SEQ = "setRecentFinishSeq";
    private static final String SET_RECENT_FINISH_TO_HOME = "setRecentFinishToHome";
    private static final String SET_RECENT_INTERRUPT = "setInterruptTransition";
    private static final String SET_RECENT_TRANSITION = "setRecentTransition";
    private static final String SET_TASK_LEASH = "setTaskLeash";
    private static final String SET_TRIGGER_PREBOOT_LIMITSIZE = "setTriggerPreBootTransitionLimitSize";
    private static final String SET_WCT_EXTENDED_INFO = "setWCTExtendInfo";
    private static final String TAG = "SharedReflectionHelper";
    private static Object sFlexibleWindowManager;
    private static volatile Method sGetInstanceMethod;
    private static Field sTransitionInfoExtField;
    private static final ConcurrentHashMap<Class<?>, Field> sExtFieldCache = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Method> sMethodCache = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Field> sFieldCache = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Constructor<?>> sConstructorCache = new ConcurrentHashMap<>();
    private static final Class<?> sFlexibleWindowClazz = findClass("com.oplus.flexiblewindow.FlexibleWindowManager");

    private SharedReflectionHelper() {
    }

    private static Field cachedExtField(Object obj) {
        Class<?> cls = obj.getClass();
        ConcurrentHashMap<Class<?>, Field> concurrentHashMap = sExtFieldCache;
        Field field = concurrentHashMap.get(cls);
        if (field != null) {
            return field;
        }
        try {
            Field declaredField = cls.getDeclaredField(EXT);
            declaredField.setAccessible(true);
            concurrentHashMap.put(cls, declaredField);
            return declaredField;
        } catch (Exception unused) {
            Log.e(TAG, "cachedExtField error: ".concat(cls.getName()));
            return null;
        }
    }

    private static Field cachedField(Class<?> cls, String str) {
        String str2 = cls.getName() + "#" + str;
        ConcurrentHashMap<String, Field> concurrentHashMap = sFieldCache;
        Field field = concurrentHashMap.get(str2);
        if (field != null) {
            return field;
        }
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            concurrentHashMap.put(str2, declaredField);
            return declaredField;
        } catch (Exception unused) {
            Log.e(TAG, "cachedField error: " + cls.getName() + "#" + str);
            return null;
        }
    }

    private static Method cachedMethod(Class<?> cls, String str, Class<?>... clsArr) {
        String str2 = cls.getName() + "#" + str + "#" + Arrays.toString(clsArr);
        ConcurrentHashMap<String, Method> concurrentHashMap = sMethodCache;
        Method method = concurrentHashMap.get(str2);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            concurrentHashMap.put(str2, declaredMethod);
            return declaredMethod;
        } catch (Exception unused) {
            Log.e(TAG, "cachedMethod error: " + cls.getName() + "#" + str);
            return null;
        }
    }

    private static Method cachedPublicMethod(Class<?> cls, String str, Class<?>... clsArr) {
        String str2 = cls.getName() + "#pub#" + str + "#" + Arrays.toString(clsArr);
        ConcurrentHashMap<String, Method> concurrentHashMap = sMethodCache;
        Method method = concurrentHashMap.get(str2);
        if (method != null) {
            return method;
        }
        try {
            Method method2 = cls.getMethod(str, clsArr);
            method2.setAccessible(true);
            concurrentHashMap.put(str2, method2);
            return method2;
        } catch (Exception unused) {
            Log.e(TAG, "cachedPublicMethod error: " + cls.getName() + "#" + str);
            return null;
        }
    }

    private static Class<?> findClass(String str) {
        try {
            return Class.forName(str);
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("findClass error: "), TAG);
            return null;
        }
    }

    public static boolean getBackAnimContinuous(WindowContainerTransaction windowContainerTransaction) {
        Object obj;
        Method methodCachedMethod;
        Object objInvoke;
        Method methodCachedMethod2;
        if (windowContainerTransaction == null) {
            return false;
        }
        try {
            Field fieldCachedExtField = cachedExtField(windowContainerTransaction);
            if (fieldCachedExtField == null || (obj = fieldCachedExtField.get(windowContainerTransaction)) == null || (methodCachedMethod = cachedMethod(obj.getClass(), GET_WCT_EXTENDED_INFO, new Class[0])) == null || (objInvoke = methodCachedMethod.invoke(obj, null)) == null || (methodCachedMethod2 = cachedMethod(objInvoke.getClass(), GET_BACK_ANIM_CONTINUOUS, new Class[0])) == null) {
                return false;
            }
            return ((Boolean) methodCachedMethod2.invoke(objInvoke, null)).booleanValue();
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("getBackAnimContinuous error : "), TAG);
            return false;
        }
    }

    private static Object getFlexibleWindowManager() {
        Class<?> cls = sFlexibleWindowClazz;
        if (cls == null) {
            return null;
        }
        if (sFlexibleWindowManager == null) {
            try {
                Method declaredMethod = sGetInstanceMethod;
                if (declaredMethod == null) {
                    Class[] clsArr = new Class[0];
                    declaredMethod = cls.getDeclaredMethod("getInstance", null);
                    declaredMethod.setAccessible(true);
                    sGetInstanceMethod = declaredMethod;
                }
                sFlexibleWindowManager = declaredMethod.invoke(null, null);
            } catch (Exception e) {
                IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("getFlexibleWindowManager error: "), TAG);
            }
        }
        return sFlexibleWindowManager;
    }

    public static boolean getIsMergedWithPredictive(TransitionInfo transitionInfo) {
        Object obj;
        Method methodCachedMethod;
        Object objInvoke;
        Method methodCachedMethod2;
        if (transitionInfo == null) {
            return false;
        }
        try {
            Field fieldCachedExtField = cachedExtField(transitionInfo);
            if (fieldCachedExtField == null || (obj = fieldCachedExtField.get(transitionInfo)) == null || (methodCachedMethod = cachedMethod(obj.getClass(), GET_EXTENDED_INFO, new Class[0])) == null || (objInvoke = methodCachedMethod.invoke(obj, null)) == null || (methodCachedMethod2 = cachedMethod(objInvoke.getClass(), IS_MERGED_WITH_PREDICTIVE, new Class[0])) == null) {
                return false;
            }
            return ((Boolean) methodCachedMethod2.invoke(objInvoke, null)).booleanValue();
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("getIsMergedWithPredictive error : "), TAG);
            return false;
        }
    }

    public static boolean getRecentFinishInputConsumerEnabled(WindowContainerTransaction windowContainerTransaction) {
        Object obj;
        Method methodCachedMethod;
        Object objInvoke;
        Method methodCachedMethod2;
        if (windowContainerTransaction == null) {
            return false;
        }
        try {
            Field fieldCachedExtField = cachedExtField(windowContainerTransaction);
            if (fieldCachedExtField == null || (obj = fieldCachedExtField.get(windowContainerTransaction)) == null || (methodCachedMethod = cachedMethod(obj.getClass(), GET_WCT_EXTENDED_INFO, new Class[0])) == null || (objInvoke = methodCachedMethod.invoke(obj, null)) == null || (methodCachedMethod2 = cachedMethod(objInvoke.getClass(), GET_RECENT_FINISH_INPUT_CONSUMER_ENABLED, new Class[0])) == null) {
                return false;
            }
            return ((Boolean) methodCachedMethod2.invoke(objInvoke, null)).booleanValue();
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("getRecentFinishInputConsumerEnabled error : "), TAG);
            return false;
        }
    }

    public static long getRecentFinishSeq(WindowContainerTransaction windowContainerTransaction) {
        Object obj;
        Method methodCachedMethod;
        Object objInvoke;
        Method methodCachedMethod2;
        if (windowContainerTransaction == null) {
            return 0L;
        }
        try {
            Field fieldCachedExtField = cachedExtField(windowContainerTransaction);
            if (fieldCachedExtField == null || (obj = fieldCachedExtField.get(windowContainerTransaction)) == null || (methodCachedMethod = cachedMethod(obj.getClass(), GET_WCT_EXTENDED_INFO, new Class[0])) == null || (objInvoke = methodCachedMethod.invoke(obj, null)) == null || (methodCachedMethod2 = cachedMethod(objInvoke.getClass(), GET_RECENT_FINISH_SEQ, new Class[0])) == null) {
                return 0L;
            }
            return ((Long) methodCachedMethod2.invoke(objInvoke, null)).longValue();
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("getRecentFinishSeq error : "), TAG);
            return 0L;
        }
    }

    public static boolean getRemoteBackAnimContinuous(WindowContainerTransaction windowContainerTransaction) {
        Object obj;
        Method methodCachedMethod;
        Object objInvoke;
        Method methodCachedMethod2;
        if (windowContainerTransaction == null) {
            return false;
        }
        try {
            Field fieldCachedExtField = cachedExtField(windowContainerTransaction);
            if (fieldCachedExtField == null || (obj = fieldCachedExtField.get(windowContainerTransaction)) == null || (methodCachedMethod = cachedMethod(obj.getClass(), GET_WCT_EXTENDED_INFO, new Class[0])) == null || (objInvoke = methodCachedMethod.invoke(obj, null)) == null || (methodCachedMethod2 = cachedMethod(objInvoke.getClass(), GET_REMOTE_BACK_ANIM_CONTINUOUS, new Class[0])) == null) {
                return false;
            }
            return ((Boolean) methodCachedMethod2.invoke(objInvoke, null)).booleanValue();
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("getRemoteBackAnimContinuous error : "), TAG);
            return false;
        }
    }

    public static boolean getRemoteContinuous(WindowContainerTransaction windowContainerTransaction) {
        Object obj;
        Method methodCachedMethod;
        Object objInvoke;
        Method methodCachedMethod2;
        if (windowContainerTransaction == null) {
            return false;
        }
        try {
            Field fieldCachedExtField = cachedExtField(windowContainerTransaction);
            if (fieldCachedExtField == null || (obj = fieldCachedExtField.get(windowContainerTransaction)) == null || (methodCachedMethod = cachedMethod(obj.getClass(), GET_WCT_EXTENDED_INFO, new Class[0])) == null || (objInvoke = methodCachedMethod.invoke(obj, null)) == null || (methodCachedMethod2 = cachedMethod(objInvoke.getClass(), GET_REMOTE_INTERRUPT, new Class[0])) == null) {
                return false;
            }
            return ((Boolean) methodCachedMethod2.invoke(objInvoke, null)).booleanValue();
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("getRemoteContinuous error : "), TAG);
            return false;
        }
    }

    public static Bitmap getSplitPairPreviewBitmap(StartingWindowInfo startingWindowInfo) {
        Object obj;
        Method methodCachedMethod;
        Object objInvoke;
        Method methodCachedMethod2;
        if (startingWindowInfo == null) {
            return null;
        }
        try {
            Field fieldCachedExtField = cachedExtField(startingWindowInfo);
            if (fieldCachedExtField == null || (obj = fieldCachedExtField.get(startingWindowInfo)) == null || (methodCachedMethod = cachedMethod(obj.getClass(), GET_EXTENDED_INFO, new Class[0])) == null || (objInvoke = methodCachedMethod.invoke(obj, null)) == null || (methodCachedMethod2 = cachedMethod(objInvoke.getClass(), GET_SPLIT_PAIR_PREVIEW_BITMAP, new Class[0])) == null) {
                return null;
            }
            return (Bitmap) methodCachedMethod2.invoke(objInvoke, null);
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("getSplitPairPreviewBitmap error : "), TAG);
            return null;
        }
    }

    public static SurfaceControl.Transaction getTmpFinishT(TransitionInfo transitionInfo) {
        Object obj;
        Method methodCachedMethod;
        Object objInvoke;
        Method methodCachedMethod2;
        if (transitionInfo == null) {
            return null;
        }
        try {
            Field fieldCachedExtField = cachedExtField(transitionInfo);
            if (fieldCachedExtField == null || (obj = fieldCachedExtField.get(transitionInfo)) == null || (methodCachedMethod = cachedMethod(obj.getClass(), GET_EXTENDED_INFO, new Class[0])) == null || (objInvoke = methodCachedMethod.invoke(obj, null)) == null || (methodCachedMethod2 = cachedMethod(objInvoke.getClass(), GET_TMP_FINISH_T, new Class[0])) == null) {
                return null;
            }
            return (SurfaceControl.Transaction) methodCachedMethod2.invoke(objInvoke, null);
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("getTmpFinishT error : "), TAG);
            return null;
        }
    }

    private static Field getTransitionInfoExtField() {
        if (sTransitionInfoExtField == null) {
            try {
                Field declaredField = TransitionInfo.class.getDeclaredField(EXT);
                declaredField.setAccessible(true);
                sTransitionInfoExtField = declaredField;
            } catch (NoSuchFieldException e) {
                Log.w(TAG, "getTransitionInfoExtField error: " + e.getMessage());
            }
        }
        return sTransitionInfoExtField;
    }

    public static Object getValue(Class cls, Object obj, String str) {
        Field fieldCachedField = cachedField(cls, str);
        if (fieldCachedField == null) {
            return null;
        }
        try {
            return fieldCachedField.get(obj);
        } catch (IllegalAccessException unused) {
            Log.e(TAG, "getValue IllegalAccess " + cls + " field 2" + str);
            return null;
        }
    }

    public static boolean isForceUseBackgroundColor(TransitionInfo transitionInfo) {
        Object obj;
        if (transitionInfo == null) {
            return false;
        }
        try {
            Field transitionInfoExtField = getTransitionInfoExtField();
            if (transitionInfoExtField != null && (obj = transitionInfoExtField.get(transitionInfo)) != null) {
                Class[] clsArr = new Class[0];
                Method declaredMethod = obj.getClass().getDeclaredMethod(GET_EXTENDED_INFO, null);
                declaredMethod.setAccessible(true);
                Object objInvoke = declaredMethod.invoke(obj, null);
                if (objInvoke != null) {
                    Class[] clsArr2 = new Class[0];
                    Method declaredMethod2 = objInvoke.getClass().getDeclaredMethod(IS_FORCE_USE_BACKGROUND_COLOR, null);
                    declaredMethod2.setAccessible(true);
                    return ((Boolean) declaredMethod2.invoke(objInvoke, null)).booleanValue();
                }
            }
            return false;
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("isForceUseBackgroundColor error : "), TAG);
            return false;
        }
    }

    public static boolean isRecentFinishToHome(WindowContainerTransaction windowContainerTransaction) {
        Object obj;
        Method methodCachedMethod;
        Object objInvoke;
        Method methodCachedMethod2;
        if (windowContainerTransaction == null) {
            return false;
        }
        try {
            Field fieldCachedExtField = cachedExtField(windowContainerTransaction);
            if (fieldCachedExtField == null || (obj = fieldCachedExtField.get(windowContainerTransaction)) == null || (methodCachedMethod = cachedMethod(obj.getClass(), GET_WCT_EXTENDED_INFO, new Class[0])) == null || (objInvoke = methodCachedMethod.invoke(obj, null)) == null || (methodCachedMethod2 = cachedMethod(objInvoke.getClass(), IS_RECENT_FINISH_TO_HOME, new Class[0])) == null) {
                return false;
            }
            return ((Boolean) methodCachedMethod2.invoke(objInvoke, null)).booleanValue();
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("isRecentFinishToHome error : "), TAG);
            return false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0017 A[Catch: Exception -> 0x0015, TryCatch #0 {Exception -> 0x0015, blocks: (B:8:0x000d, B:10:0x0010, B:17:0x0023, B:19:0x0026, B:21:0x002b, B:13:0x0017), top: B:25:0x000d }] */
    public static <T> T methodInvoke(Object obj, Class<?> cls, String str, Class<?>[] clsArr, Object... objArr) {
        Method methodCachedMethod;
        if (obj != null && cls != null && !TextUtils.isEmpty(str)) {
            if (clsArr != null) {
                try {
                    if (clsArr.length > 0) {
                        methodCachedMethod = cachedMethod(cls, str, clsArr);
                    } else {
                        methodCachedMethod = cachedMethod(cls, str, new Class[0]);
                    }
                } catch (Exception e) {
                    Log.e(TAG, cls + " methodInvoke " + str + " failed2", e);
                }
            } else {
                methodCachedMethod = cachedMethod(cls, str, new Class[0]);
            }
            if (methodCachedMethod == null) {
                return null;
            }
            return (clsArr == null || clsArr.length <= 0) ? (T) methodCachedMethod.invoke(obj, null) : (T) methodCachedMethod.invoke(obj, objArr);
        }
        return null;
    }

    public static <T> T methodInvokeOnExt(Object obj, String str, Class<?>[] clsArr, Object... objArr) {
        if (obj == null) {
            return null;
        }
        try {
            Object value = getValue(obj.getClass(), obj, EXT);
            if (value != null) {
                return (T) methodInvoke(value, value.getClass(), str, clsArr, objArr);
            }
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("methodInvokeOnExt error: "), TAG);
        }
        return null;
    }

    private static Object newExtendedInstance(String str) throws NoSuchMethodException {
        ConcurrentHashMap<String, Constructor<?>> concurrentHashMap = sConstructorCache;
        Constructor<?> declaredConstructor = concurrentHashMap.get(str);
        if (declaredConstructor == null) {
            Class[] clsArr = new Class[0];
            declaredConstructor = Class.forName(str).getDeclaredConstructor(null);
            concurrentHashMap.put(str, declaredConstructor);
        }
        return declaredConstructor.newInstance(null);
    }

    public static void recordRecentFinishState(WindowContainerTransaction windowContainerTransaction, boolean z, long j, boolean z2) {
        Object obj;
        if (windowContainerTransaction == null) {
            return;
        }
        try {
            Field fieldCachedExtField = cachedExtField(windowContainerTransaction);
            if (fieldCachedExtField == null || (obj = fieldCachedExtField.get(windowContainerTransaction)) == null) {
                return;
            }
            Object objNewExtendedInstance = newExtendedInstance(CLASS_WCT_EXTENDED);
            Class<?> cls = objNewExtendedInstance.getClass();
            Class cls2 = Boolean.TYPE;
            Method methodCachedMethod = cachedMethod(cls, SET_RECENT_FINISH_TO_HOME, cls2);
            if (methodCachedMethod != null) {
                methodCachedMethod.invoke(objNewExtendedInstance, Boolean.valueOf(z));
            }
            Method methodCachedMethod2 = cachedMethod(objNewExtendedInstance.getClass(), SET_RECENT_FINISH_SEQ, Long.TYPE);
            if (methodCachedMethod2 != null) {
                methodCachedMethod2.invoke(objNewExtendedInstance, Long.valueOf(j));
            }
            Method methodCachedMethod3 = cachedMethod(objNewExtendedInstance.getClass(), SET_RECENT_FINISH_INPUT_CONSUMER_ENABLED, cls2);
            if (methodCachedMethod3 != null) {
                methodCachedMethod3.invoke(objNewExtendedInstance, Boolean.valueOf(z2));
            }
            Method methodCachedMethod4 = cachedMethod(obj.getClass(), SET_WCT_EXTENDED_INFO, Object.class);
            if (methodCachedMethod4 != null) {
                methodCachedMethod4.invoke(obj, objNewExtendedInstance);
            }
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("recordRecentFinishState error : "), TAG);
        }
    }

    public static void setBackAnimClosingLeash(TransitionInfo transitionInfo, SurfaceControl surfaceControl) {
        Object obj;
        Method methodCachedMethod;
        if (transitionInfo == null) {
            return;
        }
        try {
            Field fieldCachedExtField = cachedExtField(transitionInfo);
            if (fieldCachedExtField != null && (obj = fieldCachedExtField.get(transitionInfo)) != null && (methodCachedMethod = cachedMethod(obj.getClass(), GET_EXTENDED_INFO, new Class[0])) != null) {
                Object objInvoke = methodCachedMethod.invoke(obj, null);
                if (objInvoke == null) {
                    objInvoke = newExtendedInstance(CLASS_TRANSITION_EXTENDED);
                }
                Method methodCachedMethod2 = cachedMethod(objInvoke.getClass(), SET_BACK_ANIM_CLOSING_LEASH, SurfaceControl.class);
                if (methodCachedMethod2 != null) {
                    methodCachedMethod2.invoke(objInvoke, surfaceControl);
                }
                Method methodCachedMethod3 = cachedMethod(obj.getClass(), SET_EXTENDED_INFO, Object.class);
                if (methodCachedMethod3 != null) {
                    methodCachedMethod3.invoke(obj, objInvoke);
                }
            }
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("setBackAnimClosingLeash error : "), TAG);
        }
    }

    public static void setBackAnimContinuous(WindowContainerTransaction windowContainerTransaction, boolean z) {
        Object obj;
        Method methodCachedMethod;
        if (windowContainerTransaction == null) {
            return;
        }
        try {
            Field fieldCachedExtField = cachedExtField(windowContainerTransaction);
            if (fieldCachedExtField != null && (obj = fieldCachedExtField.get(windowContainerTransaction)) != null && (methodCachedMethod = cachedMethod(obj.getClass(), GET_WCT_EXTENDED_INFO, new Class[0])) != null) {
                Object objInvoke = methodCachedMethod.invoke(obj, null);
                if (objInvoke == null) {
                    objInvoke = newExtendedInstance(CLASS_WCT_EXTENDED);
                }
                Method methodCachedMethod2 = cachedMethod(objInvoke.getClass(), SET_BACK_ANIM_CONTINUOUS, Boolean.TYPE);
                if (methodCachedMethod2 != null) {
                    methodCachedMethod2.invoke(objInvoke, Boolean.valueOf(z));
                }
                Method methodCachedMethod3 = cachedMethod(obj.getClass(), SET_WCT_EXTENDED_INFO, Object.class);
                if (methodCachedMethod3 != null) {
                    methodCachedMethod3.invoke(obj, objInvoke);
                }
            }
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("setBackAnimContinuous error : "), TAG);
        }
    }

    public static void setContinuousTransition(WindowContainerTransaction windowContainerTransaction, boolean z) {
        Object obj;
        Method methodCachedMethod;
        if (windowContainerTransaction == null) {
            return;
        }
        try {
            Field fieldCachedExtField = cachedExtField(windowContainerTransaction);
            if (fieldCachedExtField != null && (obj = fieldCachedExtField.get(windowContainerTransaction)) != null && (methodCachedMethod = cachedMethod(obj.getClass(), GET_WCT_EXTENDED_INFO, new Class[0])) != null) {
                Object objInvoke = methodCachedMethod.invoke(obj, null);
                if (objInvoke == null) {
                    objInvoke = newExtendedInstance(CLASS_WCT_EXTENDED);
                }
                Method methodCachedMethod2 = cachedMethod(objInvoke.getClass(), SET_RECENT_INTERRUPT, Boolean.TYPE);
                if (methodCachedMethod2 != null) {
                    methodCachedMethod2.invoke(objInvoke, Boolean.valueOf(z));
                }
                Method methodCachedMethod3 = cachedMethod(obj.getClass(), SET_WCT_EXTENDED_INFO, Object.class);
                if (methodCachedMethod3 != null) {
                    methodCachedMethod3.invoke(obj, objInvoke);
                }
            }
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("setRecentInterrupt error : "), TAG);
        }
    }

    public static void setIsEdgeLeftInAdapter(boolean z, BackAnimationAdapter backAnimationAdapter) {
        if (backAnimationAdapter == null) {
            return;
        }
        try {
            Method methodCachedMethod = cachedMethod(backAnimationAdapter.getClass(), SET_IS_LEFT, Boolean.TYPE);
            if (methodCachedMethod != null) {
                methodCachedMethod.invoke(backAnimationAdapter, Boolean.valueOf(z));
            }
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("setIsEdgeLeftInAdapter error : "), TAG);
        }
    }

    public static void setIsPanoramaRemote(RemoteAnimationTarget remoteAnimationTarget, boolean z) {
        Object obj;
        Method methodCachedMethod;
        if (remoteAnimationTarget == null) {
            return;
        }
        try {
            Field fieldCachedExtField = cachedExtField(remoteAnimationTarget);
            if (fieldCachedExtField == null || (obj = fieldCachedExtField.get(remoteAnimationTarget)) == null || (methodCachedMethod = cachedMethod(obj.getClass(), SET_IS_PANORAMA_REMOTE, Boolean.TYPE)) == null) {
                return;
            }
            methodCachedMethod.invoke(obj, Boolean.valueOf(z));
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("setIsPanoramaRemote error : "), TAG);
        }
    }

    public static void setIsPredictiveBackAnimation(TransitionInfo transitionInfo, boolean z) {
        Object obj;
        Method methodCachedMethod;
        if (transitionInfo == null) {
            return;
        }
        try {
            Field fieldCachedExtField = cachedExtField(transitionInfo);
            if (fieldCachedExtField != null && (obj = fieldCachedExtField.get(transitionInfo)) != null && (methodCachedMethod = cachedMethod(obj.getClass(), GET_EXTENDED_INFO, new Class[0])) != null) {
                Object objInvoke = methodCachedMethod.invoke(obj, null);
                if (objInvoke == null) {
                    objInvoke = newExtendedInstance(CLASS_TRANSITION_EXTENDED);
                }
                Method methodCachedMethod2 = cachedMethod(objInvoke.getClass(), SET_IS_PREDICATIVE_BACK_ANIMATION, Boolean.TYPE);
                if (methodCachedMethod2 != null) {
                    methodCachedMethod2.invoke(objInvoke, Boolean.valueOf(z));
                }
                Method methodCachedMethod3 = cachedMethod(obj.getClass(), SET_EXTENDED_INFO, Object.class);
                if (methodCachedMethod3 != null) {
                    methodCachedMethod3.invoke(obj, objInvoke);
                }
            }
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("setIsPredictiveBackAnimation error : "), TAG);
        }
    }

    public static void setRemoteTaskLeash(RemoteAnimationTarget remoteAnimationTarget, SurfaceControl surfaceControl) {
        Object obj;
        Method methodCachedMethod;
        if (remoteAnimationTarget == null) {
            return;
        }
        try {
            Field fieldCachedExtField = cachedExtField(remoteAnimationTarget);
            if (fieldCachedExtField == null || (obj = fieldCachedExtField.get(remoteAnimationTarget)) == null || (methodCachedMethod = cachedMethod(obj.getClass(), SET_TASK_LEASH, SurfaceControl.class)) == null) {
                return;
            }
            methodCachedMethod.invoke(obj, surfaceControl);
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("setRemoteTaskLeash error : "), TAG);
        }
    }

    public static void setWCTExtOnRecents(WindowContainerTransaction windowContainerTransaction, boolean z, Bundle bundle) {
        Object obj;
        Method methodCachedPublicMethod;
        if (windowContainerTransaction == null) {
            return;
        }
        int i = bundle != null ? bundle.getInt(KEY_TRIGGER_PREBOOT_LIMIT_SIZE, -1) : -1;
        try {
            Field fieldCachedExtField = cachedExtField(windowContainerTransaction);
            if (fieldCachedExtField == null || (obj = fieldCachedExtField.get(windowContainerTransaction)) == null) {
                return;
            }
            Object objNewExtendedInstance = newExtendedInstance(CLASS_WCT_EXTENDED);
            Method methodCachedMethod = cachedMethod(objNewExtendedInstance.getClass(), SET_RECENT_TRANSITION, Boolean.TYPE);
            if (methodCachedMethod != null) {
                methodCachedMethod.invoke(objNewExtendedInstance, Boolean.valueOf(z));
            }
            if (i >= 0 && (methodCachedPublicMethod = cachedPublicMethod(objNewExtendedInstance.getClass(), SET_TRIGGER_PREBOOT_LIMITSIZE, Integer.TYPE)) != null) {
                methodCachedPublicMethod.invoke(objNewExtendedInstance, Integer.valueOf(i));
            }
            Method methodCachedMethod2 = cachedMethod(obj.getClass(), SET_WCT_EXTENDED_INFO, Object.class);
            if (methodCachedMethod2 != null) {
                methodCachedMethod2.invoke(obj, objNewExtendedInstance);
            }
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("setRecentTransition error : "), TAG);
        }
    }

    public static void setIsPredictiveBackAnimation(RemoteAnimationTarget remoteAnimationTarget, boolean z) {
        Object obj;
        Method methodCachedMethod;
        if (remoteAnimationTarget == null) {
            return;
        }
        try {
            Field fieldCachedExtField = cachedExtField(remoteAnimationTarget);
            if (fieldCachedExtField == null || (obj = fieldCachedExtField.get(remoteAnimationTarget)) == null || (methodCachedMethod = cachedMethod(obj.getClass(), SET_IS_PREDICATIVE_BACK_ANIMATION, Boolean.TYPE)) == null) {
                return;
            }
            methodCachedMethod.invoke(obj, Boolean.valueOf(z));
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("setIsPredictiveBackAnimation error : "), TAG);
        }
    }
}

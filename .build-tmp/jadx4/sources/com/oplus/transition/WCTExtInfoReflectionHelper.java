package com.oplus.transition;

import android.os.SystemProperties;
import android.util.Log;
import android.window.TransitionInfo;
import android.window.WindowContainerTransaction;
import androidx.activity.result.ActivityResultRegistry$;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class WCTExtInfoReflectionHelper {
    private static final String CLASS_WCT_EXTENDED = "android.window.OplusWCTExtendInfo";
    private static final String EXT = "mExt";
    private static final String GET_WCT_EXTENDED_INFO = "getWCTExtendInfo";
    private static final String SET_ADD_EXTEND_FLAGS = "addFlags";
    private static final String SET_ENTER_PIP_TRANSITION = "setIsEnterPIPTransition";
    private static final String SET_PIP_BOUNDS_CHANGE = "setPipBoundsChange";
    private static final String SET_PIP_OVERLAY_TASK_ID = "setPipOverlayTaskId";
    private static final String SET_TASK_VIEW_BOUNDS_CHANGE_TRANSITION = "setTaskViewBoundsChangeTransition";
    private static final String SET_WCT_EXTENDED_INFO = "setWCTExtendInfo";
    private static final String TAG = "WCTExtInfoReflectionHelper";
    private static volatile Field sExtField;
    private static volatile Class<?> sWctExtendedClazz;
    private static volatile Constructor<?> sWctExtendedCtor;
    private static final boolean DEBUG = SystemProperties.getBoolean("persist.sys.assert.panic", false);
    private static final ConcurrentHashMap<String, Method> sMethodCache = new ConcurrentHashMap<>();

    public static void addFlags(WindowContainerTransaction windowContainerTransaction, int i) {
        if (windowContainerTransaction == null) {
            return;
        }
        try {
            Object orCreateWctExtendedInfo = getOrCreateWctExtendedInfo(windowContainerTransaction);
            if (orCreateWctExtendedInfo != null) {
                Method cachedMethod = getCachedMethod(orCreateWctExtendedInfo.getClass(), SET_ADD_EXTEND_FLAGS, Integer.TYPE);
                if (cachedMethod != null) {
                    cachedMethod.invoke(orCreateWctExtendedInfo, Integer.valueOf(i));
                }
                Log.i(TAG, "addFlags flags : " + i);
            }
        } catch (Exception e) {
            Log.i(TAG, "addFlags error : " + e.getMessage());
        }
    }

    public static void addFlagsForKeyguardHandler(WindowContainerTransaction windowContainerTransaction, TransitionInfo transitionInfo) {
        if (windowContainerTransaction == null || transitionInfo == null || (transitionInfo.getFlags() & 256) == 0) {
            return;
        }
        addFlags(windowContainerTransaction, 256);
    }

    private static Method getCachedMethod(Class<?> cls, String str, Class<?>... clsArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(cls.getName());
        sb.append("#");
        sb.append(str);
        sb.append((clsArr == null || clsArr.length <= 0) ? "()" : Arrays.toString(clsArr));
        String string = sb.toString();
        ConcurrentHashMap<String, Method> concurrentHashMap = sMethodCache;
        Method declaredMethod = concurrentHashMap.get(string);
        if (declaredMethod != null) {
            return declaredMethod;
        }
        try {
            declaredMethod = cls.getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            concurrentHashMap.put(string, declaredMethod);
            return declaredMethod;
        } catch (Exception e) {
            StringBuilder sbM = ActivityResultRegistry$.ExternalSyntheticOutline0.m("getCachedMethod error for ", string, ": ");
            sbM.append(e.getMessage());
            Log.i(TAG, sbM.toString());
            return declaredMethod;
        }
    }

    private static Object getOrCreateWctExtendedInfo(WindowContainerTransaction windowContainerTransaction) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        Field declaredField = sExtField;
        if (declaredField == null) {
            declaredField = windowContainerTransaction.getClass().getDeclaredField(EXT);
            declaredField.setAccessible(true);
            sExtField = declaredField;
        }
        Object obj = declaredField.get(windowContainerTransaction);
        if (obj == null) {
            return null;
        }
        Method cachedMethod = getCachedMethod(obj.getClass(), GET_WCT_EXTENDED_INFO, new Class[0]);
        Object objInvoke = cachedMethod != null ? cachedMethod.invoke(obj, null) : null;
        if (objInvoke != null) {
            return objInvoke;
        }
        Class<?> cls = sWctExtendedClazz;
        if (cls == null) {
            cls = Class.forName(CLASS_WCT_EXTENDED);
            sWctExtendedClazz = cls;
        }
        Constructor<?> declaredConstructor = sWctExtendedCtor;
        if (declaredConstructor == null) {
            Class[] clsArr = new Class[0];
            declaredConstructor = cls.getDeclaredConstructor(null);
            sWctExtendedCtor = declaredConstructor;
        }
        Object objNewInstance = declaredConstructor.newInstance(null);
        Method cachedMethod2 = getCachedMethod(obj.getClass(), SET_WCT_EXTENDED_INFO, Object.class);
        if (cachedMethod2 != null) {
            cachedMethod2.invoke(obj, objNewInstance);
        }
        return objNewInstance;
    }

    public static void setEnterPipToWctExtendInfo(WindowContainerTransaction windowContainerTransaction, boolean z) {
        if (windowContainerTransaction == null) {
            return;
        }
        try {
            Object orCreateWctExtendedInfo = getOrCreateWctExtendedInfo(windowContainerTransaction);
            if (orCreateWctExtendedInfo != null) {
                Method cachedMethod = getCachedMethod(orCreateWctExtendedInfo.getClass(), SET_ENTER_PIP_TRANSITION, Boolean.TYPE);
                if (cachedMethod != null) {
                    cachedMethod.invoke(orCreateWctExtendedInfo, Boolean.valueOf(z));
                }
                Log.i(TAG, "setIsEnterPip: " + z);
            }
        } catch (Exception e) {
            Log.i(TAG, "setIsEnterPip error : " + e.getMessage());
        }
    }

    public static void setPipBoundsChange(WindowContainerTransaction windowContainerTransaction, boolean z) {
        Method cachedMethod;
        if (windowContainerTransaction == null) {
            return;
        }
        try {
            Object orCreateWctExtendedInfo = getOrCreateWctExtendedInfo(windowContainerTransaction);
            if (orCreateWctExtendedInfo == null || (cachedMethod = getCachedMethod(orCreateWctExtendedInfo.getClass(), SET_PIP_BOUNDS_CHANGE, Boolean.TYPE)) == null) {
                return;
            }
            cachedMethod.invoke(orCreateWctExtendedInfo, Boolean.valueOf(z));
        } catch (Exception e) {
            Log.i(TAG, "setPipBoundsChange error : " + e.getMessage());
        }
    }

    public static void setPipOverlayTaskId(WindowContainerTransaction windowContainerTransaction, int i) {
        if (windowContainerTransaction == null) {
            return;
        }
        try {
            Object orCreateWctExtendedInfo = getOrCreateWctExtendedInfo(windowContainerTransaction);
            if (orCreateWctExtendedInfo != null) {
                Method cachedMethod = getCachedMethod(orCreateWctExtendedInfo.getClass(), SET_PIP_OVERLAY_TASK_ID, Integer.TYPE);
                if (cachedMethod != null) {
                    cachedMethod.invoke(orCreateWctExtendedInfo, Integer.valueOf(i));
                }
                Log.i(TAG, "setPipOverlayTaskId: " + i);
            }
        } catch (Exception e) {
            Log.i(TAG, "setPipOverlayTaskId error : " + e.getMessage());
        }
    }

    public static void setTaskViewBoundsChangeTransition(WindowContainerTransaction windowContainerTransaction, boolean z) {
        Method cachedMethod;
        if (windowContainerTransaction == null) {
            return;
        }
        try {
            Object orCreateWctExtendedInfo = getOrCreateWctExtendedInfo(windowContainerTransaction);
            if (orCreateWctExtendedInfo == null || (cachedMethod = getCachedMethod(orCreateWctExtendedInfo.getClass(), SET_TASK_VIEW_BOUNDS_CHANGE_TRANSITION, Boolean.TYPE)) == null) {
                return;
            }
            cachedMethod.invoke(orCreateWctExtendedInfo, Boolean.valueOf(z));
        } catch (Exception e) {
            Log.i(TAG, "setTaskViewBoundsChangeTransition error : " + e.getMessage());
        }
    }
}

package com.oplus.zoom.common;

import android.os.IBinder;
import android.window.WindowContainerToken;
import com.oplus.zoomwindow.IOplusZoomTaskListener;
import com.oplus.zoomwindow.OplusZoomTaskInfo;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ReflectionHelper {
    private static final String TAG = "ReflectionHelper";
    private static Object sOplusActivityManager;
    private static Object sZoomTaskManagerInternal;
    private static final ConcurrentHashMap<String, Method> sMethodCache = new ConcurrentHashMap<>();
    private static final Class<?> sZoomManagerClazz = findClass("com.oplus.zoomwindow.OplusZoomTaskManagerInternal");
    private static final Class<?> sOplusActivityManagerClazz = findClass("android.app.OplusActivityManager");

    public static int OplusActivityManager_getRenderThreadTid(int i) {
        Class<?> cls = sOplusActivityManagerClazz;
        if (cls != null) {
            try {
                return ((Integer) getCachedMethod(cls, "getRenderThreadTid", Integer.TYPE).invoke(getOplusActivityManager(), Integer.valueOf(i))).intValue();
            } catch (Throwable unused) {
            }
        }
        return 0;
    }

    public static boolean OplusActivityManager_setInputConsumerEnabled(boolean z) {
        Class<?> cls = sOplusActivityManagerClazz;
        if (cls == null) {
            return false;
        }
        try {
            return ((Boolean) getCachedMethod(cls, "setInputConsumerEnabled", Boolean.TYPE).invoke(getOplusActivityManager(), Boolean.valueOf(z))).booleanValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static void OplusZoomTaskManager_onTransitionEnd(int i) {
        Class<?> cls = sZoomManagerClazz;
        if (cls == null) {
            return;
        }
        try {
            getCachedMethod(cls, "onTransitionEnd", Integer.TYPE).invoke(getZoomTaskManagerInternal(), Integer.valueOf(i));
        } catch (Throwable unused) {
        }
    }

    public static void OplusZoomTaskManager_onTransitionStart() {
        Class<?> cls = sZoomManagerClazz;
        if (cls == null) {
            return;
        }
        try {
            getCachedMethod(cls, "onTransitionStart", new Class[0]).invoke(getZoomTaskManagerInternal(), null);
        } catch (Throwable th) {
            LogD.e(TAG, "OplusZoomTaskManager_onTransitionStart error:" + th.getMessage());
        }
    }

    public static void OplusZoomTaskManager_onZoomRotateChanged(int i, int i2, OplusZoomTaskInfo oplusZoomTaskInfo) {
        Class<?> cls = sZoomManagerClazz;
        if (cls == null) {
            return;
        }
        try {
            Class cls2 = Integer.TYPE;
            getCachedMethod(cls, "onZoomRotateChanged", cls2, cls2, OplusZoomTaskInfo.class).invoke(getZoomTaskManagerInternal(), Integer.valueOf(i), Integer.valueOf(i2), oplusZoomTaskInfo);
        } catch (Throwable unused) {
        }
    }

    public static void OplusZoomTaskManager_onZoomStateChanged(OplusZoomTaskInfo oplusZoomTaskInfo) {
        Class<?> cls = sZoomManagerClazz;
        if (cls == null) {
            return;
        }
        try {
            getCachedMethod(cls, "onZoomStateChanged", OplusZoomTaskInfo.class).invoke(getZoomTaskManagerInternal(), oplusZoomTaskInfo);
        } catch (Throwable unused) {
        }
    }

    public static boolean OplusZoomTaskManager_registerZoomTaskListener(IOplusZoomTaskListener iOplusZoomTaskListener) {
        Class<?> cls = sZoomManagerClazz;
        if (cls == null) {
            return false;
        }
        try {
            return ((Boolean) getCachedMethod(cls, "registerZoomTaskListener", IOplusZoomTaskListener.class).invoke(getZoomTaskManagerInternal(), iOplusZoomTaskListener)).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static void OplusZoomTaskManager_requestChangeZoomTask(WindowContainerToken windowContainerToken, int i, boolean z) {
        Class<?> cls = sZoomManagerClazz;
        if (cls == null) {
            return;
        }
        try {
            getCachedMethod(cls, "requestChangeZoomTask", WindowContainerToken.class, Integer.TYPE, Boolean.TYPE).invoke(getZoomTaskManagerInternal(), windowContainerToken, Integer.valueOf(i), Boolean.valueOf(z));
        } catch (Throwable unused) {
        }
    }

    public static void OplusZoomTaskManager_setInputToken(WindowContainerToken windowContainerToken, IBinder iBinder) {
        Class<?> cls = sZoomManagerClazz;
        if (cls == null) {
            return;
        }
        try {
            getCachedMethod(cls, "setInputToken", WindowContainerToken.class, IBinder.class).invoke(getZoomTaskManagerInternal(), windowContainerToken, iBinder);
        } catch (Throwable unused) {
        }
    }

    public static boolean OplusZoomTaskManager_supportZoomTaskController() {
        Class<?> cls = sZoomManagerClazz;
        if (cls == null) {
            return false;
        }
        try {
            return ((Boolean) getCachedMethod(cls, "supportZoomTaskController", new Class[0]).invoke(getZoomTaskManagerInternal(), null)).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean OplusZoomTaskManager_unregisterZoomTaskListener(IOplusZoomTaskListener iOplusZoomTaskListener) {
        Class<?> cls = sZoomManagerClazz;
        if (cls == null) {
            return false;
        }
        try {
            return ((Boolean) getCachedMethod(cls, "unregisterZoomTaskListener", IOplusZoomTaskListener.class).invoke(getZoomTaskManagerInternal(), iOplusZoomTaskListener)).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    private static Class<?> findClass(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Method getCachedMethod(Class<?> cls, String str, Class<?>... clsArr) {
        String str2 = cls.getName() + "#" + str + Arrays.toString(clsArr);
        ConcurrentHashMap<String, Method> concurrentHashMap = sMethodCache;
        Method declaredMethod = concurrentHashMap.get(str2);
        if (declaredMethod != null) {
            return declaredMethod;
        }
        try {
            declaredMethod = cls.getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            concurrentHashMap.put(str2, declaredMethod);
            return declaredMethod;
        } catch (Exception unused) {
            LogD.e(TAG, "getCachedMethod error: " + cls.getName() + "#" + str);
            return declaredMethod;
        }
    }

    private static Object getOplusActivityManager() {
        if (sOplusActivityManager == null) {
            try {
                sOplusActivityManager = getCachedMethod(sOplusActivityManagerClazz, "getInstance", new Class[0]).invoke(null, null);
            } catch (Throwable th) {
                LogD.e(TAG, "getOplusActivityManager error: " + th.getMessage());
            }
        }
        return sOplusActivityManager;
    }

    private static Object getZoomTaskManagerInternal() {
        if (sZoomTaskManagerInternal == null) {
            try {
                sZoomTaskManagerInternal = getCachedMethod(sZoomManagerClazz, "getInstance", new Class[0]).invoke(null, null);
            } catch (Throwable th) {
                LogD.e(TAG, "getZoomTaskManagerInternal error: " + th.getMessage());
            }
        }
        return sZoomTaskManagerInternal;
    }
}

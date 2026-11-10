package com.oplusos.systemui.common.util;

import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ReflectionUtils {
    public static final Class[] EMPTY_CLASS_ARRAY = new Class[0];
    public static final ConcurrentHashMap CACHE_METHOD_MAP = new ConcurrentHashMap();
    public static final ConcurrentHashMap CACHE_FIELD_MAP = new ConcurrentHashMap();
    public static final ConcurrentHashMap CACHE_CONSTRUCTOR_MAP = new ConcurrentHashMap();

    public static Field getDeclaredField(Class cls, String str) throws NoSuchFieldException {
        String str2 = cls.getName() + "#" + str;
        ConcurrentHashMap concurrentHashMap = CACHE_FIELD_MAP;
        Field field = (Field) concurrentHashMap.get(str2);
        if (field != null) {
            return field;
        }
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);
        Field field2 = (Field) concurrentHashMap.putIfAbsent(str2, declaredField);
        if (field2 == null) {
            LogUtil.i("ReflectionUtils", "reflect cache miss field ".concat(str2));
        }
        return field2 != null ? field2 : declaredField;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Method getDeclaredMethod(Class cls, String str, Class... clsArr) throws NoSuchMethodException {
        String strMakeMemberKey = makeMemberKey(cls, str, clsArr);
        ConcurrentHashMap concurrentHashMap = CACHE_METHOD_MAP;
        Method method = (Method) concurrentHashMap.get(strMakeMemberKey);
        if (method != null) {
            return method;
        }
        if (clsArr == null) {
            clsArr = EMPTY_CLASS_ARRAY;
        }
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        declaredMethod.setAccessible(true);
        Method method2 = (Method) concurrentHashMap.putIfAbsent(strMakeMemberKey, declaredMethod);
        if (method2 == null) {
            LogUtil.i("ReflectionUtils", "reflect cache miss method ".concat(strMakeMemberKey));
        }
        return method2 != null ? method2 : declaredMethod;
    }

    public static Method getMethodWithParams(Class cls, Class... clsArr) {
        try {
            return getDeclaredMethod(cls, "getDisplaysBrightnessByNit", clsArr);
        } catch (NoSuchMethodException e) {
            Log.e("ReflectionUtils", "getMethodWithParams NoSuchMethod getDisplaysBrightnessByNit failed", e);
            return null;
        }
    }

    public static Object getValue(Class cls, Object obj, String str) {
        try {
            try {
                return getDeclaredField(cls, str).get(obj);
            } catch (IllegalAccessException unused) {
                Log.e("ReflectionUtils", "getValue IllegalAccess " + cls + " field " + str);
                return null;
            }
        } catch (NoSuchFieldException unused2) {
            Log.e("ReflectionUtils", "getValue NoSuchField " + cls + " field " + str);
            return null;
        }
    }

    public static String makeMemberKey(Class cls, String str, Class[] clsArr) {
        StringBuilder sb = new StringBuilder(cls.getName());
        sb.append('#');
        sb.append(str);
        if (clsArr == null) {
            clsArr = EMPTY_CLASS_ARRAY;
        }
        int length = clsArr.length;
        for (int i = 0; i < length; i++) {
            Class cls2 = clsArr[i];
            sb.append(',');
            sb.append(cls2 == null ? "null" : cls2.getName());
        }
        return sb.toString();
    }

    public static Object methodInvoke(Object obj, Class cls, String str, Class[] clsArr, Object... objArr) {
        if (obj != null && !TextUtils.isEmpty(str)) {
            if (clsArr != null) {
                try {
                    if (clsArr.length > 0) {
                        return getDeclaredMethod(cls, str, clsArr).invoke(obj, objArr);
                    }
                } catch (Exception e) {
                    Log.e("ReflectionUtils", cls + " methodInvoke " + str + " failed", e);
                }
            }
            return getDeclaredMethod(cls, str, new Class[0]).invoke(obj, null);
        }
        return null;
    }

    public static Object methodInvokeWithArgs(Object obj, Method method, Object... objArr) {
        try {
            method.setAccessible(true);
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            Log.e("ReflectionUtils", "methodInvokeWithArgs IllegalAccess " + method + " failed", e);
            return null;
        } catch (InvocationTargetException e2) {
            Log.e("ReflectionUtils", "methodInvokeWithArgs InvocationTarget " + method + " failed", e2);
            return null;
        }
    }

    public static Object newInstance(Class[] clsArr, Object... objArr) {
        try {
            Class<?> cls = Class.forName("com.oplus.wrapper.hardware.face.Face");
            String strMakeMemberKey = makeMemberKey(cls, "<init>", clsArr);
            ConcurrentHashMap concurrentHashMap = CACHE_CONSTRUCTOR_MAP;
            Constructor<?> constructor = (Constructor) concurrentHashMap.get(strMakeMemberKey);
            if (constructor == null) {
                constructor = cls.getConstructor(clsArr);
                constructor.setAccessible(true);
                Constructor<?> constructor2 = (Constructor) concurrentHashMap.putIfAbsent(strMakeMemberKey, constructor);
                if (constructor2 == null) {
                    LogUtil.i("ReflectionUtils", "reflect cache miss constructor ".concat(strMakeMemberKey));
                }
                if (constructor2 != null) {
                    constructor = constructor2;
                }
            }
            return constructor.newInstance(objArr);
        } catch (Exception e) {
            Log.e("ReflectionUtils", "new instance com.oplus.wrapper.hardware.face.Face failed.", e);
            return null;
        }
    }

    public static void setValue(Class cls, Object obj, String str, Object obj2) {
        try {
            try {
                getDeclaredField(cls, str).set(obj, obj2);
            } catch (IllegalAccessException unused) {
                Log.e("ReflectionUtils", "getValue IllegalAccess " + cls + " field " + str);
            }
        } catch (NoSuchFieldException unused2) {
            Log.e("ReflectionUtils", "getValue NoSuchField " + cls + " field " + str);
        }
    }

    public static Object staticMethodInvoke(String str, String str2, Class[] clsArr, Object... objArr) {
        try {
            Class<?> cls = Class.forName(str);
            return ((clsArr == null || clsArr.length <= 0) ? getDeclaredMethod(cls, str2, new Class[0]) : getDeclaredMethod(cls, str2, clsArr)).invoke(null, objArr);
        } catch (Exception e) {
            Log.e("ReflectionUtils", "staticMethodInvoke " + str2 + " failed", e);
            return null;
        }
    }

    public static Object methodInvoke(Object obj, String str, Class[] clsArr, Object... objArr) {
        if (obj == null) {
            return null;
        }
        if (clsArr != null) {
            try {
                if (clsArr.length > 0) {
                    return getDeclaredMethod(obj.getClass(), str, clsArr).invoke(obj, objArr);
                }
            } catch (Exception e) {
                Log.e("ReflectionUtils", "methodInvoke " + str + " failed", e);
                return null;
            }
        }
        return getDeclaredMethod(obj.getClass(), str, new Class[0]).invoke(obj, null);
    }

    public static Object methodInvoke(Object obj, String str) {
        return methodInvoke(obj, str, null, new Object[0]);
    }
}

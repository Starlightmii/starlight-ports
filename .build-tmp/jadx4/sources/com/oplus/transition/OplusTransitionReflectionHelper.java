package com.oplus.transition;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.SystemProperties;
import android.view.SurfaceControl;
import android.view.animation.Animation;
import android.window.TransitionInfo;
import android.window.WindowContainerTransaction;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.collection.FloatList$;
import com.android.wm.shell.transition.TransitionLog;
import com.android.wm.shell.util.ReflectionUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusTransitionReflectionHelper {
    private static final int ANIMATION_SUPRESSION_TIME = 1000;
    private static final String CLASS_WCT_EXTENDED = "android.window.OplusWCTExtendInfo";
    private static final String EXT = "mExt";
    private static final String FIELD_WINDOW_INFO_BUNDLE = "mWindowInfoBundle";
    private static final String GET_EXTENDED_INFO = "getExtendedInfo";
    private static final String GET_WCT_EXTENDED_INFO = "getWCTExtendInfo";
    private static final String SET_WCT_EXTENDED_INFO = "setWCTExtendInfo";
    private static final int SUPRESSION_ANIMATION = 2;
    private static boolean sGcIsSupression;
    private static volatile Method sGetExtendedInfoMethod;
    private static volatile Method sGetWctExtendInfoMethod;
    private static final boolean sIsSupressionFeatureEnabled;
    private static volatile Method sSetCornerRadiusExtMethod;
    private static volatile Method sSetWctExtendInfoMethod;
    private static volatile Field sTransitionInfoExtField;
    private static volatile Class<?> sVideoRotateClazz;
    private static volatile Constructor<?> sVideoRotateCtor;
    private static volatile Method sVideoRotateMethod;
    private static volatile Field sWctExtField;
    private static volatile Class<?> sWctExtendedClazz;
    private static volatile Constructor<?> sWctExtendedCtor;
    private static volatile Field sWindowInfoBundleField;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class VmRuntimeGcSupression {
        private static volatile Method sDesupressionMethod;
        private static volatile boolean sInitDone;
        private static volatile boolean sLoggedSupported;
        private static volatile boolean sLoggedUnavailable;
        private static volatile boolean sSupported;
        private static volatile Method sSupressionMethod;
        private static volatile Object sVmRuntime;

        private VmRuntimeGcSupression() {
        }

        public static void callDesupression(int i) {
            if (OplusTransitionReflectionHelper.sIsSupressionFeatureEnabled) {
                ensureInitialized();
                if (!sSupported || sDesupressionMethod == null || sVmRuntime == null) {
                    return;
                }
                try {
                    sDesupressionMethod.invoke(sVmRuntime, Integer.valueOf(i));
                } catch (Exception e) {
                    StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(i, "callGcDesupression failed type=", ", ");
                    sbM.append(e.getMessage());
                    TransitionLog.w(sbM.toString());
                }
            }
        }

        public static void callSupression(int i, int i2) {
            if (OplusTransitionReflectionHelper.sIsSupressionFeatureEnabled) {
                ensureInitialized();
                if (!sSupported || sSupressionMethod == null || sVmRuntime == null) {
                    return;
                }
                try {
                    sSupressionMethod.invoke(sVmRuntime, Integer.valueOf(i), Integer.valueOf(i2));
                } catch (Exception e) {
                    StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("callGcSupression failed type=", i, i2, " time=", ", ");
                    sbM.append(e.getMessage());
                    TransitionLog.w(sbM.toString());
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void ensureInitialized() {
            if (sInitDone) {
                return;
            }
            synchronized (VmRuntimeGcSupression.class) {
                if (sInitDone) {
                    return;
                }
                try {
                    try {
                        if (OplusTransitionReflectionHelper.sIsSupressionFeatureEnabled) {
                            Class[] clsArr = new Class[0];
                            sVmRuntime = Class.forName("dalvik.system.VMRuntime").getMethod("getRuntime", null).invoke(null, null);
                            if (sVmRuntime != null) {
                                Class<?> cls = sVmRuntime.getClass();
                                try {
                                    Class<?> cls2 = Integer.TYPE;
                                    sSupressionMethod = cls.getMethod("SupressionGC", cls2, cls2);
                                    sDesupressionMethod = cls.getMethod("DesupressionGC", cls2);
                                    sSupported = true;
                                    if (!sLoggedSupported) {
                                        sLoggedSupported = true;
                                        TransitionLog.debug("GC suppression supported (custom ART apex / domestic)");
                                    }
                                } catch (NoSuchMethodException e) {
                                    markUnavailable(e);
                                }
                                sInitDone = true;
                                return;
                            }
                            markUnavailable(new NoSuchMethodException("VMRuntime.getRuntime unavailable"));
                        }
                        sInitDone = true;
                    } catch (Exception e2) {
                        markUnavailable(e2);
                    }
                } catch (Throwable th) {
                    sInitDone = true;
                    throw th;
                }
            }
        }

        private static void markUnavailable(Exception exc) {
            sSupported = false;
            sSupressionMethod = null;
            sDesupressionMethod = null;
            if (sLoggedUnavailable) {
                return;
            }
            sLoggedUnavailable = true;
            TransitionLog.w("GC suppression not supported: VMRuntime.SupressionGC/DesupressionGC not found on this ART apex (export/Mainline has no Oplus natives). Domestic/custom ART apex required, or disable sys.gcsupression.optimize.enable. " + exc.getMessage());
        }
    }

    static {
        sIsSupressionFeatureEnabled = SystemProperties.getInt("sys.gcsupression.optimize.enable", 1) != 0;
        sGcIsSupression = false;
        warmUpGcSupression();
    }

    public static void callGcDesupression() {
        if (sIsSupressionFeatureEnabled && sGcIsSupression) {
            sGcIsSupression = false;
            VmRuntimeGcSupression.callDesupression(2);
        }
    }

    public static void callGcSupression() {
        if (!sIsSupressionFeatureEnabled || sGcIsSupression) {
            return;
        }
        sGcIsSupression = true;
        VmRuntimeGcSupression.callSupression(2, 1000);
    }

    private static Object getExtFromTransitionInfo(TransitionInfo transitionInfo) {
        if (transitionInfo == null) {
            return null;
        }
        try {
            Field declaredField = sTransitionInfoExtField;
            if (declaredField == null) {
                declaredField = transitionInfo.getClass().getDeclaredField(EXT);
                declaredField.setAccessible(true);
                sTransitionInfoExtField = declaredField;
            }
            Object obj = declaredField.get(transitionInfo);
            if (obj != null) {
                Method declaredMethod = sGetExtendedInfoMethod;
                if (declaredMethod == null) {
                    Class[] clsArr = new Class[0];
                    declaredMethod = obj.getClass().getDeclaredMethod(GET_EXTENDED_INFO, null);
                    declaredMethod.setAccessible(true);
                    sGetExtendedInfoMethod = declaredMethod;
                }
                return declaredMethod.invoke(obj, null);
            }
        } catch (Exception e) {
            TransitionLog.e(e, "getExtFromTransitionInfo error");
        }
        return null;
    }

    private static Object getExtInfoFromWCT(WindowContainerTransaction windowContainerTransaction) {
        if (windowContainerTransaction == null) {
            return null;
        }
        try {
            Field declaredField = sWctExtField;
            if (declaredField == null) {
                declaredField = windowContainerTransaction.getClass().getDeclaredField(EXT);
                declaredField.setAccessible(true);
                sWctExtField = declaredField;
            }
            Object obj = declaredField.get(windowContainerTransaction);
            if (obj != null) {
                Method declaredMethod = sGetWctExtendInfoMethod;
                if (declaredMethod == null) {
                    Class[] clsArr = new Class[0];
                    declaredMethod = obj.getClass().getDeclaredMethod(GET_WCT_EXTENDED_INFO, null);
                    declaredMethod.setAccessible(true);
                    sGetWctExtendInfoMethod = declaredMethod;
                }
                Object objInvoke = declaredMethod.invoke(obj, null);
                if (objInvoke == null) {
                    Class<?> cls = sWctExtendedClazz;
                    if (cls == null) {
                        cls = Class.forName(CLASS_WCT_EXTENDED);
                        sWctExtendedClazz = cls;
                    }
                    Constructor<?> declaredConstructor = sWctExtendedCtor;
                    if (declaredConstructor == null) {
                        Class[] clsArr2 = new Class[0];
                        declaredConstructor = cls.getDeclaredConstructor(null);
                        sWctExtendedCtor = declaredConstructor;
                    }
                    objInvoke = declaredConstructor.newInstance(null);
                    Method declaredMethod2 = sSetWctExtendInfoMethod;
                    if (declaredMethod2 == null) {
                        declaredMethod2 = obj.getClass().getDeclaredMethod(SET_WCT_EXTENDED_INFO, Object.class);
                        declaredMethod2.setAccessible(true);
                        sSetWctExtendInfoMethod = declaredMethod2;
                    }
                    declaredMethod2.invoke(obj, objInvoke);
                }
                return objInvoke;
            }
        } catch (Exception e) {
            TransitionLog.e(e, "getExtInfoFromWCT error");
        }
        return null;
    }

    public static Animation[] getSurfaceViewRotateAnimations(int i, int i2, int i3, int i4, int i5, Rect rect, Animation[] animationArr) {
        try {
            Class<?> cls = sVideoRotateClazz;
            if (cls == null) {
                cls = Class.forName("com.oplus.animation.OplusVideoRotateAnimation");
                sVideoRotateClazz = cls;
            }
            Constructor<?> declaredConstructor = sVideoRotateCtor;
            if (declaredConstructor == null) {
                Class[] clsArr = new Class[0];
                declaredConstructor = cls.getDeclaredConstructor(null);
                sVideoRotateCtor = declaredConstructor;
            }
            Object objNewInstance = declaredConstructor.newInstance(null);
            Method method = sVideoRotateMethod;
            if (method == null) {
                Class<?> cls2 = Integer.TYPE;
                method = cls.getMethod("getSurfaceViewRotateAnimations", cls2, cls2, cls2, cls2, cls2, Rect.class);
                sVideoRotateMethod = method;
            }
            Object objInvoke = method.invoke(objNewInstance, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), rect);
            return objInvoke instanceof Animation[] ? (Animation[]) objInvoke : animationArr;
        } catch (Exception e) {
            TransitionLog.e(e, "getSurfaceViewRotateAnimations error");
            return animationArr;
        }
    }

    public static Bundle getWindowInfoBundleFromChange(TransitionInfo.Change change) {
        if (change == null) {
            return null;
        }
        try {
            Field declaredField = sWindowInfoBundleField;
            if (declaredField == null) {
                declaredField = change.getClass().getDeclaredField(FIELD_WINDOW_INFO_BUNDLE);
                declaredField.setAccessible(true);
                sWindowInfoBundleField = declaredField;
            }
            return (Bundle) declaredField.get(change);
        } catch (Exception e) {
            TransitionLog.e(e, "getWindowInfoBundleFromChange e:");
            return null;
        }
    }

    public static <T> T methodInvokeOnExt(Object obj, String str, Class<?>[] clsArr, Object... objArr) {
        if (obj == null) {
            return null;
        }
        try {
            Object value = ReflectionUtils.getValue(obj.getClass(), obj, EXT);
            if (obj instanceof TransitionInfo) {
                value = getExtFromTransitionInfo((TransitionInfo) obj);
            } else if (obj instanceof WindowContainerTransaction) {
                value = getExtInfoFromWCT((WindowContainerTransaction) obj);
            }
            if (value != null) {
                return (T) ReflectionUtils.methodInvoke(value, value.getClass(), str, clsArr, objArr);
            }
        } catch (Exception e) {
            TransitionLog.e(e, "methodInvokeOnExt error");
        }
        return null;
    }

    public static void setCornerRadiusExt(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl, float f, float f2, int i) {
        try {
            Method declaredMethod = sSetCornerRadiusExtMethod;
            if (declaredMethod == null) {
                Class<?> cls = transaction.getClass();
                Class<?> cls2 = Float.TYPE;
                declaredMethod = cls.getDeclaredMethod("setCornerRadiusExt", SurfaceControl.class, cls2, cls2, Integer.TYPE);
                declaredMethod.setAccessible(true);
                sSetCornerRadiusExtMethod = declaredMethod;
            }
            declaredMethod.invoke(transaction, surfaceControl, Float.valueOf(f), Float.valueOf(f2), Integer.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void warmUpGcSupression() {
        if (sIsSupressionFeatureEnabled) {
            VmRuntimeGcSupression.ensureInitialized();
        }
    }
}

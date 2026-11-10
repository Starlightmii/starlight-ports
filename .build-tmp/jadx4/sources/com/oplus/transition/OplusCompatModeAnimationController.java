package com.oplus.transition;

import android.content.Context;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.window.TransitionInfo;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.wm.shell.R;
import com.android.wm.shell.transition.TransitionAnimationUtil;
import com.android.wm.shell.transition.TransitionLog;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusCompatModeAnimationController {
    private static final float ANIMATION_ALPHA = 1.0f;
    private static final int ANIMATION_DURATION = 350;
    private static final String EXT = "mExt";
    private static final String GET_EXTENDED_INFO = "getExtendedInfo";
    private static final String IN_OPLUS_COMPAT_MODE = "inOplusCompatMode";
    private static volatile Method sGetExtendedInfoMethod;
    private static volatile Method sInOplusCompatModeMethod;
    private static volatile Field sInfoExtField;
    private Context mContext;
    private volatile Method mRecentEndMethod;
    private volatile Method mRecentStartMethod;
    private final Class<?> mOplusCompatModeManagerClazz = findClass("com.oplus.compatmode.OplusCompatModeManager");
    private Object mOplusCompatModeManager = getOplusCompatModeManager();

    public OplusCompatModeAnimationController(Context context) {
        this.mContext = context;
    }

    private Class<?> findClass(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Object getOplusCompatModeManager() {
        Class<?> cls = this.mOplusCompatModeManagerClazz;
        if (cls != null) {
            try {
                Class[] clsArr = new Class[0];
                Method method = cls.getMethod("getInstance", null);
                method.setAccessible(true);
                return method.invoke(this.mOplusCompatModeManagerClazz, null);
            } catch (Exception e) {
                TransitionLog.e(e, "failure calling OplusCompactWindowManager getInstance");
            }
        }
        return null;
    }

    private int getTaskToBackAnim(boolean z, boolean z2) {
        if (z2) {
            return z ? R.anim.oplus_close_slide_enter : R.anim.oplus_close_slide_exit;
        }
        return z ? R.anim.oplus_task_close_slide_enter : R.anim.oplus_task_close_slide_exit;
    }

    private int getTaskToFrontAnim(boolean z, boolean z2) {
        if (z2) {
            return z ? R.anim.oplus_open_slide_enter : R.anim.oplus_open_slide_exit;
        }
        return z ? R.anim.oplus_task_open_slide_enter : R.anim.oplus_task_open_slide_exit;
    }

    public static boolean inOplusCompatMode(TransitionInfo transitionInfo) {
        if (transitionInfo != null) {
            try {
                Field declaredField = sInfoExtField;
                if (declaredField == null) {
                    declaredField = transitionInfo.getClass().getDeclaredField(EXT);
                    declaredField.setAccessible(true);
                    sInfoExtField = declaredField;
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
                    Object objInvoke = declaredMethod.invoke(obj, null);
                    if (objInvoke != null) {
                        Method declaredMethod2 = sInOplusCompatModeMethod;
                        if (declaredMethod2 == null) {
                            Class[] clsArr2 = new Class[0];
                            declaredMethod2 = objInvoke.getClass().getDeclaredMethod(IN_OPLUS_COMPAT_MODE, null);
                            sInOplusCompatModeMethod = declaredMethod2;
                        }
                        return ((Boolean) declaredMethod2.invoke(objInvoke, null)).booleanValue();
                    }
                }
            } catch (Exception e) {
                TransitionLog.e(e, "failure calling OplusTransitionExtendedInfo inOplusCompatMode");
            }
        }
        return false;
    }

    public Animation getOplusCompatModeAnimation(TransitionInfo transitionInfo, boolean z, boolean z2, boolean z3, int i) {
        TransitionInfo.AnimationOptions animationOptions;
        int taskToFrontAnim;
        if (!inOplusCompatMode(transitionInfo)) {
            return null;
        }
        Iterator it = transitionInfo.getChanges().iterator();
        while (true) {
            if (!it.hasNext()) {
                animationOptions = null;
                break;
            }
            TransitionInfo.Change change = (TransitionInfo.Change) it.next();
            if (change.getAnimationOptions() != null) {
                animationOptions = change.getAnimationOptions();
                break;
            }
        }
        boolean zIsSystemApp = TransitionAnimationUtil.isSystemApp(animationOptions != null ? animationOptions.getPackageName() : OplusAnimationController.PACKAGE_SYSTEM);
        int type = transitionInfo.getType();
        if (animationOptions == null && !z2) {
            TransitionLog.w("getOplusCompatModeAnimation: app windowAnimationStyle null");
            return null;
        }
        if (z3) {
            return null;
        }
        int i2 = 0;
        if (type != 1) {
            if (type != 2) {
                taskToFrontAnim = 0;
            } else if (z2) {
                taskToFrontAnim = getTaskToBackAnim(z, zIsSystemApp);
            } else {
                taskToFrontAnim = z ? R.anim.oplus_close_slide_enter : R.anim.oplus_close_slide_exit;
            }
        } else if (z2) {
            taskToFrontAnim = getTaskToFrontAnim(z, zIsSystemApp);
        } else {
            taskToFrontAnim = z ? R.anim.oplus_open_slide_enter : R.anim.oplus_open_slide_exit;
        }
        if (i == 3) {
            i2 = z ? R.anim.oplus_wallpaper_close_enter2 : R.anim.oplus_wallpaper_close_exit2;
        } else if (i == 0) {
            i2 = taskToFrontAnim;
        }
        if (i2 == 0) {
            return null;
        }
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("load oplus compat mode transition, type: ", type, ", isTask: ", " , wallpaperTransit: ", z2);
        sbM.append(i);
        TransitionLog.always(sbM.toString());
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.mContext, i2);
        if ((type != 1 || z) && !(type == 2 && z)) {
            return animationLoadAnimation;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 1.0f);
        alphaAnimation.setDuration(350L);
        return alphaAnimation;
    }

    public void onRecentAnimationEnd() {
        try {
            if (this.mOplusCompatModeManagerClazz == null || this.mOplusCompatModeManager == null) {
                return;
            }
            Method method = this.mRecentEndMethod;
            if (method == null) {
                Class[] clsArr = new Class[0];
                method = this.mOplusCompatModeManagerClazz.getMethod("onRecentAnimationEnd", null);
                method.setAccessible(true);
                this.mRecentEndMethod = method;
            }
            method.invoke(this.mOplusCompatModeManager, null);
        } catch (Exception e) {
            TransitionLog.e(e, "failure calling OplusCompactWindowManager onRecentAnimationEnd");
        }
    }

    public void onRecentAnimationStart() {
        try {
            if (this.mOplusCompatModeManagerClazz == null || this.mOplusCompatModeManager == null) {
                return;
            }
            Method method = this.mRecentStartMethod;
            if (method == null) {
                Class[] clsArr = new Class[0];
                method = this.mOplusCompatModeManagerClazz.getMethod("onRecentAnimationStart", null);
                method.setAccessible(true);
                this.mRecentStartMethod = method;
            }
            method.invoke(this.mOplusCompatModeManager, null);
        } catch (Exception e) {
            TransitionLog.e(e, "failure calling OplusCompactWindowManager onRecentAnimationStart");
        }
    }
}

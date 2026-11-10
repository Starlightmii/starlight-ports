package com.oplus.transition;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.res.ResourceId;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemProperties;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ClipRectAnimation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.window.TransitionInfo;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.animation.DefaultTransitionHelper$;
import com.android.wm.shell.flexibletask.FlexibleTaskTransitionUtils;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.startingsurface.OplusShellStartingWindowUtils;
import com.android.wm.shell.transition.AdaptiveSmoothShellAnimManager;
import com.android.wm.shell.transition.DefaultTransitionHandler;
import com.android.wm.shell.transition.TransitionAnimationUtil;
import com.android.wm.shell.transition.TransitionLog;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.util.ReflectionUtils;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplus.flexibletask.utils.FlexibleUtils;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ShellContinuousTransitionUtils {
    private static final float ALPHA_0 = 0.0f;
    private static final float ALPHA_1 = 1.0f;
    public static final int ANIMATION_LEVEL_LIGHT_LOW = 4;
    public static final int ANIMATION_LEVEL_UNSET = 0;
    public static final boolean DEBUG_ADAPTIVE_SMOOTH_ANIM_PROP;
    public static final boolean DEBUG_LITE_OS_NAV_BLOCKABLE_ANIM;
    public static final boolean DEBUG_NAV_BLOCKABLE_ANIM;
    public static final float DEFAULT_CORNER_RADIUS_VALUE = 0.0f;
    public static final boolean ENABLE_ACTIVITY_OR_TASK_SECONDARY_CONTINUOUS;
    public static final boolean ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION;
    public static final boolean ENABLE_CONTINUOUS_REMOTE;
    public static final boolean ENABLE_FULL_SCENE_CONTINUOUS;
    public static final boolean ENABLE_NAV_INTEGRATION;
    public static final boolean ENABLE_PREDICTIVE_CONTINUOUS_REMOTE;
    public static final boolean ENABLE_REMOTE_TO_REMOTE;
    public static final int FLAG_SECONDARY_CONTINUOUS_FUN_CLOSE = 2;
    public static final int FLAG_SECONDARY_CONTINUOUS_IN_BLACK_LIST = 4;
    public static final int FLAG_SECONDARY_CONTINUOUS_IN_SPLIT_MODE = 32;
    private static final String GET_EXT_FLAGS = "getFlags";
    public static final String GET_IS_FROM_HOME = "getIsFromHome";
    private static final String GET_LAUNCH_UNITY_START = "getLaunchUnityStart";
    private static final ThreadLocal<Matrix> IDENTITY_MATRIX_CACHE;
    private static final List<Class<?>> INVALID_ANIMATION_CLASSES;
    private static final String IS_START_FROM_SHORTCUT = "isStartFromShortcut";
    public static final String LAUNCHER_CMP = "com.android.launcher/.Launcher";
    public static final String LAUNCHER_DEBUG_NAME = "QuickstepLaunch";
    public static final String LAUNCHER_KEY_MODE_DEBUG_NAME = "QuickstepLaunchHome";
    public static final boolean LIGHT_OS_ANIM_LEVEL_LOW;
    public static final boolean LOG_DEBUG;
    private static final ThreadLocal<float[]> MATRIX_VALUES_CACHE;
    private static final int MATRIX_VALUES_LENGTH = 9;
    public static final String OPLUS_ANIMATION_LEVEL_PROP = "persist.sys.oplus.anim_level";
    public static final String OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM = "oplus.software.adaptive_smooth_animation";
    private static final String SET_LAUNCH_UNITY_START = "setLaunchUnityStart";
    public static final String SKIP_DEFAULT_CONTINUE_TRANSFORMATION = "skipDefaultContinueTransformation";
    public static final List<String> SKIP_DEFAULT_CONTINUE_TRANSFORMATION_ACTIVITIES;
    public static final String SYSUI_DEBUG_NAME = "SysUILaunch";
    public static final int S_ANIMATION_LEVEL;
    public static final int TRANSFORM_INDEX_CLOSE_TARGET = 0;
    public static final int TRANSFORM_INDEX_OPEN_TARGET = 1;
    private static final int TRANSITION_CHANGE_MODE_COUNT = 2;
    private static final int WINDOWING_MODE_COMPACTWINDOW = 120;

    static {
        boolean z = SystemProperties.getBoolean("persist.wm.debug.light_os_smooth_anim", false);
        DEBUG_ADAPTIVE_SMOOTH_ANIM_PROP = z;
        boolean z2 = SystemProperties.getBoolean("persist.wm.debug.nav_blockable_animation_lite_os", true);
        DEBUG_LITE_OS_NAV_BLOCKABLE_ANIM = z2;
        boolean z3 = z || OplusFeatureConfigManager.getInstance().hasFeature(OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM);
        ENABLE_ADAPTIVE_SMOOTH_ANIM_TRANSITION = z3;
        boolean z4 = SystemProperties.getBoolean("persist.wm.debug.nav_blockable_animation", true);
        DEBUG_NAV_BLOCKABLE_ANIM = z4;
        ENABLE_NAV_INTEGRATION = SystemProperties.getBoolean("persist.wm.debug.nav_integration", true) && (OplusShellStartingWindowUtils.HIGH_PERFORMANCE_ANIM_LEVEL || z2);
        ENABLE_REMOTE_TO_REMOTE = z3 && z4 && (OplusShellStartingWindowUtils.HIGH_PERFORMANCE_ANIM_LEVEL || z2);
        LOG_DEBUG = SystemProperties.getBoolean("persist.sys.assert.panic", false) || SystemProperties.getBoolean("persist.sys.alwayson.enable", false);
        ENABLE_PREDICTIVE_CONTINUOUS_REMOTE = SystemProperties.getBoolean("persist.wm.enable.predictive.continuous", true);
        ENABLE_CONTINUOUS_REMOTE = SystemProperties.getBoolean("interrupt_remote_animation", true);
        int i = SystemProperties.getInt(OPLUS_ANIMATION_LEVEL_PROP, 0);
        S_ANIMATION_LEVEL = i;
        boolean z5 = i == 4;
        LIGHT_OS_ANIM_LEVEL_LOW = z5;
        ENABLE_FULL_SCENE_CONTINUOUS = SystemProperties.getBoolean("persist.full_scene_continuous.enable", true) && !z5;
        ENABLE_ACTIVITY_OR_TASK_SECONDARY_CONTINUOUS = SystemProperties.getBoolean("persist.transition.activity_or_task_secondary.enable", true);
        SKIP_DEFAULT_CONTINUE_TRANSFORMATION_ACTIVITIES = Arrays.asList("com.tencent.mm.ui.chatting.AppAttachNewDownloadUI");
        final int i2 = 0;
        MATRIX_VALUES_CACHE = ThreadLocal.withInitial(new Supplier() { // from class: com.oplus.transition.ShellContinuousTransitionUtils$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                switch (i2) {
                    case 0:
                        return ShellContinuousTransitionUtils.lambda$static$0();
                    default:
                        return new Matrix();
                }
            }
        });
        final int i3 = 1;
        IDENTITY_MATRIX_CACHE = ThreadLocal.withInitial(new Supplier() { // from class: com.oplus.transition.ShellContinuousTransitionUtils$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                switch (i3) {
                    case 0:
                        return ShellContinuousTransitionUtils.lambda$static$0();
                    default:
                        return new Matrix();
                }
            }
        });
        INVALID_ANIMATION_CLASSES = Arrays.asList(ScaleAnimation.class, RotateAnimation.class, ClipRectAnimation.class);
    }

    public static boolean checkHasAlphaChange(AlphaAnimation alphaAnimation) {
        if (alphaAnimation == null) {
            return false;
        }
        Object value = ReflectionUtils.getValue(AlphaAnimation.class, alphaAnimation, "mFromAlpha");
        Object value2 = ReflectionUtils.getValue(AlphaAnimation.class, alphaAnimation, "mToAlpha");
        return (value != null ? ((Float) value).floatValue() : 1.0f) - (value2 != null ? ((Float) value2).floatValue() : 1.0f) != 0.0f;
    }

    public static boolean checkHasXTranslateOnly(Animation animation) {
        if (animation != null && !(animation instanceof AlphaAnimation)) {
            Stream<Class<?>> stream = INVALID_ANIMATION_CLASSES.stream();
            ShellContinuousTransitionUtils$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new ShellContinuousTransitionUtils$.ExternalSyntheticLambda0(0);
            externalSyntheticLambda0.f$0 = animation;
            VarHandle.storeStoreFence();
            if (!stream.anyMatch(externalSyntheticLambda0)) {
                if ((animation instanceof TranslateAnimation) && !hasXTranslateOnly((TranslateAnimation) animation)) {
                    return false;
                }
                List<Animation> animations = animation instanceof AnimationSet ? ((AnimationSet) animation).getAnimations() : null;
                TransitionLog.always("checkHasXTranslateOnly, animList = " + animations);
                for (int i = 0; animations != null && i < animations.size(); i++) {
                    Animation animation2 = animations.get(i);
                    if (!(animation2 instanceof AlphaAnimation) || animations.size() != 1) {
                        Stream<Class<?>> stream2 = INVALID_ANIMATION_CLASSES.stream();
                        ShellContinuousTransitionUtils$.ExternalSyntheticLambda0 externalSyntheticLambda1 = new ShellContinuousTransitionUtils$.ExternalSyntheticLambda0(1);
                        externalSyntheticLambda1.f$0 = animation2;
                        VarHandle.storeStoreFence();
                        if (!stream2.anyMatch(externalSyntheticLambda1)) {
                            if ((animation2 instanceof TranslateAnimation) && !hasXTranslateOnly((TranslateAnimation) animation2)) {
                                return false;
                            }
                        }
                    }
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public static ComponentName getComponentNameByMode(int i, TransitionInfo transitionInfo) {
        for (int i2 = 0; i2 < transitionInfo.getChanges().size(); i2++) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(i2);
            if (change != null && change.getMode() == i) {
                return TransitionAnimationUtil.getComponentNameFromChange(change);
            }
        }
        return null;
    }

    public static float getContinueCornerRadius(TransitionInfo transitionInfo, ComponentName componentName, TransitionInfo.Change change) {
        ConcurrentHashMap backAnimContinueCornerRadiusMap;
        if (change != null && (backAnimContinueCornerRadiusMap = Transitions.getBackAnimContinueCornerRadiusMap()) != null && !backAnimContinueCornerRadiusMap.isEmpty()) {
            float fFloatValue = ((Float) backAnimContinueCornerRadiusMap.getOrDefault(TransitionAnimationUtil.getMapKeyByComponentAndMode(componentName, !TransitionUtil.isClosingMode(change.getMode()) ? 1 : 0), Float.valueOf(0.0f))).floatValue();
            if (fFloatValue > 0.0f && needRoundCorner(transitionInfo, componentName)) {
                return fFloatValue;
            }
        }
        return 0.0f;
    }

    public static int getFlags(TransitionInfo transitionInfo) {
        Integer num = (Integer) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_EXT_FLAGS, null, new Object[0]);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public static int getModeByComponentName(TransitionInfo transitionInfo, ComponentName componentName) {
        ComponentName componentNameFromChange;
        if (componentName == null) {
            return 0;
        }
        for (int i = 0; i < transitionInfo.getChanges().size(); i++) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(i);
            if (change != null && (componentNameFromChange = TransitionAnimationUtil.getComponentNameFromChange(change)) != null && componentNameFromChange.equals(componentName)) {
                return change.getMode();
            }
        }
        return 0;
    }

    public static TransitionInfo.Change getReadyChangeBasedOnActiveChange(TransitionInfo transitionInfo, TransitionInfo.Change change) {
        ComponentName componentNameFromChange;
        if (transitionInfo == null || change == null || (componentNameFromChange = TransitionAnimationUtil.getComponentNameFromChange(change)) == null) {
            return null;
        }
        for (TransitionInfo.Change change2 : transitionInfo.getChanges()) {
            if (componentNameFromChange.equals(TransitionAnimationUtil.getComponentNameFromChange(change2)) && isChangeModeOpposite(change2, change)) {
                return change2;
            }
        }
        return null;
    }

    public static boolean hasAlphaChange(Animation animation) {
        if (animation == null) {
            return false;
        }
        if (animation instanceof AlphaAnimation) {
            return checkHasAlphaChange((AlphaAnimation) animation);
        }
        if (animation instanceof AnimationSet) {
            List<Animation> animations = ((AnimationSet) animation).getAnimations();
            for (int i = 0; animations != null && i < animations.size(); i++) {
                Animation animation2 = animations.get(i);
                if ((animation2 instanceof AlphaAnimation) && checkHasAlphaChange((AlphaAnimation) animation2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasRotationLeash(TransitionInfo.Change change) {
        Bundle windowInfoBundleFromChange;
        return (change == null || (windowInfoBundleFromChange = OplusTransitionReflectionHelper.getWindowInfoBundleFromChange(change)) == null || !windowInfoBundleFromChange.getBoolean("has-fix-rotation-leash", false)) ? false : true;
    }

    public static boolean hasXTranslateOnly(TranslateAnimation translateAnimation) {
        if (translateAnimation == null) {
            return false;
        }
        Object value = ReflectionUtils.getValue(TranslateAnimation.class, translateAnimation, "mFromXValue");
        Object value2 = ReflectionUtils.getValue(TranslateAnimation.class, translateAnimation, "mToXValue");
        Object value3 = ReflectionUtils.getValue(TranslateAnimation.class, translateAnimation, "mFromYValue");
        Object value4 = ReflectionUtils.getValue(TranslateAnimation.class, translateAnimation, "mToYValue");
        float fFloatValue = value != null ? ((Float) value).floatValue() : 0.0f;
        float fFloatValue2 = value3 != null ? ((Float) value3).floatValue() : 0.0f;
        return (((fFloatValue - (value2 != null ? ((Float) value2).floatValue() : 0.0f)) > 0.0f ? 1 : ((fFloatValue - (value2 != null ? ((Float) value2).floatValue() : 0.0f)) == 0.0f ? 0 : -1)) != 0) && !(((fFloatValue2 - (value4 != null ? ((Float) value4).floatValue() : 0.0f)) > 0.0f ? 1 : ((fFloatValue2 - (value4 != null ? ((Float) value4).floatValue() : 0.0f)) == 0.0f ? 0 : -1)) != 0);
    }

    public static boolean isActivitySecondaryTransition(TransitionInfo transitionInfo) {
        return transitionInfo != null && (transitionInfo.getType() == 1 || transitionInfo.getType() == 2) && transitionInfo.getChanges().size() == 2 && !isInValidTransitResourcePair(((TransitionInfo.Change) transitionInfo.getChanges().get(0)).getAnimationOptions()) && DefaultTransitionHandler.isActivityLevelOnly(transitionInfo) && !isNotTranslateAnimation(transitionInfo) && !hasRotationLeash((TransitionInfo.Change) transitionInfo.getChanges().get(0));
    }

    public static boolean isActivitySecondaryTransitionPair(TransitionInfo transitionInfo, TransitionInfo transitionInfo2) {
        return isActivitySecondaryTransition(transitionInfo) && isActivitySecondaryTransition(transitionInfo2) && isTransitionTypeOpposite(transitionInfo, transitionInfo2);
    }

    public static boolean isChangeModeOpposite(TransitionInfo.Change change, TransitionInfo.Change change2) {
        if (TransitionUtil.isOpeningMode(change.getMode()) && TransitionUtil.isClosingMode(change2.getMode())) {
            return true;
        }
        return TransitionUtil.isClosingMode(change.getMode()) && TransitionUtil.isOpeningMode(change2.getMode());
    }

    public static boolean isInNotSupportMode(TransitionInfo transitionInfo) {
        TransitionInfo.Change change;
        if (transitionInfo == null || !((transitionInfo.getType() == 1 || transitionInfo.getType() == 2) && transitionInfo.getChanges().size() == 2 && (getFlags(transitionInfo) & 32) == 0 && (change = (TransitionInfo.Change) transitionInfo.getChanges().get(0)) != null)) {
            return true;
        }
        Bundle flexibleTaskBundleFromChange = FlexibleTaskTransitionUtils.getFlexibleTaskBundleFromChange(change);
        return FlexibleTaskTransitionUtils.isFlexibleTask(flexibleTaskBundleFromChange) || FlexibleTransitionUtils.isFlexibleActivityTransition(flexibleTaskBundleFromChange) || OplusCompatModeAnimationController.inOplusCompatMode(transitionInfo);
    }

    private static boolean isInValidTransitResourcePair(TransitionInfo.AnimationOptions animationOptions) {
        if (animationOptions == null || animationOptions.getType() != 1) {
            return false;
        }
        return (ResourceId.isValid(animationOptions.getEnterResId()) && ResourceId.isValid(animationOptions.getExitResId())) ? false : true;
    }

    public static boolean isKeyguardAppearTransition(TransitionInfo transitionInfo) {
        if (transitionInfo == null) {
            return false;
        }
        return (transitionInfo.getType() == 3 || transitionInfo.getType() == 1) && !((transitionInfo.getFlags() & 64) == 0 && (transitionInfo.getFlags() & OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT) == 0);
    }

    public static boolean isLaunchUnityStart(TransitionInfo transitionInfo, TransitionInfo transitionInfo2) {
        if (ENABLE_NAV_INTEGRATION) {
            return false;
        }
        return isLaunchUnityStartOnly(transitionInfo, transitionInfo2);
    }

    public static boolean isLaunchUnityStartOnly(TransitionInfo transitionInfo, TransitionInfo transitionInfo2) {
        return (transitionInfo != null ? ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_LAUNCH_UNITY_START, null, new Object[0])).booleanValue() : false) || (transitionInfo2 != null ? ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo2, GET_LAUNCH_UNITY_START, null, new Object[0])).booleanValue() : false);
    }

    public static boolean isNotTranslateAnimation(TransitionInfo transitionInfo) {
        if (transitionInfo != null && transitionInfo.getChanges().size() == 2 && transitionInfo.getChanges().get(0) != null) {
            int type = transitionInfo.getType();
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(0);
            TransitionInfo.AnimationOptions animationOptions = change.getAnimationOptions();
            if (animationOptions != null && animationOptions.getType() == 1) {
                AdaptiveSmoothShellAnimManager adaptiveSmoothShellAnimManager = AdaptiveSmoothShellAnimManager.getInstance();
                if (adaptiveSmoothShellAnimManager.canCustomizeAppTransition(transitionInfo, animationOptions, type, change)) {
                    return adaptiveSmoothShellAnimManager.hookCustomAnimation(animationOptions, type, TransitionUtil.isOpeningType(change.getMode())) instanceof AlphaAnimation;
                }
                return false;
            }
        }
        return false;
    }

    public static boolean isPredictiveBackAnimation(TransitionInfo transitionInfo) {
        if (ENABLE_PREDICTIVE_CONTINUOUS_REMOTE && !isLaunchUnityStartOnly(null, transitionInfo)) {
            return transitionInfo.getType() == 13 || !TransitionUtil.isNotGestureBackTransition(transitionInfo);
        }
        return false;
    }

    public static boolean isPredictiveToBackAnimation(TransitionInfo transitionInfo) {
        if (ENABLE_PREDICTIVE_CONTINUOUS_REMOTE && !isLaunchUnityStartOnly(null, transitionInfo) && transitionInfo != null && transitionInfo.getType() == 13 && transitionInfo.getChanges().size() == 2) {
            boolean z = false;
            boolean z2 = false;
            for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM >= 0; iM--) {
                TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(iM);
                if (!change.hasFlags(131072) || change.getTaskInfo() == null || change.getTaskInfo().topActivity == null) {
                    return false;
                }
                if (change.getMode() == 3 && LAUNCHER_CMP.equals(change.getTaskInfo().topActivity.flattenToShortString())) {
                    z = true;
                } else if (change.getMode() == 6 || change.getMode() == 4) {
                    z2 = true;
                }
            }
            if (z && z2) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRemoteOpenFromLauncher(TransitionInfo transitionInfo, IBinder iBinder) {
        Boolean bool;
        if (transitionInfo == null || (bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_IS_FROM_HOME, null, new Object[0])) == null || !bool.booleanValue()) {
            return false;
        }
        return AdaptiveSmoothShellAnimManager.getInstance().isRemoteFrom(iBinder, transitionInfo, LAUNCHER_DEBUG_NAME) || AdaptiveSmoothShellAnimManager.getInstance().isRemoteFrom(iBinder, transitionInfo, LAUNCHER_KEY_MODE_DEBUG_NAME);
    }

    public static boolean isRemoteOpenFromSystemUI(TransitionInfo transitionInfo, IBinder iBinder) {
        Boolean bool;
        if (transitionInfo == null || !TransitionUtil.isOpeningType(transitionInfo.getType()) || (bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_IS_FROM_HOME, null, new Object[0])) == null || bool.booleanValue()) {
            return false;
        }
        return AdaptiveSmoothShellAnimManager.getInstance().isRemoteFrom(iBinder, transitionInfo, SYSUI_DEBUG_NAME);
    }

    public static boolean isRemoteToHomeNotFromLauncher(TransitionInfo transitionInfo, IBinder iBinder) {
        Boolean bool;
        if (transitionInfo != null && (bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_IS_FROM_HOME, null, new Object[0])) != null && !bool.booleanValue()) {
            List changes = transitionInfo.getChanges();
            boolean z = false;
            boolean z2 = false;
            for (int size = changes.size() - 1; size >= 0; size--) {
                TransitionInfo.Change change = (TransitionInfo.Change) changes.get(size);
                ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
                if (taskInfo != null) {
                    if (taskInfo.getActivityType() == 2 && TransitionUtil.isOpeningMode(change.getMode())) {
                        z = true;
                    } else if (TransitionUtil.isClosingMode(change.getMode()) && taskInfo.getWindowingMode() == 1 && !FlexibleUtils.isFlexibleTaskAndHasCaption(taskInfo)) {
                        z2 = true;
                    }
                }
            }
            if (z && z2) {
                return AdaptiveSmoothShellAnimManager.getInstance().isRemoteFrom(iBinder, transitionInfo, LAUNCHER_DEBUG_NAME) || AdaptiveSmoothShellAnimManager.getInstance().isRemoteFrom(iBinder, transitionInfo, LAUNCHER_KEY_MODE_DEBUG_NAME);
            }
        }
        return false;
    }

    public static boolean isSameComponent(TransitionInfo transitionInfo, TransitionInfo transitionInfo2, boolean z) {
        ComponentName componentNameFromChange = TransitionAnimationUtil.getComponentNameFromChange((TransitionInfo.Change) transitionInfo.getChanges().get(0));
        ComponentName componentNameFromChange2 = TransitionAnimationUtil.getComponentNameFromChange((TransitionInfo.Change) transitionInfo.getChanges().get(1));
        ComponentName componentNameFromChange3 = TransitionAnimationUtil.getComponentNameFromChange((TransitionInfo.Change) transitionInfo2.getChanges().get(0));
        ComponentName componentNameFromChange4 = TransitionAnimationUtil.getComponentNameFromChange((TransitionInfo.Change) transitionInfo2.getChanges().get(1));
        TransitionLog.debug("isSameComponent readyInfo.type = " + transitionInfo2.getType() + " isTaskLevel = " + z + " first_mode = " + ((TransitionInfo.Change) transitionInfo2.getChanges().get(0)).getMode() + " first_cmp = " + componentNameFromChange3 + " second_mode = " + ((TransitionInfo.Change) transitionInfo2.getChanges().get(1)).getMode() + " second_cmp = " + componentNameFromChange4);
        if (componentNameFromChange != null && componentNameFromChange2 != null && componentNameFromChange3 != null && componentNameFromChange4 != null) {
            if (z && componentNameFromChange.equals(componentNameFromChange4) && componentNameFromChange2.equals(componentNameFromChange3)) {
                return true;
            }
            if (componentNameFromChange.equals(componentNameFromChange3) && componentNameFromChange2.equals(componentNameFromChange4)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSecondaryClosingType(TransitionInfo transitionInfo) {
        if (transitionInfo == null) {
            return false;
        }
        return transitionInfo.getType() == 2 || transitionInfo.getType() == 4;
    }

    public static boolean isSecondaryOpeningType(TransitionInfo transitionInfo) {
        if (transitionInfo == null) {
            return false;
        }
        return transitionInfo.getType() == 1 || transitionInfo.getType() == 3;
    }

    public static boolean isStartFromRecentsSlide(TransitionInfo transitionInfo) {
        if (transitionInfo == null) {
            return false;
        }
        Integer num = (Integer) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, OplusAnimationController.GET_TRANSITION_LAUNCH_FROM, null, new Object[0]);
        TransitionLog.always(" is start from recents slide, startFrom: " + num);
        Integer num2 = 9;
        return num2.equals(num);
    }

    public static boolean isStartFromShorcut(TransitionInfo transitionInfo) {
        if (transitionInfo == null) {
            return false;
        }
        return ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, IS_START_FROM_SHORTCUT, null, new Object[0])).booleanValue();
    }

    public static boolean isTaskFragmentSecondaryTransition(TransitionInfo transitionInfo) {
        return transitionInfo != null && (transitionInfo.getType() == 1 || transitionInfo.getType() == 2) && transitionInfo.getChanges().size() == 2 && isTaskFragmentTransition(transitionInfo);
    }

    public static boolean isTaskFragmentSecondaryTransitionPair(TransitionInfo transitionInfo, TransitionInfo transitionInfo2) {
        return isTaskFragmentSecondaryTransition(transitionInfo) && isTaskFragmentSecondaryTransition(transitionInfo2) && isTransitionTypeOpposite(transitionInfo, transitionInfo2);
    }

    private static boolean isTaskFragmentTransition(TransitionInfo transitionInfo) {
        for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM >= 0; iM--) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(iM);
            if (change.getTaskFragmentToken() == null || !change.hasFlags(1536)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isTaskSecondaryTransition(TransitionInfo transitionInfo) {
        return transitionInfo != null && (transitionInfo.getType() == 1 || transitionInfo.getType() == 4 || transitionInfo.getType() == 2) && transitionInfo.getChanges().size() == 2 && DefaultTransitionHandler.isTaskTransition(transitionInfo);
    }

    public static boolean isTaskSecondaryTransitionPair(TransitionInfo transitionInfo, TransitionInfo transitionInfo2) {
        return isTaskSecondaryTransition(transitionInfo) && isTaskSecondaryTransition(transitionInfo2) && isTransitionTypeOpposite(transitionInfo, transitionInfo2);
    }

    public static boolean isTaskTransitionPairIgnoreWallpaper(TransitionInfo transitionInfo, TransitionInfo transitionInfo2) {
        return TransitionAnimationUtil.isTaskLevelTransition(transitionInfo) && TransitionAnimationUtil.isTaskLevelTransition(transitionInfo2) && isTransitionTypeOpposite(transitionInfo, transitionInfo2);
    }

    public static boolean isTransitionTypeOpposite(TransitionInfo transitionInfo, TransitionInfo transitionInfo2) {
        if (isSecondaryOpeningType(transitionInfo) && isSecondaryClosingType(transitionInfo2)) {
            return true;
        }
        return isSecondaryClosingType(transitionInfo) && isSecondaryOpeningType(transitionInfo2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$checkHasXTranslateOnly$1(Animation animation, Class cls) {
        return cls.isInstance(animation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$checkHasXTranslateOnly$2(Animation animation, Class cls) {
        return cls.isInstance(animation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float[] lambda$static$0() {
        return new float[9];
    }

    public static boolean matchAnimationMatrix(Matrix matrix) {
        if (matrix == null) {
            return false;
        }
        float[] fArr = MATRIX_VALUES_CACHE.get();
        matrix.getValues(fArr);
        Matrix matrix2 = IDENTITY_MATRIX_CACHE.get();
        matrix2.reset();
        matrix2.setTranslate(fArr[2], 0.0f);
        TransitionLog.debug("animMatrix = " + matrix + " identityMatrix = " + matrix2);
        return matrix.equals(matrix2);
    }

    public static boolean matchReverseContinuous(TransitionInfo transitionInfo, TransitionInfo transitionInfo2) {
        boolean zIsActivitySecondaryTransitionPair = isActivitySecondaryTransitionPair(transitionInfo, transitionInfo2);
        boolean zIsTaskSecondaryTransitionPair = isTaskSecondaryTransitionPair(transitionInfo, transitionInfo2);
        boolean zIsTaskFragmentSecondaryTransitionPair = isTaskFragmentSecondaryTransitionPair(transitionInfo, transitionInfo2);
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("matchReverseContinuous isMatchActivitySecondary = ", " isMatchTaskSecondary = ", " isMatchTaskFragmentSecondary = ", zIsActivitySecondaryTransitionPair, zIsTaskSecondaryTransitionPair);
        sbM.append(zIsTaskFragmentSecondaryTransitionPair);
        TransitionLog.always(sbM.toString());
        return (zIsActivitySecondaryTransitionPair || zIsTaskSecondaryTransitionPair || zIsTaskFragmentSecondaryTransitionPair) && isSameComponent(transitionInfo, transitionInfo2, zIsTaskSecondaryTransitionPair);
    }

    private static boolean needRoundCorner(TransitionInfo transitionInfo, ComponentName componentName) {
        int modeByComponentName = getModeByComponentName(transitionInfo, componentName);
        if (!TransitionAnimationUtil.isSystemApp(componentName != null ? componentName.getPackageName() : null)) {
            return false;
        }
        int type = transitionInfo.getType();
        if (TransitionUtil.isOpeningType(type) && TransitionUtil.isOpeningMode(modeByComponentName)) {
            return true;
        }
        return TransitionUtil.isClosingType(type) && TransitionUtil.isClosingMode(modeByComponentName);
    }

    public static boolean shouldSkipDefaultContinueTransformation(ComponentName componentName) {
        if (componentName == null) {
            return false;
        }
        String shortString = componentName.toShortString();
        int i = 0;
        while (true) {
            List<String> list = SKIP_DEFAULT_CONTINUE_TRANSFORMATION_ACTIVITIES;
            if (i >= list.size()) {
                return false;
            }
            if (shortString.contains(list.get(i))) {
                return true;
            }
            i++;
        }
    }
}

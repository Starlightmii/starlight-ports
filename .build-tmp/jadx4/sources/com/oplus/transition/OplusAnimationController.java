package com.oplus.transition;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ResourceId;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Binder;
import android.os.Bundle;
import android.os.OplusPropertyList;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.DisplayInfo;
import android.view.InflateException;
import android.view.RoundedCorner;
import android.view.SurfaceControl;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.window.RemoteTransition;
import android.window.TransitionInfo;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.internal.policy.AttributeCache;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.animation.DefaultTransitionHelper$;
import com.android.systemui.inputmethod.ui.viewmodel.ImeSwitcherMenuViewModel$MenuItem$;
import com.android.wm.shell.R;
import com.android.wm.shell.activityembedding.OplusEmbedReflectionUtils;
import com.android.wm.shell.common.ComponentUtils;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.flexibletask.FlexibleTaskTransitionUtils;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.transition.AdaptiveSmoothShellAnimManager;
import com.android.wm.shell.transition.DefaultTransitionHandler;
import com.android.wm.shell.transition.TransitionAnimationUtil;
import com.android.wm.shell.transition.TransitionLog;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.util.ReflectionUtils;
import com.google.android.collect.Sets;
import com.oplus.flexiblewindow.FlexibleWindowManager;
import com.oplus.splitscreen.ReflectionHelper;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.separate.OplusQSSimpleHeader;
import com.oplus.util.OplusPlatformLevelUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusAnimationController {
    private static final String ACTIVITY_NAME_BASESCANUI = "com.tencent.mm/.plugin.scanner.ui.BaseScanUI";
    private static final int ADJUST_Z_ORDER_BOTTOM = 2;
    private static final int ADJUST_Z_ORDER_NONE = 0;
    private static final int ADJUST_Z_ORDER_TOP = 1;
    private static final String APP_ZOOM_OUT = "AppZoomOut:";
    private static final int COMPAT_CLOSE_IN_ZOOM_CHANGES_NUM = 3;
    private static final float DEFAULT_ALPHA_VALUE_ONE = 1.0f;
    private static final float DEFAULT_ALPHA_VALUE_ZERO = 0.0f;
    private static final String DTA = "DefaultTaskDisplayArea";
    public static final int FLAG_ALLOW_LIGHTOS_CORNER_RADIUS = 65536;
    public static final int FLAG_CANCEL_FIXED_ROTATION = 262144;
    public static final int FLAG_DISABLE_SPRING_ROTATION = 8;
    public static final int FLAG_EXCHANGE_FULL_TO_FLEXIBLE_TRANSITION_ROTATION = 256;
    public static final int FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION = 512;
    public static final int FLAG_FOLD_DISPLAY_ROTATION = 16;
    public static final int FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1 = 4096;
    public static final int FLAG_LAUNCHER_SELF_ROTATION = 1;
    public static final int FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE = 1024;
    public static final int FLAG_TRANSITION_ABORTED = 64;
    public static final int FLAG_TRANSITION_DISPLAY_CHANGE_ABORT = 2048;
    public static final int FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING = 8192;
    private static final int FLAG_TRANSIT_CLOSE = 2;
    private static final int FLAG_TRANSIT_OPEN = 1;
    private static final int FLAG_TRANSIT_OPEN_AND_CLOSE = 3;
    private static final String GET_EXT_FLAGS = "getFlags";
    private static final String GET_IS_AVOID_DELEGATE_TO_REMOTE = "getIsAvoidDelegateToRemote";
    public static final String GET_TRANSITION_LAUNCH_FROM = "getTransitionLaunchFrom";
    private static final String GET_USER_ID_TRANSITION = "getUserIdTransition";
    private static final String GET_USE_ORIGINAL_ANIM_RES = "getUseOriginalAnimRes";
    private static final String IS_GLOBAL_SEARCH_PAGE_SWITCH = "isGlobalSearchPageSwitch";
    private static final String IS_SWIPEUP_BROWSER_SCREEN_SHOWING = "isSwipeUpBrowserScreenShowing";
    private static final String KEY_ATTACHED_TO_GLOBAL_SEARCH = "attached_to_global_search";
    private static final String KEY_BROWSER_SWIPE_UP_ANIM_ENABLE = "sys.persist.browser_swipeup_anim.enable";
    private static final String KEY_HAS_BALL_ALLOW_SAW = "has_bal_allow_saw";
    private static final String KEY_HAS_HOME_TASK = "has_home_task";
    private static final String KEY_HAS_MAIN_WINDOW = "has-main-window";
    private static final String KEY_HAS_TRANSLUCENT_TOP_AR = "has-translucent-top-ar";
    private static final String KEY_START_FROM_ASSIST_STREAM = "start_from_assist_stream";
    private static final String KEY_START_FROM_GLOBAL_SEARCH = "start_from_global_search";
    private static final String KEY_TOP_MOST_ACTIVITY = "top_most_activity";
    private static final String KEY_UNEMBEDDED_IN_LAUNCHER = "unembedded_in_launcher";
    private static final int LARGE_SCREEN_SMALLEST_SWDP = 600;
    private static final String LAUNCHER_DEBUG_NAME = "QuickstepLaunch";
    private static final String LAUNCHER_KEY_MODE_DEBUG_NAME = "QuickstepLaunchHome";
    private static final String LAUNCHER_UNITY_START_DEBUG_NAME = "QuickstepLaunchClientHome";
    public static final String METHOD_GET_ROOT_LAYER_Z_ORDER = "getRootLayerZOrder";
    public static final String METHOD_GET_ROOT_LEASH_PARENT = "getRootLeashParent";
    public static final String METHOD_NAME_TRANSITION_EXTENDED_INFO_HAS_FLAG = "hasFlag";
    private static final int MIN_CHANGES_NUM = 3;
    private static final int NAME_OFF_SET = 13;
    public static final String NEED_NO_ALPHA_GLOBAL_SEARCH_SCENE = "needNoAlphaGlobalSearchScene";
    public static final int NONE_ABORT = 0;
    private static final String OPLUS_SUPPER_POWER_SAVE_MODE_LAUNCHER = "com.android.launcher/.powersave.SuperPowerSaveModeLauncher";
    public static final String PACKAGE_SYSTEM = "android";
    public static final String PKG_LAUNCHER = "com.android.launcher";
    private static final String PROPERTY_RC_SECONDARY_SIZE = "ro.oplus.display.secondary.rc.size";
    private static final String SET_USER_ID_TRANSITION = "setUserIdTransition";
    public static final int SMOOTH_CORNER_TYPE = 1;
    public static final float SMOOTH_CORNER_WEIGHT = 2.5f;
    public static final int TRANSITION_LAUNCH_FROM_RECENTS_SLIDE = 9;
    public static final int TRANSITION_LAUNCH_FROM_SWIPEUP_QUICK_BACK = 1;
    public static final int TRANSITION_LAUNCH_FROM_SYSUI_PLUGIN = 2;
    public static final int TRANSITION_LAUNCH_FROM_SYSUI_QS = 5;
    private static final float TRANSIT_TRANSLUCENT_CLOSE_EXIT_INTERPOLATOR = 1.0f;
    private static final int TRANSIT_TRANSLUCENT_CLOSE_EXIT_TIME = 100;
    private static final int TYPE_OVERRIDE_ANIM_CUSTOM_1 = 100;
    private static final String UNOCCLUDE = "unocclude";
    private static final String WEIXIN_CHATTINGUI_COMPONENT = "com.tencent.mm/.ui.chatting.ChattingUI";
    private static final String WEIXIN_LAUNCHERUI_COMPONENT = "com.tencent.mm/.ui.LauncherUI";
    private static final int WINDOWING_MODE_ZOOM = 100;
    private static HashMap<String, Integer> sForceOverrideCustomPackages;
    private float mBaseWindowCornerRadius;
    private Context mContext;
    private boolean mNeedWindowCornerRadius;
    public static final Set<String> NO_BACKCOLOR_PACKAGE_IN_SCREEN_ROTATION = Sets.newHashSet(new String[]{"com.tencent.qqlive"});
    public static final Set<String> PKG_GLOBAL_SEARCH = Sets.newHashSet(new String[]{"com.oppo.quicksearchbox", "com.heytap.quicksearchbox"});
    public static final Set<String> PKG_SKIP_OVERRIDE = Sets.newHashSet(new String[]{OplusQSSimpleHeader.GOOGLE_CALENDAR_PKG_NAME});
    private static final Set<String> FORCE_LOAD_OPLUS_ANIM_COMPONENT = Sets.newHashSet(new String[]{"com.makemytrip/com.mmt.hotel.altacco.ui.AltAccoLandingActivity", "com.makemytrip/com.mmt.hotel.landingV3.ui.HotelLandingActivityV3"});
    private static final Set<String> FORCE_OPLUS_ANIM_LIST = Sets.newHashSet(new String[]{"com.eg.android.AlipayGphone/com.alipay.android.msp.ui.views.MspUniRenderActivity"});
    private static HashMap<Integer, Integer> sAnimMapLowEnd = new HashMap<>();
    private final List<String> mSkipLargeScreenTaskSwitch = List.of("com.oplus.upgradeguide");
    private final List<String> mNoTaskLargeScreenAnimCmps = List.of(ShellContinuousTransitionUtils.LAUNCHER_CMP, "com.microsoft.launcher/.setting.SettingActivity");
    private float mBaseWindowCornerRadiusForPrimary = 0.0f;
    private float mBaseWindowCornerRadiusForSecond = 0.0f;
    private boolean mIsOldDevice = false;
    private int mCurrentWindowCornerRadiusModeId = -1;

    static {
        HashMap<String, Integer> map = new HashMap<>();
        sForceOverrideCustomPackages = map;
        map.put("com.fy.qqkp.new.nearme.gamecenter", 100);
    }

    public OplusAnimationController(Context context) {
        this.mContext = context;
        this.mBaseWindowCornerRadius = getCornerRadius(context.getDisplay());
        initWindowCornerRadiusForDisplay();
        initAnimMap();
        SystemProperties.set(KEY_BROWSER_SWIPE_UP_ANIM_ENABLE, "true");
    }

    private boolean avoidDelegateForAssistStream(RemoteTransition remoteTransition, TransitionInfo transitionInfo) {
        if (remoteTransition == null || transitionInfo == null || !isAssistStreamPageSwitch(transitionInfo) || !TransitionUtil.isClosingType(transitionInfo.getType())) {
            return false;
        }
        return "QuickstepLaunch".equals(remoteTransition.getDebugName()) || "QuickstepLaunchHome".equals(remoteTransition.getDebugName()) || LAUNCHER_UNITY_START_DEBUG_NAME.equals(remoteTransition.getDebugName());
    }

    private boolean avoidDelegateForBrowserSwipeup(RemoteTransition remoteTransition, TransitionInfo transitionInfo) {
        if (remoteTransition != null && transitionInfo != null) {
            boolean zIsSwipeUpBrowserPageSwitch = isSwipeUpBrowserPageSwitch(transitionInfo);
            int iIntValue = ((Integer) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_TRANSITION_LAUNCH_FROM, null, new Object[0])).intValue();
            if (zIsSwipeUpBrowserPageSwitch && TransitionUtil.isClosingType(transitionInfo.getType()) && ("QuickstepLaunch".equals(remoteTransition.getDebugName()) || "QuickstepLaunchHome".equals(remoteTransition.getDebugName()))) {
                return true;
            }
            if (TransitionUtil.isOpeningType(transitionInfo.getType()) && iIntValue == 1) {
                return true;
            }
        }
        return false;
    }

    private boolean avoidDelegateForGlobalSearch(RemoteTransition remoteTransition, TransitionInfo transitionInfo) {
        if (remoteTransition == null || transitionInfo == null || !TransitionUtil.isClosingType(transitionInfo.getType())) {
            return false;
        }
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if (change.getTaskInfo() == null && !change.hasFlags(2)) {
                return false;
            }
            Bundle windowInfoBundleFromChange = OplusTransitionReflectionHelper.getWindowInfoBundleFromChange(change);
            if (windowInfoBundleFromChange != null && windowInfoBundleFromChange.getBoolean(KEY_START_FROM_GLOBAL_SEARCH, false) && LAUNCHER_UNITY_START_DEBUG_NAME.equals(remoteTransition.getDebugName())) {
                return true;
            }
        }
        return false;
    }

    private boolean avoidDelegateForKeyguardUnoccludeOpen(RemoteTransition remoteTransition, TransitionInfo transitionInfo) {
        if (remoteTransition == null || transitionInfo == null || !TransitionUtil.isOpeningType(transitionInfo.getType()) || !"QuickstepLaunchHome".equals(remoteTransition.getDebugName()) || (transitionInfo.getFlags() & FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) == 0 || (transitionInfo.getFlags() & 64) != 0) {
            return false;
        }
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if (change.getTaskInfo() != null && TransitionUtil.isClosingMode(change.getMode()) && change.hasFlags(4)) {
                TransitionLog.always("avoidDelegateForKeyguardUnoccludeOpen hit #" + transitionInfo.getDebugId() + " flags=0x" + Integer.toHexString(transitionInfo.getFlags()) + " realAct=" + change.getTaskInfo().realActivity);
                return true;
            }
        }
        return false;
    }

    private boolean avoidDelegateForShowWhenLockedOverlayClose(RemoteTransition remoteTransition, TransitionInfo transitionInfo) {
        if (remoteTransition != null && transitionInfo != null && TransitionUtil.isClosingType(transitionInfo.getType()) && "QuickstepLaunchHome".equals(remoteTransition.getDebugName()) && (transitionInfo.getFlags() & 64) == 0) {
            boolean z = false;
            boolean z2 = false;
            for (TransitionInfo.Change change : transitionInfo.getChanges()) {
                if (change.getTaskInfo() != null) {
                    if (!z && TransitionUtil.isClosingMode(change.getMode()) && change.hasFlags(4)) {
                        Bundle windowInfoBundleFromChange = OplusTransitionReflectionHelper.getWindowInfoBundleFromChange(change);
                        z = windowInfoBundleFromChange != null && windowInfoBundleFromChange.getBoolean(KEY_HAS_BALL_ALLOW_SAW, false);
                    } else if (!z2 && change.getMode() == 6 && change.hasFlags(ShellContinuousTransitionController.TRANSIT_FLAG_VIEW_SEAMLESS_REMOTE) && change.getTaskInfo().getActivityType() == 2) {
                        z2 = true;
                    }
                    if (z && z2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x002b  */
    private int calculateRadiusFromString(String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            i = 0;
        } else {
            String[] strArrSplit = str.split(OplusPreventModeData.SEPARATOR);
            if (strArrSplit.length > 0) {
                try {
                    i = Integer.parseInt(strArrSplit[0]);
                    for (int i2 = 1; i2 < strArrSplit.length; i2++) {
                        int i3 = Integer.parseInt(strArrSplit[i2]);
                        if (i3 < i) {
                            i = i3;
                        }
                    }
                } catch (Exception unused) {
                    TransitionLog.always("getRoundCornerRadius error");
                    i = 0;
                }
            } else {
                i = 0;
            }
        }
        return Math.max(i, 0);
    }

    private boolean checkNeedAlpha(TransitionInfo transitionInfo, TransitionInfo.Change change) {
        Bundle windowInfoBundleFromChange;
        return transitionInfo == null || change == null || !TransitionAnimationUtil.isTaskLevelTransition(transitionInfo) || (windowInfoBundleFromChange = OplusTransitionReflectionHelper.getWindowInfoBundleFromChange(change)) == null || !windowInfoBundleFromChange.getBoolean(KEY_HAS_TRANSLUCENT_TOP_AR, false);
    }

    private boolean containDeskTopShortcut(ActivityManager.RunningTaskInfo runningTaskInfo) {
        ComponentName componentName;
        return (runningTaskInfo == null || (componentName = runningTaskInfo.topActivity) == null || !ACTIVITY_NAME_BASESCANUI.equals(componentName.flattenToShortString())) ? false : true;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0018  */
    private Animation forceOverrideCustomAnimation(TransitionInfo.AnimationOptions animationOptions, int i, boolean z) {
        boolean z2;
        AlphaAnimation alphaAnimation = null;
        if (animationOptions == null) {
            return null;
        }
        String packageName = animationOptions.getPackageName();
        if (animationOptions.getEnterResId() == 0 && animationOptions.getExitResId() == 0) {
            z2 = i == 1;
        }
        if (z2 && !TextUtils.isEmpty(packageName) && sForceOverrideCustomPackages.containsKey(packageName)) {
            int iIntValue = sForceOverrideCustomPackages.get(packageName).intValue();
            if (iIntValue == 100) {
                alphaAnimation = z ? new AlphaAnimation(0.0f, 0.0f) : new AlphaAnimation(1.0f, 1.0f);
                alphaAnimation.setDuration(150L);
            }
            TransitionLog.always("forceOverrideCustomAnimation use override anim for app=:" + packageName + ", anim=" + alphaAnimation + ", animType=" + iIntValue + ", enter=" + z);
        }
        return alphaAnimation;
    }

    private Animation getAnimationbyResId(int i, int i2, TransitionInfo.Change change, boolean z, int i3) {
        if (i == 0) {
            return null;
        }
        int platformLevelAnimation = getPlatformLevelAnimation(this.mContext, i);
        boolean zIsDefaultContinuous = AdaptiveSmoothShellAnimManager.getInstance().isDefaultContinuous();
        boolean zHasRoundedCorners = hasRoundedCorners(change);
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("load oplus transition, type: ", i2, ", isTask: ", " , wallpaperTransit: ", z);
        sbM.append(i3);
        sbM.append(" mNeedWindowCornerRadius = ");
        KeyguardLogger$.ExternalSyntheticOutline0.m(" , isDefaultContinuous = ", " hasRoundedCorners = ", sbM, this.mNeedWindowCornerRadius, zIsDefaultContinuous);
        sbM.append(zHasRoundedCorners);
        TransitionLog.always(sbM.toString());
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.mContext, platformLevelAnimation);
        if (!zIsDefaultContinuous) {
            zHasRoundedCorners = this.mNeedWindowCornerRadius;
        }
        TransitionAnimationUtil.setAnimationHasRoundedCorners(animationLoadAnimation, zHasRoundedCorners);
        return animationLoadAnimation;
    }

    private int getCornerRadius(Display display) {
        return maxRadius(display, maxRadius(display, maxRadius(display, maxRadius(display, 0, 0), 1), 2), 3);
    }

    private static int getPlatformLevelAnimation(Context context, int i) {
        int platformAnimationLevel = OplusPlatformLevelUtils.getInstance(context).getPlatformAnimationLevel();
        StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(platformAnimationLevel, "getPlatformAnimation platformLevel ", " LEVEL_LOW 1 needAnimForLowEndDevice ");
        boolean z = OplusShellLightOSTransition.OPLUS_FEATURE_WINDOW_ANIM_LOW_END_CONFIG;
        sbM.append(z);
        TransitionLog.always(sbM.toString());
        if ((platformAnimationLevel != 1 && !z) || !sAnimMapLowEnd.containsKey(Integer.valueOf(i))) {
            return i;
        }
        int iIntValue = sAnimMapLowEnd.get(Integer.valueOf(i)).intValue();
        TransitionLog.always("Using resId " + Integer.toHexString(iIntValue) + " for animResId " + Integer.toHexString(i));
        return iIntValue;
    }

    private int getResIdInner(TransitionInfo transitionInfo, boolean z, boolean z2, boolean z3, boolean z4) {
        int i;
        int i2;
        int type = transitionInfo.getType();
        if (type != 1) {
            if (type != 2) {
                if (type != 3) {
                    if (type != 4) {
                        return 0;
                    }
                }
            }
            if (z3) {
                return getTaskToBackAnim(z, true, z2);
            }
            if (z4) {
                i2 = z ? R.anim.oplus_close_slide_enter_flexible : R.anim.oplus_close_slide_exit_flexible;
            } else if (z) {
                i2 = z2 ? R.anim.oplus_close_slide_enter : R.anim.oplus_close_slide_enter_no_alpha;
            } else {
                i2 = R.anim.oplus_close_slide_exit;
            }
            this.mNeedWindowCornerRadius = !z;
            return i2;
        }
        if (z3) {
            return getTaskToFrontAnim(z, true, z2);
        }
        if (z4) {
            i = z ? R.anim.oplus_open_slide_enter_flexible : R.anim.oplus_open_slide_exit_flexible;
        } else if (z) {
            i = R.anim.oplus_open_slide_enter;
        } else {
            i = z2 ? R.anim.oplus_open_slide_exit : R.anim.oplus_open_slide_exit_no_alpha;
        }
        this.mNeedWindowCornerRadius = z;
        return i;
    }

    private String getTargetPackageName(TransitionInfo.AnimationOptions animationOptions, boolean z, TransitionInfo.Change change) {
        ComponentName componentName;
        ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
        if (z && taskInfo != null && (componentName = taskInfo.realActivity) != null) {
            return componentName.getPackageName();
        }
        if (animationOptions != null) {
            return animationOptions.getPackageName();
        }
        return null;
    }

    private int getTaskToBackAnim(boolean z, boolean z2, boolean z3) {
        if (!z2) {
            this.mNeedWindowCornerRadius = true;
            return z ? R.anim.oplus_task_close_slide_enter : R.anim.oplus_task_close_slide_exit;
        }
        if (z) {
            return z3 ? R.anim.oplus_close_slide_enter : R.anim.oplus_close_slide_enter_no_alpha;
        }
        int i = R.anim.oplus_close_slide_exit;
        this.mNeedWindowCornerRadius = true;
        return i;
    }

    private int getTaskToFrontAnim(boolean z, boolean z2, boolean z3) {
        if (!z2) {
            this.mNeedWindowCornerRadius = true;
            return z ? R.anim.oplus_task_open_slide_enter : R.anim.oplus_task_open_slide_exit;
        }
        if (!z) {
            return z3 ? R.anim.oplus_open_slide_exit : R.anim.oplus_open_slide_exit_no_alpha;
        }
        int i = R.anim.oplus_open_slide_enter;
        this.mNeedWindowCornerRadius = true;
        return i;
    }

    private float getWindowCornerRadius(boolean z) {
        if (!OplusShellLightOSTransition.IS_LIGHTOS) {
            if (this.mIsOldDevice) {
                return AdaptiveSmoothShellAnimManager.getInstance().isDeviceFolded() ? this.mBaseWindowCornerRadiusForSecond : this.mBaseWindowCornerRadiusForPrimary;
            }
            return this.mBaseWindowCornerRadius;
        }
        if (!z) {
            return 0.0f;
        }
        TransitionLog.debug("allow LightOS corner radius, mBaseWindowCornerRadius=" + this.mBaseWindowCornerRadius);
        return this.mBaseWindowCornerRadius;
    }

    private boolean hasRoundedCorners(TransitionInfo.Change change) {
        ComponentName componentNameFromChange;
        if (change == null) {
            return false;
        }
        try {
            ConcurrentHashMap backAnimContinueCornerRadiusMap = Transitions.getBackAnimContinueCornerRadiusMap();
            return backAnimContinueCornerRadiusMap != null && (componentNameFromChange = TransitionAnimationUtil.getComponentNameFromChange(change)) != null && ((Float) backAnimContinueCornerRadiusMap.getOrDefault(TransitionAnimationUtil.getMapKeyByComponentAndMode(componentNameFromChange, TransitionUtil.isClosingMode(change.getMode()) ? 1 : 0), Float.valueOf(0.0f))).floatValue() > 0.0f && TransitionAnimationUtil.isSystemApp(componentNameFromChange.getPackageName());
        } catch (Exception e) {
            Log.e("OplusAnimation", "Error in hasRoundedCorners", e);
            return false;
        }
    }

    private boolean hasToFrontAnim(TransitionInfo transitionInfo) {
        for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM >= 0; iM--) {
            if (((TransitionInfo.Change) transitionInfo.getChanges().get(iM)).getMode() == 3) {
                return true;
            }
        }
        return false;
    }

    private void initAnimMap() {
        TransitionLog.always("Initialize anim map");
        sAnimMapLowEnd.put(Integer.valueOf(R.anim.oplus_close_slide_enter), Integer.valueOf(R.anim.oplus_close_slide_enter_low));
        sAnimMapLowEnd.put(Integer.valueOf(R.anim.oplus_close_slide_exit), Integer.valueOf(R.anim.oplus_close_slide_exit_low));
        sAnimMapLowEnd.put(Integer.valueOf(R.anim.oplus_open_slide_enter), Integer.valueOf(R.anim.oplus_open_slide_enter_low));
        sAnimMapLowEnd.put(Integer.valueOf(R.anim.oplus_open_slide_exit), Integer.valueOf(R.anim.oplus_open_slide_exit_low));
    }

    private void initWindowCornerRadiusForDisplay() {
        String str = OplusPropertyList.RC_SIZE;
        String str2 = SystemProperties.get(PROPERTY_RC_SECONDARY_SIZE, "0");
        this.mIsOldDevice = isValidCornerString(str) || isValidCornerString(str2);
        this.mBaseWindowCornerRadiusForPrimary = calculateRadiusFromString(str);
        this.mBaseWindowCornerRadiusForSecond = calculateRadiusFromString(str2);
    }

    private boolean isAllowLightOSCornerRadius(TransitionInfo transitionInfo) {
        return (transitionInfo == null || (getFlags(transitionInfo) & FLAG_ALLOW_LIGHTOS_CORNER_RADIUS) == 0) ? false : true;
    }

    public static boolean isAssistStreamPageSwitch(TransitionInfo transitionInfo) {
        if (transitionInfo == null) {
            return false;
        }
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if (change.getTaskInfo() == null && !change.hasFlags(2)) {
                return false;
            }
            Bundle windowInfoBundleFromChange = OplusTransitionReflectionHelper.getWindowInfoBundleFromChange(change);
            if (windowInfoBundleFromChange != null && windowInfoBundleFromChange.getBoolean(KEY_START_FROM_ASSIST_STREAM, false)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCustomAnimaiton(TransitionInfo transitionInfo, TransitionInfo.Change change) {
        TransitionInfo.AnimationOptions animationOptions = change.getAnimationOptions();
        return animationOptions != null && (animationOptions != null ? animationOptions.getType() : 0) == 1 && (!(change.getTaskInfo() != null) || AdaptiveSmoothShellAnimManager.getInstance().canCustomizeAppTransition(transitionInfo, animationOptions, transitionInfo.getType(), change));
    }

    public static boolean isGlobalSearchPageSwitch(TransitionInfo transitionInfo) {
        Boolean bool;
        if (transitionInfo != null && (transitionInfo.getFlags() & 128) == 0) {
            int i = 0;
            boolean z = false;
            for (TransitionInfo.Change change : transitionInfo.getChanges()) {
                String packageNameFromChange = TransitionAnimationUtil.getPackageNameFromChange(change);
                Bundle windowInfoBundleFromChange = OplusTransitionReflectionHelper.getWindowInfoBundleFromChange(change);
                Set<String> set = PKG_GLOBAL_SEARCH;
                if (set.contains(packageNameFromChange)) {
                    return ((transitionInfo.getChanges().size() == 1 && change.getTaskInfo() != null && change.hasFlags(4)) || (windowInfoBundleFromChange != null && windowInfoBundleFromChange.getBoolean(KEY_UNEMBEDDED_IN_LAUNCHER, false))) ? false : true;
                }
                if (!set.contains(packageNameFromChange) && 1 == transitionInfo.getChanges().size() && change.getTaskInfo() == null && change.hasFlags(4)) {
                    return false;
                }
                if (change.getTaskInfo() != null) {
                    if ("com.android.launcher".equals(TransitionAnimationUtil.getPackageNameFromChange(change))) {
                        z = true;
                    }
                    i++;
                }
                if (windowInfoBundleFromChange != null ? windowInfoBundleFromChange.getBoolean(KEY_ATTACHED_TO_GLOBAL_SEARCH, false) : false) {
                    return true;
                }
            }
            if ((i != 1 || !z) && (bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, IS_GLOBAL_SEARCH_PAGE_SWITCH, null, new Object[0])) != null && bool.booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRemoteFromSysUIQS(TransitionInfo transitionInfo) {
        return ((Integer) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_TRANSITION_LAUNCH_FROM, null, new Object[0])).intValue() == 5;
    }

    public static boolean isStartFromSysUIPlugin(TransitionInfo transitionInfo) {
        return ((Integer) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_TRANSITION_LAUNCH_FROM, null, new Object[0])).intValue() == 2;
    }

    public static boolean isSwipeUpBrowserPageSwitch(TransitionInfo transitionInfo) {
        if (transitionInfo == null) {
            return false;
        }
        return ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, IS_SWIPEUP_BROWSER_SCREEN_SHOWING, null, new Object[0])).booleanValue();
    }

    private boolean isValidCornerString(String str) {
        return (TextUtils.isEmpty(str) || str.equals("0")) ? false : true;
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0077  */
    private boolean matchOplusStyleCondition(TransitionInfo transitionInfo, TransitionInfo.Change change, boolean z, int i) {
        if (transitionInfo.getType() == 1 && change.getMode() == 1 && change.getActivityComponent() != null && FORCE_OPLUS_ANIM_LIST.contains(change.getActivityComponent().flattenToString())) {
            TransitionLog.debug("force load oplus anim for change: " + change);
            return true;
        }
        List changes = transitionInfo.getChanges();
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = true;
        for (int i2 = 0; i2 < changes.size(); i2++) {
            TransitionInfo.Change change2 = (TransitionInfo.Change) changes.get(i2);
            if (TransitionAnimationUtil.isSystemApp(getTargetPackageName(change.getAnimationOptions(), change2.getTaskInfo() != null, change2))) {
                z2 = true;
            } else if (TransitionAnimationUtil.isSystemApp(change2.getActivityComponent() != null ? change2.getActivityComponent().getPackageName() : null)) {
                z2 = true;
            }
            if (change2.getTaskInfo() != null) {
                z3 = true;
            }
            Bundle windowInfoBundleFromChange = OplusTransitionReflectionHelper.getWindowInfoBundleFromChange(change);
            if (change2.getMode() == 2 && (windowInfoBundleFromChange == null || !windowInfoBundleFromChange.getBoolean(KEY_HAS_MAIN_WINDOW, true))) {
                z4 = false;
            }
        }
        boolean z5 = change.getTaskInfo() == null && change.getMode() == 1 && change.hasFlags(4) && change.getActivityComponent() != null && FORCE_LOAD_OPLUS_ANIM_COMPONENT.contains(change.getActivityComponent().flattenToString());
        boolean z6 = z || (4 & change.getFlags()) != 0;
        if ((z6 && !z5) || !z4) {
            TransitionLog.w("don't load oplus anim, hasTranslucentFlag = " + z6 + ", closingPageHasMainWindow = " + z4);
            return false;
        }
        if (z2) {
            return true;
        }
        boolean zShouldLoadCustomAnimation = AdaptiveSmoothShellAnimManager.getInstance().shouldLoadCustomAnimation(change, i);
        if (!z3 && zShouldLoadCustomAnimation) {
            TransitionLog.always("This page has its own override animation.");
        } else if (!z3 && !zShouldLoadCustomAnimation) {
            return true;
        }
        TransitionLog.w("no system app in targets, skip loading oplus animation.");
        return false;
    }

    private int maxRadius(Display display, int i, int i2) {
        RoundedCorner roundedCorner = display != null ? display.getRoundedCorner(i2) : null;
        return roundedCorner != null ? Math.max(i, roundedCorner.getRadius()) : i;
    }

    private int recalculateZOrderLevel(TransitionInfo transitionInfo, TransitionInfo.Change change) {
        int size = transitionInfo.getChanges().size();
        TransitionInfo.AnimationOptions animationOptions = change.getAnimationOptions();
        int type = animationOptions != null ? animationOptions.getType() : 0;
        boolean z = change.getTaskInfo() != null;
        int i = 2;
        boolean z2 = z && change.getTaskInfo().getActivityType() == 2 && "com.android.launcher".equals(ComponentUtils.getPackageName(change.getTaskInfo()));
        boolean z3 = transitionInfo.getType() == 2;
        HashMap map = null;
        int i2 = 0;
        boolean z4 = false;
        boolean z5 = false;
        while (i2 < size) {
            TransitionInfo.Change change2 = (TransitionInfo.Change) transitionInfo.getChanges().get(i2);
            if (change2.getMode() == 6) {
                z4 = true;
            } else if (change2.getMode() == 3 || (TransitionUtil.isClosingType(change2.getMode()) && (change2.getFlags() & 4) == 0)) {
                z4 = false;
            }
            if (z2 && z3 && map == null) {
                map = new HashMap();
            }
            if (map != null && !z5 && change2.getTaskInfo() != null && change2.getTaskInfo().getActivityType() != i) {
                String packageName = ComponentUtils.getPackageName(change2.getTaskInfo());
                if (!"com.android.launcher".equals(packageName) && !TextUtils.isEmpty(packageName)) {
                    int iIntValue = ((Integer) map.getOrDefault(packageName, -1)).intValue();
                    if (change2.getMode() == 1) {
                        iIntValue |= 1;
                    } else if (change2.getMode() == 2) {
                        iIntValue |= 2;
                    }
                    if ((iIntValue & 3) == 3) {
                        z5 = true;
                    } else {
                        map.put(packageName, Integer.valueOf(iIntValue));
                    }
                }
            }
            i2++;
            i = 2;
        }
        boolean zIsClosingType = (type == 1 && animationOptions != null && animationOptions.getExitResId() == 0) & z4 & (change.getMode() == 6 && (change.getFlags() & ShellContinuousTransitionController.TRANSIT_FLAG_VIEW_SEAMLESS_REMOTE) != 0) & TransitionUtil.isClosingType(transitionInfo.getType());
        boolean z6 = z && change.getMode() == 3 && (change.getFlags() & ShellContinuousTransitionController.TRANSIT_FLAG_VIEW_SEAMLESS_REMOTE) != 0 && (transitionInfo.getType() == 2 || transitionInfo.getType() == 4);
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        for (int i3 = 0; i3 < size; i3++) {
            TransitionInfo.Change change3 = (TransitionInfo.Change) transitionInfo.getChanges().get(i3);
            ComponentName activityComponent = change3.getActivityComponent();
            if (change3.getMode() == 3 && activityComponent != null && WEIXIN_LAUNCHERUI_COMPONENT.equals(activityComponent.flattenToShortString())) {
                z8 = true;
            } else if (change3.getMode() == 2 && activityComponent != null && WEIXIN_CHATTINGUI_COMPONENT.equals(activityComponent.flattenToShortString())) {
                z7 = true;
            }
            if (!z9 && change3.getTaskInfo() != null && change3.getTaskInfo().getActivityType() != 2 && change3.getStartRotation() != change3.getEndRotation()) {
                int mode = change3.getMode();
                if (mode != 6 && mode != 1) {
                    if (mode == 3) {
                    }
                }
                z9 = true;
            }
        }
        boolean z10 = ((!z && type == 1 && transitionInfo.getType() == 1) && z7 && z8) | zIsClosingType;
        boolean z11 = (z6 && !AdaptiveSmoothShellAnimManager.getInstance().mLargeScreenAnim.contains(Integer.valueOf(transitionInfo.getDebugId()))) || z5 || (z2 && change.getMode() == 4 && transitionInfo.getType() == 6 && change.getStartRotation() != change.getEndRotation() && z9);
        if (z10) {
            return 1;
        }
        return z11 ? 2 : 0;
    }

    private boolean seamlessDisplayChangeToLauncher(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction) {
        if (transitionInfo != null && transitionInfo.getChanges() != null && transitionInfo.getChanges().size() >= 3) {
            int size = transitionInfo.getChanges().size() - 1;
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(size);
            if (change == null || !change.hasFlags(32) || change.getMode() != 6 || change.getStartRotation() == change.getEndRotation() || (!TransitionUtil.isClosingType(transitionInfo.getType()) && (transitionInfo.getType() != 6 || change.getRotationAnimation() != 3))) {
                return false;
            }
            TransitionInfo.Change change2 = null;
            boolean z = false;
            boolean z2 = false;
            while (size >= 0) {
                TransitionInfo.Change change3 = (TransitionInfo.Change) transitionInfo.getChanges().get(size);
                if (change3 != null && change3.getTaskInfo() != null) {
                    if (TransitionUtil.isClosingType(change3.getMode())) {
                        z2 = true;
                        change2 = change3;
                    } else if (change3.getMode() == 3 && change3.getTaskInfo().getActivityType() == 2) {
                        z = true;
                    }
                }
                size--;
            }
            if (z && z2 && change2 != null && change2.getLeash() != null && change2.getLeash().isValid()) {
                TransitionLog.debug("seamlessDisplayChangeToLauncher hide task:" + change2);
                transaction.hide(change2.getLeash());
            }
            if (z2 && z) {
                return true;
            }
        }
        return false;
    }

    private boolean skipLargeScreenAnimStartFromRecents(TransitionInfo transitionInfo) {
        int size;
        ActivityManager.RunningTaskInfo taskInfo;
        ComponentName componentName;
        if (!TransitionAnimationUtil.isTabletDevice() || transitionInfo == null || transitionInfo.getChanges() == null || transitionInfo.getType() != 3 || (size = transitionInfo.getChanges().size()) != 2) {
            return false;
        }
        boolean zEquals = false;
        boolean z = false;
        for (int i = size - 1; i >= 0; i--) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(i);
            if (change == null || (taskInfo = change.getTaskInfo()) == null || (componentName = taskInfo.topActivity) == null) {
                return false;
            }
            if (change.getMode() == 4) {
                zEquals = "com.android.launcher".equals(componentName.getPackageName());
            }
            if (change.getMode() == 3) {
                z = true;
            }
            if (zEquals && z) {
                return true;
            }
        }
        return false;
    }

    private boolean skipLargeScreenAnimation(TransitionInfo transitionInfo, TransitionInfo.Change change) {
        if (skipLargeScreenAnimStartFromRecents(transitionInfo)) {
            return true;
        }
        ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
        ComponentName componentName = taskInfo != null ? taskInfo.topActivity : null;
        boolean z = componentName != null && this.mNoTaskLargeScreenAnimCmps.contains(componentName.flattenToShortString());
        TransitionLog.debug("skipLargeScreenAnimation, noTaskLargeScreenAnimCmp = " + z);
        return z;
    }

    private boolean visibleIfNeed(TransitionInfo transitionInfo, TransitionInfo.Change change) {
        boolean zIsOpeningType = TransitionUtil.isOpeningType(transitionInfo.getType());
        int mode = change.getMode();
        if (zIsOpeningType) {
            return (mode == 1 || mode == 3) && (change.getFlags() & 8) == 0;
        }
        return false;
    }

    public void adjustAnimHierarchyLayer(int i, Animation animation, TransitionInfo.Change change, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction) {
        int i2;
        int size = transitionInfo.getChanges().size();
        if (i < 0 || i >= size) {
            return;
        }
        int i3 = size + 1;
        int iRecalculateZOrderLevel = recalculateZOrderLevel(transitionInfo, change);
        if (iRecalculateZOrderLevel == 1) {
            i2 = (i3 + size) - i;
            transaction.setLayer(change.getLeash(), i2);
        } else if (iRecalculateZOrderLevel == 2) {
            i2 = i3 - i;
            transaction.setLayer(change.getLeash(), i2);
        } else {
            i2 = 0;
        }
        if (iRecalculateZOrderLevel != 0) {
            TransitionLog.debug("adjustAnimHierarchyLayer leash: " + change.getLeash() + ", layer: " + i2 + ", adjust state: " + iRecalculateZOrderLevel);
        }
    }

    public void adjustAnimHierarchyLayerForDefaultContinue(TransitionInfo transitionInfo, int i, TransitionInfo.Change change, SurfaceControl.Transaction transaction) {
        if (AdaptiveSmoothShellAnimManager.getInstance().isDefaultContinuous() && DefaultTransitionHandler.isTaskTransition(transitionInfo) && change.getLeash() != null && change.getLeash().isValid()) {
            int iCalculateAnimLayer = TransitionUtil.calculateAnimLayer(change, i, transitionInfo.getChanges().size(), transitionInfo.getType());
            transaction.setLayer(change.getLeash(), iCalculateAnimLayer);
            TransitionLog.debug("adjustAnimHierarchyLayerForDefaultContinue setLayer z-order:" + iCalculateAnimLayer + " leash:" + change.getLeash());
        }
    }

    public void adjustAnimStartAlpha(TransitionInfo transitionInfo, TransitionInfo.Change change, SurfaceControl.Transaction transaction) {
        ComponentName componentName;
        if (transitionInfo == null || change == null || transaction == null) {
            return;
        }
        boolean z = false;
        boolean z2 = change.getTaskInfo() != null;
        TransitionInfo.AnimationOptions animationOptions = change.getAnimationOptions();
        if (z2 && change.getMode() == 3 && (change.getFlags() & ShellContinuousTransitionController.TRANSIT_FLAG_VIEW_SEAMLESS_REMOTE) != 0) {
            z = true;
        }
        ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
        String packageName = (animationOptions != null || taskInfo == null || (componentName = taskInfo.baseActivity) == null) ? null : componentName.getPackageName();
        String packageName2 = animationOptions != null ? animationOptions.getPackageName() : packageName;
        if (packageName2 == null) {
            packageName2 = PACKAGE_SYSTEM;
        }
        boolean zIsSystemApp = TransitionAnimationUtil.isSystemApp(packageName2);
        if ((TransitionAnimationUtil.needAdjustAnimSystemApp(packageName2) || TransitionAnimationUtil.needAdjustAnimSystemApp(packageName) || !zIsSystemApp) && z && transitionInfo.getType() == 4 && (change.getFlags() & 8) == 0) {
            TransitionLog.always("adjustAnimStartAlpha set alpha to 0, change=" + change + ", packageName:" + packageName2);
            transaction.setAlpha(change.getLeash(), 0.0f);
        }
        if (change.hasFlags(FLAG_CANCEL_FIXED_ROTATION) && transitionInfo.getType() == 1 && TransitionAnimationUtil.isFlipDevice()) {
            TransitionLog.always("adjustAnimStartAlpha flag_no_animation set alpha to 1, change=" + change + ", packageName:" + packageName2);
            transaction.setAlpha(change.getLeash(), 1.0f);
        }
    }

    public void adjustNoAnimChangeStartAlpha(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction) {
        if (transitionInfo == null || transaction == null) {
            return;
        }
        int size = transitionInfo.getChanges().size();
        for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM >= 0; iM--) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(iM);
            if (change != null && (change.getFlags() & 2) != 0 && change.getMode() == 3) {
                TransitionLog.debug("adjustNoAnimChangeStartAlpha FLAG_IS_WALLPAPER set alpha to 1 and set layer");
                transaction.setAlpha(change.getLeash(), 1.0f);
                transaction.setLayer(change.getLeash(), ((-size) + size) - iM);
            }
        }
    }

    public void adjustWsnHierarchyLayer(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction) {
        int i;
        if (!AdaptiveSmoothShellAnimManager.getInstance().matchCustomFilter(transitionInfo)) {
            seamlessDisplayChangeToLauncher(transitionInfo, transaction);
            return;
        }
        int size = transitionInfo.getChanges().size();
        int i2 = size + 1;
        for (int i3 = size - 1; i3 >= 0; i3--) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(i3);
            if (change != null && change.getLeash() != null && change.getLeash().isValid()) {
                if (change.hasFlags(1) && change.getTaskInfo() != null && change.getTaskInfo().getActivityType() == 2) {
                    i = i2 + size;
                    transaction.setLayer(change.getLeash(), i);
                } else if (change.hasFlags(2)) {
                    i = (i2 + size) - 1;
                    transaction.setLayer(change.getLeash(), i);
                } else {
                    i = 1 + i2 + size;
                    transaction.setLayer(change.getLeash(), i);
                }
                StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(i, "adjustWsnHierarchyLayer setLayer zorder:", " leash:");
                sbM.append(change.getLeash());
                TransitionLog.debug(sbM.toString());
            }
        }
    }

    public void adjustZBoostForLayer(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction) {
        HashSet hashSetNewHashSet = Sets.newHashSet();
        TransitionInfo.Change change = null;
        for (int i = 0; i < transitionInfo.getChanges().size(); i++) {
            TransitionInfo.Change change2 = (TransitionInfo.Change) transitionInfo.getChanges().get(i);
            if (change2 != null && change2.getTaskInfo() != null && change2.getLeash() != null && change2.getLeash().isValid()) {
                if (TransitionUtil.isClosingType(change2.getMode())) {
                    hashSetNewHashSet.add(change2);
                }
                if (AdaptiveSmoothShellAnimManager.getInstance().mLargeScreenAnim.contains(Integer.valueOf(transitionInfo.getDebugId())) && TransitionUtil.isClosingType(transitionInfo.getType()) && TransitionUtil.isOpeningType(change2.getMode())) {
                    int size = transitionInfo.getChanges().size();
                    transaction.setAlpha(change2.getLeash(), 0.0f);
                    transaction.setLayer(change2.getLeash(), ((size + 1) + size) - i);
                    change = change2;
                }
            }
        }
        if (change != null) {
            Iterator it = hashSetNewHashSet.iterator();
            while (it.hasNext()) {
                transaction.setRelativeLayer(((TransitionInfo.Change) it.next()).getLeash(), change.getLeash(), -1);
            }
        }
        AdaptiveSmoothShellAnimManager.getInstance().mLargeScreenAnim.remove(Integer.valueOf(transitionInfo.getDebugId()));
    }

    public boolean avoidDelegateToRemote(RemoteTransition remoteTransition, TransitionInfo transitionInfo) {
        if (remoteTransition == null) {
            return false;
        }
        int iIntValue = ((Integer) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_USER_ID_TRANSITION, null, new Object[0])).intValue();
        int iIntValue2 = ((Integer) ReflectionUtils.methodInvoke(remoteTransition, remoteTransition.getClass(), GET_USER_ID_TRANSITION, (Class[]) null, new Object[0])).intValue();
        if (iIntValue != iIntValue2) {
            TransitionLog.debug("transition: " + transitionInfo.getDebugId() + " userid: " + iIntValue + " is different from pending remote userid: " + iIntValue2 + "; pendingRemote: " + remoteTransition);
            return true;
        }
        if (avoidDelegateForGlobalSearch(remoteTransition, transitionInfo)) {
            TransitionLog.always("avoidDelegateForGlobalSearch true");
            return true;
        }
        if (avoidDelegateForBrowserSwipeup(remoteTransition, transitionInfo)) {
            TransitionLog.always("avoidDelegateForBrowserSwipeup true");
            return true;
        }
        if (avoidDelegateForAssistStream(remoteTransition, transitionInfo)) {
            TransitionLog.always("avoidDelegateForAssistStream true");
            return true;
        }
        if (avoidDelegateForShowWhenLockedOverlayClose(remoteTransition, transitionInfo)) {
            TransitionLog.always("avoidDelegateForShowWhenLockedOverlayClose true");
            return true;
        }
        Boolean bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_IS_AVOID_DELEGATE_TO_REMOTE, null, new Object[0]);
        if (bool != null && bool.booleanValue()) {
            TransitionLog.always("avoid delegate transition to remote due to wmcore request");
            return true;
        }
        if (!avoidDelegateForKeyguardUnoccludeOpen(remoteTransition, transitionInfo)) {
            return false;
        }
        TransitionLog.always("avoidDelegateForKeyguardUnoccludeOpen true");
        return true;
    }

    public boolean cancelAnimationMergingForDesktopShortcut(TransitionInfo transitionInfo) {
        boolean zIsDeviceFolded = AdaptiveSmoothShellAnimManager.getInstance().isDeviceFolded();
        if (transitionInfo != null && transitionInfo.getType() == 1 && TransitionAnimationUtil.isFoldDevice() && !zIsDeviceFolded) {
            boolean zHasDisplayChange = TransitionUtil.hasDisplayChange(transitionInfo);
            for (TransitionInfo.Change change : transitionInfo.getChanges()) {
                ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
                if (TransitionUtil.isOpeningType(change.getMode()) && zHasDisplayChange && containDeskTopShortcut(taskInfo)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Animation checkAndLoadCustomAnimation(TransitionInfo.AnimationOptions animationOptions, int i, boolean z) {
        if (animationOptions == null) {
            TransitionLog.w("custom transition options null");
            return null;
        }
        String packageName = animationOptions.getPackageName();
        int enterResId = z ? animationOptions.getEnterResId() : animationOptions.getExitResId();
        StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i, "checkAndLoadCustomAnimation packageName =:", packageName, ",transit =:", ",enter =:");
        sbM.append(z);
        sbM.append(",animResId =:");
        sbM.append(enterResId);
        TransitionLog.always(sbM.toString());
        Animation animationForceOverrideCustomAnimation = forceOverrideCustomAnimation(animationOptions, i, z);
        if (animationForceOverrideCustomAnimation != null) {
            return animationForceOverrideCustomAnimation;
        }
        if (!ResourceId.isValid(enterResId) || ((-16777216) & enterResId) != 16777216) {
            return null;
        }
        if (i != 1 && i != 2) {
            return null;
        }
        AlphaAnimation alphaAnimation = z ? new AlphaAnimation(0.0f, 1.0f) : new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(150L);
        TransitionLog.always("checkAndLoadCustomAnimation use override anim for  =:" + packageName);
        return alphaAnimation;
    }

    public Animation forceLoadOplusAnim(TransitionInfo transitionInfo, TransitionInfo.Change change, boolean z, boolean z2, int i) {
        TransitionLog.always("forceLoadOplusAnim now");
        boolean z3 = change.getTaskInfo() != null;
        int type = transitionInfo.getType();
        int resIdInner = getResIdInner(transitionInfo, z2, z, false, false);
        AdaptiveSmoothShellAnimManager.getInstance().getSpringSlideAnimationController().markUseSpringSlideAnim(transitionInfo, resIdInner, change);
        return getAnimationbyResId(resIdInner, type, change, z3, i);
    }

    public int getFlags(TransitionInfo transitionInfo) {
        Integer num = (Integer) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_EXT_FLAGS, null, new Object[0]);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public Animation getOplusDefaultTransition(TransitionInfo transitionInfo, TransitionInfo.Change change, boolean z, boolean z2, int i, int i2) {
        this.mNeedWindowCornerRadius = false;
        boolean z3 = change.getTaskInfo() != null;
        int windowModeFromChange = OplusEmbedReflectionUtils.getWindowModeFromChange(change);
        Bundle flexibleTaskBundleFromChange = FlexibleTaskTransitionUtils.getFlexibleTaskBundleFromChange(change);
        boolean z4 = flexibleTaskBundleFromChange != null ? flexibleTaskBundleFromChange.getBoolean("flexible_activity_transition") : false;
        if (!matchOplusStyleCondition(transitionInfo, change, z2, i2)) {
            return null;
        }
        int type = transitionInfo.getType();
        int resIdInner = getResIdInner(transitionInfo, z, checkNeedAlpha(transitionInfo, change), z3, z4);
        boolean z5 = z3;
        if (i == 3 || i == 5) {
            this.mNeedWindowCornerRadius = true;
            resIdInner = z ? R.anim.oplus_wallpaper_close_enter2 : R.anim.oplus_wallpaper_close_exit2;
        } else if (i != 0) {
            return null;
        }
        int i3 = resIdInner;
        if (!z5 && (windowModeFromChange == 6 || z4)) {
            this.mNeedWindowCornerRadius = false;
        }
        AdaptiveSmoothShellAnimManager.getInstance().getSpringSlideAnimationController().markUseSpringSlideAnim(transitionInfo, i3, change);
        return getAnimationbyResId(i3, type, change, z5, i);
    }

    public float getRoundedCornersIfNeed(TransitionInfo transitionInfo, int i, int i2, boolean z, Animation animation, TransitionInfo.AnimationOptions animationOptions) {
        boolean zIsAllowLightOSCornerRadius = isAllowLightOSCornerRadius(transitionInfo);
        float windowCornerRadius = getWindowCornerRadius(zIsAllowLightOSCornerRadius);
        if (zIsAllowLightOSCornerRadius && windowCornerRadius > 0.0f) {
            animation.setHasRoundedCorners(true);
            TransitionLog.always("allowLightOSCornerRadius setHasRoundedCorners true, anim=" + animation);
        }
        if (animation.hasRoundedCorners()) {
            TransitionLog.debug("animation has round corner " + windowCornerRadius);
            return windowCornerRadius;
        }
        if (animation instanceof AlphaAnimation) {
            return 0.0f;
        }
        boolean z2 = false;
        if (animation instanceof AnimationSet) {
            AnimationSet animationSet = (AnimationSet) animation;
            if (animationSet.getAnimations().size() == 1 && (animationSet.getAnimations().get(0) instanceof AlphaAnimation)) {
                return 0.0f;
            }
        }
        if (!OplusShellLightOSTransition.IS_LIGHTOS) {
            if ((i >= 1 && i <= 5) || (i2 >= 2 && i2 <= 5)) {
                boolean z3 = (animationOptions != null ? animationOptions.getType() : 0) == 1;
                if (!z3 && animationOptions != null) {
                    int enterResId = z ? animationOptions.getEnterResId() : animationOptions.getExitResId();
                    if (!ResourceId.isValid(enterResId) || (enterResId & (-16777216)) == 16777216) {
                        return 0.0f;
                    }
                }
                String packageName = animationOptions != null ? animationOptions.getPackageName() : PACKAGE_SYSTEM;
                if (!TransitionAnimationUtil.isSystemApp(packageName)) {
                    return 0.0f;
                }
                if (TransitionUtil.isOpeningType(i)) {
                    z2 = z;
                } else if (TransitionUtil.isClosingType(i)) {
                    z2 = !z;
                }
                StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("addRoundedCornersToAnimationIfNeed for packageName =:", packageName, ", needRoundedCorners =:", z2, ",enter =:");
                sbM.append(z);
                sbM.append(",isCustomAnimation =:");
                sbM.append(z3);
                TransitionLog.debug(sbM.toString());
                if (z2) {
                }
            }
            return 0.0f;
        }
        if (i2 != 3 || !z) {
            return 0.0f;
        }
        return windowCornerRadius;
    }

    public float getWindowCornerRadiusForPredictBack() {
        if (this.mIsOldDevice) {
            return AdaptiveSmoothShellAnimManager.getInstance().isDeviceFolded() ? this.mBaseWindowCornerRadiusForSecond : this.mBaseWindowCornerRadiusForPrimary;
        }
        return this.mBaseWindowCornerRadius;
    }

    public boolean inSkipOverrideSet(TransitionInfo transitionInfo) {
        ComponentName componentName;
        if (transitionInfo != null && transitionInfo.getChanges().size() >= 1) {
            for (int i = 0; i < transitionInfo.getChanges().size(); i++) {
                ActivityManager.RunningTaskInfo taskInfo = ((TransitionInfo.Change) transitionInfo.getChanges().get(i)).getTaskInfo();
                if (taskInfo != null && (componentName = taskInfo.realActivity) != null && PKG_SKIP_OVERRIDE.contains(componentName.getPackageName())) {
                    TransitionLog.always("inSkipOverrideSet because in PKG_SKIP_OVERRIDE:" + componentName.getPackageName());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isLargeScreenTaskSwitch(TransitionInfo transitionInfo) {
        boolean z;
        boolean z2 = false;
        if (transitionInfo.getChanges().size() <= 1) {
            return false;
        }
        int type = transitionInfo.getType();
        if (type != 1 && type != 3 && type != 2 && type != 4) {
            return false;
        }
        boolean z3 = TransitionAnimationUtil.isFoldDevice() || TransitionAnimationUtil.isTabletDevice();
        boolean zIsMinimizedPocketStudio = FlexibleWindowManager.getInstance().isMinimizedPocketStudio(0);
        int iFlexibleWindowManager_getFlexibleMinimizedTaskId = zIsMinimizedPocketStudio ? ReflectionHelper.FlexibleWindowManager_getFlexibleMinimizedTaskId() : -1;
        int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1);
        boolean z4 = false;
        int windowModeFromChange = 0;
        boolean z5 = true;
        while (true) {
            if (iM >= 0) {
                TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(iM);
                ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
                if (taskInfo != null) {
                    if (!change.hasFlags(4)) {
                        z4 = taskInfo.configuration.smallestScreenWidthDp >= 600;
                        windowModeFromChange = OplusEmbedReflectionUtils.getWindowModeFromChange(change);
                        if ((windowModeFromChange == 1 || windowModeFromChange == 120) && !skipLargeScreenAnimation(transitionInfo, change)) {
                            if (z5) {
                                z5 = iFlexibleWindowManager_getFlexibleMinimizedTaskId == taskInfo.taskId;
                            }
                            iM--;
                        }
                    }
                    z = true;
                    break;
                }
                if (change.getTaskFragmentToken() == null) {
                    z = true;
                    break;
                }
                iM--;
            } else {
                z = false;
                break;
            }
        }
        if ((z4 || (zIsMinimizedPocketStudio && z5)) && z3 && !z) {
            z2 = true;
        }
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("check large screen, type: ", type, ", largeScreen: ", ", windowMode: ", z4);
        ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(windowModeFromChange, ", isMinimizedPocketStudio: ", ", isMinimizedTask: ", sbM, zIsMinimizedPocketStudio);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", skipLargeScreenAnim: ", ", largeScreenTaskSwitch: ", sbM, z5, z);
        sbM.append(z2);
        TransitionLog.debug(sbM.toString());
        return z2;
    }

    public boolean isLauncherSelfRotationSupport(TransitionInfo transitionInfo) {
        return (getFlags(transitionInfo) & 1) != 0;
    }

    public boolean isWsnSwitch(int i, TransitionInfo.Change change) {
        ComponentName componentName;
        ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
        if (taskInfo == null || (componentName = taskInfo.topActivity) == null || !this.mSkipLargeScreenTaskSwitch.contains(componentName.getPackageName())) {
            return false;
        }
        TransitionLog.debug("isWsnSwitch , top change: " + taskInfo.topActivity.getPackageName());
        return true;
    }

    public Animation loadLargeScreenTaskSwitchAnimation(int i, boolean z) {
        int i2;
        boolean z2;
        if (z) {
            i2 = R.anim.oplus_task_open_enlarge_enter;
            z2 = true;
        } else {
            i2 = R.anim.oplus_task_open_enlarge_exit;
            z2 = false;
        }
        Animation animation = null;
        try {
            Context context = this.mContext;
            if (context != null) {
                Animation animationLoadAnimation = AnimationUtils.loadAnimation(context, i2);
                animationLoadAnimation.setHasRoundedCorners(z2);
                animation = animationLoadAnimation;
            }
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("load oplus style animation for large screen task switching,transit =: ", i, ",isEnter =:", ",resId =: ", z);
            sbM.append(Integer.toHexString(i2));
            sbM.append(",needWindowCornerRadius =:");
            sbM.append(z2);
            TransitionLog.debug(sbM.toString());
            return animation;
        } catch (Resources.NotFoundException | InflateException unused) {
            TransitionLog.always("Unable to load animation resource");
            return null;
        }
    }

    public Animation loadWsnTaskSwitchAnimation(int i, boolean z) {
        if (!z) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setInterpolator(new AccelerateInterpolator(1.0f));
            alphaAnimation.setDuration(100L);
            return alphaAnimation;
        }
        int i2 = R.anim.oplus_open_slide_enter;
        Animation animation = null;
        try {
            Context context = this.mContext;
            if (context != null) {
                Animation animationLoadAnimation = AnimationUtils.loadAnimation(context, i2);
                animationLoadAnimation.setHasRoundedCorners(true);
                animation = animationLoadAnimation;
            }
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("load oplus style animation for wsn task switching,transit =: ", i, ",isEnter =:", ",resId =: ", z);
            sbM.append(Integer.toHexString(i2));
            TransitionLog.debug(sbM.toString());
            return animation;
        } catch (Resources.NotFoundException | InflateException unused) {
            TransitionLog.always("Unable to load animation resource");
            return null;
        }
    }

    public void markUserIdTransition(RemoteTransition remoteTransition) {
        int userId = UserHandle.getUserId(Binder.getCallingUid());
        if (userId > -1) {
            ReflectionUtils.methodInvoke(remoteTransition, remoteTransition.getClass(), SET_USER_ID_TRANSITION, new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(userId)});
            TransitionLog.debug("mark userId: " + userId + " remote: " + remoteTransition);
        }
    }

    public void modifyFlexibleLeashIfNeed(boolean z, TransitionInfo transitionInfo, TransitionInfo.Change change, float f, float f2, SurfaceControl.Transaction transaction) {
        SurfaceControl leash;
        if (z && transitionInfo != null && change != null && transaction != null && change.getLeash() != null && change.getLeash().isValid() && transitionInfo.getChanges().size() == 3 && transitionInfo.getType() == 2 && change.getMode() == 6 && (leash = transitionInfo.getRoot(TransitionUtil.rootIndexFor(change, transitionInfo)).getLeash()) != null && leash.isValid() && leash.toString().contains("Transition Root: Task=") && hasToFrontAnim(transitionInfo)) {
            Rect rect = new Rect(change.getEndAbsBounds());
            rect.offsetTo(0, 0);
            TransitionLog.always("modifyFlexibleLeashIfNeed rootLeash = " + leash + ", adjustScale = " + f2 + ", adjustCornerRadius = " + f + ", clipRect = " + rect);
            transaction.setScale(change.getLeash(), f2, f2).setCrop(change.getLeash(), rect).setCornerRadius(change.getLeash(), f);
        }
    }

    public void onConfigurationChanged(int i, DisplayController displayController) {
        Display display = displayController != null ? displayController.getDisplay(i) : null;
        if (display != null) {
            DisplayInfo displayInfo = new DisplayInfo();
            display.getDisplayInfo(displayInfo);
            int i2 = displayInfo.modeId;
            if (this.mCurrentWindowCornerRadiusModeId == i2) {
                return;
            }
            this.mBaseWindowCornerRadius = getCornerRadius(display);
            this.mCurrentWindowCornerRadiusModeId = i2;
            TransitionLog.debug("OplusTransitionAnimationManager mBaseWindowCornerRadius=" + this.mBaseWindowCornerRadius + ", mCurrentWindowCornerRadiusModeId =" + this.mCurrentWindowCornerRadiusModeId);
        }
    }

    public void registerReceiverForPkgRemoved() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        this.mContext.registerReceiver(new PackageRemovedReceiver(this, 0), intentFilter);
    }

    public void setAlphaForCustom(TransitionInfo transitionInfo, TransitionInfo.Change change, SurfaceControl.Transaction transaction, Animation animation) {
        boolean zIsCustomAnimaiton = isCustomAnimaiton(transitionInfo, change);
        boolean zVisibleIfNeed = visibleIfNeed(transitionInfo, change);
        if (animation == null && zIsCustomAnimaiton && zVisibleIfNeed) {
            transaction.setAlpha(change.getLeash(), 1.0f);
        }
    }

    public boolean shouldHideSurface(TransitionInfo.Change change) {
        if (change.getTaskInfo() == null && change.getActivityTransitionInfo() == null && change.getLeash() != null) {
            String string = change.getLeash().toString();
            if (string.startsWith(DTA, 13)) {
                TransitionLog.always("skip hide surface DefaultTaskDisplayArea on shouldHideSurface.");
                return false;
            }
            if (string.startsWith(APP_ZOOM_OUT, 13)) {
                TransitionLog.debug("skip hide surface AppZoomOut on shouldHideSurface.");
                return false;
            }
        }
        return true;
    }

    public boolean shouldKeepOverride(TransitionInfo transitionInfo) {
        TransitionInfo.Change change;
        TransitionInfo.AnimationOptions animationOptions;
        if (transitionInfo == null) {
            return false;
        }
        if (inSkipOverrideSet(transitionInfo)) {
            return true;
        }
        if (transitionInfo.getChanges().size() == 1 && (animationOptions = (change = (TransitionInfo.Change) transitionInfo.getChanges().get(0)).getAnimationOptions()) != null && !TextUtils.isEmpty(animationOptions.getPackageName()) && PKG_GLOBAL_SEARCH.contains(animationOptions.getPackageName())) {
            int type = animationOptions.getType();
            boolean zIsOpeningType = TransitionUtil.isOpeningType(change.getMode());
            if (change.getActivityComponent() != null && type == 14 && animationOptions.getCustomActivityTransition(zIsOpeningType) != null) {
                TransitionLog.always("shouldKeepOverride because only a change");
                return true;
            }
        }
        return false;
    }

    public boolean shouldLoadOplusAnimForSwipeUpBrowser(TransitionInfo transitionInfo, TransitionInfo.Change change) {
        if (transitionInfo == null || change == null) {
            return false;
        }
        if (!TransitionUtil.isClosingType(change.getMode()) || (change.getFlags() & 4) == 0) {
            return isSwipeUpBrowserPageSwitch(transitionInfo);
        }
        return false;
    }

    public boolean shouldSkipSetupWallpaper(TransitionInfo transitionInfo) {
        if (transitionInfo == null || !TransitionUtil.isClosingType(transitionInfo.getType())) {
            return false;
        }
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if (change.getTaskInfo() != null && TransitionUtil.isClosingType(change.getMode())) {
                Bundle windowInfoBundleFromChange = OplusTransitionReflectionHelper.getWindowInfoBundleFromChange(change);
                if ((windowInfoBundleFromChange != null ? windowInfoBundleFromChange.getBoolean(KEY_HAS_BALL_ALLOW_SAW, false) : false) && (transitionInfo.getFlags() & 64) != 0 && (transitionInfo.getFlags() & FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) != 0) {
                    TransitionLog.always("shouldSkipSetupWallpaper to avoid it being hide, this task has permission to show on lockscreen, " + change);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean showTargetLeashWhenMergeToRecentIfNeed(TransitionInfo transitionInfo, TransitionInfo.Change change) {
        if (transitionInfo == null || transitionInfo.getChanges().size() <= 3 || transitionInfo.getType() != 3 || (getFlags(transitionInfo) & 16) == 0 || change.getTaskInfo() == null || change.getMode() != 3 || !change.hasFlags(ShellContinuousTransitionController.TRANSIT_FLAG_VIEW_SEAMLESS_REMOTE) || change.getTaskInfo().getActivityType() == 2) {
            return false;
        }
        TransitionLog.debug("merge to recent force show :" + change);
        return true;
    }

    public void updateAlphaIfNeed(TransitionInfo transitionInfo, TransitionInfo.Change change, SurfaceControl.Transaction transaction) {
        if (visibleIfNeed(transitionInfo, change)) {
            transaction.setAlpha(change.getLeash(), 1.0f);
            TransitionLog.debug("updateAlphaIfNeed 1  " + change.getLeash());
        }
    }

    public boolean useOriginalAnimRes(TransitionInfo transitionInfo) {
        return ((Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, GET_USE_ORIGINAL_ANIM_RES, null, new Object[0])).booleanValue();
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    class PackageRemovedReceiver extends BroadcastReceiver {
        public /* synthetic */ PackageRemovedReceiver(OplusAnimationController oplusAnimationController, int i) {
            this(oplusAnimationController);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getData() == null || !"android.intent.action.PACKAGE_REMOVED".equals(intent.getAction())) {
                return;
            }
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            AttributeCache attributeCacheInstance = AttributeCache.instance();
            TransitionLog.debug("onReceive, package removed, pkgName = " + schemeSpecificPart + ", ac = " + attributeCacheInstance);
            if (attributeCacheInstance == null || schemeSpecificPart == null) {
                return;
            }
            attributeCacheInstance.removePackage(schemeSpecificPart);
        }

        private PackageRemovedReceiver(OplusAnimationController oplusAnimationController) {
        }
    }
}

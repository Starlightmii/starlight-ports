package com.oplus.systemui.navigationbar.gesture.proxy;

import android.R;
import android.content.Context;
import android.os.UserManager;
import android.provider.Settings;
import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import androidx.collection.FloatList$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.settingslib.volume.MediaSessions$MediaControllerRecord$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.multiuser.OplusMultiUserManager;
import com.oplus.systemui.common.util.OTAUtils;
import com.oplus.systemui.navigationbar.taskbar.TaskbarUtils;
import com.oplus.systemui.navigationbar.utils.OcrScreenCompatVersion;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.NavBarFeatureOption;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavBarSettingsValueProxy extends AbsSettingsValueProxy {
    public static final Companion Companion = new Companion();
    public static final String DEBUG_GAME_MODE_VALUE = "debug_gamemode_value";
    private static final String IMMERSIVE_MODE_CONFIRMED = "confirmed";
    public static final String IPE_PALM_REJECTION = "ipe_palm_rejection";
    public static final String KEY_ASSIST_LONG_PRESS_HOME_ENABLED = "assist_long_press_home_enabled";
    private static final String KEY_DISABLE_GESTURES = "disable_gestures";
    public static final String KEY_DISPLAY_AUTO_HIDE_VIRTUAL_KEY_TOAST_TIMES = "display_auto_hide_virtual_key_toast_times";
    public static final String KEY_GESTURE_UP_ENTER_LANDSCAPE_TIPS_NUMBER = "up_gesture_enter_landscape_tips_num";
    private static final String KEY_IMMERSIVE_MODE = "immersive_mode_confirmations";
    public static final String KEY_INVALIDATE_HIDE_GESTURE_BAR_BY_OTA = "invalidate_hide_gesture_bar_by_ota";
    public static final String KEY_KEYBOARD_SHORT_MODEL = "current_keyboard_layout";
    public static final String KEY_KEYBOARD_SHORT_SELECTED_MODEL = "current_hima_keyboard_layout_settings";
    private static final String KEY_LAUNCHER_SLID_SLIP_ENABLE = "launcher_slid_slip_enable_state";
    public static final String KEY_MOVE_LAST_NAVIGATION_STATE = "move_last_navigation_state";
    public static final String KEY_NAVBAR_HANDLE_WAKE_CUI = "oplus_gesture_handle_cui_enable";
    public static final String KEY_NAV_BAR_HIDE_STATE = "manual_hide_navigationbar";
    public static final String KEY_NAV_BAR_IMMERSIVE = "nav_bar_immersive";
    public static final String KEY_NAV_BUTTONS_POSITION = "navigation_buttons_position";
    public static final String KEY_NAV_BUTTONS_SWITCH_DIRECT = "navigation_buttons_switch_direct";
    private static final String KEY_NAV_GESTURE = "navigation_gesture";
    public static final String KEY_NAV_STATE = "hide_navigationbar_enable";
    public static final String KEY_NAV_TYPE_PROGRESS_GESTURE_GEAR_USER_TYPE = "nav_type_progress_gesture_gear_user";
    public static final String KEY_NEED_SHOW_GESTURE_GUIDE = "need_show_learn_gesture_guide";
    private static final String KEY_OPLUS_GESTURE_REGION_REDUCE_ENABLE = "oplus_gesture_region_reduce_enable";
    public static final String KEY_SWIPE_SIDE_GESTURE_BACK_VIBRATE_ENABLE = "gesture_side_back_vibrate_enable";
    public static final String KEY_SWIPE_SIDE_GESTURE_BAR_TYPE = "gesture_side_hide_bar_prevention_enable";
    public static final String KEY_SWIPE_SIDE_GESTURE_BAR_VISIBILITY_TYPE = "navbar_gesture_visibility";
    public static final String KEY_SWIPE_SIDE_GESTURE_HAS_FEATURE = "has_feature_gesture_side";
    public static final String KEY_SWIPE_SIDE_GESTURE_MISTOUCH_PREVENTION_ENABLE = "gesture_mistouch_prevention_side_enable";
    public static final String KEY_SWIPE_SIDE_GESTURE_MISTOUCH_PREVENTION_TYPE = "gesture_mistouch_prevention_side_type";
    public static final String KEY_SWIPE_UP_GESTURE_BAR_TYPE = "hide_gesture_bar_enable";
    public static final String KEY_SWIPE_UP_GESTURE_KEYS_MODE = "settings_gesture_keys_mode";
    public static final String KEY_SWIPE_UP_GESTURE_MISTOUCH_PREVENTION_ENABLE = "gesture_mistouch_prevention_enable";
    public static final String KEY_TASKBAR_ENABLE = "enable_launcher_taskbar";
    public static final String KEY_VIRTUAL_KEY_COMBINATION_TYPE = "settings_navigation_bar_combination";
    public static final String KEY_VIRTUAL_KEY_HIDE_ENABLE = "nav_bar_hide_enable";
    public static final String KEY_VIRTUAL_KEY_LONG_PRESS_HOME = "oplus_home_button_cui_enable";
    private static final String LAUNCHER_LEFT_SLID_SLIP_ENABLE_STATE = "launcher_left_slid_slip_enable_state";
    public static final String LAUNCHER_RIGHT_SLID_SLIP_ENABLE_STATE = "launcher_right_slid_slip_enable_state";
    public static final String NAV_TYPE_PROGRESS_GESTURE_GEAR_USER_INIT = "-1,-1,-1,-1";
    public static final String OPLUS_HOME_BUTTON_WAKE_UP_OCR = "oplus_home_button_wake_up_ocr_enable";
    public static final String OPLUS_HOME_BUTTON_WAKE_UP_OCR_DIALOG = "oplus_home_button_wake_up_ocr_dialog";
    public static final String OPLUS_HOME_HANDLE_WAKE_UP_OCR = "oplus_home_handle_wake_up_ocr_enable";
    public static final String OPLUS_HOME_HANDLE_WAKE_UP_OCR_DIALOG = "oplus_home_handle_wake_up_ocr_dialog";
    public static final String SUPER_POWERSAVE_LAUNCHER_ENTER = "super_powersave_launcher_enter";
    private static final String TAG = "NavBarSettingsValueProxy";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final int getDisplayAutoHideVirtualKeyToastTimes(Context context) {
            if (context != null) {
                return AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_DISPLAY_AUTO_HIDE_VIRTUAL_KEY_TOAST_TIMES, -1);
            }
            return -1;
        }

        public final int getGameModeState(Context context) {
            return AbsSettingsValueProxy.Companion.getGlobalIntValue(context, NavBarSettingsValueProxy.DEBUG_GAME_MODE_VALUE, -1);
        }

        public final int getInvalidateHideGestureBarByOtaState(Context context) {
            if (context == null) {
                return -1;
            }
            int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_INVALIDATE_HIDE_GESTURE_BAR_BY_OTA, -1);
            if (secureIntValue == 0 || secureIntValue == 1) {
                return secureIntValue;
            }
            return -1;
        }

        public final int getIpeSlideGestureState(Context context) {
            return AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.IPE_PALM_REJECTION, 0);
        }

        public final int getKIDSMode(Context context, int i) {
            if (context != null) {
                return AbsSettingsValueProxy.Companion.getSecureIntValue(context, "nav_bar_kids_mode", -1, i);
            }
            return -1;
        }

        public final int getKeyboardShortModel(Context context) {
            return AbsSettingsValueProxy.Companion.getGlobalIntValue(context, NavBarSettingsValueProxy.KEY_KEYBOARD_SHORT_MODEL, -1);
        }

        public final int getLauncherSlidSlipEnable(Context context) {
            if (context == null) {
                return -1;
            }
            int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_LAUNCHER_SLID_SLIP_ENABLE, 1);
            if (secureIntValue == 0 || secureIntValue == 1) {
                return secureIntValue;
            }
            return -1;
        }

        public final int getLongPressHomeWakeAssistEnable(Context context) {
            if (context == null) {
                return -1;
            }
            int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_ASSIST_LONG_PRESS_HOME_ENABLED, -1);
            if (secureIntValue == 0 || secureIntValue == 1) {
                return secureIntValue;
            }
            return -1;
        }

        public final int getMoveLastNavigationState(Context context, int i) {
            return getSystemUserSecureValue(context, NavBarSettingsValueProxy.KEY_MOVE_LAST_NAVIGATION_STATE, i);
        }

        public final int getNavBarHideState(Context context) {
            if (context == null) {
                return -1;
            }
            int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_NAV_BAR_HIDE_STATE, -1);
            if (secureIntValue == 0 || secureIntValue == 1) {
                return secureIntValue;
            }
            return -1;
        }

        public final int getNavBarImmersiveEnable(Context context) {
            if (context == null) {
                return -1;
            }
            int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_NAV_BAR_IMMERSIVE, 0);
            if (secureIntValue == 0 || secureIntValue == 1) {
                return secureIntValue;
            }
            return -1;
        }

        public final int getNavButtonsPosition(Context context) {
            if (context != null) {
                return AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_NAV_BUTTONS_POSITION, -1);
            }
            return -1;
        }

        public final int getNavButtonsSwitchDirect(Context context) {
            if (context != null) {
                return AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_NAV_BUTTONS_SWITCH_DIRECT, -1);
            }
            return -1;
        }

        public final String getNavGestureProgressGearUserState(Context context) {
            return AbsSettingsValueProxy.Companion.getSecureStringValue(context, NavBarSettingsValueProxy.KEY_NAV_TYPE_PROGRESS_GESTURE_GEAR_USER_TYPE);
        }

        public final int getNavGestureState(Context context) {
            if (context == null) {
                return -1;
            }
            int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_NAV_GESTURE, -1);
            if (secureIntValue == 0 || secureIntValue == 1) {
                return secureIntValue;
            }
            return -1;
        }

        public final int getNavState(Context context) {
            if (context == null) {
                return -1;
            }
            int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_NAV_STATE, -1);
            if (secureIntValue != 0) {
                if (secureIntValue == 1) {
                    return 0;
                }
                if (secureIntValue != 2 && secureIntValue != 3) {
                    return -1;
                }
            }
            return secureIntValue;
        }

        public final int getNavType(Context context) {
            int navState = getNavState(context);
            if (navState == 0 || navState == 1) {
                return 0;
            }
            if (navState != 2) {
                return navState != 3 ? -1 : 2;
            }
            return 1;
        }

        public final int getNavbarHandlerCuiEnable(Context context) {
            if (context == null) {
                return -1;
            }
            int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_NAVBAR_HANDLE_WAKE_CUI, -1);
            if (secureIntValue == 0 || secureIntValue == 1) {
                return secureIntValue;
            }
            return -1;
        }

        public final int getNavbarHandlerOcrDialogEnable(Context context) {
            if (context == null) {
                return -1;
            }
            int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.OPLUS_HOME_HANDLE_WAKE_UP_OCR_DIALOG, -1);
            if (secureIntValue == 0 || secureIntValue == 1) {
                return secureIntValue;
            }
            return -1;
        }

        public final int getNavbarHandlerOcrEnable(Context context) {
            if (context == null) {
                return -1;
            }
            int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.OPLUS_HOME_HANDLE_WAKE_UP_OCR, -1);
            if (secureIntValue == 0 || secureIntValue == 1) {
                return secureIntValue;
            }
            return -1;
        }

        public final int getNeedShowGestureGuide(Context context) {
            if (context != null) {
                return AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_NEED_SHOW_GESTURE_GUIDE, -1);
            }
            return -1;
        }

        public final int getSuperPowerSaveState(Context context) {
            return AbsSettingsValueProxy.Companion.getSystemIntValue(context, "super_powersave_launcher_enter", -1);
        }

        public final int getSwipeSideEnableBackVibrate(Context context) {
            if (context == null) {
                return -1;
            }
            int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_SWIPE_SIDE_GESTURE_BACK_VIBRATE_ENABLE, -1);
            if (secureIntValue == 0 || secureIntValue == 1) {
                return secureIntValue;
            }
            return -1;
        }

        public final int getSwipeSideGestureBarType(Context context) {
            if (context == null) {
                return -1;
            }
            int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_SWIPE_SIDE_GESTURE_BAR_TYPE, -1);
            if (secureIntValue == 0 || secureIntValue == 1) {
                return secureIntValue;
            }
            return -1;
        }

        public final int getSwipeSideGestureMistouchPreventionEnable(Context context) {
            if (context == null) {
                return -1;
            }
            int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_SWIPE_SIDE_GESTURE_MISTOUCH_PREVENTION_ENABLE, -1);
            if (secureIntValue == 0 || secureIntValue == 1) {
                return secureIntValue;
            }
            return -1;
        }

        public final int getSwipeSideGestureMistouchPreventionType(Context context) {
            return AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_SWIPE_SIDE_GESTURE_MISTOUCH_PREVENTION_TYPE, -1);
        }

        public final int getSwipeUpGestureBarType(Context context) {
            if (context == null) {
                return -1;
            }
            int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_SWIPE_UP_GESTURE_BAR_TYPE, -1);
            if (secureIntValue == 0 || secureIntValue == 1) {
                return secureIntValue;
            }
            return -1;
        }

        public final int getSwipeUpGestureKeysMode(Context context) {
            int secureIntValue = -1;
            if (context == null || (secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_SWIPE_UP_GESTURE_KEYS_MODE, -1)) == 0 || secureIntValue == 1 || secureIntValue == 2 || secureIntValue == 3) {
                return secureIntValue;
            }
            return 2;
        }

        public final int getSwipeUpGestureMistouchPreventionEnable(Context context) {
            if (context == null) {
                return -1;
            }
            int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_SWIPE_UP_GESTURE_MISTOUCH_PREVENTION_ENABLE, -1);
            if (secureIntValue == 0 || secureIntValue == 1) {
                return secureIntValue;
            }
            return -1;
        }

        public final int getSystemUserSecureValue(Context context, String str, int i) {
            return AbsSettingsValueProxy.Companion.getSecureIntValue(context, str, i, 0);
        }

        public final int getTaskbarEnable(Context context) {
            return AbsSettingsValueProxy.Companion.getSystemIntValue(context, NavBarSettingsValueProxy.KEY_TASKBAR_ENABLE, 1);
        }

        public final int getUpGestureEnterLandScapeTipsNumber(Context context) {
            if (context != null) {
                return AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_GESTURE_UP_ENTER_LANDSCAPE_TIPS_NUMBER, -1);
            }
            return -1;
        }

        public final int getVirtualKeyCombinationType(Context context) {
            if (context == null) {
                return -1;
            }
            int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_VIRTUAL_KEY_COMBINATION_TYPE, -1);
            if (secureIntValue == 0 || secureIntValue == 1 || secureIntValue == 2 || secureIntValue == 3) {
                return secureIntValue;
            }
            return -1;
        }

        public final int getVirtualKeyHideEnable(Context context) {
            if (context == null) {
                return -1;
            }
            int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_VIRTUAL_KEY_HIDE_ENABLE, -1);
            if (secureIntValue == 0 || secureIntValue == 1) {
                return secureIntValue;
            }
            return -1;
        }

        public final int getVirtualKeyLongPressHomeCuiEnable(Context context) {
            if (context == null) {
                return -1;
            }
            int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.KEY_VIRTUAL_KEY_LONG_PRESS_HOME, -1);
            if (secureIntValue == 0 || secureIntValue == 1) {
                return secureIntValue;
            }
            return -1;
        }

        public final int getVirtualKeyLongPressHomeOcrDialogEnable(Context context) {
            if (context == null) {
                return -1;
            }
            int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.OPLUS_HOME_BUTTON_WAKE_UP_OCR_DIALOG, -1);
            if (secureIntValue == 0 || secureIntValue == 1) {
                return secureIntValue;
            }
            return -1;
        }

        public final int getVirtualKeyLongPressHomeOcrEnable(Context context) {
            if (context == null) {
                return -1;
            }
            int secureIntValue = AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.OPLUS_HOME_BUTTON_WAKE_UP_OCR, -1);
            if (secureIntValue == 0 || secureIntValue == 1) {
                return secureIntValue;
            }
            return -1;
        }

        public final boolean isLauncherLeftSlidSlipEnable(Context context) {
            return context != null && AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.LAUNCHER_LEFT_SLID_SLIP_ENABLE_STATE, 0) == 0;
        }

        public final boolean isLauncherRightSlidSlipEnable(Context context) {
            return context != null && AbsSettingsValueProxy.Companion.getSecureIntValue(context, NavBarSettingsValueProxy.LAUNCHER_RIGHT_SLID_SLIP_ENABLE_STATE, 0) == 0;
        }

        public final void setDisableState(Context context, int i) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "setDisableState:", "NavBarSettingsValueProxy-->", "SystemUi--NavBar");
            }
            if (context != null) {
                AbsSettingsValueProxy.Companion.setSecureIntValue(context, NavBarSettingsValueProxy.KEY_DISABLE_GESTURES, i, -2);
            }
        }

        public final void setDisplayAutoHideVirtualKeyToastTimes(Context context, int i) {
            if (context != null) {
                NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_DISPLAY_AUTO_HIDE_VIRTUAL_KEY_TOAST_TIMES, i);
            }
        }

        public final void setGestureRegionReduceEnableState(Context context, int i) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "setGestureRegionReduceEnableState:", "NavBarSettingsValueProxy-->", "SystemUi--NavBar");
            }
            if (context != null) {
                AbsSettingsValueProxy.Companion.setSecureIntValue(context, NavBarSettingsValueProxy.KEY_OPLUS_GESTURE_REGION_REDUCE_ENABLE, i, -2);
            }
        }

        public final void setInvalidateHideGestureBarByOtaState(Context context, int i) {
            if (context != null) {
                if (i == 0 || i == 1) {
                    NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_INVALIDATE_HIDE_GESTURE_BAR_BY_OTA, i);
                }
            }
        }

        public final void setKeyboardShortSelectedModel(Context context, int i) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYBOARD)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "setKeyboardShortSelectedModel :", "NavBarSettingsValueProxy-->", "SystemUi--KeyBoard");
            }
            AbsSettingsValueProxy.Companion.setGlobalIntValue(context, NavBarSettingsValueProxy.KEY_KEYBOARD_SHORT_SELECTED_MODEL, i);
        }

        public final void setLauncherSlidSlipEnable(Context context, int i) {
            if (i == 0 || i == 1) {
                AbsSettingsValueProxy.Companion.setSecureIntValue(context, NavBarSettingsValueProxy.KEY_LAUNCHER_SLID_SLIP_ENABLE, i);
            }
        }

        public final void setLongPressHomeWakeAssistEnable(Context context, int i) {
            if (context != null) {
                if (i == 0 || i == 1) {
                    NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_ASSIST_LONG_PRESS_HOME_ENABLED, i);
                }
            }
        }

        public final void setMoveLastNavigationState(Context context, int i) {
            setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_MOVE_LAST_NAVIGATION_STATE, i);
        }

        public final void setMultiSystemUserSecureIntValue(Context context, String str, int i) {
            int multiSystemUserId = OplusMultiUserManager.getInstance().getMultiSystemUserId();
            if (multiSystemUserId != -10000) {
                int currentUser = KeyguardUpdateMonitor.getCurrentUser();
                boolean zIsMultiSystemUserId = OplusMultiUserManager.getInstance().isMultiSystemUserId(currentUser);
                boolean z = currentUser == 0;
                if (zIsMultiSystemUserId) {
                    AbsSettingsValueProxy.Companion.setSecureIntValue(context, str, i, 0);
                } else if (z) {
                    AbsSettingsValueProxy.Companion.setSecureIntValue(context, str, i, multiSystemUserId);
                }
            }
            AbsSettingsValueProxy.Companion.setSecureIntValue(context, str, i, -2);
        }

        public final void setMultiSystemUserSecureStringValue(Context context, String str, String str2) {
            int multiSystemUserId = OplusMultiUserManager.getInstance().getMultiSystemUserId();
            if (multiSystemUserId != -10000) {
                int currentUser = KeyguardUpdateMonitor.getCurrentUser();
                boolean zIsMultiSystemUserId = OplusMultiUserManager.getInstance().isMultiSystemUserId(currentUser);
                boolean z = currentUser == 0;
                if (zIsMultiSystemUserId) {
                    AbsSettingsValueProxy.Companion.setSecureStringValue(context, str, str2, 0);
                } else if (z) {
                    AbsSettingsValueProxy.Companion.setSecureStringValue(context, str, str2, multiSystemUserId);
                }
            }
            AbsSettingsValueProxy.Companion.setSecureStringValue(context, str, str2, -2);
        }

        public final void setNavBarHideState(Context context, int i) {
            if (context != null) {
                if (i == 0 || i == 1) {
                    NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_NAV_BAR_HIDE_STATE, i);
                }
            }
        }

        public final void setNavBarImmersiveEnable(Context context, int i) {
            if (context != null) {
                if (i == 0 || i == 1) {
                    NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_NAV_BAR_IMMERSIVE, i);
                }
            }
        }

        public final void setNavButtonsPosition(Context context, int i) {
            if (context != null) {
                NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_NAV_BUTTONS_POSITION, i);
            }
        }

        public final void setNavButtonsSwitchDirect(Context context, int i) {
            if (context != null) {
                NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_NAV_BUTTONS_SWITCH_DIRECT, i);
            }
        }

        public final void setNavGestureProgressGearUserState(Context context, String str) {
            setMultiSystemUserSecureStringValue(context, NavBarSettingsValueProxy.KEY_NAV_TYPE_PROGRESS_GESTURE_GEAR_USER_TYPE, str);
        }

        public final void setNavGestureState(Context context, int i) {
            if (context != null) {
                if (i == 0) {
                    NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_NAV_GESTURE, 0);
                } else if (i == 2 || i == 3) {
                    NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_NAV_GESTURE, 1);
                }
            }
        }

        public final void setNavState(Context context, int i) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("setNavState:", i, KeyguardUpdateMonitor.getCurrentUser(), " userId = ", "NavBarSettingsValueProxy-->"));
            }
            if (i == 1) {
                i = 0;
            }
            if ((i == 0 || i == 2 || i == 3) && context != null) {
                NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_NAV_STATE, i);
            }
        }

        public final void setNavbarHandlerCuiEnable(Context context, int i) {
            if ((i == 0 || i == 1) && context != null) {
                NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_NAVBAR_HANDLE_WAKE_CUI, i);
            }
        }

        public final void setNavbarHandlerOcrDialogEnable(Context context, int i) {
            if (context != null) {
                if (i == 0 || i == 1) {
                    NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.OPLUS_HOME_HANDLE_WAKE_UP_OCR_DIALOG, i);
                }
            }
        }

        public final void setNavbarHandlerOcrEnable(Context context, int i) {
            if (context != null) {
                if (i == 0 || i == 1) {
                    NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.OPLUS_HOME_HANDLE_WAKE_UP_OCR, i);
                }
            }
        }

        public final void setNeedShowGestureGuide(Context context, int i) {
            if (context != null) {
                NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_NEED_SHOW_GESTURE_GUIDE, i);
            }
        }

        public final void setSwipeSideEnableBackVibrate(Context context, int i) {
            if (context != null) {
                if (i == 0 || i == 1) {
                    NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_SWIPE_SIDE_GESTURE_BACK_VIBRATE_ENABLE, i);
                }
            }
        }

        public final void setSwipeSideGestureBarType(Context context, int i) {
            if (context != null) {
                if (i == 0 || i == 1) {
                    NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_SWIPE_SIDE_GESTURE_BAR_TYPE, i);
                }
            }
        }

        public final void setSwipeSideGestureBarVisibilityType(Context context, int i) {
            if (context != null) {
                if (i == 0 || i == 1) {
                    NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_SWIPE_SIDE_GESTURE_BAR_VISIBILITY_TYPE, i);
                }
            }
        }

        public final void setSwipeSideGestureMistouchPreventionEnable(Context context, int i) {
            if (context != null) {
                if (i == 0 || i == 1) {
                    NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_SWIPE_SIDE_GESTURE_MISTOUCH_PREVENTION_ENABLE, i);
                }
            }
        }

        public final void setSwipeSideGestureMistouchPreventionType(Context context, int i) {
            setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_SWIPE_SIDE_GESTURE_MISTOUCH_PREVENTION_TYPE, i);
        }

        public final void setSwipeUpGestureBarType(Context context, int i) {
            if (context != null) {
                if (i == 0 || i == 1) {
                    NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_SWIPE_UP_GESTURE_BAR_TYPE, i);
                }
            }
        }

        public final void setSwipeUpGestureKeysMode(Context context, int i) {
            if (context != null) {
                if (i == 0 || i == 1 || i == 2 || i == 3) {
                    NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_SWIPE_UP_GESTURE_KEYS_MODE, i);
                }
            }
        }

        public final void setSwipeUpGestureMistouchPreventionEnable(Context context, int i) {
            if (context != null) {
                if (i == 0 || i == 1) {
                    NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_SWIPE_UP_GESTURE_MISTOUCH_PREVENTION_ENABLE, i);
                }
            }
        }

        public final void setUpGestureEnterLandScapeTipsNumber(Context context, int i) {
            if (context != null) {
                NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_GESTURE_UP_ENTER_LANDSCAPE_TIPS_NUMBER, i);
            }
        }

        public final void setVirtualKeyCombinationType(Context context, int i) {
            if (context != null) {
                if (i == 0 || i == 1 || i == 2 || i == 3) {
                    NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_VIRTUAL_KEY_COMBINATION_TYPE, i);
                }
            }
        }

        public final void setVirtualKeyHideEnable(Context context, int i) {
            if (context != null) {
                if (i == 0 || i == 1) {
                    NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_VIRTUAL_KEY_HIDE_ENABLE, i);
                }
            }
        }

        public final void setVirtualKeyLongPressHomeCuiEnable(Context context, int i) {
            if ((i == 0 || i == 1) && context != null) {
                NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.KEY_VIRTUAL_KEY_LONG_PRESS_HOME, i);
            }
        }

        public final void setVirtualKeyLongPressHomeOcrDialogEnable(Context context, int i) {
            if (context != null) {
                if (i == 0 || i == 1) {
                    NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.OPLUS_HOME_BUTTON_WAKE_UP_OCR_DIALOG, i);
                }
            }
        }

        public final void setVirtualKeyLongPressHomeOcrEnable(Context context, int i) {
            if (context != null) {
                if (i == 0 || i == 1) {
                    NavBarSettingsValueProxy.Companion.setMultiSystemUserSecureIntValue(context, NavBarSettingsValueProxy.OPLUS_HOME_BUTTON_WAKE_UP_OCR, i);
                }
            }
        }
    }

    public static final int getGameModeState(Context context) {
        return Companion.getGameModeState(context);
    }

    public static final int getInvalidateHideGestureBarByOtaState(Context context) {
        return Companion.getInvalidateHideGestureBarByOtaState(context);
    }

    public static final int getIpeSlideGestureState(Context context) {
        return Companion.getIpeSlideGestureState(context);
    }

    public static final int getKIDSMode(Context context, int i) {
        return Companion.getKIDSMode(context, i);
    }

    public static final int getKeyboardShortModel(Context context) {
        return Companion.getKeyboardShortModel(context);
    }

    public static final int getLauncherSlidSlipEnable(Context context) {
        return Companion.getLauncherSlidSlipEnable(context);
    }

    public static final int getLongPressHomeWakeAssistEnable(Context context) {
        return Companion.getLongPressHomeWakeAssistEnable(context);
    }

    public static final int getMoveLastNavigationState(Context context, int i) {
        return Companion.getMoveLastNavigationState(context, i);
    }

    public static final int getNavBarHideState(Context context) {
        return Companion.getNavBarHideState(context);
    }

    public static final int getNavBarImmersiveEnable(Context context) {
        return Companion.getNavBarImmersiveEnable(context);
    }

    public static final int getNavButtonsPosition(Context context) {
        return Companion.getNavButtonsPosition(context);
    }

    public static final int getNavButtonsSwitchDirect(Context context) {
        return Companion.getNavButtonsSwitchDirect(context);
    }

    public static final String getNavGestureProgressGearUserState(Context context) {
        return Companion.getNavGestureProgressGearUserState(context);
    }

    public static final int getNavGestureState(Context context) {
        return Companion.getNavGestureState(context);
    }

    public static final int getNavState(Context context) {
        return Companion.getNavState(context);
    }

    public static final int getNavType(Context context) {
        return Companion.getNavType(context);
    }

    public static final int getNavbarHandlerCuiEnable(Context context) {
        return Companion.getNavbarHandlerCuiEnable(context);
    }

    public static final int getNavbarHandlerOcrDialogEnable(Context context) {
        return Companion.getNavbarHandlerOcrDialogEnable(context);
    }

    public static final int getNavbarHandlerOcrEnable(Context context) {
        return Companion.getNavbarHandlerOcrEnable(context);
    }

    public static final int getNeedShowGestureGuide(Context context) {
        return Companion.getNeedShowGestureGuide(context);
    }

    public static final int getSuperPowerSaveState(Context context) {
        return Companion.getSuperPowerSaveState(context);
    }

    public static final int getSwipeSideEnableBackVibrate(Context context) {
        return Companion.getSwipeSideEnableBackVibrate(context);
    }

    public static final int getSwipeSideGestureBarType(Context context) {
        return Companion.getSwipeSideGestureBarType(context);
    }

    public static final int getSwipeSideGestureMistouchPreventionEnable(Context context) {
        return Companion.getSwipeSideGestureMistouchPreventionEnable(context);
    }

    public static final int getSwipeSideGestureMistouchPreventionType(Context context) {
        return Companion.getSwipeSideGestureMistouchPreventionType(context);
    }

    public static final int getSwipeUpGestureBarType(Context context) {
        return Companion.getSwipeUpGestureBarType(context);
    }

    public static final int getSwipeUpGestureKeysMode(Context context) {
        return Companion.getSwipeUpGestureKeysMode(context);
    }

    public static final int getSwipeUpGestureMistouchPreventionEnable(Context context) {
        return Companion.getSwipeUpGestureMistouchPreventionEnable(context);
    }

    public static final int getSystemUserSecureValue(Context context, String str, int i) {
        return Companion.getSystemUserSecureValue(context, str, i);
    }

    public static final int getTaskbarEnable(Context context) {
        return Companion.getTaskbarEnable(context);
    }

    public static final int getUpGestureEnterLandScapeTipsNumber(Context context) {
        return Companion.getUpGestureEnterLandScapeTipsNumber(context);
    }

    public static final int getVirtualKeyCombinationType(Context context) {
        return Companion.getVirtualKeyCombinationType(context);
    }

    public static final int getVirtualKeyLongPressHomeCuiEnable(Context context) {
        return Companion.getVirtualKeyLongPressHomeCuiEnable(context);
    }

    public static final int getVirtualKeyLongPressHomeOcrDialogEnable(Context context) {
        return Companion.getVirtualKeyLongPressHomeOcrDialogEnable(context);
    }

    public static final int getVirtualKeyLongPressHomeOcrEnable(Context context) {
        return Companion.getVirtualKeyLongPressHomeOcrEnable(context);
    }

    public static final boolean isLauncherLeftSlidSlipEnable(Context context) {
        return Companion.isLauncherLeftSlidSlipEnable(context);
    }

    public static final boolean isLauncherRightSlidSlipEnable(Context context) {
        return Companion.isLauncherRightSlidSlipEnable(context);
    }

    public static final void setDisableState(Context context, int i) {
        Companion.setDisableState(context, i);
    }

    public static final void setGestureRegionReduceEnableState(Context context, int i) {
        Companion.setGestureRegionReduceEnableState(context, i);
    }

    public static final void setInvalidateHideGestureBarByOtaState(Context context, int i) {
        Companion.setInvalidateHideGestureBarByOtaState(context, i);
    }

    public static final void setKeyboardShortSelectedModel(Context context, int i) {
        Companion.setKeyboardShortSelectedModel(context, i);
    }

    public static final void setLauncherSlidSlipEnable(Context context, int i) {
        Companion.setLauncherSlidSlipEnable(context, i);
    }

    public static final void setLongPressHomeWakeAssistEnable(Context context, int i) {
        Companion.setLongPressHomeWakeAssistEnable(context, i);
    }

    public static final void setMoveLastNavigationState(Context context, int i) {
        Companion.setMoveLastNavigationState(context, i);
    }

    public static final void setMultiSystemUserSecureIntValue(Context context, String str, int i) {
        Companion.setMultiSystemUserSecureIntValue(context, str, i);
    }

    public static final void setMultiSystemUserSecureStringValue(Context context, String str, String str2) {
        Companion.setMultiSystemUserSecureStringValue(context, str, str2);
    }

    public static final void setNavBarHideState(Context context, int i) {
        Companion.setNavBarHideState(context, i);
    }

    public static final void setNavBarImmersiveEnable(Context context, int i) {
        Companion.setNavBarImmersiveEnable(context, i);
    }

    public static final void setNavButtonsPosition(Context context, int i) {
        Companion.setNavButtonsPosition(context, i);
    }

    public static final void setNavButtonsSwitchDirect(Context context, int i) {
        Companion.setNavButtonsSwitchDirect(context, i);
    }

    public static final void setNavGestureProgressGearUserState(Context context, String str) {
        Companion.setNavGestureProgressGearUserState(context, str);
    }

    public static final void setNavGestureState(Context context, int i) {
        Companion.setNavGestureState(context, i);
    }

    public static final void setNavState(Context context, int i) {
        Companion.setNavState(context, i);
    }

    public static final void setNavbarHandlerCuiEnable(Context context, int i) {
        Companion.setNavbarHandlerCuiEnable(context, i);
    }

    public static final void setNavbarHandlerOcrDialogEnable(Context context, int i) {
        Companion.setNavbarHandlerOcrDialogEnable(context, i);
    }

    public static final void setNavbarHandlerOcrEnable(Context context, int i) {
        Companion.setNavbarHandlerOcrEnable(context, i);
    }

    public static final void setNeedShowGestureGuide(Context context, int i) {
        Companion.setNeedShowGestureGuide(context, i);
    }

    public static final void setSwipeSideEnableBackVibrate(Context context, int i) {
        Companion.setSwipeSideEnableBackVibrate(context, i);
    }

    public static final void setSwipeSideGestureBarType(Context context, int i) {
        Companion.setSwipeSideGestureBarType(context, i);
    }

    public static final void setSwipeSideGestureBarVisibilityType(Context context, int i) {
        Companion.setSwipeSideGestureBarVisibilityType(context, i);
    }

    public static final void setSwipeSideGestureMistouchPreventionEnable(Context context, int i) {
        Companion.setSwipeSideGestureMistouchPreventionEnable(context, i);
    }

    public static final void setSwipeSideGestureMistouchPreventionType(Context context, int i) {
        Companion.setSwipeSideGestureMistouchPreventionType(context, i);
    }

    public static final void setSwipeUpGestureBarType(Context context, int i) {
        Companion.setSwipeUpGestureBarType(context, i);
    }

    public static final void setSwipeUpGestureKeysMode(Context context, int i) {
        Companion.setSwipeUpGestureKeysMode(context, i);
    }

    public static final void setSwipeUpGestureMistouchPreventionEnable(Context context, int i) {
        Companion.setSwipeUpGestureMistouchPreventionEnable(context, i);
    }

    public static final void setUpGestureEnterLandScapeTipsNumber(Context context, int i) {
        Companion.setUpGestureEnterLandScapeTipsNumber(context, i);
    }

    public static final void setVirtualKeyCombinationType(Context context, int i) {
        Companion.setVirtualKeyCombinationType(context, i);
    }

    public static final void setVirtualKeyHideEnable(Context context, int i) {
        Companion.setVirtualKeyHideEnable(context, i);
    }

    public static final void setVirtualKeyLongPressHomeCuiEnable(Context context, int i) {
        Companion.setVirtualKeyLongPressHomeCuiEnable(context, i);
    }

    public static final void setVirtualKeyLongPressHomeOcrDialogEnable(Context context, int i) {
        Companion.setVirtualKeyLongPressHomeOcrDialogEnable(context, i);
    }

    public static final void setVirtualKeyLongPressHomeOcrEnable(Context context, int i) {
        Companion.setVirtualKeyLongPressHomeOcrEnable(context, i);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x006b  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy
    public void checkValidity(Context context) {
        int systemUserSecureValue;
        LogUtil.i$default(LogUtil.TAG_NAVBAR, getTAG(), "checkValidity: start", null, 8);
        boolean zIsMultiSystemUserId = OplusMultiUserManager.getInstance().isMultiSystemUserId(KeyguardUpdateMonitor.getCurrentUser());
        Companion companion = Companion;
        if (companion.getNavState(context) == -1) {
            if (zIsMultiSystemUserId) {
                companion.setNavState(context, companion.getSystemUserSecureValue(context, KEY_NAV_STATE, -1));
            } else if (context.getUserId() != 0) {
                NavBarFeatureOption.INSTANCE.getClass();
                if (NavBarFeatureOption.isDefaultNavVirtualKey() || (!UserManager.get(context).getUserInfo(context.getUserId()).isGuest() && Settings.Secure.getIntForUser(context.getContentResolver(), "user_setup_complete", 0, context.getUserId()) == 0)) {
                    companion.setNavState(context, 0);
                    companion.setVirtualKeyCombinationType(context, CustomizeFeatureOption.sIsVirtualkeyRecentRightOfHome ? 1 : 0);
                } else {
                    companion.setNavState(context, 3);
                }
            } else {
                companion.setNavState(context, 0);
                companion.setVirtualKeyCombinationType(context, CustomizeFeatureOption.sIsVirtualkeyRecentRightOfHome ? 1 : 0);
            }
        }
        if (companion.getVirtualKeyCombinationType(context) == -1) {
            if (zIsMultiSystemUserId) {
                companion.setVirtualKeyCombinationType(context, companion.getSystemUserSecureValue(context, KEY_VIRTUAL_KEY_COMBINATION_TYPE, -1));
            } else {
                companion.setVirtualKeyCombinationType(context, CustomizeFeatureOption.sIsVirtualkeyRecentRightOfHome ? 1 : 0);
            }
        }
        if (companion.getVirtualKeyCombinationType(context) == 2) {
            companion.setVirtualKeyCombinationType(context, 0);
        } else if (companion.getVirtualKeyCombinationType(context) == 3) {
            companion.setVirtualKeyCombinationType(context, 1);
        }
        if (companion.getVirtualKeyHideEnable(context) == -1) {
            if (zIsMultiSystemUserId) {
                companion.setVirtualKeyHideEnable(context, companion.getSystemUserSecureValue(context, KEY_VIRTUAL_KEY_HIDE_ENABLE, -1));
            } else {
                companion.setVirtualKeyHideEnable(context, 0);
            }
        }
        if (companion.getNavState(context) == 2) {
            if (companion.getSwipeUpGestureKeysMode(context) == -1 || companion.getSwipeUpGestureKeysMode(context) == 1) {
                companion.setSwipeUpGestureKeysMode(context, 2);
                companion.setNavState(context, 3);
            } else if (!CustomizeFeatureOption.sHasSupportMoreGestureUp && (companion.getSwipeUpGestureKeysMode(context) == 0 || companion.getSwipeUpGestureKeysMode(context) == 3)) {
                companion.setSwipeUpGestureKeysMode(context, 2);
                companion.setNavState(context, 0);
            }
        }
        AbsSettingsValueProxy.Companion companion2 = AbsSettingsValueProxy.Companion;
        if (companion2.getSecureIntValue(context, KEY_NAV_STATE, -1) == 1) {
            companion.setNavState(context, 0);
        }
        if (companion.getSwipeUpGestureBarType(context) == -1) {
            if (zIsMultiSystemUserId) {
                companion.setSwipeUpGestureBarType(context, companion.getSystemUserSecureValue(context, KEY_SWIPE_UP_GESTURE_BAR_TYPE, -1));
            } else {
                companion.setSwipeUpGestureBarType(context, 0);
            }
        }
        if (companion.getSwipeSideGestureBarType(context) == -1) {
            if (zIsMultiSystemUserId) {
                companion.setSwipeSideGestureBarType(context, companion.getSystemUserSecureValue(context, KEY_SWIPE_SIDE_GESTURE_BAR_TYPE, -1));
            } else {
                NavBarFeatureOption.INSTANCE.getClass();
                if (NavBarFeatureOption.sEnableHideGesture) {
                    companion.setSwipeSideGestureBarType(context, 1);
                } else {
                    companion.setSwipeSideGestureBarType(context, 0);
                }
            }
            NavBarFeatureOption.INSTANCE.getClass();
            if (NavBarFeatureOption.sIsHideGestureBarByOta) {
                companion.setInvalidateHideGestureBarByOtaState(context, 1);
            }
        } else {
            NavBarFeatureOption.INSTANCE.getClass();
            if (NavBarFeatureOption.sIsHideGestureBarByOta && companion.getInvalidateHideGestureBarByOtaState(context) != 1) {
                companion.setInvalidateHideGestureBarByOtaState(context, 1);
                companion.setSwipeSideGestureBarType(context, 1);
            }
        }
        if (companion.getSwipeUpGestureMistouchPreventionEnable(context) == -1) {
            companion.setSwipeUpGestureMistouchPreventionEnable(context, zIsMultiSystemUserId ? companion.getSystemUserSecureValue(context, KEY_SWIPE_UP_GESTURE_MISTOUCH_PREVENTION_ENABLE, -1) : 1);
        }
        if (companion.getSwipeSideGestureMistouchPreventionEnable(context) == -1) {
            companion.setSwipeSideGestureMistouchPreventionEnable(context, zIsMultiSystemUserId ? companion.getSystemUserSecureValue(context, KEY_SWIPE_SIDE_GESTURE_MISTOUCH_PREVENTION_ENABLE, -1) : 0);
        }
        if (companion.getSwipeSideGestureMistouchPreventionType(context) == -1) {
            if (zIsMultiSystemUserId) {
                systemUserSecureValue = companion.getSystemUserSecureValue(context, KEY_SWIPE_SIDE_GESTURE_MISTOUCH_PREVENTION_TYPE, -1);
            } else {
                systemUserSecureValue = companion.getSwipeSideGestureMistouchPreventionEnable(context) == 1 ? 0 : 1;
            }
            companion.setSwipeSideGestureMistouchPreventionType(context, systemUserSecureValue);
        }
        if (companion.getSwipeSideEnableBackVibrate(context) == -1) {
            if (zIsMultiSystemUserId) {
                companion.setSwipeSideEnableBackVibrate(context, companion.getSystemUserSecureValue(context, KEY_SWIPE_SIDE_GESTURE_BACK_VIBRATE_ENABLE, 0));
            } else {
                companion.setSwipeSideEnableBackVibrate(context, 0);
            }
        }
        if (companion.getUpGestureEnterLandScapeTipsNumber(context) == -1) {
            if (zIsMultiSystemUserId) {
                companion.setUpGestureEnterLandScapeTipsNumber(context, companion.getSystemUserSecureValue(context, KEY_GESTURE_UP_ENTER_LANDSCAPE_TIPS_NUMBER, -1));
            } else {
                companion.setUpGestureEnterLandScapeTipsNumber(context, 3);
            }
        }
        if (companion.getDisplayAutoHideVirtualKeyToastTimes(context) == -1) {
            if (zIsMultiSystemUserId) {
                companion.setDisplayAutoHideVirtualKeyToastTimes(context, companion.getSystemUserSecureValue(context, KEY_DISPLAY_AUTO_HIDE_VIRTUAL_KEY_TOAST_TIMES, -1));
            } else {
                companion.setDisplayAutoHideVirtualKeyToastTimes(context, 5);
            }
        }
        if (!IMMERSIVE_MODE_CONFIRMED.equals(companion2.getSecureStringValue(context, KEY_IMMERSIVE_MODE))) {
            companion2.setSecureStringValue(context, KEY_IMMERSIVE_MODE, IMMERSIVE_MODE_CONFIRMED);
        }
        if (companion.getNavGestureState(context) == -1) {
            companion.setNavGestureState(context, companion.getNavState(context));
        }
        if (companion.getNeedShowGestureGuide(context) == -1) {
            if (zIsMultiSystemUserId) {
                companion.setNeedShowGestureGuide(context, companion.getSystemUserSecureValue(context, KEY_NEED_SHOW_GESTURE_GUIDE, -1));
            } else {
                companion.setNeedShowGestureGuide(context, 1);
            }
        }
        if (companion.getNavButtonsPosition(context) == -1) {
            if (zIsMultiSystemUserId) {
                companion.setNavButtonsPosition(context, companion.getSystemUserSecureValue(context, KEY_NAV_BUTTONS_POSITION, -1));
            } else {
                companion.setNavButtonsPosition(context, 1);
            }
        }
        if (companion.getNavButtonsSwitchDirect(context) == -1) {
            if (zIsMultiSystemUserId) {
                companion.setNavButtonsSwitchDirect(context, companion.getSystemUserSecureValue(context, KEY_NAV_BUTTONS_SWITCH_DIRECT, -1));
            } else {
                companion.setNavButtonsSwitchDirect(context, 1);
            }
        }
        int taskbarEnable = companion.getTaskbarEnable(context);
        int navButtonsPosition = companion.getNavButtonsPosition(context);
        NavBarFeatureOption.INSTANCE.getClass();
        boolean z = NavBarFeatureOption.sIsSupportTaskbar;
        Object[] objArr = z && taskbarEnable == 1;
        String tag = getTAG();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(tag, LogUtil.SEPARATOR);
            StringBuilder sbM2 = FloatList$.ExternalSyntheticOutline0.m(" taskbarEnable:", taskbarEnable, navButtonsPosition, " navBarPosition:", " taskbarFeature:");
            sbM2.append(z);
            sbM.append(sbM2.toString());
            Log.d("SystemUi--NavBar", sbM.toString());
        }
        if (objArr != false && navButtonsPosition == 0 && OTAUtils.isOTAUpgrade(context) && TaskbarUtils.isSupportTaskbarLauncher(context)) {
            companion.setNavButtonsPosition(context, 1);
            String tag2 = getTAG();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(tag2, "-->ota setNavButtonsPosition", "SystemUi--NavBar");
            }
        }
        if (companion.getLongPressHomeWakeAssistEnable(context) == -1) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isExpRegion()) {
                if (zIsMultiSystemUserId) {
                    companion.setLongPressHomeWakeAssistEnable(context, companion.getSystemUserSecureValue(context, KEY_ASSIST_LONG_PRESS_HOME_ENABLED, -1));
                } else {
                    companion.setLongPressHomeWakeAssistEnable(context, context.getResources().getBoolean(R.bool.config_attachNavBarToAppDuringTransition) ? 1 : 0);
                }
            }
        }
        OcrScreenCompatVersion.INSTANCE.init(context);
        if (!z) {
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isExpRegion() && OcrScreenCompatVersion.isMetaDataSupport) {
                if (companion.getVirtualKeyLongPressHomeOcrEnable(context) == -1) {
                    if (zIsMultiSystemUserId) {
                        companion.setVirtualKeyLongPressHomeOcrEnable(context, companion.getSystemUserSecureValue(context, OPLUS_HOME_BUTTON_WAKE_UP_OCR, -1));
                    } else {
                        companion.setVirtualKeyLongPressHomeOcrEnable(context, 0);
                        if (companion.getVirtualKeyLongPressHomeCuiEnable(context) == 1) {
                            companion.setVirtualKeyLongPressHomeOcrDialogEnable(context, 1);
                            companion.setVirtualKeyLongPressHomeCuiEnable(context, 0);
                        }
                    }
                }
                if (companion.getNavbarHandlerOcrEnable(context) == -1) {
                    if (zIsMultiSystemUserId) {
                        companion.setNavbarHandlerOcrEnable(context, companion.getSystemUserSecureValue(context, OPLUS_HOME_HANDLE_WAKE_UP_OCR, -1));
                        return;
                    }
                    companion.setNavbarHandlerOcrEnable(context, 0);
                    if (companion.getNavbarHandlerCuiEnable(context) == 1) {
                        companion.setNavbarHandlerOcrDialogEnable(context, 1);
                        companion.setNavbarHandlerCuiEnable(context, 0);
                        return;
                    }
                    return;
                }
                return;
            }
        }
        if (companion.getNavbarHandlerCuiEnable(context) == -1) {
            if (zIsMultiSystemUserId) {
                companion.setNavbarHandlerCuiEnable(context, companion.getSystemUserSecureValue(context, KEY_NAVBAR_HANDLE_WAKE_CUI, -1));
            } else {
                FeatureOption.INSTANCE.getClass();
                companion.setNavbarHandlerCuiEnable(context, !FeatureOption.isExpRegion() ? 1 : 0);
            }
        }
        if (companion.getVirtualKeyLongPressHomeCuiEnable(context) == -1) {
            if (zIsMultiSystemUserId) {
                companion.setVirtualKeyLongPressHomeCuiEnable(context, companion.getSystemUserSecureValue(context, KEY_VIRTUAL_KEY_LONG_PRESS_HOME, -1));
            } else {
                FeatureOption.INSTANCE.getClass();
                companion.setVirtualKeyLongPressHomeCuiEnable(context, !FeatureOption.isExpRegion() ? 1 : 0);
            }
        }
    }
}

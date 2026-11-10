package com.oplusos.systemui.common.settingsvalue;

import android.content.Context;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CommonSettingsValueProxy extends AbsSettingsValueProxy {
    public static final Companion Companion = new Companion();
    public static final String DYNAMIC_FONT_WEIGHT_CONFIG_STRING = "'wght' ";
    public static final String FONT_VARIATION_SETTINGS = "font_variation_settings";
    public static final String KEY_CAST_PANEL_SETTING = "key_settings_cast_panel";
    public static final String KEY_CHILDREN_MODE = "children_mode_on";
    public static final String KEY_DEVELOPMENT_SETTINGS = "development_settings_enabled";
    public static final String KEY_FOCUS_MODE_SWITCH = "focusmode_switch";
    public static final String KEY_FOCUS_NEW_MODE_SWITCH = "focusmode_switch_new";
    public static final String KEY_GESTURE_UP_AVAILABLE = "navbarsettings_gestureup_available";
    public static final String KEY_HAPTIC_FEEDBACK = "haptic_feedback_enabled";
    public static final String KEY_HIGH_PERFORMANCE_MODE = "high_performance_mode_on";
    public static final String KEY_HIGH_TEMP_SAFETY_STATE = "oplus_settings_hightemp_safety_state";
    public static final String KEY_IN_BRICK_MODE_STATUS = "op_breath_mode_status";
    public static final String KEY_LOCK_TASK_MODE = "lock_to_app_enabled";
    public static final String KEY_PC_CAST_PANEL_SETTING = "key_settings_pc_switch_panel";
    public static final String KEY_POWER_SAVING_MODE = "low_power";
    public static final String KEY_QS_PERSONALITY_SHAPE_TYPE = "qs_personality_shape_type";
    public static final String KEY_SIGNAL_ICON_DISPLAY_MODE = "signal_icon_display_mode";
    public static final String KEY_SPEEDCHASSIST_MAIN_TYPE = "oplus.speechassist.main.type";
    public static final String KEY_STATUSBAR_EXPAND_STATE = "statusbar_expand_disable";
    public static final String KEY_STATUS_BAR_ALARM_ICON_STATUS = "clock_icon_enabled";
    public static final String KEY_STUDY_CENTER_MODE = "STUDY_CENTER_MODE";
    public static final String KEY_STUDY_CENTER_MODE_WHITE_LIST = "study_center_mode_whitelist";
    public static final String KEY_SUPER_POWERSAVE_AUTO_CLOSE_MODE_STATE = "super_powersave_auto_close_state";
    public static final String KEY_SUPER_POWERSAVE_MODE_STATE = "super_powersave_mode_state";
    public static final String KEY_SUPPORT_CHARGE = "support_charge_sound_switch";
    public static final String KEY_VISUAL_COMFORT_SWITCH = "visual_comfort_switch";
    public static final String KEY_WELL_BEING_ASSISTANT_MODE_STATE = "key_well_being_assistant_mode_state";
    public static final String KEY_WIFI_USE_TECHNICAL_STANDARD_ICONS_SWITCH_ON = "wifi_use_technical_standard_icons_switch_on";
    public static final String KEY_WIRELESS_REVERSE = "flag_disable_wireleseReverse_function";
    public static final String KEY_WLAN_BAND_LABEL_SWITCH_ON = "wlan_band_label_switch_on";
    public static final String ONRPLUS_RED_ONE_ENABLE = "oneplus_red_one_enable";
    public static final String PHONE_MUTE_STATE_FOR_CUSTOM = "phone_mute_state_for_custom";
    public static final String SETTINGS_SMART_TOUCH_SWITCH = "smart_touch_value";
    public static final String SMART_CONNECTION_NOTIFICATION_SYNC = "smart_connection_notification_sync";
    public static final String SMART_CONNECTION_NOTIFICATION_SYNC_PAD = "smart_connection_notification_sync_pad";
    public static final String SUPER_POWER_SAVE_DESKTOP_APP_LIST = "super_power_save_desktop_app_list";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final int getBreathModeState(Context context, String str) {
            return AbsSettingsValueProxy.Companion.getSecureIntValue(context, str, 0);
        }

        public final int getChildrenModeState(Context context) {
            return AbsSettingsValueProxy.Companion.getGlobalIntValue(context, "children_mode_on", 0);
        }

        public final int getDevelopmentSettingsState(Context context) {
            return AbsSettingsValueProxy.Companion.getGlobalIntValue(context, CommonSettingsValueProxy.KEY_DEVELOPMENT_SETTINGS, 0);
        }

        public final int getFocusModeState(Context context, String str) {
            return AbsSettingsValueProxy.Companion.getSecureIntValue(context, str, 0);
        }

        public final int getFontVariationStatus(Context context) {
            return AbsSettingsValueProxy.Companion.getSystemIntValue(context, CommonSettingsValueProxy.FONT_VARIATION_SETTINGS, 550, 0);
        }

        public final int getFontVariationValues(Context context) {
            return AbsSettingsValueProxy.Companion.getSystemIntValue(context, CommonSettingsValueProxy.FONT_VARIATION_SETTINGS, 550, 0);
        }

        public final int getFontVariationValuesForUser(Context context) {
            return AbsSettingsValueProxy.Companion.getSystemIntValue(context, CommonSettingsValueProxy.FONT_VARIATION_SETTINGS, 550, -2);
        }

        public final int getGestureUpModeAvailable(Context context) {
            FlavorOneFeatureOption.INSTANCE.getClass();
            if (!FlavorOneFeatureOption.isFlavorOneDevice()) {
                return AbsSettingsValueProxy.Companion.getSystemIntValue(context, CommonSettingsValueProxy.KEY_GESTURE_UP_AVAILABLE, 1);
            }
            int systemIntValue = AbsSettingsValueProxy.Companion.getSystemIntValue(context, CommonSettingsValueProxy.KEY_GESTURE_UP_AVAILABLE, 1);
            ArrayList arrayList = CustomizeFeatureOption.APP_FEATURE_CACHE_LIST;
            if (!FlavorOneFeatureOption.isFlavorOneDevice() || CustomizeFeatureOption.sKeepGestureUp) {
                return 0;
            }
            return systemIntValue;
        }

        public final int getHapticFeedbackState(Context context) {
            return AbsSettingsValueProxy.Companion.getSystemIntValue(context, CommonSettingsValueProxy.KEY_HAPTIC_FEEDBACK, 1, OsBinderCacheUtils.getCurrentUserId());
        }

        public final int getHighPerformanceModeState(Context context) {
            return AbsSettingsValueProxy.Companion.getSystemIntValue(context, CommonSettingsValueProxy.KEY_HIGH_PERFORMANCE_MODE, 0, 0);
        }

        public final int getHighTempSafetyState(Context context) {
            return AbsSettingsValueProxy.Companion.getSystemIntValue(context, CommonSettingsValueProxy.KEY_HIGH_TEMP_SAFETY_STATE, 0, 0);
        }

        public final int getLockTaskModeState(Context context) {
            return AbsSettingsValueProxy.Companion.getSystemIntValue(context, CommonSettingsValueProxy.KEY_LOCK_TASK_MODE, 0);
        }

        public final int getPowerSavingModeState(Context context) {
            return AbsSettingsValueProxy.Companion.getSystemIntValue(context, CommonSettingsValueProxy.KEY_POWER_SAVING_MODE, 0, 0);
        }

        public final int getQSShapeType(Context context, int i) {
            return AbsSettingsValueProxy.Companion.getSystemIntValue(context, CommonSettingsValueProxy.KEY_QS_PERSONALITY_SHAPE_TYPE, i);
        }

        public final int getRedOneStyle(Context context) {
            return AbsSettingsValueProxy.Companion.getSystemIntValue(context, CommonSettingsValueProxy.ONRPLUS_RED_ONE_ENABLE, 0, 0);
        }

        public final int getSpeedChassistMainState(Context context, String str) {
            return AbsSettingsValueProxy.Companion.getGlobalIntValue(context, str, 0);
        }

        public final int getStatusBarAlarmIconState(Context context) {
            return AbsSettingsValueProxy.Companion.getSystemIntValue(context, CommonSettingsValueProxy.KEY_STATUS_BAR_ALARM_ICON_STATUS, 0, -2);
        }

        public final int getStatusBarExpandDisable(Context context) {
            return AbsSettingsValueProxy.Companion.getSecureIntValue(context, CommonSettingsValueProxy.KEY_STATUSBAR_EXPAND_STATE, 0);
        }

        public final int getStudyCenterModeState(Context context) {
            return AbsSettingsValueProxy.Companion.getGlobalIntValue(context, CommonSettingsValueProxy.KEY_STUDY_CENTER_MODE, 0);
        }

        public final String getSuperPowerListState(Context context) {
            return AbsSettingsValueProxy.Companion.getSystemStringValue(context, CommonSettingsValueProxy.SUPER_POWER_SAVE_DESKTOP_APP_LIST, 0);
        }

        public final int getSuperPowerSaveState(Context context) {
            return AbsSettingsValueProxy.Companion.getSystemIntValue(context, CommonSettingsValueProxy.KEY_SUPER_POWERSAVE_MODE_STATE, 0, 0);
        }

        public final int getWellBeingAssistantState(Context context) {
            return AbsSettingsValueProxy.Companion.getSystemIntValue(context, CommonSettingsValueProxy.KEY_WELL_BEING_ASSISTANT_MODE_STATE, 0, 0);
        }

        public final int getWifiTechnicalStandardState(Context context) {
            return AbsSettingsValueProxy.Companion.getGlobalIntValue(context, CommonSettingsValueProxy.KEY_WIFI_USE_TECHNICAL_STANDARD_ICONS_SWITCH_ON, 0);
        }

        public final int getWirelessReverseEnableState(Context context) {
            return AbsSettingsValueProxy.Companion.getGlobalIntValue(context, CommonSettingsValueProxy.KEY_WIRELESS_REVERSE, 0);
        }

        public final int getWlanBandLabelState(Context context) {
            return AbsSettingsValueProxy.Companion.getGlobalIntValue(context, CommonSettingsValueProxy.KEY_WLAN_BAND_LABEL_SWITCH_ON, 1);
        }

        public final boolean isCast(Context context) {
            return AbsSettingsValueProxy.Companion.getSecureIntValue(context, CommonSettingsValueProxy.KEY_CAST_PANEL_SETTING, 0, -2) == 1;
        }

        public final boolean isFontVariationStatus(Context context) {
            int fontVariationStatus = (getFontVariationStatus(context) & 61440) >> 12;
            return fontVariationStatus == 1 || fontVariationStatus == 2;
        }

        public final boolean isHapticFeedbackOn(Context context) {
            return getHapticFeedbackState(context) == 1;
        }

        public final boolean isPcCast(Context context) {
            return AbsSettingsValueProxy.Companion.getSecureIntValue(context, CommonSettingsValueProxy.KEY_PC_CAST_PANEL_SETTING, 0, -2) == 1;
        }

        public final boolean isVisualComfortSwitchOpen(Context context) {
            return AbsSettingsValueProxy.Companion.getSystemIntValue(context, CommonSettingsValueProxy.KEY_VISUAL_COMFORT_SWITCH, 0, -2) == 1;
        }

        public final void setCastState(Context context, int i) {
            AbsSettingsValueProxy.Companion.setSecureIntValue(context, CommonSettingsValueProxy.KEY_CAST_PANEL_SETTING, i);
        }

        public final void setDevelopmentSettingsState(Context context, int i) {
            AbsSettingsValueProxy.Companion.setGlobalIntValue(context, CommonSettingsValueProxy.KEY_DEVELOPMENT_SETTINGS, i);
        }

        public final void setGestureUpModeAvailable(Context context, int i) {
            AbsSettingsValueProxy.Companion.setSystemIntValue(context, CommonSettingsValueProxy.KEY_GESTURE_UP_AVAILABLE, i);
        }

        public final void setLockTaskModeState(Context context, int i) {
            AbsSettingsValueProxy.Companion.setSystemIntValue(context, CommonSettingsValueProxy.KEY_LOCK_TASK_MODE, i);
        }

        public final void setPcCastState(Context context, int i) {
            AbsSettingsValueProxy.Companion.setSecureIntValue(context, CommonSettingsValueProxy.KEY_PC_CAST_PANEL_SETTING, i);
        }

        public final void setPowerSavingModeState(Context context, int i) {
            AbsSettingsValueProxy.Companion.setSystemIntValue(context, CommonSettingsValueProxy.KEY_POWER_SAVING_MODE, i);
        }

        public final void setQSShapeType(Context context, int i) {
            AbsSettingsValueProxy.Companion.setSystemIntValue(context, CommonSettingsValueProxy.KEY_QS_PERSONALITY_SHAPE_TYPE, i);
        }

        public final void setStatusBarAlarmIconState(Context context, int i) {
            AbsSettingsValueProxy.Companion.setSystemIntValue(context, CommonSettingsValueProxy.KEY_STATUS_BAR_ALARM_ICON_STATUS, i);
        }

        public final void setSuperPowerAutoSaveState(Context context) {
            AbsSettingsValueProxy.Companion.setSystemIntValue(context, CommonSettingsValueProxy.KEY_SUPER_POWERSAVE_AUTO_CLOSE_MODE_STATE, 1, 0);
        }
    }

    public static final int getBreathModeState(Context context, String str) {
        return Companion.getBreathModeState(context, str);
    }

    public static final int getChildrenModeState(Context context) {
        return Companion.getChildrenModeState(context);
    }

    public static final int getDevelopmentSettingsState(Context context) {
        return Companion.getDevelopmentSettingsState(context);
    }

    public static final int getFocusModeState(Context context, String str) {
        return Companion.getFocusModeState(context, str);
    }

    public static final int getFontVariationStatus(Context context) {
        return Companion.getFontVariationStatus(context);
    }

    public static final int getFontVariationValues(Context context) {
        return Companion.getFontVariationValues(context);
    }

    public static final int getFontVariationValuesForUser(Context context) {
        return Companion.getFontVariationValuesForUser(context);
    }

    public static final int getGestureUpModeAvailable(Context context) {
        return Companion.getGestureUpModeAvailable(context);
    }

    public static final int getHapticFeedbackState(Context context) {
        return Companion.getHapticFeedbackState(context);
    }

    public static final int getHighPerformanceModeState(Context context) {
        return Companion.getHighPerformanceModeState(context);
    }

    public static final int getHighTempSafetyState(Context context) {
        return Companion.getHighTempSafetyState(context);
    }

    public static final int getLockTaskModeState(Context context) {
        return Companion.getLockTaskModeState(context);
    }

    public static final int getPowerSavingModeState(Context context) {
        return Companion.getPowerSavingModeState(context);
    }

    public static final int getQSShapeType(Context context, int i) {
        return Companion.getQSShapeType(context, i);
    }

    public static final int getRedOneStyle(Context context) {
        return Companion.getRedOneStyle(context);
    }

    public static final int getSpeedChassistMainState(Context context, String str) {
        return Companion.getSpeedChassistMainState(context, str);
    }

    public static final int getStatusBarExpandDisable(Context context) {
        return Companion.getStatusBarExpandDisable(context);
    }

    public static final int getStudyCenterModeState(Context context) {
        return Companion.getStudyCenterModeState(context);
    }

    public static final String getSuperPowerListState(Context context) {
        return Companion.getSuperPowerListState(context);
    }

    public static final int getSuperPowerSaveState(Context context) {
        return Companion.getSuperPowerSaveState(context);
    }

    public static final int getWellBeingAssistantState(Context context) {
        return Companion.getWellBeingAssistantState(context);
    }

    public static final int getWifiTechnicalStandardState(Context context) {
        return Companion.getWifiTechnicalStandardState(context);
    }

    public static final int getWirelessReverseEnableState(Context context) {
        return Companion.getWirelessReverseEnableState(context);
    }

    public static final int getWlanBandLabelState(Context context) {
        return Companion.getWlanBandLabelState(context);
    }

    public static final boolean isCast(Context context) {
        return Companion.isCast(context);
    }

    public static final boolean isFontVariationStatus(Context context) {
        return Companion.isFontVariationStatus(context);
    }

    public static final boolean isHapticFeedbackOn(Context context) {
        return Companion.isHapticFeedbackOn(context);
    }

    public static final boolean isPcCast(Context context) {
        return Companion.isPcCast(context);
    }

    public static final void setCastState(Context context, int i) {
        Companion.setCastState(context, i);
    }

    public static final void setDevelopmentSettingsState(Context context, int i) {
        Companion.setDevelopmentSettingsState(context, i);
    }

    public static final void setGestureUpModeAvailable(Context context, int i) {
        Companion.setGestureUpModeAvailable(context, i);
    }

    public static final void setLockTaskModeState(Context context, int i) {
        Companion.setLockTaskModeState(context, i);
    }

    public static final void setPcCastState(Context context, int i) {
        Companion.setPcCastState(context, i);
    }

    public static final void setPowerSavingModeState(Context context, int i) {
        Companion.setPowerSavingModeState(context, i);
    }

    public static final void setQSShapeType(Context context, int i) {
        Companion.setQSShapeType(context, i);
    }

    public static final void setSuperPowerAutoSaveState(Context context) {
        Companion.setSuperPowerAutoSaveState(context);
    }

    @Override // com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy
    public void checkValidity(Context context) {
    }
}

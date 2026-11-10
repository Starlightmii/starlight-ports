package com.oplusos.systemui.common.feature;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemProperties;
import android.os.Trace;
import android.os.customize.OplusCustomizeRestrictionManager;
import android.os.oplusdevicepolicy.OplusDevicepolicyManager;
import android.telephony.OplusOSTelephonyManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.common.coroutine.ChannelExt;
import com.oplus.content.IOplusFeatureConfigManager;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplus.coreapp.appfeature.AppFeatureProviderUtils;
import com.oplus.keyguard.util.FeatureOption$isCompactWindowMode$2$;
import com.oplus.systemui.qs.pipeline.data.repository.AirplaneModeConfirmRepository$isAirplaneModeNeedConfirm$1$callback$1;
import com.oplus.systemui.shade.ShadeExpansionHelperImpl;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1;
import com.oplus.util.OplusPlatformLevelUtils;
import com.oplus.zoom.common.zoomparameter.SmallScreenZoomParameter;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ReflectionUtils;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FeatureOption {
    private static final String BOOT_COMPLETED = "sys.boot_completed";
    private static final String BOOT_COMPLETED_STATE = "1";
    private static final String CHARGING_ERROR_NO_ALERT_STR = "com.android.systemui.chargingerror_noalert";
    private static final String DEVICES_COMPONENT_NAME = "com.heytap.mydevices";
    private static final String DEVICES_DATA_NAME = "mydevices.feature";
    private static final String ENABLE_LIGHT_FEATURE = "com.oplus.systemui.enable_lightfeature_notificationshade";
    private static final String ENABLE_LIVEALERT_EFFECT_LIGHT = "com.oplus.systemui.livealert_effect_light.enable";
    private static final String ENABLE_MATERIAL_FEATURE = "com.oplus.appcompat.texture_effect.Enable";
    private static final String FEATURE_AI_KEY_MULTI_ENABLE = "oplus.software.ai_key_multi_enable";
    private static final String FEATURE_BINDER_THREAD_POOL_EXPAND = "oplus.software.binder.thread_pool_expand";
    private static final String FEATURE_DISABLE_KEYGUARD_BLUR_STACKING = "com.oplus.systemui.disable_keyguard_blur_stacking";
    private static final String FEATURE_DISABLE_LAYERED_ANIMATION = "com.oplus.systemui.disable_layered_animation";
    private static final String FEATURE_DISABLE_LOW_BATTERY_DIALOG = "com.android.systemui.powerui_disable_low_level_dialog";
    private static final String FEATURE_DISABLE_VOLUME_BLUR = "com.android.systemui.disable_volume_blur";
    private static final String FEATURE_FINGER_FLASH_NOTE = "oplus.software.directservice.finger_flashnotes_enable";
    private static final String FEATURE_FOLD = "oplus.hardware.type.fold";
    private static final String FEATURE_FOLD_REMAP_DISPLAY_DISABLED = "oplus.software.fold_remap_display_disabled";
    private static final String FEATURE_FPS_SWITCH = "oplus.software.display.dynamic_fps_switch";
    private static final String FEATURE_GO_TO_AFTER_SERVICE_CN = "com.android.systemui.go_to_after_service_in_cn";
    private static final String FEATURE_INSPIRATION_CLOCK_ATTR_ENABLE = "com.android.launcher.FEATURE_INSPIRATION_CLOCK_ATTR_ENABLE";
    private static final String FEATURE_MISC_FAN_SUPPORT = "oplus.misc.fan.support";
    private static final String FEATURE_NEW_FEATURES_DISPLAYING = "oplus.software.new_features_displaying";
    private static final String FEATURE_OPERATIONMANUAL_CMCC_SUPPORT = "oppo.operationmanual.cmcc.support";
    private static final String FEATURE_OPERATIONMANUAL_CMCC_SUPPORT_1 = "oplus.operationmanual.cmcc.support";
    private static final String FEATURE_REVISE_RECTANGLE_DISPLAY_ORIENTATION = "oplus.software.revise_rectangle_display_orientation";
    private static final String FEATURE_REVISE_SQUARE_DISPLAY_ORIENTATION = "oplus.software.revise_square_display_orientation";
    private static final String FEATURE_SCENARIO_ASSISTANT_SUPPORT = "com.oplus.systemui.scenario_assistant_support";
    private static final String FEATURE_STUDY_ROOM_FACE_SUPPORT = "oplus.hardware.study_room_face_support";
    private static final String FEATURE_TABLET = "oplus.hardware.type.tablet";
    private static final String FEATURE__LITE_LIVEALERT_DEBUG = "persist.debug.lite_livealert";
    private static final String GAME_MLBB_SUPPORT = "oplus.software.game.mlbb_support";
    private static final String GAME_SCROFF_ACT_PRELOAD = "oplus.software.game_scroff_act_preload";
    private static final String HETEROMORPHISM_SCREEN_STRING = "oplus.software.display.screen_heteromorphism";
    private static final String IOPLUSPROJECT_NAME = "android.os.ProjectManager";
    private static final String IS_ALWAYSON_ENABLE_STRING = "persist.sys.alwayson.enable";
    private static final String IS_AUDIO_TOP_VOLUME_KEY = "persist.oplus.software.audio.top_volume_key";
    private static final String IS_MEDIA_IMMERSIVE_FULLSCREEN = "com.oplus.systemui.media_immersive_fullscreen";
    private static final String IS_OPLUS_ACTION_BUTTON = "ro.oplus.key.actionbutton";
    private static final String IS_OPLUS_AOD_SUPPORT_STRING = "oplus.software.display.aod_support";
    private static final String IS_OPLUS_CONFIG_GO = "ro.oplus.config.go";
    private static final String IS_OPLUS_THREE_KAY_STRING = "oplus.software.audio.alert_slider";
    private static final String IS_PANIC_VERSION_STRING = "persist.sys.assert.panic";
    private static final String IS_SOFTWARE_SUPPORT_APP_FROZEN = "oplus.software.pms_app_frozen";
    private static final String IS_SUPPORT_DISPLAY_BRIGHTNESS_RM_UX = "oplus.software.light_os_brightness_ux";
    private static final String IS_SUPPORT_GAME_HYPER_BUTTON = "oplus.software.game.hyper_button_support";
    private static final String IS_SUPPORT_MULTIMODE_FLASHLIGHT = "com.oplus.systemui.enable_multimode_adjustable_flashlight";
    private static final String IS_SUPPORT_UNDER_SCREEN_PROXIMITY_STRING = "oplus.proximity.need.delay";
    private static final String IS_VERSION_SELL_MODE_STRING = "oplus.software.pms_sellmode";
    private static final String IS_VOLUME_KEY_RIGHT_STRING = "persist.oplus.software.audio.right_volume_key";
    private static final String IS_X_LINEAR_VIBRATOR = "oplus.hardware.vibrator_xlinear_type";
    private static final String LOW_BATTERY_NO_SOUND_STR = "com.android.systemui.lowbattery_nosound";
    private static final String MTK = "mt[0-9]*";
    private static final String MTK_GEMINI_SUPPORT_STRING = "mtk.gemini.support";
    private static final String MY_ENGINEERING_TYPE_STRING = "ro.oplus.image.my_engineering.type";
    private static final String OPLUS_DISABLE_POWER_SAVE_STRING = "oplus.software.power.disable_power_save";
    private static final String OPLUS_EDIT_DUAL_PS = "oplus.software.radio.dual_ps";
    private static final String OPLUS_EDIT_MEDIA_CONTROL = "oplus.software.audio.media_control";
    private static final String OPLUS_FEATURE_ASSISTANT_VOLUME_SUPPORT = "oplus.software.audio.assistant_volume_support";
    private static final String OPLUS_FEATURE_BD_SATELLITE_SUPPORT = "oplus.software.radio.bdsatellite_support";
    private static final String OPLUS_FEATURE_TT_SATELLITE_SUPPORT = "oplus.software.radio.tt_satellite_support";
    private static final String OPLUS_GAME_BETOP_JOYSTICK = "oplus.software.joystick.game_betop_joystick_support";
    private static final String OPLUS_GAME_LOL_SUPPORT = "oplus.software.game.lol_support";
    private static final String OPLUS_GAME_PEL_SUPPORT = "oplus.software.game.pel_support";
    private static final String OPLUS_HAEDWARE_WEAVER_LOCKSETTINGS = "oplus.hardware.weaver_locksettings";
    private static final String OPLUS_LIGHT_OS = "ro.oplus.lightos";
    private static final String OPLUS_LIGHT_OS_FOR_LH_DOMESTIC = "oplus.software.support_gp.product_light";
    private static final String OPLUS_LIGHT_OS_H_FOR_LH_DOMESTIC = "oplus.software.support_gp.product_light_h";
    private static final String OPLUS_MOTOR_SUPPORT = "oplus.hardware.motor.support";
    private static final String OPLUS_PANORAMA_BRANCH = "persist.oplus.panorama.branch.enable";
    private static final String OPLUS_PANORAMA_BRANCH_FOLDABLE = "persist.oplus.panorama.branch.foldable.enable";
    private static final String OPLUS_PANORAMA_WORK_STATION = "oplus.software.wms.panorama_work_station";
    private static final String OPLUS_POCKET_SUPPORT = "oplus.hardware.sensor.ultrasonic.proximity";
    private static final String OPLUS_REVERSE_CHARGING = "oplus.hardware.wired_reverse_charging";
    private static final String OPLUS_SOFTWARE_VIBRATOR_LUXUNVIBRATOR = "oplus.software.vibrator_luxunvibrator";
    private static final String OPLUS_WM_SIZE_RESOLUTION_SWITCH_SUPPORT = "oplus.software.display.wm_size_resolution_switch_support";
    private static final String PREVENT_START_SERVICES_IF_NEEDED = "com.oplus.systemui.prevent_start_services_if_needed";
    private static final String PROPERTY_SCENARIO_ASSISTANT_ENABLE = "persist.feature.scenario_assistant.enabled";
    private static final String PROP_ALLOW_BATTERY_WARNING = "oplus.battery.warning.allow";
    private static final String QCOM = "qcom";
    private static final String QCOM_GEMINI_SUPPORT_STRING = "oplus.qualcomm.gemini.support";
    private static final String SUPPORT_FLUID_ENTRY = "oplus.software.support_fluid_entry";
    private static final String SUPPORT_LITE_LIVEALERT = "oplus.software.support_lite_livealert";
    private static final String SUPPORT_SOS_STAT_ICON_STRING = "com.android.systemui.stat_sys_sos_icon";
    private static final String SYSTEM_AGING_TEST = "persist.sys.agingtest";
    private static final String TAG = "FeatureOption";
    private static final String VELOCITY_TRACKER_STRATEGY = "oplus.velocitytracker.strategy.support";
    private static final String VENDOR_REGIONMARK = "ro.vendor.oplus.regionmark";
    private static final String VENDOR_REGIONMARK_RU = "RU";
    private static final String WIFI_ONLY = "wifi-only";
    private static Context aplicationContext;
    private static final Lazy binderPoolExpand$delegate;
    public static boolean disableKeyguardBlurStacking;
    private static final Lazy disablePowerSave$delegate;
    public static boolean enableLightFeature;
    public static boolean enableLiveAlertEffectLight;
    public static boolean enableMaterialFeature;
    private static final Lazy firstApiLevel$delegate;
    private static final Lazy fluidDevice$delegate;
    private static final Lazy fluidDeviceForCharge$delegate;
    private static final Lazy fullLiveAlertDevice$delegate;
    private static final Lazy getGoAfterServiceInCN$delegate;
    private static final Lazy hasFlashlight$delegate;
    private static final Lazy isAgingVersion$delegate;
    private static final Lazy isAlwaysOnEnable$delegate;
    private static final Lazy isBattWarningAllowed$delegate;
    private static final Lazy isBrightnessUxSupport$delegate;
    private static final Lazy isChargingErrorNoAlert$delegate;
    private static final Lazy isCmccCustomizedVersion$delegate;
    private static final Lazy isDragonfly$delegate;
    private static final Lazy isDualSimConcurrently$delegate;
    private static final Lazy isDynamicFPSSupport$delegate;
    private static final Lazy isEarScreen$delegate;
    private static final Lazy isExpRegion$delegate;
    private static final Lazy isFactoryMode$delegate;
    private static final Lazy isFactoryVersion$delegate;
    private static final Lazy isFeaturesDisplaying$delegate;
    private static final Lazy isFirstApiLevelLessThanB$delegate;
    private static final Lazy isFirstApiLevelLessThanV$delegate;
    private static final Lazy isFirstApiLevelMoreThanC$delegate;
    private static final Lazy isFoldDevice$delegate;
    private static final Lazy isFoldRemapDisabled$delegate;
    private static final Lazy isGameLolSupport$delegate;
    private static final Lazy isGameMlbbSupport$delegate;
    private static final Lazy isGamePelSupport$delegate;
    private static final Lazy isHighTempAgingMode$delegate;
    private static final Lazy isLargeScreenFolding$delegate;
    private static final Lazy isLauncherOneShotSupport$delegate;
    private static final Lazy isLightBase$delegate;
    private static final Lazy isLightOs$delegate;
    private static final Lazy isLightOsForLH$delegate;
    private static final Lazy isLinearVibratorSupport$delegate;
    private static final Lazy isLiteDebugMode$delegate;
    private static final Lazy isLowBatteryNoSound$delegate;
    private static final Lazy isLuxunVibratorSupport$delegate;
    public static boolean isMediaImmersiveFullEnable;
    private static final Lazy isMiscFanSupport$delegate;
    private static final Lazy isMtkGeminiSupport$delegate;
    private static final Lazy isOplusActionButton$delegate;
    private static final Lazy isOplusAlertSlider$delegate;
    private static final Lazy isOplusAodSupport$delegate;
    private static final Lazy isOplusAssistantVolumeSupport$delegate;
    private static final Lazy isOplusConfigGo$delegate;
    private static final Lazy isOplusMediaControlSupport$delegate;
    private static final Lazy isOplusSatelliteSupport$delegate;
    private static final Lazy isOplusTopVolumeKey$delegate;
    private static final Lazy isOplusVolumeKeyInRight$delegate;
    private static final Lazy isPanicVersion$delegate;
    private static final Lazy isPlatformMTK$delegate;
    private static final Lazy isPlatformQCOM$delegate;
    private static final Lazy isQcomGeminiSupport$delegate;
    private static final Lazy isRectangleDisplay$delegate;
    private static final Lazy isRuRegionMark$delegate;
    private static final Lazy isStudyCenterFaceSupport$delegate;
    private static final Lazy isSupportAppFrozen$delegate;
    private static final Lazy isSupportBreenoMemos$delegate;
    private static final Lazy isSupportGameBetopJoystick$delegate;
    private static final Lazy isSupportGameHyperButton$delegate;
    private static final Lazy isSupportKeyboardAIFunction$delegate;
    private static final Lazy isSupportMotorHardware$delegate;
    public static boolean isSupportMultiModeFlashLight;
    private static final Lazy isSupportMyDevice$delegate;
    private static final Lazy isSupportNavBarButtonsPosition$delegate;
    private static final Lazy isSupportOnlySingleCard$delegate;
    private static final Lazy isSupportPanoramaWorkStation$delegate;
    private static final Lazy isSupportPocketSensor$delegate;
    private static final Lazy isSupportReverseCharging$delegate;
    private static final Lazy isSupportScenarioAssistant$delegate;
    private static final Lazy isSupportUnderScreenProximity$delegate;
    private static final Lazy isSupportWmSizeResolutionSwitch$delegate;
    private static final Lazy isTablet$delegate;
    private static final Lazy isTablet5G$delegate;
    private static final Lazy isTabletWifi$delegate;
    private static final Lazy isVelocityFeatureSupport$delegate;
    private static final Lazy isVersionSellMode$delegate;
    private static final Lazy isWeaverLockSettings$delegate;
    private static final Lazy isWhiteSwanFoldDevice$delegate;
    private static final Lazy isWifiOnly$delegate;
    private static final Lazy isXLinearVibratorSupport$delegate;
    private static final Lazy liteLiveAlertDevice$delegate;
    public static boolean mIsDisableLowBatteryDialog;
    public static boolean mPreventStartServices;
    private static final Lazy sGameScreenOffActPreload$delegate;
    public static volatile boolean showDevicesExpRegion;
    public static final FeatureOption INSTANCE = new FeatureOption();
    private static final String FEATURE_SYSTEMUI_LIGHT_OS = "com.android.systemui.light_os";
    private static final List<String> appFeatureList = CollectionsKt__CollectionsKt.mutableListOf(new String[]{FEATURE_SYSTEMUI_LIGHT_OS});
    public static final boolean sOplusSystemUiNavigationBarUi = true;
    public static boolean useLayeredPanelAnimation = true;

    /* JADX INFO: renamed from: $r8$lambda$-QThH38XDoJjK_DwT8XH_gT_xwg, reason: not valid java name */
    public static boolean m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg() {
        PackageManager packageManager;
        Context context = aplicationContext;
        boolean zHasSystemFeature = (context == null || (packageManager = context.getPackageManager()) == null) ? false : packageManager.hasSystemFeature(OPLUS_HAEDWARE_WEAVER_LOCKSETTINGS);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("isWeaverLockSettings = ", "FeatureOption-->", "SystemUi--Common", zHasSystemFeature);
        }
        return zHasSystemFeature;
    }

    public static boolean $r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8() {
        Context context = aplicationContext;
        if (context != null) {
            return context.getPackageManager().hasSystemFeature("android.hardware.camera.flash");
        }
        return false;
    }

    public static boolean $r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y() {
        Context context = aplicationContext;
        if (context != null) {
            return OplusOSTelephonyManager.getDefault(context).isOplusSingleSimCard();
        }
        return false;
    }

    public static Boolean $r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I() {
        Context context = aplicationContext;
        if (context != null) {
            return Boolean.valueOf(AppFeatureProviderUtils.getBoolean(context.getContentResolver(), FEATURE_INSPIRATION_CLOCK_ATTR_ENABLE, true));
        }
        return null;
    }

    public static boolean $r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw() {
        Context context = aplicationContext;
        if (context != null) {
            return context.getPackageManager().hasSystemFeature(FEATURE_AI_KEY_MULTI_ENABLE);
        }
        return false;
    }

    public static boolean $r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI() {
        Context context = aplicationContext;
        if (context != null) {
            return context.getPackageManager().hasSystemFeature(OPLUS_POCKET_SUPPORT);
        }
        return false;
    }

    public static boolean $r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8() {
        Context context = aplicationContext;
        if (context != null) {
            return context.getPackageManager().hasSystemFeature(OPLUS_MOTOR_SUPPORT);
        }
        return false;
    }

    /* JADX INFO: renamed from: $r8$lambda$GgAK9zqnb5-c-96xZj5N6xY0BxM, reason: not valid java name */
    public static boolean m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM() {
        INSTANCE.getClass();
        if (isTablet() && WIFI_ONLY.equals(SystemProperties.get("ro.carrier"))) {
            return true;
        }
        Context context = aplicationContext;
        if (context != null) {
            return !((TelephonyManager) context.getSystemService(TelephonyManager.class)).isDataCapable();
        }
        return false;
    }

    public static boolean $r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU() {
        Context context = aplicationContext;
        if (context != null) {
            return AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), AppFeatureProviderUtils.FeatureID.STATIC_COMPONENT, LOW_BATTERY_NO_SOUND_STR);
        }
        return false;
    }

    public static boolean $r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI() {
        Context context = aplicationContext;
        if (context != null) {
            return context.getPackageManager().hasSystemFeature(MTK_GEMINI_SUPPORT_STRING);
        }
        return false;
    }

    /* JADX INFO: renamed from: $r8$lambda$S05f_ca-tjW7SJ0XfmJydRoEc9M, reason: not valid java name */
    public static boolean m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M() {
        Context context = aplicationContext;
        if (context != null) {
            return context.getPackageManager().hasSystemFeature(OPLUS_MOTOR_SUPPORT);
        }
        return false;
    }

    public static boolean $r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4() {
        FeatureOption featureOption = INSTANCE;
        if (featureOption.getFullLiveAlertDevice() || featureOption.getLiteLiveAlertDevice()) {
            return true;
        }
        FlavorOneFeatureOption.INSTANCE.getClass();
        return FlavorOneFeatureOption.isSupportMiniCapsule();
    }

    /* JADX INFO: renamed from: $r8$lambda$VF4bAS-qTemBlv_gbJXDzaW3wRg, reason: not valid java name */
    public static boolean m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg() {
        INSTANCE.getClass();
        return (!isFoldDevice() || isFoldRemapDisabled() || ((Boolean) isRectangleDisplay$delegate.getValue()).booleanValue()) ? false : true;
    }

    public static boolean $r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU() {
        PackageManager packageManager;
        PackageManager packageManager2;
        Context context = aplicationContext;
        if ((context == null || (packageManager2 = context.getPackageManager()) == null) ? false : packageManager2.hasSystemFeature(OPLUS_LIGHT_OS_H_FOR_LH_DOMESTIC)) {
            return true;
        }
        Context context2 = aplicationContext;
        return ((context2 == null || (packageManager = context2.getPackageManager()) == null) ? false : packageManager.hasSystemFeature(OPLUS_LIGHT_OS_FOR_LH_DOMESTIC)) || SystemProperties.getBoolean(OPLUS_LIGHT_OS, false);
    }

    public static boolean $r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc() {
        Context context = aplicationContext;
        if (context != null) {
            return context.getPackageManager().hasSystemFeature(FEATURE_OPERATIONMANUAL_CMCC_SUPPORT) || context.getPackageManager().hasSystemFeature(FEATURE_OPERATIONMANUAL_CMCC_SUPPORT_1);
        }
        return false;
    }

    public static boolean $r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4() {
        Context context = aplicationContext;
        if (context != null) {
            return AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), AppFeatureProviderUtils.FeatureID.STATIC_COMPONENT, CHARGING_ERROR_NO_ALERT_STR);
        }
        return false;
    }

    public static boolean $r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c() {
        Context context = aplicationContext;
        if (context != null) {
            return context.getPackageManager().hasSystemFeature(QCOM_GEMINI_SUPPORT_STRING);
        }
        return false;
    }

    /* JADX INFO: renamed from: $r8$lambda$xHwdcjzyifebrbQ-Z0dzAUNHqzE, reason: not valid java name */
    public static boolean m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE() {
        Context context = aplicationContext;
        if (!(context != null ? INSTANCE.hasAppFeature(context, FEATURE_SYSTEMUI_LIGHT_OS) : false)) {
            KeyguardFeatureOption.INSTANCE.getClass();
            if (KeyguardFeatureOption.sKeyguardLightOsLevel < 1) {
                return false;
            }
        }
        return true;
    }

    static {
        final int i = 0;
        isLowBatteryNoSound$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i2 = 2;
        isChargingErrorNoAlert$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i2) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i3 = 14;
        isDynamicFPSSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i3) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i4 = 26;
        getGoAfterServiceInCN$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i4) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i5 = 8;
        firstApiLevel$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i5) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i6 = 20;
        isFirstApiLevelLessThanV$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i6) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i7 = 3;
        isFirstApiLevelLessThanB$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i7) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i8 = 14;
        isFirstApiLevelMoreThanC$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i8) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i9 = 20;
        isAgingVersion$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i9) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i10 = 21;
        isRuRegionMark$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i10) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i11 = 11;
        isCmccCustomizedVersion$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i11) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i12 = 22;
        isSupportNavBarButtonsPosition$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i12) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i13 = 0;
        isExpRegion$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i13) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i14 = 14;
        isTablet$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i14) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i15 = 25;
        isLightOs$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i15) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i16 = 0;
        isLightBase$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i16) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i17 = 17;
        isOplusConfigGo$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i17) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i18 = 22;
        isLightOsForLH$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i18) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i19 = 23;
        isTablet5G$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i19) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i20 = 1;
        isTabletWifi$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i20) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i21 = 3;
        isWifiOnly$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i21) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i22 = 4;
        isFoldDevice$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i22) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i23 = 5;
        isRectangleDisplay$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i23) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i24 = 6;
        isWhiteSwanFoldDevice$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i24) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i25 = 7;
        isDragonfly$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i25) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i26 = 8;
        isLargeScreenFolding$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i26) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i27 = 9;
        isWeaverLockSettings$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i27) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i28 = 10;
        isOplusAodSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i28) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i29 = 12;
        isSupportUnderScreenProximity$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i29) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i30 = 13;
        isSupportPocketSensor$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i30) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i31 = 15;
        isVelocityFeatureSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i31) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i32 = 16;
        disablePowerSave$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i32) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i33 = 17;
        isLiteDebugMode$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i33) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i34 = 18;
        liteLiveAlertDevice$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i34) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i35 = 19;
        fullLiveAlertDevice$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i35) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i36 = 20;
        fluidDevice$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i36) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i37 = 21;
        binderPoolExpand$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i37) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i38 = 23;
        fluidDeviceForCharge$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i38) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i39 = 24;
        isFoldRemapDisabled$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i39) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i40 = 25;
        isSupportWmSizeResolutionSwitch$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i40) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i41 = 27;
        isPlatformMTK$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i41) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i42 = 28;
        isPlatformQCOM$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i42) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i43 = 29;
        isSupportMotorHardware$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:43:0x00be  */
            public final Object invoke() {
                boolean z = true;
                switch (i43) {
                    case 0:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$LaGYaqcIknSAPTm3CTGLbnz9fKU());
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && FeatureOption.isWifiOnly());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$uh8Tv_d6T8mV0M81PhtTmhNIVA4());
                    case 3:
                        return Boolean.valueOf(FeatureOption.m613$r8$lambda$GgAK9zqnb5c96xZj5N6xY0BxM());
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.type.fold") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.revise_square_display_orientation")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.revise_rectangle_display_orientation");
                    case 6:
                        return Boolean.valueOf(FeatureOption.m615$r8$lambda$VF4bASqTemBlv_gbJXDzaW3wRg());
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled());
                    case 8:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled());
                    case 9:
                        return Boolean.valueOf(FeatureOption.m612$r8$lambda$QThH38XDoJjK_DwT8XH_gT_xwg());
                    case 10:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 11:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Zx7Pj0ffJz3c2HBLpTB5DoF9ebc());
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$Ffa6qZz_nUyKaTOh4wndNXDRTL8());
                    case 13:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$CLZzhVLCxboAJsWFgKzaWAMstvI());
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.velocitytracker.strategy.support");
                    case 16:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.power.disable_power_save");
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.debug.lite_livealert", false));
                    case 18:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_lite_livealert")) {
                            FeatureOption.INSTANCE.getClass();
                            if (!FeatureOption.isLiteDebugMode()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FeatureOption.INSTANCE.getClass();
                            z = FeatureOption.isLiteDebugMode() ? false : true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$V19YusYNZ56oDwLfsLdWviPbR_4());
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.binder.thread_pool_expand");
                    case 22:
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isTablet() && (!FeatureOption.isFoldDevice() || FeatureOption.isFoldRemapDisabled())) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 23:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry")) {
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (!FlavorOneFeatureOption.isSupportMiniCapsule()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fold_remap_display_disabled");
                    case 25:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.wm_size_resolution_switch_support");
                    case 26:
                        return Boolean.valueOf(FeatureOption.getGoAfterServiceInCN_delegate$lambda$6());
                    case 27:
                        FeatureOption.INSTANCE.getClass();
                        String str = Build.HARDWARE;
                        str.getClass();
                        return Boolean.valueOf(new Regex("mt[0-9]*").matches(str));
                    case 28:
                        FeatureOption.INSTANCE.getClass();
                        String str2 = Build.HARDWARE;
                        str2.getClass();
                        return Boolean.valueOf(new Regex("qcom").matches(str2));
                    default:
                        return Boolean.valueOf(FeatureOption.m614$r8$lambda$S05f_catjW7SJ0XfmJydRoEc9M());
                }
            }
        });
        final int i44 = 1;
        isMtkGeminiSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i44) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i45 = 2;
        isQcomGeminiSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i45) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i46 = 3;
        hasFlashlight$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i46) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i47 = 4;
        isPanicVersion$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i47) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i48 = 5;
        isAlwaysOnEnable$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i48) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i49 = 6;
        isSupportAppFrozen$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i49) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i50 = 7;
        isFactoryMode$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i50) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i51 = 9;
        isHighTempAgingMode$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i51) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i52 = 10;
        isBattWarningAllowed$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i52) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i53 = 11;
        isFeaturesDisplaying$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i53) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i54 = 12;
        isOplusAlertSlider$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i54) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i55 = 13;
        isOplusActionButton$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i55) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i56 = 15;
        isSupportGameHyperButton$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i56) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i57 = 16;
        isOplusVolumeKeyInRight$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i57) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i58 = 17;
        isOplusTopVolumeKey$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i58) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i59 = 18;
        isSupportOnlySingleCard$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i59) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i60 = 19;
        isFactoryVersion$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i60) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i61 = 21;
        isXLinearVibratorSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i61) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i62 = 22;
        isLinearVibratorSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i62) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i63 = 23;
        isLuxunVibratorSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i63) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i64 = 24;
        isVersionSellMode$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i64) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i65 = 26;
        isEarScreen$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i65) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i66 = 27;
        sGameScreenOffActPreload$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i66) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i67 = 28;
        isGameMlbbSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i67) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i68 = 29;
        isOplusMediaControlSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                switch (i68) {
                    case 0:
                        return Boolean.valueOf(!TextUtils.equals(SystemProperties.get("ro.oplus.image.system_ext.area", "domestic"), "domestic"));
                    case 1:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$RZZfFv3GAgqp4_LPAyIrwu2fnPI());
                    case 2:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$walZqbLkNyp5kKM_k163Fi1w57c());
                    case 3:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$1yMrhMP2_zwtSriiO6GfkaL18t8());
                    case 4:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.assert.panic", true));
                    case 5:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.sys.alwayson.enable", false));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_app_frozen");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "factory"));
                    case 8:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 9:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.image.my_engineering.type", ShadeExpansionHelperImpl.VAL_INVALID), "hightempaging"));
                    case 10:
                        return Boolean.valueOf(SystemProperties.getBoolean("oplus.battery.warning.allow", true));
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.new_features_displaying");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                    case 13:
                        return Boolean.valueOf(SystemProperties.getInt("ro.oplus.key.actionbutton", 0) == 1);
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.type.tablet");
                    case 15:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.hyper_button_support");
                    case 16:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.right_volume_key", false));
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("persist.oplus.software.audio.top_volume_key", false));
                    case 18:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$2qTu46cLaOW0gHD43Op5VCnTu2Y());
                    case 19:
                        Object objStaticMethodInvoke = ReflectionUtils.staticMethodInvoke("android.os.ProjectManager", "getEngVersion", null, new Object[0]);
                        return Boolean.valueOf(objStaticMethodInvoke != null ? objStaticMethodInvoke.equals(12) : false);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.vibrator_xlinear_type");
                    case 22:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_lmvibrator");
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 24:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.pms_sellmode");
                    case 25:
                        return Boolean.valueOf(FeatureOption.m617$r8$lambda$xHwdcjzyifebrbQZ0dzAUNHqzE());
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game_scroff_act_preload");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.mlbb_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.media_control");
                }
            }
        });
        final int i69 = 1;
        isOplusAssistantVolumeSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i69) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i70 = 2;
        isOplusSatelliteSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i70) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i71 = 4;
        isMiscFanSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i71) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i72 = 5;
        isGameLolSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i72) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i73 = 6;
        isGamePelSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i73) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i74 = 7;
        isSupportReverseCharging$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i74) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i75 = 8;
        isSupportGameBetopJoystick$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i75) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i76 = 9;
        isSupportPanoramaWorkStation$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i76) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i77 = 10;
        isSupportMyDevice$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i77) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i78 = 11;
        isDualSimConcurrently$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i78) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i79 = 12;
        isSupportKeyboardAIFunction$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i79) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i80 = 13;
        isSupportBreenoMemos$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i80) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i81 = 15;
        isSupportScenarioAssistant$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i81) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i82 = 16;
        isLauncherOneShotSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i82) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i83 = 18;
        isStudyCenterFaceSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i83) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
        final int i84 = 19;
        isBrightnessUxSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FeatureOption$$ExternalSyntheticLambda6
            public final Object invoke() {
                boolean z = true;
                switch (i84) {
                    case 0:
                        String str = SystemProperties.get("ro.oplus.os_type", "");
                        String str2 = SystemProperties.get("debug.ro.oplus.os_type", "");
                        if (!Intrinsics.areEqual(str, "light_base") && !Intrinsics.areEqual(str2, "light_base")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.assistant_volume_support");
                    case 2:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.tt_satellite_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.bdsatellite_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 3:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() < 36);
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.misc.fan.support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.lol_support");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.game.pel_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.wired_reverse_charging");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.joystick.game_betop_joystick_support");
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wms.panorama_work_station") && !SystemProperties.getBoolean("persist.oplus.panorama.branch.foldable.enable", false) && !SystemProperties.getBoolean("persist.oplus.panorama.branch.enable", false)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 10:
                        return Boolean.valueOf(FeatureOption.showDevicesExpRegion);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.radio.dual_ps");
                    case 12:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$7xoR79lTgeXePOtVpY5B9OXi3Kw());
                    case 13:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.directservice.finger_flashnotes_enable");
                    case 14:
                        return Boolean.valueOf(FeatureOption.INSTANCE.getFirstApiLevel() >= 37);
                    case 15:
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isExpRegion() || ScrimUtil.isBAnimLevel() || (!OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.systemui.scenario_assistant_support") && !SystemProperties.getBoolean("persist.feature.scenario_assistant.enabled", false))) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 16:
                        return FeatureOption.$r8$lambda$5wtOzqO2QMzXggJq5w9B5GSin4I();
                    case 17:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.config.go", false));
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.study_room_face_support");
                    case 19:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.agingtest", "0")));
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.regionmark", "").equals("RU"));
                    case 22:
                        return Boolean.valueOf(FeatureOption.$r8$lambda$XQyttYPdYYbfF66N6VfEtWeJkTU());
                    default:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet() && !FeatureOption.isWifiOnly());
                }
            }
        });
    }

    private final boolean getBinderPoolExpand() {
        return ((Boolean) binderPoolExpand$delegate.getValue()).booleanValue();
    }

    public static final boolean getDisableKeyguardBlurStacking() {
        return disableKeyguardBlurStacking;
    }

    public static /* synthetic */ void getDisableKeyguardBlurStacking$annotations() {
    }

    private final boolean getDisablePowerSave() {
        return ((Boolean) disablePowerSave$delegate.getValue()).booleanValue();
    }

    public static final boolean getEnableLightFeature() {
        return enableLightFeature;
    }

    public static /* synthetic */ void getEnableLightFeature$annotations() {
    }

    public static final boolean getEnableLiveAlertEffectLight() {
        return enableLiveAlertEffectLight;
    }

    public static /* synthetic */ void getEnableLiveAlertEffectLight$annotations() {
    }

    public static final boolean getEnableMaterialFeature() {
        return enableMaterialFeature;
    }

    public static /* synthetic */ void getEnableMaterialFeature$annotations() {
    }

    private final boolean getFluidDevice() {
        return ((Boolean) fluidDevice$delegate.getValue()).booleanValue();
    }

    private final boolean getFluidDeviceForCharge() {
        return ((Boolean) fluidDeviceForCharge$delegate.getValue()).booleanValue();
    }

    private final boolean getFullLiveAlertDevice() {
        return ((Boolean) fullLiveAlertDevice$delegate.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getGoAfterServiceInCN_delegate$lambda$6() {
        Context context = aplicationContext;
        if (context != null) {
            return AppFeatureProviderUtils.getBoolean(context.getContentResolver(), FEATURE_GO_TO_AFTER_SERVICE_CN, false);
        }
        return false;
    }

    private final boolean getLiteLiveAlertDevice() {
        return ((Boolean) liteLiveAlertDevice$delegate.getValue()).booleanValue();
    }

    public static final boolean getUseLayeredPanelAnimation() {
        return useLayeredPanelAnimation;
    }

    public static /* synthetic */ void getUseLayeredPanelAnimation$annotations() {
    }

    public static boolean isAgingVersion() {
        return ((Boolean) isAgingVersion$delegate.getValue()).booleanValue();
    }

    public static boolean isBattWarningAllowed() {
        return ((Boolean) isBattWarningAllowed$delegate.getValue()).booleanValue();
    }

    public static boolean isBrightnessUxSupport() {
        return ((Boolean) isBrightnessUxSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isChargingErrorNoAlert() {
        return ((Boolean) isChargingErrorNoAlert$delegate.getValue()).booleanValue();
    }

    public static boolean isCmccCustomizedVersion() {
        return ((Boolean) isCmccCustomizedVersion$delegate.getValue()).booleanValue();
    }

    public static boolean isDragonfly() {
        return ((Boolean) isDragonfly$delegate.getValue()).booleanValue();
    }

    public static boolean isDualSimConcurrently() {
        return ((Boolean) isDualSimConcurrently$delegate.getValue()).booleanValue();
    }

    public static boolean isDynamicFPSSupport() {
        return ((Boolean) isDynamicFPSSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isEarScreen() {
        return ((Boolean) isEarScreen$delegate.getValue()).booleanValue();
    }

    public static boolean isExpRegion() {
        return ((Boolean) isExpRegion$delegate.getValue()).booleanValue();
    }

    public static boolean isFactoryMode() {
        return ((Boolean) isFactoryMode$delegate.getValue()).booleanValue();
    }

    public static boolean isFactoryVersion() {
        return ((Boolean) isFactoryVersion$delegate.getValue()).booleanValue();
    }

    public static boolean isFeaturesDisplaying() {
        return ((Boolean) isFeaturesDisplaying$delegate.getValue()).booleanValue();
    }

    public static boolean isFirstApiLevelLessThanB() {
        return ((Boolean) isFirstApiLevelLessThanB$delegate.getValue()).booleanValue();
    }

    public static final boolean isFluidDevice() {
        return INSTANCE.getFluidDevice();
    }

    public static final boolean isFluidDeviceForCharge() {
        return INSTANCE.getFluidDeviceForCharge();
    }

    public static boolean isFoldDevice() {
        return ((Boolean) isFoldDevice$delegate.getValue()).booleanValue();
    }

    public static boolean isFoldRemapDisabled() {
        return ((Boolean) isFoldRemapDisabled$delegate.getValue()).booleanValue();
    }

    public static final boolean isFullLiveAlertDevice() {
        FeatureOption featureOption = INSTANCE;
        return featureOption.getFullLiveAlertDevice() && !featureOption.getLiteLiveAlertDevice();
    }

    public static boolean isGameLolSupport() {
        return ((Boolean) isGameLolSupport$delegate.getValue()).booleanValue();
    }

    public static final boolean isGameMlbbSupport() {
        return ((Boolean) isGameMlbbSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isGamePelSupport() {
        return ((Boolean) isGamePelSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isHighTempAgingMode() {
        return ((Boolean) isHighTempAgingMode$delegate.getValue()).booleanValue();
    }

    public static boolean isLargeScreenFolding() {
        return ((Boolean) isLargeScreenFolding$delegate.getValue()).booleanValue();
    }

    public static final Boolean isLauncherOneShotSupport() {
        return (Boolean) isLauncherOneShotSupport$delegate.getValue();
    }

    public static boolean isLightBase() {
        return ((Boolean) isLightBase$delegate.getValue()).booleanValue();
    }

    public static boolean isLightOs() {
        return ((Boolean) isLightOs$delegate.getValue()).booleanValue();
    }

    public static boolean isLightOsForLH() {
        return ((Boolean) isLightOsForLH$delegate.getValue()).booleanValue();
    }

    public static boolean isLinearVibratorSupport() {
        return ((Boolean) isLinearVibratorSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isLiteDebugMode() {
        return ((Boolean) isLiteDebugMode$delegate.getValue()).booleanValue();
    }

    public static final boolean isLiteLiveAlertDevice() {
        return INSTANCE.getLiteLiveAlertDevice();
    }

    public static boolean isLowBatteryNoSound() {
        return ((Boolean) isLowBatteryNoSound$delegate.getValue()).booleanValue();
    }

    public static boolean isLuxunVibratorSupport() {
        return ((Boolean) isLuxunVibratorSupport$delegate.getValue()).booleanValue();
    }

    public static final boolean isMiscFanSupport() {
        return ((Boolean) isMiscFanSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isMtkGeminiSupport() {
        return ((Boolean) isMtkGeminiSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isOplusActionButton() {
        return ((Boolean) isOplusActionButton$delegate.getValue()).booleanValue();
    }

    public static boolean isOplusAlertSlider() {
        return ((Boolean) isOplusAlertSlider$delegate.getValue()).booleanValue();
    }

    public static boolean isOplusAodSupport() {
        return ((Boolean) isOplusAodSupport$delegate.getValue()).booleanValue();
    }

    public static final boolean isOplusAssistantVolumeSupport() {
        return ((Boolean) isOplusAssistantVolumeSupport$delegate.getValue()).booleanValue();
    }

    public static final boolean isOplusConfigGo() {
        return ((Boolean) isOplusConfigGo$delegate.getValue()).booleanValue();
    }

    public static final boolean isOplusMediaControlSupport() {
        return ((Boolean) isOplusMediaControlSupport$delegate.getValue()).booleanValue();
    }

    public static final boolean isOplusSatelliteSupport() {
        return ((Boolean) isOplusSatelliteSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isOplusTopVolumeKey() {
        return ((Boolean) isOplusTopVolumeKey$delegate.getValue()).booleanValue();
    }

    public static boolean isOplusVolumeKeyInRight() {
        return ((Boolean) isOplusVolumeKeyInRight$delegate.getValue()).booleanValue();
    }

    public static boolean isPanicVersion() {
        return ((Boolean) isPanicVersion$delegate.getValue()).booleanValue();
    }

    public static boolean isPlatformMTK() {
        return ((Boolean) isPlatformMTK$delegate.getValue()).booleanValue();
    }

    public static boolean isPlatformQCOM() {
        return ((Boolean) isPlatformQCOM$delegate.getValue()).booleanValue();
    }

    public static boolean isQcomGeminiSupport() {
        return ((Boolean) isQcomGeminiSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isRuRegionMark() {
        return ((Boolean) isRuRegionMark$delegate.getValue()).booleanValue();
    }

    public static boolean isStudyCenterFaceSupport() {
        return ((Boolean) isStudyCenterFaceSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportAppFrozen() {
        return ((Boolean) isSupportAppFrozen$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportBreenoMemos() {
        return ((Boolean) isSupportBreenoMemos$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportGameBetopJoystick() {
        return ((Boolean) isSupportGameBetopJoystick$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportGameHyperButton() {
        return ((Boolean) isSupportGameHyperButton$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportKeyboardAIFunction() {
        return ((Boolean) isSupportKeyboardAIFunction$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportMotorHardware() {
        return ((Boolean) isSupportMotorHardware$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportMyDevice() {
        return ((Boolean) isSupportMyDevice$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportNavBarButtonsPosition() {
        return ((Boolean) isSupportNavBarButtonsPosition$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportOnlySingleCard() {
        return ((Boolean) isSupportOnlySingleCard$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportPanoramaWorkStation() {
        return ((Boolean) isSupportPanoramaWorkStation$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportPocketSensor() {
        return ((Boolean) isSupportPocketSensor$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportReverseCharging() {
        return ((Boolean) isSupportReverseCharging$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportScenarioAssistant() {
        return ((Boolean) isSupportScenarioAssistant$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportUnderScreenProximity() {
        return ((Boolean) isSupportUnderScreenProximity$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportWmSizeResolutionSwitch() {
        return ((Boolean) isSupportWmSizeResolutionSwitch$delegate.getValue()).booleanValue();
    }

    public static boolean isTablet() {
        return ((Boolean) isTablet$delegate.getValue()).booleanValue();
    }

    public static boolean isTablet5G() {
        return ((Boolean) isTablet5G$delegate.getValue()).booleanValue();
    }

    public static boolean isTabletWifi() {
        return ((Boolean) isTabletWifi$delegate.getValue()).booleanValue();
    }

    public static boolean isVelocityFeatureSupport() {
        return ((Boolean) isVelocityFeatureSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isVersionSellMode() {
        return ((Boolean) isVersionSellMode$delegate.getValue()).booleanValue();
    }

    public static boolean isWeaverLockSettings() {
        return ((Boolean) isWeaverLockSettings$delegate.getValue()).booleanValue();
    }

    public static boolean isWhiteSwanFoldDevice() {
        return ((Boolean) isWhiteSwanFoldDevice$delegate.getValue()).booleanValue();
    }

    public static boolean isWifiOnly() {
        return ((Boolean) isWifiOnly$delegate.getValue()).booleanValue();
    }

    public static boolean isXLinearVibratorSupport() {
        return ((Boolean) isXLinearVibratorSupport$delegate.getValue()).booleanValue();
    }

    public static final void setDisableKeyguardBlurStacking(boolean z) {
        disableKeyguardBlurStacking = z;
    }

    public static final void setEnableLightFeature(boolean z) {
        enableLightFeature = z;
    }

    public static final void setEnableLiveAlertEffectLight(boolean z) {
        enableLiveAlertEffectLight = z;
    }

    public static final void setEnableMaterialFeature(boolean z) {
        enableMaterialFeature = z;
    }

    public static final void setMediaImmersiveFullEnable(boolean z) {
        isMediaImmersiveFullEnable = z;
    }

    public static final void setSupportMultiModeFlashLight(boolean z) {
        isSupportMultiModeFlashLight = z;
    }

    public final int getFirstApiLevel() {
        return ((Number) firstApiLevel$delegate.getValue()).intValue();
    }

    public final boolean getGetGoAfterServiceInCN() {
        return ((Boolean) getGoAfterServiceInCN$delegate.getValue()).booleanValue();
    }

    public final boolean getHasFlashlight() {
        return ((Boolean) hasFlashlight$delegate.getValue()).booleanValue();
    }

    public final boolean getSGameScreenOffActPreload() {
        return ((Boolean) sGameScreenOffActPreload$delegate.getValue()).booleanValue();
    }

    public final boolean hasAppFeature(Context context, String str) {
        return AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), AppFeatureProviderUtils.FeatureID.STATIC_COMPONENT, str);
    }

    public final void init(final Context context) {
        Bundle bundle;
        PackageManager packageManager;
        AppFeatureProviderUtils.FeatureID featureID = AppFeatureProviderUtils.FeatureID.STATIC_COMPONENT;
        Log.i(TAG, "init feature option start, appContext: " + context);
        aplicationContext = context;
        FlavorOneFeatureOption flavorOneFeatureOption = FlavorOneFeatureOption.INSTANCE;
        flavorOneFeatureOption.getClass();
        if (FlavorOneFeatureOption.isFlavorOneDevice()) {
            appFeatureList.addAll(flavorOneFeatureOption.getAppFeatureCacheList());
        } else {
            FlavorTwoFeatureOption flavorTwoFeatureOption = FlavorTwoFeatureOption.INSTANCE;
            flavorTwoFeatureOption.getClass();
            if (FlavorTwoFeatureOption.isFlavorTwoDevice()) {
                appFeatureList.addAll(flavorTwoFeatureOption.getAppFeatureCacheList());
            }
        }
        List<String> list = appFeatureList;
        list.addAll(QSFeatureOption.INSTANCE.getAppFeatureCacheList());
        list.addAll(AodFeatureOption.INSTANCE.getAppFeatureCacheList());
        list.addAll(KeyguardFeatureOption.INSTANCE.getAppFeatureCacheList());
        list.addAll(StatusBarFeatureOption.INSTANCE.getAppFeatureCacheList());
        list.addAll(ChargingFeatureOption.INSTANCE.getAppFeatureCacheList());
        list.addAll(UsbFeatureOption.INSTANCE.getAppFeatureCacheList());
        list.addAll(NotificationFeatureOption.INSTANCE.getAppFeatureCacheList());
        list.addAll(NavBarFeatureOption.INSTANCE.getAppFeatureCacheList());
        list.addAll(EdgePanelFeatureOption.getAppFeatureCacheList());
        list.add(FEATURE_DISABLE_LOW_BATTERY_DIALOG);
        list.add(PREVENT_START_SERVICES_IF_NEEDED);
        list.add(FEATURE_DISABLE_LAYERED_ANIMATION);
        list.add(FEATURE_DISABLE_KEYGUARD_BLUR_STACKING);
        list.add(ENABLE_LIGHT_FEATURE);
        list.add(ENABLE_MATERIAL_FEATURE);
        list.add(ENABLE_LIVEALERT_EFFECT_LIGHT);
        list.add(IS_MEDIA_IMMERSIVE_FULLSCREEN);
        list.add(IS_SUPPORT_MULTIMODE_FLASHLIGHT);
        list.add(FEATURE_INSPIRATION_CLOCK_ATTR_ENABLE);
        list.addAll(CustomizeFeatureOption.getAppFeatureCacheList());
        list.addAll(VolumeFeatureOption.INSTANCE.getAppFeatureCacheList());
        loadAppFeature(context);
        CustomizeFeatureOption.sContext = context;
        CustomizeFeatureOption.sIsSystemUiExpSignalUi = context.getResources().getBoolean(2131034183);
        LogUtil.i(LogUtil.TAG_QS, "CustomizeFeatureOption", "registerFeatureObserver");
        OplusFeatureConfigManager oplusFeatureConfigManager = OplusFeatureConfigManager.getInstance();
        ArrayList arrayList = CustomizeFeatureOption.sSlotOplusFeatureList;
        IOplusFeatureConfigManager.FeatureID featureID2 = IOplusFeatureConfigManager.FeatureID.DYNAMIC_SIMSLOT_1;
        CustomizeFeatureOption.SlotOplusFeatureObserver slotOplusFeatureObserver = CustomizeFeatureOption.sSlotOplusFeatureObserver;
        oplusFeatureConfigManager.registerFeatureObserver(arrayList, featureID2, slotOplusFeatureObserver);
        OplusFeatureConfigManager oplusFeatureConfigManager2 = OplusFeatureConfigManager.getInstance();
        IOplusFeatureConfigManager.FeatureID featureID3 = IOplusFeatureConfigManager.FeatureID.DYNAMIC_SIMSLOT_2;
        oplusFeatureConfigManager2.registerFeatureObserver(arrayList, featureID3, slotOplusFeatureObserver);
        OplusFeatureConfigManager.getInstance().registerFeatureObserver(CustomizeFeatureOption.sOplusFeatureList, CustomizeFeatureOption.sOplusFeatureObserver);
        ThreadManager.getInstance().start();
        final Handler threadHandler = ThreadManager.getInstance().getThreadHandler();
        ContentResolver contentResolver = context.getContentResolver();
        AppFeatureProviderUtils.FeatureID featureID4 = AppFeatureProviderUtils.FeatureID.DYNAMIC_SIMSLOT_1;
        boolean z = false;
        z = false;
        z = false;
        z = false;
        final int i = z ? 1 : 0;
        ContentObserver contentObserver = new ContentObserver(threadHandler) { // from class: com.oplusos.systemui.common.feature.CustomizeFeatureOption.6
            public final /* synthetic */ int $r8$classId;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public /* synthetic */ AnonymousClass6(final Handler threadHandler2) {
                super(threadHandler2);
                i = i;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z2) {
                switch (i) {
                    case 0:
                        LogUtil.i(LogUtil.TAG_QS, "CustomizeFeatureOption", "appFeature onChange FeatureID.DYNAMIC_SIMSLOT_1");
                        CustomizeFeatureOption.loadAppFeatureForSlotId(CustomizeFeatureOption.sContext, AppFeatureProviderUtils.FeatureID.DYNAMIC_SIMSLOT_1);
                        Iterator it = CustomizeFeatureOption.sSlotFeatureChangeCallBacks.iterator();
                        while (it.hasNext()) {
                            ((OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1) it.next()).onFeatureChange();
                        }
                        break;
                    case 1:
                        LogUtil.i(LogUtil.TAG_QS, "CustomizeFeatureOption", "appFeature onChange FeatureID.DYNAMIC_SIMSLOT_2");
                        CustomizeFeatureOption.loadAppFeatureForSlotId(CustomizeFeatureOption.sContext, AppFeatureProviderUtils.FeatureID.DYNAMIC_SIMSLOT_2);
                        Iterator it2 = CustomizeFeatureOption.sSlotFeatureChangeCallBacks.iterator();
                        while (it2.hasNext()) {
                            ((OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1) it2.next()).onFeatureChange();
                        }
                        break;
                    default:
                        CustomizeFeatureOption.sHideOrganizationMsg = AppFeatureProviderUtils.isFeatureSupport(CustomizeFeatureOption.sContext.getContentResolver(), AppFeatureProviderUtils.FeatureID.STATIC_COMPONENT, "com.android.systemui.hide_organization_message");
                        ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_QS, "CustomizeFeatureOption", new StringBuilder("appFeature:HIDE_ORGANIZATION_MSG onChange: "), CustomizeFeatureOption.sHideOrganizationMsg);
                        break;
                }
            }
        };
        Uri uriForFeatureID = AppFeatureProviderUtils.getUriForFeatureID(featureID4);
        final int i2 = 1;
        if (contentResolver != null) {
            contentResolver.registerContentObserver(uriForFeatureID, true, contentObserver);
        }
        ContentResolver contentResolver2 = context.getContentResolver();
        AppFeatureProviderUtils.FeatureID featureID5 = AppFeatureProviderUtils.FeatureID.DYNAMIC_SIMSLOT_2;
        ContentObserver contentObserver2 = new ContentObserver(threadHandler2) { // from class: com.oplusos.systemui.common.feature.CustomizeFeatureOption.6
            public final /* synthetic */ int $r8$classId;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public /* synthetic */ AnonymousClass6(final Handler threadHandler2) {
                super(threadHandler2);
                i = i2;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z2) {
                switch (i) {
                    case 0:
                        LogUtil.i(LogUtil.TAG_QS, "CustomizeFeatureOption", "appFeature onChange FeatureID.DYNAMIC_SIMSLOT_1");
                        CustomizeFeatureOption.loadAppFeatureForSlotId(CustomizeFeatureOption.sContext, AppFeatureProviderUtils.FeatureID.DYNAMIC_SIMSLOT_1);
                        Iterator it = CustomizeFeatureOption.sSlotFeatureChangeCallBacks.iterator();
                        while (it.hasNext()) {
                            ((OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1) it.next()).onFeatureChange();
                        }
                        break;
                    case 1:
                        LogUtil.i(LogUtil.TAG_QS, "CustomizeFeatureOption", "appFeature onChange FeatureID.DYNAMIC_SIMSLOT_2");
                        CustomizeFeatureOption.loadAppFeatureForSlotId(CustomizeFeatureOption.sContext, AppFeatureProviderUtils.FeatureID.DYNAMIC_SIMSLOT_2);
                        Iterator it2 = CustomizeFeatureOption.sSlotFeatureChangeCallBacks.iterator();
                        while (it2.hasNext()) {
                            ((OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1) it2.next()).onFeatureChange();
                        }
                        break;
                    default:
                        CustomizeFeatureOption.sHideOrganizationMsg = AppFeatureProviderUtils.isFeatureSupport(CustomizeFeatureOption.sContext.getContentResolver(), AppFeatureProviderUtils.FeatureID.STATIC_COMPONENT, "com.android.systemui.hide_organization_message");
                        ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_QS, "CustomizeFeatureOption", new StringBuilder("appFeature:HIDE_ORGANIZATION_MSG onChange: "), CustomizeFeatureOption.sHideOrganizationMsg);
                        break;
                }
            }
        };
        Uri uriForFeatureID2 = AppFeatureProviderUtils.getUriForFeatureID(featureID5);
        if (contentResolver2 != null) {
            contentResolver2.registerContentObserver(uriForFeatureID2, true, contentObserver2);
        }
        ContentResolver contentResolver3 = context.getContentResolver();
        final int i3 = 2;
        ContentObserver contentObserver3 = new ContentObserver(threadHandler2) { // from class: com.oplusos.systemui.common.feature.CustomizeFeatureOption.6
            public final /* synthetic */ int $r8$classId;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public /* synthetic */ AnonymousClass6(final Handler threadHandler2) {
                super(threadHandler2);
                i = i3;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z2) {
                switch (i) {
                    case 0:
                        LogUtil.i(LogUtil.TAG_QS, "CustomizeFeatureOption", "appFeature onChange FeatureID.DYNAMIC_SIMSLOT_1");
                        CustomizeFeatureOption.loadAppFeatureForSlotId(CustomizeFeatureOption.sContext, AppFeatureProviderUtils.FeatureID.DYNAMIC_SIMSLOT_1);
                        Iterator it = CustomizeFeatureOption.sSlotFeatureChangeCallBacks.iterator();
                        while (it.hasNext()) {
                            ((OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1) it.next()).onFeatureChange();
                        }
                        break;
                    case 1:
                        LogUtil.i(LogUtil.TAG_QS, "CustomizeFeatureOption", "appFeature onChange FeatureID.DYNAMIC_SIMSLOT_2");
                        CustomizeFeatureOption.loadAppFeatureForSlotId(CustomizeFeatureOption.sContext, AppFeatureProviderUtils.FeatureID.DYNAMIC_SIMSLOT_2);
                        Iterator it2 = CustomizeFeatureOption.sSlotFeatureChangeCallBacks.iterator();
                        while (it2.hasNext()) {
                            ((OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1) it2.next()).onFeatureChange();
                        }
                        break;
                    default:
                        CustomizeFeatureOption.sHideOrganizationMsg = AppFeatureProviderUtils.isFeatureSupport(CustomizeFeatureOption.sContext.getContentResolver(), AppFeatureProviderUtils.FeatureID.STATIC_COMPONENT, "com.android.systemui.hide_organization_message");
                        ExpandHelper$.ExternalSyntheticOutline0.m$1(LogUtil.TAG_QS, "CustomizeFeatureOption", new StringBuilder("appFeature:HIDE_ORGANIZATION_MSG onChange: "), CustomizeFeatureOption.sHideOrganizationMsg);
                        break;
                }
            }
        };
        Uri uriForFeatureID3 = AppFeatureProviderUtils.getUriForFeatureID(featureID);
        if (contentResolver3 != null) {
            contentResolver3.registerContentObserver(uriForFeatureID3, true, contentObserver3);
        }
        CustomizeFeatureOption.loadAppFeature(context);
        CustomizeFeatureOption.loadAppFeatureForSlotId(CustomizeFeatureOption.sContext, featureID4);
        CustomizeFeatureOption.loadAppFeatureForSlotId(CustomizeFeatureOption.sContext, featureID5);
        CustomizeFeatureOption.loadOplusFeatureForSlotId(featureID2);
        CustomizeFeatureOption.loadOplusFeatureForSlotId(featureID3);
        context.getPackageManager().hasSystemFeature("oplus.phone.show.google.emergencydialer");
        CustomizeFeatureOption.sIsSupportJapanFelica = SystemProperties.getBoolean("ro.oplus.nfc.support_japan_felica", false);
        context.getPackageManager().hasSystemFeature("oplus.customize.splitscreen.disable");
        CustomizeFeatureOption.sIsGeminiExperience = context.getPackageManager().hasSystemFeature("com.google.android.feature.GEMINI_EXPERIENCE");
        CustomizeFeatureOption.sIsSupportFiveSignalBar = OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.five_signal_bar");
        OplusPlatformLevelUtils.getInstance(context).getPlatformLevel(1);
        CustomizeFeatureOption.sIsSupportMode = OplusFeatureConfigManager.getInstance().hasFeature("os.charge.endurance.mode.support");
        CustomizeFeatureOption.sIsSupportHomeWaveVibration = OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.vibrator.long_press_home_wave_support");
        CustomizeFeatureOption.sWlanIconAlwaysOn = OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wlan.assistant.wlan_icon_always_on");
        OplusDevicepolicyManager oplusDevicepolicyManager = OplusDevicepolicyManager.getInstance();
        CustomizeFeatureOption.sOplusDevicepolicyManager = oplusDevicepolicyManager;
        if (oplusDevicepolicyManager != null) {
            oplusDevicepolicyManager.registerOplusDevicepolicyObserver("oplus.software.customize.auto_brightness_force_mode", CustomizeFeatureOption.sAutoBrightnessForceModeObserver);
            CustomizeFeatureOption.sOplusDevicepolicyManager.registerOplusDevicepolicyObserver("auto_brightness_switch_status", CustomizeFeatureOption.sBrightnessSlidersObserver);
            CustomizeFeatureOption.sOplusDevicepolicyManager.registerOplusDevicepolicyObserver("NO_DISTURB_DISABLED", CustomizeFeatureOption.sNoDisturbDisabledbserver);
            CustomizeFeatureOption.sOplusDevicepolicyManager.registerOplusDevicepolicyObserver("persist.sys.oplus.customize.forbcap", CustomizeFeatureOption.sScreenCaptureDisabledObserver);
            CustomizeFeatureOption.sOplusDevicepolicyManager.registerOplusDevicepolicyObserver("custom_sysui_qs_hide_tiles_list", CustomizeFeatureOption.sHiddenQsTilesObserver);
        } else {
            Log.e("CustomizeFeatureOption", "mOplusDevicepolicyManager null");
        }
        INSTANCE.getClass();
        CustomizeFeatureOption.sIsSupportCircleToSearch = isExpRegion() && context.getPackageManager().hasSystemFeature("com.google.android.feature.CONTEXTUAL_SEARCH");
        if (isExpRegion()) {
            context.getPackageManager().hasSystemFeature("oplus.software.circle_to_search_guide");
        }
        CustomizeFeatureOption.sIsSupportCircleToSearchNavbarHidden = CustomizeFeatureOption.sIsSupportCircleToSearch && OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.navbar_hidden_contextual_search");
        try {
            Context context2 = aplicationContext;
            ApplicationInfo applicationInfo = (context2 == null || (packageManager = context2.getPackageManager()) == null) ? null : packageManager.getApplicationInfo(DEVICES_COMPONENT_NAME, 128);
            if (applicationInfo != null && (bundle = applicationInfo.metaData) != null && bundle.getInt(DEVICES_DATA_NAME) == 1) {
                z = true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, "e:" + e);
        }
        showDevicesExpRegion = z;
        ContentResolver contentResolver4 = context.getContentResolver();
        final Handler threadHandler2 = ThreadManager.Companion.getInstance().getThreadHandler();
        ContentObserver contentObserver4 = new ContentObserver(threadHandler2) { // from class: com.oplusos.systemui.common.feature.FeatureOption$initAppFeature$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z2) {
                Log.d("FeatureOption", "appFeature onChange!");
                FeatureOption.INSTANCE.loadAppFeature(context);
                CustomizeFeatureOption.loadAppFeature(CustomizeFeatureOption.sContext);
                Iterator it = CustomizeFeatureOption.sFeatureChangeCallBacks.iterator();
                while (it.hasNext()) {
                    ChannelExt.trySendWithFailureLogging$default(ChannelExt.INSTANCE, ((AirplaneModeConfirmRepository$isAirplaneModeNeedConfirm$1$callback$1) ((CustomizeFeatureOption.CustomFeatureChangeCallBack) it.next())).$$this$conflatedCallbackFlow, Unit.INSTANCE, "AirplaneModeConfirmRepository");
                }
            }
        };
        Uri uriForFeatureID4 = AppFeatureProviderUtils.getUriForFeatureID(featureID);
        if (contentResolver4 != null) {
            contentResolver4.registerContentObserver(uriForFeatureID4, true, contentObserver4);
        }
    }

    public final boolean isBinderPoolExpand() {
        return getBinderPoolExpand();
    }

    public final boolean isDisablePowerSave(Context context) {
        return getDisablePowerSave() || OplusCustomizeRestrictionManager.getInstance(context).isPowerSavingModeDisabled((ComponentName) null);
    }

    public final boolean isSuperPowerModeDisabled(Context context) {
        if (context == null) {
            return false;
        }
        try {
            OplusCustomizeRestrictionManager oplusCustomizeRestrictionManager = OplusCustomizeRestrictionManager.getInstance(context);
            if (oplusCustomizeRestrictionManager != null) {
                return ((Boolean) ReflectionUtils.methodInvoke(oplusCustomizeRestrictionManager, "isSuperPowerSavingModeDisabled")).booleanValue();
            }
            return false;
        } catch (NoSuchMethodException e) {
            if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                return false;
            }
            Log.d("SystemUi--Notification", "FeatureOption-->".concat("isSuperPowerModeDisabled e" + e));
            return false;
        }
    }

    public final void loadAppFeature(Context context) {
        Log.i(TAG, "loadAppFeature start, context: " + context);
        Trace.beginSection("FeatureOption#loadAppFeature");
        Trace.beginSection("FeatureOption#updateAppFeatureCache");
        AppFeatureProviderUtils.enableAppFeatureCache(context.getContentResolver(), AppFeatureProviderUtils.FeatureID.STATIC_COMPONENT, appFeatureList);
        Trace.endSection();
        FlavorOneFeatureOption flavorOneFeatureOption = FlavorOneFeatureOption.INSTANCE;
        flavorOneFeatureOption.getClass();
        if (FlavorOneFeatureOption.isFlavorOneDevice()) {
            flavorOneFeatureOption.loadAppFeature(context);
        } else {
            FlavorTwoFeatureOption flavorTwoFeatureOption = FlavorTwoFeatureOption.INSTANCE;
            flavorTwoFeatureOption.getClass();
            if (FlavorTwoFeatureOption.isFlavorTwoDevice()) {
                flavorTwoFeatureOption.loadAppFeature(context);
            }
        }
        mIsDisableLowBatteryDialog = hasAppFeature(context, FEATURE_DISABLE_LOW_BATTERY_DIALOG);
        mPreventStartServices = hasAppFeature(context, PREVENT_START_SERVICES_IF_NEEDED);
        useLayeredPanelAnimation = !hasAppFeature(context, FEATURE_DISABLE_LAYERED_ANIMATION);
        disableKeyguardBlurStacking = hasAppFeature(context, FEATURE_DISABLE_KEYGUARD_BLUR_STACKING);
        enableLightFeature = hasAppFeature(context, ENABLE_LIGHT_FEATURE);
        enableMaterialFeature = hasAppFeature(context, ENABLE_MATERIAL_FEATURE);
        enableLiveAlertEffectLight = hasAppFeature(context, ENABLE_LIVEALERT_EFFECT_LIGHT);
        isMediaImmersiveFullEnable = hasAppFeature(context, IS_MEDIA_IMMERSIVE_FULLSCREEN);
        isSupportMultiModeFlashLight = hasAppFeature(context, IS_SUPPORT_MULTIMODE_FLASHLIGHT);
        QSFeatureOption.INSTANCE.loadAppFeature(context);
        AodFeatureOption.INSTANCE.loadAppFeature(context);
        KeyguardFeatureOption.INSTANCE.loadAppFeature(context);
        StatusBarFeatureOption.INSTANCE.loadAppFeature(context);
        ChargingFeatureOption.INSTANCE.loadAppFeature(context);
        NotificationFeatureOption.INSTANCE.loadAppFeature(context);
        UsbFeatureOption.INSTANCE.loadAppFeature(context);
        NavBarFeatureOption.INSTANCE.loadAppFeature(context);
        VolumeFeatureOption.INSTANCE.loadAppFeature(context);
        ArrayList arrayList = EdgePanelFeatureOption.APP_FEATURE_CACHE_LIST;
        AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), "com.oplus.smartsidebar.default.off");
        AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), "com.oplus.smartsidebar_disable");
        Trace.endSection();
        Log.i(TAG, "loadAppFeature finish");
    }
}

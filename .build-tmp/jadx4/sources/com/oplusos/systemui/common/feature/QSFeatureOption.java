package com.oplusos.systemui.common.feature;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemProperties;
import android.os.customize.OplusCustomizeRestrictionManager;
import android.telephony.OplusTelephonyManager;
import android.util.Log;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplus.coreapp.appfeature.AppFeatureProviderUtils;
import com.oplus.keyguard.util.FeatureOption$isCompactWindowMode$2$;
import com.oplus.os.OplusBuild;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.virtualcomm.VirtualCommServiceState;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ReflectionUtils;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSFeatureOption {
    private static final String AUTO_BRIGHTNESS_FORCE_MODE = "AUTO_BRIGHTNESS_FORCE_MODE";
    private static final String AUTO_BRIGHTNESS_SWITCH_STATUS = "auto_brightness_switch_status";
    private static final String BOOT_COMPLETED = "sys.boot_completed";
    private static final String BOOT_COMPLETED_STATE = "1";
    private static final String DISPLAY_PIXELWORKS_ENABLE = "oplus.software.display.pixelworks_enable";
    private static final String IS_CAST_TILE_NOT_SUPPORT_STRING = "oplus.multiuser.wfd.notsupport";
    private static final String IS_OPLUS_THREE_KAY_STRING = "oplus.software.audio.alert_slider";
    private static final String IS_SMART_DRIVE_DISABLE_STRING = "oplus.smartdrive.disable.smartdrive";
    private static final String IS_SYSTEM_OSIE_DISABLE_STRING = "persist.sys.dpp.feature";
    private static final String OPLUS_SOFTWARE_VIBRATOR_LUXUNVIBRATOR = "oplus.software.vibrator_luxunvibrator";
    private static final String OSIE_AIPQ_SUPPORT = "oplus.software.display.osie_aipq_support";
    private static final String PACKAGE_SETTINGS = "com.android.settings";
    private static final String PROPERT_OSIE_ON = "1";
    private static final String SUPPORT_SETTINGS_AIRPLANE = "settings_airplane_mode_support";
    private static final String TAG = "FeatureOption";
    private static final String VIDEO_OSIE_SUPPORT = "oplus.software.video.osie_support";
    private static final String VIDEO_SR_SUPPORT = "oplus.software.video.sr_support";
    private static final String VISUAL_COMFORT_SUPPORT = "oplus.software.display.visual_comfort_support";
    public static boolean disableMediaSeedlingFeature = false;
    public static boolean disableNotificationMediaSeedlingFeature = false;
    private static String disableQSTileList = "";
    public static boolean isAlipayPaymentSupport = false;
    public static boolean isAlipayScanSupport = false;
    public static boolean isApplyLightOSQsTile = false;
    public static boolean isCalendarCn = false;
    public static boolean isCellularDisabledByCustomize = false;
    public static boolean isDisableBGBlurNonAnim = false;
    public static boolean isEnvelopSupport = false;
    public static boolean isGoogleAssistantSupport = false;
    private static final Lazy isIris5ModeSupport$delegate;
    private static final Lazy isLuxunVibratorSupport$delegate;
    public static boolean isMediaShowSupport = false;
    private static final Lazy isOplusAlertSlider$delegate;
    private static final Lazy isOsieAipqSupport$delegate;
    private static final Lazy isOsieModeSupport$delegate;
    public static boolean isPowerSaveCustomTileSupport = false;
    private static final Lazy isSmartDriveDisable$delegate;
    public static boolean isSupportAreaRecord = false;
    private static final Lazy isSupportIris5$delegate;
    private static final Lazy isSupportIris5Rlm$delegate;
    private static final Lazy isSupportMyDevice$delegate;
    private static Boolean isSupportSettingsAirplane = null;
    private static final Lazy isSupportVideoOsie$delegate;
    private static final Lazy isSupportVideoSuperResolution$delegate;
    public static boolean isSupportVirtualComm = false;
    private static final Lazy isSupportVisualComfort$delegate;
    private static final Lazy isSupportVolumeSeekBar$delegate;
    private static final Lazy isTemperatureSupport$delegate;
    public static boolean isVirtualCommInited = false;
    public static boolean isWechatpayPaymentSupport = false;
    private static String mediaSeedlingConfig = "";
    public static boolean notShowGuideDialog;
    private static PackageManager packageManager;
    public static boolean shouldRemoveAutoBrightness;
    public static int virtualCommDeviceType;
    public static final QSFeatureOption INSTANCE = new QSFeatureOption();
    public static final boolean isSuperPowerModeSupport = true;
    private static final String IS_CELLUAR_DISABLED_BY_CUSTOMIZE_STRING = "com.oplus.wirelesssettings.customize_mobile_data_disable";
    private static final String IS_WIFI_DISPLAY_ENABLED_STRING = "com.oplus.wirelesssettings.wifi_show_wfd_menu";
    private static final String IS_WECHATPAY_PAYMENT_SUPPORTED_STRING = "com.android.systemui.wechatpay_payment_supported";
    private static final String IS_ALIPAY_PAYMENT_SUPPORTED_STRING = "com.android.systemui.alipay_payment_supported";
    private static final String IS_ALIPAY_SCAN_SUPPORTED_STRING = "com.android.systemui.alipay_scan_supported";
    private static final String SHOULD_REMOVE_AUTO_BRIGHTNESS = "com.android.systemui.remove_auto_brightness";
    private static final String IS_GOOGLE_ASSISTANT_SUPPORTED_STRING = "com.android.systemui.google_assistant_supported";
    private static final String APPLY_LIGHT_OS_QS_TILE_STRING = "com.android.systemui.apply_light_os_qs_tile";
    private static final String IS_ENVELOP_SUPPORTED_STRING = "com.android.systemui.envelop_supported";
    private static final String IS_CALENDAR_CN_STRING = "com.android.systemui.calendar_cn";
    private static final String DISABLE_QS_TILE_LIST = "com.opus.systemui.disable_qs_list";
    private static final String NOT_SHOW_GUIDE_DIALOG = "com.oplus.systemui.not_show_guide_dialog";
    private static final String SUPPORT_BRIGHTNESS_DIALOG = "com.oplus.systemui.support_brightness_dialog";
    private static final String SUPPORT_MEDIA_SHOW = "com.android.systemui.support_media_show";
    private static final String SUPPORT_VOLUME_SEEKBAR = "com.oplus.systemui.support_volume_seekbar";
    private static final String DISABLE_MEDIA_SEEDLING_CONFIG = "com.oplus.mediacontroller.fluidConfig";
    private static final String DISABLE_BG_BLUR_NON_ANIM = "com.oplus.systemui.disable_bg_blur_non_anim";
    private static final String IS_WHITESWAN = "com.android.systemui.whiteswan_show";
    private static final String IS_POWER_SAVE_CUSTOM_TILE_SUPPORT = "com.oplus.battery.power_save_mode_tile";
    private static final String SUPPORT_AREA_RECORD = "com.oplus.screenrecorder.area_record";
    private static final List<String> appFeatureList = CollectionsKt__CollectionsKt.listOf(new String[]{IS_CELLUAR_DISABLED_BY_CUSTOMIZE_STRING, IS_WIFI_DISPLAY_ENABLED_STRING, IS_WECHATPAY_PAYMENT_SUPPORTED_STRING, IS_ALIPAY_PAYMENT_SUPPORTED_STRING, IS_ALIPAY_SCAN_SUPPORTED_STRING, SHOULD_REMOVE_AUTO_BRIGHTNESS, IS_GOOGLE_ASSISTANT_SUPPORTED_STRING, APPLY_LIGHT_OS_QS_TILE_STRING, IS_ENVELOP_SUPPORTED_STRING, IS_CALENDAR_CN_STRING, DISABLE_QS_TILE_LIST, NOT_SHOW_GUIDE_DIALOG, SUPPORT_BRIGHTNESS_DIALOG, SUPPORT_MEDIA_SHOW, SUPPORT_VOLUME_SEEKBAR, DISABLE_MEDIA_SEEDLING_CONFIG, DISABLE_BG_BLUR_NON_ANIM, IS_WHITESWAN, IS_POWER_SAVE_CUSTOM_TILE_SUPPORT, SUPPORT_AREA_RECORD});

    static {
        final int i = 0;
        isSupportIris5Rlm$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.QSFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.rm_memc");
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isSupportMyDevice());
                    case 2:
                        QSFeatureOption qSFeatureOption = QSFeatureOption.INSTANCE;
                        return Boolean.TRUE;
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.sr_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.osie_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.osie_aipq_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.dpp.feature")));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.smartdrive.disable.smartdrive");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.intelligent_color_temperature_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.visual_comfort_support");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.sr_support");
                    case 10:
                        QSFeatureOption.INSTANCE.getClass();
                        if (!QSFeatureOption.isSupportIris5Rlm() && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.osie_support");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.osie_aipq_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                }
            }
        });
        final int i2 = 9;
        isSupportVideoSuperResolution$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.QSFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i2) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.rm_memc");
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isSupportMyDevice());
                    case 2:
                        QSFeatureOption qSFeatureOption = QSFeatureOption.INSTANCE;
                        return Boolean.TRUE;
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.sr_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.osie_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.osie_aipq_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.dpp.feature")));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.smartdrive.disable.smartdrive");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.intelligent_color_temperature_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.visual_comfort_support");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.sr_support");
                    case 10:
                        QSFeatureOption.INSTANCE.getClass();
                        if (!QSFeatureOption.isSupportIris5Rlm() && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.osie_support");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.osie_aipq_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                }
            }
        });
        final int i3 = 10;
        isSupportIris5$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.QSFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i3) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.rm_memc");
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isSupportMyDevice());
                    case 2:
                        QSFeatureOption qSFeatureOption = QSFeatureOption.INSTANCE;
                        return Boolean.TRUE;
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.sr_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.osie_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.osie_aipq_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.dpp.feature")));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.smartdrive.disable.smartdrive");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.intelligent_color_temperature_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.visual_comfort_support");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.sr_support");
                    case 10:
                        QSFeatureOption.INSTANCE.getClass();
                        if (!QSFeatureOption.isSupportIris5Rlm() && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.osie_support");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.osie_aipq_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                }
            }
        });
        final int i4 = 11;
        isSupportVideoOsie$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.QSFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i4) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.rm_memc");
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isSupportMyDevice());
                    case 2:
                        QSFeatureOption qSFeatureOption = QSFeatureOption.INSTANCE;
                        return Boolean.TRUE;
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.sr_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.osie_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.osie_aipq_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.dpp.feature")));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.smartdrive.disable.smartdrive");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.intelligent_color_temperature_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.visual_comfort_support");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.sr_support");
                    case 10:
                        QSFeatureOption.INSTANCE.getClass();
                        if (!QSFeatureOption.isSupportIris5Rlm() && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.osie_support");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.osie_aipq_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                }
            }
        });
        final int i5 = 12;
        isOsieAipqSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.QSFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i5) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.rm_memc");
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isSupportMyDevice());
                    case 2:
                        QSFeatureOption qSFeatureOption = QSFeatureOption.INSTANCE;
                        return Boolean.TRUE;
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.sr_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.osie_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.osie_aipq_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.dpp.feature")));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.smartdrive.disable.smartdrive");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.intelligent_color_temperature_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.visual_comfort_support");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.sr_support");
                    case 10:
                        QSFeatureOption.INSTANCE.getClass();
                        if (!QSFeatureOption.isSupportIris5Rlm() && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.osie_support");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.osie_aipq_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                }
            }
        });
        final int i6 = 13;
        isOplusAlertSlider$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.QSFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i6) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.rm_memc");
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isSupportMyDevice());
                    case 2:
                        QSFeatureOption qSFeatureOption = QSFeatureOption.INSTANCE;
                        return Boolean.TRUE;
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.sr_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.osie_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.osie_aipq_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.dpp.feature")));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.smartdrive.disable.smartdrive");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.intelligent_color_temperature_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.visual_comfort_support");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.sr_support");
                    case 10:
                        QSFeatureOption.INSTANCE.getClass();
                        if (!QSFeatureOption.isSupportIris5Rlm() && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.osie_support");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.osie_aipq_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                }
            }
        });
        final int i7 = 1;
        isSupportMyDevice$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.QSFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i7) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.rm_memc");
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isSupportMyDevice());
                    case 2:
                        QSFeatureOption qSFeatureOption = QSFeatureOption.INSTANCE;
                        return Boolean.TRUE;
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.sr_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.osie_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.osie_aipq_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.dpp.feature")));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.smartdrive.disable.smartdrive");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.intelligent_color_temperature_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.visual_comfort_support");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.sr_support");
                    case 10:
                        QSFeatureOption.INSTANCE.getClass();
                        if (!QSFeatureOption.isSupportIris5Rlm() && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.osie_support");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.osie_aipq_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                }
            }
        });
        final int i8 = 2;
        isSupportVolumeSeekBar$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.QSFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i8) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.rm_memc");
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isSupportMyDevice());
                    case 2:
                        QSFeatureOption qSFeatureOption = QSFeatureOption.INSTANCE;
                        return Boolean.TRUE;
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.sr_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.osie_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.osie_aipq_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.dpp.feature")));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.smartdrive.disable.smartdrive");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.intelligent_color_temperature_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.visual_comfort_support");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.sr_support");
                    case 10:
                        QSFeatureOption.INSTANCE.getClass();
                        if (!QSFeatureOption.isSupportIris5Rlm() && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.osie_support");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.osie_aipq_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                }
            }
        });
        final int i9 = 3;
        isLuxunVibratorSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.QSFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i9) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.rm_memc");
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isSupportMyDevice());
                    case 2:
                        QSFeatureOption qSFeatureOption = QSFeatureOption.INSTANCE;
                        return Boolean.TRUE;
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.sr_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.osie_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.osie_aipq_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.dpp.feature")));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.smartdrive.disable.smartdrive");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.intelligent_color_temperature_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.visual_comfort_support");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.sr_support");
                    case 10:
                        QSFeatureOption.INSTANCE.getClass();
                        if (!QSFeatureOption.isSupportIris5Rlm() && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.osie_support");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.osie_aipq_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                }
            }
        });
        final int i10 = 4;
        isIris5ModeSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.QSFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i10) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.rm_memc");
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isSupportMyDevice());
                    case 2:
                        QSFeatureOption qSFeatureOption = QSFeatureOption.INSTANCE;
                        return Boolean.TRUE;
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.sr_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.osie_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.osie_aipq_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.dpp.feature")));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.smartdrive.disable.smartdrive");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.intelligent_color_temperature_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.visual_comfort_support");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.sr_support");
                    case 10:
                        QSFeatureOption.INSTANCE.getClass();
                        if (!QSFeatureOption.isSupportIris5Rlm() && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.osie_support");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.osie_aipq_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                }
            }
        });
        final int i11 = 5;
        isOsieModeSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.QSFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i11) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.rm_memc");
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isSupportMyDevice());
                    case 2:
                        QSFeatureOption qSFeatureOption = QSFeatureOption.INSTANCE;
                        return Boolean.TRUE;
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.sr_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.osie_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.osie_aipq_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.dpp.feature")));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.smartdrive.disable.smartdrive");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.intelligent_color_temperature_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.visual_comfort_support");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.sr_support");
                    case 10:
                        QSFeatureOption.INSTANCE.getClass();
                        if (!QSFeatureOption.isSupportIris5Rlm() && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.osie_support");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.osie_aipq_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                }
            }
        });
        final int i12 = 6;
        isSmartDriveDisable$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.QSFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i12) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.rm_memc");
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isSupportMyDevice());
                    case 2:
                        QSFeatureOption qSFeatureOption = QSFeatureOption.INSTANCE;
                        return Boolean.TRUE;
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.sr_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.osie_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.osie_aipq_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.dpp.feature")));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.smartdrive.disable.smartdrive");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.intelligent_color_temperature_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.visual_comfort_support");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.sr_support");
                    case 10:
                        QSFeatureOption.INSTANCE.getClass();
                        if (!QSFeatureOption.isSupportIris5Rlm() && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.osie_support");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.osie_aipq_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                }
            }
        });
        final int i13 = 7;
        isTemperatureSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.QSFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i13) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.rm_memc");
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isSupportMyDevice());
                    case 2:
                        QSFeatureOption qSFeatureOption = QSFeatureOption.INSTANCE;
                        return Boolean.TRUE;
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.sr_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.osie_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.osie_aipq_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.dpp.feature")));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.smartdrive.disable.smartdrive");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.intelligent_color_temperature_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.visual_comfort_support");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.sr_support");
                    case 10:
                        QSFeatureOption.INSTANCE.getClass();
                        if (!QSFeatureOption.isSupportIris5Rlm() && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.osie_support");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.osie_aipq_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                }
            }
        });
        final int i14 = 8;
        isSupportVisualComfort$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.QSFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i14) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.rm_memc");
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isSupportMyDevice());
                    case 2:
                        QSFeatureOption qSFeatureOption = QSFeatureOption.INSTANCE;
                        return Boolean.TRUE;
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.vibrator_luxunvibrator");
                    case 4:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.sr_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.video.osie_support") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.osie_aipq_support")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        return Boolean.valueOf("1".equals(SystemProperties.get("persist.sys.dpp.feature")));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.smartdrive.disable.smartdrive");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.intelligent_color_temperature_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.visual_comfort_support");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.sr_support");
                    case 10:
                        QSFeatureOption.INSTANCE.getClass();
                        if (!QSFeatureOption.isSupportIris5Rlm() && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.pixelworks_enable")) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.video.osie_support");
                    case 12:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.osie_aipq_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.alert_slider");
                }
            }
        });
    }

    public static boolean isIris5ModeSupport() {
        return ((Boolean) isIris5ModeSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isOsieAipqSupport() {
        return ((Boolean) isOsieAipqSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isOsieModeSupport() {
        return ((Boolean) isOsieModeSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isSmartDriveDisable() {
        return ((Boolean) isSmartDriveDisable$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportIris5() {
        return ((Boolean) isSupportIris5$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportIris5Rlm() {
        return ((Boolean) isSupportIris5Rlm$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportMyDevice() {
        return ((Boolean) isSupportMyDevice$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportVideoOsie() {
        return ((Boolean) isSupportVideoOsie$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportVideoSuperResolution() {
        return ((Boolean) isSupportVideoSuperResolution$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportVisualComfort() {
        return ((Boolean) isSupportVisualComfort$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportVolumeSeekBar() {
        return ((Boolean) isSupportVolumeSeekBar$delegate.getValue()).booleanValue();
    }

    public static boolean isTemperatureSupport() {
        return ((Boolean) isTemperatureSupport$delegate.getValue()).booleanValue();
    }

    public final List<String> getAppFeatureCacheList() {
        return appFeatureList;
    }

    public final boolean getDisableMediaSeedlingFeature() {
        return disableMediaSeedlingFeature;
    }

    public final boolean getDisableNotificationMediaSeedlingFeature() {
        return disableNotificationMediaSeedlingFeature;
    }

    public final String getDisableQSTileList() {
        return disableQSTileList;
    }

    public final boolean getNotShowGuideDialog() {
        return notShowGuideDialog;
    }

    public final boolean getShouldRemoveAutoBrightness() {
        return shouldRemoveAutoBrightness;
    }

    public final boolean isSuperPowerModeDisabled(Context context) {
        try {
            OplusCustomizeRestrictionManager oplusCustomizeRestrictionManager = OplusCustomizeRestrictionManager.getInstance(context);
            if (oplusCustomizeRestrictionManager != null) {
                return ((Boolean) ReflectionUtils.methodInvoke(oplusCustomizeRestrictionManager, "isSuperPowerSavingModeDisabled")).booleanValue();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public final boolean isSupportSettingsAirplane(Context context) {
        ApplicationInfo applicationInfo;
        Bundle bundle;
        Boolean bool = isSupportSettingsAirplane;
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean z = false;
        if (OplusBuild.VERSION.SDK_VERSION < 33) {
            isSupportSettingsAirplane = Boolean.FALSE;
            return false;
        }
        if (context == null) {
            return false;
        }
        try {
            PackageManager packageManager2 = context.getPackageManager();
            if (packageManager2 != null && (applicationInfo = packageManager2.getApplicationInfo(PACKAGE_SETTINGS, 128)) != null && (bundle = applicationInfo.metaData) != null) {
                z = bundle.getBoolean(SUPPORT_SETTINGS_AIRPLANE, false);
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "FeatureOption-->".concat("hasMetaData " + z));
            }
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.e$default(8, LogUtil.TAG_QS, TAG, "Couldn't find application info for com.android.settings");
        }
        isSupportSettingsAirplane = Boolean.valueOf(z);
        return z;
    }

    public final boolean isVirtualCommConsumerDevice(Context context) {
        if (!isVirtualCommInited) {
            OplusTelephonyManager oplusTelephonyManager = OplusTelephonyManager.getInstance(context);
            boolean zIsVirtualCommSupport = oplusTelephonyManager.isVirtualCommSupport();
            isSupportVirtualComm = zIsVirtualCommSupport;
            if (zIsVirtualCommSupport) {
                virtualCommDeviceType = oplusTelephonyManager.getVirtualcommDeviceType();
            }
            if ("1".equals(SystemProperties.get(BOOT_COMPLETED))) {
                isVirtualCommInited = true;
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            StringBuilder sb = new StringBuilder("sIsSupportVirtualComm = ");
            sb.append(isSupportVirtualComm);
            sb.append(" sVirtualCommDeviceType = ");
            MobileInputLogger$.ExternalSyntheticOutline0.m(virtualCommDeviceType, "FeatureOption-->", "SystemUi--Keyguard", sb);
        }
        return isSupportVirtualComm && virtualCommDeviceType == 2;
    }

    public final boolean isVirtualCommInService(Context context) {
        VirtualCommServiceState virtualCommState;
        return isVirtualCommConsumerDevice(context) && (virtualCommState = OplusTelephonyManager.getInstance(context).getVirtualCommState()) != null && virtualCommState.getState() == 0;
    }

    public final void loadAppFeature(Context context) {
        boolean zOptBoolean;
        Object obj;
        boolean zBooleanValue;
        packageManager = context.getPackageManager();
        ContentResolver contentResolver = context.getContentResolver();
        AppFeatureProviderUtils.FeatureID featureID = AppFeatureProviderUtils.FeatureID.STATIC_COMPONENT;
        isCellularDisabledByCustomize = AppFeatureProviderUtils.isFeatureSupport(contentResolver, featureID, IS_CELLUAR_DISABLED_BY_CUSTOMIZE_STRING);
        isPowerSaveCustomTileSupport = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), IS_POWER_SAVE_CUSTOM_TILE_SUPPORT, false);
        AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), IS_WIFI_DISPLAY_ENABLED_STRING);
        isWechatpayPaymentSupport = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), IS_WECHATPAY_PAYMENT_SUPPORTED_STRING, false);
        isAlipayPaymentSupport = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), IS_ALIPAY_PAYMENT_SUPPORTED_STRING, false);
        isAlipayScanSupport = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), IS_ALIPAY_SCAN_SUPPORTED_STRING, false);
        shouldRemoveAutoBrightness = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, SHOULD_REMOVE_AUTO_BRIGHTNESS);
        AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), SUPPORT_BRIGHTNESS_DIALOG);
        FeatureOption.INSTANCE.getClass();
        isMediaShowSupport = FeatureOption.isFoldDevice() || AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, SUPPORT_MEDIA_SHOW);
        PackageManager packageManager2 = packageManager;
        if (packageManager2 != null) {
            packageManager2.hasSystemFeature(IS_CAST_TILE_NOT_SUPPORT_STRING);
        }
        isGoogleAssistantSupport = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), IS_GOOGLE_ASSISTANT_SUPPORTED_STRING, true);
        isApplyLightOSQsTile = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, APPLY_LIGHT_OS_QS_TILE_STRING);
        isCalendarCn = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), IS_CALENDAR_CN_STRING, false);
        isEnvelopSupport = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), IS_ENVELOP_SUPPORTED_STRING, false);
        disableQSTileList = AppFeatureProviderUtils.getString(context.getContentResolver(), DISABLE_QS_TILE_LIST, "");
        notShowGuideDialog = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, NOT_SHOW_GUIDE_DIALOG);
        String string = AppFeatureProviderUtils.getString(context.getContentResolver(), DISABLE_MEDIA_SEEDLING_CONFIG, "");
        mediaSeedlingConfig = string;
        if (string.length() == 0) {
            zOptBoolean = false;
        } else {
            try {
                zOptBoolean = new JSONObject(string).optBoolean("disable", false);
            } catch (JSONException e) {
                LogUtil.e$default(12, TAG, "json exception: " + e.getMessage() + "; mediaSeedlingConfig: " + mediaSeedlingConfig, null);
                zOptBoolean = false;
            }
        }
        disableMediaSeedlingFeature = zOptBoolean;
        String str = mediaSeedlingConfig;
        if (str.length() == 0) {
            zBooleanValue = false;
        } else {
            try {
                obj = Result.constructor-impl(Boolean.valueOf(new JSONObject(str).optBoolean("ntf_entry_disable", false)));
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                LogUtil.e$default(12, TAG, "json exception: " + th2.getMessage() + "; mediaSeedlingConfig: " + mediaSeedlingConfig, null);
            }
            if (Result.exceptionOrNull-impl(obj) != null) {
                obj = Boolean.FALSE;
            }
            zBooleanValue = ((Boolean) obj).booleanValue();
        }
        disableNotificationMediaSeedlingFeature = zBooleanValue;
        isDisableBGBlurNonAnim = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, DISABLE_BG_BLUR_NON_ANIM);
        AppFeatureProviderUtils.getBoolean(context.getContentResolver(), IS_WHITESWAN, false);
        isSupportAreaRecord = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), SUPPORT_AREA_RECORD, false);
    }

    public final void setDisableMediaSeedlingFeature(boolean z) {
        disableMediaSeedlingFeature = z;
    }

    public final void setDisableNotificationMediaSeedlingFeature(boolean z) {
        disableNotificationMediaSeedlingFeature = z;
    }
}

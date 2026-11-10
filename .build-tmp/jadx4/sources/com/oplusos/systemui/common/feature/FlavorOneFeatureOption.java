package com.oplusos.systemui.common.feature;

import android.content.ContentResolver;
import android.content.Context;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplus.coreapp.appfeature.AppFeatureProviderUtils;
import com.oplus.cust.IOplusCfgFilePolicyExt;
import com.oplus.keyguard.util.FeatureOption$isCompactWindowMode$2$;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.util.OplusPlatformLevelUtils;
import com.oplus.wrapper.os.SystemProperties;
import com.oplusos.systemui.common.util.BrandUtils;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import system.ext.loader.core.ExtLoader;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FlavorOneFeatureOption {
    private static final String FILE_PATH_PREFIX = "/overlay/";
    private static final String IS_SUPPORT_DISPLAY_BRIGHTNESS_RM_UX = "oplus.software.light_os_brightness_ux";
    private static final String IS_SUPPORT_SECOND_MAX_BRIGHTNESS = "oplus.software.display.sec_max_brightness_rm";
    private static final String NAME_COMPANY_CHARGING_ANIM_HUOYING = "ChargingAnim_HuoYing.apk";
    private static final String NAME_COMPANY_CHARGING_ANIM_LONGZHU = "ChargingAnim_LongZhu.apk";
    private static final String NAME_COMPANY_CHARGING_ANIM_LONGZHU_OVERYLAY_BATTERY = "GTAnim_LongZhu_overylay_battery.apk";
    private static final String NAME_COMPANY_CHARGING_ANIM_LONGZHU_OVERYLAY_GTAPP = "GTAnim_LongZhu_overylay_gtapp.apk";
    private static final String NAME_COMPANY_CHARGING_ANIM_LUIGI_COCACOLA = "ChargingAnim_LuigiCocacola.apk";
    private static final String OPLUS_BREATH_LIGHT_SUPPORT = "oplus.software.display.multi_led_support";
    private static final String OPLUS_SHUTDOWN_BREATH_LIGHT_DONGFENG = "ro.oplus.display.breath_light.rm";
    private static final String OPLUS_TYPE_ONE_BREATH_LIGHT_SUPPORT = "ro.oplus.display.breath_light_led.rm.rom";
    private static final String PALM_UNLOCK_SUPPORT = "oplus.software.palmprint";
    private static final String SMART_BUTTON_SUPPORT = "oplus.software.check_smart_button_open";
    private static final String SUPPORT_RESET_FRAMERATE_PANEL_EXPENED = "oplus.software.low_framerate_panel_expened";
    private static final Lazy isShutdownBreathLightDongFengSupport$delegate;
    private static final Lazy isShutdownBreathLightZondaSupport$delegate;
    private static final Lazy isSupportPalmUnlock$delegate;
    private static final Lazy isThreeFlashSupport$delegate;
    private static final Lazy otherBreathLightPhoneSupport$delegate;
    public static boolean sIsAlexaSupport;
    public static boolean sIsAntiTheftRlockSupport;
    public static boolean sIsAntiTheftSupport;
    private static final Lazy sIsBrightnessSecMaxSupported$delegate;
    private static final Lazy sIsBrightnessUxSupport$delegate;
    public static boolean sIsDisableWindowTile;
    public static boolean sIsShowRealmeCustomizedChargingAnimOrOPPOVfxAnim;
    public static boolean sIsSupportActiveSpaceGesture;
    public static boolean sIsSupportMiniCapsule;
    public static boolean sIsUsbPortSupport;
    public static boolean sOtgAutoCloseAlarmDisable;
    public static boolean sShowChargingWithUltraDart;
    private static final Lazy sSmartButtonSupport$delegate;
    private static final Lazy sSupportResetFrameRate$delegate;
    public static final FlavorOneFeatureOption INSTANCE = new FlavorOneFeatureOption();
    private static final String SHOULD_SHOW_VOOC_LOGO = "com.android.systemui.vooc_logo";
    private static final String ALEXA_SUPPORT_FEATURE = "com.android.systemui.support.alexa";
    private static final String IS_SHOW_CHARGING_WITH_ULTRADART = "com.android.systemui.show_charging_with_ultraDart";
    private static final String IS_SHOW_CHARGING_WATTAGE_SUPPORT_FEATURE = "com.android.systemui.support.show_charging_wattage";
    private static final String IS_SHOW_REALME_CUSTOMIZED_CHARGING_ANIM_OR_OPPO_VFX_ANIM = "com.android.systemui.customized_project_charging_anim";
    private static final String IS_SUPPORT_33W_SHOW_SUPERVOOC_OR_SUPERDART = "com.android.systemui.support_33w_show_supervooc_or_superdart";
    private static final String IS_OTG_AUTO_CLOSE_ALARM_DISABLE = "com.android.systemui.otg_auto_close_alarm_disable";
    private static final String SUPPORT_MINI_CAPSULE = "com.oplus.software.support.mini_capsule";
    private static final String DISABLE_WINDOW_TILE = "com.android.systemui.window_tile_disabled";
    private static final String SUPPORT_ANTI_THEFT = "com.oplus.rom.antitheft_support";
    private static final String THREE_FLASH_SUPPORT = "com.oplus.camera.three.flash.support";
    private static final String SUPPORT_ANTI_THEFTRLOCK = "com.oplus.rom.antitheft_rlock_support";
    private static final String OPLUS_BREATH_LIGHT_TYPE_SUPPORT = "com.android.settings.breathing_light_rlm_ui";
    private static final String IS_SUPPORT_ACTIVE_SPACE_GESTURE = "com.oplus.gesture.activate_space_gesture";
    private static final String USB_PORT_SUPPORT = "com.android.systemui.usb_port_support";
    private static final List<String> appFeatureList = CollectionsKt__CollectionsKt.listOf(new String[]{SHOULD_SHOW_VOOC_LOGO, ALEXA_SUPPORT_FEATURE, IS_SHOW_CHARGING_WITH_ULTRADART, IS_SHOW_CHARGING_WATTAGE_SUPPORT_FEATURE, IS_SHOW_REALME_CUSTOMIZED_CHARGING_ANIM_OR_OPPO_VFX_ANIM, IS_SUPPORT_33W_SHOW_SUPERVOOC_OR_SUPERDART, IS_OTG_AUTO_CLOSE_ALARM_DISABLE, SUPPORT_MINI_CAPSULE, DISABLE_WINDOW_TILE, SUPPORT_ANTI_THEFT, THREE_FLASH_SUPPORT, SUPPORT_ANTI_THEFTRLOCK, OPLUS_BREATH_LIGHT_TYPE_SUPPORT, IS_SUPPORT_ACTIVE_SPACE_GESTURE, USB_PORT_SUPPORT});
    public static int sPlatformLevel = -1;
    public static int breathLightType = -1;

    static {
        final int i = 0;
        otherBreathLightPhoneSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FlavorOneFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.breath_light_led.rm.rom", false));
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.sec_max_brightness_rm");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.low_framerate_panel_expened");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.check_smart_button_open");
                    case 4:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FlavorOneFeatureOption.isFlavorOneDevice() && OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.palmprint"));
                    case 5:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FlavorOneFeatureOption.isFlavorOneDevice() && OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.camera.three.flash.support"));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.multi_led_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.breath_light.rm", false));
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                }
            }
        });
        final int i2 = 1;
        sIsBrightnessSecMaxSupported$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FlavorOneFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.breath_light_led.rm.rom", false));
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.sec_max_brightness_rm");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.low_framerate_panel_expened");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.check_smart_button_open");
                    case 4:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FlavorOneFeatureOption.isFlavorOneDevice() && OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.palmprint"));
                    case 5:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FlavorOneFeatureOption.isFlavorOneDevice() && OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.camera.three.flash.support"));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.multi_led_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.breath_light.rm", false));
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                }
            }
        });
        final int i3 = 2;
        sSupportResetFrameRate$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FlavorOneFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i3) {
                    case 0:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.breath_light_led.rm.rom", false));
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.sec_max_brightness_rm");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.low_framerate_panel_expened");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.check_smart_button_open");
                    case 4:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FlavorOneFeatureOption.isFlavorOneDevice() && OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.palmprint"));
                    case 5:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FlavorOneFeatureOption.isFlavorOneDevice() && OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.camera.three.flash.support"));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.multi_led_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.breath_light.rm", false));
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                }
            }
        });
        final int i4 = 3;
        sSmartButtonSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FlavorOneFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i4) {
                    case 0:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.breath_light_led.rm.rom", false));
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.sec_max_brightness_rm");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.low_framerate_panel_expened");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.check_smart_button_open");
                    case 4:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FlavorOneFeatureOption.isFlavorOneDevice() && OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.palmprint"));
                    case 5:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FlavorOneFeatureOption.isFlavorOneDevice() && OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.camera.three.flash.support"));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.multi_led_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.breath_light.rm", false));
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                }
            }
        });
        final int i5 = 4;
        isSupportPalmUnlock$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FlavorOneFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i5) {
                    case 0:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.breath_light_led.rm.rom", false));
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.sec_max_brightness_rm");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.low_framerate_panel_expened");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.check_smart_button_open");
                    case 4:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FlavorOneFeatureOption.isFlavorOneDevice() && OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.palmprint"));
                    case 5:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FlavorOneFeatureOption.isFlavorOneDevice() && OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.camera.three.flash.support"));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.multi_led_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.breath_light.rm", false));
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                }
            }
        });
        final int i6 = 5;
        isThreeFlashSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FlavorOneFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i6) {
                    case 0:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.breath_light_led.rm.rom", false));
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.sec_max_brightness_rm");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.low_framerate_panel_expened");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.check_smart_button_open");
                    case 4:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FlavorOneFeatureOption.isFlavorOneDevice() && OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.palmprint"));
                    case 5:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FlavorOneFeatureOption.isFlavorOneDevice() && OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.camera.three.flash.support"));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.multi_led_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.breath_light.rm", false));
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                }
            }
        });
        final int i7 = 6;
        isShutdownBreathLightZondaSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FlavorOneFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i7) {
                    case 0:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.breath_light_led.rm.rom", false));
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.sec_max_brightness_rm");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.low_framerate_panel_expened");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.check_smart_button_open");
                    case 4:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FlavorOneFeatureOption.isFlavorOneDevice() && OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.palmprint"));
                    case 5:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FlavorOneFeatureOption.isFlavorOneDevice() && OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.camera.three.flash.support"));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.multi_led_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.breath_light.rm", false));
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                }
            }
        });
        final int i8 = 7;
        isShutdownBreathLightDongFengSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FlavorOneFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i8) {
                    case 0:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.breath_light_led.rm.rom", false));
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.sec_max_brightness_rm");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.low_framerate_panel_expened");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.check_smart_button_open");
                    case 4:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FlavorOneFeatureOption.isFlavorOneDevice() && OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.palmprint"));
                    case 5:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FlavorOneFeatureOption.isFlavorOneDevice() && OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.camera.three.flash.support"));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.multi_led_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.breath_light.rm", false));
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                }
            }
        });
        final int i9 = 8;
        sIsBrightnessUxSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.FlavorOneFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i9) {
                    case 0:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.breath_light_led.rm.rom", false));
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.sec_max_brightness_rm");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.low_framerate_panel_expened");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.check_smart_button_open");
                    case 4:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FlavorOneFeatureOption.isFlavorOneDevice() && OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.palmprint"));
                    case 5:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FlavorOneFeatureOption.isFlavorOneDevice() && OplusFeatureConfigManager.getInstance().hasFeature("com.oplus.camera.three.flash.support"));
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.multi_led_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.breath_light.rm", false));
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.light_os_brightness_ux");
                }
            }
        });
    }

    private final boolean getOtherBreathLightPhoneSupport() {
        return ((Boolean) otherBreathLightPhoneSupport$delegate.getValue()).booleanValue();
    }

    private final boolean getSIsBrightnessSecMaxSupported() {
        return ((Boolean) sIsBrightnessSecMaxSupported$delegate.getValue()).booleanValue();
    }

    private final boolean getSSmartButtonSupport() {
        return ((Boolean) sSmartButtonSupport$delegate.getValue()).booleanValue();
    }

    private final boolean getSSupportResetFrameRate() {
        return ((Boolean) sSupportResetFrameRate$delegate.getValue()).booleanValue();
    }

    public static boolean isFlavorOneDevice() {
        BrandUtils.INSTANCE.getClass();
        return BrandUtils.isRealme;
    }

    public static boolean isFlavorOneDeviceExp() {
        if (!isFlavorOneDevice()) {
            return false;
        }
        FeatureOption.INSTANCE.getClass();
        return FeatureOption.isExpRegion();
    }

    public static boolean isShowPhoneAntiTheftLocation() {
        if (!isFlavorOneDevice()) {
            return false;
        }
        FeatureOption.INSTANCE.getClass();
        return FeatureOption.isExpRegion() && !FeatureOption.isTablet() && sIsAntiTheftSupport;
    }

    public static boolean isShutdownBreathLightDongFengSupport() {
        return ((Boolean) isShutdownBreathLightDongFengSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isShutdownBreathLightZondaSupport() {
        return ((Boolean) isShutdownBreathLightZondaSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportActiveSpaceGesture() {
        return isFlavorOneDevice() && sIsSupportActiveSpaceGesture;
    }

    public static boolean isSupportMiniCapsule() {
        return isFlavorOneDevice() && sIsSupportMiniCapsule;
    }

    public static boolean isSupportPalmUnlock() {
        return ((Boolean) isSupportPalmUnlock$delegate.getValue()).booleanValue();
    }

    public static boolean isThreeFlashSupport() {
        return ((Boolean) isThreeFlashSupport$delegate.getValue()).booleanValue();
    }

    public final List<String> getAppFeatureCacheList() {
        return isFlavorOneDevice() ? appFeatureList : CollectionsKt__CollectionsKt.emptyList();
    }

    public final boolean getSIsBrightnessUxSupport() {
        return ((Boolean) sIsBrightnessUxSupport$delegate.getValue()).booleanValue();
    }

    public final boolean isBrightnessSecMaxSupported() {
        return isFlavorOneDevice() && getSIsBrightnessSecMaxSupported();
    }

    public final boolean isCfgFileExist(String str) {
        return ((IOplusCfgFilePolicyExt) ExtLoader.create(IOplusCfgFilePolicyExt.class, this)).getCfgTopPriorityFile(str, FILE_PATH_PREFIX, -1) != null;
    }

    public final boolean isFlavorOneOtherBreathLightSupport() {
        return getOtherBreathLightPhoneSupport() || breathLightType == 0;
    }

    public final boolean isFlavorOneShutdownBreathLightSupport() {
        if (isFlavorOneDevice()) {
            return isShutdownBreathLightZondaSupport() || isShutdownBreathLightDongFengSupport() || getOtherBreathLightPhoneSupport() || breathLightType == 0;
        }
        return false;
    }

    public final boolean isShowRealmeCustomizedChargingAnimOrOPPOVfxAnim() {
        if (isFlavorOneDevice()) {
            return sIsShowRealmeCustomizedChargingAnimOrOPPOVfxAnim || isCfgFileExist(NAME_COMPANY_CHARGING_ANIM_LUIGI_COCACOLA) || isCfgFileExist(NAME_COMPANY_CHARGING_ANIM_HUOYING) || isCfgFileExist(NAME_COMPANY_CHARGING_ANIM_LONGZHU) || isCfgFileExist(NAME_COMPANY_CHARGING_ANIM_LONGZHU_OVERYLAY_BATTERY) || isCfgFileExist(NAME_COMPANY_CHARGING_ANIM_LONGZHU_OVERYLAY_GTAPP);
        }
        return false;
    }

    public final boolean isSmartButtonSupport() {
        return isFlavorOneDevice() && getSSmartButtonSupport();
    }

    public final boolean isSupportReSetFrameRate() {
        return getSSupportResetFrameRate();
    }

    public final void loadAppFeature(Context context) {
        AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), SHOULD_SHOW_VOOC_LOGO);
        ContentResolver contentResolver = context.getContentResolver();
        AppFeatureProviderUtils.FeatureID featureID = AppFeatureProviderUtils.FeatureID.STATIC_COMPONENT;
        sShowChargingWithUltraDart = AppFeatureProviderUtils.isFeatureSupport(contentResolver, featureID, IS_SHOW_CHARGING_WITH_ULTRADART);
        sIsAlexaSupport = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, ALEXA_SUPPORT_FEATURE);
        AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), IS_SHOW_CHARGING_WATTAGE_SUPPORT_FEATURE);
        sIsShowRealmeCustomizedChargingAnimOrOPPOVfxAnim = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, IS_SHOW_REALME_CUSTOMIZED_CHARGING_ANIM_OR_OPPO_VFX_ANIM);
        AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), IS_SUPPORT_33W_SHOW_SUPERVOOC_OR_SUPERDART);
        sIsSupportMiniCapsule = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, SUPPORT_MINI_CAPSULE);
        sPlatformLevel = OplusPlatformLevelUtils.getInstance(context).getPlatformGaussianLevel();
        sOtgAutoCloseAlarmDisable = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, IS_OTG_AUTO_CLOSE_ALARM_DISABLE);
        sIsDisableWindowTile = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, DISABLE_WINDOW_TILE);
        sIsAntiTheftSupport = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, SUPPORT_ANTI_THEFT);
        sIsSupportActiveSpaceGesture = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), IS_SUPPORT_ACTIVE_SPACE_GESTURE, false);
        sIsAntiTheftRlockSupport = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, SUPPORT_ANTI_THEFTRLOCK);
        breathLightType = AppFeatureProviderUtils.getInt(context.getContentResolver(), featureID, OPLUS_BREATH_LIGHT_TYPE_SUPPORT, -1);
        sIsUsbPortSupport = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, USB_PORT_SUPPORT);
    }
}

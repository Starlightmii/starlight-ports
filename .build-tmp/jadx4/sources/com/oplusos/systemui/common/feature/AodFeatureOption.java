package com.oplusos.systemui.common.feature;

import android.content.Context;
import android.os.SystemProperties;
import com.oplus.coreapp.appfeature.AppFeatureProviderUtils;
import com.oplus.keyguard.util.FeatureOption$isCompactWindowMode$2$;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AodFeatureOption {
    private static final String AOD_DISABLE_MUSIC_ALWAYS_ON_DISPLAY_MODE = "oplus.software.disable_aod_all_day_mode";
    private static final String AOD_FEATURE = "oplus.software.display.aod_support";
    private static final String AOD_FULL_BRIGHTNESS_WHILE_SHOW = "com.oplus.aod.full_brightness_while_show";
    private static final String AOD_LCD_SCREEN_DISPLAY_MODE = "oplus.software.display.lcd_aod_mode";
    private static final String AOD_LTPO_1HZ_AOD = "oplus.software.display.oha_support";
    private static final String AOD_PREVENT_BURN_NEW_MECH = "oplus.software.display.aod_prevent_burn_new_mech";
    private static final String AOD_PREVENT_SCREEN_BURN = "oplus.software.display.aod_prevent_burn_support";
    private static final String AOD_PROXIMITY_DELAY = "oppo.proximity.need.delay";
    private static final String AOD_RAMLESS_FEATURE = "oplus.software.display.aod_ramless_support";
    private static final String AOD_VIDEO_MODE_AOD_SUPPORT = "oplus.software.display.video_mode_aod_support";
    private static final String AOD_VIDEO_MODE_RAMLESS_LHBM_AOD_SUPPORT = "oplus.software.display.video_mode_ramless_lhbm_aod_support";
    private static final String TAG = "FeatureOption";
    public static int defaultOpenAodStyleAndMode;
    public static boolean isAodFullBrightnessWhileShow;
    public static boolean isAodIncreaseBrightness;
    public static boolean isAodInspirationDisable;
    public static boolean isAodSwitchDefaultEnabled;
    private static final Lazy isDisableAodAlwaysOnDisplayMode$delegate;
    private static final Lazy isLcdScreenAodModeSupport$delegate;
    public static boolean isPanoramicAODFeatureEnable;
    private static final Lazy isPreventBurnNewMech$delegate;
    private static final Lazy isPreventedScreenBurn$delegate;
    public static boolean isPreventedScreenBurnDevices;
    private static final Lazy isRamlessLhbmVideoModeAodSupport$delegate;
    private static final Lazy isSupportAod$delegate;
    private static final Lazy isSupportCurvedDisplay$delegate;
    private static final Lazy isSupportLTPO1HzAOD$delegate;
    private static final Lazy isSupportRamLessAod$delegate;
    private static final Lazy isVideoModeAodSupport$delegate;
    private static final Lazy sFirstApiLevel$delegate;
    public static final AodFeatureOption INSTANCE = new AodFeatureOption();
    private static final String AOD_DISABLE_FULL_BRIGHTNESS_WHILE_SHOW = "com.oplus.aod.full_brightness_while_show.disable";
    private static final String AOD_DISABLE_INCREASE_BRIGHTNESS = "com.oplus.aod.increase_brightness.disable";
    private static final String AOD_INCREASE_BRIGHTNESS = "com.oplus.aod.increase_brightness";
    private static final String AOD_PREVENTED_SCREEN_BURN_FEATURE = "com.android.systemui.prevented_screen_burn";
    private static final String AOD_SWITCH_DEFALUT_ENABLED_FEATURE = "feature_aod_enable_on";
    private static final String OPLUS_AOD_SCENE_INFO_FEATURE = "com.oplus.aod.scene_info_enable";
    private static final String OPLUS_AOD_SCENE_INFO_LIGHTOS = "com.oplus.aod.scene_info_lightOS";
    private static final String AOD_INSPIRATION_DISABLED = "os.personalization.theme.inspiration_aod.disable";
    private static final String OPLUS_AOD_WAKE_BY_CLICK = "oplus.aod.wakebyclick.support";
    private static final String OPLUS_PANORAMIC_AOD_ENABLE = "com.oplus.systemui.panoramic_aod.enable";
    private static final String OPLUS_AOD_OPEN_DEFAULT_STYLE_AND_MODE = "com.oplus.systemui.aod_open_default_style_and_mode";
    private static final List<String> appFeatureList = CollectionsKt__CollectionsKt.listOf(new String[]{AOD_DISABLE_FULL_BRIGHTNESS_WHILE_SHOW, AOD_DISABLE_INCREASE_BRIGHTNESS, AOD_INCREASE_BRIGHTNESS, AOD_PREVENTED_SCREEN_BURN_FEATURE, AOD_SWITCH_DEFALUT_ENABLED_FEATURE, OPLUS_AOD_SCENE_INFO_FEATURE, OPLUS_AOD_SCENE_INFO_LIGHTOS, AOD_INSPIRATION_DISABLED, OPLUS_AOD_WAKE_BY_CLICK, OPLUS_PANORAMIC_AOD_ENABLE, OPLUS_AOD_OPEN_DEFAULT_STYLE_AND_MODE});

    static {
        final int i = 0;
        sFirstApiLevel$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.AodFeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:20:0x0049  */
            /* JADX WARN: Code duplicated, block: B:28:0x0064  */
            public final Object invoke() {
                switch (i) {
                    case 0:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.disable_aod_all_day_mode");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.lcd_aod_mode");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_ramless_support");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.oha_support");
                    case 6:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        boolean z = true;
                        if (FlavorOneFeatureOption.isFlavorOneDevice()) {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod()) {
                                z = false;
                            } else if (!AodFeatureOption.isSupportRamLessAod()) {
                                FeatureOption.INSTANCE.getClass();
                                if (FeatureOption.isLightOs()) {
                                    if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                        z = false;
                                    }
                                }
                            } else if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                z = false;
                            }
                        } else {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod() || AodFeatureOption.isSupportRamLessAod()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_new_mech");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_aod_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_ramless_lhbm_aod_support");
                }
            }
        });
        final int i2 = 3;
        isSupportRamLessAod$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.AodFeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:20:0x0049  */
            /* JADX WARN: Code duplicated, block: B:28:0x0064  */
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.disable_aod_all_day_mode");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.lcd_aod_mode");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_ramless_support");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.oha_support");
                    case 6:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        boolean z = true;
                        if (FlavorOneFeatureOption.isFlavorOneDevice()) {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod()) {
                                z = false;
                            } else if (!AodFeatureOption.isSupportRamLessAod()) {
                                FeatureOption.INSTANCE.getClass();
                                if (FeatureOption.isLightOs()) {
                                    if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                        z = false;
                                    }
                                }
                            } else if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                z = false;
                            }
                        } else {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod() || AodFeatureOption.isSupportRamLessAod()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_new_mech");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_aod_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_ramless_lhbm_aod_support");
                }
            }
        });
        final int i3 = 4;
        isSupportAod$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.AodFeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:20:0x0049  */
            /* JADX WARN: Code duplicated, block: B:28:0x0064  */
            public final Object invoke() {
                switch (i3) {
                    case 0:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.disable_aod_all_day_mode");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.lcd_aod_mode");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_ramless_support");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.oha_support");
                    case 6:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        boolean z = true;
                        if (FlavorOneFeatureOption.isFlavorOneDevice()) {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod()) {
                                z = false;
                            } else if (!AodFeatureOption.isSupportRamLessAod()) {
                                FeatureOption.INSTANCE.getClass();
                                if (FeatureOption.isLightOs()) {
                                    if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                        z = false;
                                    }
                                }
                            } else if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                z = false;
                            }
                        } else {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod() || AodFeatureOption.isSupportRamLessAod()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_new_mech");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_aod_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_ramless_lhbm_aod_support");
                }
            }
        });
        final int i4 = 5;
        isSupportLTPO1HzAOD$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.AodFeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:20:0x0049  */
            /* JADX WARN: Code duplicated, block: B:28:0x0064  */
            public final Object invoke() {
                switch (i4) {
                    case 0:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.disable_aod_all_day_mode");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.lcd_aod_mode");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_ramless_support");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.oha_support");
                    case 6:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        boolean z = true;
                        if (FlavorOneFeatureOption.isFlavorOneDevice()) {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod()) {
                                z = false;
                            } else if (!AodFeatureOption.isSupportRamLessAod()) {
                                FeatureOption.INSTANCE.getClass();
                                if (FeatureOption.isLightOs()) {
                                    if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                        z = false;
                                    }
                                }
                            } else if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                z = false;
                            }
                        } else {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod() || AodFeatureOption.isSupportRamLessAod()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_new_mech");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_aod_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_ramless_lhbm_aod_support");
                }
            }
        });
        final int i5 = 6;
        isSupportCurvedDisplay$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.AodFeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:20:0x0049  */
            /* JADX WARN: Code duplicated, block: B:28:0x0064  */
            public final Object invoke() {
                switch (i5) {
                    case 0:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.disable_aod_all_day_mode");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.lcd_aod_mode");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_ramless_support");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.oha_support");
                    case 6:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        boolean z = true;
                        if (FlavorOneFeatureOption.isFlavorOneDevice()) {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod()) {
                                z = false;
                            } else if (!AodFeatureOption.isSupportRamLessAod()) {
                                FeatureOption.INSTANCE.getClass();
                                if (FeatureOption.isLightOs()) {
                                    if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                        z = false;
                                    }
                                }
                            } else if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                z = false;
                            }
                        } else {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod() || AodFeatureOption.isSupportRamLessAod()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_new_mech");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_aod_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_ramless_lhbm_aod_support");
                }
            }
        });
        final int i6 = 7;
        isPreventedScreenBurn$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.AodFeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:20:0x0049  */
            /* JADX WARN: Code duplicated, block: B:28:0x0064  */
            public final Object invoke() {
                switch (i6) {
                    case 0:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.disable_aod_all_day_mode");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.lcd_aod_mode");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_ramless_support");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.oha_support");
                    case 6:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        boolean z = true;
                        if (FlavorOneFeatureOption.isFlavorOneDevice()) {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod()) {
                                z = false;
                            } else if (!AodFeatureOption.isSupportRamLessAod()) {
                                FeatureOption.INSTANCE.getClass();
                                if (FeatureOption.isLightOs()) {
                                    if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                        z = false;
                                    }
                                }
                            } else if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                z = false;
                            }
                        } else {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod() || AodFeatureOption.isSupportRamLessAod()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_new_mech");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_aod_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_ramless_lhbm_aod_support");
                }
            }
        });
        final int i7 = 8;
        isPreventBurnNewMech$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.AodFeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:20:0x0049  */
            /* JADX WARN: Code duplicated, block: B:28:0x0064  */
            public final Object invoke() {
                switch (i7) {
                    case 0:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.disable_aod_all_day_mode");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.lcd_aod_mode");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_ramless_support");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.oha_support");
                    case 6:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        boolean z = true;
                        if (FlavorOneFeatureOption.isFlavorOneDevice()) {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod()) {
                                z = false;
                            } else if (!AodFeatureOption.isSupportRamLessAod()) {
                                FeatureOption.INSTANCE.getClass();
                                if (FeatureOption.isLightOs()) {
                                    if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                        z = false;
                                    }
                                }
                            } else if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                z = false;
                            }
                        } else {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod() || AodFeatureOption.isSupportRamLessAod()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_new_mech");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_aod_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_ramless_lhbm_aod_support");
                }
            }
        });
        final int i8 = 9;
        isVideoModeAodSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.AodFeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:20:0x0049  */
            /* JADX WARN: Code duplicated, block: B:28:0x0064  */
            public final Object invoke() {
                switch (i8) {
                    case 0:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.disable_aod_all_day_mode");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.lcd_aod_mode");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_ramless_support");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.oha_support");
                    case 6:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        boolean z = true;
                        if (FlavorOneFeatureOption.isFlavorOneDevice()) {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod()) {
                                z = false;
                            } else if (!AodFeatureOption.isSupportRamLessAod()) {
                                FeatureOption.INSTANCE.getClass();
                                if (FeatureOption.isLightOs()) {
                                    if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                        z = false;
                                    }
                                }
                            } else if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                z = false;
                            }
                        } else {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod() || AodFeatureOption.isSupportRamLessAod()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_new_mech");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_aod_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_ramless_lhbm_aod_support");
                }
            }
        });
        final int i9 = 10;
        isRamlessLhbmVideoModeAodSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.AodFeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:20:0x0049  */
            /* JADX WARN: Code duplicated, block: B:28:0x0064  */
            public final Object invoke() {
                switch (i9) {
                    case 0:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.disable_aod_all_day_mode");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.lcd_aod_mode");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_ramless_support");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.oha_support");
                    case 6:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        boolean z = true;
                        if (FlavorOneFeatureOption.isFlavorOneDevice()) {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod()) {
                                z = false;
                            } else if (!AodFeatureOption.isSupportRamLessAod()) {
                                FeatureOption.INSTANCE.getClass();
                                if (FeatureOption.isLightOs()) {
                                    if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                        z = false;
                                    }
                                }
                            } else if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                z = false;
                            }
                        } else {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod() || AodFeatureOption.isSupportRamLessAod()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_new_mech");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_aod_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_ramless_lhbm_aod_support");
                }
            }
        });
        final int i10 = 1;
        isDisableAodAlwaysOnDisplayMode$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.AodFeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:20:0x0049  */
            /* JADX WARN: Code duplicated, block: B:28:0x0064  */
            public final Object invoke() {
                switch (i10) {
                    case 0:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.disable_aod_all_day_mode");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.lcd_aod_mode");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_ramless_support");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.oha_support");
                    case 6:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        boolean z = true;
                        if (FlavorOneFeatureOption.isFlavorOneDevice()) {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod()) {
                                z = false;
                            } else if (!AodFeatureOption.isSupportRamLessAod()) {
                                FeatureOption.INSTANCE.getClass();
                                if (FeatureOption.isLightOs()) {
                                    if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                        z = false;
                                    }
                                }
                            } else if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                z = false;
                            }
                        } else {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod() || AodFeatureOption.isSupportRamLessAod()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_new_mech");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_aod_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_ramless_lhbm_aod_support");
                }
            }
        });
        final int i11 = 2;
        isLcdScreenAodModeSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.AodFeatureOption$$ExternalSyntheticLambda0
            /* JADX WARN: Code duplicated, block: B:20:0x0049  */
            /* JADX WARN: Code duplicated, block: B:28:0x0064  */
            public final Object invoke() {
                switch (i11) {
                    case 0:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.disable_aod_all_day_mode");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.lcd_aod_mode");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_ramless_support");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.oha_support");
                    case 6:
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        boolean z = true;
                        if (FlavorOneFeatureOption.isFlavorOneDevice()) {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod()) {
                                z = false;
                            } else if (!AodFeatureOption.isSupportRamLessAod()) {
                                FeatureOption.INSTANCE.getClass();
                                if (FeatureOption.isLightOs()) {
                                    if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                        z = false;
                                    }
                                }
                            } else if (KeyguardFeatureOption.INSTANCE.getSKeyguardLightOsLevel() != 1) {
                                z = false;
                            }
                        } else {
                            AodFeatureOption.INSTANCE.getClass();
                            if (!AodFeatureOption.isSupportAod() || AodFeatureOption.isSupportRamLessAod()) {
                                z = false;
                            }
                        }
                        return Boolean.valueOf(z);
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.aod_prevent_burn_new_mech");
                    case 9:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_aod_support");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.video_mode_ramless_lhbm_aod_support");
                }
            }
        });
    }

    public static boolean isDisableAodAlwaysOnDisplayMode() {
        return ((Boolean) isDisableAodAlwaysOnDisplayMode$delegate.getValue()).booleanValue();
    }

    public static boolean isLcdScreenAodModeSupport() {
        return ((Boolean) isLcdScreenAodModeSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isPreventBurnNewMech() {
        return ((Boolean) isPreventBurnNewMech$delegate.getValue()).booleanValue();
    }

    public static boolean isPreventedScreenBurn() {
        return ((Boolean) isPreventedScreenBurn$delegate.getValue()).booleanValue();
    }

    public static boolean isRamlessLhbmVideoModeAodSupport() {
        return ((Boolean) isRamlessLhbmVideoModeAodSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportAod() {
        return ((Boolean) isSupportAod$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportCurvedDisplay() {
        return ((Boolean) isSupportCurvedDisplay$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportLTPO1HzAOD() {
        return ((Boolean) isSupportLTPO1HzAOD$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportRamLessAod() {
        return ((Boolean) isSupportRamLessAod$delegate.getValue()).booleanValue();
    }

    public static boolean isVideoModeAodSupport() {
        return ((Boolean) isVideoModeAodSupport$delegate.getValue()).booleanValue();
    }

    public final List<String> getAppFeatureCacheList() {
        return appFeatureList;
    }

    public final int getDefaultOpenAodStyleAndMode() {
        return defaultOpenAodStyleAndMode;
    }

    public final int getSFirstApiLevel() {
        return ((Number) sFirstApiLevel$delegate.getValue()).intValue();
    }

    public final void loadAppFeature(Context context) {
        int sFirstApiLevel = getSFirstApiLevel();
        boolean z = true;
        AppFeatureProviderUtils.FeatureID featureID = AppFeatureProviderUtils.FeatureID.STATIC_COMPONENT;
        isAodFullBrightnessWhileShow = (sFirstApiLevel >= 33 && !AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, AOD_DISABLE_FULL_BRIGHTNESS_WHILE_SHOW)) || AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, AOD_FULL_BRIGHTNESS_WHILE_SHOW);
        context.getPackageManager().hasSystemFeature(AOD_PROXIMITY_DELAY);
        isPreventedScreenBurnDevices = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, AOD_PREVENTED_SCREEN_BURN_FEATURE);
        isAodSwitchDefaultEnabled = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), AOD_SWITCH_DEFALUT_ENABLED_FEATURE, false);
        if ((getSFirstApiLevel() < 33 || AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, AOD_DISABLE_INCREASE_BRIGHTNESS)) && !AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, AOD_INCREASE_BRIGHTNESS)) {
            z = false;
        }
        isAodIncreaseBrightness = z;
        AppFeatureProviderUtils.getBoolean(context.getContentResolver(), OPLUS_AOD_SCENE_INFO_FEATURE, false);
        AppFeatureProviderUtils.getBoolean(context.getContentResolver(), OPLUS_AOD_SCENE_INFO_LIGHTOS, false);
        isAodInspirationDisable = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), AOD_INSPIRATION_DISABLED, false);
        AppFeatureProviderUtils.getBoolean(context.getContentResolver(), OPLUS_AOD_WAKE_BY_CLICK, false);
        isPanoramicAODFeatureEnable = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), OPLUS_PANORAMIC_AOD_ENABLE, false);
        defaultOpenAodStyleAndMode = AppFeatureProviderUtils.getInt(context.getContentResolver(), featureID, OPLUS_AOD_OPEN_DEFAULT_STYLE_AND_MODE, 0);
    }
}

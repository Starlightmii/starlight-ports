package com.oplusos.systemui.common.feature;

import android.content.ContentResolver;
import android.content.Context;
import android.os.customize.OplusCustomizeRestrictionManager;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplus.coreapp.appfeature.AppFeatureProviderUtils;
import com.oplus.keyguard.util.FeatureOption$isCompactWindowMode$2$;
import com.oplus.systemui.shade.ShadeExpansionHelperImpl;
import com.oplus.wrapper.os.SystemProperties;
import com.oplus.zoom.animation.ZoomAnimationUtils;
import com.oplusos.systemui.common.util.ReflectionUtils;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationFeatureOption {
    private static final String FEATURE_HARDWARE_AON_ENABLE = "oplus.hardware.aon_enable";
    private static final String FEATURE_SENSORHUB_AON_AOD_ENABLE = "oplus.software.aon_sensorhub_enable";
    private static final String FEATURE_SOFTWARE_AON_AOD_ENABLE = "oplus.software.aon_aod_enable";
    private static final String FEATURE_SOFTWARE_AON_SUB_CAM_ENABLE = "oplus.software.aon_sub_cam_enable";
    private static final String HETEROMORPHISM_SCREEN_STRING = "oplus.software.display.screen_heteromorphism";
    private static final String IS_DISABLE_AON_ANT_PEEP_STRING = "oplus.software.aon_ant_peep_disable";
    private static final String IS_OPLUS_ATO_STRING = "ro.oplus.factory_mode";
    private static final String IS_OPLUS_N_1_CAPSULE_DEFAULT_STATE = "oplus.software.lockscreen.capsule_default_state";
    private static Context applicationContext;
    private static final Lazy isAntPeepingDisable$delegate;
    public static boolean isAodMediaDisable;
    private static final Lazy isAtoVersion$delegate;
    public static boolean isDefaultLockScreenNotificationContentHidden;
    private static final Lazy isEarScreen$delegate;
    public static boolean isEnvelopSupport;
    public static boolean isGaussBlurDisabled;
    private static final Lazy isHardwareAonEnable$delegate;
    public static boolean isLightOsZoomTimeoutEnabled;
    public static boolean isNotificationCapsuleMetaBallEnable;
    public static boolean isNotificationLightOs;
    private static final Lazy isOplusN1CapsuleDefaultState$delegate;
    public static boolean isPanViewBlurDisabled;
    public static boolean isPcSynergyEnable;
    public static boolean isSeedlingExpRegion;
    private static final Lazy isSensorhubAonAodEnable$delegate;
    public static boolean isSmallReplyEnabled;
    private static final Lazy isSoftwareAonAodEnable$delegate;
    private static final Lazy isSoftwareAonSubCamEnable$delegate;
    public static boolean isWallpaperBlurDisabled;
    public static final NotificationFeatureOption INSTANCE = new NotificationFeatureOption();
    private static final String IS_ENVELOP_SUPPORTED_STRING = "com.android.systemui.envelop_supported";
    private static final String USB_SERVICE_FOR_CTA = "com.android.systemui.usb_service_for_cta";
    private static final String ORIGIN_NOTIFICATION_BEHAVIOR = "com.android.systemui.origin_notification_behavior";
    private static final String FEATURE_AFTER_SALE_NOTICE = "com.android.systemui.after_sale_notice";
    private static final String FEATURE_USER_SETUP_COMPLETED_CN = "com.android.systemui.user_setup_completed";
    private static final String FEATURE_GAUSS_BLUR__DISABLED = "com.android.systemui.gauss_blur_disabled";
    private static final String FEATURE_PAN_VIEW_GAUSS_BLUR_DISABLED = "com.android.systemui.pan_view_gauss_blur_disabled";
    private static final String FEATURE_PC_SYNERGY = "com.android.systemui.pc_synergy";
    private static final String FEATURE_AOD_MEDIA_DISABLE = "com.android.systemui.spotify_aod_disable";
    private static final String NOTIFICATION_LIMIT_NUM = "com.android.systemui.notification_limit_num";
    private static final String NOTIFICATION_LIMIT_TIME = "com.android.systemui.notification_limit_time";
    private static final String SUPPORT_FLUID_EXP_SMART_SUGGESTION = "com.oplus.systemui.fluid_seeding_settings_for_ex";
    private static final String SUPPORT_FLUID_EXP_REGION_IS_INDIA = "com.oplus.systemui.fluid_seeding_settings_for_india";
    private static final String DEFAULT_HIDE_NOTIFICATION_CONTENT = "com.oplus.systemui.hide_notification_content";
    private static final String FEATURE_SMALL_REPLY_ENABLED = "com.android.systemui.small_reply_enabled";
    private static final String FEATURE_NOTIFICATION_LIGHT_OS = "com.android.systemui.notification_light_os_level";
    private static final String FEATURE_WALLPAPER_BLUR_DISABLED = "com.android.systemui.wallpaper_blur_disabled";
    private static final String FEATURE_NOTIFICATION_CAPSULE_META_BALL = "com.android.systemui.notification_capsule_meta_ball";
    private static final String FEATURE_ZOOM_IN_PAUSE_TIMEOUT_LIGHTOS_FLOATING_HANDLE = "com.android.systemui.zoom_in_pause_timeout_lightos_floating_handle";
    private static final List<String> appFeatureList = CollectionsKt__CollectionsKt.listOf(new String[]{IS_ENVELOP_SUPPORTED_STRING, USB_SERVICE_FOR_CTA, ORIGIN_NOTIFICATION_BEHAVIOR, FEATURE_AFTER_SALE_NOTICE, FEATURE_USER_SETUP_COMPLETED_CN, FEATURE_GAUSS_BLUR__DISABLED, FEATURE_PAN_VIEW_GAUSS_BLUR_DISABLED, FEATURE_PC_SYNERGY, FEATURE_AOD_MEDIA_DISABLE, NOTIFICATION_LIMIT_NUM, NOTIFICATION_LIMIT_TIME, SUPPORT_FLUID_EXP_SMART_SUGGESTION, SUPPORT_FLUID_EXP_REGION_IS_INDIA, DEFAULT_HIDE_NOTIFICATION_CONTENT, FEATURE_SMALL_REPLY_ENABLED, FEATURE_NOTIFICATION_LIGHT_OS, FEATURE_WALLPAPER_BLUR_DISABLED, FEATURE_NOTIFICATION_CAPSULE_META_BALL, FEATURE_ZOOM_IN_PAUSE_TIMEOUT_LIGHTOS_FLOATING_HANDLE});
    public static int notificationLimitNum = ZoomAnimationUtils.TRANSIT_SCALE_AND_POSITION;
    public static int notificationLimitTime = 48;

    static {
        final int i = 0;
        isEarScreen$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.NotificationFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 1:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.factory_mode", ShadeExpansionHelperImpl.VAL_INVALID), "1"));
                    case 2:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.hardware.aon_enable"));
                    case 3:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_aod_enable"));
                    case 4:
                        NotificationFeatureOption.INSTANCE.getClass();
                        if (NotificationFeatureOption.isSoftwareAonAodEnable() && NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_sensorhub_enable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_ant_peep_disable"));
                    case 6:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_sub_cam_enable"));
                    default:
                        return Integer.valueOf(android.os.SystemProperties.getInt("oplus.software.lockscreen.capsule_default_state", 0));
                }
            }
        });
        final int i2 = 1;
        isAtoVersion$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.NotificationFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i2) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 1:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.factory_mode", ShadeExpansionHelperImpl.VAL_INVALID), "1"));
                    case 2:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.hardware.aon_enable"));
                    case 3:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_aod_enable"));
                    case 4:
                        NotificationFeatureOption.INSTANCE.getClass();
                        if (NotificationFeatureOption.isSoftwareAonAodEnable() && NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_sensorhub_enable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_ant_peep_disable"));
                    case 6:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_sub_cam_enable"));
                    default:
                        return Integer.valueOf(android.os.SystemProperties.getInt("oplus.software.lockscreen.capsule_default_state", 0));
                }
            }
        });
        final int i3 = 2;
        isHardwareAonEnable$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.NotificationFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i3) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 1:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.factory_mode", ShadeExpansionHelperImpl.VAL_INVALID), "1"));
                    case 2:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.hardware.aon_enable"));
                    case 3:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_aod_enable"));
                    case 4:
                        NotificationFeatureOption.INSTANCE.getClass();
                        if (NotificationFeatureOption.isSoftwareAonAodEnable() && NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_sensorhub_enable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_ant_peep_disable"));
                    case 6:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_sub_cam_enable"));
                    default:
                        return Integer.valueOf(android.os.SystemProperties.getInt("oplus.software.lockscreen.capsule_default_state", 0));
                }
            }
        });
        final int i4 = 3;
        isSoftwareAonAodEnable$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.NotificationFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i4) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 1:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.factory_mode", ShadeExpansionHelperImpl.VAL_INVALID), "1"));
                    case 2:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.hardware.aon_enable"));
                    case 3:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_aod_enable"));
                    case 4:
                        NotificationFeatureOption.INSTANCE.getClass();
                        if (NotificationFeatureOption.isSoftwareAonAodEnable() && NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_sensorhub_enable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_ant_peep_disable"));
                    case 6:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_sub_cam_enable"));
                    default:
                        return Integer.valueOf(android.os.SystemProperties.getInt("oplus.software.lockscreen.capsule_default_state", 0));
                }
            }
        });
        final int i5 = 4;
        isSensorhubAonAodEnable$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.NotificationFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i5) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 1:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.factory_mode", ShadeExpansionHelperImpl.VAL_INVALID), "1"));
                    case 2:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.hardware.aon_enable"));
                    case 3:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_aod_enable"));
                    case 4:
                        NotificationFeatureOption.INSTANCE.getClass();
                        if (NotificationFeatureOption.isSoftwareAonAodEnable() && NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_sensorhub_enable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_ant_peep_disable"));
                    case 6:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_sub_cam_enable"));
                    default:
                        return Integer.valueOf(android.os.SystemProperties.getInt("oplus.software.lockscreen.capsule_default_state", 0));
                }
            }
        });
        final int i6 = 5;
        isAntPeepingDisable$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.NotificationFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i6) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 1:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.factory_mode", ShadeExpansionHelperImpl.VAL_INVALID), "1"));
                    case 2:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.hardware.aon_enable"));
                    case 3:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_aod_enable"));
                    case 4:
                        NotificationFeatureOption.INSTANCE.getClass();
                        if (NotificationFeatureOption.isSoftwareAonAodEnable() && NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_sensorhub_enable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_ant_peep_disable"));
                    case 6:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_sub_cam_enable"));
                    default:
                        return Integer.valueOf(android.os.SystemProperties.getInt("oplus.software.lockscreen.capsule_default_state", 0));
                }
            }
        });
        final int i7 = 6;
        isSoftwareAonSubCamEnable$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.NotificationFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i7) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 1:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.factory_mode", ShadeExpansionHelperImpl.VAL_INVALID), "1"));
                    case 2:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.hardware.aon_enable"));
                    case 3:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_aod_enable"));
                    case 4:
                        NotificationFeatureOption.INSTANCE.getClass();
                        if (NotificationFeatureOption.isSoftwareAonAodEnable() && NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_sensorhub_enable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_ant_peep_disable"));
                    case 6:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_sub_cam_enable"));
                    default:
                        return Integer.valueOf(android.os.SystemProperties.getInt("oplus.software.lockscreen.capsule_default_state", 0));
                }
            }
        });
        final int i8 = 7;
        isOplusN1CapsuleDefaultState$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.NotificationFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i8) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_heteromorphism");
                    case 1:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.factory_mode", ShadeExpansionHelperImpl.VAL_INVALID), "1"));
                    case 2:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.hardware.aon_enable"));
                    case 3:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_aod_enable"));
                    case 4:
                        NotificationFeatureOption.INSTANCE.getClass();
                        if (NotificationFeatureOption.isSoftwareAonAodEnable() && NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_sensorhub_enable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 5:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_ant_peep_disable"));
                    case 6:
                        NotificationFeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(NotificationFeatureOption.isOplusFeatureSupport("oplus.software.aon_sub_cam_enable"));
                    default:
                        return Integer.valueOf(android.os.SystemProperties.getInt("oplus.software.lockscreen.capsule_default_state", 0));
                }
            }
        });
    }

    public static boolean isAntPeepingDisable() {
        return ((Boolean) isAntPeepingDisable$delegate.getValue()).booleanValue();
    }

    public static boolean isCapsuleN3() {
        return isOplusN1CapsuleDefaultState() == 3;
    }

    public static boolean isEarScreen() {
        return ((Boolean) isEarScreen$delegate.getValue()).booleanValue();
    }

    public static boolean isHardwareAonEnable() {
        return ((Boolean) isHardwareAonEnable$delegate.getValue()).booleanValue();
    }

    public static boolean isOplusFeatureSupport(String str) {
        OplusFeatureConfigManager oplusFeatureConfigManager = OplusFeatureConfigManager.getInstance();
        return oplusFeatureConfigManager != null && oplusFeatureConfigManager.hasFeature(str);
    }

    public static int isOplusN1CapsuleDefaultState() {
        return ((Number) isOplusN1CapsuleDefaultState$delegate.getValue()).intValue();
    }

    public static boolean isSensorhubAonAodEnable() {
        return ((Boolean) isSensorhubAonAodEnable$delegate.getValue()).booleanValue();
    }

    public static boolean isSoftwareAonAodEnable() {
        return ((Boolean) isSoftwareAonAodEnable$delegate.getValue()).booleanValue();
    }

    public static boolean isSoftwareAonSubCamEnable() {
        return ((Boolean) isSoftwareAonSubCamEnable$delegate.getValue()).booleanValue();
    }

    public final List<String> getAppFeatureCacheList() {
        return appFeatureList;
    }

    public final int getNotificationLimitNum() {
        return notificationLimitNum;
    }

    public final int getNotificationLimitTime() {
        return notificationLimitTime;
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
        } catch (Exception unused) {
            return false;
        }
    }

    public final void loadAppFeature(Context context) {
        applicationContext = context;
        isEnvelopSupport = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), IS_ENVELOP_SUPPORTED_STRING, false);
        AppFeatureProviderUtils.getBoolean(context.getContentResolver(), USB_SERVICE_FOR_CTA, false);
        AppFeatureProviderUtils.getBoolean(context.getContentResolver(), ORIGIN_NOTIFICATION_BEHAVIOR, true);
        AppFeatureProviderUtils.getBoolean(context.getContentResolver(), FEATURE_AFTER_SALE_NOTICE, false);
        ContentResolver contentResolver = context.getContentResolver();
        AppFeatureProviderUtils.FeatureID featureID = AppFeatureProviderUtils.FeatureID.STATIC_COMPONENT;
        AppFeatureProviderUtils.isFeatureSupport(contentResolver, featureID, FEATURE_USER_SETUP_COMPLETED_CN);
        isPanViewBlurDisabled = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, FEATURE_PAN_VIEW_GAUSS_BLUR_DISABLED);
        isPcSynergyEnable = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), FEATURE_PC_SYNERGY, true);
        isAodMediaDisable = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, FEATURE_AOD_MEDIA_DISABLE);
        isGaussBlurDisabled = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, FEATURE_GAUSS_BLUR__DISABLED);
        isSeedlingExpRegion = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, SUPPORT_FLUID_EXP_SMART_SUGGESTION);
        AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, SUPPORT_FLUID_EXP_REGION_IS_INDIA);
        isDefaultLockScreenNotificationContentHidden = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, DEFAULT_HIDE_NOTIFICATION_CONTENT);
        notificationLimitNum = AppFeatureProviderUtils.getInt(context.getContentResolver(), featureID, NOTIFICATION_LIMIT_NUM, ZoomAnimationUtils.TRANSIT_SCALE_AND_POSITION);
        notificationLimitTime = AppFeatureProviderUtils.getInt(context.getContentResolver(), featureID, NOTIFICATION_LIMIT_TIME, 48);
        isSmallReplyEnabled = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), FEATURE_SMALL_REPLY_ENABLED, false);
        isNotificationLightOs = AppFeatureProviderUtils.getInt(context.getContentResolver(), featureID, FEATURE_NOTIFICATION_LIGHT_OS, -1) >= 1;
        isWallpaperBlurDisabled = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, FEATURE_WALLPAPER_BLUR_DISABLED);
        isNotificationCapsuleMetaBallEnable = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), FEATURE_NOTIFICATION_CAPSULE_META_BALL, true);
        isLightOsZoomTimeoutEnabled = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), FEATURE_ZOOM_IN_PAUSE_TIMEOUT_LIGHTOS_FLOATING_HANDLE, false);
    }

    public final void setNotificationCapsuleMetaBallEnable(boolean z) {
        isNotificationCapsuleMetaBallEnable = z;
    }
}

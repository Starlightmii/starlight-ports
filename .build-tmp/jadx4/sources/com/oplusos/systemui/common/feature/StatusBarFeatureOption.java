package com.oplusos.systemui.common.feature;

import android.content.ContentResolver;
import android.content.Context;
import android.os.SystemProperties;
import com.oplus.content.OplusFeatureConfigManager;
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
public final class StatusBarFeatureOption {
    private static final String ENABLE_HARDWARE_ROUND_CORNER = "oplus.software.display.enable_hardware_roundcorner";
    private static final String FEATURE_STATUS_BAR_ICON_RIGHT_PADDING = "com.android.systemui.statusbar_icon_rightpadding";
    private static final String FEATURE_WLB = "oplus.software.wlb";
    private static final String OPLUS_DISPLAY_RESOLUION_SWITCH_SUPPORT = "oplus.software.display.resolution_switch_support";
    private static final String OPLUS_SUPPORT_PRIVACY_CALL_MODE = "oplus.hardware.audio.dipole_speaker_support";
    private static final String OPLUS_SUPPORT_PRIVACY_NOISE_MODE = "oplus.hardware.audio.voice_isolation_support";
    private static final String ROUND_CORNER_SUPPORT = "oplus.software.display.origin_roundcorner_support";
    private static final String SCREEN_SELECT = "oplus.software.display.screen_select";
    private static Context applicationContext;
    private static final Lazy firstApiLevel$delegate;
    private static final Lazy isChargeVoocSpecialColorShow$delegate;
    private static final Lazy isEnableHardwareRoundCorner$delegate;
    public static boolean isHideScreenHoleBorder;
    public static boolean isHighlightNoDeveloper;
    public static boolean isOnePxOptSupport;
    private static final Lazy isRoundCornerSupport$delegate;
    private static final Lazy isScreenResolutionSwitchSupport$delegate;
    private static final Lazy isScreenSelect$delegate;
    private static final Lazy isSignalMobileIconDefaultParallel$delegate;
    public static boolean isStatusBarChipSupport;
    public static boolean isSupportPasspoint;
    private static final Lazy isSupportPrivacyCallMode$delegate;
    private static final Lazy isSupportPrivacyNoiseMode$delegate;
    public static boolean isSupportSosStatIcon;
    private static final Lazy isSystemIconRightPadding$delegate;
    public static boolean isSystemUiExpSignalUi;
    public static boolean sWlbFeatureSupport;
    public static boolean sendDeveloperModeNotification;
    public static final StatusBarFeatureOption INSTANCE = new StatusBarFeatureOption();
    private static final String HD_CT_SUPPORT_STRING = "com.android.systemui.hd_ct_support";
    private static final String SUPPORT_SOS_STAT_ICON_STRING = "com.android.systemui.stat_sys_sos_icon";
    private static final String HIGHLIGHT_NODEVELOPER_STRING = "com.android.systemui.highlight_nodeveloper";
    private static final String ONE_PX_OPT_SUPPORT = "com.android.systemui.one_px_opt_support";
    private static final String SEND_DEVELOPER_MODE_NOTIFICATION_STRING = "com.android.systemui.send_developer_mode_notification";
    private static final String FEATURE_HIDE_SCREEN_HOLE_BORDER = "com.android.systemui.hide_ro.oplus.screenhole.border";
    private static final String FEATURE_SUPPORT_STATUS_BAR_CHIP = "com.android.systemui.support_status_bar_chip";
    private static final List<String> appFeatureList = CollectionsKt__CollectionsKt.listOf(new String[]{HD_CT_SUPPORT_STRING, SUPPORT_SOS_STAT_ICON_STRING, HIGHLIGHT_NODEVELOPER_STRING, ONE_PX_OPT_SUPPORT, SEND_DEVELOPER_MODE_NOTIFICATION_STRING, FEATURE_HIDE_SCREEN_HOLE_BORDER, FEATURE_SUPPORT_STATUS_BAR_CHIP});

    static {
        final int i = 0;
        isScreenResolutionSwitchSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.StatusBarFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_select");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.origin_roundcorner_support");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.enable_hardware_roundcorner");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.audio.dipole_speaker_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("com.android.systemui.statusbar_icon_rightpadding");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.audio.voice_isolation_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 8:
                        return Boolean.valueOf(StatusBarFeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    default:
                        return Boolean.valueOf(StatusBarFeatureOption.INSTANCE.getFirstApiLevel() < 37);
                }
            }
        });
        final int i2 = 1;
        isScreenSelect$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.StatusBarFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_select");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.origin_roundcorner_support");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.enable_hardware_roundcorner");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.audio.dipole_speaker_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("com.android.systemui.statusbar_icon_rightpadding");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.audio.voice_isolation_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 8:
                        return Boolean.valueOf(StatusBarFeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    default:
                        return Boolean.valueOf(StatusBarFeatureOption.INSTANCE.getFirstApiLevel() < 37);
                }
            }
        });
        final int i3 = 2;
        isRoundCornerSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.StatusBarFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i3) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_select");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.origin_roundcorner_support");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.enable_hardware_roundcorner");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.audio.dipole_speaker_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("com.android.systemui.statusbar_icon_rightpadding");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.audio.voice_isolation_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 8:
                        return Boolean.valueOf(StatusBarFeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    default:
                        return Boolean.valueOf(StatusBarFeatureOption.INSTANCE.getFirstApiLevel() < 37);
                }
            }
        });
        final int i4 = 3;
        isEnableHardwareRoundCorner$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.StatusBarFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i4) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_select");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.origin_roundcorner_support");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.enable_hardware_roundcorner");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.audio.dipole_speaker_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("com.android.systemui.statusbar_icon_rightpadding");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.audio.voice_isolation_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 8:
                        return Boolean.valueOf(StatusBarFeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    default:
                        return Boolean.valueOf(StatusBarFeatureOption.INSTANCE.getFirstApiLevel() < 37);
                }
            }
        });
        final int i5 = 4;
        isSupportPrivacyCallMode$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.StatusBarFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i5) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_select");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.origin_roundcorner_support");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.enable_hardware_roundcorner");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.audio.dipole_speaker_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("com.android.systemui.statusbar_icon_rightpadding");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.audio.voice_isolation_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 8:
                        return Boolean.valueOf(StatusBarFeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    default:
                        return Boolean.valueOf(StatusBarFeatureOption.INSTANCE.getFirstApiLevel() < 37);
                }
            }
        });
        final int i6 = 5;
        isSystemIconRightPadding$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.StatusBarFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i6) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_select");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.origin_roundcorner_support");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.enable_hardware_roundcorner");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.audio.dipole_speaker_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("com.android.systemui.statusbar_icon_rightpadding");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.audio.voice_isolation_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 8:
                        return Boolean.valueOf(StatusBarFeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    default:
                        return Boolean.valueOf(StatusBarFeatureOption.INSTANCE.getFirstApiLevel() < 37);
                }
            }
        });
        final int i7 = 6;
        isSupportPrivacyNoiseMode$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.StatusBarFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i7) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_select");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.origin_roundcorner_support");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.enable_hardware_roundcorner");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.audio.dipole_speaker_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("com.android.systemui.statusbar_icon_rightpadding");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.audio.voice_isolation_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 8:
                        return Boolean.valueOf(StatusBarFeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    default:
                        return Boolean.valueOf(StatusBarFeatureOption.INSTANCE.getFirstApiLevel() < 37);
                }
            }
        });
        final int i8 = 7;
        firstApiLevel$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.StatusBarFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i8) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_select");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.origin_roundcorner_support");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.enable_hardware_roundcorner");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.audio.dipole_speaker_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("com.android.systemui.statusbar_icon_rightpadding");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.audio.voice_isolation_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 8:
                        return Boolean.valueOf(StatusBarFeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    default:
                        return Boolean.valueOf(StatusBarFeatureOption.INSTANCE.getFirstApiLevel() < 37);
                }
            }
        });
        final int i9 = 8;
        isChargeVoocSpecialColorShow$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.StatusBarFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i9) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_select");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.origin_roundcorner_support");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.enable_hardware_roundcorner");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.audio.dipole_speaker_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("com.android.systemui.statusbar_icon_rightpadding");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.audio.voice_isolation_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 8:
                        return Boolean.valueOf(StatusBarFeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    default:
                        return Boolean.valueOf(StatusBarFeatureOption.INSTANCE.getFirstApiLevel() < 37);
                }
            }
        });
        final int i10 = 9;
        isSignalMobileIconDefaultParallel$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.StatusBarFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i10) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.screen_select");
                    case 2:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.origin_roundcorner_support");
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.enable_hardware_roundcorner");
                    case 4:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.audio.dipole_speaker_support");
                    case 5:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("com.android.systemui.statusbar_icon_rightpadding");
                    case 6:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.hardware.audio.voice_isolation_support");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return Integer.valueOf(SystemProperties.getInt("ro.product.first_api_level", -1));
                    case 8:
                        return Boolean.valueOf(StatusBarFeatureOption.INSTANCE.getFirstApiLevel() < 35);
                    default:
                        return Boolean.valueOf(StatusBarFeatureOption.INSTANCE.getFirstApiLevel() < 37);
                }
            }
        });
    }

    public static boolean isChargeVoocSpecialColorShow() {
        return ((Boolean) isChargeVoocSpecialColorShow$delegate.getValue()).booleanValue();
    }

    public static boolean isEnableHardwareRoundCorner() {
        return ((Boolean) isEnableHardwareRoundCorner$delegate.getValue()).booleanValue();
    }

    public static boolean isRoundCornerSupport() {
        return ((Boolean) isRoundCornerSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isScreenResolutionSwitchSupport() {
        return ((Boolean) isScreenResolutionSwitchSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isScreenSelect() {
        return ((Boolean) isScreenSelect$delegate.getValue()).booleanValue();
    }

    public static boolean isSignalMobileIconDefaultParallel() {
        return ((Boolean) isSignalMobileIconDefaultParallel$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportPrivacyCallMode() {
        return ((Boolean) isSupportPrivacyCallMode$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportPrivacyNoiseMode() {
        return ((Boolean) isSupportPrivacyNoiseMode$delegate.getValue()).booleanValue();
    }

    public final List<String> getAppFeatureCacheList() {
        return appFeatureList;
    }

    public final int getFirstApiLevel() {
        return ((Number) firstApiLevel$delegate.getValue()).intValue();
    }

    public final boolean getSendDeveloperModeNotification() {
        return sendDeveloperModeNotification;
    }

    public final void loadAppFeature(Context context) {
        applicationContext = context;
        isSystemUiExpSignalUi = context.getResources().getBoolean(2131034183);
        isSupportPasspoint = context.getResources().getBoolean(2131034245);
        AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), HD_CT_SUPPORT_STRING);
        ContentResolver contentResolver = context.getContentResolver();
        AppFeatureProviderUtils.FeatureID featureID = AppFeatureProviderUtils.FeatureID.STATIC_COMPONENT;
        isSupportSosStatIcon = AppFeatureProviderUtils.isFeatureSupport(contentResolver, featureID, SUPPORT_SOS_STAT_ICON_STRING);
        isHighlightNoDeveloper = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, HIGHLIGHT_NODEVELOPER_STRING);
        sendDeveloperModeNotification = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, SEND_DEVELOPER_MODE_NOTIFICATION_STRING);
        isOnePxOptSupport = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, ONE_PX_OPT_SUPPORT);
        sWlbFeatureSupport = OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.wlb");
        isHideScreenHoleBorder = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, FEATURE_HIDE_SCREEN_HOLE_BORDER);
        isStatusBarChipSupport = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), FEATURE_SUPPORT_STATUS_BAR_CHIP, false);
    }

    public final void setCtHdSupport(boolean z) {
    }

    public final void setSupportPasspoint(boolean z) {
        isSupportPasspoint = z;
    }
}

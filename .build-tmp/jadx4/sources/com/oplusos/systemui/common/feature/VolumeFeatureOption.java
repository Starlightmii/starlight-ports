package com.oplusos.systemui.common.feature;

import android.content.Context;
import android.os.SystemProperties;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplus.coreapp.appfeature.AppFeatureProviderUtils;
import com.oplus.keyguard.util.FeatureOption$isCompactWindowMode$2$;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VolumeFeatureOption {
    private static final String FEATURE_KEY_IS_SUPPORT_STEALTH_MODE = "oplus.software.stealth_security_mode";
    private static final String IS_OPLUS_SUPER_VOLUME_3X = "oplus.software.audio.super_volume_3x";
    private static final String IS_OPLUS_SUPER_VOLUME_4X = "oplus.software.audio.super_volume_4x";
    private static final String IS_OPLUS_SUPER_VOLUME_STRING = "oplus.software.audio.super_volume";
    private static final String IS_OPLUS_SUPER_VOLUME_STRING_EARPIECE = "oplus.software.audio.super_volume_call_earpiece";
    private static String IS_VOLUME_THREEKEY_LOCATION = "";
    private static final String IS_VOLUME_THREEKEY_LOCATION_STRING = "persist.oplus.software.alertslider.location";
    private static final String OPLUS_DISABLE_VOLUME_BLUR = "ro.oplus.display.disable.volume_blur";
    private static final String OPLUS_MULTI_MEDIA_SUPPORT = "oplus.software.multi_app.volume.adjust.support";
    private static final String OPLUS_SUPER_VOLUME_LEVEL = "persist.sys.audio.super.volume";
    private static final String VOLUME_THREEKEY_LOCATION_COMMON = "2";
    private static final String VOLUME_THREEKEY_LOCATION_LEFT = "1";
    private static final String VOLUME_THREEKEY_LOCATION_STRING = "persist.oplus.software.alertslider.location";
    private static final Lazy getOplusSuperVolumeLevel$delegate;
    private static final Lazy isOplusSuperVolume$delegate;
    private static final Lazy isOplusSuperVolumeCall$delegate;
    private static final Lazy isOplusSuperVolumeMode2x$delegate;
    private static final Lazy isOplusSuperVolumeMode3x$delegate;
    private static final Lazy isOplusSuperVolumeMode4x$delegate;
    private static final Lazy isOplusVolumeThreeKeyLocation$delegate;
    private static final Lazy isSupportsStealthMode$delegate;
    private static final Lazy isVolumeDynamicBlurEnable$delegate;
    private static final Lazy sFlavorOneMultiMediaDevice$delegate;
    public static boolean sIsSupportStealthMode;
    public static boolean sIsVolumeDynamicBlurEnable;
    private static final Lazy volumeThreekeyLocation$delegate;
    public static final VolumeFeatureOption INSTANCE = new VolumeFeatureOption();
    private static final String FEATURE_ENABLE_VOLUME_DYNAMIC_BLUR = "com.android.systemui.volume_dynamic_blur.enable";
    private static final List<String> appFeatureList = CollectionsKt__CollectionsJVMKt.listOf(FEATURE_ENABLE_VOLUME_DYNAMIC_BLUR);

    static {
        final int i = 0;
        volumeThreekeyLocation$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.VolumeFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i) {
                    case 0:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case 1:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_4x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 400) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 2:
                        return Integer.valueOf(VolumeFeatureOption.getOplusSuperVolumeLevel_delegate$lambda$10());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.multi_app.volume.adjust.support");
                    case 4:
                        return Boolean.valueOf(VolumeFeatureOption.sIsSupportStealthMode);
                    case 5:
                        return Boolean.valueOf(VolumeFeatureOption.sIsVolumeDynamicBlurEnable);
                    case 6:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.super_volume_call_earpiece");
                    case 8:
                        VolumeFeatureOption.INSTANCE.getClass();
                        if (!VolumeFeatureOption.isOplusSuperVolumeMode2x() && !VolumeFeatureOption.isOplusSuperVolumeMode3x() && !VolumeFeatureOption.isOplusSuperVolumeMode4x()) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 200) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    default:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_3x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 300) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                }
            }
        });
        final int i2 = 3;
        sFlavorOneMultiMediaDevice$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.VolumeFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i2) {
                    case 0:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case 1:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_4x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 400) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 2:
                        return Integer.valueOf(VolumeFeatureOption.getOplusSuperVolumeLevel_delegate$lambda$10());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.multi_app.volume.adjust.support");
                    case 4:
                        return Boolean.valueOf(VolumeFeatureOption.sIsSupportStealthMode);
                    case 5:
                        return Boolean.valueOf(VolumeFeatureOption.sIsVolumeDynamicBlurEnable);
                    case 6:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.super_volume_call_earpiece");
                    case 8:
                        VolumeFeatureOption.INSTANCE.getClass();
                        if (!VolumeFeatureOption.isOplusSuperVolumeMode2x() && !VolumeFeatureOption.isOplusSuperVolumeMode3x() && !VolumeFeatureOption.isOplusSuperVolumeMode4x()) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 200) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    default:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_3x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 300) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                }
            }
        });
        final int i3 = 4;
        isSupportsStealthMode$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.VolumeFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i3) {
                    case 0:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case 1:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_4x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 400) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 2:
                        return Integer.valueOf(VolumeFeatureOption.getOplusSuperVolumeLevel_delegate$lambda$10());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.multi_app.volume.adjust.support");
                    case 4:
                        return Boolean.valueOf(VolumeFeatureOption.sIsSupportStealthMode);
                    case 5:
                        return Boolean.valueOf(VolumeFeatureOption.sIsVolumeDynamicBlurEnable);
                    case 6:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.super_volume_call_earpiece");
                    case 8:
                        VolumeFeatureOption.INSTANCE.getClass();
                        if (!VolumeFeatureOption.isOplusSuperVolumeMode2x() && !VolumeFeatureOption.isOplusSuperVolumeMode3x() && !VolumeFeatureOption.isOplusSuperVolumeMode4x()) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 200) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    default:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_3x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 300) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                }
            }
        });
        final int i4 = 5;
        isVolumeDynamicBlurEnable$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.VolumeFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i4) {
                    case 0:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case 1:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_4x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 400) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 2:
                        return Integer.valueOf(VolumeFeatureOption.getOplusSuperVolumeLevel_delegate$lambda$10());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.multi_app.volume.adjust.support");
                    case 4:
                        return Boolean.valueOf(VolumeFeatureOption.sIsSupportStealthMode);
                    case 5:
                        return Boolean.valueOf(VolumeFeatureOption.sIsVolumeDynamicBlurEnable);
                    case 6:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.super_volume_call_earpiece");
                    case 8:
                        VolumeFeatureOption.INSTANCE.getClass();
                        if (!VolumeFeatureOption.isOplusSuperVolumeMode2x() && !VolumeFeatureOption.isOplusSuperVolumeMode3x() && !VolumeFeatureOption.isOplusSuperVolumeMode4x()) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 200) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    default:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_3x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 300) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                }
            }
        });
        final int i5 = 6;
        isOplusVolumeThreeKeyLocation$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.VolumeFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i5) {
                    case 0:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case 1:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_4x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 400) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 2:
                        return Integer.valueOf(VolumeFeatureOption.getOplusSuperVolumeLevel_delegate$lambda$10());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.multi_app.volume.adjust.support");
                    case 4:
                        return Boolean.valueOf(VolumeFeatureOption.sIsSupportStealthMode);
                    case 5:
                        return Boolean.valueOf(VolumeFeatureOption.sIsVolumeDynamicBlurEnable);
                    case 6:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.super_volume_call_earpiece");
                    case 8:
                        VolumeFeatureOption.INSTANCE.getClass();
                        if (!VolumeFeatureOption.isOplusSuperVolumeMode2x() && !VolumeFeatureOption.isOplusSuperVolumeMode3x() && !VolumeFeatureOption.isOplusSuperVolumeMode4x()) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 200) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    default:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_3x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 300) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                }
            }
        });
        final int i6 = 7;
        isOplusSuperVolumeCall$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.VolumeFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i6) {
                    case 0:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case 1:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_4x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 400) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 2:
                        return Integer.valueOf(VolumeFeatureOption.getOplusSuperVolumeLevel_delegate$lambda$10());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.multi_app.volume.adjust.support");
                    case 4:
                        return Boolean.valueOf(VolumeFeatureOption.sIsSupportStealthMode);
                    case 5:
                        return Boolean.valueOf(VolumeFeatureOption.sIsVolumeDynamicBlurEnable);
                    case 6:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.super_volume_call_earpiece");
                    case 8:
                        VolumeFeatureOption.INSTANCE.getClass();
                        if (!VolumeFeatureOption.isOplusSuperVolumeMode2x() && !VolumeFeatureOption.isOplusSuperVolumeMode3x() && !VolumeFeatureOption.isOplusSuperVolumeMode4x()) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 200) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    default:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_3x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 300) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                }
            }
        });
        final int i7 = 8;
        isOplusSuperVolume$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.VolumeFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i7) {
                    case 0:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case 1:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_4x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 400) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 2:
                        return Integer.valueOf(VolumeFeatureOption.getOplusSuperVolumeLevel_delegate$lambda$10());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.multi_app.volume.adjust.support");
                    case 4:
                        return Boolean.valueOf(VolumeFeatureOption.sIsSupportStealthMode);
                    case 5:
                        return Boolean.valueOf(VolumeFeatureOption.sIsVolumeDynamicBlurEnable);
                    case 6:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.super_volume_call_earpiece");
                    case 8:
                        VolumeFeatureOption.INSTANCE.getClass();
                        if (!VolumeFeatureOption.isOplusSuperVolumeMode2x() && !VolumeFeatureOption.isOplusSuperVolumeMode3x() && !VolumeFeatureOption.isOplusSuperVolumeMode4x()) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 200) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    default:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_3x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 300) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                }
            }
        });
        final int i8 = 9;
        isOplusSuperVolumeMode2x$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.VolumeFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i8) {
                    case 0:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case 1:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_4x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 400) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 2:
                        return Integer.valueOf(VolumeFeatureOption.getOplusSuperVolumeLevel_delegate$lambda$10());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.multi_app.volume.adjust.support");
                    case 4:
                        return Boolean.valueOf(VolumeFeatureOption.sIsSupportStealthMode);
                    case 5:
                        return Boolean.valueOf(VolumeFeatureOption.sIsVolumeDynamicBlurEnable);
                    case 6:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.super_volume_call_earpiece");
                    case 8:
                        VolumeFeatureOption.INSTANCE.getClass();
                        if (!VolumeFeatureOption.isOplusSuperVolumeMode2x() && !VolumeFeatureOption.isOplusSuperVolumeMode3x() && !VolumeFeatureOption.isOplusSuperVolumeMode4x()) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 200) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    default:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_3x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 300) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                }
            }
        });
        final int i9 = 10;
        isOplusSuperVolumeMode3x$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.VolumeFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i9) {
                    case 0:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case 1:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_4x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 400) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 2:
                        return Integer.valueOf(VolumeFeatureOption.getOplusSuperVolumeLevel_delegate$lambda$10());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.multi_app.volume.adjust.support");
                    case 4:
                        return Boolean.valueOf(VolumeFeatureOption.sIsSupportStealthMode);
                    case 5:
                        return Boolean.valueOf(VolumeFeatureOption.sIsVolumeDynamicBlurEnable);
                    case 6:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.super_volume_call_earpiece");
                    case 8:
                        VolumeFeatureOption.INSTANCE.getClass();
                        if (!VolumeFeatureOption.isOplusSuperVolumeMode2x() && !VolumeFeatureOption.isOplusSuperVolumeMode3x() && !VolumeFeatureOption.isOplusSuperVolumeMode4x()) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 200) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    default:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_3x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 300) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                }
            }
        });
        final int i10 = 1;
        isOplusSuperVolumeMode4x$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.VolumeFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i10) {
                    case 0:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case 1:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_4x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 400) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 2:
                        return Integer.valueOf(VolumeFeatureOption.getOplusSuperVolumeLevel_delegate$lambda$10());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.multi_app.volume.adjust.support");
                    case 4:
                        return Boolean.valueOf(VolumeFeatureOption.sIsSupportStealthMode);
                    case 5:
                        return Boolean.valueOf(VolumeFeatureOption.sIsVolumeDynamicBlurEnable);
                    case 6:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.super_volume_call_earpiece");
                    case 8:
                        VolumeFeatureOption.INSTANCE.getClass();
                        if (!VolumeFeatureOption.isOplusSuperVolumeMode2x() && !VolumeFeatureOption.isOplusSuperVolumeMode3x() && !VolumeFeatureOption.isOplusSuperVolumeMode4x()) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 200) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    default:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_3x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 300) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                }
            }
        });
        final int i11 = 2;
        getOplusSuperVolumeLevel$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.VolumeFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = true;
                switch (i11) {
                    case 0:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case 1:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_4x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 400) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 2:
                        return Integer.valueOf(VolumeFeatureOption.getOplusSuperVolumeLevel_delegate$lambda$10());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.multi_app.volume.adjust.support");
                    case 4:
                        return Boolean.valueOf(VolumeFeatureOption.sIsSupportStealthMode);
                    case 5:
                        return Boolean.valueOf(VolumeFeatureOption.sIsVolumeDynamicBlurEnable);
                    case 6:
                        return SystemProperties.get("persist.oplus.software.alertslider.location", "");
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.audio.super_volume_call_earpiece");
                    case 8:
                        VolumeFeatureOption.INSTANCE.getClass();
                        if (!VolumeFeatureOption.isOplusSuperVolumeMode2x() && !VolumeFeatureOption.isOplusSuperVolumeMode3x() && !VolumeFeatureOption.isOplusSuperVolumeMode4x()) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    case 9:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 200) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    default:
                        if (!OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.audio.super_volume_3x") && VolumeFeatureOption.INSTANCE.getGetOplusSuperVolumeLevel() != 300) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getOplusSuperVolumeLevel_delegate$lambda$10() {
        return SystemProperties.getInt(OPLUS_SUPER_VOLUME_LEVEL, -1);
    }

    public static boolean isOplusSuperVolume() {
        return ((Boolean) isOplusSuperVolume$delegate.getValue()).booleanValue();
    }

    public static boolean isOplusSuperVolumeCall() {
        return ((Boolean) isOplusSuperVolumeCall$delegate.getValue()).booleanValue();
    }

    public static boolean isOplusSuperVolumeMode2x() {
        return ((Boolean) isOplusSuperVolumeMode2x$delegate.getValue()).booleanValue();
    }

    public static boolean isOplusSuperVolumeMode3x() {
        return ((Boolean) isOplusSuperVolumeMode3x$delegate.getValue()).booleanValue();
    }

    public static boolean isOplusSuperVolumeMode4x() {
        return ((Boolean) isOplusSuperVolumeMode4x$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportsStealthMode() {
        return ((Boolean) isSupportsStealthMode$delegate.getValue()).booleanValue();
    }

    public static boolean isVolumeDynamicBlurEnable() {
        return ((Boolean) isVolumeDynamicBlurEnable$delegate.getValue()).booleanValue();
    }

    public final List<String> getAppFeatureCacheList() {
        return appFeatureList;
    }

    public final int getGetOplusSuperVolumeLevel() {
        return ((Number) getOplusSuperVolumeLevel$delegate.getValue()).intValue();
    }

    public final boolean getSFlavorOneMultiMediaDevice() {
        return ((Boolean) sFlavorOneMultiMediaDevice$delegate.getValue()).booleanValue();
    }

    public final String getVolumeThreekeyLocation() {
        return (String) volumeThreekeyLocation$delegate.getValue();
    }

    public final String isOplusVolumeThreeKeyLocation() {
        return (String) isOplusVolumeThreeKeyLocation$delegate.getValue();
    }

    public final void loadAppFeature(Context context) {
        sIsSupportStealthMode = OplusFeatureConfigManager.getInstance().hasFeature(FEATURE_KEY_IS_SUPPORT_STEALTH_MODE);
        IS_VOLUME_THREEKEY_LOCATION = SystemProperties.get("persist.oplus.software.alertslider.location", "");
        sIsVolumeDynamicBlurEnable = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), FEATURE_ENABLE_VOLUME_DYNAMIC_BLUR, false);
    }
}

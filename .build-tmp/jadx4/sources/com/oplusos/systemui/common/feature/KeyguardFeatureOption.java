package com.oplusos.systemui.common.feature;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.os.customize.OplusCustomizeRestrictionManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import androidx.appcompat.widget.ListPopupWindow$;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplus.coreapp.appfeature.AppFeatureProviderUtils;
import com.oplus.keyguard.util.FeatureOption$isCompactWindowMode$2$;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.transition.OplusAnimationController;
import com.oplus.transition.ShellContinuousTransitionUtils;
import com.oplus.util.OplusPlatformLevelUtils;
import com.oplus.zoom.common.zoomparameter.SmallScreenZoomParameter;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import vendor.oplus.hardware.displaypanelfeature.IDisplayPanelFeature;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardFeatureOption {
    private static final String BOLD_SCREENHOLE_BORDER = "ro.oplus.screenhole.border";
    private static final String DEFAUTL_PICTORIAL_NAME = "com.heytap.pictorial";
    private static final String DEGUB_DISPLAY_REFRESH_RATE_INFO = "persist.sys.oplus.aod.display.debug_refresh_rate";
    private static final String DISPLAY_BRIGHTNESS_MODE = "persist.sys.oplus.display.brightness.mode";
    private static final String EXTEND_BRIGHTNESS_MODE = "persist.sys.extend.brightness";
    private static final String FACE_UNLOCK_SUPPORT = "android.hardware.biometrics.face";
    private static final String FEATURE_FPS_SWITCH = "oplus.software.display.dynamic_fps_switch";
    private static final String FINGERPRINT_DELAY_AUTH = "oplus.software.kgd.fp_delay_auth";
    private static final String FINGERPRINT_SENSOR_TYPE_OPTICAL = "optical";
    private static final String FINGERPRINT_SENSOR_TYPE_SIDE = "side";
    private static final String FINGERPRINT_SENSOR_TYPE_ULTRASONIC = "ultrasonic";
    private static final String HONGSHU_OS_VALUE = "light_base";
    private static final String IS_EXPONENTIAL_BRIGHTNESS_MODE = "oplus.exponential.brightness.mode";
    private static final String IS_HONGSHU_OS = "ro.oplus.os_type";
    private static final String IS_LOW_RAW = "ro.vendor.config.oplus.low_ram";
    private static final String IS_QUICK_LAUNCH_HIDE = "touchpanel.fingerprintquickstart.hide";
    private static final String IS_SUPPORT_FINGERPRINT_FEATURE = "android.hardware.fingerprint";
    private static final String KEY_FINGERPRINT_SENSOR_TYPE = "persist.vendor.fingerprint.sensor_type";
    private static final String KEY_FINGERPRINT_SIDE_NEAR_TYPE = "persist.vendor.side.fp.near.feature.support";
    private static final String OPLUS_COLORMODE_DISABLE_SWITCH = "ro.oplus.display.colormode.systemui_disable_switch";
    private static final String OPLUS_DISPLAY_RESOLUION_SWITCH_SUPPORT = "oplus.software.display.resolution_switch_support";
    private static final String OPLUS_FACE_FILL_LIGHT_DISABLE_ANIMATION = "oplus.software.display.ui_disable_fill_light_animation";
    private static final String OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM = "oplus.software.adaptive_smooth_animation";
    private static final String OPLUS_FINGERPRINT_FACE_COMBINE_UNLOCK = "oplus.software.fingerprint_face_combine_unlock";
    private static final String OPLUS_FP_BLIND_UNLOCK_FEATURE = "oplus.hardware.fingerprint.unlock_without_icon.support";
    private static final String OPLUS_GLANCE_PKG = "com.glance.lockscreenOp";
    private static final String OPLUS_IRIS_DISABLE_SWITCH = "ro.oplus.display.iris_systemui_disable_switch";
    private static final String OPLUS_KEYGUARD_PICTORIAL_DISABLE_FEATURE = "oplus.keyguard.disable.pictorial";
    private static final String OPLUS_MURA_ENHANCE_BRIGHTNESS_SUPPORT = "oplus.software.display.mura_enhance_brightness_support";
    private static final String OPLUS_PROXIMITY_TP_SUPPORT = "oplus.proximity.tp.support";
    private static final String OPLUS_RESUME_ON_REBOOT = "oplus.hardware.resume_on_reboot";
    private static final String OPLUS_RLM_REGION_NAME = "com.android.launcher.REGION_NAME";
    private static final String OPLUS_SCREENHOLE_POSITION = "ro.oplus.display.screenhole.positon";
    private static final String OPLUS_SCREEN_MULTIBRIGHTNESS = "sys.oplus.multibrightness";
    private static final String OPLUS_SECONDARY_SCREENHOLE_POSITION = "ro.oplus.display.secondary.screenhole.position";
    private static final String OPLUS_SEPARATE_WALLPAPER_FOR_MULTI_DISPLAY = "oplus.software.wallpaper.separate_wallpaper_for_multi_display";
    private static final String OPLUS_SUPPORT_WCG_MODE = "oplus.software.display.wcg_2.0_support";
    private static final String RM_SCREENHOLE_NOTCH_SUPPORT = "ro.oplus.display.notch_support";
    private static final String SAVE_SP_TO_TEE_SUCCESS = "persist.sys.oplus.save_sp";
    private static final String SERVICE_NAME = "vendor.oplus.hardware.displaypanelfeature.IDisplayPanelFeature/default";
    private static final String TAG = "FeatureOption";
    private static final String UNLOCK_ANIMATION_EXIT_FAST = "oplus.software.unlock.animation_exit_fast";
    private static Context aplicationContext = null;
    private static final Lazy boldPinholeFrontCameraBorder$delegate;
    private static String glPictorialAgreementUrl = "";
    private static String glPictorialPolicyUrl = "";
    private static final Lazy is8BitScreen$delegate;
    private static final Lazy isAONHardwareSupport$delegate;
    private static final Lazy isBoldPinholeFrontCamera$delegate;
    private static final Lazy isDebugRefreshRate$delegate;
    private static final Lazy isDisableAppDimLayer$delegate;
    public static boolean isDisableBiometricUnlockAnim = false;
    public static boolean isDisableBouncerBlurScrim = false;
    private static final Lazy isDisableColorModeSwitch$delegate;
    private static final Lazy isDisableFillLightAnim$delegate;
    private static final Lazy isDisableIrisSwitch$delegate;
    private static final Lazy isDisplayBrightnessMode$delegate;
    public static boolean isEnableBouncerWallpaperBlur = true;
    public static boolean isEnableSOAVFXInLightOS = false;
    private static final Lazy isExponentialBrightnessMode$delegate;
    private static final Lazy isExtendBrightnessMode$delegate;
    private static final Lazy isFingerprintDelayAuth$delegate;
    private static final Lazy isFingerprintFaceCombineUnlock$delegate;
    public static boolean isFpBlindUnlockEnabled = true;
    private static final Lazy isHighFramePlatform$delegate;
    public static boolean isKeepHeyTapPictorial = false;
    public static boolean isKeyguardDisableShowSideFPTip = false;
    public static boolean isKeyguardDisableWallpaperZoom = false;
    private static final Lazy isLargeScreenFolding$delegate;
    private static final Lazy isLightBase$delegate;
    private static final Lazy isLocalHBM$delegate;
    private static final Lazy isLocalHBMAccel$delegate;
    private static final Lazy isLowRamSize$delegate;
    public static boolean isMotionOptimizationEnable = false;
    public static boolean isNotUseCameraMotorUpAnimation = false;
    public static boolean isPasswordDialEnabled = true;
    public static boolean isPictorialDisabled = true;
    public static boolean isPreventModeDefaultOff = false;
    private static final Lazy isRmScreenHoleNotchSupport$delegate;
    private static final Lazy isSavedSP$delegate;
    private static final Lazy isScreenResolutionSwitchSupport$delegate;
    private static final Lazy isSideFpNear$delegate;
    private static final Lazy isSupportAdaptiveSmoothAnim$delegate;
    private static final Lazy isSupportBackTouchFingerprint$delegate;
    public static boolean isSupportCPF = false;
    private static final Lazy isSupportCameraFpShutter$delegate;
    private static final Lazy isSupportColorMode$delegate;
    private static final Lazy isSupportDoublePinholeFrontCameraAnim$delegate;
    private static final Lazy isSupportFaceNoPowerBlock$delegate;
    private static final Lazy isSupportFaceUnlock$delegate;
    private static final Lazy isSupportFingerprintQuickLaunch$delegate;
    private static final Lazy isSupportFpUnlock$delegate;
    private static final Lazy isSupportLocalHbmBrighten$delegate;
    public static boolean isSupportOplusGLPictorial = false;
    private static final Lazy isSupportOplusRoR$delegate;
    private static final Lazy isSupportOpticalFps$delegate;
    public static boolean isSupportOtherPictorial = false;
    private static final Lazy isSupportPinholeFrontCameraAnim$delegate;
    public static boolean isSupportPukCodeShow = false;
    private static final Lazy isSupportResolutionSwitch$delegate;
    private static final Lazy isSupportSeparateWallpaper$delegate;
    private static final Lazy isSupportSidefps$delegate;
    public static boolean isSupportSmartLock = false;
    private static final Lazy isSupportUdfps$delegate;
    private static final Lazy isSupportUltrasonicFps$delegate;
    public static boolean isSupportWaterRipple = false;
    public static boolean isSupportWhiteSwanShow = true;
    private static final Lazy isTablet$delegate;
    public static boolean isTabletSupportNFC = false;
    private static final Lazy isTpProximitySensor$delegate;
    private static final Lazy isUnlockAnimationExitFast$delegate;
    private static final Lazy multiBrightness$delegate;
    private static String otherPictorialName = "com.heytap.pictorial";
    private static final Lazy pinholeFrontCameraPosition$delegate;
    private static String rmRegionName = "";
    public static boolean sIsLehuaSwitchCorrected = false;
    public static int sKeyguardLightOsLevel = -1;
    public static int sPlatforAnimationLevel = -1;
    public static boolean sSupportDynamicPictorial;
    public static boolean sSupportSmartTouch;
    private static final Lazy sSupportWcgColorMode$delegate;
    private static final Lazy secondaryPinholeFrontCameraPosition$delegate;
    public static final KeyguardFeatureOption INSTANCE = new KeyguardFeatureOption();
    private static final String FEATURE_SUPPORT_CPF = "com.android.systemui.support_cpf";
    private static final String FEATURE_ENABLE_SOA_VFX_IN_LIGHT_OS = "com.android.systemui.enable_soa_vfx_in_light_os";
    private static final String IS_DISABLE_FP_BLIND_UNLOCK = "com.android.systemui.disable_fp_blind_unlock";
    private static final String NOT_USE_CAMERA_MOTOR_ANIMATION_STRING = "com.android.systemui.notuse_camera_motor_animation";
    private static final String DISABLE_BIOMETRIC_UNLOCK_ANIM_STRING = "com.android.systemui.disable_biometric_unlock_anim";
    private static final String OPLUS_RLM_OTHER_PICTORIAL_SUPPORT = "com.android.systemui.other_pictorial_support";
    private static final String SMART_LOCK_ENABLE_STRING = "com.android.systemui.smartlock_enable";
    private static final String PUK_CODE_SHOW_STRING = "com.android.systemui.puk_code_show";
    private static final String FEATURE_LEHUA_SWITCH_CORRECTED = "com.android.systemui.lehua_switch_corrected";
    private static final String FEATURE_SUPPORT_DYNAMIC_PICTORIAL = "com.android.systemui.pictorial_dynamic_wallpaper";
    private static final String FEATURE_KEEP_HEYTAP_PICTORIAL = "com.android.systemui.keep_heytap_pictorial";
    private static final String FEATURE_SUPPORT_SMART_TOUCH = "feature.super_settings_smart_touch.support";
    private static final String FEATURE_PREVENT_MODE_DEFAULT_OFF = "com.android.systemui.prevent_mode_default_off";
    private static final String FEATURE_TABLET_SUPPORT_NFC = "oplus.ptc.nfc.settings";
    private static final String FEATURE_SMART_CHARGE_MODE = "com.oplus.battery.smart.charge.mode";
    private static final String FEATURE_KEYGUARD_NEW_ANIM_DISABLE = "com.android.systemui.kgd.new_anim.disable";
    private static final String SUPPORT_UPGRADE_ANIM_WATERRIPPLE = "com.android.systemui.support_upgrade_anim_waterripple";
    private static final String FEATURE_LIGHT_OS_LEVEL = "com.android.systemui.kg_light_os_level";
    private static final String FEATURE_KEYGUARD_DISABLE_ZOOM_WALLPAPER = "com.oplus.systemui.kg_disable_zoom_wallpaper";
    private static final String OPLUS_RLM_OTHER_PICTORIAL_GL_AGREEMENT_URL = "com.coloros.bootreg_new_glance_agreements";
    private static final String OPLUS_RLM_OTHER_PICTORIAL_GL_PRIVACY_URL = "com.coloros.bootreg_new_glance_privacy_policy";
    private static final String DISABLE_BOUNCER_BLUR_SCRIM = "com.android.systemui.disable_bouncer_blur_scrim";
    private static final String FEATURE_SUPPORT_STYLE_WIDGET = "com.android.systemui.support_keyguard_widget";
    private static final String FEATURE_SUPPORT_STYLE_BIG_CLOCK = "com.oplus.wallpapers.big_clock_theme";
    private static final String FEATURE_IP_CUSTOMIZE_DEVICE = "com.android.systemui.ip_customize_device";
    private static final String ENABLE_BOUNCER_WALLPAPER_BLUR = "com.android.systemui.enable_bouncer_wallpaper_blur";
    private static final String FEATURE_SUPPORT_WHITE_SWAN_SHOW = "com.android.systemui.whiteswan_show";
    private static final String FEATURE_KEYGUARD_DISABLE_SIDE_FP_SHOW_TIP = "com.android.systemui.kg_disable_side_fp_show_tip";
    private static final String FEATURE_PASSWORD_DIAL_ENABLE = "com.oplus.systemui.keyguard_effect_light_enable";
    private static final List<String> appFeatureList = CollectionsKt__CollectionsKt.listOf(new String[]{FEATURE_SUPPORT_CPF, FEATURE_ENABLE_SOA_VFX_IN_LIGHT_OS, IS_DISABLE_FP_BLIND_UNLOCK, NOT_USE_CAMERA_MOTOR_ANIMATION_STRING, DISABLE_BIOMETRIC_UNLOCK_ANIM_STRING, OPLUS_RLM_OTHER_PICTORIAL_SUPPORT, SMART_LOCK_ENABLE_STRING, PUK_CODE_SHOW_STRING, FEATURE_LEHUA_SWITCH_CORRECTED, FEATURE_SUPPORT_DYNAMIC_PICTORIAL, FEATURE_KEEP_HEYTAP_PICTORIAL, FEATURE_SUPPORT_SMART_TOUCH, FEATURE_PREVENT_MODE_DEFAULT_OFF, FEATURE_TABLET_SUPPORT_NFC, FEATURE_SMART_CHARGE_MODE, FEATURE_KEYGUARD_NEW_ANIM_DISABLE, SUPPORT_UPGRADE_ANIM_WATERRIPPLE, FEATURE_LIGHT_OS_LEVEL, FEATURE_KEYGUARD_DISABLE_ZOOM_WALLPAPER, OPLUS_RLM_OTHER_PICTORIAL_GL_AGREEMENT_URL, OPLUS_RLM_OTHER_PICTORIAL_GL_PRIVACY_URL, DISABLE_BOUNCER_BLUR_SCRIM, FEATURE_SUPPORT_STYLE_WIDGET, FEATURE_SUPPORT_STYLE_BIG_CLOCK, FEATURE_IP_CUSTOMIZE_DEVICE, ENABLE_BOUNCER_WALLPAPER_BLUR, FEATURE_SUPPORT_WHITE_SWAN_SHOW, FEATURE_KEYGUARD_DISABLE_SIDE_FP_SHOW_TIP, FEATURE_PASSWORD_DIAL_ENABLE});

    public static boolean $r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk() {
        List listEmptyList;
        List listEmptyList2;
        List listEmptyList3;
        KeyguardFeatureOption keyguardFeatureOption = INSTANCE;
        if (TextUtils.isEmpty(keyguardFeatureOption.getPinholeFrontCameraPosition())) {
            return false;
        }
        try {
            List listSplit = new Regex(":").split(keyguardFeatureOption.getPinholeFrontCameraPosition(), 0);
            if (!listSplit.isEmpty()) {
                ListIterator listIterator = listSplit.listIterator(listSplit.size());
                while (true) {
                    if (!listIterator.hasPrevious()) {
                        listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                        break;
                    }
                    if (((String) listIterator.previous()).length() != 0) {
                        listEmptyList = CollectionsKt___CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                        break;
                    }
                }
            } else {
                listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                break;
            }
            String[] strArr = (String[]) listEmptyList.toArray(new String[0]);
            List listSplit2 = new Regex(OplusPreventModeData.SEPARATOR).split(strArr[0], 0);
            if (!listSplit2.isEmpty()) {
                ListIterator listIterator2 = listSplit2.listIterator(listSplit2.size());
                while (true) {
                    if (!listIterator2.hasPrevious()) {
                        listEmptyList2 = CollectionsKt__CollectionsKt.emptyList();
                        break;
                    }
                    if (((String) listIterator2.previous()).length() != 0) {
                        listEmptyList2 = CollectionsKt___CollectionsKt.take(listSplit2, listIterator2.nextIndex() + 1);
                        break;
                    }
                }
            } else {
                listEmptyList2 = CollectionsKt__CollectionsKt.emptyList();
                break;
            }
            String[] strArr2 = (String[]) listEmptyList2.toArray(new String[0]);
            List listSplit3 = new Regex(OplusPreventModeData.SEPARATOR).split(strArr[1], 0);
            if (!listSplit3.isEmpty()) {
                ListIterator listIterator3 = listSplit3.listIterator(listSplit3.size());
                while (true) {
                    if (!listIterator3.hasPrevious()) {
                        listEmptyList3 = CollectionsKt__CollectionsKt.emptyList();
                        break;
                    }
                    if (((String) listIterator3.previous()).length() != 0) {
                        listEmptyList3 = CollectionsKt___CollectionsKt.take(listSplit3, listIterator3.nextIndex() + 1);
                        break;
                    }
                }
            } else {
                listEmptyList3 = CollectionsKt__CollectionsKt.emptyList();
                break;
            }
            String[] strArr3 = (String[]) listEmptyList3.toArray(new String[0]);
            return Integer.parseInt(strArr3[0]) - Integer.parseInt(strArr2[0]) > Integer.parseInt(strArr3[1]) - Integer.parseInt(strArr2[1]);
        } catch (Exception unused) {
            if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                return false;
            }
            Log.d("SystemUi--Keyguard", "FeatureOption-->get positon fails");
            return false;
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$2qN2uMlt22o4gR_BAXFhtW-kDcg, reason: not valid java name */
    public static boolean m618$r8$lambda$2qN2uMlt22o4gR_BAXFhtWkDcg() {
        Context context = aplicationContext;
        if (context != null) {
            return context.getPackageManager().hasSystemFeature(FACE_UNLOCK_SUPPORT);
        }
        return false;
    }

    public static boolean $r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI() {
        List listEmptyList;
        List listEmptyList2;
        List listEmptyList3;
        KeyguardFeatureOption keyguardFeatureOption = INSTANCE;
        if (TextUtils.isEmpty(keyguardFeatureOption.getPinholeFrontCameraPosition())) {
            return false;
        }
        try {
            List listSplit = new Regex(":").split(keyguardFeatureOption.getPinholeFrontCameraPosition(), 0);
            if (!listSplit.isEmpty()) {
                ListIterator listIterator = listSplit.listIterator(listSplit.size());
                while (true) {
                    if (!listIterator.hasPrevious()) {
                        listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                        break;
                    }
                    if (((String) listIterator.previous()).length() != 0) {
                        listEmptyList = CollectionsKt___CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                        break;
                    }
                }
            } else {
                listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                break;
            }
            String[] strArr = (String[]) listEmptyList.toArray(new String[0]);
            List listSplit2 = new Regex(OplusPreventModeData.SEPARATOR).split(strArr[0], 0);
            if (!listSplit2.isEmpty()) {
                ListIterator listIterator2 = listSplit2.listIterator(listSplit2.size());
                while (true) {
                    if (!listIterator2.hasPrevious()) {
                        listEmptyList2 = CollectionsKt__CollectionsKt.emptyList();
                        break;
                    }
                    if (((String) listIterator2.previous()).length() != 0) {
                        listEmptyList2 = CollectionsKt___CollectionsKt.take(listSplit2, listIterator2.nextIndex() + 1);
                        break;
                    }
                }
            } else {
                listEmptyList2 = CollectionsKt__CollectionsKt.emptyList();
                break;
            }
            String[] strArr2 = (String[]) listEmptyList2.toArray(new String[0]);
            List listSplit3 = new Regex(OplusPreventModeData.SEPARATOR).split(strArr[1], 0);
            if (!listSplit3.isEmpty()) {
                ListIterator listIterator3 = listSplit3.listIterator(listSplit3.size());
                while (true) {
                    if (!listIterator3.hasPrevious()) {
                        listEmptyList3 = CollectionsKt__CollectionsKt.emptyList();
                        break;
                    }
                    if (((String) listIterator3.previous()).length() != 0) {
                        listEmptyList3 = CollectionsKt___CollectionsKt.take(listSplit3, listIterator3.nextIndex() + 1);
                        break;
                    }
                }
            } else {
                listEmptyList3 = CollectionsKt__CollectionsKt.emptyList();
                break;
            }
            String[] strArr3 = (String[]) listEmptyList3.toArray(new String[0]);
            return Integer.parseInt(strArr3[0]) - Integer.parseInt(strArr2[0]) == Integer.parseInt(strArr3[1]) - Integer.parseInt(strArr2[1]);
        } catch (Exception unused) {
            if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                return false;
            }
            Log.d("SystemUi--Keyguard", "FeatureOption-->get positon fails");
            return false;
        }
    }

    public static boolean $r8$lambda$IdQhTz2IUxhl8QTqu6qW_E99r3k() {
        Context context = aplicationContext;
        if (context != null) {
            return context.getPackageManager().hasSystemFeature(IS_SUPPORT_FINGERPRINT_FEATURE);
        }
        return false;
    }

    /* JADX INFO: renamed from: $r8$lambda$Pd2QiML-oi8HzlCTayMwwltv5_o, reason: not valid java name */
    public static boolean m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o() {
        Context context = aplicationContext;
        if (context != null) {
            return context.getPackageManager().hasSystemFeature(OPLUS_RESUME_ON_REBOOT);
        }
        return false;
    }

    /* JADX INFO: renamed from: $r8$lambda$R-cO4zVBK5PzBtdKEUJTsopCJdI, reason: not valid java name */
    public static boolean m620$r8$lambda$RcO4zVBK5PzBtdKEUJTsopCJdI() {
        int i;
        Object obj;
        int i2;
        try {
            IDisplayPanelFeature aidlService = INSTANCE.getAidlService();
            int[] iArr = new int[1];
            if (aidlService != null) {
                aidlService.getDisplayPanelFeatureValue(69, iArr);
            }
            i2 = iArr[0];
            if (i2 == 0) {
                i2 = getDisplayPanelFeatureHidl()[0];
            }
            try {
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                i = i2;
                th = th;
                int i3 = i;
                obj = Result.constructor-impl(ResultKt.createFailure(th));
                i2 = i3;
            }
        } catch (Throwable th2) {
            th = th2;
            i = 0;
        }
        Throwable th3 = Result.exceptionOrNull-impl(obj);
        if (th3 != null) {
            LogUtil.e$default(12, TAG, "is8BitScreen getAidlService error: " + th3.getMessage(), null);
        }
        LogUtil.i$default(LogUtil.TAG_KEYGUARD, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i2, "is8BitScreen bitType: "), null, 8);
        return i2 == 8;
    }

    public static boolean $r8$lambda$jqGfNSX7yAtk2OBVNxvJsZ1wPxU() {
        Context context = aplicationContext;
        if (context == null) {
            return false;
        }
        INSTANCE.getClass();
        return (!isSupportUdfps() || context.getPackageManager().hasSystemFeature(IS_QUICK_LAUNCH_HIDE) || OplusCustomizeRestrictionManager.getInstance(context).getUnlockByFingerprintPolicies((ComponentName) null) == 0) ? false : true;
    }

    public static boolean $r8$lambda$kNgI5MCLF5mL4xftI3aTv3xLMyA() {
        Context context = aplicationContext;
        DisplayManager displayManager = context != null ? (DisplayManager) context.getSystemService(DisplayManager.class) : null;
        if (displayManager != null) {
            Display.Mode[] supportedModes = displayManager.getDisplay(0).getSupportedModes();
            if (supportedModes != null && supportedModes.length >= 2) {
                int physicalWidth = supportedModes[0].getPhysicalWidth();
                Iterator it = ArrayIteratorKt.iterator(supportedModes);
                while (it.hasNext()) {
                    if (((Display.Mode) it.next()).getPhysicalWidth() != physicalWidth) {
                        if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                            return true;
                        }
                        Log.d("SystemUi--Keyguard", "FeatureOption-->support resolution switch");
                        return true;
                    }
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                    Log.d("SystemUi--Keyguard", "FeatureOption-->don't support resolution switch");
                    return false;
                }
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "FeatureOption-->don't support resolution switch no enough mode");
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: $r8$lambda$keGXMvxoGXLQAqKC8lCDxbG-uGc, reason: not valid java name */
    public static boolean m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc() {
        Context context = aplicationContext;
        if (context != null) {
            return context.getPackageManager().hasSystemFeature(OPLUS_SUPPORT_WCG_MODE);
        }
        return false;
    }

    public static boolean $r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU() {
        Context context = aplicationContext;
        if (context != null) {
            return context.getPackageManager().hasSystemFeature(OPLUS_PROXIMITY_TP_SUPPORT);
        }
        return false;
    }

    public static boolean $r8$lambda$vh89e6iVJtakstICHi7amzY1Lt0() {
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        int i = 8;
        try {
            IDisplayPanelFeature aidlService = INSTANCE.getAidlService();
            if (aidlService != null) {
                int[] iArr = new int[1];
                aidlService.getDisplayPanelFeatureValue(211, iArr);
                intRef.element = iArr[0];
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                    Log.d("SystemUi--Keyguard", "FeatureOption-->".concat("aidl sOnScreenFingerprintType=" + intRef.element));
                }
            } else {
                vendor.oplus.hardware.displaypanelfeature.V1_0.IDisplayPanelFeature.Proxy service = vendor.oplus.hardware.displaypanelfeature.V1_0.IDisplayPanelFeature.getService();
                if (service != null) {
                    getUdfpsType.1 r2 = new getUdfpsType.1(1);
                    r2.$udfpsType = intRef;
                    VarHandle.storeStoreFence();
                    service.getDisplayPanelFeatureValue(211, r2);
                }
            }
        } catch (Exception e) {
            LogUtil.w$default(8, LogUtil.TAG_KEYGUARD, TAG, "Read FP IDisplayPanelFeature exception : " + e);
        }
        if (intRef.element == -1) {
            INSTANCE.getClass();
            if (!isSupportUdfps()) {
                i = 2;
            } else if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.display.fingerprint_disable_dimlayer")) {
                i = 4;
            }
            intRef.element = i;
        }
        return intRef.element == 4;
    }

    public static boolean $r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo() {
        Context context = aplicationContext;
        if (context != null) {
            return context.getPackageManager().hasSystemFeature(IS_EXPONENTIAL_BRIGHTNESS_MODE);
        }
        return false;
    }

    static {
        final int i = 0;
        isSupportOplusRoR$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i2 = 2;
        isTpProximitySensor$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i2) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i3 = 14;
        isHighFramePlatform$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i3) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i4 = 26;
        isFingerprintDelayAuth$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i4) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i5 = 7;
        isUnlockAnimationExitFast$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                boolean z = false;
                switch (i5) {
                    case 0:
                        int udfpsType = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType != -1 && (udfpsType & 16) != 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet());
                    case 2:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isLargeScreenFolding());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m(ShellContinuousTransitionUtils.OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM);
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.aod.display.debug_refresh_rate", "0").equals("1"));
                    case 5:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.os_type", ""), "light_base"));
                    case 6:
                        return Boolean.valueOf(StringsKt__StringsJVMKt.equals(SystemProperties.get("ro.vendor.config.oplus.low_ram", "false"), "true", true));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.unlock.animation_exit_fast");
                    case 8:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$kNgI5MCLF5mL4xftI3aTv3xLMyA());
                    case 9:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$IdQhTz2IUxhl8QTqu6qW_E99r3k());
                    case 10:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && (SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical") || SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic"))) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 12:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 13:
                        int udfpsType2 = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType2 != -1) {
                            z = ((udfpsType2 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0) & ((udfpsType2 & 16) != 0);
                        }
                        return Boolean.valueOf(z);
                    case 14:
                        return Boolean.valueOf(KeyguardFeatureOption.m620$r8$lambda$RcO4zVBK5PzBtdKEUJTsopCJdI());
                    case 15:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vh89e6iVJtakstICHi7amzY1Lt0());
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$jqGfNSX7yAtk2OBVNxvJsZ1wPxU());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.m618$r8$lambda$2qN2uMlt22o4gR_BAXFhtWkDcg());
                    default:
                        return SystemProperties.get("sys.oplus.multibrightness", "");
                }
            }
        });
        final int i6 = 8;
        isSupportResolutionSwitch$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                boolean z = false;
                switch (i6) {
                    case 0:
                        int udfpsType = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType != -1 && (udfpsType & 16) != 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet());
                    case 2:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isLargeScreenFolding());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m(ShellContinuousTransitionUtils.OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM);
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.aod.display.debug_refresh_rate", "0").equals("1"));
                    case 5:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.os_type", ""), "light_base"));
                    case 6:
                        return Boolean.valueOf(StringsKt__StringsJVMKt.equals(SystemProperties.get("ro.vendor.config.oplus.low_ram", "false"), "true", true));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.unlock.animation_exit_fast");
                    case 8:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$kNgI5MCLF5mL4xftI3aTv3xLMyA());
                    case 9:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$IdQhTz2IUxhl8QTqu6qW_E99r3k());
                    case 10:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && (SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical") || SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic"))) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 12:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 13:
                        int udfpsType2 = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType2 != -1) {
                            z = ((udfpsType2 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0) & ((udfpsType2 & 16) != 0);
                        }
                        return Boolean.valueOf(z);
                    case 14:
                        return Boolean.valueOf(KeyguardFeatureOption.m620$r8$lambda$RcO4zVBK5PzBtdKEUJTsopCJdI());
                    case 15:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vh89e6iVJtakstICHi7amzY1Lt0());
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$jqGfNSX7yAtk2OBVNxvJsZ1wPxU());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.m618$r8$lambda$2qN2uMlt22o4gR_BAXFhtWkDcg());
                    default:
                        return SystemProperties.get("sys.oplus.multibrightness", "");
                }
            }
        });
        final int i7 = 9;
        isSupportFpUnlock$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                boolean z = false;
                switch (i7) {
                    case 0:
                        int udfpsType = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType != -1 && (udfpsType & 16) != 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet());
                    case 2:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isLargeScreenFolding());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m(ShellContinuousTransitionUtils.OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM);
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.aod.display.debug_refresh_rate", "0").equals("1"));
                    case 5:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.os_type", ""), "light_base"));
                    case 6:
                        return Boolean.valueOf(StringsKt__StringsJVMKt.equals(SystemProperties.get("ro.vendor.config.oplus.low_ram", "false"), "true", true));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.unlock.animation_exit_fast");
                    case 8:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$kNgI5MCLF5mL4xftI3aTv3xLMyA());
                    case 9:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$IdQhTz2IUxhl8QTqu6qW_E99r3k());
                    case 10:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && (SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical") || SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic"))) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 12:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 13:
                        int udfpsType2 = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType2 != -1) {
                            z = ((udfpsType2 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0) & ((udfpsType2 & 16) != 0);
                        }
                        return Boolean.valueOf(z);
                    case 14:
                        return Boolean.valueOf(KeyguardFeatureOption.m620$r8$lambda$RcO4zVBK5PzBtdKEUJTsopCJdI());
                    case 15:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vh89e6iVJtakstICHi7amzY1Lt0());
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$jqGfNSX7yAtk2OBVNxvJsZ1wPxU());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.m618$r8$lambda$2qN2uMlt22o4gR_BAXFhtWkDcg());
                    default:
                        return SystemProperties.get("sys.oplus.multibrightness", "");
                }
            }
        });
        final int i8 = 10;
        isSupportUdfps$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                boolean z = false;
                switch (i8) {
                    case 0:
                        int udfpsType = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType != -1 && (udfpsType & 16) != 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet());
                    case 2:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isLargeScreenFolding());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m(ShellContinuousTransitionUtils.OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM);
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.aod.display.debug_refresh_rate", "0").equals("1"));
                    case 5:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.os_type", ""), "light_base"));
                    case 6:
                        return Boolean.valueOf(StringsKt__StringsJVMKt.equals(SystemProperties.get("ro.vendor.config.oplus.low_ram", "false"), "true", true));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.unlock.animation_exit_fast");
                    case 8:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$kNgI5MCLF5mL4xftI3aTv3xLMyA());
                    case 9:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$IdQhTz2IUxhl8QTqu6qW_E99r3k());
                    case 10:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && (SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical") || SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic"))) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 12:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 13:
                        int udfpsType2 = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType2 != -1) {
                            z = ((udfpsType2 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0) & ((udfpsType2 & 16) != 0);
                        }
                        return Boolean.valueOf(z);
                    case 14:
                        return Boolean.valueOf(KeyguardFeatureOption.m620$r8$lambda$RcO4zVBK5PzBtdKEUJTsopCJdI());
                    case 15:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vh89e6iVJtakstICHi7amzY1Lt0());
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$jqGfNSX7yAtk2OBVNxvJsZ1wPxU());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.m618$r8$lambda$2qN2uMlt22o4gR_BAXFhtWkDcg());
                    default:
                        return SystemProperties.get("sys.oplus.multibrightness", "");
                }
            }
        });
        final int i9 = 11;
        isSupportOpticalFps$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                boolean z = false;
                switch (i9) {
                    case 0:
                        int udfpsType = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType != -1 && (udfpsType & 16) != 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet());
                    case 2:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isLargeScreenFolding());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m(ShellContinuousTransitionUtils.OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM);
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.aod.display.debug_refresh_rate", "0").equals("1"));
                    case 5:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.os_type", ""), "light_base"));
                    case 6:
                        return Boolean.valueOf(StringsKt__StringsJVMKt.equals(SystemProperties.get("ro.vendor.config.oplus.low_ram", "false"), "true", true));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.unlock.animation_exit_fast");
                    case 8:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$kNgI5MCLF5mL4xftI3aTv3xLMyA());
                    case 9:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$IdQhTz2IUxhl8QTqu6qW_E99r3k());
                    case 10:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && (SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical") || SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic"))) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 12:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 13:
                        int udfpsType2 = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType2 != -1) {
                            z = ((udfpsType2 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0) & ((udfpsType2 & 16) != 0);
                        }
                        return Boolean.valueOf(z);
                    case 14:
                        return Boolean.valueOf(KeyguardFeatureOption.m620$r8$lambda$RcO4zVBK5PzBtdKEUJTsopCJdI());
                    case 15:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vh89e6iVJtakstICHi7amzY1Lt0());
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$jqGfNSX7yAtk2OBVNxvJsZ1wPxU());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.m618$r8$lambda$2qN2uMlt22o4gR_BAXFhtWkDcg());
                    default:
                        return SystemProperties.get("sys.oplus.multibrightness", "");
                }
            }
        });
        final int i10 = 12;
        isSupportUltrasonicFps$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                boolean z = false;
                switch (i10) {
                    case 0:
                        int udfpsType = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType != -1 && (udfpsType & 16) != 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet());
                    case 2:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isLargeScreenFolding());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m(ShellContinuousTransitionUtils.OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM);
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.aod.display.debug_refresh_rate", "0").equals("1"));
                    case 5:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.os_type", ""), "light_base"));
                    case 6:
                        return Boolean.valueOf(StringsKt__StringsJVMKt.equals(SystemProperties.get("ro.vendor.config.oplus.low_ram", "false"), "true", true));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.unlock.animation_exit_fast");
                    case 8:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$kNgI5MCLF5mL4xftI3aTv3xLMyA());
                    case 9:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$IdQhTz2IUxhl8QTqu6qW_E99r3k());
                    case 10:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && (SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical") || SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic"))) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 12:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 13:
                        int udfpsType2 = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType2 != -1) {
                            z = ((udfpsType2 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0) & ((udfpsType2 & 16) != 0);
                        }
                        return Boolean.valueOf(z);
                    case 14:
                        return Boolean.valueOf(KeyguardFeatureOption.m620$r8$lambda$RcO4zVBK5PzBtdKEUJTsopCJdI());
                    case 15:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vh89e6iVJtakstICHi7amzY1Lt0());
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$jqGfNSX7yAtk2OBVNxvJsZ1wPxU());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.m618$r8$lambda$2qN2uMlt22o4gR_BAXFhtWkDcg());
                    default:
                        return SystemProperties.get("sys.oplus.multibrightness", "");
                }
            }
        });
        final int i11 = 11;
        isSupportSidefps$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i11) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i12 = 22;
        isSupportBackTouchFingerprint$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i12) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i13 = 0;
        isLocalHBM$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                boolean z = false;
                switch (i13) {
                    case 0:
                        int udfpsType = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType != -1 && (udfpsType & 16) != 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet());
                    case 2:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isLargeScreenFolding());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m(ShellContinuousTransitionUtils.OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM);
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.aod.display.debug_refresh_rate", "0").equals("1"));
                    case 5:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.os_type", ""), "light_base"));
                    case 6:
                        return Boolean.valueOf(StringsKt__StringsJVMKt.equals(SystemProperties.get("ro.vendor.config.oplus.low_ram", "false"), "true", true));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.unlock.animation_exit_fast");
                    case 8:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$kNgI5MCLF5mL4xftI3aTv3xLMyA());
                    case 9:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$IdQhTz2IUxhl8QTqu6qW_E99r3k());
                    case 10:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && (SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical") || SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic"))) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 12:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 13:
                        int udfpsType2 = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType2 != -1) {
                            z = ((udfpsType2 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0) & ((udfpsType2 & 16) != 0);
                        }
                        return Boolean.valueOf(z);
                    case 14:
                        return Boolean.valueOf(KeyguardFeatureOption.m620$r8$lambda$RcO4zVBK5PzBtdKEUJTsopCJdI());
                    case 15:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vh89e6iVJtakstICHi7amzY1Lt0());
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$jqGfNSX7yAtk2OBVNxvJsZ1wPxU());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.m618$r8$lambda$2qN2uMlt22o4gR_BAXFhtWkDcg());
                    default:
                        return SystemProperties.get("sys.oplus.multibrightness", "");
                }
            }
        });
        final int i14 = 13;
        isLocalHBMAccel$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                boolean z = false;
                switch (i14) {
                    case 0:
                        int udfpsType = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType != -1 && (udfpsType & 16) != 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet());
                    case 2:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isLargeScreenFolding());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m(ShellContinuousTransitionUtils.OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM);
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.aod.display.debug_refresh_rate", "0").equals("1"));
                    case 5:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.os_type", ""), "light_base"));
                    case 6:
                        return Boolean.valueOf(StringsKt__StringsJVMKt.equals(SystemProperties.get("ro.vendor.config.oplus.low_ram", "false"), "true", true));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.unlock.animation_exit_fast");
                    case 8:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$kNgI5MCLF5mL4xftI3aTv3xLMyA());
                    case 9:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$IdQhTz2IUxhl8QTqu6qW_E99r3k());
                    case 10:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && (SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical") || SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic"))) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 12:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 13:
                        int udfpsType2 = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType2 != -1) {
                            z = ((udfpsType2 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0) & ((udfpsType2 & 16) != 0);
                        }
                        return Boolean.valueOf(z);
                    case 14:
                        return Boolean.valueOf(KeyguardFeatureOption.m620$r8$lambda$RcO4zVBK5PzBtdKEUJTsopCJdI());
                    case 15:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vh89e6iVJtakstICHi7amzY1Lt0());
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$jqGfNSX7yAtk2OBVNxvJsZ1wPxU());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.m618$r8$lambda$2qN2uMlt22o4gR_BAXFhtWkDcg());
                    default:
                        return SystemProperties.get("sys.oplus.multibrightness", "");
                }
            }
        });
        final int i15 = 14;
        is8BitScreen$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                boolean z = false;
                switch (i15) {
                    case 0:
                        int udfpsType = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType != -1 && (udfpsType & 16) != 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet());
                    case 2:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isLargeScreenFolding());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m(ShellContinuousTransitionUtils.OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM);
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.aod.display.debug_refresh_rate", "0").equals("1"));
                    case 5:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.os_type", ""), "light_base"));
                    case 6:
                        return Boolean.valueOf(StringsKt__StringsJVMKt.equals(SystemProperties.get("ro.vendor.config.oplus.low_ram", "false"), "true", true));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.unlock.animation_exit_fast");
                    case 8:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$kNgI5MCLF5mL4xftI3aTv3xLMyA());
                    case 9:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$IdQhTz2IUxhl8QTqu6qW_E99r3k());
                    case 10:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && (SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical") || SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic"))) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 12:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 13:
                        int udfpsType2 = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType2 != -1) {
                            z = ((udfpsType2 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0) & ((udfpsType2 & 16) != 0);
                        }
                        return Boolean.valueOf(z);
                    case 14:
                        return Boolean.valueOf(KeyguardFeatureOption.m620$r8$lambda$RcO4zVBK5PzBtdKEUJTsopCJdI());
                    case 15:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vh89e6iVJtakstICHi7amzY1Lt0());
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$jqGfNSX7yAtk2OBVNxvJsZ1wPxU());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.m618$r8$lambda$2qN2uMlt22o4gR_BAXFhtWkDcg());
                    default:
                        return SystemProperties.get("sys.oplus.multibrightness", "");
                }
            }
        });
        final int i16 = 15;
        isDisableAppDimLayer$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                boolean z = false;
                switch (i16) {
                    case 0:
                        int udfpsType = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType != -1 && (udfpsType & 16) != 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet());
                    case 2:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isLargeScreenFolding());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m(ShellContinuousTransitionUtils.OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM);
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.aod.display.debug_refresh_rate", "0").equals("1"));
                    case 5:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.os_type", ""), "light_base"));
                    case 6:
                        return Boolean.valueOf(StringsKt__StringsJVMKt.equals(SystemProperties.get("ro.vendor.config.oplus.low_ram", "false"), "true", true));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.unlock.animation_exit_fast");
                    case 8:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$kNgI5MCLF5mL4xftI3aTv3xLMyA());
                    case 9:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$IdQhTz2IUxhl8QTqu6qW_E99r3k());
                    case 10:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && (SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical") || SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic"))) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 12:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 13:
                        int udfpsType2 = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType2 != -1) {
                            z = ((udfpsType2 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0) & ((udfpsType2 & 16) != 0);
                        }
                        return Boolean.valueOf(z);
                    case 14:
                        return Boolean.valueOf(KeyguardFeatureOption.m620$r8$lambda$RcO4zVBK5PzBtdKEUJTsopCJdI());
                    case 15:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vh89e6iVJtakstICHi7amzY1Lt0());
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$jqGfNSX7yAtk2OBVNxvJsZ1wPxU());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.m618$r8$lambda$2qN2uMlt22o4gR_BAXFhtWkDcg());
                    default:
                        return SystemProperties.get("sys.oplus.multibrightness", "");
                }
            }
        });
        final int i17 = 16;
        isSupportFingerprintQuickLaunch$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                boolean z = false;
                switch (i17) {
                    case 0:
                        int udfpsType = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType != -1 && (udfpsType & 16) != 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet());
                    case 2:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isLargeScreenFolding());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m(ShellContinuousTransitionUtils.OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM);
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.aod.display.debug_refresh_rate", "0").equals("1"));
                    case 5:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.os_type", ""), "light_base"));
                    case 6:
                        return Boolean.valueOf(StringsKt__StringsJVMKt.equals(SystemProperties.get("ro.vendor.config.oplus.low_ram", "false"), "true", true));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.unlock.animation_exit_fast");
                    case 8:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$kNgI5MCLF5mL4xftI3aTv3xLMyA());
                    case 9:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$IdQhTz2IUxhl8QTqu6qW_E99r3k());
                    case 10:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && (SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical") || SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic"))) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 12:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 13:
                        int udfpsType2 = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType2 != -1) {
                            z = ((udfpsType2 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0) & ((udfpsType2 & 16) != 0);
                        }
                        return Boolean.valueOf(z);
                    case 14:
                        return Boolean.valueOf(KeyguardFeatureOption.m620$r8$lambda$RcO4zVBK5PzBtdKEUJTsopCJdI());
                    case 15:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vh89e6iVJtakstICHi7amzY1Lt0());
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$jqGfNSX7yAtk2OBVNxvJsZ1wPxU());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.m618$r8$lambda$2qN2uMlt22o4gR_BAXFhtWkDcg());
                    default:
                        return SystemProperties.get("sys.oplus.multibrightness", "");
                }
            }
        });
        final int i18 = 17;
        isSupportFaceUnlock$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                boolean z = false;
                switch (i18) {
                    case 0:
                        int udfpsType = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType != -1 && (udfpsType & 16) != 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet());
                    case 2:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isLargeScreenFolding());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m(ShellContinuousTransitionUtils.OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM);
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.aod.display.debug_refresh_rate", "0").equals("1"));
                    case 5:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.os_type", ""), "light_base"));
                    case 6:
                        return Boolean.valueOf(StringsKt__StringsJVMKt.equals(SystemProperties.get("ro.vendor.config.oplus.low_ram", "false"), "true", true));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.unlock.animation_exit_fast");
                    case 8:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$kNgI5MCLF5mL4xftI3aTv3xLMyA());
                    case 9:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$IdQhTz2IUxhl8QTqu6qW_E99r3k());
                    case 10:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && (SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical") || SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic"))) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 12:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 13:
                        int udfpsType2 = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType2 != -1) {
                            z = ((udfpsType2 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0) & ((udfpsType2 & 16) != 0);
                        }
                        return Boolean.valueOf(z);
                    case 14:
                        return Boolean.valueOf(KeyguardFeatureOption.m620$r8$lambda$RcO4zVBK5PzBtdKEUJTsopCJdI());
                    case 15:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vh89e6iVJtakstICHi7amzY1Lt0());
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$jqGfNSX7yAtk2OBVNxvJsZ1wPxU());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.m618$r8$lambda$2qN2uMlt22o4gR_BAXFhtWkDcg());
                    default:
                        return SystemProperties.get("sys.oplus.multibrightness", "");
                }
            }
        });
        final int i19 = 18;
        multiBrightness$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                boolean z = false;
                switch (i19) {
                    case 0:
                        int udfpsType = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType != -1 && (udfpsType & 16) != 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet());
                    case 2:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isLargeScreenFolding());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m(ShellContinuousTransitionUtils.OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM);
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.aod.display.debug_refresh_rate", "0").equals("1"));
                    case 5:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.os_type", ""), "light_base"));
                    case 6:
                        return Boolean.valueOf(StringsKt__StringsJVMKt.equals(SystemProperties.get("ro.vendor.config.oplus.low_ram", "false"), "true", true));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.unlock.animation_exit_fast");
                    case 8:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$kNgI5MCLF5mL4xftI3aTv3xLMyA());
                    case 9:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$IdQhTz2IUxhl8QTqu6qW_E99r3k());
                    case 10:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && (SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical") || SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic"))) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 12:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 13:
                        int udfpsType2 = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType2 != -1) {
                            z = ((udfpsType2 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0) & ((udfpsType2 & 16) != 0);
                        }
                        return Boolean.valueOf(z);
                    case 14:
                        return Boolean.valueOf(KeyguardFeatureOption.m620$r8$lambda$RcO4zVBK5PzBtdKEUJTsopCJdI());
                    case 15:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vh89e6iVJtakstICHi7amzY1Lt0());
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$jqGfNSX7yAtk2OBVNxvJsZ1wPxU());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.m618$r8$lambda$2qN2uMlt22o4gR_BAXFhtWkDcg());
                    default:
                        return SystemProperties.get("sys.oplus.multibrightness", "");
                }
            }
        });
        final int i20 = 1;
        isExponentialBrightnessMode$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i20) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i21 = 3;
        isSavedSP$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i21) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i22 = 4;
        isExtendBrightnessMode$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i22) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i23 = 5;
        isDisplayBrightnessMode$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i23) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i24 = 6;
        isSideFpNear$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i24) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i25 = 7;
        isSupportColorMode$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i25) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i26 = 8;
        isSupportCameraFpShutter$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i26) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i27 = 9;
        isRmScreenHoleNotchSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i27) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i28 = 10;
        pinholeFrontCameraPosition$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i28) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i29 = 12;
        secondaryPinholeFrontCameraPosition$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i29) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i30 = 13;
        boldPinholeFrontCameraBorder$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i30) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i31 = 15;
        isBoldPinholeFrontCamera$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i31) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i32 = 16;
        isSupportPinholeFrontCameraAnim$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i32) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i33 = 17;
        isSupportDoublePinholeFrontCameraAnim$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i33) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i34 = 18;
        isSupportFaceNoPowerBlock$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i34) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i35 = 19;
        isAONHardwareSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i35) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i36 = 20;
        isSupportSeparateWallpaper$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i36) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i37 = 21;
        sSupportWcgColorMode$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i37) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i38 = 23;
        isScreenResolutionSwitchSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i38) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i39 = 24;
        isDisableColorModeSwitch$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i39) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i40 = 25;
        isDisableIrisSwitch$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i40) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i41 = 27;
        isDisableFillLightAnim$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i41) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i42 = 28;
        isFingerprintFaceCombineUnlock$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i42) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i43 = 29;
        isSupportLocalHbmBrighten$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean z = false;
                switch (i43) {
                    case 0:
                        return Boolean.valueOf(KeyguardFeatureOption.m619$r8$lambda$Pd2QiMLoi8HzlCTayMwwltv5_o());
                    case 1:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vjT3Zr7tTv7Th7qd3E2Ot972QNo());
                    case 2:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$qmHjl7LnxvyTfGSq3AWCxXKcnFU());
                    case 3:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.save_sp", "").equals("1"));
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.extend.brightness", "").equals("1"));
                    case 5:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.display.brightness.mode", "0").equals("1"));
                    case 6:
                        return Boolean.valueOf(!"0".equals(SystemProperties.get("persist.vendor.side.fp.near.feature.support", "0")));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.colormode_support");
                    case 8:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint.shutter");
                    case 9:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.oplus.display.notch_support", false));
                    case 10:
                        return SystemProperties.get("ro.oplus.display.screenhole.positon", "");
                    case 11:
                        return Boolean.valueOf(SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("side"));
                    case 12:
                        return SystemProperties.get("ro.oplus.display.secondary.screenhole.position", "");
                    case 13:
                        return SystemProperties.get("ro.oplus.screenhole.border", "");
                    case 14:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.dynamic_fps_switch");
                    case 15:
                        return Boolean.valueOf(!TextUtils.isEmpty(KeyguardFeatureOption.INSTANCE.getBoldPinholeFrontCameraBorder()));
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$FtW2f3_4EO1nm8ooMplH9Xv9rYI());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$252fg8ExbdUy2XMmF23wjWIjcDk());
                    case 18:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.face_nopowerblock");
                    case 19:
                        if (OplusFeatureConfigManager.getInstance().hasFeature("oplus.hardware.aon_enable") && !OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.aon_auto_none_screen_off_disable")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_MINI_LEFT_RIGHT_OFFSET_DP /* 20 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.wallpaper.separate_wallpaper_for_multi_display");
                    case SmallScreenZoomParameter.SMALL_PORT_SCREEN_SUPER_MINI_LEFT_RIGHT_OFFSET_DP /* 21 */:
                        return Boolean.valueOf(KeyguardFeatureOption.m621$r8$lambda$keGXMvxoGXLQAqKC8lCDxbGuGc());
                    case 22:
                        return Boolean.valueOf(KeyguardFeatureOption.INSTANCE.isOplusFeatureSupport("oplus.software.fingeprint_back_touch"));
                    case 23:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.resolution_switch_support");
                    case 24:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.colormode.systemui_disable_switch", "0").equals("1"));
                    case 25:
                        return Boolean.valueOf(SystemProperties.get("ro.oplus.display.iris_systemui_disable_switch", "0").equals("1"));
                    case 26:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.kgd.fp_delay_auth");
                    case 27:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.ui_disable_fill_light_animation");
                    case 28:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.fingerprint_face_combine_unlock");
                    default:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.display.mura_enhance_brightness_support");
                }
            }
        });
        final int i44 = 1;
        isTablet$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                boolean z = false;
                switch (i44) {
                    case 0:
                        int udfpsType = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType != -1 && (udfpsType & 16) != 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet());
                    case 2:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isLargeScreenFolding());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m(ShellContinuousTransitionUtils.OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM);
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.aod.display.debug_refresh_rate", "0").equals("1"));
                    case 5:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.os_type", ""), "light_base"));
                    case 6:
                        return Boolean.valueOf(StringsKt__StringsJVMKt.equals(SystemProperties.get("ro.vendor.config.oplus.low_ram", "false"), "true", true));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.unlock.animation_exit_fast");
                    case 8:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$kNgI5MCLF5mL4xftI3aTv3xLMyA());
                    case 9:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$IdQhTz2IUxhl8QTqu6qW_E99r3k());
                    case 10:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && (SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical") || SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic"))) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 12:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 13:
                        int udfpsType2 = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType2 != -1) {
                            z = ((udfpsType2 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0) & ((udfpsType2 & 16) != 0);
                        }
                        return Boolean.valueOf(z);
                    case 14:
                        return Boolean.valueOf(KeyguardFeatureOption.m620$r8$lambda$RcO4zVBK5PzBtdKEUJTsopCJdI());
                    case 15:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vh89e6iVJtakstICHi7amzY1Lt0());
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$jqGfNSX7yAtk2OBVNxvJsZ1wPxU());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.m618$r8$lambda$2qN2uMlt22o4gR_BAXFhtWkDcg());
                    default:
                        return SystemProperties.get("sys.oplus.multibrightness", "");
                }
            }
        });
        final int i45 = 2;
        isLargeScreenFolding$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                boolean z = false;
                switch (i45) {
                    case 0:
                        int udfpsType = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType != -1 && (udfpsType & 16) != 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet());
                    case 2:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isLargeScreenFolding());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m(ShellContinuousTransitionUtils.OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM);
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.aod.display.debug_refresh_rate", "0").equals("1"));
                    case 5:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.os_type", ""), "light_base"));
                    case 6:
                        return Boolean.valueOf(StringsKt__StringsJVMKt.equals(SystemProperties.get("ro.vendor.config.oplus.low_ram", "false"), "true", true));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.unlock.animation_exit_fast");
                    case 8:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$kNgI5MCLF5mL4xftI3aTv3xLMyA());
                    case 9:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$IdQhTz2IUxhl8QTqu6qW_E99r3k());
                    case 10:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && (SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical") || SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic"))) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 12:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 13:
                        int udfpsType2 = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType2 != -1) {
                            z = ((udfpsType2 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0) & ((udfpsType2 & 16) != 0);
                        }
                        return Boolean.valueOf(z);
                    case 14:
                        return Boolean.valueOf(KeyguardFeatureOption.m620$r8$lambda$RcO4zVBK5PzBtdKEUJTsopCJdI());
                    case 15:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vh89e6iVJtakstICHi7amzY1Lt0());
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$jqGfNSX7yAtk2OBVNxvJsZ1wPxU());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.m618$r8$lambda$2qN2uMlt22o4gR_BAXFhtWkDcg());
                    default:
                        return SystemProperties.get("sys.oplus.multibrightness", "");
                }
            }
        });
        final int i46 = 3;
        isSupportAdaptiveSmoothAnim$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                boolean z = false;
                switch (i46) {
                    case 0:
                        int udfpsType = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType != -1 && (udfpsType & 16) != 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet());
                    case 2:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isLargeScreenFolding());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m(ShellContinuousTransitionUtils.OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM);
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.aod.display.debug_refresh_rate", "0").equals("1"));
                    case 5:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.os_type", ""), "light_base"));
                    case 6:
                        return Boolean.valueOf(StringsKt__StringsJVMKt.equals(SystemProperties.get("ro.vendor.config.oplus.low_ram", "false"), "true", true));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.unlock.animation_exit_fast");
                    case 8:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$kNgI5MCLF5mL4xftI3aTv3xLMyA());
                    case 9:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$IdQhTz2IUxhl8QTqu6qW_E99r3k());
                    case 10:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && (SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical") || SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic"))) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 12:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 13:
                        int udfpsType2 = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType2 != -1) {
                            z = ((udfpsType2 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0) & ((udfpsType2 & 16) != 0);
                        }
                        return Boolean.valueOf(z);
                    case 14:
                        return Boolean.valueOf(KeyguardFeatureOption.m620$r8$lambda$RcO4zVBK5PzBtdKEUJTsopCJdI());
                    case 15:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vh89e6iVJtakstICHi7amzY1Lt0());
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$jqGfNSX7yAtk2OBVNxvJsZ1wPxU());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.m618$r8$lambda$2qN2uMlt22o4gR_BAXFhtWkDcg());
                    default:
                        return SystemProperties.get("sys.oplus.multibrightness", "");
                }
            }
        });
        final int i47 = 4;
        isDebugRefreshRate$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                boolean z = false;
                switch (i47) {
                    case 0:
                        int udfpsType = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType != -1 && (udfpsType & 16) != 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet());
                    case 2:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isLargeScreenFolding());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m(ShellContinuousTransitionUtils.OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM);
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.aod.display.debug_refresh_rate", "0").equals("1"));
                    case 5:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.os_type", ""), "light_base"));
                    case 6:
                        return Boolean.valueOf(StringsKt__StringsJVMKt.equals(SystemProperties.get("ro.vendor.config.oplus.low_ram", "false"), "true", true));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.unlock.animation_exit_fast");
                    case 8:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$kNgI5MCLF5mL4xftI3aTv3xLMyA());
                    case 9:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$IdQhTz2IUxhl8QTqu6qW_E99r3k());
                    case 10:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && (SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical") || SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic"))) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 12:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 13:
                        int udfpsType2 = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType2 != -1) {
                            z = ((udfpsType2 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0) & ((udfpsType2 & 16) != 0);
                        }
                        return Boolean.valueOf(z);
                    case 14:
                        return Boolean.valueOf(KeyguardFeatureOption.m620$r8$lambda$RcO4zVBK5PzBtdKEUJTsopCJdI());
                    case 15:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vh89e6iVJtakstICHi7amzY1Lt0());
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$jqGfNSX7yAtk2OBVNxvJsZ1wPxU());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.m618$r8$lambda$2qN2uMlt22o4gR_BAXFhtWkDcg());
                    default:
                        return SystemProperties.get("sys.oplus.multibrightness", "");
                }
            }
        });
        final int i48 = 5;
        isLightBase$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                boolean z = false;
                switch (i48) {
                    case 0:
                        int udfpsType = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType != -1 && (udfpsType & 16) != 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet());
                    case 2:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isLargeScreenFolding());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m(ShellContinuousTransitionUtils.OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM);
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.aod.display.debug_refresh_rate", "0").equals("1"));
                    case 5:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.os_type", ""), "light_base"));
                    case 6:
                        return Boolean.valueOf(StringsKt__StringsJVMKt.equals(SystemProperties.get("ro.vendor.config.oplus.low_ram", "false"), "true", true));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.unlock.animation_exit_fast");
                    case 8:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$kNgI5MCLF5mL4xftI3aTv3xLMyA());
                    case 9:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$IdQhTz2IUxhl8QTqu6qW_E99r3k());
                    case 10:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && (SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical") || SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic"))) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 12:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 13:
                        int udfpsType2 = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType2 != -1) {
                            z = ((udfpsType2 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0) & ((udfpsType2 & 16) != 0);
                        }
                        return Boolean.valueOf(z);
                    case 14:
                        return Boolean.valueOf(KeyguardFeatureOption.m620$r8$lambda$RcO4zVBK5PzBtdKEUJTsopCJdI());
                    case 15:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vh89e6iVJtakstICHi7amzY1Lt0());
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$jqGfNSX7yAtk2OBVNxvJsZ1wPxU());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.m618$r8$lambda$2qN2uMlt22o4gR_BAXFhtWkDcg());
                    default:
                        return SystemProperties.get("sys.oplus.multibrightness", "");
                }
            }
        });
        final int i49 = 6;
        isLowRamSize$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.KeyguardFeatureOption$$ExternalSyntheticLambda3
            public final Object invoke() {
                boolean z = false;
                switch (i49) {
                    case 0:
                        int udfpsType = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType != -1 && (udfpsType & 16) != 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 1:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isTablet());
                    case 2:
                        FeatureOption.INSTANCE.getClass();
                        return Boolean.valueOf(FeatureOption.isLargeScreenFolding());
                    case 3:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m(ShellContinuousTransitionUtils.OPLUS_FEATURE_ADAPTIVE_SMOOTH_ANIM);
                    case 4:
                        return Boolean.valueOf(SystemProperties.get("persist.sys.oplus.aod.display.debug_refresh_rate", "0").equals("1"));
                    case 5:
                        return Boolean.valueOf(Intrinsics.areEqual(SystemProperties.get("ro.oplus.os_type", ""), "light_base"));
                    case 6:
                        return Boolean.valueOf(StringsKt__StringsJVMKt.equals(SystemProperties.get("ro.vendor.config.oplus.low_ram", "false"), "true", true));
                    case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.unlock.animation_exit_fast");
                    case 8:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$kNgI5MCLF5mL4xftI3aTv3xLMyA());
                    case 9:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$IdQhTz2IUxhl8QTqu6qW_E99r3k());
                    case 10:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && (SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical") || SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic"))) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 11:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("optical")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 12:
                        KeyguardFeatureOption.INSTANCE.getClass();
                        if (KeyguardFeatureOption.isSupportFpUnlock() && SystemProperties.get("persist.vendor.fingerprint.sensor_type", "").equals("ultrasonic")) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    case 13:
                        int udfpsType2 = KeyguardFeatureOption.INSTANCE.getUdfpsType();
                        if (udfpsType2 != -1) {
                            z = ((udfpsType2 & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) != 0) & ((udfpsType2 & 16) != 0);
                        }
                        return Boolean.valueOf(z);
                    case 14:
                        return Boolean.valueOf(KeyguardFeatureOption.m620$r8$lambda$RcO4zVBK5PzBtdKEUJTsopCJdI());
                    case 15:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$vh89e6iVJtakstICHi7amzY1Lt0());
                    case 16:
                        return Boolean.valueOf(KeyguardFeatureOption.$r8$lambda$jqGfNSX7yAtk2OBVNxvJsZ1wPxU());
                    case 17:
                        return Boolean.valueOf(KeyguardFeatureOption.m618$r8$lambda$2qN2uMlt22o4gR_BAXFhtWkDcg());
                    default:
                        return SystemProperties.get("sys.oplus.multibrightness", "");
                }
            }
        });
    }

    private final IDisplayPanelFeature getAidlService() {
        return IDisplayPanelFeature.Stub.asInterface(ServiceManager.getService(SERVICE_NAME));
    }

    public static int[] getDisplayPanelFeatureHidl() {
        Object obj;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new int[1];
        try {
            vendor.oplus.hardware.displaypanelfeature.V1_0.IDisplayPanelFeature.Proxy service = vendor.oplus.hardware.displaypanelfeature.V1_0.IDisplayPanelFeature.getService();
            if (service != null) {
                KeyguardFeatureOption$getDisplayPanelFeatureHidl$1$1 keyguardFeatureOption$getDisplayPanelFeatureHidl$1$1 = new KeyguardFeatureOption$getDisplayPanelFeatureHidl$1$1();
                keyguardFeatureOption$getDisplayPanelFeatureHidl$1$1.$featureCode = 69;
                keyguardFeatureOption$getDisplayPanelFeatureHidl$1$1.$feature = objectRef;
                VarHandle.storeStoreFence();
                service.getDisplayPanelFeatureValue(69, keyguardFeatureOption$getDisplayPanelFeatureHidl$1$1);
            } else {
                LogUtil.i$default(LogUtil.TAG_KEYGUARD, TAG, "getDisplayFeature achieve displayPanelFeature failed", null, 8);
            }
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            LogUtil.e$default(8, LogUtil.TAG_KEYGUARD, TAG, "getDisplayFeature error: " + th2.getMessage());
        }
        return (int[]) objectRef.element;
    }

    private final String getSecondaryPinholeFrontCameraPosition() {
        return (String) secondaryPinholeFrontCameraPosition$delegate.getValue();
    }

    public static boolean is8BitScreen() {
        return ((Boolean) is8BitScreen$delegate.getValue()).booleanValue();
    }

    public static boolean isAONHardwareSupport() {
        return ((Boolean) isAONHardwareSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isBoldPinholeFrontCamera() {
        return ((Boolean) isBoldPinholeFrontCamera$delegate.getValue()).booleanValue();
    }

    public static boolean isDebugRefreshRate() {
        return ((Boolean) isDebugRefreshRate$delegate.getValue()).booleanValue();
    }

    public static boolean isDisableAppDimLayer() {
        return ((Boolean) isDisableAppDimLayer$delegate.getValue()).booleanValue();
    }

    public static boolean isDisableColorModeSwitch() {
        return ((Boolean) isDisableColorModeSwitch$delegate.getValue()).booleanValue();
    }

    public static boolean isDisableFillLightAnim() {
        return ((Boolean) isDisableFillLightAnim$delegate.getValue()).booleanValue();
    }

    public static boolean isDisableIrisSwitch() {
        return ((Boolean) isDisableIrisSwitch$delegate.getValue()).booleanValue();
    }

    public static boolean isDisplayBrightnessMode() {
        return ((Boolean) isDisplayBrightnessMode$delegate.getValue()).booleanValue();
    }

    public static boolean isExponentialBrightnessMode() {
        return ((Boolean) isExponentialBrightnessMode$delegate.getValue()).booleanValue();
    }

    public static boolean isExtendBrightnessMode() {
        return ((Boolean) isExtendBrightnessMode$delegate.getValue()).booleanValue();
    }

    public static boolean isFingerprintDelayAuth() {
        return ((Boolean) isFingerprintDelayAuth$delegate.getValue()).booleanValue();
    }

    public static boolean isFingerprintFaceCombineUnlock() {
        return ((Boolean) isFingerprintFaceCombineUnlock$delegate.getValue()).booleanValue();
    }

    public static boolean isHighFramePlatform() {
        return ((Boolean) isHighFramePlatform$delegate.getValue()).booleanValue();
    }

    public static boolean isLightBase() {
        return ((Boolean) isLightBase$delegate.getValue()).booleanValue();
    }

    public static boolean isLihgtOsLowLevel() {
        return sKeyguardLightOsLevel > 1;
    }

    public static boolean isLocalHBM() {
        return ((Boolean) isLocalHBM$delegate.getValue()).booleanValue();
    }

    public static boolean isLocalHBMAccel() {
        return ((Boolean) isLocalHBMAccel$delegate.getValue()).booleanValue();
    }

    public static boolean isNonLocalHbmSupportOpticalFp() {
        return isSupportOpticalFps() && !isLocalHBM();
    }

    public static boolean isOplusRoR() {
        return isSupportOplusRoR() && ((Boolean) isSavedSP$delegate.getValue()).booleanValue();
    }

    public static boolean isRmScreenHoleNotchSupport() {
        return ((Boolean) isRmScreenHoleNotchSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isScreenResolutionSwitchSupport() {
        return ((Boolean) isScreenResolutionSwitchSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isSideFpNear() {
        return ((Boolean) isSideFpNear$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportAdaptiveSmoothAnim() {
        return ((Boolean) isSupportAdaptiveSmoothAnim$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportBackTouchFingerprint() {
        return ((Boolean) isSupportBackTouchFingerprint$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportCameraFpShutter() {
        return ((Boolean) isSupportCameraFpShutter$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportColorMode() {
        return ((Boolean) isSupportColorMode$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportDoublePinholeFrontCameraAnim() {
        return ((Boolean) isSupportDoublePinholeFrontCameraAnim$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportFaceNoPowerBlock() {
        return ((Boolean) isSupportFaceNoPowerBlock$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportFaceUnlock() {
        return ((Boolean) isSupportFaceUnlock$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportFingerprintQuickLaunch() {
        return ((Boolean) isSupportFingerprintQuickLaunch$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportFpUnlock() {
        return ((Boolean) isSupportFpUnlock$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportLocalHbmBrighten() {
        return ((Boolean) isSupportLocalHbmBrighten$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportOplusRoR() {
        return ((Boolean) isSupportOplusRoR$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportOpticalFps() {
        return ((Boolean) isSupportOpticalFps$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportPinholeFrontCameraAnim() {
        return ((Boolean) isSupportPinholeFrontCameraAnim$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportResolutionSwitch() {
        return ((Boolean) isSupportResolutionSwitch$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportSeparateWallpaper() {
        return ((Boolean) isSupportSeparateWallpaper$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportSidefps() {
        return ((Boolean) isSupportSidefps$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportUdfps() {
        return ((Boolean) isSupportUdfps$delegate.getValue()).booleanValue();
    }

    public static boolean isSupportUltrasonicFps() {
        return ((Boolean) isSupportUltrasonicFps$delegate.getValue()).booleanValue();
    }

    public static boolean isTablet() {
        return ((Boolean) isTablet$delegate.getValue()).booleanValue();
    }

    public static void isTpProximitySensor() {
        ((Boolean) isTpProximitySensor$delegate.getValue()).getClass();
    }

    public static boolean isUnlockAnimationExitFast() {
        return ((Boolean) isUnlockAnimationExitFast$delegate.getValue()).booleanValue();
    }

    public final List<String> getAppFeatureCacheList() {
        return appFeatureList;
    }

    public final String getBoldPinholeFrontCameraBorder() {
        return (String) boldPinholeFrontCameraBorder$delegate.getValue();
    }

    public final String getGlPictorialAgreementUrl() {
        return glPictorialAgreementUrl;
    }

    public final String getGlPictorialPolicyUrl() {
        return glPictorialPolicyUrl;
    }

    public final String getMultiBrightness() {
        return (String) multiBrightness$delegate.getValue();
    }

    public final String getOtherPictorialName() {
        return otherPictorialName;
    }

    public final String getPinholeFrontCameraPosition(int i) {
        if (i == 0) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isFoldDevice()) {
                return getSecondaryPinholeFrontCameraPosition();
            }
        }
        return getPinholeFrontCameraPosition();
    }

    public final String getRmRegionName() {
        return rmRegionName;
    }

    public final boolean getSIsLehuaSwitchCorrected() {
        return sIsLehuaSwitchCorrected;
    }

    public final int getSKeyguardLightOsLevel() {
        return sKeyguardLightOsLevel;
    }

    public final int getSPlatforAnimationLevel() {
        return sPlatforAnimationLevel;
    }

    public final boolean getSSupportDynamicPictorial() {
        return sSupportDynamicPictorial;
    }

    public final boolean getSSupportSmartTouch() {
        return sSupportSmartTouch;
    }

    public final boolean getSSupportWcgColorMode() {
        return ((Boolean) sSupportWcgColorMode$delegate.getValue()).booleanValue();
    }

    public final int getUdfpsType() {
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        try {
            IDisplayPanelFeature aidlService = getAidlService();
            if (aidlService != null) {
                int[] iArr = new int[1];
                aidlService.getDisplayPanelFeatureValue(211, iArr);
                intRef.element = iArr[0];
            } else {
                vendor.oplus.hardware.displaypanelfeature.V1_0.IDisplayPanelFeature.Proxy service = vendor.oplus.hardware.displaypanelfeature.V1_0.IDisplayPanelFeature.getService();
                if (service != null) {
                    getUdfpsType.1 r7 = new getUdfpsType.1(0);
                    r7.$udfpsType = intRef;
                    VarHandle.storeStoreFence();
                    service.getDisplayPanelFeatureValue(211, r7);
                }
            }
            LogUtil.i$default(LogUtil.TAG_KEYGUARD, TAG, "isLocalHBM val=" + Integer.toHexString(intRef.element), null, 8);
        } catch (Exception e) {
            LogUtil.e$default(8, LogUtil.TAG_KEYGUARD, TAG, "isLocalHBM got error: " + e.getMessage());
        }
        return intRef.element;
    }

    public final boolean isOplusFeatureSupport(String str) {
        OplusFeatureConfigManager oplusFeatureConfigManager = OplusFeatureConfigManager.getInstance();
        return oplusFeatureConfigManager != null && oplusFeatureConfigManager.hasFeature(str);
    }

    public final void loadAppFeature(Context context) {
        ComponentName componentNameUnflattenFromString;
        PackageManager packageManager;
        PackageManager packageManager2;
        Log.i(TAG, "keyguard loadAppFeature start, context: " + context);
        aplicationContext = context;
        ContentResolver contentResolver = context.getContentResolver();
        AppFeatureProviderUtils.FeatureID featureID = AppFeatureProviderUtils.FeatureID.STATIC_COMPONENT;
        isDisableBiometricUnlockAnim = AppFeatureProviderUtils.isFeatureSupport(contentResolver, featureID, DISABLE_BIOMETRIC_UNLOCK_ANIM_STRING);
        isSupportCPF = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, FEATURE_SUPPORT_CPF);
        isEnableSOAVFXInLightOS = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, FEATURE_ENABLE_SOA_VFX_IN_LIGHT_OS);
        AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), FEATURE_IP_CUSTOMIZE_DEVICE);
        Context context2 = aplicationContext;
        isFpBlindUnlockEnabled = (context2 == null || (packageManager2 = context2.getPackageManager()) == null || !packageManager2.hasSystemFeature(OPLUS_FP_BLIND_UNLOCK_FEATURE)) ? false : true;
        isNotUseCameraMotorUpAnimation = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, NOT_USE_CAMERA_MOTOR_ANIMATION_STRING);
        isSupportPukCodeShow = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, PUK_CODE_SHOW_STRING);
        isDisableBouncerBlurScrim = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, DISABLE_BOUNCER_BLUR_SCRIM);
        isSupportWhiteSwanShow = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), FEATURE_SUPPORT_WHITE_SWAN_SHOW, false);
        isPasswordDialEnabled = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), FEATURE_PASSWORD_DIAL_ENABLE, true);
        isEnableBouncerWallpaperBlur = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), ENABLE_BOUNCER_WALLPAPER_BLUR, false);
        Context context3 = aplicationContext;
        isPictorialDisabled = (context3 == null || (packageManager = context3.getPackageManager()) == null || !packageManager.hasSystemFeature(OPLUS_KEYGUARD_PICTORIAL_DISABLE_FEATURE)) ? false : true;
        String string = AppFeatureProviderUtils.getString(context.getContentResolver(), OPLUS_RLM_OTHER_PICTORIAL_SUPPORT, "");
        otherPictorialName = string;
        if (TextUtils.isEmpty(string)) {
            isSupportOtherPictorial = false;
            otherPictorialName = DEFAUTL_PICTORIAL_NAME;
        } else {
            isSupportOtherPictorial = true;
        }
        glPictorialAgreementUrl = AppFeatureProviderUtils.getString(context.getContentResolver(), OPLUS_RLM_OTHER_PICTORIAL_GL_AGREEMENT_URL, "");
        glPictorialPolicyUrl = AppFeatureProviderUtils.getString(context.getContentResolver(), OPLUS_RLM_OTHER_PICTORIAL_GL_PRIVACY_URL, "");
        rmRegionName = AppFeatureProviderUtils.getString(context.getContentResolver(), OPLUS_RLM_REGION_NAME, "");
        isSupportOplusGLPictorial = Intrinsics.areEqual(otherPictorialName, OPLUS_GLANCE_PKG);
        isKeepHeyTapPictorial = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, FEATURE_KEEP_HEYTAP_PICTORIAL);
        sIsLehuaSwitchCorrected = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, FEATURE_LEHUA_SWITCH_CORRECTED);
        sSupportDynamicPictorial = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, FEATURE_SUPPORT_DYNAMIC_PICTORIAL);
        isSupportSmartLock = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, SMART_LOCK_ENABLE_STRING);
        try {
            Context context4 = aplicationContext;
            if (context4 != null && (componentNameUnflattenFromString = ComponentName.unflattenFromString(context4.getString(2131953418))) != null) {
                context4.getPackageManager().setComponentEnabledSetting(componentNameUnflattenFromString, isSupportSmartLock ? 2 : 1, 1);
            }
        } catch (Exception unused) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "FeatureOption-->setComponentEnabledSetting exception");
            }
        }
        sPlatforAnimationLevel = OplusPlatformLevelUtils.getInstance(context).getPlatformAnimationLevel();
        isTabletSupportNFC = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, FEATURE_TABLET_SUPPORT_NFC);
        sSupportSmartTouch = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, FEATURE_SUPPORT_SMART_TOUCH);
        isPreventModeDefaultOff = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, FEATURE_PREVENT_MODE_DEFAULT_OFF);
        FlavorOneFeatureOption.INSTANCE.getClass();
        isMotionOptimizationEnable = ((!FlavorOneFeatureOption.isFlavorOneDevice() || ScrimUtil.isMiddleOrHighAnimLevel(context)) && !AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, FEATURE_KEYGUARD_NEW_ANIM_DISABLE)) ? SystemProperties.getBoolean("debug.kgd.new.motion", true) : false;
        AppFeatureProviderUtils.getInt(context.getContentResolver(), featureID, FEATURE_SMART_CHARGE_MODE, -1);
        sKeyguardLightOsLevel = AppFeatureProviderUtils.getInt(context.getContentResolver(), featureID, FEATURE_LIGHT_OS_LEVEL, -1);
        isSupportWaterRipple = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, SUPPORT_UPGRADE_ANIM_WATERRIPPLE);
        isKeyguardDisableWallpaperZoom = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, FEATURE_KEYGUARD_DISABLE_ZOOM_WALLPAPER);
        isKeyguardDisableShowSideFPTip = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), FEATURE_KEYGUARD_DISABLE_SIDE_FP_SHOW_TIP, false);
    }

    public final void setEnableBouncerWallpaperBlur(boolean z) {
        isEnableBouncerWallpaperBlur = z;
    }

    public final void setHongshuSpecialLightEnable(boolean z) {
    }

    private final String getPinholeFrontCameraPosition() {
        return (String) pinholeFrontCameraPosition$delegate.getValue();
    }
}

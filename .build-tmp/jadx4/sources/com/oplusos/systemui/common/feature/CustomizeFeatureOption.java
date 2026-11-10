package com.oplusos.systemui.common.feature;

import android.content.ContentResolver;
import android.content.Context;
import android.os.oplusdevicepolicy.OplusDevicepolicyManager;
import android.util.Log;
import androidx.appcompat.widget.ListPopupWindow$;
import com.oplus.content.IOplusFeatureConfigManager;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplus.coreapp.appfeature.AppFeatureProviderUtils;
import com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class CustomizeFeatureOption {
    public static final AnonymousClass1 sAutoBrightnessForceModeObserver;
    public static final AnonymousClass1 sBrightnessSlidersObserver;
    public static Context sContext;
    public static final AnonymousClass1 sHiddenQsTilesObserver;
    public static final AnonymousClass1 sNoDisturbDisabledbserver;
    public static final OplusFeatureObserver sOplusFeatureObserver;
    public static final AnonymousClass1 sScreenCaptureDisabledObserver;
    public static final SlotOplusFeatureObserver sSlotOplusFeatureObserver;
    public static final ArrayList APP_FEATURE_CACHE_LIST = new ArrayList(Arrays.asList("com.android.systemui.keyguard_show_networkstatus", "com.android.systemui.keyguard_show_at_least_3g", "com.android.systemui.letter_vowifi_icon", "com.android.systemui.navigationbar_disable_in_startapp", "com.android.systemui.keyguard_show_pinpukremains", "com.android.systemui.keyguard_show_operatornotice", "com.android.systemui.airplanemode_second_confirm", "com.android.systemui.statusbar_g_in_gprs", "com.android.systemui.virtualkey_recent_right_of_home", "com.android.systemui.signal_layout_att", "com.android.systemui.keyguard_hide_pukinput", "com.android.systemui.support_more_swipup_gestures", "com.android.systemui.keep_swipup_gestures", "com.android.systemui.kddi_show_au", "com.android.systemui.statusbar_show_big_5g", "com.android.systemui.show_5g_anim_in_lock_screen", "com.android.systemui.statusbar_show_cta_nosim", "com.android.systemui.disable_otg_auto_close", "com.oplus.floatassistant.air_view_disable", "com.android.systemui.hide_organization_message", "com.android.systemui.show_ust_hotspot", "com.android.systemui.show_carrier_air_wfc", "com.android.systemui.show_sprint_hotspot_dia", "com.android.systemui.tmo_signal_layout", "com.android.systemui.sprint_signal_layout", "com.android.systemui.lol_special", "com.oplus.wirelesssettings.customize_passpoint_icon", "com.oplus.floatassistant.assistive_ball_disable", "com.android.systemui.show_operator_name", "com.oplus.systemui.customize_protect_services", "oplus.software.support_secondary_mini_app", "com.android.systemui.statusbar_show_big_5guwc", "oplus.software.connectivity.absent_card_stop_tethering", "com.android.systemui.statusbar_show_wifi6_for_ct", "os.personalization.wallpaper.scale.default", "oplus.wallpaper.new_anim.disable", "com.android.systemui.not_show_nfc_notification", "com.android.phone.em_service_show_in_simpin_state", "oplus.software.show.flashlight_notification_packages", "com.android.systemui.gemini_icon_gone_custom", "com.android.systemui.satellite_hide_volte", "com.android.systemui.hide_pictorial", "com.android.systemui.show_wifi_6e", "com.android.systemui.sep_show_spn", "com.oplus.rom.antitheft_rlock_support", "com.oplus.assistant.yandex_guide", "oplus.software.default_assistant"));
    public static boolean sHideOrganizationMsg = false;
    public static boolean sIsSupportNetworkStatus = false;
    public static boolean sIsSupportAtLeast3G = false;
    public static boolean sIsSupportShowPinpukRemains = false;
    public static boolean sIsSupportShowOperatorNotice = false;
    public static boolean sIsLetterVowifiIcon = false;
    public static boolean sIsSupportJapanFelica = false;
    public static boolean sIsAirplanemodeConfirm = false;
    public static boolean sIsVirtualkeyRecentRightOfHome = false;
    public static boolean sIsGeminiExperience = false;
    public static boolean sIsYandexExperience = false;
    public static boolean sIsDefaultAssistantFeatureEnable = false;
    public static boolean sIsSupportHidePukInput = false;
    public static boolean sHasSupportMoreGestureUp = false;
    public static boolean sKeepGestureUp = false;
    public static boolean sIsSupportEsimCard = false;
    public static boolean sIsShowBig5G = false;
    public static boolean sIsShowCtaNoSimView = false;
    public static boolean sIsShowCarrierAirWfc = false;
    public static boolean sIsShowSprintHotSpotDia = false;
    public static boolean sIsTmoSignalViewLayout = false;
    public static boolean sIsSprintSignalViewLayout = false;
    public static boolean sIsDisableOtgAutoClose = false;
    public static boolean sIsNotShowNfcNotification = false;
    public static boolean sLOLFeature = false;
    public static boolean sFloatAssistantFeature = false;
    public static boolean sIsShowOperatorName = false;
    public static boolean sIsSupportProtectServices = false;
    public static boolean sIsSupportThreekeyVibration = false;
    public static boolean sIsShowBig5GUWC = false;
    public static boolean sIsShowEMEWhenNoservice = false;
    public static boolean sIsAutoBrightnessDisabled = false;
    public static boolean sIsBrightnessSlidersDisabled = false;
    public static boolean sIsNoDisturbDisabled = false;
    public static boolean sIsScreenCaptureDisabled = false;
    public static final boolean[] sIsSupportHideVoWifiIcon = new boolean[2];
    public static final boolean[] sIsSupportHideVoLTEIcon = new boolean[2];
    public static final boolean[] sIsSupportHideRoamingIcon = new boolean[2];
    public static final boolean[] sIsSupportShowGForGPRS = new boolean[2];
    public static final boolean[] sIsSupportShow2GForE = new boolean[2];
    public static final boolean[] sIsSupportShow3GForH = new boolean[2];
    public static final boolean[] sIsSupportShow3GForHPlus = new boolean[2];
    public static final boolean[] sIsSupportShowLTEFor4G = new boolean[2];
    public static final boolean[] sIsSupportShowLTEFor4GPlus = new boolean[2];
    public static final boolean[] sIsSupportShow4GFor4GPlus = new boolean[2];
    public static final boolean[] sIsSupportShow4Dot5GFor4G = new boolean[2];
    public static final boolean[] sIsSupportShow4Dot5GFor4GPlus = new boolean[2];
    public static final boolean[] sIsSupportShowLTEPlusFor4GPlus = new boolean[2];
    public static final boolean[] sIsSupportShowGFor2G = new boolean[2];
    public static final boolean[] sIsSupportShow3GPlusForH = new boolean[2];
    public static final boolean[] sIsSupportShowHForHPlus = new boolean[2];
    public static final boolean[] sIsSupportShowLineNumberCust = new boolean[2];
    public static final boolean[] sIsSupportGPSTileShowVoWifiDialog = new boolean[2];
    public static final boolean[] sIsSupportShowDataPrompting = new boolean[2];
    public static final int[] sTetheringEntitlement = new int[2];
    public static final boolean[] sVZWTethering = new boolean[2];
    public static final boolean[] sIsSupportShowVoltefForVo5G = new boolean[2];
    public static final boolean[] sIsSupportShowVoNR = new boolean[2];
    public static final boolean[] sIsSupportShowHDPLUS = new boolean[2];
    public static final boolean[] sIsIconNrad5guc = new boolean[2];
    public static final boolean[] sIsIconNrad5guw = new boolean[2];
    public static final boolean[] sIsIconNrad5gPlus = new boolean[2];
    public static final boolean[] sIsIconNrad5ga = new boolean[2];
    public static final boolean[] sIsIconLtead5ge = new boolean[2];
    public static final boolean[] sIsIconNrad5gPlusPlus = new boolean[2];
    public static final boolean[] sIsIconFourDot5g = new boolean[2];
    public static final boolean[] sIsIconNradFiveDot5g = new boolean[2];
    public static final boolean[] sIsSoftBankSim = new boolean[2];
    public static final boolean[] sIsSoftBankVoiceType = new boolean[2];
    public static boolean sIsSupportFiveSignalBar = false;
    public static boolean sIsSupportWorTileAnime = false;
    public static boolean sIsSupportCustomizePasspointIcon = false;
    public static boolean sIsSupportMiniApp = false;
    public static boolean sIsShowWifi6IconForCtTest = false;
    public static boolean sIsSupportMode = false;
    public static boolean sIsSupportHomeWaveVibration = false;
    public static boolean sIsGetWallpaperFeature = false;
    public static boolean sIsWallpaperScaleDefaultOn = true;
    public static boolean sIsWallpaperNewAnimDisable = false;
    public static boolean sIsSupportCircleToSearch = false;
    public static boolean sWlanIconAlwaysOn = false;
    public static boolean sIsSupportShowWifi6E = false;
    public static boolean sIsSupportCircleToSearchNavbarHidden = false;
    public static boolean sIsSupportGeminiGone = false;
    public static boolean sIsSystemUiExpSignalUi = false;
    public static boolean sAntitheftRlockSupport = false;
    public static final boolean[] sIsTigo5GIconSupport = new boolean[2];
    public static OplusDevicepolicyManager sOplusDevicepolicyManager = null;
    public static final ArrayList sSlotAppFeatureList = new ArrayList(Arrays.asList("com.android.systemui.hide_vowifi_icon", "com.android.systemui.hide_volte_icon", "com.android.systemui.hide_roaming_icon", "com.android.systemui.show_g_for_gprs", "com.android.systemui.show_2g_for_e", "com.android.systemui.show_3g_for_h", "com.android.systemui.show_3g_for_hplus", "com.android.systemui.show_lte_for_4g", "com.android.systemui.show_lte_for_4gplus", "com.android.systemui.show_4dot5g_for_4g", "com.android.systemui.show_4dot5g_for_4gplus", "com.android.systemui.show_lteplus_for_4gplus", "com.android.systemui.show_g_for_2g", "com.android.systemui.show_3gplus_for_h", "com.android.systemui.show_h_for_hplus", "com.android.systemui.show_4g_for_4gplus", "com.android.systemui.gps_tile_vowifi_dialog", "com.android.systemui.support_tethering_entitlement", "com.android.systemui.show_data_prompting", "com.android.systemui.softbank_voice_type", "com.android.systemui.support_vzw_tethering_check", "com.android.systemui.show_volte_for_vo5g", "oplus.software.connectivity.absent_card_stop_tethering", "com.android.systemui.show_vonr", "com.android.systemui.show_hdplus", "com.android.systemui.tigo_5g_icon"));
    public static final ArrayList sSlotOplusFeatureList = new ArrayList(Arrays.asList("oplus.software.radio.icon_nrad_5gplus", "oplus.software.radio.icon_ltead_5ge", "oplus.software.radio.icon_nrad_5guc", "oplus.software.radio.icon_nrad_5guw", "oplus.software.radio.icon_nrad_5ga", "oplus.software.radio.icon_ltead_four_point_five_g_plus", "oplus.software.radio.icon_nrad_5gplus_plus", "oplus.software.radio.icon_nrad_5gdot_5g"));
    public static final CopyOnWriteArrayList sFeatureChangeCallBacks = new CopyOnWriteArrayList();
    public static final CopyOnWriteArrayList sSlotFeatureChangeCallBacks = new CopyOnWriteArrayList();
    public static final ArrayList sOplusFeatureList = new ArrayList(Arrays.asList("oplus.software.five_signal_bar"));
    public static List sShowFlashLightNotificationPackages = new ArrayList();
    public static List sHideVolteOperator = new ArrayList();
    public static List sHiddenQsTilesList = new ArrayList();
    public static boolean sIsHidePictorial = false;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface CustomFeatureChangeCallBack {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class OplusFeatureObserver implements OplusFeatureConfigManager.OnFeatureObserver {
        public OplusFeatureConfigManager mOplusFeatureConfigManager;

        public final void onFeatureUpdate(List list) {
            CustomizeFeatureOption.sIsSupportFiveSignalBar = this.mOplusFeatureConfigManager.hasFeature("oplus.software.five_signal_bar");
            Log.i("CustomizeFeatureOption", "sIsSupportFiveSignalBar= " + CustomizeFeatureOption.sIsSupportFiveSignalBar);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SlotOplusFeatureObserver implements OplusFeatureConfigManager.OnFeatureMapObserver {
        public final void onFeatureUpdate(List list, IOplusFeatureConfigManager.FeatureID featureID) {
            CustomizeFeatureOption.loadOplusFeatureForSlotId(featureID);
            for (OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1 oplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1 : CustomizeFeatureOption.sSlotFeatureChangeCallBacks) {
                IOplusFeatureConfigManager.FeatureID featureID2 = IOplusFeatureConfigManager.FeatureID.DYNAMIC_SIMSLOT_1;
                oplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1.onFeatureChange();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [com.oplusos.systemui.common.feature.CustomizeFeatureOption$1] */
    /* JADX WARN: Type inference failed for: r0v5, types: [com.oplusos.systemui.common.feature.CustomizeFeatureOption$1] */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.oplusos.systemui.common.feature.CustomizeFeatureOption$1] */
    /* JADX WARN: Type inference failed for: r0v7, types: [com.oplusos.systemui.common.feature.CustomizeFeatureOption$1] */
    /* JADX WARN: Type inference failed for: r0v8, types: [com.oplusos.systemui.common.feature.CustomizeFeatureOption$1] */
    static {
        OplusFeatureObserver oplusFeatureObserver = new OplusFeatureObserver();
        oplusFeatureObserver.mOplusFeatureConfigManager = OplusFeatureConfigManager.getInstance();
        sOplusFeatureObserver = oplusFeatureObserver;
        sSlotOplusFeatureObserver = new SlotOplusFeatureObserver();
        final int i = 0;
        sAutoBrightnessForceModeObserver = new OplusDevicepolicyManager.OplusDevicePolicyObserver() { // from class: com.oplusos.systemui.common.feature.CustomizeFeatureOption.1
            public final void onOplusDevicePolicyUpdate(String str, String str2) {
                switch (i) {
                    case 0:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set auto brightness force mode updated: " + str2);
                        CustomizeFeatureOption.sIsAutoBrightnessDisabled = Boolean.valueOf(str2).booleanValue();
                        break;
                    case 1:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set brightness sliders updated: " + str2);
                        CustomizeFeatureOption.sIsBrightnessSlidersDisabled = Boolean.valueOf(str2).booleanValue();
                        break;
                    case 2:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set no disturb updated: " + str2);
                        CustomizeFeatureOption.sIsNoDisturbDisabled = Boolean.valueOf(str2).booleanValue();
                        break;
                    case 3:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set screen capture disabled update: " + str2);
                        CustomizeFeatureOption.sIsScreenCaptureDisabled = Boolean.parseBoolean(str2);
                        break;
                    default:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set tiles hidden updated");
                        break;
                }
            }

            public final void onOplusDevicePolicyUpdate(String str, List list) {
                switch (i) {
                    case 0:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set auto brightness force mode updated");
                        break;
                    case 1:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set brightness sliders updated");
                        break;
                    case 2:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set no disturb updated");
                        break;
                    case 3:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set screen capture disabled update");
                        break;
                    default:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set tiles hidden updated: " + list);
                        CustomizeFeatureOption.sHiddenQsTilesList = list;
                        break;
                }
            }
        };
        final int i2 = 1;
        sBrightnessSlidersObserver = new OplusDevicepolicyManager.OplusDevicePolicyObserver() { // from class: com.oplusos.systemui.common.feature.CustomizeFeatureOption.1
            public final void onOplusDevicePolicyUpdate(String str, String str2) {
                switch (i2) {
                    case 0:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set auto brightness force mode updated: " + str2);
                        CustomizeFeatureOption.sIsAutoBrightnessDisabled = Boolean.valueOf(str2).booleanValue();
                        break;
                    case 1:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set brightness sliders updated: " + str2);
                        CustomizeFeatureOption.sIsBrightnessSlidersDisabled = Boolean.valueOf(str2).booleanValue();
                        break;
                    case 2:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set no disturb updated: " + str2);
                        CustomizeFeatureOption.sIsNoDisturbDisabled = Boolean.valueOf(str2).booleanValue();
                        break;
                    case 3:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set screen capture disabled update: " + str2);
                        CustomizeFeatureOption.sIsScreenCaptureDisabled = Boolean.parseBoolean(str2);
                        break;
                    default:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set tiles hidden updated");
                        break;
                }
            }

            public final void onOplusDevicePolicyUpdate(String str, List list) {
                switch (i2) {
                    case 0:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set auto brightness force mode updated");
                        break;
                    case 1:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set brightness sliders updated");
                        break;
                    case 2:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set no disturb updated");
                        break;
                    case 3:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set screen capture disabled update");
                        break;
                    default:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set tiles hidden updated: " + list);
                        CustomizeFeatureOption.sHiddenQsTilesList = list;
                        break;
                }
            }
        };
        final int i3 = 2;
        sNoDisturbDisabledbserver = new OplusDevicepolicyManager.OplusDevicePolicyObserver() { // from class: com.oplusos.systemui.common.feature.CustomizeFeatureOption.1
            public final void onOplusDevicePolicyUpdate(String str, String str2) {
                switch (i3) {
                    case 0:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set auto brightness force mode updated: " + str2);
                        CustomizeFeatureOption.sIsAutoBrightnessDisabled = Boolean.valueOf(str2).booleanValue();
                        break;
                    case 1:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set brightness sliders updated: " + str2);
                        CustomizeFeatureOption.sIsBrightnessSlidersDisabled = Boolean.valueOf(str2).booleanValue();
                        break;
                    case 2:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set no disturb updated: " + str2);
                        CustomizeFeatureOption.sIsNoDisturbDisabled = Boolean.valueOf(str2).booleanValue();
                        break;
                    case 3:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set screen capture disabled update: " + str2);
                        CustomizeFeatureOption.sIsScreenCaptureDisabled = Boolean.parseBoolean(str2);
                        break;
                    default:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set tiles hidden updated");
                        break;
                }
            }

            public final void onOplusDevicePolicyUpdate(String str, List list) {
                switch (i3) {
                    case 0:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set auto brightness force mode updated");
                        break;
                    case 1:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set brightness sliders updated");
                        break;
                    case 2:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set no disturb updated");
                        break;
                    case 3:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set screen capture disabled update");
                        break;
                    default:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set tiles hidden updated: " + list);
                        CustomizeFeatureOption.sHiddenQsTilesList = list;
                        break;
                }
            }
        };
        final int i4 = 3;
        sScreenCaptureDisabledObserver = new OplusDevicepolicyManager.OplusDevicePolicyObserver() { // from class: com.oplusos.systemui.common.feature.CustomizeFeatureOption.1
            public final void onOplusDevicePolicyUpdate(String str, String str2) {
                switch (i4) {
                    case 0:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set auto brightness force mode updated: " + str2);
                        CustomizeFeatureOption.sIsAutoBrightnessDisabled = Boolean.valueOf(str2).booleanValue();
                        break;
                    case 1:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set brightness sliders updated: " + str2);
                        CustomizeFeatureOption.sIsBrightnessSlidersDisabled = Boolean.valueOf(str2).booleanValue();
                        break;
                    case 2:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set no disturb updated: " + str2);
                        CustomizeFeatureOption.sIsNoDisturbDisabled = Boolean.valueOf(str2).booleanValue();
                        break;
                    case 3:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set screen capture disabled update: " + str2);
                        CustomizeFeatureOption.sIsScreenCaptureDisabled = Boolean.parseBoolean(str2);
                        break;
                    default:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set tiles hidden updated");
                        break;
                }
            }

            public final void onOplusDevicePolicyUpdate(String str, List list) {
                switch (i4) {
                    case 0:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set auto brightness force mode updated");
                        break;
                    case 1:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set brightness sliders updated");
                        break;
                    case 2:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set no disturb updated");
                        break;
                    case 3:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set screen capture disabled update");
                        break;
                    default:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set tiles hidden updated: " + list);
                        CustomizeFeatureOption.sHiddenQsTilesList = list;
                        break;
                }
            }
        };
        final int i5 = 4;
        sHiddenQsTilesObserver = new OplusDevicepolicyManager.OplusDevicePolicyObserver() { // from class: com.oplusos.systemui.common.feature.CustomizeFeatureOption.1
            public final void onOplusDevicePolicyUpdate(String str, String str2) {
                switch (i5) {
                    case 0:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set auto brightness force mode updated: " + str2);
                        CustomizeFeatureOption.sIsAutoBrightnessDisabled = Boolean.valueOf(str2).booleanValue();
                        break;
                    case 1:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set brightness sliders updated: " + str2);
                        CustomizeFeatureOption.sIsBrightnessSlidersDisabled = Boolean.valueOf(str2).booleanValue();
                        break;
                    case 2:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set no disturb updated: " + str2);
                        CustomizeFeatureOption.sIsNoDisturbDisabled = Boolean.valueOf(str2).booleanValue();
                        break;
                    case 3:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set screen capture disabled update: " + str2);
                        CustomizeFeatureOption.sIsScreenCaptureDisabled = Boolean.parseBoolean(str2);
                        break;
                    default:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set tiles hidden updated");
                        break;
                }
            }

            public final void onOplusDevicePolicyUpdate(String str, List list) {
                switch (i5) {
                    case 0:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set auto brightness force mode updated");
                        break;
                    case 1:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set brightness sliders updated");
                        break;
                    case 2:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set no disturb updated");
                        break;
                    case 3:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set screen capture disabled update");
                        break;
                    default:
                        LogUtil.d(LogUtil.TAG_QS, "CustomizeFeatureOption", "set tiles hidden updated: " + list);
                        CustomizeFeatureOption.sHiddenQsTilesList = list;
                        break;
                }
            }
        };
    }

    public static boolean checkSlotIdValid(int i) {
        if (i >= 0 && i <= 1) {
            return true;
        }
        Log.i("CustomizeFeatureOption", "invalid slotId:" + i);
        return false;
    }

    public static ArrayList<String> getAppFeatureCacheList() {
        return APP_FEATURE_CACHE_LIST;
    }

    public static boolean getForbidRecordScreenState() {
        return sIsScreenCaptureDisabled;
    }

    public static List<String> getHiddenQsTilesList() {
        List<String> list = sHiddenQsTilesList;
        return list != null ? list : Collections.EMPTY_LIST;
    }

    public static List<String> getShowFlashlightNotificationPackageList() {
        return sShowFlashLightNotificationPackages;
    }

    public static boolean isSupportShowHDPLUS(int i) {
        if (!checkSlotIdValid(i) || sIsSystemUiExpSignalUi) {
            return false;
        }
        return sIsSupportShowHDPLUS[i];
    }

    public static void loadAppFeature(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        AppFeatureProviderUtils.FeatureID featureID = AppFeatureProviderUtils.FeatureID.STATIC_COMPONENT;
        sIsSupportNetworkStatus = AppFeatureProviderUtils.isFeatureSupport(contentResolver, featureID, "com.android.systemui.keyguard_show_networkstatus");
        sIsSupportAtLeast3G = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.keyguard_show_at_least_3g");
        sIsLetterVowifiIcon = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.letter_vowifi_icon");
        AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), "com.android.systemui.navigationbar_disable_in_startapp");
        sIsSupportShowPinpukRemains = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.keyguard_show_pinpukremains");
        sIsSupportShowOperatorNotice = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.keyguard_show_operatornotice");
        sIsAirplanemodeConfirm = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.airplanemode_second_confirm");
        AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), "com.android.systemui.statusbar_g_in_gprs");
        sIsVirtualkeyRecentRightOfHome = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.virtualkey_recent_right_of_home");
        AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), "com.android.systemui.signal_layout_att");
        sIsSupportHidePukInput = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.keyguard_hide_pukinput");
        sHasSupportMoreGestureUp = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.support_more_swipup_gestures");
        sKeepGestureUp = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.keep_swipup_gestures");
        AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), "com.android.systemui.kddi_show_au");
        sIsSupportEsimCard = OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.radio.esim_support");
        sIsShowBig5G = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.statusbar_show_big_5g");
        AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), "com.android.systemui.show_5g_anim_in_lock_screen");
        sIsShowCtaNoSimView = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.statusbar_show_cta_nosim");
        sIsDisableOtgAutoClose = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.disable_otg_auto_close");
        sIsNotShowNfcNotification = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.not_show_nfc_notification");
        AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), "com.android.systemui.pocketmode_unsupported");
        sHideOrganizationMsg = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.hide_organization_message");
        AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_ust_hotspot");
        sIsShowCarrierAirWfc = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_carrier_air_wfc");
        sIsShowSprintHotSpotDia = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_sprint_hotspot_dia");
        sIsTmoSignalViewLayout = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.tmo_signal_layout");
        sIsSprintSignalViewLayout = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.sprint_signal_layout");
        sLOLFeature = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.lol_special");
        sIsSupportCustomizePasspointIcon = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.oplus.wirelesssettings.customize_passpoint_icon");
        sFloatAssistantFeature = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.oplus.floatassistant.assistive_ball_disable");
        sIsShowOperatorName = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_operator_name");
        sIsSupportProtectServices = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.oplus.systemui.customize_protect_services");
        sIsSupportMiniApp = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "oplus.software.support_secondary_mini_app");
        sIsSupportThreekeyVibration = context.getPackageManager().hasSystemFeature("oplus.software.vibration_threestage_key");
        sIsShowBig5GUWC = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.statusbar_show_big_5guwc");
        sIsShowWifi6IconForCtTest = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.statusbar_show_wifi6_for_ct");
        AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), "com.oplus.floatassistant.air_view_disable");
        Log.i("CustomizeFeatureOption", "loadAppFeature sIsShowWifi6IconForCtTest= " + sIsShowWifi6IconForCtTest + ", sHideOrganizationMsg= " + sHideOrganizationMsg);
        sIsWallpaperScaleDefaultOn = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), "os.personalization.wallpaper.scale.default", true);
        sIsWallpaperNewAnimDisable = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "oplus.wallpaper.new_anim.disable");
        sIsAutoBrightnessDisabled = OplusDevicepolicyManager.getInstance().getBoolean("oplus.software.customize.auto_brightness_force_mode", 1, false);
        sIsBrightnessSlidersDisabled = OplusDevicepolicyManager.getInstance().getBoolean("auto_brightness_switch_status", 1, false);
        sIsNoDisturbDisabled = OplusDevicepolicyManager.getInstance().getBoolean("NO_DISTURB_DISABLED", 1, false);
        sIsScreenCaptureDisabled = OplusDevicepolicyManager.getInstance().getBoolean("persist.sys.oplus.customize.forbcap", 1, false);
        sHiddenQsTilesList = OplusDevicepolicyManager.getInstance().getList("custom_sysui_qs_hide_tiles_list", 1);
        sIsShowEMEWhenNoservice = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.phone.em_service_show_in_simpin_state");
        sShowFlashLightNotificationPackages = AppFeatureProviderUtils.getStringList(context.getContentResolver(), "oplus.software.show.flashlight_notification_packages");
        sIsGetWallpaperFeature = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "oplus.wallpaper.should_update_wallpaper");
        sIsSupportGeminiGone = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.gemini_icon_gone_custom");
        sHideVolteOperator = AppFeatureProviderUtils.getStringList(context.getContentResolver(), "com.android.systemui.satellite_hide_volte");
        sIsSupportShowWifi6E = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_wifi_6e");
        sIsHidePictorial = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.hide_pictorial");
        AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), "com.android.systemui.sep_show_spn");
        sAntitheftRlockSupport = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.oplus.rom.antitheft_rlock_support");
        sIsYandexExperience = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.oplus.assistant.yandex_guide");
        sIsDefaultAssistantFeatureEnable = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "oplus.software.default_assistant");
    }

    public static void loadAppFeatureForSlotId(Context context, AppFeatureProviderUtils.FeatureID featureID) {
        Log.i("CustomizeFeatureOption", "loadAppFeatureForSlotId featureid: " + featureID);
        AppFeatureProviderUtils.enableAppFeatureCache(context.getContentResolver(), featureID, sSlotAppFeatureList);
        AppFeatureProviderUtils.FeatureID featureID2 = AppFeatureProviderUtils.FeatureID.DYNAMIC_SIMSLOT_1;
        sIsSupportHideVoWifiIcon[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.hide_vowifi_icon");
        sIsSupportHideVoLTEIcon[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.hide_volte_icon");
        sIsSupportHideRoamingIcon[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.hide_roaming_icon");
        sIsSupportShowGForGPRS[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_g_for_gprs");
        sIsSupportShow2GForE[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_2g_for_e");
        sIsSupportShow3GForH[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_3g_for_h");
        sIsSupportShow3GForHPlus[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_3g_for_hplus");
        sIsSupportShowLTEFor4G[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_lte_for_4g");
        sIsSupportShowLTEFor4GPlus[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_lte_for_4gplus");
        sIsSupportShow4Dot5GFor4G[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_4dot5g_for_4g");
        sIsSupportShow4Dot5GFor4GPlus[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_4dot5g_for_4gplus");
        sIsSupportShowLTEPlusFor4GPlus[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_lteplus_for_4gplus");
        sIsSupportShowGFor2G[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_g_for_2g");
        sIsSupportShow3GPlusForH[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_3gplus_for_h");
        sIsSupportShowHForHPlus[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_h_for_hplus");
        sIsSupportShow4GFor4GPlus[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_4g_for_4gplus");
        sIsSupportShowLineNumberCust[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_linenumber_cust");
        sIsSupportGPSTileShowVoWifiDialog[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.gps_tile_vowifi_dialog");
        sIsSupportShowDataPrompting[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_data_prompting");
        sTetheringEntitlement[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.getInt(context.getContentResolver(), featureID, "com.android.systemui.support_tethering_entitlement", -1);
        sVZWTethering[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.support_vzw_tethering_check");
        sIsSoftBankVoiceType[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.softbank_voice_type");
        sIsSoftBankSim[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "oplus.software.connectivity.absent_card_stop_tethering");
        sIsSupportShowVoltefForVo5G[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_volte_for_vo5g");
        sIsSupportShowVoNR[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_vonr");
        sIsSupportShowHDPLUS[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.show_hdplus");
        sIsTigo5GIconSupport[featureID == featureID2 ? (char) 0 : (char) 1] = AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, "com.android.systemui.tigo_5g_icon");
    }

    public static void loadOplusFeatureForSlotId(IOplusFeatureConfigManager.FeatureID featureID) {
        IOplusFeatureConfigManager.FeatureID featureID2 = IOplusFeatureConfigManager.FeatureID.DYNAMIC_SIMSLOT_1;
        if (featureID != featureID2 && featureID != IOplusFeatureConfigManager.FeatureID.DYNAMIC_SIMSLOT_2) {
            Log.i("CustomizeFeatureOption", "loadOplusFeatureForSlotId invalid featureid= " + featureID.ordinal());
            return;
        }
        OplusFeatureConfigManager oplusFeatureConfigManager = OplusFeatureConfigManager.getInstance();
        int i = featureID == featureID2 ? 0 : 1;
        boolean zHasFeature = oplusFeatureConfigManager.hasFeature("oplus.software.radio.icon_nrad_5guc", featureID);
        boolean[] zArr = sIsIconNrad5guc;
        zArr[i] = zHasFeature;
        boolean zHasFeature2 = oplusFeatureConfigManager.hasFeature("oplus.software.radio.icon_nrad_5guw", featureID);
        boolean[] zArr2 = sIsIconNrad5guw;
        zArr2[i] = zHasFeature2;
        boolean zHasFeature3 = oplusFeatureConfigManager.hasFeature("oplus.software.radio.icon_nrad_5gplus", featureID);
        boolean[] zArr3 = sIsIconNrad5gPlus;
        zArr3[i] = zHasFeature3;
        boolean zHasFeature4 = oplusFeatureConfigManager.hasFeature("oplus.software.radio.icon_ltead_5ge", featureID);
        boolean[] zArr4 = sIsIconLtead5ge;
        zArr4[i] = zHasFeature4;
        boolean zHasFeature5 = oplusFeatureConfigManager.hasFeature("oplus.software.radio.icon_nrad_5ga", featureID);
        boolean[] zArr5 = sIsIconNrad5ga;
        zArr5[i] = zHasFeature5;
        boolean zHasFeature6 = oplusFeatureConfigManager.hasFeature("oplus.software.radio.icon_ltead_four_point_five_g_plus", featureID);
        boolean[] zArr6 = sIsIconFourDot5g;
        zArr6[i] = zHasFeature6;
        boolean zHasFeature7 = oplusFeatureConfigManager.hasFeature("oplus.software.radio.icon_nrad_5gplus_plus", featureID);
        boolean[] zArr7 = sIsIconNrad5gPlusPlus;
        zArr7[i] = zHasFeature7;
        boolean zHasFeature8 = oplusFeatureConfigManager.hasFeature("oplus.software.radio.icon_nrad_5gdot_5g", featureID);
        boolean[] zArr8 = sIsIconNradFiveDot5g;
        zArr8[i] = zHasFeature8;
        StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(i, "slot= ", ",sIsIconNrad5guc= ");
        sbM.append(zArr[i]);
        sbM.append(",sIsIconNrad5guw= ");
        sbM.append(zArr2[i]);
        sbM.append(",sIsIconNrad5gPlus= ");
        sbM.append(zArr3[i]);
        sbM.append(",sIsIconLtead5ge= ");
        sbM.append(zArr4[i]);
        sbM.append(",sIsIconNrad5ga= ");
        sbM.append(zArr5[i]);
        sbM.append(",sIsIconFourDot5g= ");
        sbM.append(zArr6[i]);
        sbM.append(",sIsIconNrad5gPlusPlus=");
        sbM.append(zArr7[i]);
        sbM.append(",sIsIconNradFiveDot5g= ");
        sbM.append(zArr8[i]);
        Log.i("CustomizeFeatureOption", sbM.toString());
    }

    public static void setFeatureChangeCallBack(CustomFeatureChangeCallBack customFeatureChangeCallBack) {
        sFeatureChangeCallBacks.add(customFeatureChangeCallBack);
    }

    public static void setSupportWorTileAnime(boolean z) {
        sIsSupportWorTileAnime = z;
    }
}

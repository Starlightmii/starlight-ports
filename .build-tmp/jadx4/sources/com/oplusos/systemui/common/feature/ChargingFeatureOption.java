package com.oplusos.systemui.common.feature;

import android.content.Context;
import android.os.OplusPropertyList;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import com.oplus.coreapp.appfeature.AppFeatureProviderUtils;
import java.util.List;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ChargingFeatureOption {
    private static final String CHARGE_ANIM_GUNDAM = "ro.vendor.oplus.gundam";
    private static final String DEVICE_19161 = "19161";
    private static final String EVA_PHONE_COLOR = "00FFF003";
    private static final String HW_PHONE_COLOR = "ro.hw.phone.color";
    public static boolean isHideWireLessChargeNotification = false;
    private static final Lazy isSupportEvaVoocAnimation$delegate;
    public static boolean isSupportFullScreenChargeAnim = true;
    private static final Lazy isSupportFullscreenChargeAnim$delegate;
    private static final Lazy isSupportGundamAnimation$delegate;
    public static boolean isSupportWirelessCharging;
    public static final ChargingFeatureOption INSTANCE = new ChargingFeatureOption();
    private static final String SMART_CHARGE_THRESHOLD = "com.android.systemui.smart_charge_threshold";
    private static final String OPLUS_SUPPORT_WIRELESS_CHARGE_NOTIFICATE = "os.charge.settings.wirelesschargingcoil.position";
    private static final String SMART_CHARGE_OPEN = "os.charge.settings.smartchargeswitch.open";
    private static final String SUPPORT_WIRELESS_CHARGING = "oplus.power.onwirelesscharger.support";
    private static final List<String> appFeatureList = CollectionsKt__CollectionsKt.listOf(new String[]{SMART_CHARGE_THRESHOLD, OPLUS_SUPPORT_WIRELESS_CHARGE_NOTIFICATE, SMART_CHARGE_OPEN, SUPPORT_WIRELESS_CHARGING});

    static {
        final int i = 0;
        isSupportFullscreenChargeAnim$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.ChargingFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        ChargingFeatureOption chargingFeatureOption = ChargingFeatureOption.INSTANCE;
                        return Boolean.TRUE;
                    case 1:
                        return Boolean.valueOf("19161".equals(Objects.toString(OplusPropertyList.SEPARATE_SOFT, "")) && "00FFF003".equals(SystemProperties.get("ro.hw.phone.color", "")));
                    default:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.gundam", "false").equals("true"));
                }
            }
        });
        final int i2 = 1;
        isSupportEvaVoocAnimation$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.ChargingFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        ChargingFeatureOption chargingFeatureOption = ChargingFeatureOption.INSTANCE;
                        return Boolean.TRUE;
                    case 1:
                        return Boolean.valueOf("19161".equals(Objects.toString(OplusPropertyList.SEPARATE_SOFT, "")) && "00FFF003".equals(SystemProperties.get("ro.hw.phone.color", "")));
                    default:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.gundam", "false").equals("true"));
                }
            }
        });
        final int i3 = 2;
        isSupportGundamAnimation$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.ChargingFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i3) {
                    case 0:
                        ChargingFeatureOption chargingFeatureOption = ChargingFeatureOption.INSTANCE;
                        return Boolean.TRUE;
                    case 1:
                        return Boolean.valueOf("19161".equals(Objects.toString(OplusPropertyList.SEPARATE_SOFT, "")) && "00FFF003".equals(SystemProperties.get("ro.hw.phone.color", "")));
                    default:
                        return Boolean.valueOf(SystemProperties.get("ro.vendor.oplus.gundam", "false").equals("true"));
                }
            }
        });
    }

    public static boolean isSupportFullscreenChargeAnim() {
        return ((Boolean) isSupportFullscreenChargeAnim$delegate.getValue()).booleanValue();
    }

    public final List<String> getAppFeatureCacheList() {
        return appFeatureList;
    }

    public final void loadAppFeature(Context context) {
        float f;
        boolean zHasSystemFeature = context.getPackageManager().hasSystemFeature(SUPPORT_WIRELESS_CHARGING);
        AppFeatureProviderUtils.FeatureID featureID = AppFeatureProviderUtils.FeatureID.STATIC_COMPONENT;
        isSupportWirelessCharging = zHasSystemFeature || AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), featureID, SUPPORT_WIRELESS_CHARGING);
        String stringForFeature = AppFeatureProviderUtils.getStringForFeature(context.getContentResolver(), featureID, OPLUS_SUPPORT_WIRELESS_CHARGE_NOTIFICATE, "float");
        if (TextUtils.isEmpty(stringForFeature)) {
            Log.e("AppFeatureProviderUtils", "getFloat: getStringForFeature return null");
            f = -1.0f;
        } else {
            f = Float.parseFloat(stringForFeature);
        }
        isHideWireLessChargeNotification = f >= 0.0f;
        AppFeatureProviderUtils.isFeatureSupport(context.getContentResolver(), SMART_CHARGE_OPEN);
    }

    public final void setSupportFullScreenChargeAnim(boolean z) {
        isSupportFullScreenChargeAnim = z;
    }
}

package com.oplusos.systemui.common.feature;

import android.content.Context;
import com.oplus.coreapp.appfeature.AppFeatureProviderUtils;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UsbFeatureOption {
    public static boolean isAdbNeedSimAuto;
    public static boolean isAdbNeedSimManual;
    public static boolean isUsbServiceForCta;
    public static final UsbFeatureOption INSTANCE = new UsbFeatureOption();
    private static final String USB_SERVICE_FOR_CTA = "com.android.systemui.usb_service_for_cta";
    private static final String FEATURE_ADB_NEED_SIM_AUTO = "com.android.systemui.adb_need_sim_auto";
    private static final String FEATURE_ADB_NEED_SIM_MANUAL = "com.android.systemui.adb_need_sim_manual";
    private static final List<String> appFeatureList = CollectionsKt__CollectionsKt.listOf(new String[]{USB_SERVICE_FOR_CTA, FEATURE_ADB_NEED_SIM_AUTO, FEATURE_ADB_NEED_SIM_MANUAL});

    public final List<String> getAppFeatureCacheList() {
        return appFeatureList;
    }

    public final void loadAppFeature(Context context) {
        isUsbServiceForCta = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), USB_SERVICE_FOR_CTA, false);
        isAdbNeedSimAuto = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), FEATURE_ADB_NEED_SIM_AUTO, false);
        isAdbNeedSimManual = AppFeatureProviderUtils.getBoolean(context.getContentResolver(), FEATURE_ADB_NEED_SIM_MANUAL, false);
    }
}

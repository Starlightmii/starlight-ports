package com.oplus.systemui.notification.utlils;

import android.content.Context;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DeviceStateUtils {
    public static final DeviceStateUtils INSTANCE = new DeviceStateUtils();

    public static final boolean isLandInSmallScreen(Context context, boolean z) {
        if (z) {
            DeviceStateController deviceStateController = DeviceStateController.getInstance();
            deviceStateController.getClass();
            FeatureOption.INSTANCE.getClass();
            boolean z2 = FeatureOption.isLargeScreenFolding() && !deviceStateController.isFolding();
            if (!FeatureOption.isTablet() && !z2) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isPortraitInSmallScreen(Context context, boolean z) {
        if (z) {
            DeviceStateController deviceStateController = DeviceStateController.getInstance();
            deviceStateController.getClass();
            FeatureOption.INSTANCE.getClass();
            boolean z2 = FeatureOption.isLargeScreenFolding() && !deviceStateController.isFolding();
            if (!FeatureOption.isTablet() && !z2) {
                return true;
            }
        }
        return false;
    }
}

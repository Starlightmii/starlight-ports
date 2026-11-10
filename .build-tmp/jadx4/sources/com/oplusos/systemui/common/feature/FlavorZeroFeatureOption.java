package com.oplusos.systemui.common.feature;

import com.oplusos.systemui.common.util.BrandUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class FlavorZeroFeatureOption {
    public static boolean isFlavorZeroDevice() {
        BrandUtils.INSTANCE.getClass();
        return BrandUtils.isOppo;
    }

    public static boolean isFlavorZeroLightOsDevice() {
        if (!isFlavorZeroDevice()) {
            return false;
        }
        FeatureOption.INSTANCE.getClass();
        return FeatureOption.isLightOs();
    }
}

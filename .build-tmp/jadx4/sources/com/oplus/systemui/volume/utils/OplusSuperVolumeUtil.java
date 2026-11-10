package com.oplus.systemui.volume.utils;

import com.oplus.zoom.animation.ZoomAnimationUtils;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSuperVolumeUtil {
    public static final OplusSuperVolumeUtil INSTANCE = new OplusSuperVolumeUtil();
    private static final String TAG = "OplusSuperVolumeUtil";

    public static final int getOplusSuperVolumeLevel() {
        VolumeFeatureOption.INSTANCE.getClass();
        if (VolumeFeatureOption.isOplusSuperVolumeMode4x()) {
            return 400;
        }
        if (VolumeFeatureOption.isOplusSuperVolumeMode3x()) {
            return 300;
        }
        if (VolumeFeatureOption.isOplusSuperVolumeMode2x()) {
            return ZoomAnimationUtils.TRANSIT_SCALE_AND_POSITION;
        }
        return 100;
    }
}

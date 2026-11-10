package com.oplus.systemui.opthreekey.utils;

import android.content.res.Resources;
import com.oplusos.systemui.common.feature.FeatureOption;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OpVolumeUtils {
    public static int sOplusScreenResolution;

    static {
        FeatureOption.INSTANCE.getClass();
        FeatureOption.isPanicVersion();
    }

    public static int getDimensionPixelSize(Resources resources, int i, int i2) {
        if (resources == null) {
            return 0;
        }
        int dimensionPixelSize = resources.getDimensionPixelSize(i);
        if (!resources.getBoolean(2131034184)) {
            int i3 = sOplusScreenResolution;
            if (i3 == 1440) {
                return Math.round((dimensionPixelSize * (i3 != 1440 ? 1080 : 1440)) / i2);
            }
        }
        return dimensionPixelSize;
    }

    public static int getScreenResolution() {
        return sOplusScreenResolution;
    }
}

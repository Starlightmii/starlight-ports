package com.oplus.systemui.statusbar.location;

import android.content.Context;
import android.provider.DeviceConfig;
import com.android.systemui.statusbar.location.OplusLocationControllerEx;
import com.android.systemui.util.DeviceConfigProxy;
import com.oplusos.systemui.common.feature.FeatureOption;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusLocationControllerExImpl extends OplusLocationControllerEx {
    private final Context context;

    public OplusLocationControllerExImpl(Context context) {
        this.context = context;
    }

    public boolean getAllAccessesSetting(DeviceConfigProxy deviceConfigProxy) {
        FeatureOption.INSTANCE.getClass();
        boolean z = !FeatureOption.isExpRegion();
        deviceConfigProxy.getClass();
        return DeviceConfig.getBoolean("privacy", "location_indicators_small_enabled", z);
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean hadActiveLocationRequests(boolean z, boolean z2) {
        FeatureOption.INSTANCE.getClass();
        return (!FeatureOption.isExpRegion() && z2) || z;
    }
}

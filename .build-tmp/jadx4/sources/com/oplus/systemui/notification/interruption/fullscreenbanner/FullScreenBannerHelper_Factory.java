package com.oplus.systemui.notification.interruption.fullscreenbanner;

import android.content.Context;
import com.android.systemui.statusbar.policy.ConfigurationController;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class FullScreenBannerHelper_Factory implements Factory {
    public static FullScreenBannerHelper newInstance(Context context, ConfigurationController configurationController) {
        return new FullScreenBannerHelper(context, configurationController);
    }
}

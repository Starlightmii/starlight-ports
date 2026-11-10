package com.oplus.systemui.utils;

import android.content.res.Configuration;
import android.content.res.OplusBaseConfigurationAddon;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusThemeControllerKt {
    public static final boolean isOplusCustomTheme(Configuration configuration) {
        return (oplusThirdThemeChangedFlag(configuration) & 1) != 0;
    }

    public static final int oplusThirdThemeChange(Configuration configuration) {
        return OplusBaseConfigurationAddon.getThemeChanged(configuration);
    }

    public static final long oplusThirdThemeChangedFlag(Configuration configuration) {
        return OplusBaseConfigurationAddon.getThemeChangedFlags(configuration);
    }
}

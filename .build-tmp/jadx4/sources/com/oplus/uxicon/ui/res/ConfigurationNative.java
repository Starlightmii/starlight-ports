package com.oplus.uxicon.ui.res;

import android.content.res.Configuration;
import android.content.res.OplusBaseConfiguration;
import android.util.Log;
import com.android.launcher3.icons.IconProvider$;
import oplus.content.res.OplusExtraConfiguration;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ConfigurationNative {
    public static long getThemeChangedFlags(Configuration configuration) {
        OplusExtraConfiguration oplusExtraConfiguration;
        try {
            if (!OplusBaseConfiguration.class.isInstance(configuration)) {
                configuration = null;
            }
            if (configuration != null && (oplusExtraConfiguration = ((OplusBaseConfiguration) configuration).mOplusExtraConfiguration) != null) {
                return oplusExtraConfiguration.mThemeChangedFlags;
            }
            StringBuilder sb = new StringBuilder("getThemeChangedFlags maybe null:");
            sb.append(configuration == null);
            Log.e("ConfigurationNative", sb.toString());
            return -1L;
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("getThemeChangedFlags error:"), "ConfigurationNative");
            return -1L;
        }
    }

    public static long getUxIconConfig(Configuration configuration) {
        OplusExtraConfiguration oplusExtraConfiguration;
        try {
            if (!OplusBaseConfiguration.class.isInstance(configuration)) {
                configuration = null;
            }
            if (configuration != null && (oplusExtraConfiguration = ((OplusBaseConfiguration) configuration).mOplusExtraConfiguration) != null) {
                return oplusExtraConfiguration.mUxIconConfig;
            }
            StringBuilder sb = new StringBuilder("getUxIconConfig maybe null:");
            sb.append(configuration == null);
            Log.e("ConfigurationNative", sb.toString());
            return -1L;
        } catch (Exception e) {
            IconProvider$.ExternalSyntheticOutline0.m(e, new StringBuilder("getUxIconConfig error:"), "ConfigurationNative");
            return -1L;
        }
    }
}

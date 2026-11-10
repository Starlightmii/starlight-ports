package com.oplus.systemui.qs.observer;

import android.content.Context;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class AccessOplusSettingValueProxy extends AbsSettingsValueProxy {
    public static final Companion Companion = new Companion();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final int getAccessOplusSettingValue(Context context) {
            return AbsSettingsValueProxy.Companion.getSystemIntValue(context, "access_color_setting", -1);
        }
    }

    public static final int getAccessOplusSettingValue(Context context) {
        return Companion.getAccessOplusSettingValue(context);
    }
}

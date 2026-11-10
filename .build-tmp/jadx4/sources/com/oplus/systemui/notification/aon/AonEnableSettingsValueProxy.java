package com.oplus.systemui.notification.aon;

import android.content.Context;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class AonEnableSettingsValueProxy extends AbsSettingsValueProxy {
    public static final Companion Companion = new Companion();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final boolean getAonSwitchState(Context context) {
            return AbsSettingsValueProxy.Companion.getSecureIntValue(context, AonEnableObserver.SMART_ANTI_VOYEUR_ENABLE, -1) == 1;
        }

        public final void setAonSwitchState(Context context, int i) {
            if (i == 0 || i == 1) {
                AbsSettingsValueProxy.Companion.setSecureIntValue(context, AonEnableObserver.SMART_ANTI_VOYEUR_ENABLE, i);
            }
        }
    }
}

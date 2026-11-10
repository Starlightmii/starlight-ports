package com.oplus.systemui.statusbar.receiver;

import com.oplusos.systemui.common.receiver.AbstractReceiver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class CustomPowerReceiver extends AbstractReceiver {
    public static final String ACTION_TURN_ON_SAVE_POWER_MODE = "oplus.systemui.OPEN_SAVE_POWER_MODE";
    public static final Companion Companion = new Companion();
    public static final String SUPER_POWER_SAVE_FUNC = "super_powersave_on";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
    public String[] getActions() {
        return new String[]{ACTION_TURN_ON_SAVE_POWER_MODE, SUPER_POWER_SAVE_FUNC};
    }
}

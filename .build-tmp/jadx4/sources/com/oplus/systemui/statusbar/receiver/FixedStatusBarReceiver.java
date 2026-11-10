package com.oplus.systemui.statusbar.receiver;

import com.oplusos.systemui.common.receiver.AbstractReceiver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FixedStatusBarReceiver extends AbstractReceiver {
    public static final String ACTION_FIXED_STATUS_BAR = "com.oplusos.screenrecorder.FIXED_STATUS_BAR";
    public static final String EXTRA_FIXED_STATUS_BAR = "recording_settings_fixed_status_bar";
    public static final String EXTRA_FIXED_STATUS_BAR_TIME = "recording_settings_fixed_status_bar_time";
    public static final Companion Companion = new Companion();
    private static final FixedStatusBarReceiver instance = new FixedStatusBarReceiver();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final FixedStatusBarReceiver getInstance() {
            return FixedStatusBarReceiver.instance;
        }
    }

    @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
    public String[] getActions() {
        return new String[]{ACTION_FIXED_STATUS_BAR};
    }
}

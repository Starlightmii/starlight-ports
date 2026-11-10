package com.oplus.systemui.notification.headsup.dagger;

import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.LogBuffer;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpContainerWindow;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpLayout;
import com.oplus.systemui.notification.log.OplusNotificationEchoTracker;
import com.oplus.zoom.ui.tips.TipsManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class HeadsUpModule {
    private static final String BUFFER_NAME = "OplusHeadsUpLogBuffer";
    public static final Companion Companion = new Companion();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final HeadsUpLayout bindHeadsUpLayout(HeadsUpContainerWindow headsUpContainerWindow) {
            return headsUpContainerWindow.providerHeadsUpLayout();
        }

        public final LogBuffer provideOplusHeadsUpLogBuffer(DumpManager dumpManager, OplusNotificationEchoTracker oplusNotificationEchoTracker) {
            LogBuffer logBuffer = new LogBuffer(HeadsUpModule.BUFFER_NAME, TipsManager.DELAY_SING_TAP_HIDE_TIME, oplusNotificationEchoTracker, false, 16);
            dumpManager.registerBuffer(HeadsUpModule.BUFFER_NAME, logBuffer);
            return logBuffer;
        }
    }
}

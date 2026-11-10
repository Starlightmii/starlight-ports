package com.oplus.systemui.notification.headsup.dagger;

import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.LogBuffer;
import com.oplus.systemui.notification.log.OplusNotificationEchoTracker;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class HeadsUpModule_Companion_ProvideOplusHeadsUpLogBufferFactory implements Factory {
    public static LogBuffer provideOplusHeadsUpLogBuffer(DumpManager dumpManager, OplusNotificationEchoTracker oplusNotificationEchoTracker) {
        LogBuffer logBufferProvideOplusHeadsUpLogBuffer = HeadsUpModule.Companion.provideOplusHeadsUpLogBuffer(dumpManager, oplusNotificationEchoTracker);
        logBufferProvideOplusHeadsUpLogBuffer.getClass();
        return logBufferProvideOplusHeadsUpLogBuffer;
    }
}

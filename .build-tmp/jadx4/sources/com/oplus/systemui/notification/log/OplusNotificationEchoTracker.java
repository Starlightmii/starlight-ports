package com.oplus.systemui.notification.log;

import com.android.systemui.log.LogcatEchoTracker;
import com.android.systemui.log.core.LogLevel;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationEchoTracker implements LogcatEchoTracker {
    public boolean isBufferLoggable(String str, LogLevel logLevel) {
        return true;
    }

    public boolean isTagLoggable(String str, LogLevel logLevel) {
        return true;
    }
}

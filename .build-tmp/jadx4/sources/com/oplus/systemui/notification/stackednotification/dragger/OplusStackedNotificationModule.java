package com.oplus.systemui.notification.stackednotification.dragger;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogBufferFactory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStackedNotificationModule {
    public static final OplusStackedNotificationModule INSTANCE = new OplusStackedNotificationModule();
    private static final String STACKED_NOTIFICATION_LOG_BUFFER = "StackedNotificationLog";

    public final LogBuffer provideStackedNotificationLogBuffer(LogBufferFactory logBufferFactory) {
        return LogBufferFactory.create$default(logBufferFactory, STACKED_NOTIFICATION_LOG_BUFFER, 500, false, (String) null, 28);
    }
}

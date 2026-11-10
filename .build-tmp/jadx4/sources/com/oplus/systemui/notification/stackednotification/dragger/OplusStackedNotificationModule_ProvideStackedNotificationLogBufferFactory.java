package com.oplus.systemui.notification.stackednotification.dragger;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogBufferFactory;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusStackedNotificationModule_ProvideStackedNotificationLogBufferFactory implements Factory {
    public static LogBuffer provideStackedNotificationLogBuffer(LogBufferFactory logBufferFactory) {
        LogBuffer logBufferProvideStackedNotificationLogBuffer = OplusStackedNotificationModule.INSTANCE.provideStackedNotificationLogBuffer(logBufferFactory);
        logBufferProvideStackedNotificationLogBuffer.getClass();
        return logBufferProvideStackedNotificationLogBuffer;
    }
}

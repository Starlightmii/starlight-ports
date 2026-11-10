package com.oplus.systemui.log.dagger;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogBufferFactory;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class LogModuleExImpl_ProvideCommandQueueLogBufferFactory implements Factory {
    public static LogBuffer provideCommandQueueLogBuffer(LogBufferFactory logBufferFactory) {
        LogBuffer logBufferProvideCommandQueueLogBuffer = LogModuleExImpl.INSTANCE.provideCommandQueueLogBuffer(logBufferFactory);
        logBufferProvideCommandQueueLogBuffer.getClass();
        return logBufferProvideCommandQueueLogBuffer;
    }
}

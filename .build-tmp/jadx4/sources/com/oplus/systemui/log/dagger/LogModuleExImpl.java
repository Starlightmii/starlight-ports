package com.oplus.systemui.log.dagger;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogBufferFactory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LogModuleExImpl {
    public static final LogModuleExImpl INSTANCE = new LogModuleExImpl();

    public final LogBuffer provideCommandQueueLogBuffer(LogBufferFactory logBufferFactory) {
        return LogBufferFactory.create$default(logBufferFactory, "CommandQueueLog", 60, false, (String) null, 16);
    }
}

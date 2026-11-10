package com.oplus.systemui.separate.dagger;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogBufferFactory;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class QsGuideModule_Companion_ProvideSeparateQsGuideLogBufferFactory implements Factory {
    public static LogBuffer provideSeparateQsGuideLogBuffer(LogBufferFactory logBufferFactory) {
        LogBuffer logBufferProvideSeparateQsGuideLogBuffer = QsGuideModule.Companion.provideSeparateQsGuideLogBuffer(logBufferFactory);
        logBufferProvideSeparateQsGuideLogBuffer.getClass();
        return logBufferProvideSeparateQsGuideLogBuffer;
    }
}

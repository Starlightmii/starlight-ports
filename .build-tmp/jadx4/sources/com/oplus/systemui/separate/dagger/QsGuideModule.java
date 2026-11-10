package com.oplus.systemui.separate.dagger;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogBufferFactory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface QsGuideModule {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final LogBuffer provideSeparateQsGuideLogBuffer(LogBufferFactory logBufferFactory) {
            return LogBufferFactory.create$default(logBufferFactory, "QsGuideLog", 500, false, (String) null, 28);
        }
    }
}

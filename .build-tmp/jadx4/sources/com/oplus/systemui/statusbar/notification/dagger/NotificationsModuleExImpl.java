package com.oplus.systemui.statusbar.notification.dagger;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogBufferFactory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface NotificationsModuleExImpl {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final LogBuffer providePanelAnimationLogBuffer(LogBufferFactory logBufferFactory) {
            return LogBufferFactory.create$default(logBufferFactory, "LockscreenNotificationCenterLog", 1000, false, (String) null, 28);
        }
    }
}

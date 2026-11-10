package com.oplus.systemui.notification.debug;

import com.android.systemui.CoreStartable;
import com.android.systemui.InitController;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationDebug implements CoreStartable {
    private final InitController initController;
    private final LogBuffer loggerBuffer;
    private final NotifPipeline pipeline;
    public static final Companion Companion = new Companion();
    private static final List<String> BLACK_LIST = CollectionsKt__CollectionsJVMKt.listOf("com.oplus.olc");

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusNotificationDebug(NotifPipeline notifPipeline, InitController initController, LogBuffer logBuffer) {
        this.pipeline = notifPipeline;
        this.initController = initController;
        this.loggerBuffer = logBuffer;
    }

    public final boolean getSkipCheckPostedBeforeLock() {
        return false;
    }

    public final void start() {
    }
}

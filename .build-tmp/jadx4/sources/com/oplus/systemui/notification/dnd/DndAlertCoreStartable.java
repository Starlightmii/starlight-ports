package com.oplus.systemui.notification.dnd;

import com.android.systemui.CoreStartable;
import com.android.systemui.InitController;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionFilter;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionType;
import com.oplus.systemui.statusbar.notification.helper.DndAlertHelper;
import com.oplus.systemui.statusbar.seeding.SeedlingPluginManager;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Set;
import kotlin.collections.SetsKt__SetsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DndAlertCoreStartable implements CoreStartable {
    private static final Companion Companion = new Companion();
    private static final String TAG = "DndAlertCoreStartable";
    private Lazy dndAlertHelper;
    private final InitController initController;
    private final SeedlingPluginManager seedlingPluginManager;
    private final VisualInterruptionDecisionProvider visualInterruptionDecisionProvider;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.dnd.DndAlertCoreStartable$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ DndAlertCoreStartable this$0;

        @Override // java.lang.Runnable
        public final void run() {
            VisualInterruptionDecisionProvider visualInterruptionDecisionProvider = this.this$0.visualInterruptionDecisionProvider;
            Set of = SetsKt__SetsJVMKt.setOf(VisualInterruptionType.PEEK);
            final DndAlertCoreStartable dndAlertCoreStartable = this.this$0;
            visualInterruptionDecisionProvider.addFilter(new VisualInterruptionFilter(of) { // from class: com.oplus.systemui.notification.dnd.DndAlertCoreStartable.start.1.1
                public String reason = "dnd headsUp visible";

                public String getReason() {
                    return this.reason;
                }

                public final boolean shouldSuppress(NotificationEntry notificationEntry) {
                    Lazy dndAlertHelper;
                    DndAlertHelper dndAlertHelper2;
                    DndAlertCoreStartable dndAlertCoreStartable2 = dndAlertCoreStartable;
                    if (!dndAlertCoreStartable2.seedlingPluginManager.isPluginAvailable || (dndAlertHelper = dndAlertCoreStartable2.getDndAlertHelper()) == null || (dndAlertHelper2 = (DndAlertHelper) dndAlertHelper.get()) == null || !dndAlertHelper2.isDNDNoticeChannelNotification(notificationEntry)) {
                        return false;
                    }
                    this.reason = "Fluid dnd suppress";
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("suppress dnd headsUp, key=", notificationEntry.getKey(), "DndAlertCoreStartable-->", "SystemUi--Notification");
                    }
                    return true;
                }
            });
        }
    }

    public DndAlertCoreStartable(InitController initController, VisualInterruptionDecisionProvider visualInterruptionDecisionProvider, SeedlingPluginManager seedlingPluginManager) {
        this.initController = initController;
        this.visualInterruptionDecisionProvider = visualInterruptionDecisionProvider;
        this.seedlingPluginManager = seedlingPluginManager;
    }

    public final Lazy getDndAlertHelper() {
        return this.dndAlertHelper;
    }

    public final void setDndAlertHelper(Lazy lazy) {
        this.dndAlertHelper = lazy;
    }

    public final void start() {
        InitController initController = this.initController;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        initController.addPostInitTask(anonymousClass1);
    }
}

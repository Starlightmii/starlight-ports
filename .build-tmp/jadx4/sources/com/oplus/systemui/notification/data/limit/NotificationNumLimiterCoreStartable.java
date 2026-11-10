package com.oplus.systemui.notification.data.limit;

import android.os.Handler;
import com.android.systemui.CoreStartable;
import com.android.systemui.InitController;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationNumLimiterCoreStartable implements CoreStartable, NotifCollectionListener {
    private final InitController initController;
    private final Runnable limiterCheck;
    private final Handler mainHandler;
    private final NotificationLimiterController notificationLimiterController;
    private final NotifPipeline pipeline;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.data.limit.NotificationNumLimiterCoreStartable$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ NotificationNumLimiterCoreStartable this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.pipeline.addCollectionListener(this.this$0);
                    break;
                default:
                    this.this$0.notificationLimiterController.applyToLimiter();
                    break;
            }
        }
    }

    public NotificationNumLimiterCoreStartable(NotifPipeline notifPipeline, InitController initController, NotificationLimiterController notificationLimiterController, Handler handler) {
        this.pipeline = notifPipeline;
        this.initController = initController;
        this.notificationLimiterController = notificationLimiterController;
        this.mainHandler = handler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(1);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.limiterCheck = anonymousClass1;
    }

    public void onEntryAdded(NotificationEntry notificationEntry) {
        if (this.mainHandler.hasCallbacks(this.limiterCheck)) {
            return;
        }
        this.mainHandler.post(this.limiterCheck);
    }

    public final void start() {
        InitController initController = this.initController;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        initController.addPostInitTask(anonymousClass1);
    }
}

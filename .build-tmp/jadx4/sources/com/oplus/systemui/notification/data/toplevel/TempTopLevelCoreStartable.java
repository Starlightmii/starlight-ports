package com.oplus.systemui.notification.data.toplevel;

import com.android.systemui.CoreStartable;
import com.android.systemui.DependencyEx;
import com.android.systemui.InitController;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.VisualStabilityCoordinatorEx;
import com.android.systemui.statusbar.notification.collection.provider.VisualStabilityProvider;
import com.android.systemui.util.time.SystemClock;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TempTopLevelCoreStartable implements CoreStartable {
    public static final Companion Companion = new Companion();
    private final SystemClock clock;
    private final InitController initController;
    private final TempTopLevelCoreStartable$limiterPromoter$1 limiterPromoter = new TempTopLevelCoreStartable$limiterPromoter$1(this);
    private final NotifPipeline pipeline;
    private VisualStabilityCoordinatorEx visualStabilityCoordinatorEx;
    private final VisualStabilityProvider visualStabilityProvider;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.data.toplevel.TempTopLevelCoreStartable$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ TempTopLevelCoreStartable this$0;

        @Override // java.lang.Runnable
        public final void run() {
            this.this$0.pipeline.addPromoter(this.this$0.limiterPromoter);
        }
    }

    public TempTopLevelCoreStartable(NotifPipeline notifPipeline, InitController initController, VisualStabilityProvider visualStabilityProvider, SystemClock systemClock) {
        this.pipeline = notifPipeline;
        this.initController = initController;
        this.visualStabilityProvider = visualStabilityProvider;
        this.clock = systemClock;
    }

    public final VisualStabilityCoordinatorEx getVisualStabilityCoordinatorEx() {
        return this.visualStabilityCoordinatorEx;
    }

    public final void setVisualStabilityCoordinatorEx(VisualStabilityCoordinatorEx visualStabilityCoordinatorEx) {
        this.visualStabilityCoordinatorEx = visualStabilityCoordinatorEx;
    }

    public final void start() {
        this.visualStabilityCoordinatorEx = (VisualStabilityCoordinatorEx) DependencyEx.sDependency.getDependency(VisualStabilityCoordinatorEx.class);
        InitController initController = this.initController;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        initController.addPostInitTask(anonymousClass1);
    }
}

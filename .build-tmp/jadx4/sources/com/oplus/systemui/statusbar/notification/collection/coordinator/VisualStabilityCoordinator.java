package com.oplus.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.coordinator.Coordinator;
import com.android.systemui.statusbar.notification.collection.render.ShadeViewDifferEx;
import dagger.Lazy;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VisualStabilityCoordinator implements Coordinator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "VisualStabilityCoordinator";
    private final Lazy shadeViewDifferEx;
    private final VisualStabilityCoordinator$updateNotifFilter$1 updateNotifFilter = new VisualStabilityCoordinator$updateNotifFilter$1("VisualStabilityCoordinatorUpdate");

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator$attach$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ VisualStabilityCoordinator this$0;

        @Override // java.lang.Runnable
        public final void run() {
            this.this$0.updateNotifFilter.invalidateList(this.this$0.updateNotifFilter.getName());
        }
    }

    public VisualStabilityCoordinator(Lazy lazy) {
        this.shadeViewDifferEx = lazy;
    }

    public void attach(NotifPipeline notifPipeline) {
        ShadeViewDifferEx shadeViewDifferEx = (ShadeViewDifferEx) this.shadeViewDifferEx.get();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        shadeViewDifferEx.addAppUpdateListener(anonymousClass1);
        notifPipeline.addFinalizeFilter(this.updateNotifFilter);
    }
}

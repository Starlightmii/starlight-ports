package com.oplus.systemui.notification.icdf;

import android.os.Handler;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.inflation.BindEventManager;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import java.lang.invoke.VarHandle;
import java.util.Map;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SynergyCallBackHelper$updateRanking$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ SynergyCallBackHelper this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.icdf.SynergyCallBackHelper$updateRanking$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ Object $rankingMap;
        public /* synthetic */ SynergyCallBackHelper this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.getIcdfNotificationHelper().updateRanking((Map) this.$rankingMap);
                    break;
                default:
                    this.this$0.getIcdfNotificationHelper().onDeleteNotification(((NotificationEntry) this.$rankingMap).getKey());
                    SynergyCallBackHelper.access$updateRanking(this.this$0);
                    break;
            }
        }
    }

    public /* synthetic */ SynergyCallBackHelper$updateRanking$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = 0;
        switch (this.$r8$classId) {
            case 0:
                Map icdfRankingMap = this.this$0.getIcdfRankingMap();
                Handler workHandler = this.this$0.getWorkHandler();
                SynergyCallBackHelper synergyCallBackHelper = this.this$0;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(i);
                anonymousClass1.this$0 = synergyCallBackHelper;
                anonymousClass1.$rankingMap = icdfRankingMap;
                VarHandle.storeStoreFence();
                workHandler.post(anonymousClass1);
                break;
            case 1:
                this.this$0.getIcdfHeadsUpDecisionRecorder().clearAll();
                break;
            case 2:
                this.this$0.getIcdfHeadsUpDecisionRecorder().clearAll();
                NotificationStackScrollLayout view = ((NotificationStackScrollLayoutController) this.this$0.getIcdfNotificationHelper().getStackScrollLayoutControllerLazy().get()).getView();
                if (view != null) {
                    view.clearNotifications(false);
                }
                break;
            default:
                this.this$0.getMNotifPipeline().addCollectionListener(this.this$0.entryListener);
                BindEventManager bindEventManager = this.this$0.getBindEventManager();
                SynergyCallBackHelper synergyCallBackHelper2 = this.this$0;
                SynergyCallBackHelper$registerListeners$2$1 synergyCallBackHelper$registerListeners$2$1 = new SynergyCallBackHelper$registerListeners$2$1();
                synergyCallBackHelper$registerListeners$2$1.$tmp0 = synergyCallBackHelper2;
                VarHandle.storeStoreFence();
                bindEventManager.addListener(synergyCallBackHelper$registerListeners$2$1);
                break;
        }
    }
}

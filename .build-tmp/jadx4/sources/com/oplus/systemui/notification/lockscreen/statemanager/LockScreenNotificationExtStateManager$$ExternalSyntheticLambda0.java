package com.oplus.systemui.notification.lockscreen.statemanager;

import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class LockScreenNotificationExtStateManager$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ LockScreenNotificationExtStateManager f$0;

    public /* synthetic */ LockScreenNotificationExtStateManager$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager = this.f$0;
        switch (i) {
            case 0:
                LockScreenNotificationExtStateManager.Companion companion = LockScreenNotificationExtStateManager.Companion;
                LockScreenNotificationExtStateManager$$ExternalSyntheticLambda0 lockScreenNotificationExtStateManager$$ExternalSyntheticLambda0 = new LockScreenNotificationExtStateManager$$ExternalSyntheticLambda0(1);
                lockScreenNotificationExtStateManager$$ExternalSyntheticLambda0.f$0 = lockScreenNotificationExtStateManager;
                VarHandle.storeStoreFence();
                return new NotificationDisplayRectCallbackWrapper(lockScreenNotificationExtStateManager$$ExternalSyntheticLambda0);
            default:
                return ((NotificationStackScrollLayoutController) lockScreenNotificationExtStateManager.nsslController.get()).getView();
        }
    }
}

package com.oplus.systemui.notification.lockscreen.stack;

import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.oplus.systemui.notification.lockscreen.statistic.LockScreenStatisticUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StackCardControl$pointRunnable$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ StackCardControl this$0;

    public /* synthetic */ StackCardControl$pointRunnable$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                NotificationStackScrollLayout view = ((NotificationStackScrollLayoutController) this.this$0.nsslController.get()).getView();
                if (view != null) {
                    StackCardControl stackCardControl = this.this$0;
                    1.1 r1 = new 1.1();
                    r1.$it = view;
                    r1.this$0 = stackCardControl;
                    VarHandle.storeStoreFence();
                    view.postOnAnimation(r1);
                }
                break;
            default:
                LockScreenStatisticUtil.incrementOneCounter(this.this$0.getHasNotificationStacked() ? LockScreenStatisticUtil.KEY_STACK_EXPAND_WITH_TAIL_COUNT : LockScreenStatisticUtil.KEY_STACK_EXPAND_FLAT_COUNT);
                break;
        }
    }
}

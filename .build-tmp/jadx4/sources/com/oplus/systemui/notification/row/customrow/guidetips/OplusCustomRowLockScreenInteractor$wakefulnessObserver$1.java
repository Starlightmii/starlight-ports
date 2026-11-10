package com.oplus.systemui.notification.row.customrow.guidetips;

import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCustomRowLockScreenInteractor$wakefulnessObserver$1 implements WakefulnessLifecycle.Observer {
    public /* synthetic */ OplusCustomRowLockScreenInteractor this$0;

    public final void onFinishedWakingUp() {
        ILockScreenExtStateController iLockScreenExtStateController;
        OplusCustomRowLockScreenInteractor oplusCustomRowLockScreenInteractor = this.this$0;
        if (((StatusBarStateController) oplusCustomRowLockScreenInteractor.statusBarStateController.get()).getState() == 1 && (iLockScreenExtStateController = (ILockScreenExtStateController) oplusCustomRowLockScreenInteractor.lockScreenExtStateController.get()) != null && iLockScreenExtStateController.isStackState()) {
            OplusCustomRowLockScreenInteractor.access$seedlingCardExposedOnLockScreen(oplusCustomRowLockScreenInteractor);
        }
    }

    public final void onStartedGoingToSleep() {
    }
}

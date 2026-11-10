package com.oplus.systemui.notification.lockscreen.statemanager;

import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenNotificationExtStateManager$wakefulnessObserver$1 implements WakefulnessLifecycle.Observer {
    public /* synthetic */ LockScreenNotificationExtStateManager this$0;

    public final void onFinishedGoingToSleep() {
        LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager = this.this$0;
        if (((OplusPanoramicAodRepository) lockScreenNotificationExtStateManager.oplusPanoramicAodRepository.get()).getSwitchAodEnable() || !lockScreenNotificationExtStateManager.isTopStackState()) {
            return;
        }
        lockScreenNotificationExtStateManager.collapseExpandedNotifications("onFinishedGoingToSleep");
    }

    public final void onStartedGoingToSleep() {
        LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager = this.this$0;
        if (((OplusPanoramicAodRepository) lockScreenNotificationExtStateManager.oplusPanoramicAodRepository.get()).getSwitchAodEnable() && lockScreenNotificationExtStateManager.isTopStackState()) {
            lockScreenNotificationExtStateManager.collapseExpandedNotifications("onStartedGoingToSleep");
        }
    }

    public final void onStartedWakingUp() {
        LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager = this.this$0;
        LockScreenNotificationExtStateManager.Companion companion = LockScreenNotificationExtStateManager.Companion;
        lockScreenNotificationExtStateManager.updateKeyguardFpSuppressionState();
    }
}

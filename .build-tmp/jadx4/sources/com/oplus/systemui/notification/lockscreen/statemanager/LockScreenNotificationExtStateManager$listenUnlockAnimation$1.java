package com.oplus.systemui.notification.lockscreen.statemanager;

import com.android.systemui.keyguard.KeyguardUnlockAnimationController;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplus.systemui.notification.lockscreen.immersive.ImmersiveCardControl;
import com.oplus.systemui.notification.lockscreen.seedling.LockScreenSeedingInteractor;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenNotificationExtStateManager$listenUnlockAnimation$1 implements KeyguardUnlockAnimationController.KeyguardUnlockAnimationListener {
    public /* synthetic */ LockScreenNotificationExtStateManager this$0;

    public final void onUnlockAnimationStarted(boolean z, boolean z2) {
        FocusCard focusCard;
        String key;
        LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(lockScreenNotificationExtStateManager.getExtState(), "[onUnlockAnimationStarted]:", "LockScreenExtStateController-->", "SystemUi--Notification");
        }
        if (lockScreenNotificationExtStateManager.getExtState() != 2 || (focusCard = ((ImmersiveCardControl) lockScreenNotificationExtStateManager.immersiveCardControl.get()).getFocusCard()) == null || (key = focusCard.getKey()) == null) {
            return;
        }
        ((LockScreenSeedingInteractor) lockScreenNotificationExtStateManager.lockScreenSeedingInteractor.get()).notifyUnlockAnimationStarted(key);
        lockScreenNotificationExtStateManager.focusCardKey = key;
    }
}

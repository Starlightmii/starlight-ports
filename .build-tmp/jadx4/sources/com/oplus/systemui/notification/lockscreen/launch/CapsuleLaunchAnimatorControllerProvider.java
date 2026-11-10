package com.oplus.systemui.notification.lockscreen.launch;

import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleCardControl;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.immersive.OplusImmersiveViewManager;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleLaunchAnimatorControllerProvider {
    private final CapsuleCardControl capsuleCardControl;
    private final OplusImmersiveViewManager immersiveViewManager;
    private final ILockScreenExtStateController lockScreenExtStateController;

    public CapsuleLaunchAnimatorControllerProvider(CapsuleCardControl capsuleCardControl, OplusImmersiveViewManager oplusImmersiveViewManager, ILockScreenExtStateController iLockScreenExtStateController) {
        this.capsuleCardControl = capsuleCardControl;
        this.immersiveViewManager = oplusImmersiveViewManager;
        this.lockScreenExtStateController = iLockScreenExtStateController;
    }

    public final CapsuleTransitionAnimatorController getAnimatorController(ExpandableNotificationRow expandableNotificationRow, Runnable runnable) {
        CapsuleItemView baseCard = this.capsuleCardControl.getBaseCard();
        if (baseCard == null) {
            return null;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("getAnimatorController: notification=", expandableNotificationRow.getKey(), ",use capsuleView", "CapsuleLaunchAnimatorController-->", "SystemUi--Notification");
        }
        return getAnimatorController(baseCard, runnable);
    }

    public final CapsuleTransitionAnimatorController getAnimatorController(CapsuleItemView capsuleItemView, Runnable runnable) {
        return new CapsuleTransitionAnimatorController(capsuleItemView, runnable, this.immersiveViewManager, this.lockScreenExtStateController);
    }

    public final CapsuleTransitionAnimatorController getAnimatorController(ExpandableNotificationRow expandableNotificationRow) {
        return getAnimatorController(expandableNotificationRow, (Runnable) null);
    }

    public final CapsuleTransitionAnimatorController getAnimatorController(CapsuleItemView capsuleItemView) {
        return getAnimatorController(capsuleItemView, (Runnable) null);
    }
}

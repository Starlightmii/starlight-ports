package com.oplus.systemui.notification.lockscreen.statemanager;

import android.os.Bundle;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.statusbar.notification.helper.NotificationKeyguardHelper;
import dagger.Lazy;
import java.util.Iterator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenNotificationExtStateDecider {
    public static final Companion Companion = new Companion();
    private final Lazy extStateSkipStrategy;
    private final Lazy lockScreenExtStateController;
    private final Lazy notificationKeyguardHelper;
    private final Lazy nsslController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public LockScreenNotificationExtStateDecider(Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4) {
        this.lockScreenExtStateController = lazy;
        this.nsslController = lazy2;
        this.notificationKeyguardHelper = lazy3;
        this.extStateSkipStrategy = lazy4;
    }

    private final boolean getEnableLockScreenNotificationSinking() {
        return ((NotificationKeyguardHelper) this.notificationKeyguardHelper.get()).mLockScreenNotificationEnableSinkingObserver.getLockScreenNotificationEnableSinking();
    }

    private final boolean getHasNotificationStacked() {
        return ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).getHasNotificationStacked();
    }

    private final String getImmersiveCardKey() {
        Bundle extra = ((ILockScreenExtStateController.ExtStateEvent) ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).getExtStateEvent().getValue()).getExtra();
        if (extra != null) {
            return ILockScreenExtStateController.ExtStateEvent.Companion.getImmersiveKey(extra);
        }
        return null;
    }

    public final int onCapsuleSlideToUp() {
        return (getEnableLockScreenNotificationSinking() && ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).getDefaultExtState() == 3) ? 3 : 0;
    }

    public final int onNsslSlideToDown(int i) {
        if (i != 0 || !((NotificationKeyguardHelper) this.notificationKeyguardHelper.get()).mLockScreenNotificationEnableSinkingObserver.getLockScreenNotificationEnableSinking()) {
            return 1;
        }
        if (((ILockScreenExtStateController) this.lockScreenExtStateController.get()).getHasNotificationStacked()) {
            return ((LockScreenExtStateSkipStrategy) this.extStateSkipStrategy.get()).shouldSkipTargetStateToNext(3) ? 1 : 3;
        }
        Iterator it = ((NotificationStackScrollLayoutController) this.nsslController.get()).getView().getVisibleChildren().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if ((((ExpandableView) it.next()) instanceof ExpandableNotificationRow) && (i2 = i2 + 1) > 2) {
                return 3;
            }
        }
        return 1;
    }

    public final Integer onNsslSlideToUp(int i) {
        if (i == 0) {
            return getHasNotificationStacked() ? 10 : null;
        }
        if (i == 1) {
            return Integer.valueOf(getEnableLockScreenNotificationSinking() ? 3 : 0);
        }
        if (i == 2) {
            return getImmersiveCardKey() != null ? 0 : null;
        }
        if (i == 3 && getHasNotificationStacked()) {
            return Integer.valueOf(((LockScreenExtStateSkipStrategy) this.extStateSkipStrategy.get()).shouldSkipTargetStateToNext(0) ? 10 : 0);
        }
        return null;
    }
}

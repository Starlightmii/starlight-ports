package com.oplus.systemui.statusbar.lockscreen;

import android.app.smartspace.SmartspaceSession;
import android.os.UserHandle;
import com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusLockscreenSmartspaceControllerExImpl extends OplusLockscreenSmartspaceControllerEx {
    private LockscreenSmartspaceController lockscreenSmartspaceController;

    @Override // com.oplus.systemui.statusbar.lockscreen.OplusLockscreenSmartspaceControllerEx
    public void init(LockscreenSmartspaceController lockscreenSmartspaceController) {
        this.lockscreenSmartspaceController = lockscreenSmartspaceController;
    }

    @Override // com.oplus.systemui.statusbar.lockscreen.OplusLockscreenSmartspaceControllerEx
    public final void reloadSmartspace() {
        SmartspaceSession session;
        LockscreenSmartspaceController lockscreenSmartspaceController;
        UserHandle managedUserHandle;
        LockscreenSmartspaceController lockscreenSmartspaceController2 = this.lockscreenSmartspaceController;
        if (lockscreenSmartspaceController2 != null) {
            lockscreenSmartspaceController2.setShowNotifications(true);
        }
        LockscreenSmartspaceController lockscreenSmartspaceController3 = this.lockscreenSmartspaceController;
        if (lockscreenSmartspaceController3 != null) {
            lockscreenSmartspaceController3.setShowSensitiveContentForCurrentUser(true);
        }
        LockscreenSmartspaceController lockscreenSmartspaceController4 = this.lockscreenSmartspaceController;
        Integer numValueOf = null;
        if (lockscreenSmartspaceController4 != null) {
            lockscreenSmartspaceController4.setManagedUserHandle(lockscreenSmartspaceController4 != null ? lockscreenSmartspaceController4.getWorkProfileUser() : null);
        }
        LockscreenSmartspaceController lockscreenSmartspaceController5 = this.lockscreenSmartspaceController;
        if (lockscreenSmartspaceController5 != null && (managedUserHandle = lockscreenSmartspaceController5.getManagedUserHandle()) != null) {
            numValueOf = Integer.valueOf(managedUserHandle.getIdentifier());
        }
        if (numValueOf != null && (lockscreenSmartspaceController = this.lockscreenSmartspaceController) != null) {
            lockscreenSmartspaceController.setShowSensitiveContentForManagedUser(true);
        }
        LockscreenSmartspaceController lockscreenSmartspaceController6 = this.lockscreenSmartspaceController;
        if (lockscreenSmartspaceController6 == null || (session = lockscreenSmartspaceController6.getSession()) == null) {
            return;
        }
        session.requestSmartspaceUpdate();
    }
}

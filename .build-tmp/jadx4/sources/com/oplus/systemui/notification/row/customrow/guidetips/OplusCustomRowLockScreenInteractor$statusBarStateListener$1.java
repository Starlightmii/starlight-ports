package com.oplus.systemui.notification.row.customrow.guidetips;

import com.android.systemui.plugins.statusbar.StatusBarStateController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCustomRowLockScreenInteractor$statusBarStateListener$1 implements StatusBarStateController.StateListener {
    public /* synthetic */ OplusCustomRowLockScreenInteractor this$0;

    public final void onStateChanged(int i) {
        OplusCustomRowLockScreenInteractor oplusCustomRowLockScreenInteractor = this.this$0;
        if (i == 0) {
            OplusCustomRowLockScreenInteractor.requestHideGuideTips$default(oplusCustomRowLockScreenInteractor, 501);
        } else {
            if (i != 2) {
                return;
            }
            OplusCustomRowLockScreenInteractor.requestHideGuideTips$default(oplusCustomRowLockScreenInteractor, 502);
        }
    }
}

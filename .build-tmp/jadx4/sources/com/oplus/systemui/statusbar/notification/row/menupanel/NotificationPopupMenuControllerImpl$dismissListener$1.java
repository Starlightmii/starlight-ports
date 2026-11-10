package com.oplus.systemui.statusbar.notification.row.menupanel;

import android.widget.PopupWindow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPopupMenuControllerImpl$dismissListener$1 implements PopupWindow.OnDismissListener {
    public /* synthetic */ NotificationPopupMenuControllerImpl this$0;

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        NotificationPopupMenuController.dismissPopupMenu$default(this.this$0, "COUIPopupMenuRootView click", false, 6);
    }
}

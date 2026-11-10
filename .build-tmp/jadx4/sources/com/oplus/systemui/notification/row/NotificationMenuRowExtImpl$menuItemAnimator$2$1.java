package com.oplus.systemui.notification.row;

import android.util.Size;
import com.android.systemui.statusbar.notification.row.ExpandableView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMenuRowExtImpl$menuItemAnimator$2$1 implements MenuViewInfoProvider {
    public /* synthetic */ NotificationMenuRowExtImpl this$0;

    @Override // com.oplus.systemui.notification.row.MenuViewInfoProvider
    public int getIconMargin() {
        return this.this$0.getSHorizMarginForIcon();
    }

    @Override // com.oplus.systemui.notification.row.MenuViewInfoProvider
    public int getIconMarginExtra() {
        return this.this$0.getSHorizMarginForIconExtra();
    }

    @Override // com.oplus.systemui.notification.row.MenuViewInfoProvider
    public final Size getIconSize(boolean z) {
        return this.this$0.menuRowItemViewParam.getIconSize(z);
    }

    @Override // com.oplus.systemui.notification.row.MenuViewInfoProvider
    public final void updateMetaBall() {
        NotificationMenuRowMetaBallController notificationMenuRowMetaBallController;
        NotificationMenuRowExtImpl notificationMenuRowExtImpl = this.this$0;
        ExpandableView menuRowParent = notificationMenuRowExtImpl.getMenuRowParent();
        Float fValueOf = menuRowParent != null ? Float.valueOf(menuRowParent.getTranslation()) : null;
        if (fValueOf == null || (notificationMenuRowMetaBallController = notificationMenuRowExtImpl.metaBallController) == null) {
            return;
        }
        notificationMenuRowMetaBallController.updateMetaBallPosition(fValueOf.floatValue());
    }
}

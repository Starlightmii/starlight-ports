package com.oplus.systemui.notification.tempfilter;

import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.systemui.statusbar.notification.collection.render.NodeController;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.NotificationListContainer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusTempFilterImpl extends OplusTempFilter {
    @Override // com.oplus.systemui.notification.tempfilter.OplusTempFilter
    public boolean addChildAt(NotificationListContainer notificationListContainer, NodeController nodeController, int i) {
        return false;
    }

    @Override // com.oplus.systemui.notification.tempfilter.OplusTempFilter
    public boolean removeChild(NotificationListContainer notificationListContainer, NodeController nodeController, boolean z) {
        NotifFilter excludingFilter;
        ExpandableNotificationRow view = nodeController.getView();
        if (!(view instanceof ExpandableNotificationRow) || (excludingFilter = view.getEntry().getExcludingFilter()) == null) {
            return false;
        }
        excludingFilter.isTempFilterOut();
        return false;
    }
}

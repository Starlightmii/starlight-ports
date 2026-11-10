package com.oplus.systemui.statusbar.notification.collection.coordinator;

import android.app.Notification;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import androidx.compose.runtime.collection.MutableVectorKt$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.RowAppearanceCoordinatorEx;
import com.android.systemui.statusbar.notification.collection.provider.VisualStabilityProvider;
import com.oplus.systemui.statusbar.notification.collection.OplusNotificationEntryExImpl;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RowAppearanceCoordinatorExImpl extends RowAppearanceCoordinatorEx {
    private final VisualStabilityProvider mVisualStabilityProvider;
    public int oldListSize;
    private final StatusBarStateController statusBarStateController;

    public RowAppearanceCoordinatorExImpl(VisualStabilityProvider visualStabilityProvider, StatusBarStateController statusBarStateController) {
        this.mVisualStabilityProvider = visualStabilityProvider;
        this.statusBarStateController = statusBarStateController;
    }

    public boolean isAllowedChange(NotificationEntry notificationEntry, List<? extends PipelineEntry> list) {
        int size = list.size();
        boolean z = this.oldListSize != size;
        if (z && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "RowAppearanceCoordinatorExImpl-->".concat(MutableVectorKt$.ExternalSyntheticOutline0.m("newListSize:{", size, this.oldListSize, "} oldListSize:{", "}")));
        }
        this.oldListSize = size;
        return notificationEntry == null || this.mVisualStabilityProvider.isReorderingAllowed || z;
    }

    public boolean isAutoExpandFirstNotificationAllowed(NotificationEntry notificationEntry) {
        Notification notification;
        StatusBarNotification sbn = notificationEntry.getSbn();
        if (sbn != null && (notification = sbn.getNotification()) != null && this.statusBarStateController.getState() == 0) {
            Notification.Action[] actionArr = notification.actions;
            if ((actionArr != null ? actionArr.length : 0) > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isForceExpanded(NotificationEntry notificationEntry) {
        if (this.statusBarStateController.getState() != 0) {
            return false;
        }
        OplusNotificationEntryEx ex = notificationEntry.getEx();
        if (!(ex instanceof OplusNotificationEntryExImpl)) {
            return false;
        }
        OplusNotificationEntryExImpl oplusNotificationEntryExImpl = (OplusNotificationEntryExImpl) ex;
        return oplusNotificationEntryExImpl.getForceExpandedStyle() == 0 || oplusNotificationEntryExImpl.getMcsInfo().getHideExpandButton();
    }
}

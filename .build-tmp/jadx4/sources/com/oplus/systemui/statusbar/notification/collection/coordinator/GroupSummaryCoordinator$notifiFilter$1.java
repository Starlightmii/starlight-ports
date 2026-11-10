package com.oplus.systemui.statusbar.notification.collection.coordinator;

import android.app.Notification;
import android.os.Bundle;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GroupSummaryCoordinator$notifiFilter$1 extends NotifFilter {
    public final boolean shouldFilterOut(NotificationEntry notificationEntry, long j) {
        Bundle bundle;
        Bundle bundle2;
        List children;
        Notification notification = notificationEntry.getSbn().getNotification();
        if (notification != null && notification.isGroupSummary()) {
            GroupEntry parent = notificationEntry.getParent();
            CharSequence charSequence = null;
            GroupEntry groupEntry = parent instanceof GroupEntry ? parent : null;
            int size = (groupEntry == null || (children = groupEntry.getChildren()) == null) ? 0 : children.size();
            Notification notification2 = notificationEntry.getSbn().getNotification();
            CharSequence charSequence2 = (notification2 == null || (bundle2 = notification2.extras) == null) ? null : bundle2.getCharSequence("android.title");
            Notification notification3 = notificationEntry.getSbn().getNotification();
            if (notification3 != null && (bundle = notification3.extras) != null) {
                charSequence = bundle.getCharSequence("android.text");
            }
            if (size == 0 && ((charSequence2 == null || charSequence2.length() == 0) && (charSequence == null || charSequence.length() == 0))) {
                if (LogUtil.panicEnabled && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("shouldFilterOut true entry ", notificationEntry.getKey(), "GroupSummaryCoordinator-->", "SystemUi--Notification");
                }
                return true;
            }
        }
        return false;
    }
}

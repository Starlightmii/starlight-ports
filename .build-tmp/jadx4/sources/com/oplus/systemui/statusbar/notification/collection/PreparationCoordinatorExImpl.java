package com.oplus.systemui.statusbar.notification.collection;

import android.util.Log;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.notification.PreparationCoordinatorEx;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class PreparationCoordinatorExImpl extends PreparationCoordinatorEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "PreparationCoordinatorEx";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public Integer applyInflationStateIfNull(Integer num, int i, NotificationEntry notificationEntry) {
        if (num != null) {
            return num;
        }
        Log.w(TAG, "stateObj is null, so return errorState, entry is" + (notificationEntry != null ? notificationEntry.getKey() : null));
        return Integer.valueOf(i);
    }

    public boolean shouldBypassUninflatedSummaryWait(GroupEntry groupEntry, boolean z) {
        if (z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("shouldBypassUninflatedSummaryWait: attached+inflated child in group ", groupEntry.getKey(), "PreparationCoordinatorEx-->", "SystemUi--Notification");
            }
            return true;
        }
        for (NotificationEntry notificationEntry : groupEntry.getChildren()) {
            if (notificationEntry.wasAttachedInPreviousPass()) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("shouldBypassUninflatedSummaryWait: attached child ", notificationEntry.getKey(), " during re-inflate, group ", groupEntry.getKey(), "PreparationCoordinatorEx-->"));
                }
                return true;
            }
        }
        return false;
    }
}

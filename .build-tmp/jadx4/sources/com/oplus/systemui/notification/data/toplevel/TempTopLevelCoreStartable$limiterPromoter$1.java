package com.oplus.systemui.notification.data.toplevel;

import android.os.SystemClock;
import android.util.Log;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.OplusListEntryEx;
import com.android.systemui.statusbar.notification.collection.VisualStabilityCoordinatorEx;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifPromoter;
import com.android.systemui.statusbar.notification.collection.provider.OnReorderingAllowedListener;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TempTopLevelCoreStartable$limiterPromoter$1 extends NotifPromoter implements OnReorderingAllowedListener {
    public final /* synthetic */ TempTopLevelCoreStartable this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TempTopLevelCoreStartable$limiterPromoter$1(TempTopLevelCoreStartable tempTopLevelCoreStartable) {
        super("TempTopLevelBecause2Children");
        this.this$0 = tempTopLevelCoreStartable;
    }

    public final void onReorderingAllowed() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "TempTopLevelCoreStartable-->onReorderingAllowed");
        }
        invalidateList("onReorderingAllowed");
    }

    public final boolean shouldPromoteToTopLevel(NotificationEntry notificationEntry) {
        TempTopLevelCoreStartable tempTopLevelCoreStartable = this.this$0;
        tempTopLevelCoreStartable.clock.getClass();
        if (SystemClock.elapsedRealtime() - notificationEntry.getCreationTime() > 200 || notificationEntry.getPreviousAttachState().getParent() != null || tempTopLevelCoreStartable.visualStabilityProvider.isReorderingAllowed) {
            return false;
        }
        GroupEntry parent = notificationEntry.getParent();
        GroupEntry groupEntry = parent instanceof GroupEntry ? parent : null;
        if (groupEntry == null) {
            return false;
        }
        OplusListEntryEx mListEntryEx = groupEntry.getMListEntryEx();
        if (mListEntryEx != null && mListEntryEx.getHasBeShowed()) {
            return false;
        }
        VisualStabilityCoordinatorEx visualStabilityCoordinatorEx = tempTopLevelCoreStartable.getVisualStabilityCoordinatorEx();
        if ((visualStabilityCoordinatorEx != null && visualStabilityCoordinatorEx.isPanoramicAodShowAllDay()) || groupEntry.getChildren().size() != 2) {
            return false;
        }
        tempTopLevelCoreStartable.visualStabilityProvider.addTemporaryReorderingAllowedListener(this);
        return true;
    }
}

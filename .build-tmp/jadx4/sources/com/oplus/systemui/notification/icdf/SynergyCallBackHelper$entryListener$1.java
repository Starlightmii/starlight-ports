package com.oplus.systemui.notification.icdf;

import android.os.Handler;
import android.util.Log;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SynergyCallBackHelper$entryListener$1 implements NotifCollectionListener {
    public /* synthetic */ SynergyCallBackHelper this$0;

    public final void onEntryRemoved(NotificationEntry notificationEntry, int i) {
        SynergyCallBackHelper synergyCallBackHelper = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "PC_Synergy-->onEntryRemoved");
        }
        synergyCallBackHelper.getMInflationStatesHelper().removeEntryInflationState(notificationEntry);
        Handler workHandler = synergyCallBackHelper.getWorkHandler();
        SynergyCallBackHelper$updateRanking$1.AnonymousClass1 anonymousClass1 = new SynergyCallBackHelper$updateRanking$1.AnonymousClass1(1);
        anonymousClass1.this$0 = synergyCallBackHelper;
        anonymousClass1.$rankingMap = notificationEntry;
        VarHandle.storeStoreFence();
        workHandler.post(anonymousClass1);
    }

    public final void onRankingApplied() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "PC_Synergy-->onRankingApplied");
        }
        SynergyCallBackHelper.access$updateRanking(this.this$0);
    }
}

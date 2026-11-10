package com.oplus.systemui.statusbar.notification.collection.inflation;

import com.android.systemui.statusbar.notification.collection.inflation.NotifUiAdjustmentEx;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotifUiAdjustmentExImpl extends NotifUiAdjustmentEx {
    public boolean isInBlackList;

    public boolean isDiffTo(NotifUiAdjustmentEx notifUiAdjustmentEx) {
        if (notifUiAdjustmentEx instanceof NotifUiAdjustmentExImpl) {
            return this.isInBlackList != ((NotifUiAdjustmentExImpl) notifUiAdjustmentEx).isInBlackList;
        }
        return super.isDiffTo(notifUiAdjustmentEx);
    }
}

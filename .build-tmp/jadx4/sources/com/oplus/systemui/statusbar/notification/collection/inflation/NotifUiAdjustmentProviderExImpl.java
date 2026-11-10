package com.oplus.systemui.statusbar.notification.collection.inflation;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import com.android.systemui.statusbar.notification.collection.inflation.NotifUiAdjustmentEx;
import com.android.systemui.statusbar.notification.collection.inflation.NotifUiAdjustmentProviderEx;
import com.oplusos.systemui.notification.commercialize.CommercialBlackListManager;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotifUiAdjustmentProviderExImpl extends NotifUiAdjustmentProviderEx {
    private final Context context;

    public NotifUiAdjustmentProviderExImpl(Context context) {
        this.context = context;
    }

    public NotifUiAdjustmentEx createAdjustmentEx(StatusBarNotification statusBarNotification) {
        boolean zIsInBlackList = CommercialBlackListManager.Companion.getInstance(this.context).isInBlackList(statusBarNotification);
        NotifUiAdjustmentExImpl notifUiAdjustmentExImpl = new NotifUiAdjustmentExImpl();
        notifUiAdjustmentExImpl.isInBlackList = zIsInBlackList;
        VarHandle.storeStoreFence();
        return notifUiAdjustmentExImpl;
    }

    public final Context getContext() {
        return this.context;
    }
}

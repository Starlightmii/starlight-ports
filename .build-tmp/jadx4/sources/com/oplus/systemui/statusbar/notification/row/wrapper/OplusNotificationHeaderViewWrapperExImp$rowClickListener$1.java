package com.oplus.systemui.statusbar.notification.row.wrapper;

import android.view.View;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationHeaderViewWrapper;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationHeaderViewWrapperExImp$rowClickListener$1 implements View.OnClickListener {
    public /* synthetic */ OplusNotificationHeaderViewWrapperExImp this$0;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        View view2;
        View.OnClickListener clickerListener;
        NotificationHeaderViewWrapper notificationHeaderViewWrapperM459getBase = this.this$0.m459getBase();
        if (notificationHeaderViewWrapperM459getBase == null || (view2 = ((NotificationViewWrapper) notificationHeaderViewWrapperM459getBase).mRow) == null || (clickerListener = view2.getClickerListener()) == null) {
            return;
        }
        clickerListener.onClick(view2);
    }
}

package com.oplus.systemui.statusbar.notification.row;

import android.view.View;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplus.systemui.notification.row.click.NotificationClickEffectControllerImpl;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusExpandableNotificationRowExImpl$fakeClicker$1 implements View.OnClickListener {
    public /* synthetic */ OplusExpandableNotificationRowExImpl this$0;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        View.OnClickListener clickerListener;
        NotificationEntry entry;
        ExpandableNotificationRow view2 = this.this$0.getView();
        OplusNotificationLogger.logNotificationClickEffect("ExpandableNotificationRow", "fake click call, row = " + ((view2 == null || (entry = view2.getEntry()) == null) ? null : entry.getKey()));
        if (((NotificationClickEffectControllerImpl) this.this$0.mNotificationClickEffectController.get()).getTalkBackState()) {
            OplusNotificationLogger.logNotificationClickEffect("ExpandableNotificationRow", "talkBack have open and start intent");
            ExpandableNotificationRow view3 = this.this$0.getView();
            if (view3 == null || (clickerListener = view3.getClickerListener()) == null) {
                return;
            }
            clickerListener.onClick(this.this$0.getView());
        }
    }
}

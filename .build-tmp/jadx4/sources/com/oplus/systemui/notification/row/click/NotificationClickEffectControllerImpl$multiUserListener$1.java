package com.oplus.systemui.notification.row.click;

import com.oplusos.systemui.common.multiuser.IMultiUserListener;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationClickEffectControllerImpl$multiUserListener$1 implements IMultiUserListener {
    public /* synthetic */ NotificationClickEffectControllerImpl this$0;

    @Override // com.oplusos.systemui.common.multiuser.IMultiUserListener
    public final void onSwitched(int i) {
        OplusNotificationLogger.logNotificationClickEffect(NotificationClickEffectControllerImpl.TAG, "REGION_LOG_TAG-->onSwitched currentUserId: " + i);
        this.this$0.initTalkBackState(i);
    }
}

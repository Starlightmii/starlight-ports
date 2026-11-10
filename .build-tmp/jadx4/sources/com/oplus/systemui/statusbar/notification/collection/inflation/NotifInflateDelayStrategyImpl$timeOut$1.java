package com.oplus.systemui.statusbar.notification.collection.inflation;

import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotifInflateDelayStrategyImpl$timeOut$1 implements Runnable {
    public /* synthetic */ NotifInflateDelayStrategyImpl this$0;

    @Override // java.lang.Runnable
    public final void run() {
        NotifInflateDelayStrategyImpl notifInflateDelayStrategyImpl = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(notifInflateDelayStrategyImpl.currentType, "timeOut:currentType=", "NotifInflateDelay-->", "SystemUi--Notification");
        }
        NotifInflateDelayStrategyImpl notifInflateDelayStrategyImpl2 = this.this$0;
        notifInflateDelayStrategyImpl2.isWait = false;
        notifInflateDelayStrategyImpl2.checkNext();
    }
}

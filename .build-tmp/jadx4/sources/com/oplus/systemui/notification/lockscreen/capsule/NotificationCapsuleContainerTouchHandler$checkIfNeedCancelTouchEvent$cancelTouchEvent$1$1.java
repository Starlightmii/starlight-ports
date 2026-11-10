package com.oplus.systemui.notification.lockscreen.capsule;

import com.oplusos.systemui.common.util.GlobalUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationCapsuleContainerTouchHandler$checkIfNeedCancelTouchEvent$cancelTouchEvent$1$1 implements Runnable {
    public /* synthetic */ String $reason;
    public /* synthetic */ NotificationCapsuleContainer $this_apply;
    public /* synthetic */ NotificationCapsuleContainerTouchHandler this$0;

    @Override // java.lang.Runnable
    public final void run() {
        this.this$0.dataChangeInterceptor.unIntercept();
        GlobalUtils.sendCancelMotionEvent(this.$this_apply, this.$reason);
    }
}

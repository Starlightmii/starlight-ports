package com.oplus.systemui.statusbar.notification.helper;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class NotificationCenterConnectImpl$runWithRestart$1<T> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ NotificationCenterConnectImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationCenterConnectImpl$runWithRestart$1(NotificationCenterConnectImpl notificationCenterConnectImpl, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = notificationCenterConnectImpl;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return NotificationCenterConnectImpl.access$runWithRestart(this.this$0, null, null, this);
    }
}

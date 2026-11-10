package com.oplus.systemui.statusbar;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class LockScreenNotificationDispatcherImp$getNotificationIconList$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ LockScreenNotificationDispatcherImp this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LockScreenNotificationDispatcherImp$getNotificationIconList$1(LockScreenNotificationDispatcherImp lockScreenNotificationDispatcherImp, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = lockScreenNotificationDispatcherImp;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return LockScreenNotificationDispatcherImp.access$getNotificationIconList(this.this$0, null, this);
    }
}

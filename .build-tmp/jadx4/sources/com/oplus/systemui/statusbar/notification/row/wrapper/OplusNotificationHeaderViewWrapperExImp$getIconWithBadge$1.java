package com.oplus.systemui.statusbar.notification.row.wrapper;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ OplusNotificationHeaderViewWrapperExImp this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusNotificationHeaderViewWrapperExImp$getIconWithBadge$1(OplusNotificationHeaderViewWrapperExImp oplusNotificationHeaderViewWrapperExImp, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = oplusNotificationHeaderViewWrapperExImp;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return OplusNotificationHeaderViewWrapperExImp.access$getIconWithBadge(this.this$0, null, this);
    }
}

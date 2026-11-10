package com.oplus.systemui.notification.row.oplusgroup;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class GroupIconManager$loadSmallIconInfo$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ GroupIconManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GroupIconManager$loadSmallIconInfo$1(GroupIconManager groupIconManager, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = groupIconManager;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return GroupIconManager.access$loadSmallIconInfo(this.this$0, null, this);
    }
}

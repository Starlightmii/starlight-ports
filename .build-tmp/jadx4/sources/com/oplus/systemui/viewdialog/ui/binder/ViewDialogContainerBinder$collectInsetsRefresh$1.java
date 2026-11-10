package com.oplus.systemui.viewdialog.ui.binder;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class ViewDialogContainerBinder$collectInsetsRefresh$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ViewDialogContainerBinder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewDialogContainerBinder$collectInsetsRefresh$1(ViewDialogContainerBinder viewDialogContainerBinder, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = viewDialogContainerBinder;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ViewDialogContainerBinder.access$collectInsetsRefresh(this.this$0, null, this);
    }
}

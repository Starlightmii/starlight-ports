package com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusMobileIconInteractorImpl$createImsModel$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    int I$5;
    Object L$0;
    Object L$1;
    boolean Z$0;
    boolean Z$1;
    boolean Z$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ OplusMobileIconInteractorImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusMobileIconInteractorImpl$createImsModel$1(OplusMobileIconInteractorImpl oplusMobileIconInteractorImpl, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = oplusMobileIconInteractorImpl;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return OplusMobileIconInteractorImpl.access$createImsModel(this.this$0, 0, null, false, null, false, false, this);
    }
}

package com.oplus.systemui.notification.headsup.windowframe;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusHeadsUpAnimator$show$1$1$emit$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ OplusHeadsUpAnimator.AnonymousClass1.C00191 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusHeadsUpAnimator$show$1$1$emit$1(OplusHeadsUpAnimator.AnonymousClass1.C00191 c00191, Continuation continuation) {
        super(continuation);
        this.this$0 = c00191;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(0, (Continuation) this);
    }
}

package com.oplus.systemui.plugins.qs.customize.viewmodel;

import kotlin.Pair;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class TileEditViewModel$4$3$emit$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TileEditViewModel.AnonymousClass4.AnonymousClass3 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TileEditViewModel$4$3$emit$1(TileEditViewModel.AnonymousClass4.AnonymousClass3 anonymousClass3, Continuation continuation) {
        super(continuation);
        this.this$0 = anonymousClass3;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit((Pair) null, (Continuation) this);
    }
}

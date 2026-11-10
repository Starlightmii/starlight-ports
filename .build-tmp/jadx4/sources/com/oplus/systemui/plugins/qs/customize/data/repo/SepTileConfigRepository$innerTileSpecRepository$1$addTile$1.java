package com.oplus.systemui.plugins.qs.customize.data.repo;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepTileConfigRepository$innerTileSpecRepository$1$addTile$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SepTileConfigRepository$innerTileSpecRepository$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepTileConfigRepository$innerTileSpecRepository$1$addTile$1(SepTileConfigRepository$innerTileSpecRepository$1 sepTileConfigRepository$innerTileSpecRepository$1, Continuation continuation) {
        super(continuation);
        this.this$0 = sepTileConfigRepository$innerTileSpecRepository$1;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.addTile(0, null, 0, this);
    }
}

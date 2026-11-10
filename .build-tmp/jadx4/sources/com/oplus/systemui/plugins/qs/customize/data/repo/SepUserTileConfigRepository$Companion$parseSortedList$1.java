package com.oplus.systemui.plugins.qs.customize.data.repo;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepUserTileConfigRepository$Companion$parseSortedList$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SepUserTileConfigRepository.Companion this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepUserTileConfigRepository$Companion$parseSortedList$1(SepUserTileConfigRepository.Companion companion, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = companion;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return SepUserTileConfigRepository.Companion.access$parseSortedList(this.this$0, null, null, this);
    }
}

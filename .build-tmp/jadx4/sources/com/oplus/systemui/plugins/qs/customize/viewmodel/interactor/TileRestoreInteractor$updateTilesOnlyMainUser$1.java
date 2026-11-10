package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class TileRestoreInteractor$updateTilesOnlyMainUser$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TileRestoreInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TileRestoreInteractor$updateTilesOnlyMainUser$1(TileRestoreInteractor tileRestoreInteractor, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = tileRestoreInteractor;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return TileRestoreInteractor.access$updateTilesOnlyMainUser(this.this$0, false, this);
    }
}

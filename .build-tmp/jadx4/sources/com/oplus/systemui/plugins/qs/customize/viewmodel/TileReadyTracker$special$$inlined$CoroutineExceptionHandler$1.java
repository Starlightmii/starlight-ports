package com.oplus.systemui.plugins.qs.customize.viewmodel;

import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileReadyTracker$special$$inlined$CoroutineExceptionHandler$1 extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    public final /* synthetic */ TileReadyTracker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TileReadyTracker$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key key, TileReadyTracker tileReadyTracker) {
        super(key);
        this.this$0 = tileReadyTracker;
    }

    public final void handleException(CoroutineContext coroutineContext, Throwable th) {
        TileReadyTracker.access$cancelTracking(this.this$0, th, "CoroutineException: " + th.getMessage());
    }
}

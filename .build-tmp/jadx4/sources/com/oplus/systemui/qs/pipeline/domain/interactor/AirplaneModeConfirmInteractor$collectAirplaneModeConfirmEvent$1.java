package com.oplus.systemui.qs.pipeline.domain.interactor;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class AirplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AirplaneModeConfirmInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AirplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$1(AirplaneModeConfirmInteractor airplaneModeConfirmInteractor, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = airplaneModeConfirmInteractor;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return AirplaneModeConfirmInteractor.access$collectAirplaneModeConfirmEvent(this.this$0, this);
    }
}

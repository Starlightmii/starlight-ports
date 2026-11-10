package com.oplus.systemui.qs.pipeline.domain.interactor;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AirplaneModeConfirmInteractor$collectAirplaneModeConfirmEvent$2 implements FlowCollector {
    public /* synthetic */ AirplaneModeConfirmInteractor this$0;

    public final Object emit(Object obj, Continuation continuation) {
        AirplaneModeConfirmInteractor.access$showDialog(this.this$0);
        return Unit.INSTANCE;
    }
}

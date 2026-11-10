package com.oplus.systemui.plugins.qs.customize.data.exception;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ExceptionHandler {
    public static final ExceptionHandler INSTANCE = new ExceptionHandler();
    private static final MutableSharedFlow _exceptionFlow;
    private static final SharedFlow exceptionFlow;

    static {
        MutableSharedFlow MutableSharedFlow = SharedFlowKt.MutableSharedFlow(0, 10, BufferOverflow.DROP_OLDEST);
        _exceptionFlow = MutableSharedFlow;
        exceptionFlow = FlowKt.asSharedFlow(MutableSharedFlow);
    }

    public final SharedFlow getExceptionFlow() {
        return exceptionFlow;
    }

    public final Object send(ExceptionInfo exceptionInfo, Continuation continuation) {
        Object objEmit = _exceptionFlow.emit(exceptionInfo, continuation);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }
}

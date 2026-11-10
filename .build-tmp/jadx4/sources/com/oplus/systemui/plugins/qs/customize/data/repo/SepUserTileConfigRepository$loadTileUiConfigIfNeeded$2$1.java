package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepUserTileConfigRepository$loadTileUiConfigIfNeeded$2$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ SepUserTileConfigRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepUserTileConfigRepository$loadTileUiConfigIfNeeded$2$1(SepUserTileConfigRepository sepUserTileConfigRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sepUserTileConfigRepository;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new SepUserTileConfigRepository$loadTileUiConfigIfNeeded$2$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SepUserTileConfigRepository sepUserTileConfigRepository = this.this$0;
            int i2 = sepUserTileConfigRepository.userId;
            this.label = 1;
            obj = SepUserTileConfigRepository.access$loadGridInfoFromDBOrDefault(sepUserTileConfigRepository, i2, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        SepUserTileConfigRepository sepUserTileConfigRepository2 = this.this$0;
        MutableStateFlow mutableStateFlowTraceAs = FlowExtKt.traceAs(StateFlowKt.MutableStateFlow((GridInfoEntity) obj), "SepUserTileConfigRepository-gridInfo-flow");
        SepUserTileConfigRepository sepUserTileConfigRepository3 = this.this$0;
        CoroutineTracingKt.launchTraced$default(sepUserTileConfigRepository3.mainScope, "SepUserTileConfigRepository-launch-startGridInfoFlowCollections", sepUserTileConfigRepository3.bgDispatcher, (CoroutineStart) null, new SepUserTileConfigRepository$startGridInfoFlowCollections$1(mutableStateFlowTraceAs, sepUserTileConfigRepository3, null), 4);
        sepUserTileConfigRepository2._gridInfo = mutableStateFlowTraceAs;
        return Unit.INSTANCE;
    }
}

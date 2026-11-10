package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepUserTileConfigRepository$startGridInfoFlowCollections$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ MutableStateFlow $gridInfoState;
    int label;
    final /* synthetic */ SepUserTileConfigRepository this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepUserTileConfigRepository$startGridInfoFlowCollections$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements FlowCollector {
        public /* synthetic */ SepUserTileConfigRepository this$0;

        public final Object emit(Object obj, Continuation continuation) throws Throwable {
            Object objInsertGridInfo = this.this$0.sepEditDBTileRepository.insertGridInfo((GridInfoEntity) obj, continuation);
            return objInsertGridInfo == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInsertGridInfo : Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepUserTileConfigRepository$startGridInfoFlowCollections$1(MutableStateFlow mutableStateFlow, SepUserTileConfigRepository sepUserTileConfigRepository, Continuation continuation) {
        super(2, continuation);
        this.$gridInfoState = mutableStateFlow;
        this.this$0 = sepUserTileConfigRepository;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new SepUserTileConfigRepository$startGridInfoFlowCollections$1(this.$gridInfoState, this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutableStateFlow mutableStateFlow = this.$gridInfoState;
            SepUserTileConfigRepository sepUserTileConfigRepository = this.this$0;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = sepUserTileConfigRepository;
            VarHandle.storeStoreFence();
            this.label = 1;
            if (FlowExtKt.collectTraced(mutableStateFlow, "SepUserTileConfigRepository-collect-startGridInfoFlowCollections", anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}

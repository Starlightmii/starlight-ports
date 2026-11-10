package com.oplus.systemui.qs.pipeline.data.event;

import android.os.oplusdevicepolicy.OplusDevicepolicyManager;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplus.systemui.qs.pipeline.data.model.TileModifyEvent;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CustomizeModifyQsTilesEvent implements TileModifyEvent {
    private static final String CUSTOM_SYSUI_QS_HIDE_TILES_STATE_LIST = "custom_sysui_qs_hide_tiles_state_list";
    public static final Companion Companion = new Companion();
    private static final String SEPARATOR = "-";
    private static final String TAG = "CustomizeModifyQsTilesEvent";
    private final CoroutineContext bgCoroutineContext;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.data.event.CustomizeModifyQsTilesEvent$modifyTilesSignal$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(2, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            ProducerScope producerScope = (ProducerScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                OplusDevicepolicyManager oplusDevicepolicyManager = OplusDevicepolicyManager.getInstance();
                CustomizeModifyQsTilesEvent$modifyTilesSignal$1$observer$1 customizeModifyQsTilesEvent$modifyTilesSignal$1$observer$1 = new CustomizeModifyQsTilesEvent$modifyTilesSignal$1$observer$1();
                customizeModifyQsTilesEvent$modifyTilesSignal$1$observer$1.$$this$conflatedCallbackFlow = producerScope;
                VarHandle.storeStoreFence();
                oplusDevicepolicyManager.registerOplusDevicepolicyObserver(CustomizeModifyQsTilesEvent.CUSTOM_SYSUI_QS_HIDE_TILES_STATE_LIST, customizeModifyQsTilesEvent$modifyTilesSignal$1$observer$1);
                CustomizeModifyQsTilesEvent$modifyTilesSignal$1$$ExternalSyntheticLambda0 customizeModifyQsTilesEvent$modifyTilesSignal$1$$ExternalSyntheticLambda0 = new CustomizeModifyQsTilesEvent$modifyTilesSignal$1$$ExternalSyntheticLambda0();
                customizeModifyQsTilesEvent$modifyTilesSignal$1$$ExternalSyntheticLambda0.f$0 = oplusDevicepolicyManager;
                customizeModifyQsTilesEvent$modifyTilesSignal$1$$ExternalSyntheticLambda0.f$1 = customizeModifyQsTilesEvent$modifyTilesSignal$1$observer$1;
                VarHandle.storeStoreFence();
                this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
                this.L$1 = SpillingKt.nullOutSpilledVariable(oplusDevicepolicyManager);
                this.L$2 = SpillingKt.nullOutSpilledVariable(customizeModifyQsTilesEvent$modifyTilesSignal$1$observer$1);
                this.label = 1;
                if (ProduceKt.awaitClose(producerScope, customizeModifyQsTilesEvent$modifyTilesSignal$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.data.event.CustomizeModifyQsTilesEvent$modifyTilesSignal$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        /* synthetic */ Object L$0;
        int label;

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(2, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((List) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            List list = (List) this.L$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return FlowKt.asFlow(list);
            }
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    public CustomizeModifyQsTilesEvent(CoroutineContext coroutineContext) {
        this.bgCoroutineContext = coroutineContext;
    }

    @Override // com.oplus.systemui.qs.pipeline.data.model.TileModifyEvent
    public Flow modifyTilesSignal(int i) {
        Flow flowFlatMapConcat = FlowKt.flatMapConcat(FlowConflatedKt.conflatedCallbackFlow(new AnonymousClass1(2, null)), new AnonymousClass2(2, null));
        CustomizeModifyQsTilesEvent$modifyTilesSignal$$inlined$mapNotNull$1 customizeModifyQsTilesEvent$modifyTilesSignal$$inlined$mapNotNull$1 = new CustomizeModifyQsTilesEvent$modifyTilesSignal$$inlined$mapNotNull$1();
        customizeModifyQsTilesEvent$modifyTilesSignal$$inlined$mapNotNull$1.$this_unsafeTransform$inlined = flowFlatMapConcat;
        VarHandle.storeStoreFence();
        return FlowKt.flowOn(customizeModifyQsTilesEvent$modifyTilesSignal$$inlined$mapNotNull$1, this.bgCoroutineContext);
    }
}

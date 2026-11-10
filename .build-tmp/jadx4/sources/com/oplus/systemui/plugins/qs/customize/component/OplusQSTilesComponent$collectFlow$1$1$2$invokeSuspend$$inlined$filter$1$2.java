package com.oplus.systemui.plugins.qs.customize.component;

import com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditEffect;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditEffectWrapper;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlinx.coroutines.flow.FlowCollector;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSTilesComponent$collectFlow$1$1$2$invokeSuspend$$inlined$filter$1$2 implements FlowCollector {
    public /* synthetic */ FlowCollector $this_unsafeFlow;

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object emit(Object obj, Continuation continuation) {
        1 r0;
        if (continuation instanceof 1) {
            r0 = (1) continuation;
            int i = r0.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                r0.label = i - Integer.MIN_VALUE;
            } else {
                r0 = new 1(this, continuation);
            }
        } else {
            r0 = new 1(this, continuation);
        }
        Object obj2 = r0.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = r0.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj2);
            FlowCollector flowCollector = this.$this_unsafeFlow;
            TileEditEffectWrapper tileEditEffectWrapper = (TileEditEffectWrapper) obj;
            if (tileEditEffectWrapper.getTarget() == IntentTarget.OTHER_LIST && (tileEditEffectWrapper.getEffect() instanceof TileEditEffect.TransformToSpecEffect)) {
                r0.L$0 = SpillingKt.nullOutSpilledVariable(obj);
                r0.L$1 = SpillingKt.nullOutSpilledVariable(r0);
                r0.L$2 = SpillingKt.nullOutSpilledVariable(obj);
                r0.L$3 = SpillingKt.nullOutSpilledVariable(flowCollector);
                r0.I$0 = 0;
                r0.label = 1;
                if (flowCollector.emit(obj, r0) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj2);
        }
        return Unit.INSTANCE;
    }
}

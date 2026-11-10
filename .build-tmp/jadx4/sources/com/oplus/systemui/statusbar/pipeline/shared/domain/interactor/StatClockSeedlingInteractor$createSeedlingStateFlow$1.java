package com.oplus.systemui.statusbar.pipeline.shared.domain.interactor;

import androidx.compose.foundation.gestures.DefaultDraggableAnchors$;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.oplus.systemui.statusbar.pipeline.shared.domain.model.StatClockSeedlingState;
import com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class StatClockSeedlingInteractor$createSeedlingStateFlow$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ StatClockSeedlingInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StatClockSeedlingInteractor$createSeedlingStateFlow$1(StatClockSeedlingInteractor statClockSeedlingInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = statClockSeedlingInteractor;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        StatClockSeedlingInteractor$createSeedlingStateFlow$1 statClockSeedlingInteractor$createSeedlingStateFlow$1 = new StatClockSeedlingInteractor$createSeedlingStateFlow$1(this.this$0, continuation);
        statClockSeedlingInteractor$createSeedlingStateFlow$1.L$0 = obj;
        return statClockSeedlingInteractor$createSeedlingStateFlow$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        ProducerScope producerScope = (ProducerScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            Ref.ObjectRef objectRefM = DefaultDraggableAnchors$.ExternalSyntheticOutline0.m(obj);
            HiddenIconDispatcher hiddenIconDispatcher = this.this$0.hiddenIconDispatcher;
            StatusBarLocation statusBarLocation = StatusBarLocation.HOME;
            objectRefM.element = new StatClockSeedlingState(hiddenIconDispatcher.getHiddenAreas(statusBarLocation), this.this$0.hiddenIconDispatcher.getLastShouldScale(statusBarLocation));
            StatClockSeedlingInteractor$createSeedlingStateFlow$1$receiver$1 statClockSeedlingInteractor$createSeedlingStateFlow$1$receiver$1 = new StatClockSeedlingInteractor$createSeedlingStateFlow$1$receiver$1();
            statClockSeedlingInteractor$createSeedlingStateFlow$1$receiver$1.$currentState = objectRefM;
            statClockSeedlingInteractor$createSeedlingStateFlow$1$receiver$1.$$this$callbackFlow = producerScope;
            VarHandle.storeStoreFence();
            this.this$0.hiddenIconDispatcher.addHiddenReceiver(statusBarLocation, statClockSeedlingInteractor$createSeedlingStateFlow$1$receiver$1);
            producerScope.trySend-JP2dKIU(objectRefM.element);
            StatClockSeedlingInteractor statClockSeedlingInteractor = this.this$0;
            StatClockSeedlingInteractor$createSeedlingStateFlow$1$$ExternalSyntheticLambda0 statClockSeedlingInteractor$createSeedlingStateFlow$1$$ExternalSyntheticLambda0 = new StatClockSeedlingInteractor$createSeedlingStateFlow$1$$ExternalSyntheticLambda0();
            statClockSeedlingInteractor$createSeedlingStateFlow$1$$ExternalSyntheticLambda0.f$0 = statClockSeedlingInteractor;
            statClockSeedlingInteractor$createSeedlingStateFlow$1$$ExternalSyntheticLambda0.f$1 = statClockSeedlingInteractor$createSeedlingStateFlow$1$receiver$1;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable(objectRefM);
            this.L$2 = SpillingKt.nullOutSpilledVariable(statClockSeedlingInteractor$createSeedlingStateFlow$1$receiver$1);
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, statClockSeedlingInteractor$createSeedlingStateFlow$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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

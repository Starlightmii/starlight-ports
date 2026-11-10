package com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor;

import android.content.Context;
import com.android.systemui.tuner.TunerService;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusMobileIconsInteractorImpl$forceHiddenHDPlus$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    final /* synthetic */ TunerService $tunerService;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusMobileIconsInteractorImpl$forceHiddenHDPlus$1(TunerService tunerService, Context context, Continuation continuation) {
        super(2, continuation);
        this.$tunerService = tunerService;
        this.$context = context;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        OplusMobileIconsInteractorImpl$forceHiddenHDPlus$1 oplusMobileIconsInteractorImpl$forceHiddenHDPlus$1 = new OplusMobileIconsInteractorImpl$forceHiddenHDPlus$1(this.$tunerService, this.$context, continuation);
        oplusMobileIconsInteractorImpl$forceHiddenHDPlus$1.L$0 = obj;
        return oplusMobileIconsInteractorImpl$forceHiddenHDPlus$1;
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
            Context context = this.$context;
            OplusMobileIconsInteractorImpl$forceHiddenHDPlus$1$callback$1 oplusMobileIconsInteractorImpl$forceHiddenHDPlus$1$callback$1 = new OplusMobileIconsInteractorImpl$forceHiddenHDPlus$1$callback$1();
            oplusMobileIconsInteractorImpl$forceHiddenHDPlus$1$callback$1.$context = context;
            oplusMobileIconsInteractorImpl$forceHiddenHDPlus$1$callback$1.$$this$conflatedCallbackFlow = producerScope;
            VarHandle.storeStoreFence();
            this.$tunerService.addTunable(oplusMobileIconsInteractorImpl$forceHiddenHDPlus$1$callback$1, new String[]{"icon_blacklist"});
            TunerService tunerService = this.$tunerService;
            OplusMobileIconsInteractorImpl$forceHiddenHDPlus$1$$ExternalSyntheticLambda0 oplusMobileIconsInteractorImpl$forceHiddenHDPlus$1$$ExternalSyntheticLambda0 = new OplusMobileIconsInteractorImpl$forceHiddenHDPlus$1$$ExternalSyntheticLambda0();
            oplusMobileIconsInteractorImpl$forceHiddenHDPlus$1$$ExternalSyntheticLambda0.f$0 = tunerService;
            oplusMobileIconsInteractorImpl$forceHiddenHDPlus$1$$ExternalSyntheticLambda0.f$1 = oplusMobileIconsInteractorImpl$forceHiddenHDPlus$1$callback$1;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable(oplusMobileIconsInteractorImpl$forceHiddenHDPlus$1$callback$1);
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, oplusMobileIconsInteractorImpl$forceHiddenHDPlus$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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

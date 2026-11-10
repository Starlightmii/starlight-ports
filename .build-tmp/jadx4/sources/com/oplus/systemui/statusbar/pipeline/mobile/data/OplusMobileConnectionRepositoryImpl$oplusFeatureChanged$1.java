package com.oplus.systemui.statusbar.pipeline.mobile.data;

import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    public final Continuation create(Object obj, Continuation continuation) {
        OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1 oplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1 = new OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1(2, continuation);
        oplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1.L$0 = obj;
        return oplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1;
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
            OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1 oplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1 = new OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1();
            oplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1.$$this$conflatedCallbackFlow = producerScope;
            VarHandle.storeStoreFence();
            CustomizeFeatureOption.sSlotFeatureChangeCallBacks.add(oplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1);
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, OplusMobileConnectionRepositoryImpl.TAG, "slot oplusFeatureChanged regist!!!", null, 8);
            producerScope.trySend-JP2dKIU(Boxing.boxBoolean(false));
            OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$$ExternalSyntheticLambda0 oplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$$ExternalSyntheticLambda0 = new OplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$$ExternalSyntheticLambda0();
            oplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$$ExternalSyntheticLambda0.f$0 = oplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable(oplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$featureChangeCallBack$1);
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, oplusMobileConnectionRepositoryImpl$oplusFeatureChanged$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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

package com.oplus.systemui.statusbar.pipeline;

import com.android.systemui.kairos.BuildScopeKt;
import com.oplusos.systemui.common.typeface.SystemTypefaceHelper;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.text.Regex$;
import kotlinx.coroutines.channels.ProducerScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusWifiSignalExImpl$bindEx$typefaceCallback$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ OplusWifiSignalExImpl this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.OplusWifiSignalExImpl$bindEx$typefaceCallback$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1 {
        public final Object invoke(Object obj) {
            return Integer.valueOf(((SystemTypefaceHelper) ((CallableReference) this).receiver).mapNetworkTypeStatusBarIconWeight(((Number) obj).intValue()));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusWifiSignalExImpl$bindEx$typefaceCallback$1(OplusWifiSignalExImpl oplusWifiSignalExImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = oplusWifiSignalExImpl;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        OplusWifiSignalExImpl$bindEx$typefaceCallback$1 oplusWifiSignalExImpl$bindEx$typefaceCallback$1 = new OplusWifiSignalExImpl$bindEx$typefaceCallback$1(this.this$0, continuation);
        oplusWifiSignalExImpl$bindEx$typefaceCallback$1.L$0 = obj;
        return oplusWifiSignalExImpl$bindEx$typefaceCallback$1;
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
            OplusWifiSignalExImpl oplusWifiSignalExImpl = this.this$0;
            OplusWifiSignalExImpl$bindEx$typefaceCallback$1$callback$1 oplusWifiSignalExImpl$bindEx$typefaceCallback$1$callback$1 = new OplusWifiSignalExImpl$bindEx$typefaceCallback$1$callback$1();
            oplusWifiSignalExImpl$bindEx$typefaceCallback$1$callback$1.this$0 = oplusWifiSignalExImpl;
            oplusWifiSignalExImpl$bindEx$typefaceCallback$1$callback$1.$$this$conflatedCallbackFlow = producerScope;
            VarHandle.storeStoreFence();
            SystemTypefaceHelper.Companion companion = SystemTypefaceHelper.Companion;
            companion.getInstance(oplusWifiSignalExImpl.context).addCallback(oplusWifiSignalExImpl$bindEx$typefaceCallback$1$callback$1, new AnonymousClass1(1, companion.getInstance(this.this$0.context), SystemTypefaceHelper.class, "mapNetworkTypeStatusBarIconWeight", "mapNetworkTypeStatusBarIconWeight(I)I", 0));
            OplusWifiSignalExImpl oplusWifiSignalExImpl2 = this.this$0;
            OplusWifiSignalExImpl$bindEx$typefaceCallback$1$$ExternalSyntheticLambda0 oplusWifiSignalExImpl$bindEx$typefaceCallback$1$$ExternalSyntheticLambda0 = new OplusWifiSignalExImpl$bindEx$typefaceCallback$1$$ExternalSyntheticLambda0();
            oplusWifiSignalExImpl$bindEx$typefaceCallback$1$$ExternalSyntheticLambda0.f$0 = oplusWifiSignalExImpl2;
            oplusWifiSignalExImpl$bindEx$typefaceCallback$1$$ExternalSyntheticLambda0.f$1 = oplusWifiSignalExImpl$bindEx$typefaceCallback$1$callback$1;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable(oplusWifiSignalExImpl$bindEx$typefaceCallback$1$callback$1);
            this.label = 1;
            if (BuildScopeKt.awaitClose(oplusWifiSignalExImpl$bindEx$typefaceCallback$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        Regex$.ExternalSyntheticBUOutline0.m$1();
        return null;
    }
}

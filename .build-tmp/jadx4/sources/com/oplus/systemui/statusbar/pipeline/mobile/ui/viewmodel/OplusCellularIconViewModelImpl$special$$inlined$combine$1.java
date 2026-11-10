package com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel;

import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.internal.CombineKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCellularIconViewModelImpl$special$$inlined$combine$1 implements Flow {
    public /* synthetic */ Flow[] $flows$inlined;
    public /* synthetic */ OplusCellularIconViewModelImpl this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusCellularIconViewModelImpl$special$$inlined$combine$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Function0 {
        public /* synthetic */ Flow[] $flows;

        public final Object invoke() {
            return new Object[this.$flows.length];
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusCellularIconViewModelImpl$special$$inlined$combine$1$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 extends SuspendLambda implements Function3 {
        int I$0;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        final /* synthetic */ OplusCellularIconViewModelImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(OplusCellularIconViewModelImpl oplusCellularIconViewModelImpl, Continuation continuation) {
            super(3, continuation);
            this.this$0 = oplusCellularIconViewModelImpl;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.this$0, (Continuation) obj3);
            anonymousClass3.L$0 = (FlowCollector) obj;
            anonymousClass3.L$1 = (Object[]) obj2;
            return anonymousClass3.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Object[] objArr = (Object[]) this.L$1;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Object obj2 = objArr[0];
                Object obj3 = objArr[1];
                Object obj4 = objArr[2];
                Object obj5 = objArr[3];
                Object obj6 = objArr[4];
                boolean zBooleanValue = ((Boolean) objArr[5]).booleanValue();
                boolean zBooleanValue2 = ((Boolean) obj6).booleanValue();
                boolean zBooleanValue3 = ((Boolean) obj5).booleanValue();
                boolean zBooleanValue4 = ((Boolean) obj4).booleanValue();
                boolean zBooleanValue5 = ((Boolean) obj3).booleanValue();
                boolean zBooleanValue6 = ((Boolean) obj2).booleanValue();
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    int subscriptionId = this.this$0.getSubscriptionId();
                    FeatureOption.INSTANCE.getClass();
                    StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("showNetworkTypeIcon subId:", subscriptionId, " isExpRegion: ", " dataConnected:", FeatureOption.isExpRegion());
                    KeyguardLogger$.ExternalSyntheticOutline0.m(" dataEnabled:", " alwaysShow:", sbM, zBooleanValue6, zBooleanValue5);
                    KeyguardLogger$.ExternalSyntheticOutline0.m(" mobileIsDefault:", " carrierNetworkChange:", sbM, zBooleanValue4, zBooleanValue3);
                    SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("OplusCellularIconViewModel-->", "SystemUi--Statusbar", sbM, zBooleanValue2);
                }
                Boolean boolBoxBoolean = Boxing.boxBoolean(zBooleanValue4 || (!zBooleanValue2 && ((zBooleanValue5 || zBooleanValue) && zBooleanValue6 && zBooleanValue3)));
                this.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
                this.L$1 = SpillingKt.nullOutSpilledVariable(objArr);
                this.label = 1;
                if (flowCollector.emit(boolBoxBoolean, this) == coroutine_suspended) {
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

    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        Flow[] flowArr = this.$flows$inlined;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.$flows = flowArr;
        VarHandle.storeStoreFence();
        Object objCombineInternal = CombineKt.combineInternal(flowCollector, flowArr, anonymousClass2, new AnonymousClass3(this.this$0, null), continuation);
        return objCombineInternal == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCombineInternal : Unit.INSTANCE;
    }
}

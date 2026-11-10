package com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor;

import com.android.systemui.statusbar.pipeline.mobile.data.model.SubscriptionModel;
import com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel;
import com.android.systemui.statusbar.pipeline.shared.data.model.DefaultConnectionModel;
import com.oplus.systemui.statusbar.pipeline.mobile.data.model.CapabilityTypeModel;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
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
public final class OplusMobileIconInteractorImpl$special$$inlined$combine$1 implements Flow {
    public /* synthetic */ Flow[] $flows$inlined;
    public final /* synthetic */ int $r8$classId;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconInteractorImpl$special$$inlined$combine$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Function0 {
        public /* synthetic */ Flow[] $flows;
        public final /* synthetic */ int $r8$classId;

        public /* synthetic */ AnonymousClass2(int i) {
            this.$r8$classId = i;
        }

        public final Object invoke() {
            switch (this.$r8$classId) {
                case 0:
                    return new Object[this.$flows.length];
                case 1:
                    return new Object[this.$flows.length];
                default:
                    return new Object[this.$flows.length];
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconInteractorImpl$special$$inlined$combine$1$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 extends SuspendLambda implements Function3 {
        int I$0;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(3, (Continuation) obj3);
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
                Object obj7 = objArr[5];
                Object obj8 = objArr[6];
                boolean zBooleanValue = ((Boolean) objArr[7]).booleanValue();
                SignalIconModel.CellularTypeIconModel.Cellular cellularCopy = (SignalIconModel) obj8;
                DefaultConnectionModel defaultConnectionModel = (DefaultConnectionModel) obj7;
                SubscriptionModel subscriptionModel = (SubscriptionModel) obj6;
                CapabilityTypeModel capabilityTypeModel = (CapabilityTypeModel) obj5;
                boolean zBooleanValue2 = ((Boolean) obj4).booleanValue();
                boolean zBooleanValue3 = ((Boolean) obj3).booleanValue();
                boolean zBooleanValue4 = ((Boolean) obj2).booleanValue();
                if (cellularCopy instanceof SignalIconModel.CellularTypeIconModel.Cellular) {
                    SignalIconModel.CellularTypeIconModel.Cellular cellular = cellularCopy;
                    cellularCopy = cellular.copy(cellular.getLevel(), cellular.getNumberOfLevels(), cellular.getShowExclamationMark(), cellular.getCarrierNetworkChange(), zBooleanValue4, subscriptionModel != null ? subscriptionModel.getPhoneId() : -1, zBooleanValue2, zBooleanValue3, capabilityTypeModel.getImsRegistered(), (defaultConnectionModel.isValidated || !defaultConnectionModel.getMobile().isDefault) ? 1 : 0, zBooleanValue);
                }
                this.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
                this.L$1 = SpillingKt.nullOutSpilledVariable(objArr);
                this.label = 1;
                if (flowCollector.emit(cellularCopy, this) == coroutine_suspended) {
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
        switch (this.$r8$classId) {
            case 0:
                Flow[] flowArr = this.$flows$inlined;
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(0);
                anonymousClass2.$flows = flowArr;
                VarHandle.storeStoreFence();
                Object objCombineInternal = CombineKt.combineInternal(flowCollector, flowArr, anonymousClass2, new AnonymousClass3(3, null), continuation);
                return objCombineInternal == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCombineInternal : Unit.INSTANCE;
            default:
                Flow[] flowArr2 = this.$flows$inlined;
                AnonymousClass2 anonymousClass3 = new AnonymousClass2(1);
                anonymousClass3.$flows = flowArr2;
                VarHandle.storeStoreFence();
                Object objCombineInternal2 = CombineKt.combineInternal(flowCollector, flowArr2, anonymousClass3, new OplusMobileIconInteractorImpl$special$$inlined$combine$2$3(3, null), continuation);
                return objCombineInternal2 == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCombineInternal2 : Unit.INSTANCE;
        }
    }
}

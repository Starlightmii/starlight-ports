package com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor;

import com.android.systemui.statusbar.pipeline.mobile.data.model.SubscriptionModel;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$callbackEvents$1$1$;
import com.oplus.systemui.statusbar.pipeline.mobile.data.model.CapabilityTypeModel;
import com.oplus.systemui.statusbar.pipeline.mobile.data.model.SimStateModel;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMobileIconInteractorImpl$special$$inlined$combine$2$3 extends SuspendLambda implements Function3 {
    int I$0;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        OplusMobileIconInteractorImpl$special$$inlined$combine$2$3 oplusMobileIconInteractorImpl$special$$inlined$combine$2$3 = new OplusMobileIconInteractorImpl$special$$inlined$combine$2$3(3, (Continuation) obj3);
        oplusMobileIconInteractorImpl$special$$inlined$combine$2$3.L$0 = (FlowCollector) obj;
        oplusMobileIconInteractorImpl$special$$inlined$combine$2$3.L$1 = (Object[]) obj2;
        return oplusMobileIconInteractorImpl$special$$inlined$combine$2$3.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Boolean boolBoxBoolean;
        FlowCollector flowCollector = (FlowCollector) this.L$0;
        Object[] objArr = (Object[]) this.L$1;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            boolean z = false;
            Object obj2 = objArr[0];
            Object obj3 = objArr[1];
            Object obj4 = objArr[2];
            Object obj5 = objArr[3];
            Object obj6 = objArr[4];
            Object obj7 = objArr[5];
            CapabilityTypeModel capabilityTypeModel = (CapabilityTypeModel) objArr[6];
            boolean zBooleanValue = ((Boolean) obj7).booleanValue();
            SubscriptionModel subscriptionModel = (SubscriptionModel) obj6;
            boolean zBooleanValue2 = ((Boolean) obj5).booleanValue();
            boolean zBooleanValue3 = ((Boolean) obj4).booleanValue();
            SimStateModel simStateModel = (SimStateModel) obj3;
            ((Boolean) obj2).getClass();
            int phoneId = subscriptionModel != null ? subscriptionModel.getPhoneId() : -1;
            if (phoneId < 0 || phoneId > 1) {
                boolBoxBoolean = Boxing.boxBoolean(false);
            } else {
                FeatureOption.INSTANCE.getClass();
                if (!FeatureOption.isExpRegion() || simStateModel.getSimStateIMSI()[phoneId] || simStateModel.getSimLoaded()[phoneId]) {
                    if (!CustomizeFeatureOption.checkSlotIdValid(phoneId) ? false : CustomizeFeatureOption.sIsSupportHideVoLTEIcon[phoneId]) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                            MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(phoneId, "shouldShowVoLTE isSupportHideVoLTE", ", return false", "OplusMobileIconInteractorImpl-->", "SystemUi--Statusbar");
                        }
                        boolBoxBoolean = Boxing.boxBoolean(false);
                    } else if (capabilityTypeModel.getHasHideVolteOperator() && zBooleanValue) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                            MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(phoneId, "Terrestrial hasHideVolteOperator ", ", return false", "OplusMobileIconInteractorImpl-->", "SystemUi--Statusbar");
                        }
                        boolBoxBoolean = Boxing.boxBoolean(false);
                    } else {
                        if (simStateModel.getSimInserted()[phoneId] && (zBooleanValue3 || zBooleanValue2)) {
                            z = true;
                        }
                        boolBoxBoolean = Boxing.boxBoolean(z);
                    }
                } else {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                        MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(phoneId, "shouldShowVoLTE !simStateIMSI slotId:", ", return false", "OplusMobileIconInteractorImpl-->", "SystemUi--Statusbar");
                    }
                    boolBoxBoolean = Boxing.boxBoolean(false);
                }
            }
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

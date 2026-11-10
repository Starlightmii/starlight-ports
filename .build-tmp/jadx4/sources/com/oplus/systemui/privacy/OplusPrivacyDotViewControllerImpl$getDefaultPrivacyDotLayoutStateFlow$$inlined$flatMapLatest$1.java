package com.oplus.systemui.privacy;

import com.android.systemui.statusbar.phone.PhoneStatusBarViewEx;
import com.android.systemui.statusbar.phone.fragment.dagger.HomeStatusBarComponent;
import com.oplus.systemui.statusbar.phone.PhoneStatusBarViewExImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPrivacyDotViewControllerImpl$getDefaultPrivacyDotLayoutStateFlow$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        OplusPrivacyDotViewControllerImpl$getDefaultPrivacyDotLayoutStateFlow$$inlined$flatMapLatest$1 oplusPrivacyDotViewControllerImpl$getDefaultPrivacyDotLayoutStateFlow$$inlined$flatMapLatest$1 = new OplusPrivacyDotViewControllerImpl$getDefaultPrivacyDotLayoutStateFlow$$inlined$flatMapLatest$1(3, (Continuation) obj3);
        oplusPrivacyDotViewControllerImpl$getDefaultPrivacyDotLayoutStateFlow$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        oplusPrivacyDotViewControllerImpl$getDefaultPrivacyDotLayoutStateFlow$$inlined$flatMapLatest$1.L$1 = obj2;
        return oplusPrivacyDotViewControllerImpl$getDefaultPrivacyDotLayoutStateFlow$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        StateFlow stateFlowFlowOf;
        FlowCollector flowCollector = (FlowCollector) this.L$0;
        Object obj2 = this.L$1;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            HomeStatusBarComponent homeStatusBarComponent = (HomeStatusBarComponent) obj2;
            PhoneStatusBarViewEx phoneStatusBarViewEx = homeStatusBarComponent != null ? homeStatusBarComponent.getPhoneStatusBarViewEx() : null;
            PhoneStatusBarViewExImpl phoneStatusBarViewExImpl = phoneStatusBarViewEx instanceof PhoneStatusBarViewExImpl ? (PhoneStatusBarViewExImpl) phoneStatusBarViewEx : null;
            if (phoneStatusBarViewExImpl == null || (stateFlowFlowOf = phoneStatusBarViewExImpl.getPrivacyDotLayoutStateFlow()) == null) {
                stateFlowFlowOf = FlowKt.flowOf(PrivacyDotLayoutState.Companion.getEMPTY());
            }
            this.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
            this.L$1 = SpillingKt.nullOutSpilledVariable(obj2);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, stateFlowFlowOf, this) == coroutine_suspended) {
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

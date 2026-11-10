package com.oplus.systemui.statusbar.phone.signal;

import com.oplus.systemui.statusbar.pipeline.mobile.data.model.SimStateModel;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusStatusBarSignalPolicy$noSimIcon$1 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;
    final /* synthetic */ OplusStatusBarSignalPolicy this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusStatusBarSignalPolicy$noSimIcon$1(OplusStatusBarSignalPolicy oplusStatusBarSignalPolicy, Continuation continuation) {
        super(4, continuation);
        this.this$0 = oplusStatusBarSignalPolicy;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj3).booleanValue();
        OplusStatusBarSignalPolicy$noSimIcon$1 oplusStatusBarSignalPolicy$noSimIcon$1 = new OplusStatusBarSignalPolicy$noSimIcon$1(this.this$0, (Continuation) obj4);
        oplusStatusBarSignalPolicy$noSimIcon$1.L$0 = (SimStateModel) obj;
        oplusStatusBarSignalPolicy$noSimIcon$1.Z$0 = zBooleanValue;
        oplusStatusBarSignalPolicy$noSimIcon$1.Z$1 = zBooleanValue2;
        return oplusStatusBarSignalPolicy$noSimIcon$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0096  */
    public final Object invokeSuspend(Object obj) {
        int i;
        SimStateModel simStateModel = (SimStateModel) this.L$0;
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, "OplusStatusBarSignalPolicy", "noSimIcon simState: " + simStateModel + ", noSubs:" + z + " isAirplaneMode:" + z2, null, 8);
        int i2 = 0;
        if (z2) {
            i = i2;
        } else {
            i = 2131236842;
            if (CustomizeFeatureOption.sIsShowCtaNoSimView) {
                i2 = !simStateModel.getSimInserted()[0] ? 2131236842 : 0;
                if (simStateModel.getSimInserted()[1]) {
                    i = 0;
                }
            } else {
                if (simStateModel.isNoSim() && z) {
                    FeatureOption.INSTANCE.getClass();
                    if (!FeatureOption.isWifiOnly() && !QSFeatureOption.INSTANCE.isVirtualCommConsumerDevice(this.this$0.context)) {
                        if (simStateModel.isAnySimInserted()) {
                            i = (CustomizeFeatureOption.sIsTmoSignalViewLayout || CustomizeFeatureOption.sIsSprintSignalViewLayout) ? 2131237031 : 2131236821;
                        }
                        i2 = i;
                    }
                }
                i = i2;
            }
        }
        return new OplusStatusBarSignalPolicy.NoSimIcon(i2, i2, i);
    }
}

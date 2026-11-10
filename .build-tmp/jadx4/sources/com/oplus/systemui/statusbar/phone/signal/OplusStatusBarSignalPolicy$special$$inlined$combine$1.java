package com.oplus.systemui.statusbar.phone.signal;

import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.systemui.statusbar.phone.signal.model.OplusIMSModel;
import com.oplusos.systemui.common.util.LogUtil;
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
public final class OplusStatusBarSignalPolicy$special$$inlined$combine$1 implements Flow {
    public /* synthetic */ Flow[] $flows$inlined;
    public /* synthetic */ OplusStatusBarSignalPolicy this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.signal.OplusStatusBarSignalPolicy$special$$inlined$combine$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Function0 {
        public /* synthetic */ Flow[] $flows;

        public final Object invoke() {
            return new Object[this.$flows.length];
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.signal.OplusStatusBarSignalPolicy$special$$inlined$combine$1$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 extends SuspendLambda implements Function3 {
        int I$0;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        final /* synthetic */ OplusStatusBarSignalPolicy this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(OplusStatusBarSignalPolicy oplusStatusBarSignalPolicy, Continuation continuation) {
            super(3, continuation);
            this.this$0 = oplusStatusBarSignalPolicy;
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
                OplusIMSModel oplusIMSModel = (OplusIMSModel) obj3;
                OplusIMSModel oplusIMSModel2 = (OplusIMSModel) obj2;
                boolean z = (oplusIMSModel2.getNoSim() || oplusIMSModel.getNoSim()) ? false : true;
                OplusStatusBarSignalPolicy oplusStatusBarSignalPolicy = this.this$0;
                if (zBooleanValue3) {
                    oplusStatusBarSignalPolicy.volteIcon = 0;
                    oplusStatusBarSignalPolicy.volteContenDescription = "";
                } else {
                    OplusStatusBarSignalPolicy.access$updateVo5GIcon(oplusStatusBarSignalPolicy, oplusIMSModel2, oplusIMSModel, z);
                    OplusStatusBarSignalPolicy.access$updateHDPlusNewIcon(this.this$0, oplusIMSModel2, oplusIMSModel, z, zBooleanValue);
                    OplusStatusBarSignalPolicy oplusStatusBarSignalPolicy2 = this.this$0;
                    int i2 = oplusStatusBarSignalPolicy2.vo5GIcon;
                    if (i2 > 0) {
                        oplusStatusBarSignalPolicy2.volteIcon = i2;
                    } else {
                        int i3 = oplusStatusBarSignalPolicy2.hdPlusNewIcon;
                        if (i3 > 0) {
                            oplusStatusBarSignalPolicy2.volteIcon = i3;
                        } else {
                            OplusStatusBarSignalPolicy.access$updateVolteIcon(oplusStatusBarSignalPolicy2, oplusIMSModel2, oplusIMSModel, z);
                        }
                    }
                }
                OplusStatusBarSignalPolicy.access$updateVoWifiIcon(this.this$0, oplusIMSModel2, oplusIMSModel, z, zBooleanValue2);
                StringBuilder sb = new StringBuilder("imsIcon: ");
                sb.append(oplusIMSModel2);
                sb.append(", ");
                sb.append(oplusIMSModel);
                sb.append(", noSubs:");
                KeyguardLogger$.ExternalSyntheticOutline0.m(", airplaneMode:", " is5GNewCall:", sb, zBooleanValue4, zBooleanValue3);
                sb.append(zBooleanValue);
                LogUtil.i$default(LogUtil.TAG_STATUSBAR, "OplusStatusBarSignalPolicy", sb.toString(), null, 8);
                OplusStatusBarSignalPolicy oplusStatusBarSignalPolicy3 = this.this$0;
                int i4 = oplusStatusBarSignalPolicy3.volteIcon;
                String str = oplusStatusBarSignalPolicy3.volteContenDescription;
                OplusStatusBarSignalPolicy oplusStatusBarSignalPolicy4 = this.this$0;
                OplusStatusBarSignalPolicy.IMSIcon iMSIcon = new OplusStatusBarSignalPolicy.IMSIcon(i4, oplusStatusBarSignalPolicy4.vowifiIcon, str, oplusStatusBarSignalPolicy4.vowifiContenDescription, 16, 0);
                this.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
                this.L$1 = SpillingKt.nullOutSpilledVariable(objArr);
                this.label = 1;
                if (flowCollector.emit(iMSIcon, this) == coroutine_suspended) {
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

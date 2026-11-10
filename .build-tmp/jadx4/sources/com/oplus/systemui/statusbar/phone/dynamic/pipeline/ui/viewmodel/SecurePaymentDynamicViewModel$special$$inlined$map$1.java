package com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel;

import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.model.DynamicIconModel;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.model.IconSource;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SecurePaymentDynamicViewModel$special$$inlined$map$1 implements Flow {
    public /* synthetic */ StateFlow $this_unsafeTransform$inlined;
    public /* synthetic */ SecurePaymentDynamicViewModel this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel.SecurePaymentDynamicViewModel$special$$inlined$map$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements FlowCollector {
        public /* synthetic */ FlowCollector $this_unsafeFlow;
        public /* synthetic */ SecurePaymentDynamicViewModel this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel.SecurePaymentDynamicViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
        public final class AnonymousClass1 extends ContinuationImpl {
            int I$0;
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            int label;
            /* synthetic */ Object result;

            public AnonymousClass1(Continuation continuation) {
                super(continuation);
            }

            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass2.this.emit(null, this);
            }
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0019  */
        public final Object emit(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1;
            long j;
            SecurePaymentDynamicViewModel securePaymentDynamicViewModel = this.this$0;
            if (continuation instanceof AnonymousClass1) {
                anonymousClass1 = (AnonymousClass1) continuation;
                int i = anonymousClass1.label;
                if ((i & Integer.MIN_VALUE) != 0) {
                    anonymousClass1.label = i - Integer.MIN_VALUE;
                } else {
                    anonymousClass1 = new AnonymousClass1(continuation);
                }
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
            Object obj2 = anonymousClass1.result;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = anonymousClass1.label;
            DynamicIconModel dynamicIconModel = null;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj2);
                FlowCollector flowCollector = this.$this_unsafeFlow;
                int iIntValue = ((Number) obj).intValue();
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(iIntValue, "iconModel=", "SecurePaymentDynamicViewModel-->", "SystemUi--Statusbar");
                }
                switch (iIntValue) {
                    case 1000:
                    case 1001:
                    case 1002:
                    case 1003:
                    case 1004:
                        String str = (String) securePaymentDynamicViewModel.texts.get(Boxing.boxInt(iIntValue));
                        if (str == null) {
                            str = "";
                        }
                        String str2 = str;
                        switch (iIntValue) {
                            case 1000:
                            case 1001:
                                j = -1;
                                break;
                            case 1002:
                            case 1003:
                            case 1004:
                                j = 3000;
                                break;
                            default:
                                j = 0;
                                break;
                        }
                        long j2 = j;
                        IconSource.StateLinkIconSource stateLinkIconSource = new IconSource.StateLinkIconSource(true);
                        stateLinkIconSource.iconState = iIntValue;
                        VarHandle.storeStoreFence();
                        dynamicIconModel = new DynamicIconModel(0, 20, str2, j2, stateLinkIconSource, securePaymentDynamicViewModel.slot, str2);
                        break;
                }
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(obj);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(anonymousClass1);
                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(obj);
                anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(flowCollector);
                anonymousClass1.I$0 = 0;
                anonymousClass1.label = 1;
                if (flowCollector.emit(dynamicIconModel, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj2);
            }
            return Unit.INSTANCE;
        }
    }

    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        StateFlow stateFlow = this.$this_unsafeTransform$inlined;
        SecurePaymentDynamicViewModel securePaymentDynamicViewModel = this.this$0;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.$this_unsafeFlow = flowCollector;
        anonymousClass2.this$0 = securePaymentDynamicViewModel;
        VarHandle.storeStoreFence();
        Object objCollect = stateFlow.collect(anonymousClass2, continuation);
        return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }
}

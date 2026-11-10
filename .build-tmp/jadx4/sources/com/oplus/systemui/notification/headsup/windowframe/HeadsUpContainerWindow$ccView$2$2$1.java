package com.oplus.systemui.notification.headsup.windowframe;

import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class HeadsUpContainerWindow$ccView$2$2$1 extends SuspendLambda implements Function3 {
    int label;
    final /* synthetic */ HeadsUpContainerWindow this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.windowframe.HeadsUpContainerWindow$ccView$2$2$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements FlowCollector {
        public /* synthetic */ HeadsUpContainerWindow this$0;

        public final Object emit(Object obj, Continuation continuation) {
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            HeadsUpContainerWindow headsUpContainerWindow = this.this$0;
            if (zBooleanValue) {
                headsUpContainerWindow.lp.flags &= -9;
            } else {
                headsUpContainerWindow.lp.flags |= 8;
            }
            if (headsUpContainerWindow.getCcView().getParent() != null) {
                headsUpContainerWindow.getWm().updateViewLayout(headsUpContainerWindow.getCcView(), headsUpContainerWindow.lp);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeadsUpContainerWindow$ccView$2$2$1(HeadsUpContainerWindow headsUpContainerWindow, Continuation continuation) {
        super(3, continuation);
        this.this$0 = headsUpContainerWindow;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return new HeadsUpContainerWindow$ccView$2$2$1(this.this$0, (Continuation) obj3).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(this.this$0.remoteInputInteractor.isRemoteInputActive());
            HeadsUpContainerWindow headsUpContainerWindow = this.this$0;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = headsUpContainerWindow;
            VarHandle.storeStoreFence();
            this.label = 1;
            if (flowDistinctUntilChanged.collect(anonymousClass1, this) == coroutine_suspended) {
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

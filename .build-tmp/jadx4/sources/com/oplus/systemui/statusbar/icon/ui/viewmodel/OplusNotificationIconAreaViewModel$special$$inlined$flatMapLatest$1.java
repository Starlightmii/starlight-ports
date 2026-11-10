package com.oplus.systemui.statusbar.icon.ui.viewmodel;

import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconsViewData;
import com.oplus.systemui.statusbar.icon.data.NotificationIconAreaType;
import com.oplus.systemui.statusbar.icon.domain.OplusNotificationIconAreaInteractor;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationIconAreaViewModel$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ OplusNotificationIconAreaInteractor $oplusNotificationIconAreaInteractor$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ OplusNotificationIconAreaViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusNotificationIconAreaViewModel$special$$inlined$flatMapLatest$1(Continuation continuation, OplusNotificationIconAreaInteractor oplusNotificationIconAreaInteractor, OplusNotificationIconAreaViewModel oplusNotificationIconAreaViewModel) {
        super(3, continuation);
        this.$oplusNotificationIconAreaInteractor$inlined = oplusNotificationIconAreaInteractor;
        this.this$0 = oplusNotificationIconAreaViewModel;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        OplusNotificationIconAreaViewModel$special$$inlined$flatMapLatest$1 oplusNotificationIconAreaViewModel$special$$inlined$flatMapLatest$1 = new OplusNotificationIconAreaViewModel$special$$inlined$flatMapLatest$1((Continuation) obj3, this.$oplusNotificationIconAreaInteractor$inlined, this.this$0);
        oplusNotificationIconAreaViewModel$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        oplusNotificationIconAreaViewModel$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return oplusNotificationIconAreaViewModel$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Flow flowFlowOf;
        FlowCollector flowCollector = (FlowCollector) this.L$0;
        Object obj2 = this.L$1;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (((NotificationIconAreaType) obj2) == NotificationIconAreaType.NOTIFICATION_SHOW_ICON) {
                Flow flowConflate = FlowKt.conflate(FlowKt.combine(this.$oplusNotificationIconAreaInteractor$inlined.getOplusStatusBarNotifs(), this.$oplusNotificationIconAreaInteractor$inlined.getMaxIconSize(), new OplusNotificationIconAreaViewModel$icons$1$1(3, null)));
                OplusNotificationIconAreaViewModel oplusNotificationIconAreaViewModel = this.this$0;
                OplusNotificationIconAreaViewModel$icons$1$2 oplusNotificationIconAreaViewModel$icons$1$2 = new OplusNotificationIconAreaViewModel$icons$1$2();
                oplusNotificationIconAreaViewModel$icons$1$2.this$0 = oplusNotificationIconAreaViewModel;
                VarHandle.storeStoreFence();
                flowFlowOf = FlowKt.distinctUntilChanged(flowConflate, oplusNotificationIconAreaViewModel$icons$1$2);
            } else {
                flowFlowOf = FlowKt.flowOf(new NotificationIconsViewData((List) null, 0, 7));
            }
            this.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
            this.L$1 = SpillingKt.nullOutSpilledVariable(obj2);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flowFlowOf, this) == coroutine_suspended) {
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

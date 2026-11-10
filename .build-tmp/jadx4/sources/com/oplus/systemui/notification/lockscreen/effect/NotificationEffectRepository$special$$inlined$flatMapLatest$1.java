package com.oplus.systemui.notification.lockscreen.effect;

import com.android.systemui.util.settings.SettingsProxyExt;
import java.lang.invoke.VarHandle;
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
public final class NotificationEffectRepository$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ NotificationEffectRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationEffectRepository$special$$inlined$flatMapLatest$1(Continuation continuation, NotificationEffectRepository notificationEffectRepository) {
        super(3, continuation);
        this.this$0 = notificationEffectRepository;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        NotificationEffectRepository$special$$inlined$flatMapLatest$1 notificationEffectRepository$special$$inlined$flatMapLatest$1 = new NotificationEffectRepository$special$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0);
        notificationEffectRepository$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        notificationEffectRepository$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return notificationEffectRepository$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        FlowCollector flowCollector = (FlowCollector) this.L$0;
        Object obj2 = this.L$1;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            int iIntValue = ((Number) obj2).intValue();
            Flow flowOnStart = FlowKt.onStart(SettingsProxyExt.INSTANCE.observerFlow(this.this$0.systemSettings, iIntValue, new String[]{"system_material_stroke_enable"}), new NotificationEffectRepository$isStrokeLineEnable$1$1(2, null));
            NotificationEffectRepository notificationEffectRepository = this.this$0;
            NotificationEffectRepository$isStrokeLineEnable$lambda$2$$inlined$map$1 notificationEffectRepository$isStrokeLineEnable$lambda$2$$inlined$map$1 = new NotificationEffectRepository$isStrokeLineEnable$lambda$2$$inlined$map$1();
            notificationEffectRepository$isStrokeLineEnable$lambda$2$$inlined$map$1.$this_unsafeTransform$inlined = flowOnStart;
            notificationEffectRepository$isStrokeLineEnable$lambda$2$$inlined$map$1.this$0 = notificationEffectRepository;
            notificationEffectRepository$isStrokeLineEnable$lambda$2$$inlined$map$1.$user$inlined = iIntValue;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
            this.L$1 = SpillingKt.nullOutSpilledVariable(obj2);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, notificationEffectRepository$isStrokeLineEnable$lambda$2$$inlined$map$1, this) == coroutine_suspended) {
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

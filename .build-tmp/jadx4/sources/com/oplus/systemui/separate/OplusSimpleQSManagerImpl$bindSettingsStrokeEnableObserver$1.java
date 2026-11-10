package com.oplus.systemui.separate;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.oplus.systemui.notification.lockscreen.effect.NotificationEffectRepository;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusSimpleQSManagerImpl$bindSettingsStrokeEnableObserver$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ OplusQSSimpleHeader $header;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ OplusSimpleQSManagerImpl this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.separate.OplusSimpleQSManagerImpl$bindSettingsStrokeEnableObserver$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ OplusQSSimpleHeader $header;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ OplusSimpleQSManagerImpl this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.separate.OplusSimpleQSManagerImpl$bindSettingsStrokeEnableObserver$1$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C01071 extends SuspendLambda implements Function2 {
            final /* synthetic */ OplusQSSimpleHeader $header;
            int label;
            final /* synthetic */ OplusSimpleQSManagerImpl this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.separate.OplusSimpleQSManagerImpl$bindSettingsStrokeEnableObserver$1$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            public final class C01081 implements FlowCollector {
                public /* synthetic */ OplusQSSimpleHeader $header;

                public final Object emit(Object obj, Continuation continuation) {
                    ((Boolean) obj).booleanValue();
                    this.$header.refreshSettingsButtonBackground();
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01071(OplusSimpleQSManagerImpl oplusSimpleQSManagerImpl, OplusQSSimpleHeader oplusQSSimpleHeader, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusSimpleQSManagerImpl;
                this.$header = oplusQSSimpleHeader;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C01071(this.this$0, this.$header, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow stateFlowIsStrokeLineEnable = ((NotificationEffectRepository) this.this$0.notificationEffectRepository.get()).isStrokeLineEnable();
                    OplusQSSimpleHeader oplusQSSimpleHeader = this.$header;
                    C01081 c01081 = new C01081();
                    c01081.$header = oplusQSSimpleHeader;
                    VarHandle.storeStoreFence();
                    this.label = 1;
                    if (stateFlowIsStrokeLineEnable.collect(c01081, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                Regex$.ExternalSyntheticBUOutline0.m$1();
                return null;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(OplusSimpleQSManagerImpl oplusSimpleQSManagerImpl, OplusQSSimpleHeader oplusQSSimpleHeader, Continuation continuation) {
            super(2, continuation);
            this.this$0 = oplusSimpleQSManagerImpl;
            this.$header = oplusQSSimpleHeader;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$header, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            BuildersKt.launch$default(coroutineScope, Dispatchers.getMain(), (CoroutineStart) null, new C01071(this.this$0, this.$header, null), 2);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusSimpleQSManagerImpl$bindSettingsStrokeEnableObserver$1(OplusSimpleQSManagerImpl oplusSimpleQSManagerImpl, OplusQSSimpleHeader oplusQSSimpleHeader, Continuation continuation) {
        super(3, continuation);
        this.this$0 = oplusSimpleQSManagerImpl;
        this.$header = oplusQSSimpleHeader;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        OplusSimpleQSManagerImpl$bindSettingsStrokeEnableObserver$1 oplusSimpleQSManagerImpl$bindSettingsStrokeEnableObserver$1 = new OplusSimpleQSManagerImpl$bindSettingsStrokeEnableObserver$1(this.this$0, this.$header, (Continuation) obj3);
        oplusSimpleQSManagerImpl$bindSettingsStrokeEnableObserver$1.L$0 = (LifecycleOwner) obj;
        return oplusSimpleQSManagerImpl$bindSettingsStrokeEnableObserver$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Lifecycle.State state = Lifecycle.State.CREATED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$header, null);
            this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
            this.label = 1;
            if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass1, this) == coroutine_suspended) {
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

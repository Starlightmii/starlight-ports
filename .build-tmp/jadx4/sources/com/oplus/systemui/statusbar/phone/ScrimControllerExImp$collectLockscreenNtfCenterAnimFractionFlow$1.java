package com.oplus.systemui.statusbar.phone;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class ScrimControllerExImp$collectLockscreenNtfCenterAnimFractionFlow$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ScrimControllerExImp this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.ScrimControllerExImp$collectLockscreenNtfCenterAnimFractionFlow$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ ScrimControllerExImp this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.ScrimControllerExImp$collectLockscreenNtfCenterAnimFractionFlow$1$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C01361 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ ScrimControllerExImp this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01361(ScrimControllerExImp scrimControllerExImp, Continuation continuation) {
                super(2, continuation);
                this.this$0 = scrimControllerExImp;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C01361(this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow notificationCenterAnimFraction = this.this$0.oplusLockscreenShadeTransitionController.getNotificationCenterAnimFraction();
                    ScrimControllerExImp scrimControllerExImp = this.this$0;
                    ScrimControllerExImp$collectUseMotionBlur$2 scrimControllerExImp$collectUseMotionBlur$2 = new ScrimControllerExImp$collectUseMotionBlur$2(1);
                    scrimControllerExImp$collectUseMotionBlur$2.this$0 = scrimControllerExImp;
                    VarHandle.storeStoreFence();
                    this.label = 1;
                    if (notificationCenterAnimFraction.collect(scrimControllerExImp$collectUseMotionBlur$2, this) == coroutine_suspended) {
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
        public AnonymousClass1(ScrimControllerExImp scrimControllerExImp, Continuation continuation) {
            super(2, continuation);
            this.this$0 = scrimControllerExImp;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
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
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C01361(this.this$0, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScrimControllerExImp$collectLockscreenNtfCenterAnimFractionFlow$1(ScrimControllerExImp scrimControllerExImp, Continuation continuation) {
        super(3, continuation);
        this.this$0 = scrimControllerExImp;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        ScrimControllerExImp$collectLockscreenNtfCenterAnimFractionFlow$1 scrimControllerExImp$collectLockscreenNtfCenterAnimFractionFlow$1 = new ScrimControllerExImp$collectLockscreenNtfCenterAnimFractionFlow$1(this.this$0, (Continuation) obj3);
        scrimControllerExImp$collectLockscreenNtfCenterAnimFractionFlow$1.L$0 = (LifecycleOwner) obj;
        return scrimControllerExImp$collectLockscreenNtfCenterAnimFractionFlow$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Lifecycle.State state = Lifecycle.State.STARTED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, null);
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

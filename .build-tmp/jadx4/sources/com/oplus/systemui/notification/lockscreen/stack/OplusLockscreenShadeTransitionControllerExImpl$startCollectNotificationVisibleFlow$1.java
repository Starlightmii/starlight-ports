package com.oplus.systemui.notification.lockscreen.stack;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.DependencyEx;
import com.android.systemui.notification.stackednotification.StackedNotificationEx;
import com.android.systemui.shade.NotificationPanelViewController;
import com.oplusos.systemui.common.util.LogUtil;
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
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusLockscreenShadeTransitionControllerExImpl$startCollectNotificationVisibleFlow$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ OplusLockscreenShadeTransitionControllerExImpl this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.stack.OplusLockscreenShadeTransitionControllerExImpl$startCollectNotificationVisibleFlow$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ OplusLockscreenShadeTransitionControllerExImpl this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.stack.OplusLockscreenShadeTransitionControllerExImpl$startCollectNotificationVisibleFlow$1$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00261 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ OplusLockscreenShadeTransitionControllerExImpl this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.stack.OplusLockscreenShadeTransitionControllerExImpl$startCollectNotificationVisibleFlow$1$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            public final class C00271 implements FlowCollector {
                public /* synthetic */ OplusLockscreenShadeTransitionControllerExImpl this$0;

                public final Object emit(Object obj, Continuation continuation) {
                    boolean zBooleanValue = ((Boolean) obj).booleanValue();
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        OplusLockIconView$.ExternalSyntheticOutline0.m("isAnyNotificationVisible ", "OplusLockscreenShadeTransitionControllerExImpl-->", "SystemUi--Notification", zBooleanValue);
                    }
                    NotificationPanelViewController notificationPanelViewController = this.this$0.notificationPanelViewController;
                    if (notificationPanelViewController != null) {
                        notificationPanelViewController.positionClockAndNotifications(false);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00261(OplusLockscreenShadeTransitionControllerExImpl oplusLockscreenShadeTransitionControllerExImpl, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusLockscreenShadeTransitionControllerExImpl;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C00261(this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow stateFlowIsAnyNotificationVisibleForClock = ((StackedNotificationEx) DependencyEx.sDependency.getDependency(StackedNotificationEx.class)).isAnyNotificationVisibleForClock();
                    OplusLockscreenShadeTransitionControllerExImpl oplusLockscreenShadeTransitionControllerExImpl = this.this$0;
                    C00271 c00271 = new C00271();
                    c00271.this$0 = oplusLockscreenShadeTransitionControllerExImpl;
                    VarHandle.storeStoreFence();
                    this.label = 1;
                    if (stateFlowIsAnyNotificationVisibleForClock.collect(c00271, this) == coroutine_suspended) {
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
        public AnonymousClass1(OplusLockscreenShadeTransitionControllerExImpl oplusLockscreenShadeTransitionControllerExImpl, Continuation continuation) {
            super(2, continuation);
            this.this$0 = oplusLockscreenShadeTransitionControllerExImpl;
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
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00261(this.this$0, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusLockscreenShadeTransitionControllerExImpl$startCollectNotificationVisibleFlow$1(OplusLockscreenShadeTransitionControllerExImpl oplusLockscreenShadeTransitionControllerExImpl, Continuation continuation) {
        super(3, continuation);
        this.this$0 = oplusLockscreenShadeTransitionControllerExImpl;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        OplusLockscreenShadeTransitionControllerExImpl$startCollectNotificationVisibleFlow$1 oplusLockscreenShadeTransitionControllerExImpl$startCollectNotificationVisibleFlow$1 = new OplusLockscreenShadeTransitionControllerExImpl$startCollectNotificationVisibleFlow$1(this.this$0, (Continuation) obj3);
        oplusLockscreenShadeTransitionControllerExImpl$startCollectNotificationVisibleFlow$1.L$0 = (LifecycleOwner) obj;
        return oplusLockscreenShadeTransitionControllerExImpl$startCollectNotificationVisibleFlow$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Lifecycle.State state = Lifecycle.State.CREATED;
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

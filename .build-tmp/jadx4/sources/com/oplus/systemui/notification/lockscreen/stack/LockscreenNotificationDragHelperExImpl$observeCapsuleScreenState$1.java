package com.oplus.systemui.notification.lockscreen.stack;

import com.android.keyguard.OplusLockIconView$;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataScreenMonitor;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class LockscreenNotificationDragHelperExImpl$observeCapsuleScreenState$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ LockscreenNotificationDragHelperExImpl this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.stack.LockscreenNotificationDragHelperExImpl$observeCapsuleScreenState$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements FlowCollector {
        public /* synthetic */ LockscreenNotificationDragHelperExImpl this$0;

        public final Object emit(Object obj, Continuation continuation) {
            LockscreenNotificationDragHelperExImpl lockscreenNotificationDragHelperExImpl = this.this$0;
            if (Intrinsics.areEqual((Boolean) obj, Boxing.boxBoolean(false))) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    OplusLockIconView$.ExternalSyntheticOutline0.m("unIntercept when screen off, isTrackingTouch: ", "OplusLockscreenNotificationDragHelperEx-->", "SystemUi--Notification", lockscreenNotificationDragHelperExImpl.isTrackingTouch);
                }
                if (lockscreenNotificationDragHelperExImpl.isTrackingTouch) {
                    lockscreenNotificationDragHelperExImpl.stopDragging$1();
                } else {
                    lockscreenNotificationDragHelperExImpl.dataChangeInterceptor.unIntercept();
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LockscreenNotificationDragHelperExImpl$observeCapsuleScreenState$1(LockscreenNotificationDragHelperExImpl lockscreenNotificationDragHelperExImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = lockscreenNotificationDragHelperExImpl;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new LockscreenNotificationDragHelperExImpl$observeCapsuleScreenState$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            StateFlow showStateFlow = ((CapsuleDataScreenMonitor) this.this$0.capsuleDataScreenMonitor.get()).getShowStateFlow();
            LockscreenNotificationDragHelperExImpl$observeCapsuleScreenState$1$invokeSuspend$$inlined$map$1 lockscreenNotificationDragHelperExImpl$observeCapsuleScreenState$1$invokeSuspend$$inlined$map$1 = new LockscreenNotificationDragHelperExImpl$observeCapsuleScreenState$1$invokeSuspend$$inlined$map$1();
            lockscreenNotificationDragHelperExImpl$observeCapsuleScreenState$1$invokeSuspend$$inlined$map$1.$this_unsafeTransform$inlined = showStateFlow;
            VarHandle.storeStoreFence();
            Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(lockscreenNotificationDragHelperExImpl$observeCapsuleScreenState$1$invokeSuspend$$inlined$map$1);
            LockscreenNotificationDragHelperExImpl lockscreenNotificationDragHelperExImpl = this.this$0;
            AnonymousClass2 anonymousClass2 = new AnonymousClass2();
            anonymousClass2.this$0 = lockscreenNotificationDragHelperExImpl;
            VarHandle.storeStoreFence();
            this.label = 1;
            if (flowDistinctUntilChanged.collect(anonymousClass2, this) == coroutine_suspended) {
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

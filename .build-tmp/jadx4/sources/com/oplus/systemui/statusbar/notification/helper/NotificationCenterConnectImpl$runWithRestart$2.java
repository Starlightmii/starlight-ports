package com.oplus.systemui.statusbar.notification.helper;

import android.util.Log;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class NotificationCenterConnectImpl$runWithRestart$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function1 $callback;
    final /* synthetic */ Ref.ObjectRef<Function1> $doAction;
    final /* synthetic */ Object $normalValue;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ NotificationCenterConnectImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationCenterConnectImpl$runWithRestart$2(Ref.ObjectRef objectRef, NotificationCenterConnectImpl notificationCenterConnectImpl, Object obj, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$doAction = objectRef;
        this.this$0 = notificationCenterConnectImpl;
        this.$normalValue = obj;
        this.$callback = function1;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new NotificationCenterConnectImpl$runWithRestart$2(this.$doAction, this.this$0, this.$normalValue, this.$callback, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        Ref.ObjectRef<Function1> objectRef = this.$doAction;
        NotificationCenterConnectImpl notificationCenterConnectImpl = this.this$0;
        Object obj2 = this.$normalValue;
        Function1 function1 = this.$callback;
        this.L$0 = objectRef;
        this.L$1 = notificationCenterConnectImpl;
        this.L$2 = obj2;
        this.L$3 = function1;
        this.I$0 = 0;
        this.label = 1;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(this), 1);
        cancellableContinuationImpl.initCancellability();
        NotificationCenterConnectImpl$runWithRestart$2$1$1 notificationCenterConnectImpl$runWithRestart$2$1$1 = new NotificationCenterConnectImpl$runWithRestart$2$1$1();
        notificationCenterConnectImpl$runWithRestart$2$1$1.$consumer = cancellableContinuationImpl;
        notificationCenterConnectImpl$runWithRestart$2$1$1.$callback = function1;
        VarHandle.storeStoreFence();
        objectRef.element = notificationCenterConnectImpl$runWithRestart$2$1$1;
        Boxing.boxBoolean(notificationCenterConnectImpl.waitQueue.add(notificationCenterConnectImpl$runWithRestart$2$1$1));
        if (!NotificationCenterConnectImpl.access$bind(notificationCenterConnectImpl)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationCenterConnect-->runWithRestart: bind fail");
            }
            cancellableContinuationImpl.resumeWith(Result.constructor-impl(obj2));
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(this);
        }
        return result == coroutine_suspended ? coroutine_suspended : result;
    }
}

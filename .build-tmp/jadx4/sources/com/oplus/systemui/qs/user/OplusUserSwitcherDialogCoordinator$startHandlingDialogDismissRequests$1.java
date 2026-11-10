package com.oplus.systemui.qs.user;

import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusUserSwitcherDialogCoordinator$startHandlingDialogDismissRequests$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ OplusUserSwitcherDialogCoordinator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusUserSwitcherDialogCoordinator$startHandlingDialogDismissRequests$1(OplusUserSwitcherDialogCoordinator oplusUserSwitcherDialogCoordinator, Continuation continuation) {
        super(2, continuation);
        this.this$0 = oplusUserSwitcherDialogCoordinator;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new OplusUserSwitcherDialogCoordinator$startHandlingDialogDismissRequests$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow flowFilterNotNull = FlowKt.filterNotNull(((UserSwitcherInteractor) this.this$0.interactor.get()).getDialogDismissRequests());
            OplusUserSwitcherDialogCoordinator oplusUserSwitcherDialogCoordinator = this.this$0;
            OplusUserSwitcherDialogCoordinator$startHandlingDialogShowRequests$1.AnonymousClass1 anonymousClass1 = new OplusUserSwitcherDialogCoordinator$startHandlingDialogShowRequests$1.AnonymousClass1(1);
            anonymousClass1.this$0 = oplusUserSwitcherDialogCoordinator;
            VarHandle.storeStoreFence();
            this.label = 1;
            if (flowFilterNotNull.collect(anonymousClass1, this) == coroutine_suspended) {
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

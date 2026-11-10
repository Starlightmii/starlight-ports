package com.oplus.systemui.qs.user;

import android.app.Dialog;
import android.content.Context;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import com.android.systemui.user.domain.model.ShowDialogRequestModel;
import com.oplus.systemui.qs.helper.UserSwitcherControllerHelper;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusUserSwitcherDialogCoordinator$startHandlingDialogShowRequests$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ OplusUserSwitcherDialogCoordinator this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.user.OplusUserSwitcherDialogCoordinator$startHandlingDialogShowRequests$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusUserSwitcherDialogCoordinator this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        public final Object emit(Object obj, Continuation continuation) {
            switch (this.$r8$classId) {
                case 0:
                    ShowDialogRequestModel.ShowUserSwitcherDialog showUserSwitcherDialog = (ShowDialogRequestModel) obj;
                    OplusUserSwitcherDialogCoordinator oplusUserSwitcherDialogCoordinator = this.this$0;
                    Dialog dialog = oplusUserSwitcherDialogCoordinator.currentDialog;
                    if (dialog != null && dialog.isShowing()) {
                        dialog.cancel();
                    }
                    Dialog dialogExitGuestDialog = null;
                    if (showUserSwitcherDialog instanceof ShowDialogRequestModel.ShowUserSwitcherDialog) {
                        Boxing.boxBoolean(oplusUserSwitcherDialogCoordinator.getDetailInteractor().show(((Boolean) oplusUserSwitcherDialogCoordinator.separateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue() ? null : showUserSwitcherDialog.getView()));
                    } else {
                        if (showUserSwitcherDialog instanceof ShowDialogRequestModel.ShowExitGuestDialog) {
                            ShowDialogRequestModel.ShowExitGuestDialog showExitGuestDialog = (ShowDialogRequestModel.ShowExitGuestDialog) showUserSwitcherDialog;
                            dialogExitGuestDialog = ((UserSwitcherControllerHelper) oplusUserSwitcherDialogCoordinator.userSwitcherControllerHelper.get()).exitGuestDialog((Context) oplusUserSwitcherDialogCoordinator.context.get(), showExitGuestDialog.getGuestUserId(), showExitGuestDialog.getDismissDialog(), showExitGuestDialog.getTargetUserId());
                        }
                        oplusUserSwitcherDialogCoordinator.currentDialog = dialogExitGuestDialog;
                    }
                    ((UserSwitcherInteractor) oplusUserSwitcherDialogCoordinator.interactor.get()).onDialogShown();
                    break;
                default:
                    OplusUserSwitcherDialogCoordinator oplusUserSwitcherDialogCoordinator2 = this.this$0;
                    oplusUserSwitcherDialogCoordinator2.getDetailInteractor().dismiss(true);
                    Dialog dialog2 = oplusUserSwitcherDialogCoordinator2.currentDialog;
                    if (dialog2 != null && dialog2.isShowing()) {
                        dialog2.cancel();
                    }
                    ((UserSwitcherInteractor) oplusUserSwitcherDialogCoordinator2.interactor.get()).onDialogDismissed();
                    break;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusUserSwitcherDialogCoordinator$startHandlingDialogShowRequests$1(OplusUserSwitcherDialogCoordinator oplusUserSwitcherDialogCoordinator, Continuation continuation) {
        super(2, continuation);
        this.this$0 = oplusUserSwitcherDialogCoordinator;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new OplusUserSwitcherDialogCoordinator$startHandlingDialogShowRequests$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow flowFilterNotNull = FlowKt.filterNotNull(((UserSwitcherInteractor) this.this$0.interactor.get()).getDialogShowRequests());
            OplusUserSwitcherDialogCoordinator oplusUserSwitcherDialogCoordinator = this.this$0;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
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

package com.oplus.systemui.viewdialog.ui.binder;

import android.view.ViewGroup;
import com.oplus.systemui.viewdialog.ui.viewmodel.ViewDialogContainerViewModel;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class ViewDialogContainerBinder$collectViewLifecycle$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ ViewGroup $container;
    final /* synthetic */ ViewDialogContainerViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewDialogContainerBinder$collectViewLifecycle$2(ViewGroup viewGroup, ViewDialogContainerViewModel viewDialogContainerViewModel, Continuation continuation) {
        super(2, continuation);
        this.$container = viewGroup;
        this.$viewModel = viewDialogContainerViewModel;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        ViewDialogContainerBinder$collectViewLifecycle$2 viewDialogContainerBinder$collectViewLifecycle$2 = new ViewDialogContainerBinder$collectViewLifecycle$2(this.$container, this.$viewModel, continuation);
        viewDialogContainerBinder$collectViewLifecycle$2.L$0 = obj;
        return viewDialogContainerBinder$collectViewLifecycle$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        ProducerScope producerScope = (ProducerScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ViewDialogContainerBinder.INSTANCE.getClass();
            LogUtil.i$default(LogUtil.TAG_QS, "ViewDialogContainerBinder", "onCreate: ", null, 8);
            ViewGroup viewGroup = this.$container;
            ViewDialogContainerBinder$collectViewLifecycle$2$$ExternalSyntheticLambda0 viewDialogContainerBinder$collectViewLifecycle$2$$ExternalSyntheticLambda0 = new ViewDialogContainerBinder$collectViewLifecycle$2$$ExternalSyntheticLambda0();
            viewDialogContainerBinder$collectViewLifecycle$2$$ExternalSyntheticLambda0.f$0 = viewGroup;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, viewDialogContainerBinder$collectViewLifecycle$2$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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

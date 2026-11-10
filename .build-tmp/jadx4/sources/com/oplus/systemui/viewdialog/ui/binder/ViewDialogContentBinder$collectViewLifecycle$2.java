package com.oplus.systemui.viewdialog.ui.binder;

import android.view.View;
import com.oplus.systemui.viewdialog.ui.viewmodel.ViewDialogContentViewModel;
import com.oplusos.systemui.common.viewdialog.model.ViewDialogAnimationManager;
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
final class ViewDialogContentBinder$collectViewLifecycle$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ ViewDialogAnimationManager $animationManager;
    final /* synthetic */ View $content;
    final /* synthetic */ ViewDialogContentViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewDialogContentBinder$collectViewLifecycle$2(View view, ViewDialogAnimationManager viewDialogAnimationManager, ViewDialogContentViewModel viewDialogContentViewModel, Continuation continuation) {
        super(2, continuation);
        this.$content = view;
        this.$animationManager = viewDialogAnimationManager;
        this.$viewModel = viewDialogContentViewModel;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        ViewDialogContentBinder$collectViewLifecycle$2 viewDialogContentBinder$collectViewLifecycle$2 = new ViewDialogContentBinder$collectViewLifecycle$2(this.$content, this.$animationManager, this.$viewModel, continuation);
        viewDialogContentBinder$collectViewLifecycle$2.L$0 = obj;
        return viewDialogContentBinder$collectViewLifecycle$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final Object invokeSuspend(Object obj) {
        ProducerScope producerScope = (ProducerScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        int i2 = 1;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ViewDialogContentBinder viewDialogContentBinder = ViewDialogContentBinder.INSTANCE;
            View view = this.$content;
            ViewDialogAnimationManager viewDialogAnimationManager = this.$animationManager;
            ViewDialogContentViewModel viewDialogContentViewModel = this.$viewModel;
            viewDialogContentBinder.getClass();
            View originView = viewDialogContentViewModel.getOriginView();
            ViewDialogContentBinder$$ExternalSyntheticLambda0 viewDialogContentBinder$$ExternalSyntheticLambda0 = new ViewDialogContentBinder$$ExternalSyntheticLambda0(0);
            viewDialogContentBinder$$ExternalSyntheticLambda0.f$0 = view;
            VarHandle.storeStoreFence();
            viewDialogAnimationManager.showDetailFromView(originView, viewDialogContentViewModel.getContentViewId(), viewDialogContentBinder$$ExternalSyntheticLambda0, viewDialogContentViewModel.getAnimationManager());
            viewDialogContentViewModel.onCreate();
            ViewDialogContentViewModel viewDialogContentViewModel2 = this.$viewModel;
            ViewDialogContentBinder$$ExternalSyntheticLambda0 viewDialogContentBinder$$ExternalSyntheticLambda1 = new ViewDialogContentBinder$$ExternalSyntheticLambda0(i2);
            viewDialogContentBinder$$ExternalSyntheticLambda1.f$0 = viewDialogContentViewModel2;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, viewDialogContentBinder$$ExternalSyntheticLambda1, this) == coroutine_suspended) {
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

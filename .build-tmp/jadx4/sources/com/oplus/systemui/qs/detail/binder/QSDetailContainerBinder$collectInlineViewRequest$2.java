package com.oplus.systemui.qs.detail.binder;

import android.os.Bundle;
import android.view.View;
import com.oplus.systemui.qs.base.widget.MaxHeightAndWidthLinearLayout;
import com.oplus.systemui.qs.detail.viewmodel.QSDetailContainerViewModel;
import com.oplusos.systemui.common.inline.OplusQsInlineHostView;
import com.oplusos.systemui.common.inline.SystemUIInlineViewHelper;
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
final class QSDetailContainerBinder$collectInlineViewRequest$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ View $container;
    final /* synthetic */ OplusQsInlineHostView $hostView;
    final /* synthetic */ QSDetailContainerViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.detail.binder.QSDetailContainerBinder$collectInlineViewRequest$2$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements OplusQsInlineHostView.OnAnalyzeSysInlineRequestListener {
        public /* synthetic */ View $container;
        public /* synthetic */ QSDetailContainerViewModel $viewModel;

        @Override // com.oplusos.systemui.common.inline.OplusQsInlineHostView.OnAnalyzeSysInlineRequestListener
        public final void onAnalyzeSysInlineRequest(Bundle bundle) {
            this.$viewModel.onAnalyzeSysInlineRequest(bundle);
            if (bundle != null ? bundle.containsKey(SystemUIInlineViewHelper.KEY_TOUCH_ACTION) : false) {
                View view = this.$container;
                MaxHeightAndWidthLinearLayout maxHeightAndWidthLinearLayout = view instanceof MaxHeightAndWidthLinearLayout ? (MaxHeightAndWidthLinearLayout) view : null;
                if (maxHeightAndWidthLinearLayout == null) {
                    return;
                }
                maxHeightAndWidthLinearLayout.dispatchSpotLightBundle(bundle);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSDetailContainerBinder$collectInlineViewRequest$2(OplusQsInlineHostView oplusQsInlineHostView, QSDetailContainerViewModel qSDetailContainerViewModel, View view, Continuation continuation) {
        super(2, continuation);
        this.$hostView = oplusQsInlineHostView;
        this.$viewModel = qSDetailContainerViewModel;
        this.$container = view;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        QSDetailContainerBinder$collectInlineViewRequest$2 qSDetailContainerBinder$collectInlineViewRequest$2 = new QSDetailContainerBinder$collectInlineViewRequest$2(this.$hostView, this.$viewModel, this.$container, continuation);
        qSDetailContainerBinder$collectInlineViewRequest$2.L$0 = obj;
        return qSDetailContainerBinder$collectInlineViewRequest$2;
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
            OplusQsInlineHostView oplusQsInlineHostView = this.$hostView;
            QSDetailContainerViewModel qSDetailContainerViewModel = this.$viewModel;
            View view = this.$container;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.$viewModel = qSDetailContainerViewModel;
            anonymousClass1.$container = view;
            VarHandle.storeStoreFence();
            oplusQsInlineHostView.setSysInlineRequestListener(anonymousClass1);
            OplusQsInlineHostView oplusQsInlineHostView2 = this.$hostView;
            QSDetailContainerBinder$collectLifecycleState$2$$ExternalSyntheticLambda0 qSDetailContainerBinder$collectLifecycleState$2$$ExternalSyntheticLambda0 = new QSDetailContainerBinder$collectLifecycleState$2$$ExternalSyntheticLambda0(1);
            qSDetailContainerBinder$collectLifecycleState$2$$ExternalSyntheticLambda0.f$0 = oplusQsInlineHostView2;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, qSDetailContainerBinder$collectLifecycleState$2$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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

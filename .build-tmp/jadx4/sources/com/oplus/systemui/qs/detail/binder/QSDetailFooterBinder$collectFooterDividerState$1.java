package com.oplus.systemui.qs.detail.binder;

import android.view.View;
import com.oplus.systemui.qs.detail.viewmodel.QSDetailFooterViewModel;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class QSDetailFooterBinder$collectFooterDividerState$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ View $footerDivider;
    final /* synthetic */ QSDetailFooterViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSDetailFooterBinder$collectFooterDividerState$1(View view, QSDetailFooterViewModel qSDetailFooterViewModel, Continuation continuation) {
        super(2, continuation);
        this.$viewModel = qSDetailFooterViewModel;
        this.$footerDivider = view;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new QSDetailFooterBinder$collectFooterDividerState$1(this.$footerDivider, this.$viewModel, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow dividerVisible = this.$viewModel.getDividerVisible();
            View view = this.$footerDivider;
            QSDetailFooterBinder$collectSettingsBtnState$3 qSDetailFooterBinder$collectSettingsBtnState$3 = new QSDetailFooterBinder$collectSettingsBtnState$3(1);
            qSDetailFooterBinder$collectSettingsBtnState$3.$settingsButton = view;
            VarHandle.storeStoreFence();
            this.label = 1;
            if (dividerVisible.collect(qSDetailFooterBinder$collectSettingsBtnState$3, this) == coroutine_suspended) {
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

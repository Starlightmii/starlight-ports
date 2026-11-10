package com.oplus.systemui.statusbar.pipeline.shared.ui.binder;

import com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel.OplusHomeStatusBarViewModel;
import com.oplus.systemui.statusbar.widget.StatClock;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusHomeStatusBarViewBinderImpl$bindClock$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ StatClock $clockView;
    final /* synthetic */ OplusHomeStatusBarViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ OplusHomeStatusBarViewBinderImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusHomeStatusBarViewBinderImpl$bindClock$2(OplusHomeStatusBarViewBinderImpl oplusHomeStatusBarViewBinderImpl, OplusHomeStatusBarViewModel oplusHomeStatusBarViewModel, StatClock statClock, Continuation continuation) {
        super(2, continuation);
        this.$viewModel = oplusHomeStatusBarViewModel;
        this.this$0 = oplusHomeStatusBarViewBinderImpl;
        this.$clockView = statClock;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        OplusHomeStatusBarViewBinderImpl$bindClock$2 oplusHomeStatusBarViewBinderImpl$bindClock$2 = new OplusHomeStatusBarViewBinderImpl$bindClock$2(this.this$0, this.$viewModel, this.$clockView, continuation);
        oplusHomeStatusBarViewBinderImpl$bindClock$2.L$0 = obj;
        return oplusHomeStatusBarViewBinderImpl$bindClock$2;
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
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new 1(this.this$0, this.$viewModel, this.$clockView, (Continuation) null), 3);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new 2(this.$viewModel, this.$clockView, (Continuation) null), 3);
        return BuildersKt.launch$default(coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new 3(this.$viewModel, this.$clockView, (Continuation) null), 2);
    }
}

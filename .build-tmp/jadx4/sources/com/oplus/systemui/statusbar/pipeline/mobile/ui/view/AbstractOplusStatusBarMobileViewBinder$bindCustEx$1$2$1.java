package com.oplus.systemui.statusbar.pipeline.mobile.ui.view;

import android.view.ViewGroup;
import com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.LocationBasedMobileViewModel;
import com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconViewModel;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class AbstractOplusStatusBarMobileViewBinder$bindCustEx$1$2$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ AbstractOplusStatusBarMobileViewBinder$Binding $binding;
    final /* synthetic */ StateFlow $iconTint;
    final /* synthetic */ Ref.BooleanRef $isCollecting;
    final /* synthetic */ OplusMobileIconViewModel $oplusViewModel;
    final /* synthetic */ ViewGroup $view;
    final /* synthetic */ LocationBasedMobileViewModel $viewModel;
    final /* synthetic */ Flow $visibilityState;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractOplusStatusBarMobileViewBinder$bindCustEx$1$2$1(ViewGroup viewGroup, LocationBasedMobileViewModel locationBasedMobileViewModel, AbstractOplusStatusBarMobileViewBinder$Binding abstractOplusStatusBarMobileViewBinder$Binding, OplusMobileIconViewModel oplusMobileIconViewModel, Continuation continuation, Ref.BooleanRef booleanRef, Flow flow, StateFlow stateFlow) {
        super(2, continuation);
        this.$isCollecting = booleanRef;
        this.$binding = abstractOplusStatusBarMobileViewBinder$Binding;
        this.$visibilityState = flow;
        this.$oplusViewModel = oplusMobileIconViewModel;
        this.$viewModel = locationBasedMobileViewModel;
        this.$view = viewGroup;
        this.$iconTint = stateFlow;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        Ref.BooleanRef booleanRef = this.$isCollecting;
        AbstractOplusStatusBarMobileViewBinder$Binding abstractOplusStatusBarMobileViewBinder$Binding = this.$binding;
        Flow flow = this.$visibilityState;
        OplusMobileIconViewModel oplusMobileIconViewModel = this.$oplusViewModel;
        AbstractOplusStatusBarMobileViewBinder$bindCustEx$1$2$1 abstractOplusStatusBarMobileViewBinder$bindCustEx$1$2$1 = new AbstractOplusStatusBarMobileViewBinder$bindCustEx$1$2$1(this.$view, this.$viewModel, abstractOplusStatusBarMobileViewBinder$Binding, oplusMobileIconViewModel, continuation, booleanRef, flow, this.$iconTint);
        abstractOplusStatusBarMobileViewBinder$bindCustEx$1$2$1.L$0 = obj;
        return abstractOplusStatusBarMobileViewBinder$bindCustEx$1$2$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.$isCollecting.element = true;
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new 1(this.$visibilityState, this.$binding, (Continuation) null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new 2(this.$oplusViewModel, this.$binding, (Continuation) null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new 3(this.$viewModel, this.$view, (Continuation) null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new 4(this.$oplusViewModel, this.$binding, (Continuation) null), 3);
                BuildersKt.launch$default(coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new 5(this.$iconTint, this.$binding, (Continuation) null), 2);
                this.$binding.bindSubclassFlows(coroutineScope);
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.label = 1;
                if (DelayKt.awaitCancellation(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        } catch (Throwable th) {
            this.$isCollecting.element = false;
            throw th;
        }
    }
}

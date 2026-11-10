package com.oplusos.systemui.common.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class BaseViewModel extends ViewModel {
    private final MutableSharedFlow _intentFlow;
    private final MutableSharedFlow _uiEffectFlow;
    private final MutableStateFlow innerUiStateFlow;
    private final SharedFlow intentFlow;
    private final SharedFlow uiEffectFlow;
    private final StateFlow uiStateFlow;

    /* JADX INFO: renamed from: com.oplusos.systemui.common.viewmodel.BaseViewModel$sendIntent$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ IUiIntent $uiIntent;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(IUiIntent iUiIntent, Continuation continuation) {
            super(2, continuation);
            this.$uiIntent = iUiIntent;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return BaseViewModel.this.new AnonymousClass1(this.$uiIntent, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                MutableSharedFlow mutableSharedFlow = BaseViewModel.this._intentFlow;
                IUiIntent iUiIntent = this.$uiIntent;
                this.label = 1;
                if (mutableSharedFlow.emit(iUiIntent, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplusos.systemui.common.viewmodel.BaseViewModel$sendUiEffect$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass2(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BaseViewModel.this.sendUiEffect((Function1) null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.oplusos.systemui.common.viewmodel.BaseViewModel$sendUiState$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C03242 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C03242(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BaseViewModel.this.sendUiState((Function1) null, (Continuation) this);
        }
    }

    public BaseViewModel() {
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(initUIState());
        this.innerUiStateFlow = MutableStateFlow;
        this.uiStateFlow = FlowKt.asStateFlow(MutableStateFlow);
        MutableSharedFlow mutableSharedFlowMutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7);
        this._uiEffectFlow = mutableSharedFlowMutableSharedFlow$default;
        this.uiEffectFlow = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default);
        MutableSharedFlow mutableSharedFlowMutableSharedFlow$default2 = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7);
        this._intentFlow = mutableSharedFlowMutableSharedFlow$default2;
        this.intentFlow = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default2);
    }

    public final SharedFlow getIntentFlow() {
        return this.intentFlow;
    }

    public final SharedFlow getUiEffectFlow() {
        return this.uiEffectFlow;
    }

    public final StateFlow getUiStateFlow() {
        return this.uiStateFlow;
    }

    public abstract IUiState initUIState();

    public final Job sendIntent(IUiIntent iUiIntent) {
        return BuildersKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(iUiIntent, null), 3);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0070, code lost:
    
        if (r5.emit(r7, r0) == r1) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object sendUiEffect(kotlin.jvm.functions.Function1 r6, kotlin.coroutines.Continuation r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.oplusos.systemui.common.viewmodel.BaseViewModel.AnonymousClass2
            if (r0 == 0) goto L13
            r0 = r7
            com.oplusos.systemui.common.viewmodel.BaseViewModel$sendUiEffect$2 r0 = (com.oplusos.systemui.common.viewmodel.BaseViewModel.AnonymousClass2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.oplusos.systemui.common.viewmodel.BaseViewModel$sendUiEffect$2 r0 = new com.oplusos.systemui.common.viewmodel.BaseViewModel$sendUiEffect$2
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L43
            if (r2 == r4) goto L3b
            if (r2 != r3) goto L34
            java.lang.Object r5 = r0.L$1
            com.oplusos.systemui.common.viewmodel.IUiEffect r5 = (com.oplusos.systemui.common.viewmodel.IUiEffect) r5
            java.lang.Object r5 = r0.L$0
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L73
        L34:
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r5)
            r5 = 0
            return r5
        L3b:
            java.lang.Object r6 = r0.L$0
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L55
        L43:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r0.L$0 = r7
            r0.label = r4
            java.lang.Object r7 = r6.invoke(r0)
            if (r7 != r1) goto L55
            goto L72
        L55:
            com.oplusos.systemui.common.viewmodel.IUiEffect r7 = (com.oplusos.systemui.common.viewmodel.IUiEffect) r7
            if (r7 == 0) goto L73
            kotlinx.coroutines.flow.MutableSharedFlow r5 = r5._uiEffectFlow
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r0.L$0 = r6
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r0.L$1 = r6
            r6 = 0
            r0.I$0 = r6
            r0.label = r3
            java.lang.Object r5 = r5.emit(r7, r0)
            if (r5 != r1) goto L73
        L72:
            return r1
        L73:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplusos.systemui.common.viewmodel.BaseViewModel.sendUiEffect(kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0070, code lost:
    
        if (r5.emit(r7, r0) == r1) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object sendUiState(kotlin.jvm.functions.Function1 r6, kotlin.coroutines.Continuation r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.oplusos.systemui.common.viewmodel.BaseViewModel.C03242
            if (r0 == 0) goto L13
            r0 = r7
            com.oplusos.systemui.common.viewmodel.BaseViewModel$sendUiState$2 r0 = (com.oplusos.systemui.common.viewmodel.BaseViewModel.C03242) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.oplusos.systemui.common.viewmodel.BaseViewModel$sendUiState$2 r0 = new com.oplusos.systemui.common.viewmodel.BaseViewModel$sendUiState$2
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L43
            if (r2 == r4) goto L3b
            if (r2 != r3) goto L34
            java.lang.Object r5 = r0.L$1
            com.oplusos.systemui.common.viewmodel.IUiState r5 = (com.oplusos.systemui.common.viewmodel.IUiState) r5
            java.lang.Object r5 = r0.L$0
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L73
        L34:
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r5)
            r5 = 0
            return r5
        L3b:
            java.lang.Object r6 = r0.L$0
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L55
        L43:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r0.L$0 = r7
            r0.label = r4
            java.lang.Object r7 = r6.invoke(r0)
            if (r7 != r1) goto L55
            goto L72
        L55:
            com.oplusos.systemui.common.viewmodel.IUiState r7 = (com.oplusos.systemui.common.viewmodel.IUiState) r7
            if (r7 == 0) goto L73
            kotlinx.coroutines.flow.MutableStateFlow r5 = r5.innerUiStateFlow
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r0.L$0 = r6
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r0.L$1 = r6
            r6 = 0
            r0.I$0 = r6
            r0.label = r3
            java.lang.Object r5 = r5.emit(r7, r0)
            if (r5 != r1) goto L73
        L72:
            return r1
        L73:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplusos.systemui.common.viewmodel.BaseViewModel.sendUiState(kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object sendUiEffect(IUiEffect iUiEffect, Continuation continuation) {
        Object objEmit = this._uiEffectFlow.emit(iUiEffect, continuation);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    public final Object sendUiState(IUiState iUiState, Continuation continuation) {
        Object objEmit = this.innerUiStateFlow.emit(iUiState, continuation);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }
}

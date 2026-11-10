package com.oplus.systemui.qs.res.domain.interactor;

import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.util.QSConstant;
import com.oplusos.systemui.common.outline.RoundRectOutlineProvider;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class BaseQSResInteractor$startDetailPanelOutlineCollection$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ BaseQSResInteractor this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.BaseQSResInteractor$startDetailPanelOutlineCollection$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        /* synthetic */ int I$0;
        int label;
        final /* synthetic */ BaseQSResInteractor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(BaseQSResInteractor baseQSResInteractor, Continuation continuation) {
            super(3, continuation);
            this.this$0 = baseQSResInteractor;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            int iIntValue = ((Number) obj2).intValue();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, (Continuation) obj3);
            anonymousClass1.I$0 = iIntValue;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            int i = this.I$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            return QSConstant.getSmoothRoundRectOutlineProvider(this.this$0.context, this.this$0.context.getResources().getDimension(i));
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.BaseQSResInteractor$startDetailPanelOutlineCollection$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        /* synthetic */ Object L$0;
        int label;

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(2, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((RoundRectOutlineProvider) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            RoundRectOutlineProvider roundRectOutlineProvider = (RoundRectOutlineProvider) this.L$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            BaseQSResPool.updateDetailPanelOutline(roundRectOutlineProvider);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseQSResInteractor$startDetailPanelOutlineCollection$1(BaseQSResInteractor baseQSResInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = baseQSResInteractor;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new BaseQSResInteractor$startDetailPanelOutlineCollection$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.combine(this.this$0.configuration, this.this$0.personalityRepository.getDetailPanelRadius(), new AnonymousClass1(this.this$0, null)));
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(2, null);
            this.label = 1;
            if (FlowKt.collectLatest(flowDistinctUntilChanged, anonymousClass2, this) == coroutine_suspended) {
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

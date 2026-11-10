package com.oplus.systemui.qs.res.domain.interactor;

import com.oplus.systemui.qs.base.res.StdQSResPool;
import com.oplus.systemui.qs.base.res.model.QSDrawableConfig;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class StdQSTileResInteractor$startTileBackgroundCollection$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ StdQSTileResInteractor this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.StdQSTileResInteractor$startTileBackgroundCollection$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function4 {
        /* synthetic */ boolean Z$0;
        /* synthetic */ boolean Z$1;
        /* synthetic */ boolean Z$2;
        int label;
        final /* synthetic */ StdQSTileResInteractor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(StdQSTileResInteractor stdQSTileResInteractor, Continuation continuation) {
            super(4, continuation);
            this.this$0 = stdQSTileResInteractor;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
            boolean zBooleanValue3 = ((Boolean) obj3).booleanValue();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, (Continuation) obj4);
            anonymousClass1.Z$0 = zBooleanValue;
            anonymousClass1.Z$1 = zBooleanValue2;
            anonymousClass1.Z$2 = zBooleanValue3;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            boolean z = this.Z$0;
            boolean z2 = this.Z$1;
            boolean z3 = this.Z$2;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            if (z3) {
                StdQSTileResInteractor stdQSTileResInteractor = this.this$0;
                return FlowKt.mapLatest(stdQSTileResInteractor.themeChangeFlow, new StdQSTileResInteractor.startTileBackgroundCollection.getGlobalThemeBackground.1(stdQSTileResInteractor, (Continuation) null));
            }
            StdQSTileResInteractor stdQSTileResInteractor2 = this.this$0;
            if (z2) {
                return FlowKt.combine(stdQSTileResInteractor2.activeColorFlow, stdQSTileResInteractor2.isLowGaussianLevel, stdQSTileResInteractor2.isNightMode, new StdQSTileResInteractor.startTileBackgroundCollection.getSuperPowerBackground.1(stdQSTileResInteractor2, (Continuation) null));
            }
            return z ? FlowKt.combine(stdQSTileResInteractor2.isNightMode, stdQSTileResInteractor2.activeColorFlow, new StdQSTileResInteractor$startTileBackgroundCollection$getLightOSBackground$1(3, null)) : FlowKt.combine(stdQSTileResInteractor2.isSupportStrokeCorner, stdQSTileResInteractor2.activeColorFlow, stdQSTileResInteractor2.isNightMode, stdQSTileResInteractor2.mirrorScaleValue, new StdQSTileResInteractor$startTileBackgroundCollection$getBlurBackground$1(5, null));
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.StdQSTileResInteractor$startTileBackgroundCollection$1$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        /* synthetic */ Object L$0;
        int label;

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(2, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((QSDrawableConfig) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            QSDrawableConfig qSDrawableConfig = (QSDrawableConfig) this.L$0;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            StdQSResPool.updateTileViewDrawable(qSDrawableConfig.getStdTileTypeBuilder());
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StdQSTileResInteractor$startTileBackgroundCollection$1(StdQSTileResInteractor stdQSTileResInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = stdQSTileResInteractor;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new StdQSTileResInteractor$startTileBackgroundCollection$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.transformLatest(FlowKt.combine(this.this$0.isLowGaussianLevel, this.this$0.superPowerSaveMode, this.this$0.isGlobalThemeApplied, new AnonymousClass1(this.this$0, null)), new StdQSTileResInteractor$startTileBackgroundCollection$1$invokeSuspend$$inlined$flatMapLatest$1(3, null)));
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(2, null);
            this.label = 1;
            if (FlowKt.collectLatest(flowDistinctUntilChanged, anonymousClass3, this) == coroutine_suspended) {
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

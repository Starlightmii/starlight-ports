package com.oplus.systemui.plugins.qs;

import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.SepQSResPool;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepQSTileViewBinder {
    public static final SepQSTileViewBinder INSTANCE = new SepQSTileViewBinder();

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.SepQSTileViewBinder$collectTileViewRes$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ List<IQsComponent> $componentNodes;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(List list, Continuation continuation) {
            super(2, continuation);
            this.$componentNodes = list;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$componentNodes, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSTileViewBinder sepQSTileViewBinder = SepQSTileViewBinder.INSTANCE;
                List<IQsComponent> list = this.$componentNodes;
                this.label = 1;
                sepQSTileViewBinder.getClass();
                Object objCollectLatest = FlowKt.collectLatest(BaseQSResPool.INSTANCE.isGlobalThemeApplied(), new SepQSTileViewBinder$collectThemeApplied$2(list, null), this);
                if (objCollectLatest != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    objCollectLatest = Unit.INSTANCE;
                }
                if (objCollectLatest == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.SepQSTileViewBinder$collectTileViewRes$10, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass10 extends SuspendLambda implements Function2 {
        final /* synthetic */ List<IQsComponent> $componentNodes;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass10(List list, Continuation continuation) {
            super(2, continuation);
            this.$componentNodes = list;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass10(this.$componentNodes, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSTileViewBinder sepQSTileViewBinder = SepQSTileViewBinder.INSTANCE;
                List<IQsComponent> list = this.$componentNodes;
                this.label = 1;
                sepQSTileViewBinder.getClass();
                SepQSResPool sepQSResPool = SepQSResPool.INSTANCE;
                Object objCollect = FlowKt.collect(FlowKt.combine(sepQSResPool.m246getTileIconColorState(), sepQSResPool.m247getTileIconThemeColorState(), new SepQSTileViewBinder$collectIconTint$2(list, null)), this);
                if (objCollect != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    objCollect = Unit.INSTANCE;
                }
                if (objCollect == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.SepQSTileViewBinder$collectTileViewRes$11, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass11 extends SuspendLambda implements Function2 {
        final /* synthetic */ List<IQsComponent> $componentNodes;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass11(List list, Continuation continuation) {
            super(2, continuation);
            this.$componentNodes = list;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass11(this.$componentNodes, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSTileViewBinder sepQSTileViewBinder = SepQSTileViewBinder.INSTANCE;
                List<IQsComponent> list = this.$componentNodes;
                this.label = 1;
                sepQSTileViewBinder.getClass();
                Object objCollectLatest = FlowKt.collectLatest(SepQSResPool.INSTANCE.getMediaPanelOutline(), new SepQSTileViewBinder$collectMediaPanelOutline$2(list, null), this);
                if (objCollectLatest != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    objCollectLatest = Unit.INSTANCE;
                }
                if (objCollectLatest == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.SepQSTileViewBinder$collectTileViewRes$12, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass12 extends SuspendLambda implements Function2 {
        final /* synthetic */ List<IQsComponent> $componentNodes;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass12(List list, Continuation continuation) {
            super(2, continuation);
            this.$componentNodes = list;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass12(this.$componentNodes, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSTileViewBinder sepQSTileViewBinder = SepQSTileViewBinder.INSTANCE;
                List<IQsComponent> list = this.$componentNodes;
                this.label = 1;
                sepQSTileViewBinder.getClass();
                Object objCollectLatest = FlowKt.collectLatest(SepQSResPool.INSTANCE.getMediaPanelDrawable(), new SepQSTileViewBinder$collectMediaPanelDrawable$2(list, null), this);
                if (objCollectLatest != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    objCollectLatest = Unit.INSTANCE;
                }
                if (objCollectLatest == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.SepQSTileViewBinder$collectTileViewRes$13, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass13 extends SuspendLambda implements Function2 {
        final /* synthetic */ List<IQsComponent> $componentNodes;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass13(List list, Continuation continuation) {
            super(2, continuation);
            this.$componentNodes = list;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass13(this.$componentNodes, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSTileViewBinder sepQSTileViewBinder = SepQSTileViewBinder.INSTANCE;
                List<IQsComponent> list = this.$componentNodes;
                this.label = 1;
                sepQSTileViewBinder.getClass();
                Object objCollectLatest = FlowKt.collectLatest(SepQSResPool.INSTANCE.getMediaPanelThemeDrawable(), new SepQSTileViewBinder$collectMediaPanelThemeDrawable$2(list, null), this);
                if (objCollectLatest != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    objCollectLatest = Unit.INSTANCE;
                }
                if (objCollectLatest == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.SepQSTileViewBinder$collectTileViewRes$14, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass14 extends SuspendLambda implements Function2 {
        final /* synthetic */ List<IQsComponent> $componentNodes;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass14(List list, Continuation continuation) {
            super(2, continuation);
            this.$componentNodes = list;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass14(this.$componentNodes, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSTileViewBinder sepQSTileViewBinder = SepQSTileViewBinder.INSTANCE;
                List<IQsComponent> list = this.$componentNodes;
                this.label = 1;
                if (SepQSTileViewBinder.access$collectQuickEntranceDrawable(sepQSTileViewBinder, list, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.SepQSTileViewBinder$collectTileViewRes$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ List<IQsComponent> $componentNodes;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(List list, Continuation continuation) {
            super(2, continuation);
            this.$componentNodes = list;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass2(this.$componentNodes, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSTileViewBinder sepQSTileViewBinder = SepQSTileViewBinder.INSTANCE;
                List<IQsComponent> list = this.$componentNodes;
                this.label = 1;
                sepQSTileViewBinder.getClass();
                Object objCollectLatest = FlowKt.collectLatest(SepQSResPool.INSTANCE.getTileOutline(), new SepQSTileViewBinder$collectTileOutline$2(list, null), this);
                if (objCollectLatest != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    objCollectLatest = Unit.INSTANCE;
                }
                if (objCollectLatest == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.SepQSTileViewBinder$collectTileViewRes$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        final /* synthetic */ List<IQsComponent> $componentNodes;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(List list, Continuation continuation) {
            super(2, continuation);
            this.$componentNodes = list;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass3(this.$componentNodes, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSTileViewBinder sepQSTileViewBinder = SepQSTileViewBinder.INSTANCE;
                List<IQsComponent> list = this.$componentNodes;
                this.label = 1;
                sepQSTileViewBinder.getClass();
                Object objCollectLatest = FlowKt.collectLatest(SepQSResPool.INSTANCE.getTileViewDrawable(), new SepQSTileViewBinder$collectTileDrawable$2(list, null), this);
                if (objCollectLatest != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    objCollectLatest = Unit.INSTANCE;
                }
                if (objCollectLatest == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.SepQSTileViewBinder$collectTileViewRes$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass4 extends SuspendLambda implements Function2 {
        final /* synthetic */ List<IQsComponent> $componentNodes;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(List list, Continuation continuation) {
            super(2, continuation);
            this.$componentNodes = list;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass4(this.$componentNodes, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSTileViewBinder sepQSTileViewBinder = SepQSTileViewBinder.INSTANCE;
                List<IQsComponent> list = this.$componentNodes;
                this.label = 1;
                sepQSTileViewBinder.getClass();
                Object objCollectLatest = FlowKt.collectLatest(SepQSResPool.INSTANCE.getTileThemeDrawable(), new SepQSTileViewBinder$collectTileThemeDrawable$2(list, null), this);
                if (objCollectLatest != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    objCollectLatest = Unit.INSTANCE;
                }
                if (objCollectLatest == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.SepQSTileViewBinder$collectTileViewRes$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass5 extends SuspendLambda implements Function2 {
        final /* synthetic */ List<IQsComponent> $componentNodes;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(List list, Continuation continuation) {
            super(2, continuation);
            this.$componentNodes = list;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass5(this.$componentNodes, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSTileViewBinder sepQSTileViewBinder = SepQSTileViewBinder.INSTANCE;
                List<IQsComponent> list = this.$componentNodes;
                this.label = 1;
                sepQSTileViewBinder.getClass();
                Object objCollectLatest = FlowKt.collectLatest(SepQSResPool.INSTANCE.getHighLightTileOutline(), new SepQSTileViewBinder$collectHighlightTileOutline$2(list, null), this);
                if (objCollectLatest != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    objCollectLatest = Unit.INSTANCE;
                }
                if (objCollectLatest == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.SepQSTileViewBinder$collectTileViewRes$6, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass6 extends SuspendLambda implements Function2 {
        final /* synthetic */ List<IQsComponent> $componentNodes;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass6(List list, Continuation continuation) {
            super(2, continuation);
            this.$componentNodes = list;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass6(this.$componentNodes, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSTileViewBinder sepQSTileViewBinder = SepQSTileViewBinder.INSTANCE;
                List<IQsComponent> list = this.$componentNodes;
                this.label = 1;
                sepQSTileViewBinder.getClass();
                Object objCollectLatest = FlowKt.collectLatest(SepQSResPool.INSTANCE.getHighlightTileViewDrawable(), new SepQSTileViewBinder$collectHighlightTileDrawable$2(list, null), this);
                if (objCollectLatest != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    objCollectLatest = Unit.INSTANCE;
                }
                if (objCollectLatest == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.SepQSTileViewBinder$collectTileViewRes$7, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass7 extends SuspendLambda implements Function2 {
        final /* synthetic */ List<IQsComponent> $componentNodes;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass7(List list, Continuation continuation) {
            super(2, continuation);
            this.$componentNodes = list;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass7(this.$componentNodes, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSTileViewBinder sepQSTileViewBinder = SepQSTileViewBinder.INSTANCE;
                List<IQsComponent> list = this.$componentNodes;
                this.label = 1;
                sepQSTileViewBinder.getClass();
                Object objCollectLatest = FlowKt.collectLatest(SepQSResPool.INSTANCE.getHighLightTileThemeDrawable(), new SepQSTileViewBinder$collectHighlightTileThemeDrawable$2(list, null), this);
                if (objCollectLatest != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    objCollectLatest = Unit.INSTANCE;
                }
                if (objCollectLatest == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.SepQSTileViewBinder$collectTileViewRes$8, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass8 extends SuspendLambda implements Function2 {
        final /* synthetic */ List<IQsComponent> $componentNodes;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass8(List list, Continuation continuation) {
            super(2, continuation);
            this.$componentNodes = list;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass8(this.$componentNodes, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSTileViewBinder sepQSTileViewBinder = SepQSTileViewBinder.INSTANCE;
                List<IQsComponent> list = this.$componentNodes;
                this.label = 1;
                sepQSTileViewBinder.getClass();
                Object objCollectLatest = FlowKt.collectLatest(SepQSResPool.INSTANCE.getHighLightIconBg(), new SepQSTileViewBinder$collectIconDrawable$2(list, null), this);
                if (objCollectLatest != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    objCollectLatest = Unit.INSTANCE;
                }
                if (objCollectLatest == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.SepQSTileViewBinder$collectTileViewRes$9, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass9 extends SuspendLambda implements Function2 {
        final /* synthetic */ List<IQsComponent> $componentNodes;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass9(List list, Continuation continuation) {
            super(2, continuation);
            this.$componentNodes = list;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass9(this.$componentNodes, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SepQSTileViewBinder sepQSTileViewBinder = SepQSTileViewBinder.INSTANCE;
                List<IQsComponent> list = this.$componentNodes;
                this.label = 1;
                sepQSTileViewBinder.getClass();
                Object objCollectLatest = FlowKt.collectLatest(SepQSResPool.INSTANCE.getHighLightIconThemeBg(), new SepQSTileViewBinder$collectIconThemeDrawable$2(list, null), this);
                if (objCollectLatest != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    objCollectLatest = Unit.INSTANCE;
                }
                if (objCollectLatest == coroutine_suspended) {
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

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object access$collectQuickEntranceDrawable(SepQSTileViewBinder sepQSTileViewBinder, List list, ContinuationImpl continuationImpl) {
        SepQSTileViewBinder$collectQuickEntranceDrawable$1 sepQSTileViewBinder$collectQuickEntranceDrawable$1;
        sepQSTileViewBinder.getClass();
        if (continuationImpl instanceof SepQSTileViewBinder$collectQuickEntranceDrawable$1) {
            sepQSTileViewBinder$collectQuickEntranceDrawable$1 = (SepQSTileViewBinder$collectQuickEntranceDrawable$1) continuationImpl;
            int i = sepQSTileViewBinder$collectQuickEntranceDrawable$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                sepQSTileViewBinder$collectQuickEntranceDrawable$1.label = i - Integer.MIN_VALUE;
            } else {
                sepQSTileViewBinder$collectQuickEntranceDrawable$1 = new SepQSTileViewBinder$collectQuickEntranceDrawable$1(sepQSTileViewBinder, continuationImpl);
            }
        } else {
            sepQSTileViewBinder$collectQuickEntranceDrawable$1 = new SepQSTileViewBinder$collectQuickEntranceDrawable$1(sepQSTileViewBinder, continuationImpl);
        }
        Object obj = sepQSTileViewBinder$collectQuickEntranceDrawable$1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = sepQSTileViewBinder$collectQuickEntranceDrawable$1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            StateFlow quickEntranceDrawable = SepQSResPool.INSTANCE.getQuickEntranceDrawable();
            SepQSTileViewBinder$collectQuickEntranceDrawable$2 sepQSTileViewBinder$collectQuickEntranceDrawable$2 = new SepQSTileViewBinder$collectQuickEntranceDrawable$2();
            sepQSTileViewBinder$collectQuickEntranceDrawable$2.$nodes = list;
            VarHandle.storeStoreFence();
            sepQSTileViewBinder$collectQuickEntranceDrawable$1.L$0 = SpillingKt.nullOutSpilledVariable(list);
            sepQSTileViewBinder$collectQuickEntranceDrawable$1.label = 1;
            if (quickEntranceDrawable.collect(sepQSTileViewBinder$collectQuickEntranceDrawable$2, sepQSTileViewBinder$collectQuickEntranceDrawable$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        Regex$.ExternalSyntheticBUOutline0.m$1();
        return null;
    }

    public static final void collectTileViewRes(CoroutineScope coroutineScope, List<? extends IQsComponent> list) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(list, null), 3);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(list, null), 3);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(list, null), 3);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass4(list, null), 3);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass5(list, null), 3);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass6(list, null), 3);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass7(list, null), 3);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass8(list, null), 3);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass9(list, null), 3);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass10(list, null), 3);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass11(list, null), 3);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass12(list, null), 3);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass13(list, null), 3);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass14(list, null), 3);
    }
}

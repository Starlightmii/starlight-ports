package com.oplus.systemui.qs.pipeline.domain.interactor;

import androidx.appcompat.widget.ListPopupWindow$;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.qs.QSEditEvent;
import com.android.systemui.qs.pipeline.data.repository.IUnionTileSpecRepository;
import com.android.systemui.qs.pipeline.data.repository.TileSpecRepository;
import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractor;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.oplus.systemui.qs.pipeline.data.model.TileModifyEvent;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusTileModifyInteractor {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusTileModifyInteractor";
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    private final CoroutineScope scope;
    private final Set<TileModifyEvent> tileModifyEvent;
    private final TileSpecRepository tileSpecRepository;
    private final UiEventLogger uiEventLogger;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.interactor.OplusTileModifyInteractor$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ CurrentTilesInteractor $currentTilesInteractor;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ OplusTileModifyInteractor this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.interactor.OplusTileModifyInteractor$init$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00851 extends SuspendLambda implements Function2 {
            final /* synthetic */ CoroutineScope $$this$launch;
            /* synthetic */ int I$0;
            int label;
            final /* synthetic */ OplusTileModifyInteractor this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.domain.interactor.OplusTileModifyInteractor$init$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00861 extends SuspendLambda implements Function2 {
                final /* synthetic */ int $userId;
                int label;
                final /* synthetic */ OplusTileModifyInteractor this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00861(OplusTileModifyInteractor oplusTileModifyInteractor, int i, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusTileModifyInteractor;
                    this.$userId = i;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00861(this.this$0, this.$userId, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        OplusTileModifyInteractor oplusTileModifyInteractor = this.this$0;
                        int i2 = this.$userId;
                        this.label = 1;
                        if (OplusTileModifyInteractor.access$collectTilesModifyEvent(oplusTileModifyInteractor, i2, this) == coroutine_suspended) {
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

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00851(CoroutineScope coroutineScope, OplusTileModifyInteractor oplusTileModifyInteractor, Continuation continuation) {
                super(2, continuation);
                this.$$this$launch = coroutineScope;
                this.this$0 = oplusTileModifyInteractor;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                C00851 c00851 = new C00851(this.$$this$launch, this.this$0, continuation);
                c00851.I$0 = ((Number) obj).intValue();
                return c00851;
            }

            public final Object invoke(Object obj, Object obj2) {
                return create(Integer.valueOf(((Number) obj).intValue()), (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                int i = this.I$0;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                CoroutineTracingKt.launchTraced$default(this.$$this$launch, (String) null, (CoroutineContext) null, (CoroutineStart) null, new C00861(this.this$0, i, null), 7);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(CurrentTilesInteractor currentTilesInteractor, OplusTileModifyInteractor oplusTileModifyInteractor, Continuation continuation) {
            super(2, continuation);
            this.$currentTilesInteractor = currentTilesInteractor;
            this.this$0 = oplusTileModifyInteractor;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$currentTilesInteractor, this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow userId = this.$currentTilesInteractor.getUserId();
                C00851 c00851 = new C00851(coroutineScope, this.this$0, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.label = 1;
                if (FlowKt.collectLatest(userId, c00851, this) == coroutine_suspended) {
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

    public OplusTileModifyInteractor(Set<TileModifyEvent> set, CoroutineScope coroutineScope, UiEventLogger uiEventLogger, TileSpecRepository tileSpecRepository) {
        this.tileModifyEvent = set;
        this.scope = coroutineScope;
        this.uiEventLogger = uiEventLogger;
        this.tileSpecRepository = tileSpecRepository;
    }

    public static final Object access$collectTilesModifyEvent(OplusTileModifyInteractor oplusTileModifyInteractor, int i, Continuation continuation) {
        Set<TileModifyEvent> set = oplusTileModifyInteractor.tileModifyEvent;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(set, 10));
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            arrayList.add(((TileModifyEvent) it.next()).modifyTilesSignal(i));
        }
        Flow flowMerge = FlowKt.merge(arrayList);
        OplusTileModifyInteractor$collectTilesModifyEvent$3 oplusTileModifyInteractor$collectTilesModifyEvent$3 = new OplusTileModifyInteractor$collectTilesModifyEvent$3();
        oplusTileModifyInteractor$collectTilesModifyEvent$3.this$0 = oplusTileModifyInteractor;
        oplusTileModifyInteractor$collectTilesModifyEvent$3.$userId = i;
        VarHandle.storeStoreFence();
        Object objCollect = flowMerge.collect(oplusTileModifyInteractor$collectTilesModifyEvent$3, continuation);
        return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    public final Object addTile(int i, TileSpec tileSpec, int i2, Continuation continuation) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "addTile: userId=" + i + " ,spec=" + tileSpec + ", position=" + i2, null, 8);
        IUnionTileSpecRepository iUnionTileSpecRepository = this.tileSpecRepository;
        if (iUnionTileSpecRepository instanceof IUnionTileSpecRepository) {
            Object objAddTileSpec = iUnionTileSpecRepository.addTileSpec(i, tileSpec, i2, continuation);
            return objAddTileSpec == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAddTileSpec : Unit.INSTANCE;
        }
        Object objAddTile = iUnionTileSpecRepository.addTile(i, tileSpec, i2, continuation);
        return objAddTile == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAddTile : Unit.INSTANCE;
    }

    public final void init(CurrentTilesInteractor currentTilesInteractor) {
        if (this.initialized.compareAndSet(false, true)) {
            CoroutineTracingKt.launchTraced$default(this.scope, (String) null, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(currentTilesInteractor, this, null), 7);
        }
    }

    public final Object removeTiles(int i, Collection<? extends TileSpec> collection, Continuation continuation) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "removeTiles: userId=" + i + " ,specs=" + collection, null, 8);
        IUnionTileSpecRepository iUnionTileSpecRepository = this.tileSpecRepository;
        if (iUnionTileSpecRepository instanceof IUnionTileSpecRepository) {
            Object objRemoveTileSpec = iUnionTileSpecRepository.removeTileSpec(i, collection, continuation);
            return objRemoveTileSpec == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRemoveTileSpec : Unit.INSTANCE;
        }
        Object objRemoveTiles = iUnionTileSpecRepository.removeTiles(i, collection, continuation);
        return objRemoveTiles == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRemoveTiles : Unit.INSTANCE;
    }

    public final Object resetTiles(int i, Continuation continuation) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "resetTiles: userId="), null, 8);
        this.uiEventLogger.log(QSEditEvent.QS_EDIT_RESET);
        Object objResetToDefault = this.tileSpecRepository.resetToDefault(i, continuation);
        return objResetToDefault == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objResetToDefault : Unit.INSTANCE;
    }
}

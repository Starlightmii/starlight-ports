package com.oplus.systemui.qs.pipeline.data.adapter;

import com.android.systemui.plugins.qs.TileSpecOperate;
import com.android.systemui.qs.pipeline.data.repository.IUnionTileSpecRepository;
import com.android.systemui.qs.pipeline.data.repository.TileSpecRepository;
import com.android.systemui.qs.pipeline.shared.QSPipelineLoggerEx;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.oplus.systemui.qs.pipeline.data.repository.TileSpecSeparateQSRepository;
import com.oplus.systemui.qs.pipeline.data.repository.TileSpecStandardQSRepository;
import java.util.Collection;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileSpecRepositoryAdapter implements TileSpecRepository, IUnionTileSpecRepository {
    public static final /* synthetic */ int $r8$clinit = 0;
    private final StateFlow isAvailable;
    private final QSPipelineLoggerEx loggerEx;
    private final TileSpecSeparateQSRepository separateQSRepository;
    private final TileSpecStandardQSRepository standardQSRepository;
    private final StateFlow tileSpecRepository;
    private final ReceiveChannel tilesUpgradePath;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter$addTileSpec$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TileSpecRepositoryAdapter.this.addTileSpec(0, null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter$reconcileRestore$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02601 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C02601(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TileSpecRepositoryAdapter.this.reconcileRestore(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter$removeTileSpec$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02611 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C02611(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TileSpecRepositoryAdapter.this.removeTileSpec(0, null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter$tilesSpecs$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02621 extends ContinuationImpl {
        int I$0;
        int label;
        /* synthetic */ Object result;

        public C02621(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TileSpecRepositoryAdapter.this.tilesSpecs(0, this);
        }
    }

    public TileSpecRepositoryAdapter(CoroutineScope coroutineScope, CoroutineContext coroutineContext, QSPipelineLoggerEx qSPipelineLoggerEx, TileSpecStandardQSRepository tileSpecStandardQSRepository, TileSpecSeparateQSRepository tileSpecSeparateQSRepository) {
        this.loggerEx = qSPipelineLoggerEx;
        this.standardQSRepository = tileSpecStandardQSRepository;
        this.separateQSRepository = tileSpecSeparateQSRepository;
        StateFlow stateFlowStateIn = FlowKt.stateIn(FlowKt.flowOn(FlowKt.onEach(FlowKt.distinctUntilChanged(FlowKt.mapLatest(tileSpecSeparateQSRepository.isAvailable(), new TileSpecRepositoryAdapter$tileSpecRepository$1(this, null))), new TileSpecRepositoryAdapter$tileSpecRepository$2(this, null)), coroutineContext), coroutineScope, SharingStarted.Companion.getEagerly(), getTargetTileSpecRepository(((Boolean) tileSpecSeparateQSRepository.isAvailable().getValue()).booleanValue()));
        this.tileSpecRepository = stateFlowStateIn;
        this.tilesUpgradePath = ((TileSpecRepository) stateFlowStateIn.getValue()).getTilesUpgradePath();
        this.isAvailable = StateFlowKt.MutableStateFlow(Boolean.TRUE);
    }

    public Object addTile(int i, TileSpec tileSpec, int i2, Continuation continuation) {
        TileSpecRepository tileSpecRepository = (TileSpecRepository) this.tileSpecRepository.getValue();
        this.loggerEx.logAddTile(i, tileSpec, i2, tileSpecRepository);
        Object objAddTile = tileSpecRepository.addTile(i, tileSpec, i2, continuation);
        return objAddTile == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAddTile : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0069, code lost:
    
        if (r5.addTile(r6, r7, r8, r0) == r1) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object addTileSpec(int r6, com.android.systemui.qs.pipeline.shared.TileSpec r7, int r8, kotlin.coroutines.Continuation r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter$addTileSpec$1 r0 = (com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter$addTileSpec$1 r0 = new com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter$addTileSpec$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L43
            if (r2 == r4) goto L37
            if (r2 != r3) goto L30
            java.lang.Object r5 = r0.L$0
            com.android.systemui.qs.pipeline.shared.TileSpec r5 = (com.android.systemui.qs.pipeline.shared.TileSpec) r5
            kotlin.ResultKt.throwOnFailure(r9)
            goto L6c
        L30:
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r5)
            r5 = 0
            return r5
        L37:
            int r8 = r0.I$1
            int r6 = r0.I$0
            java.lang.Object r7 = r0.L$0
            com.android.systemui.qs.pipeline.shared.TileSpec r7 = (com.android.systemui.qs.pipeline.shared.TileSpec) r7
            kotlin.ResultKt.throwOnFailure(r9)
            goto L57
        L43:
            kotlin.ResultKt.throwOnFailure(r9)
            com.oplus.systemui.qs.pipeline.data.repository.TileSpecStandardQSRepository r9 = r5.standardQSRepository
            r0.L$0 = r7
            r0.I$0 = r6
            r0.I$1 = r8
            r0.label = r4
            java.lang.Object r9 = r9.addTile(r6, r7, r8, r0)
            if (r9 != r1) goto L57
            goto L6b
        L57:
            com.oplus.systemui.qs.pipeline.data.repository.TileSpecSeparateQSRepository r5 = r5.separateQSRepository
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r0.L$0 = r9
            r0.I$0 = r6
            r0.I$1 = r8
            r0.label = r3
            java.lang.Object r5 = r5.addTile(r6, r7, r8, r0)
            if (r5 != r1) goto L6c
        L6b:
            return r1
        L6c:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter.addTileSpec(int, com.android.systemui.qs.pipeline.shared.TileSpec, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final TileSpecRepository getTargetTileSpecRepository(boolean z) {
        return z ? this.separateQSRepository : this.standardQSRepository;
    }

    public ReceiveChannel getTilesUpgradePath() {
        return this.tilesUpgradePath;
    }

    public StateFlow isAvailable() {
        return this.isAvailable;
    }

    public Object modifyTiles(int i, List<? extends TileSpec> list, List<? extends TileSpecOperate> list2, Continuation continuation) {
        TileSpecRepository tileSpecRepository = (TileSpecRepository) this.tileSpecRepository.getValue();
        this.loggerEx.logModifyTiles(i, list, list2, tileSpecRepository);
        Object objModifyTiles = tileSpecRepository.modifyTiles(i, list, list2, continuation);
        return objModifyTiles == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objModifyTiles : Unit.INSTANCE;
    }

    public Object prependDefault(int i, Continuation continuation) {
        TileSpecRepository tileSpecRepository = (TileSpecRepository) this.tileSpecRepository.getValue();
        this.loggerEx.logPrependDefault(i, tileSpecRepository);
        Object objPrependDefault = tileSpecRepository.prependDefault(i, continuation);
        return objPrependDefault == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPrependDefault : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0088, code lost:
    
        if (r5.reconcileRestore(r6, r7, r0) == r1) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object reconcileRestore(com.android.systemui.qs.pipeline.data.model.RestoreData r6, java.util.Set<? extends com.android.systemui.qs.pipeline.shared.TileSpec> r7, kotlin.coroutines.Continuation r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter.C02601
            if (r0 == 0) goto L13
            r0 = r8
            com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter$reconcileRestore$1 r0 = (com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter.C02601) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter$reconcileRestore$1 r0 = new com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter$reconcileRestore$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L51
            if (r2 == r4) goto L3f
            if (r2 != r3) goto L38
            java.lang.Object r5 = r0.L$2
            com.oplus.systemui.qs.pipeline.data.repository.TileSpecStandardQSRepository r5 = (com.oplus.systemui.qs.pipeline.data.repository.TileSpecStandardQSRepository) r5
            java.lang.Object r5 = r0.L$1
            java.util.Set r5 = (java.util.Set) r5
            java.lang.Object r5 = r0.L$0
            com.android.systemui.qs.pipeline.data.model.RestoreData r5 = (com.android.systemui.qs.pipeline.data.model.RestoreData) r5
            kotlin.ResultKt.throwOnFailure(r8)
            goto L8b
        L38:
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r5)
            r5 = 0
            return r5
        L3f:
            java.lang.Object r6 = r0.L$2
            com.oplus.systemui.qs.pipeline.data.repository.TileSpecStandardQSRepository r6 = (com.oplus.systemui.qs.pipeline.data.repository.TileSpecStandardQSRepository) r6
            java.lang.Object r7 = r0.L$1
            java.util.Set r7 = (java.util.Set) r7
            java.lang.Object r2 = r0.L$0
            com.android.systemui.qs.pipeline.data.model.RestoreData r2 = (com.android.systemui.qs.pipeline.data.model.RestoreData) r2
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r6
            r6 = r2
            goto L6e
        L51:
            kotlin.ResultKt.throwOnFailure(r8)
            com.oplus.systemui.qs.pipeline.data.repository.TileSpecStandardQSRepository r8 = r5.standardQSRepository
            com.android.systemui.qs.pipeline.shared.QSPipelineLoggerEx r2 = r5.loggerEx
            r2.logReconcileRestore(r6, r7)
            r0.L$0 = r6
            r0.L$1 = r7
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r0.L$2 = r2
            r0.label = r4
            java.lang.Object r2 = r8.reconcileRestore(r6, r7, r0)
            if (r2 != r1) goto L6e
            goto L8a
        L6e:
            com.oplus.systemui.qs.pipeline.data.repository.TileSpecSeparateQSRepository r5 = r5.separateQSRepository
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r0.L$0 = r2
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r0.L$1 = r2
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r0.L$2 = r8
            r0.label = r3
            java.lang.Object r5 = r5.reconcileRestore(r6, r7, r0)
            if (r5 != r1) goto L8b
        L8a:
            return r1
        L8b:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter.reconcileRestore(com.android.systemui.qs.pipeline.data.model.RestoreData, java.util.Set, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public Object removePackage(String str, int i, Continuation continuation) {
        TileSpecRepository tileSpecRepository = (TileSpecRepository) this.tileSpecRepository.getValue();
        this.loggerEx.logRemovePackage(i, str, tileSpecRepository);
        Object objRemovePackage = tileSpecRepository.removePackage(str, i, continuation);
        return objRemovePackage == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRemovePackage : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0063, code lost:
    
        if (r5.removeTiles(r6, r7, r0) == r1) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object removeTileSpec(int r6, java.util.Collection<? extends com.android.systemui.qs.pipeline.shared.TileSpec> r7, kotlin.coroutines.Continuation r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter.C02611
            if (r0 == 0) goto L13
            r0 = r8
            com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter$removeTileSpec$1 r0 = (com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter.C02611) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter$removeTileSpec$1 r0 = new com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter$removeTileSpec$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L41
            if (r2 == r4) goto L37
            if (r2 != r3) goto L30
            java.lang.Object r5 = r0.L$0
            java.util.Collection r5 = (java.util.Collection) r5
            kotlin.ResultKt.throwOnFailure(r8)
            goto L66
        L30:
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r5)
            r5 = 0
            return r5
        L37:
            int r6 = r0.I$0
            java.lang.Object r7 = r0.L$0
            java.util.Collection r7 = (java.util.Collection) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L53
        L41:
            kotlin.ResultKt.throwOnFailure(r8)
            com.oplus.systemui.qs.pipeline.data.repository.TileSpecStandardQSRepository r8 = r5.standardQSRepository
            r0.L$0 = r7
            r0.I$0 = r6
            r0.label = r4
            java.lang.Object r8 = r8.removeTiles(r6, r7, r0)
            if (r8 != r1) goto L53
            goto L65
        L53:
            com.oplus.systemui.qs.pipeline.data.repository.TileSpecSeparateQSRepository r5 = r5.separateQSRepository
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r0.L$0 = r8
            r0.I$0 = r6
            r0.label = r3
            java.lang.Object r5 = r5.removeTiles(r6, r7, r0)
            if (r5 != r1) goto L66
        L65:
            return r1
        L66:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.qs.pipeline.data.adapter.TileSpecRepositoryAdapter.removeTileSpec(int, java.util.Collection, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public Object removeTiles(int i, Collection<? extends TileSpec> collection, Continuation continuation) {
        TileSpecRepository tileSpecRepository = (TileSpecRepository) this.tileSpecRepository.getValue();
        this.loggerEx.logRemoveTiles(i, collection, tileSpecRepository);
        Object objRemoveTiles = tileSpecRepository.removeTiles(i, collection, continuation);
        return objRemoveTiles == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRemoveTiles : Unit.INSTANCE;
    }

    public Object resetToDefault(int i, Continuation continuation) {
        TileSpecRepository tileSpecRepository = (TileSpecRepository) this.tileSpecRepository.getValue();
        this.loggerEx.logResetToDefault(i, tileSpecRepository);
        return tileSpecRepository.resetToDefault(i, continuation);
    }

    public Object setTiles(int i, List<? extends TileSpec> list, Continuation continuation) {
        TileSpecRepository tileSpecRepository = (TileSpecRepository) this.tileSpecRepository.getValue();
        this.loggerEx.logSetTiles(i, list, tileSpecRepository);
        Object tiles = tileSpecRepository.setTiles(i, list, continuation);
        return tiles == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? tiles : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public Object tilesSpecs(int i, Continuation continuation) {
        C02621 c02621;
        if (continuation instanceof C02621) {
            c02621 = (C02621) continuation;
            int i2 = c02621.label;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c02621.label = i2 - Integer.MIN_VALUE;
            } else {
                c02621 = new C02621(continuation);
            }
        } else {
            c02621 = new C02621(continuation);
        }
        Object obj = c02621.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c02621.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            TileSpecStandardQSRepository tileSpecStandardQSRepository = this.standardQSRepository;
            c02621.I$0 = i;
            c02621.label = 1;
            if (tileSpecStandardQSRepository.initTilesSpecs(i, c02621) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i3 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            i = c02621.I$0;
            ResultKt.throwOnFailure(obj);
        }
        return FlowKt.transformLatest(this.tileSpecRepository, new TileSpecRepositoryAdapter$tilesSpecs$$inlined$flatMapLatest$1(null, i));
    }
}

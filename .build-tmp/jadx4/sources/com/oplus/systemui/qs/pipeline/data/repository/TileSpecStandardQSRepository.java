package com.oplus.systemui.qs.pipeline.data.repository;

import com.android.systemui.qs.pipeline.data.model.RestoreData;
import com.android.systemui.qs.pipeline.data.repository.TileSpecRepository;
import com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository;
import com.android.systemui.qs.pipeline.shared.QSPipelineLoggerEx;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileSpecStandardQSRepository implements TileSpecRepository {
    public static final /* synthetic */ int $r8$clinit = 0;
    private final StateFlow isAvailable;
    private final QSPipelineLoggerEx loggerEx;
    private final TileSpecSettingsRepository settingsRepository;
    private final TileSpecSuperPowerRepository superPowerRepository;
    private final StateFlow tileSpecRepository;
    private final ReceiveChannel tilesUpgradePath;
    private final TileSpecWellBeingRepository wellBeingRepository;

    public TileSpecStandardQSRepository(CoroutineScope coroutineScope, CoroutineContext coroutineContext, QSPipelineLoggerEx qSPipelineLoggerEx, TileSpecSettingsRepository tileSpecSettingsRepository, TileSpecSuperPowerRepository tileSpecSuperPowerRepository, TileSpecWellBeingRepository tileSpecWellBeingRepository) {
        this.loggerEx = qSPipelineLoggerEx;
        this.settingsRepository = tileSpecSettingsRepository;
        this.superPowerRepository = tileSpecSuperPowerRepository;
        this.wellBeingRepository = tileSpecWellBeingRepository;
        StateFlow stateFlowStateIn = FlowKt.stateIn(FlowKt.flowOn(FlowKt.onEach(FlowKt.distinctUntilChanged(FlowKt.combine(tileSpecSuperPowerRepository.isAvailable(), tileSpecWellBeingRepository.isAvailable(), new TileSpecStandardQSRepository$tileSpecRepository$1(this, null))), new TileSpecStandardQSRepository$tileSpecRepository$2(this, null)), coroutineContext), coroutineScope, SharingStarted.Companion.getEagerly(), getTargetTileSpecRepository(((Boolean) tileSpecSuperPowerRepository.isAvailable().getValue()).booleanValue(), ((Boolean) tileSpecWellBeingRepository.isAvailable().getValue()).booleanValue()));
        this.tileSpecRepository = stateFlowStateIn;
        this.tilesUpgradePath = ((TileSpecRepository) stateFlowStateIn.getValue()).getTilesUpgradePath();
        this.isAvailable = StateFlowKt.MutableStateFlow(Boolean.TRUE);
    }

    public Object addTile(int i, TileSpec tileSpec, int i2, Continuation continuation) {
        Object objAddTile = ((TileSpecRepository) this.tileSpecRepository.getValue()).addTile(i, tileSpec, i2, continuation);
        return objAddTile == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAddTile : Unit.INSTANCE;
    }

    public final TileSpecRepository getTargetTileSpecRepository(boolean z, boolean z2) {
        if (z) {
            return this.superPowerRepository;
        }
        return z2 ? this.wellBeingRepository : this.settingsRepository;
    }

    public ReceiveChannel getTilesUpgradePath() {
        return this.tilesUpgradePath;
    }

    public final Object initTilesSpecs(int i, Continuation continuation) {
        Object objTilesSpecs = this.settingsRepository.tilesSpecs(i, continuation);
        return objTilesSpecs == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objTilesSpecs : Unit.INSTANCE;
    }

    public StateFlow isAvailable() {
        return this.isAvailable;
    }

    public /* bridge */ /* synthetic */ Object modifyTiles(int i, List list, List list2, Continuation continuation) {
        return super.modifyTiles(i, list, list2, continuation);
    }

    public Object prependDefault(int i, Continuation continuation) {
        Object objPrependDefault = ((TileSpecRepository) this.tileSpecRepository.getValue()).prependDefault(i, continuation);
        return objPrependDefault == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPrependDefault : Unit.INSTANCE;
    }

    public Object reconcileRestore(RestoreData restoreData, Set<? extends TileSpec> set, Continuation continuation) {
        Object objReconcileRestore = ((TileSpecRepository) this.tileSpecRepository.getValue()).reconcileRestore(restoreData, set, continuation);
        return objReconcileRestore == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objReconcileRestore : Unit.INSTANCE;
    }

    public Object removePackage(String str, int i, Continuation continuation) {
        Object objRemovePackage = ((TileSpecRepository) this.tileSpecRepository.getValue()).removePackage(str, i, continuation);
        return objRemovePackage == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRemovePackage : Unit.INSTANCE;
    }

    public Object removeTiles(int i, Collection<? extends TileSpec> collection, Continuation continuation) {
        Object objRemoveTiles = ((TileSpecRepository) this.tileSpecRepository.getValue()).removeTiles(i, collection, continuation);
        return objRemoveTiles == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRemoveTiles : Unit.INSTANCE;
    }

    public Object resetToDefault(int i, Continuation continuation) {
        return ((TileSpecRepository) this.tileSpecRepository.getValue()).resetToDefault(i, continuation);
    }

    public Object setTiles(int i, List<? extends TileSpec> list, Continuation continuation) {
        Object tiles = ((TileSpecRepository) this.tileSpecRepository.getValue()).setTiles(i, list, continuation);
        return tiles == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? tiles : Unit.INSTANCE;
    }

    public Object tilesSpecs(int i, Continuation continuation) {
        return FlowKt.transformLatest(this.tileSpecRepository, new TileSpecStandardQSRepository$tilesSpecs$$inlined$flatMapLatest$1(null, i));
    }
}

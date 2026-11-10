package com.oplus.systemui.qs.pipeline.domain.interactor;

import com.oplus.systemui.qs.pipeline.data.model.TileModifySignal;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusTileModifyInteractor$collectTilesModifyEvent$3 implements FlowCollector {
    public /* synthetic */ int $userId;
    public /* synthetic */ OplusTileModifyInteractor this$0;

    public final Object emit(Object obj, Continuation continuation) {
        TileModifySignal tileModifySignal = (TileModifySignal) obj;
        int i = this.$userId;
        OplusTileModifyInteractor oplusTileModifyInteractor = this.this$0;
        if (tileModifySignal instanceof TileModifySignal.Reset) {
            Object objResetTiles = oplusTileModifyInteractor.resetTiles(i, continuation);
            return objResetTiles == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objResetTiles : Unit.INSTANCE;
        }
        if (tileModifySignal instanceof TileModifySignal.Add) {
            TileModifySignal.Add add = (TileModifySignal.Add) tileModifySignal;
            Object objAddTile = oplusTileModifyInteractor.addTile(i, add.getSpec(), add.getPosition(), continuation);
            return objAddTile == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAddTile : Unit.INSTANCE;
        }
        if (tileModifySignal instanceof TileModifySignal.Remove) {
            Object objRemoveTiles = oplusTileModifyInteractor.removeTiles(i, ((TileModifySignal.Remove) tileModifySignal).getSpecs(), continuation);
            return objRemoveTiles == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRemoveTiles : Unit.INSTANCE;
        }
        LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
        return null;
    }
}

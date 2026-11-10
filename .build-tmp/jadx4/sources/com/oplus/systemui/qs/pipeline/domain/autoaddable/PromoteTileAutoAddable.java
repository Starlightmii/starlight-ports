package com.oplus.systemui.qs.pipeline.domain.autoaddable;

import com.android.systemui.qs.pipeline.data.model.RestoreData;
import com.android.systemui.qs.pipeline.data.model.RestoreProcessor;
import com.android.systemui.qs.pipeline.domain.model.AutoAddSignal;
import com.android.systemui.qs.pipeline.domain.model.AutoAddTracking;
import com.android.systemui.qs.pipeline.domain.model.AutoAddable;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class PromoteTileAutoAddable implements AutoAddable, RestoreProcessor {
    private final MutableSharedFlow extraSignalFlow = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7);
    private final TileSpec spec;

    public PromoteTileAutoAddable(TileSpec tileSpec) {
        this.spec = tileSpec;
    }

    public Flow autoAddSignal(int i) {
        boolean zIsPromoteTile = isPromoteTile();
        TileSpec tileSpec = this.spec;
        return FlowKt.merge(new Flow[]{zIsPromoteTile ? FlowKt.flowOf(new AutoAddSignal.Add(tileSpec, 0, 6)) : FlowKt.flowOf(new AutoAddSignal.RemoveTracking(tileSpec)), this.extraSignalFlow});
    }

    public AutoAddTracking getAutoAddTracking() {
        return isPromoteTile() ? new AutoAddTracking.IfNotAdded(this.spec) : AutoAddTracking.Always.INSTANCE;
    }

    public abstract /* synthetic */ String getDescription();

    public final TileSpec getSpec() {
        return this.spec;
    }

    public abstract boolean isPromoteTile();

    public Object postProcessRestore(RestoreData restoreData, Continuation continuation) {
        if (isPromoteTile() || !restoreData.getRestoredAutoAddedTiles().contains(this.spec)) {
            return Unit.INSTANCE;
        }
        Object objEmit = this.extraSignalFlow.emit(new AutoAddSignal.RemoveTracking(this.spec), continuation);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object preProcessRestore(RestoreData restoreData, Continuation continuation) {
        return super.preProcessRestore(restoreData, continuation);
    }
}

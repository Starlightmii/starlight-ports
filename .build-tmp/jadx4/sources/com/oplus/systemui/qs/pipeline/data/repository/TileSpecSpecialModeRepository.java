package com.oplus.systemui.qs.pipeline.data.repository;

import android.content.res.Resources;
import com.android.systemui.plugins.qs.TileSpecOperate;
import com.android.systemui.qs.pipeline.data.model.RestoreData;
import com.android.systemui.qs.pipeline.data.repository.TileSpecRepository;
import com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import java.lang.invoke.VarHandle;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class TileSpecSpecialModeRepository implements TileSpecRepository {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final /* synthetic */ TileSpecSettingsRepository $$delegate_0;
    private final Lazy specialModeTiles$delegate;

    public TileSpecSpecialModeRepository(Resources resources, TileSpecSettingsRepository tileSpecSettingsRepository, int i) {
        this.$$delegate_0 = tileSpecSettingsRepository;
        TileSpecSpecialModeRepository$$ExternalSyntheticLambda0 tileSpecSpecialModeRepository$$ExternalSyntheticLambda0 = new TileSpecSpecialModeRepository$$ExternalSyntheticLambda0();
        tileSpecSpecialModeRepository$$ExternalSyntheticLambda0.f$0 = resources;
        tileSpecSpecialModeRepository$$ExternalSyntheticLambda0.f$1 = i;
        VarHandle.storeStoreFence();
        this.specialModeTiles$delegate = LazyKt__LazyJVMKt.lazy(tileSpecSpecialModeRepository$$ExternalSyntheticLambda0);
    }

    public Object addTile(int i, TileSpec tileSpec, int i2, Continuation continuation) {
        return Unit.INSTANCE;
    }

    public final List<TileSpec> getSpecialModeTiles() {
        return (List) this.specialModeTiles$delegate.getValue();
    }

    public ReceiveChannel getTilesUpgradePath() {
        return this.$$delegate_0.getTilesUpgradePath();
    }

    public StateFlow isAvailable() {
        return this.$$delegate_0.isAvailable();
    }

    public Object modifyTiles(int i, List<? extends TileSpec> list, List<? extends TileSpecOperate> list2, Continuation continuation) {
        return this.$$delegate_0.modifyTiles(i, list, list2, continuation);
    }

    public Object prependDefault(int i, Continuation continuation) {
        return Unit.INSTANCE;
    }

    public Object reconcileRestore(RestoreData restoreData, Set<? extends TileSpec> set, Continuation continuation) {
        return this.$$delegate_0.reconcileRestore(restoreData, set, continuation);
    }

    public Object removePackage(String str, int i, Continuation continuation) {
        return this.$$delegate_0.removePackage(str, i, continuation);
    }

    public Object removeTiles(int i, Collection<? extends TileSpec> collection, Continuation continuation) {
        return Unit.INSTANCE;
    }

    public Object resetToDefault(int i, Continuation continuation) {
        return this.$$delegate_0.resetToDefault(i, continuation);
    }

    public Object setTiles(int i, List<? extends TileSpec> list, Continuation continuation) {
        return Unit.INSTANCE;
    }

    public Object tilesSpecs(int i, Continuation continuation) {
        return FlowKt.flowOf(getSpecialModeTiles());
    }
}

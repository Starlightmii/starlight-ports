package com.oplus.systemui.plugins.qs.addtile;

import com.oplus.systemui.plugins.qs.CellAreaProvider;
import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.plugins.qs.OplusQSRootView;
import com.oplus.systemui.plugins.qs.customize.viewmodel.StatisticHandler;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AddTileComponent_Factory implements Factory {
    public Provider cellAreaProvider;
    public InstanceFactory contextInstanceProvider;
    public Provider editViewModelProvider;
    public InstanceFactory qsRootViewProvider;
    public DoubleCheck statisticHandlerProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public AddTileComponent m118get() {
        return new AddTileComponent((OplusQSRootView) this.qsRootViewProvider.get(), (TileEditViewModel) this.editViewModelProvider.get(), (CellAreaProvider) this.cellAreaProvider.get(), (StatisticHandler) this.statisticHandlerProvider.get(), (ContextInstance) this.contextInstanceProvider.get());
    }
}

package com.oplus.systemui.plugins.qs.customize.viewmodel;

import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.plugins.qs.customize.SeparateQsTileListLogBuffer;
import com.oplus.systemui.plugins.qs.customize.SeparateQsTileListLogBuffer_Factory;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.AllTilesInteractor;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.PanelPluginInteractor;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileConfigRepoSwitcher;
import com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileEditInteractor;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileEditViewModel_Factory implements Factory {
    public DoubleCheck allTilesInteractorProvider;
    public InstanceFactory contextInstanceProvider;
    public DoubleCheck panelPluginInteractorProvider;
    public DoubleCheck separateQSHostAdapterProvider;
    public SeparateQsTileListLogBuffer_Factory separateQsTileLoggerProvider;
    public DoubleCheck statisticHandlerProvider;
    public DoubleCheck tileConfigRepoSwitcherProvider;
    public DoubleCheck tileEditInteractorProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public TileEditViewModel m178get() {
        return new TileEditViewModel((PanelPluginInteractor) this.panelPluginInteractorProvider.get(), (TileEditInteractor) this.tileEditInteractorProvider.get(), (SepQSHostAdapter) this.separateQSHostAdapterProvider.get(), (TileDataViewModelMapper) TileDataViewModelMapper_Factory.InstanceHolder.INSTANCE.get(), (AllTilesInteractor) this.allTilesInteractorProvider.get(), (TileConfigRepoSwitcher) this.tileConfigRepoSwitcherProvider.get(), (StatisticHandler) this.statisticHandlerProvider.get(), (ContextInstance) this.contextInstanceProvider.get(), (SeparateQsTileListLogBuffer) this.separateQsTileLoggerProvider.get());
    }
}

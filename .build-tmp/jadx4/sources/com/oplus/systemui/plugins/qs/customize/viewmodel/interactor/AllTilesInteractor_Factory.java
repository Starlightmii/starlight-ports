package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepStaticTileConfigRepository;
import com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AllTilesInteractor_Factory implements Factory {
    public Provider bgDispatcherProvider;
    public Provider contextInstanceProvider;
    public Provider mainScopeProvider;
    public DoubleCheck moreTileConfigRepoProvider;
    public Provider panelPluginInteractorProvider;
    public Provider separateQSHostAdapterProvider;
    public Provider separateQsPluginStubProvider;
    public Provider staticTileConfigRepoProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public AllTilesInteractor m180get() {
        return new AllTilesInteractor((ContextInstance) this.contextInstanceProvider.get(), (OplusSeparateQSPlugin.IStub) this.separateQsPluginStubProvider.get(), (PanelPluginInteractor) this.panelPluginInteractorProvider.get(), (SepQSHostAdapter) this.separateQSHostAdapterProvider.get(), (SepMoreTileConfigRepository) this.moreTileConfigRepoProvider.get(), (SepStaticTileConfigRepository) this.staticTileConfigRepoProvider.get(), (CoroutineScope) this.mainScopeProvider.get(), (CoroutineDispatcher) this.bgDispatcherProvider.get());
    }
}

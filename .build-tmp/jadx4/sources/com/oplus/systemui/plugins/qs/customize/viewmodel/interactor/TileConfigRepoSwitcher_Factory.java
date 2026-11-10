package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import android.os.Handler;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigRepository;
import com.oplus.systemui.plugins.qs.dagger.GlobalConcurrencyModule_ProvideMainHandlerFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileConfigRepoSwitcher_Factory implements Factory {
    public Provider bgDispatcherProvider;
    public GlobalConcurrencyModule_ProvideMainHandlerFactory mainHandlerProvider;
    public Provider mainScopeProvider;
    public DoubleCheck tileConfigRepositoryProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public TileConfigRepoSwitcher m184get() {
        return new TileConfigRepoSwitcher((SepTileConfigRepository) this.tileConfigRepositoryProvider.get(), (Handler) this.mainHandlerProvider.get(), (CoroutineScope) this.mainScopeProvider.get(), (CoroutineDispatcher) this.bgDispatcherProvider.get());
    }
}
